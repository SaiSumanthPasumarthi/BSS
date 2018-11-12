package com.arbiva.apsfl.tms.serviceImpl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbiva.apsfl.coms.dto.CafsForBlockListVO;
import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.dto.AAAUsageDTO;
import com.arbiva.apsfl.dto.AaaUsageBYHoursDTO;
import com.arbiva.apsfl.reports.vo.AgeingReportVO;
import com.arbiva.apsfl.reports.vo.ChnlsOnAlacarteVO;
import com.arbiva.apsfl.reports.vo.CpeOrderVO;
import com.arbiva.apsfl.reports.vo.PackWiseChnlsVO;
import com.arbiva.apsfl.reports.vo.PackWiseSubsVO;
import com.arbiva.apsfl.reports.vo.ReportsDTO;
import com.arbiva.apsfl.reports.vo.SrvsWiseSubsVO;
import com.arbiva.apsfl.reports.vo.StatusWiseSubsVO;
import com.arbiva.apsfl.reports.vo.SubsDtlsVO;
import com.arbiva.apsfl.reports.vo.TransactionLogVO;
import com.arbiva.apsfl.tms.daoImpl.ReportsDaoImpl;
import com.arbiva.apsfl.tms.dto.CpeStockVO;
import com.arbiva.apsfl.tms.model.AAAUsageBO;
import com.arbiva.apsfl.tms.model.HSICummSummaryMonthlyCustViewDTO;
import com.arbiva.apsfl.tms.model.HSICummSummaryMonthlyViewDTO;

@Service
public class ReportsServiceImpl {

	@Autowired
	ReportsDaoImpl reportsDaoImpl;

	public List<Object[]> getListByQuery(String query) {
		List<Object[]> list = null;
		try {
			list = reportsDaoImpl.getListByQuery(query);
		} catch (Exception e) {

		} finally {

		}
		return list;
	}

