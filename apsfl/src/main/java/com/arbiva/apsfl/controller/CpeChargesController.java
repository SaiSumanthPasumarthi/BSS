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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.arbiva.apsfl.tms.dto.CpeCharHelperDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

@Controller
public class CpeChargesController {
	
	private static final Logger logger = Logger.getLogger(CpeChargesController.class);

	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	IpAddressValues ipAddressValues;

	
	@RequestMapping(value = "/createCpeChrgAgmt", method = RequestMethod.GET)
	public String createCpeChrgAgmt(Model model, HttpServletRequest request) {
		
		CpeCharHelperDTO cpeCharHelperDTO = new CpeCharHelperDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<CpeCharHelperDTO> response;
		String url = "";
		try {
			logger.info("CpeChargesController :: createCpeChrgAgmt() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "createCpeChrgAgmt";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,CpeCharHelperDTO.class);
			cpeCharHelperDTO = response.getBody();
			logger.info("CpeChargesController :: createCpeChrgAgmt() :: End");
		} catch (Exception e) {
			logger.error("CpeChargesController :: createCpeChrgAgmt() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			url = null;
		}
		model.addAttribute("cpeTmplList", cpeCharHelperDTO.getCpeTmplList());
		return "cpeChargeAgmt";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getChargeCodesByTemplate", method = RequestMethod.GET)
	@ResponseBody
	public List<CpeCharHelperDTO> getChargeCodesByTemplate(@RequestParam("tmplCode") String tmplCode) {
		
		List<CpeCharHelperDTO> list = new ArrayList<CpeCharHelperDTO>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		String url = "";
		try {
			logger.info("CpeChargesController :: getChargeCodesByTemplate() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "getChargeCodesByTemplate?tmplCode="+tmplCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,ArrayList.class);
			list = response.getBody();
			logger.info("CpeChargesController :: getChargeCodesByTemplate() :: End");
		} catch (Exception e) {
			logger.error("CpeChargesController :: getChargeCodesByTemplate() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			url = null;
		}
		return list;
	}
	
	@RequestMapping(value = "/saveCpeChrgAgmt", method = RequestMethod.POST)
	@ResponseBody
	public String saveCpeChrgAgmt(@RequestBody CpeCharHelperDTO cpeCharHelperDTO) {
		
		String responce = "";
		HttpEntity<CpeCharHelperDTO> httpEntity;
		ResponseEntity<String> response;
		String url = "";
		try {
			logger.info("CpeChargesController :: saveCpeChrgAgmt() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(),cpeCharHelperDTO);
			url = ipAddressValues.getTmsURL() + "saveCpeChrgAgmt";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
			responce = response.getBody();
			logger.info("CpeChargesController :: saveCpeChrgAgmt() :: End");
		} catch (Exception e) {
			logger.error("CpeChargesController :: saveCpeChrgAgmt() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			url = null;
		}
		return responce;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/viewCpeChrgAgmt", method = RequestMethod.GET)
	public String viewCpeChrgAgmt(HttpServletRequest req,Model model) {
		
		List<CpeCharHelperDTO> responce =new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		String url = "";
		String tanantCode = (String) req.getSession().getAttribute("tenantcode");
		try {
			logger.info("CpeChargesController :: viewCpeChrgAgmt() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "viewCpeChrgAgmt?tanantCode="+tanantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,ArrayList.class);
			responce = response.getBody();
			logger.info("CpeChargesController :: viewCpeChrgAgmt() :: End");
		} catch (Exception e) {
			logger.error("CpeChargesController :: viewCpeChrgAgmt() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			url = null;
		}
		model.addAttribute("cpeAggrList", responce);
		return "viewCpeChargeAgmt";
	}

}
