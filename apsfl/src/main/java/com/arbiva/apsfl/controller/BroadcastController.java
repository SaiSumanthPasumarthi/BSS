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

import com.arbiva.apsfl.broadcast.dto.IptvChannelRateCard;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

@Controller
public class BroadcastController {
	
	private static final Logger logger = Logger.getLogger(BroadcastController.class);
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	IpAddressValues ipAddressValues;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/viewMonthlyInvoice ", method = RequestMethod.GET)
	public String viewMonthlyInvoicePage(Model model, HttpServletRequest request) {
		
		List<Object[]> monthlyInvoiceList;
		
		try {
			logger.info("BroadcastController :: viewMonthlyInvoicePage :: Start");
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "viewMonthlyInvoice";
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			monthlyInvoiceList = response.getBody();
			model.addAttribute("monthlyInvoiceList", monthlyInvoiceList);
			logger.info("BroadcastController :: viewMonthlyInvoicePage :: End");
		} catch (Exception e) {
			logger.error("BroadcastController :: viewMonthlyInvoicePage :: " +e);
			e.printStackTrace();
		}finally {
			monthlyInvoiceList = null;
		}
		return "viewmonthlyinvoice";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/viewRateCard ", method = RequestMethod.GET)
	public String viewBroadcastersPage(Model model, HttpServletRequest request) {
		
		List<IptvChannelRateCard> broadcasterCodeList;
		
		try {
			logger.info("BroadcastController :: viewBroadcastersPage :: Start");
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "viewRateCard";
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			broadcasterCodeList = response.getBody();
			model.addAttribute("broadcasterCodeList", broadcasterCodeList);
			logger.info("BroadcastController :: viewBroadcastersPage :: End");
		} catch (Exception e) {
			logger.error("BroadcastController :: viewBroadcastersPage :: "+e);
			e.printStackTrace();
		}finally {
			broadcasterCodeList = null;
		}
		return "viewratecard";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewBroadcasterPriceDetails", method = RequestMethod.POST)
	public String viewRateCardPage(Model model, @RequestParam(value = "broadcasterCode", required = false) String broadcasterCode, 
			                       HttpServletRequest request) {
		
		List<Object[]> broadcasterCodeList;
		
		try {
			logger.info("BroadcastController :: viewRateCardPage :: Start");
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "viewBroadcasterPriceDetails?broadcasterCode="+broadcasterCode;
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			broadcasterCodeList = response.getBody();
			model.addAttribute("broadcasterCodeList", broadcasterCodeList);
			logger.info("BroadcastController :: viewRateCardPage :: End");
		} catch(Exception e) {
			logger.error("BroadcastController :: viewRateCardPage :: "+e);
			e.printStackTrace();
		}finally {
			broadcasterCodeList = null;
		}
		return "broadcasterspricedetails";
    }
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewChannelDetails", method = RequestMethod.POST)
	public String viewChannelListPage(Model model, @RequestParam(value = "priceGroupCode", required = false) String priceGroupCode,
			                         HttpServletRequest request) {
		
		List<Object[]> channelList;
		
		try {
			logger.info("BroadcastController :: viewChannelListPage :: Start");
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "viewChannelDetails?priceGroupCode="+priceGroupCode;
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			channelList = response.getBody();
			model.addAttribute("channelList", channelList);
			logger.info("BroadcastController :: viewChannelListPage :: End");
		} catch(Exception e) {
			logger.error("BroadcastController :: viewChannelListPage :: " +e);
			e.printStackTrace();
		}finally {
			channelList = null;
		}
		return "channeldetails";
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewInvoiceDetails", method = RequestMethod.POST)
	public String viewInvoiceDetailsPage(Model model, @RequestParam(value = "invNo", required = false) Long invNo,
			                         HttpServletRequest request) {
		
		List<Object[]> invoiceList;
		
		try {
			logger.info("BroadcastController :: viewInvoiceDetailsPage :: Start");
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "viewInvoiceDetails?invNo="+invNo;
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			invoiceList = response.getBody();
			model.addAttribute("invoiceList", invoiceList);
			logger.info("BroadcastController :: viewInvoiceDetailsPage :: End");
		} catch(Exception e) {
			logger.error("BroadcastController :: viewInvoiceDetailsPage :: " +e);
			e.printStackTrace();
		}finally {
			invoiceList = null;
		}
		return "invoicedetails";
    }
	
}
