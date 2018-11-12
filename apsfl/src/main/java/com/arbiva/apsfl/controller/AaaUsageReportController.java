package com.arbiva.apsfl.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.dto.AAAUsageDTO;
import com.arbiva.apsfl.dto.AaaUsageBYHoursDTO;
import com.arbiva.apsfl.dto.MailDTO;
import com.arbiva.apsfl.tms.daoImpl.TenantDaoImpl;
import com.arbiva.apsfl.tms.model.AAAUsageBO;
import com.arbiva.apsfl.tms.model.EmailMaster;
import com.arbiva.apsfl.tms.model.EmailSendDetails;
import com.arbiva.apsfl.tms.model.HSICummSummaryMonthlyCustViewDTO;
import com.arbiva.apsfl.tms.model.HSICummSummaryMonthlyViewDTO;
import com.arbiva.apsfl.tms.serviceImpl.DemandNoteServiceImpl;
import com.arbiva.apsfl.tms.serviceImpl.ReportsServiceImpl;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.DateUtill;
import com.arbiva.apsfl.util.IpAddressValues;
import com.arbiva.apsfl.util.PaginationObject;

/**
 * @author Srinivas V
 * @since March 15 2017
 *
 */

@Controller
public class AaaUsageReportController {
	

	private static final Logger logger = Logger.getLogger(AaaUsageReportController.class);
	
	@Autowired
	IpAddressValues ipAddressValues;
	
	@Autowired
	TenantDaoImpl tenantDaoImpl;
	
	@Autowired
	ReportsServiceImpl reportsServiceImpl;
	
	@Autowired
	DemandNoteServiceImpl demandNoteServiceImpl;
	
	@Value("${AAA_Usage_ReportName}")
	private String aaaUsageReportReportName;
	
	@RequestMapping(value = "/aaaUsageReport", method = RequestMethod.GET)
	public String aaaUsageReport(Model model){
		logger.info("AaaUsageReportController :: home() :: Start");
		String	month = DateUtill.getCurrentMonth();
		String	year = DateUtill.getCurrentYear();
		
		List<Object[]> districtList = new ArrayList<Object[]>();
		String stateID="1";
		districtList=tenantDaoImpl.getDistrictList(stateID);
		model.addAttribute("districtList",districtList);
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		logger.info("AaaUsageReportController :: home() :: End");
		return "aaaUsageReport";
	}
	
	@RequestMapping(value = "/getMandalsList", method = RequestMethod.GET)
	@ResponseBody
	public List<Object[]> getMandalsList(@RequestParam("districtName") String districtID, Model model){
		logger.info("AaaUsageReportController :: home() :: Start");
		List<Object[]> mandalsList = new ArrayList<Object[]>();
		String stateID="1";
		mandalsList=tenantDaoImpl.getMandalList(stateID,districtID);
		logger.info("AaaUsageReportController :: home() :: End");
		return mandalsList;
	}
	
