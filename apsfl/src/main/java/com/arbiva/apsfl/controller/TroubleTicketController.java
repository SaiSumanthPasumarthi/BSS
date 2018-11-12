package com.arbiva.apsfl.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.arbiva.apfgc.tenant.bo.PONWiseBo;
import com.arbiva.apsfl.coms.dto.CustomerCafBO;
import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.coms.dto.SplitterVO;
import com.arbiva.apsfl.dto.UsersDataPageObjectTT;
import com.arbiva.apsfl.scs.dto.TTIssueCodeAttributesDTO;
import com.arbiva.apsfl.tms.serviceImpl.DemandNoteServiceImpl;
import com.arbiva.apsfl.tt.dto.CafUsageDTO;
import com.arbiva.apsfl.tt.dto.LMOSLADTO;
import com.arbiva.apsfl.tt.dto.OntPowerStatusDTO;
import com.arbiva.apsfl.tt.dto.SLADTO;
import com.arbiva.apsfl.tt.dto.TTCustomerDTO;
import com.arbiva.apsfl.tt.dto.TTHelperDTO;
import com.arbiva.apsfl.tt.dto.TTHistoryDTO;
import com.arbiva.apsfl.tt.dto.TTTenantDTO;
import com.arbiva.apsfl.tt.dto.TroubleTicketDTO;
import com.arbiva.apsfl.tt.dto.ViewBillDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.FileUtil;
import com.arbiva.apsfl.util.IpAddressValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.sun.mail.iap.Response;

/**
 * @author Gowthami
 *
 */
@Controller
/*@SessionAttributes({ "loginID", "tenantcode", "domain", "pwd", "tenantname", "moduleNameMap", "userName", "roleName" })*/
public class TroubleTicketController {

	private static final Logger logger = Logger.getLogger(TroubleTicketController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;
	
	@Autowired
	DemandNoteServiceImpl demandNoteServiceImpl;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "/createTT")// /userCreation
	public ModelAndView createTT(@ModelAttribute(value = "troubleTicketDTO") TroubleTicketDTO troubleTicketDTO, BindingResult result,
			Model model,HttpServletRequest request) {
		
		logger.info(":::In CreateTT:::");
		model.addAttribute("tenantCode", request.getSession().getAttribute("tenantcode").toString());
		model.addAttribute("domain", request.getSession().getAttribute("domain").toString());
		return new ModelAndView("tt.createTT");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/createTroubleTicket")
	public ModelAndView createTroubleTicket(@ModelAttribute(value = "troubleTicketDTO") TroubleTicketDTO troubleTicketDTO, BindingResult result,
			Model model,HttpServletRequest request) {
		TroubleTicketDTO troubleTicketDTOObj =new TroubleTicketDTO(); 
		model.addAttribute("domain", request.getSession().getAttribute("domain").toString());
		String domain = request.getSession().getAttribute("domain").toString().trim();
		
		logger.info("entered trouble ticket domain...."+domain);
		logger.info("tenantcode...."+request.getSession().getAttribute("tenantcode").toString());
		logger.info("flag...."+troubleTicketDTO.getFlag());
		logger.info("ticket....."+troubleTicketDTO.getTicketFor());
		
		//model.addAttribute("loginTenantCode", request.getSession().getAttribute("tenantcode").toString());
		model.addAttribute("imgPath",null);
		 if(troubleTicketDTO.getTenantcode()==null ||"".equalsIgnoreCase(troubleTicketDTO.getTenantcode()))
			 troubleTicketDTO.setTenantcode(request.getSession().getAttribute("tenantcode").toString());
		if("updateTTFLAG".equalsIgnoreCase(troubleTicketDTO.getFlag())){
			HttpEntity<String> httpEntity8 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url8 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=TT_CLOSURE_REASON";
			ResponseEntity<HashMap> response8 = restTemplate.exchange(url8, HttpMethod.GET, httpEntity8, HashMap.class);
			HashMap<String,String> actualTicketMap = response8.getBody();
			model.addAttribute("actualTicketMap",actualTicketMap);
			
			HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			String url3 = ipAddressValues.getTtURL() + "getTTInfo?ticketNo="+troubleTicketDTO.getTicketNo();
			logger.info("ticketNo....."+troubleTicketDTO.getTicketNo());
			logger.info("url3....."+url3);
			ResponseEntity<TroubleTicketDTO> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, TroubleTicketDTO.class);
			troubleTicketDTOObj = response3.getBody();
			troubleTicketDTO.setCafNo(troubleTicketDTOObj.getCafNo());
			troubleTicketDTO.setTicketFor(troubleTicketDTOObj.getTicketFor());
			troubleTicketDTO.setTicketType(troubleTicketDTOObj.getTicketType());
			troubleTicketDTO.setIssueType(troubleTicketDTOObj.getIssueType());
			troubleTicketDTO.setIssueCode(troubleTicketDTOObj.getIssueCode());
			troubleTicketDTO.setTicketDesc(troubleTicketDTOObj.getTicketDesc());
			troubleTicketDTO.setAssignedTo(troubleTicketDTOObj.getAssignedTo()==null?"-1":troubleTicketDTOObj.getAssignedTo());
			troubleTicketDTO.setStatus(troubleTicketDTOObj.getStatus());
			troubleTicketDTO.setTicketRemark(troubleTicketDTOObj.getTicketRemark());
			troubleTicketDTO.setTenantcode(troubleTicketDTOObj.getTenantcode());
			troubleTicketDTO.setCreatedBy(troubleTicketDTOObj.getCreatedBy());
			troubleTicketDTO.setCreatedOn(troubleTicketDTOObj.getCreatedOn());
			troubleTicketDTO.setExpcloseDate(troubleTicketDTOObj.getExpcloseDate());
			troubleTicketDTO.setAttValues(troubleTicketDTOObj.getAttValues());
			troubleTicketDTO.setFeedback(troubleTicketDTOObj.getFeedback());
			troubleTicketDTO.setActualIssue(troubleTicketDTOObj.getActualIssue());
			model.addAttribute("grupLmoTenantCode", troubleTicketDTOObj.getTenantcode());
			
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
			String url = ipAddressValues.getTtURL() + "getIssueTypeBasedIssues?issueType="+troubleTicketDTO.getIssueType()+"&tickerFor="+troubleTicketDTO.getTicketFor()+"&ticketType="+troubleTicketDTO.getTicketType();
			ResponseEntity<TreeMap> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TreeMap.class);
			logger.info("url normal....."+url);
			TreeMap<Integer,String> issueMap = response.getBody();
			
	/*		HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
			String url1 = ipAddressValues.getTtURL() + "getIssueTypeBasedIssues?issueType="+troubleTicketDTO.getIssueType()+"&tickerFor="+troubleTicketDTO.getTicketFor()+"&ticketType="+troubleTicketDTO.getTicketType();
			ResponseEntity<TreeMap> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, TreeMap.class);
			
			TreeMap<Integer,String> feedbackMap = response1.getBody();*/
			
			
			if(troubleTicketDTOObj.getImagesList().size()>0)
				model.addAttribute("dwFlag","Y");
			else
				model.addAttribute("dwFlag","N");
			model.addAttribute("troubleTickeimages",troubleTicketDTOObj.getImagesList());
			
			if(troubleTicketDTOObj.getCafNo()!=null)
			{
				model.addAttribute("subStationName",troubleTicketDTOObj.getSubStationName());
				model.addAttribute("port",troubleTicketDTOObj.getPort());
				model.addAttribute("oltONUID",troubleTicketDTOObj.getOltONUID());
				model.addAttribute("oltSerialNo",troubleTicketDTOObj.getOltSerialNo());
				model.addAttribute("onuRegNo",troubleTicketDTOObj.getOnuRegNo());
				model.addAttribute("tenantMobile",troubleTicketDTOObj.getTenantMobile());
				model.addAttribute("districtid",troubleTicketDTOObj.getDistricts());
				model.addAttribute("custID", troubleTicketDTOObj.getCustID());
				model.addAttribute("customerID", troubleTicketDTOObj.getCustomerID());
				model.addAttribute("agoraSerCode",troubleTicketDTOObj.getAgoraSerCode());
				model.addAttribute("cpeProfileID",troubleTicketDTOObj.getCpeProfileID());
				
				model.addAttribute("popOltIpaddress",troubleTicketDTOObj.getPopOltIpaddress());
				model.addAttribute("oltCardNo",troubleTicketDTOObj.getOltCardNo());
				model.addAttribute("oltPort",troubleTicketDTOObj.getOltPort());
				
				//model.addAttribute("aadharNo",troubleTicketDTOObj.getAadharNo());
				model.addAttribute("onuModel",troubleTicketDTOObj.getOnuModel());
				model.addAttribute("onuSerialNo",troubleTicketDTOObj.getOnuSerialNo());
				model.addAttribute("onuMACAddr",troubleTicketDTOObj.getOnuMACAddr());
				model.addAttribute("apsflTrackID",(troubleTicketDTOObj.getApsflTrackID()==null || "".equalsIgnoreCase(troubleTicketDTOObj.getApsflTrackID()))?troubleTicketDTOObj.getAadharNo():troubleTicketDTOObj.getApsflTrackID());
				model.addAttribute("cafDetailsVO",troubleTicketDTOObj.getCafDetailsVO());
				model.addAttribute("iptDTO",troubleTicketDTOObj.getIptDTO());
			}
			
			model.addAttribute("districtid",troubleTicketDTOObj.getDistricts());
			model.addAttribute("popid",troubleTicketDTOObj.getPops());
			model.addAttribute("oltid",troubleTicketDTOObj.getOlts());
			model.addAttribute("portid",troubleTicketDTOObj.getPorts());
			model.addAttribute("splitl1id",troubleTicketDTOObj.getSplitL1());
			model.addAttribute("splitl2id",troubleTicketDTOObj.getSplitL2());
			model.addAttribute("actualIssue",troubleTicketDTOObj.getActualIssue());
			model.addAttribute("imagePath",troubleTicketDTOObj.getImagePath()+ "/" +troubleTicketDTOObj.getTicketNo()+"/");
			model.addAttribute("imgPath",troubleTicketDTOObj.getImagePath());
			model.addAttribute("attrValMap",troubleTicketDTOObj.getAttValues());
			model.addAttribute("feedbackMap",troubleTicketDTOObj.getFeedback());
			model.addAttribute("issueMapVal",issueMap);
			model.addAttribute("ticketTypeVal",troubleTicketDTO.getTicketType());
			model.addAttribute("issueCodeVal",troubleTicketDTO.getIssueCode());
			logger.info("troubleTicketDTO.getIssueType()"+troubleTicketDTO.getIssueType());
			model.addAttribute("issueTypeVal",troubleTicketDTO.getIssueType());
			model.addAttribute("ticketDesc",troubleTicketDTO.getTicketDesc());
			model.addAttribute("assignedToVal",troubleTicketDTO.getAssignedTo());
			model.addAttribute("statusVal",troubleTicketDTO.getStatus());
			model.addAttribute("ticketRemark",troubleTicketDTO.getTicketRemark());
			model.addAttribute("createdOn",troubleTicketDTO.getCreatedOn());
			model.addAttribute("createdBy",troubleTicketDTO.getCreatedBy());
			model.addAttribute("expcloseDate",troubleTicketDTO.getExpcloseDate());
			model.addAttribute("parentTicketNo",troubleTicketDTOObj.getParentticketno());
			model.addAttribute("ticketReason",troubleTicketDTOObj.getTicketReason());
			model.addAttribute("oltStatusFlag",troubleTicketDTOObj.getOltStatusFlag());
			//model.addAttribute("ipAddr",troubleTicketDTOObj.getIpAddr().trim());
			if(troubleTicketDTOObj.getIpAddr()!=null && !troubleTicketDTOObj.getIpAddr().isEmpty()){
				model.addAttribute("ipAddr",troubleTicketDTOObj.getIpAddr().trim());
			}else{
				model.addAttribute("ipAddr","");
			}
			//model.addAttribute("tickCategory",troubleTicketDTOObj.getTickCategory());
			
			troubleTicketDTO.setParentticketno(troubleTicketDTOObj.getParentticketno());
			logger.info(troubleTicketDTO.getExpcloseDate()+"troubleTicketDTO.getExpcloseDate()");
			
			HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
			String url1 = ipAddressValues.getTtURL() + "getWipStagesInfo?appCode=TT";
			ResponseEntity<TreeMap> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, TreeMap.class);
			TreeMap<String,String> statusMap = response1.getBody();
			logger.info("url1....."+url1);
			HttpEntity<String> httpEntity41 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
			String url41 = ipAddressValues.getTtURL() + "getFeedbackInfo?lovName=FEEDBACK";
			ResponseEntity<TreeMap> response41 = restTemplate.exchange(url41, HttpMethod.GET, httpEntity41, TreeMap.class);
			TreeMap<String,String> feedbackMap = response41.getBody();
			
