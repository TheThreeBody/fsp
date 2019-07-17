package com.zhengtong.fsp.manager.pojo.jd;

import java.io.Serializable;

public class CommonResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5787151984961068855L;

	private int isSuccess;

	private Object data;

	private String msg;

	public static CommonResponse newSuccessResponse() {
		CommonResponse exchangeResponse = new CommonResponse();
		exchangeResponse.setIsSuccess(1);
		return exchangeResponse;
	}

	public static CommonResponse newFailedResponse() {
		CommonResponse exchangeResponse = new CommonResponse();
		exchangeResponse.setIsSuccess(0);
		return exchangeResponse;
	}

	private CommonResponse() {

	}

	public int getIsSuccess() {
		return isSuccess;
	}

	private void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Object getData() {
		return data;
	}

	public CommonResponse setData(Object data) {
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public CommonResponse setMsg(String msg) {
		this.msg = msg;
		return this;
	}

}
