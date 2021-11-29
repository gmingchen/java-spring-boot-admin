package com.slipper.common.exception;

import com.slipper.common.utils.Constant;

import java.io.Serializable;

/**
 * 自定义异常
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class RunException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	private int code = Constant.ERROR_CODE;
    private String message = Constant.ERROR_MESSAGE;

	public RunException(int code) {
		super();
		this.code = code;
	}

	public RunException(int code,  Throwable e) {
		super(e);
		this.code = code;
	}

	public RunException(String message) {
		super();
		this.message = message;
	}

	public RunException(String message,  Throwable e) {
		super(e);
		this.message = message;
	}

	public RunException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public RunException(int code, String message, Throwable e) {
		super(e);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
