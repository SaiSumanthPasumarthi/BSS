package com.arbiva.apsfl.tms.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.arbiva.apsfl.tms.daoImpl.StoredProcedureDAO;
import com.arbiva.apsfl.tms.daoImpl.TenantDaoImpl;
import com.arbiva.apsfl.tms.dto.TenantDTO;
import com.arbiva.apsfl.tms.dto.TenantVO;
import com.arbiva.apsfl.tms.model.Tenant;
import com.arbiva.apsfl.tms.model.TenantBankDetails;
import com.arbiva.apsfl.tms.model.TenantBussinessAreas;
import com.arbiva.apsfl.tms.model.TenantDocuments;
import com.arbiva.apsfl.tms.model.TenantLicenses;
import com.arbiva.apsfl.tms.model.TenantWallet;
import com.arbiva.apsfl.tms.dto.CpeStockVO;
import com.arbiva.apsfl.tms.dto.CreatePartnerDto;

@Service
public class TenantServiceImpl {

	@Autowired
	TenantDaoImpl tenantDao;

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	StoredProcedureDAO storedProcedureDAO;

	@Autowired
	TenantDocumentsServiceImpl tenantDocumentsService;

	@Autowired
	TenantBankDetailsServiceImpl tenantBankDetailsService;

	@Autowired
	TenantLicensesServiceImpl tenantLicensesService;

	@Autowired
	TenantWalletServiceImpl tenantWalletServiceImpl;
	
	@Autowired
	PortalAssetsServiceImpl portalassetsservice;
	
	@Autowired
	PortalAreaServiceImpl portalAreaService;

	@Autowired
	PortalAssetsServiceImpl portalAssetsServiceImpl;

	@Autowired
	PortalAreaServiceImpl portalAreaServiceImpl;

	@Value("${UMS-URL}")
	private String umsURL;

	private static final Logger LOGGER = Logger.getLogger(TenantServiceImpl.class);

	// private static int autoIncrement = 1006;

