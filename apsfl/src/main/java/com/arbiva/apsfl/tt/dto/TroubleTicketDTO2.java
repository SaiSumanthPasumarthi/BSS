package com.arbiva.apsfl.tt.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.coms.dto.PaymentDetailsVO;

/**
 * 
 * @author gowthami
 *
 */

public class TroubleTicketDTO2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger ticketNo;
	private String childTicketNos;
	private String tenantMobile;
	private BigInteger parentticketno;
	private String oltStatusFlag;
	private String ipAddr;
	private String custID;
	private List<String> imagesList;
	private String custName;

	private BigInteger cafNo;

	private String contactAddr;

	private String contactLandline;

	private String contactMobile;

	private String contactemail;

	private String tenantcode;

	private String ticketFor;

	private String ticketType;

	private Integer issueCode;

	private String ticketDesc;

	private String ticketRemark;

	private String assignedTo;

	private Timestamp expcloseDate;
	private Timestamp ttHisModifiedOn;

	private Byte status;

	private Timestamp createdOn;

	private String createdBy;

	private Timestamp modifiedOn;

	private String modifiedBy;

	private String ticketPriority;

	private String message;

	private String issueType;

	private String domain;

	private String ticketForHidden;

	private String flag;

	private String dynAttFlag;

	private String currentStatus;
	
	private String districts;
	private String mandalID;
	private String villageID;
	private String apsflID;
	private String pops;
	private String olts;
	private String ports;
	private String splitL1;
	private String splitL2;
	private String dist;
	private String tickCategory;
	private String subStationName;
	private String oltSerialNo;
	private String port;
	private String oltONUID;
	private String onuRegNo;
	private String districtName;
	private String mandalName;
	private String villageName;
	private PageObject pageObject;
	private String tenantType;
	private String tenantCode;
	private String ttNumber;
	private String ttType;
	private String status1;
	private String userDomain;	
	private String loginID;	
	private String cafNO;
	private String count;
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	public String getCafNO() {
		return cafNO;
	}

	public void setCafNO(String cafNO) {
		this.cafNO = cafNO;
	}

	public String getUserDomain() {
		return userDomain;
	}

	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getTtType() {
		return ttType;
	}

	public void setTtType(String ttType) {
		this.ttType = ttType;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getTtNumber() {
		return ttNumber;
	}

	public void setTtNumber(String ttNumber) {
		this.ttNumber = ttNumber;
	}

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	private HashMap<String, String> attValues = new HashMap<String, String>();

	private String fromDate;

	private String toDate;

	private String issue;

	private String assignedToName;

	private String reportingManagerEmailID;

	private String assignToEmailID;

	private String actionFlag;

	private String dummyVar;

	private String createdOnDate;

	private String expectedClosureDate;
	
	private String timeDiff;
	
	private String ticketReason;
	
	private String modifiedOnString;
	
	private String groupTTMsg;
	private BigInteger customerID;
	private String aadharNo;
	
	private String userName;
	
	private String actualIssue;
	private List<MultipartFile> images;
	private String imagePath;
	
	private String oldIssue;
	
	private String apsflTrackID;
	private String onuModel;
	private String onuSerialNo;
	private String onuMACAddr;
	List<PaymentDetailsVO> cafDetailsVO;
	List<TTTIptvDTO> iptDTO;
	
    private String agoraSerCode;
    
    private String cpeProfileID;
	
    List<String> baseFormatimages;
    
    private String popOltIpaddress;
    private String oltCardNo;
    private String oltPort;
    
    private String feedback;
    
    public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getPopOltIpaddress() {
		return popOltIpaddress;
	}

	public void setPopOltIpaddress(String popOltIpaddress) {
		this.popOltIpaddress = popOltIpaddress;
	}

	public String getOltCardNo() {
		return oltCardNo;
	}

	public void setOltCardNo(String oltCardNo) {
		this.oltCardNo = oltCardNo;
	}

	public String getOltPort() {
		return oltPort;
	}

	public void setOltPort(String oltPort) {
		this.oltPort = oltPort;
	}

	
	
   	public List<String> getBaseFormatimages() {
   		return baseFormatimages;
   	}

   	public void setBaseFormatimages(List<String> baseFormatimages) {
   		this.baseFormatimages = baseFormatimages;
   	}
	public String getCpeProfileID() {
		return cpeProfileID;
	}

	public void setCpeProfileID(String cpeProfileID) {
		this.cpeProfileID = cpeProfileID;
	}

	public String getAgoraSerCode() {
		return agoraSerCode;
	}

	public void setAgoraSerCode(String agoraSerCode) {
		this.agoraSerCode = agoraSerCode;
	}
	public String getImagePath() {
	return imagePath;
	}

	public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
	} 
	public List<MultipartFile> getImages() {
	return images;
	}

	public void setImages(List<MultipartFile> images) {
	this.images = images;
	}
	public TroubleTicketDTO2() {
	}

	public BigInteger getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(BigInteger ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public BigInteger getCafNo() {
		return cafNo;
	}

	public void setCafNo(BigInteger cafNo) {
		this.cafNo = cafNo;
	}

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public String getContactLandline() {
		return contactLandline;
	}

	public void setContactLandline(String contactLandline) {
		this.contactLandline = contactLandline;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactemail() {
		return contactemail;
	}

	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}

	public String getTicketFor() {
		return ticketFor;
	}

	public void setTicketFor(String ticketFor) {
		this.ticketFor = ticketFor;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public Integer getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(Integer issueCode) {
		this.issueCode = issueCode;
	}

	public String getTicketDesc() {
		return ticketDesc;
	}

	public void setTicketDesc(String ticketDesc) {
		this.ticketDesc = ticketDesc;
	}

	public String getTicketRemark() {
		return ticketRemark;
	}

	public void setTicketRemark(String ticketRemark) {
		this.ticketRemark = ticketRemark;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getTicketPriority() {
		return ticketPriority;
	}

	public void setTicketPriority(String ticketPriority) {
		this.ticketPriority = ticketPriority;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getTicketForHidden() {
		return ticketForHidden;
	}

	public void setTicketForHidden(String ticketForHidden) {
		this.ticketForHidden = ticketForHidden;
	}

	public Timestamp getExpcloseDate() {
		return expcloseDate;
	}

	public void setExpcloseDate(Timestamp expcloseDate) {
		this.expcloseDate = expcloseDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTenantcode() {
		return tenantcode;
	}

	public void setTenantcode(String tenantcode) {
		this.tenantcode = tenantcode;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public HashMap<String, String> getAttValues() {
		return attValues;
	}

	public void setAttValues(HashMap<String, String> attValues) {
		this.attValues = attValues;
	}

	public String getDynAttFlag() {
		return dynAttFlag;
	}

	public void setDynAttFlag(String dynAttFlag) {
		this.dynAttFlag = dynAttFlag;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getAssignedToName() {
		return assignedToName;
	}

	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}

	public String getReportingManagerEmailID() {
		return reportingManagerEmailID;
	}

	public void setReportingManagerEmailID(String reportingManagerEmailID) {
		this.reportingManagerEmailID = reportingManagerEmailID;
	}

	public String getAssignToEmailID() {
		return assignToEmailID;
	}

	public void setAssignToEmailID(String assignToEmailID) {
		this.assignToEmailID = assignToEmailID;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public String getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(String createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public String getExpectedClosureDate() {
		return expectedClosureDate;
	}

	public void setExpectedClosureDate(String expectedClosureDate) {
		this.expectedClosureDate = expectedClosureDate;
	}

	public String getDummyVar() {
		return dummyVar;
	}

	public void setDummyVar(String dummyVar) {
		this.dummyVar = dummyVar;
	}

	

	public String getTicketReason() {
		return ticketReason;
	}

	public void setTicketReason(String ticketReason) {
		this.ticketReason = ticketReason;
	}

	
	public String getModifiedOnString() {
		return modifiedOnString;
	}

	public void setModifiedOnString(String modifiedOnString) {
		this.modifiedOnString = modifiedOnString;
	}

	public String getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(String timeDiff) {
		this.timeDiff = timeDiff;
	}

	public BigInteger getParentticketno() {
		return parentticketno;
	}

	public void setParentticketno(BigInteger parentticketno) {
		this.parentticketno = parentticketno;
	}

	public Timestamp getTtHisModifiedOn() {
		return ttHisModifiedOn;
	}

	public void setTtHisModifiedOn(Timestamp ttHisModifiedOn) {
		this.ttHisModifiedOn = ttHisModifiedOn;
	}

	public String getChildTicketNos() {
		return childTicketNos;
	}

	public void setChildTicketNos(String childTicketNos) {
		this.childTicketNos = childTicketNos;
	}

	public String getDistricts() {
		return districts;
	}

	public void setDistricts(String districts) {
		this.districts = districts;
	}

	public String getPops() {
		return pops;
	}

	public void setPops(String pops) {
		this.pops = pops;
	}

	public String getOlts() {
		return olts;
	}

	public void setOlts(String olts) {
		this.olts = olts;
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public String getSplitL1() {
		return splitL1;
	}

	public void setSplitL1(String splitL1) {
		this.splitL1 = splitL1;
	}

	public String getSplitL2() {
		return splitL2;
	}

	public void setSplitL2(String splitL2) {
		this.splitL2 = splitL2;
	}

	public String getGroupTTMsg() {
		return groupTTMsg;
	}

	public void setGroupTTMsg(String groupTTMsg) {
		this.groupTTMsg = groupTTMsg;
	}

	public BigInteger getCustomerID() {
		return customerID;
	}

	public void setCustomerID(BigInteger customerID) {
		this.customerID = customerID;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getActualIssue() {
		return actualIssue;
	}

	public void setActualIssue(String actualIssue) {
		this.actualIssue = actualIssue;
	}

	public List<String> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getTickCategory() {
		return tickCategory;
	}

	public void setTickCategory(String tickCategory) {
		this.tickCategory = tickCategory;
	}

	public String getOldIssue() {
		return oldIssue;
	}

	public void setOldIssue(String oldIssue) {
		this.oldIssue = oldIssue;
	}

	public String getMandalID() {
		return mandalID;
	}

	public void setMandalID(String mandalID) {
		this.mandalID = mandalID;
	}

	public String getVillageID() {
		return villageID;
	}

	public void setVillageID(String villageID) {
		this.villageID = villageID;
	}

	public String getApsflID() {
		return apsflID;
	}

	public void setApsflID(String apsflID) {
		this.apsflID = apsflID;
	}

	public String getSubStationName() {
		return subStationName;
	}

	public void setSubStationName(String subStationName) {
		this.subStationName = subStationName;
	}

	public String getOltSerialNo() {
		return oltSerialNo;
	}

	public void setOltSerialNo(String oltSerialNo) {
		this.oltSerialNo = oltSerialNo;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getOltONUID() {
		return oltONUID;
	}

	public void setOltONUID(String oltONUID) {
		this.oltONUID = oltONUID;
	}

	public String getOnuRegNo() {
		return onuRegNo;
	}

	public void setOnuRegNo(String onuRegNo) {
		this.onuRegNo = onuRegNo;
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

	public List<PaymentDetailsVO> getCafDetailsVO() {
		return cafDetailsVO;
	}

	public void setCafDetailsVO(List<PaymentDetailsVO> cafDetailsVO) {
		this.cafDetailsVO = cafDetailsVO;
	}

	public List<TTTIptvDTO> getIptDTO() {
		return iptDTO;
	}

	public void setIptDTO(List<TTTIptvDTO> iptDTO) {
		this.iptDTO = iptDTO;
	}

	public String getOltStatusFlag() {
		return oltStatusFlag;
	}

	public void setOltStatusFlag(String oltStatusFlag) {
		this.oltStatusFlag = oltStatusFlag;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

}
