package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.arbiva.apsfl.tms.model.Tenant;

/**
 * 
 * @author Laxman
 * 
 */
public class Caf extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cafNo;

	private String lmoCode;

	private String custType;

	private String custIdNo; // Aadhar no for individual customers and PAN+TAN
								// for enterprise customers
	private Long custId;

	private String custCode;

	private String pmntCustCode;

	private Long pmntCustId;

	private String aadharNo;

	private String panNo;

	private String tanNo;

	private Calendar cafDate;

	private float longitude;

	private float lattitude;

	private float altitude;

	private String cpePlace;

	private String cpeslNo;

	private String cpeMacAddr;

	private String cpeIpAddr;

	private Character cpeLeaseyn;

	private String cpeTenantcode;

	private int cpeLockedmons;

	private Date cpeLockeddate;

	private String billfreqLov;

	private Character customerDeclaration;

	private Character lmoDeclaration;

	private String deActivatedBy;

	private Date deActivatedOn;

	private String deActivatedIpAddr;

	private Integer popDistrict;

	private Integer popMandal;

	private String oltId;

	private Integer oltPortid;

	private Long cpeModel;

	private Integer oltOnuid;

	private String portSplit;

	private String onuRegnNo;
	
	private String agoraHsiSubsCode;
	
	private String popSubstnno;

	private Integer oltCardno;

	private String instAddress1;

	private String instAddress2;

	private String instLocality;

	private String instArea;

	private String instDistrict;
	
	private Integer custdistUid;

	private String instMandal;

	private String instCityVillage;

	private String instState;

	private String instPin;

	private String instTaxgradeLov;

	private Date actDate;

	private Tenant tenantcode;

	private Customer customerId;

	private CpeModal cpeModal;

	private CpeModal stbModel;

	private String contactPerson;

	private String contactEmail;

	private String contactmobileNo;
	
	private String apsflUniqueId;

	private String paymentResponsible;

	private OLTPortDetails olPortDetails;

	private String pocDesignation;

	private String successRecordCount;

	private String failedRecordCount;
	
	private PageObject pageObject;
	
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public String getApsflUniqueId() {
		return apsflUniqueId;
	}

	public void setApsflUniqueId(String apsflUniqueId) {
		this.apsflUniqueId = apsflUniqueId;
	}

	public String getSuccessRecordCount() {
		return successRecordCount;
	}

	public void setSuccessRecordCount(String successRecordCount) {
		this.successRecordCount = successRecordCount;
	}

	public String getFailedRecordCount() {
		return failedRecordCount;
	}

	public void setFailedRecordCount(String failedRecordCount) {
		this.failedRecordCount = failedRecordCount;
	}

	public String getPocDesignation() {
		return pocDesignation;
	}

	public void setPocDesignation(String pocDesignation) {
		this.pocDesignation = pocDesignation;
	}

	public String getInstDistrict() {
		return instDistrict;
	}

	public void setInstDistrict(String instDistrict) {
		this.instDistrict = instDistrict;
	}
	
	public Integer getCustdistUid() {
		return custdistUid;
	}

	public void setCustdistUid(Integer custdistUid) {
		this.custdistUid = custdistUid;
	}

	public String getInstMandal() {
		return instMandal;
	}

	public void setInstMandal(String instMandal) {
		this.instMandal = instMandal;
	}

	public String getInstAddress1() {
		return instAddress1;
	}

	public void setInstAddress1(String instAddress1) {
		this.instAddress1 = instAddress1;
	}

	public String getInstAddress2() {
		return instAddress2;
	}

	public void setInstAddress2(String instAddress2) {
		this.instAddress2 = instAddress2;
	}

	public String getInstLocality() {
		return instLocality;
	}

	public void setInstLocality(String instLocality) {
		this.instLocality = instLocality;
	}

	public String getInstArea() {
		return instArea;
	}

	public void setInstArea(String instArea) {
		this.instArea = instArea;
	}

	public String getInstCityVillage() {
		return instCityVillage;
	}

	public void setInstCityVillage(String instCityVillage) {
		this.instCityVillage = instCityVillage;
	}

	public String getInstState() {
		return instState;
	}

	public void setInstState(String instState) {
		this.instState = instState;
	}

	public String getInstPin() {
		return instPin;
	}

	public void setInstPin(String instPin) {
		this.instPin = instPin;
	}

	public CpeModal getCpeModal() {
		return cpeModal;
	}

	public void setCpeModal(CpeModal cpeModal) {
		this.cpeModal = cpeModal;
	}

	public String getPopSubstnno() {
		return popSubstnno;
	}

	public void setPopSubstnno(String popSubstnno) {
		this.popSubstnno = popSubstnno;
	}

	public Integer getOltCardno() {
		return oltCardno;
	}

	public void setOltCardno(Integer oltCardno) {
		this.oltCardno = oltCardno;
	}

	public Integer getPopDistrict() {
		return popDistrict;
	}

	public void setPopDistrict(Integer popDistrict) {
		this.popDistrict = popDistrict;
	}

	public Integer getPopMandal() {
		return popMandal;
	}

	public void setPopMandal(Integer popMandal) {
		this.popMandal = popMandal;
	}

	public String getOltId() {
		return oltId;
	}

	public void setOltId(String oltId) {
		this.oltId = oltId;
	}

	public Integer getOltPortid() {
		return oltPortid;
	}

	public void setOltPortid(Integer oltPortid) {
		this.oltPortid = oltPortid;
	}

	public Long getCpeModel() {
		return cpeModel;
	}

	public void setCpeModel(Long cpeModel) {
		this.cpeModel = cpeModel;
	}

	public Integer getOltOnuid() {
		return oltOnuid;
	}

	public void setOltOnuid(Integer oltOnuid) {
		this.oltOnuid = oltOnuid;
	}

	public Tenant getTenantcode() {
		return tenantcode;
	}

	public void setTenantcode(Tenant tenantcode) {
		this.tenantcode = tenantcode;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Long getCafNo() {
		return cafNo;
	}

	public void setCafNo(Long cafNo) {
		this.cafNo = cafNo;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCpePlace() {
		return cpePlace;
	}

	public void setCpePlace(String cpePlace) {
		this.cpePlace = cpePlace;
	}

	public String getCpeslNo() {
		return cpeslNo;
	}

	public void setCpeslNo(String cpeslNo) {
		this.cpeslNo = cpeslNo;
	}

	public String getCpeMacAddr() {
		return cpeMacAddr;
	}

	public void setCpeMacAddr(String cpeMacAddr) {
		this.cpeMacAddr = cpeMacAddr;
	}

	public String getCpeIpAddr() {
		return cpeIpAddr;
	}

	public void setCpeIpAddr(String cpeIpAddr) {
		this.cpeIpAddr = cpeIpAddr;
	}

	public Character getCpeLeaseyn() {
		return cpeLeaseyn;
	}

	public void setCpeLeaseyn(Character cpeLeaseyn) {
		this.cpeLeaseyn = cpeLeaseyn;
	}

	public String getCpeTenantcode() {
		return cpeTenantcode;
	}

	public void setCpeTenantcode(String cpeTenantcode) {
		this.cpeTenantcode = cpeTenantcode;
	}

	public int getCpeLockedmons() {
		return cpeLockedmons;
	}

	public void setCpeLockedmons(int cpeLockedmons) {
		this.cpeLockedmons = cpeLockedmons;
	}

	public Date getCpeLockeddate() {
		return cpeLockeddate;
	}

	public void setCpeLockeddate(Date cpeLockeddate) {
		this.cpeLockeddate = cpeLockeddate;
	}

	public Character getCustomerDeclaration() {
		return customerDeclaration;
	}

	public void setCustomerDeclaration(Character customerDeclaration) {
		this.customerDeclaration = customerDeclaration;
	}

	public Character getLmoDeclaration() {
		return lmoDeclaration;
	}

	public void setLmoDeclaration(Character lmoDeclaration) {
		this.lmoDeclaration = lmoDeclaration;
	}

	public String getDeActivatedBy() {
		return deActivatedBy;
	}

	public void setDeActivatedBy(String deActivatedBy) {
		this.deActivatedBy = deActivatedBy;
	}

	public Date getDeActivatedOn() {
		return deActivatedOn;
	}

	public void setDeActivatedOn(Date deActivatedOn) {
		this.deActivatedOn = deActivatedOn;
	}

	public String getDeActivatedIpAddr() {
		return deActivatedIpAddr;
	}

	public void setDeActivatedIpAddr(String deActivatedIpAddr) {
		this.deActivatedIpAddr = deActivatedIpAddr;
	}

	public String getCustIdNo() {
		return custIdNo;
	}

	public void setCustIdNo(String custIdNo) {
		this.custIdNo = custIdNo;
	}

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

	public String getPmntCustCode() {
		return pmntCustCode;
	}

	public void setPmntCustCode(String pmntCustCode) {
		this.pmntCustCode = pmntCustCode;
	}

	public Long getPmntCustId() {
		return pmntCustId;
	}

	public void setPmntCustId(Long pmntCustId) {
		this.pmntCustId = pmntCustId;
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

	public Calendar getCafDate() {
		return cafDate;
	}

	public void setCafDate(Calendar cafDate) {
		this.cafDate = cafDate;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLattitude() {
		return lattitude;
	}

	public void setLattitude(float lattitude) {
		this.lattitude = lattitude;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public String getBillfreqLov() {
		return billfreqLov;
	}

	public void setBillfreqLov(String billfreqLov) {
		this.billfreqLov = billfreqLov;
	}

	public String getOnuRegnNo() {
		return onuRegnNo;
	}

	public void setOnuRegnNo(String onuRegnNo) {
		this.onuRegnNo = onuRegnNo;
	}
	
	public String getAgoraHsiSubsCode() {
		return agoraHsiSubsCode;
	}

	public void setAgoraHsiSubsCode(String agoraHsiSubsCode) {
		this.agoraHsiSubsCode = agoraHsiSubsCode;
	}

	public String getInstTaxgradeLov() {
		return instTaxgradeLov;
	}

	public void setInstTaxgradeLov(String instTaxgradeLov) {
		this.instTaxgradeLov = instTaxgradeLov;
	}

	public Date getActDate() {
		return actDate;
	}

	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	public CpeModal getStbModel() {
		return stbModel;
	}

	public void setStbModel(CpeModal stbModel) {
		this.stbModel = stbModel;
	}

	public OLTPortDetails getOlPortDetails() {
		return olPortDetails;
	}

	public void setOlPortDetails(OLTPortDetails olPortDetails) {
		this.olPortDetails = olPortDetails;
	}

	public String getPortSplit() {
		return portSplit;
	}

	public void setPortSplit(String portSplit) {
		this.portSplit = portSplit;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactmobileNo() {
		return contactmobileNo;
	}

	public void setContactmobileNo(String contactmobileNo) {
		this.contactmobileNo = contactmobileNo;
	}

	public String getPaymentResponsible() {
		return paymentResponsible;
	}

	public void setPaymentResponsible(String paymentResponsible) {
		this.paymentResponsible = paymentResponsible;
	}

}
