package org.yun.sales.security;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Token工具类
 */
public class TokenAuthenticationTool {
	/** 有效期(毫秒) */
	private static final long EXPIRATION_TIME = 1 * 60 * 60 * 1000;
	/** JWT密码 */
	private static final String SECRET = "Ifu*5fp4wK65Yb3&%9vT";
	/** Token前缀 */
	private static final String TOKEN_PREFIX = "Basic";
	/** 存放Token的Header中的Key */
	private static final String HEADER_STRING = "Authorization";

	/**
	 * 生成JWT并写入到Body
	 * @param auth
	 * @param userName
	 */
	public static String addAuthentication(Authentication auth, String userName) {
		StringBuilder authorities = new StringBuilder();
		auth.getAuthorities().forEach(aut -> {
			authorities.append(aut.getAuthority());
			authorities.append(",");
		});
		if(authorities.length() > 0){
			authorities.delete(authorities.length() - 1, authorities.length());
		}
		
		// 生成JWT
		final String JWT = Jwts.builder()
				// 保存权限（角色）
				.claim("authorities", authorities)
				// 用户名写入标题
				.setSubject(userName)
				// 有效期设置
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				// 签名设置
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		return JWT;
	}

	/**
	 * 验证Head中的JWT并生成验证令牌
	 * @param request
	 * @return Authentication 验证令牌
	 */
	public static Authentication getAuthentication(HttpServletRequest request) {
		// 从Header中拿到token
		String token = request.getHeader(HEADER_STRING);
		if (token == null) {
			return null;
		} else {
			// 去掉 Bearer
			token = token.replace(TOKEN_PREFIX, "");
			
			try {
				// 解析 Token
				JwtParser parser = Jwts.parser();
				// 验签
				parser.setSigningKey(SECRET);
				
				Claims claims = parser.parseClaimsJws(token).getBody();
				// 拿用户名
				String user = claims.getSubject();

				if(user == null){
					return null;
				}else{
					// 得到 权限（角色）
					List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
					// 返回验证令牌
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
					return authentication;
				}
			} catch (ExpiredJwtException e) {
				return null;
			}
		}
	}
}
