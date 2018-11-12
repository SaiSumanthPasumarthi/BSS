package com.arbiva.apsfl.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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

import com.arbiva.apfgc.tenant.bo.CafWiseRevenueOfLoginLmo;
import com.arbiva.apfgc.tenant.bo.LmoWalletUpdateByChequePaymentBO;
import com.arbiva.apfgc.tenant.bo.MsoDetailsWithLmosBO;
import com.arbiva.apfgc.tenant.bo.MsoRevenueShareBO;
import com.arbiva.apfgc.tenant.bo.Offline_Payment1;
import com.arbiva.apsfl.coms.dto.AadharDTO;
import com.arbiva.apsfl.coms.dto.BalAdjCafinvDtslBO;
import com.arbiva.apsfl.coms.dto.BalanceAdjustmentVO;
import com.arbiva.apsfl.coms.dto.BulkCustomerDTO;
import com.arbiva.apsfl.coms.dto.Caf;
import com.arbiva.apsfl.coms.dto.CafAndCpeChargesVO;
import com.arbiva.apsfl.coms.dto.CafDetailsVO;
import com.arbiva.apsfl.coms.dto.CafProducts;
import com.arbiva.apsfl.coms.dto.CafsVO;
import com.arbiva.apsfl.coms.dto.ComsHelperDTO;
import com.arbiva.apsfl.coms.dto.CpeChargeDetailsBO;
import com.arbiva.apsfl.coms.dto.CpeModal;
import com.arbiva.apsfl.coms.dto.Cpecharges;
import com.arbiva.apsfl.coms.dto.Customer;
import com.arbiva.apsfl.coms.dto.CustomerCafBO;
import com.arbiva.apsfl.coms.dto.CustomerCafVO;
import com.arbiva.apsfl.coms.dto.Districts;
import com.arbiva.apsfl.coms.dto.EntCafStage;
import com.arbiva.apsfl.coms.dto.EnterpriseCustomer;
import com.arbiva.apsfl.coms.dto.EnterpriseCustomerVO;
import com.arbiva.apsfl.coms.dto.ErrorJsonsDTO;
import com.arbiva.apsfl.coms.dto.ErrorsDTO;
import com.arbiva.apsfl.coms.dto.FeatureParamsVO;
import com.arbiva.apsfl.coms.dto.FingerPrintBO;
import com.arbiva.apsfl.coms.dto.FingerPrintJson;
import com.arbiva.apsfl.coms.dto.LmoDetailsBO;
import com.arbiva.apsfl.coms.dto.Mandals;
import com.arbiva.apsfl.coms.dto.MonthlyPayment;
import com.arbiva.apsfl.coms.dto.MultiAction;
import com.arbiva.apsfl.coms.dto.OLT;
import com.arbiva.apsfl.coms.dto.OLTPortDetails;
import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.coms.dto.PaymentVO;
import com.arbiva.apsfl.coms.dto.PendingProvisionErrorsDTO;
import com.arbiva.apsfl.coms.dto.ProductsVO;
import com.arbiva.apsfl.coms.dto.PublicIpAddressDTO;
import com.arbiva.apsfl.coms.dto.SearchDataVO;
import com.arbiva.apsfl.coms.dto.SplitterVO;
import com.arbiva.apsfl.coms.dto.Substations;
import com.arbiva.apsfl.coms.dto.TerminatePkgBO;
import com.arbiva.apsfl.coms.dto.VPNServiceExcelUploadVO;
import com.arbiva.apsfl.coms.dto.VPNSrvcNames;
import com.arbiva.apsfl.coms.dto.VPNSrvcNamesStage;
import com.arbiva.apsfl.coms.dto.VPNSrvcVO;
import com.arbiva.apsfl.coms.dto.Villages;
import com.arbiva.apsfl.dto.MailDTO;
import com.arbiva.apsfl.dto.Offline_Payment;

import com.arbiva.apsfl.dto.UsersDataPageObject;
import com.arbiva.apsfl.tms.dto.CpeHelperDTO;
import com.arbiva.apsfl.tms.dto.CpeStockVO;
import com.arbiva.apsfl.tms.dto.Lovs;
import com.arbiva.apsfl.tms.dto.TmsHelperDTO;
import com.arbiva.apsfl.tms.dto.UploadHistDTO;
import com.arbiva.apsfl.tms.model.CafMaster;
import com.arbiva.apsfl.tms.model.CpeStock;
import com.arbiva.apsfl.tms.serviceImpl.DemandNoteServiceImpl;
import com.arbiva.apsfl.tms.serviceImpl.ReportsServiceImpl;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.CafEnumCodes;
import com.arbiva.apsfl.util.DateUtill;
import com.arbiva.apsfl.util.FileUtil;
import com.arbiva.apsfl.util.IpAddressValues;
import com.arbiva.apsfl.util.PaginationObject;
import com.arbiva.apsfl.util.ReadXlsExcelFileForCPEStock;
import com.arbiva.apsfl.util.ReadXlsFileForCafs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;

@Controller
public class ComsController {

