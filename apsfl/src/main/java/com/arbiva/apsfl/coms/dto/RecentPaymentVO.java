package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

public class RecentPaymentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pmntMode;
	
	private String pmntDate;
	
	private String pmntRefNo;
	
	private String pmntAmt;

	public String getPmntMode() {
		return pmntMode;
	}

	public void setPmntMode(String pmntMode) {
		this.pmntMode = pmntMode;
	}

	public String getPmntDate() {
		return pmntDate;
	}

	public void setPmntDate(String pmntDate) {
		this.pmntDate = pmntDate;
	}

	public String getPmntRefNo() {
		return pmntRefNo;
	}

	public void setPmntRefNo(String pmntRefNo) {
		this.pmntRefNo = pmntRefNo;
	}

	public String getPmntAmt() {
		return pmntAmt;
	}

	public void setPmntAmt(String pmntAmt) {
		this.pmntAmt = pmntAmt;
	}
	
}
