package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubstationWiseCafBo implements Serializable {
	
	private static final long serialVersionUID = 2L;

	@Column(name = "substnname")
	private String popName;
	
	@Id
	@Column(name="pop_olt_ipaddress")
	private String pop_olt_ipaddress;
	
	@Column(name="districtname")
	private String districtname;
	
	@Column(name="mandalname")
	private String mandalname;

	@Id
	@Column(name = "portno")
	private Integer portNo;
	
	@Column(name = "cafcount")
	private Long cafCount;
	
	@Id
	@Column(name = "pop_olt_serialno")
	private String pop_olt_serialno;
	
	@Column(name = "cardid")
	private Integer cardid;

	public String getPopName() {
		return popName;
	}

	public void setPopName(String popName) {
		this.popName = popName;
	}

	public String getPop_olt_ipaddress() {
		return pop_olt_ipaddress;
	}

	public void setPop_olt_ipaddress(String pop_olt_ipaddress) {
		this.pop_olt_ipaddress = pop_olt_ipaddress;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getMandalname() {
		return mandalname;
	}

	public void setMandalname(String mandalname) {
		this.mandalname = mandalname;
	}

	public Integer getPortNo() {
		return portNo;
	}

	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}

	public Long getCafCount() {
		return cafCount;
	}

	public void setCafCount(Long cafCount) {
		this.cafCount = cafCount;
	}

	public String getPop_olt_serialno() {
		return pop_olt_serialno;
	}

	public void setPop_olt_serialno(String pop_olt_serialno) {
		this.pop_olt_serialno = pop_olt_serialno;
	}

	public Integer getCardid() {
		return cardid;
	}

	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
}
