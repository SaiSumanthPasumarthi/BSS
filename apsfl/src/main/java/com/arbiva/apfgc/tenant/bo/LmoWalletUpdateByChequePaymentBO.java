package com.arbiva.apfgc.tenant.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LmoWalletUpdateByChequePaymentBO implements Serializable {


	private static final long serialVersionUID = 5218940029138586458L;
	
	@Id
	@Column(name="lmocode")
	private String lmoCode;
	
	@Id
	@Column(name="cheque_ddno")
	private String cheque_DDno;
	
	@Column(name="amount")
	private String cheque_Dd_Amount;
	
	@Column(name="bankname")
	private String bankName;
	
	@Column(name="deposit_amt")
	private String lmo_walletAmnt;
	
	@Column(name="status")
	private String status;
	
	@Column(name="dep_date")
	private String dep_date;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDep_date() {
		return dep_date;
	}
	public void setDep_date(String dep_date) {
		this.dep_date = dep_date;
	}
	@Column(name="payment_mode")
	private String paymentMode;
	
	
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getLmoCode() {
		return lmoCode;
	}
	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}
	public String getCheque_DDno() {
		return cheque_DDno;
	}
	public void setCheque_DDno(String cheque_DDno) {
		this.cheque_DDno = cheque_DDno;
	}
	public String getCheque_Dd_Amount() {
		return cheque_Dd_Amount;
	}
	public void setCheque_Dd_Amount(String cheque_Dd_Amount) {
		this.cheque_Dd_Amount = cheque_Dd_Amount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getLmo_walletAmnt() {
		return lmo_walletAmnt;
	}
	public void setLmo_walletAmnt(String lmo_walletAmnt) {
		this.lmo_walletAmnt = lmo_walletAmnt;
	}
	

}

