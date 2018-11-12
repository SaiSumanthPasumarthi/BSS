package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rstmplmst")
public class RevenueSharingTemplateMaster implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "rstmplcode", unique = true, nullable = false)
	private String rstmplCode;
	
	@Column(name = "rstmplname")
	private String rstmplName;
	
	@Column(name = "partnercnt")
	private int partnerCnt;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "rstmpltypelov")
	private String templType;
	
	@Transient
	private List<String> chargeCodes;
	
	
	
	
	public List<String> getChargeCodes() {
		return chargeCodes;
	}

	public void setChargeCodes(List<String> chargeCodes) {
		this.chargeCodes = chargeCodes;
	}

	public String getTemplType() {
		return templType;
	}

	public void setTemplType(String templType) {
		this.templType = templType;
	}

	public String getRstmplCode() {
		return rstmplCode;
	}

	public void setRstmplCode(String rstmplCode) {
		this.rstmplCode = rstmplCode;
	}

	public String getRstmplName() {
		return rstmplName;
	}

	public void setRstmplName(String rstmplName) {
		this.rstmplName = rstmplName;
	}

	public int getPartnerCnt() {
		return partnerCnt;
	}

	public void setPartnerCnt(int partnerCnt) {
		this.partnerCnt = partnerCnt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
