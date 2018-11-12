package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CafWiseRevenueOfLoginLmoBo implements Serializable {

	private static final long serialVersionUID = 8465593851012312544L;
	
    @Id
    @Column(name="cafno")
	private String cafNo;

	@Column(name="lmocode")
	private String lmocode;
	
	/*@Column(name="villagename")
	private String village;*/
	
	
	@Column(name="custname")
		private String custname;
	 @Column(name="cafDate")
		private String cafDate;
	 @Column(name="contactmob")
		private String contactmob;
	/* @Column(name="depbal")
		private String depbal;*/
	 @Id
	 @Column(name="APSFLShare")
		private String APSFLShare;
	 @Id
	 @Column(name="MSOShare")
		private String MSOShare;
	 @Id
	 @Column(name="LMOShare")
		private String LMOShare;
	 @Column(name="total")
		private String total;
	 @Column(name="previousBalance")
		private String previousBalance;
	 @Column(name="paymentstatus")
		private String paymentstatus;
	
	
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getCustname() {
			return custname;
		}
		public void setCustname(String custname) {
			this.custname = custname;
		}
		public String getCafDate() {
			return cafDate;
		}
		public void setCafDate(String cafDate) {
			this.cafDate = cafDate;
		}
		public String getContactmob() {
			return contactmob;
		}
		public void setContactmob(String contactmob) {
			this.contactmob = contactmob;
		}
		/*public String getDepbal() {
			return depbal;
		}
		public void setDepbal(String depbal) {
			this.depbal = depbal;
		}*/
		public String getAPSFLShare() {
			return APSFLShare;
		}
		public void setAPSFLShare(String aPSFLShare) {
			APSFLShare = aPSFLShare;
		}
		public String getMSOShare() {
			return MSOShare;
		}
		public void setMSOShare(String mSOShare) {
			MSOShare = mSOShare;
		}
		public String getLMOShare() {
			return LMOShare;
		}
		public void setLMOShare(String lMOShare) {
			LMOShare = lMOShare;
		}
		
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
	
	 
	public String getPreviousBalance() {
			return previousBalance;
		}
		public void setPreviousBalance(String previousBalance) {
			this.previousBalance = previousBalance;
		}
	public String getCafNo() {
		return cafNo;
	}
	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}
	public String getLmocode() {
		return lmocode;
	}
	public void setLmocode(String lmocode) {
		this.lmocode = lmocode;
	}
	
	

	/*public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	*/


}
