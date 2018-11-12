package com.arbiva.apsfl.tms.dto;

public class CpeDeliveryDTO {
	
	private String dlvid;
	private String dlvDate;
	private String dlvQty;
	private String isPaid;
	
	
	public String getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}
	public String getDlvid() {
		return dlvid;
	}
	public void setDlvid(String dlvid) {
		this.dlvid = dlvid;
	}
	public String getDlvDate() {
		return dlvDate;
	}
	public void setDlvDate(String dlvDate) {
		this.dlvDate = dlvDate;
	}
	public String getDlvQty() {
		return dlvQty;
	}
	public void setDlvQty(String dlvQty) {
		this.dlvQty = dlvQty;
	}

}
