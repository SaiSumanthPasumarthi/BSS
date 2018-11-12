package com.arbiva.apsfl.dto;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
//Created By SaiSumanth
public class TenantWalletTransDTO {
	
	private String tenantcode;
	private String tenantname;
	private String regoff_pocname;
	private String regoff_pocmob1;
	private String districtname;
	private String mandalname;
	private String villagename;
	private String trandate;
	private String tranrefno;
	private Double tranamt;
	public String getTenantcode() {
		return tenantcode;
	}
	public void setTenantcode(String tenantcode) {
		this.tenantcode = tenantcode;
	}
	public String getTenantname() {
		return tenantname;
	}
	public void setTenantname(String tenantname) {
		this.tenantname = tenantname;
	}
	public String getRegoff_pocname() {
		return regoff_pocname;
	}
	public void setRegoff_pocname(String regoff_pocname) {
		this.regoff_pocname = regoff_pocname;
	}
	public String getRegoff_pocmob1() {
		return regoff_pocmob1;
	}
	public void setRegoff_pocmob1(String regoff_pocmob1) {
		this.regoff_pocmob1 = regoff_pocmob1;
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
	public String getVillagename() {
		return villagename;
	}
	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	public String getTrandate() {
		return trandate;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	public String getTranrefno() {
		return tranrefno;
	}
	public void setTranrefno(String tranrefno) {
		this.tranrefno = tranrefno;
	}
	public Double getTranamt() {
		return tranamt;
	}
	public void setTranamt(Double tranamt) {
		this.tranamt = tranamt;
	}
	
	

}
