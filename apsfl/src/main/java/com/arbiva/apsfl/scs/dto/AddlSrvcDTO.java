package com.arbiva.apsfl.scs.dto;

public class AddlSrvcDTO {
	
	private String srvcCode;
	private String srvcName;
	private String coreSrvcName;
	private String createdby;
	private String createdon;
	private String coresrvcCode;
	private String effectivefrom;
	private String effectiveTo;
	private String status;
	private String allChannels;
	private String addedChannels;
	private String deltedChannels;
	
	
	
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	public String getAllChannels() {
		return allChannels;
	}
	public void setAllChannels(String allChannels) {
		this.allChannels = allChannels;
	}
	public String getAddedChannels() {
		return addedChannels;
	}
	public void setAddedChannels(String addedChannels) {
		this.addedChannels = addedChannels;
	}
	public String getDeltedChannels() {
		return deltedChannels;
	}
	public void setDeltedChannels(String deltedChannels) {
		this.deltedChannels = deltedChannels;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCoresrvcCode() {
		return coresrvcCode;
	}
	public void setCoresrvcCode(String coresrvcCode) {
		this.coresrvcCode = coresrvcCode;
	}
	public String getSrvcCode() {
		return srvcCode;
	}
	public void setSrvcCode(String srvcCode) {
		this.srvcCode = srvcCode;
	}
	public String getSrvcName() {
		return srvcName;
	}
	public void setSrvcName(String srvcName) {
		this.srvcName = srvcName;
	}
	public String getCoreSrvcName() {
		return coreSrvcName;
	}
	public void setCoreSrvcName(String coreSrvcName) {
		this.coreSrvcName = coreSrvcName;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	public String getEffectivefrom() {
		return effectivefrom;
	}
	public void setEffectivefrom(String effectivefrom) {
		this.effectivefrom = effectivefrom;
	}
	
	

}
