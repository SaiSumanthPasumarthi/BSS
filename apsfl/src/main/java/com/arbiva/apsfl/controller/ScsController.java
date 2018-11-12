package com.arbiva.apsfl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.arbiva.apsfl.scs.dto.AdditionalServicesDTO;
import com.arbiva.apsfl.scs.dto.AddlSrvcDTO;
import com.arbiva.apsfl.scs.dto.CoreService;
import com.arbiva.apsfl.scs.dto.CoreServiceDTO;
import com.arbiva.apsfl.scs.dto.ScsHelperDTO;
import com.arbiva.apsfl.scs.dto.ScsHomeDTO;
import com.arbiva.apsfl.scs.dto.SrvcFeatures;
import com.arbiva.apsfl.scs.dto.TaxParamDTO;
import com.arbiva.apsfl.scs.dto.ViewAddlSrvcDTO;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;

@Controller
public class ScsController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	IpAddressValues ipAddressValues;
	
	private static final Logger logger = Logger.getLogger(ScsController.class);
	
	@RequestMapping(value = "/scsHome", method = RequestMethod.GET)
	public ModelAndView showCoreServices(@ModelAttribute(value="coreServieForm") CoreServiceDTO coreServiceDTO, Model model, HttpServletRequest request) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ScsHomeDTO> response;
		ScsHomeDTO scsHomeDTO = new ScsHomeDTO();
		try {
			logger.info("ScsController :: showCoreServices() :: Start");
		    httpEntity= ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/";
			response= restTemplate.exchange(url, HttpMethod.GET, httpEntity, ScsHomeDTO.class);
			scsHomeDTO= response.getBody();
		
		logger.info("Welcome home! The client locale is sujit {}.");
		
		model.addAttribute("provTargetTypeLov",scsHomeDTO.getProvTargetTypeLov());
		model.addAttribute("glCodeLov",scsHomeDTO.getGlCodeLov());
		model.addAttribute("taxCodeLov",scsHomeDTO.getTaxCodeLov());
		model.addAttribute("dataTypeLov",scsHomeDTO.getDataTypeLov());
		model.addAttribute("lovs",scsHomeDTO.getLovVals());
		logger.info("ScsController :: showCoreServices() :: End");
		}catch (Exception e) {
			logger.error("ScsController :: showCoreServices() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			scsHomeDTO = null;
		}
		return new ModelAndView("coreServiceCreation");
	}
	
	@RequestMapping(value = "/additionalServicesCreation", method = RequestMethod.GET)
	public ModelAndView additionalServicesCreation(@ModelAttribute(value="addlServieForm") ViewAddlSrvcDTO addlServiceDTO, Model model, HttpServletRequest request) {
		logger.info("additionalServicesCreation !!!");
		HttpEntity<String> httpEntity;
		ResponseEntity<ScsHomeDTO> response;
		ScsHomeDTO scsHomeDTO = new ScsHomeDTO();
		try{
			logger.info("ScsController :: additionalServicesCreation() :: Start");
			httpEntity= ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/additionalServicesCreation";
			response= restTemplate.exchange(url, HttpMethod.GET, httpEntity, ScsHomeDTO.class);
			scsHomeDTO= response.getBody();
			model.addAttribute("coreSrvcsList",scsHomeDTO.getCoreSrvcsList());
			logger.info("ScsController :: additionalServicesCreation() :: End");
		}
		catch(Exception e){
			logger.error("ScsController :: additionalServicesCreation() :: " + e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			scsHomeDTO = null;
		}
		return new ModelAndView("additionalServiceCreation");
	}
	
	
	@RequestMapping(value = "/paramList", method = RequestMethod.GET)
	public @ResponseBody ScsHelperDTO paramList(@RequestParam("coreServCode") String coreServCode) {
		logger.info(" In ParameterList...");
		ScsHelperDTO scsHelperDTO = new ScsHelperDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<ScsHelperDTO> response;
		try{
			logger.info("ScsController :: paramList() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/paramList?coreServCode="+coreServCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ScsHelperDTO.class);
			scsHelperDTO = response.getBody();
			logger.info("ScsController :: paramList() :: End");
		}
		catch (Exception exc) {
			logger.error("ScsController :: paramList() :: " + exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		
		return scsHelperDTO;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getCoreServices", method = RequestMethod.GET)
	public @ResponseBody  List<CoreService> getCoreServices() {
		logger.info(" In ParameterList...");
		 List<CoreService> coreServList = new ArrayList<>();
		 HttpEntity<String> httpEntity;
	     ResponseEntity<ArrayList> response;
		try{
			logger.info("ScsController :: getCoreServices() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/getCoreServices";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			coreServList = response.getBody();
			logger.info("ScsController :: getCoreServices() :: End");
		}
		catch (Exception exc) {
			logger.error("ScsController :: getCoreServices() :: " + exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return coreServList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getSrvcTaxes", method = RequestMethod.GET)
	@ResponseBody
	public  List<TaxParamDTO> getSrvcTaxes(@RequestParam("coreServCode") String coreServCode) {
		logger.info(" In getServiceTaxes...");
		List<TaxParamDTO> taxsDTO = new ArrayList<>();
		HttpEntity<String> httpEntity;
	    ResponseEntity<ArrayList> response;
		try{
			logger.info("ScsController :: getSrvcTaxes() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/getSrvcTaxes?coreServCode="+coreServCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			taxsDTO = response.getBody();
			logger.info("ScsController :: getSrvcTaxes() :: End");
		}
		catch (Exception exc) {
			logger.error("ScsController :: getSrvcTaxes() :: " + exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return taxsDTO;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createCoreService", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseBody
	public Map<String,Object> createCoreService(HttpServletRequest request, @RequestBody CoreServiceDTO coreServiceDTO) {
		Map<String,Object> map = new LinkedHashMap<>();
		HttpEntity<CoreServiceDTO> httpEntity;
	    ResponseEntity<HashMap> response;
		try {
			logger.info("ScsController :: createCoreService() :: Start");
			coreServiceDTO.setLoginId(request.getSession().getAttribute("loginID").toString());
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd(), coreServiceDTO);
			String url = ipAddressValues.getCatURL() + "scs/createCoreService";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, HashMap.class);
			map = response.getBody();
			logger.info("ScsController :: createCoreService() :: End");
		} catch (Exception exc) {
			logger.error("ScsController :: createCoreService() :: " + exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewCoreServices", method = RequestMethod.GET)
	public ModelAndView viewCoreServices(@ModelAttribute(value="coreServieForm") CoreServiceDTO coreServiceDTO, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is sujit {}.");
		List<CoreService> coreServicesList =  new ArrayList<>();
		HttpEntity<String> httpEntity;
	    ResponseEntity<ArrayList> response;
		try {
			logger.info("ScsController :: viewCoreServices() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/viewCoreServices";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			coreServicesList =  response.getBody();
			model.addAttribute("coreServicesList",coreServicesList);
			model.addAttribute("viewStatus","view");
			logger.info("ScsController :: viewCoreServices() :: End");
		}
		catch (Exception exc){
			logger.error("ScsController :: viewCoreServices() :: "+exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			coreServicesList = null;
		}
		
		return new ModelAndView("coreServiceCreation");
	}
	
	
	@RequestMapping(value = "/editCoreService", method = RequestMethod.GET)
	public ModelAndView editCoreService(@ModelAttribute(value="coreServieForm") CoreServiceDTO coreServiceDTO, ModelMap model,@RequestParam("srvcCode") String srvcCode, @RequestParam("effFrom") String effFrom) {
		
		HttpEntity<String> httpEntity;
	    ResponseEntity<ScsHomeDTO> response;
	    ScsHomeDTO scsHomeDTO = new ScsHomeDTO();
		try {
			logger.info("ScsController :: editCoreService() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/editCoreService?srvcCode="+srvcCode+"&effFrom="+effFrom;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ScsHomeDTO.class);
			scsHomeDTO= response.getBody();
			
		model.addAttribute("provTargetTypeLov",scsHomeDTO.getProvTargetTypeLov());
		model.addAttribute("glCodeLov",scsHomeDTO.getGlCodeLov());
		model.addAttribute("taxCodeLov",scsHomeDTO.getTaxCodeLov());
		model.addAttribute("dataTypeLov",scsHomeDTO.getDataTypeLov());
		model.addAttribute("coreServieForm", scsHomeDTO.getCoreServiceDTO());
		model.addAttribute("srvcParamList",scsHomeDTO.getCoreServiceDTO().getServParams());
		model.addAttribute("editStatus","viewOnly");
		model.addAttribute("taxParamsList",scsHomeDTO.getTaxParams());
		logger.info("ScsController :: editCoreService() :: End");
		} catch (Exception e) {
			logger.info("ScsController :: editCoreService() :: "+e);
		    e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			scsHomeDTO = null;
		}
		return new ModelAndView("coreServiceCreation");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/additionalCoreServicesSave", method = RequestMethod.POST, headers = {"content-type=application/json"})
	public @ResponseBody Map<String,Object> additionalCoreServicesSave(HttpServletRequest request, @RequestBody AdditionalServicesDTO addServiceDTO) {
		Map<String,Object> map = new LinkedHashMap<>();
		HttpEntity<AdditionalServicesDTO> httpEntity;
	    ResponseEntity<HashMap> response;
		try {
			logger.info("ScsController :: additionalCoreServicesSave() :: Start");
			addServiceDTO.setLoginId(request.getSession().getAttribute("loginID").toString());
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd(), addServiceDTO);
			String url = ipAddressValues.getCatURL() + "scs/additionalCoreServicesSave";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, HashMap.class);
			 map = response.getBody();
			 String t = (String) map.get("status");
			 System.out.println(t);
			 logger.info("ScsController :: additionalCoreServicesSave() :: End");
		} catch (Exception exc) {
			logger.error("ScsController :: additionalCoreServicesSave() :: " + exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		
		return map;
	}
	
	@RequestMapping(value = "/editAdditionalService", method = RequestMethod.GET)
	public ModelAndView editAdditionalService(@ModelAttribute(value="addlServieForm") ViewAddlSrvcDTO addlServiceDTO,ModelMap model, @RequestParam("coreSrvcCode") String coreSrvcCode, @RequestParam("srvcCode") String srvcCode,
			@RequestParam("effFrom") String effFrom, @RequestParam(value="editChannels",required = false) String editChannels) {
		
		HttpEntity<String> httpEntity;
	    ResponseEntity<ScsHomeDTO> response;
	    ScsHomeDTO scsHomeDTO = new ScsHomeDTO();
	    List<SrvcFeatures> list = new ArrayList<>();
		try{
			logger.info("ScsController :: editAdditionalService() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/editAdditionalService?coreSrvcCode="+coreSrvcCode+"&effFrom="+effFrom+"&srvcCode="+srvcCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ScsHomeDTO.class);
			scsHomeDTO = response.getBody();
			
			for(SrvcFeatures srvcFea : scsHomeDTO.getSrvcFeaturesList()){
				for(SrvcFeatures srvcFea1 : scsHomeDTO.getAddlServcList()){
					if(srvcFea.getFeaturecode().equalsIgnoreCase(srvcFea1.getFeaturecode()))
						srvcFea.setSelectedVal("selected");
				}
				list.add(srvcFea);
			}
			
			 model.addAttribute("addlServieForm",scsHomeDTO.getAddlServiceDTO());
			 model.addAttribute("coreSrvcsList",scsHomeDTO.getCoreSrvcsList());
			 model.addAttribute("taxList",scsHomeDTO.getSrvcTaxes());
			 model.addAttribute("addlSrvcParamDTOList",scsHomeDTO.getAddlSrvcParamDTOList());
			 model.addAttribute("editStatus","viewOnly");
			 model.addAttribute("srvcFeatList",scsHomeDTO.getAddlServcList());
			 model.addAttribute("srvcFaturesAll",list);
			 model.addAttribute("editChannels",editChannels.trim());
			 logger.info("ScsController :: editAdditionalService() :: End");
		}
		catch(Exception exc) {
			logger.error("ScsController :: editAdditionalService() :: " + exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			scsHomeDTO = null;
		}
		
		return new ModelAndView("additionalServiceCreation");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getLovVals", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> getLovVals(@RequestParam("lovName") String lovName) {
		
		Map<String,String> coreServicesList =  new HashMap<>();
		HttpEntity<String> httpEntity;
	    ResponseEntity<HashMap> response;
		try {
			logger.info("ScsController :: getLovVals() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/getLovVals?lovName="+lovName;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, HashMap.class);
			coreServicesList = response.getBody();
			logger.info("ScsController :: getLovVals() :: End");
		} catch (Exception e) {
			logger.error("ScsController :: getLovVals() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return coreServicesList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/viewAddlServices", method = RequestMethod.GET)
	public ModelAndView viewAddlServices(@ModelAttribute(value="addlServieForm") ViewAddlSrvcDTO addlServiceDTO, Model model
			, HttpServletRequest request, @RequestParam(value="message", required = false) String message) {
		
		List<AddlSrvcDTO> addlServcList =  new ArrayList<>();
		HttpEntity<String> httpEntity;
	    ResponseEntity<ArrayList> response;
		try {
			logger.info("ScsController :: viewAddlServices() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/viewAddlServices";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			addlServcList =  response.getBody();
			
			model.addAttribute("addlServcList",addlServcList);
			model.addAttribute("viewStatus","view");
			model.addAttribute("message",message);
			logger.info("ScsController :: viewAddlServices() :: End");
		} catch (Exception e) {
			logger.error("ScsController :: viewAddlServices() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			addlServcList = null;
		}
		return new ModelAndView("additionalServiceCreation");
	}
	
	@RequestMapping(value = "/taxPercentage", method = RequestMethod.GET)
	public @ResponseBody String taxPercentage(@RequestParam("taxCode") String taxCode) {
		logger.info(":::In getTaxPerc :::");
		String res = "";
		HttpEntity<String> httpEntity;
	    ResponseEntity<String> response;
		try {
			logger.info("ScsController :: taxPercentage() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/taxPercentage?taxCode="+taxCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
			res =  response.getBody();
			logger.info("ScsController :: taxPercentage() :: End");
		} catch (Exception exception) {
			logger.error("ScsController :: taxPercentage() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return res;
	}
	
	@RequestMapping(value = "/getCountOfSrvcFeatByCoreSrvcCode", method = RequestMethod.GET)
	public @ResponseBody Integer getCountOfSrvcFeatByCoreSrvcCode(@RequestParam("coreSrvcCode") String coreSrvcCode) {
		logger.info(":::In getTaxPerc :::");
		Integer res = 0;
		HttpEntity<String> httpEntity;
	    ResponseEntity<Integer> response;
		try {
			logger.info("ScsController :: getCountOfSrvcFeatByCoreSrvcCode() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/getCountOfSrvcFeatByCoreSrvcCode?coreSrvcCode="+coreSrvcCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Integer.class);
			res =  response.getBody();
			logger.info("ScsController :: getCountOfSrvcFeatByCoreSrvcCode() :: End");
		} catch (Exception exception) {
			logger.error("ScsController :: getCountOfSrvcFeatByCoreSrvcCode() :: "+exception);
			exception.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return res;
	}
	
	@RequestMapping(value = "/corpusPackageSaveCall", method = RequestMethod.GET)
	public String  corpusPackageSaveCall(@RequestParam("srvcCode") String srvcCode,
			@RequestParam("effFrom") String effFrom) {
		logger.info(" In getAddlSrvcList()...");
		HttpEntity<String> httpEntity;
	    ResponseEntity<Integer> response;
		try {
			logger.info("ScsController :: corpusPackageSaveCall() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/corpusPackageSaveCall?srvcCode="+srvcCode+"&effFrom="+effFrom;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Integer.class);
			response.getBody();
			logger.info("ScsController :: corpusPackageSaveCall() :: End");
		} catch (Exception exc) {
			logger.error("ScsController :: corpusPackageSaveCall() :: "+exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return "redirect:/viewAddlServices";
	}
	
	@RequestMapping(value = "/updateChannel", method = RequestMethod.GET)
	public String  updateChannel(@RequestParam("srvcCode") String srvcCode,
			@RequestParam("effFrom") String effFrom,
			@RequestParam("srvcFeaturesCodes") List<String> srvcFeaturesCodes){
		
		HttpEntity<ScsHelperDTO> httpEntity = null;
	    ResponseEntity<String> response = null;
	    ScsHelperDTO scsHelperDto = new ScsHelperDTO();
	    scsHelperDto.setSrvcFeaturesCodes(srvcFeaturesCodes);
	    scsHelperDto.setSrvcCode(srvcCode);
	    scsHelperDto.setEffeFrom(effFrom);
	    
		try {
			logger.info("ScsController :: updateChannel() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd(),scsHelperDto);
			String url = ipAddressValues.getCatURL() + "scs/updateChannel";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			response.getBody();
			logger.info("ScsController :: updateChannel() :: End");
		} catch (Exception exc) {
			logger.error("ScsController :: updateChannel() :: "+exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
		}
		return "redirect:/viewAddlServices?message="+response.getBody();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/viewAddlServicesByCoreSrvcCode", method = RequestMethod.GET)
	public String viewAddlServicesByCoreSrvcCode(HttpServletRequest request, Model uiModel) {
		List<AddlSrvcDTO> addlServcList = new ArrayList<>();
		HttpEntity<String> httpEntity = null;
	    ResponseEntity<ArrayList> response = null;
	    String coreSrvcCode = "IPTV";
		try{
			logger.info("ScsController :: viewAddlServicesByCoreSrvcCode() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/viewAddlServicesByCoreSrvcCode?coreSrvcCode="+coreSrvcCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			addlServcList = response.getBody();
			logger.info("ScsController :: viewAddlServicesByCoreSrvcCode() :: End");
		}
		catch (Exception exc) {
			logger.error("ScsController :: viewAddlServicesByCoreSrvcCode() :: " + exc);
			exc.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		uiModel.addAttribute("addlServcList", addlServcList);
		
		return "viewIptvAddlSrvcs";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getAllSrvdFeaturesByFeatuesCodes", method = RequestMethod.GET)
	public @ResponseBody List<SrvcFeatures> getAllSrvdFeaturesByFeatuesCodes(@RequestParam("featureCodes") String featureCodes) {
		List<SrvcFeatures> addlServcList = null;
		HttpEntity<String> httpEntity = null;
	    ResponseEntity<ArrayList> response = null;
		try {
			logger.info("ScsRestController :: getAllSrvdFeaturesByFeatuesCodes() :: START");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/getAllSrvdFeaturesByFeatuesCodes?featureCodes="+featureCodes;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			addlServcList = response.getBody();
			logger.info("ScsRestController :: getAllSrvdFeaturesByFeatuesCodes() :: END");
		} catch (Exception exc) {
			exc.printStackTrace();
			logger.error("ScsRestController :: getAllSrvdFeaturesByFeatuesCodes() " + exc);
		}
		return addlServcList;
	}
}
