package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

public class SelectPaymentVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String paymentMode;
	private String addAmt1;
	private String custId1;

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getAddAmt1() {
		return addAmt1;
	}

	public void setAddAmt1(String addAmt1) {
		this.addAmt1 = addAmt1;
	}

	public String getCustId1() {
		return custId1;
	}

	public void setCustId1(String custId1) {
		this.custId1 = custId1;
	}
}
