package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(EnterpriseSubscriberBOPK.class)
public class EnterpriseSubscriberBO implements Serializable {
	
	private static final long serialVersionUID = -1157473540307390846L;
	
	public enum UsersDataSearchParams{

		COLUMN_0("custname"),COLUMN_1("parentcustname"),COLUMN_2("tenantname"),COLUMN_3("totalcafs"),
		COLUMN_4("pendingforcafeditcount"),COLUMN_5("pendingforpaymentcount"),COLUMN_6("pendingforprovisioncount"),
		COLUMN_7("activecount"),COLUMN_8("suspendcount"),COLUMN_9("inactivecount");
		
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
	@Column(name="custid")
	private String custid;
	
	@Column(name="custname")
	private String custname;
	
	@Column(name="parentcustname")
	private String  parentcustname;
	
	@Column(name="totalcafs")
	private Long  totalcafs;
	
	@Id
	@Column(name="lmocode")
	private String lmocode;
	
	@Column(name="activecount")
	private Long activecount;
	
	@Column(name="suspendcount")
	private Long suspendcount;
	
	@Column(name="inactivecount")
	private Long inactivecount;
	
	@Column(name="pendingforcafeditcount")
	private Long pendingforcafeditcount;
	
	@Column(name="pendingforprovisioncount")
	private Long pendingforprovisioncount;
	
	@Column(name="pendingforpaymentcount")
	private Long pendingforpaymentcount;
	
	@Column(name="tenantname")
	private String tenantname;

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getParentcustname() {
		return parentcustname;
	}

	public void setParentcustname(String parentcustname) {
		this.parentcustname = parentcustname;
	}

	public Long getTotalcafs() {
		return totalcafs;
	}

	public void setTotalcafs(Long totalcafs) {
		this.totalcafs = totalcafs;
	}

	public String getLmocode() {
		return lmocode;
	}

	public void setLmocode(String lmocode) {
		this.lmocode = lmocode;
	}

	public Long getActivecount() {
		return activecount;
	}

	public void setActivecount(Long activecount) {
		this.activecount = activecount;
	}

	public Long getSuspendcount() {
		return suspendcount;
	}

	public void setSuspendcount(Long suspendcount) {
		this.suspendcount = suspendcount;
	}

	public Long getInactivecount() {
		return inactivecount;
	}

	public void setInactivecount(Long inactivecount) {
		this.inactivecount = inactivecount;
	}

	public Long getPendingforcafeditcount() {
		return pendingforcafeditcount;
	}

	public void setPendingforcafeditcount(Long pendingforcafeditcount) {
		this.pendingforcafeditcount = pendingforcafeditcount;
	}

	public Long getPendingforprovisioncount() {
		return pendingforprovisioncount;
	}

	public void setPendingforprovisioncount(Long pendingforprovisioncount) {
		this.pendingforprovisioncount = pendingforprovisioncount;
	}

	public Long getPendingforpaymentcount() {
		return pendingforpaymentcount;
	}

	public void setPendingforpaymentcount(Long pendingforpaymentcount) {
		this.pendingforpaymentcount = pendingforpaymentcount;
	}

	public String getTenantname() {
		return tenantname;
	}

	public void setTenantname(String tenantname) {
		this.tenantname = tenantname;
	}
}
