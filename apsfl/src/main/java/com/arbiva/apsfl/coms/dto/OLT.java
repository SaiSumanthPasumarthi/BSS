/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Arbiva
 *
 */
public class OLT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long popId;
	
	private String popName;
	
	private String popType;
	
	private String popOltSerialno;
	
	private String popOltIpaddress;
	
	private String pop_oltlabelno;
	
	private int status;
	
	private String oltPortId;
	
	private String popSubstnuid;
	
	private String substnname;
	
	private String subscriberCount;
	
	private String substnuid;
	
	private String oltAccessNode;
	
	
	private String createdon;
	
	private String createdby;
	
	private String modifiedon;
	
	private String modifiedby;
	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOltAccessNode() {
		return oltAccessNode;
	}

	public void setOltAccessNode(String oltAccessNode) {
		this.oltAccessNode = oltAccessNode;
	}

	public String getOltPortId() {
		return oltPortId;
	}

	public void setOltPortId(String oltPortId) {
		this.oltPortId = oltPortId;
	}

	public String getPop_oltlabelno() {
		return pop_oltlabelno;
	}

	public void setPop_oltlabelno(String pop_oltlabelno) {
		this.pop_oltlabelno = pop_oltlabelno;
	}
	
	public String getPopSubstnuid() {
		return popSubstnuid;
	}

	public void setPopSubstnuid(String popSubstnuid) {
		this.popSubstnuid = popSubstnuid;
	}

	public Long getPopId() {
		return popId;
	}

	public void setPopId(Long popId) {
		this.popId = popId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPopName() {
		return popName;
	}

	public void setPopName(String popName) {
		this.popName = popName;
	}

	public String getPopType() {
		return popType;
	}

	public void setPopType(String popType) {
		this.popType = popType;
	}

	public String getPopOltSerialno() {
		return popOltSerialno;
	}

	public void setPopOltSerialno(String popOltSerialno) {
		this.popOltSerialno = popOltSerialno;
	}

	public String getPopOltIpaddress() {
		return popOltIpaddress;
	}

	public void setPopOltIpaddress(String popOltIpaddress) {
		this.popOltIpaddress = popOltIpaddress;
	}

	public String getSubstnname() {
		return substnname;
	}

	public void setSubstnname(String substnname) {
		this.substnname = substnname;
	}

	public String getSubscriberCount() {
		return subscriberCount;
	}

	public void setSubscriberCount(String subscriberCount) {
		this.subscriberCount = subscriberCount;
	}

	public String getSubstnuid() {
		return substnuid;
	}

	public void setSubstnuid(String substnuid) {
		this.substnuid = substnuid;
	}

}
