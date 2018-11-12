package com.arbiva.apsfl.coms.dto;

public class TicketDetailsVO {
	
	private String ticket_no;
	
	private String submitted_on;
	
	private String description;
	
	private String status;
	
	private String closed_on;
	
	private String statusId;

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getTicket_no() {
		return ticket_no;
	}

	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}

	public String getSubmitted_on() {
		return submitted_on;
	}

	public void setSubmitted_on(String submitted_on) {
		this.submitted_on = submitted_on;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClosed_on() {
		return closed_on;
	}

	public void setClosed_on(String closed_on) {
		this.closed_on = closed_on;
	}
	
}