	private static final Logger logger = Logger.getLogger(ComsController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;

	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	DemandNoteServiceImpl demandNoteServiceImpl;

	@Autowired
	ReportsServiceImpl reportsServiceImpl;
	
	@Value("${ponChangeUrl}")
	private String ponChangeUrl;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/comsHome", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @RequestParam(value = "flag", required = false) String flag, HttpServletRequest request) {

		List<Object[]> customerList = new ArrayList<>();//Delete this line in future
		HttpEntity<String> httpEntity;
		String url = "";
		try {
			logger.info("ComsController :: home() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "?tenantCode=" + tenantCode;
			customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class).getBody();
			model.addAttribute("customerList", customerList);//Delete this line in future
			model.addAttribute("message", flag);
			logger.info("ComsController :: home() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: home() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			customerList = null;
		}
		return "comsHome";
	}

	@ResponseBody
	@RequestMapping(value = "/checkDuplicateRegNo", method = RequestMethod.GET)
	public String checkDuplicateRegNo(@RequestParam(value = "regNo", required = false) String regNo) {
		String status = "false";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: checkDuplicateRegNo() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "checkDuplicateRegNo?regNo=" + regNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: checkDuplicateRegNo() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: checkDuplicateRegNo() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkExistAPSFLCode", method = RequestMethod.GET)
	public boolean checkExistAPSFLCode(@RequestParam(value = "apsflUniqueId", required = false) String apsflUniqueId) {
		boolean status = false;
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<Boolean> response;
		try {
			logger.info("ComsController :: checkExistAPSFLCode() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "checkExistAPSFLCode?apsflUniqueId=" + apsflUniqueId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, boolean.class);
			status = response.getBody();
			logger.info("ComsController :: checkExistAPSFLCode() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: checkExistAPSFLCode() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}

	@RequestMapping(value = "/individualCafFPActivation", method = RequestMethod.GET)
	public String individualCafFPActivation(Model model, HttpServletRequest request) {
		return "individualCafFPActivation";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/individualCafFPActivationPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<FingerPrintBO> individualCafFPActivationPagination(HttpServletRequest request) {

		HttpEntity<PageObject> httpEntity;
		String url = "";
		ResponseEntity<PaginationObject> response;
		PageObject pageObject = new PageObject();
		PaginationObject<FingerPrintBO> fpDataPageObject = new PaginationObject<FingerPrintBO>();
		try {
			Integer pageDisplayLength = 10;
			Integer pageNumber = 1;
			String sdir = "desc";
			String sortColumn = "";
			int sortParameter = 0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			if (request.getParameter("iSortCol_0") != null) {
				sortParameter = Integer.parseInt(request.getParameter("iSortCol_0"));
			}
			sortColumn = FingerPrintBO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
			if (request.getParameter("sSortDir_0") != null)
				sdir = request.getParameter("sSortDir_0");

			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			pageObject.setMinSize((pageNumber - 1) * pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), pageObject);
			url = ipAddressValues.getComURL() + "individualCafFPActivationPagination";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, PaginationObject.class);
			fpDataPageObject = response.getBody();
		} catch (Exception e) {
			logger.info("ComsController :: viewcustomersPage() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return fpDataPageObject;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/groupCafFPActivation", method = RequestMethod.GET)
	public String groupCafFPActivation(Model model, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		List<Districts> districtList = new ArrayList<>();
		try {
			logger.info("ComsController :: groupCafFPActivation() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "groupCafFPActivation";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			districtList = response.getBody();
			model.addAttribute("districtList", districtList);
			logger.info("ComsController :: groupCafFPActivation() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: groupCafFPActivation() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			districtList = null;
		}
		return "groupCafFPActivation";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/groupCafFPActivationDetails", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<FingerPrintBO> groupCafFPActivationDetails(FingerPrintBO fingerPrintBO, HttpServletRequest request) {

		HttpEntity<FingerPrintBO> httpEntity;
		String url = "";
		ResponseEntity<PaginationObject> response;
		PageObject pageObject = new PageObject();
		PaginationObject<FingerPrintBO> fpDataPageObject = new PaginationObject<FingerPrintBO>();
		try {
			fingerPrintBO.setSelectCaf(fingerPrintBO.getSelectCaf().equalsIgnoreCase("Gl") ? "Yes" : "No");
			Integer pageDisplayLength = 10;
			Integer pageNumber = 1;
			String sdir = "desc";
			String sortColumn = "";
			int sortParameter = 0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			if (request.getParameter("iSortCol_0") != null) {
				sortParameter = Integer.parseInt(request.getParameter("iSortCol_0"));
			}
			sortColumn = FingerPrintBO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
			if (request.getParameter("sSortDir_0") != null)
				sdir = request.getParameter("sSortDir_0");

			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			pageObject.setMinSize((pageNumber - 1) * pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);
			fingerPrintBO.setPageObject(pageObject);

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), fingerPrintBO);
			url = ipAddressValues.getComURL() + "groupCafFPActivationDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, PaginationObject.class);
			fpDataPageObject = response.getBody();
		} catch (Exception e) {
			logger.info("ComsController :: viewcustomersPage() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return fpDataPageObject;
	}

	@RequestMapping(value = "/checkBlackListCustomer", method = RequestMethod.GET)
	@ResponseBody
	public String checkBlackListCustomer(Model model, HttpServletRequest request,
			@RequestParam(value = "aadharNumber", required = false) String aadharNumber) {
		String status = "false";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: checkBlackListCustomer() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "checkBlackListCustomer?aadharNumber=" + aadharNumber;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: checkBlackListCustomer() :: End");
		} catch (Exception e) {
			logger.error("CustomerController :: checkBlockListCustomer() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}

	@RequestMapping(value = "/checkExistCustomer", method = RequestMethod.GET)
	@ResponseBody
	public Customer checkExistCustomer(Model model, HttpServletRequest request,
			@RequestParam(value = "aadharNo", required = false) String aadharNo) {

		Customer customer = new Customer();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<Customer> response;

		try {
			logger.info("ComsController :: checkExistCustomer() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "checkExistCustomer?aadharNo=" + aadharNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Customer.class);
			customer = response.getBody();
			logger.info("ComsController :: checkExistCustomer() :: Start");
		} catch (Exception e) {
			logger.info("ComsController :: checkExistCustomer() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return customer;
	}

	@RequestMapping(value = "/multiactionsearch", method = RequestMethod.GET)
	public String multiActionSearchPage(Locale locale, Model model, @RequestParam(value = "flag", required = false) String flag, HttpServletRequest request) {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: multiActionSearchPage() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "multiactionsearch?tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("statusCodes", comsHelperDTO.getStatusCodesList());
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("CustomerTypeList", comsHelperDTO.getCustomerTypeList());
			model.addAttribute("CustomerSubTypeList", comsHelperDTO.getCustomerSubTypeList());
			model.addAttribute("tenantType", tenantType);
			model.addAttribute("flag", flag);
			logger.info("ComsController :: multiActionSearchPage() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: multiActionSearchPage() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return "multiaction";
	}

	@RequestMapping(value = "/monthlypayment", method = RequestMethod.GET)
	public String monthlyPaymentpage(Locale locale, Model model, HttpServletRequest request) {
		return "monthlypayment";
	}

	@RequestMapping(value = "/getSearchPackageDetails", method = RequestMethod.POST)
	public String getSearchPackageDetails(Model model,
			@RequestParam(value = "cafNumber", required = false) Long cafNumber, HttpServletRequest request) {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		String tenantCode = "";
		String tenantName = "";
		String tenantType = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: getSearchPackageDetails() :: Start");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantName = (String) request.getSession(false).getAttribute("tenantname");
			tenantType = (String) request.getSession(false).getAttribute("domain");

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getSearchPackageDetails?cafNumber=" + cafNumber + "&tenantName="+ tenantName + "&tenantCode=" + tenantCode + "&tenantType=" + tenantType;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();

			model.addAttribute("cafNo", comsHelperDTO.getCustomerCafVO().getCafNo());
			model.addAttribute("custId", comsHelperDTO.getCustomerCafVO().getCustId());
			model.addAttribute("billCycleFreq", comsHelperDTO.getCustomerCafVO().getBillCycle());
			model.addAttribute("custType", comsHelperDTO.getCustomerCafVO().getCustType());
			HttpSession session = request.getSession(false);
			session.setAttribute("productList", comsHelperDTO.getProductsList());

			model.addAttribute("oltList", comsHelperDTO.getOltList());
			model.addAttribute("popList", comsHelperDTO.getSubStationsList());
			model.addAttribute("cpeModalList", comsHelperDTO.getCpeModalsList());
			model.addAttribute("stbModelList", comsHelperDTO.getStbModelsList());
			model.addAttribute("baseProductList", comsHelperDTO.getBaseProductList());
			model.addAttribute("addOnProductList", comsHelperDTO.getAddOnProductList());
			model.addAttribute("oneTimeProductList", comsHelperDTO.getOneTimeProductList());
			model.addAttribute("customerCafVO", comsHelperDTO.getCustomerCafVO());
			model.addAttribute("lmoName", tenantName);
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getFlag());
			model.addAttribute("lmoAgrmntMspCodes", comsHelperDTO.getLmoAgrmntMspCodes());
			model.addAttribute("tenantType", tenantType);
			model.addAttribute("telephoneConnections", comsHelperDTO.getTelephoneConnectionsList());
			logger.info("ComsController :: getSearchPackageDetails() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: getSearchPackageDetails() :: " + e);
			e.printStackTrace();
		} finally {
			comsHelperDTO = null;
			httpEntity = null;
			url = null;
			tenantCode = null;
			tenantName = null;
			response = null;
		}
		return "selectpackages";
	}

	@RequestMapping(value = "/createcustomer", method = RequestMethod.POST)
	public String getProducts(Model model, EnterpriseCustomerVO enterpriseCustomerVO, HttpServletRequest request) {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpEntity<EnterpriseCustomerVO> httpEntity;
		String url = "";
		Date entDate = new Date();
		String tenantCode = "";
		String tenantName = "";
		ResponseEntity<ComsHelperDTO> response;

		try {
			logger.info("ComsController :: getProducts() :: Start");
			enterpriseCustomerVO.setCustTypeLov(enterpriseCustomerVO.getCustTypeLov() != null && !enterpriseCustomerVO.getCustTypeLov().isEmpty() ? enterpriseCustomerVO.getCustTypeLov() : CafEnumCodes.CUST_TYPE_CODE.getCode());
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantName = (String) request.getSession(false).getAttribute("tenantname");
			enterpriseCustomerVO.setTenantCode(tenantCode);

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), enterpriseCustomerVO);
			url = ipAddressValues.getComURL() + "createcustomer";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			if (comsHelperDTO.getCustomer() != null) {
				enterpriseCustomerVO.setCustType(comsHelperDTO.getCustomer().getCustType());
				enterpriseCustomerVO.setUidNo(comsHelperDTO.getCustomer().getRegnCode());
				enterpriseCustomerVO.setOrgName(comsHelperDTO.getCustomer().getCustName());
				enterpriseCustomerVO.setEmailId(comsHelperDTO.getCustomer().getEmail1());
				enterpriseCustomerVO.setPocName(comsHelperDTO.getCustomer().getPocName());
				enterpriseCustomerVO.setBillCycle(comsHelperDTO.getCustomer().getBillfreqLov());
			}
			model.addAttribute("customer", comsHelperDTO.getCustomer());
			model.addAttribute("enterpriseCustomerVO", enterpriseCustomerVO);
			model.addAttribute("enterpriseCustomer", comsHelperDTO.getEnterpriseCustomer());
			model.addAttribute("popList", comsHelperDTO.getSubStationsList());
			model.addAttribute("billCycleList", comsHelperDTO.getBillCycleList());
			model.addAttribute("customerTypeList", comsHelperDTO.getCustomerTypeList());
			model.addAttribute("cafNo", comsHelperDTO.getCafNo());
			model.addAttribute("districts", comsHelperDTO.getDistrict());
			model.addAttribute("mandals", comsHelperDTO.getMandal());
			model.addAttribute("villageList", comsHelperDTO.getTenantBusinessAreasList());
			model.addAttribute("lmoName", tenantName);
			model.addAttribute("lmoCode", tenantCode);
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getFlag());
			model.addAttribute("state", comsHelperDTO.getState());
			model.addAttribute("custType", enterpriseCustomerVO.getCustTypeLov());
			
			HttpSession session = request.getSession(false);
			session.setAttribute("cafThrough", "web");
			
			if (enterpriseCustomerVO.getDateOfIncorporation() != null) {
				if (!enterpriseCustomerVO.getDateOfIncorporation().isEmpty()) {
					entDate = DateUtill.stringToDateString_Format(enterpriseCustomerVO.getDateOfIncorporation());
					model.addAttribute("entDate", entDate);
				}
			} else if (comsHelperDTO.getCustomer().getDateofinc() != null) {
				model.addAttribute("entDate", comsHelperDTO.getCustomer().getDateofinc());
			} else {
				model.addAttribute("entDate", "");
			}
			logger.info("ComsController :: getProducts() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: getProducts() :: " + e);
			e.printStackTrace();
		} finally {
			comsHelperDTO = null;
			httpEntity = null;
			url = null;
			tenantCode = null;
			tenantName = null;
			response = null;
		}
		return "createcustomer";
	}

	@RequestMapping(value = "/showcustomers", method = RequestMethod.GET)
	public String showCustomersPage(Model model, HttpServletRequest request) {
		return "showcustomer";
	}
	
	@RequestMapping(value = "/showCustomersPagination", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UsersDataPageObject showCustomersPagination(HttpServletRequest request, @RequestParam(value = "custType", required = false) String custType, 
			@RequestParam(value = "custId", required = false) String custId ) {
		String tenantCode = "";
		HttpEntity<ComsHelperDTO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		UsersDataPageObject usersDataPageObject = new UsersDataPageObject();
		try {
			Integer pageDisplayLength = 10;
			Integer pageNumber = 1;
			String sdir = "desc";
			String sortColumn = "";
			int sortParameter = 0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			if (request.getParameter("iSortCol_0") != null) {
				sortParameter = Integer.parseInt(request.getParameter("iSortCol_0"));
			}
			sortColumn = Customer.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
			if (request.getParameter("sSortDir_0") != null)
				sdir = request.getParameter("sSortDir_0");

			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			PageObject pageObject = new PageObject();
			pageObject.setMinSize((pageNumber - 1) * pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			comsHelperDTO.setPageObject(pageObject);
			comsHelperDTO.setTenantCode(tenantCode);
			comsHelperDTO.setTenanttype(tenantType);
			comsHelperDTO.setCustType(custType);
			comsHelperDTO.setCustId(custId);

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), comsHelperDTO);
			url = ipAddressValues.getComURL() + "showCustomersPage";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			usersDataPageObject.setAaData(comsHelperDTO.getCustList());
			usersDataPageObject.setiTotalDisplayRecords(Long.parseLong(comsHelperDTO.getCount()));
		} catch (Exception e) {
			logger.info("ComsController :: showCustomersPage() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}

		return usersDataPageObject;
	}
	
	@RequestMapping(value = "/viewcustomers", method = RequestMethod.GET)
	public String viewcustomersPage(Model model,
			HttpServletRequest request) {
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: viewCustomerDistrictPage() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "multiactionsearch?tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("statusCodes", comsHelperDTO.getStatusCodesList());
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("CustomerTypeList", comsHelperDTO.getCustomerTypeList());
			model.addAttribute("CustomerSubTypeList", comsHelperDTO.getCustomerSubTypeList());
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: viewCustomerDistrictPage() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: viewCustomerDistrictPage() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}


		return "viewcustomer";
	}
	
	@RequestMapping(value = "/viewcustomersPagination", method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody PaginationObject<CustomerCafBO> viewcustomersPagination(
			@RequestParam(value="month", required = false) String month,
			@RequestParam(value="year", required = false) String year,MultiAction multiAction,HttpServletRequest request) {
		String tenantCode = "";
		HttpEntity<ComsHelperDTO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		PaginationObject<CustomerCafBO> cafDataPageObject = new PaginationObject<CustomerCafBO>();

		try {
			logger.info("ComsController :: viewcustomersPage() :: START");

			Integer pageDisplayLength = 10;
			Integer pageNumber = 1;
			String sdir = "desc";
			String sortColumn = "";
			int sortParameter = 0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			if (request.getParameter("iSortCol_0") != null) {
				sortParameter = Integer.parseInt(request.getParameter("iSortCol_0"));
			}
			sortColumn = CustomerCafBO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
			if (request.getParameter("sSortDir_0") != null)
				sdir = request.getParameter("sSortDir_0");

			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			PageObject pageObject = new PageObject();
			pageObject.setMinSize((pageNumber - 1) * pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			comsHelperDTO.setPageObject(pageObject);
			comsHelperDTO.setTenantCode(tenantCode);
			comsHelperDTO.setTenanttype(tenantType);
			
			if(!(month==null))
			{
				comsHelperDTO.setUsageMM(month);

			}
			if(!(year==null))
			{
				comsHelperDTO.setUsageYYYY(year);

			}


			if(!(multiAction.getDistrict()==null))
			{
			comsHelperDTO.setDistrictid(multiAction.getDistrict());
//			comsHelperDTO.setLmocode1(multiAction.getLmoCode());
			}
			if(!(multiAction.getLmoCode()==null))
			{
				comsHelperDTO.setLmocode1(multiAction.getLmoCode());

			}
			if(!(multiAction.getMsoCode()==null))
			{

				comsHelperDTO.setMsoCode(multiAction.getMsoCode());

			}
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), comsHelperDTO);
			url = ipAddressValues.getComURL() + "viewcustomersPage";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			cafDataPageObject.setAaData(comsHelperDTO.getCafsList());
			cafDataPageObject.setiTotalDisplayRecords(Long.parseLong(comsHelperDTO.getCount()));
		} catch (Exception e) {
			logger.info("ComsController :: viewcustomersPage() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return cafDataPageObject;
	}
	
	@RequestMapping(value = "/cafdetails", method = RequestMethod.POST)
	public String getProducts(Model model, @RequestParam(value = "custId", required = false) Long custId,
			@RequestParam(value = "custType", required = false) String custType, HttpServletRequest request) {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		Customer customer = new Customer();
		List<Object[]> cafDaoList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			logger.info("ComsController :: getProducts() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "cafdetails?custId=" + custId +"&custType="+custType;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			customer = comsHelperDTO.getCustomer();
			cafDaoList = comsHelperDTO.getCafDaoList();
			model.addAttribute("cafDaoList", cafDaoList);
			model.addAttribute("customers", customer);
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: getProducts() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getProducts() :: " + e);
			e.printStackTrace();
		} finally {
			comsHelperDTO = null;
			customer = null;
			cafDaoList = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return "cafdetails";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getCafProducts", method = RequestMethod.GET)
	@ResponseBody
	public List<CafProducts> getCafProducts(Model model, @RequestParam(value = "cafNo", required = false) Long cafNo, HttpServletRequest request) {

		List<CafProducts> cafProductList = new ArrayList<CafProducts>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getCafProducts() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getCafProducts?cafNo=" + cafNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			cafProductList = response.getBody();
			logger.info("ComsController :: getCafProducts() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: getCafProducts() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return cafProductList;
	}

	@RequestMapping(value = "/createPaymentDetails", method = RequestMethod.POST)
	public String createPaymentDetails(Model model, PaymentVO paymentVO, HttpServletRequest request) {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpSession session;
		CustomerCafVO customerCafVO = new CustomerCafVO();
		String loginID = "";
		String tenantCode = "";
		String tenantType = "";
		HttpEntity<PaymentVO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		String cafThrough = (String) request.getSession(false).getAttribute("cafThrough");	
		try {
			logger.info("ComsController :: createPaymentDetails() :: Start");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			if (paymentVO.getPayment() == null) {
				session = request.getSession(false);
				customerCafVO = (CustomerCafVO) session.getAttribute("customerCafVOObject");
				customerCafVO.setTenantType(tenantType);
			}
			paymentVO.setTenantCode(tenantCode);
			paymentVO.setLoginId(loginID);
			paymentVO.setIpAddress(httpServletRequest.getRemoteAddr());
			paymentVO.setCustomerCafVO(customerCafVO);
			paymentVO.setTenantType(tenantType);
			
			if(cafThrough!= null && cafThrough.equals("web"))
			paymentVO.setSource(cafThrough);
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), paymentVO);
			url = ipAddressValues.getComURL() + "createPaymentDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			logger.info("ComsController :: createPaymentDetails() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: createPaymentDetails() :: " + e);
			e.printStackTrace();
		} finally {
			session = null;
			customerCafVO = null;
			loginID = null;
			tenantCode = null;
			tenantType = null;
			response = null;
		}
		if(Objects.isNull(paymentVO.getPayment()) && Objects.isNull(paymentVO.getCustomerCafVO().getChangePkgFlag())) {
			if (paymentVO.getCustType().equalsIgnoreCase("ENTERPRISE")) {
				return "redirect:/getEnterPriseList?flag=" + comsHelperDTO.getFlag();
			} else {
				return "redirect:/getCustomerList?flag=" + comsHelperDTO.getFlag();
			}
		} else if(Objects.nonNull(paymentVO.getCustomerCafVO().getChangePkgFlag())) {
			return "redirect:/multiactionsearch?flag=" + comsHelperDTO.getFlag();
		} else {
			return "redirect:/comsHome?flag=" + comsHelperDTO.getFlag();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getEnterPriseList", method = RequestMethod.GET)
	public String getEnterPriseList(Model model, @RequestParam(value = "flag", required = false) String flag, HttpServletRequest request) {

		List<Object[]> enterpriseCustomerList = new ArrayList<>();
		String tenantCode = "";
		String tenantType = "";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getEnterPriseList() :: Start");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getEnterPriseList?tenantCode=" + tenantCode + "&tenantType="+ tenantType;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			enterpriseCustomerList = response.getBody();
			logger.info("ComsController :: getEnterPriseList() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getEnterPriseList() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		if (!tenantType.equalsIgnoreCase(CafEnumCodes.SI_Tenant_Type.getCode())) {
			model.addAttribute("customerList", enterpriseCustomerList);
			model.addAttribute("message", flag);
			return "enterprisecustomer";
		} else {
			model.addAttribute("message", flag);
			return "comsHome";
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getCustomerList", method = RequestMethod.GET)
	public String getCustomerList(Model model, @RequestParam(value = "flag", required = false) String flag,
			HttpServletRequest request) {

		List<Object[]> customerList = new ArrayList<>();
		String tenantCode = "";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getEnterPriseList() :: Start");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getCustomerList?tenantCode=" + tenantCode+"&tenantType="+tenantType;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			customerList = response.getBody();
			logger.info("ComsController :: getEnterPriseList() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getEnterPriseList() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		model.addAttribute("customerList", customerList);
		model.addAttribute("message", flag);
		return "showcustomer";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/searchCafDetails", method = RequestMethod.GET)
	public @ResponseBody PaginationObject<CafAndCpeChargesVO> searchCafProductsDetails(HttpServletRequest request, MultiAction multiAction) {

		PaginationObject<CafAndCpeChargesVO> multiDataPageObject= new PaginationObject<>();
		String loginID = "";
		String tenantCode = "";
		String tenantType = "";
		HttpEntity<SearchDataVO> httpEntity;
		String url = "";
		ResponseEntity<PaginationObject> response;
		try {
			logger.info("ComsController :: searchCafProductsDetails() :: Start");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			multiAction.setTenantCode(tenantCode);
			multiAction.setLoginId(loginID);
			multiAction.setTenantType(tenantType);
			
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
	    	sortColumn = MultiAction.MultiActionColumn.getColumns("COLUMN_"+sortParameter);
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
			
			SearchDataVO searchDataVO = new SearchDataVO();
			searchDataVO.setMultiAction(multiAction);
			searchDataVO.setPageObject(pageObject);
			//
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), searchDataVO);
			url = ipAddressValues.getComURL() + "searchCafDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, PaginationObject.class);
			multiDataPageObject = response.getBody();
		} catch (Exception e) {
			logger.error("ComsController :: searchCafProductsDetails() :: " + e);
			e.printStackTrace();
		} finally {
			loginID = null;
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return multiDataPageObject;
	}

	@RequestMapping(value = "/searchMonthlyPaymentCafDetails", method = RequestMethod.POST)
	public String searchMonthlyPaymentCafDetails(Model model, MonthlyPayment monthlyPayment, HttpServletRequest request) {
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String tenantCode = "";
		String tenantName = "";
		String tenantType = "";
		String loginID = "";
		HttpEntity<MonthlyPayment> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: searchMonthlyPaymentCafDetails() :: Start");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantName = (String) request.getSession(false).getAttribute("tenantname");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			monthlyPayment.setLoginId(loginID);
			monthlyPayment.setTenantCode(tenantCode);
			monthlyPayment.setTenantName(tenantName);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), monthlyPayment);
			url = ipAddressValues.getComURL() + "searchMonthlyPaymentCafDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			if (comsHelperDTO.getMonthlyPaymentList().size() > 0) {
				model.addAttribute("monthlyPaymentList", comsHelperDTO.getMonthlyPaymentList());
				model.addAttribute("message", "");
			} else {
				model.addAttribute("message", "No records found for this search criteria.");
			}
			model.addAttribute("monthlyPaymentVO", comsHelperDTO.getMonthlyPayment());
			model.addAttribute("lmoName", comsHelperDTO.getLmoName());
			model.addAttribute("lmoCode", comsHelperDTO.getLmoCode());
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("paymentList", comsHelperDTO.getPaymentList());

			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getFlag());
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: searchMonthlyPaymentCafDetails() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: searchMonthlyPaymentCafDetails() :: " + e);
			e.printStackTrace();
		} finally {
			comsHelperDTO = null;
			tenantCode = null;
			tenantName = null;
			loginID = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return "monthlypayment";
	}
	@RequestMapping(value = "/searchBulkMonthlyPaymentCafDetails", method = RequestMethod.POST)
	public String searchBulkMonthlyPaymentCafDetails(Model model, MonthlyPayment monthlyPayment, HttpServletRequest request) {
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String tenantCode = "";
		String tenantName = "";
		String tenantType = "";
		String loginID = "";
		HttpEntity<MonthlyPayment> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: searchMonthlyPaymentCafDetails() :: Start");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantName = (String) request.getSession(false).getAttribute("tenantname");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			//String from=(String)request.getParameter("effectivefrom");
			//String to=(String)request.getParameter("effectiveto");
			
			String invmn=(String)request.getParameter("invmn");
			String invyr=(String)request.getParameter("invyr");
			String from=invyr.concat("-").concat(invmn);
			String count=request.getParameter("hitcount").toString();
			monthlyPayment.setLoginId(loginID);
			monthlyPayment.setTenantCode(tenantCode);
			monthlyPayment.setTenantName(tenantName);
			monthlyPayment.setFrom(from);
			//monthlyPayment.setTo(to);
			
			monthlyPayment.setInvmn(invmn);
			monthlyPayment.setInvyr(invyr);
		    monthlyPayment.setOffset(Integer.parseInt(count)+1);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), monthlyPayment);
			url = ipAddressValues.getComURL() + "searchBulkMonthlyPaymentCafDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			BulkCustomerDTO[] myobj=comsHelperDTO.getClist();
			/*if (comsHelperDTO.getMonthlyPaymentList().size() > 0) {
				model.addAttribute("monthlyPaymentList", comsHelperDTO.getMonthlyPaymentList());
				model.addAttribute("message", "");
			} else {
				model.addAttribute("message", "No records found for this search criteria.");
			}*/
			//model.addAttribute("effectivefrom",from);
			
			
			model.addAttribute("invmn",invmn);
			model.addAttribute("invyr",invyr);
			model.addAttribute("month",invmn);
			model.addAttribute("year",invyr);
			
			model.addAttribute("monthlyPaymentVO", comsHelperDTO.getMonthlyPayment());
			model.addAttribute("lmoName", comsHelperDTO.getLmoName());
			model.addAttribute("lmoCode", comsHelperDTO.getLmoCode());
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("paymentList", comsHelperDTO.getPaymentList());
			model.addAttribute("UsedAmount", comsHelperDTO.getUsedAmount());
			model.addAttribute("clist",myobj);
			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getFlag());
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: searchMonthlyPaymentCafDetails() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: searchMonthlyPaymentCafDetails() :: " + e);
			e.printStackTrace();
		} finally {
			comsHelperDTO = null;
			tenantCode = null;
			tenantName = null;
			loginID = null;
			httpEntity = null;
			url = null;
			response = null;
		}
			return "MonthlyBulkPayment";
	}
	
	@RequestMapping(value = "/MonthlyBulkPayment", method = RequestMethod.GET)
	public String MonthlyBulkPaymentCafDetails(Model model,ComsHelperDTO  comshelperdto, HttpServletRequest request) {
		HttpEntity<ComsHelperDTO> httpEntity;
		String loginId = (String) request.getSession(false).getAttribute("loginID");
		String url = "";
		//String from=(String)request.getParameter("effectivefrom");
		String invmn=(String)request.getParameter("invmn");
		String invyr=(String)request.getParameter("invyr");
		String from=invyr.concat("-").concat(invmn);
		ResponseEntity<ComsHelperDTO> response;
		String[] values=request.getParameterValues("selectbox");
		ComsHelperDTO comsHelperDTO=new ComsHelperDTO();
		comsHelperDTO.setLmoCode(loginId);
		comsHelperDTO.setBulkcustlist(values);
		comsHelperDTO.setEffectiveDate(from);
		httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), comsHelperDTO);
		url = ipAddressValues.getComURL() + "createBulkPaymentDetails";
		response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
		comsHelperDTO = response.getBody();
		model.addAttribute("message", comsHelperDTO.getFlag());
		return "MonthlyBulkPayment";
	}

	@RequestMapping(value = "/createCustomerCafDetails", method = RequestMethod.POST)
	public String createCustomerCafDetails(Model model, CustomerCafVO customerCafVO, HttpServletRequest request)
			throws Exception {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String loginID = "";
		String tenantCode = "";
		String tenantName = "";
		String tenantType = "";
		HttpEntity<CustomerCafVO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: createCustomerCafDetails() :: Start");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantName = (String) request.getSession(false).getAttribute("tenantname");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			customerCafVO.setCity(customerCafVO.getCity().split(",")[0]);
			customerCafVO.setLoginId(loginID);
			customerCafVO.setLmoCode(tenantCode);
			customerCafVO.setTenantType(tenantType);
			customerCafVO.setIpAddress(httpServletRequest.getRemoteAddr());
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), customerCafVO);
			url = ipAddressValues.getComURL() + "createCustomerCafDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("cafNo", customerCafVO.getCafNo());
			HttpSession session = request.getSession(false);
			session.setAttribute("productList", comsHelperDTO.getProductsList());

			model.addAttribute("oltList", comsHelperDTO.getOltList());
			model.addAttribute("popList", comsHelperDTO.getSubStationsList());
			model.addAttribute("cpeModalList", comsHelperDTO.getCpeModalsList());
			model.addAttribute("stbModelList", comsHelperDTO.getStbModelsList());
			model.addAttribute("baseProductList", comsHelperDTO.getBaseProductList());
			model.addAttribute("addOnProductList", comsHelperDTO.getAddOnProductList());
			model.addAttribute("oneTimeProductList", comsHelperDTO.getOneTimeProductList());
			model.addAttribute("customerCafVO", comsHelperDTO.getCustomerCafVO());
			model.addAttribute("message", comsHelperDTO.getMessage());
			model.addAttribute("lmoName", tenantName);
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getFlag());
			model.addAttribute("lmoAgrmntMspCodes", comsHelperDTO.getLmoAgrmntMspCodes());
			model.addAttribute("tenantType", tenantType);
			model.addAttribute("message", comsHelperDTO.getMessage());
			model.addAttribute("customerList", comsHelperDTO.getCustomerList());
			model.addAttribute("telephoneConnections", comsHelperDTO.getTelephoneConnectionsList());

			logger.info("ComsController :: createCustomerCafDetails() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: createCustomerCafDetails() :: " + e);
			e.printStackTrace();
		} finally {
			loginID = null;
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		if (comsHelperDTO.getCustomer() != null	&& customerCafVO.getCustType().equalsIgnoreCase(CafEnumCodes.CUST_TYPE_CODE.getCode())) {
			return "selectpackages";
		} else if (comsHelperDTO.getCustomer() == null && customerCafVO.getCustType().equalsIgnoreCase(CafEnumCodes.ENTCUST_TYPE_CODE.getCode())) {
			return "selectpackages";
		} else {
			return "showcustomer";
		}
	}

	@RequestMapping(value = "/saveLMOCafDetails", method = RequestMethod.POST)
	public String saveLMOCafDetails(Model model, CustomerCafVO customerCafVO, HttpServletRequest request)throws Exception {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String loginID = "";
		String tenantType = "";
		HttpEntity<CustomerCafVO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: createCustomerCafDetails() :: Start");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			customerCafVO.setLoginId(loginID);
			customerCafVO.setTenantType(tenantType);
			customerCafVO.setIpAddress(httpServletRequest.getRemoteAddr());
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), customerCafVO);
			url = ipAddressValues.getComURL() + "saveLMOCafDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();

			model.addAttribute("flag", comsHelperDTO.getMessage());
			model.addAttribute("statusCodes", comsHelperDTO.getStatusCodesList());

			logger.info("ComsController :: createCustomerCafDetails() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: createCustomerCafDetails() :: " + e);
			e.printStackTrace();
		} finally {
			loginID = null;
			tenantType = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return "multiaction";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createPackages", method = RequestMethod.POST)
	public String createSelectedPackages(Model model, CustomerCafVO customerCafVO, HttpServletRequest request) throws Exception {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String loginID = "";
		String tenantCode = "";
		String tenantName = "";
		HttpSession session;
		List<ProductsVO> productList = new ArrayList<>();
		HttpEntity<CustomerCafVO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: createSelectedPackages() :: Start");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantName = (String) request.getSession(false).getAttribute("tenantname");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			session = request.getSession(false);
			productList = (List<ProductsVO>) session.getAttribute("productList");
			customerCafVO.setLoginId(loginID);
			customerCafVO.setLmoCode(tenantCode);
			customerCafVO.setTenantType(tenantType);
			customerCafVO.setProducts(productList);
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), customerCafVO);
			url = ipAddressValues.getComURL() + "createPackages";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			session = request.getSession(false);
			session.setAttribute("customerCafVOObject", comsHelperDTO.getCustomerCafVO());
			model.addAttribute("productList", comsHelperDTO.getCafProductsList());
			model.addAttribute("customerCafVOObject", comsHelperDTO.getCustomerCafVO());
			model.addAttribute("charge", comsHelperDTO.getTotalAmount());
			model.addAttribute("cpeChargesObject", comsHelperDTO.getCpeInformationVO());
			model.addAttribute("lmoCode", tenantCode);
			model.addAttribute("lmoName", tenantName);
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("paymentList", comsHelperDTO.getPaymentList());
			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getCustomerCafVO().getFlag());
			model.addAttribute("effectiveDate", comsHelperDTO.getEffectiveDate());
			model.addAttribute("cpeModel", comsHelperDTO.getCpeModel());
			model.addAttribute("stbModel", comsHelperDTO.getStbModel());
			model.addAttribute("custBal", comsHelperDTO.getCustBal());
			model.addAttribute("secDeposit", comsHelperDTO.getSecDeposit());
			model.addAttribute("pkgTypeFlag", comsHelperDTO.getCustomerCafVO().getChangePkgFlag());
			model.addAttribute("tenantType", comsHelperDTO.getCustomerCafVO().getTenantType());
			logger.info("ComsController :: createSelectedPackages() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: createSelectedPackages() :: " + e);
			e.printStackTrace();
		} finally {
			comsHelperDTO = null;
			loginID = null;
			tenantCode = null;
			tenantName = null;
			session = null;
			productList = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return "createpayment";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getCafBulkUploadWorkorderForm", method = RequestMethod.POST)
	public String getCafBulkUploadWorkorderForm(Model model, CustomerCafVO customerCafVO, HttpServletRequest request) throws Exception {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String loginID = "";
		String tenantCode = "";
		String tenantName = "";
		HttpSession session;
		HttpEntity<CustomerCafVO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: getCafBulkUploadWorkorderForm() :: Start");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantName = (String) request.getSession(false).getAttribute("tenantname");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			session = request.getSession(false);
			List<ProductsVO> productList = (List<ProductsVO>) session.getAttribute("productList");
			customerCafVO.setLoginId(loginID);
			customerCafVO.setLmoCode(tenantCode);
			customerCafVO.setTenantType(tenantType);
			customerCafVO.setProducts(productList);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), customerCafVO);
			url = ipAddressValues.getComURL() + "getCafBulkUploadWorkorderForm";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			session = request.getSession(false);
			session.setAttribute("customerCafVOObject", comsHelperDTO.getCustomerCafVO());
			model.addAttribute("productList", comsHelperDTO.getCafProductsList());
			model.addAttribute("customerCafVOObject", comsHelperDTO.getCustomerCafVO());
			model.addAttribute("charge", comsHelperDTO.getTotalAmount());
			model.addAttribute("cpeChargesObject", comsHelperDTO.getCpeInformationVO());
			model.addAttribute("lmoCode", tenantCode);
			model.addAttribute("lmoName", tenantName);
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("paymentList", comsHelperDTO.getPaymentList());
			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getFlag());
			model.addAttribute("effectiveDate", comsHelperDTO.getEffectiveDate());
			model.addAttribute("cpeModel", comsHelperDTO.getCpeModel());
			model.addAttribute("stbModel", comsHelperDTO.getStbModel());
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: getCafBulkUploadWorkorderForm() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: getCafBulkUploadWorkorderForm() :: " + e);
			e.printStackTrace();
		} finally {
			comsHelperDTO = null;
			loginID = null;
			tenantCode = null;
			tenantName = null;
			session = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return "createpayment";
	}

