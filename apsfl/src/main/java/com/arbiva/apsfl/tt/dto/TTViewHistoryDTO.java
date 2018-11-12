package com.arbiva.apsfl.tt.dto;

import com.arbiva.apsfl.tt.dto.TTHistoryDTO;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class TTViewHistoryDTO {

	private Timestamp closedDate;
	
	private String timeDifference;
	
	private BigInteger ticketNo;
	
	private String ticketDesc;
	
	private String statusDesc;
	
	private List<TTHistoryDTO> ttHistoryList;

	public Timestamp getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Timestamp closedDate) {
		this.closedDate = closedDate;
	}

	public String getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(String timeDifference) {
		this.timeDifference = timeDifference;
	}

	

	public List<TTHistoryDTO> getTtHistoryList() {
		return ttHistoryList;
	}

	public void setTtHistoryList(List<TTHistoryDTO> ttHistoryList) {
		this.ttHistoryList = ttHistoryList;
	}

	public String getTicketDesc() {
		return ticketDesc;
	}

	public void setTicketDesc(String ticketDesc) {
		this.ticketDesc = ticketDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public BigInteger getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(BigInteger ticketNo) {
		this.ticketNo = ticketNo;
	}
}
