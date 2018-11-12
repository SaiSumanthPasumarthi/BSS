package com.arbiva.apsfl.dto;



import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * 
 * @author gowthami
 *
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, isGetterVisibility = Visibility.NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UsersDTO {
	
	

	@JsonProperty("userID")
	private int userID;
	
	@JsonProperty("loginID")
	private String loginID;
	
	@JsonProperty("tenantName")
	private String tenantName;
	
	@JsonProperty("tenantType")
	private String tenantType;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("tenantCode")
	private String tenantCode;
	
	@JsonProperty("roleName")
	private String roleName;
	
	@JsonProperty("pwdResetFlag")
	private Byte pwdResetFlag;
	
	@JsonProperty("mobile1")
	private String mobile1;
	
	@JsonProperty("domain")
	private String domain;
	
	@JsonProperty("mobile2")
	private String mobile2;
	
	@JsonProperty("emailID1")
	private String emailID1;
	
	@JsonProperty("emailID2")
	private String emailID2;
	
	@JsonProperty("rmUserID")
	private String rmUserID;
	
	@JsonProperty("status")
	private Byte status;
	
	@JsonProperty("statusHiddenID")
	private Byte statusHiddenID;
	
	@JsonProperty("createdOn")
	private Date createdOn;
	
	@JsonProperty("createdBy")
	private String createdBy;
	
	@JsonProperty("modifiedOn")
	private Date modifiedOn;
	
	@JsonProperty("modifiedBy")
	private String modifiedBy;
	
	@JsonProperty("modifiedIPAddr")
	private String modifiedIPAddr;
	
	@JsonProperty("pwd")
	private byte[] pwd;
	
	@JsonProperty("pwdInString")
	private String pwdInString;
	
	@JsonProperty("pwdHiddenID")
	private String pwdHiddenID;

	@JsonProperty("usernameHiddenID")
	private String usernameHiddenID;
	
	@JsonProperty("newPwd")
	private String newPwd;
	
	@JsonProperty("oldPwd")
	private String oldPwd;
	
	@JsonProperty("cnfPwd")
	private String cnfPwd;
	
	@JsonProperty("logginIDHidden")
	private String logginIDHidden;

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Byte getPwdResetFlag() {
		return pwdResetFlag;
	}

	public void setPwdResetFlag(Byte pwdResetFlag) {
		this.pwdResetFlag = pwdResetFlag;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getEmailID1() {
		return emailID1;
	}

	public void setEmailID1(String emailID1) {
		this.emailID1 = emailID1;
	}

	public String getEmailID2() {
		return emailID2;
	}

	public void setEmailID2(String emailID2) {
		this.emailID2 = emailID2;
	}

	public String getRmUserID() {
		return rmUserID;
	}

	public void setRmUserID(String rmUserID) {
		this.rmUserID = rmUserID;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIPAddr() {
		return modifiedIPAddr;
	}

	public void setModifiedIPAddr(String modifiedIPAddr) {
		this.modifiedIPAddr = modifiedIPAddr;
	}

	public byte[] getPwd() {
		return pwd;
	}

	public void setPwd(byte[] pwd) {
		this.pwd = pwd;
	}

	public String getPwdInString() {
		return pwdInString;
	}

	public void setPwdInString(String pwdInString) {
		this.pwdInString = pwdInString;
	}

	public String getPwdHiddenID() {
		return pwdHiddenID;
	}

	public void setPwdHiddenID(String pwdHiddenID) {
		this.pwdHiddenID = pwdHiddenID;
	}

	public String getUsernameHiddenID() {
		return usernameHiddenID;
	}

	public void setUsernameHiddenID(String usernameHiddenID) {
		this.usernameHiddenID = usernameHiddenID;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getCnfPwd() {
		return cnfPwd;
	}

	public void setCnfPwd(String cnfPwd) {
		this.cnfPwd = cnfPwd;
	}

	public String getLogginIDHidden() {
		return logginIDHidden;
	}

	public void setLogginIDHidden(String logginIDHidden) {
		this.logginIDHidden = logginIDHidden;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsersDTO [userID=" + userID + ", loginID=" + loginID + ", tenantName=" + tenantName + ", userName="
				+ userName + ", tenantCode=" + tenantCode + ", roleName=" + roleName + ", pwdResetFlag=" + pwdResetFlag
				+ ", mobile1=" + mobile1 + ", domain=" + domain + ", mobile2=" + mobile2 + ", emailID1=" + emailID1
				+ ", emailID2=" + emailID2 + ", rmUserID=" + rmUserID + ", status=" + status + ", createdOn="
				+ createdOn + ", createdBy=" + createdBy + ", modifiedOn=" + modifiedOn + ", modifiedBy=" + modifiedBy
				+ ", modifiedIPAddr=" + modifiedIPAddr + ", pwd=" + Arrays.toString(pwd) + ", pwdInString="
				+ pwdInString + ", pwdHiddenID=" + pwdHiddenID + ", usernameHiddenID=" + usernameHiddenID + ", newPwd="
				+ newPwd + ", oldPwd=" + oldPwd + ", cnfPwd=" + cnfPwd + ", logginIDHidden=" + logginIDHidden + "]";
	}

	public Byte getStatusHiddenID() {
		return statusHiddenID;
	}

	public void setStatusHiddenID(Byte statusHiddenID) {
		this.statusHiddenID = statusHiddenID;
	}
	
	
		
}
