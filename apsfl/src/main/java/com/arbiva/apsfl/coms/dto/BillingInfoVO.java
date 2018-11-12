/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

public class BillingInfoVO implements Serializable {

private static final long serialVersionUID = 1L;
	
	private String billMonth;
	
	private String amount;
	
	private String dueDate;
	
	private String invDate;
	
	private String filePath;

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
