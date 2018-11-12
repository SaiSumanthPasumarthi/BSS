package com.arbiva.apsfl.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.arbiva.apfgc.tenant.bo.CpeModelBO;
import com.arbiva.apsfl.tms.dto.CpeHelperDTO;
import com.arbiva.apsfl.tms.dto.CpeOrderSaveDTO;
import com.arbiva.apsfl.tms.dto.CpeStockVO;
import com.arbiva.apsfl.tms.dto.TmsHelperDTO;
import com.arbiva.apsfl.tms.model.CpeStock;
import com.arbiva.apsfl.tms.model.Lovs;
import com.arbiva.apsfl.tms.model.Tenant;
import com.arbiva.apsfl.tms.serviceImpl.DemandNoteServiceImpl;
import com.arbiva.apsfl.tms.serviceImpl.LovsServiceImpl;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;
import com.arbiva.apsfl.util.ReadXlsExcelFileForCPEStock;


@Controller
public class CpeController {

	private static final Logger logger = Logger.getLogger(CpeController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;
	
	@Autowired
	DemandNoteServiceImpl demandNoteServiceImpl;
	
	@Autowired
	LovsServiceImpl lovsService;
	
	
	
	
	

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createCpeOrder", method = RequestMethod.GET)
	public String createCpeOrder(Model model, HttpServletRequest request) {

		List<CpeHelperDTO> cpeMasterList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("CpeController :: createCpeOrder() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "createCpeOrder";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			cpeMasterList = response.getBody();
			
			logger.info("CpeController :: createCpeOrder() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: createCpeOrder() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		model.addAttribute("cpeMasterList", cpeMasterList);
		model.addAttribute("status", "create");
		return "cpeorder";
	}*/

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllCpeModelByCpeType", method = RequestMethod.GET)
	@ResponseBody
	public List<CpeHelperDTO> getAllCpeModelByCpeType(@RequestParam(value = "cpeType") String cpeType, @RequestParam(value = "profileId") String profileId) {

		List<CpeHelperDTO> cpeMasterList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("CpeController :: getAllCpeModelByCpeType() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getAllCpeModelByCpeType?cpeType=" + cpeType + "&profileId=" +profileId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			cpeMasterList = response.getBody();
			logger.info("CpeController :: getAllCpeModelByCpeType() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: getAllCpeModelByCpeType() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		return cpeMasterList;
	}

	@RequestMapping(value = "/getCpeChargesByProfileId", method = RequestMethod.GET)
	@ResponseBody
	public CpeHelperDTO getCpeChargesByProfileId(@RequestParam(value = "profileId") String profileId) {

		CpeHelperDTO cpeHelperDTO = new CpeHelperDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<CpeHelperDTO> response;
		try {
			logger.info("CpeController :: getCpeChargesByProfileId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getCpeChargesByProfileId?profileId=" + profileId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CpeHelperDTO.class);
			cpeHelperDTO = response.getBody();
			logger.info("CpeController :: getCpeChargesByProfileId() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: getCpeChargesByProfileId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		return cpeHelperDTO;
	}

	@RequestMapping(value = "/saveCpeOrders", method = RequestMethod.POST)
	@ResponseBody
	public String getCpeChargesByProfileId(@RequestBody List<CpeHelperDTO> cpeHelperDTOList,
			HttpServletRequest request) {

		String responce = "";
		String tenantCode = (String) request.getSession().getAttribute("tenantcode");
		String loginId = (String) request.getSession().getAttribute("loginID");
		CpeOrderSaveDTO cpeOrderSaveDTO = new CpeOrderSaveDTO();
		cpeOrderSaveDTO.setCpeModelsList(cpeHelperDTOList);
		cpeOrderSaveDTO.setLoginId(loginId);
		cpeOrderSaveDTO.setTenantCode(tenantCode);
		HttpEntity<CpeOrderSaveDTO> httpEntity;
		ResponseEntity<String> response;
		try {
			logger.info("CpeController :: getCpeChargesByProfileId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),
					cpeOrderSaveDTO);
			String url = ipAddressValues.getTmsURL() + "saveCpeOrders";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			responce = response.getBody();
			logger.info("CpeController :: getCpeChargesByProfileId() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: getCpeChargesByProfileId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
			cpeOrderSaveDTO = null;
		}
		return responce;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewApsflCpeOrder", method = RequestMethod.GET)
	public String viewCpeOrder(Model model, HttpServletRequest request) {

		List<CpeHelperDTO> cpeMasterList = new ArrayList<>();
		String tenantType = (String) request.getSession().getAttribute("domain");
		String tenantCode = (String) request.getSession().getAttribute("tenantcode");
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("CpeController :: viewCpeOrder() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "viewApsflCpeOrder?tenantCode=" + tenantCode + "&tenantType="
					+ tenantType;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			cpeMasterList = response.getBody();
			logger.info("CpeController :: viewCpeOrder() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: viewCpeOrder() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		model.addAttribute("cpeMasterList", cpeMasterList);
		model.addAttribute("status", "view");
		model.addAttribute("tenantType", tenantType.trim());
		return "viewCpeorder";
	}

	@RequestMapping(value = "/allocateCpe", method = { RequestMethod.POST, RequestMethod.GET })
	public String allocateCpe(Model model, @RequestParam(value = "demandId") String demandId,
			@RequestParam(value = "responce", required = false) String responce) {

		CpeHelperDTO cpeHelperDTO = new CpeHelperDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<CpeHelperDTO> response;
		try {
			logger.info("CpeController :: allocateCpe() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "allocateCpe?demandId=" + demandId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CpeHelperDTO.class);
			cpeHelperDTO = response.getBody();
			logger.info("CpeController :: allocateCpe() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: allocateCpe() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		model.addAttribute("cpeHelperDTO", cpeHelperDTO);
		model.addAttribute("responce", responce);
		return "allocateCpe";
	}

	@RequestMapping(value = "/saveCpeAllocation", method = RequestMethod.POST)
	public String saveCpeAllocation(Model model, @RequestParam(value = "demandId") String demandId,
			@RequestParam(value = "cpeIdsList") String[] cpeIdsList,
			@RequestParam(value = "tenantCode") String tenantCode,
			@RequestParam(value = "tenantType") String tenantType) {

		CpeHelperDTO cpeHelperDTO = new CpeHelperDTO();
		cpeHelperDTO.setTenantCode(tenantCode);
		cpeHelperDTO.setDemandId(demandId);
		cpeHelperDTO.setCpeSlnoList(Arrays.asList(cpeIdsList));
		cpeHelperDTO.setTenantType(tenantType);
		String responce = "";
		HttpEntity<CpeHelperDTO> httpEntity;
		ResponseEntity<String> response;
		try {
			logger.info("CpeController :: saveCpeAllocation() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),
					cpeHelperDTO);
			String url = ipAddressValues.getTmsURL() + "saveCpeAllocation";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			responce = response.getBody();
			logger.info("CpeController :: saveCpeAllocation() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: saveCpeAllocation() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		model.addAttribute("cpeHelperDTO", cpeHelperDTO);
		return "redirect:/allocateCpe?demandId=" + demandId + "&responce=" + responce;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/uploadCpeStock", method = RequestMethod.GET)
	public String uploadCpeStock(Model model,
			@RequestParam(value = "returnResponse", required = false) String returnResponse,
			@RequestParam(value = "cpeSlNoList", required = false) List<String> cpeSlNoList,
			
			HttpServletRequest request) {

		List<CpeHelperDTO> cpeMasterList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		boolean isNull=false;

		String list[] = cpeSlNoList == null ? null : cpeSlNoList.toString().replace("[", "").replace("]", "").split(",");
				try {					
			logger.info("CpeController :: uploadCpeStock() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "createCpeOrder";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			cpeMasterList = response.getBody();
 if (returnResponse==null)
	 returnResponse="";
			logger.info("CpeController :: uploadCpeStock() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: uploadCpeStock() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
				
				
		model.addAttribute("cpeMasterList", cpeMasterList);
		model.addAttribute("status", "create");
		model.addAttribute("response", returnResponse);
		HttpSession session = request.getSession(false);
		if(list!=null){
			//cpe changes
			if (!list[0].equals("")){
		model.addAttribute("cpeSlNoList", Arrays.asList(list));
		
			session.setAttribute("cpeUploadedStockList",""); 
			}
			else{
				
				session.setAttribute("ErrorOrDuplicateList","");
			}
		
		}else 
			if (request.getParameter("tenantId")==null){
				session.setAttribute("ErrorOrDuplicateList","");
				session.setAttribute("cpeUploadedStockList","");
			}
				
		if (isNull){
		
	    session.setAttribute("ErrorOrDuplicateList",""); 
		}
		
		return "cpeStockUpload";
	}

	@RequestMapping(value = "/uploadCpeStockFile", method = RequestMethod.POST)
	public String uploadCpeStockFile(@RequestParam("profileId") String profileId,
			@RequestParam("tenantId") String tenantId, @RequestParam("cpeStockFile") MultipartFile file, Model model,
			HttpServletRequest request) {
		String tenantCode = (String) request.getSession().getAttribute("tenantcode");
		String loginId = (String) request.getSession().getAttribute("loginID");
		
		String response = null;
		HttpEntity<CpeHelperDTO> httpEntity;
		CpeHelperDTO cpeHealper = new CpeHelperDTO();
		CpeHelperDTO cpeDetailsHelper = new CpeHelperDTO();
		TmsHelperDTO tmsDetailsHelper = new TmsHelperDTO();
		List<String> cpeSlnoList= new ArrayList<String>();
		List<String> cpeErrorList= new ArrayList<String>();
		TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
		boolean balFlag = false;
		try {
			if (profileId != null && !profileId.equalsIgnoreCase("")) {

				if (file != null && !file.isEmpty()) {

					logger.info("CpeController :: uploadCpeStockFile() :: Start");
					String name = file.getOriginalFilename();
					String ext = FilenameUtils.getExtension(name);
					if (ext != null && ext.equalsIgnoreCase("xlsx") && ext != "") {
						List<CpeStock> cpeList = ReadXlsExcelFileForCPEStock.extractExcelFile(file, model, request);//added request arg -20-02-2018
						
						CpeStock cp = cpeList.size() > 0 ? cpeList.get(0) : null;
						
						if(cp != null){
							balFlag = demandNoteServiceImpl.getBalanceStatus(cpeList.size(),profileId,tenantId);
						}
						
						if (!model.containsAttribute("errorMsg") && (!cpeList.isEmpty())) {
						if(balFlag){
							
								
								cpeHealper.setProfileId(profileId);
								cpeHealper.setCpeList(cpeList);
								cpeHealper.setTenantId(tenantId);
								cpeHealper.setTenantCode(tenantCode);
								cpeHealper.setLoginId(loginId);
								
								
								for ( CpeStock cpeStock : cpeList )
								{
									cpeSlnoList.add(cpeStock.getCpeslno());
								}
								cpeDetailsHelper.setCpeSlnoList(cpeSlnoList);
								
								
								httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(),ipAddressValues.getTmsPwd(), cpeHealper);
								String url = ipAddressValues.getTmsURL() + "uploadCpeStockFile";
								ResponseEntity<TmsHelperDTO> res = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
										TmsHelperDTO.class);
								tmsDetailsHelper = res.getBody();
								
								
								
								HttpSession session = request.getSession(false);
								if (tmsDetailsHelper.getCpeStockList().isEmpty() &&  !cpeList.isEmpty()){
								
								httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(),ipAddressValues.getTmsPwd(), cpeDetailsHelper);
								String uploadSearchurl = ipAddressValues.getTmsURL() + "searchUploadedcpeDetails";
								ResponseEntity<TmsHelperDTO> responce = restTemplate.exchange(uploadSearchurl, HttpMethod.POST, httpEntity,
										TmsHelperDTO.class);
								tmsHelperDTO=responce.getBody();
								
								session.setAttribute("cpeUploadedStockList", tmsHelperDTO.getCpeStockList());
				
								}else
								{
									session.setAttribute("ErrorOrDuplicateList", tmsDetailsHelper.getCpeStockList());
									
									for(CpeStockVO cpeVO : tmsDetailsHelper.getCpeStockList()){
										cpeErrorList.add(cpeVO.getCpeSrlno());
									}
									
								}
							
								
						}else
							tmsDetailsHelper.setStatus("<font color='red'> error :: Insufficient Balance. </font>");
							} else{
								String errorMsg= (String)model.asMap().get("errorMsg");
								if (errorMsg!=null)
								tmsDetailsHelper.setStatus("<font color='red'>  error :: Xlsx Format is Not Correct.<br>" + errorMsg+  "</font>");
							}							

					} else
						tmsDetailsHelper.setStatus("Upload valid xlsx Document....");
					logger.info("CpeController :: uploadCpeStockFile() :: End");
					logger.info("Respose  " + response);

				} else {
					tmsDetailsHelper.setStatus("Please Upload File");
				}

			} else {
				tmsDetailsHelper.setStatus("Please Select Profile");
			}
		} catch (Exception e) {
			logger.error("CpeController :: uploadCpeStockFile() :: " + e);
			tmsDetailsHelper.setStatus(e.getMessage());
		} finally {
			httpEntity = null;
		}

		return "redirect:/uploadCpeStock?returnResponse=" + tmsDetailsHelper.getStatus() + "&cpeSlNoList="
			+ cpeErrorList + "&tenantId=" +tenantId;
	}

	@RequestMapping(value = "/cpePayment", method = RequestMethod.POST)
	public String cpePayment(Model model, @RequestParam(value = "demandId") String demandId,
			@RequestParam(value = "paymentMode") String paymentMode, @RequestParam(value = "dlvId") String dlvId,
			@RequestParam(value = "totalCost") String totalCost) {

		CpeHelperDTO cpeHelperDTO = new CpeHelperDTO();
		cpeHelperDTO.setDemandId(demandId);
		cpeHelperDTO.setDlvId(dlvId);
		cpeHelperDTO.setPaymentMode(paymentMode);
		cpeHelperDTO.setTotalCost(totalCost);
		HttpEntity<CpeHelperDTO> httpEntity;
		ResponseEntity<String> response;
		String responce = "";
		try {
			logger.info("CpeController :: cpePayment() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),
					cpeHelperDTO);
			String url = ipAddressValues.getTmsURL() + "cpePayment";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			responce = response.getBody();
			logger.info("CpeController :: cpePayment() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: cpePayment() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		model.addAttribute("cpeHelperDTO", cpeHelperDTO);
		return "redirect:/allocateCpe?demandId=" + demandId + "&responce=" + responce;
	}

	@RequestMapping(value = "/viewCpeByTenant", method = RequestMethod.POST)
	public String viewCpeByTenant(Model model, @RequestParam(value = "demandId") String demandId) {

		CpeHelperDTO cpeHelperDTO = new CpeHelperDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<CpeHelperDTO> response;
		try {
			logger.info("CpeController :: viewCpeByTenant() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "allocateCpe?demandId=" + demandId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CpeHelperDTO.class);
			cpeHelperDTO = response.getBody();
			logger.info("CpeController :: viewCpeByTenant() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: viewCpeByTenant() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		model.addAttribute("cpeHelperDTO", cpeHelperDTO);
		return "viewTenantCpe";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getCpeNosByDlvId", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getCpeNosByDlvId(Model model, @RequestParam(value = "dlvId") String dlvId) {

		List<String> responce = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("CpeController :: getCpeNosByDlvId() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getCpeNosByDlvId?dlvId=" + dlvId;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			responce = response.getBody();
			logger.info("CpeController :: getCpeNosByDlvId() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: getCpeNosByDlvId() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		return responce;
	}

	/*
	 * @RequestMapping(value = "/cpeCreditCardPayment", method =
	 * RequestMethod.POST) public String cpeCreditCardPayment(Model
	 * model, @RequestBody CpeHelperDTO cpeHelperDTO) {
	 * 
	 * model.addAttribute("cpeHelperDTO", cpeHelperDTO); return
	 * "creditCardPayment"; }
	 */

	@RequestMapping(value = "/cpeCreditCardPayment", method = RequestMethod.POST)
	public String cpeCreditCardPayment(Model model, @RequestParam(value = "demandId") String demandId,
			@RequestParam(value = "paymentMode") String paymentMode, @RequestParam(value = "dlvId") String dlvId,
			@RequestParam(value = "totalCost") String totalCost, @RequestParam(value = "tenantCode") String tenantCode,
			@RequestParam(value = "tenantName") String tenantName) {
		ResponseEntity<Tenant> response;
		CpeHelperDTO cpeHelperDTO = new CpeHelperDTO();
		HttpEntity<String> httpEntity;
		Tenant tenant = null;
		try {

			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getTenantByTenantCode?tenantCode=" + tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Tenant.class);
			tenant = response.getBody();

			logger.info("CpeController :: cpeCreditCardPayment() :: Start");
			cpeHelperDTO.setDemandId(demandId);
			cpeHelperDTO.setPaymentMode(paymentMode);
			cpeHelperDTO.setDlvId(dlvId);
			cpeHelperDTO.setTotalCost(totalCost);
			cpeHelperDTO.setTenantCode(tenantCode);
			cpeHelperDTO.setTenantName(tenantName);
			cpeHelperDTO.setEmailId(tenant.getEmailId1());
			cpeHelperDTO.setPaymentType("cpe");
			logger.info("CpeController :: cpeCreditCardPayment() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: cpeCreditCardPayment() :: " + e);
			e.printStackTrace();
		}
		model.addAttribute("paymentHelperDTO", cpeHelperDTO);
		return "creditCardPayment";
	}

	/*@SuppressWarnings("unused")
	private Model getHeader(Model model, HttpServletRequest req) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		UserMenuItemsRestDTO userMenuObj = new UserMenuItemsRestDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<UserMenuItemsRestDTO> response;
		try {
			logger.info("CpeController :: getHeader() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd());
			String url = ipAddressValues.getUmsURL() + "getHeader?userName=" + userName;
			logger.info(url + "url");
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, UserMenuItemsRestDTO.class);
			logger.info(response + "response");
			userMenuObj = response.getBody();
			logger.info(userMenuObj.getMenuItemList().size() + "menuListgggg");
			model.addAttribute("domain", userMenuObj.getUsersDTOobj().getDomain());
			model.addAttribute("tenantcode", userMenuObj.getUsersDTOobj().getTenantCode());
			model.addAttribute("tenantname", userMenuObj.getUsersDTOobj().getTenantName());
			model.addAttribute("moduleNameMap", userMenuObj.getMenuItemList());
			model.addAttribute("userName", userMenuObj.getUsersDTOobj().getUserName());
			model.addAttribute("loginID", userMenuObj.getUsersDTOobj().getLoginID());
			model.addAttribute("roleName", userMenuObj.getUsersDTOobj().getRoleName());
			logger.info("CpeController :: getHeader() :: End");
		} catch (Exception e) {
			e.printStackTrace();
			;
			logger.error("CpeController :: getHeader() :: " + e);
		} finally {
			httpEntity = null;
			response = null;
			userMenuObj = null;
			user = null;
		}
		return model;
	}
	*/
	@RequestMapping(value = "/downloadExcelFile", method = RequestMethod.GET)
	public void downLoadApk(HttpServletRequest request, HttpServletResponse response){

		File file = null;
		file = new File(ipAddressValues.getExcelPath());
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
	
	@RequestMapping(value = "/getCpeModelInfoBySrlNo", method = RequestMethod.GET)
	@ResponseBody
	public CpeModelBO getCpeModelInfoBySrlNo(Model model, @RequestParam(value = "cpeSrlNo") String cpeSrlNo) {

		CpeModelBO responce = new CpeModelBO();
			responce = demandNoteServiceImpl.getCpeModelInfoBySrlNo(cpeSrlNo);
		return responce;
	}

	
	@RequestMapping(value = "/addPrefix", method = RequestMethod.POST)
	@ResponseBody
	public boolean addPrefix( @RequestParam(value = "prefixValue") String prefixValue,
			                  @RequestParam(value = "cpeType") String cpeType){
		
		
		if (cpeType.contains("ONU")){
			 
			if ((lovsService.addLovsByLovName("CPE ONU PREFIXES",prefixValue)))
					return true;
					
			 
		 }else if (cpeType.contains("IPTV"))
		 {
			if( (lovsService.addLovsByLovName("CPE IPTV PREFIXES",prefixValue)))
					return true;
		 }
		
		return false;
		
		
	}
	
	
	@RequestMapping(value = "/deletePrefix", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteByLovsByLovName( @RequestBody String[] prefixes,
			                           @RequestParam(value = "cpeType") String cpeType,
			                           HttpServletRequest request){
		
		
		if (cpeType.contains("ONU")){
			 
			lovsService.deleteByLovsByLovName("CPE ONU PREFIXES",prefixes);
					
					
			 
		 }else if (cpeType.contains("IPTV"))
		 {
			lovsService.deleteByLovsByLovName("CPE IPTV PREFIXES",prefixes);
					
		 }

	}

	@RequestMapping(value = "/getCpePrefixesByCpeType", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getCpePrefixesByCpeType(@RequestParam(value = "cpeType") String cpeType, Model model) {

		
		ResponseEntity<ArrayList> response;
		
		List<String> cpePrefixList=new ArrayList<String>();
		
		try {
			logger.info("CpeController :: getCpePrefixesByCpeType() :: Start");
			
			if (cpePrefixList!=null)
				 cpePrefixList.clear();
			 if (cpeType.contains("ONU")){
				 
				 if (lovsService.findByLovsByLovName("CPE ONU PREFIXES")!=null)
				 for (Lovs lov : lovsService.findByLovsByLovName("CPE ONU PREFIXES"))
				 {
					 cpePrefixList.add(lov.getLovValue());
					 
				 }
				 
				 
			 }else if (cpeType.contains("IPTV"))
			 {

				 if (lovsService.findByLovsByLovName("CPE IPTV PREFIXES")!=null)
					 for (Lovs lov : lovsService.findByLovsByLovName("CPE IPTV PREFIXES"))
					 {
						 cpePrefixList.add(lov.getLovValue());
						 
					 }
				 
			 }
			
			logger.info("CpeController :: getCpePrefixesByCpeType() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: getCpePrefixesByCpeType() :: " + e);
			e.printStackTrace();
		} finally {
			
			response = null;
		}
		return cpePrefixList;
	}
	

	
	
	
	
	
}	
