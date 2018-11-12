package com.arbiva.apsfl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.arbiva.apsfl.tms.dto.CpeDemandNoteDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

@Controller
public class CpeDemandNoteController {
	
	private static final Logger logger = Logger.getLogger(CpeDemandNoteController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/lmoCpeRequestPortal", method = RequestMethod.GET)
	public String lmoCpeRequest(Model model, HttpServletRequest request, 
			@RequestParam(value="returnVal", required = false) String returnVal) {

		List<Object[]> lmosList = new ArrayList<>();
		HttpEntity<String> httpEntity = null;
		ResponseEntity<ArrayList> response = null;
		String tenantCode = request.getSession().getAttribute("tenantcode").toString();
		try {
			logger.info("CpeController :: createCpeOrder() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "getAllLmosByTenantCodeMigrData?tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			lmosList = response.getBody();
			
			logger.info("CpeController :: createCpeOrder() :: End");
		} catch (Exception e) {
			logger.error("CpeController :: createCpeOrder() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			response = null;
		}
		model.addAttribute("lmosList", lmosList);
		model.addAttribute("returnVal", returnVal);
		return "lmoCpeRequestPortal";
	}
	
	@RequestMapping(value = "/lmoCpeRequestSavePortal", method = RequestMethod.POST)
	public String lmoCpeRequestSavePortal( HttpServletRequest request, CpeDemandNoteDTO cpeHelperDTO) {
		HttpEntity<CpeDemandNoteDTO> httpEntity = null;
		ResponseEntity<String> response = null;
		String returnVal = null;
		String tenantCode = request.getSession().getAttribute("tenantcode").toString();
		cpeHelperDTO.setTenantCode(tenantCode);
		try {
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),cpeHelperDTO);
			String url = ipAddressValues.getTmsURL() + "lmoCpeRequestSavePortal";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			returnVal = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		returnVal = returnVal == null ? "<font color='red'>CPE Demand Request is Failed.</font>" : "<font color='Green'>CPE Demand / New Lmo CPE Request is Successfully Submitted.</font>" ;
		return "redirect:/lmoCpeRequestPortal?returnVal="+returnVal;
	}

}
