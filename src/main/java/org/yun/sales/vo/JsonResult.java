package org.yun.sales.vo;

import org.yun.sales.enums.ResultCode;

/**
 * 返回页面对象
 */
public class JsonResult {
    private int code;
    private String message;
    private Object data;

    public JsonResult(ResultCode result) {
        this.code = result.code;
        this.message = result.msg;
    }

    public JsonResult(ResultCode result, String message) {
    	this.code = result.code;
        this.message = message;
    }
    
    public JsonResult(ResultCode result, Object data) {
        this(result);
        this.data = data;
    }

    public JsonResult(ResultCode result, String message, Object data) {
        this(result, message);
        this.data = data;
    }

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

}

