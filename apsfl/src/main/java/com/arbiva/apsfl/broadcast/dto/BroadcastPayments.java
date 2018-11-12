package com.arbiva.apsfl.broadcast.dto;

import java.io.Serializable;
import java.util.Date;

public class BroadcastPayments implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String paymentRefNo;
	
	private Date paymentDate;
	
	private float paymentAmount;
	
	private Long invoiceNo;

	public String getPaymentRefNo() {
		return paymentRefNo;
	}

	public void setPaymentRefNo(String paymentRefNo) {
		this.paymentRefNo = paymentRefNo;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Long getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	

}
