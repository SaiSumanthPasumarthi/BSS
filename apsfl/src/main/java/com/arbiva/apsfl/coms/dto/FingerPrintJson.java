/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class FingerPrintJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fpPosition;
	
	private String fpFontType;
	
	private String fpFontSize;
	
	private String fpFontColor;
	
	private String fpDuration;
	
	private String fpfingerPrintType;
	
	private String fpChannel;
	
	private String fpBgColor;
	
	private String fpMessage;
	
	private String fpUserCanCloseMessage;
	
	private String fpStatus;
	
	private String[] selectSubScribersCodes;
	
	private String loginId;
	
	private String glchecked;
	
	private String district;
	
	private String mandal;
	
	private String village;
	
	public String getGlchecked() {
		return glchecked;
	}

	public void setGlchecked(String glchecked) {
		this.glchecked = glchecked;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFpPosition() {
		return fpPosition;
	}

	public void setFpPosition(String fpPosition) {
		this.fpPosition = fpPosition;
	}

	public String getFpFontType() {
		return fpFontType;
	}

	public void setFpFontType(String fpFontType) {
		this.fpFontType = fpFontType;
	}

	public String getFpFontSize() {
		return fpFontSize;
	}

	public void setFpFontSize(String fpFontSize) {
		this.fpFontSize = fpFontSize;
	}

	public String getFpFontColor() {
		return fpFontColor;
	}

	public void setFpFontColor(String fpFontColor) {
		this.fpFontColor = fpFontColor;
	}

	public String getFpDuration() {
		return fpDuration;
	}

	public void setFpDuration(String fpDuration) {
		this.fpDuration = fpDuration;
	}

	public String getFpfingerPrintType() {
		return fpfingerPrintType;
	}

	public void setFpfingerPrintType(String fpfingerPrintType) {
		this.fpfingerPrintType = fpfingerPrintType;
	}

	public String getFpChannel() {
		return fpChannel;
	}

	public void setFpChannel(String fpChannel) {
		this.fpChannel = fpChannel;
	}

	public String getFpBgColor() {
		return fpBgColor;
	}

	public void setFpBgColor(String fpBgColor) {
		this.fpBgColor = fpBgColor;
	}

	public String getFpMessage() {
		return fpMessage;
	}

	public void setFpMessage(String fpMessage) {
		this.fpMessage = fpMessage;
	}

	public String getFpUserCanCloseMessage() {
		return fpUserCanCloseMessage;
	}

	public void setFpUserCanCloseMessage(String fpUserCanCloseMessage) {
		this.fpUserCanCloseMessage = fpUserCanCloseMessage;
	}

	public String getFpStatus() {
		return fpStatus;
	}

	public void setFpStatus(String fpStatus) {
		this.fpStatus = fpStatus;
	}

	public String[] getSelectSubScribersCodes() {
		return selectSubScribersCodes;
	}

	public void setSelectSubScribersCodes(String[] selectSubScribersCodes) {
		this.selectSubScribersCodes = selectSubScribersCodes;
	}

	
}
