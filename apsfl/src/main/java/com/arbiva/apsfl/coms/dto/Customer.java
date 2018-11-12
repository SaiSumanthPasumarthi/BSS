/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;

/**
 * @author Lakshman
 *
 */
public class Customer extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum UsersDataSearchParams{

		COLUMN_0("custname"),COLUMN_1("aadharno"),COLUMN_2("addr1"),COLUMN_3("pocmob1"),
		COLUMN_4("phoneNo");

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

	private Long custId;

	private String custCode;
	
	private String custUid;
	
	private String custTypeLov;
	
	private String custType;

	private String officeTypeLov;

	private String officeLocation;

	private String parentcustcode;
	
	private String regnCode;
	
	private String panNo;

	private String tanNo;
	
	private String aadharNo;
	
	private String titleLov;
	
	private String custName;
	
	private String mName;
	
	private String lName;
	
	private String fhName;
	
	private char gender;
	
	private int pmntliabilityflag;

	private String address1;

	private String address2;

	private String locality;

	private String area;
	
	private String mandal;
	
	private String district;

	private String cityVillage;

	private String state;

	private String pin;

	private String stdCode;

	private String landLine1;

	private String landLine2;

	private String email1;

	private String email2;

	private String fax1;

	private String fax2;

	private String pocName;

	private String pocMob1;

	private String pocMob2;
	
	private String blAddress1;

	private String blAddress2;

	private String blLocality;

	private String blArea;
	
	private String blMandal;
	
	private String blDistrict;
	
	private Integer custdistUid;
	
	private String blCityVillage;

	private String blState;

	private String blPin;

	private String blStdCode;

	private String blLandLine1;

	private String blLandLine2;

	private String blEmail1;

	private String blEmail2;

	private String blFax1;

	@Column(name = "blfax2")
	private String blFax2;

	private String blPocName;

	private String blPocMob1;

	private String blPocMob2;

	private String billfreqLov;
	
	private Date actDate;
	
	private String lmoCode;
	
	private String channelLov;
	
	private String segmentLov;
	
	private Date dateofinc;
	
	private char blacklistFlag;

	private Calendar deActivatedOn;
	
	private String deActivatedBy;
	
	private String deActivatedIpAddr;
	
	private String pocDesignation;
	
	private String dob;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustUid() {
		return custUid;
	}

	public void setCustUid(String custUid) {
		this.custUid = custUid;
	}

	public String getCustTypeLov() {
		return custTypeLov;
	}

