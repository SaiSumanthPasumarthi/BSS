package com.arbiva.apsfl.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.arbiva.apsfl.coms.dto.ChangeOnuDTO;
import com.arbiva.apsfl.coms.dto.ChangeOnuHelperDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

/**
 * @author Srinivas V
 * @since Feb 08 2017
 */
 
@Controller
public class ChangeOnuController {
	private static final Logger logger = Logger.getLogger(ChangeOnuController.class);

	RestTemplate restTemplate = new RestTemplate();
	private static String addVal = "";
	private static String mobileVal = "";
	private static String cafVal = "";

	@Autowired
	IpAddressValues ipAddressValues;

	/**
	 * @return --View Page
	 */
	@RequestMapping(value = "/changeOnuRequest", method = RequestMethod.GET)
	public String changeOnuRequest() {
		return "changeOnu";

	}

	/**
	 * @param changeOnuDto
	 * @param statusMessage
	 * @param m
	 * @param request
	 * @return Customer List
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getOnuDetails", method = {RequestMethod.POST,RequestMethod.GET})
	public String getOnuDetails(ChangeOnuDTO changeOnuDto,
			@RequestParam(value = "statusMessage", required = false) String statusMessage, Model m,
			HttpServletRequest request) {

		List<Object[]> customerList = new ArrayList<>();
		HttpEntity<ChangeOnuDTO> httpEntity;
		String url = "";
		
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenantType = (String) request.getSession(false).getAttribute("domain");
		
		changeOnuDto.setTenantCodeOnuChange(tenantCode);
		changeOnuDto.setTenantType(tenantType);
		
	

		if (changeOnuDto.getAadharNumberOnuChange() != null && !changeOnuDto.getAadharNumberOnuChange().isEmpty()) {
			try {
				addVal = changeOnuDto.getAadharNumberOnuChange();
				mobileVal = "";
				cafVal = "";
				logger.info("ChangeOnuController :: home() :: Start");
				
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),
						changeOnuDto);
				url = ipAddressValues.getComURL() + "getCafsByAadharNumber";
				customerList = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeOnuController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (changeOnuDto.getMobileNumberOnuChange() != null
				&& !changeOnuDto.getMobileNumberOnuChange().isEmpty()) {
			try {
				mobileVal = changeOnuDto.getMobileNumberOnuChange();
				addVal = "";
				cafVal = "";
				logger.info("ChangeOnuController :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),
						changeOnuDto);
				url = ipAddressValues.getComURL() + "getCafsByMobileNumber";
				customerList = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeOnuController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (changeOnuDto.getCafNumberOnuChange() != null && !changeOnuDto.getCafNumberOnuChange().isEmpty()) {
			try {
				cafVal = changeOnuDto.getCafNumberOnuChange();
				addVal = "";
				mobileVal = "";
				logger.info("ChangeOnuController :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),
						changeOnuDto);
				url = ipAddressValues.getComURL() + "getCafsByCafNumber";
				customerList = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeOnuController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				addVal = "";
				mobileVal = "";
				cafVal = "";
				logger.info("ChangeOnuController :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),
						changeOnuDto);
				url = ipAddressValues.getComURL() + "getCafsByTenant";
				customerList = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeOnuController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m.addAttribute("statusMessage", statusMessage);
		return "changeOnu";

	}

	/**
	 * @param cafNumber
	 * @param request
	 * @return Onu Details
	 */
	@RequestMapping(value = "/getOnuDetailsByCafNo", method = RequestMethod.GET)
	@ResponseBody
	public ChangeOnuHelperDTO getOnuDetailsByCafNo(@RequestParam("cafNumber") String cafNumber,
			HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ChangeOnuHelperDTO onuList = null;
		try {
			logger.info("ChangeStbController :: home() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getOnuDetailsByCafNo?cafNumber=" + cafNumber;
			onuList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ChangeOnuHelperDTO.class).getBody();
			logger.info("ChangeStbController :: home() :: End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onuList;
	}

	/**
	 * @param changeOnuDTO
	 * @param request
	 * @param m - Model
	 * @return Update Status
	 */
	@RequestMapping(value = "updateOnuChangeDetails", method = RequestMethod.POST)
	
	public String updateOnuChangeDetails(ChangeOnuDTO changeOnuDTO, HttpServletRequest request, Model m) {
		HttpEntity<ChangeOnuDTO> httpEntity;
		String url = "";
		String statusMessage = "";
		try {
			changeOnuDTO.setTenantCodeOnuChange((String) request.getSession(false).getAttribute("tenantcode"));
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),
					changeOnuDTO);
			url = ipAddressValues.getComURL() + "updateOnuChangeDetails";
			statusMessage = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class).getBody();
			logger.info("ChangeStbController :: home() :: End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/getOnuDetails?aadharNumberOnuChange=" +addVal+ "&mobileNumberOnuChange=" +mobileVal+ "&cafNumberOnuChange=" +cafVal+ "&statusMessage=" + statusMessage;
	}
	
	/**
	 * Validate ONU based on availability of ONU serial at LMO or LMO partner Mso
	 * @param changeOnuDTO
	 * @param request -HttpServletRequest
	 * @param m --Model
	 * @return ONU Validation Status 
	 */
	@RequestMapping(value="/getOnuStatus",method=RequestMethod.GET)
	@ResponseBody
	public String getNewOnuStatus(ChangeOnuDTO changeOnuDTO, HttpServletRequest request, Model m){

		HttpEntity<ChangeOnuDTO> httpEntity;
		String url = "";
		String statusMessage = "";
		try {
			logger.info("ChangeStbController :: home() :: Start");
			changeOnuDTO.setTenantCodeOnuChange((String) request.getSession(false).getAttribute("tenantcode"));
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),changeOnuDTO);
			url = ipAddressValues.getComURL() + "getNewOnuStatus";
			statusMessage = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class).getBody();
			logger.info("ChangeStbController :: home() :: End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusMessage;
	}
}
