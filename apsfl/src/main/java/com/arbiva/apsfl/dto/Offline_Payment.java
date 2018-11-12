package com.arbiva.apsfl.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "offline_payment")
public class Offline_Payment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name = "cheque_ddno")
	private String cheque_ddno;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "lmocode")
	private String lmocode;
	
	@Column(name = "bankname")
	private String bankname;
	
	@Column(name = "apsflreceived_date")
	private String received_date;
	
	@Column(name = "dep_date")
	private Date dep_date;
	
	@Column(name = "modified_on")
	private Date modified_on;
	
	
	@Column(name = "cheque_Date")
	private Date valid_date;
	
	@Column(name = "clearing_date")
	private Date clearing_date;
	
	@Column(name = "payref_id")
	private String trans_id;

	@Column(name = "status")
	private double status;
	
	@Column(name = "approver_1")
	private String approver_1;
	
	@Column(name = "approver_2")
	private String approver_2;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "payment_mode")
	private String payment_mode;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getReceived_date() {
		return received_date;
	}

	public void setReceived_date(String received_date) {
		this.received_date = received_date;
	}

	public String getCheque_ddno(){
		return cheque_ddno;
	}

	public void setCheque_ddno(String cheque_ddno){
		this.cheque_ddno=cheque_ddno;
	}

	public double getAmount(){
		return amount;
	}

	public void setAmount(double amount){
		this.amount=amount;
	}

	public String getLmocode(){
		return lmocode;
	}

	public void setLmocode(String lmocode){
		this.lmocode=lmocode;
	}

	public String getBankname(){
		return bankname;
	}

	public void setBankname(String bankname){
		this.bankname=bankname;
	}

	public Date getDep_date(){
		return dep_date;
	}

	public void setDep_date(Date dep_date){
		this.dep_date=dep_date;
	}
	
	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public Date getClearing_date() {
		return clearing_date;
	}

	public void setClearing_date(Date clearing_date) {
		this.clearing_date = clearing_date;
	}

	public Date getValid_date(){
		return valid_date;
	}

	public void setValid_date(Date valid_Date){
		this.valid_date=valid_Date;
	}

	public String getTrans_id(){
		return trans_id;
	}

	public void setTrans_id(String trans_id){
		this.trans_id=trans_id;
	}

	public double getStatus(){
		return status;
	}

	public void setStatus(double status){
		this.status=status;
	}

	public String getApprover_1(){
		return approver_1;
	}

	public void setApprover_1(String approver_1){
		this.approver_1=approver_1;
	}

	public String getApprover_2(){
		return approver_2;
	}

	public void setApprover_2(String approver_2){
		this.approver_2=approver_2;
	}

	public String getComments(){
		return comments;
	}

	public void setComments(String comments){
		this.comments=comments;
	}

	public String getPayment_mode(){
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode){
		this.payment_mode=payment_mode;
	}
}
