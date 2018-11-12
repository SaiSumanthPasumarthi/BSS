package com.arbiva.apsfl.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.arbiva.apsfl.coms.dto.Districts;
import com.arbiva.apsfl.dto.BillingEngineDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

@Controller
public class BEController {

	private static final Logger logger = Logger.getLogger(BEController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/billingengine", method = RequestMethod.GET)
	public String billingEngineHome(@ModelAttribute(value = "billingEngineDTO") BillingEngineDTO billingEngineDTO,
			Model model, HttpServletRequest request) {

		try {
			logger.info("BEController :: billingEngineHome() :: Start");

			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(),
					ipAddressValues.getComPwd());
			String url = ipAddressValues.getComURL() + "alldistricts";
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					ArrayList.class);
			ArrayList<Districts> districtsList = response.getBody();

			// Map<String,String> districtsMap = new HashMap<>();
			// for(Districts d : districtsList){
			// districtsMap.put(d.getDistrictUid().toString(),
			// d.getDistrictName());
			// }
			// model.addAllAttributes(districtsMap);

			// model.addAttribute("districtsMap",districtsMap);
			model.addAttribute("districtsList", districtsList);

			logger.info("BEController :: billingEngineHome() :: End");
		} catch (Exception e) {
			logger.error("BEController :: billingEngineHome() :: " + e);
			e.printStackTrace();
		} finally {

		}
		return "be.billingEngine";
	}

	@RequestMapping(value = "/billingenginesubmit", method = RequestMethod.POST)
	public String billingEngineSubmit(@ModelAttribute(value = "billingEngineDTO") BillingEngineDTO billingEngineDTO,
			Model model) {
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		try {
			logger.info("BEController :: billingEngineSubmit() :: Start");

			String path = "";

			String yearMonth = billingEngineDTO.getYear() + ((Integer.parseInt(billingEngineDTO.getMonth()) <= 9 && billingEngineDTO.getMonth().length() ==1)
					? ("0" + billingEngineDTO.getMonth()) : billingEngineDTO.getMonth());

			if (billingEngineDTO != null && billingEngineDTO.getSubmitType().equals("GENERATE_INVOICE")) {

				if (billingEngineDTO.getCustomerType().equals("INDIVIDUAL")) {
					path = "processcharge/" + billingEngineDTO.getDistrict() + "/" + yearMonth;
				} else if (billingEngineDTO.getCustomerType().equals("ENTERPRISE_GOVT")) {
					path = "processEnterpriseGovt/" + yearMonth;
				}
				else if (billingEngineDTO.getCustomerType().equals("ENTERPRISE_PRIVATE")) {
					path = "processEnterprisePrivate/" + yearMonth;
				}

			} else if (billingEngineDTO != null && billingEngineDTO.getSubmitType().equals("GENERATE_PDF")) {
				if (billingEngineDTO.getCustomerType().equals("INDIVIDUAL")) {
					path = "generateBillIndividual/" + billingEngineDTO.getDistrict() + "/" + yearMonth;
				} else if (billingEngineDTO.getCustomerType().equals("ENTERPRISE_GOVT")) {
					path = "generateBillEnterpriseGovt/" + yearMonth;
				}
				else if (billingEngineDTO.getCustomerType().equals("ENTERPRISE_PRIVATE")) {
					path = "generateBillEnterprisePrivate/" + yearMonth;
				}

			}else if (billingEngineDTO != null && billingEngineDTO.getSubmitType().equals("SEND_SMS_EMAIL")) {
				 
					path = "sendSmsAndEmailOnInvoices/" + yearMonth;
				 

			}

			
			HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(),
					ipAddressValues.getComPwd());
			String url1 = ipAddressValues.getComURL() + "alldistricts";
			ResponseEntity<ArrayList> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1,
					ArrayList.class);
			ArrayList<Districts> districtsList = response1.getBody();
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getBeUserName(), ipAddressValues.getBePwd());
			String url = ipAddressValues.getBeURL() + path;
			logger.info(url + "url");
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			logger.info(response + "response");

			String result = response.getBody();
			
			
			
			model.addAttribute("districtsList", districtsList);
			model.addAttribute("successMsg","Submited Successfully");

			logger.info("BEController :: billingEngineSubmit() :: End");
		} catch (Exception e) {
			logger.error("BEController :: billingEngineSubmit() :: " + e);
			model.addAttribute("errorMsg","Server Busy/Invalid Request. Try After Sometime");
			e.printStackTrace();
		} finally {
			
		}
		return "be.billingEngine";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/billingengineForSinglCust", method = RequestMethod.GET)
	public String billingengineForSinglCust(@ModelAttribute(value = "billingEngineDTO") BillingEngineDTO billingEngineDTO,
			Model model, HttpServletRequest request) {

		try {
			logger.info("BEController :: billingengineForSinglCust() :: Start");

			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(),
					ipAddressValues.getComPwd());
			String url = ipAddressValues.getComURL() + "alldistricts";
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					ArrayList.class);
			ArrayList<Districts> districtsList = response.getBody();
			model.addAttribute("districtsList", districtsList);

			logger.info("BEController :: billingengineForSinglCust() :: End");
		} catch (Exception e) {
			logger.error("BEController :: billingengineForSinglCust() :: " + e);
			e.printStackTrace();
		} finally {

		}
		return "billingEngineForCust";
	}
	
	@RequestMapping(value = "/billingenginesubmitForSinglCust", method = RequestMethod.POST)
	public String billingenginesubmitForSinglCust(@ModelAttribute(value = "billingEngineDTO") BillingEngineDTO billingEngineDTO,
			Model model) {
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		try {
			logger.info("BEController :: billingenginesubmitForSinglCust() :: Start");

			String path = "";
			
			String yearMonth = billingEngineDTO.getYear() + ((Integer.parseInt(billingEngineDTO.getMonth()) <= 9 && billingEngineDTO.getMonth().length() ==1)
					? ("0" + billingEngineDTO.getMonth()) : billingEngineDTO.getMonth());

			 if (billingEngineDTO != null && billingEngineDTO.getSubmitType().equals("GENERATE_PDF")) {
				if (billingEngineDTO.getCustomerType().equals("INDIVIDUAL")) {
					path = "generateBillIndividualForSingleCustomer/" + billingEngineDTO.getDistrict() + "/" + yearMonth + "/" + billingEngineDTO.getCustomerId();
				} else if (billingEngineDTO.getCustomerType().equals("ENTERPRISE_GOVT")) {
					path = "generateBillEnterpriseGovtForSingleCustomer/" + yearMonth + "/" + billingEngineDTO.getCustomerId();
				}
				else if (billingEngineDTO.getCustomerType().equals("ENTERPRISE_PRIVATE")) {
					path = "generateBillEnterprisePrivateForSingleCustomer/" + yearMonth + "/" + billingEngineDTO.getCustomerId();
				}

			}
			
			HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(),
					ipAddressValues.getComPwd());
			String url1 = ipAddressValues.getComURL() + "alldistricts";
			ResponseEntity<ArrayList> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1,
					ArrayList.class);
			ArrayList<Districts> districtsList = response1.getBody();
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getBeUserName(), ipAddressValues.getBePwd());
			String url = ipAddressValues.getBeURL() + path;
			logger.info(url + "url");
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			logger.info(response + "response");

			String result = response.getBody();
			
			model.addAttribute("districtsList", districtsList);
			model.addAttribute("successMsg","Submited Successfully");

			logger.info("BEController :: billingenginesubmitForSinglCust() :: End");
		} catch (Exception e) {
			logger.error("BEController :: billingenginesubmitForSinglCust() :: " + e);
			model.addAttribute("errorMsg","Server Busy/Invalid Request. Try After Sometime");
			e.printStackTrace();
		} finally {
			
		}
		return "billingEngineForCust";
	}
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/billingengineForSinglCust", method = RequestMethod.GET)
	public String billingEngineHomeForSingleCust(@ModelAttribute(value = "billingEngineDTO") BillingEngineDTO billingEngineDTO,
			Model model, HttpServletRequest request) {

		try {
			logger.info("BEController :: billingengineForSinglCust() :: Start");
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(),
					ipAddressValues.getComPwd());
			String url = ipAddressValues.getComURL() + "alldistricts";
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					ArrayList.class);
			ArrayList<Districts> districtsList = response.getBody();
			model.addAttribute("districtsList", districtsList);
			logger.info("BEController :: billingengineForSinglCust() :: End");
		} catch (Exception e) {
			logger.error("BEController :: billingengineForSinglCust() :: " + e);
			e.printStackTrace();
		} finally {

		}
		return "billingEngineForCust";
	}
	
	@RequestMapping(value = "/billingenginesubmitForSinglCust", method = RequestMethod.POST)
	public String billingEngineSubmitForSinglCust(@ModelAttribute(value = "billingEngineDTO") BillingEngineDTO billingEngineDTO,
			Model model) {
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		try {
			logger.info("BEController :: billingEngineSubmitForSinglCust() :: Start");
			String path = "";
			String yearMonth = billingEngineDTO.getYear() + ((Integer.parseInt(billingEngineDTO.getMonth()) <= 9 && billingEngineDTO.getMonth().length() ==1)
					? ("0" + billingEngineDTO.getMonth()) : billingEngineDTO.getMonth());
			path = "generateBillIndividualForSingleCustomer/" + billingEngineDTO.getDistrict() + "/" + yearMonth+ "/" + billingEngineDTO.getCustomerId();
			HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(),
					ipAddressValues.getComPwd());
			String url1 = ipAddressValues.getComURL() + "alldistricts";
			ResponseEntity<ArrayList> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1,
					ArrayList.class);
			ArrayList<Districts> districtsList = response1.getBody();
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getBeUserName(), ipAddressValues.getBePwd());
			String url = ipAddressValues.getBeURL() + path;
			logger.info(url + "url");
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			logger.info(response + "response");

			String result = response.getBody();
			
			model.addAttribute("districtsList", districtsList);
			model.addAttribute("successMsg","Submited Successfully");

			logger.info("BEController :: billingEngineSubmitForSinglCust() :: End");
		} catch (Exception e) {
			logger.error("BEController :: billingEngineSubmitForSinglCust() :: " + e);
			model.addAttribute("errorMsg","Server Busy/Invalid Request. Try After Sometime");
			e.printStackTrace();
		} finally {
			
		}
		return "billingEngineForCust";
	}
*/
}
