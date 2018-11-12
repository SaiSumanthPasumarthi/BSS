package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.arbiva.apsfl.tms.dto.Lovs;

import com.arbiva.apsfl.tms.dto.UploadHistDTO;

public class ComsHelperDTO implements Serializable {

 private static final long serialVersionUID = 1L;

	private List<CpeInformationVO> cafPaymentList;
	
	private String lmoName;
	
	private String lmoCode;
	
	private String contactMob;
	
	private String districtid;
	
	private String msoCode;
	
	private String usageYYYY;
	
	private String usageMM;
	
	private String[] bulkcustlist;
	public String[] getBulkcustlist() {
		return bulkcustlist;
	}

	public void setBulkcustlist(String[] bulkcustlist) {
		this.bulkcustlist = bulkcustlist;
	}

	public String getUsageYYYY() {
		return usageYYYY;
	}

	public void setUsageYYYY(String usageYYYY) {
		this.usageYYYY = usageYYYY;
	}

	public String getUsageMM() {
		return usageMM;
	}

	public void setUsageMM(String usageMM) {
		this.usageMM = usageMM;
	}

	public String getMsoCode() {
		return msoCode;
	}

	public void setMsoCode(String msoCode) {
		this.msoCode = msoCode;
	}

	public String getDistrictid() {
		return districtid;
	}

	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}

	public String getLmocode1() {
		return lmocode1;
	}

	public void setLmocode1(String lmocode1) {
		this.lmocode1 = lmocode1;
	}

	private String lmocode1;

	private BigDecimal sumpmnt;

	public BigDecimal getSumpmnt() {
		return sumpmnt;
	}

	public void setSumpmnt(BigDecimal sumpmnt) {
		this.sumpmnt = sumpmnt;
	}

	public BigDecimal getSumreg() {
		return sumreg;
	}

	public void setSumreg(BigDecimal sumreg) {
		this.sumreg = sumreg;
	}

	private BigDecimal sumreg;

	
	public String getContactMob() {
		return contactMob;
	}

	public void setContactMob(String contactMob) {
		this.contactMob = contactMob;
	}

	private String effectiveDate;
	
	private String lmoWalletBalence;
	
	private String creditLimit;
	
	private List<Lovs> paymentList;
	
	private String actualUserAmount;
	
	private String usedAmount;
	


	public String getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(String usedAmount) {
		this.usedAmount = usedAmount;
	}

	private String alert;
	
	private String flag;
	
	private List<ProductsVO> currentProductList;
	
	private List<ProductsVO> baseProductList;
	
	private List<ProductsVO> addOnProductList;
	
	private List<ProductsVO> oneTimeProductList;
	
	private Customer customer;
	
	private List<Lovs> poaList;
	
	private List<Lovs> poiList;
	
	private List<Substations> subStationsList;
	
	private List<CpeModal> cpeModalsList;
	
	private List<Lovs> channelList;
	
	private List<Lovs> segmentList;
	
	private List<Lovs> billCycleList;
	
	private List<Lovs> customerTypeList;
	
	private List<Lovs> customerSubTypeList;
	
	private String cafNo;
	
	private List<Districts> districtList;
	
	private List<Mandals> mandalList;
	
	private String orgName;
	
	private String billCycle;
	
	private String state;
	
	private String customerCode;
	
	private String custType;
	
	private String segment;
	
	private String channel;
	
	private List<ProductsVO> productsList;
	
	private List<Object[]> cafDaoList;
	
	private List<Object[]> customerList;
	
	
	
	

	private List<CafAndCpeChargesVO> cafProductsList;
	
	private MultiAction multiAction;
	
	private List<MonthlyPaymentBO> monthlyPaymentList;
	
	private MonthlyPayment monthlyPayment;
	
	private CpeInformationVO cpeInformationVO;
	
	private String message;
	
	private String emailId;
	
	private String officeTypeLov;
	
	private List<Object[]> enterpriseCustomerList;
	
	private EnterpriseCustomer enterpriseCustomer;
	
	private List<StatusCodes> statusCodesList;
	
	private String totalTax;
	
	private String totalAmount;
	
	private List<ErrorsDTO> provisionErrorList;
	
	private CustomerCafVO customerCafVO;
	
	private CafProducts cafProducts;
	
	private String VATTax;
	
	private List<Mandals> blMandalList;
	
	private Villages blVillages;
	
	private Villages villages;
	
	private String featureCodes;
	
	private List<CpeModal> stbModelsList;
	
	private String cpeModel;
	
	private String stbModel;
	
	private Caf caf;
	
	private List<OLT> oltList;
	
	private List<OLTPortDetails> oltPortDetailsList;
	
	private OnuStbChargesVO onuStbChargesVO;
	
	private List<Lovs> officeTypeLovList;
	
	private String lmoAgrmntMspCodes;
	
	private Districts district;
	
	private Mandals mandal;
	
	private List<TenantBusinessAreas> tenantBusinessAreasList;
	
	private List<Object[]> TenantType;
	
	

	public void setClist(BulkCustomerDTO[] clist) {
		this.clist = clist;
	}
	
	public BulkCustomerDTO[] getClist() {
		return this.clist;
	}

	private BulkCustomerDTO[] clist;
	
	private List<Mandals> instMandalList;
	
	private List<Villages> instVillageList;
	
	private List<CafProducts> iptvProductsList;
	
	private List<Lovs> telephoneConnectionsList;
	
	private CustomerCafBO customerCafBO;
	
	private LmoDetailsBO lmoDetailsBO;

	
	private PageObject pageObject;
	
	private String count;
	
	private List<Customer> custList;
	
	private List<CustomerCafBO> cafsList;
	
	private List<LmoDetailsBO> lmoList;

	private String tenantCode;
	
	private String tenanttype;
	
	private String custId;
	
	private List<UploadHistDTO> VpnErrorList;
	
	private String custBal;
	
	private List<NoOfSTBsVO> noOfSTBsList;
	
	private String secDeposit;
	
	private List<CafsVO> cafList;
	
	public List<CafsVO> getCafList() {
		return cafList;
	}

	public void setCafList(List<CafsVO> cafList) {
		this.cafList = cafList;
	}

	public String getSecDeposit() {
		return secDeposit;
	}

	public void setSecDeposit(String secDeposit) {
		this.secDeposit = secDeposit;
	}
	
	public List<NoOfSTBsVO> getNoOfSTBsList() {
		return noOfSTBsList;
	}

	public void setNoOfSTBsList(List<NoOfSTBsVO> noOfSTBsList) {
		this.noOfSTBsList = noOfSTBsList;
	}
	
	public String getCustBal() {
		return custBal;
	}

	public void setCustBal(String custBal) {
		this.custBal = custBal;
	}
	
	public List<UploadHistDTO> getVpnErrorList() {
		return VpnErrorList;
	}

	public void setVpnErrorList(List<UploadHistDTO> vpnErrorList) {
		VpnErrorList = vpnErrorList;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	private List<EnterpriseCustomerVO> entCustList;
	
	public List<EnterpriseCustomerVO> getEntCustList() {
		return entCustList;
	}

	public void setEntCustList(List<EnterpriseCustomerVO> entCustList) {
		this.entCustList = entCustList;
	}
	
	public List<Lovs> getTelephoneConnectionsList() {
		return telephoneConnectionsList;
	}

	public void setTelephoneConnectionsList(List<Lovs> telephoneConnectionsList) {
		this.telephoneConnectionsList = telephoneConnectionsList;
	}
	
	public List<CafProducts> getIptvProductsList() {
		return iptvProductsList;
	}

	public void setIptvProductsList(List<CafProducts> iptvProductsList) {
		this.iptvProductsList = iptvProductsList;
	}
	
	public List<Mandals> getInstMandalList() {
		return instMandalList;
	}

	public void setInstMandalList(List<Mandals> instMandalList) {
		this.instMandalList = instMandalList;
	}
	
	public List<Villages> getInstVillageList() {
		return instVillageList;
	}

	public void setInstVillageList(List<Villages> instVillageList) {
		this.instVillageList = instVillageList;
	}

	public List<Object[]> getTenantType() {
		return TenantType;
	}

	public void setTenantType(List<Object[]> tenantType) {
		TenantType = tenantType;
	}
	
	public List<TenantBusinessAreas> getTenantBusinessAreasList() {
		return tenantBusinessAreasList;
	}

	public void setTenantBusinessAreasList(List<TenantBusinessAreas> tenantBusinessAreasList) {
		this.tenantBusinessAreasList = tenantBusinessAreasList;
	}
	
	public Districts getDistrict() {
		return district;
	}

	public void setDistrict(Districts district) {
		this.district = district;
	}

	public Mandals getMandal() {
		return mandal;
	}

	public void setMandal(Mandals mandal) {
		this.mandal = mandal;
	}

	
	public String getLmoAgrmntMspCodes() {
		return lmoAgrmntMspCodes;
	}

	public void setLmoAgrmntMspCodes(String lmoAgrmntMspCodes) {
		this.lmoAgrmntMspCodes = lmoAgrmntMspCodes;
	}
	 
	public List<Lovs> getOfficeTypeLovList() {
		return officeTypeLovList;
	}

	public void setOfficeTypeLovList(List<Lovs> officeTypeLovList) {
		this.officeTypeLovList = officeTypeLovList;
	}
	 
	public OnuStbChargesVO getOnuStbChargesVO() {
		return onuStbChargesVO;
	}

	public void setOnuStbChargesVO(OnuStbChargesVO onuStbChargesVO) {
		this.onuStbChargesVO = onuStbChargesVO;
	}
	 
	public List<OLT> getOltList() {
		return oltList;
	}

	public void setOltList(List<OLT> oltList) {
		this.oltList = oltList;
	}

	public List<OLTPortDetails> getOltPortDetailsList() {
		return oltPortDetailsList;
	}

	public void setOltPortDetailsList(List<OLTPortDetails> oltPortDetailsList) {
		this.oltPortDetailsList = oltPortDetailsList;
	}
	 
	public Caf getCaf() {
		return caf;
	}

	public void setCaf(Caf caf) {
		this.caf = caf;
	}

	public String getStbModel() {
		return stbModel;
	}

	public void setStbModel(String stbModel) {
		this.stbModel = stbModel;
	}
	 
	public String getCpeModel() {
		return cpeModel;
	}

	public void setCpeModel(String cpeModel) {
		this.cpeModel = cpeModel;
	}

	public List<CpeModal> getStbModelsList() {
		return stbModelsList;
	}

	public void setStbModelsList(List<CpeModal> stbModelsList) {
		this.stbModelsList = stbModelsList;
	}
	 
	public String getFeatureCodes() {
		return featureCodes;
	}

	public void setFeatureCodes(String featureCodes) {
		this.featureCodes = featureCodes;
	}

	public Villages getBlVillages() {
		return blVillages;
	}

	public void setBlVillages(Villages blVillages) {
		this.blVillages = blVillages;
	}

	public Villages getVillages() {
		return villages;
	}

	public void setVillages(Villages villages) {
		this.villages = villages;
	}
	public List<Mandals> getBlMandalList() {
		return blMandalList;
	}

	public void setBlMandalList(List<Mandals> blMandalList) {
		this.blMandalList = blMandalList;
	}
	
	private List<Villages> villageList;

	public List<Villages> getVillageList() {
		return villageList;
	}

	public void setVillageList(List<Villages> villageList) {
		this.villageList = villageList;
	}
	
	public String getVATTax() {
		return VATTax;
	}

	public void setVATTax(String vATTax) {
		VATTax = vATTax;
	}
	public CafProducts getCafProducts() {
		return cafProducts;
	}

	public void setCafProducts(CafProducts cafProducts) {
		this.cafProducts = cafProducts;
	}

	public CustomerCafVO getCustomerCafVO() {
		return customerCafVO;
	}

	public void setCustomerCafVO(CustomerCafVO customerCafVO) {
		this.customerCafVO = customerCafVO;
	}
	public List<ErrorsDTO> getProvisionErrorList() {
		return provisionErrorList;
	}

	public void setProvisionErrorList(List<ErrorsDTO> provisionErrorList) {
		this.provisionErrorList = provisionErrorList;
	}
	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<StatusCodes> getStatusCodesList() {
		return statusCodesList;
	}

	public void setStatusCodesList(List<StatusCodes> statusCodesList) {
		this.statusCodesList = statusCodesList;
	}
	public List<Object[]> getEnterpriseCustomerList() {
		return enterpriseCustomerList;
	}

	public void setEnterpriseCustomerList(List<Object[]> enterpriseCustomerList) {
		this.enterpriseCustomerList = enterpriseCustomerList;
	}

	public EnterpriseCustomer getEnterpriseCustomer() {
		return enterpriseCustomer;
	}

	public void setEnterpriseCustomer(EnterpriseCustomer enterpriseCustomer) {
		this.enterpriseCustomer = enterpriseCustomer;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOfficeTypeLov() {
		return officeTypeLov;
	}

	public void setOfficeTypeLov(String officeTypeLov) {
		this.officeTypeLov = officeTypeLov;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CpeInformationVO getCpeInformationVO() {
		return cpeInformationVO;
	}

	public void setCpeInformationVO(CpeInformationVO cpeInformationVO) {
		this.cpeInformationVO = cpeInformationVO;
	}
	
	public List<MonthlyPaymentBO> getMonthlyPaymentList() {
		return monthlyPaymentList;
	}

	public void setMonthlyPaymentList(List<MonthlyPaymentBO> monthlyPaymentList) {
		this.monthlyPaymentList = monthlyPaymentList;
	}

	public MonthlyPayment getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(MonthlyPayment monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	
	public List<CafAndCpeChargesVO> getCafProductsList() {
		return cafProductsList;
	}

	public void setCafProductsList(List<CafAndCpeChargesVO> cafProductsList) {
		this.cafProductsList = cafProductsList;
	}

	public MultiAction getMultiAction() {
		return multiAction;
	}

	public void setMultiAction(MultiAction multiAction) {
		this.multiAction = multiAction;
	}
	
	public List<Object[]> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Object[]> customerList) {
		this.customerList = customerList;
	}

	public List<Object[]> getCafDaoList() {
		return cafDaoList;
	}

	public void setCafDaoList(List<Object[]> cafDaoList) {
		this.cafDaoList = cafDaoList;
	}

	public List<ProductsVO> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<ProductsVO> productsList) {
		this.productsList = productsList;
	}

	public List<ProductsVO> getBaseProductList() {
		return baseProductList;
	}

	public void setBaseProductList(List<ProductsVO> baseProductList) {
		this.baseProductList = baseProductList;
	}

	public List<ProductsVO> getAddOnProductList() {
		return addOnProductList;
	}

	public void setAddOnProductList(List<ProductsVO> addOnProductList) {
		this.addOnProductList = addOnProductList;
	}

	public List<ProductsVO> getOneTimeProductList() {
		return oneTimeProductList;
	}

	public void setOneTimeProductList(List<ProductsVO> oneTimeProductList) {
		this.oneTimeProductList = oneTimeProductList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Lovs> getPoaList() {
		return poaList;
	}

	public void setPoaList(List<Lovs> poaList) {
		this.poaList = poaList;
	}

	public List<Lovs> getPoiList() {
		return poiList;
	}

	public void setPoiList(List<Lovs> poiList) {
		this.poiList = poiList;
	}

	public List<Substations> getSubStationsList() {
		return subStationsList;
	}

	public void setSubStationsList(List<Substations> subStationsList) {
		this.subStationsList = subStationsList;
	}

	public List<CpeModal> getCpeModalsList() {
		return cpeModalsList;
	}

	public void setCpeModalsList(List<CpeModal> cpeModalsList) {
		this.cpeModalsList = cpeModalsList;
	}

	public List<Lovs> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Lovs> channelList) {
		this.channelList = channelList;
	}

	public List<Lovs> getSegmentList() {
		return segmentList;
	}

	public void setSegmentList(List<Lovs> segmentList) {
		this.segmentList = segmentList;
	}
	
	public List<Lovs> getBillCycleList() {
		return billCycleList;
	}

	public void setBillCycleList(List<Lovs> billCycleList) {
		this.billCycleList = billCycleList;
	}

	public List<Lovs> getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCustomerTypeList(List<Lovs> customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	public List<Districts> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<Districts> districtList) {
		this.districtList = districtList;
	}

	public List<Mandals> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<Mandals> mandalList) {
		this.mandalList = mandalList;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public List<CpeInformationVO> getCafPaymentList() {
		return cafPaymentList;
	}

	public void setCafPaymentList(List<CpeInformationVO> cafPaymentList) {
		this.cafPaymentList = cafPaymentList;
	}

	public String getLmoName() {
		return lmoName;
	}

	public void setLmoName(String lmoName) {
		this.lmoName = lmoName;
	}

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getLmoWalletBalence() {
		return lmoWalletBalence;
	}

	public void setLmoWalletBalence(String lmoWalletBalence) {
		this.lmoWalletBalence = lmoWalletBalence;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public List<Lovs> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Lovs> paymentList) {
		this.paymentList = paymentList;
	}

	public String getActualUserAmount() {
		return actualUserAmount;
	}

	public void setActualUserAmount(String actualUserAmount) {
		this.actualUserAmount = actualUserAmount;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public CustomerCafBO getCustomerCafBO() {
		return customerCafBO;
	}

	public void setCustomerCafBO(CustomerCafBO customerCafBO) {
		this.customerCafBO = customerCafBO;
	}

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<Customer> getCustList() {
		return custList;
	}

	public void setCustList(List<Customer> custList) {
		this.custList = custList;
	}

	public List<CustomerCafBO> getCafsList() {
		return cafsList;
	}

	public LmoDetailsBO getLmoDetailsBO() {
		return lmoDetailsBO;
	}

	public void setLmoDetailsBO(LmoDetailsBO lmoDetailsBO) {
		this.lmoDetailsBO = lmoDetailsBO;
	}

	public List<LmoDetailsBO> getLmoList() {
		return lmoList;
	}

	public void setLmoList(List<LmoDetailsBO> lmoList) {
		this.lmoList = lmoList;
	}

	public void setCafsList(List<CustomerCafBO> cafsList) {
		this.cafsList = cafsList;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getTenanttype() {
		return tenanttype;
	}

	public void setTenanttype(String tenanttype) {
		this.tenanttype = tenanttype;
	}

	public List<ProductsVO> getCurrentProductList() {
		return currentProductList;
	}

	public void setCurrentProductList(List<ProductsVO> currentProductList) {
		this.currentProductList = currentProductList;
	}

	public List<Lovs> getCustomerSubTypeList() {
		return customerSubTypeList;
	}

	public void setCustomerSubTypeList(List<Lovs> customerSubTypeList) {
		this.customerSubTypeList = customerSubTypeList;
	}
	
}
