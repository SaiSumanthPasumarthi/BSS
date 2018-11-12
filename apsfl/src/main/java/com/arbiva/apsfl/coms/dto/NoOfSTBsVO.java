package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
/**
 * @author kiran
 *
 */
public class NoOfSTBsVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String stbCafNo;
	
	private String stbSlNo;

	private String stbMacAddr;

	public String getStbCafNo() {
		return stbCafNo;
	}

	public void setStbCafNo(String stbCafNo) {
		this.stbCafNo = stbCafNo;
	}

	public String getStbSlNo() {
		return stbSlNo;
	}

	public void setStbSlNo(String stbSlNo) {
		this.stbSlNo = stbSlNo;
	}

	public String getStbMacAddr() {
		return stbMacAddr;
	}

	public void setStbMacAddr(String stbMacAddr) {
		this.stbMacAddr = stbMacAddr;
	}
	
	
	
}
