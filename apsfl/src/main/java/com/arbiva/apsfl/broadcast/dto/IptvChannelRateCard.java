package com.arbiva.apsfl.broadcast.dto;

import java.io.Serializable;
import java.util.Date;

public class IptvChannelRateCard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 private String broadcaster;
	
	private String priceGroupCode;
	 private Date effectiveFrom;
	
	 private float unitPrice;

	public String getBroadcaster() {
		return broadcaster;
	}

	public void setBroadcaster(String broadcaster) {
		this.broadcaster = broadcaster;
	}

	public String getPriceGroupCode() {
		return priceGroupCode;
	}

	public void setPriceGroupCode(String priceGroupCode) {
		this.priceGroupCode = priceGroupCode;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

}
