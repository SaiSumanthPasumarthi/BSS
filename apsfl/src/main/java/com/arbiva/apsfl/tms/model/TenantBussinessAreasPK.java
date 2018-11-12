package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

public class TenantBussinessAreasPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tenantcode;
	
	private String substnuid;
	
	private int stateid;
	
	private int districtuid;
	
	private int mandalslno;
	
	private int villageslno;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + districtuid;
		result = prime * result + mandalslno;
		result = prime * result + stateid;
		result = prime * result + ((substnuid == null) ? 0 : substnuid.hashCode());
		result = prime * result + ((tenantcode == null) ? 0 : tenantcode.hashCode());
		result = prime * result + villageslno;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenantBussinessAreasPK other = (TenantBussinessAreasPK) obj;
		if (districtuid != other.districtuid)
			return false;
		if (mandalslno != other.mandalslno)
			return false;
		if (stateid != other.stateid)
			return false;
		if (substnuid == null) {
			if (other.substnuid != null)
				return false;
		} else if (!substnuid.equals(other.substnuid))
			return false;
		if (tenantcode == null) {
			if (other.tenantcode != null)
				return false;
		} else if (!tenantcode.equals(other.tenantcode))
			return false;
		if (villageslno != other.villageslno)
			return false;
		return true;
	}
}
