/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Lakshman
 *
 */
public class CustomerCafVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer custId;

	private String custCode;

	private String loginId;

	private String prodCode;

	private String tenantCode;

	private String effectiveFrom;

	private Long cafNo;

	private String ipAddress;

	private String custType;

	private String lmoCode;

	private String lmoName;

	private String panNo;

	private String tanNo;

	private String aadharNumber;

	private String titleLovName;

	private String firstName;

	private String middleName;

	private String lastName;

	private String fatherName;

	private String dob;

	private String gender;

	private String emailId;

	private String billCycle;

	private String segment;

	private String channel;

	private String cpeId;

	private String cpeLease;

	private Float instCharge;

	private String longitude;

	private String latitude;

	private String location;

	private String address1;

	private String address2;

	private String locality;

	private String city;

	private String state;

	private String district;

	private String mandal;

	private String pinCode;

	private String stdCode;

	private String mobileNo;

	private String mobileNo1;

	private String landLineNo;

	private String customerDec;

	private String lmoDec;

	private String agruniqueid;

	private String totalAmount;

	private String totalTax;

	private String popDistrict;

	private String popMandal;

	private String oltId;

	private String oltPortId;

	private String cpeModal;

	private String popId;

	private String pocName;

	private String onuEmiPrice;

	private String cpePrice;

	private String cableCharge;

	private List<ProductsVO> products;

	private List<CafAndCpeChargesVO> cafAndCpeChargesList;

	private String featureCodes;

	private List<FeatuireVO> featureCodeList;

	private String telProdCode;

	private String telSrvcCode;

	private String telTenantCode;

	private String flag;

	private String installmentCount;

	private String status;

	private String onuMacAddress;

	private String entCustomerCode;

	private String entCustType;

	private String l1Slot;

	private String l2Slot;

	private String l3Slot;

	@JsonIgnore
	private MultipartFile bulkUploadFile;

	private String cafStatus;

	private List<Caf> cafList;

	private String coreSrvcCode;

	private String pocDesignation;

	private String bulkUpload;

	private String tenantType;

	private String fileName;

	private String fileSize;

	private String noOfCols;

	private String noOfRows;
	
	private String apsflUniqueId;
	
	private String vpnService;
	
	private List<IPTVBoxVO> iptvBoxList;
	
	private String noOfTPConn;
	
	private String iptvPackage;
	
	private String version;
	
	private String custDistrictId;
	
	private String changePkgFlag;
	
	private String prodCafNo;
	
	private ChangePkgBO changePkgBO;
	
	private String cityVillage;
	
	public String getCityVillage() {
		return cityVillage;
	}

	public void setCityVillage(String cityVillage) {
		this.cityVillage = cityVillage;
	}
	
	public ChangePkgBO getChangePkgBO() {
		return changePkgBO;
	}

	public void setChangePkgBO(ChangePkgBO changePkgBO) {
		this.changePkgBO = changePkgBO;
	}
	
	public String getProdCafNo() {
		return prodCafNo;
	}

	public void setProdCafNo(String prodCafNo) {
		this.prodCafNo = prodCafNo;
	}

	
	public String getChangePkgFlag() {
		return changePkgFlag;
	}

	public void setChangePkgFlag(String changePkgFlag) {
		this.changePkgFlag = changePkgFlag;
	}

	public String getCustDistrictId() {
		return custDistrictId;
	}

	public void setCustDistrictId(String custDistrictId) {
		this.custDistrictId = custDistrictId;
	}

	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	public String getIptvPackage() {
		return iptvPackage;
	}

	public void setIptvPackage(String iptvPackage) {
		this.iptvPackage = iptvPackage;
	}

	public String getNoOfTPConn() {
		return noOfTPConn;
	}

	public void setNoOfTPConn(String noOfTPConn) {
		this.noOfTPConn = noOfTPConn;
	}

	public List<IPTVBoxVO> getIptvBoxList() {
		return iptvBoxList;
	}

	public void setIptvBoxList(List<IPTVBoxVO> iptvBoxList) {
		this.iptvBoxList = iptvBoxList;
	}

	public String getVpnService() {
		return vpnService;
	}

	public void setVpnService(String vpnService) {
		this.vpnService = vpnService;
	}

	public String getApsflUniqueId() {
		return apsflUniqueId;
	}

	public void setApsflUniqueId(String apsflUniqueId) {
		this.apsflUniqueId = apsflUniqueId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getBulkUpload() {
		return bulkUpload;
	}

	public void setBulkUpload(String bulkUpload) {
		this.bulkUpload = bulkUpload;
	}

	public String getNoOfCols() {
		return noOfCols;
	}

	public void setNoOfCols(String noOfCols) {
		this.noOfCols = noOfCols;
	}

	public String getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(String noOfRows) {
		this.noOfRows = noOfRows;
	}

	public String getPocDesignation() {
		return pocDesignation;
	}

	public void setPocDesignation(String pocDesignation) {
		this.pocDesignation = pocDesignation;
	}

	public String getCoreSrvcCode() {
		return coreSrvcCode;
	}

	public void setCoreSrvcCode(String coreSrvcCode) {
		this.coreSrvcCode = coreSrvcCode;
	}

	public String getL1Slot() {
		return l1Slot;
	}

	public void setL1Slot(String l1Slot) {
		this.l1Slot = l1Slot;
	}

	public String getL2Slot() {
		return l2Slot;
	}

	public void setL2Slot(String l2Slot) {
		this.l2Slot = l2Slot;
	}

	public String getL3Slot() {
		return l3Slot;
	}

	public void setL3Slot(String l3Slot) {
		this.l3Slot = l3Slot;
	}

	public String getEntCustType() {
		return entCustType;
	}

	public void setEntCustType(String entCustType) {
		this.entCustType = entCustType;
	}

	public String getEntCustomerCode() {
		return entCustomerCode;
	}

	public void setEntCustomerCode(String entCustomerCode) {
		this.entCustomerCode = entCustomerCode;
	}

	public String getOnuMacAddress() {
		return onuMacAddress;
	}

	public void setOnuMacAddress(String onuMacAddress) {
		this.onuMacAddress = onuMacAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstallmentCount() {
		return installmentCount;
	}

	public void setInstallmentCount(String installmentCount) {
		this.installmentCount = installmentCount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTelProdCode() {
		return telProdCode;
	}

	public void setTelProdCode(String telProdCode) {
		this.telProdCode = telProdCode;
	}

	public String getTelSrvcCode() {
		return telSrvcCode;
	}

	public void setTelSrvcCode(String telSrvcCode) {
		this.telSrvcCode = telSrvcCode;
	}

	public String getTelTenantCode() {
		return telTenantCode;
	}

	public void setTelTenantCode(String telTenantCode) {
		this.telTenantCode = telTenantCode;
	}

	public List<FeatuireVO> getFeatureCodeList() {
		return featureCodeList;
	}

	public void setFeatureCodeList(List<FeatuireVO> featureCodeList) {
		this.featureCodeList = featureCodeList;
	}

	public String getFeatureCodes() {
		return featureCodes;
	}

	public void setFeatureCodes(String featureCodes) {
		this.featureCodes = featureCodes;
	}

	public List<CafAndCpeChargesVO> getCafAndCpeChargesList() {
		return cafAndCpeChargesList;
	}

	public void setCafAndCpeChargesList(List<CafAndCpeChargesVO> cafAndCpeChargesList) {
		this.cafAndCpeChargesList = cafAndCpeChargesList;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getCableCharge() {
		return cableCharge;
	}

	public void setCableCharge(String cableCharge) {
		this.cableCharge = cableCharge;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getMobileNo1() {
		return mobileNo1;
	}

	public void setMobileNo1(String mobileNo1) {
		this.mobileNo1 = mobileNo1;
	}
	
	public String getOnuEmiPrice() {
		return onuEmiPrice;
	}

	public void setOnuEmiPrice(String onuEmiPrice) {
		this.onuEmiPrice = onuEmiPrice;
	}

	public String getCpePrice() {
		return cpePrice;
	}

	public void setCpePrice(String cpePrice) {
		this.cpePrice = cpePrice;
	}

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getPopId() {
		return popId;
	}

	public void setPopId(String popId) {
		this.popId = popId;
	}

	public String getPopDistrict() {
		return popDistrict;
	}

	public void setPopDistrict(String popDistrict) {
		this.popDistrict = popDistrict;
	}

	public String getPopMandal() {
		return popMandal;
	}

	public void setPopMandal(String popMandal) {
		this.popMandal = popMandal;
	}

	public String getOltId() {
		return oltId;
	}

	public void setOltId(String oltId) {
		this.oltId = oltId;
	}

	public String getOltPortId() {
		return oltPortId;
	}

	public void setOltPortId(String oltPortId) {
		this.oltPortId = oltPortId;
	}

	public String getCpeModal() {
		return cpeModal;
	}

	public void setCpeModal(String cpeModal) {
		this.cpeModal = cpeModal;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public List<ProductsVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductsVO> products) {
		this.products = products;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public String getAgruniqueid() {
		return agruniqueid;
	}

	public void setAgruniqueid(String agruniqueid) {
		this.agruniqueid = agruniqueid;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getLmoName() {
		return lmoName;
	}

	public void setLmoName(String lmoName) {
		this.lmoName = lmoName;
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

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getTitleLovName() {
		return titleLovName;
	}

	public void setTitleLovName(String titleLovName) {
		this.titleLovName = titleLovName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCpeId() {
		return cpeId;
	}

	public void setCpeId(String cpeId) {
		this.cpeId = cpeId;
	}

	public String getCpeLease() {
		return cpeLease;
	}

	public void setCpeLease(String cpeLease) {
		this.cpeLease = cpeLease;
	}

	public Float getInstCharge() {
		return instCharge;
	}

	public void setInstCharge(Float instCharge) {
		this.instCharge = instCharge;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandLineNo() {
		return landLineNo;
	}

	public void setLandLineNo(String landLineNo) {
		this.landLineNo = landLineNo;
	}
	
	public String getCustomerDec() {
		return customerDec;
	}

	public void setCustomerDec(String customerDec) {
		this.customerDec = customerDec;
	}

	public String getLmoDec() {
		return lmoDec;
	}

	public void setLmoDec(String lmoDec) {
		this.lmoDec = lmoDec;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Long getCafNo() {
		return cafNo;
	}

	public void setCafNo(Long cafNo) {
		this.cafNo = cafNo;
	}

	public MultipartFile getBulkUploadFile() {
		return bulkUploadFile;
	}

	public void setBulkUploadFile(MultipartFile bulkUploadFile) {
		this.bulkUploadFile = bulkUploadFile;
	}

	public String getCafStatus() {
		return cafStatus;
	}

	public void setCafStatus(String cafStatus) {
		this.cafStatus = cafStatus;
	}

	public List<Caf> getCafList() {
		return cafList;
	}

	public void setCafList(List<Caf> cafList) {
		this.cafList = cafList;
	}

}
