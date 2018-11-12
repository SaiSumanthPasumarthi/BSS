package com.arbiva.apsfl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.arbiva.apsfl.em.dto.TPUsageErrorsDTO;
import com.arbiva.apsfl.tt.dto.ISUsageErrorsDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

/**
 * @author Gowthami
 *
 */
@Controller
public class ErrorManagementController {

	private static final Logger logger = Logger.getLogger(ErrorManagementController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/meISErrorProcess", method = RequestMethod.GET)
	public ModelAndView meErrorProcess(@ModelAttribute(value = "usageErrorsSearchDTO") UsageErrorsSearchDTO usageErrorsSearchDTO, BindingResult result,Model model,HttpServletRequest request) {
		logger.info(":::In mnpErrorProcess:::");
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getEmUserName(), ipAddressValues.getEmPwd());
		String url = ipAddressValues.getEmURL() + "getWipStagesInfo?appCode=IS_USAGE_ERRS_STATUS";
		ResponseEntity<TreeMap> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TreeMap.class);
		TreeMap<String,String> statusMap = response.getBody();
		model.addAttribute("statusMap",statusMap);
		return new ModelAndView("em.meISErrorProcess");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/meTPErrorProcess", method = RequestMethod.GET)
	public ModelAndView meTPErrorProcess(@ModelAttribute(value = "usageErrorsSearchDTO") UsageErrorsSearchDTO usageErrorsSearchDTO, BindingResult result,Model model,HttpServletRequest request) {
		logger.info(":::In mnpErrorProcess:::");
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getEmUserName(), ipAddressValues.getEmPwd());
		String url = ipAddressValues.getEmURL() + "getWipStagesInfo?appCode=TP_USAGE_ERRS_STATUS";
		ResponseEntity<TreeMap> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TreeMap.class);
		TreeMap<String,String> statusMap = response.getBody();
		model.addAttribute("statusMap",statusMap);
		return new ModelAndView("em.meTPErrorProcess");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getISUsageErrors", method = RequestMethod.GET)
	public @ResponseBody List<ISUsageErrorsDTO> getISUsageErrorsInfo(@RequestParam String status,@RequestParam String fromDate,@RequestParam String toDate,
			HttpServletRequest request) {
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getEmUserName(), ipAddressValues.getEmPwd());
		String uri = "getISUsageErrors?status="+status+"&fromDate="+fromDate+"&toDate="+toDate;
		String url = ipAddressValues.getEmURL() + uri;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		
		List<ISUsageErrorsDTO> usageErrsList = response.getBody();
		logger.info("In getUsageErrorsInfo" + usageErrsList.size());
		return usageErrsList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTPUsageErrors", method = RequestMethod.GET)
	public @ResponseBody List<TPUsageErrorsDTO> getTPUsageErrorsInfo(@RequestParam String status,@RequestParam String fromDate,@RequestParam String toDate,
			HttpServletRequest request) {
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getEmUserName(), ipAddressValues.getEmPwd());
		String uri = "getTPUsageErrors?status="+status+"&fromDate="+fromDate+"&toDate="+toDate;
		String url = ipAddressValues.getEmURL() + uri;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		
		List<TPUsageErrorsDTO> usageErrsList = response.getBody();
		logger.info("In getUsageErrorsInfo" + usageErrsList.size());
		return usageErrsList;
	}
	
	/*@RequestMapping(value = "/updateTPError", method = RequestMethod.GET)
	public @ResponseBody List<TPUsageErrorsDTO> updateTPError(@RequestParam String recordID,HttpServletRequest request) {
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getEmUserName(), ipAddressValues.getEmPwd());
		String uri = "updateTPError?recordID="+recordID;
		String url = ipAddressValues.getEmURL() + uri;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		List<TPUsageErrorsDTO> usageErrsList = response.getBody();
		logger.info("In getUsageErrorsInfo" + usageErrsList.size());
		return usageErrsList;
	}*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateTPError", method = RequestMethod.POST)
	public ModelAndView updateTPError(@ModelAttribute(value = "tpUsageErrorsDTOObj") TPUsageErrorsDTO tpUsageErrorsDTOObj,BindingResult result,Model model,@RequestParam String recordID,HttpServletRequest request) {
		
		logger.info(":::In meTPErrorProcess:::"+recordID);
		
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getEmUserName(), ipAddressValues.getEmPwd());
		String url = ipAddressValues.getEmURL() + "updateTPError?recordID="+recordID;
		ResponseEntity<TPUsageErrorsDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TPUsageErrorsDTO.class);
		tpUsageErrorsDTOObj = response.getBody();
		
		HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getEmUserName(), ipAddressValues.getEmPwd());
		String url1 = ipAddressValues.getEmURL() + "getWipStagesInfo?appCode=TP_USAGE_ERRS_STATUS";
		ResponseEntity<TreeMap> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, TreeMap.class);
		TreeMap<String,String> statusMap = response1.getBody();
		
		
		logger.info(statusMap.size()+"statusMap");
		
		System.out.println(statusMap.size()+"statusMap");
		
		model.addAttribute("recid",tpUsageErrorsDTOObj.getRecid());
		model.addAttribute("srcSrvcSubtype",tpUsageErrorsDTOObj.getSrcSrvcSubtype());
		model.addAttribute("fileSeqNo",tpUsageErrorsDTOObj.getFileSeqNo());
		model.addAttribute("roleOfNode",tpUsageErrorsDTOObj.getRoleOfNode());
		model.addAttribute("callingPartyAddress",tpUsageErrorsDTOObj.getCallingPartyAddress());
		model.addAttribute("calledPartyAddress",tpUsageErrorsDTOObj.getCalledPartyAddress());
		model.addAttribute("starttime",tpUsageErrorsDTOObj.getStarttime());
		model.addAttribute("endtime",tpUsageErrorsDTOObj.getEndtime());
		model.addAttribute("srcSpecType",tpUsageErrorsDTOObj.getSrcSpecType());
		model.addAttribute("localRecordSeqNo",tpUsageErrorsDTOObj.getLocalRecordSeqNo());
		model.addAttribute("callDuration",tpUsageErrorsDTOObj.getCallDuration());
		model.addAttribute("serviceCategory",tpUsageErrorsDTOObj.getServiceCategory());
		model.addAttribute("errorcode",tpUsageErrorsDTOObj.getErrorcode());
		model.addAttribute("serviceRequestTime",tpUsageErrorsDTOObj.getServiceRequestTime());
		model.addAttribute("orgIOI",tpUsageErrorsDTOObj.getOrgIOI());
		model.addAttribute("termIOI",tpUsageErrorsDTOObj.getTermIOI());
		model.addAttribute("recordSequenceNo",tpUsageErrorsDTOObj.getRecordSequenceNo());
		model.addAttribute("recCloseReason",tpUsageErrorsDTOObj.getRecCloseReason());
		model.addAttribute("subIDData",tpUsageErrorsDTOObj.getSubIDData());
		model.addAttribute("reqPartyAddress",tpUsageErrorsDTOObj.getReqPartyAddress());
		model.addAttribute("callDesc",tpUsageErrorsDTOObj.getCallDesc());
		model.addAttribute("serviceID",tpUsageErrorsDTOObj.getServiceID());
		model.addAttribute("valueAddedServiceID",tpUsageErrorsDTOObj.getValueAddedServiceID());
		model.addAttribute("supplementryServiceList",tpUsageErrorsDTOObj.getSupplementryServiceList());
		model.addAttribute("otherFields",tpUsageErrorsDTOObj.getOtherFields());
		model.addAttribute("createdon",tpUsageErrorsDTOObj.getCreatedon());
		model.addAttribute("processedon",tpUsageErrorsDTOObj.getProcessedon());
		model.addAttribute("status",tpUsageErrorsDTOObj.getStatus());
		model.addAttribute("statusMap",statusMap);
		
		return new ModelAndView("em.updateTP");
	}
	
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/updateError", method = RequestMethod.POST)
	public ModelAndView updateError(@ModelAttribute(value = "tpUsageErrorsDTOObj") TPUsageErrorsDTO tpUsageErrorsDTOObj,Model model, BindingResult result,HttpServletRequest request) {
	
		logger.info("Inside updateError");
		
		HttpEntity<TPUsageErrorsDTO> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getEmUserName(), ipAddressValues.getEmPwd(),tpUsageErrorsDTOObj);
		String url2 = ipAddressValues.getEmURL() + "updateError";
		ResponseEntity<String> response2 = restTemplate.exchange(url2, HttpMethod.POST, httpEntity2, String.class);
		String msg = response2.getBody();
		logger.info(msg+"Message in controller");
		model.addAttribute("msg",msg);
		logger.info("Inside updateError before view");
		return new ModelAndView("em.updateTP");
	}
	
	
	
	@RequestMapping(value = "/mnpErrorProcess")
	public ModelAndView mnpErrorProcess(Model model,HttpServletRequest request) {
		logger.info(":::In mnpErrorProcess:::");
		return new ModelAndView("em.mnpErrorProcess");
	}
}
