package com.arbiva.apsfl.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.arbiva.apfgc.tenant.bo.CafWiseReportBO;
import com.arbiva.apfgc.tenant.bo.DistrictPonWiseCafCountBO;
import com.arbiva.apfgc.tenant.bo.DistrictWiseCafBO;
import com.arbiva.apfgc.tenant.bo.DistrictWiseCpeBO;
import com.arbiva.apfgc.tenant.bo.EnterpriseSubscriberBO;
import com.arbiva.apfgc.tenant.bo.TenantStockReportBO;
import com.arbiva.apfgc.tenant.bo.LmoStockCountBO;
import com.arbiva.apfgc.tenant.bo.LmoWalletUpdateByChequePaymentBO;
import com.arbiva.apfgc.tenant.bo.MsoCafNotCpeStockBo;
import com.arbiva.apfgc.tenant.bo.MsoDetailsWithLmosBO;
import com.arbiva.apfgc.tenant.bo.MsoWiseCpeBo;
import com.arbiva.apfgc.tenant.bo.OLTMasterDataBO;
import com.arbiva.apfgc.tenant.bo.PONWiseBo;
import com.arbiva.apfgc.tenant.bo.PONWithZeroCAFBO;
import com.arbiva.apfgc.tenant.bo.RiggedCafBO;
import com.arbiva.apsfl.coms.dto.CustomerDTO;
import com.arbiva.apsfl.coms.dto.Districts;
import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.dto.TenantWalletTransDTO;
import com.arbiva.apsfl.reports.vo.MsoListVo;
import com.arbiva.apsfl.reports.vo.ReportsDTO;
import com.arbiva.apsfl.reports.vo.SubstationWiseCafVO;
import com.arbiva.apsfl.tms.daoImpl.DemandNoteDaoImpl;
import com.arbiva.apsfl.tms.dto.TmsHelperDTO;
import com.arbiva.apsfl.tms.serviceImpl.DemandNoteServiceImpl;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.DateUtill;
import com.arbiva.apsfl.util.IpAddressValues;
import com.arbiva.apsfl.util.PaginationObject;

import freemarker.core.ParseException;


@SuppressWarnings("deprecation")
@Controller
public class DemandNoteReportController {
	RestTemplate restTemplate = new RestTemplate();
	private static final Logger logger = Logger.getLogger(DemandNoteReportController.class);
	
	@Autowired
	IpAddressValues ipAddressValues;

	@Autowired
	DemandNoteServiceImpl demandNoteServiceImpl;

