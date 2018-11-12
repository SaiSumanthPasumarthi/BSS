package com.arbiva.apsfl.coms.dto;

import java.util.List;


public class CafDetailsVO {

	private List<RecentPaymentVO> paymentList;

	private Object[] cafDao;

	private List<CpeInformationVO> cafList;

	private List<PaymentDetailsVO> newCafList;

	private List<TelephoneServcVO> telephoneser;

	private List<TelephoneNoVO> telephoneNo;

	private List<BillingInfoVO> billInfo;
	
	private List<CafStbsVO> stbInfoList;
	
	private List<TicketDetailsVO> ticketInfoList;
	
	private List<CustomerDTO> customerInfoList;

	public List<BillingInfoVO> getBillInfo() {
		return billInfo;
	}

	public void setBillInfo(List<BillingInfoVO> billInfo) {
		this.billInfo = billInfo;
	}
	
	public List<TelephoneNoVO> getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(List<TelephoneNoVO> telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public List<TelephoneServcVO> getTelephoneser() {
		return telephoneser;
	}

	public void setTelephoneser(List<TelephoneServcVO> telephoneser) {
		this.telephoneser = telephoneser;
	}

	public List<RecentPaymentVO> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<RecentPaymentVO> paymentList) {
		this.paymentList = paymentList;
	}

	public List<PaymentDetailsVO> getNewCafList() {
		return newCafList;
	}

	public void setNewCafList(List<PaymentDetailsVO> newCafList) {
		this.newCafList = newCafList;
	}

	public Object[] getCafDao() {
		return cafDao;
	}

	public void setCafDao(Object[] cafDao) {
		this.cafDao = cafDao;
	}

	public List<CpeInformationVO> getCafList() {
		return cafList;
	}

	public void setCafList(List<CpeInformationVO> cafList) {
		this.cafList = cafList;
	}

	public List<CafStbsVO> getStbInfoList() {
		return stbInfoList;
	}

	public void setStbInfoList(List<CafStbsVO> stbInfoList) {
		this.stbInfoList = stbInfoList;
	}

	public List<TicketDetailsVO> getTicketInfoList() {
		return ticketInfoList;
	}

	public void setTicketInfoList(List<TicketDetailsVO> ticketInfoList) {
		this.ticketInfoList = ticketInfoList;
	}

	public List<CustomerDTO> getCustomerInfoList() {
		return customerInfoList;
	}

	public void setCustomerInfoList(List<CustomerDTO> customerInfoList) {
		this.customerInfoList = customerInfoList;
	}
}
