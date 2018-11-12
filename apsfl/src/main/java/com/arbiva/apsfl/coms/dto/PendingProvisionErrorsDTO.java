package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

public class PendingProvisionErrorsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createdDate;
	private String executedDate;
	private String req;
	private String requestid;
	private String resp;
	private String status;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getExecutedDate() {
		return executedDate;
	}

	public void setExecutedDate(String executedDate) {
		this.executedDate = executedDate;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
