package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.arbiva.apfgc.tenant.bo.EnterpriseSubscriberBO.UsersDataSearchParams;

@Entity
public class PONWiseBo implements Serializable{

	private static final long serialVersionUID = 3382069165346345239L;

	public enum UsersDataSearchParams{

		COLUMN_0("pop_name"),COLUMN_1("pop_olt_ipaddress"),COLUMN_2("portno"),COLUMN_3("lmocode"),
		COLUMN_4("regoff_pocname"),COLUMN_5("regoff_pocmob1"),COLUMN_6("cafno"),
		COLUMN_7("districtname"),COLUMN_8("mandalname"),COLUMN_9("createdon");
		
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

	@Column(name = "pop_name")
	private String pop_name;

	@Id
	@Column(name = "pop_olt_ipaddress")
	private String pop_olt_ipaddress;

	@Id
	@Column(name = "portno")
	private String portno;

	@Id
	@Column(name = "lmocode")
	private String lmocode;

	@Column(name = "regoff_pocname")
	private String regoff_pocname;
	
	@Column(name = "regoff_pocmob1")
	private String regoff_pocmob1;

	@Id
	@Column(name = "cafno")
	private Long cafno;

	@Column(name = "districtname")
	private String districtname;

	@Column(name = "mandalname")
	private String mandalname;

	@Id
	@Column(name = "createdon")
	private String createdon;

	public String getPop_name() {
		return pop_name;
	}

	public void setPop_name(String pop_name) {
		this.pop_name = pop_name;
	}

	public String getPop_olt_ipaddress() {
		return pop_olt_ipaddress;
	}

	public void setPop_olt_ipaddress(String pop_olt_ipaddress) {
		this.pop_olt_ipaddress = pop_olt_ipaddress;
	}

	public String getPortno() {
		return portno;
	}

	public void setPortno(String portno) {
		this.portno = portno;
	}

	public String getLmocode() {
		return lmocode;
	}

	public void setLmocode(String lmocode) {
		this.lmocode = lmocode;
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

	public Long getCafno() {
		return cafno;
	}

	public void setCafno(Long cafno) {
		this.cafno = cafno;
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

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}


}
