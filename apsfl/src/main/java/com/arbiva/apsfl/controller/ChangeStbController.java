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

import com.arbiva.apsfl.coms.dto.ChangeStbDTO;
import com.arbiva.apsfl.coms.dto.ChangeStbHelperDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

/**
 * @author Srinivas V
 * @since Feb 08 2017
 */

@Controller
public class ChangeStbController {
	private static final Logger logger = Logger.getLogger(ChangeStbController.class);

	RestTemplate restTemplate = new RestTemplate();

	private static String addVal = "";
	private static String mobileVal = "";
	private static String cafVal = "";

	@Autowired
	IpAddressValues ipAddressValues;
/**
 * This method Is used to display ChangeStb view page
 * @return to JSP 
 */
	@RequestMapping(value = "/changeStbRequest", method = RequestMethod.GET)
	public String getchangeStb() {
		return "changeStb";
	}
/**
 * This method is USed to Display records by using aadhar number or mobile number or CAF number
 * @param aadharNo
 * @param mobileNumber
 * @param cafNumber
 * @param statusMessage
 * @param m  --Model Customers Lsit
 * @param request -- HttpServletRequest 
 * @return  CustomerList to View Page
 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getStbDetails", method = {RequestMethod.POST,RequestMethod.GET})
	public String getStbDetails(@RequestParam("aadharCardNo") String aadharNo,
			@RequestParam("mobileNumber") String mobileNumber, @RequestParam("cafNumber") String cafNumber,
			@RequestParam(value = "statusMessage", required = false) String statusMessage, Model m,
			HttpServletRequest request) {
		List<Object[]> customerList = new ArrayList<>();
		HttpEntity<String> httpEntity;
		String url = "";
		String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		String tenatType = (String) request.getSession(false).getAttribute("domain");
		
		if (aadharNo != null && !aadharNo.isEmpty()) {
			addVal = aadharNo;
			mobileVal = "";
			cafVal = "";
			try {
				logger.info("ChangeStbController :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				url = ipAddressValues.getComURL() + "getCafsByAadharNo?aadharNo=" + aadharNo + "&tenantCode="
						+ tenantCode+"&tenatType="+tenatType;
				customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeStbController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (mobileNumber != null && !mobileNumber.isEmpty()) {
			mobileVal = mobileNumber;
			addVal = "";
			cafVal = "";
			try {
				logger.info("ChangeStbController :: home() :: Start");

				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				url = ipAddressValues.getComURL() + "getCafsByMobileNo?mobileNumber=" + mobileNumber + "&tenantCode="
						+ tenantCode+"&tenatType="+tenatType;
				customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeStbController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (cafNumber != null && !cafNumber.isEmpty()) {
			cafVal = cafNumber;
			addVal = "";
			mobileVal = "";

			try {
				logger.info("ChangeStbController :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				url = ipAddressValues.getComURL() + "getCafsByCafNo?cafNumber=" + cafNumber + "&tenantCode="
						+ tenantCode+"&tenatType="+tenatType;
				customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeStbController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				addVal = "";
				mobileVal = "";
				cafVal = "";
				logger.info("ChangeStbController :: home() :: Start");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
				url = ipAddressValues.getComURL() + "getCafsByTenantCode?tenantCode=" + tenantCode+"&tenatType="+tenatType;
				customerList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class).getBody();
				m.addAttribute("customerList", customerList);
				logger.info("ChangeStbController :: home() :: End");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m.addAttribute("statusMessage", statusMessage);
		return "changeStb";
	}

	/**
	 * This Method is Used to get the list of STB based on CAF number
	 * @param cafNum
	 * @param request --HttpServletRequest
	 * @return  -- List of STB's details
	 */
	@RequestMapping(value = "/getStbDetailsByCafNo", method = RequestMethod.GET)
	@ResponseBody
	public ChangeStbHelperDTO getStbDetailsByCafNo(@RequestParam("cafNum") String cafNum, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		String url = "";
		ChangeStbHelperDTO cStbHelper = null;
		try {
			logger.info("ChangeStbController :: home() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getStbDetailsByCafNo?cafNum=" + cafNum;
			cStbHelper = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ChangeStbHelperDTO.class).getBody();
			logger.info("ChangeStbController :: home() :: End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cStbHelper;
	}

	/**
	 * This method is used to update the old STB mac address with new STB mac Address
	 * @param changeStbDto
	 * @param request --httpservletRequest
	 * @param m --model
	 * @return --Status message
	 */
	@RequestMapping(value = "/updatestbDetails", method = RequestMethod.POST)
	public String updatestbDetails(ChangeStbDTO changeStbDto, HttpServletRequest request, Model m) {
		HttpEntity<ChangeStbDTO> httpEntity;
		String url = "";
		String statusMessage = "";
		try {
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd(),
					changeStbDto);
			url = ipAddressValues.getComURL() + "updateStbMacNumber?tenantCode=" + tenantCode;
			statusMessage = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class).getBody();
			logger.info("ChangeStbController :: home() :: End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/getStbDetails?aadharCardNo=" + addVal + "&mobileNumber=" + mobileVal + "&cafNumber=" + cafVal
				+ "&statusMessage=" + statusMessage;
	}
	
	/**
	 * This method gives the status of new STB mac address is assigned to LMO   or LMO partner MSO
	 * @param newMacAdd
	 * @param request -- HttpServletRequest
	 * @param m -- MOdel
	 * @return --Status of the New STB  valid or not
	 */

	@RequestMapping(value = "/getNewStdStatus", method = RequestMethod.GET)
	@ResponseBody
	public String  getNewStdStatus(@RequestParam("newStbserial") String newStbserial, HttpServletRequest request, Model m) {
		HttpEntity<String> httpEntity;
		String url = "";
		String  statusMessage = null;
		try {
			logger.info("ChangeStbController :: home() :: Start");
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getNewStdStatus?newStbserial=" + newStbserial + "&tenantCode=" + tenantCode;
			statusMessage = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String .class).getBody();
			logger.info("ChangeStbController :: home() :: End");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusMessage;

	}

}