	@Transactional
	public String saveTenant(TenantVO tenantVO, Integer tenantId) {
		String responce = null; 
		Tenant tenant = new Tenant();
		String userName = (String) httpServletRequest.getSession(false).getAttribute("loginID");
		Long tenId = null;
		try {
			LOGGER.info("TenantServiceImpl :: saveTenant() :: START");
			if (tenantId == null) {
				tenId = storedProcedureDAO.executeStoredProcedure("TENANTID");
				tenantId = tenId.intValue();
				tenant = new Tenant();
				tenant.setCreatedBy(userName);
				tenant.setTenantId(tenId.intValue());
				tenant.setCreatedDate(Calendar.getInstance());
				tenant.setCratedIPAddress(httpServletRequest.getRemoteAddr());
				tenant.setStatus(1);
				tenant.setModifiedDate(Calendar.getInstance());
			} else {
				tenant = findByTenantId(tenantId);
				tenant.setModifiedBy(userName);
				tenant.setModifiedDate(Calendar.getInstance());
				tenant.setModifiedIPAddress(httpServletRequest.getRemoteAddr());
			}
			
			if(tenantDao.checkTenentCode(tenantVO.getTenantCode()).equalsIgnoreCase("Failure") && !tenantVO.getTenantViewStatus().equalsIgnoreCase("Edit"))
			{
				return "duplicateTenentCode";
			}
			
			if(tenantVO.getTenantTypeLov().equalsIgnoreCase("APSFL"))
			{
				if(tenantDao.checkTenentType().equalsIgnoreCase("Failure"))
				{
					return "duplicateTenentType";
				}
			}
			tenant.setTenantCode(tenantVO.getTenantCode());
			tenant.setName(tenantVO.getName());
			tenant.setPortalEnrllmentno(tenantVO.getPortalEnrllmentno());
			tenant.setPortalPostalRegno(tenantVO.getPortalPostalRegno());
			tenant.setPortalPostExpDate(tenantVO.getPortalPostExpDate());
			tenant.setAadharCardNo(tenantVO.getAadharCardNo());
			tenant.setTenantTypeLov(tenantVO.getTenantTypeLov());
			tenant.setAddress1(tenantVO.getAddress1());
			tenant.setAddress2(tenantVO.getAddress2());
			tenant.setArea(tenantVO.getArea());
			tenant.setCity(tenantVO.getCity());
			tenant.setEmailId1(tenantVO.getEmailId1());
			tenant.setEmailId2(tenantVO.getEmailId2());
			tenant.setFax1(tenantVO.getFax1());
			tenant.setFax2(tenantVO.getFax2());
			tenant.setGstNo(tenantVO.getGstNo());
			tenant.setLandline1(tenantVO.getLandline1());
			tenant.setLandline2(tenantVO.getLandline2());
			tenant.setLocality(tenantVO.getLocality());
			tenant.setLocalOfficeAddress1(tenantVO.getLocalOfficeAddress1());
			tenant.setLocalOfficeAddress2(tenantVO.getLocalOfficeAddress2());
			tenant.setLocalOfficeArea(tenantVO.getLocalOfficeArea());
			tenant.setLocalOfficeCity(tenantVO.getLocalOfficeCity());
			tenant.setLocalOfficeEmailId1(tenantVO.getLocalOfficeEmailId1());
			tenant.setLocalOfficeEmailId2(tenantVO.getLocalOfficeEmailId2());
			tenant.setLocalOfficeFax1(tenantVO.getLocalOfficeFax1());
			tenant.setLocalOfficeFax2(tenantVO.getLocalOfficeFax2());
			tenant.setLocalOfficeLandline1(tenantVO.getLocalOfficeLandline1());
			tenant.setLocalOfficeLandline2(tenantVO.getLocalOfficeLandline2());
			tenant.setLocalOfficeLocality(tenantVO.getLocalOfficeLocality());
			tenant.setLocalOfficePincode(tenantVO.getLocalOfficePincode());
			tenant.setLocalOfficePocMobileNo1(tenantVO.getLocalOfficePocMobileNo1());
			tenant.setLocalOfficePocMobileNo2(tenantVO.getLocalOfficePocMobileNo2());
			tenant.setLocalOfficePocName(tenantVO.getLocalOfficePocName());
			tenant.setLocalOfficeStateName(tenantVO.getLocalOfficeStateName());
			tenant.setLocalOfficestdcode(tenantVO.getLocalOfficestdcode());
			tenant.setPanNo(tenantVO.getPanNo());
			tenant.setPincode(tenantVO.getPincode());
			tenant.setPocMobileNo1(tenantVO.getPocMobileNo1());
			tenant.setPocMobileNo2(tenantVO.getPocMobileNo2());
			tenant.setPocName(tenantVO.getPocName());
			tenant.setStateName(tenantVO.getStateName());
			tenant.setStdcode(tenantVO.getStdcode());
			tenant.setTanNo(tenantVO.getTanNo());
			tenant.setTinNo(tenantVO.getTinNo());
			tenant.setVatNo(tenantVO.getVatNo());
			tenant.setPortalSubstn1Id(tenantVO.getPortalSubstn1Id());
			tenant.setPortalSubstn1Distance(tenantVO.getPortalSubstn1Distance());
			tenant.setPortalSubstn2Id(tenantVO.getPortalSubstn2Id());
			tenant.setPortalSubstn2Distance(tenantVO.getPortalSubstn2Distance());
			tenant.setPortalMsoName(tenantVO.getPortalMsoName());
			tenant.setPortalDasLicenceProvider(tenantVO.getPortalDasLicenceProvider());
			tenant.setPortalRgnmsp1(tenantVO.getPortalRgnmsp1());
			tenant.setPortalRegMsp1AssocYrs(tenantVO.getPortalRegMsp1AssocYrs());
			tenant.setPortalLocmsp1(tenantVO.getPortalLocmsp1());
			tenant.setPortalLocMsp1AssocYrs(tenantVO.getPortalLocMsp1AssocYrs());
			tenant.setPortalRgnMsp2(tenantVO.getPortalRgnMsp2());
			tenant.setPortalRegMsp2AssocYrs(tenantVO.getPortalRegMsp2AssocYrs());
			tenant.setPortalLocMsp2(tenantVO.getPortalLocMsp2());
			tenant.setPortalLocMsp2AssocYrs(tenantVO.getPortalLocMsp2AssocYrs());
			tenant.setPortalRgnMsp3(tenantVO.getPortalRgnMsp3());
			tenant.setPortalRegMsp3AssocYrs(tenantVO.getPortalRegMsp3AssocYrs());
			tenant.setPortalLocMsp3(tenantVO.getPortalLocMsp3());
			tenant.setPortalLocMsp3AssocYrs(tenantVO.getPortalLocMsp3AssocYrs());
			tenant.setPortalRgnMsp4(tenantVO.getPortalRgnMsp4());
			tenant.setPortalRegMsp4AssocYrs(tenantVO.getPortalRegMsp4AssocYrs());
			tenant.setPortalLocMsp4(tenantVO.getPortalLocMsp4());
			tenant.setPortalLocMsp4AssocYrs(tenantVO.getPortalLocMsp4AssocYrs());
			tenant.setPortalRgnMsp5(tenantVO.getPortalRgnMsp5());
			tenant.setPortalRegMsp5Assocyrs(tenantVO.getPortalRegMsp5Assocyrs());
			tenant.setPortalLocMsp5(tenantVO.getPortalLocMsp5());
			tenant.setPortalLocMsp5AssocYrs(tenantVO.getPortalLocMsp5AssocYrs());
			tenant.setPortalRgnMsp6(tenantVO.getPortalRgnMsp6());
			tenant.setPortalRegMsp6Assocyrs(tenantVO.getPortalRegMsp6Assocyrs());
			tenant.setPortalLocMsp6(tenantVO.getPortalLocMsp6());
			tenant.setPortalLocMsp6AssocYrs(tenantVO.getPortalLocMsp6AssocYrs());
			tenant.setPortalDgtConnCnt(tenantVO.getPortalDgtConnCnt());
			tenant.setPortalAnlConnCnt(tenantVO.getPortalAnlConnCnt());
			tenant.setPortalDasLicense(tenantVO.getPortalDasLicense());
			tenant.setPortalDasLicenceType(tenantVO.getPortalDasLicenceType());
			tenant.setPortalDasLicenseHolder(tenantVO.getPortalDasLicenseHolder());
			tenant.setPortalDasLicenseExpDate(tenantVO.getPortalDasLicenseExpDate());
			tenant.setPortalPaychnlCnt(tenantVO.getPortalPaychnlCnt());
			tenant.setPortalCompanyType(tenantVO.getPortalCompanyType());
			tenant.setPortalPartnerName(tenantVO.getPortalPartnerName());
			tenant.setPortalHouseHoldCnt(tenantVO.getPortalHouseHoldCnt());
			tenant.setPortalMibLicenseNo(tenantVO.getPortalMibLicenseNo());
			tenant.setPortalMibLicenseExpDate(tenantVO.getPortalMibLicenseExpDate());
			tenant.setPortal_districtid(tenantVO.getPortal_districtid());
			tenant.setPortal_mandalid(tenantVO.getPortal_mandalid());
			tenant.setPortal_villageid(tenantVO.getPortal_villageid());
			//tenant.setVillageIds(tenantVO.getVillagesEnrolled());
			tenant = tenantDao.saveTenant(tenant);
			tenantVO.setTenantId(tenant.getTenantId());

			tenantDocumentsService.saveTenantDocument(tenantVO, tenantId);

			tenantLicensesService.saveTenantLicenses(tenantVO, tenant, tenantId);

			tenantBankDetailsService.saveTenantBankDetails(tenantVO, tenantId);

			tenantWalletServiceImpl.saveTenantWallet(tenantVO, tenantId);
			
			if (!tenantVO.getPassets().isEmpty()) {
				portalAssetsServiceImpl.savePortalAssets(tenantVO);
			}
			if (tenantVO.getPareas() != null && !tenantVO.equals("")) {
				portalAreaServiceImpl.savePortalAreas(tenantVO);
			}
			if(tenantVO.getTenantsbaList()!= null && !tenantVO.getTenantsbaList().isEmpty() ){
				
				for(TenantBussinessAreas tba : tenantVO.getTenantsbaList()){
					/*TenantBussinessAreas disId = tenantVO.getTenantsbaList().get(tenant.getPortal_districtid());*/
					if(tba.getDistrictuid()!=0 && tba.getMandalslno()!= 0)
					{
					tba.setTenantcode(tenantVO.getTenantCode());
					tenantDao.saveTenantBussinessAreas(tba);
					}	
				}
			}
			responce = "Success";
			LOGGER.info("TenantServiceImpl :: saveTenant() :: END");
		} catch (Exception e) {
			LOGGER.error("TenantServiceImpl::saveTenants() " + e);
			responce = "Failure";
			e.printStackTrace();
		}
		finally{
			tenantVO = null;
			tenant =  null;
			userName = null;
			tenId = null;
		}
		return responce;
	}

