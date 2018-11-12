package com.arbiva.apsfl.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.arbiva.apsfl.pcs.dto.CharTaxGlDTO;
import com.arbiva.apsfl.pcs.dto.ChargeCodesDTO;
import com.arbiva.apsfl.pcs.dto.CoreServiceDTO;
import com.arbiva.apsfl.pcs.dto.CreateProductDTO;
import com.arbiva.apsfl.pcs.dto.ProductInfoDTO;
import com.arbiva.apsfl.pcs.dto.ResponseDTO;
import com.arbiva.apsfl.pcs.dto.SrvcFeaturesDTO;
import com.arbiva.apsfl.pcs.dto.SrvcsDTO;
import com.arbiva.apsfl.pcs.dto.TelePhoneyHelperDTO;
import com.arbiva.apsfl.scs.dto.SrvcFeatures;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.IpAddressValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Controller
public class PcsController {

	private static final Logger logger = Logger.getLogger(PcsController.class);

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	IpAddressValues ipAddressValues;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = { "/pcsHome" }, method = RequestMethod.GET)
	public String home(Model model, @RequestParam(value = "returnValue", required = false) String returnValue,HttpServletRequest request)
			throws ParseException {
		
		List<ProductInfoDTO> productsList =new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		String tenantCode = request.getSession().getAttribute("tenantcode").toString();
       try {
	    	   logger.info("PcsController :: home() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "pcs/?tenantCode="+tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			productsList = response.getBody();
			 logger.info("PcsController :: home() :: End");
	       }catch (Exception e) {
	    	   logger.error("PcsController :: home() :: "+e);
			   e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			}
			model.addAttribute("productsList", productsList);
			model.addAttribute("returnValue", returnValue);
			return "pcsHome";

	}

	@RequestMapping(value = "/pcsGetAllServicesByProductIds", method = RequestMethod.GET)
	public String getAllServicesByProductIds(@RequestParam(value = "prodcode") String prodcode,
			@RequestParam(value = "tenantcode") String tenantcode,
			@RequestParam(value = "effectiveDate") String effFrom, Model model) throws ParseException {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ProductInfoDTO> response;
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
		try {
			 logger.info("PcsController :: getAllServicesByProductIds() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
	
			String url = ipAddressValues.getCatURL() + "pcs/getAllServicesByProductIds?prodcode=" + prodcode + "&tenantcode=" + tenantcode
					+ "&effectiveDate=" + effFrom;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,ProductInfoDTO.class);
			productInfoDTO = response.getBody();
			model.addAttribute("product", productInfoDTO);
			logger.info("PcsController :: getAllServicesByProductIds() :: End");
		}catch (Exception e) {
			logger.error("PcsController :: getAllServicesByProductIds() :: "+e);
			e.printStackTrace();
		}finally{
			httpEntity = null;
			response = null;
			productInfoDTO = null;
		}
		return "pcsShowProductInfo";
	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.GET)
	public String createProduct(Model model, HttpServletRequest request) {

		HttpEntity<String> httpEntity;
		ResponseEntity<CreateProductDTO> response;
		CreateProductDTO createProductDTO =new CreateProductDTO();
		try {
			logger.info("PcsController :: createProduct() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "pcs/createProduct";
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,CreateProductDTO.class);
			createProductDTO= response.getBody();
			model.addAttribute("servicesList", createProductDTO.getServiceslist());
			model.addAttribute("chargeTypeLov", createProductDTO.getChargeTypeLov());
			model.addAttribute("custTypeList", createProductDTO.getCustTypeList());
			model.addAttribute("prodTypeList", createProductDTO.getProdTypeList());
			model.addAttribute("glCodesList", createProductDTO.getGlCodes());
			model.addAttribute("taxCodesList", createProductDTO.getTaxCodesList());
			model.addAttribute("chargeTypeList", createProductDTO.getChargeTypeList());
			logger.info("PcsController :: createProduct() :: End");
        }catch (Exception e) {
        	logger.error("PcsController :: createProduct() :: "+e);
			e.printStackTrace();
        }finally {
        	httpEntity = null;
			response = null;
			createProductDTO = null;
        }
		return "pcsCreateProduct";

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllCoreServicesByProductType", method = RequestMethod.GET)
	@ResponseBody
	public List<CoreServiceDTO> getAllCoreServicesByProductType(@RequestParam(value = "prodType") String prodType) {

		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url = ipAddressValues.getCatURL() + "pcs/getAllCoreServicesByProductType?prodType=" + prodType;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
		List<CoreServiceDTO> coreServicesList = response.getBody();

		return coreServicesList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllAdditionalSrvcsByCoreSrvcCode", method = RequestMethod.GET)
	@ResponseBody
	public List<SrvcsDTO> getAllAdditionalSrvcsByCoreSrvcCode(
			@RequestParam(value = "coreSrvcList") String[] coreSrvcList) {

		StringBuilder coreServicesWithComma = new StringBuilder("");
		List<SrvcsDTO> serviceslist = new ArrayList<>();
		ResponseEntity<ArrayList> response; 
		HttpEntity<String> httpEntity;
		for (int i = 0; i < coreSrvcList.length; i++) {
			if (i == 0)
				coreServicesWithComma.append("'" + coreSrvcList[i] + "'");
			else
				coreServicesWithComma.append("," + "'" + coreSrvcList[i] + "'");
		}
		
	  try {
		    logger.info("PcsController :: getAllAdditionalSrvcsByCoreSrvcCode() :: Start");
		    httpEntity= ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			StringBuilder url = new StringBuilder(ipAddressValues.getCatURL());
			url.append("pcs/getAllAdditionalSrvcsByCoreSrvcCode?coreSrvcList=");
			url.append(coreServicesWithComma.toString());
			response= restTemplate.exchange(url.toString(), HttpMethod.GET, httpEntity,ArrayList.class);
			serviceslist= response.getBody();
			logger.info("PcsController :: getAllAdditionalSrvcsByCoreSrvcCode() :: End");
	  }catch (Exception e) {
		  logger.error("PcsController :: getAllAdditionalSrvcsByCoreSrvcCode() :: "+e);
		e.printStackTrace();
	}finally {
		httpEntity = null;
		response = null;
		coreServicesWithComma = null;
	}
		return serviceslist;
	}

	@RequestMapping(value = "/getAllChargeTypesByChargeCodes", method = RequestMethod.GET)
	@ResponseBody
	public CharTaxGlDTO getAllChargeTypesByChargeCodes(
			@RequestParam(value = "chargeTypeIdsList", required = false) String chargeTypeIds,
			@RequestParam(value = "srvcFeaturesObjList", required = false) String srvcFeaturesObjList,
			@RequestParam(value = "srvcCode") String srvcCode) {
		
		CharTaxGlDTO chargeCodesVO = new CharTaxGlDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<CharTaxGlDTO> response;
		CharTaxGlDTO chargeCodesVO1 = new CharTaxGlDTO();
		StringBuilder coreServicesWithComma = new StringBuilder("");
		try {
			logger.info("PcsController :: getAllChargeTypesByChargeCodes() :: Start");
			if (chargeTypeIds != null) {
				String[] chargeTypeIdsList = chargeTypeIds.split(",");
				
				if(chargeTypeIdsList != null){
					for (int i = 0; i < chargeTypeIdsList.length; i++) {
						if (i == 0)
							coreServicesWithComma.append("'" + chargeTypeIdsList[i] + "'");
						else
							coreServicesWithComma.append("," + "'" + chargeTypeIdsList[i] + "'");
					}
				}
			
				httpEntity= ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
				String url = ipAddressValues.getCatURL() + "pcs/getAllChargeTypesByChargeCodes?srvcCode="+srvcCode+"&chargeTypeIdsList="
						+coreServicesWithComma+"&featureName=dummy&featureCode=0";
				
				response= restTemplate.exchange(url, HttpMethod.GET, httpEntity,CharTaxGlDTO.class);
				chargeCodesVO = response.getBody();

			} else if (srvcFeaturesObjList != null) {
				ObjectMapper mapper = new ObjectMapper();
				List<SrvcFeaturesDTO> SrvcFeaturesDTOList = mapper.readValue(srvcFeaturesObjList, TypeFactory.defaultInstance().constructCollectionType(List.class, SrvcFeaturesDTO.class));

				for (SrvcFeaturesDTO srvcFeaturesDTO : SrvcFeaturesDTOList) {
					coreServicesWithComma = new StringBuilder("");
					String[] chargeTypeIdsList = srvcFeaturesDTO.getChargeCode();
					if(chargeTypeIdsList != null){
						for (int i = 0; i < chargeTypeIdsList.length; i++) {
							if (i == 0)
								coreServicesWithComma.append("'" + chargeTypeIdsList[i] + "'");
							else
								coreServicesWithComma.append("," + "'" + chargeTypeIdsList[i] + "'");
						}
						
					}
					httpEntity= ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
					String url = ipAddressValues.getCatURL() + "pcs/getAllChargeTypesByChargeCodes?srvcCode=" + srvcCode
							+ "&chargeTypeIdsList=" + coreServicesWithComma + "&featureName="
							+srvcFeaturesDTO.getFeatureName()+ "&featureCode="+srvcFeaturesDTO.getFeatureCode();
					response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
							CharTaxGlDTO.class);
					chargeCodesVO1 = response.getBody();
					chargeCodesVO.getChargeTypes().addAll(chargeCodesVO1.getChargeTypes());
				}

			}
			logger.info("PcsController :: getAllChargeTypesByChargeCodes() :: End");
		} catch (Exception e) {
			logger.error("PcsController :: getAllChargeTypesByChargeCodes() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
			chargeCodesVO1 = null;
			coreServicesWithComma = null;
		}

		return chargeCodesVO;
	}

	@RequestMapping(value = "/saveProductData", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO saveProductData(@RequestBody ProductInfoDTO productInfoDTO, HttpServletRequest request) {
		
		productInfoDTO.setLoginId(request.getSession().getAttribute("loginID").toString());
		HttpEntity<ProductInfoDTO> httpEntity;
		ResponseEntity<ResponseDTO> response;
		ResponseDTO responceDTO = new ResponseDTO();
		try {
			logger.info("PcsController :: saveProductData() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd(), productInfoDTO);
			String url = ipAddressValues.getCatURL() + "pcs/saveProductData";
			response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,ResponseDTO.class);
			responceDTO= response.getBody();
			logger.info("PcsController :: saveProductData() :: End");
		} catch (Exception e) {
			logger.error("PcsController :: saveProductData() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return responceDTO;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllCommaSeperateFeaturesFromSrvc", method = RequestMethod.GET)
	@ResponseBody
	public List<SrvcFeatures> getAllCommaSeperateFeaturesFromSrvc(
			@RequestParam(value = "addlSrvcCode") String addlSrvcCode, HttpServletRequest request) {
		
		List<SrvcFeatures> responceDTO = new ArrayList<>();
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		try {
			logger.info("PcsController :: getAllCommaSeperateFeaturesFromSrvc() :: Start");
		    httpEntity= ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "scs/getAllSrvdFeaturesByAddlSrvcCode?srvcCode=" + addlSrvcCode;
			response= restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			responceDTO = response.getBody();
			logger.info("PcsController :: getAllCommaSeperateFeaturesFromSrvc() :: End");
		}catch (Exception e) {
			logger.info("PcsController :: getAllCommaSeperateFeaturesFromSrvc() :: "+e);
			e.printStackTrace();
		}finally {
			httpEntity = null;
			response = null;
		}
		return responceDTO;
	}

	@RequestMapping(value = "/getTelePhonySrvcFeatures", method = RequestMethod.GET)
	@ResponseBody
	public CreateProductDTO getTelePhonySrvcFeatures(@RequestParam(value = "addlSrvcCode") String addlSrvcCode) {

		CreateProductDTO createProductDTO = new CreateProductDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<CreateProductDTO> response;
		try {
			logger.info("PcsController :: getTelePhonySrvcFeatures() :: Start");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "pcs/getTelePhonySrvcFeatures?addlSrvcCode=" + addlSrvcCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,CreateProductDTO.class);
			createProductDTO = response.getBody();
			logger.info("PcsController :: getTelePhonySrvcFeatures() :: End");
		} catch (Exception ex) {
			logger.error("PcsController :: getTelePhonySrvcFeatures() :: " + ex);
		}finally {
			httpEntity = null;
			response = null;
		}

		return createProductDTO;

	}
	
	@RequestMapping(value = "/getAllTelePhoneyValues", method = RequestMethod.GET)
	@ResponseBody
	public TelePhoneyHelperDTO getAllTelePhoneyValues(@RequestParam(value = "coreSrvcCode") String coreSrvcCode) {

		TelePhoneyHelperDTO telePhoneyHelperDTO = new TelePhoneyHelperDTO();
		HttpEntity<String> httpEntity;
		ResponseEntity<TelePhoneyHelperDTO> response;
		try {
			logger.info("PcsController :: getAllTelePhoneyValues() :: Start");
			httpEntity= ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
			String url = ipAddressValues.getCatURL() + "pcs/getAllTelePhoneyValues?coreSrvcCode=" + coreSrvcCode;
			response= restTemplate.exchange(url, HttpMethod.GET, httpEntity,TelePhoneyHelperDTO.class);
			telePhoneyHelperDTO = response.getBody();
			logger.info("PcsController :: getAllTelePhoneyValues() :: End");
		} catch (Exception ex) {
			logger.error("PcsController :: getAllTelePhoneyValues() :: " + ex);
		}finally {
			httpEntity = null;
			response = null;
		}
		return telePhoneyHelperDTO;
	}
	
	@RequestMapping(value = "/getProdPrices", method = RequestMethod.GET)
	public String getProdPrices(@RequestParam(value = "prodcode") String prodcode,
			@RequestParam(value = "tenantcode") String tenantcode,
			@RequestParam(value = "effectiveDate") String effFrom,
			@RequestParam(value = "status", required = false) String status, Model model) throws ParseException {

		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url = ipAddressValues.getCatURL() + "pcs/getAllServicesByProductIds?prodcode=" + prodcode + "&tenantcode=" + tenantcode
				+ "&effectiveDate=" + effFrom;
		ResponseEntity<ProductInfoDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				ProductInfoDTO.class);
		ProductInfoDTO productInfoDTO = response.getBody();
		model.addAttribute("product", productInfoDTO);
		model.addAttribute("status", status);
		return "changeProdCharges";
	}
	
	

	@RequestMapping(value = "/updateProdPrices", method = RequestMethod.GET)
	public String updateProdPrices(@ModelAttribute ProductInfoDTO productInfoDTO, HttpServletRequest request, Model model) throws ParseException {
		productInfoDTO.setLoginId(request.getSession().getAttribute("loginID").toString());
		HttpEntity<ProductInfoDTO> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd(),productInfoDTO);
		String url = ipAddressValues.getCatURL() + "pcs/updateProdPrices";
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,String.class);
		 String status = response.getBody();
		
		return "redirect:/getProdPrices?prodcode="+productInfoDTO.getProductCode()+"&tenantcode="+productInfoDTO.getTenantcode()+"&effectiveDate="+productInfoDTO.getEffectiveFrom()+"&status="+status;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getChargeCodesForPackagePriceVersioning", method = RequestMethod.GET)
	@ResponseBody
	public List<ChargeCodesDTO> getChargeCodesForPackagePriceVersioning(@RequestParam("value") String value) throws ParseException {
		
		HttpEntity<String> httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getCatUserName(), ipAddressValues.getCatPwd());
		String url = ipAddressValues.getCatURL() + "pcs/getChargeCodesForPackagePriceVersioning?value="+value;
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,ArrayList.class);
		List<ChargeCodesDTO> list = response.getBody();
		return list;
	}

}
