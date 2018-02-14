package org.yun.sales.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.yun.sales.enums.ResultCode;
import org.yun.sales.security.exception.AccOrPassErrorException;
import org.yun.sales.security.exception.LoginErrorException;
import org.yun.sales.utils.Tools;
import org.yun.sales.vo.JsonResult;

/**
 * 拦截用户登录请求
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	private final Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
		//登录时需要验证时候调用
		String acc = req.getParameter("acc");
		String pwd = req.getParameter("pwd");
		String type = req.getParameter("type");
		AccountCredentials.AccountEnum accountType;
		try {
			accountType = "admin".equals(type) ? AccountCredentials.AccountEnum.ROLE_ADMIN : AccountCredentials.AccountEnum.ROLE_USER;
		} catch (Exception e) {
			logger.error("account type parameter error.", e);
			accountType = null;
		}
		
		// JSON反序列化成 AccountCredentials
		//new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);
		AccountCredentials creds = new AccountCredentials(acc, pwd, accountType);
		
		// 返回一个验证令牌
		Authentication authentication = new UsernamePasswordAuthenticationToken(creds, creds.getPassword());
		authentication = getAuthenticationManager().authenticate(authentication);
		return authentication;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		String acc = auth.getName();
		String jwt = TokenAuthenticationTool.addAuthentication(auth, acc);
		
		Map<String, Object> map = new HashMap<>();
		map.put("acc", acc);
		map.put("token", jwt);
		
		JsonResult result = new JsonResult(ResultCode.SUCCESS, map);
		String msg = Tools.getJsonString(result);
		
		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().println(msg);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException exc) throws IOException, ServletException {
		JsonResult result;
		if(exc instanceof AccOrPassErrorException){
			result = new JsonResult(ResultCode.ACC_OR_PASS_ERROR, "登录失败，帐号或密码错误!");
		}else if(exc instanceof LoginErrorException){
			logger.error("", exc);
			result = new JsonResult(ResultCode.SYSTEM_ERROR, "登录失败，请重新登录!");
		}else{
			logger.error("", exc);
			result = new JsonResult(ResultCode.SYSTEM_ERROR);
		}
		String msg = Tools.getJsonString(result);
		
		res.setContentType("application/json;charset=UTF-8");
		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().println(msg);
	}

}
