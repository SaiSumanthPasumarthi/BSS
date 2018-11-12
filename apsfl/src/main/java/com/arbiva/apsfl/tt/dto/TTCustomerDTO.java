package com.arbiva.apsfl.tt.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class TTCustomerDTO {
	
	private String onuModel;
	private String onuSerialNo;
	private String onuMACAddr;

	private BigDecimal custId;
	private String apsflTrackID;
	private String apsflLandlineNo;
	private String custCode;
	private String custUid;
	private String custType;
	private String parentCustCode;
	private String idNo;
	private String aadharNo;
	private String panNo; 
	private String tanNo;
	private String title;
	private String fName;
	private String mName;
	private String lName;
	private String fhName;
	private Integer ageYears;
	private Timestamp ageyrsason;
	private Timestamp deriveddob;
	private Timestamp actualdob;
	private Character gender;
	private String address1;
	private String address2;
	private String locality;
	private String area;
	private String cityVillage;
	private String state;
	private String pin;
	private String district;
	private String mandal;
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
	private String poiDocLov;
	private String poiDocNo;
	private Timestamp poiTimestampOfIssue;
	private Timestamp poiExpTimestamp;
	private String poiPlaceOfIssue;
	private String poiIssuingAuthority;	
	private String poiDocLoc;
	private String poaDocLov;
	private String poaDocNo;
	private Timestamp poaTimestampOfIssue;
	private Timestamp poaExpTimestamp;
	private String poaPlaceOfIssue;
	private String poaIssuingAuthority;
	private String poaDocLoc;
	private String channelLov;
	private String segmentLov;
	private String lmoCode;
	private String billfreqLov;
	private int ifscCode;
	private int accountno;
	private String deActivatedBy;
	private Timestamp deActivatedOn;
	private String deActivatedIpAddr;
	private String cafNo;
	private String districtName;
	private String mandalName;
	private String villageName;
	private String tenantMobile;
	List<TroubleTicketDTO> listOfTroubleTickets;
	private String count;

	public List<TroubleTicketDTO> getListOfTroubleTickets() {
		return listOfTroubleTickets;
	}
	public void setListOfTroubleTickets(List<TroubleTicketDTO> listOfTroubleTickets) {
		this.listOfTroubleTickets = listOfTroubleTickets;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public BigDecimal getCustId() {
		return custId;
	}
	public void setCustId(BigDecimal custId) {
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
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getParentCustCode() {
		return parentCustCode;
	}
	public void setParentCustCode(String parentCustCode) {
		this.parentCustCode = parentCustCode;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
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
	public Integer getAgeYears() {
		return ageYears;
	}
	public void setAgeYears(Integer ageYears) {
		this.ageYears = ageYears;
	}
	public Timestamp getAgeyrsason() {
		return ageyrsason;
	}
	public void setAgeyrsason(Timestamp ageyrsason) {
		this.ageyrsason = ageyrsason;
	}
	public Timestamp getDeriveddob() {
		return deriveddob;
	}
	public void setDeriveddob(Timestamp deriveddob) {
		this.deriveddob = deriveddob;
	}
	public Timestamp getActualdob() {
		return actualdob;
	}
	public void setActualdob(Timestamp actualdob) {
		this.actualdob = actualdob;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
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
	public String getPoiDocLov() {
		return poiDocLov;
	}
	public void setPoiDocLov(String poiDocLov) {
		this.poiDocLov = poiDocLov;
	}
	public String getPoiDocNo() {
		return poiDocNo;
	}
	public void setPoiDocNo(String poiDocNo) {
		this.poiDocNo = poiDocNo;
	}
	public Timestamp getPoiTimestampOfIssue() {
		return poiTimestampOfIssue;
	}
	public void setPoiTimestampOfIssue(Timestamp poiTimestampOfIssue) {
		this.poiTimestampOfIssue = poiTimestampOfIssue;
	}
	public Timestamp getPoiExpTimestamp() {
		return poiExpTimestamp;
	}
	public void setPoiExpTimestamp(Timestamp poiExpTimestamp) {
		this.poiExpTimestamp = poiExpTimestamp;
	}
	public String getPoiPlaceOfIssue() {
		return poiPlaceOfIssue;
	}
	public void setPoiPlaceOfIssue(String poiPlaceOfIssue) {
		this.poiPlaceOfIssue = poiPlaceOfIssue;
	}
	public String getPoiIssuingAuthority() {
		return poiIssuingAuthority;
	}
	public void setPoiIssuingAuthority(String poiIssuingAuthority) {
		this.poiIssuingAuthority = poiIssuingAuthority;
	}
	public String getPoiDocLoc() {
		return poiDocLoc;
	}
	public void setPoiDocLoc(String poiDocLoc) {
		this.poiDocLoc = poiDocLoc;
	}
	public String getPoaDocLov() {
		return poaDocLov;
	}
	public void setPoaDocLov(String poaDocLov) {
		this.poaDocLov = poaDocLov;
	}
	public String getPoaDocNo() {
		return poaDocNo;
	}
	public void setPoaDocNo(String poaDocNo) {
		this.poaDocNo = poaDocNo;
	}
	public Timestamp getPoaTimestampOfIssue() {
		return poaTimestampOfIssue;
	}
	public void setPoaTimestampOfIssue(Timestamp poaTimestampOfIssue) {
		this.poaTimestampOfIssue = poaTimestampOfIssue;
	}
	public Timestamp getPoaExpTimestamp() {
		return poaExpTimestamp;
	}
	public void setPoaExpTimestamp(Timestamp poaExpTimestamp) {
		this.poaExpTimestamp = poaExpTimestamp;
	}
	public String getPoaPlaceOfIssue() {
		return poaPlaceOfIssue;
	}
	public void setPoaPlaceOfIssue(String poaPlaceOfIssue) {
		this.poaPlaceOfIssue = poaPlaceOfIssue;
	}
	public String getPoaIssuingAuthority() {
		return poaIssuingAuthority;
	}
	public void setPoaIssuingAuthority(String poaIssuingAuthority) {
		this.poaIssuingAuthority = poaIssuingAuthority;
	}
	public String getPoaDocLoc() {
		return poaDocLoc;
	}
	public void setPoaDocLoc(String poaDocLoc) {
		this.poaDocLoc = poaDocLoc;
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
	public String getLmoCode() {
		return lmoCode;
	}
	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}
	public String getBillfreqLov() {
		return billfreqLov;
	}
	public void setBillfreqLov(String billfreqLov) {
		this.billfreqLov = billfreqLov;
	}
	public int getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(int ifscCode) {
		this.ifscCode = ifscCode;
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public String getDeActivatedBy() {
		return deActivatedBy;
	}
	public void setDeActivatedBy(String deActivatedBy) {
		this.deActivatedBy = deActivatedBy;
	}
	public Timestamp getDeActivatedOn() {
		return deActivatedOn;
	}
	public void setDeActivatedOn(Timestamp deActivatedOn) {
		this.deActivatedOn = deActivatedOn;
	}
	public String getDeActivatedIpAddr() {
		return deActivatedIpAddr;
	}
	public void setDeActivatedIpAddr(String deActivatedIpAddr) {
		this.deActivatedIpAddr = deActivatedIpAddr;
	}
	public String getCafNo() {
		return cafNo;
	}
	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getTenantMobile() {
		return tenantMobile;
	}
	public void setTenantMobile(String tenantMobile) {
		this.tenantMobile = tenantMobile;
	}
	public String getApsflTrackID() {
		return apsflTrackID;
	}
	public void setApsflTrackID(String apsflTrackID) {
		this.apsflTrackID = apsflTrackID;
	}
	public String getOnuModel() {
		return onuModel;
	}
	public void setOnuModel(String onuModel) {
		this.onuModel = onuModel;
	}
	public String getOnuSerialNo() {
		return onuSerialNo;
	}
	public void setOnuSerialNo(String onuSerialNo) {
		this.onuSerialNo = onuSerialNo;
	}
	public String getOnuMACAddr() {
		return onuMACAddr;
	}
	public void setOnuMACAddr(String onuMACAddr) {
		this.onuMACAddr = onuMACAddr;
	}
	public String getApsflLandlineNo() {
		return apsflLandlineNo;
	}
	public void setApsflLandlineNo(String apsflLandlineNo) {
		this.apsflLandlineNo = apsflLandlineNo;
	}
}
