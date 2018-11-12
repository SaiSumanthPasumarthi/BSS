package com.arbiva.apsfl.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.arbiva.apsfl.coms.dto.CafsForBlockListVO;
import com.arbiva.apsfl.coms.dto.Districts;
import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.coms.dto.PaymentVO;
import com.arbiva.apsfl.reports.vo.AgeingReportVO;
import com.arbiva.apsfl.reports.vo.CafDatesVO;
import com.arbiva.apsfl.reports.vo.ChnlsOnAlacarteVO;
import com.arbiva.apsfl.reports.vo.CpeOrderVO;
import com.arbiva.apsfl.reports.vo.OsdFingPrntVO;
import com.arbiva.apsfl.reports.vo.PackWiseChnlsVO;
import com.arbiva.apsfl.reports.vo.PackWiseSubsVO;
import com.arbiva.apsfl.reports.vo.ReportsDTO;
import com.arbiva.apsfl.reports.vo.ReportsVO;
import com.arbiva.apsfl.tms.daoImpl.TenantDaoImpl;
import com.arbiva.apsfl.tms.dto.CpeReportDTO;
import com.arbiva.apsfl.tms.dto.CpeStockVO;
import com.arbiva.apsfl.reports.vo.SrvsWiseSubsVO;
import com.arbiva.apsfl.reports.vo.StatusWiseSubsVO;
import com.arbiva.apsfl.reports.vo.SubsDtlsVO;
import com.arbiva.apsfl.reports.vo.TransactionLogVO;
import com.arbiva.apsfl.tms.daoImpl.TenantDaoImpl;
import com.arbiva.apsfl.tms.serviceImpl.ReportsServiceImpl;
import com.arbiva.apsfl.util.ApsflHelper;
import com.arbiva.apsfl.util.CafEnumCodes;
import com.arbiva.apsfl.util.FileUtil;
import com.arbiva.apsfl.util.IpAddressValues;
import com.arbiva.apsfl.util.PaginationObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;

@SuppressWarnings({ "static-access", "deprecation" })
@Controller
public class ReportsController {

	private static final Logger logger = Logger.getLogger(ReportsController.class);
	
	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	ReportsServiceImpl reportsServiceImpl;
	
	@Autowired
	TenantDaoImpl tenantDaoImpl;
	
	@Autowired
	IpAddressValues ipAddressValues;

	DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	/* Ageing Report */
	@RequestMapping(value = "/getAgeingReportByActivation", method = RequestMethod.GET)
	public String getBasedOnActivationDate(HttpServletRequest request, HttpServletResponse response, Model uiModel) throws IOException {
		return "ageingReport";
	}
	
	@RequestMapping(value = "/getAgeingReportByActivationPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<AgeingReportVO> getBasedOnActivationDatePagination(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			                                         @RequestParam(value = "date", required = false) String date) throws IOException {

		PaginationObject<AgeingReportVO> dataPageObject = new PaginationObject<AgeingReportVO>();
		ReportsDTO reportDto = new ReportsDTO();

		try {
			logger.info("ReportsController :: getAgeingReportByActivationPagination :: Start");
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
			sortColumn = AgeingReportVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			if (date != null) {
				
				Date currentDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
				String newDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);

				reportDto = reportsServiceImpl.getAgeingReport(newDate,pageObject);

				dataPageObject.setAaData(reportDto.getAgeingList());
				dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
			}
			logger.info("ReportsController :: getAgeingReportByActivationPagination :: End");
		} catch (Exception ex) {
			logger.error("ReportsController :: getAgeingReportByActivationPagination :: " + ex);
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;
	}
	
