package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

import javax.persistence.Column;

public class AAAUsageBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Column(name="custname")
	private String custname; 
	
	private String cafNo;
	
	@Column(name="aadharno")
	private String aadharno; 
	
	@Column(name="apsfluniqueid")
	private String apsfluniqueid;
	
	@Column(name="pocmob1")
	private String pocmob1; 
	
	@Column(name="cpeslno")
	private String cpeslno; 
	
	@Column(name="cpemacaddr")
	private String cpemacaddr; 
	
	@Column(name="substnname")
	private String substnname; 
	
	@Column(name="agorahsisubscode")
	private String agorahsisubscode;
	
	@Column(name="aaacode")
	private String aaacode;
	
	@Column(name="starttime")
	private String sesStartTime ;
	
	@Column(name="endtime")
	private String sesEndTime; 
	
	@Column(name="sessiontime")
	private String sessiontime; 
	
	@Column(name="dwnldsize")
	private String dwnldsize;
	
	@Column(name="upldsize")
	private String upldsize;
	
	@Column(name="totalSize")
	private String totalSize;
	
	private String acctStatusType;
	
	
	public String getAcctStatusType() {
		/*String returnVal = null;
		for (AAAErrors aaa : AAAErrors.values()){
			if(acctStatusType.trim().equalsIgnoreCase(aaa.getError().trim()))
				returnVal = aaa.getReason();
		}*/
		return acctStatusType;
	}
	public void setAcctStatusType(String acctStatusType) {
		this.acctStatusType = acctStatusType;
	}
	public String getCafNo() {
		return cafNo;
	}
	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}
	public String getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}
	public String getSessiontime() {
		String val = null;
		if(!sessiontime.equalsIgnoreCase("NA")){
			String[] s = sessiontime.split("\\.");
			long seconds = Long.parseLong(s[0]);
	        long minutes ;
	        long hours;
			hours = seconds / 3600;
	        minutes = (seconds%3600)/60;
	        long seconds_output = (seconds% 3600)%60;
	        val = hours+":"+minutes+":"+seconds_output;;
		}else
			val = sessiontime;
		
    return val;
	}
	public void setSessiontime(String sessiontime) {
		this.sessiontime = sessiontime;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getAadharno() {
		return aadharno;
	}
	public void setAadharno(String aadharno) {
		this.aadharno = aadharno;
	}
	public String getApsfluniqueid() {
		return apsfluniqueid;
	}
	public void setApsfluniqueid(String apsfluniqueid) {
		this.apsfluniqueid = apsfluniqueid;
	}
	public String getPocmob1() {
		return pocmob1;
	}
	public void setPocmob1(String pocmob1) {
		this.pocmob1 = pocmob1;
	}
	public String getCpeslno() {
		return cpeslno;
	}
	public void setCpeslno(String cpeslno) {
		this.cpeslno = cpeslno;
	}
	public String getCpemacaddr() {
		return cpemacaddr;
	}
	public void setCpemacaddr(String cpemacaddr) {
		this.cpemacaddr = cpemacaddr;
	}
	public String getSubstnname() {
		return substnname;
	}
	public void setSubstnname(String substnname) {
		this.substnname = substnname;
	}
	public String getAgorahsisubscode() {
		return agorahsisubscode;
	}
	public void setAgorahsisubscode(String agorahsisubscode) {
		this.agorahsisubscode = agorahsisubscode;
	}
	public String getAaacode() {
		return aaacode;
	}
	public void setAaacode(String aaacode) {
		this.aaacode = aaacode;
	}
	public String getSesStartTime() {
		return sesStartTime;
	}
	public void setSesStartTime(String sesStartTime) {
		this.sesStartTime = sesStartTime;
	}
	public String getSesEndTime() {
		return sesEndTime;
	}
	public void setSesEndTime(String sesEndTime) {
		this.sesEndTime = sesEndTime;
	}
	public String getDwnldsize() {
		return dwnldsize;
	}
	public void setDwnldsize(String dwnldsize) {
		this.dwnldsize = dwnldsize;
	}
	public String getUpldsize() {
		return upldsize;
	}
	public void setUpldsize(String upldsize) {
		this.upldsize = upldsize;
	}
}
