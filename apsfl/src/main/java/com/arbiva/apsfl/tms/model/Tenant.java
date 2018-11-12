package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tenants")
public class Tenant implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Tenant() {
		
	}

	@Id
	@Column(name = "tenantId", unique = true, nullable = false)
	private Integer tenantId;
	
	@Column(name = "tenantcode")
	private String tenantCode;
	
	@Column(name = "tenantname")
	private String name;
	
	@Column(name = "aadharcardno")
	private String aadharCardNo;
	
	@Column(name = "tenanttypelov")
	private String tenantTypeLov;
	
	@Column(name = "panno")
	private String panNo;
	
	@Column(name = "tanno")
	private String tanNo;
	
	@Column(name = "tinno")
	private String tinNo;
	
	@Column(name = "gstno")
	private String gstNo;
	
	@Column(name = "vatno")
	private String vatNo;
	
	@Column(name = "regoff_addr1")
	private String address1;
	
	@Column(name = "regoff_addr2")
	private String address2;
	
	@Column(name = "regoff_locality")
	private String locality;
	
	@Column(name = "regoff_area")
	private String area;
	
	@Column(name = "regoff_city")
	private String city;
	
	@Column(name = "regoff_state")
	private String stateName;
	
	@Column(name = "regoff_pin")
	private String pincode;
	
	@Column(name = "regoff_stdcode")
	private String stdcode;
	
	@Column(name = "regoff_landline1")
	private String landline1;
	
	@Column(name = "regoff_landline2")
	private String landline2;
	
	@Column(name = "regoff_email1")
	private String emailId1;
	
	@Column(name = "regoff_email2")
	private String emailId2;
	
	@Column(name = "regoff_fax1")
	private String fax1;
	
	@Column(name = "regoff_fax2")
	private String fax2;
	
	@Column(name = "regoff_pocname")
	private String pocName;
	
	@Column(name = "regoff_pocmob1")
	private String pocMobileNo1;
	
	@Column(name = "regoff_pocmob2")
	private String pocMobileNo2;
	
	@Column(name = "locoff_addr1")
	private String localOfficeAddress1;
	
	@Column(name = "locoff_addr2")
	private String localOfficeAddress2;
	
	@Column(name = "locoff_locality")
	private String localOfficeLocality;
	
	@Column(name = "locoff_area")
	private String localOfficeArea;
	
	@Column(name ="locoff_city")
	private String localOfficeCity;
	
	@Column(name = "locoff_state")
	private String localOfficeStateName;
	
	@Column(name = "locoff_pin")
	private String localOfficePincode;
	
	@Column(name = "locoff_stdcode")
	private String localOfficestdcode;
	
	@Column(name = "locoff_landline1")
	private String localOfficeLandline1;
	
	@Column(name = "locoff_landline2")
	private String localOfficeLandline2;
	
	@Column(name = "locoff_email1")
	private String localOfficeEmailId1;
	
	@Column(name = "locoff_email2")
	private String localOfficeEmailId2;
	
	@Column(name = "locoff_fax1")
	private String localOfficeFax1;
	
	@Column(name = "locoff_fax2")
	private String localOfficeFax2;
	
	@Column(name = "locoff_pocname")
	private String localOfficePocName;
	
	@Column(name = "locoff_pocmob1")
	private String localOfficePocMobileNo1;
	
	@Column(name = "locoff_pocmob2")
	private String localOfficePocMobileNo2;
	

	@Column(name = "status")
	private Integer status;
	
	@Column(name = "portal_enrollmentno")
	private String portalEnrllmentno;
	
	@Column(name = "portal_loginid")
	private String portal_loginid;
	
	@Column(name = "portal_postalregno")
	private String portalPostalRegno;
	
	@Column(name = "portal_postexpdate")
	//@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(style = "MM")
	private String portalPostExpDate;
	
	@Column(name = "portal_habitation")
	private String portal_habitation;
	
	@Column(name = "portal_srvc_yrs")
	private Integer portal_srvc_yrs;
	
	@Column(name = "portal_emp_cnt")
	private Integer portal_emp_cnt;
	
	@Column(name = "portal_subscriber_cnt")
	private Long portal_subscriber_cnt;
	
	@Column(name = "portal_conn_cnt")
	private Long portal_conn_cnt;
	
	@Column(name = "portal_mso_name")
	private String portalMsoName;
	
	@Column(name = "portal_daslicence_provider")
	private String portalDasLicenceProvider;
	
	@Column(name = "portal_prndoc")
	private String portal_prndoc;
	
	@Column(name = "portal_villageid")
	private Integer portal_villageid;
	
	@Column(name = "portal_mandalid")
	private Integer portal_mandalid;
	
	@Column(name = "portal_districtid")
	private Integer portal_districtid;
	
	@Column(name = "portal_stateid")
	private Integer portal_stateid;
	
	@Column(name = "portal_substn1_id")
	private String portalSubstn1Id;
	
	
	@Column(name = "portal_substn1_distance")
	private Float portalSubstn1Distance;
	
	@Column(name = "portal_substn2_id")
	private String portalSubstn2Id;
	
	@Column(name = "portal_substn2_distance")
	private Float portalSubstn2Distance;
	
	@Column(name = "portal_rgnmsp1")
	private String portalRgnmsp1;
	
	@Column(name = "portal_regmsp1_assocyrs")
	private Integer portalRegMsp1AssocYrs;
	
	@Column(name = "portal_locmsp1")
	private String portalLocmsp1;
	
	@Column(name = "portal_locmsp1_assocyrs")
	private Integer portalLocMsp1AssocYrs;
	
	@Column(name = "portal_rgnmsp2")
	private String portalRgnMsp2;
	
	@Column(name = "portal_regmsp2_assocyrs")
	private Integer portalRegMsp2AssocYrs;
	
	@Column(name = "portal_locmsp2")
	private String portalLocMsp2;
	
	@Column(name = "portal_locmsp2_assocyrs")
	private Integer portalLocMsp2AssocYrs;
	
	@Column(name = "portal_rgnmsp3")
	private String portalRgnMsp3;
	
	@Column(name = "portal_regmsp3_assocyrs")
	private Integer portalRegMsp3AssocYrs;
	
	@Column(name = "portal_locmsp3")
	private String portalLocMsp3;
	
	@Column(name = "portal_locmsp3_assocyrs")
	private Integer portalLocMsp3AssocYrs;
	
	@Column(name = "portal_rgnmsp4")
	private String portalRgnMsp4;
	
	@Column(name = "portal_regmsp4_assocyrs")
	private Integer portalRegMsp4AssocYrs;
	
	@Column(name = "portal_locmsp4")
	private String portalLocMsp4;
	
	@Column(name = "portal_locmsp4_assocyrs")
	private Integer portalLocMsp4AssocYrs;
	
	@Column(name = "portal_rgnmsp5")
	private String portalRgnMsp5;
	
	@Column(name = "portal_regmsp5_assocyrs")
	private Integer portalRegMsp5Assocyrs;
	
	@Column(name = "portal_locmsp5")
	private String portalLocMsp5;
	
	@Column(name = "portal_locmsp5_assocyrs")
	private Integer portalLocMsp5AssocYrs;
	
	@Column(name = "portal_rgnmsp6")
	private String portalRgnMsp6;
	
	@Column(name = "portal_regmsp6_assocyrs")
	private Integer portalRegMsp6Assocyrs;
	
	@Column(name = "portal_locmsp6")
	private String portalLocMsp6;
	
	@Column(name = "portal_locmsp6_assocyrs")
	private Integer portalLocMsp6AssocYrs;
	
	@Column(name = "portal_dgtconn_cnt")
	private Long portalDgtConnCnt;
	
	@Column(name = "portal_anlconn_cnt")
	private Long portalAnlConnCnt;
	
	@Column(name = "portal_daslicense")
	private String portalDasLicense;
	
	@Column(name = "portal_daslicencetype")
	private String portalDasLicenceType;
	
	@Column(name = "portal_daslicenseholder")
	private String portalDasLicenseHolder;
	
	@Column(name = "portal_daslicenseexpdate")
	//@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(style = "MM")
	private String portalDasLicenseExpDate;
	
	@Column(name = "portal_paychnl_cnt")
	private Integer portalPaychnlCnt;
	
	@Column(name = "portal_paychnl_amt")
	private Long portal_paychnl_amt;
		
	@Column(name = "portal_companytype")
	private String portalCompanyType;
	
	@Column(name = "portal_partnername")
	private String portalPartnerName;
	
	@Column(name = "portal_household_cnt")
	private Long portalHouseHoldCnt;
	
	@Column(name = "portal_miblicenseno")  
	private String portalMibLicenseNo;
	
	@Column(name = "portal_miblicenseexpdate")
	//@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(style = "MM")
	private String portalMibLicenseExpDate;
	
	@Column(name = "portal_msodoc")
	private String portal_msodoc;
	
	@Column(name = "createdon")
	private Calendar createdDate;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "createdipaddr")
	private String cratedIPAddress;
	
	@Column(name = "modifiedon")
	private Calendar modifiedDate;
	
	@Column(name = "modifiedby")
	private String modifiedBy;
	
	private @Column(name = "modifiedipaddr")
	String modifiedIPAddress;
	
	@Column(name = "deactivatedon")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	private Calendar deactivatedDate;
	
	@Column(name = "deactivatedby")
	private String deactivatedBy;
	
	@Column(name = "deactivatedipaddr")
	private String deactivatedIpAddress;
	
	/*@OneToMany(targetEntity=TenantLmoMspAgreement.class, mappedBy="tenantMsp", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	private List<TenantLmoMspAgreement> tenantLmoMspAgreementsMsp;
	
	@OneToMany(targetEntity=TenantLmoMspAgreement.class, mappedBy="tenantLmo", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	private List<TenantLmoMspAgreement> tenantLmoMspAgreementsLmo;

	public List<TenantLmoMspAgreement> getTenantLmoMspAgreementsMsp() {
		return tenantLmoMspAgreementsMsp;
	}

	public void setTenantLmoMspAgreementsMsp(List<TenantLmoMspAgreement> tenantLmoMspAgreementsMsp) {
		this.tenantLmoMspAgreementsMsp = tenantLmoMspAgreementsMsp;
	}

	public List<TenantLmoMspAgreement> getTenantLmoMspAgreementsLmo() {
		return tenantLmoMspAgreementsLmo;
	}

	public void setTenantLmoMspAgreementsLmo(List<TenantLmoMspAgreement> tenantLmoMspAgreementsLmo) {
		this.tenantLmoMspAgreementsLmo = tenantLmoMspAgreementsLmo;
	}*/
	
	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	

	public String getPortalPostalRegno() {
		return portalPostalRegno;
	}

	public void setPortalPostalRegno(String portalPostalRegno) {
		this.portalPostalRegno = portalPostalRegno;
	}

	public String getPortalPostExpDate() {
		return portalPostExpDate;
	}

	public void setPortalPostExpDate(String portalPostExpDate) {
		this.portalPostExpDate = portalPostExpDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAadharCardNo() {
		return aadharCardNo;
	}

	public void setAadharCardNo(String aadharCardNo) {
		this.aadharCardNo = aadharCardNo;
	}

	public String getTenantTypeLov() {
		return tenantTypeLov;
	}

	public void setTenantTypeLov(String tenantTypeLov) {
		this.tenantTypeLov = tenantTypeLov;
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

	public String getTinNo() {
		return tinNo;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
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

	public String getLocalOfficeCity() {
		return localOfficeCity;
	}

	public void setLocalOfficeCity(String localOfficeCity) {
		this.localOfficeCity = localOfficeCity;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStdcode() {
		return stdcode;
	}

	public void setStdcode(String stdcode) {
		this.stdcode = stdcode;
	}

	public String getLandline1() {
		return landline1;
	}

	public void setLandline1(String landline1) {
		this.landline1 = landline1;
	}

	public String getLandline2() {
		return landline2;
	}

	public void setLandline2(String landline2) {
		this.landline2 = landline2;
	}

	public String getEmailId1() {
		return emailId1;
	}

	public void setEmailId1(String emailId1) {
		this.emailId1 = emailId1;
	}

	public String getEmailId2() {
		return emailId2;
	}

	public void setEmailId2(String emailId2) {
		this.emailId2 = emailId2;
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

	public String getPocMobileNo1() {
		return pocMobileNo1;
	}

	public void setPocMobileNo1(String pocMobileNo1) {
		this.pocMobileNo1 = pocMobileNo1;
	}

	public String getPocMobileNo2() {
		return pocMobileNo2;
	}

	public void setPocMobileNo2(String pocMobileNo2) {
		this.pocMobileNo2 = pocMobileNo2;
	}

	public String getLocalOfficeAddress1() {
		return localOfficeAddress1;
	}

	public void setLocalOfficeAddress1(String localOfficeAddress1) {
		this.localOfficeAddress1 = localOfficeAddress1;
	}

	public String getLocalOfficeAddress2() {
		return localOfficeAddress2;
	}

	public void setLocalOfficeAddress2(String localOfficeAddress2) {
		this.localOfficeAddress2 = localOfficeAddress2;
	}

	public String getLocalOfficeLocality() {
		return localOfficeLocality;
	}

	public void setLocalOfficeLocality(String localOfficeLocality) {
		this.localOfficeLocality = localOfficeLocality;
	}

	public String getLocalOfficeArea() {
		return localOfficeArea;
	}

	public void setLocalOfficeArea(String localOfficeArea) {
		this.localOfficeArea = localOfficeArea;
	}

	public String getLocalOfficePincode() {
		return localOfficePincode;
	}

	public void setLocalOfficePincode(String localOfficePincode) {
		this.localOfficePincode = localOfficePincode;
	}

	public String getLocalOfficestdcode() {
		return localOfficestdcode;
	}

	public void setLocalOfficestdcode(String localOfficestdcode) {
		this.localOfficestdcode = localOfficestdcode;
	}

	public String getLocalOfficeLandline1() {
		return localOfficeLandline1;
	}

	public void setLocalOfficeLandline1(String localOfficeLandline1) {
		this.localOfficeLandline1 = localOfficeLandline1;
	}

	public String getLocalOfficeLandline2() {
		return localOfficeLandline2;
	}

	public void setLocalOfficeLandline2(String localOfficeLandline2) {
		this.localOfficeLandline2 = localOfficeLandline2;
	}

	public String getLocalOfficeEmailId1() {
		return localOfficeEmailId1;
	}

	public void setLocalOfficeEmailId1(String localOfficeEmailId1) {
		this.localOfficeEmailId1 = localOfficeEmailId1;
	}

	public String getLocalOfficeEmailId2() {
		return localOfficeEmailId2;
	}

	public void setLocalOfficeEmailId2(String localOfficeEmailId2) {
		this.localOfficeEmailId2 = localOfficeEmailId2;
	}

	public String getLocalOfficeFax1() {
		return localOfficeFax1;
	}

	public void setLocalOfficeFax1(String localOfficeFax1) {
		this.localOfficeFax1 = localOfficeFax1;
	}

	public String getLocalOfficeFax2() {
		return localOfficeFax2;
	}

	public void setLocalOfficeFax2(String localOfficeFax2) {
		this.localOfficeFax2 = localOfficeFax2;
	}

	public String getLocalOfficePocName() {
		return localOfficePocName;
	}

	public void setLocalOfficePocName(String localOfficePocName) {
		this.localOfficePocName = localOfficePocName;
	}

	public String getLocalOfficePocMobileNo1() {
		return localOfficePocMobileNo1;
	}

	public void setLocalOfficePocMobileNo1(String localOfficePocMobileNo1) {
		this.localOfficePocMobileNo1 = localOfficePocMobileNo1;
	}

	public String getLocalOfficePocMobileNo2() {
		return localOfficePocMobileNo2;
	}

	public void setLocalOfficePocMobileNo2(String localOfficePocMobileNo2) {
		this.localOfficePocMobileNo2 = localOfficePocMobileNo2;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getLocalOfficeStateName() {
		return localOfficeStateName;
	}

	public void setLocalOfficeStateName(String localOfficeStateName) {
		this.localOfficeStateName = localOfficeStateName;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	public String getPortalEnrllmentno() {
		return portalEnrllmentno;
	}

	public void setPortalEnrllmentno(String portalEnrllmentno) {
		this.portalEnrllmentno = portalEnrllmentno;
	}

	public String getPortal_loginid() {
		return portal_loginid;
	}

	public void setPortal_loginid(String portal_loginid) {
		this.portal_loginid = portal_loginid;
	}

	public String getPortal_habitation() {
		return portal_habitation;
	}

	public void setPortal_habitation(String portal_habitation) {
		this.portal_habitation = portal_habitation;
	}

	public Integer getPortal_srvc_yrs() {
		return portal_srvc_yrs;
	}

	public void setPortal_srvc_yrs(Integer portal_srvc_yrs) {
		this.portal_srvc_yrs = portal_srvc_yrs;
	}

	public Integer getPortal_emp_cnt() {
		return portal_emp_cnt;
	}

	public void setPortal_emp_cnt(Integer portal_emp_cnt) {
		this.portal_emp_cnt = portal_emp_cnt;
	}

	public Long getPortal_subscriber_cnt() {
		return portal_subscriber_cnt;
	}

	public void setPortal_subscriber_cnt(Long portal_subscriber_cnt) {
		this.portal_subscriber_cnt = portal_subscriber_cnt;
	}

	public Long getPortal_conn_cnt() {
		return portal_conn_cnt;
	}

	public void setPortal_conn_cnt(Long portal_conn_cnt) {
		this.portal_conn_cnt = portal_conn_cnt;
	}

	public String getPortalMsoName() {
		return portalMsoName;
	}

	public void setPortalMsoName(String portalMsoName) {
		this.portalMsoName = portalMsoName;
	}

	public String getPortalDasLicenceProvider() {
		return portalDasLicenceProvider;
	}

	public void setPortalDasLicenceProvider(String portalDasLicenceProvider) {
		this.portalDasLicenceProvider = portalDasLicenceProvider;
	}

	public String getPortal_prndoc() {
		return portal_prndoc;
	}

	public void setPortal_prndoc(String portal_prndoc) {
		this.portal_prndoc = portal_prndoc;
	}

	public Integer getPortal_villageid() {
		return portal_villageid;
	}

	public void setPortal_villageid(Integer portal_villageid) {
		this.portal_villageid = portal_villageid;
	}

	public Integer getPortal_mandalid() {
		return portal_mandalid;
	}

	public void setPortal_mandalid(Integer portal_mandalid) {
		this.portal_mandalid = portal_mandalid;
	}

	public Integer getPortal_districtid() {
		return portal_districtid;
	}

	public void setPortal_districtid(Integer portal_districtid) {
		this.portal_districtid = portal_districtid;
	}

	public Integer getPortal_stateid() {
		return portal_stateid;
	}

	public void setPortal_stateid(Integer portal_stateid) {
		this.portal_stateid = portal_stateid;
	}

	
	public String getPortalSubstn1Id() {
		return portalSubstn1Id;
	}

	public void setPortalSubstn1Id(String portalSubstn1Id) {
		this.portalSubstn1Id = portalSubstn1Id;
	}

	public Float getPortalSubstn1Distance() {
		return portalSubstn1Distance;
	}

	public void setPortalSubstn1Distance(Float portalSubstn1Distance) {
		this.portalSubstn1Distance = portalSubstn1Distance;
	}

	public String getPortalSubstn2Id() {
		return portalSubstn2Id;
	}

	public void setPortalSubstn2Id(String portalSubstn2Id) {
		this.portalSubstn2Id = portalSubstn2Id;
	}

	public Float getPortalSubstn2Distance() {
		return portalSubstn2Distance;
	}

	public void setPortalSubstn2Distance(Float portalSubstn2Distance) {
		this.portalSubstn2Distance = portalSubstn2Distance;
	}


	public Integer getPortalPaychnlCnt() {
		return portalPaychnlCnt;
	}

	public void setPortalPaychnlCnt(Integer portalPaychnlCnt) {
		this.portalPaychnlCnt = portalPaychnlCnt;
	}

	public Long getPortal_paychnl_amt() {
		return portal_paychnl_amt;
	}

	public void setPortal_paychnl_amt(Long portal_paychnl_amt) {
		this.portal_paychnl_amt = portal_paychnl_amt;
	}

	public String getPortalRgnmsp1() {
		return portalRgnmsp1;
	}

	public void setPortalRgnmsp1(String portalRgnmsp1) {
		this.portalRgnmsp1 = portalRgnmsp1;
	}

	public Integer getPortalRegMsp1AssocYrs() {
		return portalRegMsp1AssocYrs;
	}

	public void setPortalRegMsp1AssocYrs(Integer portalRegMsp1AssocYrs) {
		this.portalRegMsp1AssocYrs = portalRegMsp1AssocYrs;
	}

	public String getPortalLocmsp1() {
		return portalLocmsp1;
	}

	public void setPortalLocmsp1(String portalLocmsp1) {
		this.portalLocmsp1 = portalLocmsp1;
	}


	public Integer getPortalLocMsp1AssocYrs() {
		return portalLocMsp1AssocYrs;
	}

	public void setPortalLocMsp1AssocYrs(Integer portalLocMsp1AssocYrs) {
		this.portalLocMsp1AssocYrs = portalLocMsp1AssocYrs;
	}

	public String getPortalRgnMsp2() {
		return portalRgnMsp2;
	}

	public void setPortalRgnMsp2(String portalRgnMsp2) {
		this.portalRgnMsp2 = portalRgnMsp2;
	}


	public Integer getPortalRegMsp2AssocYrs() {
		return portalRegMsp2AssocYrs;
	}

	public void setPortalRegMsp2AssocYrs(Integer portalRegMsp2AssocYrs) {
		this.portalRegMsp2AssocYrs = portalRegMsp2AssocYrs;
	}

	public String getPortalLocMsp2() {
		return portalLocMsp2;
	}

	public void setPortalLocMsp2(String portalLocMsp2) {
		this.portalLocMsp2 = portalLocMsp2;
	}

	public Integer getPortalLocMsp2AssocYrs() {
		return portalLocMsp2AssocYrs;
	}

	public void setPortalLocMsp2AssocYrs(Integer portalLocMsp2AssocYrs) {
		this.portalLocMsp2AssocYrs = portalLocMsp2AssocYrs;
	}
	
	public String getPortalRgnMsp3() {
		return portalRgnMsp3;
	}

	public void setPortalRgnMsp3(String portalRgnMsp3) {
		this.portalRgnMsp3 = portalRgnMsp3;
	}


	public Integer getPortalRegMsp3AssocYrs() {
		return portalRegMsp3AssocYrs;
	}

	public void setPortalRegMsp3AssocYrs(Integer portalRegMsp3AssocYrs) {
		this.portalRegMsp3AssocYrs = portalRegMsp3AssocYrs;
	}

	public String getPortalLocMsp3() {
		return portalLocMsp3;
	}

	public void setPortalLocMsp3(String portalLocMsp3) {
		this.portalLocMsp3 = portalLocMsp3;
	}

	
	public Integer getPortalLocMsp3AssocYrs() {
		return portalLocMsp3AssocYrs;
	}

	public void setPortalLocMsp3AssocYrs(Integer portalLocMsp3AssocYrs) {
		this.portalLocMsp3AssocYrs = portalLocMsp3AssocYrs;
	}

	public String getPortalRgnMsp4() {
		return portalRgnMsp4;
	}

	public void setPortalRgnMsp4(String portalRgnMsp4) {
		this.portalRgnMsp4 = portalRgnMsp4;
	}
	
	public Integer getPortalRegMsp4AssocYrs() {
		return portalRegMsp4AssocYrs;
	}

	public void setPortalRegMsp4AssocYrs(Integer portalRegMsp4AssocYrs) {
		this.portalRegMsp4AssocYrs = portalRegMsp4AssocYrs;
	}

	public String getPortalLocMsp4() {
		return portalLocMsp4;
	}

	public void setPortalLocMsp4(String portalLocMsp4) {
		this.portalLocMsp4 = portalLocMsp4;
	}

	public Integer getPortalLocMsp4AssocYrs() {
		return portalLocMsp4AssocYrs;
	}

	public void setPortalLocMsp4AssocYrs(Integer portalLocMsp4AssocYrs) {
		this.portalLocMsp4AssocYrs = portalLocMsp4AssocYrs;
	}

	public String getPortalRgnMsp5() {
		return portalRgnMsp5;
	}

	public void setPortalRgnMsp5(String portalRgnMsp5) {
		this.portalRgnMsp5 = portalRgnMsp5;
	}

	public Integer getPortalRegMsp5Assocyrs() {
		return portalRegMsp5Assocyrs;
	}

	public void setPortalRegMsp5Assocyrs(Integer portalRegMsp5Assocyrs) {
		this.portalRegMsp5Assocyrs = portalRegMsp5Assocyrs;
	}

	public String getPortalLocMsp5() {
		return portalLocMsp5;
	}

	public void setPortalLocMsp5(String portalLocMsp5) {
		this.portalLocMsp5 = portalLocMsp5;
	}

	public Integer getPortalLocMsp5AssocYrs() {
		return portalLocMsp5AssocYrs;
	}

	public void setPortalLocMsp5AssocYrs(Integer portalLocMsp5AssocYrs) {
		this.portalLocMsp5AssocYrs = portalLocMsp5AssocYrs;
	}

	public String getPortalRgnMsp6() {
		return portalRgnMsp6;
	}

	public void setPortalRgnMsp6(String portalRgnMsp6) {
		this.portalRgnMsp6 = portalRgnMsp6;
	}

	public Integer getPortalRegMsp6Assocyrs() {
		return portalRegMsp6Assocyrs;
	}

	public void setPortalRegMsp6Assocyrs(Integer portalRegMsp6Assocyrs) {
		this.portalRegMsp6Assocyrs = portalRegMsp6Assocyrs;
	}

	public String getPortalLocMsp6() {
		return portalLocMsp6;
	}

	public void setPortalLocMsp6(String portalLocMsp6) {
		this.portalLocMsp6 = portalLocMsp6;
	}

	public Integer getPortalLocMsp6AssocYrs() {
		return portalLocMsp6AssocYrs;
	}

	public void setPortalLocMsp6AssocYrs(Integer portalLocMsp6AssocYrs) {
		this.portalLocMsp6AssocYrs = portalLocMsp6AssocYrs;
	}

	public Long getPortalDgtConnCnt() {
		return portalDgtConnCnt;
	}

	public void setPortalDgtConnCnt(Long portalDgtConnCnt) {
		this.portalDgtConnCnt = portalDgtConnCnt;
	}

	
	public Long getPortalAnlConnCnt() {
		return portalAnlConnCnt;
	}

	public void setPortalAnlConnCnt(Long portalAnlConnCnt) {
		this.portalAnlConnCnt = portalAnlConnCnt;
	}

	
	public String getPortalDasLicense() {
		return portalDasLicense;
	}

	public void setPortalDasLicense(String portalDasLicense) {
		this.portalDasLicense = portalDasLicense;
	}


	public String getPortalDasLicenceType() {
		return portalDasLicenceType;
	}

	public void setPortalDasLicenceType(String portalDasLicenceType) {
		this.portalDasLicenceType = portalDasLicenceType;
	}

	public String getPortalDasLicenseHolder() {
		return portalDasLicenseHolder;
	}

	public void setPortalDasLicenseHolder(String portalDasLicenseHolder) {
		this.portalDasLicenseHolder = portalDasLicenseHolder;
	}

	public String getPortalDasLicenseExpDate() {
		return portalDasLicenseExpDate;
	}

	public void setPortalDasLicenseExpDate(String portalDasLicenseExpDate) {
		this.portalDasLicenseExpDate = portalDasLicenseExpDate;
	}

	
	public String getPortalCompanyType() {
		return portalCompanyType;
	}

	public void setPortalCompanyType(String portalCompanyType) {
		this.portalCompanyType = portalCompanyType;
	}

	public String getPortalPartnerName() {
		return portalPartnerName;
	}

	public void setPortalPartnerName(String portalPartnerName) {
		this.portalPartnerName = portalPartnerName;
	}

	
	public Long getPortalHouseHoldCnt() {
		return portalHouseHoldCnt;
	}

	public void setPortalHouseHoldCnt(Long portalHouseHoldCnt) {
		this.portalHouseHoldCnt = portalHouseHoldCnt;
	}


	public String getPortalMibLicenseNo() {
		return portalMibLicenseNo;
	}

	public void setPortalMibLicenseNo(String portalMibLicenseNo) {
		this.portalMibLicenseNo = portalMibLicenseNo;
	}

	
	public String getPortalMibLicenseExpDate() {
		return portalMibLicenseExpDate;
	}

	public void setPortalMibLicenseExpDate(String portalMibLicenseExpDate) {
		this.portalMibLicenseExpDate = portalMibLicenseExpDate;
	}

	public String getPortal_msodoc() {
		return portal_msodoc;
	}

	public void setPortal_msodoc(String portal_msodoc) {
		this.portal_msodoc = portal_msodoc;
	}


	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCratedIPAddress() {
		return cratedIPAddress;
	}

	public void setCratedIPAddress(String cratedIPAddress) {
		this.cratedIPAddress = cratedIPAddress;
	}

	public Calendar getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Calendar getDeactivatedDate() {
		return deactivatedDate;
	}
	public void setDeactivatedDate(Calendar deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}
	public String getDeactivatedBy() {
		return deactivatedBy;
	}
	public void setDeactivatedBy(String deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}
	public String getDeactivatedIpAddress() {
		return deactivatedIpAddress;
	}
	public void setDeactivatedIpAddress(String deactivatedIpAddress) {
		this.deactivatedIpAddress = deactivatedIpAddress;
	}
	public String getModifiedIPAddress() {
		return modifiedIPAddress;
	}

	public void setModifiedIPAddress(String modifiedIPAddress) {
		this.modifiedIPAddress = modifiedIPAddress;
	}

}
