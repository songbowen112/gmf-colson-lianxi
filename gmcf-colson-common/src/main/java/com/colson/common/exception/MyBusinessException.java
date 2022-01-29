package com.colson.common.exception;


import com.colson.common.code.RspCode;

/**
 * 本地异常
 */
public class MyBusinessException extends BaseBusinessException {

	private static final long serialVersionUID = 1L;

	public MyBusinessException(String msg, String code, Exception ex, String jsonContent) {
		super(msg, code, ex, jsonContent);
	}

	public MyBusinessException(String msg, String code, String jsonContent) {
		super(msg, code, jsonContent);
	}

	public MyBusinessException(String msg, String code) {
		super(msg, code);
	}

	public MyBusinessException(RspCode respCode) {
		super(respCode.getMessage(), respCode.getCode());
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}


}
