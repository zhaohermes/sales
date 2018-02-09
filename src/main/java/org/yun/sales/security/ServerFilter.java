package org.yun.sales.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

public class ServerFilter extends FilterSecurityInterceptor {
//	private RequestMatcher requiresAuthenticationRequestMatcher;
	
	public ServerFilter(String url, AuthenticationManager authManager) {
//		requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(url);
		super.setAuthenticationManager(authManager);
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 跨域设置
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		
		chain.doFilter(request, response);
		
//		if(!requiresAuthentication(req, res)){
//			chain.doFilter(request, response);
//			return;
//		}
	}
	
//	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
//		return requiresAuthenticationRequestMatcher.matches(request);
//	}
	
}
