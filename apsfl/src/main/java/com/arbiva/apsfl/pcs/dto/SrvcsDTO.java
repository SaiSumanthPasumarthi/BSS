package com.arbiva.apsfl.pcs.dto;

import java.util.Calendar;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SrvcsDTO {
	
	
    private String srvccode;


    private String srvcname;

    private String coresrvccode;

    private Short status;
    private Calendar createdon;

    private String createdby;

    private String createdipaddr;

    private Calendar modifiedon;

    private String modifiedby;

    private String modifiedipaddr;
	
	private Double totalTax;
	
	private Character multSrvcsAllowed;
	
    private String coresrvcName;
	
	
	
	public String getCoresrvcName() {
		return coresrvcName;
	}

	public void setCoresrvcName(String coresrvcName) {
		this.coresrvcName = coresrvcName;
	}

	public Character getMultSrvcsAllowed() {
		return multSrvcsAllowed;
	}

	public void setMultSrvcsAllowed(Character multSrvcsAllowed) {
		this.multSrvcsAllowed = multSrvcsAllowed;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public String getSrvccode() {
        return this.srvccode;
    }

	public void setSrvccode(String id) {
        this.srvccode = id;
    }

	public String getSrvcname() {
        return srvcname;
    }

	public void setSrvcname(String srvcname) {
        this.srvcname = srvcname;
    }

	public String getCoresrvccode() {
        return coresrvccode;
    }

	public void setCoresrvccode(String coresrvccode) {
        this.coresrvccode = coresrvccode;
    }

	public Short getStatus() {
        return status;
    }

	public void setStatus(Short status) {
        this.status = status;
    }

	public Calendar getCreatedon() {
        return createdon;
    }

	public void setCreatedon(Calendar createdon) {
        this.createdon = createdon;
    }

	public String getCreatedby() {
        return createdby;
    }

	public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

	public String getCreatedipaddr() {
        return createdipaddr;
    }

	public void setCreatedipaddr(String createdipaddr) {
        this.createdipaddr = createdipaddr;
    }

	public Calendar getModifiedon() {
        return modifiedon;
    }

	public void setModifiedon(Calendar modifiedon) {
        this.modifiedon = modifiedon;
    }

	public String getModifiedby() {
        return modifiedby;
    }

	public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

	public String getModifiedipaddr() {
        return modifiedipaddr;
    }

	public void setModifiedipaddr(String modifiedipaddr) {
        this.modifiedipaddr = modifiedipaddr;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	
}