	public List<Tenant> findAllTenants() {
		List<Tenant> tenants = new ArrayList<Tenant>();
		try{
			tenants = tenantDao.findAllTenants();
		}catch(Exception e){
			
		}finally{
			
		}
		
		return tenants;
	}

	public Tenant findByTenantCode(String tenantCode) {
		Tenant tenant = new Tenant();
		try{
			tenant = tenantDao.findByTenantCode(tenantCode);
		}catch(Exception e){
			
		}finally{
			
		}
		
		return tenant;
	}

	public List<Tenant> findMspCodeByTenantType() {
		List<Tenant> tenants = new ArrayList<Tenant>();
		try{
			tenants = tenantDao.findMspCodeByTenantType();
		}catch(Exception e){
			
		}finally{
			
		}
		
		return tenants;
	}

	public List<Tenant> findLmoCodeByTenantType(String tenantCode) {
		List<Tenant> tenants = new ArrayList<Tenant>();
		try{
			tenants = tenantDao.findLmoCodeByTenantType(tenantCode);
		}catch(Exception e){
			
		}finally{
			
		}
		
		return tenants;
	}

	public Tenant findByTenantId(Integer tenantId) {
		Tenant tenant = new Tenant();
		try{
			tenant = tenantDao.findByTenantId(tenantId);
		}catch(Exception e){
			
		}finally{
			
		}
		
		return tenant;
	}
	
