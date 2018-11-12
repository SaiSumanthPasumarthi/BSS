package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.arbiva.apsfl.coms.dto.OLTPortDetails;
import com.arbiva.apsfl.tms.model.Tenant;
import com.arbiva.apsfl.tms.model.TenantBankDetails;
import com.arbiva.apsfl.tms.model.TenantBussinessAreas;
import com.arbiva.apsfl.tms.model.TenantDocuments;
import com.arbiva.apsfl.tms.model.TenantLicenses;
import com.arbiva.apsfl.tms.model.TenantWallet;
import com.arbiva.apsfl.util.DateUtill;

public class TenantVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public TenantVO(){
		
	}
	
	public TenantVO(Tenant tenant, TenantBankDetails  tenantBankDetails, 
			TenantLicenses tenantLicenses, List<TenantDocuments> tenantDocuments,
			TenantWallet tenantWallet){
		try{
			this.tenantId = tenant.getTenantId();
			this.creditLimit = tenantWallet.getCrLimitAmt() == null ? "0" : String.valueOf(tenantWallet.getCrLimitAmt());
			this.walletAmt = tenantWallet.getWalletAmount() == null ? "0" : String.valueOf(tenantWallet.getWalletAmount());
			this.tenantCode = tenant.getTenantCode();
			this.name = tenant.getName();
			this.aadharCardNo = tenant.getAadharCardNo();
			this.tenantTypeLov = tenant.getTenantTypeLov();
			this.panNo = tenant.getPanNo();
			this.tanNo = tenant.getTanNo();
			this.tinNo = tenant.getTinNo();
			this.gstNo = tenant.getGstNo();
			this.vatNo = tenant.getVatNo();
			this.portalEnrllmentno = tenant.getPortalEnrllmentno();
			this.portalPostalRegno = tenant.getPortalPostalRegno();
			this.portalPostExpDate = tenant.getPortalPostExpDate();
			this.address1 = tenant.getAddress1();
			this.address2 = tenant.getAddress2();
			this.locality = tenant.getLocality();
			this.area = tenant.getArea();
			this.city = tenant.getCity();
			this.pincode = tenant.getPincode();
			this.stdcode = tenant.getStdcode();
			this.landline1 = tenant.getLandline1();
			this.landline2 = tenant.getLandline2();
			this.emailId1 = tenant.getEmailId1();
			this.emailId2 = tenant.getEmailId2();
			this.fax1 = tenant.getFax1();
			this.fax2 = tenant.getFax2();
			this.pocName = tenant.getPocName();
			this.pocMobileNo1 = tenant.getPocMobileNo1();
			this.pocMobileNo2 = tenant.getPocMobileNo2();
			this.localOfficeAddress1 = tenant.getLocalOfficeAddress1();
			this.localOfficeAddress2 = tenant.getLocalOfficeAddress2();
			this.localOfficeLocality = tenant.getLocalOfficeLocality();
			this.localOfficeArea = tenant.getLocalOfficeArea();
			this.localOfficeCity = tenant.getLocalOfficeCity();
			this.portalSubstn1Id = tenant.getPortalSubstn1Id();
			this.portalSubstn1Distance = tenant.getPortalSubstn1Distance();
			this.portalSubstn2Id = tenant.getPortalSubstn2Id();
			this.portalSubstn2Distance = tenant.getPortalSubstn2Distance();
			this.portalMsoName = tenant.getPortalMsoName();
			this.portalDasLicenceProvider = tenant.getPortalDasLicenceProvider();
			this.portalRgnmsp1 = tenant.getPortalRgnmsp1();
			this.portalRegMsp1AssocYrs = tenant.getPortalRegMsp1AssocYrs();
			this.portalLocmsp1 = tenant.getPortalLocmsp1();
			this.portalLocMsp1AssocYrs = tenant.getPortalLocMsp1AssocYrs();
			this.portalRgnMsp2 = tenant.getPortalRgnMsp2();
			this.portalRegMsp2AssocYrs = tenant.getPortalRegMsp2AssocYrs();
			this.portalLocMsp2 = tenant.getPortalLocMsp2();
			this.portalLocMsp2AssocYrs = tenant.getPortalLocMsp2AssocYrs();
			this.portalRgnMsp3 = tenant.getPortalRgnMsp3();
			this.portalRegMsp3AssocYrs = tenant.getPortalRegMsp3AssocYrs();
			this.portalLocMsp3 = tenant.getPortalLocMsp3();
			this.portalLocMsp3AssocYrs = tenant.getPortalLocMsp3AssocYrs();
			this.portalRgnMsp4 = tenant.getPortalRgnMsp4();
			this.portalRegMsp4AssocYrs = tenant.getPortalRegMsp4AssocYrs();
			this.portalLocMsp4 = tenant.getPortalLocMsp4();
			this.portalLocMsp4AssocYrs = tenant.getPortalLocMsp4AssocYrs();
			this.portalRgnMsp5 = tenant.getPortalRgnMsp5();
			this.portalRegMsp5Assocyrs = tenant.getPortalRegMsp5Assocyrs();
			this.portalLocMsp5 = tenant.getPortalLocMsp5();
			this.portalLocMsp5AssocYrs = tenant.getPortalLocMsp5AssocYrs();
			this.portalRgnMsp6 = tenant.getPortalRgnMsp6();
			this.portalRegMsp6Assocyrs = tenant.getPortalRegMsp6Assocyrs();
			this.portalLocMsp6 = tenant.getPortalLocMsp6();
			this.portalLocMsp6AssocYrs = tenant.getPortalLocMsp6AssocYrs();
			this.portalDgtConnCnt = tenant.getPortalDgtConnCnt();
			this.portalAnlConnCnt = tenant.getPortalAnlConnCnt();
			this.portalDasLicense = tenant.getPortalDasLicense();
			this.portalDasLicenceType = tenant.getPortalDasLicenceType();
			this.portalDasLicenseHolder = tenant.getPortalDasLicenseHolder();
			this.portalDasLicenseExpDate = tenant.getPortalDasLicenseExpDate();
			this.portalPaychnlCnt = tenant.getPortalPaychnlCnt();
			this.portalCompanyType = tenant.getPortalCompanyType();
			this.portalPartnerName = tenant.getPortalPartnerName();
			this.portalHouseHoldCnt = tenant.getPortalHouseHoldCnt();
			this.portalMibLicenseNo = tenant.getPortalMibLicenseNo();
			this.portalMibLicenseExpDate = tenant.getPortalMibLicenseExpDate();
			this.localOfficePincode = tenant.getLocalOfficePincode();
			this.localOfficestdcode = tenant.getLocalOfficestdcode();
			this.localOfficeLandline1 = tenant.getLocalOfficeLandline1();
			this.localOfficeLandline2 = tenant.getLocalOfficeLandline2();
			this.localOfficeEmailId1 = tenant.getLocalOfficeEmailId1();
			this.localOfficeEmailId2 = tenant.getLocalOfficeEmailId2();
			this.localOfficeFax1 = tenant.getLocalOfficeFax1();
			this.localOfficeFax2 = tenant.getLocalOfficeFax2();
			this.localOfficePocName = tenant.getLocalOfficePocName();
			this.localOfficePocMobileNo1 = tenant.getLocalOfficePocMobileNo1();
			this.localOfficePocMobileNo2 = tenant.getLocalOfficePocMobileNo2();
			this.stateName = tenant.getStateName();
			this.localOfficeStateName = tenant.getLocalOfficeStateName();
			this.region =  tenantLicenses.getRegion() == null ? "" : tenantLicenses.getRegion().getRegionName();
			this.accountNo = tenantBankDetails.getAccountNo();
			this.ifscCode = tenantBankDetails.getIfscCode();
			this.acctTypelov = tenantBankDetails.getAcctTypelov();
			this.bankNamelov = tenantBankDetails.getBankNamelov();
			this.branchName = tenantBankDetails.getBranchName();
			this.portal_districtid = tenant.getPortal_districtid();
			this.portal_mandalid = tenant.getPortal_mandalid();
			this.portal_villageid = tenant.getPortal_villageid();
			for(TenantDocuments tenantDocs:tenantDocuments)
			{
				if(tenantDocs.getDocumentLovName().equalsIgnoreCase("GENERAL DOCUMENTS"))
				{
					this.licenserefno = tenantDocs.getDocuniqueId();
					this.doclov = tenantDocs.getDocmentLov();
					this.effectiveFrom = tenantDocs.getEffectiveFrom() == null ? "" : DateUtill.dateToString(tenantDocs.getEffectiveFrom());
					this.effectiveTO = tenantDocs.getEffectiveTo() == null ? "" : DateUtill.dateToString(tenantDocs.getEffectiveTo());
				}
				else if(tenantDocs.getDocumentLovName().equalsIgnoreCase("POI DOCUMENTS"))
				{
					this.docUniqueId1 = tenantDocs.getDocuniqueId();
					this.doclov1 = tenantDocs.getDocmentLov();
					this.effectiveFrom1 = tenantDocs.getEffectiveFrom() == null ? "" : DateUtill.dateToString(tenantDocs.getEffectiveFrom());
					this.effectiveTO1 = tenantDocs.getEffectiveTo() == null ? "" : DateUtill.dateToString(tenantDocs.getEffectiveTo());
				}
				else if(tenantDocs.getDocumentLovName().equalsIgnoreCase("POA DOCUMENTS"))
				{
					this.docUniqueId2 = tenantDocs.getDocuniqueId();
					this.doclov2 = tenantDocs.getDocmentLov();
					this.effectiveFrom2 = tenantDocs.getEffectiveFrom() == null ? "" : DateUtill.dateToString(tenantDocs.getEffectiveFrom());
					this.effectiveTO2 = tenantDocs.getEffectiveTo() == null ? "" : DateUtill.dateToString(tenantDocs.getEffectiveTo());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	private Integer tenantId;
	
	private String creditLimit;
	
	private String walletAmt;
	
	private String tenantCode;
	
	private String name;
	
	private String aadharCardNo;
	
	private String tenantTypeLov;
	
	private String panNo;
	
	private String tanNo;
	
	private String tinNo;
	
	private String gstNo;
	
	private String vatNo;
	
	private String portalEnrllmentno;
	
	private String portalPostalRegno;
	
	private String portalPostExpDate;
	
	private String address1;
	
	private String address2;
	
	private String locality;
	
	private String area;
	
	private String city;
	
	private String pincode;
	
	private String stdcode;
	
	private String landline1;
	
	private String landline2;
	
	private String emailId1;
	
	private String emailId2;
	
	private String fax1;
	
	private String fax2;
	
	private String pocName;
	
	private String pocMobileNo1;
	
	private String pocMobileNo2;
	
	private String localOfficeAddress1;
	
	private String localOfficeAddress2;
	
	private String localOfficeLocality;
	
	private String localOfficeArea;
	
	private String localOfficeCity;
	
	private String portalSubstn1Id;
	
	private Float portalSubstn1Distance;
	
	private String portalSubstn2Id;
	
	private Float portalSubstn2Distance;
	
	private String portalMsoName;
	
	private String portalDasLicenceProvider;
	
	private String portalRgnmsp1;
	
	private Integer portalRegMsp1AssocYrs;
	
	private String portalLocmsp1;
	
	private Integer portalLocMsp1AssocYrs;
	
	private String portalRgnMsp2;
	
	private Integer portalRegMsp2AssocYrs;
	
	private String portalLocMsp2;
	
	private Integer portalLocMsp2AssocYrs;
	
	private String portalRgnMsp3;
	
	private Integer portalRegMsp3AssocYrs;
	
	private String portalLocMsp3;
	
	private Integer portalLocMsp3AssocYrs;
	
	private String portalRgnMsp4;
	
	private Integer portalRegMsp4AssocYrs;
	
	private String portalLocMsp4;
	
	private Integer portalLocMsp4AssocYrs;
	
	private String portalRgnMsp5;
	
	private Integer portalRegMsp5Assocyrs;
	
	private String portalLocMsp5;
	
	private Integer portalLocMsp5AssocYrs;
	
	private String portalRgnMsp6;
	
	private Integer portalRegMsp6Assocyrs;
	
	private String portalLocMsp6;
	
	private Integer portalLocMsp6AssocYrs;
	
	private Long portalDgtConnCnt;
	
	private Long portalAnlConnCnt;
	
	private String portalDasLicense;
	
	private String portalDasLicenceType;
	
	private String portalDasLicenseHolder;
	
	private String portalDasLicenseExpDate;
	
	private Integer portalPaychnlCnt;
	
	private String portalCompanyType;
	
	private String portalPartnerName;
	
	private Long portalHouseHoldCnt;
	
	private String portalMibLicenseNo;
	
	private String portalMibLicenseExpDate;
	
	private String tenantViewStatus;
	
	private List<TenantBussinessAreas> tenantsbaList;
	
	private List<Tenant> tenantList;
	
	private List<OLTPortDetails> oLTPortDetails;
	
	private Map<String, String> portSubscriberCount;
	
	private Map<String, String> portLmoCode;
	
	private Integer portal_districtid;
	
	private Integer portal_mandalid ;
	
	private Integer portal_villageid;
	
private Map<String, String> portCreatedOn;
	
	private Map<String, String> portCreatedBy;
	
	private Map<String, String> portModifiedOn;

	public Map<String, String> getPortCreatedOn() {
		return portCreatedOn;
	}

	public void setPortCreatedOn(Map<String, String> portCreatedOn) {
		this.portCreatedOn = portCreatedOn;
	}

	public Map<String, String> getPortCreatedBy() {
		return portCreatedBy;
	}

	public void setPortCreatedBy(Map<String, String> portCreatedBy) {
		this.portCreatedBy = portCreatedBy;
	}

	public Map<String, String> getPortModifiedOn() {
		return portModifiedOn;
	}

	public void setPortModifiedOn(Map<String, String> portModifiedOn) {
		this.portModifiedOn = portModifiedOn;
	}

	public Integer getPortal_districtid() {
		return portal_districtid;
	}

	public void setPortal_districtid(Integer portal_districtid) {
		this.portal_districtid = portal_districtid;
	}

	public Integer getPortal_mandalid() {
		return portal_mandalid;
	}

	public void setPortal_mandalid(Integer portal_mandalid) {
		this.portal_mandalid = portal_mandalid;
	}

	public Integer getPortal_villageid() {
		return portal_villageid;
	}

	public void setPortal_villageid(Integer portal_villageid) {
		this.portal_villageid = portal_villageid;
	}

	public List<TenantBussinessAreas> getTenantsbaList() {
		return tenantsbaList;
	}

	public void setTenantsbaList(List<TenantBussinessAreas> tenantsbaList) {
		this.tenantsbaList = tenantsbaList;
	}

	public String getTenantViewStatus() {
		return tenantViewStatus;
	}

	public void setTenantViewStatus(String tenantViewStatus) {
		this.tenantViewStatus = tenantViewStatus;
	}

	public String getWalletAmt() {
		return walletAmt;
	}

	public void setWalletAmt(String walletAmt) {
		this.walletAmt = walletAmt;
	}

	public String getPortalEnrllmentno() {
		return portalEnrllmentno;
	}

	public void setPortalEnrllmentno(String portalEnrllmentno) {
		this.portalEnrllmentno = portalEnrllmentno;
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

	public Integer getPortalPaychnlCnt() {
		return portalPaychnlCnt;
	}

	public void setPortalPaychnlCnt(Integer portalPaychnlCnt) {
		this.portalPaychnlCnt = portalPaychnlCnt;
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

	public Integer getPortalRegMsp2AssocYrs() {
		return portalRegMsp2AssocYrs;
	}

	public void setPortalRegMsp2AssocYrs(Integer portalRegMsp2AssocYrs) {
		this.portalRegMsp2AssocYrs = portalRegMsp2AssocYrs;
	}

	public Integer getPortalRegMsp3AssocYrs() {
		return portalRegMsp3AssocYrs;
	}

	public void setPortalRegMsp3AssocYrs(Integer portalRegMsp3AssocYrs) {
		this.portalRegMsp3AssocYrs = portalRegMsp3AssocYrs;
	}

	public Integer getPortalRegMsp4AssocYrs() {
		return portalRegMsp4AssocYrs;
	}

	public void setPortalRegMsp4AssocYrs(Integer portalRegMsp4AssocYrs) {
		this.portalRegMsp4AssocYrs = portalRegMsp4AssocYrs;
	}

	public Integer getPortalRegMsp5Assocyrs() {
		return portalRegMsp5Assocyrs;
	}

	public void setPortalRegMsp5Assocyrs(Integer portalRegMsp5Assocyrs) {
		this.portalRegMsp5Assocyrs = portalRegMsp5Assocyrs;
	}

	public Integer getPortalRegMsp6Assocyrs() {
		return portalRegMsp6Assocyrs;
	}

	public void setPortalRegMsp6Assocyrs(Integer portalRegMsp6Assocyrs) {
		this.portalRegMsp6Assocyrs = portalRegMsp6Assocyrs;
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

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getLocalOfficeCity() {
		return localOfficeCity;
	}

	public void setLocalOfficeCity(String localOfficeCity) {
		this.localOfficeCity = localOfficeCity;
	}

	private String localOfficePincode;
	
	private String localOfficestdcode;
	
	private String localOfficeLandline1;
	
	private String localOfficeLandline2;
	
	private String localOfficeEmailId1;
	
	private String localOfficeEmailId2;
	
	private String localOfficeFax1;
	
	private String localOfficeFax2;
	
	private String localOfficePocName;
	
	private String localOfficePocMobileNo1;
	
	private String localOfficePocMobileNo2;
	
	private String stateName;
	
	private String localOfficeStateName;
	
	private String region;
	
	private MultipartFile licenceId;
	
	private MultipartFile idProof;
	
	private MultipartFile addressProof;
	
	private String effectiveFrom;
	
	private String effectiveTO;
	
	private String effectiveFrom1;
	
	private String effectiveTO1;
	
	private String effectiveFrom2;
	
	private String effectiveTO2;
	
	private String docUniqueId;
	
	private String docUniqueId1;
	
	private String docUniqueId2;
	
	private String licenserefno;
	
	private Character regionType;
	
	private String licenseexpDate;
	
	private String licenseAuthority;
	
	private String accountNo;
	
	private String ifscCode;
	
	private String acctTypelov;
	
	private String bankNamelov;
	
	private String doclov;
	
	private String doclov1;
	
	private String doclov2;
	
	public String getDoclov1() {
		return doclov1;
	}

	public void setDoclov1(String doclov1) {
		this.doclov1 = doclov1;
	}

	public String getDoclov2() {
		return doclov2;
	}

	public void setDoclov2(String doclov2) {
		this.doclov2 = doclov2;
	}

	public String getEffectiveFrom1() {
		return effectiveFrom1;
	}

	public void setEffectiveFrom1(String effectiveFrom1) {
		this.effectiveFrom1 = effectiveFrom1;
	}

	public String getEffectiveTO1() {
		return effectiveTO1;
	}

	public void setEffectiveTO1(String effectiveTO1) {
		this.effectiveTO1 = effectiveTO1;
	}

	public String getEffectiveFrom2() {
		return effectiveFrom2;
	}

	public void setEffectiveFrom2(String effectiveFrom2) {
		this.effectiveFrom2 = effectiveFrom2;
	}

	public String getEffectiveTO2() {
		return effectiveTO2;
	}

	public void setEffectiveTO2(String effectiveTO2) {
		this.effectiveTO2 = effectiveTO2;
	}

	public String getDocUniqueId() {
		return docUniqueId;
	}

	public void setDocUniqueId(String docUniqueId) {
		this.docUniqueId = docUniqueId;
	}

	public String getDocUniqueId1() {
		return docUniqueId1;
	}

	public void setDocUniqueId1(String docUniqueId1) {
		this.docUniqueId1 = docUniqueId1;
	}

	public String getDoclov() {
		return doclov;
	}

	public void setDoclov(String doclov) {
		this.doclov = doclov;
	}

	public String getDocUniqueId2() {
		return docUniqueId2;
	}

	public void setDocUniqueId2(String docUniqueId2) {
		this.docUniqueId2 = docUniqueId2;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getEffectiveTO() {
		return effectiveTO;
	}

	public void setEffectiveTO(String effectiveTO) {
		this.effectiveTO = effectiveTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String branchName;
	
	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public MultipartFile getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(MultipartFile licenceId) {
		this.licenceId = licenceId;
	}

	public MultipartFile getIdProof() {
		return idProof;
	}

	public void setIdProof(MultipartFile idProof) {
		this.idProof = idProof;
	}

	public MultipartFile getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(MultipartFile addressProof) {
		this.addressProof = addressProof;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getLicenserefno() {
		return licenserefno;
	}

	public void setLicenserefno(String licenserefno) {
		this.licenserefno = licenserefno;
	}

	public Character getRegionType() {
		return regionType;
	}

	public void setRegionType(Character regionType) {
		this.regionType = regionType;
	}

	public String getLicenseexpDate() {
		return licenseexpDate;
	}

	public void setLicenseexpDate(String licenseexpDate) {
		this.licenseexpDate = licenseexpDate;
	}

	public String getLicenseAuthority() {
		return licenseAuthority;
	}

	public void setLicenseAuthority(String licenseAuthority) {
		this.licenseAuthority = licenseAuthority;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAcctTypelov() {
		return acctTypelov;
	}

	public void setAcctTypelov(String acctTypelov) {
		this.acctTypelov = acctTypelov;
	}

	public String getBankNamelov() {
		return bankNamelov;
	}

	public void setBankNamelov(String bankNamelov) {
		this.bankNamelov = bankNamelov;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	
	private String area_enrollmentno;
	
	private List<ListOfPortalAreas> pareas = new ArrayList<>();


	public List<ListOfPortalAreas> getPareas() {
		return pareas;
	}

	public void setPareas(List<ListOfPortalAreas> pareas) {
		this.pareas = pareas;
	}

	
	public String getArea_enrollmentno() {
		return area_enrollmentno;
	}

	public void setArea_enrollmentno(String area_enrollmentno) {
		this.area_enrollmentno = area_enrollmentno;
	}

	
	private String asset_enrollmentno;
	
	private List<ListOfPortalAssets> passets = new ArrayList<>();
	
	
	public String getAsset_enrollmentno() {
		return asset_enrollmentno;
	}

	public List<ListOfPortalAssets> getPassets() {
		return passets;
	}

	public void setPassets(List<ListOfPortalAssets> passets) {
		this.passets = passets;
	}

	public void setAsset_enrollmentno(String asset_enrollmentno) {
		this.asset_enrollmentno = asset_enrollmentno;
	}

	public List<Tenant> getTenantList() {
		return tenantList;
	}

	public void setTenantList(List<Tenant> tenantList) {
		this.tenantList = tenantList;
	}

	public List<OLTPortDetails> getoLTPortDetails() {
		return oLTPortDetails;
	}

	public void setoLTPortDetails(List<OLTPortDetails> oLTPortDetails) {
		this.oLTPortDetails = oLTPortDetails;
	}

	public Map<String, String> getPortSubscriberCount() {
		return portSubscriberCount;
	}

	public void setPortSubscriberCount(Map<String, String> portSubscriberCount) {
		this.portSubscriberCount = portSubscriberCount;
	}

	public Map<String, String> getPortLmoCode() {
		return portLmoCode;
	}

	public void setPortLmoCode(Map<String, String> portLmoCode) {
		this.portLmoCode = portLmoCode;
	}
	
	
}