	@RequestMapping(value = "/getAadharDetails", method = RequestMethod.GET)
	@ResponseBody
	public AadharDTO getAadharDetails(Model model, @RequestParam(value = "aadharNumber") String aadharNumber,
			HttpServletRequest request) {

		AadharDTO aadharInfo = new AadharDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<AadharDTO> response;
		try {
			logger.info("ComsController :: getAadharDetails() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getAadharDetails?aadharNumber=" + aadharNumber;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, AadharDTO.class);
			aadharInfo = response.getBody();
			logger.info("ComsController :: getAadharDetails() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: getAadharDetails() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return aadharInfo;
	}

	@RequestMapping(value = "/getAllCafDetails", method = RequestMethod.GET)
	@ResponseBody
	public CafDetailsVO getAllCafDetails(@RequestParam(value = "cafNo") Long cafNo,
			@RequestParam(value = "custId", required = false) String custId) {

		CafDetailsVO cafDetailsVO = new CafDetailsVO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<CafDetailsVO> response;
		try {
			logger.info("ComsController :: getAllCafDetails() :: Start");
			logger.info("ComsController :: getAllCafDetails() Cafno "+cafNo);
			logger.info("ComsController :: getAllCafDetails() Custid "+custId);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getAllCafDetails?cafNo=" + cafNo + "&custId=" + custId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CafDetailsVO.class);
			cafDetailsVO = response.getBody();
			logger.info("ComsController :: getAllCafDetails() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getAllCafDetails() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
		}
		return cafDetailsVO;
	}

	@RequestMapping(value = "/getFingerPrintDetails", method = RequestMethod.POST)
	@ResponseBody
	public String getFingerPrintDetails(@RequestBody FingerPrintJson fingerPrintJson, HttpServletRequest request) {
		String status = "";
		String loginID = "";
		HttpEntity<FingerPrintJson> httpEntity;
		ResponseEntity<String> response;
		String url = "";
		try {
			logger.info("ComsController :: getFingerPrintDetails() :: Start");
			loginID = (String) request.getSession(false).getAttribute("loginID");
			fingerPrintJson.setLoginId(loginID);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), fingerPrintJson);
			url = ipAddressValues.getComURL() + "getFingerPrintDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: getFingerPrintDetails() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getFingerPrintDetails() :: " + e);
			e.printStackTrace();
		} finally {
			loginID = null;
			httpEntity = null;
			response = null;
			url = null;
		}
		return status;
	}

