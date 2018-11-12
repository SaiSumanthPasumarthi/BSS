package com.arbiva.apsfl.broadcast.dto;

import java.io.Serializable;
import java.util.Date;

public class IptvChannelGroups implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String chnlCode;
	
	 private Date effectiveFrom;
	
	 private String broadcaster;
	
	private String priceGroupCode;

	public String getChnlCode() {
		return chnlCode;
	}

	public void setChnlCode(String chnlCode) {
		this.chnlCode = chnlCode;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

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
	
	

}
