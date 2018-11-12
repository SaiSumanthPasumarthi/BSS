package com.arbiva.apsfl.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.arbiva.apfgc.tenant.bo.TenantStockReportBO;
import com.arbiva.apsfl.coms.dto.CafsForBlockListVO;
import com.arbiva.apsfl.coms.dto.OLT;
import com.arbiva.apsfl.coms.dto.OLTPortDetails;
import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.tms.dto.BlackListInfoVO;
import com.arbiva.apsfl.tms.dto.BlackListResultVO;
import com.arbiva.apsfl.tms.dto.CpeHelperDTO;
import com.arbiva.apsfl.tms.dto.CpeStockVO;
import com.arbiva.apsfl.tms.dto.CreatePartnerDto;
import com.arbiva.apsfl.tms.dto.CustomerInvDtlsDTO;
import com.arbiva.apsfl.tms.dto.Districts;
import com.arbiva.apsfl.tms.dto.Mandals;
import com.arbiva.apsfl.tms.dto.ProductAgreementPartnersVO;
import com.arbiva.apsfl.tms.dto.ProductAgreementVO;
import com.arbiva.apsfl.tms.dto.ProductInfoVo;
import com.arbiva.apsfl.tms.dto.RequestDTO;
import com.arbiva.apsfl.tms.dto.RevenueSharingTemplateDetails;
import com.arbiva.apsfl.tms.dto.RevenueSharingTemplateMaster;
import com.arbiva.apsfl.tms.dto.RsTemplateDetailsDTO;
import com.arbiva.apsfl.tms.dto.RsTemplateDetailsListDTO;
import com.arbiva.apsfl.tms.dto.ServiceDetailsDTO;
import com.arbiva.apsfl.tms.dto.TemplatePartnerVO;
import com.arbiva.apsfl.tms.dto.TenantDTO;
import com.arbiva.apsfl.tms.dto.TenantDataVO;
import com.arbiva.apsfl.tms.dto.TenantLMORevServiceDTO;
import com.arbiva.apsfl.tms.dto.TenantVO;
import com.arbiva.apsfl.tms.dto.TenantValidation;
import com.arbiva.apsfl.tms.dto.TmsHelperDTO;
import com.arbiva.apsfl.tms.model.CpeStock;
import com.arbiva.apsfl.tms.model.MspChnlsStg;
import com.arbiva.apsfl.tms.model.SelectPaymentVO;
import com.arbiva.apsfl.tms.model.Tenant;
import com.arbiva.apsfl.tms.model.TenantBussinessAreas;
import com.arbiva.apsfl.tms.model.TenantDocuments;
import com.arbiva.apsfl.tms.serviceImpl.LovsServiceImpl;
import com.arbiva.apsfl.tms.serviceImpl.PortalAreaServiceImpl;
import com.arbiva.apsfl.tms.serviceImpl.PortalAssetsServiceImpl;
import com.arbiva.apsfl.tms.serviceImpl.RegionServiceImpl;
import com.arbiva.apsfl.tms.serviceImpl.TenantDocumentsServiceImpl;
import com.arbiva.apsfl.tms.serviceImpl.TenantServiceImpl;
import com.arbiva.apsfl.tt.dto.TroubleTicketDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.DateUtill;
import com.arbiva.apsfl.util.FileUtil;
import com.arbiva.apsfl.util.IpAddressValues;
import com.arbiva.apsfl.util.PaginationObject;
import com.arbiva.apsfl.util.ReadXlsExcelFileForCPEStock;
import com.arbiva.apsfl.util.ReadXlsExcelFileForMspChannelUpload;
import com.arbiva.apsfl.util.ReadXlsFileForCafs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;

@Controller
public class TmsController {
	
	private static final Logger logger = Logger.getLogger(TmsController.class);
	
	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;
	
	@Autowired
	TenantServiceImpl tenantService;
	
	@Autowired
	PortalAssetsServiceImpl portalassetsservice;
	
	@Autowired
	PortalAreaServiceImpl portalAreaService;
	
	@Autowired
	RegionServiceImpl regionService;
	
	@Autowired
	LovsServiceImpl lovsService;
	
	@Autowired
	TenantDocumentsServiceImpl tenantDocumentsService;
	
	@RequestMapping(value = "/tmsHome", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		return "tmsHome";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/tmsHomePagination", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PaginationObject<TenantDTO> home(HttpServletRequest request){
		
		HttpEntity<TenantDataVO> httpEntity;
		ResponseEntity<PaginationObject> response;
		PaginationObject<TenantDTO> tenantsLists = new PaginationObject<>();
		String tenantType = (String) request.getSession().getAttribute("domain");
		try {
		    logger.info("TmsController :: home() :: Start");
		 // Getting PAGE Data 
 			Integer pageDisplayLength=5;
 			Integer pageNumber = 1;
 			String sdir="desc";
 			String sortColumn="";
 			int sortParameter=0;
 			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
 			if (null != request.getParameter("iDisplayStart"))
 				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/pageDisplayLength)+1;
 			if(request.getParameter("iSortCol_0")!=null){
 				sortParameter =Integer.parseInt( request.getParameter("iSortCol_0"));
 			}
 	    	sortColumn = TenantDTO.SortColumnName.getColumns("COLUMN_"+sortParameter);
 			if(request.getParameter("sSortDir_0")!=null)
 		   	sdir = request.getParameter("sSortDir_0");
 			String searchParameter = request.getParameter("sSearch");
 			
 			//Setting All page Data to PageObject
 			PageObject pageObject= new PageObject();
 			pageObject.setMinSize((pageNumber-1)*pageDisplayLength);
 			pageObject.setMaxSize(pageDisplayLength);
 			pageObject.setSortColumn(sortColumn);
 			pageObject.setSortOrder(sdir);
 			pageObject.setSearchParameter(searchParameter);
 			pageObject.setTenantType(tenantType);
 			
 			TenantDataVO tenantDataVO = new TenantDataVO();
 			tenantDataVO.setLoginId((String)request.getSession().getAttribute("loginID"));
 			tenantDataVO.setPageObject(pageObject);
 			
 			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(), tenantDataVO);
			String url = ipAddressValues.getTmsURL()+"tmsHome";
			response= restTemplate.exchange(url, HttpMethod.POST, httpEntity, PaginationObject.class);
			tenantsLists= response.getBody();
			logger.info("TmsController :: home() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: home() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return tenantsLists;
	}
	
	@RequestMapping(value = "/lmo", method = RequestMethod.GET)
	public String getLMOPage(@ModelAttribute(value="TenantForm") TenantVO tenantVO, Model model, HttpServletRequest request) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		Map<String,String> vsubstationList ;
		try {
			logger.info("TmsController :: getLMOPage() :: Start");
			String  tenantType =  (String) request.getSession(false).getAttribute("domain");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "lmo";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			TmsHelperDTO tmsHelperDTO = response.getBody();
			
			vsubstationList = tenantService.getSubstationList();
			model.addAttribute("regionList", tmsHelperDTO.getRegionsList());
			model.addAttribute("generalDocList", tmsHelperDTO.getGeneralDoc());
			model.addAttribute("poiDocList", tmsHelperDTO.getPoiDoc());
			model.addAttribute("poaDocList", tmsHelperDTO.getPoaDoc());
			model.addAttribute("tenantTypes", tmsHelperDTO.getTenantTpesList());
			model.addAttribute("viewStatus", "");
			model.addAttribute("stateList",tmsHelperDTO.getStateList());
			model.addAttribute("districtList",tmsHelperDTO.getDistrictList());
			model.addAttribute("apDistrictList",tmsHelperDTO.getApDistrictList());
			model.addAttribute("mandalList",tmsHelperDTO.getMandalList());
			model.addAttribute("villageList",tmsHelperDTO.getVillageList());
			model.addAttribute("cableList",tmsHelperDTO.getCableList());
			model.addAttribute("assetsList",tmsHelperDTO.getAssetsList());
			model.addAttribute("AssetsStatus","Create");
			model.addAttribute("AreasStatus","Create");
			model.addAttribute("vsubstationList",vsubstationList);
			model.addAttribute("tenantType", tenantType);
			logger.info("TmsController :: getLMOPage() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: getLMOPage() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			vsubstationList = null;
		}
		return "lmo";
	}
	
