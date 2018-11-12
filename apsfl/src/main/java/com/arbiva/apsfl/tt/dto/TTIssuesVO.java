package com.arbiva.apsfl.tt.dto;

import java.io.Serializable;

public class TTIssuesVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String issueName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	
	

}
