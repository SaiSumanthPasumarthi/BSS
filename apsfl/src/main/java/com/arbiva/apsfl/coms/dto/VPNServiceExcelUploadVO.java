package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class VPNServiceExcelUploadVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String substnUid;
	
	private String oltSerialNo;
	
	private String vpnSrvcName;
	
	private String tenantType;
	
	private String loginId;
	
	private String fileName;
	
	private String fileSize;
	
	private String noofRows;
	
	private String noofCols;
	
	private String tenantCode;
	
	@JsonIgnore
	private MultipartFile vpnUploadFile;
	
	private List<VPNSrvcVO> vpnSrvcList;

	public String getSubstnUid() {
		return substnUid;
	}

	public void setSubstnUid(String substnUid) {
		this.substnUid = substnUid;
	}

	public String getOltSerialNo() {
		return oltSerialNo;
	}

	public void setOltSerialNo(String oltSerialNo) {
		this.oltSerialNo = oltSerialNo;
	}

	public String getVpnSrvcName() {
		return vpnSrvcName;
	}

	public void setVpnSrvcName(String vpnSrvcName) {
		this.vpnSrvcName = vpnSrvcName;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public String getNoofRows() {
		return noofRows;
	}

	public void setNoofRows(String noofRows) {
		this.noofRows = noofRows;
	}

	public String getNoofCols() {
		return noofCols;
	}

	public void setNoofCols(String noofCols) {
		this.noofCols = noofCols;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public MultipartFile getVpnUploadFile() {
		return vpnUploadFile;
	}

	public void setVpnUploadFile(MultipartFile vpnUploadFile) {
		this.vpnUploadFile = vpnUploadFile;
	}

	public List<VPNSrvcVO> getVpnSrvcList() {
		return vpnSrvcList;
	}

	public void setVpnSrvcList(List<VPNSrvcVO> vpnSrvcList) {
		this.vpnSrvcList = vpnSrvcList;
	}
	
}