	@RequestMapping(value = "/DeActivateCaf")
	@ResponseBody
	public String DeActivateCaf(@RequestParam(value = "cafNo", required = false) Long cafNo) {
		String status = "";
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: DeActivateCaf() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "DeActivateCaf?cafNo=" + cafNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: DeActivateCaf() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: DeActivateCaf() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return gson.toJson(status);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getFeatureParamsByFeatureCodes")
	@ResponseBody
	public List<FeatureParamsVO> getFeatureParamsByFeatureCodes(
			@RequestParam(value = "featureCodes", required = false) String featureCodes) {

		List<FeatureParamsVO> featureParamList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getFeatureParamsByFeatureCodes() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getFeatureParamsByFeatureCodes?featureCodes=" + featureCodes;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			featureParamList = response.getBody();
			logger.info("ComsController :: getFeatureParamsByFeatureCodes() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getFeatureParamsByFeatureCodes() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return featureParamList;
	}

	@RequestMapping(value = "/getDistrictByDistrictId")
	@ResponseBody
	public Districts getDistrictByDistrictId(@RequestParam(value = "districtId", required = false) Integer districtId) {
		Districts districts = new Districts();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<Districts> response;
		try {
			logger.info("ComsController :: getDistrictByDistrictId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getDistrictByDistrictId?districtId=" + districtId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Districts.class);
			districts = response.getBody();
			logger.info("ComsController :: getDistrictByDistrictId() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getDistrictByDistrictId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return districts;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getMandalsByDistrictId")
	@ResponseBody
	public List<Mandals> getMandalsByDistrictId(Model model,
			@RequestParam(value = "districtId", required = false) Integer districtId) {

		List<Mandals> mandalsList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getMandalsByDistrictId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getMandalsByDistrictId?districtId=" + districtId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			mandalsList = response.getBody();
			logger.info("ComsController :: getMandalsByDistrictId() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getMandalsByDistrictId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return mandalsList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getSILMOMandals")
	@ResponseBody
	public List<Mandals> getSILMOMandals(Model model, @RequestParam(value = "district", required = false) String district, HttpServletRequest request) {

		List<Mandals> mandalsList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getMandalsByDistrictId() :: Start");
			String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getSILMOMandals?district="+district+"&tenantcode="+tenantcode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			mandalsList = response.getBody();
			logger.info("ComsController :: getMandalsByDistrictId() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getMandalsByDistrictId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return mandalsList;
	}

	@RequestMapping(value = "/getMandalsByDistrictIdAndMandalSrlNo")
	@ResponseBody
	public Mandals getMandalsByDistrictIdAndMandalSrlNo(
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "mandalSrlNo", required = false) Integer mandalSrlNo) {

		Mandals mandals = new Mandals();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<Mandals> response;
		try {
			logger.info("ComsController :: getMandalsByDistrictIdAndMandalSrlNo() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getMandalsByDistrictIdAndMandalSrlNo?districtId=" + districtId+ "&mandalSrlNo=" + mandalSrlNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Mandals.class);
			mandals = response.getBody();
			logger.info("ComsController :: getMandalsByDistrictIdAndMandalSrlNo() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getMandalsByDistrictIdAndMandalSrlNo() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return mandals;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getVillagesByDistrictIdAndMandalId")
	@ResponseBody
	public List<Villages> getVillagesByDistrictIdAndMandalId(Model model,
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "mandalId", required = false) Integer mandalId) {

		List<Villages> villagesList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getVillagesByDistrictIdAndMandalId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getVillagesByDistrictIdAndMandalId?districtId=" + districtId+ "&mandalId=" + mandalId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			villagesList = response.getBody();
			logger.info("ComsController :: getVillagesByDistrictIdAndMandalId() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getVillagesByDistrictIdAndMandalId() :: End" + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return villagesList;
	}

	@RequestMapping(value = "/getVillagesByVillageId")
	@ResponseBody
	public Villages getVillagesByVillageId(Model model,
			@RequestParam(value = "villageSlno", required = false) Integer villageSlno,
			@RequestParam(value = "mandalSlno", required = false) Integer mandalSlno,
			@RequestParam(value = "districtUid", required = false) Integer districtUid) {

		Villages villages = new Villages();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<Villages> response;
		try {
			logger.info("ComsController :: getVillagesByVillageId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getVillagesByVillageId?villageSlno=" + villageSlno + "&mandalSlno="+ mandalSlno + "&districtUid=" + districtUid;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Villages.class);
			villages = response.getBody();
			logger.info("ComsController :: getVillagesByVillageId() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getVillagesByVillageId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return villages;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getVillagesBySubstationId")
	@ResponseBody
	public List<Villages> getVillagesBySubstationId(Model model,
			@RequestParam(value = "subStnSlno", required = false) String subStnSlno,
			@RequestParam(value = "tenantCode", required = false) String tenantCode) {

		List<Villages> villagesList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getVillagesBySubstationId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getVillagesBySubstationId?subStnSlno=" + subStnSlno + "&tenantCode="+ tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			villagesList = response.getBody();
			logger.info("ComsController :: getVillagesBySubstationId() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getVillagesBySubstationId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return villagesList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getSubstationsByDistrictIdAndMandalId")
	@ResponseBody
	public String getSubstationsByDistrictIdAndMandalId(Model model,
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "mandalId", required = false) Integer mandalId) {

		List<Substations> substationsList = new ArrayList<>();
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getSubstationsByDistrictIdAndMandalId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getSubstationsByDistrictIdAndMandalId?districtId=" + districtId
					+ "&mandalId=" + mandalId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			substationsList = response.getBody();
			logger.info("ComsController :: getSubstationsByDistrictIdAndMandalId() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getSubstationsByDistrictIdAndMandalId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return gson.toJson(substationsList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getOLTSLNOBySubstationsrlno")
	@ResponseBody
	public String getOLTSLNOBySubstationsrlno(Model model,
			@RequestParam(value = "subStnSlno", required = false) Integer subStnSlno) {

		List<OLT> oltsList = new ArrayList<>();
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getOLTSLNOBySubstationsrlno() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getOLTSLNOBySubstationsrlno?subStnSlno=" + subStnSlno;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			oltsList = response.getBody();
			logger.info("ComsController :: getOLTSLNOBySubstationsrlno() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getOLTSLNOBySubstationsrlno() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return gson.toJson(oltsList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getOLTPopIdByOltSrlNo")
	@ResponseBody
	public String getOLTPopIdByOltSrlNo(Model model,
			@RequestParam(value = "oltSrlNo", required = false) String oltSrlNo,
			@RequestParam(value = "lmoCode", required = false) String lmoCode) {
		List<OLTPortDetails> oltPortIdsList = new ArrayList<>();
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getOLTPopIdByOltSrlNo() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getOLTPopIdByOltSrlNo?oltSrlNo=" + oltSrlNo + "&lmoCode=" + lmoCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			oltPortIdsList = response.getBody();
			logger.info("ComsController :: getOLTPopIdByOltSrlNo() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getOLTPopIdByOltSrlNo() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return gson.toJson(oltPortIdsList);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getVPNServicesByPopIdAndOltSrlNo")
	@ResponseBody
	public String getVPNServicesByPopIdAndOltSrlNo(Model model, @RequestParam(value = "oltSrlNo", required = false) String oltSrlNo,
			@RequestParam(value = "subStnId", required = false) String subStnId) {
		List<VPNSrvcNames> vpnSrvcNamesList = new ArrayList<>();
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getVPNServicesByPopIdAndOltSrlNo() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getVPNServicesByPopIdAndOltSrlNo?oltSrlNo=" + oltSrlNo + "&subStnId=" + subStnId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			vpnSrvcNamesList = response.getBody();
			logger.info("ComsController :: getVPNServicesByPopIdAndOltSrlNo() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getVPNServicesByPopIdAndOltSrlNo() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return gson.toJson(vpnSrvcNamesList);
	}

	@RequestMapping(value = "/getOLTPortSplitterData")
	@ResponseBody
	public SplitterVO getOLTPortSplitterData(Model model,
			@RequestParam(value = "oltSrlNo", required = false) String oltSrlNo,
			@RequestParam(value = "lmoCode", required = false) String lmoCode,
			@RequestParam(value = "oltPort", required = false) String oltPort,
			@RequestParam(value = "l1slot", required = false) String l1slot) {
		SplitterVO splitterVO = new SplitterVO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<SplitterVO> response;
		try {
			logger.info("ComsController :: getOLTPortSplitterData() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getOLTPortSplitterData?oltSrlNo=" + oltSrlNo + "&lmoCode=" + lmoCode+ "&oltPort=" + oltPort + "&l1slot=" + l1slot;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SplitterVO.class);
			splitterVO = response.getBody();
			logger.info("ComsController :: getOLTPortSplitterData() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getOLTPortSplitterData() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return splitterVO;
	}
	
	
	
	
	@RequestMapping(value = "/getOLTL3PortSplitterData")
	@ResponseBody
	public SplitterVO getOLTL3PortSplitterData(Model model,
			@RequestParam(value = "oltSrlNo", required = false) String oltSrlNo,
			@RequestParam(value = "lmoCode", required = false) String lmoCode,
			@RequestParam(value = "oltPort", required = false) String oltPort,
			@RequestParam(value = "l1L2slot", required = false) String l1L2slot) {
		SplitterVO splitterVO = new SplitterVO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<SplitterVO> response;
		try {
			logger.info("ComsController :: getOLTL3PortSplitterData() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getOLTL3PortSplitterData?oltSrlNo=" + oltSrlNo + "&lmoCode=" + lmoCode+ "&oltPort=" + oltPort + "&l1L2slot=" + l1L2slot;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SplitterVO.class);
			splitterVO = response.getBody();
			logger.info("ComsController :: getOLTL3PortSplitterData() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getOLTL3PortSplitterData() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return splitterVO;
	}
	
	
	

	@RequestMapping(value = "/checkSplitterValue")
	@ResponseBody
	public String checkSplitterValue(Model model, @RequestParam(value = "oltSrlNo", required = false) String oltSrlNo,
			@RequestParam(value = "portSlotValue", required = false) String portSlotValue,
			@RequestParam(value = "oltPort", required = false) String oltPort) {
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		String status = "false";
		try {
			logger.info("ComsController :: getOLTPortSplitterData() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "checkSplitterValue?oltSrlNo=" + oltSrlNo + "&oltPort=" + oltPort+ "&portSlotValue=" + portSlotValue;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: getOLTPortSplitterData() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getOLTPortSplitterData() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getOLTSLNOByDistrictIdAndMandalIdAndSubstationsrlno")
	@ResponseBody
	public String getOLTSLNOByDistrictIdAndMandalIdAndSubstationsrlno(
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "mandalId", required = false) Integer mandalId,
			@RequestParam(value = "subStnSlno", required = false) Integer subStnSlno) {
		List<OLT> oltsList = new ArrayList<>();
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getOLTSLNOByDistrictIdAndMandalIdAndSubstationsrlno() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getOLTSLNOByDistrictIdAndMandalIdAndSubstationsrlno?districtId="+ districtId + "&mandalId=" + mandalId + "&subStnSlno=" + subStnSlno;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			oltsList = response.getBody();
			logger.info("ComsController :: getOLTSLNOByDistrictIdAndMandalIdAndSubstationsrlno() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getOLTSLNOByDistrictIdAndMandalIdAndSubstationsrlno() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}

		return gson.toJson(oltsList);
	}
	
	@RequestMapping(value = "/getCpeEmiCountByCpeModel")
	@ResponseBody
	public String getCpeEmiCountByCpeModel(Model model, @RequestParam(value = "profileId", required = false) Long profileId ) {
		Cpecharges cpecharges = new Cpecharges();
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<Cpecharges> response;
		try {
			logger.info("ComsController :: getCpeEmiCountByCpeModel() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getCpeEmiCountByCpeModel?profileId="+profileId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Cpecharges.class);
			cpecharges = response.getBody();
			logger.info("ComsController :: getCpeEmiCountByCpeModel() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getCpeEmiCountByCpeModel() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return gson.toJson(cpecharges);
	}

	@RequestMapping(value = "/getCpeChargesByCpeModel")
	@ResponseBody
	public Cpecharges getCpeChargesByCpeModel(Model model,
			@RequestParam(value = "profileId", required = false) Long profileId, @RequestParam(value = "emiCount", required = false) String emiCount) {

		Cpecharges cpecharges = new Cpecharges();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<Cpecharges> response;
		try {
			logger.info("ComsController :: getCpeChargesByCpeModel() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getCpeChargesByCpeModel?profileId=" + profileId+"&emiCount="+emiCount;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Cpecharges.class);
			cpecharges = response.getBody();
			logger.info("ComsController :: getCpeChargesByCpeModel() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getCpeChargesByCpeModel() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return cpecharges;
	}

	@RequestMapping(value = "/getCpeAndIptvbosSrlNoCheck")
	@ResponseBody
	public CpeChargeDetailsBO getCpeAndIptvbosSrlNoCheck(@RequestParam(value = "cpeId", required = false) String cpeId,
			@RequestParam(value = "lmoCode", required = false) String lmoCode, 
			@RequestParam(value = "lmoAgrmntMspCodes", required = false) String lmoAgrmntMspCodes,
			@RequestParam(value = "cpetypelov", required = false) String cpetypelov) {
		CpeChargeDetailsBO cpeChargeDetailsBO = new CpeChargeDetailsBO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<CpeChargeDetailsBO> response;
		try {
			logger.info("ComsController :: getCpeAndIptvbosSrlNoCheck() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getCpeAndIptvbosSrlNoCheck?cpeId=" + cpeId + "&lmoCode=" + lmoCode+ "&lmoAgrmntMspCodes=" + lmoAgrmntMspCodes+ "&cpetypelov=" + cpetypelov;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CpeChargeDetailsBO.class);
			cpeChargeDetailsBO = response.getBody();
			logger.info("ComsController :: getCpeAndIptvbosSrlNoCheck() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getCpeAndIptvbosSrlNoCheck() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return cpeChargeDetailsBO;
	}

	@RequestMapping(value = "/enterpriseCustomer", method = RequestMethod.GET)
	public String enterpriseCustomersPage(Model model, HttpServletRequest request) {
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		try {
			logger.info("ComsController :: getCreateCustomerPage() :: Start");
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: getCreateCustomerPage() :: End");
		} catch (Exception e) {
			logger.error("EnterpriseCustomerController :: getCreateCustomerPage()" + e);
			e.printStackTrace();
		} finally {
		}
		return "enterprisecustomer";
	}
	
	@RequestMapping(value = "/enterpriseCustomersPagination", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PaginationObject<EnterpriseCustomerVO> enterpriseCustomersPagination(HttpServletRequest request) {
		String tenantCode = "";
		HttpEntity<ComsHelperDTO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		PaginationObject<EnterpriseCustomerVO> entCustPageObject = new PaginationObject<EnterpriseCustomerVO>();
		try {
			Integer pageDisplayLength = 10;
			Integer pageNumber = 1;
			String sdir = "desc";
			String sortColumn = "";
			int sortParameter = 0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			if (request.getParameter("iSortCol_0") != null) {
				sortParameter = Integer.parseInt(request.getParameter("iSortCol_0"));
			}
			sortColumn = EnterpriseCustomerVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
			if (request.getParameter("sSortDir_0") != null)
				sdir = request.getParameter("sSortDir_0");

			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			PageObject pageObject = new PageObject();
			pageObject.setMinSize((pageNumber - 1) * pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			comsHelperDTO.setPageObject(pageObject);
			comsHelperDTO.setTenantCode(tenantCode);
			comsHelperDTO.setTenanttype(tenantType);

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), comsHelperDTO);
			url = ipAddressValues.getComURL() + "enterpriseCustomersPage";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			entCustPageObject.setAaData(comsHelperDTO.getEntCustList());
			entCustPageObject.setiTotalDisplayRecords(Long.parseLong(comsHelperDTO.getCount()));
		} catch (Exception e) {
			logger.info("ComsController :: viewcustomersPage() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return entCustPageObject;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getEnterpriseSubTypes")
	@ResponseBody
	public String getEnterpriseSubTypes(Model model,
			@RequestParam(value = "custType", required = false) String custType) {

		List<Lovs> lovsList = new ArrayList<>();
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getEnterpriseSubTypes() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getEnterpriseSubTypes?custType=" + custType;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			lovsList = response.getBody();
			logger.info("ComsController :: getEnterpriseSubTypes() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getEnterpriseSubTypes() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return gson.toJson(lovsList);
	}

	@RequestMapping(value = "/createEntCustomer", method = RequestMethod.GET)
	public String getCreateEntCustomer(Model model, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		try {
			logger.info("ComsController :: getCreateEntCustomer() :: Start");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "createEntCustomer";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("name", tenantName);
			model.addAttribute("tenantType", tenantType);
			model.addAttribute("state", comsHelperDTO.getState());
			model.addAttribute("lovsList", comsHelperDTO.getCustomerTypeList());
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("mandalList", comsHelperDTO.getMandalList());
			model.addAttribute("billCycleList", comsHelperDTO.getBillCycleList());
			logger.info("ComsController :: getCreateEntCustomer() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: getCreateEntCustomer() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			comsHelperDTO = null;
		}
		return "createentcustomer";
	}

	@RequestMapping(value = "/nodeForm", method = RequestMethod.POST)
	public String showNodeForm(Model model, @RequestParam(value = "customerCode", required = false) String customerCode,
			@RequestParam(value = "custType", required = false) String custType,
			@RequestParam(value = "emailId", required = false) String emailId,
			@RequestParam(value = "billCycle", required = false) String billCycle,
			@RequestParam(value = "officeTypeLov", required = false) String officeTypeLov,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "dateOfIncorporation", required = false) String dateOfIncorporation,
			HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		Date dateOfInc=new Date();
		try {
			logger.info("ComsController :: showNodeForm() :: Start");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "nodeForm?customerCode=" + customerCode + "&custType=" + custType+ "&emailId=" + emailId + "&billCycle=" + billCycle + "&officeTypeLov=" + officeTypeLov;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();

			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("mandalList", comsHelperDTO.getMandalList());
			model.addAttribute("name", tenantName);
			model.addAttribute("billCycle", comsHelperDTO.getBillCycle());
			model.addAttribute("customerCode", comsHelperDTO.getCustomerCode());
			model.addAttribute("custType", comsHelperDTO.getCustType());
			model.addAttribute("emailId", comsHelperDTO.getEmailId());
			model.addAttribute("officeTypeLov", comsHelperDTO.getOfficeTypeLov());
			model.addAttribute("orgCode", orgCode);
			if (dateOfIncorporation != null) {
				if (!dateOfIncorporation.isEmpty()) {
					dateOfInc = DateUtill.stringToDateString_Format(dateOfIncorporation);
					model.addAttribute("dateOfIncorporation", dateOfInc);
				}
			}
			logger.info("ComsController :: showNodeForm() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: showNodeForm() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			comsHelperDTO = null;
		}
		return "createnode";
	}

	@RequestMapping(value = "/showentcustomer", method = RequestMethod.POST)
	public String showEntCustomer(Model model, EnterpriseCustomerVO enterpriseCustomerVO, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: showEntCustomer() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			enterpriseCustomerVO.setTenantCode(tenantCode);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "showentcustomer?pmntResFlag="+enterpriseCustomerVO.getPaymentResponsible()+"&custId="+enterpriseCustomerVO.getCustId();
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			String pmntCustomerId = response.getBody();
			enterpriseCustomerVO.setCustCode(pmntCustomerId);
			model.addAttribute("enterpriseCustomerVO", enterpriseCustomerVO);
			logger.info("ComsController :: showEntCustomer() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: showEntCustomer() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return "showcustomer";
	}

	@RequestMapping(value = "/bulkUploadCafs", method = RequestMethod.POST)
	public String bulkUploadCafs(Model model, EnterpriseCustomerVO enterpriseCustomerVO, HttpServletRequest request) {

		HttpEntity<EnterpriseCustomerVO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		try {
			logger.info("ComsController :: bulkUploadCafs() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			enterpriseCustomerVO.setTenantCode(tenantCode);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), enterpriseCustomerVO);
			url = ipAddressValues.getComURL() + "bulkUploadCafs";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			HttpSession session = request.getSession(false);
			session.setAttribute("productList", comsHelperDTO.getProductsList());

			model.addAttribute("baseProductList", comsHelperDTO.getBaseProductList());
			model.addAttribute("oneTimeProductList", comsHelperDTO.getOneTimeProductList());
			model.addAttribute("addOnProductList", comsHelperDTO.getAddOnProductList());
			model.addAttribute("enterpriseCustomerVO", enterpriseCustomerVO);
			model.addAttribute("tenantTypeList", comsHelperDTO.getTenantType());
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("lmoName", tenantName);
			logger.info("ComsController :: showEntCustomer() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: showEntCustomer() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return "bulkupload";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveBulkCafUpload", method = RequestMethod.POST)
	public String saveBulkCafUpload(Model model, CustomerCafVO customerCafVO, HttpServletRequest request) {
		HttpEntity<CustomerCafVO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		List<Caf> cafList = new ArrayList<>();
		HttpSession session;
		List<ProductsVO> productList = new ArrayList<>();
		try {
			logger.info("ComsController :: bulkUploadCafs() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			String loginID = (String) request.getSession(false).getAttribute("loginID");
			if (customerCafVO.getBulkUpload().equalsIgnoreCase("MultipleCafs")) {
				MultipartFile file = customerCafVO.getBulkUploadFile();
				if (file != null && !file.isEmpty()) {
					String name = file.getOriginalFilename();
					String ext = FilenameUtils.getExtension(name);
					if (ext != null && (ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("xls")) && ext != "") {
						cafList = ReadXlsFileForCafs.BulkUploadExcelFile(file, ext);

						customerCafVO.setFileName(name);
						customerCafVO.setFileSize(String.valueOf(customerCafVO.getBulkUploadFile().getSize()));
						Map<String, String> recordDetails = ReadXlsFileForCafs.getNumberOfColsAndRows(customerCafVO.getBulkUploadFile(), ext);
						customerCafVO.setNoOfCols(recordDetails.get("noOfCols"));
						customerCafVO.setNoOfRows(recordDetails.get("noOfRows"));
					}
				}
			} else if (customerCafVO.getBulkUpload().equalsIgnoreCase("SingalCaf")) {
				cafList = ReadXlsFileForCafs.BulkUploadCafList(customerCafVO);
			}
			customerCafVO.setCafList(cafList);
			session = request.getSession(false);
			productList = (List<ProductsVO>) session.getAttribute("productList");
			customerCafVO.setProducts(productList);
			customerCafVO.setLoginId(loginID);
			customerCafVO.setLmoCode(tenantCode);
			customerCafVO.setIpAddress(httpServletRequest.getRemoteAddr());
			customerCafVO.setTenantType(tenantType);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), customerCafVO);
			url = ipAddressValues.getComURL() + "saveBulkCafUpload";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("customerList", comsHelperDTO.getEnterpriseCustomerList());
			model.addAttribute("message", comsHelperDTO.getMessage());
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: saveBulkCafUpload() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: saveBulkCafUpload() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			cafList = null;
			productList = null;
		}
		return "enterprisecustomer";
	}

	@RequestMapping(value = "/updateEntCustomer", method = RequestMethod.POST)
	public String updateEntCustomer(Model model,
			@RequestParam(value = "customerCode", required = false) Long customerCode, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		try {
			logger.info("ComsController :: updateEntCustomer() :: Start");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "updateEntCustomer?customerCode=" + customerCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("name", tenantName);
			model.addAttribute("state", comsHelperDTO.getState());
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("mandalList", comsHelperDTO.getMandalList());
			model.addAttribute("billCycleList", comsHelperDTO.getBillCycleList());
			model.addAttribute("enterpriseCustomer", comsHelperDTO.getEnterpriseCustomer());
			logger.info("ComsController :: updateEntCustomer() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: updateEntCustomer() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			comsHelperDTO = null;
		}
		return "createentcustomer";
	}

	@RequestMapping(value = "/viewEntCustomer", method = RequestMethod.POST)
	public String viewEntCustomer(Model model,
			@RequestParam(value = "customerCode", required = false) Long customerCode,
			@RequestParam(value = "modify", required = false) String modify, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		try {
			logger.info("ComsController :: viewEntCustomer() :: Start");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "viewEntCustomer?customerCode=" + customerCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("enterpriseCustomer", comsHelperDTO.getEnterpriseCustomer());
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("mandalList", comsHelperDTO.getMandalList());
			model.addAttribute("blMandalList", comsHelperDTO.getBlMandalList());
			model.addAttribute("villages", comsHelperDTO.getVillages());
			model.addAttribute("blVillages", comsHelperDTO.getBlVillages());
			model.addAttribute("name", tenantName);
			model.addAttribute("tenantType", tenantType);
			model.addAttribute("lovsList", comsHelperDTO.getCustomerTypeList());
			model.addAttribute("officeTypeLovsList", comsHelperDTO.getOfficeTypeLovList());
			model.addAttribute("billCycleList", comsHelperDTO.getBillCycleList());
			model.addAttribute("villageList", comsHelperDTO.getVillageList());
			logger.info("ComsController :: viewEntCustomer() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: viewEntCustomer() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			comsHelperDTO = null;
		}
		if (modify != null) {
			return "createentcustomer";
		} else {
			return "viewentcustomer";
		}
	}

	@RequestMapping(value = "/showEntNodes", method = RequestMethod.POST)
	public String showEntNodes(Model model, @RequestParam(value = "customerCode", required = false) Long customerCode,
			@RequestParam(value = "modify", required = false) String modify, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		try {
			logger.info("ComsController :: showEntNodes() :: Start");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "showEntNodes?customerCode=" + customerCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("enterpriseCustomerNode", comsHelperDTO.getEnterpriseCustomer());
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("mandalList", comsHelperDTO.getMandalList());
			model.addAttribute("blMandalList", comsHelperDTO.getBlMandalList());
			model.addAttribute("villages", comsHelperDTO.getVillages());
			model.addAttribute("blVillages", comsHelperDTO.getBlVillages());
			model.addAttribute("name", tenantName);
			model.addAttribute("villageList", comsHelperDTO.getVillageList());
			model.addAttribute("modify", modify);
			logger.info("ComsController :: showEntNodes() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: showEntNodes() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			comsHelperDTO = null;
		}
		if (modify != null) {
			return "createnode";
		} else {
			return "viewentcustomernode";
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewEntNodes", method = RequestMethod.GET)
	@ResponseBody
	public List<EnterpriseCustomer> viewEntNodes(Model model,
			@RequestParam(value = "custId", required = false) String custId, HttpServletRequest request) {

		List<EnterpriseCustomer> entNodeList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: viewEntNodes() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "viewEntNodes?custId=" + custId + "&tenantCode=" + tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			entNodeList = response.getBody();
			model.addAttribute("custId", custId);
			model.addAttribute("entNodeList", entNodeList);
			logger.info("ComsController :: viewEntNodes() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: viewEntNodes() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return entNodeList;
	}

	@RequestMapping(value = "/saveEntCustomer", method = RequestMethod.POST)
	public String saveEnterpriseCustomer(Model model, EnterpriseCustomerVO enterpriseCustomerVO,
			HttpServletRequest request) {

		HttpEntity<EnterpriseCustomerVO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		try {
			logger.info("ComsController :: saveEnterpriseCustomer() :: Start");
			String loginID = (String) request.getSession(false).getAttribute("loginID");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			enterpriseCustomerVO.setTenantCode(tenantCode);
			enterpriseCustomerVO.setTenantType(tenantType);
			enterpriseCustomerVO.setLoginId(loginID);
			enterpriseCustomerVO.setIpAddress(httpServletRequest.getRemoteAddr());
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),enterpriseCustomerVO);
			url = ipAddressValues.getComURL() + "saveEntCustomer";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("customerList", comsHelperDTO.getEnterpriseCustomerList());
			model.addAttribute("message", comsHelperDTO.getMessage());
			model.addAttribute("msg", comsHelperDTO.getFlag());
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: saveEnterpriseCustomer() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: saveEnterpriseCustomer() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			comsHelperDTO = null;
		}
		return "enterprisecustomer";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/provisonErrors", method = RequestMethod.GET)
	public String provisonErrors(Model model, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		List<ErrorsDTO> provisionErrorList = new ArrayList<>();
		try {
			logger.info("ComsController :: provisonErrors() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "provisonErrors";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			provisionErrorList = response.getBody();
			model.addAttribute("provErrList", provisionErrorList);
			logger.info("ComsController :: provisonErrors() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: provisonErrors() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			provisionErrorList = null;
		}
		return "provisonErrors";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getErrJsons", method = RequestMethod.GET)
	@ResponseBody
	public List<ErrorJsonsDTO> getErrJsons(@RequestParam("reqid") String reqid) {

		List<ErrorJsonsDTO> ErrorJsonsList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getErrJsons() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getErrJsons?reqid=" + reqid;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			ErrorJsonsList = response.getBody();
			logger.info("ComsController :: getErrJsons() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getErrJsons() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return ErrorJsonsList;
	}

	@RequestMapping(value = "/updateProvisionErrors", method = RequestMethod.GET)
	public String updateProvisionErrors(Model model, HttpServletRequest request) {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: updateProvisionErrors() :: Start");
			String arr = request.getParameter("hiddenReqIds");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "updateProvisionErrors?arr=" + arr;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			
			
			logger.info("ComsController :: updateProvisionErrors() :: End");	
		} catch (Exception e) {
			logger.info("ComsController :: updateProvisionErrors() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		model.addAttribute("provErrList", comsHelperDTO.getProvisionErrorList());
		model.addAttribute("message", comsHelperDTO.getMessage());
		return "redirect:./provisonpending";
	}
	
	
	@RequestMapping(value = "/saveProvErrRecycle", method = RequestMethod.GET)
	public String saveProvErrRecycle(Model model, HttpServletRequest request) {

		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: saveProvErrRecycle() :: Start");
			String arr = request.getParameter("hiddenReqIds");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "saveProvErrRecycle?arr=" + arr;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			logger.info("ComsController :: saveProvErrRecycle() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: saveProvErrRecycle() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		model.addAttribute("provErrList", comsHelperDTO.getProvisionErrorList());
		model.addAttribute("message", comsHelperDTO.getMessage());
		return "provisonErrors";
	}
	
	

	@RequestMapping(value = "/getCafCustDetailsToModify", method = RequestMethod.POST)
	public String getCafCustDetailsToModify(Model model,
			@RequestParam(value = "cafNumber", required = false) Long cafNumber, HttpServletRequest request) {
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenantName = (String) request.getSession(false).getAttribute("tenantname");
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: getCafCustDetailsToModify() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getCafCustDetailsToModify?cafNo=" + cafNumber + "&tenantCode="+ tenantCode + "&tenantType=" + tenantType;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();

			model.addAttribute("cafObject", comsHelperDTO.getCaf());
			model.addAttribute("customer", comsHelperDTO.getCustomer());
			model.addAttribute("entCustomer", comsHelperDTO.getEnterpriseCustomer());
			model.addAttribute("popList", comsHelperDTO.getSubStationsList());
			model.addAttribute("billCycleList", comsHelperDTO.getBillCycleList());
			model.addAttribute("customerTypeList", comsHelperDTO.getCustomerTypeList());
			model.addAttribute("districts", comsHelperDTO.getDistrict());
			model.addAttribute("mandals", comsHelperDTO.getMandal());
			model.addAttribute("mandalList", comsHelperDTO.getMandalList());
			model.addAttribute("villageList", comsHelperDTO.getTenantBusinessAreasList());
			//model.addAttribute("instMandalList", comsHelperDTO.getInstMandalList());
			model.addAttribute("instVillageList", comsHelperDTO.getInstVillageList());
			model.addAttribute("lmoName", tenantName);
			model.addAttribute("lmoCode", tenantCode);
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getFlag());
			model.addAttribute("state", comsHelperDTO.getState());
			model.addAttribute("tenantType", tenantType);
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			logger.info("ComsController :: getCafCustDetailsToModify() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: getCafCustDetailsToModify() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			comsHelperDTO = null;
		}
		return "customerModify";
	}

	@RequestMapping(value = "/getCafDetailsToAPSFLCafModify", method = RequestMethod.POST)
	public String getCafDetailsToAPSFLCafModify(Model model,
			@RequestParam(value = "cafNumber", required = false) Long cafNumber, HttpServletRequest request) {
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenantName = (String) request.getSession(false).getAttribute("tenantname");
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: getCafDetailsToAPSFLCafModify() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getCafDetailsToAPSFLCafModify?cafNo=" + cafNumber + "&tenantCode="+ tenantCode + "&tenantType=" + tenantType;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("cafObject", comsHelperDTO.getCaf());
			model.addAttribute("tenantTypeList", comsHelperDTO.getTenantType());
			model.addAttribute("name", tenantName);
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("mandalList", comsHelperDTO.getMandalList());
			logger.info("ComsController :: getCafCustDetailsToModify() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: getCafCustDetailsToModify() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			comsHelperDTO = null;
		}
		return "caflmomodify";
	}
	@RequestMapping(value = "/downloadExcelSheet", method = RequestMethod.GET)
	public void downloadExcelForBulkCafUpload(HttpServletRequest request, HttpServletResponse response, String from) throws FileNotFoundException{

		File file = null;
		file = new File(from.equalsIgnoreCase("BulkCafs") ? ipAddressValues.getBulkExcelPath() : ipAddressValues.getVpnExcelPath());
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
	
	@RequestMapping(value = "/vpnServiceUpload", method = RequestMethod.GET)
	public String getVPNServiceExcelUploadPage(Locale locale, Model model, HttpServletRequest request) {
		return "vpnServiceUpload";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveVPNServiceExcelUpload", method = RequestMethod.POST)
	public String saveVPNServiceExcelUpload(Model model, VPNServiceExcelUploadVO vpnServiceExcelUploadVO, HttpServletRequest request) {
		String message = "";
		HttpEntity<VPNServiceExcelUploadVO> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		List<VPNSrvcVO> vpnSrvcList = new ArrayList<>();
		try {
			logger.info("ComsController :: saveVPNServiceExcelUpload() :: Start");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String loginID = (String) request.getSession(false).getAttribute("loginID");
			vpnServiceExcelUploadVO.setLoginId(loginID);
			vpnServiceExcelUploadVO.setTenantType(tenantType);
			vpnServiceExcelUploadVO.setTenantCode(tenantCode);
				MultipartFile file = vpnServiceExcelUploadVO.getVpnUploadFile();
				if (file != null && !file.isEmpty()) {
					String name = file.getOriginalFilename();
					String ext = FilenameUtils.getExtension(name);
					if (ext != null && (ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("xls")) && ext != "") {
						vpnSrvcList = ReadXlsFileForCafs.getVPNServiceList(file, ext);
						vpnServiceExcelUploadVO.setVpnSrvcList(vpnSrvcList);
						vpnServiceExcelUploadVO.setFileName(name);
						vpnServiceExcelUploadVO.setFileSize(String.valueOf(vpnServiceExcelUploadVO.getVpnUploadFile().getSize()));
						Map<String, String> recordDetails = ReadXlsFileForCafs.getNumberOfColsAndRows(vpnServiceExcelUploadVO.getVpnUploadFile(), ext);
						vpnServiceExcelUploadVO.setNoofCols(recordDetails.get("noOfCols"));
						vpnServiceExcelUploadVO.setNoofRows(recordDetails.get("noOfRows"));
					}
				}
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), vpnServiceExcelUploadVO);
				url = ipAddressValues.getComURL() + "saveVPNServiceExcelUpload";
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
				message = response.getBody();
				model.addAttribute("message", message);
				logger.info("ComsController :: saveVPNServiceExcelUpload() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: saveVPNServiceExcelUpload() :: " + e);
			e.printStackTrace();
		} finally {
			
		}
		return "vpnServiceUpload";
	}
	
	@RequestMapping(value = "/cafBulkUploadErrors", method = RequestMethod.GET)
	public String cafBulkUploadErrorsPage(Model model, HttpServletRequest request) {
		return "cafBulkUploadError";
	}

	@RequestMapping(value = "/cafBulkUploadErrors", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PaginationObject<UploadHistDTO> cafBulkUploadErrorsPagination(HttpServletRequest request) {
		String tenantCode = "";
		HttpEntity<ComsHelperDTO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		PaginationObject<UploadHistDTO> caferrDataPageObject = new PaginationObject<UploadHistDTO>();
		try {
			Integer pageDisplayLength = 10;
			Integer pageNumber = 1;
			String sdir = "desc";
			String sortColumn = "";
			int sortParameter = 0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			if (request.getParameter("iSortCol_0") != null) {
				sortParameter = Integer.parseInt(request.getParameter("iSortCol_0"));
			}
			sortColumn = UploadHistDTO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
			if (request.getParameter("sSortDir_0") != null)
				sdir = request.getParameter("sSortDir_0");

			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			PageObject pageObject = new PageObject();
			pageObject.setMinSize((pageNumber - 1) * pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			comsHelperDTO.setPageObject(pageObject);
			comsHelperDTO.setTenantCode(tenantCode);
			comsHelperDTO.setTenanttype(tenantType);

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), comsHelperDTO);
			url = ipAddressValues.getComURL() + "cafBulkUploadErrorsPage";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			caferrDataPageObject.setAaData(comsHelperDTO.getVpnErrorList());
			caferrDataPageObject.setiTotalDisplayRecords(Long.parseLong(comsHelperDTO.getCount()));
		} catch (Exception e) {
			logger.info("ComsController :: cafBulkUploadErrors() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return caferrDataPageObject;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cafBulkUploadErrorDownload", method = RequestMethod.POST)
	@ResponseBody
	public void cafBulkUploadErrorDownload(Model model, HttpServletRequest request,	
			@RequestParam(value = "uploadId", required=false) String uploadId,
			HttpServletResponse httpResponse) throws IOException, ParseException {
		
		List<EntCafStage> errorList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "cafBulkUploadErrorDownload?uploadId="+uploadId+"";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			errorList = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<EntCafStage> cafErrorList = mapper.readValue(gson.toJson(errorList),TypeFactory.defaultInstance().constructCollectionType(List.class, EntCafStage.class));
			FileUtil.cafBulkUplaodErrorDownloadExcel(cafErrorList,httpResponse);
		} catch (Exception e) {
			logger.info("ComsController :: cafBulkUploadErrors() :: " + e);
			e.printStackTrace();
		} finally {
			errorList = null;
			httpEntity = null;
			url = null;
			response = null;
		}
	}
	
	@RequestMapping(value = "/vpnUploadErrors", method = RequestMethod.GET)
	public String vpnUploadErrorsPage(Model model, HttpServletRequest request) {
		return "vpnUploadError";
	}
	
	@RequestMapping(value = "/vpnUploadErrorsPagination", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PaginationObject<UploadHistDTO> vpnUploadErrorsPagination(HttpServletRequest request) {
		String tenantCode = "";
		HttpEntity<ComsHelperDTO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		PaginationObject<UploadHistDTO> vpnErrDataPageObject = new PaginationObject<UploadHistDTO>();
		try {
			Integer pageDisplayLength = 10;
			Integer pageNumber = 1;
			String sdir = "desc";
			String sortColumn = "";
			int sortParameter = 0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			if (request.getParameter("iSortCol_0") != null) {
				sortParameter = Integer.parseInt(request.getParameter("iSortCol_0"));
			}
			sortColumn = UploadHistDTO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
			if (request.getParameter("sSortDir_0") != null)
				sdir = request.getParameter("sSortDir_0");

			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			PageObject pageObject = new PageObject();
			pageObject.setMinSize((pageNumber - 1) * pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			comsHelperDTO.setPageObject(pageObject);
			comsHelperDTO.setTenantCode(tenantCode);
			comsHelperDTO.setTenanttype(tenantType);

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), comsHelperDTO);
			url = ipAddressValues.getComURL() + "vpnUploadErrPage";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			vpnErrDataPageObject.setAaData(comsHelperDTO.getVpnErrorList());
			vpnErrDataPageObject.setiTotalDisplayRecords(Long.parseLong(comsHelperDTO.getCount()));
		} catch (Exception e) {
			logger.info("ComsController :: vpnUploadErrorsPagination() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return vpnErrDataPageObject;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/vpnUploadErrorDownload", method = RequestMethod.POST)
	public void vpnUploadErrorDownload(Model model, HttpServletRequest request,	
			@RequestParam(value = "uploadId", required=false) String uploadId,
			HttpServletResponse httpResponse) throws IOException, ParseException {
		
		List<VPNSrvcNamesStage> errorList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "vpnUploadErrorDownload?uploadId="+uploadId+"";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			errorList = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<VPNSrvcNamesStage> cafErrorList = mapper.readValue(gson.toJson(errorList),TypeFactory.defaultInstance().constructCollectionType(List.class, VPNSrvcNamesStage.class));
			FileUtil.vpnUplaodErrorDownloadExcel(cafErrorList,httpResponse);
		} catch (Exception e) {
			logger.info("ComsController :: cafBulkUploadErrors() :: " + e);
			e.printStackTrace();
		} finally {
			errorList = null;
			httpEntity = null;
			url = null;
			response = null;
		}
	}
	
	@RequestMapping(value ="/editCustomer", method = RequestMethod.POST)
    public String editCustomer(Model model, @RequestParam(value = "custId", required = false) Long custId, 
            @RequestParam(value = "modify", required = false) String modify, HttpServletRequest request ) {
            ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
            String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
            String tenantName = (String) request.getSession(false).getAttribute("tenantname");
        try {
            HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
            String url = ipAddressValues.getComURL() + "editCustomer?custId="+custId+"&tenantCode="+tenantCode;
            ResponseEntity<ComsHelperDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
            comsHelperDTO = response.getBody();
            model.addAttribute("cafObject", comsHelperDTO.getCaf());
            model.addAttribute("customer", comsHelperDTO.getCustomer());
            model.addAttribute("billCycleList",comsHelperDTO.getBillCycleList());
            model.addAttribute("customerTypeList", comsHelperDTO.getCustomerTypeList());
            model.addAttribute("districtList", comsHelperDTO.getDistrictList());
            model.addAttribute("mandalList", comsHelperDTO.getMandalList());
            model.addAttribute("villageList", comsHelperDTO.getVillageList());
            model.addAttribute("villages", comsHelperDTO.getVillages());
            model.addAttribute("lmoName", tenantName);
            model.addAttribute("lmoCode", tenantCode);
            model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
            model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
            model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
            model.addAttribute("alert", comsHelperDTO.getAlert());
            model.addAttribute("flag", comsHelperDTO.getFlag());
            model.addAttribute("state", comsHelperDTO.getState());
            
        } catch(Exception e) {
        	logger.info("ComsController :: editCustomer() :: " + e);
            e.printStackTrace();
        }
            return "editCustomer";
    
    }
	
	@RequestMapping(value ="/updateCustomerDetails", method = RequestMethod.POST)
	public String updateCustomerDetails(Model model, CustomerCafVO customerCafVO, 
			@RequestParam(value = "pocMob1Old", required = false) String pocMob1Old, @RequestParam(value = "pocMob2Old", required = false) String pocMob2Old,
			@RequestParam(value = "stdCodeOld", required = false) String stdCodeOld, @RequestParam(value = "landLine1Old", required = false) String landLine1Old, 
			@RequestParam(value = "email1Old", required = false) String email1Old, 
			@RequestParam(value = "address1Old", required = false) String address1Old, @RequestParam(value = "address2Old", required = false) String address2Old,
			@RequestParam(value = "localityOld", required = false) String localityOld, @RequestParam(value = "districtOld", required = false) String districtOld, 
			@RequestParam(value = "mandalOld", required = false) String mandalOld,@RequestParam(value = "villageOld", required = false) String villageOld, 
			@RequestParam(value = "pinOld", required = false) String pinOld,
			HttpServletRequest request) {
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		String loginID = (String) request.getSession(false).getAttribute("loginID");
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenantName = (String) request.getSession(false).getAttribute("tenantname");
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		customerCafVO.setLoginId(loginID);
		customerCafVO.setLmoCode(tenantCode);
		customerCafVO.setTenantType(tenantType);
		try {
			HttpEntity<CustomerCafVO> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), customerCafVO);
			String url = ipAddressValues.getComURL() + "updateCustomerDetails?pocMob1Old="+pocMob1Old+"&pocMob2Old="+pocMob2Old+"&stdCodeOld="+stdCodeOld+"&landLine1Old="+landLine1Old+"&email1Old="+email1Old+"&tenantCode="+tenantCode+"&tenantName="+tenantName+"&address1Old="+address1Old+"&address2Old="+address2Old+"&localityOld="+localityOld+"&districtOld="+districtOld+"&mandalOld="+mandalOld+"&villageOld="+villageOld+"&pinOld="+pinOld;
			ResponseEntity<ComsHelperDTO> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
		} catch(Exception e) {
			logger.info("ComsController :: updateCustomerDetails() :: " + e);
			e.printStackTrace();
		}
		model.addAttribute("message", comsHelperDTO.getMessage());
		model.addAttribute("customersList", comsHelperDTO.getCustomerList());
		return "viewcustomer";
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getStbModelList", method = RequestMethod.GET)
	@ResponseBody
	public List<CpeModal> getStbModelList( HttpServletRequest request) {
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		String url = "";
		List<CpeModal> cpeModelList = new ArrayList<>();
		try {
			logger.info("ComsController :: getFingerPrintDetails() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getStbModelList";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class);
			cpeModelList = response.getBody();
			logger.info("ComsController :: getFingerPrintDetails() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getFingerPrintDetails() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
			url = null;
		}
		return cpeModelList;
	}
	
	@RequestMapping(value ="/SearchForAddPackageDetails", method = RequestMethod.POST)
	public String SearchForAddPackageDetails(Model model, @RequestParam(value = "cafNumber", required = false) Long cafNumber,
			@RequestParam(value = "addOrChangePkgFlag", required = false) String addOrChangePkgFlag, 
			 @RequestParam(value = "prodCafNo", required = false) String prodCafNo, HttpServletRequest request ) {
		
		try {
			logger.info("CustomerController :: SearchForAddPackageDetails()");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String url = ipAddressValues.getComURL() + "SearchForAddPackageDetails?cafNumber="+cafNumber+"&tenantType="+tenantType+"&addOrChangePkgFlag="+addOrChangePkgFlag+"&tenantCode="+tenantCode;
			ResponseEntity<ComsHelperDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			ComsHelperDTO comsHelperDTO = response.getBody();
			
			model.addAttribute("customerCode", comsHelperDTO.getCustomerCode());
			HttpSession session = request.getSession(false);
			session.setAttribute("productList", comsHelperDTO.getProductsList());
			model.addAttribute("currentProductList", comsHelperDTO.getCurrentProductList());
			model.addAttribute("baseProductList", comsHelperDTO.getBaseProductList());
			model.addAttribute("addOnProductList", comsHelperDTO.getAddOnProductList());
			model.addAttribute("oneTimeProductList", comsHelperDTO.getOneTimeProductList());
			model.addAttribute("customerCafVO", comsHelperDTO.getCustomerCafVO());
			model.addAttribute("lmoName", tenantName);
			model.addAttribute("lmoWalletBalence", comsHelperDTO.getLmoWalletBalence());
			model.addAttribute("creditLimit", comsHelperDTO.getCreditLimit());
			model.addAttribute("actualUserAmount", comsHelperDTO.getActualUserAmount());
			model.addAttribute("lmoAgrmntMspCodes", comsHelperDTO.getLmoAgrmntMspCodes());
			model.addAttribute("alert", comsHelperDTO.getAlert());
			model.addAttribute("flag", comsHelperDTO.getFlag());
			model.addAttribute("noOfSTBs", comsHelperDTO.getNoOfSTBsList());
			model.addAttribute("iptvCount", comsHelperDTO.getNoOfSTBsList().size());
			model.addAttribute("changePkgFlag", addOrChangePkgFlag);
			model.addAttribute("prodCafNo", prodCafNo);
		} catch(Exception e) {
			logger.error("CustomerController :: getSearchCafDetails()" + e);
			e.printStackTrace();
		}
		if(addOrChangePkgFlag.equalsIgnoreCase("chnagePkgPageFlag")) {
			return "changepackage";
		} else {
			return "addPackages";
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getProdInfoByCafNo", method = RequestMethod.GET)
	@ResponseBody
	public List<TerminatePkgBO> getProdInfoByCafNo(@RequestParam(value = "cafNo") Long cafNo) {
		List<TerminatePkgBO> productsVOList = new ArrayList<TerminatePkgBO>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getProdInfoByCafNo() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getProdInfoByCafNo?cafNo=" + cafNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			productsVOList = response.getBody();
			logger.info("ComsController :: getProdInfoByCafNo() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getProdInfoByCafNo() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
		}
		return productsVOList;
	}
	
	@RequestMapping(value = "/terminateAddonIPTVPackage", method = RequestMethod.POST)
	@ResponseBody
	public String terminateAddonIPTVPackage(@RequestBody List<TerminatePkgBO> productsList, HttpServletRequest request) {
		HttpEntity<List<TerminatePkgBO>> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		String status = "false";
		try {
			logger.info("ComsController :: getPkgDtls() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), productsList);
			url = ipAddressValues.getComURL() + "terminateAddonIPTVPackage";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: getPkgDtls() :: End");
		} catch (Exception exception) {
			logger.error("ComsController :: getPkgDtls() :: " + exception);
			exception.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		return status;
	}
	
	@RequestMapping(value = "/viewEnterpriseCustomerDtls", method = RequestMethod.GET)
	public String viewEnterpriseCustomerDtlsPage(Model model, HttpServletRequest request) {
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		try {
			logger.info("ComsController :: viewEnterpriseCustomerDtls() :: Start");
			model.addAttribute("tenantType", tenantType);
			logger.info("ComsController :: viewEnterpriseCustomerDtls() :: End");
		} catch (Exception e) {
			logger.error("EnterpriseCustomerController :: viewEnterpriseCustomerDtls()" + e);
			e.printStackTrace();
		} finally {
		}
		return "viewEntCustDtls";
	}
	
	@RequestMapping(value = "/viewEntCafDtlsPagination", method = RequestMethod.GET)
	public @ResponseBody PaginationObject<CafsVO> viewEntCafDtlsPagination(HttpServletRequest request, CafsVO caf) {

		PaginationObject<CafsVO> cafObject= new PaginationObject<>();
		HttpEntity<ComsHelperDTO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO=new ComsHelperDTO();
		try {
			logger.info("ComsController :: searchCafProductsDetails() :: Start");
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
	    	sortColumn = CafsVO.CafsVOColumn.getColumns("COLUMN_"+sortParameter);
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
			
			comsHelperDTO.setPageObject(pageObject);
			comsHelperDTO.setCustId(caf.getCustId().toString());
			//
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), comsHelperDTO);
			url = ipAddressValues.getComURL() + "viewEntCafDetails";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			cafObject.setAaData(comsHelperDTO.getCafList());
			cafObject.setiTotalDisplayRecords(Long.parseLong(comsHelperDTO.getCount()));
		} catch (Exception e) {
			logger.error("ComsController :: searchCafProductsDetails() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return cafObject;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getCafInfo", method = RequestMethod.GET)
	@ResponseBody
	public List<CafsVO> getCafInfo(@RequestParam(value = "cafNo") Long cafNo) {
		List<CafsVO> cafsVO = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		try {
			logger.info("ComsController :: getCafInfo() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getCafInfo?cafNo=" + cafNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			cafsVO = response.getBody();
			logger.info("ComsController :: getCafInfo() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getCafInfo() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
		}
		return cafsVO;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/multiActionCafDownload", method = RequestMethod.POST)
	@ResponseBody
	public void multiActionCafDownload(HttpServletRequest request, HttpServletResponse httpResponse, MultiAction multiAction) throws IOException, ParseException {
		
		List<CafAndCpeChargesVO> cafAndCpeChargesVOList = new ArrayList<>();
		HttpEntity<MultiAction> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		String loginID="";
		String tenantCode="";
		String tenantType="";
		HSSFWorkbook workbook = null;
		FileUtil fileUtil=new FileUtil();
		try {
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			multiAction.setTenantCode(tenantCode);
			multiAction.setLoginId(loginID);
			multiAction.setTenantType(tenantType);
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),multiAction);
			url = ipAddressValues.getComURL() + "multiActionCafDownload";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class);
			cafAndCpeChargesVOList = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<CafAndCpeChargesVO> cafsList = mapper.readValue(gson.toJson(cafAndCpeChargesVOList),TypeFactory.defaultInstance().constructCollectionType(List.class, CafAndCpeChargesVO.class));
			workbook=fileUtil.multiActionCafDownload(cafsList,httpResponse);
		} catch (Exception e) {
			logger.info("ComsController :: cafBulkUploadErrors() :: " + e);
			e.printStackTrace();
		} finally {
			cafAndCpeChargesVOList = null;
			httpEntity = null;
			url = null;
			response = null;
			workbook = null;
		}
	}
	
 
	
	/**
	 * @author sreev 
	 * @since 13/June/2017
	 * @return JspPage
	 */
	
	@RequestMapping(value="/publicIPCheck", method=RequestMethod.GET)
	public String publicIPCheck()
	{
		return "publicIPCheck";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getCafsIpAddress", method=RequestMethod.POST)
	public String getCafsIpAddress(PublicIpAddressDTO publicIpAddressDTo, @RequestParam(value = "statusMessage", required = false) String statusMessage, Model m,
			HttpServletRequest request)
	{
		List<Object[]> customerList = new ArrayList<>();
		HttpEntity<PublicIpAddressDTO> httpEntity;
		String url = "";
		
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		
		publicIpAddressDTo.setTenantCode(tenantCode);
		publicIpAddressDTo.setTenantType(tenantType);
		 
			try {
				 
				logger.info("Coms Controller :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),
						publicIpAddressDTo);
				url = ipAddressValues.getComURL() + "getCafsListPuIP";
				customerList = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("Coms Controller :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		m.addAttribute("statusMessage", statusMessage);
	 
		return "publicIPCheck";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getIpAddress", method=RequestMethod.GET)
	public @ResponseBody String getIpAddress(PublicIpAddressDTO publicIpAddressDTo, HttpServletRequest request)
	{
		List<Object[]> ipAddress = new ArrayList<>();
		HttpEntity<PublicIpAddressDTO> httpEntity;
		String url = "";
		String ipaadress = "";
		Object  pIpAddress=null;
		publicIpAddressDTo.setpIpAddress(null);
		 
		try {
			 
			logger.info("Coms Controller :: home() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),
					publicIpAddressDTo);
			url = ipAddressValues.getComURL() + "getIpAddress";
			ipAddress = restTemplate.exchange(url, HttpMethod.POST, httpEntity,  ArrayList.class).getBody();
			 
			 pIpAddress = ipAddress.get(0);
			 ipaadress = pIpAddress.toString();
			logger.info("coms Controller :: home() :: End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return ipaadress;
	}
	
	@RequestMapping(value="/updateIpAddress", method=RequestMethod.GET)
	public  @ResponseBody String updateIpAddress(PublicIpAddressDTO publicIpAddressDTo, HttpServletRequest request){
		HttpEntity<PublicIpAddressDTO> httpEntity;
		String url = "";
		String statusMsg="";
		
		try {
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			
			publicIpAddressDTo.setTenantCode(tenantCode);
			publicIpAddressDTo.setTenantType(tenantType);
			 
			logger.info("Coms Controller :: home() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),publicIpAddressDTo);
			url = ipAddressValues.getComURL() + "updateIpAddress";
			statusMsg = restTemplate.exchange(url, HttpMethod.POST, httpEntity,  String.class).getBody();
			 
			logger.info("coms Controller :: home() :: End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusMsg;
		
	}
 
	@RequestMapping(value = "/downLoadBill", method = RequestMethod.POST)
	public void downloadInvoiceBill(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "filePath", required = false) String filePath) {

		File file = null;
		file = new File(filePath);
		try (InputStream inputStream = new FileInputStream(file);
				BufferedInputStream bIs = new BufferedInputStream(inputStream);
				ServletOutputStream os = response.getOutputStream()) {

			if (file != null) {
				
				String mimeType = "application/pdf";
				response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
				//response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
				response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
				response.setContentLength((int) file.length());
				FileCopyUtils.copy(bIs, os);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			file = null;
		}
	}
	
	//balanceAdjustment Code
	@RequestMapping(value = "/balanceAdjustment", method = RequestMethod.GET)
	public String balanceAdjustment(Model model, HttpServletRequest request) {
		return "balanceAdjustment";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchBalanceAdjustment", method = RequestMethod.POST)
	public String searchBalanceAdjustment(Model model, @RequestParam(value = "custId" , required = false) String custId, 
			@RequestParam(value = "AadharNo" , required = false) String AadharNo,  HttpServletRequest request) {
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		ArrayList custList = null;
		try {
			logger.info("TmsController :: searchBalanceAdjustment() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String url = ipAddressValues.getComURL() +"searchBalanceAdjustment?custId="+custId+"&AadharNo="+AadharNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			custList =  response.getBody();
			if (custList.size() > 0) {
				model.addAttribute("Smsg", "");
			} else {
				model.addAttribute("Emsg", "No records found for the given Number..");
			}
			model.addAttribute("cafList", custList);
			model.addAttribute("searchTypeCustId", ""+custId+"");
			model.addAttribute("searchTypeAadharNo", ""+AadharNo+"");
			logger.info("TmsController :: searchCafDetailsForBlockList() :: End");
		} catch (Exception e) {
			logger.error("TmsController :: searchCafDetailsForBlockList() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return "balanceAdjustment";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/showCafinvDetailsByCustinvno", method = RequestMethod.POST)
	public String showCafinvDetailsByCustinvno(Model model, BalanceAdjustmentVO balVO, HttpServletRequest request){
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		ArrayList custList = null;
		try {
			logger.info("COMSController :: searchBalanceAdjustment() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String url = ipAddressValues.getComURL() + "showCafinvDetailsByCustinvno?custInvno="+balVO.getCustinvno();
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			custList =  response.getBody();
			if (custList.size() > 0) {
				model.addAttribute("Smsg", "");
			} else {
				model.addAttribute("Emsg", "No records found for the given Number..");
			}
			model.addAttribute("cafList", custList);
			logger.info("COMSController :: searchCafDetailsForBlockList() :: End");
		} catch (Exception e) {
			logger.error("COMSController :: searchCafDetailsForBlockList() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return "cafinvDetailsByCustInvno";
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/checkedCafInvCafNos", method = RequestMethod.GET)
	@ResponseBody
	public List<BalAdjCafinvDtslBO> checkedCafInvCafNos(@RequestParam(value = "cafNo") Long cafNo, 
			@RequestParam(value = "month") String month, @RequestParam(value = "billNo") String billNo){
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		List<BalAdjCafinvDtslBO> cafList = null;
		try {
			logger.info("COMSController :: searchBalanceAdjustment() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String url = ipAddressValues.getComURL() + "checkedCafInvCafNos?acctCafNo="+cafNo+"&month="+month+"&billNo="+billNo;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			cafList =  response.getBody();
			logger.info("COMSController :: searchCafDetailsForBlockList() :: End");
		} catch (Exception e) {
			logger.error("COMSController :: searchCafDetailsForBlockList() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return cafList;
	}
	
	/*@RequestMapping(value = "/saveBalanceAdjustment", method = RequestMethod.POST)
	@ResponseBody
	public String saveBalanceAdjustment(@RequestBody List<BalAdjCafinvDtslBO> balAdjCafinvDtslBO, HttpServletRequest request) {
		HttpEntity<List<BalAdjCafinvDtslBO>> httpEntity;
		ResponseEntity<String> response;
		String msg = "";
		try{
			logger.error("COMSController :: saveBalanceAdjustment() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), balAdjCafinvDtslBO);
			String url = ipAddressValues.getComURL()+"saveBalanceAdjustment";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			msg = response.getBody();
		}catch(Exception e){
			logger.error("COMSController :: saveBalanceAdjustment() :: " + e);
			e.printStackTrace();
		}
		
		return "";
	}*/
	
	@RequestMapping(value = "/saveBalanceAdjustment", method = RequestMethod.POST)
	@ResponseBody
	public String saveBalanceAdjustments(@RequestBody List<BalAdjCafinvDtslBO> balAdjCafinvDtslBO, HttpServletRequest request) {
		HttpEntity<List<BalAdjCafinvDtslBO>> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		String status = "false";
		try {
			logger.info("ComsController :: saveBalanceAdjustment() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), balAdjCafinvDtslBO);
			url = ipAddressValues.getComURL() + "saveBalanceAdjustment?tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: saveBalanceAdjustment() :: End");
		} catch (Exception exception) {
			logger.error("ComsController :: saveBalanceAdjustment() :: " + exception);
			exception.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		return status;
	}
	
	@RequestMapping(value = "/changeProfileForCPE", method = RequestMethod.POST)
	public String changeProfileForCPE(HttpServletRequest request, 
			@RequestParam(value="cpeSrlNo",required = false) String cpeSrlNo,
			@RequestParam(value="newProfileId",required = false) String newProfileId){
		HttpEntity<String> httpEntity = null;
		ResponseEntity<String> response = null;
		String message = null;
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		try {
			logger.info("COMSController :: changeProfileForCPE() :: Start");

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String url = ipAddressValues.getComURL() + "changeProfileForCPE?cpeSrlNo=" + cpeSrlNo + "&newProfileId="+ newProfileId+"&tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			message = response.getBody();

			logger.info("COMSController :: changeProfileForCPE() :: End");
		} catch (Exception e) {
			logger.error("COMSController :: changeProfileForCPE() :: " + e);
			message = e.getMessage();
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return "redirect:/changeProfileForCPE?message="+message;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/changeProfileForCPE", method = RequestMethod.GET)
	public String changeProfileForCPE(Model model, 
			@RequestParam(value="message",required = false) String message){
		List<CpeHelperDTO> cpeMasterList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("CpeController :: uploadCpeStock() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "createCpeOrder";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			cpeMasterList = response.getBody();
			logger.info("CpeController :: uploadCpeStock() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: uploadCpeStock() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		model.addAttribute("cpeMasterList", cpeMasterList);
		model.addAttribute("message", message);
		
		return "changeProfileForCPE";
		
	}
	
	// change code 10/1/2018

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/multiActionCafDownload1", method = RequestMethod.POST)
	@ResponseBody
	public void multiActionCafDownload1(HttpServletRequest request, HttpServletResponse httpResponse,
			@RequestParam(value="month", required = false) String month,
			@RequestParam(value="year", required = false) String year,
			@RequestParam(value="lmocode", required = false) String lmocode,MultiAction multiAction) throws IOException, ParseException {
		
		List<CafAndCpeChargesVO> cafAndCpeChargesVOList = new ArrayList<>();
		HttpEntity<MultiAction> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		String loginID="";
		String tenantCode="";
		String tenantType="";
		HSSFWorkbook workbook = null;
		FileUtil fileUtil=new FileUtil();
		CafMaster email = null;

		try {
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			multiAction.setTenantCode(tenantCode);
			multiAction.setLoginId(loginID);
			multiAction.setTenantType(tenantType);
//			if(multiAction.getTenantType().equals()
//			{
				//email = demandNoteServiceImpl.getEamilOfCafWiseReport1(tenantCode);//Siddharth_5_5_merge
				multiAction.setUsageMM(month);
				multiAction.setUsageYYYY(year);
				if (null!=lmocode)
				multiAction.setLmoCode(lmocode);

//			}
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),multiAction);
			url = ipAddressValues.getComURL() + "multiActionCafDownload1";

			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class);
			cafAndCpeChargesVOList = response.getBody();

			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<CafAndCpeChargesVO> cafsList = mapper.readValue(gson.toJson(cafAndCpeChargesVOList),TypeFactory.defaultInstance().constructCollectionType(List.class, CafAndCpeChargesVO.class));
			workbook=fileUtil.multiActionCafDownload1(cafsList,httpResponse,multiAction,email);  
		} catch (Exception e) {
			logger.info("ComsController :: multiActionCafDownload1() :: " + e);
			e.printStackTrace();
		} finally {
			cafAndCpeChargesVOList = null;
			httpEntity = null;
			url = null;
			response = null;
			workbook = null;
		}
	}
	// new code 22-01-2018
	
	@RequestMapping(value = "/getLMOTotalAmountInfo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody LmoDetailsBO getLMOTotalAmountInfo(HttpServletRequest request) {
		String tenantCode = "";
		HttpEntity<ComsHelperDTO> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		LmoDetailsBO comsHelperDTO1 = new LmoDetailsBO();
		try {
			logger.info("ComsController :: LMOTotalAmount() :: START");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			comsHelperDTO.setTenantCode(tenantCode);
			comsHelperDTO.setTenanttype(tenantType);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(), comsHelperDTO);
			url = ipAddressValues.getComURL() + "viewcustomersPage1";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			comsHelperDTO1.setRegBal(comsHelperDTO.getSumreg());
			comsHelperDTO1.setPmntAmt(comsHelperDTO.getSumpmnt());
			logger.info("ComsController :: LMOTotalAmount() :: END");


		} catch (Exception e) {
			logger.info("ComsController :: viewcustomersPage() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			httpEntity = null;
			url = null;
			response = null;
		}
		return comsHelperDTO1;
	}
	
	//lmoList MsoLogin
	@RequestMapping(value = "/lmosListOfLoggedinMso", method = RequestMethod.GET)
	public String getLmoDetailsOfLoggedinMso(Model msoModel, HttpServletRequest request) {
		try{
		logger.info("ComsController :: LmoDetailsOfLoggedinMso() :: START");
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		List<MsoDetailsWithLmosBO> listMso = new ArrayList<>();
		listMso = demandNoteServiceImpl.getLmoDetailsOfLoggedinMso(tenantCode);
		msoModel.addAttribute("lmoList", listMso);
		msoModel.addAttribute("tenantCode", tenantCode);
		msoModel.addAttribute("tenantType", tenantType);
		logger.info("ComsController :: LmoDetailsOfLoggedinMso() :: END");
		} catch(Exception e){
			logger.info("ComsController :: LmoDetailsOfLoggedinMso() :: " + e);
			e.printStackTrace();
	
		}

		return "lmosListOfLoggedinMso";
	}
	
	/**
	 * @author rajesh s
	 * This method return pending provision records from olprovjosons table with status as '9'
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/provisonpending", method = RequestMethod.GET)
	public String pendingProvison(Model model, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		List<PendingProvisionErrorsDTO> provisionErrorList = new ArrayList<>();
		try {
			logger.info("ComsController :: pendingProvison() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "pendingProvison";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			provisionErrorList = response.getBody();
			model.addAttribute("pedprovErrList", provisionErrorList);
			logger.info("ComsController :: pendingProvison() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: pendingProvison() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
			provisionErrorList = null;
		}
		return "pendingProvison";
	}
	
	
	//LMOs CAF DEtails

	@RequestMapping(value = "/lmoCafsDetails", method = RequestMethod.GET)
	public String lmoCafDetailsOfLoginMso(Model model, @RequestParam(value = "lmocode") String lmocode, HttpServletRequest request) {
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;
		try {
			logger.info("ComsController :: lmoCafDetailsOfLoginMso() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "multiactionsearch?tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("statusCodes", comsHelperDTO.getStatusCodesList());
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			model.addAttribute("CustomerTypeList", comsHelperDTO.getCustomerTypeList());
			model.addAttribute("CustomerSubTypeList", comsHelperDTO.getCustomerSubTypeList());
			model.addAttribute("domain", tenantType);
			model.addAttribute("tenantcode", tenantCode);
			model.addAttribute("lmocode", lmocode);
			
			logger.info("ComsController :: lmoCafDetailsOfLoginMso() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: lmoCafDetailsOfLoginMso() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		} 
		return "lmoCafsDetails";
		

		
	}
	
	
	//Revenue of LoginMSO
	
	@RequestMapping(value = "/LoginTenantRevenueShare", method = RequestMethod.GET)
	public String getRevenueShareDetailsOfLoginMso(@RequestParam(value = "year",required=false) String year, @RequestParam(value = "month",required=false) String month, HttpServletRequest request,Model model) {
		try{
		logger.info("ComsController :: getRevenueShareDetailsOfLoginTenant() :: START");
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		HttpSession session;
		    		
		List<MsoRevenueShareBO> revnList = new ArrayList<>();
		revnList = demandNoteServiceImpl.getRevenueShareDetailsOfLoginMso(year, month, tenantCode);
		//model.addAttribute("revnList", revnList);
		session = request.getSession(false);
		session.setAttribute("revnList",revnList );
		model.addAttribute("tenantCode", tenantCode);
		model.addAttribute("tenantType", tenantType);
		model.addAttribute("year", year);
		model.addAttribute("month", month);

		logger.info("ComsController :: getRevenueShareDetailsOfLoginTenant() :: END");
		
		} catch(Exception e){
			logger.info("ComsController :: getRevenueShareDetailsOfLoginTenant() :: " + e);
			e.printStackTrace();
			
		}

		return "RevenueShare";
	}
	
	//CAF wise Revenue of LoginLMO
	
	@RequestMapping(value = "/cafwiseRevenueDetailsOfLoginLmo", method = RequestMethod.GET)
	public String getRevenueDetailsCafwiseOfLoginLmo(@RequestParam(value = "invyr",required=false) String year,
			@RequestParam(value = "msocode",required=false) String msocode,
			@RequestParam(value = "apsflshare",required=false) String apsflshare,
			@RequestParam(value = "lmoshare",required=false) String lmoshare,
			@RequestParam(value = "msoshare",required=false) String msoshare,
			@RequestParam(value = "totalcollected",required=false) String totalcollected,
			@RequestParam(value = "totalprevbal",required=false) String totalprevbal,
			@RequestParam(value = "cafcount",required=false) String cafcount,
			@RequestParam(value = "remaininglimit",required=false) String remaininglimit,
			@RequestParam(value = "paidcount",required=false) String paidcount,
			@RequestParam(value = "notpaidcount",required=false) String notpaidcount,
			@RequestParam(value = "invmn",required=false) String month, HttpServletRequest request,
			@RequestParam(value = "lmocode",required=false) String lmocode,	Model model) {
		try{
		logger.info("ComsController :: getRevenueDetailsCafwiseOfLoginLmo() :: START");
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		
		if (lmocode!=null && lmocode!="")
			tenantCode=lmocode;
			
		    		
		List<CafWiseRevenueOfLoginLmo> revnList = new ArrayList<>();
		revnList = demandNoteServiceImpl.getRevenueDetailsCafwiseOfLoginLmo(year, month, tenantCode);
		model.addAttribute("revnList", revnList);
		model.addAttribute("tenantCode", tenantCode);
		model.addAttribute("tenantType", tenantType);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("msocode", msocode);
		model.addAttribute("apsflshare", apsflshare);
		model.addAttribute("lmoshare", lmoshare);
		model.addAttribute("msoshare", msoshare);
		model.addAttribute("totalcollected", totalcollected);
		model.addAttribute("totalprevbal", totalprevbal);
		model.addAttribute("remaininglimit", remaininglimit);
		model.addAttribute("paidcount", paidcount);
		model.addAttribute("notpaidcount", notpaidcount);
		model.addAttribute("lmocode", lmocode);
		logger.info("ComsController :: getRevenueDetailsCafwiseOfLoginLmo() :: END");
		
		} catch(Exception e){
			logger.info("ComsController :: getRevenueDetailsCafwiseOfLoginLmo() :: " + e);
			e.printStackTrace();
			
		}

		return "customerWiseRevDetails";
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/lmosUnderMsoRevenueShareDownload", method = RequestMethod.GET)
	public  void lmosUnderMsoRevenueShareDownload(HttpServletRequest request ,HttpServletResponse httpResponse) {
		ResponseEntity<ArrayList> response;
		HttpSession session;
		HSSFWorkbook workbook = null;
		List<MsoRevenueShareBO> revShareList = new ArrayList<MsoRevenueShareBO>();
		FileUtil fileUtil=new FileUtil();
		try {
			session = request.getSession(false);
			revShareList = (List<MsoRevenueShareBO>) session.getAttribute("revnList");
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<MsoRevenueShareBO> revSummarylist = mapper.readValue(gson.toJson(revShareList),TypeFactory.defaultInstance().constructCollectionType(List.class, MsoRevenueShareBO.class));
			workbook = fileUtil.lmosUnderMsoRevenueShareDownload(revSummarylist,httpResponse);
		} catch (Exception e) {
			logger.error("IN lmosUnderMsoRevenueShareDownload DOWNLOAD");
			e.printStackTrace();
		}
		logger.info("after lmosUnderMsoRevenueShareDownload " + revShareList.size());
	}
	
	
	
	
	
	
	
	
	
	
	// Revenue Share report
	@RequestMapping(value = "/downloadTenantRevenueDetails1", method = RequestMethod.GET)
	public void downloadTenantRevenueShareDetails(@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "year") String year,
			@RequestParam(value = "month") String month,
			@RequestParam(value = "tenantCode") String tenantCode,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getTenantRevenueShareDetailsExcel(year, month, tenantCode);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=MSO Wise LMO Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	@RequestMapping(value = "/SecondApproverManager", method = RequestMethod.GET)
	public String SecondApproverScreen(Model model, HttpServletRequest request) {
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		List<Offline_Payment> lst = new ArrayList<Offline_Payment>();
		try {
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			String loginID = (String) request.getSession(false).getAttribute("loginID");
			lst = demandNoteServiceImpl.getOfflinePaymentChequeDetails();
			model.addAttribute("pendinglist", lst);
		} catch (Exception e) {
		} finally {
		}
		return "SecondApproverManager";
	}
	
	@RequestMapping(value = "/SecondApproverManagerUpdate", method = RequestMethod.POST)
	public String SecondApproverpdate(Model model, HttpServletRequest request,HttpServletResponse res) throws IOException {
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		String alist="";
		String rlist="";
		try {
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantName = (String) request.getSession(false).getAttribute("tenantname");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			String loginID = (String) request.getSession(false).getAttribute("loginID");
			String[] values = request.getParameterValues("selectbox1");
			String[] values1 = request.getParameterValues("selectbox2");
			int status=0;
			if(values!=null) for (int i = 0; i < values.length; i++) {
				String[] temp = (values[i].toString()).split("#");
				Offline_Payment lst = new Offline_Payment();
				lst.setId(Long.valueOf(temp[0]));
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
				java.sql.Date sql = new java.sql.Date(formatter.parse(temp[1]).getTime());
				lst.setDep_date(sql);
				lst.setTrans_id(temp[2]);
				lst.setAmount(Double.valueOf(temp[3]));
				lst.setComments(temp[4]);
				lst.setLmocode(temp[5]);
				lst.setStatus(3);
				status=demandNoteServiceImpl.updateCheque_DDPayment(lst);
				if(status!=0) alist+=lst.getLmocode()+"%";
			}
			status=0;
			if(values1!=null) for (int i = 0; i < values1.length; i++) {
				String[] temp = (values1[i].toString()).split("#");
				Offline_Payment lst = new Offline_Payment();
				lst.setId(Long.valueOf(temp[0]));
				lst.setDep_date(null);
				lst.setTrans_id(null);
				lst.setAmount(Double.valueOf(temp[3]));
				lst.setComments(temp[4]);
				lst.setLmocode(temp[5]);
				lst.setStatus(4);
				status=demandNoteServiceImpl.updateCheque_DDPayment(lst);
				if(status!=0) rlist+=lst.getLmocode()+"%";
			}
			List<Offline_Payment> lst1 = new ArrayList<Offline_Payment>();
			Object temp = (Object) request.getParameter("approvelst");
		} catch (Exception e) {
		} finally {
			res.sendRedirect("/apsfl/SecondApproverManager?alist="+alist+"&"+"rlist="+rlist);
		}
		return "SecondApproverManager";
	}
		@SuppressWarnings({ "rawtypes", "unchecked", "unused" })	
	@RequestMapping(value = "/cafdetailsemail", method = RequestMethod.POST)
	@ResponseBody
	public void cafdetailsemail(HttpServletRequest request, HttpServletResponse httpResponse, 
			@RequestParam(value="month", required = false) String month,
			@RequestParam(value="year", required = false) String year,MultiAction multiAction) throws IOException, ParseException {

		HttpEntity<MailDTO> httpEntity ;
		String url ="";
		ResponseEntity<String> response;
		String loginID="";
		String tenantCode="";
		String tenantType="";
		CafMaster email = null;
		RestTemplate restTemplate = new RestTemplate();
		MailDTO mailDto = null;
		HSSFWorkbook workbook = null;
		
		try  {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			logger.info("ComsController :: cafdetailsemail() :: START");
			
			loginID = (String) request.getSession(false).getAttribute("loginID");
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			tenantType = (String) request.getSession(false).getAttribute("domain");
			
			multiAction.setTenantCode(tenantCode);
			multiAction.setLoginId(loginID);
			multiAction.setTenantType(tenantType);
			multiAction.setUsageMM(month);
			multiAction.setUsageYYYY(year);
		//	workbook=fileUtil.multiActionCafDownload1(cafAndCpeChargesVOList,httpResponse);
		//	email = demandNoteServiceImpl.getEamilOfCafWiseReport1(tenantCode);// sid_5_5_merge
		//	workbook = reportsServiceImpl.aaaUsageReportEmail1(multiAction,email);// sid_5_5_merge
			workbook.write(bos);
//			for (EmailMaster emailMast : emailList) {
				mailDto = new MailDTO();
				mailDto.setTo(email.getEmail()); 
				mailDto.setFile(bos.toByteArray());
				mailDto.setMsg("Dear "+email.getTenantname()+", Please find attached LMO Wise CAF Report. This is system auto generated email report. Regards, --Team APSFL"); 
				mailDto.setSubject("LMO Wise Caf Details ");
				mailDto.setFileName("LMO'S WISE CAF DETAILS.xls");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), mailDto);
				 url = ipAddressValues.getUmsURL() + "sendMailWithAttachment";
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
				String x=response.getBody();
				logger.info("ComsController :: cafdetailsemail() :: END");

//				EmailSendDetails emailSendDetails = new EmailSendDetails();
//				emailSendDetails.setEmailid(email.getEmail());
//				logger.info("hjdifn "+email.getEmail());	
//				logger.info("swfkjnfkl12 "+emailSendDetails.getEmailid());
//				
//				emailSendDetails.setRptname("LMO'S WISE CAF DETAILS");
//				logger.info("swfkjnfkl13 "+emailSendDetails.getRptname());
//				
//				emailSendDetails.setSenttime(Calendar.getInstance());
//				logger.info("swfkjnfkl14 "+emailSendDetails.getSenttime());
//				
//			emailSendDetails.setStatus(response.getBody().substring(0, 1));
//				logger.info("swfkjnfkl15 "+emailSendDetails.getStatus());
//				
//				demandNoteServiceImpl.save(emailSendDetails);
//			}
		} catch (Exception e) {
			logger.error("The Exception is SchedulerController :: cafdetailsemail" +e);
			e.printStackTrace();
			logger.error(e);
		}
		
		finally {
			
		    mailDto = null;
			httpEntity = null;
			url = null;
			response = null;
			workbook = null;
		 }
		
	}
	
	@SuppressWarnings({})
	@RequestMapping(value = "/savelmoofflinepayment", method = RequestMethod.GET)
	public String saveLmoOfflinePayment(@ModelAttribute(value = "OfflinePayment") Offline_Payment OfflinePayment,
			Model model, HttpServletRequest request) {
	
		try {
			logger.info("ComsController :: savelmoofflinepayment() :: Start");
			logger.info("ComsController :: savelmoofflinepayment() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: savelmoofflinepayment() :: " + e);
			e.printStackTrace();
		}
		return "lMOOfflinePayment";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/savelmoofflinepayment1", method = RequestMethod.POST)
	public String saveLmoOfflinePayment1(@ModelAttribute(value = "OfflinePayment")  Offline_Payment OfflinePayment,
			Model model, HttpServletRequest request) {
		
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		OfflinePayment.setApprover_1(tenantCode);
		String responsetojsp;
		List<Offline_Payment> offline_PaymentList = new ArrayList<>();
		HttpEntity<Offline_Payment> httpEntity;
		String url = "";
		StringBuilder response1 = new StringBuilder("Your Entry is Successfull ");
		
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: savelmoofflinepayment1() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),OfflinePayment);
			url = ipAddressValues.getComURL() + "addcheck";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
			responsetojsp = response.getBody();
			if(responsetojsp.equals("true")){
		
			offline_PaymentList = demandNoteServiceImpl.getChequeDetails(OfflinePayment.getCheque_ddno());
	       
			model.addAttribute("offline_PaymentList", offline_PaymentList);
			model.addAttribute("statusCodes", response1);
			}
			logger.info("ComsController :: savelmoofflinepayment1() :: End");
		} catch (Exception e) {
			
			e.printStackTrace();
			model.addAttribute("statusCodes", "Your Cheque entry is invalid");
			logger.info("ComsController :: savelmoofflinepayment1() :: " + e);
		} finally {
			httpEntity = null;
			url = null;
			response1 = null;
		}
		return "lMOOfflinePayment";
	}
	
	
	//cheque payment third screen
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getLmoWalletUpdateByChequePayment", method={RequestMethod.POST,RequestMethod.GET})
	public String getLmoWalletUpdateByChequePayment(@RequestParam(value="lmocode", required = false) String lmocode,
			@RequestParam(value="cheque_ddno", required = false) String cheque_ddno,
			@RequestParam(value="effectivefrom", required = false) String effectivefrom,
			@RequestParam(value="effectiveto", required = false) String effectiveto,
			Model model, HttpServletRequest request) {
		
	
		try {
			List<LmoWalletUpdateByChequePaymentBO> lmoWalletUpdateByChequePaymentList = demandNoteServiceImpl.getLmoWalletUpdateByChequePayment(lmocode,effectivefrom,effectiveto,cheque_ddno);
			
			model.addAttribute("lmoWalletUpdateByChequePaymentList", lmoWalletUpdateByChequePaymentList);
			model.addAttribute("lmocode", lmocode);
			model.addAttribute("cheque_ddno", cheque_ddno);
			model.addAttribute("effectivefrom", effectivefrom);
			model.addAttribute("effectiveto", effectiveto);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "LmoWalletUpdateByChequePayment";
	}
	
	
	
	//download dist wise pon wiseCafcount Report
	@RequestMapping(value = "/downloadgetLmoWalletUpdateByChequePayment", method = RequestMethod.GET)
	public void downloadgetLmoWalletUpdateByChequePayment(@RequestParam(value="lmocode", required = false) String lmocode,
			@RequestParam(value="cheque_ddno", required = false) String cheque_ddno,
			@RequestParam(value="effectivefrom", required = false) String effectivefrom,
			@RequestParam(value="effectiveto", required = false) String effectiveto,
			Model model,HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadgetLmoWalletUpdateByChequePayment(lmocode,effectivefrom,effectiveto,cheque_ddno);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=LMO_Wallet_Details_after_NEFT_Cheque_DD_Payment_Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	@RequestMapping(value = "/approver1ChequeDetails", method = RequestMethod.GET)
	public String getChequeDetails(@ModelAttribute(value = "OfflinePayment")  Offline_Payment OfflinePayment,Model model, HttpServletRequest request) {
		
		List<Offline_Payment> offline_PaymentList = new ArrayList<>();
	
		try {
			logger.info("ComsController :: getChequeDetails() :: Start");
			
			offline_PaymentList = demandNoteServiceImpl.getOfflinePaymentChequeDetails();
			
			
			model.addAttribute("offline_PaymentList",offline_PaymentList);
			logger.info("ComsController :: getChequeDetails() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getChequeDetails() :: " + e);
			e.printStackTrace();
		}
		return "lMOOfflinePayment";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/uploadOfflineCheckPayment",  method=RequestMethod.GET)
	public String uploadOfflineCheckPaymentFile(
			 Model model,
			HttpServletRequest request) {

		return "offlinebulkPayment";
	}

	
	
	
	
	@RequestMapping(value = "/uploadOfflineCheckPaymentFile",  method=RequestMethod.POST)
	public String uploadOfflineCheckPaymentFile(
			 @RequestParam(value="OfflineCheckPaymentFile",required=false) MultipartFile file, Model model,
			HttpServletRequest request) {
		String tenantCode = (String) request.getSession().getAttribute("tenantcode");
		String response = null;
		HttpEntity<CpeHelperDTO> httpEntity;
	
		TmsHelperDTO tmsDetailsHelper = new TmsHelperDTO();
	
		List<String> cpeErrorList= new ArrayList<String>();
		
		try {


				if (file != null && !file.isEmpty()) {

					logger.info("ComsController :: uploadOfflineCheckPaymentFile() :: Start");
					String name = file.getOriginalFilename();
					String ext = FilenameUtils.getExtension(name);
					if (ext != null && ext.equalsIgnoreCase("xlsx") && ext != "") {
						List<Offline_Payment1> offlinePaymentList = FileUtil.extractBulkOfflinePaymentExcelFile(file, model, request);//added request arg -20-02-2018
						
									
						if (!model.containsAttribute("errorMsg") && (!offlinePaymentList.isEmpty())) {
						
								for ( Offline_Payment1 offlinepayment : offlinePaymentList )
								{
								boolean	status=demandNoteServiceImpl.insertBulkCheque_DDPayment(offlinepayment);
								if (status)
									tmsDetailsHelper.setStatus("<font color='green'>  Successfully uploaded </font>");
							
								}
	
								
						}else{
							String errorMsg= (String)model.asMap().get("errorMsg");
							if (errorMsg!=null)
							tmsDetailsHelper.setStatus("<font color='red'> " + errorMsg+  "</font>");
						}							

							} else{
								String errorMsg= (String)model.asMap().get("errorMsg");
								if (errorMsg!=null)
								tmsDetailsHelper.setStatus("<font color='red'>  error :: Xlsx Format is Not Correct.<br>" + errorMsg+  "</font>");
							}							

					} else
						tmsDetailsHelper.setStatus("Upload valid xlsx Document....");
					logger.info("ComsController :: uploadOfflineCheckPaymentFile() :: End");
					logger.info("Respose  " + response);
					
					model.addAttribute("status", tmsDetailsHelper.getStatus());

				

			
		} catch (Exception e) {
			logger.error("ComsController :: uploadCpeStockFile() :: " + e);
			tmsDetailsHelper.setStatus(e.getMessage());
		} finally {
			httpEntity = null;
		}

		return "offlinebulkPayment";
	}
	
	@RequestMapping(value = "/downloadOfflineExcelFileTemp", method = RequestMethod.GET)
	public void downLoadOfflineExcelFileTemp(HttpServletRequest request, HttpServletResponse response){

		File file = null;
		file = new File(ipAddressValues.getOfflinepaymentexceltemplate());
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

	
			@RequestMapping(value = "/assignvillage",  method={RequestMethod.POST,RequestMethod.GET})
		public String assignVillage(HttpServletRequest request,Model model, 
				@RequestParam(value = "districtuid", required = false) String districtUid,
				@RequestParam(value = "mandalslno", required = false) String mandalSlno,
				@RequestParam(value = "villagename", required = false) String villageName,
				
				@RequestParam(value = "mandalList", required = false) List<Mandals>  mandalList) {
			


			String returnText="";
			HttpEntity<String> httpEntity = null;
			ResponseEntity<String> response;
			String url="";
			List<Districts> districtList = new ArrayList<>();
			
			try {

				
				logger.info("ComsController :: assignvillage() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				
				
						

					url = ipAddressValues.getComURL() + "groupCafFPActivation";
					ResponseEntity<ArrayList> distResponse  = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
					districtList = distResponse.getBody();
					
				//	HttpSession session = request.getSession(true);
				//	session.setAttribute("districtList", districtList);
				model.addAttribute("districtList", districtList);
					
					
				if(districtUid!=null && districtUid!=""){
				
				 url = ipAddressValues.getComURL() + "assignvillage?districtuid="+districtUid+"&mandalslno="+mandalSlno+"&villagename="+villageName;
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
				returnText = response.getBody();
				}
				
				
				model.addAttribute("districtuid", districtUid);
				
				model.addAttribute("mandalslno", mandalSlno);
				model.addAttribute("villagename", villageName);
				
				
				
				if (returnText.equals("Successfully Saved"))
				model.addAttribute("message", returnText);
				else
					model.addAttribute("message1", returnText);
				
				
				logger.info("ComsController :: assignvillage() :: End");
			}catch (Exception exception) {
				logger.error("ComsController :: assignvillage() :: "+exception);
				exception.printStackTrace();
			}finally {
				httpEntity = null;
				response = null;
				
			}
			return "assignVillage";
		}





	@ResponseBody
	@RequestMapping(value = "/ponChangeGetCafDetails", method = RequestMethod.GET)
	public String ponChangeGetCafDetails(@RequestParam(value = "cafNo") String cafNo,HttpServletRequest request) {
		String status = "false";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: ponChangeGetCafDetails() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String lmoCode = (String) request.getSession(false).getAttribute("tenantcode");
			url = ipAddressValues.getComURL() + "ponChangeGetCafDetails?cafNo=" + cafNo+"&lmoCode="+lmoCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: ponChangeGetCafDetails() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: ponChangeGetCafDetails() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ponChangeGetLmoOlts", method = RequestMethod.GET)
	public String ponChangeGetLmoOlts(HttpServletRequest request) {
		String status = "false";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: ponChangeGetLmoOlts() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String lmoCode = (String) request.getSession(false).getAttribute("tenantcode");
			url = ipAddressValues.getComURL() + "ponChangeGetLmoOlts?lmoCode="+lmoCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: ponChangeGetLmoOlts() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: ponChangeGetLmoOlts() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatePonChange", method = RequestMethod.GET)
	public String updatePonChange(@RequestParam(value = "cafNo") String cafNo,
			@RequestParam(value = "oltId") String newOltSerialNo,
			@RequestParam(value = "OltPortId") String newOltPort,
			@RequestParam(value = "oltIp") String oldOltIp,
			@RequestParam(value = "oldOltPort") String oldOltPort,
			@RequestParam(value = "onuId") String oldOnuId,
			@RequestParam(value = "splitter") String splitter,
			@RequestParam(value = "oldOltSrlNo") String oldOltSrlNo,
			HttpServletRequest request) {
		String status = "false";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			
			 httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			 String ponChangeUrl = this.getPonChangeUrl();
			 String lmoCode = (String) request.getSession(false).getAttribute("tenantcode");
			 url = ipAddressValues.getComURL() + "updatePonChange?lmoCode="+lmoCode+"&cafNo="+cafNo+"&newOltSerialNo="+newOltSerialNo
					 +"&newOltPort="+newOltPort+"&oldOltSrlNo="+oldOltSrlNo+"&oldOltPort="+oldOltPort+"&onuId="+oldOnuId
					 +"&ponChangeUrl="+ponChangeUrl+"&splitter="+splitter+"&oltIp="+oldOltIp;	
		     response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			 status = response.getBody();
			
			/*String requestBody ="<Request><RequestType>16</RequestType><Caf>"+cafNo+"</Caf><OltId>"+newOltSerialNo+"</OltId><OltPortId>"+newOltPort+"</OltPortId><OltIp>"+oldOltIp+"</OltIp><OnuId>"+oldOnuId+"</OnuId></Request>";
			logger.info("ComsController :: updatePonChange() :: Start");
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_XML);
		    httpEntity = new HttpEntity<String>(requestBody, headers);
			url = this.getPonChangeUrl();
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			status = response.getBody();
			if (!status.contains("Error")){
				
				HttpEntity<String> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				String lmoCode = (String) request.getSession(false).getAttribute("tenantcode");
				String url2 = ipAddressValues.getComURL() + "updateOltPortAndCafTables?lmoCode="+lmoCode+"&cafNo="+cafNo+"&newOltSerialNo="+newOltSerialNo+"&newOltPort="+newOltPort+"&oldOltSrlNo="+oldOltSrlNo+"&splitter="+splitter+"&oldOltPort="+oldOltPort;
				ResponseEntity<String> response2 = restTemplate.exchange(url2, HttpMethod.GET, httpEntity2, String.class);
				String status2 = response2.getBody();
				
			}*/
			
			logger.info("ComsController :: updatePonChange() :: End"+status);
		} catch (Exception e) {
			logger.error("ComsController :: updatePonChange() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifiedCafsData", method = RequestMethod.GET, produces = "application/json")
	public String modifiedCafsData(HttpServletRequest request) {
		String status = "false";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: modifiedCafsData() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			String lmoCode = (String) request.getSession(false).getAttribute("tenantcode");
			url = ipAddressValues.getComURL() + "getModifiedCafs?lmoCode="+lmoCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: modifiedCafsData() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: modifiedCafsData() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}
	
	
	
	@RequestMapping(value = "/ponChange", method = RequestMethod.GET)
	public String ponChange(Model model, HttpServletRequest request) {
		ComsHelperDTO comsHelperDTO = new ComsHelperDTO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ComsHelperDTO> response;

		try {
			logger.info("ComsController :: ponChange() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			logger.info("ComsController :: ponChange() :: tenant code "+tenantCode);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "multiactionsearch";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ComsHelperDTO.class);
			comsHelperDTO = response.getBody();
			model.addAttribute("districtList", comsHelperDTO.getDistrictList());
			logger.info("ComsController :: ponChange() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: ponChange() :: " + e);
			e.printStackTrace();
		} 
		return "ponChange";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateCafAddress", method = RequestMethod.GET, produces = "application/json")
	public String updateCafAddress(HttpServletRequest request,
			@RequestParam(value = "cafNo", required = false) String cafNo,
			@RequestParam(value = "addressLine1", required = false) String addressLine1,
			@RequestParam(value = "addressLine2", required = false) String addressLine2,
			@RequestParam(value = "locality", required = false) String locality,
			@RequestParam(value = "village", required = false) String village,
			@RequestParam(value = "mandal", required = false) String mandal,
			@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "district", required = false) String district) {
		String status = "false";
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<String> response;
		try {
			logger.info("ComsController :: updateCafAddress() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "updateCafAddress?cafNo="+cafNo+"&addressLine1="+addressLine1+"&addressLine2="+addressLine2+"&locality="+locality+"&area="+area+"&village="+village+"&mandal="+mandal+"&district="+district;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			status = response.getBody();
			logger.info("ComsController :: updateCafAddress() :: End");
		} catch (Exception e) {
			logger.error("ComsController :: updateCafAddress() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return status;
	}

	public String getPonChangeUrl() {
		return ponChangeUrl;
	}

	public void setPonChangeUrl(String ponChangeUrl) {
		this.ponChangeUrl = ponChangeUrl;
	}

	
	
	
	
	
	/**Upload Subscribers**/
	@RequestMapping(value = "/uploadSubscribers",  method=RequestMethod.GET)
	public String uploadSubscribersFile(
			 Model model,
			HttpServletRequest request) {

		return "uploadSubscribers";
	}
	
	
	@RequestMapping(value = "/uploadSubscribersFile",  method=RequestMethod.POST)
	public String uploadSubscribersFile(
			 @RequestParam(value="uploadSubscribersFile",required=false) MultipartFile file, Model model,
			HttpServletRequest request) {
		TmsHelperDTO tmsDetailsHelper = new TmsHelperDTO();
	
		
		try {


				if (file != null && !file.isEmpty()) {

					logger.info("ComsController :: uploadSubscribersFile() :: Start");
					String name = file.getOriginalFilename();
					String ext = FilenameUtils.getExtension(name);
					if (ext != null && ext.equalsIgnoreCase("xlsx") && ext != "") {
						List<String> subscriberCode = FileUtil.extractSubscribersExcelFile(file, model, request);//added request arg -20-02-2018
						if (!model.containsAttribute("errorMsg") && (!subscriberCode.isEmpty())) {
							tmsDetailsHelper.setStatus("<font color='green'>  Successfully uploaded </font>");
							model.addAttribute("subscriberCode",String.join(",", subscriberCode));
							List<String> messages = new ArrayList<String>();
							messages.add("ఏపీఎస్ యఫ్ యల్ వినియోగదారులకు గమనిక : మీ కేబుల్ బిల్ జమ కాలేదు, దయచేసి మీ లోకల్ కేబుల్ ఆపరేటర్ కి బిల్ చెల్లిచండి లేనియడల  మీ కనెక్షన్ నిలిపివేయబడుతుంది .");
							messages.add("చందాదారులకు గమనిక: మీ బిల్లు సకాలములొ చల్లించనందున ఏపి ఫైబర్ సేవలు నిలిపివేయబడును. వివరాలకొరకు వెంటనే మీ కెబుల్ ఆపరేటర్ను సంప్రదించగలరు.");
							model.addAttribute("messages", messages);
						
						}else{
							String errorMsg= (String)model.asMap().get("errorMsg");
							if (errorMsg!=null)
							tmsDetailsHelper.setStatus("<font color='red'> " + errorMsg+  "</font>");
						}							

							} else{
								String errorMsg= (String)model.asMap().get("errorMsg");
								if (errorMsg!=null)
								tmsDetailsHelper.setStatus("<font color='red'>  error :: Xlsx Format is Not Correct.<br>" + errorMsg+  "</font>");
							}							

					} else
						tmsDetailsHelper.setStatus("Upload valid xlsx Document....");
					logger.info("ComsController :: uploadSubscribersFile() :: End");
					model.addAttribute("status", tmsDetailsHelper.getStatus());
			
		} catch (Exception e) {
			logger.error("ComsController :: uploadCpeStockFile() :: " + e);
			tmsDetailsHelper.setStatus(e.getMessage());
		} finally {
		}

		return "uploadSubscribers";
	}	
}
