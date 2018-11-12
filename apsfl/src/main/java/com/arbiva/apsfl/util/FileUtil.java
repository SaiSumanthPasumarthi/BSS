package com.arbiva.apsfl.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.arbiva.apfgc.tenant.bo.CafWiseRevenueOfLoginLmoBo;
import com.arbiva.apfgc.tenant.bo.MsoRevenueShareBO;
import com.arbiva.apfgc.tenant.bo.Offline_Payment1;
import com.arbiva.apsfl.coms.dto.CafAndCpeChargesVO;
import com.arbiva.apsfl.coms.dto.EntCafStage;
import com.arbiva.apsfl.coms.dto.FingerPrintBO;
import com.arbiva.apsfl.coms.dto.MultiAction;
import com.arbiva.apsfl.coms.dto.VPNSrvcNamesStage;
import com.arbiva.apsfl.dto.Offline_Payment;

import com.arbiva.apsfl.tms.dto.CpeStockVO;
import com.arbiva.apsfl.tms.dto.CustomerInvDtlsDTO;
import com.arbiva.apsfl.tms.model.CafMaster;
import com.arbiva.apsfl.tms.model.CpeStock;
import com.arbiva.apsfl.tt.dto.TroubleTicketDTO;

@Component("FileUtil")
public class FileUtil {

	private static final Logger logger = Logger.getLogger(FileUtil.class);

	public String saveImage(String fileName, String basepath, byte[] bs, String loginId, String docType){
		String path = null;
		File serverFile = null;
		File directory = new File(basepath + File.separator + loginId + File.separator + docType);
		if (!directory.exists())
			directory.mkdirs();

		path = new String(directory.getAbsolutePath() + File.separator + fileName);
		serverFile = new File(path);

		if (fileName == null || fileName.isEmpty())
			path = "";
		else {

			try (FileOutputStream fos = new FileOutputStream(serverFile);
					BufferedOutputStream stream = new BufferedOutputStream(fos)) {
				stream.write(bs);

			} catch (Exception e) {
				logger.error("FileUtil::saveImage() " + e);
				e.printStackTrace();
			} finally {
				serverFile = null;
				directory = null;
			}

		}

		logger.info("The Documents Stored in to folder.");
		return path;
	}

	public static void cafBulkUplaodErrorDownloadExcel(List<EntCafStage> errorList,HttpServletResponse httpResponse ) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("cafBulkUploadError");
		// create header row
		Row header = sheet.createRow(0);

		try (ServletOutputStream out = httpResponse.getOutputStream()) {

			CellStyle style = workbook.createCellStyle();
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			header.createCell(0).setCellValue(redColor(workbook,"APSFL Code*", 10, 11));
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue(redColor(workbook, "District*", 8, 9));
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue(redColor(workbook, "Mandal*", 6, 7));
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue(redColor(workbook, "ONT Location*", 12, 13));
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Organization Name and Address");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue(redColor(workbook, "Contact Person Mobile No*", 24, 25));
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue(redColor(workbook, "Contact Person Name*", 19, 20));
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("Contact Person Designation");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("Email");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue(redColor(workbook, "Payment Responsible*", 19, 20));
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue(redColor(workbook, "SI LMO Code*", 11, 12));
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("Longitude");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("Latitude");
			header.getCell(12).setCellStyle(style);

			header.createCell(13).setCellValue("Remarks");
			header.getCell(13).setCellStyle(style);

			// create data rows
			int rowCount = 1;
			for (EntCafStage entCafStage : errorList) {
				HSSFRow Row = sheet.createRow(rowCount++);
				Row.createCell(0).setCellValue(entCafStage.getApsflUniqueId() == null ? "" : entCafStage.getApsflUniqueId());
				Row.createCell(1).setCellValue(entCafStage.getDistrict() == null ? "" : entCafStage.getDistrict());
				Row.createCell(2).setCellValue(entCafStage.getMandal() == null	? "" : entCafStage.getMandal());
				if(entCafStage.getLocation() != null && entCafStage.getLocation().contains("^")) {
					String[] location = entCafStage.getLocation().split("\\^");
					Row.createCell(3).setCellValue(location[1] == null ? "" : location[1]);
					Row.createCell(4).setCellValue(location[0] == null ? "" : location[0]);
				} else if(entCafStage.getLocation() != null && entCafStage.getLocation().contains(",")) {
					String[] location = entCafStage.getLocation().split(",");
					Row.createCell(3).setCellValue(location[1] == null ? "" : location[1]);
					Row.createCell(4).setCellValue(location[0] == null ? "" : location[0]);
				} else {
					Row.createCell(3).setCellValue(entCafStage.getLocation() == null ? "" : entCafStage.getLocation());
					Row.createCell(4).setCellValue("");
				}
				Row.createCell(5).setCellValue(entCafStage.getContactMobileNo() == null ? "" : entCafStage.getContactMobileNo());
				if(entCafStage.getContactName() != null && entCafStage.getContactName().indexOf("(") != -1) {
					String[] contactName = entCafStage.getContactName().split("\\(");
					Row.createCell(6).setCellValue(contactName[0]);
					Row.createCell(7).setCellValue(contactName[1].replace(")", ""));
				} else {
					Row.createCell(6).setCellValue( entCafStage.getContactName() == null ? "" : entCafStage.getContactName());
					Row.createCell(7).setCellValue("");
				}
				Row.createCell(8).setCellValue(entCafStage.getContactEmail() == null	? "" : entCafStage.getContactEmail());
				Row.createCell(9).setCellValue(entCafStage.getPmntrespFlag() == null ? "" : entCafStage.getPmntrespFlag());
				Row.createCell(10).setCellValue(entCafStage.getLmocode() == null	? "" : entCafStage.getLmocode());
				Row.createCell(11).setCellValue(entCafStage.getLongitude() == null	? "" : entCafStage.getLongitude());
				Row.createCell(12).setCellValue(entCafStage.getLattitude() == null	? "" : entCafStage.getLattitude());
				Row.createCell(13).setCellValue(entCafStage.getRemarks() == null	? "" : entCafStage.getRemarks());
			}
			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename=cafBulkUploadError.xls");
			workbook.write(out);
			System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) {
			logger.error("cafBulkUploadError");
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	
	public static void vpnUplaodErrorDownloadExcel(List<VPNSrvcNamesStage> errorList, HttpServletResponse httpResponse) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("VPNUploadError");
		// create header row
		Row header = sheet.createRow(0);

		try (ServletOutputStream out = httpResponse.getOutputStream()) {


			CellStyle style = workbook.createCellStyle();
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			//Red color for Star


			header.createCell(0).setCellValue(redColor(workbook, "subStationUid*", 13, 14));
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue(redColor(workbook, "oltSerialNo*", 11, 12));
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue(redColor(workbook, "VPNServiceName*", 14, 15));
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Remarks");
			header.getCell(3).setCellStyle(style);


			// create data rows
			int rowCount = 1;
			for (VPNSrvcNamesStage entCafStage : errorList) {
				HSSFRow Row = sheet.createRow(rowCount++);
				Row.createCell(0).setCellValue(entCafStage.getSubstnUid() == null	? "" : entCafStage.getSubstnUid().toString());
				Row.createCell(1).setCellValue(entCafStage.getOltSerialNo() == null ? "" : entCafStage.getOltSerialNo().toString());
				Row.createCell(2).setCellValue(entCafStage.getVpnsrvcName() == null	? "" : entCafStage.getVpnsrvcName().toString());
				Row.createCell(3).setCellValue(entCafStage.getRemarks() == null	? "" : entCafStage.getRemarks().toString());
			}
			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename=VPNUploadError.xls");
			workbook.write(out);
			System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) {
			logger.error("cafBulkUploadError");
			e.printStackTrace();
		} finally {
			workbook = null;
		}
	}
	public static HSSFRichTextString redColor(HSSFWorkbook workbook, String word, int start, int end) {
		//HSSFWorkbook workbook = new HSSFWorkbook();
		Font fontw = workbook.createFont();
		fontw.setFontHeightInPoints((short) 15);
		fontw.setColor(HSSFColor.RED.index);
		fontw.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//String sub = "subStationUid*";
		HSSFRichTextString richString = new HSSFRichTextString(word);
		richString.applyFont(start, end, fontw);
		return richString;
	}


