package com.arbiva.apfgc.tenant.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MsoWiseCpeBo {

	@Id
	@Column(name="mspcode")
	private String  mspcode;
	
	@Column(name="maxdate")
	private String maxdat;
	
	@Column(name="mspname")
	private String mspname;
	
	@Column(name="districtname")
	private String districtname;
	
	@Column(name="dqty0")
	private Long  dqty0;
	
	@Column(name="dqty36")
	private Long dqty36;
	
	@Column(name="dqty48")
	private Long dqty48;
	
	@Column(name="cqty0")
	private Long cqty0;
	
	@Column(name="cqty36")
	private Long cqty36;
	
	@Column(name="cqty48")
	private Long cqty48;

	public String getMspcode() {
		return mspcode;
	}

	public void setMspcode(String mspcode) {
		this.mspcode = mspcode;
	}

	public String getMaxdat() {
		return maxdat;
	}

	public void setMaxdat(String maxdat) {
		this.maxdat = maxdat;
	}

	public String getMspname() {
		return mspname;
	}

	public void setMspname(String mspname) {
		this.mspname = mspname;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public Long getDqty0() {
		return dqty0;
	}

	public void setDqty0(Long dqty0) {
		this.dqty0 = dqty0;
	}

	public Long getDqty36() {
		return dqty36;
	}

	public void setDqty36(Long dqty36) {
		this.dqty36 = dqty36;
	}

	public Long getDqty48() {
		return dqty48;
	}

	public void setDqty48(Long dqty48) {
		this.dqty48 = dqty48;
	}

	public Long getCqty0() {
		return cqty0;
	}

	public void setCqty0(Long cqty0) {
		this.cqty0 = cqty0;
	}

	public Long getCqty36() {
		return cqty36;
	}

	public void setCqty36(Long cqty36) {
		this.cqty36 = cqty36;
	}

	public Long getCqty48() {
		return cqty48;
	}

	public void setCqty48(Long cqty48) {
		this.cqty48 = cqty48;
	}
	
	public Long getDaySum(){
		return dqty0+dqty36+dqty48;
	}
	
	public Long getCumulativeSum(){
		return cqty0+cqty36+cqty48;
	}
}
