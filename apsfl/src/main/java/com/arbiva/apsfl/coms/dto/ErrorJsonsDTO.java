package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

public class ErrorJsonsDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String req;
	private String resp;
	private String callType;
	private String url;
	private String status;
	
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
