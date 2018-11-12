package com.arbiva.apsfl.broadcast.dto;

import java.io.Serializable;

public class InvoiceDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long invoiceNumber;
	
	private String priceGroupCode;
	
	private int subscriberCount;
	
	private float unitPrice;
	
	private float serviceTax;
	
	private float swatchTax;
	
	private float kissanTax;

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getPriceGroupCode() {
		return priceGroupCode;
	}

	public void setPriceGroupCode(String priceGroupCode) {
		this.priceGroupCode = priceGroupCode;
	}

	public int getSubscriberCount() {
		return subscriberCount;
	}

	public void setSubscriberCount(int subscriberCount) {
		this.subscriberCount = subscriberCount;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
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