	@SuppressWarnings("rawtypes")
	public HSSFWorkbook multiActionCafDownload(List<CafAndCpeChargesVO> cafAndCpeChargesVOList,HttpServletResponse httpResponse ) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("workorder");
		// create header row
		Row header = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try (ServletOutputStream out = httpResponse.getOutputStream();
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
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			// For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("Work Order");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("SNo.");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("Customer/Organization Name");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("CAF Type");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("APSFL TrackId");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Location");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("LMO Code");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("CAF No");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("Aadhar/Regristration No");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("Pop Name");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("CAF Date");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("ONU SrlNo");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("ONU MacAddress");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("ONU Place");
			header.getCell(12).setCellStyle(style);

			header.createCell(13).setCellValue("IPTV SerialNo - IPTV MacAddress");
			header.getCell(13).setCellStyle(style);

			header.createCell(14).setCellValue("Landline No");
			header.getCell(14).setCellStyle(style);

			header.createCell(15).setCellValue("Status");
			header.getCell(15).setCellStyle(style);

			// create data rows
			int rowCount = 5;
			int i=0;
			for (CafAndCpeChargesVO cafCpeChargesVO : cafAndCpeChargesVOList) {
				HSSFRow Row = sheet.createRow(rowCount++);
				StringBuilder stbSrlNoMacAddr =new StringBuilder();
				for (Map.Entry map : cafCpeChargesVO.getStbSrlNoMacAddrMap().entrySet()) {
					stbSrlNoMacAddr.append(map.getKey() + " - " + map.getValue() + " , ");
				}
				if(stbSrlNoMacAddr.toString().trim().equalsIgnoreCase("- ,"))
				{
					stbSrlNoMacAddr.deleteCharAt(stbSrlNoMacAddr.lastIndexOf("-"));
					stbSrlNoMacAddr.deleteCharAt(stbSrlNoMacAddr.lastIndexOf(","));
				}else{
					stbSrlNoMacAddr.deleteCharAt(stbSrlNoMacAddr.lastIndexOf(","));
				}
				String fname = cafCpeChargesVO.getfName() == null ? "" : cafCpeChargesVO.getfName();
				String lname = cafCpeChargesVO.getlName() == null ? "" : cafCpeChargesVO.getlName();
				String name =  fname+" "+lname;
				Row.createCell(0).setCellValue(++i);
				Row.createCell(1).setCellValue(name);
				Row.createCell(2).setCellValue(cafCpeChargesVO.getCafType() == null ? "" : cafCpeChargesVO.getCafType());
				Row.createCell(3).setCellValue(cafCpeChargesVO.getApsflTrackId() == null	? "" : cafCpeChargesVO.getApsflTrackId());
				Row.createCell(4).setCellValue(cafCpeChargesVO.getCpeplace() == null	? "" : cafCpeChargesVO.getCpeplace());
				Row.createCell(5).setCellValue(cafCpeChargesVO.getTenantCode() == null	? "" : cafCpeChargesVO.getTenantCode());
				Row.createCell(6).setCellValue(cafCpeChargesVO.getCafNo() == null	? "" : cafCpeChargesVO.getCafNo());
				Row.createCell(7).setCellValue(cafCpeChargesVO.getCustCode() == null	? "" : cafCpeChargesVO.getCustCode());
				Row.createCell(8).setCellValue(cafCpeChargesVO.getPopName() == null	? "" : cafCpeChargesVO.getPopName());
				Row.createCell(9).setCellValue(cafCpeChargesVO.getCafdate() == null	? "" : cafCpeChargesVO.getCafdate());
				Row.createCell(10).setCellValue(cafCpeChargesVO.getCpeSlno() == null ? "" : cafCpeChargesVO.getCpeSlno());
				Row.createCell(11).setCellValue(cafCpeChargesVO.getCpeMacAddr() == null	? "" : cafCpeChargesVO.getCpeMacAddr());
				Row.createCell(12).setCellValue(cafCpeChargesVO.getCpeplace() == null	? "" : cafCpeChargesVO.getCpeplace());
				Row.createCell(13).setCellValue(stbSrlNoMacAddr.toString());
				Row.createCell(14).setCellValue(cafCpeChargesVO.getContactPersonMobileNo() == null	? "NA" : cafCpeChargesVO.getContactPersonMobileNo());
				Row.createCell(15).setCellValue(cafCpeChargesVO.getStatusDesc() == null	? "" : cafCpeChargesVO.getStatusDesc());
			}
			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename=workorder.xls");
			workbook.write(out);
			System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) {
			logger.error("multiActionCafsDownload");
			e.printStackTrace();
		} finally {
			workbook = null;
		}
		return workbook;
	}

	public HSSFWorkbook TTDownloadExcel(List<TroubleTicketDTO> ttHistoryList,HttpServletResponse httpResponse ) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("workorder");
		// create header row
		Row header = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try (ServletOutputStream out = httpResponse.getOutputStream();
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
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			// For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("Trouble Tickets List");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S No.");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("TT NO");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("Account No");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Issue Type");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Issue");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Name");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("OLT Serial No");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("Port");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("OLT ONU ID");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("Created On");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("Expected Closure Date");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("Actual Closed Time");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("Resolution Time");
			header.getCell(12).setCellStyle(style);

			header.createCell(13).setCellValue("Status");
			header.getCell(13).setCellStyle(style);

			header.createCell(14).setCellValue("Complaint");
			header.getCell(14).setCellStyle(style);

			header.createCell(15).setCellValue("Aadhar No");
			header.getCell(15).setCellStyle(style);

			header.createCell(16).setCellValue("Mobile No");
			header.getCell(16).setCellStyle(style);

			header.createCell(17).setCellValue("Landline No");
			header.getCell(17).setCellStyle(style);

			header.createCell(18).setCellValue("Assigned To");
			header.getCell(18).setCellStyle(style);

			header.createCell(19).setCellValue("Priority");
			header.getCell(19).setCellStyle(style);

			header.createCell(20).setCellValue("Reason");
			header.getCell(20).setCellStyle(style);

			header.createCell(21).setCellValue("Parent Ticket No");
			header.getCell(21).setCellStyle(style);

			header.createCell(22).setCellValue("Child Ticket No");
			header.getCell(22).setCellStyle(style);

			header.createCell(23).setCellValue("Issue Category");
			header.getCell(23).setCellStyle(style);

			header.createCell(24).setCellValue("District");
			header.getCell(24).setCellStyle(style);

			header.createCell(25).setCellValue("Substation");
			header.getCell(25).setCellStyle(style);

			header.createCell(26).setCellValue("Created By");
			header.getCell(26).setCellStyle(style);

			// create data rows
			int rowCount = 5;
			int i=0;
			for (TroubleTicketDTO troubleTicketDTO : ttHistoryList) {
				HSSFRow Row = sheet.createRow(rowCount++);

				Row.createCell(0).setCellValue(++i);
				Row.createCell(1).setCellValue(troubleTicketDTO.getTicketNo() == null ? "" : troubleTicketDTO.getTicketNo().toString());
				Row.createCell(2).setCellValue(troubleTicketDTO.getCafNo() == null ? "" : troubleTicketDTO.getCafNo().toString());
				Row.createCell(3).setCellValue(troubleTicketDTO.getIssueType() == null	? "" : troubleTicketDTO.getIssueType());
				Row.createCell(4).setCellValue(troubleTicketDTO.getIssue() == null	? "" : troubleTicketDTO.getIssue());
				Row.createCell(5).setCellValue(troubleTicketDTO.getCustName() == null	? "" : troubleTicketDTO.getCustName());
				Row.createCell(6).setCellValue(troubleTicketDTO.getOltSerialNo() == null	? "" : troubleTicketDTO.getOltSerialNo());
				Row.createCell(7).setCellValue(troubleTicketDTO.getPort() == null	? "" : troubleTicketDTO.getPort());
				Row.createCell(8).setCellValue(troubleTicketDTO.getOltONUID() == null	? "" : troubleTicketDTO.getOltONUID());
				Row.createCell(9).setCellValue(troubleTicketDTO.getCreatedOnDate() == null	? "" : troubleTicketDTO.getCreatedOnDate());
				Row.createCell(10).setCellValue(troubleTicketDTO.getExpectedClosureDate() == null ? "" : troubleTicketDTO.getExpectedClosureDate());
				Row.createCell(11).setCellValue(troubleTicketDTO.getModifiedOnString() == null ? "" : troubleTicketDTO.getModifiedOnString());
				Row.createCell(12).setCellValue(troubleTicketDTO.getTimeDiff() == null ? "" : troubleTicketDTO.getTimeDiff());
				Row.createCell(13).setCellValue(troubleTicketDTO.getCurrentStatus() == null	? "" : troubleTicketDTO.getCurrentStatus().toString());
				Row.createCell(14).setCellValue(troubleTicketDTO.getTicketDesc() == null	? "NA" : troubleTicketDTO.getTicketDesc());
				Row.createCell(15).setCellValue(troubleTicketDTO.getAadharNo() == null	? "" : troubleTicketDTO.getAadharNo());
				Row.createCell(16).setCellValue(troubleTicketDTO.getContactMobile() == null	? "" : troubleTicketDTO.getContactMobile());
				Row.createCell(17).setCellValue(troubleTicketDTO.getContactLandline() == null	? "" : troubleTicketDTO.getContactLandline());
				Row.createCell(18).setCellValue(troubleTicketDTO.getAssignedTo()==null ? "":troubleTicketDTO.getAssignedTo());
				Row.createCell(19).setCellValue(troubleTicketDTO.getTicketPriority() == null	? "NA" : troubleTicketDTO.getTicketPriority());
				Row.createCell(20).setCellValue(troubleTicketDTO.getTicketReason() == null	? "" : troubleTicketDTO.getTicketReason());
				Row.createCell(21).setCellValue(troubleTicketDTO.getParentticketno() == null	? "" : troubleTicketDTO.getParentticketno().toString());
				Row.createCell(22).setCellValue(troubleTicketDTO.getChildTicketNos() == null	? "" : troubleTicketDTO.getChildTicketNos());
				Row.createCell(23).setCellValue(troubleTicketDTO.getActualIssue()== null	? "" : troubleTicketDTO.getActualIssue());
				Row.createCell(24).setCellValue(troubleTicketDTO.getDistricts() == null	? "NA" : troubleTicketDTO.getDistricts());
				Row.createCell(25).setCellValue(troubleTicketDTO.getSubStationName() == null	? "" : troubleTicketDTO.getSubStationName());
				Row.createCell(26).setCellValue(troubleTicketDTO.getCreatedBy() == null	? "" : troubleTicketDTO.getCreatedBy());
			}
			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename=TroubleTicketsList.xls");
			workbook.write(out);
			System.out.println("Excel written successfully..");
		} catch (Exception e) {
			logger.error("TTDownloadExcel :" + ttHistoryList);
			e.printStackTrace();
		} finally {
		}
		return workbook;
	}

	public HSSFWorkbook cpeStockDownload(List<CpeStockVO> cpeStockLists,HttpServletResponse httpResponse ) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("workorder");
		// create header row
		Row header = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try (ServletOutputStream out = httpResponse.getOutputStream();
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
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			// For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("CPE Stock List");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S No.");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("CPE SrlNO");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("CPE Mac Address");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("CPE Type");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("CPE Model");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("MSP Code");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("LMO Code");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("District Name");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("Mandal Name");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("Village Name");
			header.getCell(9).setCellStyle(style);


			// create data rows
			int rowCount = 5;
			int i=0;
			for (CpeStockVO cpeStockVO : cpeStockLists) {
				HSSFRow Row = sheet.createRow(rowCount++);

				Row.createCell(0).setCellValue(++i);
				Row.createCell(1).setCellValue(cpeStockVO.getCpeSrlno() == null ? "" : cpeStockVO.getCpeSrlno().toString());
				Row.createCell(2).setCellValue(cpeStockVO.getMacAddress() == null ? "" : cpeStockVO.getMacAddress().toString());
				Row.createCell(3).setCellValue(cpeStockVO.getCpeTypeLov() == null	? "" : cpeStockVO.getCpeTypeLov());
				Row.createCell(4).setCellValue(cpeStockVO.getCpeModel() == null	? "" : cpeStockVO.getCpeModel());
				Row.createCell(5).setCellValue(cpeStockVO.getMspCode() == null	? "" : cpeStockVO.getMspCode());
				Row.createCell(6).setCellValue(cpeStockVO.getLmoCode() == null	? "" : cpeStockVO.getLmoCode());
				Row.createCell(7).setCellValue(cpeStockVO.getDistrictName() == null	? "" : cpeStockVO.getDistrictName());
				Row.createCell(8).setCellValue(cpeStockVO.getMandalName() == null	? "" : cpeStockVO.getMandalName());
				Row.createCell(9).setCellValue(cpeStockVO.getVillageName() == null	? "" : cpeStockVO.getVillageName());
			}
			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename=CpeStockInfo.xls");
			workbook.write(out);
			System.out.println("Excel written successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return workbook;
	}

	// new code 10\1\2018

	@SuppressWarnings("rawtypes")
	public HSSFWorkbook multiActionCafDownload1(List<CafAndCpeChargesVO> cafAndCpeChargesVOList,HttpServletResponse httpResponse ,MultiAction multiAction,CafMaster email)
					throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet sheet = allocateNewSheet(workbook, "SHEET");//Initial sheet
		HSSFSheet sheet = workbook.createSheet("viewcustomer");
		// create header row
		Row header = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		HSSFRow row3 = sheet.createRow(3);// month/year

//		int cellNumber = 1;


		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try (ServletOutputStream out = httpResponse.getOutputStream();
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
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);


			// For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("View Customer");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);
			
//			if(multiAction.getTenantType()=="LMO")
//			{
			// For creating third row 
			
			cell = row3.createCell(0);
			cell.setCellValue("Name");
			cell.setCellStyle(style);

			cell = row3.createCell(1);
			//cell.setCellValue(email.getTenantname());  Siddharth_4_5_nag_merge
			cell.setCellStyle(style);
			
			cell = row3.createCell(2);
			cell.setCellValue("Lmocode");
			cell.setCellStyle(style);

			cell = row3.createCell(3);
			if (null!=multiAction.getLmoCode())
				cell.setCellValue(multiAction.getLmoCode());
			else
			cell.setCellValue(multiAction.getTenantCode());
			cell.setCellStyle(style);
			
			cell = row3.createCell(4);
			cell.setCellValue("Month/Year");
			cell.setCellStyle(style);

			cell = row3.createCell(5);
			cell.setCellValue(multiAction.getUsageMM()+"/"+multiAction.getUsageYYYY());
			cell.setCellStyle(style);
			
//			}
			header.createCell(0).setCellValue("SNo.");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("CAF No");
			header.getCell(1).setCellStyle(style);

			header.createCell(3).setCellValue("Customer/Organization Name");
			header.getCell(3).setCellStyle(style);

			header.createCell(11).setCellValue("Aadhar/Regristration No");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("APSFL TrackId");
			header.getCell(12).setCellStyle(style);

			header.createCell(13).setCellValue("ONU SrlNo");
			header.getCell(13).setCellStyle(style);

			header.createCell(2).setCellValue("Activation Date");
			header.getCell(2).setCellStyle(style);

			header.createCell(4).setCellValue("Moblie No");
			header.getCell(4).setCellStyle(style);

//			header.createCell(8).setCellValue("Security Deposit");
//			header.getCell(8).setCellStyle(style);

			header.createCell(14).setCellValue("Status");
			header.getCell(14).setCellStyle(style);

			header.createCell(5).setCellValue("APSFL Share");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("MSO Share");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("LMO Share");
			header.getCell(7).setCellStyle(style);

//			header.createCell(13).setCellValue("Tax");
//			header.getCell(13).setCellStyle(style);

			header.createCell(8).setCellValue("Total");
			header.getCell(8).setCellStyle(style);
			
			header.createCell(9).setCellValue("Arrears");
			header.getCell(9).setCellStyle(style);

/*			header.createCell(15).setCellValue("Payment");
			header.getCell(15).setCellStyle(style);
*/
			header.createCell(10).setCellValue("Total Payable");
			header.getCell(10).setCellStyle(style);
			
		

			// create data rows
			int rowCount = 5;
			int i=0;
			for (CafAndCpeChargesVO cafCpeChargesVO : cafAndCpeChargesVOList) {
				HSSFRow Row = sheet.createRow(rowCount++);
//				for(i=0; i <=cellNumber; i++) {
					String fname = cafCpeChargesVO.getfName() == null ? "" : cafCpeChargesVO.getfName();
					String lname = cafCpeChargesVO.getlName() == null ? "" : cafCpeChargesVO.getlName();
					BigDecimal totalpayable=new BigDecimal(0);
					double percentage=18.00/100.00;
				BigDecimal percen=new BigDecimal(percentage);

					if (cafCpeChargesVO.getTotalPayable() == null)
					{
						totalpayable=totalpayable;
					}

					else
					{
						totalpayable = cafCpeChargesVO.getTotalPayable();
					}

					BigDecimal total=new BigDecimal(0);
					if (cafCpeChargesVO.getTotal() == null)
					{
						total=total;
					}

					else
					{
						total = cafCpeChargesVO.getTotal();
					}
					BigDecimal payment=new BigDecimal(0);

					if (cafCpeChargesVO.getPayment() == null)
					{
						payment=payment;
					}

					else
					{
						payment = cafCpeChargesVO.getPayment();
					}
					if(cafCpeChargesVO.getMonthlyCharges() !=null)
					{
						cafCpeChargesVO.setMonthlyCharges(cafCpeChargesVO.getMonthlyCharges().add(cafCpeChargesVO.getMonthlyCharges().multiply(percen)).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if(cafCpeChargesVO.getOnetimeCharges() !=null)
					{
						cafCpeChargesVO.setOnetimeCharges(cafCpeChargesVO.getOnetimeCharges().add(cafCpeChargesVO.getOnetimeCharges().multiply(percen)).setScale(2, BigDecimal.ROUND_HALF_UP));

					}
					if(cafCpeChargesVO.getTelePhoneCharges() !=null)
					{
						cafCpeChargesVO.setTelePhoneCharges(cafCpeChargesVO.getTelePhoneCharges().add(cafCpeChargesVO.getTelePhoneCharges().multiply(percen)).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					String name =  fname+" "+lname;
					//BigDecimal arrears=totalpayable.subtract(total);
					BigDecimal arrears=cafCpeChargesVO.getTotalPayable();
					//BigDecimal payments=totalpayable.subtract(payment);
					BigDecimal payments=total.add(arrears);
					cafCpeChargesVO.setArrears(arrears);
					cafCpeChargesVO.setTotalPayable(payments);
					Row.createCell(0).setCellValue(++i);
					Row.createCell(1).setCellValue(cafCpeChargesVO.getCafNo() == null	? "" : cafCpeChargesVO.getCafNo());
					Row.createCell(3).setCellValue(name);
					Row.createCell(11).setCellValue(cafCpeChargesVO.getCustCode() == null	? "" : cafCpeChargesVO.getCustCode());
					Row.createCell(12).setCellValue(cafCpeChargesVO.getApsflTrackId() == null	? "" : cafCpeChargesVO.getApsflTrackId());
					Row.createCell(13).setCellValue(cafCpeChargesVO.getCpeSlno() == null ? "" : cafCpeChargesVO.getCpeSlno());
					Row.createCell(2).setCellValue(cafCpeChargesVO.getCafdate() == null	? "" : cafCpeChargesVO.getCafdate());
					Row.createCell(4).setCellValue(cafCpeChargesVO.getContactMob() == null	? "" : cafCpeChargesVO.getContactMob());
					//Row.createCell(8).setCellValue(cafCpeChargesVO.getSecDepositCharge() == null	? "" : cafCpeChargesVO.getSecDepositCharge());
					Row.createCell(14).setCellValue(cafCpeChargesVO.getStatusDesc() == null	? "" : cafCpeChargesVO.getStatusDesc());

					Row.createCell(5).setCellValue(cafCpeChargesVO.getMonthlyCharges() == null	? "" : cafCpeChargesVO.getMonthlyCharges().toPlainString());

					Row.createCell(6).setCellValue(cafCpeChargesVO.getOnetimeCharges() == null	? "" : cafCpeChargesVO.getOnetimeCharges().toPlainString());

					Row.createCell(7).setCellValue(cafCpeChargesVO.getTelePhoneCharges() == null	? "" : cafCpeChargesVO.getTelePhoneCharges().toPlainString());

//					Row.createCell(13).setCellValue(cafCpeChargesVO.getServiceTax() == null	? "" : cafCpeChargesVO.getServiceTax().toString());
					
					Row.createCell(8).setCellValue(cafCpeChargesVO.getTotal() == null	? "" : cafCpeChargesVO.getTotal().toString());
					Row.createCell(9).setCellValue(cafCpeChargesVO.getArrears() == null	? "" : cafCpeChargesVO.getArrears().toPlainString());
//					Row.createCell(15).setCellValue(cafCpeChargesVO.getPayment() == null	? "" : cafCpeChargesVO.getPayment().toString());
					Row.createCell(10).setCellValue(cafCpeChargesVO.getTotalPayable() == null	? "" : cafCpeChargesVO.getTotalPayable().toString());

//				}
//				rowCount++;

			}
			httpResponse.setContentType("application/vnd.ms-excel");
			if (null!=multiAction.getLmoCode() && multiAction.getLmoCode() !="" )
			httpResponse.setHeader("Content-Disposition", "attachment; filename="+multiAction.getLmoCode()+"_"+multiAction.getUsageMM()+"_"+multiAction.getUsageYYYY()+"_billdetails.xls");
			else 
				httpResponse.setHeader("Content-Disposition", "attachment; filename="+multiAction.getTenantCode()+"_"+multiAction.getUsageMM()+"_"+multiAction.getUsageYYYY()+"_billdetails.xls");
			workbook.write(out);

			System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) {
			logger.error("multiActionCafsDownload1");
			e.printStackTrace();
		} finally {
			workbook = null;
		}
		return workbook;
	}
	
	
	
	
	public HSSFWorkbook revShareDownload(List<CustomerInvDtlsDTO> revSummarylist,HttpServletResponse httpResponse ) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("workorder");
		// create header row
		Row header = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try (ServletOutputStream out = httpResponse.getOutputStream();
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
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			// For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("revenue share summary");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S No.");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("LMO Code");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("MSO Code");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Invoice Year");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Invoice Month");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("LMO Share");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("MSO Share");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("APSFL Share");
			header.getCell(7).setCellStyle(style);
			
			header.createCell(8).setCellValue("Total Collected");
			header.getCell(8).setCellStyle(style);
			
			header.createCell(9).setCellValue("Arrears");
			header.getCell(9).setCellStyle(style);
			
			header.createCell(10).setCellValue("Caf Count");
			header.getCell(10).setCellStyle(style);
			
			header.createCell(11).setCellValue("Credit Remaining");
			header.getCell(11).setCellStyle(style);
			
			header.createCell(12).setCellValue("Paid Count");
			header.getCell(12).setCellStyle(style);
			
			header.createCell(13).setCellValue("Not Paid Count");
			header.getCell(13).setCellStyle(style);

			// create data rows
			int rowCount = 5;
			int i=0;
			for (CustomerInvDtlsDTO revShareSummary : revSummarylist) {
				HSSFRow Row = sheet.createRow(rowCount++);

				Row.createCell(0).setCellValue(++i);
				Row.createCell(1).setCellValue(revShareSummary.getLmocode() == null ? "" : revShareSummary.getLmocode().toString());
				Row.createCell(2).setCellValue(revShareSummary.getMsocode() == null ? "" : revShareSummary.getMsocode().toString());
				Row.createCell(3).setCellValue(revShareSummary.getInvyr() == null	? "" : revShareSummary.getInvyr());
				Row.createCell(4).setCellValue(revShareSummary.getInvmn() == null	? "" : revShareSummary.getInvmn());
				Row.createCell(5).setCellValue(revShareSummary.getLmomonthlyshare() == null	? "" : revShareSummary.getLmomonthlyshare());
				Row.createCell(6).setCellValue(revShareSummary.getMsomonthlyshare() == null	? "" : revShareSummary.getMsomonthlyshare());
				Row.createCell(7).setCellValue(revShareSummary.getApsflmonthlyshare() == null	? "" : revShareSummary.getApsflmonthlyshare());
				Row.createCell(8).setCellValue(revShareSummary.getTotalcollected() == null	? "" : revShareSummary.getTotalcollected());
				Row.createCell(9).setCellValue(revShareSummary.getTotalPrevBal() == null	? "" : revShareSummary.getTotalPrevBal());
				Row.createCell(10).setCellValue(revShareSummary.getCafcount() == null	? "" : revShareSummary.getCafcount());
				Row.createCell(11).setCellValue(revShareSummary.getCreditlimit() == null	? "" : revShareSummary.getCreditlimit());
				Row.createCell(12).setCellValue(revShareSummary.getPaid()  == null	? "" : revShareSummary.getPaid() );
				Row.createCell(13).setCellValue(revShareSummary.getNotpaid() == null	? "" : revShareSummary.getNotpaid());
				
			}
			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename=revShareSummary.xls");
			workbook.write(out);
			System.out.println("Excel written successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return workbook;
	}
	
	
	public HSSFWorkbook lmosUnderMsoRevenueShareDownload(List<MsoRevenueShareBO> revSummarylist,HttpServletResponse httpResponse ) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("workorder");
		// create header row
		Row header = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		try (ServletOutputStream out = httpResponse.getOutputStream();
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
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			// For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("revenue share summary");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S No.");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("LMO Code");
			header.getCell(1).setCellStyle(style);

			/*header.createCell(2).setCellValue("MSO Code");
			header.getCell(2).setCellStyle(style);*/

			/*header.createCell(3).setCellValue("Invoice Year");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Invoice Month");
			header.getCell(4).setCellStyle(style);*/

			header.createCell(2).setCellValue("LMO Share");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("MSO Share");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("APSFL Share");
			header.getCell(4).setCellStyle(style);
			
			header.createCell(5).setCellValue("Total");
			header.getCell(5).setCellStyle(style);

			// create data rows
			int rowCount = 5;
			int i=0;
			for (MsoRevenueShareBO revShareSummary : revSummarylist) {
				HSSFRow Row = sheet.createRow(rowCount++);

				Row.createCell(0).setCellValue(++i);
				Row.createCell(1).setCellValue(revShareSummary.getLmoCode() == null ? "" : revShareSummary.getLmoCode());
				//Row.createCell(2).setCellValue(revShareSummary.getMsocode() == null ? "" : revShareSummary.getMsocode().toString());
				//Row.createCell(3).setCellValue(revShareSummary.getInvyr() == null	? "" : revShareSummary.getInvyr());
			//	Row.createCell(4).setCellValue(revShareSummary.getInvmn() == null	? "" : revShareSummary.getInvmn());
				Row.createCell(2).setCellValue(revShareSummary.getLmoShare() == null	? "" :revShareSummary.getLmoShare());
				Row.createCell(3).setCellValue(revShareSummary.getMsoShare() == null	? "" :revShareSummary.getMsoShare());
				Row.createCell(4).setCellValue(revShareSummary.getApsflShare() == null	? "" : revShareSummary.getApsflShare());
				Row.createCell(5).setCellValue(revShareSummary.getTotalBill() == null	? "" :revShareSummary.getTotalBill());
				
			}
			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename=revShareSummary.xls");
			workbook.write(out);
			System.out.println("Excel written successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return workbook;
	}
	
	
	
	
	
	
	
	public static List<Offline_Payment1> extractBulkOfflinePaymentExcelFile(MultipartFile excelFile, Model model,HttpServletRequest request) {
		List<Offline_Payment1> offlinePaymentList = new ArrayList<Offline_Payment1>();
		int numHeadRows = 1;
		int totalRows = 0;
		InputStream is = null;
		XSSFWorkbook workBook = null;
		XSSFSheet worksheet1 = null;
		XSSFRow headerRow = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	//	String[] selectedPrefixes=request.getParameterValues("cpePrefixes");
		
		//boolean isPrefixMatching=false;
		try {
			is = excelFile.getInputStream();
			 workBook = new XSSFWorkbook(is);
			// worksheet1 = workBook.getSheetAt(0);
			 
			 
			 for (int j = 0; j < workBook.getNumberOfSheets(); j++) {
				 worksheet1 = workBook.getSheetAt(j);
				 
				 String Date=worksheet1.getSheetName();
				 
				
	        
			 headerRow =  worksheet1.getRow(0); 
			int cellNum = headerRow.getPhysicalNumberOfCells();
			for(int i=0 ; i<=cellNum; i++){
				switch(i){
					case 0 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("S No")))
							model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						else 
							continue;
						break;
					case 1 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("LMO ID")))
							model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						else 
							continue;
						break;
					case 2 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Bank Reference No")))
							model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						else 
							continue;
						break;
					case 3 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Amount In Rs")))
							model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						else 
							continue;
						break;
						
					case 4 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Date")))
							model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						else 
							continue;
						break;
					
					default:
						break;
				}
			}
			String  errorMsg =  (String)model.asMap().get("errorMsg");
			if(errorMsg == null || !(errorMsg.equalsIgnoreCase("The excel document is not a valid format.")) ||
					!(errorMsg.equalsIgnoreCase("Please upload a excel document in predefined format."))){
				Iterator rows = worksheet1.rowIterator();
				totalRows = worksheet1.getPhysicalNumberOfRows() - 1;
				int rowIndex = 1;
				loop:while (rows.hasNext()) {
					
					try {
						
						if (rowIndex <= numHeadRows) {
							rowIndex++;
							rows.next();
							continue;
						}
						
						Row row = (Row) rows.next();
						
						Offline_Payment1 offlinePayment = new Offline_Payment1();
						/*java.util.Date date = formatter.parse(collectionDate);
						
						java.sql.Date sqlCollecationDate = new java.sql.Date(date.getTime()); 
						
						
						offlinePayment.setDep_date(sqlCollecationDate);*/
						offlinePayment.setPayment_mode("CASH");
						
						
						for (int index = 1; index < cellNum; index++) {
							if( (String)model.asMap().get("errorMsg") != null ){
							break;
							
							}
							switch (index) {
							case 1:
								if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR){
									model.addAttribute("errorMsg", "This document has one or more fields blank or zero. Please check the upload document and try again.");
								break;
								}
									else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
										offlinePayment.setLmocode(row.getCell(index).getStringCellValue().trim());
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
									 int name = (int) row.getCell(index).getNumericCellValue();
									 offlinePayment.setLmocode(String.valueOf(name).trim());
								}
								else continue;
								break;
							case 2:
								if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR){
									//.addAttribute("errorMsg", "The  document has one or more fields blank or zero. Please check the upload document and try again.");
									model.addAttribute("errorMsg", "This document has one or more fields blank or zero. Please check the upload document and try again");
									break;
								}
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
									offlinePayment.setTrans_id(row.getCell(index).getStringCellValue().trim());
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
									 Long name = (long) row.getCell(index).getNumericCellValue();
									 offlinePayment.setTrans_id(String.valueOf(name).trim());
								}
								else continue;
								break;
							case 3:
								
								
								if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR){
									//.addAttribute("errorMsg", "The  document has one or more fields blank or zero. Please check the upload document and try again.");
									model.addAttribute("errorMsg", "This document has one or more fields blank or zero. Please check the upload document and try again");
									break;
								}
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
									offlinePayment.setAmount(Double.valueOf(row.getCell(index).getStringCellValue().trim()));
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
									 double name = row.getCell(index).getNumericCellValue();
									 offlinePayment.setAmount(name);
								}
								else continue;
								break;
								
							case 4:
								
								
								if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR){
									//.addAttribute("errorMsg", "The  document has one or more fields blank or zero. Please check the upload document and try again.");
									model.addAttribute("errorMsg", "This document has one or more fields blank or zero. Please check the upload document and try again");
									break;
								}
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
									offlinePayment.setDep_date(new java.sql.Date(formatter.parse(row.getCell(index).getStringCellValue().trim()).getTime()));
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
									 
									if(DateUtil.isCellDateFormatted(row.getCell(index))){
									 Date date2=row.getCell(index).getDateCellValue();
									
									 java.sql.Date sqlCollecationDate = new java.sql.Date(date2.getTime());
									 offlinePayment.setDep_date(sqlCollecationDate);
									}
							
								}
								else continue;
								break;
								
								
							
							default:
								break;
							}
							
							
						}
						offlinePaymentList.add(offlinePayment);
						if( (String)model.asMap().get("errorMsg") != null )
						{offlinePaymentList.clear();
						break;
						}

					} catch (Exception exception) {
						model.addAttribute("errorMsg", exception.getMessage());
					}
				}
				
			
			}
			} 
		} catch (Exception exception) {
			return offlinePaymentList;
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 is = null;
			 workBook = null;
			 worksheet1 = null;
			 headerRow = null;
		}

		return offlinePaymentList;

	}
	

	
	/**Upload Subscribers List**/
	public static List<String> extractSubscribersExcelFile(MultipartFile excelFile, Model model,HttpServletRequest request) {
		List<String> subscriberCodes = new ArrayList<String>();
		int numHeadRows = 1;
		InputStream is = null;
		XSSFWorkbook workBook = null;
		XSSFSheet worksheet1 = null;
		XSSFRow headerRow = null;
		try {
			is = excelFile.getInputStream();
			 workBook = new XSSFWorkbook(is);
			 
			 for (int j = 0; j < workBook.getNumberOfSheets(); j++) {
				 worksheet1 = workBook.getSheetAt(j);
				 
			 headerRow =  worksheet1.getRow(0); 
			int cellNum = headerRow.getPhysicalNumberOfCells();
			Map<Integer,String> cellName = new HashMap<Integer,String>();
			for(int i=0 ; i< cellNum; i++){
				if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" ) {
					model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
					break;
				}
				switch(i){
				case 0 : 
					cellName.put(i, "cafNo");
					break;
				case 1 : 
					cellName.put(i, "custName");
					break;
				case 2 : 
					cellName.put(i, "aadharNo");
					break;
				case 3 : 
					cellName.put(i, "cafDate");
					break;
				case 4 : 
					cellName.put(i, "cpePlace");
					break;
				case 5 : 
					cellName.put(i, "stbSlno");
					break;
				case 6 : 
					cellName.put(i, "stbMacaddr");
					break;
				case 7 : 
					cellName.put(i, "nwsubsCode");
					break;
				default:
					break;
				}
			}
			String  errorMsg =  (String)model.asMap().get("errorMsg");
			if(errorMsg == null || !(errorMsg.equalsIgnoreCase("The excel document is not a valid format.")) ||
					!(errorMsg.equalsIgnoreCase("Please upload a excel document in predefined format."))){
				Iterator rows = worksheet1.rowIterator();
				int rowIndex = 1;
				loop:while (rows.hasNext()) {
					
					try {
						
						if (rowIndex <= numHeadRows) {
							rowIndex++;
							rows.next();
							continue;
						}
						
						Row row = (Row) rows.next();
						
						for (int index = 0; index < cellNum; index++) {
							if(index == 7) {
								if( (String)model.asMap().get("errorMsg") != null ){
									break;
									
									}
									
									if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR){
										if(index != 4 && index != 2) {
											model.addAttribute("errorMsg", "This document has one or more fields blank or zero. Please check the upload document and try again.");
											break;
										}
									}else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
										subscriberCodes.add(row.getCell(index).getStringCellValue().trim());
									else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
										 int name = (int) row.getCell(index).getNumericCellValue();
										 subscriberCodes.add(String.valueOf(name).trim());
									}
							}
						}
					} catch (Exception exception) {
						model.addAttribute("errorMsg", exception.getMessage());
					}
				}
			}
			} 
		} catch (Exception exception) {
			return subscriberCodes;
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 is = null;
			 workBook = null;
			 worksheet1 = null;
			 headerRow = null;
		}
		return subscriberCodes;
	}
}
