/**
 * 
 */
package com.arbiva.apfgc.tenant.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author kiran
 *
 */
@Entity
public class DistrictWiseCafBO {

	@Id
	@Column(name="districtname")
	private String districtName;
	
	@Column(name="hhuptoday")
	private String hhupToday;
	
	@Column(name="hhonday")
	private String hhOnday;
	
	@Column(name="pvtuptoday")
	private String pvtupToday;
	
	@Column(name="govtuptoday")
	private String govtupToday;
	
	@Column(name="pvtonday")
	private String pvtOnday;
	
	@Column(name="govtonday")
	private String govtOnday;

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getHhupToday() {
		return hhupToday;
	}

	public void setHhupToday(String hhupToday) {
		this.hhupToday = hhupToday;
	}

	public String getHhOnday() {
		return hhOnday;
	}

	public void setHhOnday(String hhOnday) {
		this.hhOnday = hhOnday;
	}

	public String getPvtupToday() {
		return pvtupToday;
	}

	public void setPvtupToday(String pvtupToday) {
		this.pvtupToday = pvtupToday;
	}

	public String getGovtupToday() {
		return govtupToday;
	}

	public void setGovtupToday(String govtupToday) {
		this.govtupToday = govtupToday;
	}

	public String getPvtOnday() {
		return pvtOnday;
	}

	public void setPvtOnday(String pvtOnday) {
		this.pvtOnday = pvtOnday;
	}

	public String getGovtOnday() {
		return govtOnday;
	}

	public void setGovtOnday(String govtOnday) {
		this.govtOnday = govtOnday;
	}
}