			model.addAttribute("feedbackMap",feedbackMap);
			
			model.addAttribute("statusName",statusMap.get(troubleTicketDTO.getStatus().toString()));
			
			String userDomain=request.getSession().getAttribute("domain").toString();
			HttpEntity<String> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
			String url2 = ipAddressValues.getTtURL() + "getUsersInfo?userDomain="+userDomain+"&ticketFor="+troubleTicketDTOObj.getTicketFor()+"&ticketType="+troubleTicketDTOObj.getTicketType()+"&tenantCode="+troubleTicketDTOObj.getTenantcode()+"&custID="+troubleTicketDTOObj.getCustID();
			ResponseEntity<TreeMap> response2 = restTemplate.exchange(url2, HttpMethod.GET, httpEntity2, TreeMap.class);
			TreeMap<String,String> assignedToMap = response2.getBody();
			
			HttpEntity<String> httpEntity5 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
			String url5= ipAddressValues.getTtURL() + "getGroupBasedIssueTypes?typeOfUser="+troubleTicketDTOObj.getTicketFor()+"&ticketType="+troubleTicketDTOObj.getTicketType();
			ResponseEntity<TreeMap> response5 = restTemplate.exchange(url5, HttpMethod.GET, httpEntity5, TreeMap.class);
			TreeMap<String,String> issueTypesMap = response5.getBody();
			logger.info("url5....."+url5);
			model.addAttribute("issueType",issueTypesMap);
			
			model.addAttribute("ticketNo", troubleTicketDTO.getTicketNo());
			
			//troubleTicketDTO.setFlag(troubleTicketDTO.getFlag());
			
			model.addAttribute("flag", troubleTicketDTO.getFlag());
			
