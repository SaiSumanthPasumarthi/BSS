package com.arbiva.apsfl.tt.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ViewBillDTO {

	private BigInteger billNo;
	private String billDate;
	private String billPeriod;
	private String billAmount;
	private String dueDate;
	private String billToDate;
	private String billFromDate;
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getBillFromDate() {
		return billFromDate;
	}
	public void setBillFromDate(String billFromDate) {
		this.billFromDate = billFromDate;
	}
	public String getBillToDate() {
		return billToDate;
	}
	public void setBillToDate(String billToDate) {
		this.billToDate = billToDate;
	}
	public BigInteger getBillNo() {
		return billNo;
	}
	public void setBillNo(BigInteger billNo) {
		this.billNo = billNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillPeriod() {
		return billPeriod;
	}
	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}
	public String getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
}
