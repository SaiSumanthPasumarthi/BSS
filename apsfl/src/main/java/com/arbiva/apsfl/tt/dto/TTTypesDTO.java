package com.arbiva.apsfl.tt.dto;

import java.io.Serializable;
import java.util.List;


public class TTTypesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String successMessage;
	
	private String success;
	
	private List<TTTypesVO> ttTypes;
	
	private List<TTIssueTypesVO> ttIssueTypes;
	
	private List<TTIssuesVO> ttIssues;
	
	private List<TTHistoryByIdDTO> ttHistory;

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public List<TTTypesVO> getTtTypes() {
		return ttTypes;
	}

	public void setTtTypes(List<TTTypesVO> ttTypes) {
		this.ttTypes = ttTypes;
	}

	public List<TTIssueTypesVO> getTtIssueTypes() {
		return ttIssueTypes;
	}

	public void setTtIssueTypes(List<TTIssueTypesVO> ttIssueTypes) {
		this.ttIssueTypes = ttIssueTypes;
	}

	public List<TTIssuesVO> getTtIssues() {
		return ttIssues;
	}

	public void setTtIssues(List<TTIssuesVO> ttIssues) {
		this.ttIssues = ttIssues;
	}

	public List<TTHistoryByIdDTO> getTtHistory() {
		return ttHistory;
	}

	public void setTtHistory(List<TTHistoryByIdDTO> ttHistory) {
		this.ttHistory = ttHistory;
	}
	
	

}
