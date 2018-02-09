package org.yun.sales.security;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.yun.sales.entity.User;
import org.yun.sales.security.AccountCredentials.AccountEnum;
import org.yun.sales.security.exception.AccOrPassErrorException;
import org.yun.sales.security.exception.LoginErrorException;
import org.yun.sales.service.IUserService;
import org.yun.sales.utils.Tools;

/**
 * 身份认证验证组件
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
	@Resource
	private IUserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 获取认证的用户名 & 密码
		AccountCredentials creds = (AccountCredentials)authentication.getPrincipal();
		String acc = creds.getUsername();
		String pwd = authentication.getCredentials().toString();
		
		// 这里设置权限和角色
		ArrayList<GrantedAuthority> authorities = null;
		if(creds.getAccountEnum() == AccountEnum.ROLE_USER){
			User user;
			try {
				user = userService.login(acc, Tools.MD5(acc + pwd));
			} catch (Exception e) {
				throw new LoginErrorException("user login error.", e);
			}
			
			if (user == null) {
				throw new AccOrPassErrorException();
			} else {
				authorities = new ArrayList<>();
				authorities.add(new GrantedAuthorityImpl(AccountEnum.ROLE_USER.name()));
			}
		}
		
		// 生成令牌
		Authentication auth = new UsernamePasswordAuthenticationToken(acc, pwd, authorities);
		return auth;
	}

	@Override
	public boolean supports(Class<?> clazz) {// 是否可以提供输入类型的认证服务
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}

}
