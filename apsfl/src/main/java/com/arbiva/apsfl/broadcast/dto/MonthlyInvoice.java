package com.arbiva.apsfl.broadcast.dto;


import java.io.Serializable;
import java.util.Date;

public class MonthlyInvoice  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	public MonthlyInvoice() {
		
	}

	private Long invoiceNumber;
	
	private String broadcasterCode;
	
	private Date invoiceDate;
	
	private int invoiceMonth;
	
	private float invoiceAmount;
	
	private float serviceTax;
	
	private float swatchTax;
	
	private float kissanTax;

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getBroadcasterCode() {
		return broadcasterCode;
	}

	public void setBroadcasterCode(String broadcasterCode) {
		this.broadcasterCode = broadcasterCode;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getInvoiceMonth() {
		return invoiceMonth;
	}

	public void setInvoiceMonth(int invoiceMonth) {
		this.invoiceMonth = invoiceMonth;
	}

	public float getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public float getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(float serviceTax) {
		this.serviceTax = serviceTax;
	}

	public float getSwatchTax() {
		return swatchTax;
	}

	public void setSwatchTax(float swatchTax) {
		this.swatchTax = swatchTax;
	}

	public float getKissanTax() {
		return kissanTax;
	}

	public void setKissanTax(float kissanTax) {
		this.kissanTax = kissanTax;
	}
	
	
}
