package org.yun.sales.entity;

import java.time.LocalDateTime;

/**
 * 用户
 */
public class User {
	/** 编号 */
	private long id;
	/** 账号 */
	private String acc;
	/** 密码 */
	private String pwd;
	/** 最后登录时间 */
	private LocalDateTime lastLoginTime;
	/** 创建时间 */
	private LocalDateTime createTime;
	
	public User() {}

	public User(long id, String acc, String pwd, LocalDateTime createTime) {
		this.id = id;
		this.acc = acc;
		this.pwd = pwd;
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}

	public String getAcc() {
		return acc;
	}

	public String getPwd() {
		return pwd;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

}
