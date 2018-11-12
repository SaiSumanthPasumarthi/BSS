package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Srinivas V
 * @since Feb 08 2017
 */
public class ChangeStbDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String parentCafno;
	
	private String stbCafNo;
	
	private String custId;
	
	private String nwSubCode;
	
	private String oldStbMacAddr;
	
	private String newStbMacAddr;
	
	private String changePurpose;
	
	private String oldStbSerialNum;
	
	private String newStbSerialNum;
	
	private String oldStbProfileId;
	
	private String changeComment;
	
	
		 
	public String getChangeComment() {
		return changeComment;
	}

	public void setChangeComment(String changeComment) {
		this.changeComment = changeComment;
	}

	public String getOldStbProfileId() {
		return oldStbProfileId;
	}

	public void setOldStbProfileId(String oldStbProfileId) {
		this.oldStbProfileId = oldStbProfileId;
	}

	public String getParentCafno() {
		return parentCafno;
	}

	public void setParentCafno(String parentCafno) {
		this.parentCafno = parentCafno;
	}

	public String getStbCafNo() {
		return stbCafNo;
	}

	public void setStbCafNo(String stbCafNo) {
		this.stbCafNo = stbCafNo;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getNwSubCode() {
		return nwSubCode;
	}

	public void setNwSubCode(String nwSubCode) {
		this.nwSubCode = nwSubCode;
	}

	public String getOldStbMacAddr() {
		return oldStbMacAddr;
	}

	public void setOldStbMacAddr(String oldStbMacAddr) {
		this.oldStbMacAddr = oldStbMacAddr;
	}

	public String getNewStbMacAddr() {
		return newStbMacAddr;
	}

	public void setNewStbMacAddr(String newStbMacAddr) {
		this.newStbMacAddr = newStbMacAddr;
	}

	public String getChangePurpose() {
		return changePurpose;
	}

	public void setChangePurpose(String changePurpose) {
		this.changePurpose = changePurpose;
	}

	public String getOldStbSerialNum() {
		return oldStbSerialNum;
	}

	public void setOldStbSerialNum(String oldStbSerialNum) {
		this.oldStbSerialNum = oldStbSerialNum;
	}

	public String getNewStbSerialNum() {
		return newStbSerialNum;
	}

	public void setNewStbSerialNum(String newStbSerialNum) {
		this.newStbSerialNum = newStbSerialNum;
	}

}
