package com.cxytiandi.elementui.base;

public class ResponseData {
	private Boolean status = true;
	private int code = 200;
	private String message;
	private Object data;
	
	public static ResponseData ok(Object data) {
		return new ResponseData(data);
	}
	
	public static ResponseData fail() {
		return new ResponseData(false, null);
	}
	
	public static ResponseData fail(String message) {
		return new ResponseData(false, message);
	}
	
	public static ResponseData fail(String message, int code) {
		return new ResponseData(false, message, code);
	}
	
	public static ResponseData failByParam(String message) {
		return new ResponseData(false, message, ResponseCode.PARAM_ERROR_CODE.getCode());
	}
	
	public ResponseData(Object data) {
		super();
		this.data = data;
	}
	
	public ResponseData(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ResponseData(boolean status, String message, int code) {
		super();
		this.status = status;
		this.message = message;
		this.code = code;
	}
	
	
	public ResponseData() {
		super();
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
