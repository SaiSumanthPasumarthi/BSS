package com.arbiva.apsfl.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

@Controller
public class TerminationController {
	
	private static final Logger logger = Logger.getLogger(TerminationController.class);
	
	@Autowired
	IpAddressValues ipAddressValues;
	
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value="/termination")
	public String terminate(){
		
		return "termination";
	}
	
	@RequestMapping(value="/getAllCafsByCustId" , method = {RequestMethod.POST,RequestMethod.GET})
	public String getALLCafsByCustomerId(@RequestParam(value="idValue") String idValue,
											@RequestParam(value="idType") String idType,
											Model m,HttpServletRequest request){
		
		
		Object[][] customerList = null ;
		HttpEntity<String> httpEntity;
		String url = "";
		String statusMessage = "";
		String cafNos =  null;
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenatType = (String) request.getSession(false).getAttribute("domain");
		if (idType != null && !idType.isEmpty() && idType.equalsIgnoreCase("aadhar")) {
			try {
				logger.info("TerminationController :: getALLCafsByCustomerId() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				url = ipAddressValues.getComURL() + "getCafsByAadharNo?aadharNo=" + idValue + "&tenantCode="
						+ tenantCode+"&tenatType="+tenatType;
				customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object[][].class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeStbController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (idType != null && !idType.isEmpty() && idType.equalsIgnoreCase("mobile")) {
			try {
				logger.info("ChangeStbController :: home() :: Start");

				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				url = ipAddressValues.getComURL() + "getCafsByMobileNo?mobileNumber=" + idValue + "&tenantCode="
						+ tenantCode+"&tenatType="+tenatType;;
				customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object[][].class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeStbController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (idType != null && !idType.isEmpty() && idType.equalsIgnoreCase("cafNo")) {
			try {
				logger.info("ChangeStbController :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				url = ipAddressValues.getComURL() + "getCafsByCafNo?cafNumber=" + idValue + "&tenantCode="
						+ tenantCode+"&tenatType="+tenatType;;
				customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object[][].class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeStbController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (idType != null && !idType.isEmpty() && idType.equalsIgnoreCase("trackId")) {
			try {
				logger.info("ChangeStbController :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				url = ipAddressValues.getComURL() + "getCafListByTrackId?trackId=" + idValue + "&tenantCode="
						+ tenantCode+"&tenatType="+tenatType;;
				customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object[][].class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeStbController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			statusMessage = "No records found for :: "+idValue;
		}
		
		if(customerList.length > 0){
			for(Object[] obj : customerList){
				
				cafNos = cafNos == null ? obj[0].toString() : cafNos +","+obj[0].toString();
			}
		}
		m.addAttribute("statusMessage", statusMessage);
		m.addAttribute("cafNos", cafNos);
		m.addAttribute("tenantCode", tenantCode);
		return "termination";
	}
	
	
	@RequestMapping(value = "/terminateCafs")
	@ResponseBody
	public  String terminateCafs(@RequestParam(value="cafsList") String cafs,@RequestParam(value="tenantCode") String tenantCode,
			@RequestParam(value="approvedby") String approvedby){
		
		HttpEntity<String> httpEntity = null;
		String url = "";
		String statusMessage = "";
		try{
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "terminateCafs?cafsList="+cafs + "&tenantCode="+tenantCode + "&approvedby="+approvedby;
			statusMessage = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
			
		}catch(Exception e){
			statusMessage = e.getMessage();
		}
		return statusMessage;
	}

}
