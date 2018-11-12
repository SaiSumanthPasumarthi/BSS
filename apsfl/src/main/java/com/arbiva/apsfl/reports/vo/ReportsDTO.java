package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;
import java.util.List;

import com.arbiva.apfgc.tenant.bo.EnterpriseSubscriberBO;
import com.arbiva.apfgc.tenant.bo.PONWiseBo;
import com.arbiva.apfgc.tenant.bo.PONWithZeroCAFBO;
import com.arbiva.apsfl.coms.dto.PageObject;

public class ReportsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PageObject pageObject;
	private List<AgeingReportVO> ageingList;
	private List<SrvsWiseSubsVO> subsList;
	private List<PackWiseSubsVO> packList;
	private List<SubsDtlsVO> subsDtlsList;
	private List<PackWiseChnlsVO> chnlList;
	private List<TransactionLogVO> txnList;
	private List<CpeOrderVO> cpeList;
	private List<StatusWiseSubsVO> statusWiseList;
	private List<ChnlsOnAlacarteVO> alacarteList;
	private List<EnterpriseSubscriberBO> entSubscriberList;
	private List<PONWiseBo> ponWiseList;
	private List<PONWithZeroCAFBO> ponWithZeroCafList;
	private String count;

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public List<AgeingReportVO> getAgeingList() {
		return ageingList;
	}

	public void setAgeingList(List<AgeingReportVO> ageingList) {
		this.ageingList = ageingList;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<SrvsWiseSubsVO> getSubsList() {
		return subsList;
	}

	public void setSubsList(List<SrvsWiseSubsVO> subsList) {
		this.subsList = subsList;
	}

	public List<PackWiseSubsVO> getPackList() {
		return packList;
	}

	public void setPackList(List<PackWiseSubsVO> packList) {
		this.packList = packList;
	}

	public List<SubsDtlsVO> getSubsDtlsList() {
		return subsDtlsList;
	}

	public void setSubsDtlsList(List<SubsDtlsVO> subsDtlsList) {
		this.subsDtlsList = subsDtlsList;
	}

	public List<PackWiseChnlsVO> getChnlList() {
		return chnlList;
	}

	public void setChnlList(List<PackWiseChnlsVO> chnlList) {
		this.chnlList = chnlList;
	}

	public List<TransactionLogVO> getTxnList() {
		return txnList;
	}

	public void setTxnList(List<TransactionLogVO> txnList) {
		this.txnList = txnList;
	}

	public List<CpeOrderVO> getCpeList() {
		return cpeList;
	}

	public void setCpeList(List<CpeOrderVO> cpeList) {
		this.cpeList = cpeList;
	}

	public List<StatusWiseSubsVO> getStatusWiseList() {
		return statusWiseList;
	}

	public void setStatusWiseList(List<StatusWiseSubsVO> statusWiseList) {
		this.statusWiseList = statusWiseList;
	}

	public List<ChnlsOnAlacarteVO> getAlacarteList() {
		return alacarteList;
	}

	public void setAlacarteList(List<ChnlsOnAlacarteVO> alacarteList) {
		this.alacarteList = alacarteList;
	}

	public List<EnterpriseSubscriberBO> getEntSubscriberList() {
		return entSubscriberList;
	}

	public void setEntSubscriberList(List<EnterpriseSubscriberBO> entSubscriberList) {
		this.entSubscriberList = entSubscriberList;
	}
	
	public List<PONWiseBo> getPonWiseList() {
		return ponWiseList;
	}

	public void setPonWiseList(List<PONWiseBo> ponWiseList) {
		this.ponWiseList = ponWiseList;
	}

	public List<PONWithZeroCAFBO> getPonWithZeroCafList() {
		return ponWithZeroCafList;
	}

	public void setPonWithZeroCafList(List<PONWithZeroCAFBO> ponWithZeroCafList) {
		this.ponWithZeroCafList = ponWithZeroCafList;
	}
}