			model.addAttribute("statusMap",statusMap);
			model.addAttribute("assignedToMap",assignedToMap);
			
		}//in case of update tt if end
		
		else if(("Customer".equalsIgnoreCase(troubleTicketDTO.getTicketFor())&& troubleTicketDTO.getCafNo()!=null || "CUSTOMER".equalsIgnoreCase(troubleTicketDTO.getTicketFor()) && troubleTicketDTO.getCafNo()!=null)){
			HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
			String url3 = ipAddressValues.getTtURL() + "getCustomerCafInfo?cafNo="+troubleTicketDTO.getCafNo();
			logger.info("url3.else if...."+url3);
			ResponseEntity<TroubleTicketDTO> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, TroubleTicketDTO.class);
			troubleTicketDTOObj = response3.getBody();
			model.addAttribute("subStationName",troubleTicketDTOObj.getSubStationName());
			model.addAttribute("port",troubleTicketDTOObj.getPort());
			model.addAttribute("oltONUID",troubleTicketDTOObj.getOltONUID());
			model.addAttribute("oltSerialNo",troubleTicketDTOObj.getOltSerialNo());
			model.addAttribute("agoraSerCode",troubleTicketDTOObj.getAgoraSerCode());
			model.addAttribute("onuRegNo",troubleTicketDTOObj.getOnuRegNo());
			model.addAttribute("tenantMobile",troubleTicketDTOObj.getTenantMobile());
			model.addAttribute("districtid",troubleTicketDTOObj.getDist());
			model.addAttribute("agoraSerCode",troubleTicketDTOObj.getAgoraSerCode());
			model.addAttribute("cpeProfileID",troubleTicketDTOObj.getCpeProfileID());
			
			model.addAttribute("popOltIpaddress",troubleTicketDTOObj.getPopOltIpaddress());
			model.addAttribute("oltCardNo",troubleTicketDTOObj.getOltCardNo());
			model.addAttribute("oltPort",troubleTicketDTOObj.getOltPort());
			//model.addAttribute("aadharNo",troubleTicketDTOObj.getAadharNo());
			model.addAttribute("onuModel",troubleTicketDTOObj.getOnuModel());
			model.addAttribute("onuSerialNo",troubleTicketDTOObj.getOnuSerialNo());
			model.addAttribute("onuMACAddr",troubleTicketDTOObj.getOnuMACAddr());
			model.addAttribute("apsflTrackID",(troubleTicketDTOObj.getApsflTrackID()==null || "".equalsIgnoreCase(troubleTicketDTOObj.getApsflTrackID()))?troubleTicketDTOObj.getAadharNo():troubleTicketDTOObj.getApsflTrackID());
			model.addAttribute("cafDetailsVO",troubleTicketDTOObj.getCafDetailsVO());
			model.addAttribute("iptDTO",troubleTicketDTOObj.getIptDTO());
			model.addAttribute("ipAddr",troubleTicketDTOObj.getIpAddr().trim());
			model.addAttribute("oltStatusFlag",troubleTicketDTOObj.getOltStatusFlag());
		}
		
		else{
			if(!domain.equalsIgnoreCase(troubleTicketDTO.getTicketFor()) && "createTTFLAG".equalsIgnoreCase(troubleTicketDTO.getFlag()))
			{
			logger.info("111111111111");	
			if(domain.equalsIgnoreCase("OCC") && troubleTicketDTO.getTicketFor().equalsIgnoreCase("APSFL NOC")){
				logger.info("22222222222222");
				HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
				String url3 = ipAddressValues.getTtURL() + "getCurrentUserInfo?tenantCode="+troubleTicketDTO.getTenantcode()+"&ticketFor="+troubleTicketDTO.getTicketFor();
				ResponseEntity<TroubleTicketDTO> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, TroubleTicketDTO.class);
				troubleTicketDTOObj = response3.getBody();
				//model.addAttribute("custID", troubleTicketDTOObj.getCustID());
				model.addAttribute("custID", request.getSession().getAttribute("loginID").toString());
				model.addAttribute("customerID", troubleTicketDTOObj.getCustomerID());
				model.addAttribute("tenantCode", troubleTicketDTOObj.getTenantcode());
			}
			}
			else{
				HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
				String url3 = ipAddressValues.getTtURL() + "getCurrentUserInfo?tenantCode="+troubleTicketDTO.getTenantcode()+"&ticketFor="+troubleTicketDTO.getTicketFor();
				logger.info("url3.else...."+url3);
				ResponseEntity<TroubleTicketDTO> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, TroubleTicketDTO.class);
				troubleTicketDTOObj = response3.getBody();
				//model.addAttribute("custID", troubleTicketDTOObj.getCustID());
				model.addAttribute("custID", request.getSession().getAttribute("loginID").toString());
				model.addAttribute("customerID", troubleTicketDTOObj.getCustomerID());
				model.addAttribute("tenantCode", troubleTicketDTOObj.getTenantcode());
			}
		}
	
	
		HttpEntity<String> httpEntity41 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url41 = ipAddressValues.getTtURL() + "getFeedbackInfo?lovName=FEEDBACK";
		logger.info("url41....."+url41);
		ResponseEntity<TreeMap> response41 = restTemplate.exchange(url41, HttpMethod.GET, httpEntity41, TreeMap.class);
		TreeMap<String,String> feedbackMap = response41.getBody();
		
		model.addAttribute("feedbackMap",feedbackMap);
		
		HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
		String url1 = ipAddressValues.getTtURL() + "getGroupBasedTTTypes?typeOfUser="+troubleTicketDTO.getTicketFor().trim();
		logger.info("url1 last....."+url1);
		ResponseEntity<LinkedHashMap> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, LinkedHashMap.class);
		LinkedHashMap<String,String> ttTypesMap = response1.getBody();
		
		/*HttpEntity<String> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url2 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=TICKET_CATEGORY";
		ResponseEntity<HashMap> response2 = restTemplate.exchange(url2, HttpMethod.GET, httpEntity2, HashMap.class);
		HashMap<String,String> ticketCategoryMap = response2.getBody();*/
		
		HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
		String typeOfuser=troubleTicketDTO.getTicketFor().trim();
		/*if("updateTTFLAG".equalsIgnoreCase(troubleTicketDTO.getFlag()) && "SI".equalsIgnoreCase(domain)){
			typeOfuser=domain;
		}*/
		logger.info("typeOfUser........"+typeOfuser);
		
		String url3 = ipAddressValues.getTtURL() + "getDistrictList?stateID=1&typeOfUser="+typeOfuser+"&tenantcode="+troubleTicketDTO.getTenantcode().trim()+"&domain="+domain;
		logger.info("url3..lastedt last......"+url3);
		ResponseEntity<LinkedHashMap> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, LinkedHashMap.class);
		LinkedHashMap<String,String> districtsMap = response3.getBody();
	
		model.addAttribute("custName",troubleTicketDTOObj.getCustName());
		model.addAttribute("contactMobile",troubleTicketDTOObj.getContactMobile());
		model.addAttribute("contactLandline", troubleTicketDTOObj.getContactLandline());
		model.addAttribute("contactemail", troubleTicketDTOObj.getContactemail());
		if (! (null==troubleTicketDTOObj.getContactAddr()))
		{model.addAttribute("contactAddr", troubleTicketDTOObj.getContactAddr().replaceAll("[\r\n]+", " "));}
		else 
		model.addAttribute("contactAddr", troubleTicketDTOObj.getContactAddr());
		model.addAttribute("ticketType",ttTypesMap);
		//model.addAttribute("ticketCat",ticketCategoryMap);
		model.addAttribute("districtsMap",districtsMap);
		
		model.addAttribute("ticketFor", troubleTicketDTO.getTicketFor().trim());
		model.addAttribute("tenantCode", troubleTicketDTO.getTenantcode().trim());
		if(("APSFL NOC".equalsIgnoreCase(domain) && "APSFL NOC".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim())) 
				|| ("MSP".equalsIgnoreCase(domain) && "MSP".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				|| ("LMO".equalsIgnoreCase(domain) && "LMO".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				|| ("SI".equalsIgnoreCase(domain) && "SI".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				|| ("SI".equalsIgnoreCase(domain) && "LMO".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				|| ("OCC".equalsIgnoreCase(domain) && "APSFL NOC".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
			//	|| ("SI".equalsIgnoreCase(domain) && "LMO".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
			//	|| ("MSP".equalsIgnoreCase(domain) && "APSFL".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
			//	|| ("LMO".equalsIgnoreCase(domain) && "APSFL".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				)
				{
			logger.info("entering here 1111.....");
			return new ModelAndView("tt.createTroubleTicket");
				}
		
		else if("APSFL".equalsIgnoreCase(domain) && "MSP".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim())
				|| ("APSFL".equalsIgnoreCase(domain) && "LMO".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				|| ("MSP".equalsIgnoreCase(domain) && "LMO".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				|| ("LMO".equalsIgnoreCase(domain) && "MSP".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				|| "Call Center".equalsIgnoreCase(domain) && "APSFL NOC".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim())
				|| "OCC".equalsIgnoreCase(domain) && "LMO".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim())
				)
		{logger.info("entering here 22222222.....");
			model.addAttribute("tenantType",troubleTicketDTO.getTicketFor().trim());
			if("searchFLAG".equalsIgnoreCase(troubleTicketDTO.getFlag()) || "updateTTFLAG".equalsIgnoreCase(troubleTicketDTO.getFlag())){
				return new ModelAndView("tt.createTroubleTicket");
				
			}else{
				return new ModelAndView("tt.searchLMORMSP");
			}
		}
		
		else if(("APSFL".equalsIgnoreCase(domain) || "Call Center".equalsIgnoreCase(domain) ||  "APSFL NOC".equalsIgnoreCase(domain)  || "SI".equalsIgnoreCase(domain)|| "MSP".equalsIgnoreCase(domain)|| "LMO".equalsIgnoreCase(domain)) && "Customer".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim())
//				|| (("MSP".equalsIgnoreCase(domain) || "Call Center".equalsIgnoreCase(domain)) && "Customer".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
	//			|| (("LMO".equalsIgnoreCase(domain) || "Call Center".equalsIgnoreCase(domain)) && "Customer".equalsIgnoreCase(troubleTicketDTO.getTicketFor().trim()))
				
				)
		{logger.info("entering here 333333333.....");
			model.addAttribute("cafNo", troubleTicketDTOObj.getCafNo());
			model.addAttribute("tenantCode", troubleTicketDTOObj.getTenantcode());
			//model.addAttribute("custID", troubleTicketDTOObj.getCustID());
			model.addAttribute("custID", request.getSession().getAttribute("loginID").toString());
			model.addAttribute("customerID", troubleTicketDTOObj.getCustomerID());
			
			if("searchFLAG".equalsIgnoreCase(troubleTicketDTO.getFlag()) || "updateTTFLAG".equalsIgnoreCase(troubleTicketDTO.getFlag())){
			return new ModelAndView("tt.createTroubleTicket");
			
		}
			else{
			return new ModelAndView("tt.searchCustomer");
			}
		}
		else{
			return new ModelAndView("tt.createTT");
		}
	}
	
	@RequestMapping(value = "/ttHistory", method = RequestMethod.GET)
	public String viewTT(Model model, HttpServletRequest request) {
		
		return "tt.viewTT";
	}
	
	
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewTTHistory/{ticketNo}", method = RequestMethod.GET)
	public String viewTTHistory(@PathVariable(value = "ticketNo") String ticketNo, Model model, HttpServletRequest request) {
		
		logger.info(ticketNo+"ticketNo");
		if(ticketNo==null){
			model.addAttribute("ttHistoryFlag","ttHistory");
			
		}
		else{
			model.addAttribute("ticNo",ticketNo);
			model.addAttribute("ttHistoryFlag","viewTTHistory");
		}
		return "tt.viewTT";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateTT", method = RequestMethod.GET)
	public String updateTT(@ModelAttribute(value = "troubleTicketDTO") TroubleTicketDTO troubleTicketDTO, BindingResult result, Model model, HttpServletRequest request) {
		 if(troubleTicketDTO.getTenantcode()==null ||"".equalsIgnoreCase(troubleTicketDTO.getTenantcode()))
			 troubleTicketDTO.setTenantcode(request.getSession().getAttribute("tenantcode").toString());
		String domain = request.getSession().getAttribute("domain").toString().trim();
		HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url1 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=ISSUE_PRIORITY";
		ResponseEntity<HashMap> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, HashMap.class);
		HashMap<String,String> issuePriorityMap = response1.getBody();
		
		HttpEntity<String> httpEntity4 = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
		//String typeOfuser=troubleTicketDTO.getTicketFor().trim();
		String url4 = ipAddressValues.getTtURL() + "getDistrictList?stateID=1&typeOfUser="+domain+"&tenantcode="+troubleTicketDTO.getTenantcode().trim()+"&domain="+domain;
		ResponseEntity<LinkedHashMap> response4 = restTemplate.exchange(url4, HttpMethod.GET, httpEntity4, LinkedHashMap.class);
		LinkedHashMap<String,String> districtsMap = response4.getBody();
		
		HttpEntity<String> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url2 = ipAddressValues.getTtURL() + "getWipStagesInfo?appCode=TT";
		ResponseEntity<TreeMap> response2 = restTemplate.exchange(url2, HttpMethod.GET, httpEntity2, TreeMap.class);
		TreeMap<String,String> statusMap = response2.getBody();
		
		HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url3 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=TICKET_FOR";
		ResponseEntity<HashMap> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, HashMap.class);
		HashMap<String,String> ticketForMap = response3.getBody();
		
		HttpEntity<String> httpEntity8 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url8 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=TT_CLOSURE_REASON";
		ResponseEntity<HashMap> response8 = restTemplate.exchange(url8, HttpMethod.GET, httpEntity8, HashMap.class);
		HashMap<String,String> actualTicketMap = response8.getBody();
		model.addAttribute("actualTicketMap",actualTicketMap);
		
		model.addAttribute("districtsMap",districtsMap);
		model.addAttribute("tenantCode", request.getSession().getAttribute("tenantcode").toString());
		
		model.addAttribute("issuePriorityMap",issuePriorityMap);
		model.addAttribute("statusMap",statusMap);
		if("Call Center".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()))
		{
			ticketForMap.clear();
			ticketForMap.put("APSFL NOC", "APSFL NOC");
			ticketForMap.put("CUSTOMER", "CUSTOMER");
		}
		else if("APSFL NOC".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()))
		{
			ticketForMap.clear();
			ticketForMap.put("APSFL NOC", "APSFL NOC");
			
		}
		else if("APSFL".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()) || "MSP".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()))
		{
			ticketForMap.clear();
			ticketForMap.put("LMO", "LMO");
			ticketForMap.put("MSP", "MSP");
			ticketForMap.put("CUSTOMER", "CUSTOMER");
		}
		else if("LMO".equalsIgnoreCase(request.getSession().getAttribute("domain").toString())||"SI".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()))
		{
			ticketForMap.clear();
			ticketForMap.put("LMO", "LMO");
			ticketForMap.put("CUSTOMER", "CUSTOMER");
		}
		model.addAttribute("ticketFor",ticketForMap);
		
		
		return "tt.updateTT";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateTTT", method = RequestMethod.POST)
	public String updateTTT(@ModelAttribute(value = "troubleTicketDTO") TroubleTicketDTO troubleTicketDTO, BindingResult result, Model model, HttpServletRequest request) {
		HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url1 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=ISSUE_PRIORITY";
		ResponseEntity<HashMap> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, HashMap.class);
		HashMap<String,String> issuePriorityMap = response1.getBody();
		
		HttpEntity<String> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url2 = ipAddressValues.getTtURL() + "getWipStagesInfo?appCode=TT";
		ResponseEntity<TreeMap> response2 = restTemplate.exchange(url2, HttpMethod.GET, httpEntity2, TreeMap.class);
		TreeMap<String,String> statusMap = response2.getBody();
		
		model.addAttribute("tenantCode", request.getSession().getAttribute("tenantcode").toString());
		
		model.addAttribute("issuePriorityMap",issuePriorityMap);
		model.addAttribute("statusMap",statusMap);
		
		return "tt.updateTT";
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveTT", method = RequestMethod.POST)
	public ModelAndView saveTT(@ModelAttribute(value = "troubleTicketDTO") TroubleTicketDTO troubleTicketDTO, BindingResult result, Model model, HttpServletRequest request) {
		troubleTicketDTO.setModifiedBy(request.getSession().getAttribute("loginID").toString());
		if(troubleTicketDTO.getTicketNo()==null){
			troubleTicketDTO.setCreatedBy(request.getSession().getAttribute("loginID").toString());
	    	 
		}
		/*	if("Customer".equalsIgnoreCase(troubleTicketDTO.getTicketFor()) && "Field".equalsIgnoreCase(troubleTicketDTO.getTicketType())){
				troubleTicketDTO.setStatus(Byte.valueOf("2"));
				troubleTicketDTO.setCurrentStatus("Assigned");
				HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
				String url3 = ipAddressValues.getTtURL() + "getLMOLoginID?tenantCode="+troubleTicketDTO.getTenantcode();
				ResponseEntity<String> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, String.class);
				String loginID = response3.getBody();
				troubleTicketDTO.setAssignedTo(loginID);
			}
			else{*/
				
			//}
		//troubleTicketDTO.setCreatedOn(new Timestamp(dd.getTime()));
		///troubleTicketDTO.setCreatedBy(request.getSession().getAttribute("loginID").toString());
		
		List<MultipartFile> files = troubleTicketDTO.getImages();
		   List<String> fileNames = new ArrayList<String>();
		   HttpEntity<String> httpEntity8;
		ResponseEntity<String> response8;
		String direcoryPath=null;
		if (files != null && files.size() > 0) 
		      {
		   	   httpEntity8 = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
		  String url8 = ipAddressValues.getTtURL() + "getConfigItems?configName="+"TT_FILE_UPLOAD_PATH";
		  response8 = restTemplate.exchange(url8, HttpMethod.GET, httpEntity8, String.class);
		  direcoryPath= response8.getBody();
		      }
		 if("".equalsIgnoreCase(troubleTicketDTO.getImagePath()))
			 troubleTicketDTO.setImagePath(null);
		 if ( files != null && files.size() > 0) 
		  {
		       for (MultipartFile multipartFile : files) {
		          String fileName = multipartFile.getOriginalFilename();
		          if(fileName!=null && !fileName.isEmpty()){
		        	  troubleTicketDTO.setImagePath(direcoryPath);
		          }
		          }
		  }
		 troubleTicketDTO.setImages(null);
		HttpEntity<TroubleTicketDTO> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd(),troubleTicketDTO);
		String url2 = ipAddressValues.getTtURL() + "saveTT";
		ResponseEntity<TroubleTicketDTO> response2 = restTemplate.exchange(url2, HttpMethod.POST, httpEntity2, TroubleTicketDTO.class);
		TroubleTicketDTO troubleTicketDTOObj = response2.getBody();

		
		 if ( files != null && files.size() > 0) 
		      {
		 direcoryPath=direcoryPath+troubleTicketDTOObj.getTicketNo()+"/";
		           for (MultipartFile multipartFile : files) {
		              String fileName = multipartFile.getOriginalFilename();
		              if(fileName!=null && !fileName.isEmpty()){
		              fileNames.add(fileName);
		              
		              File fileDir=new File(direcoryPath);
		              
		              if(!fileDir.exists())
		              {
		           	  fileDir.mkdirs();
		              }
		              //delete files in this folder
		              logger.info(direcoryPath+"file name:"+fileName);
		              File imageFile = new File(direcoryPath, fileName);
		              try
		              {
		                  multipartFile.transferTo(imageFile);
		              } catch (IOException e) 
		              {
		                  e.printStackTrace();
		              }
		              }
		          }
		      }
		if("success".equalsIgnoreCase(troubleTicketDTOObj.getMessage())){
			model.addAttribute("ttNumber", troubleTicketDTOObj.getTicketNo());
			return new ModelAndView("tt.ttSuccess");	
		}
		else if("existed".equalsIgnoreCase(troubleTicketDTOObj.getMessage())){
			model.addAttribute("ttNumber", troubleTicketDTOObj.getTicketNo());
			model.addAttribute("currentStatus", troubleTicketDTOObj.getCurrentStatus());
			return new ModelAndView("tt.ttSuccess");
		}
		else if("updatesuccess".equalsIgnoreCase(troubleTicketDTOObj.getMessage()))
		{
			model.addAttribute("ttNumber", troubleTicketDTOObj.getTicketNo());
			model.addAttribute("currentStatus", "updatesuccess");
			return new ModelAndView("tt.ttSuccess");
		}
		else if("failed".equalsIgnoreCase(troubleTicketDTOObj.getMessage()))
		{
			model.addAttribute("currentStatus", "failed");
			return new ModelAndView("tt.ttSuccess");
		}
		else if("updateupdatefailed".equalsIgnoreCase(troubleTicketDTOObj.getMessage()))
		{
			model.addAttribute("ttNumber", troubleTicketDTOObj.getTicketNo());
			model.addAttribute("currentStatus", "updateupdatefailed");
			return new ModelAndView("tt.ttSuccess");
		}
		else if("assignedFailed".equalsIgnoreCase(troubleTicketDTOObj.getMessage()))
		{
			model.addAttribute("ttNumber", troubleTicketDTOObj.getTicketNo());
			model.addAttribute("currentStatus", "assignedFailed");
			return new ModelAndView("tt.ttSuccess");
		}
		else
		{
			return new ModelAndView("tt.ttSuccess");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTTHistory", method = RequestMethod.GET)
	public @ResponseBody List<TTHistoryDTO> getTTHistory(@RequestParam("ttNumber") String ttNumber,
			HttpServletRequest request) {

		logger.info("In getTTHistory" + ttNumber);

		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getTTHistory?ttNumber="+ttNumber;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		
		List<TTHistoryDTO> ttHistoryList = response.getBody();
		
		/*HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
		String url3 = ipAddressValues.getTtURL() + "getTTHistoryView?ttNumber="+ttNumber;
		ResponseEntity<TTViewHistoryDTO> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, TTViewHistoryDTO.class);
		TTViewHistoryDTO ttViewHistoryDTOObj = response3.getBody();
		
		ttViewHistoryDTOObj.setTtHistoryList(ttHistoryList);*/
		
		return ttHistoryList;
	}
	
	

//	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/slaReport", method = RequestMethod.GET)
	public String slaReport(Model model, HttpServletRequest request) {
		
		String userDomain=request.getSession().getAttribute("domain").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		String loginID= request.getSession().getAttribute("loginID").toString();
		
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String uri = "getSLAViolatedTTs?tenantCode="+tenantCode+"&userType="+userDomain+"&loginID="+loginID;
		String url = ipAddressValues.getTtURL() + uri;
		ResponseEntity<SLADTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SLADTO.class);
		
		SLADTO slaObj = response.getBody();
		model.addAttribute("userDomain",userDomain);
		model.addAttribute("slaViolatedCnt", slaObj.getSlaViolatedCnt());
		model.addAttribute("statusWiseCntMap",slaObj.getStatusWiseCntMap());
		return "tt.slaReport";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getLMOSLAViolatedTTsInfo", method = RequestMethod.POST)
	public String getLMOSLAViolatedTTsInfo(@ModelAttribute(value = "lmoslaDTO") LMOSLADTO lmoslaDTO, BindingResult result,
			Model model, HttpServletRequest request) {
		String userDomain=request.getSession().getAttribute("domain").toString();
		
		String loginID=null;
		/*if("APSFL".equalsIgnoreCase(userDomain))
		{
			loginID=lmoslaDTO.getAssignedTo();
		}
		else
		{
			//lmo logged in
			loginID=request.getSession().getAttribute("loginID").toString();
		}*/
		loginID=lmoslaDTO.getLoginID();
		String tenantCode= lmoslaDTO.getTenantCode();
		String domain=request.getSession().getAttribute("domain").toString();
		String stentCode= request.getSession().getAttribute("tenantcode").toString();
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String uri = "getLMOSLAViolatedTTsInfo?userID="+loginID+"&tenantType="+lmoslaDTO.getTenantType()+"&tenantCode="+tenantCode
				+"&status="+lmoslaDTO.getStatus()+"&domain="+domain+"&stentCode="+stentCode;
		String url = ipAddressValues.getTtURL() + uri;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ArrayList.class);
		
		List<TroubleTicketDTO> ttLMOSLAlist = response.getBody();
		logger.info("after getLMOSLAViolatedTTsInfo" + ttLMOSLAlist.size());
		model.addAttribute("status",lmoslaDTO.getStatus());
		model.addAttribute("lmoTroubleTickets", ttLMOSLAlist);
		return "tt.lmoTroubleTickets";
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getSLAViolatedLMOTTs", method = RequestMethod.POST)
	public String getSLAViolatedLMOTTs(@ModelAttribute(value = "lmoslaDTO") LMOSLADTO lmoslaDTO,
			BindingResult result,Model model, HttpServletRequest request) {
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String uri = "getSLAViolatedLMOTTs";
		String userDomain=request.getSession().getAttribute("domain").toString();
		String loginID=request.getSession().getAttribute("loginID").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		String url = ipAddressValues.getTtURL() + uri+"?tenantCode="+tenantCode+"&tenantType="+userDomain+"&loginID="+loginID+"&status="+lmoslaDTO.getStatus();
		ResponseEntity<LinkedList> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, LinkedList.class);
		
		LinkedList<LMOSLADTO> lmoSLAViolatedList = response.getBody();
		model.addAttribute("lmoSLAViolatedList", lmoSLAViolatedList);
		model.addAttribute("status",lmoslaDTO.getStatus());
		logger.info("after getSLAViolatedLMOTTs" + lmoSLAViolatedList.size());
		return "tt.lmoSLAReport";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTTsInfo", method = RequestMethod.GET)
	public @ResponseBody List<TroubleTicketDTO> getTTsInfo(@RequestParam String ttNumber,@RequestParam String contactLandline,@RequestParam String contactMobile,
			@RequestParam String ticketPriority,@RequestParam String status,@RequestParam String fromDate,@RequestParam String toDate,
			@RequestParam String ticketFor,@RequestParam String ttType,@RequestParam String issueType,@RequestParam String issue,
			@RequestParam String cafNo,@RequestParam String district,@RequestParam String actualIssue,
			HttpServletRequest request) {
		String userDomain=request.getSession().getAttribute("domain").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		String loginID=request.getSession().getAttribute("loginID").toString();
		logger.info("In getTTsInfo" + ttNumber);
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String uri = "getTTsInfo?loginID="+loginID+"&tenantCode="+tenantCode+"&userType="+userDomain+"&ttNumber="+ttNumber+
				"&contactLandline="+contactLandline+"&contactMobile="+contactMobile+"&ticketPriority="
				+ticketPriority+"&status="+status+"&fromDate="+fromDate+"&toDate="+toDate+
				"&ticketFor="+ticketFor+"&ttType="+ttType+"&issueType="+issueType+"&issue="+issue +"&cafNo="+cafNo+"&district="+district+"&actualIssue="+actualIssue;
		String url = ipAddressValues.getTtURL() + uri;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		
		List<TroubleTicketDTO> ttHistoryList = response.getBody();
		HttpSession session = request.getSession(false);
		session.setAttribute("ttHistoryLst", ttHistoryList);
		logger.info("after getTTsInfo" + ttHistoryList.size());
		return ttHistoryList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public String ViewTTList(Model model,HttpServletRequest request) {
		String userDomain=request.getSession().getAttribute("domain").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		String loginID=request.getSession().getAttribute("loginID").toString();
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String uri = "getTTsInfo1?loginID="+loginID+"&tenantCode="+tenantCode;
		String url = ipAddressValues.getTtURL() + uri;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		List<TroubleTicketDTO> ttHistoryList = response.getBody();
		model.addAttribute("pendingttlist",ttHistoryList);
		model.addAttribute("y",ttHistoryList.size());
		httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		uri = "getcafbyLmocode?loginID="+loginID+"&tenantCode="+tenantCode;
		url = ipAddressValues.getTtURL() + uri;
		response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		List<CustomerCafBO> cafslist = response.getBody();
		model.addAttribute("cafslist",cafslist);
		model.addAttribute("x",cafslist.size());
		httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		uri = "getLmoBalance?loginID="+loginID+"&tenantCode="+tenantCode;
		url = ipAddressValues.getTtURL() + uri;
		ResponseEntity<String> response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		String  balance = response1.getBody();
		model.addAttribute("z",balance);
		
		List<PONWiseBo> ponWithCAFList = new ArrayList<PONWiseBo>();
		ponWithCAFList = demandNoteServiceImpl.getAllotedPONWithCaf("","", tenantCode);
		model.addAttribute("poncount",ponWithCAFList.size());
		model.addAttribute("tenantCode",tenantCode);
		
		logger.info("after getTTsInfo");
		return "ViewTTList";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ViewTTListSelection", method = RequestMethod.GET)
	public String getTTsInfo1(@RequestParam String selectoption,Model model,HttpServletRequest request) {
		String userDomain=request.getSession().getAttribute("domain").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		String loginID=request.getSession().getAttribute("loginID").toString();
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		model.addAttribute("selectoption", selectoption);
		String uri = "getTTsInfo1?loginID="+loginID+"&tenantCode="+tenantCode;
		String url = ipAddressValues.getTtURL() + uri;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		List<TroubleTicketDTO> ttHistoryList = response.getBody();
		model.addAttribute("pendingttlist",ttHistoryList);
		model.addAttribute("y",ttHistoryList.size());
		httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		uri = "getcafbyLmocode?loginID="+loginID+"&tenantCode="+tenantCode;
		url = ipAddressValues.getTtURL() + uri;
		response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		List<CustomerCafBO> cafslist = response.getBody();
		model.addAttribute("cafslist",cafslist);
		model.addAttribute("x",cafslist.size());
		logger.info("after getTTsInfo");
		return "ViewTTListSelection";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/UpdatettScreen", method = RequestMethod.GET)
	public String UpdatettScreen(@ModelAttribute(value = "troubleTicketDTO") TroubleTicketDTO troubleTicketDTO, BindingResult result, Model model, HttpServletRequest request) {
		String userDomain=request.getSession().getAttribute("domain").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		String loginID=request.getSession().getAttribute("loginID").toString();
		return "UpdatettScreen";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getLMOorMSPsInfo", method = RequestMethod.GET)
	public @ResponseBody List<TTTenantDTO> getLMOorMSPsInfo(@RequestParam String tenantTypeLov,@RequestParam String contactLandline,@RequestParam String contactMobile
			,@RequestParam String code,
			HttpServletRequest request) {
		logger.info("In getLMOorMSPsInfo" + contactLandline);
		String userDomain=request.getSession().getAttribute("domain").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getLMOorMSPsInfo?domain="+userDomain+"&tenantCode="+tenantCode+"&ticketFor="+tenantTypeLov+"&contactLandline="+contactLandline+"&contactMobile="+contactMobile+"&code="+code;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		
		List<TTTenantDTO> ttHistoryList = response.getBody();
		logger.info("after getLMOorMSPsInfo" + ttHistoryList.size());
		return ttHistoryList;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getCustomerCafsInfo", method = RequestMethod.GET)
	public @ResponseBody List<TTCustomerDTO> getCustomerCafsInfo(@RequestParam String ticketFor,@RequestParam String contactLandline,@RequestParam String contactMobile,
			@RequestParam String aadharNo,@RequestParam String district,@RequestParam String mandal,@RequestParam String village,
			@RequestParam String apsflID,
			@RequestParam String cafNo,@RequestParam String oltId,
			HttpServletRequest request) {
		logger.info("In getCustomerCafsInfo" + contactLandline);
		String userDomain=request.getSession().getAttribute("domain").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getCustomerCafsInfo?tenantCode="+tenantCode+"&userType="+userDomain+"&contactLandline="+contactLandline+"&contactMobile="+contactMobile+"&aadharNo="+aadharNo+"&district="+district+"&mandal="+mandal+"&village="+village+"&apsflID="+apsflID +"&cafNo="+cafNo+"&oltId="+oltId;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		
		List<TTCustomerDTO> ttHistoryList = response.getBody();
		
		logger.info("after getCustomerCafsInfo" + ttHistoryList.size());
		return ttHistoryList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getIssues", method = RequestMethod.GET)
	public @ResponseBody TreeMap<Integer,String> getIssues(@RequestParam("issueType") String issueType,@RequestParam("tickerFor") String tickerFor,@RequestParam("ticketType") String ticketType,HttpServletRequest request) {
 
		logger.info("In getTTHistory" + issueType);

		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getIssueTypeBasedIssues?issueType="+issueType+"&tickerFor="+tickerFor+"&ticketType="+ticketType;
		ResponseEntity<TreeMap> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TreeMap.class);
		
		TreeMap<Integer,String> issueMap = response.getBody();
		
		logger.info(issueMap.size()+"issueMap size");
		
		return issueMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getIssueTypes", method = RequestMethod.GET)
	public @ResponseBody TreeMap<String,String> getIssueTypes(@RequestParam("ticketFor") String ticketFor,@RequestParam("ttType") String ttType,HttpServletRequest request) {

		logger.info("In getIssueTypes" + ttType);
		String domain=request.getSession().getAttribute("domain").toString(); 

		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getGroupBasedIssueTypes?typeOfUser="+ticketFor+"&ticketType="+ttType;
		ResponseEntity<TreeMap> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TreeMap.class);
			
		TreeMap<String,String> issueTypeMap = response.getBody();
		
		return issueTypeMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTTTypes", method = RequestMethod.GET)
	public @ResponseBody TreeMap<String,String> getTTTypes(@RequestParam("ttFor") String ttFor,HttpServletRequest request) {

		logger.info("In getTTTypes" + ttFor);
		String domain=request.getSession().getAttribute("domain").toString();

		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getGroupBasedTTTypes?typeOfUser="+ttFor;
		ResponseEntity<TreeMap> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TreeMap.class);
			
		TreeMap<String,String> issueTypeMap = response.getBody();
		
		return issueTypeMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getIssueAttributes", method = RequestMethod.GET)
	public @ResponseBody List<TTIssueCodeAttributesDTO> getIssueAttributes(@RequestParam("issueCode") String issueCode,HttpServletRequest request) {

		logger.info("In getIssueAttributes" + issueCode);
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getIssueAttributes?issueCode="+issueCode;
		ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
		
		List<TTIssueCodeAttributesDTO> issueMap = response.getBody();
		return issueMap;
	}
	
	
	@RequestMapping(value = "/getCurrentUserInfo", method = RequestMethod.GET)
	public @ResponseBody TroubleTicketDTO getCurrentUserInfo(@RequestParam("tenantCode") String tenantCode,HttpServletRequest request) {

		logger.info("In getTTHistory" + tenantCode);
		

		HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url3 = ipAddressValues.getTtURL() + "getCurrentUserInfo?tenantCode="+tenantCode;
		ResponseEntity<TroubleTicketDTO> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, TroubleTicketDTO.class);
		TroubleTicketDTO troubleTicketDTOObj = response3.getBody();
		
		return troubleTicketDTOObj;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/checkTTExistence", method = RequestMethod.GET)
	public @ResponseBody TreeMap<String,String> checkTTExistence(@RequestParam("parentticketno") String parentticketno,HttpServletRequest request) {
		logger.info("In checkTTExistence" + parentticketno);
		
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "checkTTExistence?parentticketno="+parentticketno;
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			
		String status = response.getBody();
		logger.info("status:"+status);
		TreeMap<String,String> map=new TreeMap<String,String>();
		map.put("result", status);
		return map;
	}
	
	@RequestMapping(value = "/getDistrictLists", method = RequestMethod.GET)
	public @ResponseBody LinkedHashMap<String, String> getDistrictLists(@RequestParam("typeOfUser") String typeOfUser, @RequestParam("tenantcode") String tenantcode) {
		LinkedHashMap<String,String> districtsMap=null;
		try {
			logger.info(" :: LinkedHashMap() :: Start");
			HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			String url3 = ipAddressValues.getTtURL() + "getDistrictList?stateID=1&typeOfUser="+typeOfUser+"&tenantcode="+tenantcode;
			ResponseEntity<LinkedHashMap> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, LinkedHashMap.class);
			districtsMap = response3.getBody();
			
			logger.info(" :: LinkedHashMap() :: End");
		}catch (Exception e) {
			logger.error(" :: LinkedHashMap() :: "+e);
			e.printStackTrace();
		}
		return districtsMap;
	}

	@RequestMapping(value = "/getSubsList", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> getSubsList(@RequestParam("stateID") String stateID, @RequestParam("districtID") String districtID
			, @RequestParam("ticketFor") String ticketFor, @RequestParam("tenantCode") String tenantCode) {
		HttpEntity<String> httpEntity;
		ResponseEntity<LinkedHashMap> response;
		Map<String,String> subStatationsMap=null;
		try {
			logger.info(" :: getMandalAndSubsList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			String url = ipAddressValues.getTtURL() + "getSubstations?stateID="+stateID+"&districtID="+districtID+"&ticketFor="+ticketFor+"&tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LinkedHashMap.class);
			subStatationsMap = response.getBody();
			logger.info(" :: getMandalAndSubsList() :: End");
		}catch (Exception e) {
			logger.error(" :: getMandalAndSubsList() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return subStatationsMap;
	}
	
	@RequestMapping(value = "/getMandals", method = RequestMethod.GET)
	public @ResponseBody LinkedHashMap<String, String> getMandals(@RequestParam("stateID") String stateID, @RequestParam("districtID") String districtID) {
		HttpEntity<String> httpEntity;
		ResponseEntity<LinkedHashMap> response;
		LinkedHashMap<String,String> mandalsMap=null;
		try {
			logger.info(" :: getMandals() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			String url = ipAddressValues.getTtURL() + "getMandals?stateID="+stateID+"&districtID="+districtID;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LinkedHashMap.class);
			mandalsMap = response.getBody();
			logger.info(" :: getMandals() :: End");
		}catch (Exception e) {
			logger.error(" :: getMandals() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return mandalsMap;
	}
	
	@RequestMapping(value = "/getVillages", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> getVillages(@RequestParam("stateID") String stateID, @RequestParam("districtID") String districtID
			, @RequestParam("mandalID") String mandalID) {
		HttpEntity<String> httpEntity;
		ResponseEntity<LinkedHashMap> response;
		Map<String,String> villagesMap=null;
		try {
			logger.info(" :: getVillages() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			String url = ipAddressValues.getTtURL() + "getVillages?stateID="+stateID+"&districtID="+districtID+"&mandalID="+mandalID;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LinkedHashMap.class);
			villagesMap = response.getBody();
			logger.info(" :: getVillages() :: End");
		}catch (Exception e) {
			logger.error(" :: getVillages() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return villagesMap;
	}
	
	@RequestMapping(value = "/getOLTSBySubstationsrlno")
	@ResponseBody
	public String getOLTSBySubstationsrlno(Model model,
			@RequestParam(value = "subStnSlno", required = false) String subStnSlno,@RequestParam(value = "lmoCode", required = false) String lmoCode
			,@RequestParam(value = "ticketFor", required = false) String ticketFor) {

		LinkedHashMap<String,String> oltsList = new LinkedHashMap<String,String>();
		Gson gson = new Gson();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<LinkedHashMap> response; 
		try {
			logger.info("getOLTSBySubstationsrlno :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			url = ipAddressValues.getTtURL() + "getOLTSLNOBySubstation?subStnSlno=" + subStnSlno+"&lmoCode="+lmoCode+"&ticketFor="+ticketFor;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LinkedHashMap.class);
			oltsList = response.getBody();
			logger.info("getOLTSBySubstationsrlno :: End");
		} catch (Exception e) {
			logger.info("getOLTSBySubstationsrlno :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return gson.toJson(oltsList);
	}
	
	@RequestMapping(value = "/getOLTPortsByOltSrlNo", method = RequestMethod.GET)
	public @ResponseBody LinkedHashMap<String, String> getOLTPortsByOltSrlNo(@RequestParam("oltSrlNo") String oltSrlNo
			, @RequestParam("lmoCode") String lmoCode, @RequestParam("ticketFor") String ticketFor) {
		HttpEntity<String> httpEntity;
		ResponseEntity<LinkedHashMap> response;
		LinkedHashMap<String,String>  portsMap=null;
		try {
			logger.info(" :: getOLTPortsByOltSrlNo() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			String url = ipAddressValues.getTtURL() + "getOLTPortsByOltSrlNo?oltSrlNo="+oltSrlNo+"&lmoCode="+lmoCode+"&ticketFor="+ticketFor;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LinkedHashMap.class);
			portsMap= response.getBody();
			
		}catch (Exception e) {
			logger.error(" :: getOLTPortsByOltSrlNo() :: "+e);
			e.printStackTrace();
		}
		return portsMap;
	}
	
	@RequestMapping(value = "/getOLTPortsByLmoCode", method = RequestMethod.GET)
	public @ResponseBody LinkedHashMap<String, String> getOLTPortsByLmoCode(@RequestParam("lmoCode") String lmoCode) {
		HttpEntity<String> httpEntity;
		ResponseEntity<LinkedHashMap> response;
		LinkedHashMap<String,String>  portsMap=null;
		try {
			logger.info(" :: getOLTPortsByLmoCode() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			String url = ipAddressValues.getTtURL() + "getOLTPortsByLmoCode?lmoCode="+lmoCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LinkedHashMap.class);
			portsMap= response.getBody();
			
		}catch (Exception e) {
			logger.error(" :: getOLTPortsByLmoCode() :: "+e);
			e.printStackTrace();
		}
		return portsMap;
	}
	
	@RequestMapping(value = "/getConfigItems", method = RequestMethod.GET)
	public @ResponseBody String getConfigItems(@RequestParam("configName") String configName) {
		HttpEntity<String> httpEntity;
		ResponseEntity<String> response;
		String configVal=null;
		try {
			logger.info(" :: getConfigItems() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
			String url = ipAddressValues.getTtURL() + "getConfigItems?configName="+configName;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			configVal= response.getBody();
			
		}catch (Exception e) {
			logger.error(" :: getConfigItems() :: "+e);
			e.printStackTrace();
		}
		return configVal;
	}

	@RequestMapping(value = "/getOLTPortsSplitterData")
	@ResponseBody
	public SplitterVO getOLTPortsSplitterData(Model model,
			@RequestParam(value = "oltSrlNo", required = false) String oltSrlNo,
			@RequestParam(value = "lmoCode", required = false) String lmoCode,
			@RequestParam(value = "oltPort", required = false) String oltPort,
			@RequestParam(value = "l1slot", required = false) String l1slot) {
		SplitterVO splitterVO = new SplitterVO();
		HttpEntity<String> httpEntity;
		String url = "";
		ResponseEntity<SplitterVO> response;
		try {
			logger.info("ComsController :: getOLTPortSplitterData() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(), ipAddressValues.getComPwd());
			url = ipAddressValues.getComURL() + "getOLTPortSplitterData?oltSrlNo=" + oltSrlNo + "&lmoCode=" + lmoCode
					+ "&oltPort=" + oltPort + "&l1slot=" + l1slot;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SplitterVO.class);
			splitterVO = response.getBody();
			logger.info("ComsController :: getOLTPortSplitterData() :: End");
		} catch (Exception e) {
			logger.info("ComsController :: getOLTPortSplitterData() :: " + e);
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}
		return splitterVO;
	}

	@RequestMapping(value = "/downloadImage")
	public void getImage(Model model, @RequestParam(value = "image", required = false) String image, HttpServletResponse response ) {

	File f=new File(image);
	if(f.isDirectory())
	{
	response.setContentType("Content-type: text/zip");
	response.setHeader("Content-Disposition","attachment; filename=all_images.zip");
	List<File> files = new ArrayList<>();
	String[] s=f.list();
	for(String fileName : s)
	{

	File innerFile = new File(f,fileName);
	if(innerFile.isFile()){
	files.add(innerFile);
	}
	}

	try(ServletOutputStream out = response.getOutputStream();
	ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out)); ){

	for (File file : files) {
	zos.putNextEntry(new ZipEntry(file.getName()));
	FileInputStream fis = new FileInputStream(file);
	BufferedInputStream fif = new BufferedInputStream(fis);
	// Write the contents of the file
	int data = 0;
	while ((data = fif.read()) != -1) {
	zos.write(data);
	}
	fif.close();
	zos.closeEntry();
	}

	}catch(Exception e){

	logger.error(e.getMessage());
	}

	}

	logger.info(image);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getIssueUpdateAttributes", method = RequestMethod.GET)
	public @ResponseBody List<TTIssueCodeAttributesDTO> getIssueUpdateAttributes(@RequestParam("issueCode") String issueCode,@RequestParam("ttNumber") String ttNumber,HttpServletRequest request) {

	logger.info("In getIssueUpdateAttributes" + issueCode);
	HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
	String url = ipAddressValues.getTtURL() + "getIssueUpdateAttributes?issueCode="+issueCode+"&ttNumber="+ttNumber;
	ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);

	List<TTIssueCodeAttributesDTO> issueMap = response.getBody();
	return issueMap;

	}
	///getOltStatus
/*	@Path("/todo/{varX}/{varY}")
	@Produces({"application/xml", "application/json"})
	public Todo whatEverNameYouLike(@PathParam("varX") String varX,
	    @PathParam("varY") String varY) {
	        Todo todo = new Todo();
	        todo.setSummary(varX);
	        todo.setDescription(varY);
	        return todo;
	}*/
	
	
	@RequestMapping(value = "/getOltStatus", method = RequestMethod.GET)
	public @ResponseBody OntPowerStatusDTO getOltStatus(@RequestParam("agoraSerCode") String agoraSerCode,@RequestParam("cpeprofileid") String cpeprofileid,@RequestParam(value="cafno",required=false) String cafno,
			HttpServletRequest request) {

		logger.info("In getOltStatus" + agoraSerCode+ "Cpe prfl"+cpeprofileid);

		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getOltStatus?agoraSerCode="+agoraSerCode+"&cpeprofileid="+cpeprofileid+"&cafno="+cafno;
		ResponseEntity<OntPowerStatusDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, OntPowerStatusDTO.class);
		OntPowerStatusDTO oltsStateDTO =  response.getBody();
		 logger.info("Operatonal State :"+oltsStateDTO.getOperationalState());
		return oltsStateDTO;
	}
	
	@RequestMapping(value = "/getOntReboot", method = RequestMethod.GET)
	public @ResponseBody String getOntReboot(@RequestParam("popOltIpaddress") String popOltIpaddress,@RequestParam("oltCardNo") String oltCardNo, @RequestParam("oltPort") String oltPort,@RequestParam("oltONUID") String oltONUID,
			HttpServletRequest request) {

		logger.info("In getOntReboot Olt Ip Address" + popOltIpaddress+ "Olt Card No"+oltCardNo);

		TroubleTicketDTO troubleTicketDTO = new TroubleTicketDTO();
		
		troubleTicketDTO.setPopOltIpaddress(popOltIpaddress);
		troubleTicketDTO.setOltCardNo(oltCardNo);
		troubleTicketDTO.setOltPort(oltPort);
		troubleTicketDTO.setOltONUID(oltONUID);
		HttpEntity<TroubleTicketDTO> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd(),troubleTicketDTO);
		String url = ipAddressValues.getTtURL() + "getOntReboot";
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		String message = response.getBody();
		
		return message;
	}
	
	@RequestMapping(value = "/createAPSFLNocTT", method = RequestMethod.GET)//districts
	public @ResponseBody BigInteger createAPSFLNocTT( @RequestParam("districts") String districts, HttpServletRequest request) {

		logger.info("In createAPSFLNocTT" + districts);
	TroubleTicketDTO troubleTicketDTO = new TroubleTicketDTO();
	troubleTicketDTO.setCreatedBy(request.getSession().getAttribute("loginID").toString());
	troubleTicketDTO.setModifiedBy(request.getSession().getAttribute("loginID").toString());

	/*HttpEntity<TroubleTicketDTO> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd(),troubleTicketDTO);
	String url2 = ipAddressValues.getTtURL() + "saveTT";
	ResponseEntity<TroubleTicketDTO> response2 = restTemplate.exchange(url2, HttpMethod.POST, httpEntity2, TroubleTicketDTO.class);
	TroubleTicketDTO troubleTicketDTOObj = response2.getBody();*/
	troubleTicketDTO.setDist(districts);
	troubleTicketDTO.setDistricts(districts);

	HttpEntity<TroubleTicketDTO> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd(),troubleTicketDTO);
	String url = ipAddressValues.getTtURL() + "createAPSFLNocTT";

	ResponseEntity<BigInteger> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, BigInteger.class);
	BigInteger troubleTicketDTOObj = response.getBody();

	//BigInteger oltsStateDTO =  troubleTicketDTOObj.getTicketNo();
	logger.info(troubleTicketDTOObj);
	return troubleTicketDTOObj;
	}

	
	@RequestMapping(value = "/getCafUsageInfo", method = RequestMethod.GET)
	public @ResponseBody CafUsageDTO getCafUsageInfo(@RequestParam("cafNo") String cafNo,
			@RequestParam("districtID") String districtID,
			HttpServletRequest request) {
		logger.info("In getCafUsageInfo" + cafNo);
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getCafUsageInfo?cafNo="+cafNo+"&districtID="+districtID;
		ResponseEntity<CafUsageDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CafUsageDTO.class);
		CafUsageDTO cafUsageDTOObj =  response.getBody();
		return cafUsageDTOObj;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getViewBillInfo", method = RequestMethod.GET)
	public @ResponseBody List<ViewBillDTO> getViewBillInfo(@RequestParam("cafNo") String cafNo,
			HttpServletRequest request) {
		logger.info("In getViewBillInfo" + cafNo);
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url = ipAddressValues.getTtURL() + "getViewBillInfo?cafNo="+cafNo;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		List<ViewBillDTO> cafUsageDTOObj =  response.getBody();
		return cafUsageDTOObj;
	}//downloadViewBill
	
	@RequestMapping(value = "/downloadViewBill", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {
        // create some sample data
        List<ViewBillDTO> listBooks = new ArrayList<ViewBillDTO>();
        /*listBooks.add(new Book("Spring in Action", "Craig Walls", "1935182358",
                "June 29th 2011", 31.98F));
        listBooks.add(new Book("Spring in Practice", "Willie Wheeler, Joshua White",
                "1935182056", "May 16th 2013", 31.95F));
        listBooks.add(new Book("Pro Spring 3",
                "Clarence Ho, Rob Harrop", "1430241071", "April 18th 2012", 31.85F));
        listBooks.add(new Book("Spring Integration in Action", "Mark Fisher", "1935182439",
                "September 26th 2012", 28.73F));*/
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("pdfView", "listBooks", listBooks);
	}
	@RequestMapping(value = "/downLoadBill1", method = RequestMethod.POST)
	public void downloadInvoiceBill(HttpServletRequest request, HttpServletResponse response, 
	@RequestParam(value = "filePath", required = false) String filePath) {

	File file = null;
	//filePath="D:\\I10000115_bill_period_201702.pdf";
	file = new File(filePath);
	try (InputStream inputStream = new FileInputStream(file);
	BufferedInputStream bIs = new BufferedInputStream(inputStream);
	ServletOutputStream os = response.getOutputStream()) {

	if (file != null) {

	String mimeType = "application/pdf";
	response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
	//response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
	//response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
	response.setHeader("Content-Disposition", "inline; filename="+filePath+";");
	response.setContentLength((int) file.length());
	FileCopyUtils.copy(bIs, os);
	}

	} catch (Exception e) {
	logger.error(e.getMessage());
	} finally {
	file = null;
	}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTroubleTicketInfoDownload", method = RequestMethod.GET)
	public void  getTroubleTicketInfoDownload(@RequestParam String ttNumber,@RequestParam String contactLandline,@RequestParam String contactMobile,
			@RequestParam String ticketPriority,@RequestParam String status,@RequestParam String fromDate,@RequestParam String toDate,
			@RequestParam String ticketFor,@RequestParam String ttType,@RequestParam String issueType,@RequestParam String issue,
			@RequestParam String cafNo,@RequestParam String district,@RequestParam String actualIssue,
			HttpServletRequest request,HttpServletResponse httpResponse) {
		
		HSSFWorkbook workbook = null;
		FileUtil fileUtil=new FileUtil();
		String userDomain=request.getSession().getAttribute("domain").toString();
		String tenantCode= request.getSession().getAttribute("tenantcode").toString();
		String loginID=request.getSession().getAttribute("loginID").toString();
		try {
			logger.info("In getTTsInfo" + ttNumber);
			HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
			String uri = "getTTsInfo?loginID="+loginID+"&tenantCode="+tenantCode+"&userType="+userDomain+"&ttNumber="+ttNumber+
					"&contactLandline="+contactLandline+"&contactMobile="+contactMobile+"&ticketPriority="
					+ticketPriority+"&status="+status+"&fromDate="+fromDate+"&toDate="+toDate+
					"&ticketFor="+ticketFor+"&ttType="+ttType+"&issueType="+issueType+"&issue="+issue +"&cafNo="+cafNo+"&district="+district+"&actualIssue="+actualIssue;
			String url = ipAddressValues.getTtURL() + uri;
			ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			List<TroubleTicketDTO> ttHistoryList = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<TroubleTicketDTO> ttHistoryLists = mapper.readValue(gson.toJson(ttHistoryList),TypeFactory.defaultInstance().constructCollectionType(List.class, TroubleTicketDTO.class));
			workbook = fileUtil.TTDownloadExcel(ttHistoryLists,httpResponse);
			logger.info("after getTTsInfo" + ttHistoryLists.size());
		} catch (Exception e) {
			logger.error("IN TT DOWNLOAD");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getTTsInfoDownload", method = RequestMethod.GET)
	public  void getTTsInfoDownload(HttpServletRequest request ,HttpServletResponse httpResponse) {
		ResponseEntity<ArrayList> response;
		HttpSession session;
		HSSFWorkbook workbook = null;
		List<TroubleTicketDTO> ttHistoryList = new ArrayList<TroubleTicketDTO>();
		FileUtil fileUtil=new FileUtil();
		try {
			session = request.getSession(false);
			ttHistoryList = (List<TroubleTicketDTO>) session.getAttribute("ttHistoryLst");
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<TroubleTicketDTO> ttHistoryLists = mapper.readValue(gson.toJson(ttHistoryList),TypeFactory.defaultInstance().constructCollectionType(List.class, TroubleTicketDTO.class));
			workbook = fileUtil.TTDownloadExcel(ttHistoryLists,httpResponse);
		} catch (Exception e) {
			logger.error("IN TT DOWNLOAD");
			e.printStackTrace();
		}
		logger.info("after getTTsInfo" + ttHistoryList.size());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateTroubleTicket", method = RequestMethod.GET)
	public String updateTroubleTicket(@ModelAttribute(value = "troubleTicketDTO") TroubleTicketDTO troubleTicketDTO, BindingResult result, Model model, HttpServletRequest request) {
		 if(troubleTicketDTO.getTenantcode()==null ||"".equalsIgnoreCase(troubleTicketDTO.getTenantcode()))
			 troubleTicketDTO.setTenantcode(request.getSession().getAttribute("tenantcode").toString());
		String domain = request.getSession().getAttribute("domain").toString().trim();
		HttpEntity<String> httpEntity1 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url1 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=ISSUE_PRIORITY";
		ResponseEntity<HashMap> response1 = restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, HashMap.class);
		HashMap<String,String> issuePriorityMap = response1.getBody();
		
		HttpEntity<String> httpEntity4 = ApsflHelper.getHttpEntity(ipAddressValues.getTtUserName(), ipAddressValues.getTtPwd());
		//String typeOfuser=troubleTicketDTO.getTicketFor().trim();
		String url4 = ipAddressValues.getTtURL() + "getDistrictList?stateID=1&typeOfUser="+domain+"&tenantcode="+troubleTicketDTO.getTenantcode().trim()+"&domain="+domain;
		ResponseEntity<LinkedHashMap> response4 = restTemplate.exchange(url4, HttpMethod.GET, httpEntity4, LinkedHashMap.class);
		LinkedHashMap<String,String> districtsMap = response4.getBody();
		
		HttpEntity<String> httpEntity2 = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd());
		String url2 = ipAddressValues.getTtURL() + "getWipStagesInfo?appCode=TT";
		ResponseEntity<TreeMap> response2 = restTemplate.exchange(url2, HttpMethod.GET, httpEntity2, TreeMap.class);
		TreeMap<String,String> statusMap = response2.getBody();
		
		HttpEntity<String> httpEntity3 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url3 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=TICKET_FOR";
		ResponseEntity<HashMap> response3 = restTemplate.exchange(url3, HttpMethod.GET, httpEntity3, HashMap.class);
		HashMap<String,String> ticketForMap = response3.getBody();
		
		HttpEntity<String> httpEntity8 = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url8 = ipAddressValues.getCatURL() + "scs/getLovVals?lovName=TT_CLOSURE_REASON";
		ResponseEntity<HashMap> response8 = restTemplate.exchange(url8, HttpMethod.GET, httpEntity8, HashMap.class);
		HashMap<String,String> actualTicketMap = response8.getBody();
		model.addAttribute("actualTicketMap",actualTicketMap);
		
		model.addAttribute("districtsMap",districtsMap);
		model.addAttribute("tenantCode", request.getSession().getAttribute("tenantcode").toString());
		
		model.addAttribute("issuePriorityMap",issuePriorityMap);
		model.addAttribute("statusMap",statusMap);
		if("Call Center".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()))
		{
			ticketForMap.clear();
			ticketForMap.put("APSFL NOC", "APSFL NOC");
			ticketForMap.put("CUSTOMER", "CUSTOMER");
		}
		else if("APSFL NOC".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()))
		{
			ticketForMap.clear();
			ticketForMap.put("APSFL NOC", "APSFL NOC");
			
		}
		else if("APSFL".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()) || "MSP".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()))
		{
			ticketForMap.clear();
			ticketForMap.put("LMO", "LMO");
			ticketForMap.put("MSP", "MSP");
			ticketForMap.put("CUSTOMER", "CUSTOMER");
		}
		else if("LMO".equalsIgnoreCase(request.getSession().getAttribute("domain").toString())||"SI".equalsIgnoreCase(request.getSession().getAttribute("domain").toString()))
		{
			ticketForMap.clear();
			ticketForMap.put("LMO", "LMO");
			ticketForMap.put("CUSTOMER", "CUSTOMER");
		}
		model.addAttribute("ticketFor",ticketForMap);
		
		
		return "tt.updateTroubleTicket";
	}
	
	@RequestMapping(value = "/updateTroubleTicketPagination", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UsersDataPageObjectTT updateTroubleTicketPagination(@RequestParam(value = "ttNumber", required = false) String ttNumber,@RequestParam(value = "contactLandline", required = false) String contactLandline,@RequestParam(value = "contactMobile", required = false) String contactMobile,
			@RequestParam(value = "ticketPriority", required = false) String ticketPriority,@RequestParam(value = "status", required = false) String status,@RequestParam(value = "fromDate", required = false) String fromDate,@RequestParam(value = "toDate", required = false) String toDate,
			@RequestParam(value = "ticketFor", required = false) String ticketFor,@RequestParam(value = "ttType", required = false) String ttType,@RequestParam(value = "issueType", required = false) String issueType,@RequestParam(value = "issue", required = false) String issue,
			@RequestParam(value = "cafNo", required = false) String cafNo,@RequestParam(value = "district", required = false) String district,@RequestParam(value = "actualIssue", required = false) String actualIssue,
			HttpServletRequest request) {
		String tenantCode = "";
		//HttpEntity<TTHelperDTO> httpEntity;
		//String url = "";
		//ResponseEntity<TTHelperDTO> response;
		TroubleTicketDTO ttHelperDTO = new TroubleTicketDTO();
		UsersDataPageObjectTT usersDataPageObject = new UsersDataPageObjectTT();
		String userDomain=request.getSession().getAttribute("domain").toString();
		String loginID=request.getSession().getAttribute("loginID").toString();
		try {
			Integer pageDisplayLength = 10;
			Integer pageNumber = 1;
			String sdir = "desc";
			String sortColumn = "";
			int sortParameter = 0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			if (request.getParameter("iSortCol_0") != null) {
				sortParameter = Integer.parseInt(request.getParameter("iSortCol_0"));
			}
			sortColumn = TroubleTicketDTO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
			if (request.getParameter("sSortDir_0") != null)
				sdir = request.getParameter("sSortDir_0");

			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			PageObject pageObject = new PageObject();
			pageObject.setMinSize((pageNumber - 1) * pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);
			tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			String tenantType = (String) request.getSession(false).getAttribute("domain");
			ttHelperDTO.setPageObject(pageObject);
			ttHelperDTO.setTenantCode(tenantCode);
			ttHelperDTO.setTenantType(tenantType);
			ttHelperDTO.setTtNumber(ttNumber);
			ttHelperDTO.setContactLandline(contactLandline);
			ttHelperDTO.setContactMobile(contactMobile);
			ttHelperDTO.setTicketPriority(ticketPriority);
			ttHelperDTO.setStatus1(status);
			ttHelperDTO.setFromDate(fromDate);
			ttHelperDTO.setToDate(toDate);
			ttHelperDTO.setTicketFor(ticketFor);
			ttHelperDTO.setTtType(ttType);
			ttHelperDTO.setIssueType(issueType);
			ttHelperDTO.setIssue(issue);
			ttHelperDTO.setCafNO(cafNo);
			ttHelperDTO.setDistricts(district);
			ttHelperDTO.setActualIssue(actualIssue);
			ttHelperDTO.setLoginID(loginID);
			ttHelperDTO.setUserDomain(userDomain);
			
			
			HttpEntity<TroubleTicketDTO> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd(),ttHelperDTO);
			String url = ipAddressValues.getTtURL() + "troubleTicketPage";

			ResponseEntity<TroubleTicketDTO> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TroubleTicketDTO.class);
			ttHelperDTO = response.getBody();
			
			/*httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTtPwd(), ttHelperDTO);
			url = ipAddressValues.getTtURL() + "troubleTicketPage";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TTHelperDTO.class);
			ttHelperDTO = response.getBody();*/
			usersDataPageObject.setAaData(ttHelperDTO.getListOfTroubleTickets());
			usersDataPageObject.setiTotalDisplayRecords(Long.parseLong(ttHelperDTO.getCount()));
			
			HttpSession session = request.getSession(false);
			session.setAttribute("ttHistoryLst", ttHelperDTO.getListOfTroubleTickets());
			logger.info("after getTTsInfo" + ttHelperDTO.getListOfTroubleTickets().size());
		
		} catch (Exception e) {
			logger.info("TroubleTicketController :: updateTroubleTicketPagination() :: " + e);
			e.printStackTrace();
		} finally {
			tenantCode = null;
			
		}

		return usersDataPageObject;
	}
}