	@RequestMapping(value = "/msoDetails", method = RequestMethod.GET)
	public String getMsoDetails(Model msoModel) {
		try {
			List<MsoListVo> listMso = new ArrayList<>();
			listMso = demandNoteServiceImpl.getMsoList();
			msoModel.addAttribute("msoList", listMso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msoList";
	}

	@RequestMapping(value = "/downloadMsoDetails", method = RequestMethod.GET)
	public void downloadMsoDetails(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		List<MsoListVo> listMso = new ArrayList<>();
		listMso = demandNoteServiceImpl.getMsoList();
		if (download) {
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("MSO Wise CPE Request Report");
			Row header = sheet.createRow(4);
			HSSFRow aRow = sheet.createRow(5);
			HSSFRow row = sheet.createRow(0);// Title
			HSSFRow row1 = sheet.createRow(1);// Report name
			HSSFRow row2 = sheet.createRow(2);// Dates

			Calendar cal = Calendar.getInstance();
			String currDate = sdf.format(cal.getTime());
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {

				byte[] bytes = IOUtils.toByteArray(my_banner_image);
				int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				my_banner_image.close();
				HSSFPatriarch drawing = sheet.createDrawingPatriarch();
				ClientAnchor my_anchor = new HSSFClientAnchor();
				my_anchor.setDx1(0);
				my_anchor.setDy1(0);
				my_anchor.setDx2(0);
				my_anchor.setDy2(0);
				my_anchor.setCol1(0);
				my_anchor.setRow1(0);
				my_anchor.setCol2(1);
				my_anchor.setRow2(1);
				HSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
				my_picture.resize();

				CellStyle style = workbook.createCellStyle();
				style.setAlignment(CellStyle.ALIGN_CENTER);
				sheet.setDefaultColumnWidth(30);
				Font font = workbook.createFont();
				font.setFontName("Arial");
				style.setFillForegroundColor(HSSFColor.WHITE.index);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setColor(HSSFColor.BLACK.index);
				style.setFont(font);

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("MSO Wise CPE Request Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				cell = row2.createCell(0);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("MSO Code");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("MSO Company  Name");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("MSO Contact Name");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("MSO Contact No");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("District Name ");
				header.getCell(4).setCellStyle(style);

				header.createCell(5).setCellValue("Mandal Name");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("Upfront Cpe's");
				header.getCell(6).setCellStyle(style);

				header.createCell(7).setCellValue("CPE-36 Months(Rs.1700)");
				header.getCell(7).setCellStyle(style);

				header.createCell(8).setCellValue("CPE-48 Months(Rs.500)");
				header.getCell(8).setCellStyle(style);

				// create data rows
				int rowCount = 5;

				for (MsoListVo obj : listMso) {
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue(obj.getTenantCode());
					aRow.createCell(1).setCellValue(obj.getTenantName());
					aRow.createCell(2).setCellValue(obj.getRegOfficePocName());
					aRow.createCell(3).setCellValue(obj.getPocMobileNumber());
					aRow.createCell(4).setCellValue(obj.getDistrictName());
					aRow.createCell(5).setCellValue(obj.getMandalName());
					aRow.createCell(6).setCellValue(obj.getEmiDemandQuantity());
					aRow.createCell(7).setCellValue(obj.getEmi36DemandQuantity());
					aRow.createCell(8).setCellValue(obj.getEmi48DemandQuantity());
				}

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=MSO Wise CPE Request Report.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
		}
	}

	@RequestMapping(value = "/lmoDetails", method = RequestMethod.GET)
	public String getLmoDetails(Model msoModel, @RequestParam("tenantcode") String tenantCode) {
		List<MsoListVo> listMso = new ArrayList<>();
		listMso = demandNoteServiceImpl.getLmoList(tenantCode);
		msoModel.addAttribute("lmoList", listMso);
		msoModel.addAttribute("tenantCode", tenantCode);

		return "lmoList";
	}

	@RequestMapping(value = "/downLoadLmoDetails", method = RequestMethod.GET)
	public void downLoadLmoDetails(@RequestParam("tenantcode") String tenantCode,
			@RequestParam(value = "download", required = false) boolean download, HttpServletRequest request,
			HttpServletResponse response) {

		List<MsoListVo> listMso = new ArrayList<>();
		listMso = demandNoteServiceImpl.getLmoList(tenantCode);

		if (download) {
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("LMO Wise CPE Request Report");
			Row header = sheet.createRow(4);
			HSSFRow aRow = sheet.createRow(5);
			HSSFRow row = sheet.createRow(0);// Title
			HSSFRow row1 = sheet.createRow(1);// Report name
			HSSFRow row2 = sheet.createRow(2);// Dates

			Calendar cal = Calendar.getInstance();
			String currDate = sdf.format(cal.getTime());
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {

				byte[] bytes = IOUtils.toByteArray(my_banner_image);
				int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				my_banner_image.close();
				HSSFPatriarch drawing = sheet.createDrawingPatriarch();
				ClientAnchor my_anchor = new HSSFClientAnchor();
				my_anchor.setDx1(0);
				my_anchor.setDy1(0);
				my_anchor.setDx2(0);
				my_anchor.setDy2(0);
				my_anchor.setCol1(0);
				my_anchor.setRow1(0);
				my_anchor.setCol2(1);
				my_anchor.setRow2(1);
				HSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
				my_picture.resize();

				CellStyle style = workbook.createCellStyle();
				style.setAlignment(CellStyle.ALIGN_CENTER);
				sheet.setDefaultColumnWidth(30);
				Font font = workbook.createFont();
				font.setFontName("Arial");
				style.setFillForegroundColor(HSSFColor.WHITE.index);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setColor(HSSFColor.BLACK.index);
				style.setFont(font);

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("LMO Wise CPE Request Details");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				cell = row2.createCell(0);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("LMO Name");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("District Name");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("Mandal Name");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("Upfront Cpe's");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("CPE-36 Months(Rs.1700)");
				header.getCell(4).setCellStyle(style);

				header.createCell(5).setCellValue("CPE-48 Months(Rs.500)");
				header.getCell(5).setCellStyle(style);

				// create data rows
				int rowCount = 5;

				for (MsoListVo obj : listMso) {
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue(obj.getTenantName());
					aRow.createCell(1).setCellValue(obj.getDistrictName());
					aRow.createCell(2).setCellValue(obj.getMandalName());
					aRow.createCell(3).setCellValue(obj.getEmiDemandQuantity());
					aRow.createCell(4).setCellValue(obj.getEmi36DemandQuantity());
					aRow.createCell(5).setCellValue(obj.getEmi48DemandQuantity());
				}

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=LMO WiseCPE Request Report.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
		}
	}

	@RequestMapping(value = "/msoWiseDemand", method = RequestMethod.GET)
	public String msoDemand(Model msoModel) {
		try {
			List<MsoWiseCpeBo> listMso = new ArrayList<>();
			listMso = demandNoteServiceImpl.getMsoWiseDemand();
			msoModel.addAttribute("list", listMso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "msoWiseDemand";
	}

	@RequestMapping(value = "/downloadMsoWiseDemand", method = RequestMethod.GET)
	public void downloadMsoWiseDemand(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getMsoWiseExcelFile();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=MSO Wise Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}

	@RequestMapping(value = "/districtWiseDemand", method = RequestMethod.GET)
	public String districtWiseDemand(Model msoModel) {
		try {
			List<DistrictWiseCpeBO> listMso = new ArrayList<>();
			listMso = demandNoteServiceImpl.getDistrictWiseDemand();
			msoModel.addAttribute("list", listMso);
			msoModel.addAttribute("currentDate", DateUtill.getSTRING_dd_MMM_YYYY());
			msoModel.addAttribute("previousDate", DateUtill.getPreviousSTRING_dd_MMM_YYYY());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "districtWiseDemand";
	}

	@RequestMapping(value = "/downloadManagerDistrictWiseDemand", method = RequestMethod.GET)
	public void downloadManagerDistrictWiseDemand(@RequestParam(value="effectivefrom", required = false) String from,
			@RequestParam(value="effectiveto", required = false) String to,
			@RequestParam(value="lmocode", required = false) String lmocode,
			@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadgetmgrdistrictWiseStockCount(tenantcode,from,to,lmocode);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=District Wise Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	@RequestMapping(value = "/downloadManagerCustomerDemand", method = RequestMethod.GET)
	public void downloadManagerCustomerDemand(@RequestParam(value="effectivefrom", required = false) String efrom,@RequestParam(value="tenantCode", required = false) String tenantcode,@RequestParam(value="from", required = false) String from,
			@RequestParam(value="to", required = false) String to,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadgetmgrCustomerdetails(tenantcode,from,to);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=+"+tenantcode+"Cafs_Report.xls");
			workbook.write(out);
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	@RequestMapping(value = "/downloadDistrictWiseDemand", method = RequestMethod.GET)
	public void downloadDistrictWiseDemand(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getDistrictWiseExcelFile();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=District Wise Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}

	@RequestMapping(value = "/cafWiseDemand", method = RequestMethod.GET)
	public String cpeWiseDemand(Model msoModel) {
		try {
			List<CafWiseReportBO> list = new ArrayList<>();
			list = demandNoteServiceImpl.getCafWiseDemand();
			msoModel.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cafReport";
	}

	@RequestMapping(value = "/downloadCafWiseReport", method = RequestMethod.GET)
	public void downloadCafWiseReport(HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getCafWiseExcelFile();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=CAF Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	@RequestMapping(value = "/districtWiseCafReport", method = RequestMethod.GET)
	public String districtWiseCpeReports(Model model, HttpServletRequest request) {
		try {
			List<DistrictWiseCafBO> districtWiseCafList = new ArrayList<>();
			districtWiseCafList = demandNoteServiceImpl.getDistrictWiseCafList();
			model.addAttribute("districtWiseCafList", districtWiseCafList);
			model.addAttribute("currentDate", DateUtill.getSTRING_dd_MMM_YYYY());
			model.addAttribute("previousDate", DateUtill.getPreviousSTRING_dd_MMM_YYYY());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "distWiseCafReport";
	}
	@RequestMapping(value = "/managerdistWiseCafReport", method={RequestMethod.POST,RequestMethod.GET})
	public String mgr_districtWiseCpeReports(@RequestParam(value="effectivefrom", required = false) String from1,
			@RequestParam(value="effectiveto", required = false) String to1,
			@RequestParam(value="lmocode", required = false) String lmocode,Model model, HttpServletRequest request) {
		try {
			String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
			List<LmoStockCountBO> list = new ArrayList<>();
			/*String val1[]=from1.split("/");
			String  val2[]=to1.split("/");*/
			list = demandNoteServiceImpl.getmgrdistrictWiseStockCount(tenantcode,from1,to1, lmocode);
			
			long totalcafcount=0;
			Integer lmocount=0;
			Map<String, String> lmototalPrevbal= new HashMap<String, String>();
			
			 for (LmoStockCountBO lmostcnt : list) {


				 totalcafcount =totalcafcount + lmostcnt.getCafCount();
				 
				 if(lmostcnt.getLmoCode() != null && !lmostcnt.getLmoCode().isEmpty()){
						    	
						 lmototalPrevbal.put(lmostcnt.getLmoCode(), lmostcnt.getLmoCode());
			   }		    	
	     }
			lmocount=lmototalPrevbal.keySet().size();
			
			model.addAttribute("districtWiseCafList", list);
			model.addAttribute("effectiveto",to1);
			model.addAttribute("effectivefrom",from1);
			model.addAttribute("hiddentodate",to1);
			model.addAttribute("hiddenfromdate",from1);
			model.addAttribute("lmocode",lmocode);
			model.addAttribute("totalcafcount",totalcafcount);
			model.addAttribute("lmocount",lmocount);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return "managerdistWiseCafReport";
	}
	@SuppressWarnings("unused")
	@RequestMapping(value = "/managerCustomerCafReport", method={RequestMethod.POST,RequestMethod.GET})
	public String managerCustomerCafReport(@RequestParam(value="effectivefrom", required = false) String efrom,@RequestParam(value="tenantCode", required = false) String tenantcode,@RequestParam(value="from", required = false) String from,
			@RequestParam(value="to", required = false) String to,Model model,
			HttpServletRequest request) {
		try {
			List<CustomerDTO> recs = demandNoteServiceImpl.getCustomerDetails(tenantcode,from,to);
			model.addAttribute("districtWiseCafList", recs);
			model.addAttribute("tenantCode",tenantcode);
			model.addAttribute("hiddentodate",to);
			model.addAttribute("ofdate",from);
			model.addAttribute("hiddenfromdate",efrom);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "managerCustomerCafReport";
	}

	@RequestMapping(value = "/downloadDistrictWiseCafReport", method = RequestMethod.GET)
	public void downloadDistrictWiseCpeReports(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getDistrictWiseCafExcelFile();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=District Wise CAF Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	
	@RequestMapping(value = "/entSubscriberListReport", method = RequestMethod.GET)
	public String getBasedOnActivationDate(HttpServletRequest request, HttpServletResponse response, Model uiModel) throws IOException {
		return "entSubscriberReport";
	}
	
	@RequestMapping(value = "/entSubscriberListReportPagination", method = RequestMethod.GET)
	@ResponseBody public PaginationObject<EnterpriseSubscriberBO> entSubscriberListReportPagination(HttpServletRequest request, 
										HttpServletResponse response, Model uiModel) throws IOException {

		PaginationObject<EnterpriseSubscriberBO> dataPageObject = new PaginationObject<EnterpriseSubscriberBO>();
		ReportsDTO reportDto = new ReportsDTO();

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
			sortColumn = EnterpriseSubscriberBO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			reportDto.setPageObject(pageObject);
			reportDto = demandNoteServiceImpl.getEnterpriseSubscriberListReport(pageObject);
			dataPageObject.setAaData(reportDto.getEntSubscriberList());
			dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;
	}
	
	
	@RequestMapping(value = "/downloadEntSubscriberReport", method = RequestMethod.GET)
	public void downloadEntSubscriberReport(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getEntSubscriberExcelFile(request, response);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=entsubscriberreport.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	@RequestMapping(value = "/ponWiseReport", method = RequestMethod.GET)
	public String getPonWiseReport(HttpServletRequest request, HttpServletResponse response, Model uiModel) throws IOException {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response1;
		String url = "";
		try {
			
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "oltPortAllocation?tenantCode=" + tenantCode;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			List<Districts> districtsList = response1.getBody();
			uiModel.addAttribute("districtList", districtsList);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}	
		
		return "ponWiseReport";
	}
	
	
	@RequestMapping(value = "/ponWiseReportPagination", method = {RequestMethod.POST,RequestMethod.GET})
	public String getPonWiseReportPagination(@RequestParam(value="districtuid", required = false) String district,
			@RequestParam(value="mandalslno", required = false) String mandal,
			@RequestParam(value = "lmocode", required = false) String lmocode,HttpServletRequest request, 
			 Model uiModel) throws IOException {			
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response1;
		String url = "";
		try {
			
			TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "getDistmandalDetails?districtuid=" + district + "&mandalslno="
					+ mandal;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response1.getBody();
			

			
			List<PONWiseBo> ponWithCAFList = new ArrayList<PONWiseBo>();
			ponWithCAFList = demandNoteServiceImpl.getAllotedPONWithCaf(district, mandal, lmocode);
			
			

			
			long popcount=0;
			Integer lmocount=0;
			long cafCount=0;
			Integer portCount=0;
			
			Set<String> lmomap= new HashSet<String>();
			Set<String> popmap= new HashSet<String>();
			
			
			 for (PONWiseBo lmostotalcnt : ponWithCAFList ) {
				 
				 if(lmostotalcnt.getLmocode() != null && !lmostotalcnt.getLmocode().isEmpty()){
						    	
					 lmomap.add(lmostotalcnt.getLmocode());
			   }
				 
					 
					 if(lmostotalcnt.getPop_name() != null && !lmostotalcnt.getPop_name().isEmpty()){
							    	
						 popmap.add(lmostotalcnt.getPop_name());
				   }
					 if(lmostotalcnt.getPortno() != null && !lmostotalcnt.getPortno().isEmpty()){
					    	
						 portCount++;
				   }
					 if(lmostotalcnt.getCafno() != null){
					    	
						 cafCount = cafCount + lmostotalcnt.getCafno();
				   }
	     }
			lmocount=lmomap.size();
			popcount=popmap.size();
			
			uiModel.addAttribute("mandalList", tmsHelperDTO.getMandalsList());
			uiModel.addAttribute("districtList", tmsHelperDTO.getDistrictsList());
			uiModel.addAttribute("districtuid", district);
			uiModel.addAttribute("mandalslno", mandal);
			uiModel.addAttribute("lmocode", lmocode);
			uiModel.addAttribute("ponWithCAFList", ponWithCAFList);
			uiModel.addAttribute("lmocount", lmocount);
			uiModel.addAttribute("popcount", popcount);
			uiModel.addAttribute("cafCount", cafCount);
			uiModel.addAttribute("portCount", portCount);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			httpEntity = null;
			url = null;
			//response = null;
			response1=null;
		}

		return "ponWiseReport";
	}
	
	@RequestMapping(value = "/downloadPonWiseReport", method = RequestMethod.GET)
	public void downloadPonWiseReport(@RequestParam(value="district", required = false) String district, 
			@RequestParam(value = "mandal", required = false) String mandal,
			@RequestParam(value ="lmocode", required= false) String lmocode,
			@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadPONWiseExcel(district, mandal, lmocode, request, response);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=ponWiseRepost.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	@RequestMapping(value = "/ponWithNoCafReport", method = RequestMethod.GET)
	public String getPonWithNoCafReport(HttpServletRequest request, Model uiModel) throws IOException {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		String url = "";
		try {
			
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "oltPortAllocation?tenantCode=" + tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			List<Districts> districtsList = response.getBody();
			uiModel.addAttribute("districtList", districtsList);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			httpEntity = null;
			url = null;
			response = null;
		}	
		
		return "ponWithNoCafReport";
	}
	
	
	@RequestMapping(value = "/ponWithNoCafPagination", method = RequestMethod.POST)
	//@ResponseBody
	public String getponWithNoCafPagination(@RequestParam(value="districtuid", required = false) String district,
			@RequestParam(value="mandalslno", required = false) String mandal,
			@RequestParam(value = "lmocode", required = false) String lmocode,
			HttpServletRequest request, 
										HttpServletResponse response, Model uiModel) throws IOException {
		
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response1;
		String url = "";



		try {
			
			TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "getDistmandalDetails?districtuid=" + district + "&mandalslno=" + mandal;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response1.getBody();
			

			
			List<PONWithZeroCAFBO> ponWithZeroCAFList = new ArrayList<PONWithZeroCAFBO>();
			ponWithZeroCAFList = demandNoteServiceImpl.getPONWithZeroCAFBO(district, mandal, lmocode);
			

			
			long popcount=0;
			Integer lmocount=0;
			Integer portCount=0;
			
			Set<String> lmomap= new HashSet<String>();
			Set<String> popmap= new HashSet<String>();
			
			
			 for (PONWithZeroCAFBO lmostotalcnt : ponWithZeroCAFList ) {
				 
				 if(lmostotalcnt.getLmocode() != null && !lmostotalcnt.getLmocode().isEmpty()){
						    	
					 lmomap.add(lmostotalcnt.getLmocode());
			   }
				 
					 
					 if(lmostotalcnt.getPop_name() != null && !lmostotalcnt.getPop_name().isEmpty()){
							    	
						 popmap.add(lmostotalcnt.getPop_name());
				   }
					 
					 if(lmostotalcnt.getPortno() != 0 ){
					    	
						 portCount++;
				   }
				 
	     }
			lmocount=lmomap.size();
			popcount=popmap.size();
			
			uiModel.addAttribute("mandalList", tmsHelperDTO.getMandalsList());
			uiModel.addAttribute("districtList", tmsHelperDTO.getDistrictsList());
			uiModel.addAttribute("districtuid", district);
			uiModel.addAttribute("mandalslno", mandal);
			uiModel.addAttribute("lmocode", lmocode);
			uiModel.addAttribute("ponWithZeroCAFList", ponWithZeroCAFList);
			uiModel.addAttribute("lmocount", lmocount);
			uiModel.addAttribute("popcount", popcount);
			uiModel.addAttribute("portCount", portCount);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			httpEntity = null;
			url = null;
			response = null;
		}

		return "ponWithNoCafReport";
	}
	
	@RequestMapping(value = "/downloadPonWithNoCafReport", method = RequestMethod.GET)
	public void downloadPonWithNoCafReport(@RequestParam(value="district", required = false) String district,
			@RequestParam(value="mandal", required = false) String mandal,
			@RequestParam(value = "lmocode", required = false) String lmocode,
			@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadPONWithNoCafExcel(district,mandal,lmocode, request, response);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=ponWithNoCaf.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	@RequestMapping(value = "/substationCafReport", method ={RequestMethod.GET,RequestMethod.POST})
	public String substationCafReport(@RequestParam(value="districtuid", required = false) String district,
			@RequestParam(value="mandalslno", required = false) String mandal,Model model) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response1;
		String url = "";
		
		Long portcount8=0l, portcount16=0l, cafcount8=0l, cafcount16=0l;
		
		try {
			if (district==null)
				district="";
			if (mandal==null)
				mandal="";
				
			TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "getDistmandalDetails?districtuid=" + district + "&mandalslno=" + mandal;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response1.getBody();
			
			HashMap<Integer,List<SubstationWiseCafVO>> map = demandNoteServiceImpl.getSubstationWisePONWithCaf(district, mandal);
			
			List<SubstationWiseCafVO> list8 = map.get(8);
			List<SubstationWiseCafVO> list16 = map.get(16);
			
			if(list8.size() != 0){
			cafcount8=list8.get(list8.size() - 1).getCafCount();
			portcount8=list8.get(list8.size() - 1).getPortCount();
			
			}
			
			if(list16.size() != 0){
			cafcount16=list16.get(list16.size() - 1).getCafCount();
			portcount16=list16.get(list16.size() - 1).getPortCount();
			}
			
			model.addAttribute("mandalList", tmsHelperDTO.getMandalsList());
			model.addAttribute("districtList", tmsHelperDTO.getDistrictsList());
			model.addAttribute("districtuid", district);
			model.addAttribute("mandalslno", mandal);
			model.addAttribute("cafcount8", cafcount8);
			model.addAttribute("cafcount16", cafcount16);
			model.addAttribute("portcount8", portcount8);
			model.addAttribute("portcount16", portcount16);
			model.addAttribute("list8", list8);
			model.addAttribute("list16", list16);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "substationCafReport";
	}
	
	@RequestMapping(value = "/downloadSubWiseReport", method = RequestMethod.GET)
	public void downloadSubWiseReport(@RequestParam(value="districtuid", required = false ) String districtuid,
			@RequestParam(value="mandalslno", required = false ) String mandalslno, HttpServletRequest request, HttpServletResponse response,int noofports) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadSubWiseExcelFile(districtuid, mandalslno, request, response,noofports);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=CAF Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	//LMO Wise Stock Count
	@RequestMapping(value = "/lmoWiseStockCount", method = RequestMethod.GET)
	public String getLmoWiseStockCount(Model msoModel) {
		try {
			List<LmoStockCountBO> list = new ArrayList<>();
			list = demandNoteServiceImpl.getlmoWiseStockCount();
			msoModel.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lmoWiseStockCount";
	}
	
	// download LMO wise stock Count
	@RequestMapping(value = "/downloadlmoWiseStockCount", method = RequestMethod.GET)
	public void downloadlmoWiseStockCount(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getLmoStockCountExcel();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=LMO Wise Stock Count.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	// MSO wise LMO 
	@RequestMapping(value = "/msoDetailsWithAlignedLMOs", method = RequestMethod.GET)
	public String msoDetailsWithAlignedLMOs(Model model, HttpServletRequest request) {
		try {
			List<MsoDetailsWithLmosBO> lmoMsoList = new ArrayList<>();
			lmoMsoList = demandNoteServiceImpl.getMsoWiseLmoDetails();
			model.addAttribute("lmoMsoList", lmoMsoList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "msoDetailsWithAlignedLMOs";
	}

	// MSO wise LMO report
	@RequestMapping(value = "/downloadMsoWiseLmoDetails", method = RequestMethod.GET)
	public void downloadMsoWiseLmoDetails(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getMsoWiseLmoExcel();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=MSO Wise LMO Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	//tenant wise CPE Stock
	@RequestMapping(value = "/tenantWiseCpeStock", method = RequestMethod.GET)
	public String tenantCpeStockReport(Model model,  @RequestParam(value = "tenantCode") String tenantCode,
			@RequestParam(value = "status") String status) {
		try {
			List<TenantStockReportBO> tenantStock = new ArrayList<>();
			tenantStock = demandNoteServiceImpl.getTenantStockReport(tenantCode,status);
			model.addAttribute("tenantStock", tenantStock);
			model.addAttribute("tenantCode", tenantCode);
			model.addAttribute("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "tenantWiseCpeStock";
	}
		
	// Tenant wise CPE stock report
	@RequestMapping(value = "/downloadTenantWiseCpeStock", method = RequestMethod.GET)
	public void downloadTenantCPEStock(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "tenantCode", required = false) String tenantCode,
			@RequestParam(value = "status", required = false) String status) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getTenantStockExcel(tenantCode, status);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Tenant CPE Stock.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
		
	// MSO wise CPE Stock method
	@RequestMapping(value = "/msoWiseCpeStock", method = RequestMethod.GET)
	public String MsoWiseCpeStockDetails(Model model, HttpServletRequest request) {
		try {
			List<MsoCafNotCpeStockBo> msoCafNotCpeStockList = new ArrayList<>();
			msoCafNotCpeStockList = demandNoteServiceImpl.getMsoWiseCpeStockDetails();
			model.addAttribute("msoCafNotCpeStockList", msoCafNotCpeStockList);
			/*
			 * model.addAttribute("currentDate",
			 * DateUtill.getSTRING_dd_MMM_YYYY());
			 * model.addAttribute("previousDate",
			 * DateUtill.getPreviousSTRING_dd_MMM_YYYY());
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "msoWiseCpeStock";
	}

	// MSO wise CPE stock report
	@RequestMapping(value = "/downloadMsoCafNOtCpeStock", method = RequestMethod.GET)
	public void downloadMsoCafNotCpeStockListExcel(@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getMsoCafNotCpeStockListExcel();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=MSO Caf Not CpeStock Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	
	//district mandal village wise caf count
	@RequestMapping(value = "/distMandalvillageWiseCafCount", method={RequestMethod.POST,RequestMethod.GET})
	public String getDistrictMandalVillageWiseCafCount(@RequestParam(value="effectivefrom", required = false) String from,
			@RequestParam(value="effectiveto", required = false) String to,@RequestParam(value="district", required = false) String district,
			@RequestParam(value="mandal", required = false) String mandal,@RequestParam(value="village", required = false) String village,
			Model model, HttpServletRequest request) {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response;
		String url = "";
		List<Districts> districtList = new ArrayList<>();
		
		try {
			
			List<LmoStockCountBO> list = new ArrayList<>();
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
		
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "oltPortAllocation?tenantCode=" + tenantCode;
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			districtList = response.getBody();
			if (to != null) {
				
			  list = demandNoteServiceImpl.getDistrictMandalVillageWiseCafCount(from,to,district,mandal,village);
			}
			model.addAttribute("districtWiseCafList", list);
			model.addAttribute("effectiveto",to);
			model.addAttribute("effectivefrom",from);
			model.addAttribute("districtList",districtList);
			model.addAttribute("districthidden",district);
			model.addAttribute("mandalhidden",mandal);
			model.addAttribute("villagehidden",village);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "distMandalvillageWiseCafCount";
	}
	
	//district mandal village wise caf count till date
	@RequestMapping(value = "/distMandalvillageWiseCafCountTilldate", method={RequestMethod.POST,RequestMethod.GET})
	public String getDistrictMandalVillageWiseCafCountTilldate(Model model, HttpServletRequest request) {
		
		
		try {
			
			List<LmoStockCountBO> list = new ArrayList<>();
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			
			  list = demandNoteServiceImpl.getDistrictMandalVillageWiseCafCountTilldate();
			
			model.addAttribute("districtWiseCafList", list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "distMandalvillageWiseCafCounttilldate";
	}
	//download distMandalvillageWiseCafReport
	@RequestMapping(value = "/downloaddistMandalvillageWiseCafCount", method = RequestMethod.GET)
	public void downloadgetDistrictMandalVillageWiseCafCount(@RequestParam(value="effectivefrom", required = false) String from,
			@RequestParam(value="effectiveto", required = false) String to,@RequestParam(value="district", required = false) String district,
			@RequestParam(value="mandal", required = false) String mandal,@RequestParam(value="village", required = false) String village,
			@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadgetDistrictMandalVillageWiseCafCount(from, to, district, mandal, village);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=District Mandal and Village Wise Caf Count Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}	//download distMandalvillageWiseCafReport tilldate
	@RequestMapping(value = "/downloaddistMandalvillageWiseCafCountTilldate", method = RequestMethod.GET)
	public void downloadgetDistrictMandalVillageWiseCafCountTilldate(
			@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadgetgetDistrictMandalVillageWiseCafCountTilldate();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=District Mandal and Village Wise Caf Count till date Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/districtMandalVillageCustomerCafReport", method={RequestMethod.POST,RequestMethod.GET})
	public String districtMandalVillageCustomerCafReport(
			@RequestParam(value="effectivefrom", required = false) String from,
			@RequestParam(value="effectiveto", required = false) String to,			
			@RequestParam(value="cafDate", required = false) String cafDate,
			@RequestParam(value="district", required = false) String district,
			@RequestParam(value="mandal", required = false) String mandal,
			@RequestParam(value="village", required = false) String village,
			@RequestParam(value="districtName", required = false) String districtName,
			@RequestParam(value="mandalName", required = false) String mandalName,
			@RequestParam(value="villageName", required = false) String villageName,
			Model model,HttpServletRequest request) {
		try {
			List<CustomerDTO> recs = demandNoteServiceImpl.districtMandalVillageCustomerCafReport(cafDate,districtName,mandalName,villageName);
			model.addAttribute("districtWiseCafList", recs);
			model.addAttribute("cafDate",cafDate);
			model.addAttribute("effectiveto",to);
			model.addAttribute("effectivefrom",from);
			model.addAttribute("districthidden",district);
			model.addAttribute("mandalhidden",mandal);
			model.addAttribute("villagehidden",village);
			model.addAttribute("districtName",districtName);
			model.addAttribute("mandalName",mandalName);
			model.addAttribute("villageName",villageName);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
		}
		return "districtMandalVillageCustomerCafReport";
	}

	//download distMandalvillageWiseCustomerCafReport
	@RequestMapping(value = "/downloadgetdistrictMandalVillageCustomerCafReport", method = RequestMethod.GET)
	public void downloadgetdistrictMandalVillageCustomerCafReport(@RequestParam(value="cafDate", required = false) String cafDate,
			@RequestParam(value="districthidden", required = false) String district,
			@RequestParam(value="mandalhidden", required = false) String mandal,
			@RequestParam(value="villagehidden", required = false) String village,
			Model model,HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadgetdistrictMandalVillageCustomerCafReport(cafDate, district, mandal, village);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=District Mandal and Village Wise Customer Caf  Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	
	//District wise pon wise  Details report
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/districtWisePonWiseCafCount", method={RequestMethod.POST,RequestMethod.GET})
	public String getDistrictWisePonWiseCafCount(@RequestParam(value="effectivefrom", required = false) String effectivefrom,
			@RequestParam(value="effectiveto", required = false) String effectiveto,
			@RequestParam(value="lmocode", required = false) String lmocode, Model model, HttpServletRequest request) {
		
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		
		
		
		try {
			
			
			List<DistrictPonWiseCafCountBO> dstrictPonWiseCafCountList = demandNoteServiceImpl.getDistrictWisePonWiseCafCount(tenantcode, effectivefrom, effectiveto,lmocode);
			
			long totalcafcount=0;
			Integer lmocount=0;
			Integer portcount=0;
			Map<String, String> lmototalPrevbal= new HashMap<String, String>();
			
			
			 for (DistrictPonWiseCafCountBO lmostcnt : dstrictPonWiseCafCountList) {


				 totalcafcount =totalcafcount + Long.valueOf(lmostcnt.getCafCount());
				 
				 if(lmostcnt.getLmoCode() != null && !lmostcnt.getLmoCode().isEmpty()){
						    	
						 lmototalPrevbal.put(lmostcnt.getLmoCode(), lmostcnt.getLmoCode());
			   }
				 
				 if(lmostcnt.getPortNo() != null && !lmostcnt.getPortNo().isEmpty()){
				    	
					 portcount++;
		   }
				 
	     }
			 
			
			lmocount=lmototalPrevbal.keySet().size();
			
			model.addAttribute("districtPonWiseCafCountList", dstrictPonWiseCafCountList);
			model.addAttribute("effectivefrom", effectivefrom);
			model.addAttribute("effectiveto", effectiveto);
			model.addAttribute("lmocode", lmocode);
			model.addAttribute("totalcafcount", totalcafcount);
			model.addAttribute("lmocount", lmocount);
			model.addAttribute("portcount", portcount);
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tenantcode=null;
		}
		return "districtWisePonWiseCafCountreport";
	}
		
	
	//download dist wise pon wiseCafcount Report
	@RequestMapping(value = "/downloaddistrictWisePonWiseCafCount", method = RequestMethod.GET)
	public void downloaddistrictWisePonWiseCafCount(@RequestParam(value="effectivefrom", required = false) String effectivefrom,
			@RequestParam(value="effectiveto", required = false) String effectiveto,
			@RequestParam(value="lmocode", required = false) String lmocode, Model model,HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadgetDistrictWisePonWiseCafCountReport(tenantcode, effectivefrom, effectiveto,lmocode);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=District wise PON Wise Caf Count Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	
	//OLT Master Data Details
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/oltmastrdata", method={RequestMethod.POST,RequestMethod.GET})
	public String getOLTMasterData(Model model, HttpServletRequest request) {
		
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try {
			List<OLTMasterDataBO> oltMasterDataList = demandNoteServiceImpl.getOLTMasterData();
			model.addAttribute("oltMasterDataList", oltMasterDataList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "Oltmatserdatareport";
	}
		
	
	//download OLT Master Data Report
	@RequestMapping(value = "/downloadoltmasterdata", method = RequestMethod.GET)
	public void getOLTMasterDataDownload(Model model,HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getOLTMasterDataDownload();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=OLT Master Data Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	//Rigged CAFs Report
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/riggedcafreport", method={RequestMethod.POST,RequestMethod.GET})
	public String getRiggedCafsDetails(@RequestParam(value="districtuid", required=false) String district,
			@RequestParam(value="mandalslno", required=false) String mandal,
			@RequestParam(value="villageslno", required=false) String village,
			@RequestParam(value="effectivefrom", required=false) String from,
			@RequestParam(value="effectiveto", required=false) String to,
			Model model, HttpServletRequest request) {
		
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		
		HttpEntity<String> httpEntity;
		ResponseEntity<ArrayList> response1;
		String url = "";
		try {
			
			String tenantCode = (String) request.getSession(false).getAttribute("tenantcode");
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "oltPortAllocation?tenantCode=" + tenantCode;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ArrayList.class);
			List<Districts> districtsList = response1.getBody();
			model.addAttribute("districtList", districtsList);
			
			List<RiggedCafBO> riggedCafList = demandNoteServiceImpl.getRiggedCafsDetails(district, mandal, village, from, to);
			
			long totalcafcount=0;
			long totalRiggedCafCount=0;
			Integer lmocount=0;
			Set<String> lmototalPrevbal= new HashSet<String>();
			
			
			 for (RiggedCafBO lmostcnt : riggedCafList) {


				 totalcafcount =totalcafcount + Long.valueOf(lmostcnt.getTotalCafCount());
				 
				 totalRiggedCafCount =totalRiggedCafCount + Long.valueOf(lmostcnt.getNotConnectCafCount());
				 
				 if(lmostcnt.getLmoCode() != null && !lmostcnt.getLmoCode().isEmpty()){
						    	
						 lmototalPrevbal.add(lmostcnt.getLmoCode());
			   }
				 

		   }
				 
			 lmocount=lmototalPrevbal.size();
			
			model.addAttribute("riggedCafList", riggedCafList);
			model.addAttribute("districtuid", district);
			model.addAttribute("mandalslno", mandal);
			model.addAttribute("villageslno", village);
			model.addAttribute("effectivefrom", from);
			model.addAttribute("effectiveto", to);
			model.addAttribute("totalcafcount", totalcafcount);
			model.addAttribute("lmocount", lmocount);
			model.addAttribute("totalRiggedCafCount", totalRiggedCafCount);
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "riggedcafreport";
	}
	
	
	//download Rigged CAF details  Report
	@RequestMapping(value = "/downloadriggedcafreport", method = RequestMethod.GET)
	public void getRiggedCafsDetailsDownload(@RequestParam(value="district", required=false) String district,
			@RequestParam(value="mandal", required=false) String mandal,
			@RequestParam(value="village", required=false) String village,
			@RequestParam(value="from", required=false) String from,
			@RequestParam(value="to", required=false) String to,
			Model model,HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		String tenantcode = (String) request.getSession(false).getAttribute("tenantcode");
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.getRiggedCafsDetailsDownload(district, mandal, village, from, to);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Rigged Cafs Report.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}

	// Created By SaiSumanth
//Tenant Wallet Transaction
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/tenantWalletTrans", method = RequestMethod.GET)
	public String tenantWalletTrans(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month) throws IOException {
		
		
		

		List<Object[]> tenantlist = new ArrayList<>();
		List<TenantWalletTransDTO> tenantWalletTransDTOList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		StringBuilder builder1 = new StringBuilder();
		String message = null;
		

		try {
			logger.info("DemandNoteReportController :: tenantWalletTrans() :: Start");
				
				
                  
				
					builder = new StringBuilder("SELECT t.tenantcode,t.tenantname,t.regoff_pocname,t.regoff_pocmob1,d.districtname,m.mandalname,v.villagename,DATE_FORMAT(tw.trandate,'%Y-%m-%d') AS trandate,tw.tranrefno,tw.tranamt FROM tenants t");
					builder.append(" LEFT JOIN tenantswallettrans tw ON t.tenantcode=tw.tenantcode ");
					builder.append(" LEFT JOIN districts d ON d.districtuid=portal_districtid ");
					builder.append("LEFT JOIN mandals m ON m.mandalslno=t.portal_mandalid AND d.districtuid=m.districtuid ");
					builder.append("LEFT JOIN villages v ON v.villageslno=t.portal_villageid AND v.mandalslno=m.mandalslno AND v.districtuid=d.districtuid WHERE DATE_FORMAT(tw.trandate,'%Y') = '"+year+"' AND DATE_FORMAT(tw.trandate,'%m') = '"+month+"' ");
					
			
				
				
				tenantlist = demandNoteServiceImpl.tenantWalletTrans(builder.toString());
				logger.info("Query Size......"+tenantlist.size());
				for (Object[] obj : tenantlist) {

					TenantWalletTransDTO tenantWalletTransDTO = new TenantWalletTransDTO();
					tenantWalletTransDTO.setTenantcode(obj[0] == null ? "NA" : obj[0].toString());
					tenantWalletTransDTO.setTenantname(obj[1] == null ? "NA" : obj[1].toString());
					tenantWalletTransDTO.setRegoff_pocname(obj[2] == null ? "NA" : obj[2].toString());
					tenantWalletTransDTO.setRegoff_pocmob1(obj[3] == null ? "NA" : obj[3].toString());
					tenantWalletTransDTO.setDistrictname(obj[4] == null ? "NA" : obj[4].toString());
					tenantWalletTransDTO.setMandalname(obj[5] == null ? "NA" : obj[5].toString());
					tenantWalletTransDTO.setVillagename(obj[6] == null ? "NA" : obj[6].toString());
					tenantWalletTransDTO.setTrandate(obj[7] == null ? "NA" : obj[7].toString());
					tenantWalletTransDTO.setTranrefno(obj[8] == null ? "NA" : obj[8].toString());
					tenantWalletTransDTO.setTranamt(obj[9] == null || obj[9] == "" ? (double) obj[9] : Double.parseDouble(obj[9].toString()));
					
			
					tenantWalletTransDTOList.add(tenantWalletTransDTO);
				}

				if (tenantWalletTransDTOList.isEmpty()) {
					logger.info("empty list...."+tenantWalletTransDTOList);
					message = "No Records Found For This Search Criteria";
					logger.info("message...."+message);
					
				}
		} catch (Exception ex) {
			logger.info("DemandNoteReportController :: tenantWalletTrans() :: " + ex);
			ex.printStackTrace();
		} finally {
			builder = null;
			builder1 = null;
		}
	   uiModel.addAttribute("tenantWalletTransDTOList", tenantWalletTransDTOList);
		
		uiModel.addAttribute("year", year);
		uiModel.addAttribute("month", month);
		uiModel.addAttribute("message", message);
		
		
		
logger.info("year......"+year);
logger.info("month......"+month);
	
		
		return "tms.tenantWalletTrans";
	}
	
	// Created By SaiSumanth 
	// tenantWalletTrans Report download 
	@SuppressWarnings("unused")
	@RequestMapping(value = "/downloadtenantWalletTrans", method = RequestMethod.GET)
	public void downloadtenantWalletTrans(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month) throws IOException, ParseException {

		 

			List<Object[]> tenantlist = new ArrayList<>();
			List<TenantWalletTransDTO> tenantWalletTransDTOList = new ArrayList<>();
			StringBuilder builder = new StringBuilder();
			StringBuilder builder1 = new StringBuilder();
			String message = null;
		

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Tenant Wallet Transactions");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		

		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				
				logger.info("DemandNoteReportController :: downloadtenantWalletTrans() :: Start");
				
		
				builder = new StringBuilder("SELECT t.tenantcode,t.tenantname,t.regoff_pocname,t.regoff_pocmob1,d.districtname,m.mandalname,v.villagename,DATE_FORMAT(tw.trandate,'%Y-%m-%d') AS trandate,tw.tranrefno,tw.tranamt FROM tenants t");
				builder.append(" LEFT JOIN tenantswallettrans tw ON t.tenantcode=tw.tenantcode ");
				builder.append(" LEFT JOIN districts d ON d.districtuid=portal_districtid ");
				builder.append("LEFT JOIN mandals m ON m.mandalslno=t.portal_mandalid AND d.districtuid=m.districtuid ");
				builder.append("LEFT JOIN villages v ON v.villageslno=t.portal_villageid AND v.mandalslno=m.mandalslno AND v.districtuid=d.districtuid WHERE DATE_FORMAT(tw.trandate,'%Y') = '"+year+"' AND DATE_FORMAT(tw.trandate,'%m') = '"+month+"' ");
				
		
				tenantlist = demandNoteServiceImpl.tenantWalletTrans(builder.toString());
				logger.info("Query Size......"+tenantlist.size());
				
				for (Object[] obj : tenantlist) {

					TenantWalletTransDTO tenantWalletTransDTO = new TenantWalletTransDTO();
					tenantWalletTransDTO.setTenantcode(obj[0] == null ? "NA" : obj[0].toString());
					tenantWalletTransDTO.setTenantname(obj[1] == null ? "NA" : obj[1].toString());
					tenantWalletTransDTO.setRegoff_pocname(obj[2] == null ? "NA" : obj[2].toString());
					tenantWalletTransDTO.setRegoff_pocmob1(obj[3] == null ? "NA" : obj[3].toString());
					tenantWalletTransDTO.setDistrictname(obj[4] == null ? "NA" : obj[4].toString());
					tenantWalletTransDTO.setMandalname(obj[5] == null ? "NA" : obj[5].toString());
					tenantWalletTransDTO.setVillagename(obj[6] == null ? "NA" : obj[6].toString());
					tenantWalletTransDTO.setTrandate(obj[7] == null ? "NA" : obj[7].toString());
					tenantWalletTransDTO.setTranrefno(obj[8] == null ? "NA" : obj[8].toString());
					tenantWalletTransDTO.setTranamt(obj[9] == null || obj[9] == "" ? (double) obj[9] : Double.parseDouble(obj[9].toString()));
					
			
					tenantWalletTransDTOList.add(tenantWalletTransDTO);
				}

				if (tenantWalletTransDTOList == null) {
					logger.info("empty list...."+tenantWalletTransDTOList);
					message = "No Records Found For This Search Criteria";
					logger.info("message...."+message);
					
				}

				byte[] bytes = IOUtils.toByteArray(my_banner_image);
				int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				my_banner_image.close();
				HSSFPatriarch drawing = sheet.createDrawingPatriarch();
				ClientAnchor my_anchor = new HSSFClientAnchor();
				my_anchor.setDx1(0);
				my_anchor.setDy1(0);
				my_anchor.setDx2(0);
				my_anchor.setDy2(0);
				my_anchor.setCol1(0);
				my_anchor.setRow1(0);
				my_anchor.setCol2(1);
				my_anchor.setRow2(1);
				HSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
				my_picture.resize();

				CellStyle style = workbook.createCellStyle();
				style.setAlignment(style.ALIGN_CENTER);
				sheet.setDefaultColumnWidth(30);
				Font font = workbook.createFont();
				font.setFontName("Arial");
				style.setFillForegroundColor(HSSFColor.WHITE.index);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setColor(HSSFColor.BLACK.index);
				style.setFont(font);

				// For creating first row
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				// For creating second row
				cell = row1.createCell(2);
				cell.setCellValue("Tenant Wallet Transactions");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				// For creating third row
				cell = row2.createCell(0);
				cell.setCellValue("year");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue(year);
				cell.setCellStyle(style);

				cell = row2.createCell(2);
				cell.setCellValue("month");
				cell.setCellStyle(style);

				cell = row2.createCell(3);
				cell.setCellValue(month);
				cell.setCellStyle(style);

				
				
				header.createCell(0).setCellValue("S No.");
				header.getCell(0).setCellStyle(style);
				
				header.createCell(1).setCellValue("LMO Code");
				header.getCell(1).setCellStyle(style);
				
				header.createCell(2).setCellValue("NetWork Name");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("POC Name");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("POC Mobile no.");
				header.getCell(4).setCellStyle(style);
				
				header.createCell(5).setCellValue("District");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("Mandal");
				header.getCell(6).setCellStyle(style);

				header.createCell(7).setCellValue("Village");
				header.getCell(7).setCellStyle(style);
				
				header.createCell(8).setCellValue("Transaction Date");
				header.getCell(8).setCellStyle(style);
				
				header.createCell(9).setCellValue("Transaction RefNo.");
				header.getCell(9).setCellStyle(style);
				
				header.createCell(10).setCellValue("Transaction Amount");
				header.getCell(10).setCellStyle(style);

				// create data rows
				int rowCount = 3;
				int totalcount=0;
				for (TenantWalletTransDTO obj : tenantWalletTransDTOList) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(++totalcount);
					aRow.createCell(1).setCellValue(obj.getTenantcode() == null ? "NA" : obj.getTenantcode());
					aRow.createCell(2).setCellValue(obj.getTenantname() == null ? "NA" : obj.getTenantname());
					aRow.createCell(3).setCellValue(obj.getRegoff_pocname() == null ? "NA" : obj.getRegoff_pocname());
					aRow.createCell(4).setCellValue(obj.getRegoff_pocmob1() == null  ? "NA" : obj.getRegoff_pocmob1());
					aRow.createCell(5).setCellValue(obj.getDistrictname() == null ? "NA" : obj.getDistrictname());
					aRow.createCell(6).setCellValue(obj.getMandalname() == null ? "NA" : obj.getMandalname());
					aRow.createCell(7).setCellValue(obj.getVillagename() == null ? "NA" : obj.getVillagename());
					aRow.createCell(8).setCellValue(obj.getTrandate() == null ? "NA" : obj.getTrandate());
					aRow.createCell(9).setCellValue(obj.getTranrefno() == null ? "NA" : obj.getTranrefno());
					aRow.createCell(10).setCellValue(obj.getTranamt());
				}
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=TenantWalletTransDetails.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				builder = null;
				builder1 = null;
			}
		}
	}

	
/*//Pon report report for incentive
	
	@RequestMapping(value = "/ponReportForIncentives", method = {RequestMethod.POST,RequestMethod.GET})
	public String getPonReportForIncentives(@RequestParam(value="effectivefrom", required = false) String from,
			@RequestParam(value="effectiveto", required = false) String to,
			@RequestParam(value="districtuid", required = false) String district,
			@RequestParam(value="mandalslno", required = false) String mandal,
			@RequestParam(value = "lmocode", required = false) String lmocode,HttpServletRequest request, 
			 Model uiModel) throws IOException {
		
		HttpEntity<String> httpEntity;
		ResponseEntity<TmsHelperDTO> response1;
		String url = "";

		try {
			
			TmsHelperDTO tmsHelperDTO = new TmsHelperDTO();
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getTmsUserName(), ipAddressValues.getTmsPwd());
			url = ipAddressValues.getTmsURL() + "getDistmandalDetails?districtuid=" + district + "&mandalslno="
					+ mandal;
			response1 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TmsHelperDTO.class);
			tmsHelperDTO = response1.getBody();
			
			List<PONWiseBo> ponWithCAFList = new ArrayList<PONWiseBo>();
			ponWithCAFList = demandNoteServiceImpl.getPonReportForIncentives(district, mandal, from, to, lmocode);
			
			long popcount=0;
			Integer lmocount=0;
			long cafCount=0;
			Integer portCount=0;
			
			Set<String> lmomap= new HashSet<String>();
			Set<String> popmap= new HashSet<String>();
			
			
			 for (PONWiseBo lmostotalcnt : ponWithCAFList ) {
				 
				 if(lmostotalcnt.getLmocode() != null && !lmostotalcnt.getLmocode().isEmpty()){
						    	
					 lmomap.add(lmostotalcnt.getLmocode());
			   }
				 
					 
					 if(lmostotalcnt.getPop_name() != null && !lmostotalcnt.getPop_name().isEmpty()){
							    	
						 popmap.add(lmostotalcnt.getPop_name());
				   }
					 if(lmostotalcnt.getPortno() != null && !lmostotalcnt.getPortno().isEmpty()){
					    	
						 portCount++;
				   }
					 if(lmostotalcnt.getCafno() != null){
					    	
						 cafCount = cafCount + lmostotalcnt.getCafno();
				   }
	     }
			lmocount=lmomap.size();
			popcount=popmap.size();
			
			uiModel.addAttribute("mandalList", tmsHelperDTO.getMandalsList());
			uiModel.addAttribute("districtList", tmsHelperDTO.getDistrictsList());
			uiModel.addAttribute("districtuid", district);
			uiModel.addAttribute("mandalslno", mandal);

			uiModel.addAttribute("from", from);
			uiModel.addAttribute("to", to);
			uiModel.addAttribute("lmocode", lmocode);
			uiModel.addAttribute("ponWithCAFList", ponWithCAFList);
			uiModel.addAttribute("lmocount", lmocount);
			uiModel.addAttribute("popcount", popcount);
			uiModel.addAttribute("cafCount", cafCount);
			uiModel.addAttribute("portCount", portCount);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			

		}

		return "ponReportForIncentive";
	}
	
	@RequestMapping(value = "/downloadPonReportForIncentive", method = RequestMethod.GET)
	public void downloadPonReportForIncentive(@RequestParam(value="district", required = false) String district, 
			@RequestParam(value = "mandal", required = false) String mandal,
			@RequestParam(value="from", required = false) String from, 
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value ="lmocode", required= false) String lmocode,
			@RequestParam(value = "download", required = false) boolean download,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = demandNoteServiceImpl.downloadPONReportForIncentivesExcel(district, mandal, from, to, lmocode, request, response);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=ponReportForIncentives.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}*/
		
}
