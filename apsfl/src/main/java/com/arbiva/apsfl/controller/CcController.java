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

import com.arbiva.apsfl.tms.dto.CpeHelperDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

@Controller
public class CcController {

	private static final Logger logger = Logger.getLogger(ComsController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;
	
	@Autowired
	HttpServletRequest httpServletRequest;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ccCustomerDetails",  method = { RequestMethod.POST, RequestMethod.GET })
	public String getCustomerDetails(Model model, HttpServletRequest request,
			                         @RequestParam(value = "custMob", required = false) Long custMob,
			                         @RequestParam(value = "custId", required = false) Long custId) {

		List<Object[]> customerList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<ArrayList> response;
		String message = "";
		
		try {
			logger.info("CcController :: getCustomerDetails() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCcUserName(), ipAddressValues.getCcPwd());
				
				if(custId != null && custMob ==null){
					url = ipAddressValues.getCcURL() + "ccCustomerDetails?custId="+custId ;
				}else{
					url = ipAddressValues.getCcURL() + "ccCustomerDetails?custId="+custMob;
				}
				response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
				customerList = response.getBody();
				
				if(customerList.isEmpty()) {
				
					message = "No Records Found in this Search Criteria";
				}
				model.addAttribute("customerList", customerList);
				model.addAttribute("Mobile", custMob);
				model.addAttribute("Id", custId);
				model.addAttribute("message",message);
				
				
			logger.info("CcController :: getCustomerDetails() :: End");
			
		} catch (Exception e) {
			logger.error("CcController :: getCustomerDetails() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			customerList = null;
			response = null;
		}
		return "customerdetails";
	}
	
	@RequestMapping(value = "/custCreditCardPayment", method = RequestMethod.POST)
	public String custCreditCardPayment(Model model, @RequestParam(value = "paymentMode") String paymentMode,
													 @RequestParam(value = "addAmt1") String amount,
													 @RequestParam(value = "custName") String name,
													 @RequestParam(value = "custId1") String custId,
													 @RequestParam(value = "cafNo") String cafNo,
													 @RequestParam(value = "distId") String distId) {
		
		CpeHelperDTO cpeHelperDTO = new CpeHelperDTO();
		try {

			logger.info("CcController :: custCreditCardPayment() :: Start");
			cpeHelperDTO.setPaymentMode(paymentMode);
			cpeHelperDTO.setTotalCost(amount);
			cpeHelperDTO.setTenantCode(custId);
			cpeHelperDTO.setPaymentType("onlineSelfCare");
			cpeHelperDTO.setTenantName(name);
			cpeHelperDTO.setDlvId(distId);
			cpeHelperDTO.setCafNo(cafNo);
			logger.info("CcController :: custCreditCardPayment() :: End");
		} catch (Exception e) {
			logger.error("CcController :: custCreditCardPayment() :: " + e);
			e.printStackTrace();
		}
		model.addAttribute("paymentHelperDTO", cpeHelperDTO);

		return "creditCardPayment";
	}

}
