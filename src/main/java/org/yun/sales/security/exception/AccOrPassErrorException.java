package org.yun.sales.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户名或密码错误
 */
public class AccOrPassErrorException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public AccOrPassErrorException() {
		super("user name or password error.");
	}
	
}