	public String getSingleRecord(String query) {
		String result = "";
		try {
			result = reportsDaoImpl.getSingleRecord(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[] getObjectSingleRecord(String query) {
		Object[] object = null;
		try {
			object = reportsDaoImpl.getObjectSingleRecord(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public List<CafsForBlockListVO> getcustomerListByQuery(String query) {
		List<Object[]> customerList = null;
		List<CafsForBlockListVO> cafList = new ArrayList<>();
		CafsForBlockListVO cafinfoVO;
		try {
			customerList = reportsDaoImpl.getListByQuery(query);
			for (Object[] object : customerList) {
				cafinfoVO = new CafsForBlockListVO();
				cafinfoVO.setCafno(Long.parseLong(object[0].toString()));
				cafinfoVO.setFname(object[1] == null ? "NA" : object[1].toString());
				cafinfoVO.setLname(object[2] == null ? "NA" : object[2].toString());
				cafinfoVO.setAddr1(object[3] == null ? "NA" : object[3].toString());
				cafinfoVO.setAddr2(object[4] == null ? "NA" : object[4].toString());
				cafinfoVO.setLocality(object[5] == null ? "NA" : object[5].toString());
				cafinfoVO.setArea(object[6] == null ? "NA" : object[6].toString());
				cafinfoVO.setAadharno(object[7] == null ? "NA" : object[7].toString());
				cafinfoVO.setStbslno(object[8] == null ? "NA" : object[8].toString());
				cafinfoVO.setApprovedon(object[9] == null ? "NA" : object[9].toString());
				cafinfoVO.setStbmac(object[10] == null ? "NA" : object[10].toString());
				cafinfoVO.setNwsubscode(object[11] == null ? "NA" : object[11].toString());
				cafinfoVO.setReason(object[12] == null ? "NA" : object[12].toString());
				cafList.add(cafinfoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return cafList;
	}

	@Transactional
	public AAAUsageDTO getAAAUsageBySearch(PageObject pageObject, AAAUsageDTO aaaUsageDTO) {
		return reportsDaoImpl.getAAAUsageBySearch(pageObject,aaaUsageDTO);
	}

	public HSSFWorkbook aaaUsageReportDownload(AAAUsageDTO aaaUsageDTO) {
		PageObject pageObject = null;
		aaaUsageDTO = this.getAAAUsageBySearch(pageObject,aaaUsageDTO);
		return getHSSBook(aaaUsageDTO.getList());
	}

	public HSSFWorkbook aaaUsageReportEmail() {
		List<AAAUsageBO> list = reportsDaoImpl.aaaUsageReportEmail();
		return getHSSBook(list);
	}
	
	public HSSFWorkbook getHSSBook(List<AAAUsageBO> list) {
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("HSI Session Wise Report");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
	 

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());
		try (InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {

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
			cell.setCellValue("AAA Usage Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("Account No");
			header.getCell(0).setCellStyle(style);
			
			header.createCell(1).setCellValue("Customer / Organization Name");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("PoP Name");
			header.getCell(2).setCellStyle(style);
			
			header.createCell(3).setCellValue("Total Size [MB]");
			header.getCell(3).setCellStyle(style);
			
			header.createCell(4).setCellValue("Download Size [MB]");
			header.getCell(4).setCellStyle(style);
			
			header.createCell(5).setCellValue("Upload Size [MB]");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("Start Time");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("End Time");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("Session Time");
			header.getCell(8).setCellStyle(style);
			
			header.createCell(9).setCellValue("Session Terminate Cause");
			header.getCell(9).setCellStyle(style);
			
			

			// create data rows
			int rowCount = 5;
			 

			
			BigDecimal total = new BigDecimal("0");
			BigDecimal download = new BigDecimal("0");
			BigDecimal upload = new BigDecimal("0");

			for (AAAUsageBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(obj.getCafNo());
				aRow.createCell(1).setCellValue(obj.getCustname());
				aRow.createCell(2).setCellValue(obj.getSubstnname());
				aRow.createCell(3).setCellValue(obj.getTotalSize());
				aRow.createCell(4).setCellValue(obj.getDwnldsize());
				aRow.createCell(5).setCellValue(obj.getUpldsize());
				aRow.createCell(6).setCellValue(obj.getSesStartTime());
				aRow.createCell(7).setCellValue(obj.getSesEndTime());	
				aRow.createCell(8).setCellValue(obj.getSessiontime());
				aRow.createCell(9).setCellValue(obj.getAcctStatusType());
		
				
				download = download.add(new BigDecimal(obj.getDwnldsize()));
				upload = upload.add(new BigDecimal(obj.getUpldsize()));
				total = total.add(new BigDecimal(obj.getTotalSize()));
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

			aRow.createCell(0).setCellValue("Total");
			aRow.getCell(0).setCellStyle(style);
			cell.setCellStyle(style);
			aRow.createCell(4).setCellValue(download.toString());
			aRow.createCell(5).setCellValue(upload.toString());
			aRow.createCell(3).setCellValue(total.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	public List<AaaUsageBYHoursDTO> getaaaUsageReportByHours(AaaUsageBYHoursDTO aaaUsageBYHoursDTO) {
		 List<AaaUsageBYHoursDTO> aaaUsageBYHoursDTOs=new ArrayList<>();
		 List<AaaUsageBYHoursDTO> aaaUsageBYHoursSplitted=new ArrayList<>();
		 aaaUsageBYHoursDTOs=reportsDaoImpl.getaaaUsageReportByHours(aaaUsageBYHoursDTO);
		
		 
		 try{
			 
			 for(AaaUsageBYHoursDTO a : aaaUsageBYHoursDTOs)
			 {
				 AaaUsageBYHoursDTO obj  = null;
				 obj = this.getObj(a.getDay01usage(),a,"01");
				 if(obj != null)
				 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay02usage(),a,"02");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay03usage(),a,"03");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay04usage(),a,"04");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay05usage(),a,"05");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay06usage(),a,"06");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay07usage(),a,"07");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay08usage(),a,"08");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay09usage(),a,"09");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay10usage(),a,"10");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay11usage(),a,"11");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay12usage(),a,"12");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay13usage(),a,"13");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay14usage(),a,"14");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay15usage(),a,"15");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay16usage(),a,"16");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay17usage(),a,"17");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay18usage(),a,"18");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay19usage(),a,"19");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay20usage(),a,"20");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay21usage(),a,"21");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay22usage(),a,"22");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay23usage(),a,"23");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay24usage(),a,"24");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay25usage(),a,"25");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay26usage(),a,"26");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay27usage(),a,"27");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj =  this.getObj(a.getDay28usage(),a,"28");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay29usage(),a,"29");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay30usage(),a,"30");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
				 obj = this.getObj(a.getDay31usage(),a,"31");
				 if(obj != null)
					 	aaaUsageBYHoursSplitted.add(obj);
			 }
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		 
		return aaaUsageBYHoursSplitted;
	}
	
	public AaaUsageBYHoursDTO getObj(String str, AaaUsageBYHoursDTO a, String day){
		int m=Integer.parseInt(a.getMonthSelected())-1; // month value In Db Starts from 1-12 but Date fn returns 0-11
		AaaUsageBYHoursDTO aaaUsageByHoursDto= new AaaUsageBYHoursDTO();
		try{
			 BigDecimal sumup = new BigDecimal("0");
			 BigDecimal sumdown = new BigDecimal("0");
			 BigDecimal totalUsage = new BigDecimal("0");
			 int count=0;
			 //Splitting String to arrays and converted to  List of Strings
			 List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
			 String[] monthNames = {"JAN", "FRB", "MAR", "APR", "May", "JUN",
			                      "JULY", "AUG", "SEPT", "OCT", "NOV", "DEC"
			 };
			 //Day With Month i.e Mar-1
			 aaaUsageByHoursDto.setCafSelected(a.getCafSelected());
			 aaaUsageByHoursDto.setMonthSelected(day+"-"+monthNames[m]);
			 aaaUsageByHoursDto.setYearSelected(a.getYearSelected());
			 aaaUsageByHoursDto.setAaaCode(a.getAaaCode());
			 aaaUsageByHoursDto.setAgoraCode(a.getAgoraCode());
			 aaaUsageByHoursDto.setApsfltrackId(a.getApsfltrackId());
			 aaaUsageByHoursDto.setCustomerName(a.getCustomerName());
			 aaaUsageByHoursDto.setMobileNo(a.getMobileNo());
			 aaaUsageByHoursDto.setOnuSerialNo(a.getOnuSerialNo());
			 aaaUsageByHoursDto.setOnuMacAddress(a.getOnuMacAddress());
			 aaaUsageByHoursDto.setSubStationName(a.getSubStationName());
			 
			 //DOWNLOAD USAGE by Hours
			 aaaUsageByHoursDto.setZeroTo1(String.valueOf((new BigDecimal(items.get(0)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setOneTo2(String.valueOf((new BigDecimal(items.get(1)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setTwoTo3(String.valueOf((new BigDecimal(items.get(2)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setThreeTo4(String.valueOf((new BigDecimal(items.get(3)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setFourTo5(String.valueOf((new BigDecimal(items.get(4)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setFiveTo6(String.valueOf((new BigDecimal(items.get(5)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setSixTo7(String.valueOf((new BigDecimal(items.get(6)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setSevenTo8(String.valueOf((new BigDecimal(items.get(7)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setEightTo9(String.valueOf((new BigDecimal(items.get(8)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setNineTo10(String.valueOf((new BigDecimal(items.get(9)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setTenTo11(String.valueOf((new BigDecimal(items.get(10)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setElevenTo12(String.valueOf((new BigDecimal(items.get(11)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setTwelveTo13(String.valueOf((new BigDecimal(items.get(12)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setThirteenTo14(String.valueOf((new BigDecimal(items.get(13)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setFourteenTo15(String.valueOf((new BigDecimal(items.get(14)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setFifteenTo16(String.valueOf((new BigDecimal(items.get(15)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setSixteenTo17(String.valueOf((new BigDecimal(items.get(16)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setSeventeenTo18(String.valueOf((new BigDecimal(items.get(17)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setEighteenTo19(String.valueOf((new BigDecimal(items.get(18)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setNineteenTo20(String.valueOf((new BigDecimal(items.get(19)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setTwentyTo21(String.valueOf((new BigDecimal(items.get(20)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setTwenty1To22(String.valueOf((new BigDecimal(items.get(21)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setTwenty2To23(String.valueOf((new BigDecimal(items.get(22)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setTwenty3To24(String.valueOf((new BigDecimal(items.get(23)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			//Up USAGE by Hours
			 aaaUsageByHoursDto.setUp0to1(String.valueOf((new BigDecimal(items.get(24)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp1to2(String.valueOf((new BigDecimal(items.get(25)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp2to3(String.valueOf((new BigDecimal(items.get(26)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp3to4(String.valueOf((new BigDecimal(items.get(27)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp4to5(String.valueOf((new BigDecimal(items.get(28)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp5to6(String.valueOf((new BigDecimal(items.get(29)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp6to7(String.valueOf((new BigDecimal(items.get(30)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp7to8(String.valueOf((new BigDecimal(items.get(31)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp8to9(String.valueOf((new BigDecimal(items.get(32)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp9to10(String.valueOf((new BigDecimal(items.get(33)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp10to11(String.valueOf((new BigDecimal(items.get(34)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp11to12(String.valueOf((new BigDecimal(items.get(35)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp12to13(String.valueOf((new BigDecimal(items.get(36)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp13to14(String.valueOf((new BigDecimal(items.get(37)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp14to15(String.valueOf((new BigDecimal(items.get(38)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp15to16(String.valueOf((new BigDecimal(items.get(39)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp16to17(String.valueOf((new BigDecimal(items.get(40)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp17to18(String.valueOf((new BigDecimal(items.get(41)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp18to19(String.valueOf((new BigDecimal(items.get(42)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp19to20(String.valueOf((new BigDecimal(items.get(43)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp20to21(String.valueOf((new BigDecimal(items.get(44)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp21to22(String.valueOf((new BigDecimal(items.get(45)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp22to23(String.valueOf((new BigDecimal(items.get(46)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 aaaUsageByHoursDto.setUp23to24(String.valueOf((new BigDecimal(items.get(47)).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			
			 for(String item : items)
			 {
				 totalUsage=totalUsage.add(new BigDecimal(item));
			if(count <  24 )
				sumdown =  sumdown.add(new BigDecimal(item)) ; 
			else 
		    	sumup =  sumup.add(new BigDecimal(item));
			  count++;
			 }
			 //Total Download Usage On Day
			 aaaUsageByHoursDto.setTotalUsageOnDay(String.valueOf((sumdown.divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 
			 //Total Upload Usage On Day
			 aaaUsageByHoursDto.setUploadTotal(String.valueOf((sumup.divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 
			 //Total Usage On Day Upload+Download
			 aaaUsageByHoursDto.setTotalUsage(String.valueOf((totalUsage.divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING))));
			 
			 int i = totalUsage.compareTo(new BigDecimal("0"));
			 if(i == 0 || i == -1)
				 aaaUsageByHoursDto = null;
		 }catch(Exception e)
		{
			 e.printStackTrace();
		}
		
		 return aaaUsageByHoursDto;
	}

	public HSSFWorkbook getaaaUsageWorkBookByHoursDownload(AaaUsageBYHoursDTO aaaUsageBYHoursDTO) {
		List<AaaUsageBYHoursDTO> list =this.getaaaUsageReportByHours(aaaUsageBYHoursDTO);
		return this.getHSSBookForAAAUsageByHour(list);
	}
	
	public HSSFWorkbook getHSSBookForAAAUsageByHour(List<AaaUsageBYHoursDTO> list) {
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("AAA Usage By Hour Report");
		Row hourHeader = sheet.createRow(4);
		Row header = sheet.createRow(5);
		HSSFRow aRow = sheet.createRow(6);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		 

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());
		try (InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {

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
			cell.setCellValue("AAA Usage Report in Hours");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);
			
			cell = hourHeader.createCell(8);
			cell.setCellValue("0-1 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 8, 9));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(10);
			cell.setCellValue("1-2 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 10, 11));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(12);
			cell.setCellValue("2-3 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 12, 13));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(14);
			cell.setCellValue("3-4 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 14, 15));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(16);
			cell.setCellValue("4-5 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 16, 17));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(18);
			cell.setCellValue("5-6 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 18, 19));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(20);
			cell.setCellValue("6-7 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 20, 21));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(22);
			cell.setCellValue("7-8 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 22, 23));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(24);
			cell.setCellValue("8-9 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 24, 25));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(26);
			cell.setCellValue("9-10 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 26, 27));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(28);
			cell.setCellValue("10-11 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 28, 29));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(30);
			cell.setCellValue("11-12 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4,30, 31));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(32);
			cell.setCellValue("12-13 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 32, 33));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(34);
			cell.setCellValue("13-14 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 34, 35));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(36);
			cell.setCellValue("14-15 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 36, 38));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(38);
			cell.setCellValue("15-16 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 38, 39));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(40);
			cell.setCellValue("16-17 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 40, 41));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(42);
			cell.setCellValue("17-18 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 42, 43));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(44);
			cell.setCellValue("18-19 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 44, 45));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(46);
			cell.setCellValue("19-20 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 46, 47));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(48);
			cell.setCellValue("20-21 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 48, 49));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(50);
			cell.setCellValue("21-22 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4,50, 51));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(52);
			cell.setCellValue("22-23 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 52, 53));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			cell = hourHeader.createCell(54);
			cell.setCellValue("23-24 Hour [MB]");
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 54, 55));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);
			 
			 
			header.createCell(0).setCellValue("Account No");
			header.getCell(0).setCellStyle(style);
			
			header.createCell(1).setCellValue("Customer / Organization Name");
			header.getCell(1).setCellStyle(style);
			
			header.createCell(2).setCellValue("ONU Serial No");
			header.getCell(2).setCellStyle(style);
			
			header.createCell(3).setCellValue("ONU MAC Address");
			header.getCell(3).setCellStyle(style);
			
			header.createCell(4).setCellValue("Date");
			header.getCell(4).setCellStyle(style);
			
			header.createCell(5).setCellValue("Total Used Size [MB]");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("Total Download Size [MB]");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("Total Upload Size [MB]");
			header.getCell(7).setCellStyle(style);
			
			/*header.createCell(5).setCellValue("APSFL Track Id");
			header.getCell(5).setCellStyle(style);
			
			
			
			header.createCell(7).setCellValue("Mobile No");
			header.getCell(7).setCellStyle(style);
			
			
			
			header.createCell(10).setCellValue("Sub Station Name");
			header.getCell(10).setCellStyle(style);*/
			
		/*	header.createCell(11).setCellValue("Agora Code");
			header.getCell(11).setCellStyle(style);
			
			header.createCell(12).setCellValue("AAA Code");
			header.getCell(12).setCellStyle(style);*/
			
			
			
			header.createCell(8).setCellValue("Download Size");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("Upload Size");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("Download Size");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("Upload Size");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("Download Size");
			header.getCell(12).setCellStyle(style);

			header.createCell(13).setCellValue("Upload Size");
			header.getCell(13).setCellStyle(style);

			header.createCell(14).setCellValue("Download Size");
			header.getCell(14).setCellStyle(style);

			header.createCell(15).setCellValue("Upload Size");
			header.getCell(15).setCellStyle(style);

			header.createCell(16).setCellValue("Download Size");
			header.getCell(16).setCellStyle(style);

			header.createCell(17).setCellValue("Upload Size");
			header.getCell(17).setCellStyle(style);

			header.createCell(18).setCellValue("Download Size");
			header.getCell(18).setCellStyle(style);
			
			header.createCell(19).setCellValue("Upload Size");
			header.getCell(19).setCellStyle(style);
			
			header.createCell(20).setCellValue("Download Size");
			header.getCell(20).setCellStyle(style);
			
			header.createCell(21).setCellValue("Upload Size");
			header.getCell(21).setCellStyle(style);
			
			header.createCell(22).setCellValue("Download Size");
			header.getCell(22).setCellStyle(style);
			
			header.createCell(23).setCellValue("Upload Size");
			header.getCell(23).setCellStyle(style);
			
			header.createCell(24).setCellValue("Download Size");
			header.getCell(24).setCellStyle(style);
			
			header.createCell(25).setCellValue("Upload Size");
			header.getCell(25).setCellStyle(style);
			
			header.createCell(26).setCellValue("Download Size");
			header.getCell(26).setCellStyle(style);
			
			header.createCell(27).setCellValue("Upload Size");
			header.getCell(27).setCellStyle(style);
			
			header.createCell(28).setCellValue("Download Size");
			header.getCell(28).setCellStyle(style);
			
			header.createCell(29).setCellValue("Upload Size");
			header.getCell(29).setCellStyle(style);
			
			header.createCell(30).setCellValue("Download Size");
			header.getCell(30).setCellStyle(style);
			
			header.createCell(31).setCellValue("Upload Size");
			header.getCell(31).setCellStyle(style);
			
			header.createCell(32).setCellValue("Download Size");
			header.getCell(32).setCellStyle(style);
			
			header.createCell(33).setCellValue("Upload Size");
			header.getCell(33).setCellStyle(style);
			
			header.createCell(34).setCellValue("Download Size");
			header.getCell(34).setCellStyle(style);
			
			header.createCell(35).setCellValue("Upload Size");
			header.getCell(35).setCellStyle(style);
			
			header.createCell(36).setCellValue("Download Size");
			header.getCell(36).setCellStyle(style);
			
			header.createCell(37).setCellValue("Upload Size");
			header.getCell(37).setCellStyle(style);
			
			header.createCell(38).setCellValue("Download Size");
			header.getCell(38).setCellStyle(style);
			
			header.createCell(39).setCellValue("Upload Size");
			header.getCell(39).setCellStyle(style);
			
			header.createCell(40).setCellValue("Download Size");
			header.getCell(40).setCellStyle(style);
			
			header.createCell(41).setCellValue("Upload Size");
			header.getCell(41).setCellStyle(style);
			
			header.createCell(42).setCellValue("Download Size");
			header.getCell(42).setCellStyle(style);
			
			header.createCell(43).setCellValue("Upload Size");
			header.getCell(43).setCellStyle(style);
			
			header.createCell(44).setCellValue("Download Size");
			header.getCell(44).setCellStyle(style);
			
			header.createCell(45).setCellValue("Upload Size");
			header.getCell(45).setCellStyle(style);
			
			header.createCell(46).setCellValue("Download Size");
			header.getCell(46).setCellStyle(style);
			
			header.createCell(47).setCellValue("Upload Size");
			header.getCell(47).setCellStyle(style);
			
			header.createCell(48).setCellValue("Download Size");
			header.getCell(48).setCellStyle(style);
			
			header.createCell(49).setCellValue("Upload Size");
			header.getCell(49).setCellStyle(style);
			
			header.createCell(50).setCellValue("Download Size");
			header.getCell(50).setCellStyle(style);
			
			header.createCell(51).setCellValue("Upload Size");
			header.getCell(51).setCellStyle(style);
			
			header.createCell(52).setCellValue("Download Size");
			header.getCell(52).setCellStyle(style);
			
			header.createCell(53).setCellValue("Upload Size");
			header.getCell(53).setCellStyle(style);
			
			header.createCell(54).setCellValue("Download Size");
			header.getCell(54).setCellStyle(style);
			
			header.createCell(55).setCellValue("Upload Size");
			header.getCell(55).setCellStyle(style);
			
			// create data rows
			int rowCount = 6;
			
			BigDecimal day1_up = new BigDecimal("0");
			BigDecimal day1_down = new BigDecimal("0");
			BigDecimal day2_up = new BigDecimal("0");
			BigDecimal day2_down = new BigDecimal("0");
			BigDecimal day3_up = new BigDecimal("0");
			BigDecimal day3_down = new BigDecimal("0");
			BigDecimal day4_up = new BigDecimal("0");
			BigDecimal day4_down = new BigDecimal("0");
			BigDecimal day5_up = new BigDecimal("0");
			BigDecimal day5_down = new BigDecimal("0");
			BigDecimal day6_up = new BigDecimal("0");
			BigDecimal day6_down = new BigDecimal("0");
			BigDecimal day7_up = new BigDecimal("0");
			BigDecimal day7_down = new BigDecimal("0");
			BigDecimal day8_up = new BigDecimal("0");
			BigDecimal day8_down = new BigDecimal("0");
			BigDecimal day9_up = new BigDecimal("0");
			BigDecimal day9_down = new BigDecimal("0");
			BigDecimal day10_up = new BigDecimal("0");
			BigDecimal day10_down = new BigDecimal("0");
			BigDecimal day11_up = new BigDecimal("0");
			BigDecimal day11_down = new BigDecimal("0");
			BigDecimal day12_up = new BigDecimal("0");
			BigDecimal day12_down = new BigDecimal("0");
			BigDecimal day13_up = new BigDecimal("0");
			BigDecimal day13_down = new BigDecimal("0");
			BigDecimal day14_up = new BigDecimal("0");
			BigDecimal day14_down = new BigDecimal("0");
			BigDecimal day15_up = new BigDecimal("0");
			BigDecimal day15_down = new BigDecimal("0");
			BigDecimal day16_up = new BigDecimal("0");
			BigDecimal day16_down = new BigDecimal("0");
			BigDecimal day17_up = new BigDecimal("0");
			BigDecimal day17_down = new BigDecimal("0");
			BigDecimal day18_up = new BigDecimal("0");
			BigDecimal day18_down = new BigDecimal("0");
			BigDecimal day19_up = new BigDecimal("0");
			BigDecimal day19_down = new BigDecimal("0");
			BigDecimal day20_up = new BigDecimal("0");
			BigDecimal day20_down = new BigDecimal("0");
			BigDecimal day21_up = new BigDecimal("0");
			BigDecimal day21_down = new BigDecimal("0");
			BigDecimal day22_up = new BigDecimal("0");
			BigDecimal day22_down = new BigDecimal("0");
			BigDecimal day23_up = new BigDecimal("0");
			BigDecimal day23_down = new BigDecimal("0");
			BigDecimal day24_up = new BigDecimal("0");
			BigDecimal day24_down = new BigDecimal("0");
			BigDecimal total_up = new BigDecimal("0");
			BigDecimal total_down = new BigDecimal("0");
			BigDecimal totalUsage = new BigDecimal("0");
			

			for (AaaUsageBYHoursDTO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(obj.getCafSelected());
				aRow.createCell(1).setCellValue(obj.getCustomerName());
				aRow.createCell(2).setCellValue(obj.getOnuSerialNo());
				aRow.createCell(3).setCellValue(obj.getOnuMacAddress());
				aRow.createCell(4).setCellValue(obj.getMonthSelected());
				aRow.createCell(5).setCellValue(obj.getTotalUsage());
				aRow.createCell(6).setCellValue(obj.getTotalUsageOnDay());
				aRow.createCell(7).setCellValue(obj.getUploadTotal());
				/*aRow.createCell(5).setCellValue(obj.getApsfltrackId());*/
				
			/*	aRow.createCell(7).setCellValue(obj.getMobileNo());
				
				aRow.createCell(10).setCellValue(obj.getSubStationName());
				aRow.createCell(11).setCellValue(obj.getAgoraCode());
				aRow.createCell(12).setCellValue(obj.getAaaCode());*/
				aRow.createCell(8).setCellValue(obj.getZeroTo1());
				aRow.createCell(9).setCellValue(obj.getUp0to1());
				aRow.createCell(10).setCellValue(obj.getOneTo2());
				aRow.createCell(11).setCellValue(obj.getUp1to2());
				aRow.createCell(12).setCellValue(obj.getTwoTo3());
				aRow.createCell(13).setCellValue(obj.getUp2to3());
				aRow.createCell(14).setCellValue(obj.getThreeTo4());
				aRow.createCell(15).setCellValue(obj.getUp3to4());
				aRow.createCell(16).setCellValue(obj.getFourTo5());
				aRow.createCell(17).setCellValue(obj.getUp4to5());
				aRow.createCell(18).setCellValue(obj.getFiveTo6());
				aRow.createCell(19).setCellValue(obj.getUp5to6());
				aRow.createCell(20).setCellValue(obj.getSixTo7());
				aRow.createCell(21).setCellValue(obj.getUp6to7());
				aRow.createCell(22).setCellValue(obj.getSevenTo8());
				aRow.createCell(23).setCellValue(obj.getUp7to8());
				aRow.createCell(24).setCellValue(obj.getEightTo9());
				aRow.createCell(25).setCellValue(obj.getUp8to9());
				aRow.createCell(26).setCellValue(obj.getNineTo10());
				aRow.createCell(27).setCellValue(obj.getUp9to10());
				aRow.createCell(28).setCellValue(obj.getTenTo11());
				aRow.createCell(29).setCellValue(obj.getUp10to11());
				aRow.createCell(30).setCellValue(obj.getElevenTo12());
				aRow.createCell(31).setCellValue(obj.getUp11to12());
				aRow.createCell(32).setCellValue(obj.getTwelveTo13());
				aRow.createCell(33).setCellValue(obj.getUp12to13());
				aRow.createCell(34).setCellValue(obj.getThirteenTo14());
				aRow.createCell(35).setCellValue(obj.getUp13to14());
				aRow.createCell(36).setCellValue(obj.getFourteenTo15());
				aRow.createCell(37).setCellValue(obj.getUp14to15());
				aRow.createCell(38).setCellValue(obj.getFifteenTo16());
				aRow.createCell(39).setCellValue(obj.getUp15to16());
				aRow.createCell(40).setCellValue(obj.getSixteenTo17());
				aRow.createCell(41).setCellValue(obj.getUp16to17());
				aRow.createCell(42).setCellValue(obj.getSeventeenTo18());
				aRow.createCell(43).setCellValue(obj.getUp17to18());
				aRow.createCell(44).setCellValue(obj.getEighteenTo19());
				aRow.createCell(45).setCellValue(obj.getUp18to19());
				aRow.createCell(46).setCellValue(obj.getNineteenTo20());
				aRow.createCell(47).setCellValue(obj.getUp19to20());
				aRow.createCell(48).setCellValue(obj.getTwentyTo21());
				aRow.createCell(49).setCellValue(obj.getUp20to21());
				aRow.createCell(50).setCellValue(obj.getTwenty1To22());
				aRow.createCell(51).setCellValue(obj.getUp21to22());
				aRow.createCell(52).setCellValue(obj.getTwenty2To23());
				aRow.createCell(53).setCellValue(obj.getUp22to23());
				aRow.createCell(54).setCellValue(obj.getTwenty3To24());
				aRow.createCell(55).setCellValue(obj.getUp23to24());
				
				
				day1_down  = day1_down.add(new BigDecimal(obj.getZeroTo1()));
				day1_up = day1_up.add(new BigDecimal(obj.getUp0to1()));
				day2_down = day2_down.add(new BigDecimal(obj.getOneTo2()));
				day2_up = day2_up.add(new BigDecimal(obj.getUp1to2()));
				day3_down = day3_down.add(new BigDecimal(obj.getTwoTo3()));
				day3_up = day3_up.add(new BigDecimal(obj.getUp2to3()));
				day4_down = day4_down.add(new BigDecimal(obj.getThreeTo4()));
				day4_up = day4_up.add(new BigDecimal(obj.getUp3to4()));
				day5_down = day5_down.add(new BigDecimal(obj.getFourTo5()));
				day5_up = day5_up.add(new BigDecimal(obj.getUp4to5()));
				day6_down = day6_down.add(new BigDecimal(obj.getFiveTo6()));
				day6_up = day6_up.add(new BigDecimal(obj.getUp5to6()));
				day7_down = day7_down.add(new BigDecimal(obj.getSixTo7()));
				day7_up = day7_up.add(new BigDecimal(obj.getUp6to7()));
				day8_down = day8_down.add(new BigDecimal(obj.getSevenTo8()));
				day8_up = day8_up.add(new BigDecimal(obj.getUp7to8()));
				day9_down = day9_down.add(new BigDecimal(obj.getEightTo9()));
				day9_up = day9_up.add(new BigDecimal(obj.getUp8to9()));
				day10_down = day10_down.add(new BigDecimal(obj.getNineTo10()));
				day10_up = day10_up.add(new BigDecimal(obj.getUp9to10()));
				day11_down = day11_down.add(new BigDecimal(obj.getTenTo11()));
				day11_up = day11_up.add(new BigDecimal(obj.getUp10to11()));
				day12_down = day12_down.add(new BigDecimal(obj.getElevenTo12()));
				day12_up = day12_up.add(new BigDecimal(obj.getUp11to12()));
				day13_down = day13_down.add(new BigDecimal(obj.getTwelveTo13()));
				day13_up = day13_up.add(new BigDecimal(obj.getUp12to13()));
				day14_down = day14_down.add(new BigDecimal(obj.getThirteenTo14()));
				day14_up = day14_up.add(new BigDecimal(obj.getUp13to14()));
				day15_down = day15_down.add(new BigDecimal(obj.getFourteenTo15()));
				day15_up = day15_up.add(new BigDecimal(obj.getUp14to15()));
				day16_down = day16_down.add(new BigDecimal(obj.getFifteenTo16()));
				day16_up = day16_up.add(new BigDecimal(obj.getUp15to16()));
				day17_down = day17_down.add(new BigDecimal(obj.getSixteenTo17()));
				day17_up = day17_up.add(new BigDecimal(obj.getUp16to17()));
				day18_down = day18_down.add(new BigDecimal(obj.getSeventeenTo18()));
				day18_up = day18_up.add(new BigDecimal(obj.getUp17to18()));
				day19_down = day19_down.add(new BigDecimal(obj.getEighteenTo19()));
				day19_up = day19_up.add(new BigDecimal(obj.getUp18to19()));
				day20_down = day20_down.add(new BigDecimal(obj.getNineteenTo20()));
				day20_up = day20_up.add(new BigDecimal(obj.getUp19to20()));
				day21_down = day21_down.add(new BigDecimal(obj.getTwentyTo21()));
				day21_up = day21_up.add(new BigDecimal(obj.getUp20to21()));
				day22_down = day22_down.add(new BigDecimal(obj.getTwenty1To22()));
				day22_up = day22_up.add(new BigDecimal(obj.getUp21to22()));
				day23_down = day23_down.add(new BigDecimal(obj.getTwenty2To23()));
				day23_up = day23_up.add(new BigDecimal(obj.getUp22to23()));
				day24_down = day24_down.add(new BigDecimal(obj.getTwenty3To24()));
				day24_up = day24_up.add(new BigDecimal(obj.getUp23to24()));
				
				total_up = total_up.add(new BigDecimal(obj.getUploadTotal()));
				total_down = total_down.add(new BigDecimal(obj.getTotalUsageOnDay()));
				totalUsage = totalUsage.add(new BigDecimal(obj.getTotalUsage())) ;
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

			aRow.createCell(0).setCellValue("Total");
			aRow.getCell(0).setCellStyle(style);
			cell.setCellStyle(style);
			
			aRow.createCell(5).setCellValue(totalUsage.toString());
			aRow.createCell(6).setCellValue(total_down.toString());
			aRow.createCell(7).setCellValue(total_up.toString());
			
			aRow.createCell(8).setCellValue(day1_down.toString());
			aRow.createCell(9).setCellValue(day1_up.toString());
			aRow.createCell(10).setCellValue(day2_down.toString());
			aRow.createCell(11).setCellValue(day2_up.toString());
			aRow.createCell(12).setCellValue(day3_down.toString());
			aRow.createCell(13).setCellValue(day3_up.toString());
			aRow.createCell(14).setCellValue(day4_down.toString());
			aRow.createCell(15).setCellValue(day4_up.toString());
			aRow.createCell(16).setCellValue(day5_down.toString());
			aRow.createCell(17).setCellValue(day5_up.toString());
			aRow.createCell(18).setCellValue(day6_down.toString());
			aRow.createCell(19).setCellValue(day6_up.toString());
			aRow.createCell(20).setCellValue(day7_down.toString());
			aRow.createCell(21).setCellValue(day7_up.toString());
			aRow.createCell(22).setCellValue(day8_down.toString());
			aRow.createCell(23).setCellValue(day8_up.toString());
			aRow.createCell(24).setCellValue(day9_down.toString());
			aRow.createCell(25).setCellValue(day9_up.toString());
			aRow.createCell(26).setCellValue(day10_down.toString());
			aRow.createCell(27).setCellValue(day10_up.toString());
			aRow.createCell(28).setCellValue(day11_down.toString());
			aRow.createCell(29).setCellValue(day11_up.toString());
			aRow.createCell(30).setCellValue(day12_down.toString());
			aRow.createCell(31).setCellValue(day12_up.toString());
			aRow.createCell(32).setCellValue(day13_down.toString());
			aRow.createCell(33).setCellValue(day13_up.toString());
			aRow.createCell(34).setCellValue(day14_down.toString());
			aRow.createCell(35).setCellValue(day14_up.toString());
			aRow.createCell(36).setCellValue(day15_down.toString());
			aRow.createCell(37).setCellValue(day15_up.toString());
			aRow.createCell(38).setCellValue(day16_down.toString());
			aRow.createCell(39).setCellValue(day16_up.toString());
			aRow.createCell(40).setCellValue(day17_down.toString());
			aRow.createCell(41).setCellValue(day17_up.toString());
			aRow.createCell(42).setCellValue(day18_down.toString());
			aRow.createCell(43).setCellValue(day18_up.toString());
			aRow.createCell(44).setCellValue(day19_down.toString());
			aRow.createCell(45).setCellValue(day19_up.toString());
			aRow.createCell(46).setCellValue(day20_down.toString());
			aRow.createCell(47).setCellValue(day20_up.toString());
			aRow.createCell(48).setCellValue(day21_down.toString());
			aRow.createCell(49).setCellValue(day21_up.toString());
			aRow.createCell(50).setCellValue(day22_down.toString());
			aRow.createCell(51).setCellValue(day22_up.toString());
			aRow.createCell(52).setCellValue(day23_down.toString());
			aRow.createCell(53).setCellValue(day23_up.toString());
			aRow.createCell(54).setCellValue(day24_down.toString());
			aRow.createCell(55).setCellValue(day24_up.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	public List<Object[]> getActiveLandLines(String stateID, String districtId, String mandalId, String stdCodeNum,String landLineNum) {
		List<Object[]> acticeLandLineNums = new ArrayList<>();
		acticeLandLineNums=reportsDaoImpl.getActiveLandLines(stateID, districtId, mandalId, stdCodeNum, landLineNum);
		return acticeLandLineNums;
	}

	public HSSFWorkbook termCellReportDownload(String mandalId, String districtId, String stdCodeNum,String landLineNum) {
		List<Object[]> acticeLandLineNums = new ArrayList<>();
		String stateID="1";
		acticeLandLineNums=reportsDaoImpl.getActiveLandLines(stateID, districtId, mandalId, stdCodeNum, landLineNum);
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Term Cell Report");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0); 
		HSSFRow row1 = sheet.createRow(1); 
		HSSFRow row2 = sheet.createRow(2); 
		 

		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());
		try (InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {

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
			cell.setCellValue("Term Cell Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("Account No");
			header.getCell(0).setCellStyle(style);
			
			header.createCell(1).setCellValue("Customer Name");
			header.getCell(1).setCellStyle(style);
			
			header.createCell(2).setCellValue("Aadhar Number");
			header.getCell(2).setCellStyle(style);
			
			header.createCell(3).setCellValue("Customer Type ");
			header.getCell(3).setCellStyle(style);
			
			header.createCell(4).setCellValue("Land Line Number");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Date Of Activation");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("Active Plan");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("Address");
			header.getCell(7).setCellStyle(style);

			 
			// create data rows
			int rowCount = 5;
			CellStyle rowStyle = workbook.createCellStyle();
			rowStyle.setAlignment(CellStyle.ALIGN_CENTER);
			for (Object[] obj : acticeLandLineNums) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(obj[0] == null ? "N.A" : obj[0].toString());
				aRow.getCell(0).setCellStyle(rowStyle);
				aRow.createCell(1).setCellValue(obj[1] == null ? "N.A" : obj[1].toString());
				aRow.createCell(2).setCellValue(obj[2] == null ? "N.A" : obj[2].toString());
				aRow.getCell(2).setCellStyle(rowStyle);
				aRow.createCell(3).setCellValue(obj[3] == null ? "N.A" : obj[3].toString());
				aRow.getCell(3).setCellStyle(rowStyle);
				aRow.createCell(4).setCellValue(obj[4] == null ? "N.A" : obj[4].toString());
				aRow.getCell(4).setCellStyle(rowStyle);
				aRow.createCell(5).setCellValue(obj[5] == null ? "N.A" : obj[5].toString());
				aRow.getCell(5).setCellStyle(rowStyle);
				aRow.createCell(6).setCellValue(obj[6] == null ? "N.A" : obj[6].toString());
				aRow.getCell(6).setCellStyle(rowStyle);
				aRow.createCell(7).setCellValue(obj[7] == null ? "N.A" : obj[7].toString());
			 
				
							}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	public List<HSICummSummaryMonthlyViewDTO> getTotalAAAUsagePerMonthDaywise(String month, String year) {
		return reportsDaoImpl.getTotalAAAUsagePerMonthDaywise(month,year);
	}

	public HSICummSummaryMonthlyViewDTO getTotalAAAUsagePerMonth(String month, String year) {
		return reportsDaoImpl.getTotalAAAUsagePerMonth(month,year);
	}

	public List<HSICummSummaryMonthlyCustViewDTO> getTotalAAAUsagePerMonthCustomerDayWise(String month, String year,String cafNo) {
		return reportsDaoImpl.getTotalAAAUsagePerMonthCustomerDayWise(month,year,cafNo);
	}
	
	public HSICummSummaryMonthlyCustViewDTO getTotalAAAUsagePerMonthCustomer(String month, String year, String cafNo) {
		return reportsDaoImpl.getTotalAAAUsagePerMonthCustomer(month,year,cafNo);
	}

public ReportsDTO getAgeingReport(String date,PageObject pageObject){
		
		List<Object[]> list= new ArrayList<>();
		List<AgeingReportVO> ageingList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			list=reportsDaoImpl.getAgeingReport(date,pageObject);
			
			for(Object[] obj : list){
				AgeingReportVO ageingVO = new AgeingReportVO();
				ageingVO.setCafNo(obj[0] != null ? obj[0].toString() : "NA");
				ageingVO.setAadharNo(obj[1] != null ? obj[1].toString() : "NA");
				ageingVO.setStbMacAddr(obj[2] != null ? obj[2].toString() : "NA");
				ageingVO.setSubsCode(obj[3] != null ? obj[3].toString() : "NA");
				ageingVO.setCustId(obj[4] != null ? obj[4].toString() : "NA");
				ageingVO.setIptvPack(obj[5] != null ? obj[5].toString() : "NA");
				ageingVO.setActDate(obj[6] != null ? obj[6].toString() : "NA");
				ageingVO.setStatus(obj[7] != null ? obj[7].toString() : "NA");
				ageingVO.setAgeing(obj[8] != null ? obj[8].toString() : "NA");
				
				ageingList.add(ageingVO);
			}
			reportDto.setAgeingList(ageingList);
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}
	
	public ReportsDTO getSrvcWiseSubsReport(String date,PageObject pageObject){
		
		List<Object[]> list= new ArrayList<>();
		List<SrvsWiseSubsVO> subsList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			list=reportsDaoImpl.getSrvcWiseSubsReport(date,pageObject);
			
			for(Object[] obj : list){
				SrvsWiseSubsVO subsVO = new SrvsWiseSubsVO();
				subsVO.setIptvpackname(obj[0] !=null ? obj[0].toString() : "NA");
				subsVO.setTotal(obj[1] != null ? obj[1].toString() : "NA");
				subsList.add(subsVO);
			}
			reportDto.setSubsList(subsList);
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}

	public ReportsDTO getPackWiseSubsReport(String srvc,String activationDate,PageObject pageObject){
		
		List<Object[]> list= new ArrayList<>();
		List<PackWiseSubsVO> subsList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			list=reportsDaoImpl.getPackWiseSubsReport(srvc,activationDate,pageObject);
			
			for(Object[] obj : list){
				PackWiseSubsVO subsVO = new PackWiseSubsVO();
				subsVO.setAadharNo(obj[0] != null ? obj[0].toString() : "NA");
				subsVO.setStbMacId(obj[1] != null ? obj[1].toString() : "NA");
				subsVO.setStbSlno(obj[2] != null ? obj[2].toString() : "NA");
				subsVO.setPackName(obj[3] != null ? obj[3].toString() : "NA");
				
				subsList.add(subsVO);
			}
			reportDto.setPackList(subsList);
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}

    public ReportsDTO getSubsDtlsReport(String fromDate,String toDate,PageObject pageObject){
		
		List<Object[]> list= new ArrayList<>();
		List<SubsDtlsVO> subsList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			list=reportsDaoImpl.getSubsDtlsReport(fromDate,toDate,pageObject);
			
			for(Object[] obj : list){
				SubsDtlsVO subsVO = new SubsDtlsVO();
				
				subsVO.setStbMacId(obj[0] != null ? obj[0].toString() : "NA");
				subsVO.setCafNo(obj[1] != null ? obj[1].toString() : "NA");
				subsVO.setPackName(obj[2] != null ? obj[2].toString() : "NA");
				subsVO.setSubsCode(obj[3] != null ? obj[3].toString() : "NA");
				subsVO.setActDate(obj[4] != null ? obj[4].toString() : "NA");
				subsVO.setDeactDate(obj[5] != null ? obj[5].toString() : "NA");
				subsVO.setSuspDate(obj[6] != null ? obj[6].toString() : "NA");
				subsVO.setResumeDate(obj[7] != null ? obj[7].toString() : "NA");
				subsVO.setBlacklistDate(obj[25] != null ? obj[25].toString() : "NA");
				subsVO.setIptvPackName(obj[8] != null ? obj[8].toString() : "NA");
				subsVO.setCustId(obj[11] != null ? obj[11].toString() : "NA");
				subsVO.setAadharNo(obj[12] != null ? obj[12].toString() : "NA");
				subsVO.setOnuMacId(obj[13] != null ? obj[13].toString() : "NA");
				subsVO.setInstAddr1(obj[14] != null ? obj[14].toString() : "NA");
				subsVO.setInstAddr2(obj[15] != null ? obj[15].toString() : "NA");
				subsVO.setInstArea(obj[16] != null ? obj[16].toString() : "NA");
				subsVO.setInstCity(obj[17] != null ? obj[17].toString() : "NA");
				subsVO.setInstLocality(obj[18] != null ? obj[18].toString() : "NA");
				subsVO.setInstMandal(obj[19] != null ? obj[19].toString() : "NA");
				subsVO.setInstDist(obj[20] != null ? obj[20].toString() : "NA");
				subsVO.setInstPin(obj[21] != null ? obj[21].toString() : "NA");
				subsVO.setLatitude(obj[22] != null ? obj[22].toString() : "NA");
				subsVO.setLongitude(obj[23] != null ? obj[23].toString() : "NA");
				subsVO.setStatus(obj[24] != null ? obj[24].toString() : "NA");

				subsList.add(subsVO);
			}
			reportDto.setSubsDtlsList(subsList);
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}

	public ReportsDTO getPackWiseChnls(String date,PageObject pageObject){
		
		List<Object[]> list= new ArrayList<>();
		List<PackWiseChnlsVO> chnlList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			list=reportsDaoImpl.getPackWiseChnls(date,pageObject);
			
			for(Object[] obj : list){
				
				PackWiseChnlsVO chnlVO = new PackWiseChnlsVO();
				chnlVO.setPackCode(obj[0] != null ? obj[0].toString() : "NA");
				chnlVO.setPackName(obj[1] != null ? obj[1].toString() : "NA");
				chnlVO.setChnlCode(obj[2] != null ? obj[2].toString() : "NA");
				chnlVO.setChnlName(obj[3] != null ? obj[3].toString() : "NA");
				
				chnlList.add(chnlVO);
			}
			reportDto.setChnlList(chnlList);
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}
	
	public ReportsDTO getTransactionLog(String fromDate,String toDate,PageObject pageObject){
			
			List<Object[]> list= new ArrayList<>();
			List<TransactionLogVO> txnList = new ArrayList<>();
			ReportsDTO reportDto = new ReportsDTO();
			
			try{
				list=reportsDaoImpl.getTransactionLog(fromDate,toDate,pageObject);
				
				for(Object[] obj : list){
					
					TransactionLogVO txnVO = new TransactionLogVO();
					txnVO.setCafNo(obj[0] != null ? obj[0].toString() : "NA");
					txnVO.setAadharNo(obj[1] != null ? obj[1].toString() : "NA");
					txnVO.setStbMacAddr(obj[2] != null ? obj[2].toString() : "NA");
					txnVO.setOnuSlno(obj[3] != null ? obj[3].toString() : "NA");
					txnVO.setStbSlno(obj[4] != null ? obj[4].toString() : "NA");
					txnVO.setIptvPack(obj[5] != null ? obj[5].toString() : "NA");
					txnVO.setTransaction(obj[6] != null ? obj[6].toString() : "NA");
					txnVO.setTxnDate(obj[7] != null ? obj[7].toString() : "NA");
					txnVO.setStatus(obj[8] != null ? obj[8].toString() : "NA");
					
					txnList.add(txnVO);
				}
				reportDto.setTxnList(txnList);;
				reportDto.setCount(pageObject.getTotalDisplayCount());
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			return reportDto;
	}

	public ReportsDTO getCpeOrderReport(String fromDate,String toDate,PageObject pageObject){
		
		List<Object[]> list= new ArrayList<>();
		List<CpeOrderVO> cpeList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			list=reportsDaoImpl.getCpeOrderReport(fromDate,toDate,pageObject);
			
			for(Object[] obj : list){
				
				CpeOrderVO cpeVO = new CpeOrderVO();
				cpeVO.setMsoCode(obj[0] != null ? obj[0].toString() : "NA");
				cpeVO.setMsoName(obj[1] != null ? obj[1].toString() : "NA");
				cpeVO.setDistrict(obj[2] != null ? obj[2].toString() : "NA");
				cpeVO.setMandal(obj[3] != null ? obj[3].toString() : "NA");
				cpeVO.setCpeType(obj[4] != null ? obj[4].toString() : "NA");
				cpeVO.setCpeModel(obj[5] != null ? obj[5].toString() : "NA");
				cpeVO.setOrderDate(obj[6] != null ? obj[6].toString() : "NA");
				cpeVO.setCpeCost(obj[7] != null ? obj[7].toString() : "NA");
				cpeVO.setQnty(obj[8] != null ? obj[8].toString() : "NA");
				cpeVO.setTotalCost(obj[9] != null ? obj[9].toString() : "NA");
				
				cpeList.add(cpeVO);
			}
			reportDto.setCpeList(cpeList);
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}

	public ReportsDTO getStatusWiseSubs(String fromDate,String toDate,String status,PageObject pageObject,boolean tilldate){
		
		List<Object[]> list= new ArrayList<>();
		List<StatusWiseSubsVO> subsList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			list=reportsDaoImpl.getStatusWiseSubs(fromDate,toDate,status,pageObject,tilldate);
			
			for(Object[] obj : list){
				
				StatusWiseSubsVO subsVO = new StatusWiseSubsVO();
				subsVO.setStbMacId(obj[4] != null ? obj[4].toString() : "NA");
				subsVO.setSubsCode(obj[1] != null ? obj[1].toString() : "NA");
				subsVO.setActDate(obj[2] != null ? obj[2].toString() : "NA");
				subsVO.setSuspDate(obj[5] != null ? obj[5].toString() : "NA");
				subsVO.setDeactDate(obj[6] != null ? obj[6].toString() : "NA");
				subsVO.setBlackListDate(obj[7] != null ? obj[7].toString() : "NA");
				
				subsList.add(subsVO);
			}
			reportDto.setStatusWiseList(subsList);;
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}

	public ReportsDTO getChnlsOnAlaCarteBasis(String fromDate,String toDate,PageObject pageObject){
		
		List<Object[]> list= new ArrayList<>();
		List<ChnlsOnAlacarteVO> chnlList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			list=reportsDaoImpl.getChnlsOnAlaCarteBasis(fromDate,toDate,pageObject);
			
			for(Object[] obj : list){
				
				ChnlsOnAlacarteVO chnlsVO = new ChnlsOnAlacarteVO();
				chnlsVO.setCafNo(obj[0] != null ? obj[0].toString() : "NA");
				chnlsVO.setChnlCode(obj[1] != null ? obj[1].toString() : "NA");
				chnlsVO.setChalName(obj[2] != null ? obj[2].toString() : "NA");
				chnlsVO.setAadharNo(obj[3] != null ? obj[3].toString() : "NA");
				chnlsVO.setSubsCode(obj[4] != null ? obj[4].toString() : "NA");
				chnlsVO.setStbSlno(obj[5] != null ? obj[5].toString() : "NA");
				chnlsVO.setStbMacId(obj[6] != null ? obj[6].toString() : "NA");
				
				chnlList.add(chnlsVO);
			}
			reportDto.setAlacarteList(chnlList);
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}
	
	public List<HSICummSummaryMonthlyCustViewDTO> getTotalAAAUsagePerMonthDaywisePerCust(String month, String year, String cafNo,String day) {
        return reportsDaoImpl.getTotalAAAUsagePerMonthDaywisePerCust(month, year, cafNo, day);
    }
	
	
	public String getCpeCount(String fromDate, String toDate, String cpeType,String status,String distUid) {
		return reportsDaoImpl.getCpeCount(fromDate, toDate, cpeType, status,distUid);
	}
	
	
	public List<CpeStockVO> getCPEStockDetails(String fromDate, String toDate, String cpeType,String status,String distUid) {
		List<CpeStockVO> cpeList = new ArrayList<CpeStockVO>();
		List<Object[]> resultcpeList = new ArrayList<>();
		boolean exists=false;
		try {
			resultcpeList = reportsDaoImpl.getCpeDetails(fromDate, toDate, cpeType, status,distUid);
			for (Object[] list : resultcpeList) {
				CpeStockVO cpe = new CpeStockVO();
				
				cpe.setCpeSrlno(list[0] == null || list[0].toString() == "" ? "NA" : list[0].toString());
				cpe.setMacAddress(list[1] == null || list[1].toString() == "" ? "NA" : list[1].toString());
				cpe.setProfileId(list[2] == null || list[2].toString() == "" ? "NA" : list[2].toString());
				cpe.setMspCode(list[5] == null || list[5].toString() == "" ? "NA" : list[5].toString());
				cpe.setLmoCode(list[6] == null || list[6].toString() == "" ? "NA" : list[6].toString());
				cpe.setCpeTypeLov(list[8] == null || list[8].toString() == "" ? "NA" : list[8].toString());
				cpe.setCpeProfileName(list[9] == null || list[9].toString() == "" ? "NA" : list[9].toString());
				cpe.setDistrictName(list[10] == null || list[10].toString() == "" ? "NA" : list[10].toString());
				cpe.setMandalName(list[11] == null || list[11].toString() == "" ? "NA" : list[11].toString());
				cpe.setVillageName(list[12] == null || list[12].toString() == "" ? "NA" : list[12].toString());
				cpe.setCpeModel(list[13] == null || list[13].toString() == "" ? "NA" : list[13].toString());
				cpe.setCpeBatchDate(list[4] == null || list[4].toString() == "" ? "NA" : list[4].toString());
				cpe.setStatus(list[7] == null || list[7].toString() == "" ? "" : list[7].toString());
				cpe.setCpeCafNo(list[14] == null || list[14].toString() == "" ? "" : list[14].toString());
				cpe.setTenantCode(list[15] == null || list[15].toString() == "" ? "" : list[15].toString());
				cpe.setCafActivationDate(list[16] == null || list[16].toString() == "" ? "" : list[16].toString());
				cpe.setCafDeactivationDate(list[17] == null || list[17].toString() == "" ? "" : list[17].toString());
			
				//added  to remove duplicates for cpe stock with both mso and lmo code filled
			 for (int j = 0; j < cpeList.size(); j++) {
						  
						  if (cpe.getCpeSrlno().equals(cpeList.get(j).getCpeSrlno()))
					   {
							  if (cpe.getTenantCode().contains("MSO")){
								  exists=true;  
							  break;
							  }else
							  {
								  cpeList.remove(j);
								  exists=false;
								  break;
							  }
							 
								  
					   }else
						   exists=false;
						  
					}
			 if (!exists)
				 cpeList.add(cpe); 

			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			resultcpeList = null;
		}
		return cpeList;
	}
	
	
	
}