	public TenantVO findByTenantId(Integer tenantId, String tenantCode,String Enrollmentno) {
		Tenant tenant  = null;
		TenantBankDetails  tenantBankDetails  = null;
		TenantLicenses tenantLicenses = null;
		TenantWallet tenantWallet = null;
		List<TenantDocuments> tenantDocuments = null;
		TenantVO  tenantVO = null;
		try{
			tenant  = tenantDao.findByTenantId(tenantId);
			tenantBankDetails  = tenantBankDetailsService.findByTenantCode(tenantCode);
			 tenantLicenses = tenantLicensesService.findByTenantCode(tenantCode);
			 tenantWallet = tenantWalletServiceImpl.findByTenantCode(tenantCode);
			 tenantDocuments = tenantDocumentsService.findByTenantCode(tenantCode);
			tenantVO = new TenantVO(tenant, tenantBankDetails, tenantLicenses, tenantDocuments, tenantWallet);
		}catch(Exception e){
			
		}finally{
			 tenant  = null;
			  tenantBankDetails  = null;
			 tenantLicenses = null;
			 tenantWallet = null;
			tenantDocuments = null;
		}
	
		
		return tenantVO;
	}


	public List<TenantDTO> findAllTenantsBYCreatedBy(String loginID) {
		List<Tenant> tenantsList = null;
		List<TenantDTO> tenantsDtoList = new ArrayList<TenantDTO>();
		try{
			tenantsList = tenantDao.findAllTenantsBYCreatedBy(loginID);
			for (Tenant tenant : tenantsList) {
				TenantDTO tenantDTO = new TenantDTO(tenant);
				tenantsDtoList.add(tenantDTO);
			}
		}catch(Exception e){
			
		}finally{
			tenantsList = null;
		}
		
		
		return tenantsDtoList;
	}
	
