/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author kiran
 *
 */
public class BalanceAdjustmentVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String custId;
	
	private String custinvno;
	
	private String aadharNo;
	
	private String custName;
	
	private String invMonth;
	
	private String invYear;
	
	private String invDate;
	
	private String totalAmount;
	
	private String acctcafno;
	
	public String getAcctcafno() {
		return acctcafno;
	}

	public void setAcctcafno(String acctcafno) {
		this.acctcafno = acctcafno;
	}

	public String getCustinvno() {
		return custinvno;
	}

	public void setCustinvno(String custinvno) {
		this.custinvno = custinvno;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getInvMonth() {
		return invMonth;
	}

	public void setInvMonth(String invMonth) {
		this.invMonth = invMonth;
	}

	public String getInvYear() {
		return invYear;
	}

	public void setInvYear(String invYear) {
		this.invYear = invYear;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

}
