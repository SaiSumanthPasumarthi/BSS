package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.arbiva.apfgc.tenant.bo.PONWiseBo.UsersDataSearchParams;

@Entity
public class PONWithZeroCAFBO implements Serializable {
	
	private static final long serialVersionUID = 8627346856081721199L;

	public enum UsersDataSearchParams{
		
		COLUMN_0("pop_name"),COLUMN_1("lmocode"),COLUMN_2("portno"),COLUMN_3("tenantname"),
		COLUMN_4("mandalname"),COLUMN_5("districtname"),COLUMN_6("villagename"),COLUMN_7("regoff_pocmob1");
		
		private String colName;

		private UsersDataSearchParams(String colName) {
			this.colName = colName;
		}

		public String getColName() {
			return colName;
		}

		public static String getColumns(String colName) {
			for (UsersDataSearchParams usersDataSearchParams : UsersDataSearchParams.values()) {
				if (usersDataSearchParams.toString().equals(colName)) {
					return usersDataSearchParams.getColName();
				}
			}
			return "";
		}
	}

	@Id
	@Column(name = "pop_name")
	private String pop_name;
	
	/*@Id
	@Column(name = "pop_olt_ipaddress")
	private String popOltIPAddress;*/
	
	@Id
	@Column(name = "lmocode")
	private String lmocode;
	@Id
	@Column(name = "portno")
	private int portno;
	@Column(name = "tenantname")
	private String tenantname;
	@Column(name = "mandalname")
	private String mandalname;
	@Column(name = "districtname")
	private String districtname;
	
	@Column(name = "villagename")
	private String villagename;
	
	@Column(name = "regoff_pocmob1")
	private String regoff_pocmob1;
	public String getPop_name() {
		return pop_name;
	}

	public void setPop_name(String pop_name) {
		this.pop_name = pop_name;
	}

	/*public String getPopOltIPAddress() {
		return popOltIPAddress;
	}

	public void setPopOltIPAddress(String popOltIPAddress) {
		this.popOltIPAddress = popOltIPAddress;
	}*/

	public String getLmocode() {
		return lmocode;
	}

	public void setLmocode(String lmocode) {
		this.lmocode = lmocode;
	}

	public int getPortno() {
		return portno;
	}

	public void setPortno(int portno) {
		this.portno = portno;
	}

	public String getTenantname() {
		return tenantname;
	}

	public void setTenantname(String tenantname) {
		this.tenantname = tenantname;
	}

	public String getMandalname() {
		return mandalname;
	}

	public void setMandalname(String mandalname) {
		this.mandalname = mandalname;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getVillagename() {
		return villagename;
	}

	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}

	public String getRegoff_pocmob1() {
		return regoff_pocmob1;
	}

	public void setRegoff_pocmob1(String regoff_pocmob1) {
		this.regoff_pocmob1 = regoff_pocmob1;
	}

}