	public void setCustTypeLov(String custTypeLov) {
		this.custTypeLov = custTypeLov;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getOfficeTypeLov() {
		return officeTypeLov;
	}

	public void setOfficeTypeLov(String officeTypeLov) {
		this.officeTypeLov = officeTypeLov;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	public String getParentcustcode() {
		return parentcustcode;
	}

	public void setParentcustcode(String parentcustcode) {
		this.parentcustcode = parentcustcode;
	}

	public String getRegnCode() {
		return regnCode;
	}

	public void setRegnCode(String regnCode) {
		this.regnCode = regnCode;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getTanNo() {
		return tanNo;
	}

	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getTitleLov() {
		return titleLov;
	}

	public void setTitleLov(String titleLov) {
		this.titleLov = titleLov;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getFhName() {
		return fhName;
	}

	public void setFhName(String fhName) {
		this.fhName = fhName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getPmntliabilityflag() {
		return pmntliabilityflag;
	}

	public void setPmntliabilityflag(int pmntliabilityflag) {
		this.pmntliabilityflag = pmntliabilityflag;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCityVillage() {
		return cityVillage;
	}

	public void setCityVillage(String cityVillage) {
		this.cityVillage = cityVillage;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public String getLandLine1() {
		return landLine1;
	}

	public void setLandLine1(String landLine1) {
		this.landLine1 = landLine1;
	}

	public String getLandLine2() {
		return landLine2;
	}

	public void setLandLine2(String landLine2) {
		this.landLine2 = landLine2;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getFax1() {
		return fax1;
	}

	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	public String getFax2() {
		return fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public String getPocMob1() {
		return pocMob1;
	}

	public void setPocMob1(String pocMob1) {
		this.pocMob1 = pocMob1;
	}

	public String getPocMob2() {
		return pocMob2;
	}

	public void setPocMob2(String pocMob2) {
		this.pocMob2 = pocMob2;
	}

	public String getBlAddress1() {
		return blAddress1;
	}

	public void setBlAddress1(String blAddress1) {
		this.blAddress1 = blAddress1;
	}

	public String getBlAddress2() {
		return blAddress2;
	}

	public void setBlAddress2(String blAddress2) {
		this.blAddress2 = blAddress2;
	}

	public String getBlLocality() {
		return blLocality;
	}

	public void setBlLocality(String blLocality) {
		this.blLocality = blLocality;
	}

	public String getBlArea() {
		return blArea;
	}

	public void setBlArea(String blArea) {
		this.blArea = blArea;
	}

	public String getBlMandal() {
		return blMandal;
	}

	public void setBlMandal(String blMandal) {
		this.blMandal = blMandal;
	}

	public String getBlDistrict() {
		return blDistrict;
	}

	public void setBlDistrict(String blDistrict) {
		this.blDistrict = blDistrict;
	}
	
	public Integer getCustdistUid() {
		return custdistUid;
	}

	public void setCustdistUid(Integer custdistUid) {
		this.custdistUid = custdistUid;
	}

	public String getBlCityVillage() {
		return blCityVillage;
	}

	public void setBlCityVillage(String blCityVillage) {
		this.blCityVillage = blCityVillage;
	}

	public String getBlState() {
		return blState;
	}

	public void setBlState(String blState) {
		this.blState = blState;
	}

	public String getBlPin() {
		return blPin;
	}

	public void setBlPin(String blPin) {
		this.blPin = blPin;
	}

	public String getBlStdCode() {
		return blStdCode;
	}

	public void setBlStdCode(String blStdCode) {
		this.blStdCode = blStdCode;
	}

	public String getBlLandLine1() {
		return blLandLine1;
	}

	public void setBlLandLine1(String blLandLine1) {
		this.blLandLine1 = blLandLine1;
	}

	public String getBlLandLine2() {
		return blLandLine2;
	}

	public void setBlLandLine2(String blLandLine2) {
		this.blLandLine2 = blLandLine2;
	}

	public String getBlEmail1() {
		return blEmail1;
	}

	public void setBlEmail1(String blEmail1) {
		this.blEmail1 = blEmail1;
	}

	public String getBlEmail2() {
		return blEmail2;
	}

	public void setBlEmail2(String blEmail2) {
		this.blEmail2 = blEmail2;
	}

	public String getBlFax1() {
		return blFax1;
	}

	public void setBlFax1(String blFax1) {
		this.blFax1 = blFax1;
	}

	public String getBlFax2() {
		return blFax2;
	}

	public void setBlFax2(String blFax2) {
		this.blFax2 = blFax2;
	}

	public String getBlPocName() {
		return blPocName;
	}

	public void setBlPocName(String blPocName) {
		this.blPocName = blPocName;
	}

	public String getBlPocMob1() {
		return blPocMob1;
	}

	public void setBlPocMob1(String blPocMob1) {
		this.blPocMob1 = blPocMob1;
	}

	public String getBlPocMob2() {
		return blPocMob2;
	}

	public void setBlPocMob2(String blPocMob2) {
		this.blPocMob2 = blPocMob2;
	}

	public String getBillfreqLov() {
		return billfreqLov;
	}

	public void setBillfreqLov(String billfreqLov) {
		this.billfreqLov = billfreqLov;
	}

	public Date getActDate() {
		return actDate;
	}

	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getChannelLov() {
		return channelLov;
	}

	public void setChannelLov(String channelLov) {
		this.channelLov = channelLov;
	}

	public String getSegmentLov() {
		return segmentLov;
	}

	public void setSegmentLov(String segmentLov) {
		this.segmentLov = segmentLov;
	}

	public Date getDateofinc() {
		return dateofinc;
	}

	public void setDateofinc(Date dateofinc) {
		this.dateofinc = dateofinc;
	}

	public char getBlacklistFlag() {
		return blacklistFlag;
	}

	public void setBlacklistFlag(char blacklistFlag) {
		this.blacklistFlag = blacklistFlag;
	}

	public Calendar getDeActivatedOn() {
		return deActivatedOn;
	}

	public void setDeActivatedOn(Calendar deActivatedOn) {
		this.deActivatedOn = deActivatedOn;
	}

	public String getDeActivatedBy() {
		return deActivatedBy;
	}

	public void setDeActivatedBy(String deActivatedBy) {
		this.deActivatedBy = deActivatedBy;
	}

	public String getDeActivatedIpAddr() {
		return deActivatedIpAddr;
	}

	public void setDeActivatedIpAddr(String deActivatedIpAddr) {
		this.deActivatedIpAddr = deActivatedIpAddr;
	}

	public String getPocDesignation() {
		return pocDesignation;
	}

	public void setPocDesignation(String pocDesignation) {
		this.pocDesignation = pocDesignation;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
}