	@RequestMapping(value = "/getDistrictList", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> getDistrictList(@RequestParam("stateID") String stateID) {
		
		Map<String,String> districtList = null;
		TmsHelperDTO tmsHelperDTO;
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		try {
			logger.info("TmsController :: getDistrictList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getDistrictList?stateID="+stateID;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			districtList= tmsHelperDTO.getDistrictList();
			logger.info("TmsController :: getDistrictList() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: getDistrictList() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return districtList;
	}
	
	
	@RequestMapping(value = "/getMandalList", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> getMandalList(@RequestParam("stateID") String stateID, @RequestParam("districtID") String districtID) {
			
		Map<String,String> mandalList = null;
		TmsHelperDTO tmsHelperDTO;
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		try {
	      	logger.info("TmsController :: getMandalList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getMandalList?stateID="+stateID+"&districtID="+districtID;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO= response.getBody();
			mandalList=  tmsHelperDTO.getMandalList();
			logger.info("TmsController :: getMandalList() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getMandalList() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return mandalList;
	}
	
	@RequestMapping(value = "/getMandalAndSubsList", method = RequestMethod.GET)
	public @ResponseBody Map<String, Map<String, String>> getMandalAndSubsList(@RequestParam("stateID") String stateID, @RequestParam("districtID") String districtID) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		Map<String, Map<String,String>> MandalAndSubstationsList = null;
		try {
			logger.info("TmsController :: getMandalAndSubsList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getmandalAndSubsList?stateID="+stateID+"&districtID="+districtID;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			MandalAndSubstationsList =  tmsHelperDTO.getMandalAndSubstationsList();
			logger.info("TmsController :: getMandalAndSubsList() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getMandalAndSubsList() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return MandalAndSubstationsList;
	}
	
	@RequestMapping(value = "/getVillageList", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> getVillageList(@RequestParam("stateID") String stateID, @RequestParam("districtID") String districtID, @RequestParam("mandalID") String mandalID) {
	

		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		Map<String,String> villageList = null;
		try {
			logger.info("TmsController :: getVillageList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getVillageList?stateID="+stateID+"&districtID="+districtID+"&mandalID="+mandalID;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			villageList = tmsHelperDTO.getVillageList();
			logger.info("TmsController :: getVillageList() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getVillageList() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return villageList;
	}
	
	@RequestMapping(value = "/getMandalbySubstation", method = RequestMethod.GET)
	public @ResponseBody String getMandalbySubstation(@RequestParam("stateID") String stateID, @RequestParam("districtID") String districtID, @RequestParam("substationID") String substationID) {
	

		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		String mandalID = null;
		try {
			logger.info("TmsController :: getMandalbySubstation() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getMandalbySubstation?stateID="+stateID+"&districtID="+districtID+"&substationID="+substationID;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			mandalID = tmsHelperDTO.getMandalID();
			logger.info("TmsController :: getMandalbySubstation() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getMandalbySubstation() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return mandalID;
	}
	
	@RequestMapping(value = "/getareaLovs", method = RequestMethod.GET)
	public @ResponseBody Map<String,Map<String,String>> getareaLovs() {
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		Map<String,Map<String,String>> areaLovs = null;
		try {
			logger.info("TmsController :: getareaLovs() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getareaLovs";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			areaLovs = new LinkedHashMap<>();
			areaLovs.put("cable", tmsHelperDTO.getCableList());
			areaLovs.put("state", tmsHelperDTO.getStateList());
			logger.info("TmsController :: getareaLovs() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getareaLovs() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return areaLovs;
	}
	
	@RequestMapping(value = "/getassetsLovs", method = RequestMethod.GET)
	public @ResponseBody Map<String,Map<String,String>> getassetsLovs() {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		Map<String,String> cableList;
		Map<String,String> assetsList;
		Map<String,Map<String,String>> assetsLovs = null;
		try {
			logger.info("TmsController :: getassetsLovs() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getassetsLovs";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			cableList =  tmsHelperDTO.getCableList();
			assetsList =  tmsHelperDTO.getAssetsList();
			assetsLovs = new LinkedHashMap<>();
			assetsLovs.put("cable", cableList);
			assetsLovs.put("asset", assetsList);
			logger.info("TmsController :: getassetsLovs() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getassetsLovs() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
			cableList = null;
			assetsList = null;
		}
		return assetsLovs;
	}
	
	@RequestMapping(value = "/oltPortAlloc", method = RequestMethod.GET)
	public String oltPortAlloc(Model model, HttpServletRequest request) {
		
		try {
			logger.info("TmsController :: oltPortAlloc() :: Start");
		}catch (Exception e) {
			logger.error("TmsController :: oltPortAlloc() :: "+e);
			e.printStackTrace();
		}
		return "oltPortAlloc";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/portSplitter", method = RequestMethod.GET)
	public String portSplitter(HttpServletRequest request, Model uiModel,@RequestParam(value="returnVal", required = false) String returnVal) {
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<Object[]> list = null;
		String tenantcode = (String) request.getSession().getAttribute("tenantcode");
		try {
			logger.info("TmsController :: oltPortAlloc() :: Start");
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getOltSrlNosByTenantCode?tenantcode="+tenantcode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			list = response.getBody();	
		}catch (Exception e) {
			logger.error("TmsController :: oltPortAlloc() :: "+e);
			e.printStackTrace();
		}
		uiModel.addAttribute("oltSerNoList",list);
		uiModel.addAttribute("meassage",returnVal);
		
		return "portSplitter";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getOltPortsByTenantCodeAndOltSrNo", method = RequestMethod.GET)
	public @ResponseBody List<String> getOltPortsByTenantCodeAndOltSrNo(HttpServletRequest request, @RequestParam("oltSrNo") String oltSrNo) {
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<String> portList = null;
		String tenantcode = (String) request.getSession().getAttribute("tenantcode");
		try {
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getOltPortsByTenantCode?tenantcode="+tenantcode+"&oltSerno="+oltSrNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			portList = response.getBody();	
		}catch (Exception e) {
			logger.error("TmsController :: oltPortAlloc() :: "+e);
			e.printStackTrace();
		}
		
		return portList;
	}
	
	@RequestMapping(value = "/getL1SplitterTypeByOltport", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getL1SplitterByOltport(HttpServletRequest request, @RequestParam("oltPortNo") String oltPortNo
			, @RequestParam("oltSerNo") String oltSerNo) {
		String tenantcode = (String) request.getSession().getAttribute("tenantcode");
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelper = null;
		try {
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getL1SplitterByOltport?oltPortNo="+oltPortNo+"&oltSerNo="+oltSerNo+"&tenantCode="+tenantcode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelper = response.getBody();	
		}catch (Exception e) {
			logger.error("TmsController :: getL1SplitterByOltport() :: "+e);
			e.printStackTrace();
		}
		
		return tmsHelper.getMap();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getL1SplitterIdsByOltport", method = RequestMethod.GET)
	public @ResponseBody List<String> getL1SplitterIdsByOltport(HttpServletRequest request, @RequestParam("l1Size") String l1Size
			, @RequestParam("oltSerNo") String oltSerNo,@RequestParam("oltPortNo") String oltPortNo) {
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<String> list = null;
		String tenantcode = (String) request.getSession().getAttribute("tenantcode");
		try {
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getL1SplitterIdsByOltport?l1Size="+l1Size+"&oltSerNo="+oltSerNo+"&oltPortNo="+oltPortNo+"&tenantcode="+tenantcode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			list = response.getBody();	
		}catch (Exception e) {
			logger.error("TmsController :: getL1SplitterByOltport() :: "+e);
			e.printStackTrace();
		}
		
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getL2SplitterByL1Value", method = RequestMethod.GET)
	public @ResponseBody List<String> getL2SplitterByL1Value(HttpServletRequest request
			, @RequestParam("oltSerNo") String oltSerNo,@RequestParam("oltPortNo") String oltPortNo) {
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<String> list = null;
		String tenantcode = (String) request.getSession().getAttribute("tenantcode");
		try {
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getL2SplitterByL1Value?oltSerNo="+oltSerNo+"&oltPortNo="+oltPortNo+"&tenantcode="+tenantcode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			list = response.getBody();	
		}catch (Exception e) {
			logger.error("TmsController :: getL1SplitterByOltport() :: "+e);
			e.printStackTrace();
		}
		
		return list;
	}
	
	@RequestMapping(value = "/savePortSplitter", method = RequestMethod.POST)
	public String savePortSplitter(HttpServletRequest request,
			@RequestParam("oltSrno") String oltSrno,
			@RequestParam("oltportNo") String oltportNo,
			@RequestParam("l2PortSize") String l2PortSize,
			@RequestParam("l1PortId") String l1PortId,
			@RequestParam("l1SlotDtails") String l1SlotDtails ) {
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		String returnVal = null;
		String tenantcode = (String) request.getSession().getAttribute("tenantcode");
		try {
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "savePortSplitter?oltSrno="+oltSrno+"&oltportNo="+oltportNo+"&tenantcode="+tenantcode+"&l2PortSize="+l2PortSize+"&l1PortId="+l1PortId+"&l1SlotDtails="+l1SlotDtails;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			returnVal = response.getBody();	
		}catch (Exception e) {
			returnVal = e.getMessage();
			logger.error("TmsController :: getL1SplitterByOltport() :: "+e);
			e.printStackTrace();
		}
		
		return "redirect:/portSplitter?returnVal="+returnVal;
	}
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody List<TenantDTO> getTags(@RequestParam String tagName,  Model model, HttpServletRequest request) {
       
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<TenantDTO> data = null;
		try {
			logger.info("TmsController :: getareaLovs() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getApprovedLMOTenants?loginId="+request.getSession().getAttribute("loginID")+"&tagName="+tagName;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			data = response.getBody();	
		}catch (Exception e) {
			logger.error("TmsController :: oltPortAlloc() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return data;

	}
	
	@RequestMapping(value = "/getOLTLovs", method = RequestMethod.GET)
	public @ResponseBody Map<String,List<OLT>> getOLTLovs(@RequestParam String tenantId,  Model model, HttpServletRequest request) {

		HttpEntity<String> httpEntity = null;
		ResponseEntity<TmsHelperDTO> response =  null;
		TmsHelperDTO tmsHelperDTO = null;
		Map<String,List<OLT>> map = null;
		try {
			logger.info("TmsController :: getOLTLovs() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getLmoOlts?tenantId="+tenantId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();			
			map = new LinkedHashMap<>();
			map.put("oltLovs", tmsHelperDTO.getOltLovs());
			map.put("oltLmoList", tmsHelperDTO.getOltLmoList());
			logger.info("TmsController :: getOLTLovs() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getOLTLovs() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}	
		return map;

	}
	
	@RequestMapping(value = "/getOLTPortIds", method = RequestMethod.GET)
	public @ResponseBody Map<String,Map<String,String>> getOLTPortIds(@RequestParam("oltId") String oltId,  
			@RequestParam("tenantCode") String tenantCode, Model model, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		Map<String,Map<String,String>> map = null;
		try {
			logger.info("TmsController :: getOLTPortIds() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getOLTPortIds?oltId="+oltId+"&tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();			
			map = new LinkedHashMap<>();
			map.put("oltPortIdList", tmsHelperDTO.getOltPortIdList());
			logger.info("TmsController :: getOLTPortIds() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getOLTPortIds() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return map;

	}
	
	@RequestMapping(value = "/saveOltPorts", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseBody
	public String saveOltPorts(HttpServletRequest request, @RequestBody String jsonString) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		String result = "";
		try {
			logger.info("TmsController :: saveOltPorts() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(), jsonString);
			String url = ipAddressValues.getTmsURL() + "saveOltPorts";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			result = response.getBody();
			logger.info("TmsController :: saveOltPorts() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: saveOltPorts() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return result;
	}
	
	/*private List<Tag> simulateSearchResult(String tagName) {

		List<Tag> result = new ArrayList<Tag>();
		List<Tag> data = new ArrayList<Tag>();
		data.add(new Tag(1, "ruby"));
		data.add(new Tag(2, "rails"));
		data.add(new Tag(3, "c / c++"));
		data.add(new Tag(4, ".net"));
		data.add(new Tag(5, "python"));
		data.add(new Tag(6, "java"));
		data.add(new Tag(7, "javascript"));
		data.add(new Tag(8, "jscript"));
		// iterate a list and filter by tagName
		for (Tag tag : data) {
			if (tag.getTagName().contains(tagName)) {
				result.add(tag);
			}
		}

		return result;
	}*/
	
	/*@RequestMapping(value = "/createTenant", method = RequestMethod.POST)
	public String CreateTenant(@ModelAttribute(value="TenantForm") TenantVO tenantVO, Model model, HttpServletRequest request,
			@RequestParam(value = "tenantId", required = false) Integer tenantId) throws Exception {

		String type = null;
		TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
		String loginId = (String) request.getSession().getAttribute("loginID");
		try{
		String status = TenantValidation.tenantPageValidation(tenantVO);
		if (status.equalsIgnoreCase("true"))
		{
		
			tenantVO.setTenantId(tenantId);
			tenantVO.setLoginId(loginId);
			
			HttpEntity<TenantVO> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(), tenantVO);
			String url = ipAddressValues.getTmsURL() + "createTenant";
			ResponseEntity<TmsHelperDTO> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TmsHelperDTO.class);
			 tmsHelperDTO = response.getBody();
			
			type = tmsHelperDTO.getStatus();
			if (type.equalsIgnoreCase("Success"))
				model.addAttribute("message", "Tenant Created Successfully");
			else if (type.equalsIgnoreCase("Failure")) 
				model.addAttribute("error", "Failed to Create Tenant");
			else if(type.equalsIgnoreCase("duplicateTenentCode"))
				model.addAttribute("error", "Tenent already exists with tenent code : "+tenantVO.getTenantCode());
			else if(type.equalsIgnoreCase("duplicateTenentType"))
				model.addAttribute("error", "Tenent already exists with tenent type APSFL");
			
		}
		else
		{
			model.addAttribute("error", "Mandatory Fields are Required.");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Failed to Create Tenant");
		}
		List<TenantDTO> tenantsList = tmsHelperDTO.getTenantsLis();
		model.addAttribute("tenantsList", tenantsList);
		return "tmsHome";
	}
	
	@RequestMapping(value = "/showtenantDetails", method = RequestMethod.GET)
	public String getshowTenantDetailsPage(@ModelAttribute(value="TenantForm") TenantVO tenantVO, Model model, HttpServletRequest request,
			@RequestParam(value = "tenantId", required = false) Integer tenantId) {

		try {
			
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "showtenantDetails?tenantId="+tenantId;
			ResponseEntity<TmsHelperDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			TmsHelperDTO tmsHelperDTO = response.getBody();
			
			model.addAttribute("TenantForm", tmsHelperDTO.getTenantVo());
			
			model.addAttribute("portalassetsList", tmsHelperDTO.getPortalAssetsList());
			model.addAttribute("portalareasList", tmsHelperDTO.getPortalAreasList());
			
			model.addAttribute("regionList", tmsHelperDTO.getRegionsList());
			model.addAttribute("generalDocList", tmsHelperDTO.getGeneralDoc());
			model.addAttribute("poiDocList", tmsHelperDTO.getPoiDoc());
			model.addAttribute("poaDocList", tmsHelperDTO.getPoaDoc());
			model.addAttribute("tenantTypes", tmsHelperDTO.getTenantTpesList());
			model.addAttribute("viewStatus", "viewOnly");
			List<TenantDocuments> tenantDocuments = tmsHelperDTO.getTenantDocuments();
			for(TenantDocuments tdocs : tenantDocuments)
			{
				if(tdocs.getDocumentLovName().equalsIgnoreCase("GENERAL DOCUMENTS"))
					model.addAttribute("licenseDoc", tdocs.getDocumentLocationReference());
				else if(tdocs.getDocumentLovName().equalsIgnoreCase("POA DOCUMENTS"))
					model.addAttribute("addressDoc", tdocs.getDocumentLocationReference());
				else if(tdocs.getDocumentLovName().equalsIgnoreCase("POI DOCUMENTS"))
					model.addAttribute("IdDoc", tdocs.getDocumentLocationReference());
			}
		} catch (Exception e) {
			logger.error("TenantController::getshowTenantDetails() " + e);
			e.printStackTrace();
		}
		return "lmo";
	}*/
	
	@RequestMapping(value = "/createTenant", method = RequestMethod.POST)
	public String CreateTenant(@ModelAttribute(value="TenantForm") TenantVO tenantVO, Model model, HttpServletRequest request) throws Exception {

		String type = null;
		String loginId = (String) request.getSession().getAttribute("loginID");
		String tenantType = (String) request.getSession().getAttribute("domain");
		logger.info("tenantType...."+tenantType);
		
		Integer CreateOrEdit =  tenantVO.getTenantId();
		//List<TenantDTO> tenantsList = tenantService.findAllTenantsBYCreatedBy(loginId);
		String status = "";
		Map<String,String> apMandalList = new HashMap<>();
		Map<String,String> apVillageList = new HashMap<>();
		try {
			logger.info("TmsController:: CreteTenant() :: START ");
		status = TenantValidation.tenantPageValidation(tenantVO);
		
		logger.info("status...."+status);
		if (status.equalsIgnoreCase("true"))
		{
			type = tenantService.saveTenant(tenantVO, tenantVO.getTenantId());
			
			logger.info("type...."+type);
			if (type.equalsIgnoreCase("Success")){
				if(CreateOrEdit == null) {
					model.addAttribute("message", "Tenant Created Successfully");
				} else {
					model.addAttribute("message", "Tenant Modified Successfully");
					}
			}
			else if (type.equalsIgnoreCase("Failure")) 
				model.addAttribute("error", "Failed to Create Tenant");
			else if(type.equalsIgnoreCase("duplicateTenentCode"))
				model.addAttribute("error", "Tenant already exists with tenant code : "+tenantVO.getTenantCode());
			else if(type.equalsIgnoreCase("duplicateTenentType"))
				model.addAttribute("error", "Tenant already exists with tenent type APSFL");
		}
		else
		{
			model.addAttribute("error", "Mandatory Fields are Required.");
		}
		model.addAttribute("apMandalList", apMandalList);
		model.addAttribute("apVillageList", apVillageList);
		model.addAttribute("tenantType", tenantType);
		logger.info("TmsController:: CreteTenant() :: START ");
		}
		catch(Exception e)
		{
			logger.error("TmsController::CreateTenant() " + e);
			model.addAttribute("tenantType", tenantType);
		}
		finally{
			type = null;
			tenantVO = null;
			status = null;
			try{
				List<TenantDTO> tenantsList = tenantService.findAllTenantsBYCreatedBy(loginId);
				model.addAttribute("tenantsList", tenantsList);
			}catch(Exception e)
			{
				logger.error("TmsController::CreateTenant() " + e);
				model.addAttribute("error", "Failed to Create Tenant");
			}
		}
		return "tmsHome";
	}

	@RequestMapping(value = "/showtenantDetails", method = RequestMethod.POST)
	public String getshowTenantDetailsPage(@ModelAttribute(value="TenantForm") TenantVO tenantVO, Model model, HttpServletRequest request,
			@RequestParam(value = "tenantId", required = false) Integer tenantId) {

		Tenant tenant;
		Map<String,String> cableList;
		Map<String,String> assetsList;
		Map<String,String> stateList;
		List<TenantBussinessAreas> tbareasList;
		try {
			logger.info("TmsController :: getshowTenantDetailsPage() :: Start");
			String  tenantType =  (String) request.getSession(false).getAttribute("domain");
			tenant = tenantService.findByTenantId(tenantId);
			String tenantCode = tenant.getTenantCode();
			cableList = tenantService.getcableList();
			assetsList = tenantService.getassetsList();
			stateList = tenantService.getstateList();
			tbareasList = tenantService.getTbareasList(tenantCode);
			TenantVO tenantVO1 = tenantService.findByTenantId(tenantId, tenantCode, tenant.getPortalEnrllmentno());
			model.addAttribute("TenantForm", tenantVO1);
			model.addAttribute("portalassetsList", portalassetsservice.findByEnrollmentno(tenant.getTenantCode()));
			model.addAttribute("portalareasList", portalAreaService.findByEnrollmentno(tenant.getTenantCode()));
			model.addAttribute("regionList", regionService.findAllRegions());
			model.addAttribute("generalDocList", lovsService.findByLovsByLovName("GENERAL DOCUMENTS"));
			model.addAttribute("poiDocList", lovsService.findByLovsByLovName("POI DOCUMENTS"));
			model.addAttribute("poaDocList", lovsService.findByLovsByLovName("POA DOCUMENTS"));
			model.addAttribute("tenantTypes", lovsService.findByLovsByLovName("TENANT TYPES"));
			model.addAttribute("viewStatus", "Edit");
			model.addAttribute("cableList", cableList);
			model.addAttribute("assetsList", assetsList);
			model.addAttribute("AssetsStatus","Edit");
			model.addAttribute("AreasStatus","Edit");
			model.addAttribute("AreasStatus","Edit");
			model.addAttribute("stateList",stateList);
			model.addAttribute("tenantId",tenantId);
			model.addAttribute("apDistrictList",tenantService.getDistrictList("1"));
			model.addAttribute("apMandalList",getMandalAndSubsList("1",String.valueOf(tenantVO1.getPortal_districtid())).get("Mandals"));
			model.addAttribute("apVillageList",getVillageList("1", String.valueOf(tenantVO1.getPortal_districtid()), String.valueOf(tenantVO1.getPortal_mandalid())));
			model.addAttribute("tbareasList",tbareasList);
			model.addAttribute("tenantType", tenantType);
			
			List<TenantDocuments> tenantDocuments = tenantDocumentsService.findByTenantCode(tenantCode);
			for(TenantDocuments tdocs : tenantDocuments)
			{
				if(tdocs.getDocumentLovName().equalsIgnoreCase("GENERAL DOCUMENTS"))
					model.addAttribute("licenseDoc", tdocs.getDocumentLocationReference());
				else if(tdocs.getDocumentLovName().equalsIgnoreCase("POA DOCUMENTS"))
					model.addAttribute("addressDoc", tdocs.getDocumentLocationReference());
				else if(tdocs.getDocumentLovName().equalsIgnoreCase("POI DOCUMENTS"))
					model.addAttribute("IdDoc", tdocs.getDocumentLocationReference());
			}
			logger.info("TmsController :: getshowTenantDetailsPage() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: getshowTenantDetailsPage() :: " + e);
			e.printStackTrace();
		}finally {
			tenant = null;
			cableList = null;
			assetsList = null;
			stateList = null;
			tbareasList = null;
		}
		return "lmo";
	}
	
	@RequestMapping(value = "/routeMapImage", method = RequestMethod.GET)
	public void imageDisplay1(@RequestParam("id") long routemapid, HttpServletResponse response,HttpServletRequest request){	
		byte[] image = null;
		try(ServletOutputStream os = response.getOutputStream()) {
			logger.info("TmsController :: imageDisplay1() :: Start");
			image = portalassetsservice.findRouteMapImage(routemapid);
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		    os.write(image);
			logger.info("TmsController :: imageDisplay1() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: imageDisplay1() :: " + e);
			e.printStackTrace();
		}
		finally{
			image = null;
		}
	}
	
	@RequestMapping(value = "/capturedAssetsImage", method = RequestMethod.GET)
	public void imageDisplay2(@RequestParam("id") long routemapid, HttpServletResponse response,HttpServletRequest request) throws IOException {
	    byte[] image = null;
		try(ServletOutputStream os = response.getOutputStream()) {
			logger.info("TmsController :: imageDisplay2() :: Start");
			image = portalassetsservice.findCapturedAssetsImage(routemapid);
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		    os.write(image);
		    logger.info("TmsController :: imageDisplay2() :: End");
		} catch (Exception e) {
			logger.error("TenantController::imageDisplay2() " + e);
			e.printStackTrace();
		}
		finally{
			image = null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/approveTenant", method = RequestMethod.POST)
	public String approveTenantAgreement(Model model, HttpServletRequest request,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "tenantId", required = false) Integer tenantId) {
		String message = "";
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		ResponseEntity<ArrayList> response1;
		List<TenantDTO> tenantsList;
		try {
			logger.info("TmsController :: approveTenantAgreement() :: Start");
			String loginId = (String) request.getSession().getAttribute("loginID");
			String tenantType = (String) request.getSession().getAttribute("domain");
			httpEntity= ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "approveTenant?tenantId="+tenantId+"&action="+action+"&loginId="+loginId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO= response.getBody();
			if(tmsHelperDTO.getStatus().equalsIgnoreCase("Success"))
			{
				if(action.equalsIgnoreCase("accept"))
					message = "Tenent Approved Successfully";
				else if(action.equalsIgnoreCase("reject"))
					message = "Tenent Rejected Successfully";
				model.addAttribute("message", message);
			}
			else if(tmsHelperDTO.getStatus().equalsIgnoreCase("UserCreationFail"))
			{
				message = "Failed to create User for Tenent";
				model.addAttribute("error", message);
			}
			String url1 = ipAddressValues.getTmsURL() + "?loginId="+request.getSession().getAttribute("loginID");
			response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity, ArrayList.class);
			tenantsList = response1.getBody();

			model.addAttribute("tenantsList", tenantsList);
			model.addAttribute("tenantType", tenantType);
			logger.info("TmsController :: approveTenantAgreement() :: End");
		} catch (Exception ex) {
			logger.info("TmsController :: approveTenantAgreement() :: "+ex);
			ex.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
			response1 = null;
			tenantsList = null;
		}

		return "tmsHome";
	}
	
	@RequestMapping(value = "/revenueSharing", method = RequestMethod.GET)
	public ModelAndView revenueSharing(@ModelAttribute(value="revenueSharingForm") RsTemplateDetailsDTO rsTempDetailsDTO, Model model, HttpServletRequest request) {	
		logger.info(":::In revenueSharing Page:::");
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		Map<String,String> templatesLov;
		try {
			logger.info("TmsController :: revenueSharing() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "revenueSharing";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			
			templatesLov = tmsHelperDTO.getTemplatesLov();
			model.addAttribute("templatesLov",templatesLov);
			model.addAttribute("templateTypesLov",tmsHelperDTO.getTemplateTypesLov());
			model.addAttribute("chargeCodesList",tmsHelperDTO.getChargeCodeslist());
			logger.info("TmsController :: revenueSharing() :: End");
		} catch (Exception exception) {
			logger.error("TmsController :: revenueSharing() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
			templatesLov = null;
		}

		return new ModelAndView("showRevenueSharing");
	}
	
	@RequestMapping(value = "/getRevenueSharingLovs", method = RequestMethod.GET)
	public @ResponseBody Map<String, Map<String,String>> getRevenueSharingLovs(@RequestParam("tempCode") String tempCode) {
		logger.info(":::In getRevenueSharingLovs :::");
		Map<String, Map<String,String>> maps = new LinkedHashMap<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		try {
			logger.info("TmsController :: getRevenueSharingLovs() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getRevenueSharingLovs?tempCode="+tempCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			maps.put("tenantTypeLovs", tmsHelperDTO.getTenantTypeLovs());
			maps.put("regionLovs", tmsHelperDTO.getRegionLovs());
			maps.put("templatesLov", tmsHelperDTO.getTemplatesLov());
			maps.put("tenantTypeVals", tmsHelperDTO.getTenantTypeVals());
			logger.info("TmsController :: getRevenueSharingLovs() :: End");
		} catch (Exception exception) {
			logger.error("TmsController :: getRevenueSharingLovs() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return maps;
	}
	
	
	@RequestMapping(value = "/saveTemplateMaster", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseBody
	public String saveTemplateMaster(HttpServletRequest request, @RequestBody RevenueSharingTemplateMaster rsTemplateMaster) {
		String returnText="";
		HttpEntity<RevenueSharingTemplateMaster> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		try {
			logger.info("TmsController :: saveTemplateMaster() :: Start");
			 httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),rsTemplateMaster);
			String url = ipAddressValues.getTmsURL() + "saveTemplateMaster";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			returnText = tmsHelperDTO.getStatus();
			logger.info("TmsController :: saveTemplateMaster() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: saveTemplateMaster() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return returnText;
	}
	
	
	@RequestMapping(value = "/saveTemplateDetails", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseBody
	public String saveTemplateDetails(HttpServletRequest request, @RequestBody RsTemplateDetailsDTO rsTemplateDetailsDTO) {
		
		String returnText="";
		HttpEntity<RsTemplateDetailsDTO> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		try {
			logger.info("TmsController :: saveTemplateDetails() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),rsTemplateDetailsDTO);
			String url = ipAddressValues.getTmsURL() + "saveTemplateDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			returnText = tmsHelperDTO.getStatus();
			logger.info("TmsController :: saveTemplateDetails() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: saveTemplateDetails() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return returnText;
	}
	
	
	@RequestMapping(value = "/getTemplateValues", method = RequestMethod.GET)
	public @ResponseBody RevenueSharingTemplateMaster getTemplateValues(@RequestParam("tempCode") String tempCode) {
		logger.info(":::In getTemplateValues :::");
		RevenueSharingTemplateMaster rsTempMaster = null;
		
		HttpEntity<String> httpEntity;
		ResponseEntity<RevenueSharingTemplateMaster> response;
		try {
			logger.info("TmsController :: getTemplateValues() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getTemplateValues?tempCode="+tempCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, RevenueSharingTemplateMaster.class);
			rsTempMaster = response.getBody();
			logger.info("TmsController :: getTemplateValues() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: getTemplateValues() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
			return rsTempMaster;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getRegTempPercList", method = RequestMethod.GET)
	public @ResponseBody List<RsTemplateDetailsListDTO> getRegTempPercList(@RequestParam("tempCode") String tempCode, @RequestParam("region") String region) {
		
		logger.info(":::In getRegTempPercList :::");
		List<RsTemplateDetailsListDTO> rsTemplateList = null;
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("TmsController :: getRegTempPercList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getRegTempPercList?tempCode="+tempCode+"&region="+region;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			rsTemplateList = response.getBody();
			logger.info("TmsController :: getRegTempPercList() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: getRegTempPercList() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
			return rsTemplateList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/createProductAgreement", method = RequestMethod.GET)
	public String createProductAgreement(Model model, HttpServletRequest request) {
		
		List<ProductInfoVo> ProductInfoVoList;
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		String tenantCode = request.getSession().getAttribute("tenantcode").toString();
		try {
			logger.info("TmsController :: createProductAgreement() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "pcs/getAllApsflPackages?tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			ProductInfoVoList = response.getBody();
			model.addAttribute("productsList", ProductInfoVoList);
			logger.info("TmsController :: createProductAgreement() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: createProductAgreement() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			ProductInfoVoList = null;
		}
			return "productAgreement";
	}
	

	@RequestMapping(value = "/saveProductAgreementURL", method = RequestMethod.POST)
	@ResponseBody
	public String saveProductAgreement(@RequestBody List<ProductAgreementVO> productAgreementVO, HttpServletRequest request) {
	
       String returnText="";
       HttpEntity<List<ProductAgreementVO>> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		try {
			logger.info("TmsController :: saveProductAgreement() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),productAgreementVO);
			String url = ipAddressValues.getTmsURL() + "saveProductAgreementURL";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			returnText = tmsHelperDTO.getStatus();
			logger.info("TmsController :: saveProductAgreement() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: saveProductAgreement() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return returnText;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getAllServicesByProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ServiceDetailsDTO> getAllServicesByProduct(@RequestParam(value = "prodCode") String prodcode,
			@RequestParam(value = "tenantCode") String tenantcode,
			@RequestParam(value = "effectiveFrom") String effFrom, Model uiModel, HttpServletRequest request)
			throws ParseException {

		List<ServiceDetailsDTO> serviceVOList = new ArrayList<ServiceDetailsDTO>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("TmsController :: getAllServicesByProduct() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "pcs/getAllServicesByProduct?prodCode="+prodcode+"&tenantCode="+tenantcode+"&effectiveFrom="+effFrom;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			serviceVOList = response.getBody();
			logger.info("TmsController :: getAllServicesByProduct() :: End");
			return serviceVOList;
		} catch (Exception ex) {
			logger.error("TmsController :: saveProductAgreement() :: "+ex);
			return serviceVOList;
		}finally {
			httpEntity = null;
			response = null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getTemplatesByCount", method = RequestMethod.GET)
	@ResponseBody
	public List<RevenueSharingTemplateMaster>  getTemplatesByCount(@RequestParam("count") Integer count, HttpServletRequest request) {
		
		List<RevenueSharingTemplateMaster> rsTmplMaslist = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("TmsController :: getTemplatesByCount() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getTemplatesByCount?count="+count;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			rsTmplMaslist = response.getBody();
			logger.info("TmsController :: getTemplatesByCount() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: getTemplatesByCount() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
			return rsTmplMaslist;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllTenantTypeByTemplateCode", method = RequestMethod.GET)
	@ResponseBody
	public List<RevenueSharingTemplateDetails>  getAllTenantTypeByTemplateCode(@RequestParam("tmplCode") String tmplCode,
			@RequestParam("region") String region) {
		
		List<RevenueSharingTemplateDetails> revenueSharingTemplateDetails = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("TmsController :: getAllTenantTypeByTemplateCode() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getAllTenantTypeByTemplateCode?tmplCode="+tmplCode+"&region="+region;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			revenueSharingTemplateDetails = response.getBody();
			logger.info("TmsController :: getAllTenantTypeByTemplateCode() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: getAllTenantTypeByTemplateCode() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
			return revenueSharingTemplateDetails;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getAllTemplateRegions", method = RequestMethod.GET)
	@ResponseBody
	public List<Object[]>  getAllTemplateRegions(@RequestParam("tmplCode") String tmplCode) {
		
		List<Object[]>  templRegionList = null;
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("TmsController :: getAllTemplateRegions() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getAllTemplateRegions?tmplCode="+tmplCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			templRegionList = response.getBody();
			logger.info("TmsController :: getAllTemplateRegions() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: getAllTemplateRegions() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
			return templRegionList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllTenantsByTemplCode", method = RequestMethod.GET)
	@ResponseBody
	public List<TemplatePartnerVO>  getAllTenantsByTemplCode(@RequestParam("tmplCode") String tmplCode) {
		
		List<TemplatePartnerVO>  templPartList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("TmsController :: getAllTenantsByTemplCode() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getAllTenantsByTemplCode?tmplCode="+tmplCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			templPartList = response.getBody();
			logger.info("TmsController :: getAllTenantsByTemplCode() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: getAllTenantsByTemplCode() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return templPartList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllSimilarTemplatesByTemplCode", method = RequestMethod.GET)
	@ResponseBody
	public List<RevenueSharingTemplateMaster> getAllSimilarTemplatesByTemplCode(@RequestParam(value="templCode") String templCode) {
		
		List<RevenueSharingTemplateMaster>  templPartList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("TmsController :: getAllSimilarTemplatesByTemplCode() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getAllSimilarTemplatesByTemplCode?templCode="+templCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			templPartList = response.getBody();
			logger.info("TmsController :: getAllSimilarTemplatesByTemplCode() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: getAllSimilarTemplatesByTemplCode() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
			return templPartList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewAllPkgAgree", method = RequestMethod.GET)
	public String getviewAllPkgAgree(Model model, HttpServletRequest request) {
		String  tenantcode =  (String) request.getSession(false).getAttribute("tenantcode");
		String  tenantType =  (String) request.getSession(false).getAttribute("domain");
			List<ProductAgreementVO> prodAgreeList = new ArrayList<>();
			HttpEntity<String> httpEntity;
			ResponseEntity<ArrayList> response;
			try {
				logger.info("TmsController :: getviewAllPkgAgree() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
				String url = ipAddressValues.getTmsURL() + "viewAllPkgAgree?tenantcode="+tenantcode+"&tenantType="+tenantType;
				response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
				prodAgreeList = response.getBody();
				model.addAttribute("allpkgagreeList", prodAgreeList);
				logger.info("TmsController :: getviewAllPkgAgree() :: End");
			}catch (Exception exception) {
				logger.error("TmsController :: getviewAllPkgAgree() :: "+exception);
				exception.printStackTrace();
			}finally {
				httpEntity = null;
				response = null;
				prodAgreeList = null;
			}
		    return "viewAllPkgAgree";	
			
	}

	// BlackPage
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createBlockList", method = RequestMethod.GET)
	public String createBlockList(Model model, HttpServletRequest request) {
		
		logger.info("CustomerController :: searchCafDetailsForBlockList()");
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<BlackListInfoVO> blockedList = new ArrayList<>();
		try {
			logger.info("TmsController :: createBlockList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getBlockListedDetails?loginID=" + request.getSession().getAttribute("loginID");
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			blockedList = response.getBody();
			model.addAttribute("blockedList", blockedList);
			logger.info("TmsController :: createBlockList() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: createBlockList() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			blockedList = null;
		}
		return "createBlockList";
	}

	@RequestMapping(value = "/searchCafDetailsForBlockList", method = RequestMethod.POST)
	public String searchCafDetailsForBlockList(Model model, @RequestParam(value = "mobileNo" , required = false) String mobileNo, 
			@RequestParam(value = "stbNo" , required = false) String stbNo, @RequestParam(value = "stbMac" , required = false) String stbMac, HttpServletRequest request) {
		HttpEntity<String> httpEntity;
		ResponseEntity<BlackListResultVO> response;
		BlackListResultVO blackListResultVO = new BlackListResultVO();
		String  tenantcode =  (String) request.getSession(false).getAttribute("tenantcode");
		String  tenantType =  (String) request.getSession(false).getAttribute("domain");
		List<CafsForBlockListVO> cafList = new ArrayList<>();
		List<BlackListInfoVO> blockedList = new ArrayList<>();
		try {
			logger.info("TmsController :: searchCafDetailsForBlockList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "searchCafDetailsForBlockList?mobileNo=" + mobileNo+ "&stbNo="+stbNo+"&tenantcode="+tenantcode+"&tenantType="+tenantType+"&stbMac="+stbMac+"&loginID=" + request.getSession().getAttribute("loginID");;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, BlackListResultVO.class);
			blackListResultVO = response.getBody();
			cafList = blackListResultVO.getCafsList();
			blockedList = blackListResultVO.getBlackList();
			if (cafList.size() > 0) {
				model.addAttribute("Smsg", "");
			} else {
				model.addAttribute("Emsg", "No records found for the given Number..");
			}
			model.addAttribute("cafList", cafList);
			model.addAttribute("blockedList", blockedList);
			model.addAttribute("searchTypeMob", ""+mobileNo+"");
			model.addAttribute("searchTypeStb", ""+stbNo+"");
			model.addAttribute("searchTypeStbMac", ""+stbMac+"");
			logger.info("TmsController :: searchCafDetailsForBlockList() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: searchCafDetailsForBlockList() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			cafList = null;
		}
		return "createBlockList";
	}

	// BlackCustomer
	@RequestMapping(value = "/BlackCustomer", method = RequestMethod.POST)
	public String BlackCustomer(Model model, BlackListInfoVO blackListInfoVO, HttpServletRequest request) {
		logger.info("TenantController :: BlackCustomer()");
		String createdby = (String) request.getSession().getAttribute("loginID");
		String ipad = request.getRemoteAddr();
		blackListInfoVO.setCreatedby(createdby);
		blackListInfoVO.setCreatedipaddr(ipad);
		HttpEntity<BlackListInfoVO> httpEntity;
		ResponseEntity<BlackListResultVO> response;
		BlackListResultVO blackListResultVO = new BlackListResultVO();
		List<BlackListInfoVO> blockedList = new ArrayList<>();
		
		try {
			logger.info("TmsController :: BlackCustomer() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(), blackListInfoVO);
			String url = ipAddressValues.getTmsURL() + "BlackCustomer";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, BlackListResultVO.class);
			blackListResultVO = response.getBody();
			if(blackListResultVO.getMsgResult().equalsIgnoreCase("success")){
				model.addAttribute("Smsg", "Request for Blacklisting sent for Approval ");
				
			} else if(blackListResultVO.getMsgResult().equalsIgnoreCase("exist")) { 
				model.addAttribute("Emsg", "Blacklisting Request for selected is already exists");
				
			} else if(blackListResultVO.getMsgResult().equalsIgnoreCase("blocked"))	{ 
				model.addAttribute("Emsg", "Blacklisting Request for selected is already Blacked");
				
			} else {
				model.addAttribute("Emsg", "Request for Blacklisting not completed ");
			}
			blockedList = blackListResultVO.getBlackList(); 
			model.addAttribute("blockedList", blockedList);
			logger.info("TmsController :: BlackCustomer() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: BlackCustomer() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			blackListResultVO = null;
			blockedList = null;
		}
		return "createBlockList";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/approveBlackList", method = RequestMethod.GET)
	public String viewDataToapproveBlockList(Model model, HttpServletRequest request) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<BlackListInfoVO> blockedList = new ArrayList<>();
		try {
			logger.info("TmsController :: viewDataToapproveBlockList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "viewDataToapproveBlockList?loginID=" + request.getSession().getAttribute("loginID");
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			blockedList = response.getBody();
			model.addAttribute("blockedList", blockedList);
			logger.info("TmsController :: viewDataToapproveBlockList() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: viewDataToapproveBlockList() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			blockedList = null;
		}
		return "approveBlackList";
	}
	
	@RequestMapping(value = "/MakeApproveBlackList", method = RequestMethod.POST)
	public String MakeApproveBlackList(Model model, BlackListInfoVO blackListInfoVO,  HttpServletRequest request) {
		
		HttpEntity<String> httpEntity;
		BlackListResultVO blackListResultVO = new BlackListResultVO();
		HttpEntity<String> httpEntity1;
		try {
			logger.info("TmsController :: MakeApproveBlackList() :: Start");
			httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String url1 = ipAddressValues.getComURL() + "blacklistSubscribers?nwsubscode="+blackListInfoVO.getNwsubscode();
			ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, String.class);
			String msg = response1.getBody();
			//String msg = "accepted";
			logger.info("TmsController :: MakeApproveBlackList() :: Provisioning Status:: "+msg);
			if(msg.equalsIgnoreCase("accepted")) {
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
				String url = ipAddressValues.getTmsURL() + "MakeApproveBlackList?loginid="+ request.getSession().getAttribute("loginID")+ 
							"&effectivefrom="+blackListInfoVO.getEffectivefrom()+ 
							 "&nwsubscode="+blackListInfoVO.getNwsubscode()+"&stbcafno="+blackListInfoVO.getStbcafno();
				ResponseEntity<BlackListResultVO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, BlackListResultVO.class);
				blackListResultVO = response.getBody();
				if(blackListResultVO.getMsgResult().equalsIgnoreCase("success"))
				{
					model.addAttribute("Smsg", "Successfully Blacked");
				}else {
					model.addAttribute("Emsg", "Blacking not completed");
				}
				//blockedList = blackListResultVO.getBlackList(); 
				model.addAttribute("blockedList", blackListResultVO.getBlackList());
			}
			else { 
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
				String url = ipAddressValues.getTmsURL() + "blackListCorpusFail?loginid="+ request.getSession().getAttribute("loginID");
				ResponseEntity<BlackListResultVO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, BlackListResultVO.class);
				blackListResultVO = response.getBody();
				model.addAttribute("blockedList", blackListResultVO.getBlackList());
				model.addAttribute("Emsg", "Blacking not completed Because Corpus failed");
			}
			logger.info("TmsController :: MakeApproveBlackList() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: MakeApproveBlackList() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			blackListResultVO = null;
			httpEntity1 = null;
		}
		return "approveBlackList";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/RejectBlackList", method = RequestMethod.POST)
	public String RejectBlackList(Model model, BlackListInfoVO blackListInfoVO,  HttpServletRequest request) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<BlackListInfoVO> blockedList = new ArrayList<>();
		try {
			logger.info("TmsController :: RejectBlackList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "RejectBlackList?loginid="+ request.getSession().getAttribute("loginID")+ 
							"&effectivefrom="+blackListInfoVO.getEffectivefrom()+ 
							 "&nwsubscode="+blackListInfoVO.getNwsubscode()+"&stbcafno="+blackListInfoVO.getStbcafno();
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			blockedList = response.getBody();
			model.addAttribute("blockedList", blockedList);
			model.addAttribute("Smsg", "Successfully Rejected");
			logger.info("TmsController :: RejectBlackList() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: RejectBlackList() :: " + e);
			e.printStackTrace();
			model.addAttribute("Emsg", "Not Rejected");
		}finally {
			httpEntity = null;
			response = null;
			blockedList = null;
		}
		return "approveBlackList";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mspChannelUpload", method = RequestMethod.POST)
	public String mspChannelUpload(@RequestParam(value="mspFile") MultipartFile file, Model model, HttpServletRequest request) {
		TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
		TmsHelperDTO tmsHelperDTO1 = new TmsHelperDTO();
			ResponseEntity<TmsHelperDTO> response;
			HttpEntity<TmsHelperDTO> httpEntity;
			try {
					if (file != null && !file.isEmpty()) {
						logger.info("TmsController :: mspChannelUpload() :: Start");
						String name = file.getOriginalFilename();
						String ext = FilenameUtils.getExtension(name);
						Long size= file.getSize();
						if (ext != null && (ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("xls")) && ext != "") {
							List<MspChnlsStg> mspChnlsStgList = ReadXlsExcelFileForMspChannelUpload.extractExcelFile(file,ext);
							tmsHelperDTO.setMspChnlsStgList(mspChnlsStgList);
							tmsHelperDTO.setFileName(name);
							tmsHelperDTO.setFileSize(String.valueOf(size));
							Map<String, String> recordDetails = ReadXlsFileForCafs.getNumberOfColsAndRows(file,ext);
							tmsHelperDTO.setNoOfCols(recordDetails.get("noOfCols"));
							tmsHelperDTO.setNoOfRows(recordDetails.get("noOfRows"));
						  	} 
						}
						httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(),ipAddressValues.getTmsPwd(), tmsHelperDTO);
						String url = ipAddressValues.getTmsURL() + "mspChannelUpload?loginID=" + request.getSession().getAttribute("loginID");
						response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TmsHelperDTO.class);
						tmsHelperDTO1 = response.getBody();
						model.addAttribute("message", tmsHelperDTO1.getMessage());
						model.addAttribute("channelList", tmsHelperDTO1.getUploadHistory());
					
				} catch (Exception e) {
					logger.error("TmsController :: mspChannelUpload() :: " + e);
					tmsHelperDTO.setStatus(e.getMessage());
				} finally {
					httpEntity = null;
					response = null;
					}
			  return "mspChannelUpload";
			
			}
	
	

	@RequestMapping(value = "/mspLocalChannels", method = RequestMethod.GET)
	public String viewMspChannels(Model model, HttpServletRequest request) {
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<TmsHelperDTO> response;
		try {
			logger.info("TmsController :: showCustomersPage() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(),ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "viewMspChannels";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			TmsHelperDTO tmsHelperDTO = response.getBody();
			model.addAttribute("channelList", tmsHelperDTO.getUploadHistory());
			logger.info("TmsController :: viewMspChannels() :: End");
		} catch (Exception e) {
			logger.info("TmsController :: viewMspChannels() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return "mspChannelUpload";
	}
     
	
	@RequestMapping(value = "/mspChannelsErrorDownload",  method = RequestMethod.POST)
	public void mspChannelsErrorDownload(Model model, HttpServletRequest request,	
			@RequestParam(value = "uploadid", required = false) String uploadid,
			@RequestParam(value = "fileName", required = false) String fileName,
			HttpServletResponse httpResponse) throws IOException, ParseException {
		
		
		HttpEntity<String> httpEntity;
		String url = "";
		TmsHelperDTO tmsHelperDTO=new TmsHelperDTO();
		ResponseEntity<TmsHelperDTO> response;
		try {
			String ext = FilenameUtils.getExtension(fileName);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getTmsURL() + "mspChannelsErrorDownload?uploadid="+uploadid;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			//ObjectMapper mapper = new ObjectMapper();
			//Gson gson = new Gson();
			//List<MspChnlsStg> channelErrorList = mapper.readValue(gson.toJson(tmsHelperDTO.getMspChnlsStgList()),TypeFactory.defaultInstance().constructCollectionType(List.class, TmsHelperDTO.class));
			ReadXlsExcelFileForMspChannelUpload.channelUplaodErrorDownloadExcel(tmsHelperDTO.getMspChnlsStgList(),fileName,ext,httpResponse);
		} catch (Exception e) {
			logger.info("ComsController :: cafBulkUploadErrors() :: " + e);
			e.printStackTrace();
		} finally {
			
			httpEntity = null;
			url = null;
			response = null;
		}
	}
	
/*	@RequestMapping(value = "/viewMspChannelByLoginId", method = RequestMethod.GET)
	public String viewMspChannelByLoginId(Model model, HttpServletRequest request) throws IOException {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO=new TmsHelperDTO();
		try {
		    logger.info("TmsController :: viewMspChannelByLoginId() :: Start");
		    httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "viewMspChannelByLoginId?loginId="+request.getSession().getAttribute("loginID");
			response= restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO= response.getBody();
			model.addAttribute("packageList", tmsHelperDTO.getAdditionalService());
			model.addAttribute("channelsList", tmsHelperDTO.getMspChnlsStgList());
			logger.info("TmsController :: home() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: home() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return "channeldetails";
	}*/

	/*@RequestMapping(value = "/getChannelNameBypkgcode", method = RequestMethod.GET)
	public String getChannelNameBypkgcode(@RequestParam(value = "prodcode") String prodcode,
			@RequestParam(value = "featurescode") String featurescode,
			@RequestParam(value = "effectiveDate") String effFrom, Model model) throws ParseException {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
		List<Object[]> chnlsList = new ArrayList<>();
		try {
			 logger.info("TmsController :: getChannelNameBypkgcode() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
	
			String url = ipAddressValues.getTmsURL() + "getChannelNameBypkgcode?prodcode=" + prodcode + "&featurescode=" + featurescode
					+ "&effectiveDate=" + effFrom;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			model.addAttribute("product", tmsHelperDTO.getChnlCodeName());
			model.addAttribute("channelsList", chnlsList);
			logger.info("TmsController :: getChannelNameBypkgcode() :: End");
		}catch (Exception e) {
			logger.error("TmsController :: getChannelNameBypkgcode() :: "+e);
			e.printStackTrace();
		}finally{
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return "mspChnlsInfo";
	}*/
	
	@RequestMapping(value = "/downloadExcelFileForChnlUpload", method = RequestMethod.GET)
	public void downloadExcelFileForChnlUpload(HttpServletRequest request, HttpServletResponse response){

		File file = null;
		file = new File(ipAddressValues.getChnlUploadExcelPath());
		try(InputStream inputStream = new FileInputStream(file);
				BufferedInputStream bIs = new BufferedInputStream(inputStream);
				ServletOutputStream os = response.getOutputStream()){
			
			if (file != null) {
				String mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
				response.setContentLength((int) file.length());
				FileCopyUtils.copy(bIs, os);
			} 
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		finally {
			file = null;
		}
		
	}
	
	
	@RequestMapping(value = "/generateRevSharing",  method = RequestMethod.GET)
	public String executeRevSharing() throws IOException, ParseException {
		
		return "processRevenueSharing";
	}
	
	@RequestMapping(value = "/executeRevSharing",  method = RequestMethod.POST)
	public String executeRevSharing(Model model, HttpServletRequest request,	
			@RequestParam(value = "revDate", required = false) String revDate,
			HttpServletResponse httpResponse) throws IOException, ParseException {
		
		HttpEntity<String> httpEntity;
		String url = "";
		TmsHelperDTO tmsHelperDTO=new TmsHelperDTO();
		ResponseEntity<TmsHelperDTO> response;
		try {
			revDate = DateUtill.getDateInyyyyMM(revDate);
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "executeRevSharing?revDate="+revDate;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			model.addAttribute("message", tmsHelperDTO.getMessage());
			model.addAttribute("list", tmsHelperDTO.getRevenueSharingDTOList());
			model.addAttribute("revDate", revDate);
		} catch (Exception e) {
			logger.info("TmsController :: revSharingExcelDownload() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return "processRevenueSharing";
	}
	
	@RequestMapping(value = "/achFileGenerate",  method = RequestMethod.POST)
	public String achFileGenerate(Model model,	
			@RequestParam(value = "revDate", required = false) String revDate) throws IOException, ParseException {
		
		
		HttpEntity<String> httpEntity;
		String url = "";
		TmsHelperDTO tmsHelperDTO=new TmsHelperDTO();
		ResponseEntity<TmsHelperDTO> response;
		try {
			//revDate = DateUtill.getDateInyyyyMM(revDate);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "achFileGenerate?revDate="+revDate;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			model.addAttribute("message", tmsHelperDTO.getMessage());
			model.addAttribute("list", tmsHelperDTO.getRevenueSharingDTOList());
			model.addAttribute("revDate", revDate);
		} catch (Exception e) {
			logger.info("TmsController :: revSharingExcelDownload() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return "processRevenueSharing";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/oltPortAllocation", method = RequestMethod.GET)
	public String oltPortAllocation(Model model, HttpServletRequest request) {
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		String url = "";
		try {
			logger.info("TmsController :: oltPortAllocation() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "oltPortAllocation?tenantCode=" + tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			List<Districts> districtsList = response.getBody();
			model.addAttribute("districtList", districtsList);
			logger.info("TmsController :: oltPortAllocation() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: oltPortAllocation() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return "oltPortAlloc";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getMandalsByDistId")
	@ResponseBody
	public List<Mandals> getMandalsByDistrictId(Model model,
			@RequestParam(value = "districtId", required = false) Integer districtId) {
		List<Mandals> mandalsList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("TmsController :: getMandalsByDistrictId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "getMandalsByDistrictId?districtId=" + districtId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			mandalsList = response.getBody();
			logger.info("TmsController :: getMandalsByDistrictId() :: End");
		} catch (Exception e) {
			logger.info("TmsController :: getMandalsByDistrictId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return mandalsList;
	}

	@RequestMapping(value = "/searchOltsDetails", method = RequestMethod.POST)
	public String searchOltsDetails(Model model,
			@RequestParam(value = "districtuid", required = false) Integer districtuid,
			@RequestParam(value = "mandalslno", required = false) Integer mandalslno, HttpServletRequest request) {

		TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<TmsHelperDTO> response;
		try {
			
			
			logger.info("TmsController :: searchOltsDetails() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "searchOltsDetails?districtuid=" + districtuid + "&mandalslno="
					+ mandalslno;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			
			if (tmsHelperDTO.getOltList().size() > 0) {
				model.addAttribute("oltList", tmsHelperDTO.getOltList());
				model.addAttribute("message", "");
			} else {
				model.addAttribute("message", "No records found for this search criteria.");
			}
			model.addAttribute("districtList", tmsHelperDTO.getDistrictsList());
			model.addAttribute("districtuid", districtuid);
			model.addAttribute("mandalList", tmsHelperDTO.getMandalsList());
			model.addAttribute("mandalslno", mandalslno);
			logger.info("TmsController :: searchCafProductsDetails() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: searchCafProductsDetails() :: " + e);
			e.printStackTrace();
		} finally {
			tmsHelperDTO = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return "oltPortAlloc";
	}

	@RequestMapping(value = "/getOltDetails", method = RequestMethod.GET)
	@ResponseBody
	public TenantVO getOltDetails(@RequestParam(value = "suid", required = false) String suid,
			@RequestParam(value = "oltno", required = false) String oltno) {

		TenantVO tenantVO = new TenantVO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<TenantVO> response;
		try {
			logger.info("TmsController :: getOltDetails() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "getOltDetails?suid=" + suid + "&oltno=" + oltno;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TenantVO.class);
			tenantVO = response.getBody();
			logger.info("TmsController :: getOltDetails() :: End");
		} catch (Exception e) {
			logger.info("TmsController :: getOltDetails() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
		}
		return tenantVO;
	}

	@RequestMapping(value = "/savePortDetailsList", method = RequestMethod.POST)
	@ResponseBody
	public String savePortDetailsList(@RequestBody List<OLTPortDetails> oLTPortDetails, HttpServletRequest request) {

		String returnText = "";
		HttpEntity<List<OLTPortDetails>> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		TmsHelperDTO tmsHelperDTO;
		try {
			String loginID = (String) request.getSession(false).getAttribute("loginID");
			logger.info("TmsController :: savePortDetailsList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),
					oLTPortDetails);
			String url = ipAddressValues.getTmsURL() + "savePortDetailsList?"+"loginID="+ loginID;
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			returnText = tmsHelperDTO.getStatus();
			logger.info("TmsController :: savePortDetailsList() :: End");
		} catch (Exception exception) {
			logger.error("TmsController :: savePortDetailsList() :: " + exception);
			exception.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
		}
		return returnText;
	}
	
	@RequestMapping(value = "/cpeAllocation", method = RequestMethod.GET)
	public String cpeAllocation(Model model, HttpServletRequest request) {
		return "cpeAlloc";
	}
	
	@RequestMapping(value = "/searchCpeStockDetails", method = RequestMethod.POST)
	public String getCPEDetails(Model model,@RequestParam(value = "fromDate", required = false) String fromDate,
			@RequestParam(value = "toDate", required = false) String toDate,
			@RequestParam(value = "tenantType", required = false) String tenantType,
			@RequestParam(value = "tenantCode", required = false) String tenantCode, 
			@RequestParam(value = "cpeType", required = false) String cpeType,
			@RequestParam(value = "cpeSerialNumber", required = false) String cpeSerialNumber,		
			@RequestParam(value ="cpeSearchStockFile", required = false) MultipartFile file,HttpServletRequest request) {

		TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
		//HttpEntity<String> httpEntity;
		HttpEntity<CpeHelperDTO> httpEntity;
		String url = "";
		ResponseEntity<TmsHelperDTO> response;
		CpeHelperDTO cpeHelper = new CpeHelperDTO();
		List<String> cpeSlnoList=new ArrayList<String>();
		List<CpeStockVO> cpeVOList=new ArrayList<CpeStockVO>();
		
		try {
			logger.info("TmsController :: searchCpeStockDetails() :: Start");
			if (file!=null){
				
				String name = file.getOriginalFilename();
				String ext = FilenameUtils.getExtension(name);
				if (ext != null && ext.equalsIgnoreCase("xlsx") && ext != "") {
					//cpe changes 12/02/2018
					cpeVOList = ReadXlsExcelFileForCPEStock.extractSearchExcelFile(file, model);
					if (cpeVOList.size()!=0){
						for ( CpeStockVO cpeSlno : cpeVOList )
							{
								cpeSlnoList.add(cpeSlno.getCpeSrlno());
							}
					
						cpeHelper.setCpeSlnoList(cpeSlnoList);
					 }
		
				} else
					tmsHelperDTO.setStatus("Upload valid xls Document....");
			}

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),cpeHelper);
			url = ipAddressValues.getTmsURL() + "searchcpeDetails?fromDate=" + fromDate + "&toDate="+ toDate + "&tenantType="+ tenantType + "&tenantCode="+ tenantCode + "&cpeType="+ cpeType + "&cpeSerialNumber="+ cpeSerialNumber ;
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			if (tmsHelperDTO.getCpeStockList().size() > 0) {
				model.addAttribute("cpeList", tmsHelperDTO.getCpeStockList());
				model.addAttribute("message", "");
			} else {
				model.addAttribute("message", "No records found for this search criteria.");
			}
			model.addAttribute("fromDate", fromDate);
			model.addAttribute("toDate", toDate);
			model.addAttribute("tenantType", tenantType);
			model.addAttribute("tenantCode", tenantCode);
			model.addAttribute("cpeType", cpeType);
			model.addAttribute("cpeSerialNumber", cpeSerialNumber);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("cpeStockList", tmsHelperDTO.getCpeStockList());
			logger.info("after CpeStockList " + tmsHelperDTO.getCpeStockList().size());
			
			logger.info("TmsController :: searchCpeStockDetails() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: searchCpeStockDetails() :: " + e);
			e.printStackTrace();
		} finally {
			tmsHelperDTO = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return "cpeAlloc";
	}

	@RequestMapping(value = "/updateCpeDetails", method = RequestMethod.POST)
	public @ResponseBody String updateCpeDetails(@RequestBody List<CpeStockVO> cpeStockList,HttpServletRequest request) {
		logger.info(":::In updateCpeDetails :::");
		String returnText = "";
		TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
		HttpEntity<List<CpeStockVO>> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		//String tenantCode = (String) request.getSession().getAttribute("tenantcode");//cpe log history changes
		String loginID = (String) request.getSession(false).getAttribute("loginID");
		try {
			logger.info("TmsController :: updateCpeDetails() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),cpeStockList);
			String url = ipAddressValues.getTmsURL() + "updateCpeDetails?tenantCode="+loginID;
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			returnText = tmsHelperDTO.getStatus();
			logger.info("TmsController :: updateCpeDetails() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: updateCpeDetails() :: "+exception);
			exception.printStackTrace();
			returnText="FAILED TO UPDATE";
		}finally {
			httpEntity = null;
			response = null;
		}
			return returnText;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cpeStockDownload", method = RequestMethod.GET)
	public  void cpeStockDownload(HttpServletRequest request ,HttpServletResponse httpResponse) {
		ResponseEntity<ArrayList> response;
		HttpSession session;
		HSSFWorkbook workbook = null;
		List<CpeStockVO> cpeStckList = new ArrayList<CpeStockVO>();
		FileUtil fileUtil=new FileUtil();
		try {
			session = request.getSession(false);
			cpeStckList = (List<CpeStockVO>) session.getAttribute("cpeStockList");
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<CpeStockVO> cpeStockLists = mapper.readValue(gson.toJson(cpeStckList),TypeFactory.defaultInstance().constructCollectionType(List.class, CpeStockVO.class));
			workbook = fileUtil.cpeStockDownload(cpeStockLists,httpResponse);
		} catch (Exception e) {
			logger.error("IN cpeStockDownload DOWNLOAD");
			e.printStackTrace();
		}
		logger.info("after cpeStockDownload " + cpeStckList.size());
	}
	// 12/02/2018
	@RequestMapping(value = "/deleteCpeStocks", method = RequestMethod.POST)
	public @ResponseBody String deleteCpeStocks(@RequestBody List<CpeStockVO> cpeStockList, HttpServletRequest request) {
		logger.info(":::In deleteCpeStocks :::");
		
		
		HttpEntity<List<CpeStockVO>> httpEntity;
		
		String statusMessage="";
		CpeHelperDTO cpeHelper = new CpeHelperDTO();
		String tenantCode = (String) request.getSession().getAttribute("tenantcode");
		String loginID = (String) request.getSession(false).getAttribute("loginID");	
		
		
		try {
			logger.info("TmsController :: deleteCpeStocks() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),cpeStockList);
			String url = ipAddressValues.getTmsURL() + "deleteCpeStocks"+"?tenantCode="+tenantCode + "&loginid="+ loginID;
			cpeHelper = restTemplate.exchange(url, HttpMethod.POST, httpEntity, CpeHelperDTO.class).getBody();
			statusMessage=cpeHelper.getStatus();
			logger.info("TmsController :: deleteCpeStocks() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: deleteCpeStocks() :: "+exception);
			exception.printStackTrace();
			statusMessage ="FAILED TO DELETE";
		}finally {
			httpEntity = null;
			
		}
			return statusMessage;
		
		
	}
	
	//cpestock : 12/02/2018
	@RequestMapping(value = "/downloadSearchExcelFile", method = RequestMethod.GET)
	public void downLoadApk(HttpServletRequest request, HttpServletResponse response){

		File file = null;
		file = new File(ipAddressValues.getSearchExcelPath());
		try(InputStream inputStream = new FileInputStream(file);
				BufferedInputStream bIs = new BufferedInputStream(inputStream);
				ServletOutputStream os = response.getOutputStream()){
			
			if (file != null) {
				String mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
				response.setContentLength((int) file.length());
				FileCopyUtils.copy(bIs, os);
			} 
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		finally {
			file = null;
		}
	
	}
	
	@RequestMapping(value = "/cpeStockCreateUpdateLogHistory", method = RequestMethod.GET)
	public String cpeStockCreateUpdateLogHistory(Model model,  @RequestParam(value = "cpesrlno") String cpesrlno, HttpServletRequest request
			
			) {
		
		List<CpeStockVO> cpeLogList = new ArrayList<CpeStockVO>();

		try {
			logger.info("cpeStockCreateUpdateLogHistory :: searchCafDetails() :: START");
			cpeLogList = tenantService.getCPEStockDetails(cpesrlno,"","","");
			//model.addAttribute("cpeLogList", cpeLogList);	
			//HttpSession session = request.getSession(false);
			model.addAttribute("cpeLogList", cpeLogList);
			logger.info("cpeStockCreateUpdateLogHistory :: searchCafDetails() :: END");
		} catch (Exception e) {
			logger.error("cpeStockCreateUpdateLogHistory::searchCafDetails() " + e);
			e.printStackTrace();
		} 
		return "cpeStockCreateUpdateLogHistory";
	}
	
	@RequestMapping(value = "/deleteloghistory", method = RequestMethod.GET)
	public String deleteLogHistory(
			) {

		return "deletehistory";
	}
	
	@RequestMapping(value = "/searchDeleteHistoryDetails", method = RequestMethod.GET)
	public String getCPEDeleteHistoryDetails(Model model,@RequestParam(value = "fromDate", required = false) String fromDate,
			@RequestParam(value = "toDate", required = false) String toDate,
			@RequestParam(value = "cpeSerialNumber", required = false) String cpeSerialNumber, HttpServletRequest request		
			) {
		//code for cpe stock log history
		List<CpeStockVO> cpeList = new ArrayList<CpeStockVO>();		
		
		try {
			logger.info("TmsController :: getCPEDeleteHistoryDetails() :: START");
			cpeList = tenantService.getCPEStockDetails(cpeSerialNumber,fromDate,toDate,"delete");
		
		   // model.addAttribute("cpeDeleteLogList",cpeList );
			//HttpSession session = request.getSession(false);
			model.addAttribute("cpeDeleteLogList", cpeList);
			model.addAttribute("fromDate", fromDate);
			model.addAttribute("toDate", toDate);
			model.addAttribute("cpeSerialNumber", cpeSerialNumber);
			
			logger.info("TmsController :: getCPEDeleteHistoryDetails() :: END");
		} catch (Exception e) {
			logger.error("TmsController::getCPEDeleteHistoryDetails() " + e);
			e.printStackTrace();
		}
		return "deletehistory";
	}
	
	
	
	@RequestMapping(value = "/loghistory", method = RequestMethod.GET)
	public String logHistory(
			) {

		return "cpeAlloc";
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cpeCreateUpdateLogDownload", method = RequestMethod.GET)
	public  void cpeCreateUpdateLogDownload(HttpServletRequest request ,HttpServletResponse httpResponse,
			
			@RequestParam(value = "cpeSerialNumber", required = false) String cpeSerialNumber) {
		ResponseEntity<ArrayList> response;
		@SuppressWarnings("unused")
		//HttpSession session;
		HSSFWorkbook workbook = null;
		List<CpeStockVO> cpeStckList = new ArrayList<CpeStockVO>();
		FileUtil fileUtil=new FileUtil();
		try {
		//	session = request.getSession(false);
			cpeStckList = tenantService.getCPEStockDetails(cpeSerialNumber,"","","");
					//(List<CpeStockVO>) request.getAttribute("cpeLogList");
			
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<CpeStockVO> cpeStockLists = mapper.readValue(gson.toJson(cpeStckList),TypeFactory.defaultInstance().constructCollectionType(List.class, CpeStockVO.class));
			workbook = cpeStockUpdateLogDownload(cpeStockLists,httpResponse);
		} catch (Exception e) {
			logger.error("IN cpeCreateUpdateLogDownload DOWNLOAD");
			e.printStackTrace();
		}
		logger.info("after cpeCreateUpdateLogDownload " + cpeStckList.size());
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cpeDeleteHistoryDownload", method = RequestMethod.GET)
	public @ResponseBody void cpeDeleteHistoryDownload(HttpServletRequest request ,HttpServletResponse httpResponse,
			@RequestParam(value = "fromDate", required = false) String fromDate,
			@RequestParam(value = "toDate", required = false) String toDate,
			@RequestParam(value = "cpeSerialNumber", required = false) String cpeSerialNumber) {
		ResponseEntity<ArrayList> response;
		HSSFWorkbook workbook = null;
		List<CpeStockVO> cpeDeleteLogList = new ArrayList<CpeStockVO>();
		FileUtil fileUtil=new FileUtil();
		try {
			//HttpSession session = request.getSession(false);
			//cpeDeleteLogList = (List<CpeStockVO>) request.getAttribute("cpeDeleteLogList");
			cpeDeleteLogList = tenantService.getCPEStockDetails(cpeSerialNumber,fromDate,toDate,"delete");
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<CpeStockVO> cpeStockLists = mapper.readValue(gson.toJson(cpeDeleteLogList),TypeFactory.defaultInstance().constructCollectionType(List.class, CpeStockVO.class));
			workbook = cpeStockUpdateLogDownload(cpeStockLists,httpResponse);
		} catch (Exception e) {
			logger.error("IN cpeDeleteHistoryDownload DOWNLOAD");
			e.printStackTrace();
		}
		logger.info("after cpeDeleteHistoryDownload " + cpeDeleteLogList.size());
	}
	
	public HSSFWorkbook cpeStockUpdateLogDownload(List<CpeStockVO> cpeStockLists,HttpServletResponse httpResponse ) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("workorder");
		// create header row
		Row header = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try (ServletOutputStream out = httpResponse.getOutputStream();
				InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {

			byte[] bytes = IOUtils.toByteArray(my_banner_image);
			int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			my_banner_image.close();
			HSSFPatriarch drawing = sheet.createDrawingPatriarch();
			ClientAnchor my_anchor = new HSSFClientAnchor();
			my_anchor.setDx1(0);
			my_anchor.setDy1(0);
			my_anchor.setDx2(0);
			my_anchor.setDy2(0);
			my_anchor.setCol1(0);
			my_anchor.setRow1(0);
			my_anchor.setCol2(1);
			my_anchor.setRow2(1);
			HSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
			my_picture.resize();

			CellStyle style = workbook.createCellStyle();
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			// For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("CPE Stock Update Log List");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S No.");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("CPE SrlNO");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("CPE Mac Address");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("MSP Code");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("LMO Code");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Transaction Status");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("Transaction date");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("User");
			header.getCell(7).setCellStyle(style);
			
			// create data rows
			int rowCount = 5;
			int i=0;
			for (CpeStockVO cpeStockVO : cpeStockLists) {
				HSSFRow Row = sheet.createRow(rowCount++);

				Row.createCell(0).setCellValue(++i);
				Row.createCell(1).setCellValue(cpeStockVO.getCpeSrlno() == null ? "" : cpeStockVO.getCpeSrlno().toString());
				Row.createCell(2).setCellValue(cpeStockVO.getMacAddress() == null ? "" : cpeStockVO.getMacAddress().toString());
				Row.createCell(3).setCellValue(cpeStockVO.getMspCode() == null	? "" : cpeStockVO.getMspCode());
				Row.createCell(4).setCellValue(cpeStockVO.getLmoCode() == null	? "" : cpeStockVO.getLmoCode());
				Row.createCell(5).setCellValue(cpeStockVO.getCpeLogTransactionStatus() == null	? "" : cpeStockVO.getCpeLogTransactionStatus());
				Row.createCell(6).setCellValue(cpeStockVO.getCpeLogTransactionDate() == null	? "" : cpeStockVO.getCpeLogTransactionDate());
				Row.createCell(7).setCellValue(cpeStockVO.getUser() == null	? "" : cpeStockVO.getUser());
				
			}
			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename=CpeStockInfo.xls");
			workbook.write(out);
			System.out.println("Excel written successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return workbook;
	}
	
	
	@RequestMapping(value = "/UpdateCustInvList", method = RequestMethod.GET)
	public String UpdateCustInvList(Model model,
			@RequestParam(value = "invyr", required = false) String invyr,
			@RequestParam(value = "invmn", required = false) String invmn) {	
		logger.info(":::In revenueSharing Page:::");
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		String tmsHelperDTO;
//		Map<String,String> templatesLov;
		Date currDate=null;
		currDate=new Date();
		try {
//			String fromdate2 = fromdate.replaceAll("/", "-");
//			String todate2 = todate.replaceAll("/", "-");
//			
//			String[] parts = fromdate2.split("-");
//			String fromdate1 = parts[2].concat("-"+parts[0]).concat("-"+parts[1]); // 004
//			String[] parts2 = todate2.split("-");
//			String todate1 = parts2[2].concat("-"+parts2[0]).concat("-"+parts2[1]);
			if (invyr!=null || invmn!=null){
			logger.info("TmsController :: UpdateCustInvList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "UpdateCustInvList?invfromdate="+invyr.toString()+ "&invtodate="+invmn.toString();
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			tmsHelperDTO = response.getBody();
			
			String message = "Report was Generated on " + currDate.toString();
			model.addAttribute("invyr", invyr);
			model.addAttribute("invmn", invmn);
			model.addAttribute("message", message);
			logger.info("TmsController :: UpdateCustInvList() :: End");
			}
		} catch (Exception exception) {
			logger.error("TmsController :: UpdateCustInvList() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			tmsHelperDTO = null;
			
		}
     return "revenueDetails";
		
	}
	
	
	
	
	
	@RequestMapping(value = "/getMonthWiseShares", method = RequestMethod.GET)
	public String getMonthWiseShares(Model model,
			@RequestParam(value = "invyr", required = false) String invyr,
			@RequestParam(value = "invmn", required = false) String invmn,
			@RequestParam(value = "districtuid", required = false) String distUid,
			HttpServletRequest request) {	
		logger.info(":::In getMonthWiseShares Page:::");
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response;
		HttpSession session;
		
		CustomerInvDtlsDTO totalShareDtlsObj= new CustomerInvDtlsDTO();

		TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
		Date currDate=null;
		currDate=new Date();
		try {
			
			HttpEntity<String> httpEntityDistrict = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(),
					ipAddressValues.getComPwd());
			 String urlDistrict = ipAddressValues.getComURL() + "alldistricts";
			ResponseEntity<ArrayList> responseDistrict = restTemplate.exchange(urlDistrict, HttpMethod.GET, httpEntityDistrict,
					ArrayList.class);
			ArrayList<Districts> districtsList = responseDistrict.getBody();

			if (invyr!=null || invmn!=null){
				
				
				
				
			logger.info("TmsController :: getMonthWiseShares() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getMonthWiseShares?invfromdate="+invyr.toString()+ "&invtodate="+invmn.toString() +"&districtuid="+distUid ;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response.getBody();
			
			
			HttpEntity<String> httpEntityTotal = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String urlForTotal = ipAddressValues.getTmsURL() + "getSumtotalOfShares?invfromdate="+invyr.toString()+ "&invtodate="+invmn.toString()+"&districtuid="+distUid;
			ResponseEntity<TmsHelperDTO> responseForTotal = restTemplate.exchange(urlForTotal, HttpMethod.GET, httpEntityTotal, TmsHelperDTO.class);
			TmsHelperDTO tmsHelperDTOForTotal = responseForTotal.getBody();
			
			totalShareDtlsObj=tmsHelperDTOForTotal.getCustomerInvDtlsDTO().get(0);
			
			
			model.addAttribute("invyr", invyr);
			model.addAttribute("invmn", invmn);
			model.addAttribute("year", invyr);
			model.addAttribute("month", invmn);
			//model.addAttribute("districtList", districtsList);
			 session = request.getSession(false);
			session.setAttribute("revsharelist", tmsHelperDTO.getCustomerInvDtlsDTO());
			
			if (totalShareDtlsObj !=null)
			{
				session.setAttribute("apsfl_total_share",totalShareDtlsObj.getApsflInvshare() );
				session.setAttribute("mso_total_share",totalShareDtlsObj.getMsoInvshare() );
				session.setAttribute("lmo_total_share",totalShareDtlsObj.getLmoInvshare() );
				session.setAttribute("lmo_total_paid",totalShareDtlsObj.getTotalpaid() );
				session.setAttribute("lmo_total_notpaid",totalShareDtlsObj.getTotalnotpaid() );
				
				
			}
			
			
		
			logger.info("TmsController :: getMonthWiseShares() :: End");
			}
			model.addAttribute("districtList", districtsList);
			model.addAttribute("districtuid", distUid);

		} catch (Exception exception) {
			logger.error("TmsController :: getMonthWiseShares() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			
			
		}
     return "revenueDetailsSummary";
		
	}
	
	
	
	
	
	
	@RequestMapping(value = "/setMonthWiseShares", method = RequestMethod.GET)
	public String setMonthWiseShares(Model model,
			@RequestParam(value = "invyr", required = false) String invyr,
			@RequestParam(value = "invmn", required = false) String invmn,
			HttpServletRequest request) {	
		
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		

		Date currDate=null;
		currDate=new Date();
		try {

			if (invyr!=null || invmn!=null){
			logger.info("TmsController :: setMonthWiseShares() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "setMonthWiseShares?invfromdate="+invyr.toString()+ "&invtodate="+invmn.toString();
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			//tmsHelperDTO = response.getBody();
			
			
			String message = response.getBody() +" " + currDate.toString();
			model.addAttribute("invyr", invyr);
			model.addAttribute("invmn", invmn);
			model.addAttribute("message", message);
			
			// session = request.getSession(false);
			//session.setAttribute("revsharelist", tmsHelperDTO.getCustomerInvDtlsDTO());
			
		
			logger.info("TmsController :: setMonthWiseShares() :: End");
			}
		} catch (Exception exception) {
			logger.error("TmsController :: setMonthWiseShares() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			
			
			
		}
     return "generateRevDetailsSummary";
		
	}
	
	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/revsharedownload", method = RequestMethod.GET)
	public  void revShareDownload(HttpServletRequest request ,HttpServletResponse httpResponse) {
		ResponseEntity<ArrayList> response;
		HttpSession session;
		HSSFWorkbook workbook = null;
		List<CustomerInvDtlsDTO> revShareList = new ArrayList<CustomerInvDtlsDTO>();
		FileUtil fileUtil=new FileUtil();
		try {
			session = request.getSession(false);
			revShareList = (List<CustomerInvDtlsDTO>) session.getAttribute("revsharelist");
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<CustomerInvDtlsDTO> revSummarylist = mapper.readValue(gson.toJson(revShareList),TypeFactory.defaultInstance().constructCollectionType(List.class, CustomerInvDtlsDTO.class));
			workbook = fileUtil.revShareDownload(revSummarylist,httpResponse);
		} catch (Exception e) {
			logger.error("IN revsharedownload DOWNLOAD");
			e.printStackTrace();
		}
		logger.info("after revsharedownload " + revShareList.size());
	}
	
	
	
	
	/**
	 * @author Rajesh S
	 * @return list of monthly dues on lmo login basis
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getLMORevShare", method = RequestMethod.GET)
	public String getLMORevAPSFLShare(Model model, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String lmocode="";
		String url = "";
		ResponseEntity<ArrayList> response;
		List<TenantLMORevServiceDTO> tenantLMORevServiceDTOList = new ArrayList<>();
		try {
			logger.info("TmsController :: getLMORevAPSFLShare() :: Start");
			lmocode=(String) request.getSession().getAttribute("loginID");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getTmsURL() + "getLMORevenueShare?lmocode=" + lmocode;;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			tenantLMORevServiceDTOList = response.getBody();
			model.addAttribute("lmoapsflshare", tenantLMORevServiceDTOList);
			logger.info("TmsController :: getLMORevAPSFLShare() :: End");
		} catch (Exception e) {
			logger.info("TmsController :: getLMORevAPSFLShare() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			tenantLMORevServiceDTOList = null;
		}
		return "lmoPaymentDetails";
	}
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String Payment(Model model,SelectPaymentVO paymentVO) {
		

		CpeHelperDTO cpeHelperDTO = new CpeHelperDTO();
		try {

			logger.info("CssController :: Payment() :: Start");
			cpeHelperDTO.setPaymentMode(paymentVO.getPaymentMode());
			cpeHelperDTO.setTotalCost(paymentVO.getAddAmt1());
			cpeHelperDTO.setPaymentType("onlineSelfCareLMOAPSFLPayment");
			logger.info("CssController :: Payment() :: End");
		} catch (Exception e) {
			logger.error("CssController :: Payment() :: " + e);
			e.printStackTrace();
		}
		model.addAttribute("paymentHelperDTO", cpeHelperDTO);

		return "lmopayment";
	}
	@RequestMapping(value = "/paymentRequest", method = RequestMethod.POST)
	public void saveCreditCardPayment(Model model, HttpServletResponse response, HttpServletRequest request,RequestDTO requestDTO) throws IOException {

		HttpEntity<RequestDTO> httpEntity;
		ResponseEntity<String> res;
		String pgURL = null;
		String custId=null;
		custId=(String) request.getSession().getAttribute("loginID");
		requestDTO.setCustomerId(custId);
		requestDTO.setGatewayType("BILLDESK");
		try {
			//RequestDTO requestDTOObj = new RequestDTO(requestDTO);
			logger.info("PaymentController :: saveCreditCardPayment() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(), requestDTO);
			String url = ipAddressValues.getTmsURL() + "pg/request";
			res = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			pgURL = res.getBody();
			
			logger.info("URL PG Request ====>" + request.getRequestURL().toString());
			logger.info("URL ====>" + pgURL);
			
			logger.info("PaymentController :: saveCreditCardPayment() :: End");

		} catch (Exception e) {
			logger.error("PaymentController :: saveCreditCardPayment() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			res = null;
			requestDTO = null;
		}
		
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.sendRedirect(pgURL != null ? pgURL: "paymentError");

	}
	
	@RequestMapping(value = "/setPaidFlagForOverPaidCustomer", method = RequestMethod.GET)
	public String setPaidFlagForOverPaidCustomer(Model model,
			@RequestParam(value = "invyr", required = false) String invyr,
			@RequestParam(value = "invmn", required = false) String invmn,
			HttpServletRequest request) {	
		
	
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;

		Date currDate=null;
		currDate=new Date();
		try {

			if (invyr!=null || invmn!=null){
			logger.info("TmsController :: setPaidFlagForOverPaidCustomer() :: Start");
			
			
			logger.info("TmsController :: setPaidFlagForOverPaidCustomer() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "setPaidFlagForOverPaidCustomer?invfromdate="+invyr.toString()+ "&invtodate="+invmn.toString();
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

			String message = "  Generated on " + currDate.toString();
			model.addAttribute("invyr", invyr);
			model.addAttribute("invmn", invmn);
			model.addAttribute("message", message);
			
			// session = request.getSession(false);
			//session.setAttribute("revsharelist", tmsHelperDTO.getCustomerInvDtlsDTO());
			
		
			logger.info("TmsController :: setPaidFlagForOverPaidCustomer() :: End");
			}
		} catch (Exception exception) {
			logger.error("TmsController :: setPaidFlagForOverPaidCustomer() :: "+exception);
			exception.printStackTrace();
		}
     return "setPaidFlagForOverPaidCustomer";
		
	}
	
	
	@RequestMapping(value = "/updateTenantsWalletOnFirst", method = RequestMethod.GET)
	public String updateTenantsWalletOnFirst(Model model,
			@RequestParam(value = "invyr", required = false) String invyr,
			@RequestParam(value = "invmn", required = false) String invmn,
			HttpServletRequest request) {	
		
	
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;

		Date currDate=null;
		currDate=new Date();
		try {

			if (invyr!=null || invmn!=null){
			logger.info("TmsController :: updateTenantsWalletOnFirst() :: Start");
			
			
			logger.info("TmsController :: updateTenantsWalletOnFirst() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "updateTenantsWalletOnFirst?invfromdate="+invyr.toString()+ "&invtodate="+invmn.toString();
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

			String message = response.getBody() +" "+ currDate.toString();
			model.addAttribute("invyr", invyr);
			model.addAttribute("invmn", invmn);
			model.addAttribute("message", message);
			model.addAttribute("month", invyr);
			model.addAttribute("year", invmn);
			
			// session = request.getSession(false);
			//session.setAttribute("revsharelist", tmsHelperDTO.getCustomerInvDtlsDTO());
			
		
			logger.info("TmsController :: updateTenantsWalletOnFirst() :: End");
			}
		} catch (Exception exception) {
			logger.error("TmsController :: updateTenantsWalletOnFirst() :: "+exception);
			exception.printStackTrace();
		}
     return "updateTenantsWalletOnFirst";
		
	}
	
	
	@RequestMapping(value = "/updateTenantsWalletOnEndOfMonth", method = RequestMethod.GET)
	public String updateTenantsWalletOnEndOfMonth(Model model,
			@RequestParam(value = "invyr", required = false) String invyr,
			@RequestParam(value = "invmn", required = false) String invmn,
			HttpServletRequest request) {	
		
	
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;

		Date currDate=null;
		currDate=new Date();
		try {

			if (invyr!=null || invmn!=null){
			logger.info("TmsController :: updateTenantsWalletOnEndOfMonth() :: Start");
			
			
			logger.info("TmsController :: updateTenantsWalletOnEndOfMonth() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "updateTenantsWalletOnEndOfMonth?invfromdate="+invyr.toString()+ "&invtodate="+invmn.toString();
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

			String message = response.getBody() +" "+ currDate.toString();
			model.addAttribute("invyr", invyr);
			model.addAttribute("invmn", invmn);
			model.addAttribute("month", invyr);
			model.addAttribute("year", invmn);
			model.addAttribute("message", message);
			
			// session = request.getSession(false);
			//session.setAttribute("revsharelist", tmsHelperDTO.getCustomerInvDtlsDTO());
			
		
			logger.info("TmsController :: updateTenantsWalletOnEndOfMonth() :: End");
			}
		} catch (Exception exception) {
			logger.error("TmsController :: updateTenantsWalletOnEndOfMonth() :: "+exception);
			exception.printStackTrace();
		}
     return "updateTenantsWalletOnEndOfMonth";
		
	}
	
	@RequestMapping(value="/viewModifiedCafs", method= RequestMethod.GET)
	public String viewModifiedCafs(Model model, HttpServletRequest request){
		return "viewModifiedCafs";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/createPartner", method = RequestMethod.POST)
	@ResponseBody
	public String createPartner(@RequestBody List<ProductAgreementVO> productAgreementVOList, HttpServletRequest request) {
	
   
       HttpEntity<CreatePartnerDto> httpEntity;
		ResponseEntity<String> response;
		CreatePartnerDto createPartnerDto1=new CreatePartnerDto();
		CreatePartnerDto createPartnerDto2=new CreatePartnerDto();
		String  status="";
		String lmocode="";
		String msocode="";
		int count=0;
		try {
			if (!productAgreementVOList.isEmpty()){
			
				for (ProductAgreementVO productAgreementVO :productAgreementVOList)
				{
				if (productAgreementVO.getProdCode().equals("HomeBasic")){	
					count++;
				  List<String> partnerCodesList = new ArrayList<String>();
					List<ProductAgreementPartnersVO> productAgreementPartnerslist = productAgreementVO.getPartnrsList();
					for (ProductAgreementPartnersVO productAgreementPartnersVO : productAgreementPartnerslist) {
						
						
						if (productAgreementPartnersVO.getPartnertype().equals("LMO"))
							
						{
							
						lmocode=productAgreementPartnersVO.getPartnercode();
						}else if (productAgreementPartnersVO.getPartnertype().equals("MSP")){
							msocode=productAgreementPartnersVO.getPartnercode();
						}
					
					}
				}
				}
					
					if (!msocode.equals("") && !lmocode.equals("")){
					
							createPartnerDto1=	tenantService.getPartnerDetails(msocode);
						createPartnerDto1.setParentPartnerCode("APSFL");
						

						logger.info("TmsController :: createPartner() :: Start");
						httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd(),createPartnerDto1);
						String url1 = ipAddressValues.getCatURL() + "scs/createPartner";
						response = restTemplate.exchange(url1, HttpMethod.POST, httpEntity, String.class);
						status = response.getBody();

						createPartnerDto2=	tenantService.getPartnerDetails(lmocode);
						createPartnerDto2.setParentPartnerCode(msocode);
						

						logger.info("TmsController :: createPartner() :: Start");
						httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd(),createPartnerDto2);
						String url2 = ipAddressValues.getCatURL() + "scs/createPartner";
						response = restTemplate.exchange(url2, HttpMethod.POST, httpEntity, String.class);
						status = response.getBody();

			  
			}else
				status="One of the tenants empty";
			}
			
			if (count==0)
				status="data not posted as home basic package is not included";
			
			logger.info("TmsController :: createPartner() :: End");
		}catch (Exception exception) {
			logger.error("TmsController :: createPartner() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			
		}
		return status;
	}
	
	
	
	
}
