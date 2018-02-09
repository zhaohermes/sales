package org.yun.sales.security;

public class AccountCredentials {
	private String username;
	private String password;
	private AccountEnum accountEnum;
	
	public enum AccountEnum {
		/** 普通用户 */
		ROLE_USER,
		/** 管理员 */
		ROLE_ADMIN
	}

	public AccountCredentials(String username, String password, AccountEnum accountEnum) {
		this.username = username;
		this.password = password;
		this.accountEnum = accountEnum;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public AccountEnum getAccountEnum() {
		return accountEnum;
	}
	
}
