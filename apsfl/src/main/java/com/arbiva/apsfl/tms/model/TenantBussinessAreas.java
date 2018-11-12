package com.arbiva.apsfl.tms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tenantbusareas")
@IdClass(TenantBussinessAreasPK.class)
public class TenantBussinessAreas {
	
	@Id
	@Column(name = "tenantcode")
	private String tenantcode;
	
	@Id
	@Column(name = "substnuid")
	private String substnuid;
	
	@Id
	@Column(name = "stateid")
	private int stateid = 1;
	
	@Id
	@Column(name = "districtuid")
	private int districtuid;
	
	@Id
	@Column(name = "mandalslno")
	private int mandalslno;
	
	@Id
	@Column(name = "villageslno")
	private int villageslno;
	
	@Column(name = "subscribercnt")
	private Long subscribercnt;

	@Transient
	private String districtName;
	
	@Transient
	private String mandalName;
	
	@Transient
	private String villageName;
	
	@Transient
	private String subStationName;
	
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getSubStationName() {
		return subStationName;
	}

	public void setSubStationName(String subStationName) {
		this.subStationName = subStationName;
	}

	public String getTenantcode() {
		return tenantcode;
	}

	public void setTenantcode(String tenantcode) {
		this.tenantcode = tenantcode;
	}

	public String getSubstnuid() {
		return substnuid;
	}

	public void setSubstnuid(String substnuid) {
		this.substnuid = substnuid;
	}

	public int getStateid() {
		return stateid;
	}

	public void setStateid(int stateid) {
		this.stateid = stateid;
	}

	public int getDistrictuid() {
		return districtuid;
	}

	public void setDistrictuid(int districtuid) {
		this.districtuid = districtuid;
	}

	public int getMandalslno() {
		return mandalslno;
	}

	public void setMandalslno(int mandalslno) {
		this.mandalslno = mandalslno;
	}

	public int getVillageslno() {
		return villageslno;
	}

	public void setVillageslno(int villageslno) {
		this.villageslno = villageslno;
	}

	public Long getSubscribercnt() {
		return subscribercnt;
	}

	public void setSubscribercnt(Long subscribercnt) {
		this.subscribercnt = subscribercnt;
	}

}