	public Map<String, String> getDistrictList(String stateID) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Object[]> lovs = tenantDao.getDistrictList(stateID);
		String s1 = "";
		String s2 = "";
		for (Object[] object : lovs) {
			try {
				s1 = object[0] == null ? "" : object[0].toString();
				s2 = object[1] == null ? "" : object[1].toString();
				map.put(s1, s2);
			} catch (Exception ex) {
				ex.printStackTrace();
				// LOGGER.info(ex.getMessage());
			}finally{
				s1 = null;
				s2 = null;
				lovs = null;
			}
		}
		return map;
	}
	
	public Map<String, String> getMandalList(String stateID, String districtID) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Object[]> lovs = tenantDao.getMandalList(stateID,districtID);
		String s1 = "";
		String s2 = "";
		for (Object[] object : lovs) {
			try {
				s1 = object[0] == null ? "" : object[0].toString();
				s2 = object[1] == null ? "" : object[1].toString();
				map.put(s1, s2);
			} catch (Exception ex) {
				ex.printStackTrace();
				// LOGGER.info(ex.getMessage());
			}finally{
				s1 = null;
				s2 = null;
				lovs = null;
			}
		}
		return map;
	}
	
	public Map<String, String> getVillageList(String stateID,String districtID, String mandalID) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Object[]> lovs = tenantDao.getVillageList(stateID,districtID, mandalID);
		String s1 = "";
		String s2 = "";
		for (Object[] object : lovs) {
			try {
				s1 = object[0] == null ? "" : object[0].toString();
				s2 = object[1] == null ? "" : object[1].toString();
				map.put(s1, s2);
			} catch (Exception ex) {
				ex.printStackTrace();
				// LOGGER.info(ex.getMessage());
			}finally{
				s1 = null;
				s2 = null;
				lovs = null;
			}
		}
		return map;
	}
	
	public Map<String, String> getcableList() {
		Map<String, String> map = new LinkedHashMap<String, String>();	
		List<Object[]> lovs = tenantDao.getcableList();
		String s1 = "";
		String s2 = "";
		for (Object[] object : lovs) {
			try {
				s1 = object[0] == null ? "" : object[0].toString();
				s2 = object[1] == null ? "" : object[1].toString();
				map.put(s1, s2);
			} catch (Exception ex) {
				ex.printStackTrace();
				// LOGGER.info(ex.getMessage());
			}finally{
				s1 = null;
				s2 = null;
				lovs = null;
			}
		}
		return map;
	}
	
	public Map<String, String> getassetsList() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Object[]> lovs = tenantDao.getassetsList();
		String s1 = "";
		String s2 = "";
		for (Object[] object : lovs) {
			try {
				s1 = object[0] == null ? "" : object[0].toString();
				s2 = object[1] == null ? "" : object[1].toString();
				map.put(s1, s2);
			} catch (Exception ex) {
				ex.printStackTrace();
				// LOGGER.info(ex.getMessage());
			}finally{
				s1 = null;
				s2 = null;
				lovs = null;
			}
		}
		return map;
	}
	
	public Map<String, String> getstateList() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Object[]> lovs = tenantDao.getstateList();
		String s1 = "";
		String s2 = "";
		for (Object[] object : lovs) {
			try {
				s1 = object[0] == null ? "" : object[0].toString();
				s2 = object[1] == null ? "" : object[1].toString();
				map.put(s1, s2);
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally{
				s1 = null;
				s2 = null;
				lovs = null;
			}
		}
		return map;
	}
	
	

	public Map<String, String> getSubstationList() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Object[]> lovs = tenantDao.getSubstationList();
		String s1 = "";
		String s2 = "";
		for (Object[] object : lovs) {
			try {
				s1 = object[0] == null ? "" : object[0].toString();
				s2 = object[1] == null ? "" : object[1].toString();
				map.put(s1, s2);
			} catch (Exception ex) {
				ex.printStackTrace();
				// LOGGER.info(ex.getMessage());
			}
			finally{
				s1 = null;
				s2 = null;
				lovs = null;
			}
		}
		return map;
	}
	
	public List<TenantBussinessAreas> getTbareasList(String tenantCode){
		List<TenantBussinessAreas> tbaList = new ArrayList<>();
		try{
			tbaList = tenantDao.getTbareasList(tenantCode);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return tbaList;
	}
	
	public List<CpeStockVO> getCPEStockDetails(String cpesrlno,String fromDate , String toDate,String action) {
		List<CpeStockVO> cpeList = new ArrayList<CpeStockVO>();
		List<Object[]> resultcpeList = new ArrayList<>();
		
		try {
			resultcpeList = tenantDao.getCpeDetails(cpesrlno,fromDate,toDate,action);
			for (Object[] list : resultcpeList) {
				CpeStockVO cpe = new CpeStockVO();
				
				cpe.setCpeSrlno(list[0] == null || list[0].toString() == "" ? "NA" : list[0].toString());
				cpe.setMacAddress(list[1] == null || list[1].toString() == "" ? "NA" : list[1].toString());
				cpe.setMspCode(list[2] == null || list[2].toString() == "" ? "NA" : list[2].toString());
				cpe.setLmoCode(list[3] == null || list[3].toString() == "" ? "NA" : list[3].toString());
				cpe.setCpeLogTransactionStatus(list[4] == null || list[4].toString() == "" ? "NA" : list[4].toString());
				cpe.setCpeLogTransactionDate(list[5] == null || list[5].toString() == "" ? "NA" : list[5].toString());
				cpe.setUser(list[6] == null || list[6].toString() == "" ? "NA" : list[6].toString());
				cpeList.add(cpe);
			}
		} catch (Exception e) {
			LOGGER.error("The Exception is TenantServiceImpl :: getCPEStockDetails" + e);
			e.printStackTrace();
		} finally {
			resultcpeList = null;
		}
		return cpeList;
	}
	
	
	
	
	
	public CreatePartnerDto getPartnerDetails(String tenantCode) {
		CreatePartnerDto partner = new CreatePartnerDto();
		
		Tenant tenant=new Tenant();
		try{
			tenant = tenantDao.findByTenantCode(tenantCode);
			
			
			partner.setFranchiseCode(tenant.getTenantCode());
			partner.setFranchiseName(tenant.getTenantCode());
			partner.setFranchiseNetworkName(tenant.getName());
			partner.setEmailId(tenant.getEmailId1());
			partner.setAddress1(tenant.getAddress1());
			partner.setAreaCode(tenant.getArea());
			partner.setCityCode(tenant.getCity());
			partner.setStateCode("AP");
			partner.setCountryCode("India");
			partner.setCurrencyCode("INR");
			partner.setZipCode(tenant.getPincode());
			partner.setPhoneno(tenant.getPocMobileNo1());
			
			
		}catch(Exception e){
			
		}finally{
			
		}
		
		return partner;
	}
	
	
	
	
	
	
	
	

}