	/* Ageing Report Download */
	@RequestMapping(value = "/ageingReportDownload", method = RequestMethod.GET)
	public void ageingReportDownload(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date", required = false) String date) throws IOException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();


		if (download && date != null) {
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Ageing Details");
			Row header = sheet.createRow(4);
			HSSFRow aRow = sheet.createRow(5);
			HSSFRow row = sheet.createRow(0);// Title
			HSSFRow row1 = sheet.createRow(1);// Report name
			HSSFRow row2 = sheet.createRow(2);// Dates

			Calendar cal = Calendar.getInstance();
			String currDate = sdf.format(cal.getTime());
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {

				Date currentDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
				String newDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);

				builder.append("SELECT DISTINCT(cfs.parentcafno),cf.custcode AadharNo,cfs.stbmacaddr,cfs.nwsubscode SubsCode,cf.custid, cs.srvccode package, cs.actdate, ");
				builder.append(" 'Active' AS STATUS, DATEDIFF('"+newDate+"', cs.actdate)+1 Ageing ");
				builder.append(" FROM cafstbs cfs,cafsrvcs cs,srvcs s,cafs cf WHERE cfs.parentcafno = cs.parentcafno AND cfs.stbcafno = cs.stbcafno ");
				builder.append(" AND s.srvccode = cs.srvccode AND s.coresrvccode = 'IPTV' AND cf.cafno = cfs.parentcafno and cf.cafno = cs.parentcafno ");
				builder.append(" AND cs.status = "+CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus()+" AND CURRENT_DATE()  BETWEEN s.effectivefrom AND s.effectiveto AND cs.actdate <= '"+newDate+"' ");

				list = reportsServiceImpl.getListByQuery(builder.toString());
				
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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("Ageing Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				/* For creating third row */
				cell = row2.createCell(0);
				cell.setCellValue("Till Date");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue(date);
				cell.setCellStyle(style);

				cell = row2.createCell(2);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(3);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("CAF No");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Aadhar No");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("STB MAC-Address");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("Subscriber ID");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("Customer ID");
				header.getCell(4).setCellStyle(style);

				header.createCell(5).setCellValue("IPTV Package");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("Activation Date");
				header.getCell(6).setCellStyle(style);

				header.createCell(7).setCellValue("Status");
				header.getCell(7).setCellStyle(style);

				header.createCell(8).setCellValue("Ageing");
				header.getCell(8).setCellStyle(style);

				// create data rows
				int rowCount = 5;

				for (Object[] obj : list) {
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue(obj[0] == null || obj[0].toString() == "" ? "NA" : obj[0].toString());
					aRow.createCell(1).setCellValue(obj[1] == null || obj[1].toString() == "" ? "NA" : obj[1].toString());
					aRow.createCell(2).setCellValue(obj[2] == null || obj[2].toString() == "" ? "NA" : obj[2].toString());
					aRow.createCell(3).setCellValue(obj[3] == null || obj[3].toString() == "" ? "NA" : obj[3].toString());
					aRow.createCell(4).setCellValue(obj[4] == null || obj[4].toString() == "" ? "NA" : obj[4].toString());
					aRow.createCell(5).setCellValue(obj[5] == null || obj[5].toString() == "" ? "NA" : obj[5].toString());
					aRow.createCell(6).setCellValue(obj[6] == null || obj[6].toString() == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[6].toString()));
					aRow.createCell(7).setCellValue(obj[7] == null || obj[7].toString() == "" ? "NA" : obj[7].toString());
					aRow.createCell(8).setCellValue(obj[8] == null || obj[8].toString() == "" ? "NA" : obj[8].toString());
				}

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=AgeingReport.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
				list = null;
			}
		}
	}

	/* Package Wise Subscribers List */
	@RequestMapping(value = "/getServiceWiseSubscribers", method = RequestMethod.GET)
	public String getServiceWiseSubscribers(HttpServletRequest request, HttpServletResponse response) {
		return "srvcsWiseSubs";
	}
	
	/* Package Wise Subscribers List */
	@RequestMapping(value = "/getServiceWiseSubscribersPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<SrvsWiseSubsVO> getServiceWiseSubscribersPagination(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "date", required = false) String date) throws IOException {

			PaginationObject<SrvsWiseSubsVO> dataPageObject = new PaginationObject<SrvsWiseSubsVO>();
			ReportsDTO reportDto = new ReportsDTO();

			try {
				logger.info("ReportsController :: getServiceWiseSubscribersPagination :: Start");
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
				sortColumn = SrvsWiseSubsVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
				
				if (date != null) {
					
					Date currentDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
					String newDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);

					reportDto = reportsServiceImpl.getSrvcWiseSubsReport(newDate,pageObject);

					dataPageObject.setAaData(reportDto.getSubsList());
					dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
				}
				logger.info("ReportsController :: getServiceWiseSubscribersPagination :: End");
			} catch (Exception ex) {
				logger.error("ReportsController :: getServiceWiseSubscribersPagination :: " + ex);
				ex.printStackTrace();
			} finally {
			}

			return dataPageObject;
	}
	
	/* Package Wise Subscribers Report Download  */
	@RequestMapping(value = "/downloadServiceWiseSubscribers", method = RequestMethod.GET)
	public void downloadServiceWiseSubscribers(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date", required = false) String date) throws IOException, ParseException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Service Wise Details");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		if (download && date != null) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				Date currentDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
				String newDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);

				builder = new StringBuilder("SELECT DISTINCT (a.srvccode), COUNT(1) Total_subs FROM cafsrvcs a, srvcs b ");
				builder.append(" WHERE a.srvccode=b.srvccode AND b.coresrvccode='IPTV' and a.actdate <= '" + newDate+ "' AND current_date() BETWEEN b.effectivefrom AND b.effectiveto and a.status = "+CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus()+" GROUP BY (a.srvccode) ");

				list = reportsServiceImpl.getListByQuery(builder.toString());

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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("Service Wise Subscribers Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				/* For creating third row */
				cell = row2.createCell(0);
				cell.setCellValue("Till Date");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue(date);
				cell.setCellStyle(style);

				cell = row2.createCell(2);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(3);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("Channel/IPTV Package Name");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Total");
				header.getCell(1).setCellStyle(style);

				// create data rows
				int rowCount = 5;

				for (Object[] obj : list) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(obj[0] == null || obj[0].toString() == "" ? "NA" : obj[0].toString());
					aRow.createCell(1).setCellValue(obj[1] == null || obj[1].toString() == "" ? "NA" : obj[1].toString());
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=ServiceWiseSubReport.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
		}
	}
	
	/* Subscriber Details */
	@RequestMapping(value = "/getSubscriberDetails", method = RequestMethod.GET)
	public String getSubscriberDetails(HttpServletRequest request, HttpServletResponse response, Model uiModel) throws IOException {
		return "subsDtls";
	}
	
	/* Subscriber Details */
	@RequestMapping(value = "/getSubscriberDetailsPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<SubsDtlsVO> getSubscriberDetailsPagination(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "radio", required = false) String radio) throws IOException {


		PaginationObject<SubsDtlsVO> dataPageObject = new PaginationObject<SubsDtlsVO>();
		ReportsDTO reportDto = new ReportsDTO();
		String fromDate = "";
		String toDate = "";

		try {
			logger.info("ReportsController :: getSubscriberDetailsPagination :: Start");
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
			sortColumn = SubsDtlsVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			if (radio.equals("dateRange")) {
				if (date != null && date != null) {
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
				}
			} else {
				logger.info("ReportsController :: getSubscriberDetails() :: Else");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				toDate = (dateFormat.format(new Date()));
				logger.info("todate =" + toDate);
				fromDate = getTwoYearsOldDate();
				logger.info("fromdate =" + fromDate);
			}
			if (fromDate != null && toDate != null) {


				reportDto = reportsServiceImpl.getSubsDtlsReport(fromDate,toDate,pageObject);

			}

				dataPageObject.setAaData(reportDto.getSubsDtlsList());
				dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
				
			logger.info("ReportsController :: getSubscriberDetailsPagination :: End");
		} catch (Exception ex) {
			logger.error("ReportsController :: getSubscriberDetailsPagination :: " + ex);
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;
	}
	
	/* Subscriber Details Download */
	@RequestMapping(value = "/downloadSubscriberDetails", method = RequestMethod.GET)
	public void downloadSubscriberDetails(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "radio", required = false) String radio) throws IOException, ParseException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Subsciber Details");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		String fromDate = "";
		String toDate = "";
			
		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				
				if (radio.equals("dateRange")) {
					if (date != null && date != null) {
						Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
						Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
						fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
						toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
					}
				} else {
					logger.info("ReportsController :: getSubscriberDetails() :: Else");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					toDate = (dateFormat.format(new Date()));
					logger.info("todate =" + toDate);
					fromDate = getTwoYearsOldDate();
					logger.info("fromdate =" + fromDate);
				}
				if (fromDate != null && toDate != null) {
					builder = new StringBuilder("SELECT cfs.stbmacaddr ,cfs.parentcafno cafno,cs.prodcode package,cfs.nwsubscode Corpusnetworkid, ");
					builder.append(" DATE_FORMAT(IFNULL(cs.actdate,''),'%d-%m-%Y') ActivationDate,  DATE_FORMAT(IFNULL(cs.deactdate,''),'%d-%m-%Y') DeactivationDate, DATE_FORMAT(IFNULL(cs.suspdate,''),'%d-%m-%Y') Suspendeddate,  ");
					builder.append(" DATE_FORMAT(IFNULL(cs.resumedate,''),'%d-%m-%y') ResumeDate,s.srvcname IPTVAddlService,s.featurecodes Channellist,cs.parentcafno,cf.custid,cf.aadharno, ");
					builder.append(" cf.cpemacaddr,cf.inst_addr1,cf.inst_addr2,cf.inst_area, v.villagename,cf.inst_locality, ");
					builder.append(" m.mandalname,d.districtname,cf.inst_pin,cf.lattitude,cf.longitude, CASE WHEN cs.status = "+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + " ");
					builder.append(" then 'Active' when cs.status = " + CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus()+ " then 'Suspend' ");
					builder.append(" when cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus()+ " then 'Deactive'  when cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+ " then 'Black Listed' end status, ");
					builder.append(" CASE WHEN cs.status=" + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+ " THEN (SELECT DATE_FORMAT(IFNULL(bl.approvedon,''),'%d-%m-%Y') FROM blackliststb bl WHERE cfs.stbcafno = bl.stbcafno and bl.status = 1) ELSE '' END BlackListDate ");
					builder.append(" FROM cafstbs cfs,cafsrvcs cs,cafs cf,srvcs s,districts d, mandals m,villages v ");
					builder.append(" WHERE cfs.stbcafno = cs.stbcafno AND cfs.parentcafno = cs.parentcafno AND cf.cafno = cfs.parentcafno AND s.srvccode = cs.srvccode ");
					builder.append(" AND s.coresrvccode = 'IPTV' AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' AND cf.inst_mandal = m.mandalslno ");
					builder.append(" AND cf.inst_district = m.districtuid AND cf.inst_district = d.districtuid and cf.cafno = cs.parentcafno ");
					builder.append(" AND cf.inst_district = v.districtuid AND cf.inst_mandal = v.mandalslno AND cf.inst_city_village = v.villageslno ");
					builder.append(" AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto");
					list = reportsServiceImpl.getListByQuery(builder.toString());
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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("Subscibers Details Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				if (radio.equals("dateRange")) {

					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);
					toDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate1);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);

				} else {

					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					toDate = (dateFormat.format(new Date()));
					String tempDate = getTwoYearsOldDate();
					Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);
				}

				cell = row2.createCell(4);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("STB MAC-ID");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("CAF No");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("Package");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("Subscriber Code");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("Activation Date");
				header.getCell(4).setCellStyle(style);

				header.createCell(5).setCellValue("Deactivation Date");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("Suspended Date");
				header.getCell(6).setCellStyle(style);

				header.createCell(7).setCellValue("Resume Date");
				header.getCell(7).setCellStyle(style);

				header.createCell(8).setCellValue("Blacklist Date");
				header.getCell(8).setCellStyle(style);

				header.createCell(9).setCellValue("IPTV Package Name");
				header.getCell(9).setCellStyle(style);

				/*header.createCell(10).setCellValue("Channels List");
				header.getCell(10).setCellStyle(style);*/

				header.createCell(10).setCellValue("Customer ID");
				header.getCell(10).setCellStyle(style);

				header.createCell(11).setCellValue("Aadhar No");
				header.getCell(11).setCellStyle(style);

				header.createCell(12).setCellValue("ONU MAC ID");
				header.getCell(12).setCellStyle(style);

				header.createCell(13).setCellValue("Installation Address 1");
				header.getCell(13).setCellStyle(style);

				header.createCell(14).setCellValue("Installation Address 2");
				header.getCell(14).setCellStyle(style);

				header.createCell(15).setCellValue("Installation Area");
				header.getCell(15).setCellStyle(style);

				header.createCell(16).setCellValue("Installation City/Village");
				header.getCell(16).setCellStyle(style);

				header.createCell(17).setCellValue("Installation Locality");
				header.getCell(17).setCellStyle(style);

				header.createCell(18).setCellValue("Installation Mandal");
				header.getCell(18).setCellStyle(style);

				header.createCell(19).setCellValue("Installation District");
				header.getCell(19).setCellStyle(style);

				header.createCell(20).setCellValue("Installation Pin");
				header.getCell(20).setCellStyle(style);

				header.createCell(21).setCellValue("Latitude");
				header.getCell(21).setCellStyle(style);

				header.createCell(22).setCellValue("Longitude");
				header.getCell(22).setCellStyle(style);

				header.createCell(23).setCellValue("Status");
				header.getCell(23).setCellStyle(style);

				// create data rows
				int rowCount = 5;

				for (Object[] obj : list) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(obj[0] == null || obj[0].toString() == "" ? "NA" : obj[0].toString());
					aRow.createCell(1).setCellValue(obj[1] == null || obj[1].toString() == "" ? "NA" : obj[1].toString());
					aRow.createCell(2).setCellValue(obj[2] == null || obj[2].toString() == "" ? "NA" : obj[2].toString());
					aRow.createCell(3).setCellValue(obj[3] == null || obj[3].toString() == "" ? "NA" : obj[3].toString());
					aRow.createCell(4).setCellValue(obj[4] == null || obj[4].toString() == "" ? "NA" : obj[4].toString());
					aRow.createCell(5).setCellValue(obj[5] == null || obj[5].toString() == "" ? "NA" : obj[5].toString());
					aRow.createCell(6).setCellValue(obj[6] == null || obj[6].toString() == "" ? "NA" : obj[6].toString());
					aRow.createCell(7).setCellValue(obj[7] == null || obj[7].toString() == "" ? "NA" : obj[7].toString());
					aRow.createCell(8).setCellValue(obj[25] == null || obj[25].toString() == "" ? "NA" : obj[25].toString());
					aRow.createCell(9).setCellValue(obj[8] == null || obj[8].toString() == "" ? "NA" : obj[8].toString());
					/*aRow.createCell(10).setCellValue(obj[9] == null || obj[9].toString() == "" ? "NA" : obj[9].toString());*/
					aRow.createCell(10).setCellValue(obj[11] == null || obj[11].toString() == "" ? "NA" : obj[11].toString());
					aRow.createCell(11).setCellValue(obj[12] == null || obj[12].toString() == "" ? "NA" : obj[12].toString());
					aRow.createCell(12).setCellValue(obj[13] == null || obj[13].toString() == "" ? "NA" : obj[13].toString());
					aRow.createCell(13).setCellValue(obj[14] == null || obj[14].toString() == "" ? "NA" : obj[14].toString());
					aRow.createCell(14).setCellValue(obj[15] == null || obj[15].toString() == "" ? "NA" : obj[15].toString());
					aRow.createCell(15).setCellValue(obj[16] == null || obj[16].toString() == "" ? "NA" : obj[16].toString());
					aRow.createCell(16).setCellValue(obj[17] == null || obj[17].toString() == "" ? "NA" : obj[17].toString());
					aRow.createCell(17).setCellValue(obj[18] == null || obj[18].toString() == "" ? "NA" : obj[18].toString());
					aRow.createCell(18).setCellValue(obj[19] == null || obj[19].toString() == "" ? "NA" : obj[19].toString());
					aRow.createCell(19).setCellValue(obj[20] == null || obj[20].toString() == "" ? "NA" : obj[20].toString());
					aRow.createCell(20).setCellValue(obj[21] == null || obj[21].toString() == "" ? "NA" : obj[21].toString());
					aRow.createCell(21).setCellValue(obj[22] == null || obj[22].toString() == "" ? "NA" : obj[22].toString());
					aRow.createCell(22).setCellValue(obj[23] == null || obj[23].toString() == "" ? "NA" : obj[23].toString());
					aRow.createCell(23).setCellValue(obj[24] == null || obj[24].toString() == "" ? "NA" : obj[24].toString());
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=SubscriberDtlsReport.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (Exception ex) {
				logger.error("ReportsController :: getSubscriberDetails :: " + ex);
				ex.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
	}
			}
			
	
	/* Package Wise Channels */
	@RequestMapping(value = "/getPackageWiseChannels", method = RequestMethod.GET)
	public String getPackageWiseChannels(HttpServletRequest request, HttpServletResponse responsee) throws IOException {
		return "packWiseChnls";
	}
	
	@RequestMapping(value = "/getPackageWiseChannelsPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<PackWiseChnlsVO> getPackageWiseChannelsPagination(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date", required = false) String date) throws IOException {
		PaginationObject<PackWiseChnlsVO> dataPageObject = new PaginationObject<PackWiseChnlsVO>();
		ReportsDTO reportDto = new ReportsDTO();

		try {
			logger.info("ReportsController :: getPackageWiseChannelsPagination :: Start");
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
			sortColumn = PackWiseChnlsVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			if (date != null) {
				
				Date currentDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
				String newDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);

				reportDto = reportsServiceImpl.getPackWiseChnls(newDate,pageObject);

				dataPageObject.setAaData(reportDto.getChnlList());
				dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
			}
			logger.info("ReportsController :: getPackageWiseChannelsPagination :: End");
		} catch (Exception ex) {
			logger.error("ReportsController :: getPackageWiseChannelsPagination :: " + ex);
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;
	}
	
	/* Package Wise Channels Download */
	@RequestMapping(value = "/downloadPackageWiseChannels", method = RequestMethod.GET)
	public void downloadPackageWiseChannels(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date", required = false) String date) throws IOException, ParseException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Package Details");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		String fromDate = "";
		Date newDate = null;
		
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		if (download && date != null) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
				fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
				
				builder = new StringBuilder("SELECT b.srvccode,b. srvcname, c.featurecode, c.featurename FROM srvcs b, srvcfeatures c ");
				builder.append(" WHERE b.coresrvccode='IPTV' AND c.coresrvccode='IPTV' AND '"+fromDate+"' BETWEEN b.effectivefrom  AND b.effectiveto ");
				builder.append(" AND INSTR(CONCAT(',',b.featurecodes,','), CONCAT(',',c.featurecode,',')) > 0 ORDER BY 1,3 ");

				list = reportsServiceImpl.getListByQuery(builder.toString());

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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("Package Wise Channels Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				/* For creating third row */
				cell = row2.createCell(0);
				cell.setCellValue("Till Date");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue(date);
				cell.setCellStyle(style);

				cell = row2.createCell(2);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(3);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("Channel/IPTV Package Code");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Channel/IPTV Package Name");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("Channel Code");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("Channel Name");
				header.getCell(3).setCellStyle(style);
				// create data rows
				int rowCount = 5;

				for (Object[] obj : list) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(obj[0] == null || obj[0].toString() == "" ? "NA" : obj[0].toString());
					aRow.createCell(1).setCellValue(obj[1] == null || obj[1].toString() == "" ? "NA" : obj[1].toString());
					aRow.createCell(2).setCellValue(obj[2] == null || obj[2].toString() == "" ? "NA" : obj[2].toString());
					aRow.createCell(3).setCellValue(obj[3] == null || obj[3].toString() == "" ? "NA" : obj[3].toString());
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=PackageWiseChannelReport.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
				fromDate = null;
				newDate = null;
			}
		}
	}
	
	/* Transaction Log */
	@RequestMapping(value = "/getTransactionLog", method = RequestMethod.GET)
	public String getTransactionLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "transactionLog";
	}
	
	@RequestMapping(value = "/getTransactionLogPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<TransactionLogVO> getTransactionLogPagination(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "radio", required = false) String radio) throws IOException {

		PaginationObject<TransactionLogVO> dataPageObject = new PaginationObject<TransactionLogVO>();
		ReportsDTO reportDto = new ReportsDTO();
		String fromDate = "";
		String toDate = "";

		try {
			logger.info("ReportsController :: getTransactionLogPagination() :: Start");
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
			sortColumn = TransactionLogVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			if (radio.equals("dateRange")) {
				if (date != null && date != null) {
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
				}
			} else {
				logger.info("ReportsController :: getTransactionLogPagination() :: Else");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				toDate = (dateFormat.format(new Date()));
				logger.info("todate =" + toDate);
				fromDate = getTwoYearsOldDate();
				logger.info("fromdate =" + fromDate);
			}
			if (fromDate != null && toDate != null) {


				reportDto = reportsServiceImpl.getTransactionLog(fromDate,toDate,pageObject);

			}

				dataPageObject.setAaData(reportDto.getTxnList());
				dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
				
			logger.info("ReportsController :: getTransactionLogPagination() :: End");
		} catch (Exception ex) {
			logger.error("ReportsController :: getTransactionLogPagination() :: " + ex);
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;
	}
	
	/* Transaction Log Download */
	@RequestMapping(value = "/downloadTransactionLog", method = RequestMethod.GET)
	public void downloadTransactionLog(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "radio", required = false) String radio) throws IOException, ParseException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Transaction Log");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		String fromDate = "";
		String toDate = "";
	
		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				
				if (radio.equals("dateRange")) {
					if (date != null && date1 != null) {
						Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
						Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
						fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
						toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
					}
				} else {

					logger.info("ReportsController :: getDemoReport() :: Else");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					toDate = (dateFormat.format(new Date()));
					logger.info("todate =" + toDate);
					fromDate = getTwoYearsOldDate();
					logger.info("fromdate =" + fromDate);
				}
				if (fromDate != null && toDate != null) {

					builder = new StringBuilder("SELECT cf.cafno, cf.aadharno, cfs.stbmacaddr, cpeslno onuslno,cfs.stbslno stbslno, srvccodeaddl IPTVpackage, ");
					builder.append(" (CASE WHEN pv.url LIKE '%register%' THEN 'Corpus Subscriber Activation' WHEN pv.url LIKE '%addservicepack%' THEN 'Add Package' ");
					builder.append(" WHEN pv.url LIKE '%disconnect%' THEN 'Suspend Package' WHEN pv.url LIKE '%renew%' THEN 'Renew Package' ELSE 'Agora Subscriber Activation' ");
					builder.append(" END) 'Transaction', executed_date 'Transaction Date', CASE WHEN pv.status=1 THEN 'Transaction Successfully Completed' ");
					builder.append(" WHEN pv.status IN (0,3,4,5) THEN 'Transaction In Progress' WHEN pv.status IN (7,8,9) THEN 'Transaction Failed' END STATUS ");
					builder.append(" FROM provjsons pv, provrequests pr, cafs cf,cafstbs cfs WHERE pv.servicetype = 'IPTV' AND pv.requestid = pr.requestid ");
					builder.append(" AND executed_date BETWEEN '"+fromDate+"' AND DATE_ADD('"+toDate+"',INTERVAL 1 DAY) ");
					builder.append(" AND pr.acctcafno = cf.cafno AND cfs.stbmacaddr IS NOT NULL AND cfs.parentcafno = cf.cafno");

					list = reportsServiceImpl.getListByQuery(builder.toString());
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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("Transaction Log Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				if (radio.equals("dateRange")) {

					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);
					toDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate1);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);

				} else {

					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					toDate = (dateFormat.format(new Date()));
					String tempDate = getTwoYearsOldDate();
					Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);
				}

				cell = row2.createCell(4);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("Caf No");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Aadhar No");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("STB MAC Addr");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("ONU SL No");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("STB SL No");
				header.getCell(4).setCellStyle(style);

				header.createCell(5).setCellValue("IPTV Package");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("Transaction");
				header.getCell(6).setCellStyle(style);

				header.createCell(7).setCellValue("Transaction Date");
				header.getCell(7).setCellStyle(style);

				header.createCell(8).setCellValue("Status");
				header.getCell(8).setCellStyle(style);
				// create data rows
				int rowCount = 5;

				for (Object[] obj : list) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(obj[0] == null || obj[0].toString() == "" ? "NA" : obj[0].toString());
					aRow.createCell(1).setCellValue(obj[1] == null || obj[1].toString() == "" ? "NA" : obj[1].toString());
					aRow.createCell(2).setCellValue(obj[2] == null || obj[2].toString() == "" ? "NA" : obj[2].toString());
					aRow.createCell(3).setCellValue(obj[3] == null || obj[3].toString() == "" ? "NA" : obj[3].toString());
					aRow.createCell(4).setCellValue(obj[4] == null || obj[4].toString() == "" ? "NA" : obj[4].toString());
					aRow.createCell(5).setCellValue(obj[5] == null || obj[5].toString() == "" ? "NA" : obj[5].toString());
					aRow.createCell(6).setCellValue(obj[6] == null || obj[6].toString() == "" ? "NA" : obj[6].toString());
					aRow.createCell(7).setCellValue(obj[7] == null || obj[7].toString() == "" ? "NA" : this.stringtoCalenderDDMMYYYHHMMSS(obj[7].toString()));
					aRow.createCell(8).setCellValue(obj[8] == null || obj[8].toString() == "" ? "NA" : obj[8].toString());
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=TransactionLogReport.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
		}
	}

	/* CPE Order Report */
	@RequestMapping(value = "/getCpeOrderReport", method = RequestMethod.GET)
	public String getCpeOrderReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "cpeOrder";
	}
	
	@RequestMapping(value = "/getCpeOrderReportPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<CpeOrderVO> getCpeOrderReportPagination(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "radio", required = false) String radio) throws IOException {

		PaginationObject<CpeOrderVO> dataPageObject = new PaginationObject<CpeOrderVO>();
		ReportsDTO reportDto = new ReportsDTO();
		String fromDate = "";
		String toDate = "";

		try {
			logger.info("ReportsController :: getCpeOrderReportPagination() :: Start");
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
			sortColumn = CpeOrderVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			if (radio.equals("dateRange")) {
				if (date != null && date1 != null) {
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
				}
			} else {
				logger.info("ReportsController :: getCpeOrderReportPagination() :: Else");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				toDate = (dateFormat.format(new Date()));
				logger.info("todate =" + toDate);
				fromDate = getTwoYearsOldDate();
				logger.info("fromdate =" + fromDate);
			}
			if (fromDate != null && toDate != null) {


				reportDto = reportsServiceImpl.getCpeOrderReport(fromDate,toDate,pageObject);

			}

				dataPageObject.setAaData(reportDto.getCpeList());
				dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
				
			logger.info("ReportsController :: getCpeOrderReportPagination() :: End");
		} catch (Exception ex) {
			logger.error("ReportsController :: getCpeOrderReportPagination() :: " + ex);
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;
	}
	
	/* CPE Order Report Download */
	@RequestMapping(value = "/downloadCpeOrderReport", method = RequestMethod.GET)
	public void downloadCpeOrderReport(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "radio", required = false) String radio) throws IOException, ParseException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("CPEOrder Details");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		String fromDate = "";
		String toDate = "";
		
		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				
				if (radio.equals("dateRange")) {
					if (date1 != null && date2 != null) {
						Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
						Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
						fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
						toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
					}
				} else {
					logger.info("ReportsController :: getCpeOrderReport() :: Else");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					toDate = (dateFormat.format(new Date()));
					logger.info("todate =" + toDate);
					fromDate = getTwoYearsOldDate();
					logger.info("fromdate =" + fromDate);
				}
				if (fromDate != null && toDate != null) {

					builder = new StringBuilder("SELECT  t1.tenantcode,t.tenantname,d.districtname,m.mandalname, cpe.cpetypelov,cpe.cpe_model,DATE_FORMAT(t1.dmnddate,'%d-%m-%Y') orderDate,cp.purchasecost, t1.dmndqty,t1.dmndprice ");
					builder.append(" FROM tenantcpedmnd t1, tenants t, cpecharges cp, cpe_profilemaster cpe,districts d, mandals m WHERE t.tenantcode=t1.tenantcode  AND cp.profile_id = t1.profile_id  ");
					builder.append(" AND t1.profile_id=cpe.profile_id AND portal_districtid = d.districtuid AND portal_mandalid = m.mandalslno AND m.districtuid = d.districtuid and t1.dmnddate BETWEEN '"+ fromDate + "' AND '" + toDate + "' order by t1.dmnddate ");

					list = reportsServiceImpl.getListByQuery(builder.toString());
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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("CPE Order Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				if (radio.equals("dateRange")) {

					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);
					toDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate1);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);

				} else {

					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					toDate = (dateFormat.format(new Date()));
					String tempDate = getTwoYearsOldDate();
					Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);
				}

				cell = row2.createCell(4);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("MSO Code");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("MSP Name");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("District");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("Mandal");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("CPE Type");
				header.getCell(4).setCellStyle(style);

				header.createCell(5).setCellValue("CPE Model");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("Order Date");
				header.getCell(6).setCellStyle(style);

				header.createCell(7).setCellValue("CPE Purchase Cost");
				header.getCell(7).setCellStyle(style);

				header.createCell(8).setCellValue("Quantity");
				header.getCell(8).setCellStyle(style);

				header.createCell(9).setCellValue("Total Cost");
				header.getCell(9).setCellStyle(style);
				// create data rows
				int rowCount = 5;

				for (Object[] obj : list) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(obj[0] == null || obj[0].toString() == "" ? "NA" : obj[0].toString());
					aRow.createCell(1).setCellValue(obj[1] == null || obj[1].toString() == "" ? "NA" : obj[1].toString());
					aRow.createCell(2).setCellValue(obj[2] == null || obj[2].toString() == "" ? "NA" : obj[2].toString());
					aRow.createCell(3).setCellValue(obj[3] == null || obj[3].toString() == "" ? "NA" : obj[3].toString());
					aRow.createCell(4).setCellValue(obj[4] == null || obj[4].toString() == "" ? "NA" : obj[4].toString());
					aRow.createCell(5).setCellValue(obj[5] == null || obj[5].toString() == "" ? "NA" : obj[5].toString());
					aRow.createCell(6).setCellValue(obj[6] == null || obj[6].toString() == "" ? "NA" : obj[6].toString());
					aRow.createCell(7).setCellValue(obj[7] == null || obj[7].toString() == "" ? "NA" : obj[7].toString());
					aRow.createCell(8).setCellValue(obj[8] == null || obj[8].toString() == "" ? "NA" : obj[8].toString());
					aRow.createCell(9).setCellValue(obj[9] == null || obj[9].toString() == "" ? "NA" : obj[9].toString());
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=CPEOrderReport.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
		}
	}
	
	/* Total No Of Subscribers */
	@RequestMapping(value = "/getTotalNoOfSubscriber", method = RequestMethod.GET)
	public String getTotalNoOfSubscriber(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "radio", required = false) String radio) throws ParseException, IOException {

		List<Object[]> list = new ArrayList<>();
		String message = "";
		StringBuilder builder = new StringBuilder();
		String message1 = null;
		boolean tilldate=false;
		String whereClause="";

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		String fromDate1 = this.getTwoYearsOldDate();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String toDate1 = (dateFormat.format(new Date()));
		try {
			logger.info("ReportsController :: getTotalNoOfSubscriber() :: Start");
			String fromDate = "";
			String toDate = "";
			if (radio.equals("dateRange")) {
				if (date1 != null && date2 != null) {
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
				}
			} else {
				logger.info("ReportsController :: getTotalNoOfSubscriber() :: Else");

				toDate = (dateFormat.format(new Date()));
				logger.info("todate =" + toDate);
				fromDate = getTwoYearsOldDate();
				tilldate=true;
				
				logger.info("fromdate =" + fromDate);
			}
			
			if (fromDate != null && toDate != null) {

				message1 = "Report was Generated on " + currDate;
//commented out for report chages for Audit--removing suspended date.
       /* builder = new StringBuilder("SELECT cs.status AS statusvalue, CASE WHEN cs.status = " + CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' WHEN cs.status = " + CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus() + "  THEN 'Suspended' ");
        builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus() + " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus() + " THEN 'Blacklisted'   END STATUS, ");
        builder.append(" COUNT(DISTINCT cf.cafno) Total_Subscribers FROM cafsrvcs cs, srvcs s, cafs cf, cafstbs cb WHERE cs.srvccode = s.srvccode ");
        builder.append(" AND cf.cafno=cb.parentcafno AND cf.cafno = cs.PARENTCAFNO AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto  AND cs.actdate BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
        builder.append(" GROUP BY cs.status, CASE WHEN cs.status = " + CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' WHEN cs.status = " + CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus() + "  THEN 'Suspended' ");
        builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus() + " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus() + " THEN 'Blacklisted' END ");
        */if (tilldate!=true){
        	whereClause	="AND cs.actdate BETWEEN '" + fromDate + "' AND '" + toDate + "'";
        }else{
			
        	whereClause="";
			
		}
				 builder = new StringBuilder("SELECT cs.status AS statusvalue, CASE WHEN cs.status = " + CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active'");
			        builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus() + " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus() + " THEN 'Blacklisted'   END STATUS, ");
			        builder.append(" COUNT(DISTINCT cf.cafno) Total_Subscribers FROM cafs cf  left  JOIN cafsrvcs cs ON cf.cafno = cs.PARENTCAFNO left JOIN cafstbs cb ON cf.cafno = cb.parentcafno Left JOIN  srvcs s ON cs.srvccode = s.srvccode");			        		
			        builder.append(" WHERE  cf.status=cs.status AND  cs.status in (6,8,99)");
			        builder.append("  AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto " + whereClause);
			        builder.append(" GROUP BY cs.status, CASE WHEN cs.status = " + CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' ");
			        builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus() + " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus() + " THEN 'Blacklisted' END ");
				
			        
			        
				list = reportsServiceImpl.getListByQuery(builder.toString());

				if (list.isEmpty()) {
					message = "No Records Found For This Search Criteria";
					message1 = null;
				}

			}
			logger.info("ReportsController :: getTotalNoOfSubscriber() :: End");
		} catch (Exception ex) {
			logger.info("ReportsController :: getTotalNoOfSubscriber() :: " + ex);
			ex.printStackTrace();
		} finally {
			builder = null;
		}

		uiModel.addAttribute("message", message);
		uiModel.addAttribute("list", list);
		uiModel.addAttribute("date1", date1);
		uiModel.addAttribute("date2", date2);
		uiModel.addAttribute("message1", message1);
		uiModel.addAttribute("radio", radio);
		uiModel.addAttribute("fromDate1", fromDate1);
		uiModel.addAttribute("toDate1", toDate1);

		return "totalNoOfSubs";
	}
	
		
	/* Total No Of Subscribers download */
	@RequestMapping(value = "/downloadTotalNoOfSubscriber", method = RequestMethod.GET)
	public void downloadTotalNoOfSubscriber(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "radio", required = false) String radio) throws ParseException, IOException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		boolean tilldate=false;
		String whereClause="";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Total No Of Subsciber Details");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
	

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = "";
		String toDate = "";
		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				
				if (radio.equals("dateRange")) {
					if (date1 != null && date2 != null) {
						Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
						Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
						fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
						toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
					}
				} else {
					logger.info("ReportsController :: getTotalNoOfSubscriber() :: Else");
					tilldate=true;
					toDate = (dateFormat.format(new Date()));
					logger.info("todate =" + toDate);
					fromDate = getTwoYearsOldDate();
					logger.info("fromdate =" + fromDate);
				}
				
				if (fromDate != null && toDate != null) {
            /*  builder = new StringBuilder("SELECT cs.status AS statusvalue, CASE WHEN cs.status = " + CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' WHEN cs.status = " + CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus() + "  THEN 'Suspended' ");
              builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus() + " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus() + " THEN 'Blacklisted' END STATUS, ");
              builder.append(" COUNT(DISTINCT cf.cafno) Total_Subscribers FROM cafsrvcs cs, srvcs s, cafs cf WHERE cs.srvccode = s.srvccode AND s.coresrvccode='IPTV' ");
              builder.append(" AND cs.parentcafno = cf.cafno AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto  AND cs.actdate BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
              builder.append(" GROUP BY cs.status, CASE WHEN cs.status = " + CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' WHEN cs.status = " + CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus() + "  THEN 'Suspended' ");
              builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus() + " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus() + " THEN 'Blacklisted' END ");
              
*/
					if (tilldate!=true){
			        	whereClause	="AND cs.actdate BETWEEN '" + fromDate + "' AND '" + toDate + "'";
			        }else{
						
			        	whereClause="";
						
					}
							 builder = new StringBuilder("SELECT cs.status AS statusvalue, CASE WHEN cs.status = " + CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active'");
						        builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus() + " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus() + " THEN 'Blacklisted'   END STATUS, ");
						        builder.append(" COUNT(DISTINCT cf.cafno) Total_Subscribers FROM cafs cf  left  JOIN cafsrvcs cs ON cf.cafno = cs.PARENTCAFNO left JOIN cafstbs cb ON cf.cafno = cb.parentcafno Left JOIN  srvcs s ON cs.srvccode = s.srvccode");			        		
						        builder.append(" WHERE  cf.status=cs.status AND  cs.status in (6,8,99)");
						        builder.append("  AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto " + whereClause);
						        builder.append(" GROUP BY cs.status, CASE WHEN cs.status = " + CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' ");
						        builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus() + " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus() + " THEN 'Blacklisted' END ");
							
					
					list = reportsServiceImpl.getListByQuery(builder.toString());
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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("Total No Of Subscribers Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				if (radio.equals("dateRange")) {

					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);
					toDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate1);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);

				} else {

					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					toDate = (df.format(new Date()));
					String tempDate = getTwoYearsOldDate();
					Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);
				}

				cell = row2.createCell(4);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("Status");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Total Subscribers Count");
				header.getCell(1).setCellStyle(style);

				// create data rows
				int rowCount = 5;

				for (Object[] obj : list) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(obj[1] == null || obj[1].toString() == "" ? "NA" : obj[1].toString());
					aRow.createCell(1).setCellValue(obj[2] == null || obj[2].toString() == "" ? "NA" : obj[2].toString());
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=TotalNoOfSubReport.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
		}
	}
	
	/* Channels on a-la carte basis */
	@RequestMapping(value = "/getChannelsOnALACarteBasis", method = RequestMethod.GET)
	public String getChannelsOnALACarteBasis()  {
		return "chnlsOnAlacarte";
	}
	
	@RequestMapping(value = "/getChannelsOnALACarteBasisPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<ChnlsOnAlacarteVO> getChannelsOnALACarteBasisPagination(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "radio", required = false) String radio) throws IOException {

		PaginationObject<ChnlsOnAlacarteVO> dataPageObject = new PaginationObject<ChnlsOnAlacarteVO>();
		ReportsDTO reportDto = new ReportsDTO();
		String fromDate = "";
		String toDate = "";

		try {
			logger.info("ReportsController :: getChannelsOnALACarteBasisPagination() :: Start");
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
			sortColumn = ChnlsOnAlacarteVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			if (radio.equals("dateRange")) {
				if (date1 != null && date2 != null) {
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
				}
			} else {
				logger.info("ReportsController :: getSubscriberDetails() :: Else");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				toDate = (dateFormat.format(new Date()));
				logger.info("todate =" + toDate);
				fromDate = getTwoYearsOldDate();
				logger.info("fromdate =" + fromDate);
			}
			if (fromDate != null && toDate != null) {


				reportDto = reportsServiceImpl.getChnlsOnAlaCarteBasis(fromDate,toDate,pageObject);

			}

				dataPageObject.setAaData(reportDto.getAlacarteList());
				dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
				
			logger.info("ReportsController :: getChannelsOnALACarteBasisPagination() :: End");
		} catch (Exception ex) {
			logger.error("ReportsController :: getChannelsOnALACarteBasisPagination() :: " + ex);
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;
	}
	
	/* Channels on a-la carte basis download */
	@RequestMapping(value = "/downloadChannelsOnALACarteBasis", method = RequestMethod.GET)
	public void downloadChannelsOnALACarteBasis(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "radio", required = false) String radio) throws IOException, ParseException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("ALACarte Channels");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		String fromDate = "";
		String toDate = "";

		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				if (radio.equals("dateRange")) {
					if (date1 != null && date2 != null) {
						Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
						Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
						fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
						toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
					}
				} else {
					logger.info("ReportsController :: getChannelsOnALACarteBasis() :: Else");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					toDate = (dateFormat.format(new Date()));
					logger.info("todate =" + toDate);
					fromDate = getTwoYearsOldDate();
					logger.info("fromdate =" + fromDate);
				}
				if (fromDate != null && toDate != null) {

					builder = new StringBuilder("SELECT cfs.parentcafno, GROUP_CONCAT(cs.featurecodes) AS FeatureCodes,sf.featurename,cf.custcode,cfs.nwsubscode,cfs.stbslno,cfs.stbmacaddr ");
					builder.append(" FROM cafsrvcs cs,srvcs s,srvcfeatures sf,cafs cf,cafstbs cfs  WHERE cs.status= "+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() +" AND s.coresrvccode='IPTV' ");
					builder.append(" AND cs.srvccode=s.srvccode AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
					builder.append(" AND SPLIT_STRING(cs.featurecodes,',',1)=REPLACE(cs.featurecodes,',','') AND s.featurecodes=sf.featurecode AND cf.cafno=cfs.parentcafno ");
					builder.append(" AND cfs.parentcafno = cs.parentcafno AND cfs.stbcafno = cs.stbcafno ");
					builder.append(" GROUP BY cfs.parentcafno,sf.featurename,cf.custcode,cfs.nwsubscode,cfs.stbslno,cfs.stbmacaddr,cs.featurecodes ");

					list = reportsServiceImpl.getListByQuery(builder.toString());
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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("Channels On ALACarte Basis Report");
				cell.setCellStyle(style);

				if (radio.equals("dateRange")) {

					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);
					toDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate1);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);

				} else {

					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					toDate = (df.format(new Date()));
					String tempDate = getTwoYearsOldDate();
					Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);
				}

				cell = row2.createCell(4);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("CAF NO");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Channel Code");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("Channel Name");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("Aadhar No");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("Subscriber Code");
				header.getCell(4).setCellStyle(style);

				header.createCell(5).setCellValue("STB Serial No");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("STB MAC-ID");
				header.getCell(6).setCellStyle(style);

				// create data rows
				int rowCount = 5;

				for (Object[] obj : list) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(obj[0] == null || obj[0].toString() == "" ? "NA" : obj[0].toString());
					aRow.createCell(1).setCellValue(obj[1] == null || obj[1].toString() == "" ? "NA" : obj[1].toString());
					aRow.createCell(2).setCellValue(obj[2] == null || obj[2].toString() == "" ? "NA" : obj[2].toString());
					aRow.createCell(3).setCellValue(obj[3] == null || obj[3].toString() == "" ? "NA" : obj[3].toString());
					aRow.createCell(4).setCellValue(obj[4] == null || obj[4].toString() == "" ? "NA" : obj[4].toString());
					aRow.createCell(5).setCellValue(obj[5] == null || obj[5].toString() == "" ? "NA" : obj[5].toString());
					aRow.createCell(6).setCellValue(obj[6] == null || obj[6].toString() == "" ? "NA" : obj[6].toString());
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=AlaCarteBasisReport.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
		}
	}

	/* Status wise Subscriber Details */
	@RequestMapping(value = "/showPackagewiseSubscribers", method = RequestMethod.GET)
	public String showPackagewiseSubscribers(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "srvcCode", required = false) String srvcCode,
			@RequestParam(value = "actDate", required = false) String actDate) throws IOException {
		uiModel.addAttribute("srvccode",srvcCode);
		uiModel.addAttribute("date",actDate);
		return "packWiseSubs";
	}
	
	@RequestMapping(value = "/showPackagewiseSubscribersPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<PackWiseSubsVO> showPackagewiseSubscribersPagination(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "srvcCode", required = false) String srvcCode,
			@RequestParam(value = "actDate", required = false) String actDate) throws IOException {

		String srvc=srvcCode.trim();
		ReportsDTO reportDto = new ReportsDTO();
		PaginationObject<PackWiseSubsVO> dataPageObject = new PaginationObject<PackWiseSubsVO>();

		try {
			logger.info("ReportsController :: showPackagewiseSubscribersPagination() :: Start");
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
			sortColumn = PackWiseSubsVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			if (actDate != null && srvc != null) {
				
				Date currentDate = new SimpleDateFormat("MM/dd/yyyy").parse(actDate);
				String newDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);

				reportDto = reportsServiceImpl.getPackWiseSubsReport(srvc,newDate,pageObject);

				dataPageObject.setAaData(reportDto.getPackList());
				dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
			}
			logger.info("ReportsController :: showPackagewiseSubscribersPagination :: End");
		} catch (Exception ex) {
			logger.error("ReportsController :: showPackagewiseSubscribersPagination :: " + ex);
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;
	}
	
	/* Status wise Subscriber Details */
	@RequestMapping(value = "/getStatusWiseSubDetails", method = RequestMethod.GET)
	public String getStatusWiseSubscriberDetails(HttpServletRequest request, HttpServletResponse response,
			Model uiModel, @RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "radio", required = false) String radio) throws IOException {
		
		uiModel.addAttribute("date1",date1);
		uiModel.addAttribute("date2",date2);
		uiModel.addAttribute("status",status);
		uiModel.addAttribute("radio",radio);
		
		return "statusWiseSubs";
	}
	
	@RequestMapping(value = "/getStatusWiseSubDetailsPagination", method = RequestMethod.GET)
	@ResponseBody
	public PaginationObject<StatusWiseSubsVO> getStatusWiseSubscriberDetailsPagination(HttpServletRequest request, HttpServletResponse response,
			Model uiModel, @RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "radio", required = false) String radio) throws IOException {
		
		
		ReportsDTO reportDto = new ReportsDTO();
		PaginationObject<StatusWiseSubsVO> dataPageObject = new PaginationObject<StatusWiseSubsVO>();
		String fromDate="";
		String toDate="";
		boolean tillDate=false;
		try {
			logger.info("ReportsController :: showPackagewiseSubscribers() :: Start");
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
			sortColumn = StatusWiseSubsVO.UsersDataSearchParams.getColumns("COLUMN_" + sortParameter);
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
			
			if (radio.equals("dateRange")) {
				if (date1 != null && date2 != null) {
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
				}
			} else {
				tillDate=true;
				logger.info("ReportsController :: getSubscriberDetails() :: Else");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				toDate = (dateFormat.format(new Date()));
				logger.info("todate =" + toDate);
				fromDate = getTwoYearsOldDate();
				logger.info("fromdate =" + fromDate);
			}
			
			  if(fromDate != null && toDate!=null && status != null){
					reportDto = reportsServiceImpl.getStatusWiseSubs(fromDate,toDate,status,pageObject,tillDate);
	
					dataPageObject.setAaData(reportDto.getStatusWiseList());
					dataPageObject.setiTotalDisplayRecords(Long.parseLong(reportDto.getCount()));
		      }
			
			logger.info("ReportsController :: getBasedOnActivationDate :: End");
		} catch (Exception ex) {
			logger.error("ReportsController :: getBasedOnActivationDate :: " + ex);
			ex.printStackTrace();
		} finally {
		}

		return dataPageObject;

	}
	
	/* Customer Activity Log Report */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/customerActivityLog", method = RequestMethod.GET)
	public String getCafWiseDates(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "stbMacId", required = false) String stbMacId,
			@RequestParam(value = "nwCode", required = false) String nwCode,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2) throws IOException {

		List<Object[]> caflist = new ArrayList<>();
		List<CafDatesVO> cafDatesVOList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		StringBuilder builder1 = new StringBuilder();
		ReportsVO reportsVO = null;
		String message1 = null;
		String message = null;
		int count = 1;
		String nwSubscriberCode = nwCode;

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try {
			logger.info("ReportsController :: getCafWiseDates() :: Start");
			if ((date1 != null && date2 != null && nwCode != null) || (date1 != null && date2 != null && stbMacId != null)) {
				count++;
				Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
				String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
				Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);

				message1 = "Report was Generated on " + currDate;

				if (!nwCode.isEmpty()) {
					builder = new StringBuilder("SELECT cfs.parentcafno,cs.srvccode, cs.actdate AS ActivationDate ,IFNULL(cs.deactdate,'') AS DeactivationDate, ");
					builder.append(" IFNULL(cs.suspdates,'') AS Suspendeddateslist,IFNULL(cs.resumedates,'') AS ResumeDatesList, cfs.stbslno, cfs.stbmacaddr, cf.aadharno,cfs.nwsubscode ");
					builder.append(" FROM cafsrvcs cs, srvcs s, cafs cf,cafstbs cfs WHERE cfs.nwsubscode='"+nwCode+"' AND cs.srvccode = s.srvccode AND s.coresrvccode='IPTV' ");
					builder.append(" AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto ");
					builder.append(" AND cfs.parentcafno = cf.cafno AND cfs.parentcafno = cs.parentcafno  AND cfs.stbcafno = cs.stbcafno and cs.parentcafno = cf.cafno");

				} else if (!stbMacId.isEmpty()) {
					builder = new StringBuilder("SELECT cfs.parentcafno,cs.srvccode, cs.actdate AS ActivationDate ,IFNULL(cs.deactdate,'') AS DeactivationDate, ");
					builder.append(" IFNULL(cs.suspdates,'') AS Suspendeddateslist,IFNULL(cs.resumedates,'') AS ResumeDatesList, cfs.stbslno, cfs.stbmacaddr, cf.aadharno, cfs.nwsubscode ");
					builder.append(" FROM cafsrvcs cs, srvcs s, cafs cf,cafstbs cfs WHERE cfs.stbmacaddr= '"+stbMacId+"' AND cs.srvccode = s.srvccode AND s.coresrvccode='IPTV' ");
					builder.append(" AND cfs.parentcafno = cs.parentcafno AND cfs.parentcafno = cf.cafno AND cfs.stbcafno = cs.stbcafno and cs.parentcafno = cf.cafno ");
					builder.append(" AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto ");

					builder1 = new StringBuilder("SELECT DISTINCT(cfs.nwsubscode) FROM cafsrvcs cs, cafs cf, srvcs s,cafstbs cfs WHERE cs.srvccode=s.srvccode AND s.coresrvccode='IPTV' AND ");
					builder1.append(" IFNULL(cfs.nwsubscode,'')<> '' AND cfs.stbmacaddr = '"+stbMacId+"' AND cfs.parentcafno = cs.parentcafno and cs.parentcafno = cf.cafno ");
					builder1.append(" AND cfs.parentcafno = cf.cafno AND cfs.stbcafno = cs.stbcafno AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto ");
					
					nwSubscriberCode = reportsServiceImpl.getSingleRecord(builder1.toString());
				}
				caflist = reportsServiceImpl.getListByQuery(builder.toString());
				reportsVO = new ReportsVO();

				for (Object[] obj : caflist) {

					CafDatesVO cafDatesVO = new CafDatesVO();
					cafDatesVO.setCafNo(Long.parseLong(obj[0].toString()));
					cafDatesVO.setServiceCode(obj[1] == null ? "NA" : obj[1].toString());
					cafDatesVO.setActDate(obj[2] == null || obj[2] == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[2].toString()));
					cafDatesVO.setDeactDate(obj[3] == null || obj[3] == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[3].toString()));
					cafDatesVO.setSuspDates(obj[4] == null || obj[4] == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[4].toString()));
					cafDatesVO.setResumedates(obj[5] == null || obj[5] == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[5].toString()));
					cafDatesVO.setAadharNo(obj[8] == null ? "NA" : obj[8].toString());
					cafDatesVO.setNwSubCode(obj[9] == null ? "NA" : obj[9].toString());
					cafDatesVO.setStbMacAddress(obj[7] == null ? "NA" : obj[7].toString());
					cafDatesVO.setStbSrlNo(obj[6] == null ? "NA" : obj[6].toString());

					cafDatesVOList.add(cafDatesVO);
				}
				reportsVO.setCafDates(cafDatesVOList);
				List<OsdFingPrntVO> osdFPList = this.getOSDFDetails(nwSubscriberCode, fromDate, toDate);
				reportsVO.setOsdFingPrnt(osdFPList);

				if (cafDatesVOList == null && osdFPList == null) {
					message = "No Records Found For This Search Criteria";
					message1 = null;
				}
			}
		} catch (Exception ex) {
			logger.info("ReportsController :: getCafWiseDates() :: " + ex);
			ex.printStackTrace();
		} finally {
			builder = null;
			builder1 = null;
		}
		uiModel.addAttribute("reportsVO", reportsVO);
		uiModel.addAttribute("id", 14);
		uiModel.addAttribute("nwCode", nwCode);
		uiModel.addAttribute("stbMacId", stbMacId);
		uiModel.addAttribute("fromDate", date1);
		uiModel.addAttribute("toDate", date2);
		uiModel.addAttribute("message1", message1);
		uiModel.addAttribute("message", message);
		uiModel.addAttribute("count", count);

		return "customerActivityLog";
	}
	
	/* Customer Activity Log Report download */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/downloadcustomerActivityLog", method = RequestMethod.GET)
	public void downloadcustomerActivityLog(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "stbMacId", required = false) String stbMacId,
			@RequestParam(value = "nwCode", required = false) String nwCode,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2) throws IOException, ParseException {

		List<Object[]> caflist = new ArrayList<>();
		List<CafDatesVO> cafDatesVOList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		StringBuilder builder1 = new StringBuilder();
		ReportsVO reportsVO = null;
		int count = 1;
		String nwSubscriberCode = nwCode;

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Activity Log Details");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				
				if ((date1 != null && date2 != null && nwCode != null) || (date1 != null && date2 != null && stbMacId != null)) {
					count++;
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					String toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);

					if (!nwCode.isEmpty()) {
						builder = new StringBuilder("SELECT cfs.parentcafno,cs.srvccode, cs.actdate AS ActivationDate ,IFNULL(cs.deactdate,'') AS DeactivationDate, ");
						builder.append(" IFNULL(cs.suspdates,'') AS Suspendeddateslist,IFNULL(cs.resumedates,'') AS ResumeDatesList, cfs.stbslno, cfs.stbmacaddr, cf.aadharno,cfs.nwsubscode ");
						builder.append(" FROM cafsrvcs cs, srvcs s, cafs cf,cafstbs cfs WHERE cfs.nwsubscode='"+nwCode+"' AND cs.srvccode = s.srvccode AND s.coresrvccode='IPTV' ");
						builder.append(" AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto ");
						builder.append(" AND cfs.parentcafno = cf.cafno AND cfs.parentcafno = cs.parentcafno  AND cfs.stbcafno = cs.stbcafno and cs.parentcafno = cf.cafno");

					} else if (!stbMacId.isEmpty()) {
						builder = new StringBuilder("SELECT cfs.parentcafno,cs.srvccode, cs.actdate AS ActivationDate ,IFNULL(cs.deactdate,'') AS DeactivationDate, ");
						builder.append(" IFNULL(cs.suspdates,'') AS Suspendeddateslist,IFNULL(cs.resumedates,'') AS ResumeDatesList, cfs.stbslno, cfs.stbmacaddr, cf.aadharno, cfs.nwsubscode ");
						builder.append(" FROM cafsrvcs cs, srvcs s, cafs cf,cafstbs cfs WHERE cfs.stbmacaddr= '"+stbMacId+"' AND cs.srvccode = s.srvccode AND s.coresrvccode='IPTV' ");
						builder.append(" AND cfs.parentcafno = cs.parentcafno AND cfs.parentcafno = cf.cafno AND cfs.stbcafno = cs.stbcafno and cs.parentcafno = cf.cafno ");
						builder.append(" AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto ");

						builder1 = new StringBuilder("SELECT DISTINCT(cfs.nwsubscode) FROM cafsrvcs cs, cafs cf, srvcs s,cafstbs cfs WHERE cs.srvccode=s.srvccode AND s.coresrvccode='IPTV' AND ");
						builder1.append(" IFNULL(cfs.nwsubscode,'')<> '' AND cfs.stbmacaddr = '"+stbMacId+"' AND cfs.parentcafno = cs.parentcafno and cs.parentcafno = cf.cafno ");
						builder1.append(" AND cfs.parentcafno = cf.cafno AND cfs.stbcafno = cs.stbcafno AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto ");
						
						nwSubscriberCode = reportsServiceImpl.getSingleRecord(builder1.toString());
					}
					caflist = reportsServiceImpl.getListByQuery(builder.toString());
					reportsVO = new ReportsVO();

					for (Object[] obj : caflist) {

						CafDatesVO cafDatesVO = new CafDatesVO();
						cafDatesVO.setCafNo(Long.parseLong(obj[0].toString()));
						cafDatesVO.setServiceCode(obj[1] == null ? "NA" : obj[1].toString());
						cafDatesVO.setActDate(obj[2] == null || obj[2] == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[2].toString()));
						cafDatesVO.setDeactDate(obj[3] == null || obj[3] == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[3].toString()));
						cafDatesVO.setSuspDates(obj[4] == null || obj[4] == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[4].toString()));
						cafDatesVO.setResumedates(obj[5] == null || obj[5] == "" ? "NA" : this.stringtoCalenderDDMMYYY(obj[5].toString()));
						cafDatesVO.setAadharNo(obj[8] == null ? "NA" : obj[8].toString());
						cafDatesVO.setNwSubCode(obj[9] == null ? "NA" : obj[9].toString());
						cafDatesVO.setStbMacAddress(obj[7] == null ? "NA" : obj[7].toString());
						cafDatesVO.setStbSrlNo(obj[6] == null ? "NA" : obj[6].toString());

						cafDatesVOList.add(cafDatesVO);
					}
					reportsVO.setCafDates(cafDatesVOList);
					List<OsdFingPrntVO> osdFPList = this.getOSDFDetails(nwSubscriberCode, fromDate, toDate);
					reportsVO.setOsdFingPrnt(osdFPList);
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
				cell.setCellValue("Customer Activity Log Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				// For creating third row
				cell = row2.createCell(0);
				cell.setCellValue("From Date");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue(date1);
				cell.setCellStyle(style);

				cell = row2.createCell(2);
				cell.setCellValue("To Date");
				cell.setCellStyle(style);

				cell = row2.createCell(3);
				cell.setCellValue(date2);
				cell.setCellStyle(style);

				cell = row2.createCell(4);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("Aadhar No");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("NWSubscr.Code");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("STB SrlNO");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("STB MACId");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("Package Name");
				header.getCell(4).setCellStyle(style);

				header.createCell(5).setCellValue("Activity");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("Transaction Date");
				header.getCell(6).setCellStyle(style);

				header.createCell(7).setCellValue("Message");
				header.getCell(7).setCellStyle(style);

				// create data rows
				int rowCount = 5;

				for (CafDatesVO obj : reportsVO.getCafDates()) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(obj.getAadharNo() == null || obj.getAadharNo() == "" ? "NA" : obj.getAadharNo());
					aRow.createCell(1).setCellValue(obj.getNwSubCode() == null || obj.getNwSubCode() == "" ? "NA" : obj.getNwSubCode());
					aRow.createCell(2).setCellValue(obj.getStbSrlNo() == null || obj.getStbSrlNo() == "" ? "NA" : obj.getStbSrlNo());
					aRow.createCell(3).setCellValue(obj.getStbMacAddress() == null || obj.getStbMacAddress() == "" ? "NA" : obj.getStbMacAddress());
					aRow.createCell(4).setCellValue(obj.getServiceCode() == null || obj.getServiceCode() == "" ? "NA" : obj.getServiceCode());
					aRow.createCell(5).setCellValue("Customer Services Activated");
					aRow.createCell(6).setCellValue(obj.getActDate());
					aRow.createCell(7).setCellValue("NA");

					if (!(obj.getSuspDates()).equalsIgnoreCase("NA")) {
						String[] suspdates = obj.getSuspDates().split(",");
						for (String s : suspdates) {
							aRow = sheet.createRow(rowCount++);
							aRow.createCell(0).setCellValue(obj.getAadharNo() == null || obj.getAadharNo() == "" ? "NA" : obj.getAadharNo());
							aRow.createCell(1).setCellValue(obj.getNwSubCode() == null || obj.getNwSubCode() == "" ? "NA" : obj.getNwSubCode());
							aRow.createCell(2).setCellValue(obj.getStbSrlNo() == null || obj.getStbSrlNo() == "" ? "NA" : obj.getStbSrlNo());
							aRow.createCell(3).setCellValue(obj.getStbMacAddress() == null || obj.getStbMacAddress() == "" ? "NA" : obj.getStbMacAddress());
							aRow.createCell(4).setCellValue(obj.getServiceCode() == null || obj.getServiceCode() == "" ? "NA" : obj.getServiceCode());
							aRow.createCell(5).setCellValue("Customer Suspended");
							aRow.createCell(6).setCellValue(s);
							aRow.createCell(7).setCellValue("NA");
						}
					}

					if (!obj.getResumedates().equalsIgnoreCase("NA")) {
						String[] resumeDates = obj.getResumedates().split(",");
						for (String r : resumeDates) {
							aRow = sheet.createRow(rowCount++);
							aRow.createCell(0).setCellValue(obj.getAadharNo() == null || obj.getAadharNo() == "" ? "NA" : obj.getAadharNo());
							aRow.createCell(1).setCellValue(obj.getNwSubCode() == null || obj.getNwSubCode() == "" ? "NA" : obj.getNwSubCode());
							aRow.createCell(2).setCellValue(obj.getStbSrlNo() == null || obj.getStbSrlNo() == "" ? "NA" : obj.getStbSrlNo());
							aRow.createCell(3).setCellValue(obj.getStbMacAddress() == null || obj.getStbMacAddress() == "" ? "NA" : obj.getStbMacAddress());
							aRow.createCell(4).setCellValue(obj.getServiceCode() == null || obj.getServiceCode() == "" ? "NA" : obj.getServiceCode());
							aRow.createCell(5).setCellValue("Customer Services Resumed");
							aRow.createCell(6).setCellValue(r);
							aRow.createCell(7).setCellValue("NA");
						}
					}
					if (!(obj.getDeactDate()).equalsIgnoreCase("NA")) {
						aRow = sheet.createRow(rowCount++);
						aRow.createCell(0).setCellValue(obj.getAadharNo() == null || obj.getAadharNo() == "" ? "NA" : obj.getAadharNo());
						aRow.createCell(1).setCellValue(obj.getNwSubCode() == null || obj.getNwSubCode() == "" ? "NA" : obj.getNwSubCode());
						aRow.createCell(2).setCellValue(obj.getStbSrlNo() == null || obj.getStbSrlNo() == "" ? "NA" : obj.getStbSrlNo());
						aRow.createCell(3).setCellValue(obj.getStbMacAddress() == null || obj.getStbMacAddress() == "" ? "NA" : obj.getStbMacAddress());
						aRow.createCell(4).setCellValue(obj.getServiceCode() == null || obj.getServiceCode() == "" ? "NA" : obj.getServiceCode());
						aRow.createCell(5).setCellValue("Customer Deactivated");
						aRow.createCell(6).setCellValue(obj.getDeactDate());
						aRow.createCell(7).setCellValue("NA");
					}
				}
				for (OsdFingPrntVO obj1 : reportsVO.getOsdFingPrnt()) {
					aRow = sheet.createRow(rowCount++);

					if (obj1.getRequestType().equalsIgnoreCase("blacklist")) {
						aRow.createCell(0).setCellValue(obj1.getAadharNo() == null || obj1.getAadharNo() == "" ? "NA" : obj1.getAadharNo());
						aRow.createCell(1).setCellValue(obj1.getNwSubCode() == null || obj1.getNwSubCode() == "" ? "NA" : obj1.getNwSubCode());
						aRow.createCell(2).setCellValue(obj1.getStbSrlNo() == null || obj1.getStbSrlNo() == "" ? "NA" : obj1.getStbSrlNo());
						aRow.createCell(3).setCellValue(obj1.getStbMacAddress() == null || obj1.getStbMacAddress() == "" ? "NA" : obj1.getStbMacAddress());
						aRow.createCell(4).setCellValue(obj1.getServiceCode() == null || obj1.getServiceCode() == "" ? "NA" : obj1.getServiceCode());
						aRow.createCell(5).setCellValue("Customer Blacklisted");
						aRow.createCell(6).setCellValue(obj1.getRequestDate());
						aRow.createCell(7).setCellValue("NA");

					} else if (obj1.getRequestType().equalsIgnoreCase("FINGER_PRINT")) {
						aRow.createCell(0).setCellValue(obj1.getAadharNo() == null || obj1.getAadharNo() == "" ? "NA" : obj1.getAadharNo());
						aRow.createCell(1).setCellValue(obj1.getNwSubCode() == null || obj1.getNwSubCode() == "" ? "NA" : obj1.getNwSubCode());
						aRow.createCell(2).setCellValue(obj1.getStbSrlNo() == null || obj1.getStbSrlNo() == "" ? "NA" : obj1.getStbSrlNo());
						aRow.createCell(3).setCellValue(obj1.getStbMacAddress() == null || obj1.getStbMacAddress() == "" ? "NA" : obj1.getStbMacAddress());
						aRow.createCell(4).setCellValue(obj1.getServiceCode() == null || obj1.getServiceCode() == "" ? "NA" : obj1.getServiceCode());
						aRow.createCell(5).setCellValue("FINGER PRINT");
						aRow.createCell(6).setCellValue(obj1.getRequestDate());
						aRow.createCell(7).setCellValue("NA");

					} else if (obj1.getRequestType().equalsIgnoreCase("SCROLL_TEXT")) {
						aRow.createCell(0).setCellValue(obj1.getAadharNo() == null || obj1.getAadharNo() == "" ? "NA" : obj1.getAadharNo());
						aRow.createCell(1).setCellValue(obj1.getNwSubCode() == null || obj1.getNwSubCode() == "" ? "NA" : obj1.getNwSubCode());
						aRow.createCell(2).setCellValue(obj1.getStbSrlNo() == null || obj1.getStbSrlNo() == "" ? "NA" : obj1.getStbSrlNo());
						aRow.createCell(3).setCellValue(obj1.getStbMacAddress() == null || obj1.getStbMacAddress() == "" ? "NA" : obj1.getStbMacAddress());
						aRow.createCell(4).setCellValue(obj1.getServiceCode() == null || obj1.getServiceCode() == "" ? "NA" : obj1.getServiceCode());
						aRow.createCell(5).setCellValue("SCROLL TEXT");
						aRow.createCell(6).setCellValue(obj1.getRequestDate());
						aRow.createCell(7).setCellValue(obj1.getDetailedMessage() == null || obj1.getDetailedMessage() == "" ? "NA" : obj1.getDetailedMessage());

					} else if (obj1.getRequestType().equalsIgnoreCase("OSD")) {
						aRow.createCell(0).setCellValue(obj1.getAadharNo() == null || obj1.getAadharNo() == "" ? "NA" : obj1.getAadharNo());
						aRow.createCell(1).setCellValue(obj1.getNwSubCode() == null || obj1.getNwSubCode() == "" ? "NA" : obj1.getNwSubCode());
						aRow.createCell(2).setCellValue(obj1.getStbSrlNo() == null || obj1.getStbSrlNo() == "" ? "NA" : obj1.getStbSrlNo());
						aRow.createCell(3).setCellValue(obj1.getStbMacAddress() == null || obj1.getStbMacAddress() == "" ? "NA" : obj1.getStbMacAddress());
						aRow.createCell(4).setCellValue(obj1.getServiceCode() == null || obj1.getServiceCode() == "" ? "NA" : obj1.getServiceCode());
						aRow.createCell(5).setCellValue("OSD");
						aRow.createCell(6).setCellValue(obj1.getRequestDate());
						aRow.createCell(7).setCellValue(obj1.getDetailedMessage() == null || obj1.getDetailedMessage() == "" ? "NA" : obj1.getDetailedMessage());
					}
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=ActivityLog.xls");
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
	
	/* get OSD Details */
	public List<OsdFingPrntVO> getOSDFDetails(String nwCode, String fromDate, String toDate) throws IOException {

		StringBuilder builder1 = new StringBuilder();
		List<Object[]> osdfingerprint = new ArrayList<>();
		List<OsdFingPrntVO> osdFingPrnt = new ArrayList<>();
		try {
			if (fromDate != null && toDate != null) {
				logger.info("ReportsController :: getOSDFDetails() :: Start");

				builder1 = new StringBuilder("SELECT o.requesttype,o.requestdate,o.displaymsg, cfs.parentcafno,cs.srvccode,cfs.stbslno, cfs.stbmacaddr, cf.aadharno, cfs.nwsubscode ");
				builder1.append(" FROM osdfngprndtl o, cafsrvcs cs, srvcs s, cafs cf,cafstbs cfs ");
				builder1.append(" WHERE cfs.nwsubscode='"+nwCode+"' AND cs.srvccode = s.srvccode AND s.coresrvccode='IPTV' AND cfs.parentcafno = cs.parentcafno ");
				builder1.append(" AND cfs.parentcafno = cf.cafno AND cfs.stbcafno = cs.stbcafno AND o.subscriptionnos LIKE '%"+nwCode+"%' and cs.parentcafno = cf.cafno ");
				builder1.append(" AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto AND o.requestdate BETWEEN '"+toDate+"' AND DATE_ADD('"+fromDate+"',INTERVAL 1 DAY) ORDER BY requesttype ");

				osdfingerprint = reportsServiceImpl.getListByQuery(builder1.toString());

				for (Object[] obj : osdfingerprint) {

					OsdFingPrntVO osdFingPrntVOlist = new OsdFingPrntVO();
					osdFingPrntVOlist.setRequestType(obj[0] == null ? "" : obj[0].toString());
					osdFingPrntVOlist.setRequestDate(obj[1] == null || obj[1] == "" ? "NA" : this.stringtoCalenderDDMMYYYHHMMSS(obj[1].toString()));
					osdFingPrntVOlist.setDetailedMessage(obj[2] == null ? "NA" : obj[2].toString());
					osdFingPrntVOlist.setAadharNo(obj[7] == null ? "NA" : obj[7].toString());
					osdFingPrntVOlist.setNwSubCode(obj[8] == null ? "NA" : obj[8].toString());
					osdFingPrntVOlist.setStbMacAddress(obj[6] == null ? "NA" : obj[6].toString());
					osdFingPrntVOlist.setStbSrlNo(obj[5] == null ? "NA" : obj[5].toString());
					osdFingPrntVOlist.setServiceCode(obj[4] == null ? "NA" : obj[4].toString());
					osdFingPrntVOlist.setCafNo(obj[3] == null ? "NA" : obj[3].toString());
					osdFingPrnt.add(osdFingPrntVOlist);
				}
			}
		} catch (Exception ex) {
			logger.info("ReportsController :: getOSDFDetails() :: " + ex);
			ex.printStackTrace();
		} finally {

		}
		return osdFingPrnt;
	}
	
	// Blacked Customers
	@RequestMapping(value = "/blackListedCustomers", method = RequestMethod.GET)
	public String viewBlackListedCustomers(Model model, HttpServletRequest request) {
		return "blackListedCustomers";
	}

	/* Blacklist Customer Report */
	@RequestMapping(value = "/searchblackListedCustomers", method = RequestMethod.POST)
	public String searchBlackListedCustomers(Model model,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "stbMac", required = false) String stbMac,
			@RequestParam(value = "download", required = false) boolean download, HttpServletRequest request,
			HttpServletResponse response) {

		List<CafsForBlockListVO> BlackListed = new ArrayList<>();
		StringBuilder builder = null;
		String whereClause = null;
		String from = null;
		String to = null;
		try {
			logger.info("ComsController :: viewBlackListedCustomers() :: Start");
			if (stbMac != null && stbMac.trim() != "") {
				whereClause = " cfs.stbmacaddr='" + stbMac + "'";
				whereClause = " stb.stbmacaddr='" + stbMac + "'";
			}
			if (fromdate != null && fromdate.trim() != "" && todate != null && todate.trim() != "") {
				Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(fromdate);
				Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(todate);
				from = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
				to = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(to));
				c.add(Calendar.DATE, 1); // number of days to add
				to = sdf.format(c.getTime());
				whereClause = " bc.approvedon BETWEEN '" + from + "' AND '" + to + "'";
			}
			logger.info("START::viewBlackListedCustomers()");
			
			/*builder = new StringBuilder("SELECT cf.cafno, cust.fname, cust.lname, cf.inst_addr1, cf.inst_addr2, cf.inst_locality,cf.inst_area, cust.aadharno, cfs.stbslno,DATE_FORMAT(bc.approvedon,'%d-%m-%Y'), cfs.stbmacaddr , ");
			builder.append(" (SELECT DISTINCT cfs.nwsubscode FROM cafsrvcs cs, srvcs s WHERE cfs.nwsubscode IS NOT NULL AND cfs.parentcafno = cf.cafno AND cs.srvccode = s.srvccode ");
			builder.append(" AND s.coresrvccode = 'IPTV' AND cfs.parentcafno = cs.parentcafno AND cfs.stbcafno = cs.stbcafno) AS NwSubCode, bc.reason ");
			builder.append(" FROM customers cust, cafs cf ,BLACKLISTCUST bc,cafstbs cfs WHERE cust.custid = cf.custid AND cust.status = "+CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+" AND bc.custuid = cust.custid  ");
			builder.append(" AND "+ whereClause +" AND cfs.parentcafno = cf.cafno ORDER BY bc.approvedon");*/
			
			builder = new StringBuilder("SELECT c.cafno, cust.fname, cust.lname, c.inst_addr1, c.inst_addr2, c.inst_locality,c.inst_area, cust.aadharno, stb.stbslno , DATE_FORMAT(bc.approvedon,'%d-%m-%Y'), stb.stbmacaddr , ");
			builder.append(" stb.nwsubscode, ");
			builder.append("bc.reason  FROM customers cust, cafs c ,blackliststb bc ,cafstbs stb  WHERE cust.custid = c.custid  AND stb.stbstatus = "+CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+"   AND bc.stbcafno = stb.stbcafno ");
			builder.append(" AND stb.parentcafno = c.cafno AND bc.status=1 AND " + whereClause + " ORDER BY bc.approvedon");

			BlackListed = reportsServiceImpl.getcustomerListByQuery(builder.toString());

		} catch (Exception e) {
			logger.error("ComsController :: viewBlackListedCustomers() :: " + e);
			e.printStackTrace();
		} finally {
			builder = null;
			whereClause = null;
			from = null;
			to = null;
		}
		if (BlackListed.size() > 0) {
			model.addAttribute("BlackListed", BlackListed);
		} else {
			model.addAttribute("Emsg", "No records found ");
		}
		model.addAttribute("fromdate", fromdate);
		model.addAttribute("todate", todate);
		model.addAttribute("stbMac", stbMac);

		return "blackListedCustomers";
	}
	
	/* Blacklist Customer Download */
	@RequestMapping(value = "/blacklistCustomerDownload", method = RequestMethod.POST)
	public void blacklistCustomerDownload(Model model,
			@RequestParam(value = "fromdate", required = false) String fromdate,
			@RequestParam(value = "todate", required = false) String todate,
			@RequestParam(value = "stbMac", required = false) String stbMac,
			@RequestParam(value = "download", required = false) boolean download, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {

		List<CafsForBlockListVO> BlackListed = new ArrayList<>();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Blacked Customers");
		// create header row
		Row header = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

			logger.info("ComsController :: viewBlackListedCustomers() :: Start");
			StringBuilder builder = null;
			String whereClause = null;
			String from = null;
			String to = null;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String currDate = (dateFormat.format(new Date()));

			if (download) {
				if (stbMac != null) {
					if(!stbMac.isEmpty()) {
						whereClause = " cfstb.stbmacaddr='" + stbMac + "'";
						whereClause = " stb.stbmacaddr='" + stbMac + "'";
					}
				}
				if (fromdate != null && fromdate.trim() != "" && todate != null && todate.trim() != "") {
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(fromdate);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(todate);
					from = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					to = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(to));
					c.add(Calendar.DATE, 1); // number of days to add
					to = sdf.format(c.getTime());
					whereClause = " bc.approvedon BETWEEN '" + from + "' AND '" + to + "'";
				}
				logger.info("START::viewBlackListedCustomers()");
				
				/*builder = new StringBuilder("SELECT cf.cafno, cust.fname, cust.lname, cf.inst_addr1, cf.inst_addr2, cf.inst_locality,cf.inst_area, cust.aadharno, cfs.stbslno,DATE_FORMAT(bc.approvedon,'%d-%m-%Y'), cfs.stbmacaddr , ");
				builder.append(" (SELECT DISTINCT cfs.nwsubscode FROM cafsrvcs cs, srvcs s WHERE cfs.nwsubscode IS NOT NULL AND cfs.parentcafno = cf.cafno AND cs.srvccode = s.srvccode ");
				builder.append(" AND s.coresrvccode = 'IPTV' AND cfs.parentcafno = cs.parentcafno AND cfs.stbcafno = cs.stbcafno) AS NwSubCode, bc.reason ");
				builder.append(" FROM customers cust, cafs cf ,BLACKLISTCUST bc,cafstbs cfs WHERE cust.custid = cf.custid AND cust.status = "+CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+" AND bc.custuid = cust.custid  ");
				builder.append(" AND "+ whereClause +" AND cfs.parentcafno = cf.cafno ORDER BY bc.approvedon");*/

				
				builder = new StringBuilder("SELECT c.cafno, cust.fname, cust.lname, c.inst_addr1, c.inst_addr2, c.inst_locality,c.inst_area, cust.aadharno, stb.stbslno , DATE_FORMAT(bc.approvedon,'%d-%m-%Y'), stb.stbmacaddr , ");
				builder.append(" stb.nwsubscode, ");
				builder.append("bc.reason  FROM customers cust, cafs c ,blackliststb bc ,cafstbs stb  WHERE cust.custid = c.custid  AND stb.stbstatus = "+CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+"   AND bc.stbcafno = stb.stbcafno ");
				builder.append(" AND stb.parentcafno = c.cafno AND bc.status=1 AND " + whereClause + " ORDER BY bc.approvedon");
				
				BlackListed = reportsServiceImpl.getcustomerListByQuery(builder.toString());
				
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
					style.setAlignment(style.ALIGN_CENTER);
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
					cell.setCellValue("Blacked Customers");
					sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
					cell.setCellStyle(style);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(from);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(to);
					cell.setCellStyle(style);

					cell = row2.createCell(4);
					cell.setCellValue("Generated On");
					cell.setCellStyle(style);

					cell = row2.createCell(5);
					cell.setCellValue(currDate);
					cell.setCellStyle(style);

					header.createCell(0).setCellValue("STB-MacAddress");
					header.getCell(0).setCellStyle(style);

					header.createCell(1).setCellValue("Subscriber Code");
					header.getCell(1).setCellStyle(style);

					header.createCell(2).setCellValue("Reason");
					header.getCell(2).setCellStyle(style);

					header.createCell(3).setCellValue("BlackListed-Date");
					header.getCell(3).setCellStyle(style);

					// create data rows
					int rowCount = 5;

					for (CafsForBlockListVO pR : BlackListed) {
						HSSFRow Row = sheet.createRow(rowCount++);
						Row.createCell(0).setCellValue(pR.getStbmac() == null || pR.getStbmac().toString() == "" ? "NA" : pR.getStbmac().toString());
						Row.createCell(1).setCellValue(pR.getNwsubscode() == null || pR.getNwsubscode().toString() == "" ? "NA" : pR.getNwsubscode().toString());
						Row.createCell(2).setCellValue(pR.getReason() == null || pR.getReason().toString() == "" ? "NA" : pR.getReason().toString());
						Row.createCell(3).setCellValue(pR.getApprovedon() == null || pR.getApprovedon().toString() == "" ? "NA" : pR.getApprovedon().toString());
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment; filename=BlackedCustomers.xls");
					workbook.write(out);
					System.out.println("Excel written successfully..");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					BlackListed = null;
				}
			}
	}
		
	public static String stringtoCalenderDDMMYYY(String datestr) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		String ds2 = sdf2.format(sdf1.parse(datestr));
		return ds2;
	}

	public static String stringtoCalenderDDMMYYYHHMMSS(String datestr) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String ds2 = sdf2.format(sdf1.parse(datestr));
		return ds2;
	}

	public static String getTwoYearsOldDate() throws ParseException {
		Calendar now = Calendar.getInstance();
		now = Calendar.getInstance();
		now.add(Calendar.YEAR, -2);
		Date old = now.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String oldDate = df.format(old);
		System.out.println(oldDate);
		return oldDate;
	}
	
	@RequestMapping(value = "/termCellReport", method = RequestMethod.GET)
	public String termCellReport(@RequestParam(value = "mandalsID", required = false) String mandalId,
	@RequestParam(value = "districtID", required = false) String districtId,
	@RequestParam(value = "stdCodesID", required = false) String stdCodeNum,
	@RequestParam(value = "landLineNum", required = false) String landLineNum,Model model ){
		logger.info("TERMCellReportController :: home() :: Start");
		List<Object[]> districtList = new ArrayList<Object[]>();
		List<Object[]> totalActiveLandLineNum = new ArrayList<Object[]>();
		String stateID="1";
		DateFormat dTFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date currentDate = new Date();
		String cDate= dTFormat.format(currentDate); 
		totalActiveLandLineNum=	reportsServiceImpl.getActiveLandLines(stateID, districtId, mandalId,stdCodeNum,landLineNum);
		int toalCount=totalActiveLandLineNum.size();
		districtList=tenantDaoImpl.getDistrictList(stateID);
		model.addAttribute("toalCount", toalCount);
		model.addAttribute("cDate",cDate);
		model.addAttribute("districtList",districtList);
		
		logger.info("TERMCellReportController :: home() :: End");
		return "termCellReport";
	}
	
	@RequestMapping(value = "/getStdCodesByDist", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getStdCodesByDist( @RequestParam("districtName") String districtId, Model model){
		logger.info("TERMCellReportController :: home() :: Start");
		List<String> stdCodes = new ArrayList<String>();
		String stateID="1";
		stdCodes=tenantDaoImpl.getStdCodesByDist(stateID,districtId);
		logger.info("TERMCellReportController :: home() :: End");
		return stdCodes;
	}
	
	@RequestMapping(value = "/getMandalsByDistStdCode", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getMandalsByDistStdCode( @RequestParam("districtName") String districtId,@RequestParam("stdCodeNum") String stdCodeNum, Model model){
		logger.info("TERMCellReportController :: home() :: Start");
		List<String> mandals = new ArrayList<String>();
		String stateID="1";
		mandals=tenantDaoImpl.getMandalsByDistStdCode(stateID,districtId,stdCodeNum);
		logger.info("TERMCellReportController :: home() :: End");
		return mandals;
	}
	
	@RequestMapping(value = "/getActiveLandLines", method = RequestMethod.GET)
	@ResponseBody
	public List<Object[]> getActiveLandLines(@RequestParam("mandalName") String mandalId,@RequestParam("districtName") String districtId,@RequestParam("stdCodeNum") String stdCodeNum,@RequestParam("landLineNum") String landLineNum, Model model){
		logger.info("TERMCellReportController :: home() :: Start");
		List<Object[]> stdCodes = new ArrayList<Object[]>();
		String stateID="1";
		stdCodes=reportsServiceImpl.getActiveLandLines(stateID, districtId, mandalId,stdCodeNum,landLineNum);
		logger.info("TERMCellReportController :: home() :: End");
		return stdCodes;
	}
	
	@RequestMapping(value = "/termCellReportDownload", method = RequestMethod.GET)
	public void termCellReportDownload(@RequestParam(value = "mandalsID", required = false) String mandalId,
			@RequestParam(value = "districtID", required = false) String districtId,
			@RequestParam(value = "stdCodesID", required = false) String stdCodeNum,
			@RequestParam(value = "landLineNum", required = false) String landLineNum,
			HttpServletRequest request, HttpServletResponse response){
		 
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = reportsServiceImpl.termCellReportDownload(mandalId,districtId,stdCodeNum,landLineNum);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=TermCellReport.xls");
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	 
	/* Customer Activity Log Report */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/customerPaymentDtls", method = RequestMethod.GET)
	public String customerPaymentDtls(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "custId", required = false) String custId,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2) throws IOException {

		List<Object[]> customerlist = new ArrayList<>();
		List<PaymentVO> paymentVOList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		StringBuilder builder1 = new StringBuilder();
		ReportsVO reportsVO = null;
		String message1 = null;
		String message = null;
		int count = 1;
		String customerId = custId;

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try {
			logger.info("ReportsController :: customerPaymentDtls() :: Start");
				count++;
				Date newDate=new Date();
				Date newDate1=new Date();
				if (date1 != null && date2 != null && !date1.isEmpty() && !date2.isEmpty()) {
					 newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					 newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
				}
				
				String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);

				message1 = "Report was Generated on " + currDate;

				if (!customerId.isEmpty() && date1.isEmpty() && date2.isEmpty()) {
					builder = new StringBuilder(" select p.pmntid, p.acctcafno,p.pmntcustid,p.pmntmodelov,DATE_FORMAT(p.pmntdate,'%d-%m-%Y')vpmntdate,p.pmntamt,cst.custname,cst.mname,cst.lname ");
					builder.append(" from payments p,customermst cst where cst.custid=p.pmntcustid  and p.pmntcustid='"+customerId+"'");
				} 
				else if (date1 != null && date2 != null && customerId.isEmpty()) {
					builder = new StringBuilder(" select p.pmntid, p.acctcafno,p.pmntcustid,p.pmntmodelov,DATE_FORMAT(p.pmntdate,'%d-%m-%Y') pmntdate,p.pmntamt,cst.custname,cst.mname,cst.lname ");
					builder.append(" from payments p,customermst cst where cst.custid=p.pmntcustid  and p.pmntdate BETWEEN '"+fromDate+"' AND '"+toDate+"'");
				}
				else if (date1 != null && date2 != null && !customerId.isEmpty()) {
					builder = new StringBuilder(" select p.pmntid, p.acctcafno,p.pmntcustid,p.pmntmodelov,DATE_FORMAT(p.pmntdate,'%d-%m-%Y') pmntdate,p.pmntamt,cst.custname,cst.mname,cst.lname ");
					builder.append(" from payments p,customermst cst where cst.custid=p.pmntcustid  and p.pmntdate BETWEEN '"+fromDate+"' AND '"+toDate+"' and p.pmntcustid='"+customerId+"'");
				}
				
				customerlist = reportsServiceImpl.getListByQuery(builder.toString());
				reportsVO = new ReportsVO();

				for (Object[] obj : customerlist) {

					PaymentVO paymentVO = new PaymentVO();
					paymentVO.setPmntId(obj[0] == null ? "NA" : obj[0].toString());
					paymentVO.setAcctCafNo(obj[1] == null ? "NA" : obj[1].toString());
					paymentVO.setPmntCustId(obj[2] == null || obj[2] == "" ? "NA" : obj[2].toString());
					paymentVO.setPaymentMode(obj[3] == null || obj[3] == "" ? "NA" : obj[3].toString());
					paymentVO.setPmntDate(obj[4] == null || obj[4] == "" ? "NA" : obj[4].toString());
					paymentVO.setPaidAmount(obj[5] == null || obj[5] == "" ? (float) obj[5] : Float.parseFloat(obj[5].toString()));
					paymentVO.setCustomerName(obj[6] == null ? "" : obj[6].toString());
					paymentVO.setmName(obj[7] == null ? "" : obj[7].toString());
					paymentVO.setlName(obj[8] == null ? "" : obj[8].toString());
					paymentVOList.add(paymentVO);
				}

				if (paymentVOList == null) {
					message = "No Records Found For This Search Criteria";
					message1 = null;
				}
		} catch (Exception ex) {
			logger.info("ReportsController :: getCafWiseDates() :: " + ex);
			ex.printStackTrace();
		} finally {
			builder = null;
			builder1 = null;
		}
		uiModel.addAttribute("paymentVOList", paymentVOList);
		uiModel.addAttribute("custId", custId);
		uiModel.addAttribute("fromDate", date1);
		uiModel.addAttribute("toDate", date2);
		uiModel.addAttribute("message1", message1);
		uiModel.addAttribute("message", message);
		uiModel.addAttribute("count", count);
		uiModel.addAttribute("id", 15);

		return "customerPaymentDtls";
	}
	
	/* Customer Activity Log Report download */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/downloadcustomerPaymentDtls", method = RequestMethod.GET)
	public void downloadcustomerPaymentDtls(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "custId", required = false) String custId,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2) throws IOException, ParseException {

		List<Object[]> customerlist = new ArrayList<>();
		List<PaymentVO> paymentVOList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		StringBuilder builder1 = new StringBuilder();
		ReportsVO reportsVO = null;
		String message1 = null;
		String message = null;
		int count = 1;
		String customerId = custId;

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Customer Payment Details");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				
				logger.info("ReportsController :: customerPaymentDtls() :: Start");
				count++;
				Date newDate=new Date();
				Date newDate1=new Date();
				if (date1 != null && date2 != null && !date1.isEmpty() && !date2.isEmpty()) {
					 newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					 newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
				}
				
				String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
				String toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);

				message1 = "Report was Generated on " + currDate;

				if (!customerId.isEmpty() && date1.isEmpty() && date2.isEmpty()) {
					builder = new StringBuilder(" select p.pmntid, p.acctcafno,p.pmntcustid,p.pmntmodelov,DATE_FORMAT(p.pmntdate,'%d-%m-%Y')vpmntdate,p.pmntamt,cst.custname,cst.mname,cst.lname ");
					builder.append(" from payments p,customermst cst where cst.custid=p.pmntcustid  and p.pmntcustid='"+customerId+"'");
				} 
				else if (date1 != null && date2 != null && customerId.isEmpty()) {
					builder = new StringBuilder(" select p.pmntid, p.acctcafno,p.pmntcustid,p.pmntmodelov,DATE_FORMAT(p.pmntdate,'%d-%m-%Y') pmntdate,p.pmntamt,cst.custname,cst.mname,cst.lname ");
					builder.append(" from payments p,customermst cst where cst.custid=p.pmntcustid  and p.pmntdate BETWEEN '"+fromDate+"' AND '"+toDate+"'");
				}
				else if (date1 != null && date2 != null && !customerId.isEmpty()) {
					builder = new StringBuilder(" select p.pmntid, p.acctcafno,p.pmntcustid,p.pmntmodelov,DATE_FORMAT(p.pmntdate,'%d-%m-%Y') pmntdate,p.pmntamt,cst.custname,cst.mname,cst.lname ");
					builder.append(" from payments p,customermst cst where cst.custid=p.pmntcustid  and p.pmntdate BETWEEN '"+fromDate+"' AND '"+toDate+"' and p.pmntcustid='"+customerId+"'");
				}
				
				customerlist = reportsServiceImpl.getListByQuery(builder.toString());
				reportsVO = new ReportsVO();

				for (Object[] obj : customerlist) {

					PaymentVO paymentVO = new PaymentVO();
					paymentVO.setPmntId(obj[0] == null ? "NA" : obj[0].toString());
					paymentVO.setAcctCafNo(obj[1] == null ? "NA" : obj[1].toString());
					paymentVO.setPmntCustId(obj[2] == null || obj[2] == "" ? "NA" : obj[2].toString());
					paymentVO.setPaymentMode(obj[3] == null || obj[3] == "" ? "NA" : obj[3].toString());
					paymentVO.setPmntDate(obj[4] == null || obj[4] == "" ? "NA" : obj[4].toString());
					paymentVO.setPaidAmount(obj[5] == null || obj[5] == "" ? (float) obj[5] : Float.parseFloat(obj[5].toString()));
					paymentVO.setCustomerName(obj[6] == null ? "NA" : obj[6].toString());
					paymentVO.setmName(obj[7] == null ? "" : obj[7].toString());
					paymentVO.setlName(obj[8] == null ? "" : obj[8].toString());
					paymentVOList.add(paymentVO);
				}

				if (paymentVOList == null) {
					message = "No Records Found For This Search Criteria";
					message1 = null;
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
				cell.setCellValue("Customer Payment Details Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				// For creating third row
				cell = row2.createCell(0);
				cell.setCellValue("From Date");
				cell.setCellStyle(style);

				cell = row2.createCell(1);
				cell.setCellValue(date1);
				cell.setCellStyle(style);

				cell = row2.createCell(2);
				cell.setCellValue("To Date");
				cell.setCellStyle(style);

				cell = row2.createCell(3);
				cell.setCellValue(date2);
				cell.setCellStyle(style);

				cell = row2.createCell(4);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);
				
				header.createCell(0).setCellValue("S No.");
				header.getCell(0).setCellStyle(style);
				
				header.createCell(1).setCellValue("Payment CustId");
				header.getCell(1).setCellStyle(style);
				
				header.createCell(2).setCellValue("Customer Name");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("Account Caf No");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("Payment id");
				header.getCell(4).setCellStyle(style);
				
				header.createCell(5).setCellValue("Payment Amount");
				header.getCell(5).setCellStyle(style);

				header.createCell(6).setCellValue("Payment Mode");
				header.getCell(6).setCellStyle(style);

				header.createCell(7).setCellValue("Payment Date");
				header.getCell(7).setCellStyle(style);

				// create data rows
				int rowCount = 5;
				int totalcount=0;
				for (PaymentVO obj : paymentVOList) {
					aRow = sheet.createRow(rowCount++);

					aRow.createCell(0).setCellValue(++totalcount);
					aRow.createCell(1).setCellValue(obj.getPmntCustId() == null || obj.getPmntCustId() == "" ? "NA" : obj.getPmntCustId());
					aRow.createCell(2).setCellValue(obj.getCustomerName()+" "+obj.getmName() +" "+obj.getlName());
					aRow.createCell(3).setCellValue(obj.getAcctCafNo() == null || obj.getAcctCafNo() == "" ? "NA" : obj.getAcctCafNo());
					aRow.createCell(4).setCellValue(obj.getPmntId() == null || obj.getPmntId() == "" ? "NA" : obj.getPmntId());
					aRow.createCell(5).setCellValue(obj.getPaidAmount());
					aRow.createCell(6).setCellValue(obj.getPaymentMode() == null || obj.getPaymentMode() == "" ? "NA" : obj.getPaymentMode());
					aRow.createCell(7).setCellValue(obj.getPmntDate() == null || obj.getPmntDate() == "" ? "NA" : obj.getPmntDate());
				}
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=PaymentDetails.xls");
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
	
	
	
	/* Total No Of cpes */
	@RequestMapping(value = "/cpeInformation", method = RequestMethod.GET)
	public String getCpeSearchInformation(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			//@RequestParam(value = "districtList", required = false) String districtList,
			@RequestParam(value = "districtuid", required = false) String distUid,
			@RequestParam(value = "radio", required = false) String radio) throws ParseException, IOException {

		
		String message = "";
		StringBuilder builder = new StringBuilder();
		String message1 = null;
		CpeReportDTO cpeDetails=null;
		Integer total=0;
		String fromDate = "";
		String toDate = "";
		HttpSession session;
		
	
		String count="";

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate1 = this.getTwoYearsOldDate();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String toDate1 = (dateFormat.format(new Date()));
		HttpEntity<String> httpEntity;
		@SuppressWarnings("rawtypes")
		ResponseEntity<ArrayList> responce;
		String url = "";
		
		try {
			logger.info("ReportsController :: getCpeSearchInformation() :: Start");
			
			httpEntity = ApsflHelper.getHttpEntity(ipAddressValues.getComUserName(),
					ipAddressValues.getComPwd());
			 url = ipAddressValues.getComURL() + "alldistricts";
			responce = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					ArrayList.class);
			ArrayList<Districts> districtsList = responce.getBody();
			uiModel.addAttribute("districtList", districtsList);
			
			
			if (radio!=null)
			if (radio.equals("dateRange")) {
				if (date1 != null && date2 != null) {
					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
					
					/*String newDate = (dateFormat.format(date1));
					String newDate1 = (dateFormat.format(date2)); 
					fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
					toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);*/
					
				}
			} else {
				logger.info("ReportsController :: getCpeSearchInformation() :: Else");

				toDate = (dateFormat.format(new Date()));
				logger.info("todate =" + toDate);
				fromDate = getTwoYearsOldDate();
				logger.info("fromdate =" + fromDate);
			}
			if (fromDate != null && toDate != null) {

				message1 = "Report was Generated on " + currDate;
				
				cpeDetails=new CpeReportDTO();
			

						count = reportsServiceImpl.getCpeCount(fromDate, toDate, "IPTV", "active",distUid);
						cpeDetails.setIptvActive(count);
						
						total+=Integer.valueOf(count);

						count = reportsServiceImpl.getCpeCount(fromDate, toDate, "IPTV", "notactive",distUid);
						cpeDetails.setIptvNotActive(count);
						
						total+=Integer.valueOf(count);
						count = reportsServiceImpl.getCpeCount(fromDate, toDate, "IPTV", "defective",distUid);
						cpeDetails.setIptvDefective(count);
						
						total+=Integer.valueOf(count);
						count = reportsServiceImpl.getCpeCount(fromDate, toDate, "ONU", "active",distUid);
						cpeDetails.setOnuActive(count);
						
						total+=Integer.valueOf(count);
						count = reportsServiceImpl.getCpeCount(fromDate, toDate, "ONU", "notactive",distUid);
						cpeDetails.setOnuNotActive(count);
						
						total+=Integer.valueOf(count);
						count = reportsServiceImpl.getCpeCount(fromDate, toDate, "ONU", "defective",distUid);
						cpeDetails.setOnuDefective(count);
						total+=Integer.valueOf(count);
				
						cpeDetails.setTotal(String.valueOf(total));

				if (cpeDetails.getIptvActive().isEmpty() && cpeDetails.getIptvNotActive().isEmpty()&& cpeDetails.getIptvDefective().isEmpty()
						&& cpeDetails.getOnuActive().isEmpty() && cpeDetails.getOnuNotActive().isEmpty()&& cpeDetails.getOnuDefective().isEmpty()) {
					message = "No Records Found For This Search Criteria";
					message1 = null;
				}

			}
			logger.info("ReportsController :: getCpeSearchInformation() :: End");
		} catch (Exception ex) {
			logger.info("ReportsController :: getCpeSearchInformation() :: " + ex);
			ex.printStackTrace();
		} finally {
			builder = null;
		}
		session = request.getSession(false);
		uiModel.addAttribute("message", message);
		session.setAttribute("cpeDetails", cpeDetails);
		uiModel.addAttribute("date1", fromDate);
		uiModel.addAttribute("date2", toDate);
		uiModel.addAttribute("message1", message1);
		uiModel.addAttribute("radio", radio);
		uiModel.addAttribute("fromDate1", fromDate1);
		uiModel.addAttribute("toDate1", toDate1);
		uiModel.addAttribute("districtuid", distUid);
	
		return "cpeInformation";
	}

	@RequestMapping(value = "/getStatusWiseCpeDetails", method = RequestMethod.GET)
	public String getStatusWiseCpeDetails(HttpServletRequest request, HttpServletResponse response,
			Model uiModel, @RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "districtuid", required = false) String distUid,
			@RequestParam(value = "cpetype", required = false) String cpetype) throws IOException {
		List<CpeStockVO> list = new ArrayList<CpeStockVO>();
		HttpSession session;
		try {
			
			list = reportsServiceImpl.getCPEStockDetails(date1, date2, cpetype, status,distUid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session = request.getSession(false);
		session.setAttribute("cpeReportlist", list);
		uiModel.addAttribute("cpeReportlist", list);
		uiModel.addAttribute("cpetypeIPTV", "IPTV");
		uiModel.addAttribute("cpetypeONU", "ONU");
		uiModel.addAttribute("active", "active");
		uiModel.addAttribute("notactive", "notactive");
		uiModel.addAttribute("defective", "defective");

		return "cpeStatusWiseInfo";
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cpeHistoryDownload", method = RequestMethod.GET)
	public  void cpeStockDownload(
			HttpServletRequest request ,HttpServletResponse httpResponse) {
		ResponseEntity<ArrayList> response;
		HttpSession session;
		HSSFWorkbook workbook = null;
	    List<CpeStockVO> cpeStckList = new ArrayList<CpeStockVO>();
		FileUtil fileUtil=new FileUtil();
		try {
			session = request.getSession(false);
			cpeStckList = (List<CpeStockVO>) session.getAttribute("cpeReportlist");
			
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			List<CpeStockVO> cpeStockLists = mapper.readValue(gson.toJson(cpeStckList),TypeFactory.defaultInstance().constructCollectionType(List.class, CpeStockVO.class));
			workbook = fileUtil.cpeStockDownload(cpeStockLists,httpResponse);
		} catch (Exception e) {
			logger.error("IN cpeHistoryDownload DOWNLOAD");
			e.printStackTrace();
		}
		logger.info("after cpeHistoryDownload " + cpeStckList.size());
	}
	
	
	
	/* Total No Of Subscribers download */
	@RequestMapping(value = "/downloadCpeInfo", method = RequestMethod.GET)
	public void downloadTotalNoOfCpe(HttpServletRequest request, HttpServletResponse response, Model uiModel,
			@RequestParam(value = "download", required = false) boolean download,
			@RequestParam(value = "date1", required = false) String date1,
			@RequestParam(value = "date2", required = false) String date2,
			@RequestParam(value = "radio", required = false) String radio) throws ParseException, IOException {

		List<Object[]> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		HttpSession session;
		CpeReportDTO cpeDetails;
		 String iptvActive="";
		 String iptvNotActive="";
		 String iptvDefective="";
		 String onuActive="";
		 String onuNotActive="";
		 String onuDefective="";
		 String total="";

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Cpe Infromation");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = "";
		String toDate = "";
		if (download) {
			try (ServletOutputStream out = response.getOutputStream();
					InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
				
				if (radio.equals("dateRange")) {
					if (date1 != null && date2 != null) {
						Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
						Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
						fromDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
						toDate = new SimpleDateFormat("yyyy-MM-dd").format(newDate1);
					}
				} else {
					logger.info("ReportsController :: downloadCpeInfo() :: Else");

					toDate = (dateFormat.format(new Date()));
					logger.info("todate =" + toDate);
					fromDate = getTwoYearsOldDate();
					logger.info("fromdate =" + fromDate);
				}
				/*if (fromDate != null && toDate != null) {

					builder = new StringBuilder("SELECT cs.status AS statusvalue, CASE WHEN cs.status = "+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' WHEN cs.status = "+ CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus() + "  THEN 'Suspended' ");
					builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus()+ " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+ " THEN 'Blacklisted' END STATUS, ");
					builder.append(" COUNT(DISTINCT cf.cafno) Total_Subscribers FROM cafsrvcs cs, srvcs s, cafs cf WHERE cs.srvccode = s.srvccode AND s.coresrvccode='IPTV' ");
					builder.append(" AND cs.parentcafno = cf.cafno AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto  AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
					builder.append(" GROUP BY cs.status, CASE WHEN cs.status = "+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' WHEN cs.status = "+ CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus() + "  THEN 'Suspended' ");
					builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus()+ " THEN 'Deactivated' WHEN cs.status = "+CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+" THEN 'Blacklisted' END ");

					list = reportsServiceImpl.getListByQuery(builder.toString());
				}*/

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

				/* For creating first row */
				Cell cell = row.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				row.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = row1.createCell(2);
				cell.setCellValue("Total No Of Subscribers Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				if (radio.equals("dateRange")) {

					Date newDate = new SimpleDateFormat("MM/dd/yyyy").parse(date1);
					Date newDate1 = new SimpleDateFormat("MM/dd/yyyy").parse(date2);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);
					toDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate1);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);

				} else {

					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					toDate = (df.format(new Date()));
					String tempDate = getTwoYearsOldDate();
					Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);
					fromDate = new SimpleDateFormat("dd-MM-yyyy").format(newDate);

					/* For creating third row */
					cell = row2.createCell(0);
					cell.setCellValue("From Date");
					cell.setCellStyle(style);

					cell = row2.createCell(1);
					cell.setCellValue(fromDate);
					cell.setCellStyle(style);

					cell = row2.createCell(2);
					cell.setCellValue("To Date");
					cell.setCellStyle(style);

					cell = row2.createCell(3);
					cell.setCellValue(toDate);
					cell.setCellStyle(style);
				}

				cell = row2.createCell(4);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = row2.createCell(5);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("Status");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Cpe Count");
				header.getCell(1).setCellStyle(style);

				// create data rows
				int rowCount = 5;
				
				session = request.getSession(false);
				
				cpeDetails = (CpeReportDTO) session.getAttribute("cpeDetails");
				
				  iptvActive=cpeDetails.getIptvActive();
				  iptvNotActive=cpeDetails.getIptvNotActive();
				  iptvDefective=cpeDetails.getIptvDefective();
				  onuActive=cpeDetails.getOnuActive();
				  onuNotActive=cpeDetails.getOnuNotActive();
				  onuDefective=cpeDetails.getOnuDefective();
				  total=cpeDetails.getTotal();
				
				  aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue("IPTV CAFs done");
					aRow.createCell(1).setCellValue(iptvActive == null || iptvActive == "" ? "NA" : iptvActive);
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue("IPTV CAFs not done");
					aRow.createCell(1).setCellValue(iptvNotActive == null || iptvNotActive == "" ? "NA" : iptvNotActive);
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue("IPTV Defective Devices");
					aRow.createCell(1).setCellValue(iptvDefective == null || iptvDefective == "" ? "NA" : iptvDefective);
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue("ONU CAFs done");
					aRow.createCell(1).setCellValue(onuActive == null || onuActive == "" ? "NA" : onuActive);
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue("IPTV CAFs not done");
					aRow.createCell(1).setCellValue(onuNotActive == null || onuNotActive == "" ? "NA" : onuNotActive);
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue("ONU Defective Devices");
					aRow.createCell(1).setCellValue(onuDefective == null || onuDefective == "" ? "NA" : onuDefective);
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue("Total");
					aRow.createCell(1).setCellValue(total == null || total == "" ? "NA" : total);
				
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=statusWiseCPEInfo.xls");
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				builder = null;
				workbook = null;
				sheet = null;
				header = null;
				aRow = null;
			}
		}
	}
	
	
	
	
}