	@RequestMapping(value = "/getVillagesList", method = RequestMethod.GET)
	@ResponseBody
	public List<Object[]> getVillagesList(@RequestParam("mandalName") String mandalId,@RequestParam("districtName") String districtId, Model model){
		logger.info("AaaUsageReportController :: home() :: Start");
		List<Object[]> villagesList = new ArrayList<Object[]>();
		String stateID="1";
		villagesList=tenantDaoImpl.getVillageList(stateID, districtId, mandalId);
		logger.info("AaaUsageReportController :: home() :: End");
		return villagesList;
	}
	
	 
	@RequestMapping(value = "/aaaUsageReportByDate", method = RequestMethod.GET )
	public @ResponseBody PaginationObject<AAAUsageBO> aaaUsageBySearch(HttpServletRequest request, AAAUsageDTO aaaUsageDTO) {

		PaginationObject<AAAUsageBO> paginationObj = new PaginationObject<>();
		try {
			logger.info("Reports Controller :: aaaUsageBySearch() :: Start");
			// Getting PAGE Data 
			Integer pageDisplayLength=5;
			Integer pageNumber = 1;
			String sdir="desc";
			String sortColumn="";
			int sortParameter=0;
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/pageDisplayLength)+1;
			if(request.getParameter("iSortCol_0")!=null){
				sortParameter =Integer.parseInt( request.getParameter("iSortCol_0"));
			}
	    	sortColumn = AAAUsageDTO.AAAUsageColumnName.getColumns("COLUMN_"+sortParameter);
			if(request.getParameter("sSortDir_0")!=null)
		   	sdir = request.getParameter("sSortDir_0");
			String searchParameter = request.getParameter("sSearch");
			
			//Setting All page Data to PageObject
			PageObject pageObject= new PageObject();
			pageObject.setMinSize((pageNumber-1)*pageDisplayLength);
			pageObject.setMaxSize(pageDisplayLength);
			pageObject.setSortColumn(sortColumn);
			pageObject.setSortOrder(sdir);
			pageObject.setSearchParameter(searchParameter);
			
			aaaUsageDTO = reportsServiceImpl.getAAAUsageBySearch(pageObject,aaaUsageDTO);
			paginationObj.setAaData(aaaUsageDTO.getList());
			paginationObj.setiTotalDisplayRecords(aaaUsageDTO.getCount());
			paginationObj.setiTotalRecords(aaaUsageDTO.getCount());
			
		} catch (Exception e) {
			logger.error("ComsController :: searchCafProductsDetails() :: " + e);
			e.printStackTrace();
		} 
		return paginationObj;
	}
	
	@RequestMapping(value = "/aaaUsageReportDownload", method = RequestMethod.GET)
	public void aaaUsageReportDownload(AAAUsageDTO aaaUsageDTO,HttpServletRequest request, HttpServletResponse response){
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = reportsServiceImpl.aaaUsageReportDownload(aaaUsageDTO);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=AAA Usage Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
		
	}
	

	@RequestMapping(value = "/aaaUsageReportEmail")
	@ResponseBody
	public String aaaUsageReportEmail() {
		HttpEntity<MailDTO> httpEntity = null;
		ResponseEntity<String> response = null;
		List<EmailMaster> emailList = null;
		RestTemplate restTemplate = new RestTemplate();
		MailDTO mailDto = null;
		HSSFWorkbook workbook = null;
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook = reportsServiceImpl.aaaUsageReportEmail();
			emailList = demandNoteServiceImpl.getEamilOfCafWiseReport(aaaUsageReportReportName);
			workbook.write(bos);
			for (EmailMaster emailMast : emailList) {
				mailDto = new MailDTO();
				mailDto.setTo(emailMast.getEmailid());
				mailDto.setFile(bos.toByteArray());
				mailDto.setMsg(emailMast.getMsg());
				mailDto.setSubject(emailMast.getSubj());
				mailDto.setFileName("DISTRICT WISE CAF REPORT.xls");
				httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getUmsUserName(), ipAddressValues.getUmsPwd(), mailDto);
				String url = ipAddressValues.getUmsURL() + "sendMailWithAttachment";
				response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

				EmailSendDetails emailSendDetails = new EmailSendDetails();
				emailSendDetails.setEmailid(emailMast.getEmailid());
				emailSendDetails.setRptname(aaaUsageReportReportName);
				emailSendDetails.setSenttime(Calendar.getInstance());
				emailSendDetails.setStatus(response.getBody().substring(0, 1));
				demandNoteServiceImpl.save(emailSendDetails);
			}
		} catch (Exception e) {
			logger.error("The Exception is SchedulerController :: DistrictWiseCafListEmail" +e);
			e.printStackTrace();
			logger.error(e);
		}
		return "Success";
	}
	
	@RequestMapping(value="/aaaUsageByHours", method=RequestMethod.GET )
	public String aaaUsageByhours()
	{
		return "aaaUsageReportByHours";
		
	}
	
	@RequestMapping(value="/getaaaUsageByHours", method=RequestMethod.GET )
	public String sgetaaaUsageByHours(AaaUsageBYHoursDTO aaaUsageBYHoursDTO, Model uiModel )
	{ 
	 logger.info("ReportsController :: getaaaUsageByHours() :: END");
	 List<AaaUsageBYHoursDTO>  aaaUsageBYHoursDTOList= new ArrayList<>();
	 aaaUsageBYHoursDTOList=reportsServiceImpl.getaaaUsageReportByHours(aaaUsageBYHoursDTO);
	 uiModel.addAttribute("list", aaaUsageBYHoursDTOList);
	 uiModel.addAttribute("month", aaaUsageBYHoursDTO.getMonthSelected());
	 uiModel.addAttribute("year", aaaUsageBYHoursDTO.getYearSelected());
	 logger.info("ReportsController :: getaaaUsageByHours() :: END");
	 return "aaaUsageReportByHours";
		
	}
	
	@RequestMapping(value = "/getaaaUsageByHoursDownload", method = RequestMethod.GET)
	public void getaaaUsageByHoursDownload(AaaUsageBYHoursDTO aaaUsageBYHoursDTO,HttpServletRequest request, HttpServletResponse response){
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = reportsServiceImpl.getaaaUsageWorkBookByHoursDownload(aaaUsageBYHoursDTO);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=AAA Usage By Hour Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
		
	}
	
	@RequestMapping(value="/getTotalAAAUsagePerMonth", method={RequestMethod.POST,RequestMethod.GET})
	public String getTotalAAAUsagePerMonth(Model uiModel, 
			@RequestParam(value="month", required = false) String month,
			@RequestParam(value="year", required = false) String year )
	{ 
		List<HSICummSummaryMonthlyViewDTO> list = new ArrayList<>();
		HSICummSummaryMonthlyViewDTO hsiCummSummaryMonthlyViewDTO = new HSICummSummaryMonthlyViewDTO();
		try{
			if(month == null || month.equalsIgnoreCase(""))
				month = DateUtill.getCurrentMonth();
			if(year == null || year.equalsIgnoreCase(""))
				year = DateUtill.getCurrentYear();
			 logger.info("ReportsController :: gatTotalAAAUsagePerMonth() :: END");
			 list = reportsServiceImpl.getTotalAAAUsagePerMonthDaywise(month,year);
			 hsiCummSummaryMonthlyViewDTO = reportsServiceImpl.getTotalAAAUsagePerMonth(month,year);
			 logger.info("ReportsController :: gatTotalAAAUsagePerMonth() :: END");
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		uiModel.addAttribute("list", list);
		uiModel.addAttribute("monthlySummary", hsiCummSummaryMonthlyViewDTO);
		uiModel.addAttribute("year", year);
		uiModel.addAttribute("month", month);

	 return "aaaUsagePerMonth";
		
	}
	
	
	
	@RequestMapping(value="/getTotalAAAUsagePerMonthCustomer", method=RequestMethod.GET )
	public String getTotalAAAUsagePerMonthCustomer(Model uiModel){
		uiModel.addAttribute("month", DateUtill.getCurrentMonth());
		uiModel.addAttribute("year", DateUtill.getCurrentYear());
	 return "aaaUsagePerMonthCust";
		
	}
	@RequestMapping(value="/getTotalAAAUsagePerMonthCustomer", method=RequestMethod.POST )
	public String getTotalAAAUsagePerMonthCustomer(Model uiModel, 
			@RequestParam(value="month", required = false) String month,
			@RequestParam(value="year", required = false) String year,
			@RequestParam(value="cafNo", required = false) String cafNo )
	{ 
		List<HSICummSummaryMonthlyCustViewDTO> list = new ArrayList<>();
		HSICummSummaryMonthlyCustViewDTO hsiCummSummaryMonthlyViewDTO = new HSICummSummaryMonthlyCustViewDTO();
		try{
			if(month == null || month.equalsIgnoreCase(""))
				month = DateUtill.getCurrentMonth();
			if(year == null || year.equalsIgnoreCase(""))
				year = DateUtill.getCurrentYear();
			 logger.info("ReportsController :: gatTotalAAAUsagePerMonth() :: END");
			 list = reportsServiceImpl.getTotalAAAUsagePerMonthCustomerDayWise(month,year,cafNo);
			 hsiCummSummaryMonthlyViewDTO = reportsServiceImpl.getTotalAAAUsagePerMonthCustomer(month,year,cafNo);
			 logger.info("ReportsController :: gatTotalAAAUsagePerMonth() :: END");
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		uiModel.addAttribute("list", list);
		uiModel.addAttribute("monthlySummary", hsiCummSummaryMonthlyViewDTO);
		uiModel.addAttribute("year", year);
		uiModel.addAttribute("month", month);
		uiModel.addAttribute("cafNo", cafNo);

	 return "aaaUsagePerMonthCust";
		
	}
}
