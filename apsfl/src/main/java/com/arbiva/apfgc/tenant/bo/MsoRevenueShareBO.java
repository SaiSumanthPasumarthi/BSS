package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MsoRevenueShareBO implements Serializable{
	
	private static final long serialVersionUID = 2788639030019890765L;
	
	@Id
	@Column(name="lmocode")
	private String lmoCode;
	
	@Id
	@Column(name="msoinv_share")
	private String msoShare;
	
	@Id
	@Column(name="apsflinv_share")
	private String apsflShare;
	@Id
	@Column(name="lmoinv_share")
	private String lmoShare;
	@Id
	@Column(name="totalbill")
	private String totalBill;
	
	@Column(name="lmo_total_collected")
	private String totalCollected;
	
	
	@Column(name="yettocollect")
	private String yetToCollect;
	
	@Column(name="caf_count")
	private String cafcount;
	
	@Column(name="credit_limit")
	private String creditlimit;
	


	public String getCreditlimit() {
		return creditlimit;
	}
	public void setCreditlimit(String creditlimit) {
		this.creditlimit = creditlimit;
	}
	public String getCafcount() {
		return cafcount;
	}
	public void setCafcount(String cafcount) {
		this.cafcount = cafcount;
	}
	public String getTotalCollected() {
		return totalCollected;
	}
	public void setTotalCollected(String totalCollected) {
		this.totalCollected = totalCollected;
	}
	public String getYetToCollect() {
		return yetToCollect;
	}
	public void setYetToCollect(String yetToCollect) {
		this.yetToCollect = yetToCollect;
	}
	public String getLmoCode() {
		return lmoCode;
	}
	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}
	public String getMsoShare() {
		return msoShare;
	}
	public void setMsoShare(String msoShare) {
		this.msoShare = msoShare;
	}	public String getApsflShare() {
		return apsflShare;
	}
	public void setApsflShare(String apsflShare) {
		this.apsflShare = apsflShare;
	}
	
	public String getLmoShare() {
		return lmoShare;
	}
	public void setLmoShare(String lmoShare) {
		this.lmoShare = lmoShare;
	}
	public String getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
