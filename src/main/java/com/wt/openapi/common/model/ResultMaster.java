package com.wt.openapi.common.model;

public class ResultMaster {

	public ResultCode result;
	public Object body = null;
	
	public ResultCode getResult() {
		return result;
	}
	public void setResult(ResultCode result) {
		this.result = result;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
}
