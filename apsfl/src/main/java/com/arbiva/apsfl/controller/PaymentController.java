package com.arbiva.apsfl.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.arbiva.apsfl.tms.dto.RequestDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.BillDeskResponseIndex;
import com.arbiva.apsfl.util.IpAddressValues;

@Controller
public class PaymentController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	IpAddressValues ipAddressValues;
	
	private static final Logger logger = Logger.getLogger(PaymentController.class);
	
	private static final String PIPE_PATTERN_REGEX = Pattern.quote("|");
	private static final String CUSTOMER_ID = "<CustomerID>(.+?)</CustomerID>";
	private static final String TRANSACTION_ID = "<TransactionID>(.+?)</TransactionID>";
	private static final String AMOUNT_REGEX = "<Amount>(.+?)</Amount>";
	private static final String MESSAGE = "<Message>(.+?)</Message>";
	
	
	@RequestMapping(value = "/pgRequest", method = RequestMethod.POST)
	public void saveCreditCardPayment(Model model, HttpServletResponse response, HttpServletRequest request,RequestDTO requestDTO) throws IOException {

		HttpEntity<RequestDTO> httpEntity;
		ResponseEntity<String> res;
		String pgURL = null;
		try {
			//RequestDTO requestDTOObj = new RequestDTO(requestDTO);
			logger.info("PaymentController :: saveCreditCardPayment() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd(), requestDTO);
			String url = ipAddressValues.getTmsURL() + "pg/request";
			res = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			pgURL = res.getBody();
			
			logger.info("URL PG Request ====>" + request.getRequestURL().toString());
			logger.info("URL ====>" + pgURL);
			
			logger.info("PaymentController :: saveCreditCardPayment() :: End");

		} catch (Exception e) {
			logger.error("PaymentController :: saveCreditCardPayment() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			res = null;
			requestDTO = null;
		}
		
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.sendRedirect(pgURL != null ? pgURL: "paymentError");

	}
	@RequestMapping(value = "/paymentError", method = RequestMethod.GET)
	public String paymentError( ) {
		logger.info("PaymentController :: paymentError() :: Start");
		return "paymentfailure";
	}
	
	@RequestMapping(value = "/dsresponse", method = RequestMethod.POST)
	public String processDigitSignaturePaymentResponse( @RequestParam("xmlresponse") String paymentResponse, Model model) {
		String response = null;
		String respnseValue = null;
		HttpEntity<String> httpEntity ;
		ResponseEntity<String> res;
		try {
			logger.info("PaymentController :: processDigitSignaturePaymentResponse() :: Start");
			model.addAttribute("customerID",this.getTagValue(CUSTOMER_ID,paymentResponse));
			model.addAttribute("txnReferenceNO",this.getTagValue(TRANSACTION_ID,paymentResponse));
			model.addAttribute("txnAmount",this.getTagValue(AMOUNT_REGEX,paymentResponse));
			model.addAttribute("message",this.getTagValue(MESSAGE,paymentResponse));
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "pg/dsresponse?xmlstring="+paymentResponse;
			res = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
			response = res.getBody();
			logger.info("Respose From DIGIT_SECURE  "+response);
			
			
			if(response != null && response.equalsIgnoreCase("Success"))
				respnseValue =  "paymentsuccess";
			else
				respnseValue = "paymentfailure";
			
			logger.info("PaymentController :: processDigitSignaturePaymentResponse() :: End");
		} catch (Exception e) {
			respnseValue = "paymentfailure";
			logger.error("PaymentController :: processDigitSignaturePaymentResponse() :: "+e);
			model.addAttribute("error", e.getMessage());
		}finally{
			httpEntity = null;
			res = null;
		}
		
		
		
		return respnseValue;
	}
	
	@RequestMapping(value = "/bdresponse", method = RequestMethod.POST)
	public String processBilldeskPaymentResponse( @RequestParam("msg") String paymentResponse, Model model) {
		String respnseValue = null;
		String response = null;
		HttpEntity<String> httpEntity ;
		ResponseEntity<String> res;
		try {
			logger.info("PaymentController :: processBilldeskPaymentResponse() :: Start");
			logger.info("Respose From BILLDESK msg ====> "+paymentResponse);
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			String url = ipAddressValues.getTmsURL() + "pg/bdresponse?msg="+paymentResponse;
			res = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
			response = res.getBody();
			logger.info("Respose From BILLDESK  "+response);
			
			model.addAttribute("customerID",this.getHiddenFields(paymentResponse,BillDeskResponseIndex.CUSTOMERID.getIndex()));
			model.addAttribute("txnReferenceNO",this.getHiddenFields(paymentResponse,BillDeskResponseIndex.TXNREFERENCENO.getIndex()));
			model.addAttribute("txnAmount",this.getHiddenFields(paymentResponse,BillDeskResponseIndex.TXNAMOUNT.getIndex()));
			model.addAttribute("currencyName",this.getHiddenFields(paymentResponse,BillDeskResponseIndex.CURRENCYNAME.getIndex()));
			
			if(response != null && response.equalsIgnoreCase("Success"))
				respnseValue = "paymentsuccess";
			else
				respnseValue = "paymentfailure";
			logger.info("PaymentController :: processBilldeskPaymentResponse() :: End");
		} catch (Exception e) {
			logger.error("PaymentController :: processBilldeskPaymentResponse() :: "+e);
			model.addAttribute("error", e.getMessage());
		}finally{
			response = null;
			httpEntity = null;
			res = null;
		}
		
		return respnseValue;
	}
	
	
	
	private String getHiddenFields(String paymentResponse, int index) {
		logger.info("Index ====>" + index);
		logger.info("Index Value ====>" + paymentResponse.split(PIPE_PATTERN_REGEX)[index]);
		
		return paymentResponse.split(PIPE_PATTERN_REGEX)[index];
	}
	
	private  String getTagValue(String regex, String content) {
		
		Matcher matcher;
		try {
			logger.info("PaymentController :: getTagValue() :: Start");	
		matcher = Pattern.compile(regex).matcher(content);
		while (matcher.find()) {
			return matcher.group(1);
		}
		logger.info("PaymentController :: getTagValue() :: End");	
	   }catch (Exception e) {
		   logger.error("PaymentController :: getTagValue() :: "+e);	
		e.printStackTrace();
	}finally {
		matcher = null;
	}
		return null;
	}

	

}
