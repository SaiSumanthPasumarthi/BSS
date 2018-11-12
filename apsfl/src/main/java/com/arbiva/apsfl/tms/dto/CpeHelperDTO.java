package com.arbiva.apsfl.tms.dto;

import java.util.List;

import com.arbiva.apsfl.tms.model.CpeStock;

public class CpeHelperDTO {

	private String profileId;
	
	private String loginId;

	private String cpeTypeLov;

	private String cpeModel;

	private String cpeProfileName;

	private String custCost;

	private String custRent;

	private String purchaseCost;

	private String instalationCost;

	private String totalCost;

	private String quantity;

	private String tenantName;

	private String tenantCode;

	private String orderedDate;

	private String delvQuantity;

	private String blockedQuantity;

	private String demandId;

	private List<CpeDeliveryDTO> cpeDeliveryList;

	private String cpeAvailability;

	List<String> cpeSlnoList;

	private String paymentMode;

	private String dlvId;

	private String gateWayType;

	private String tenantType;
	
	private String tenantId;
	
	private String paymentType;
	
	private String emailId;
	
	private String mobileNo;
	
	private String status;

	private List<CpeStock> cpeList;
	
	private String cafNo;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public List<CpeStock> getCpeList() {
		return cpeList;
	}

	public void setCpeList(List<CpeStock> cpeList) {
		this.cpeList = cpeList;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getGateWayType() {
		return gateWayType;
	}

	public void setGateWayType(String gateWayType) {
		this.gateWayType = gateWayType;
	}

	public String getBlockedQuantity() {
		return blockedQuantity;
	}

	public void setBlockedQuantity(String blockedQuantity) {
		this.blockedQuantity = blockedQuantity;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDlvId() {
		return dlvId;
	}

	public void setDlvId(String dlvId) {
		this.dlvId = dlvId;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public List<String> getCpeSlnoList() {
		return cpeSlnoList;
	}

	public void setCpeSlnoList(List<String> cpeSlnoList) {
		this.cpeSlnoList = cpeSlnoList;
	}

	public List<CpeDeliveryDTO> getCpeDeliveryList() {
		return cpeDeliveryList;
	}

	public void setCpeDeliveryList(List<CpeDeliveryDTO> cpeDeliveryList) {
		this.cpeDeliveryList = cpeDeliveryList;
	}

	public String getCpeAvailability() {
		return cpeAvailability;
	}

	public void setCpeAvailability(String cpeAvailability) {
		this.cpeAvailability = cpeAvailability;
	}

	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}

	public String getDelvQuantity() {
		return delvQuantity;
	}

	public void setDelvQuantity(String delvQuantity) {
		this.delvQuantity = delvQuantity;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCustCost() {
		return custCost;
	}

	public void setCustCost(String custCost) {
		this.custCost = custCost;
	}

	public String getCustRent() {
		return custRent;
	}

	public void setCustRent(String custRent) {
		this.custRent = custRent;
	}

	public String getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(String purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public String getInstalationCost() {
		return instalationCost;
	}

	public void setInstalationCost(String instalationCost) {
		this.instalationCost = instalationCost;
	}

	public String getCpeModel() {
		return cpeModel;
	}

	public void setCpeModel(String cpeModel) {
		this.cpeModel = cpeModel;
	}

	public String getCpeProfileName() {
		return cpeProfileName;
	}

	public void setCpeProfileName(String cpeProfileName) {
		this.cpeProfileName = cpeProfileName;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getCpeTypeLov() {
		return cpeTypeLov;
	}

	public void setCpeTypeLov(String cpeTypeLov) {
		this.cpeTypeLov = cpeTypeLov;
	}

	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	
}
