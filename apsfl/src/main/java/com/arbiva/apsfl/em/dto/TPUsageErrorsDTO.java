package com.arbiva.apsfl.em.dto;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 
 * @author gowthami
 *
 */

public class TPUsageErrorsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger recid;

	private BigInteger fileSeqNo;

	private String srcSrvcSubtype;

	private String roleOfNode;

	private String callingPartyAddress;

	private String calledPartyAddress;

	private String starttime;

	private String endtime;

	private BigInteger localRecordSeqNo;

	private String srcSpecType;

	private int callDuration;

	private String serviceCategory;

	private String errorcode;

	private String serviceRequestTime;

	private String orgIOI;

	private String termIOI;

	private BigInteger recordSequenceNo;

	private int recCloseReason;

	private String subIDData;

	private String reqPartyAddress;

	private String srvcSpecData;

	private String callDesc;

	private String serviceID;

	private String valueAddedServiceID;

	private String supplementryServiceList;

	private String otherFields;

	private String createdon;

	private String processedon;

	private String status;

	public BigInteger getRecid() {
		return recid;
	}

	public void setRecid(BigInteger recid) {
		this.recid = recid;
	}

	public BigInteger getFileSeqNo() {
		return fileSeqNo;
	}

	public void setFileSeqNo(BigInteger fileSeqNo) {
		this.fileSeqNo = fileSeqNo;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSrcSrvcSubtype() {
		return srcSrvcSubtype;
	}

	public void setSrcSrvcSubtype(String srcSrvcSubtype) {
		this.srcSrvcSubtype = srcSrvcSubtype;
	}

	public String getRoleOfNode() {
		return roleOfNode;
	}

	public void setRoleOfNode(String roleOfNode) {
		this.roleOfNode = roleOfNode;
	}

	public String getCallingPartyAddress() {
		return callingPartyAddress;
	}

	public void setCallingPartyAddress(String callingPartyAddress) {
		this.callingPartyAddress = callingPartyAddress;
	}

	public String getCalledPartyAddress() {
		return calledPartyAddress;
	}

	public void setCalledPartyAddress(String calledPartyAddress) {
		this.calledPartyAddress = calledPartyAddress;
	}

	public String getSrcSpecType() {
		return srcSpecType;
	}

	public void setSrcSpecType(String srcSpecType) {
		this.srcSpecType = srcSpecType;
	}

	public int getCallDuration() {
		return callDuration;
	}

	public void setCallDuration(int callDuration) {
		this.callDuration = callDuration;
	}

	public String getServiceRequestTime() {
		return serviceRequestTime;
	}

	public void setServiceRequestTime(String serviceRequestTime) {
		this.serviceRequestTime = serviceRequestTime;
	}

	public String getOrgIOI() {
		return orgIOI;
	}

	public void setOrgIOI(String orgIOI) {
		this.orgIOI = orgIOI;
	}

	public String getTermIOI() {
		return termIOI;
	}

	public void setTermIOI(String termIOI) {
		this.termIOI = termIOI;
	}

	public String getSubIDData() {
		return subIDData;
	}

	public void setSubIDData(String subIDData) {
		this.subIDData = subIDData;
	}

	public String getReqPartyAddress() {
		return reqPartyAddress;
	}

	public void setReqPartyAddress(String reqPartyAddress) {
		this.reqPartyAddress = reqPartyAddress;
	}

	public String getSrvcSpecData() {
		return srvcSpecData;
	}

	public void setSrvcSpecData(String srvcSpecData) {
		this.srvcSpecData = srvcSpecData;
	}

	public String getCallDesc() {
		return callDesc;
	}

	public void setCallDesc(String callDesc) {
		this.callDesc = callDesc;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getValueAddedServiceID() {
		return valueAddedServiceID;
	}

	public void setValueAddedServiceID(String valueAddedServiceID) {
		this.valueAddedServiceID = valueAddedServiceID;
	}

	public String getSupplementryServiceList() {
		return supplementryServiceList;
	}

	public void setSupplementryServiceList(String supplementryServiceList) {
		this.supplementryServiceList = supplementryServiceList;
	}

	public String getOtherFields() {
		return otherFields;
	}

	public void setOtherFields(String otherFields) {
		this.otherFields = otherFields;
	}

	public BigInteger getLocalRecordSeqNo() {
		return localRecordSeqNo;
	}

	public void setLocalRecordSeqNo(BigInteger localRecordSeqNo) {
		this.localRecordSeqNo = localRecordSeqNo;
	}

	public String getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public BigInteger getRecordSequenceNo() {
		return recordSequenceNo;
	}

	public void setRecordSequenceNo(BigInteger recordSequenceNo) {
		this.recordSequenceNo = recordSequenceNo;
	}

	public int getRecCloseReason() {
		return recCloseReason;
	}

	public void setRecCloseReason(int recCloseReason) {
		this.recCloseReason = recCloseReason;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getProcessedon() {
		return processedon;
	}

	public void setProcessedon(String processedon) {
		this.processedon = processedon;
	}

}
