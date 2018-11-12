/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Lakshman
 *
 */
public class CafAndCpeChargesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String prodCode;
	
	private String prodName;
	
	private String srvcName;
	
	private String cafNo;
	
	private String cpeSlno;
	
	private String custCode;
	
	private String cpeModel;
	
	private String lockindays;
	
	private String fName;
	
	private String recurringCharge;
	
	private String recurringTax;
	
	private String custId;
	
	private String lName;
	
	private String custTypelov;
	
	private String activationCharge;
	
	private String activationTax;
	
	private String secDepositCharge;
	
	private String secDepositTax;
	
	private String prodtype;
	
	private String cafdate;
	
	private String oltSrlNo;
	
	private String oltPortNo;
	
	private String statusDesc;
	
	private String pinCode;
	
	private String srvcCode;
	
	private String billFrequency;
	
	private List<ServicesVO> services;
	
	private String macAddress;
	
	private String district;
	
	private String village;
	
	private String stbModel;
	
	private String mandal;
	
	private String tenantCode;
	
	private String popId;
	
	private String popName;
	
	private String cafStatus;
	
	private String agrmtId;
	
	private String apsflTrackId;
	
	private String insrAddress1;
	
	private String insrAddress2;
	
	private String contactPersonMobileNo;
	
	private String cpeplace;
	
	private String contactPerson;
	
	private String coreSrvcCode;
	
	private String iptvPackages;
	
	private String allocatedMobileNo;
	
	private String cafType;
	
	private String totalDisplayCount;
	
	private String tenantType;
	
	private String prodCafNo;
	
	private String cpeMacAddr;
	
	private String oltMacAddr;
	
	private String contactMob;
	
	private BigDecimal monthlyCharges;
	
		private String oltPortIdNo;
	
	private BigDecimal payment;

	
	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public BigDecimal getArrears() {
		return arrears;
	}

	public void setArrears(BigDecimal arrears) {
		this.arrears = arrears;
	}

	private BigDecimal arrears;
	
	public BigDecimal getMonthlyCharges() {
		return monthlyCharges;
	}

	public void setMonthlyCharges(BigDecimal monthlyCharges) {
		this.monthlyCharges = monthlyCharges;
	}

	public BigDecimal getOnetimeCharges() {
		return onetimeCharges;
	}

	public void setOnetimeCharges(BigDecimal onetimeCharges) {
		this.onetimeCharges = onetimeCharges;
	}

	public BigDecimal getTelePhoneCharges() {
		return TelePhoneCharges;
	}

	public void setTelePhoneCharges(BigDecimal telePhoneCharges) {
		TelePhoneCharges = telePhoneCharges;
	}

	public BigDecimal getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(BigDecimal totalPayable) {
		this.totalPayable = totalPayable;
	}

	private BigDecimal onetimeCharges;

	private BigDecimal TelePhoneCharges;

	private BigDecimal serviceTax;

	private BigDecimal total;

	private BigDecimal totalPayable;


	public String getContactMob() {
		return contactMob;
	}

	public void setContactMob(String contactMob) {
		this.contactMob = contactMob;
	}

	private Map<String,String> stbSrlNoMacAddrMap;
	
	public Map<String, String> getStbSrlNoMacAddrMap() {
		return stbSrlNoMacAddrMap;
	}

	public void setStbSrlNoMacAddrMap(Map<String, String> stbSrlNoMacAddrMap) {
		this.stbSrlNoMacAddrMap = stbSrlNoMacAddrMap;
	}
	
	public String getOltMacAddr() {
		return oltMacAddr;
	}

	public void setOltMacAddr(String oltMacAddr) {
		this.oltMacAddr = oltMacAddr;
	}
	
	public String getCpeMacAddr() {
		return cpeMacAddr;
	}

	public void setCpeMacAddr(String cpeMacAddr) {
		this.cpeMacAddr = cpeMacAddr;
	}

	public String getProdCafNo() {
		return prodCafNo;
	}

	public void setProdCafNo(String prodCafNo) {
		this.prodCafNo = prodCafNo;
	}

	
	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}
	
	public String getTotalDisplayCount() {
		return totalDisplayCount;
	}

	public void setTotalDisplayCount(String totalDisplayCount) {
		this.totalDisplayCount = totalDisplayCount;
	}
	
	public String getCafType() {
		return cafType;
	}

	public void setCafType(String cafType) {
		this.cafType = cafType;
	}
	
	public String getAllocatedMobileNo() {
		return allocatedMobileNo;
	}

	public void setAllocatedMobileNo(String allocatedMobileNo) {
		this.allocatedMobileNo = allocatedMobileNo;
	}
	
	public String getCoreSrvcCode() {
		return coreSrvcCode;
	}

	public void setCoreSrvcCode(String coreSrvcCode) {
		this.coreSrvcCode = coreSrvcCode;
	}

	public String getIptvPackages() {
		return iptvPackages;
	}

	public void setIptvPackages(String iptvPackages) {
		this.iptvPackages = iptvPackages;
	}

	
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	
	public String getCpeplace() {
		return cpeplace;
	}

	public void setCpeplace(String cpeplace) {
		this.cpeplace = cpeplace;
	}
	
	public String getContactPersonMobileNo() {
		return contactPersonMobileNo;
	}

	public void setContactPersonMobileNo(String contactPersonMobileNo) {
		this.contactPersonMobileNo = contactPersonMobileNo;
	}
	
	public String getInsrAddress1() {
		return insrAddress1;
	}

	public void setInsrAddress1(String insrAddress1) {
		this.insrAddress1 = insrAddress1;
	}

	public String getInsrAddress2() {
		return insrAddress2;
	}

	public void setInsrAddress2(String insrAddress2) {
		this.insrAddress2 = insrAddress2;
	}
	
	public String getApsflTrackId() {
		return apsflTrackId;
	}

	public void setApsflTrackId(String apsflTrackId) {
		this.apsflTrackId = apsflTrackId;
	}
	public String getAgrmtId() {
		return agrmtId;
	}

	public void setAgrmtId(String agrmtId) {
		this.agrmtId = agrmtId;
	}
	
	public String getCafStatus() {
		return cafStatus;
	}

	public void setCafStatus(String cafStatus) {
		this.cafStatus = cafStatus;
	}
	
	public String getPopName() {
		return popName;
	}

	public void setPopName(String popName) {
		this.popName = popName;
	}
	
	public String getPopId() {
		return popId;
	}

	public void setPopId(String popId) {
		this.popId = popId;
	}
	
	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	
	public String getStbModel() {
		return stbModel;
	}

	public void setStbModel(String stbModel) {
		this.stbModel = stbModel;
	}
	
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	
	public List<ServicesVO> getServices() {
		return services;
	}

	public void setServices(List<ServicesVO> services) {
		this.services = services;
	}
	
	public String getBillFrequency() {
		return billFrequency;
	}

	public void setBillFrequency(String billFrequency) {
		this.billFrequency = billFrequency;
	}
	public String getSrvcCode() {
		return srvcCode;
	}

	public void setSrvcCode(String srvcCode) {
		this.srvcCode = srvcCode;
	}

	
	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	
	public String getOltSrlNo() {
		return oltSrlNo;
	}

	public void setOltSrlNo(String oltSrlNo) {
		this.oltSrlNo = oltSrlNo;
	}

	public String getOltPortNo() {
		return oltPortNo;
	}

	public void setOltPortNo(String oltPortNo) {
		this.oltPortNo = oltPortNo;
	}
	
	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getSrvcName() {
		return srvcName;
	}

	public void setSrvcName(String srvcName) {
		this.srvcName = srvcName;
	}

	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	public String getCpeSlno() {
		return cpeSlno;
	}

	public void setCpeSlno(String cpeSlno) {
		this.cpeSlno = cpeSlno;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCpeModel() {
		return cpeModel;
	}

	public void setCpeModel(String cpeModel) {
		this.cpeModel = cpeModel;
	}

	public String getLockindays() {
		return lockindays;
	}

	public void setLockindays(String lockindays) {
		this.lockindays = lockindays;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getRecurringCharge() {
		return recurringCharge;
	}

	public void setRecurringCharge(String recurringCharge) {
		this.recurringCharge = recurringCharge;
	}

	public String getRecurringTax() {
		return recurringTax;
	}

	public void setRecurringTax(String recurringTax) {
		this.recurringTax = recurringTax;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getCustTypelov() {
		return custTypelov;
	}

	public void setCustTypelov(String custTypelov) {
		this.custTypelov = custTypelov;
	}

	public String getActivationCharge() {
		return activationCharge;
	}

	public void setActivationCharge(String activationCharge) {
		this.activationCharge = activationCharge;
	}

	public String getActivationTax() {
		return activationTax;
	}

	public void setActivationTax(String activationTax) {
		this.activationTax = activationTax;
	}

	public String getSecDepositCharge() {
		return secDepositCharge;
	}

	public void setSecDepositCharge(String secDepositCharge) {
		this.secDepositCharge = secDepositCharge;
	}

	public String getSecDepositTax() {
		return secDepositTax;
	}

	public void setSecDepositTax(String secDepositTax) {
		this.secDepositTax = secDepositTax;
	}

	public String getProdtype() {
		return prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public String getCafdate() {
		return cafdate;
	}

	public void setCafdate(String cafdate) {
		this.cafdate = cafdate;
	}

	public String getOltPortIdNo() {
		return oltPortIdNo;
	}

	public void setOltPortIdNo(String oltPortIdNo) {
		this.oltPortIdNo = oltPortIdNo;
	}
}
