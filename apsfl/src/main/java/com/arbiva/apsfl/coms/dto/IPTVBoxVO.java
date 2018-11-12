/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Arbiva
 *
 */
public class IPTVBoxVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stbLease;

	private String stbEmiPrice;

	private String stbPrice;

	private String stbModel;
	
	private String stbInstallmentCount;

	private String stbSerialNo;
	
	private String macAddress;
	
	private String iptvSrvcCodes;
	
	private String stbCafNo;
	
	public String getStbCafNo() {
		return stbCafNo;
	}

	public void setStbCafNo(String stbCafNo) {
		this.stbCafNo = stbCafNo;
	}

	public String getStbLease() {
		return stbLease;
	}

	public void setStbLease(String stbLease) {
		this.stbLease = stbLease;
	}
	
	public String getStbEmiPrice() {
		return stbEmiPrice;
	}

	public void setStbEmiPrice(String stbEmiPrice) {
		this.stbEmiPrice = stbEmiPrice;
	}

	public String getStbPrice() {
		return stbPrice;
	}

	public void setStbPrice(String stbPrice) {
		this.stbPrice = stbPrice;
	}

	public String getStbModel() {
		return stbModel;
	}

	public void setStbModel(String stbModel) {
		this.stbModel = stbModel;
	}

	public String getStbInstallmentCount() {
		return stbInstallmentCount;
	}

	public void setStbInstallmentCount(String stbInstallmentCount) {
		this.stbInstallmentCount = stbInstallmentCount;
	}

	public String getStbSerialNo() {
		return stbSerialNo;
	}

	public void setStbSerialNo(String stbSerialNo) {
		this.stbSerialNo = stbSerialNo;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getIptvSrvcCodes() {
		return iptvSrvcCodes;
	}

	public void setIptvSrvcCodes(String iptvSrvcCodes) {
		this.iptvSrvcCodes = iptvSrvcCodes;
	}
	
}
