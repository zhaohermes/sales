package org.yun.sales.enums;

/**
 * 返回值枚举类
 */
public enum ResultCode {
    /** 成功 */
    SUCCESS(200, "成功"),
    /** 系统错误 */
    SYSTEM_ERROR(500, "系统错误"),
	/** 账号或密码错误 */
	ACC_OR_PASS_ERROR(501, "账号或密码错误");

	/** 状态码 */
    public final int code;
    /** 状态说明 */
    public final String msg;

    ResultCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}

