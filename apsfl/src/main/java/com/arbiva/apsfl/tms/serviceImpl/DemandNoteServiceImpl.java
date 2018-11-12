package com.arbiva.apsfl.tms.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.arbiva.apfgc.tenant.bo.CafWiseReportBO;
import com.arbiva.apfgc.tenant.bo.CafWiseRevenueOfLoginLmo;
import com.arbiva.apfgc.tenant.bo.CafWiseRevenueOfLoginLmoBo;
import com.arbiva.apfgc.tenant.bo.CpeModelBO;
import com.arbiva.apfgc.tenant.bo.DistrictPonWiseCafCountBO;
import com.arbiva.apfgc.tenant.bo.DistrictWiseCafBO;
import com.arbiva.apfgc.tenant.bo.DistrictWiseCpeBO;
import com.arbiva.apfgc.tenant.bo.EnterpriseSubscriberBO;
import com.arbiva.apfgc.tenant.bo.TenantStockReportBO;
import com.arbiva.apfgc.tenant.bo.LmoStockCountBO;
import com.arbiva.apfgc.tenant.bo.LmoWalletUpdateByChequePaymentBO;
import com.arbiva.apfgc.tenant.bo.MsoCafNotCpeStockBo;
import com.arbiva.apfgc.tenant.bo.MsoDetailsWithLmosBO;
import com.arbiva.apfgc.tenant.bo.MsoRevenueShareBO;
import com.arbiva.apfgc.tenant.bo.MsoWiseCpeBo;
import com.arbiva.apfgc.tenant.bo.OLTMasterDataBO;
import com.arbiva.apfgc.tenant.bo.Offline_Payment1;
import com.arbiva.apfgc.tenant.bo.PONWiseBo;
import com.arbiva.apfgc.tenant.bo.PONWithZeroCAFBO;
import com.arbiva.apfgc.tenant.bo.RiggedCafBO;
import com.arbiva.apfgc.tenant.bo.SubstationWiseCafBo;
import com.arbiva.apfgc.tenant.bo.TenantStockReportBO;
import com.arbiva.apsfl.coms.dto.ComsHelperDTO;
import com.arbiva.apsfl.coms.dto.CustomerDTO;
import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.dto.Offline_Payment;

import com.arbiva.apsfl.reports.vo.MsoListVo;
import com.arbiva.apsfl.reports.vo.ReportsDTO;
import com.arbiva.apsfl.reports.vo.SubstationWiseCafVO;
import com.arbiva.apsfl.tms.daoImpl.DemandNoteDaoImpl;
import com.arbiva.apsfl.tms.model.CafMaster;
import com.arbiva.apsfl.tms.model.EmailMaster;
import com.arbiva.apsfl.tms.model.Tenant;
import com.arbiva.apsfl.util.DateUtill;
import com.arbiva.apsfl.util.MergeExcelUtil;

@Service
@Transactional
public class DemandNoteServiceImpl {

	@Autowired
	DemandNoteDaoImpl demandNoteDaoImpl;

	public List<MsoListVo> getMsoList() {
		List<Object[]> listMso = null;
		List<MsoListVo> msoListObject = new ArrayList<>();

		try {
			listMso = demandNoteDaoImpl.getMsoList();
			for (Object[] object : listMso) {
				MsoListVo msoListVo = new MsoListVo();

				msoListVo.setTenantCode(object[0] == null ? "N.A" : object[0].toString());
				msoListVo.setTenantName(object[1] == null ? "N.A" : object[1].toString());
				msoListVo.setPocMobileNumber(object[2] == null ? "N.A" : object[2].toString());
				msoListVo.setEmiDemandQuantity(object[3] == null ? "N.A" : object[3].toString());
				msoListVo.setEmi36DemandQuantity(object[4] == null ? "N.A" : object[4].toString());
				msoListVo.setEmi48DemandQuantity(object[5] == null ? "N.A" : object[5].toString());
				msoListVo.setRegOfficePocName(object[6] == null ? "N.A" : object[6].toString());
				msoListVo.setDistrictName(object[7] == null ? "N.A" : object[7].toString());
				msoListVo.setMandalName(object[8] == null ? "N.A" : object[8].toString());
				if (msoListVo.getEmiDemandQuantity().equalsIgnoreCase("0") && msoListVo.getEmi36DemandQuantity().equalsIgnoreCase("0") && msoListVo.getEmi48DemandQuantity().equalsIgnoreCase("0")) {

				} else {
					msoListObject.add(msoListVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msoListObject;

	}

	public List<MsoListVo> getLmoList(String tenantCode) {
		List<Object[]> listMso = null;
		List<MsoListVo> msoListObject = new ArrayList<>();

		try {
			listMso = demandNoteDaoImpl.getLmoList(tenantCode);
			for (Object[] object : listMso) {
				MsoListVo msoListVo = new MsoListVo();
				msoListVo.setTenantName(object[0] == null ? "NA" : object[0].toString());
				msoListVo.setEmiDemandQuantity(object[1] == null ? "N.A" : object[1].toString());
				msoListVo.setEmi36DemandQuantity(object[2] == null ? "N.A" : object[2].toString());
				msoListVo.setEmi48DemandQuantity(object[3] == null ? "N.A" : object[3].toString());
				msoListVo.setMandalName(object[4] == null ? "NA" : object[4].toString());
				msoListVo.setDistrictName(object[5] == null ? "NA" : object[5].toString());

				if (msoListVo.getEmiDemandQuantity().equalsIgnoreCase("0") && msoListVo.getEmi36DemandQuantity().equalsIgnoreCase("0") && msoListVo.getEmi48DemandQuantity().equalsIgnoreCase("0")) {

				} else {
					msoListObject.add(msoListVo);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msoListObject;
	}

	public List<MsoWiseCpeBo> getMsoWiseDemand() {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getMsoWiseDemand();
	}

	public List<DistrictWiseCpeBO> getDistrictWiseDemand() {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getDistrictWiseDemand();
	}

	public List<CafWiseReportBO> getCafWiseDemand() {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getCafWiseDemand();
	}

	public List<EmailMaster> getEamilOfCafWiseReport(String val) {
		List<EmailMaster> listMso = new ArrayList<>();
		try {
			listMso = demandNoteDaoImpl.getEamilOfCafWiseReport(val);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMso;
	}

	public HSSFWorkbook getCafWiseExcelFile() {
		List<CafWiseReportBO> list = new ArrayList<>();
		list = this.getCafWiseDemand();

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Caf Report");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		HSSFRow row3 = sheet.createRow(3);

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
			cell.setCellValue("Caf Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			/* For creating fourth row */
			cell = row3.createCell(7);
			cell.setCellValue("No. Of Enterprise CAF's On The Day");
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 7, 8));
			cell.setCellStyle(style);

			/* For creating fourth row */
			cell = row3.createCell(9);
			cell.setCellValue("Cumulative Enterprise CAF's");
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 9, 10));
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("Date");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("District");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Village / City");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Name Of the LCO");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("No. Of CAF's  on the day(HH)");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("Cumulative CAF's(HH)");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("Private");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("Govt.");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("Private");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("Govt.");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("Cumulative CAF's");
			header.getCell(11).setCellStyle(style);

			// create data rows
			int rowCount = 5;
			int i = 0;

			long nc = 0, cc = 0, op = 0, oe = 0, cp = 0, ce = 0, ecc = 0;

			for (CafWiseReportBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getMaxdat());
				aRow.createCell(2).setCellValue(obj.getDistrictname());
				aRow.createCell(3).setCellValue(obj.getVillage());
				aRow.createCell(4).setCellValue(obj.getLmoName());
				aRow.createCell(5).setCellValue(obj.getCafsCountOnTheDay());
				aRow.createCell(6).setCellValue(obj.getCumulativeCafs());
				aRow.createCell(7).setCellValue(obj.getEpCafsCountPrivateDay());
				aRow.createCell(8).setCellValue(obj.getEpCafsCountGovtDay());
				aRow.createCell(9).setCellValue(obj.getEpCafsCountPrivateCu());
				aRow.createCell(10).setCellValue(obj.getEpCafsCountGovtCu());
				aRow.createCell(11).setCellValue(obj.getCumulativeCafsEntered());

				nc = nc + obj.getCafsCountOnTheDay();
				cc = cc + obj.getCumulativeCafs();
				op = op + obj.getEpCafsCountPrivateDay();
				oe = oe + obj.getEpCafsCountGovtDay();
				cp = cp + obj.getEpCafsCountPrivateCu();
				ce = ce + obj.getEpCafsCountGovtCu();
				ecc = ecc + obj.getCumulativeCafsEntered();

			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

			aRow.createCell(0).setCellValue("Total");
			aRow.getCell(0).setCellStyle(style);
			cell.setCellStyle(style);
			aRow.createCell(5).setCellValue(nc);
			aRow.createCell(6).setCellValue(cc);
			aRow.createCell(7).setCellValue(op);
			aRow.createCell(8).setCellValue(oe);
			aRow.createCell(9).setCellValue(cp);
			aRow.createCell(10).setCellValue(ce);
			aRow.createCell(11).setCellValue(ecc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	public <T> T save(T t) {
		return demandNoteDaoImpl.save(t);
	}

	public HSSFWorkbook getDistrictWiseExcelFile() {

		List<DistrictWiseCpeBO> listMso = new ArrayList<>();
		listMso = this.getDistrictWiseDemand();

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("District Wise CPE Request Report");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		HSSFRow row3 = sheet.createRow(3);

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
			cell.setCellValue("District Wise CPE Request Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			/* For creating fourth row */
			cell = row3.createCell(2);
			cell.setCellValue("No. Of Boxes Up To " + DateUtill.getPreviousSTRING_dd_MMM_YYYY());
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 5));
			cell.setCellStyle(style);

			/* For creating fourth row */
			cell = row3.createCell(6);
			cell.setCellValue("No. Of Boxes On The Day " + DateUtill.getSTRING_dd_MMM_YYYY());
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 9));
			cell.setCellStyle(style);

			/* For creating fourth row */
			cell = row3.createCell(10);
			cell.setCellValue("Total");
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 10, 13));
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("District");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("4000");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("1700");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("500");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Total");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("4000");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("1700");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("500");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("Total");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("4000");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("1700");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("500");
			header.getCell(12).setCellStyle(style);

			header.createCell(13).setCellValue("Total");
			header.getCell(13).setCellStyle(style);

			// create data rows
			int rowCount = 5;
			int i = 0;
			long y1 = 0, y2 = 0, y3 = 0, yt = 0;
			long t1 = 0, t2 = 0, t3 = 0, tt = 0;
			long ft1 = 0, ft2 = 0, ft3 = 0, ft = 0;

			for (DistrictWiseCpeBO obj : listMso) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getDistrictname());
				aRow.createCell(2).setCellValue(obj.getD2qty0());
				aRow.createCell(3).setCellValue(obj.getD2qty36());
				aRow.createCell(4).setCellValue(obj.getD2qty48());
				aRow.createCell(5).setCellValue(obj.getD2DisTotal());
				aRow.createCell(6).setCellValue(obj.getD1qty0());
				aRow.createCell(7).setCellValue(obj.getD1qty36());
				aRow.createCell(8).setCellValue(obj.getD1qty48());
				aRow.createCell(9).setCellValue(obj.getD1DisTotal());
				aRow.createCell(10).setCellValue(obj.getDis4000Total());
				aRow.createCell(11).setCellValue(obj.getDis1700Total());
				aRow.createCell(12).setCellValue(obj.getDis500Total());
				aRow.createCell(13).setCellValue(obj.getDisTotal());
				y1 = y1 + obj.getD2qty0();
				y2 = y2 + obj.getD2qty36();
				y3 = y3 + obj.getD2qty48();
				yt = yt + obj.getD2DisTotal();
				t1 = t1 + obj.getD1qty0();
				t2 = t2 + obj.getD1qty36();
				t3 = t3 + obj.getD1qty48();
				tt = tt + obj.getD1DisTotal();
				ft1 = ft1 + obj.getDis4000Total();
				ft2 = ft2 + obj.getDis1700Total();
				ft3 = ft3 + obj.getDis500Total();
				ft = ft + obj.getDisTotal();

			}
			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

			aRow.createCell(0).setCellValue("Total");
			aRow.getCell(0).setCellStyle(style);
			cell.setCellStyle(style);
			aRow.createCell(2).setCellValue(y1);
			aRow.createCell(3).setCellValue(y2);
			aRow.createCell(4).setCellValue(y3);
			aRow.createCell(5).setCellValue(yt);
			aRow.createCell(6).setCellValue(t1);
			aRow.createCell(7).setCellValue(t2);
			aRow.createCell(8).setCellValue(t3);
			aRow.createCell(9).setCellValue(tt);
			aRow.createCell(10).setCellValue(ft1);
			aRow.createCell(11).setCellValue(ft2);
			aRow.createCell(12).setCellValue(ft3);
			aRow.createCell(13).setCellValue(ft);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	public HSSFWorkbook getMsoWiseExcelFile() {

		List<MsoWiseCpeBo> listMso = new ArrayList<>();
		listMso = this.getMsoWiseDemand();

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("MSO Wise CPE Request Report");
		Row header = sheet.createRow(4);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		HSSFRow row3 = sheet.createRow(3);

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
			cell.setCellValue("MSO Wise CPE Request Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			/* For creating fourth row */
			cell = row3.createCell(4);
			cell.setCellValue("Total Demand Raised On The Day");
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 4, 6));
			cell.setCellStyle(style);

			/* For creating fourth row */
			cell = row3.createCell(8);
			cell.setCellValue("Cumulative");
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 8, 10));
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("Date");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("District");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("MSO Code");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Name of the MSO");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("4000");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("1700");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("500");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("Total on the day");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("4000");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("1700");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("500");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("Cumulative Total");
			header.getCell(12).setCellStyle(style);

			// create data rows
			int rowCount = 5;
			int i = 0;
			long t1 = 0, t2 = 0, t3 = 0, tt = 0;
			long ft1 = 0, ft2 = 0, ft3 = 0, ft = 0;

			for (MsoWiseCpeBo obj : listMso) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getMaxdat());
				aRow.createCell(2).setCellValue(obj.getDistrictname());
				aRow.createCell(3).setCellValue(obj.getMspcode());
				aRow.createCell(4).setCellValue(obj.getMspname());
				aRow.createCell(5).setCellValue(obj.getDqty0());
				aRow.createCell(6).setCellValue(obj.getDqty36());
				aRow.createCell(7).setCellValue(obj.getDqty48());
				aRow.createCell(8).setCellValue(obj.getDaySum());
				aRow.createCell(9).setCellValue(obj.getCqty0());
				aRow.createCell(10).setCellValue(obj.getCqty36());
				aRow.createCell(11).setCellValue(obj.getCqty48());
				aRow.createCell(12).setCellValue(obj.getCumulativeSum());

				t1 = t1 + obj.getDqty0();
				t2 = t2 + obj.getDqty36();
				t3 = t3 + obj.getDqty48();
				tt = tt + obj.getDaySum();
				ft1 = ft1 + obj.getCqty0();
				ft2 = ft2 + obj.getCqty36();
				ft3 = ft3 + obj.getCqty48();
				ft = ft + obj.getCumulativeSum();

			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);
			aRow.createCell(0).setCellValue("Total");
			aRow.getCell(0).setCellStyle(style);
			cell.setCellStyle(style);
			aRow.createCell(5).setCellValue(t1);
			aRow.createCell(6).setCellValue(t2);
			aRow.createCell(7).setCellValue(t3);
			aRow.createCell(8).setCellValue(tt);
			aRow.createCell(9).setCellValue(ft1);
			aRow.createCell(10).setCellValue(ft2);
			aRow.createCell(11).setCellValue(ft3);
			aRow.createCell(12).setCellValue(ft);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	public List<DistrictWiseCafBO> getDistrictWiseCafList() {
		List<DistrictWiseCafBO> districtWiseCafList = new ArrayList<>();
		try {
			districtWiseCafList = demandNoteDaoImpl.getDistrictWiseCafList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return districtWiseCafList;
	}

	public HSSFWorkbook getDistrictWiseCafExcelFile() {

		List<DistrictWiseCafBO> districtWiseCafList = new ArrayList<>();
		districtWiseCafList = this.getDistrictWiseCafList();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("District Wise CAF Request Report");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		// HSSFRow row3 = sheet.createRow(3);//Headers

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
			
			CellStyle style1 = workbook.createCellStyle();
			Font font1 = workbook.createFont();
			font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style1.setFont(font);

			/* For creating first row */
			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			/* For creating second row */
			cell = row1.createCell(2);
			cell.setCellValue("No. Of CAF Up To " + DateUtill.getPreviousSTRING_dd_MMM_YYYY());
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 4));
			cell.setCellStyle(style);

			cell = row1.createCell(5);
			cell.setCellValue("No. Of CAF On The Day " + DateUtill.getSTRING_dd_MMM_YYYY());
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 7));
			cell.setCellStyle(style);

			cell = row1.createCell(8);
			cell.setCellValue("Total");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 10));
			cell.setCellStyle(style);

			/* For creating Third row */
			cell = row2.createCell(3);
			cell.setCellValue("No. Of Enterprise CAF's");
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 4));
			cell.setCellStyle(style);

			cell = row2.createCell(6);
			cell.setCellValue("No. Of Enterprise CAF's");
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 7));
			cell.setCellStyle(style);

			cell = row2.createCell(9);
			cell.setCellValue("No. Of Enterprise CAF's");
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 9, 10));
			cell.setCellStyle(style);

			/* For creating Headers row */
			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("District");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("House Hold CAF's");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Private");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Govt");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("House Hold CAF's");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("Private");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("Govt");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("House Hold CAF's");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("Private");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("Govt");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("Cumulative CAF's");
			header.getCell(11).setCellStyle(style);

			// create data rows
			int rowCount = 5;
			int i = 0;
			long htd = 0, ptd = 0, gtd = 0;
			long hod = 0, pod = 0, god = 0;
			long thd = 0, tpd = 0, tgd = 0;
			long alltotal = 0;

			for (DistrictWiseCafBO obj : districtWiseCafList) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getDistrictName());
				aRow.createCell(2).setCellValue(Integer.parseInt(obj.getHhupToday()));
				aRow.createCell(3).setCellValue(Integer.parseInt(obj.getPvtupToday()));
				aRow.createCell(4).setCellValue(Integer.parseInt(obj.getGovtupToday()));
				aRow.createCell(5).setCellValue(Integer.parseInt(obj.getHhOnday()));
				aRow.createCell(6).setCellValue(Integer.parseInt(obj.getPvtOnday()));
				aRow.createCell(7).setCellValue(Integer.parseInt(obj.getGovtOnday()));
				aRow.createCell(8).setCellValue(Integer.parseInt(obj.getHhupToday()) + Integer.parseInt(obj.getHhOnday()));
				aRow.createCell(9).setCellValue(Integer.parseInt(obj.getPvtupToday()) + Integer.parseInt(obj.getPvtOnday()));
				aRow.createCell(10).setCellValue(Integer.parseInt(obj.getGovtupToday()) + Integer.parseInt(obj.getGovtOnday()));
				aRow.createCell(11).setCellValue(Integer.parseInt(obj.getHhupToday()) + Integer.parseInt(obj.getPvtupToday()) + Integer.parseInt(obj.getGovtupToday()) + Integer.parseInt(obj.getHhOnday()) + Integer.parseInt(obj.getPvtOnday()) + Integer.parseInt(obj.getGovtOnday()));
				htd = htd + Integer.parseInt(obj.getHhupToday());
				ptd = ptd + Integer.parseInt(obj.getPvtupToday());
				gtd = gtd + Integer.parseInt(obj.getGovtupToday());
				hod = hod + Integer.parseInt(obj.getHhOnday());
				pod = pod + Integer.parseInt(obj.getPvtOnday());
				god = god + Integer.parseInt(obj.getGovtOnday());
				thd = thd + Integer.parseInt(obj.getHhupToday()) + Integer.parseInt(obj.getHhOnday());
				tpd = tpd + Integer.parseInt(obj.getPvtupToday()) + Integer.parseInt(obj.getPvtOnday());
				tgd = tgd + Integer.parseInt(obj.getGovtupToday()) + Integer.parseInt(obj.getGovtOnday());
				alltotal = alltotal + Integer.parseInt(obj.getHhupToday()) + Integer.parseInt(obj.getPvtupToday()) + Integer.parseInt(obj.getGovtupToday()) + Integer.parseInt(obj.getHhOnday()) + Integer.parseInt(obj.getPvtOnday()) + Integer.parseInt(obj.getGovtOnday());
			}
			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);
			
			aRow.createCell(0).setCellValue("Total");
			sheet.addMergedRegion(new CellRangeAddress(18, 18, 0, 1));
			aRow.getCell(0).setCellStyle(style);
			
			aRow.createCell(2).setCellValue(htd);
			aRow.getCell(2).setCellStyle(style1);
			
			aRow.createCell(3).setCellValue(ptd);
			aRow.getCell(3).setCellStyle(style1);
			
			aRow.createCell(4).setCellValue(gtd);
			aRow.getCell(4).setCellStyle(style1);
			
			aRow.createCell(5).setCellValue(hod);
			aRow.getCell(5).setCellStyle(style1);
			
			aRow.createCell(6).setCellValue(pod);
			aRow.getCell(6).setCellStyle(style1);
			
			aRow.createCell(7).setCellValue(god);
			aRow.getCell(7).setCellStyle(style1);
			
			aRow.createCell(8).setCellValue(thd);
			aRow.getCell(8).setCellStyle(style1);
			
			aRow.createCell(9).setCellValue(tpd);
			aRow.getCell(9).setCellStyle(style1);
			
			aRow.createCell(10).setCellValue(tgd);
			aRow.getCell(10).setCellStyle(style1);
			
			aRow.createCell(11).setCellValue(alltotal);
			aRow.getCell(11).setCellStyle(style1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}
	
public ReportsDTO getEnterpriseSubscriberListReport(PageObject pageObject){
		List<EnterpriseSubscriberBO> enterpriseSubscriberList = new ArrayList<>();
		ReportsDTO reportDto = new ReportsDTO();
		
		try{
			enterpriseSubscriberList = demandNoteDaoImpl.getEnterpriseSubscriberList(pageObject);
			reportDto.setEntSubscriberList(enterpriseSubscriberList);
			reportDto.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return reportDto;
	}
	
	public HSSFWorkbook getEntSubscriberExcelFile(HttpServletRequest request, HttpServletResponse response) {
		List<EnterpriseSubscriberBO> entSubscriberList = new ArrayList<>();
		PageObject pageObject=null;
		entSubscriberList = demandNoteDaoImpl.getEnterpriseSubscriberList(pageObject);

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Enterprise Subscriber Report");
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

			 //For creating first row 
			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			// For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("Enterprise Subscriber Report");
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

			header.createCell(1).setCellValue("Customer Name");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("Parent Customer Name");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("LMO Name");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Total CAF's");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Pending For CAF Edit");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("Pending For CAF Payment");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("Pending For CAF Provision");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("CAF Active");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("CAF Suspend");
			header.getCell(9).setCellStyle(style);
			
			header.createCell(10).setCellValue("CAF Inactive");
			header.getCell(10).setCellStyle(style);
			
			// create data rows
			int rowCount = 5;
			int i = 0;

			for (EnterpriseSubscriberBO obj : entSubscriberList) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getCustname());
				aRow.createCell(2).setCellValue(obj.getParentcustname());
				aRow.createCell(3).setCellValue(obj.getTenantname());
				aRow.createCell(4).setCellValue(obj.getTotalcafs());
				aRow.createCell(5).setCellValue(obj.getPendingforcafeditcount());
				aRow.createCell(6).setCellValue(obj.getPendingforpaymentcount());
				aRow.createCell(7).setCellValue(obj.getPendingforprovisioncount());
				aRow.createCell(8).setCellValue(obj.getActivecount());
				aRow.createCell(9).setCellValue(obj.getSuspendcount());
				aRow.createCell(10).setCellValue(obj.getInactivecount());
			}

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Enterprise Subscriber Report.xls");
			workbook.write(out);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	public boolean getBalanceStatus(int size, String profileId, String tenantId) {
		boolean flag = false;
		Tenant t = demandNoteDaoImpl.findByTenantId(Integer.parseInt(tenantId));
		 String str = demandNoteDaoImpl.getBalanceStatus(size, profileId,t.getTenantCode());
		 str = str == null ? "" : str;
		 flag = str.equalsIgnoreCase("true") ? true : false ;
		 flag = t.getTenantTypeLov().equalsIgnoreCase("LMO") ? flag : true;
		 return flag;
	}

	public CpeModelBO getCpeModelInfoBySrlNo(String cpeSrlNo) {
		return demandNoteDaoImpl.getCpeModelInfoBySrlNo(cpeSrlNo);
	}
	
	public HSSFWorkbook ponReportExcelFile() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFWorkbook workbook1 = new HSSFWorkbook();
		HSSFWorkbook workbook2 = new HSSFWorkbook();
		//HSSFWorkbook workbook3 = new HSSFWorkbook();
		
		workbook1 = getPONWiseExcelFile("","" ,"");
		workbook2 = getPONWithNoCafExcel("","","");
		//workbook3 = getSubWiseExcelFile();
		
		List<HSSFWorkbook> list = new ArrayList<HSSFWorkbook>();
		list.add(workbook1);
		list.add(workbook2);
		//list.add(workbook3);
		
		try {
			MergeExcelUtil mergeExcelUtil = new MergeExcelUtil();
			workbook = mergeExcelUtil.mergeExcelFiles(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	public List<PONWiseBo> getAllotedPONWithCaf(String district, String mandal, String lmocode){
		List<PONWiseBo> ponWiseBO = new ArrayList<PONWiseBo>();
		
		try{
			ponWiseBO = demandNoteDaoImpl.getAllotedPONWithCaf(district, mandal, lmocode);

			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ponWiseBO;
	}
	
	
	public HSSFWorkbook getPONWiseExcelFile(String district, String mandal, String lmocode) {

		List<PONWiseBo> listPON = new ArrayList<>();
		PageObject pageObject = null;
		listPON = demandNoteDaoImpl.getAllotedPONWithCaf(district, mandal, lmocode);
		// listPON = reportsDTO.getPonWiseList();
		// if(listPON!=null ||listPON.size() !=0) {
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Allotted PON Wise CAF Count Report");

		// Create Row
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		Row header = sheet.createRow(3);

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

			// For creating first row
			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			// For creating second row
			cell = row1.createCell(2);
			cell.setCellValue("Allotted PON Wise Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("POP_NAME");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("POP OLT IP Address");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Port No");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("LMO Code");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Contact Person Name");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("Contact Person Mobile");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("CAF Count");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("District Name");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("Mandal Name");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("Created ON");
			header.getCell(10).setCellStyle(style);

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (PONWiseBo obj : listPON) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getPop_name());
				aRow.createCell(2).setCellValue(obj.getPop_olt_ipaddress());
				aRow.createCell(3).setCellValue(obj.getPortno());
				aRow.createCell(4).setCellValue(obj.getLmocode());
				aRow.createCell(5).setCellValue(obj.getRegoff_pocname());
				aRow.createCell(6).setCellValue(obj.getRegoff_pocmob1());
				aRow.createCell(7).setCellValue(obj.getCafno());
				aRow.createCell(8).setCellValue(obj.getDistrictname());
				aRow.createCell(9).setCellValue(obj.getMandalname());
				aRow.createCell(10).setCellValue(obj.getCreatedon());
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
	public HSSFWorkbook downloadPONWiseExcel(String district, String mandal, String lmocode,
			HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = this.getPONWiseExcelFile(district, mandal, lmocode);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=PON Wise Report.xls");
			workbook.write(out);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	public List<PONWithZeroCAFBO> getPONWithZeroCAFBO(String district, String mandal, String lmocode){
		List<PONWithZeroCAFBO> ponWithZeroCAFBO = new ArrayList<PONWithZeroCAFBO>();
		//ReportsDTO reportsDTO = new ReportsDTO();
		try{
			ponWithZeroCAFBO = demandNoteDaoImpl.getPONWithZeroCAFBO(district, mandal, lmocode);
			//reportsDTO.setPonWithZeroCafList(ponWithZeroCAFBO);
			//reportsDTO.setCount(pageObject.getTotalDisplayCount());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ponWithZeroCAFBO;
	}
	
	public HSSFWorkbook getPONWithNoCafExcel(String district, String mandal,String lmocode) {

		List<PONWithZeroCAFBO> listofPONWithZeroCaf = new ArrayList<>();
		PageObject pageObject=null;
		listofPONWithZeroCaf = demandNoteDaoImpl.getPONWithZeroCAFBO(district, mandal, lmocode);
		//listofPONWithZeroCaf = reportsDTO.getPonWithZeroCafList();
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Allotted PON With Zero Caf's Report");
		
		//Create Row
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		Row header = sheet.createRow(3);

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

			//For creating first row 
			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			//For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("Allotted PON With ZERO CAF's");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);
			
			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);
	
			header.createCell(1).setCellValue("POP_NAME");
			header.getCell(1).setCellStyle(style);
	
			header.createCell(2).setCellValue("LMO CODE");
			header.getCell(2).setCellStyle(style);
			
			header.createCell(3).setCellValue("PORT NO");
			header.getCell(3).setCellStyle(style);
	
			header.createCell(4).setCellValue("TENANT NAME");
			header.getCell(4).setCellStyle(style);
	
			header.createCell(5).setCellValue("DISTRICT NAME");
			header.getCell(5).setCellStyle(style);
	
			header.createCell(6).setCellValue("Mandal NAME");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("Village NAME");
			header.getCell(7).setCellStyle(style);
			
			header.createCell(8).setCellValue("Contact Person Mobile");
			header.getCell(8).setCellStyle(style);
	
			// create data rows
			int rowCount = 4;
			int i = 0;
	
			for (PONWithZeroCAFBO obj : listofPONWithZeroCaf) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getPop_name());
				aRow.createCell(2).setCellValue(obj.getLmocode());
				aRow.createCell(3).setCellValue(obj.getPortno());
				aRow.createCell(4).setCellValue(obj.getTenantname());
				aRow.createCell(5).setCellValue(obj.getDistrictname());
				aRow.createCell(6).setCellValue(obj.getMandalname());
				aRow.createCell(7).setCellValue(obj.getVillagename());
				aRow.createCell(8).setCellValue(obj.getRegoff_pocmob1());
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
	
	public HSSFWorkbook downloadPONWithNoCafExcel(String district, String mandal, String lmocode, HttpServletRequest request, HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = this.getPONWithNoCafExcel(district, mandal, lmocode);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=PON With Zero Caf.xls");
			workbook.write(out);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	/*public List<PONWiseBo> getSubstationCafReport() {
		// TODO Auto-generated method stub
		PageObject pageObject = null;
		return demandNoteDaoImpl.getAllotedPONWithCaf(pageObject);
	}*/
	
	public HashMap<Integer,List<SubstationWiseCafVO>> getSubstationWisePONWithCaf(String district, String mandal) {
		List<SubstationWiseCafBo> listBo = demandNoteDaoImpl.getSubstationWisePONWithCaf(district, mandal);
		List<String> substationWithPOPNames = new ArrayList<String>();
		String substationWithPOPName = null;
		List<SubstationWiseCafVO> list8Vo = new ArrayList<SubstationWiseCafVO>();
		List<SubstationWiseCafVO> list16Vo = new ArrayList<SubstationWiseCafVO>();
		long sum = 0;
		SubstationWiseCafVO substationWiseCafVO = new SubstationWiseCafVO();
		HashMap<Integer,List<SubstationWiseCafVO>> map = new HashMap<Integer,List<SubstationWiseCafVO>>();
		
		long cafcount8 = 0;
		long portcount8 = 0;
		long cafcount16 = 0;
		long portcount16 = 0;
		
		for (SubstationWiseCafBo obj : listBo) {
			
			if(obj!=null) {
			
			substationWithPOPName = String.join(",",obj.getPopName(),obj.getPop_olt_ipaddress(),obj.getPop_olt_serialno());
			
			if(substationWithPOPNames.contains(substationWithPOPName)) {
				
			}else {
				if (substationWithPOPNames.size() != 0) {
					
					substationWiseCafVO.setTotal(sum);

					if(substationWiseCafVO.getCardid() == 1) {
						
						cafcount8 = cafcount8 + sum;
					
						substationWiseCafVO.setCafCount(cafcount8);
						substationWiseCafVO.setPortCount(portcount8);
						
						list8Vo.add(substationWiseCafVO);
					}else {
						cafcount16 = cafcount16 + sum;
						
						substationWiseCafVO.setCafCount(cafcount16);
						substationWiseCafVO.setPortCount(portcount16);
						list16Vo.add(substationWiseCafVO);
					}
				}
				substationWithPOPNames.add(substationWithPOPName);
				substationWiseCafVO = new SubstationWiseCafVO();
				sum = 0;
				substationWiseCafVO.setPopNameWithIP(substationWithPOPName);
				substationWiseCafVO.setDistrictname(obj.getDistrictname());
				substationWiseCafVO.setMandalname(obj.getMandalname());
				substationWiseCafVO.setCardid(obj.getCardid());

			}
			sum = sum+obj.getCafCount();
			
			if(substationWiseCafVO.getCardid() == 1){
				portcount8++;
			}else{
				portcount16++;
			}
			
			}
			try {
				String methodName="cafCount"+obj.getPortNo();
				
				BeanUtils.setProperty(substationWiseCafVO,methodName,obj.getCafCount());
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				
				e.printStackTrace();
			}
		}
		
		substationWiseCafVO.setTotal(sum);
		
		if(substationWiseCafVO.getCardid() == 1) {
			
			cafcount8 = cafcount8 + sum;
			substationWiseCafVO.setCafCount(cafcount8);
			substationWiseCafVO.setPortCount(portcount8);
			
			list8Vo.add(substationWiseCafVO);
		}else {
			
			cafcount16 = cafcount16 + sum;
			substationWiseCafVO.setCafCount(cafcount16);
			substationWiseCafVO.setPortCount(portcount16);
			
			list16Vo.add(substationWiseCafVO);
		}
		
		map.put(8,list8Vo);
		map.put(16, list16Vo);
		return map;
	}

	
	public HSSFWorkbook getSubWiseExcelFile(int noofports, String district, String mandal) {

		/*List<SubstationWiseCafVO> list = new ArrayList<>();
		list = this.getSubstationWisePONWithCaf(noofports);*/
		
		HashMap<Integer,List<SubstationWiseCafVO>> map = this.getSubstationWisePONWithCaf(district, mandal);
		List<SubstationWiseCafVO> list = map.get(noofports);
		
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Substation and OLT Wise Report");
		
		//Create Row
		HSSFRow aRow = sheet.createRow(5);
		HSSFRow row = sheet.createRow(0);// Title
		HSSFRow row1 = sheet.createRow(1);// Report name
		HSSFRow row2 = sheet.createRow(2);// Dates
		HSSFRow row3 = sheet.createRow(3);
		Row header = sheet.createRow(4);

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

			//For creating first row 
			Cell cell = row.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			row.setHeightInPoints(20);
			cell.setCellStyle(style);

			//For creating second row 
			cell = row1.createCell(2);
			cell.setCellValue("Substation and OLT Wise Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);
			
			//For creating Creating Fourth Row
			cell = row3.createCell(5);
			cell.setCellValue("PORT NO");
			if(noofports == 8) {
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 12));
			} else {
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 20));
			}
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("SNo");
			header.getCell(0).setCellStyle(style);
			
			header.createCell(1).setCellValue("POP NAME & POP_OLT_ipaddress");
			header.getCell(1).setCellStyle(style);
			
			header.createCell(2).setCellValue("Card ID");
			header.getCell(2).setCellStyle(style);
			
			header.createCell(3).setCellValue("District Name");
			header.getCell(3).setCellStyle(style);
			
			header.createCell(4).setCellValue("Mandal Name");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("1");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("2");
			header.getCell(6).setCellStyle(style);

			header.createCell(7).setCellValue("3");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("4");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("5");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("6");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("7");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("8");
			header.getCell(12).setCellStyle(style);
			
			if(noofports == 8) {
				header.createCell(13).setCellValue("Total");
				header.getCell(13).setCellStyle(style);
			}else {
				header.createCell(13).setCellValue("9");
				header.getCell(13).setCellStyle(style);

				header.createCell(14).setCellValue("10");
				header.getCell(14).setCellStyle(style);

				header.createCell(15).setCellValue("11");
				header.getCell(15).setCellStyle(style);

				header.createCell(16).setCellValue("12");
				header.getCell(16).setCellStyle(style);

				header.createCell(17).setCellValue("13");
				header.getCell(17).setCellStyle(style);

				header.createCell(18).setCellValue("14");
				header.getCell(18).setCellStyle(style);

				header.createCell(19).setCellValue("15");
				header.getCell(19).setCellStyle(style);

				header.createCell(20).setCellValue("16");
				header.getCell(20).setCellStyle(style);
				
				header.createCell(21).setCellValue("Total");
				header.getCell(21).setCellStyle(style);
			}
			
			

			
			// create data rows
			int rowCount = 5;
			int i = 0;

			for (SubstationWiseCafVO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getPopNameWithIP());
				aRow.createCell(2).setCellValue(obj.getCardid());
				aRow.createCell(3).setCellValue(obj.getDistrictname());
				aRow.createCell(4).setCellValue(obj.getMandalname());
				aRow.createCell(5).setCellValue(obj.getCafCount1());
				aRow.createCell(6).setCellValue(obj.getCafCount2());
				aRow.createCell(7).setCellValue(obj.getCafCount3());
				aRow.createCell(8).setCellValue(obj.getCafCount4());
				aRow.createCell(9).setCellValue(obj.getCafCount5());
				aRow.createCell(10).setCellValue(obj.getCafCount6());
				aRow.createCell(11).setCellValue(obj.getCafCount7());
				aRow.createCell(12).setCellValue(obj.getCafCount8());
				if(noofports == 8) {
					aRow.createCell(13).setCellValue(obj.getTotal());
				}else {
					aRow.createCell(13).setCellValue(obj.getCafCount9());
					aRow.createCell(14).setCellValue(obj.getCafCount10());
					aRow.createCell(15).setCellValue(obj.getCafCount11());
					aRow.createCell(16).setCellValue(obj.getCafCount12());
					aRow.createCell(17).setCellValue(obj.getCafCount13());
					aRow.createCell(18).setCellValue(obj.getCafCount14());
					aRow.createCell(19).setCellValue(obj.getCafCount15());
					aRow.createCell(20).setCellValue(obj.getCafCount16());
					aRow.createCell(21).setCellValue(obj.getTotal());
				}
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
	
	public HSSFWorkbook downloadSubWiseExcelFile( String district, String mandal, HttpServletRequest request, HttpServletResponse response,int noofports) {
		HSSFWorkbook workbook = null;
		try (ServletOutputStream out = response.getOutputStream()) {
			workbook = this.getSubWiseExcelFile(noofports,district, mandal);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Substation and OLT Wise Report.xls");
			workbook.write(out);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	//LMO Wise Stock Count
	public List<LmoStockCountBO> getlmoWiseStockCount() {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getlmoWiseStockCount();
	}

	// district Manager
	public List<LmoStockCountBO> getmgrdistrictWiseStockCount(String tenantcode, String year, String mon, String lmocode) {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getdistrictManagerStockCount(tenantcode, year, mon, lmocode);
	}

	public HSSFWorkbook downloadgetmgrdistrictWiseStockCount(String tenantcode, String year, String mon, String lmocode) {
		// TODO Auto-generated method stub
		List<LmoStockCountBO> list = new ArrayList<>();
		list = this.getmgrdistrictWiseStockCount(tenantcode, year, mon, lmocode);
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("District Wise Caf Details From :" + year + " To :" + mon);
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow title = sheet.createRow(0);// Title
		HSSFRow report_name = sheet.createRow(1);// Report name
		HSSFRow duration = sheet.createRow(2);// Dates
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
			Cell cell = title.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			title.setHeightInPoints(20);
			cell.setCellStyle(style);

			/* For creating second row */
			cell = report_name.createCell(2);
			cell.setCellValue("District Wise Caf Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = duration.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = duration.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("LMO CODE");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("District");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Mandal");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Village");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Created Date");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("Total Cafs");
			header.getCell(6).setCellStyle(style);

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (LmoStockCountBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getLmoCode());
				aRow.createCell(2).setCellValue(obj.getDistrict());
				aRow.createCell(3).setCellValue(obj.getMandal());
				aRow.createCell(4).setCellValue(obj.getVillage());
				aRow.createCell(5).setCellValue(obj.getCreateddate());
				aRow.createCell(6).setCellValue(obj.getCafCount());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	// download LMO Wise Stock Count
	public HSSFWorkbook getLmoStockCountExcel() {
		List<LmoStockCountBO> list = new ArrayList<>();
		list = this.getlmoWiseStockCount();

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("LMO Wise Stock Details");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
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
			cell.setCellValue("LMO WISE CPE STOCK COUNT");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("LMO CODE");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("LMO Network Name");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Contact Person");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Mobile No");
			header.getCell(4).setCellStyle(style);
			
			header.createCell(5).setCellValue("District");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("Mandal");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("Village");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("CAF Count");
			header.getCell(8).setCellStyle(style);
			
			header.createCell(9).setCellValue("Stock Available Count");
			header.getCell(9).setCellStyle(style);


			// create data rows
			int rowCount = 4;
			int i = 0;

			for (LmoStockCountBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getLmoCode());
				aRow.createCell(2).setCellValue(obj.getNetworkName());
				aRow.createCell(3).setCellValue(obj.getContactName());
				aRow.createCell(4).setCellValue(obj.getMobileNo());
				aRow.createCell(5).setCellValue(obj.getDistrict());
				aRow.createCell(6).setCellValue(obj.getMandal());
				aRow.createCell(7).setCellValue(obj.getVillage());
				aRow.createCell(8).setCellValue(obj.getCafCount());
				aRow.createCell(9).setCellValue(obj.getStockCount());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}
	
	//getMsoWiseLmoDetails
	public List<MsoDetailsWithLmosBO> getMsoWiseLmoDetails() {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getMsoWiseLmoDetails();
	}
		
	public HSSFWorkbook getMsoWiseLmoExcel() {
		List<MsoDetailsWithLmosBO> list = new ArrayList<>();
		list = this.getMsoWiseLmoDetails();

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("MSO WISE LMO DETAILS");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
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
			cell.setCellValue("MSO WISE LMO DETAILS");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("MSO NETWORK NAME");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("MSO Code");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Name of MSO Contact Person");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("MSO Mobile No");
			header.getCell(4).setCellStyle(style);
			
			header.createCell(5).setCellValue("MSO District");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("MSO Mandal");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("MSO Village");
			header.getCell(7).setCellStyle(style);
			
			header.createCell(8).setCellValue("MSO On Board Date");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("LMO NETWORK NAME");
			header.getCell(9).setCellStyle(style);

			header.createCell(10).setCellValue("LMO CODE");
			header.getCell(10).setCellStyle(style);

			header.createCell(11).setCellValue("Name of LMO Contact Person");
			header.getCell(11).setCellStyle(style);

			header.createCell(12).setCellValue("LMO Mobile No");
			header.getCell(12).setCellStyle(style);
			
			header.createCell(13).setCellValue("LMO District");
			header.getCell(13).setCellStyle(style);

			header.createCell(14).setCellValue("LMO Mandal");
			header.getCell(14).setCellStyle(style);

			header.createCell(15).setCellValue("LMO Village");
			header.getCell(15).setCellStyle(style);
			
			header.createCell(16).setCellValue("LMO On Board Date");
			header.getCell(16).setCellStyle(style);

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (MsoDetailsWithLmosBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getMsoNetwName());
				aRow.createCell(2).setCellValue(obj.getMsoCode());
				aRow.createCell(3).setCellValue(obj.getMsoName());
				aRow.createCell(4).setCellValue(obj.getMsoMob());
				aRow.createCell(5).setCellValue(obj.getMsoDistrict());
				aRow.createCell(6).setCellValue(obj.getMsoMandal());
				aRow.createCell(7).setCellValue(obj.getMsoVillage());
				aRow.createCell(8).setCellValue(obj.getMso_createdon());
				aRow.createCell(9).setCellValue(obj.getLmoNetwName());
				aRow.createCell(10).setCellValue(obj.getLmoCode());
				aRow.createCell(11).setCellValue(obj.getLmoName());
				aRow.createCell(12).setCellValue(obj.getLmoMob());
				aRow.createCell(13).setCellValue(obj.getLmoDistrict());
				aRow.createCell(14).setCellValue(obj.getLmoMandal());
				aRow.createCell(15).setCellValue(obj.getLmoVillage());
				aRow.createCell(16).setCellValue(obj.getLmo_createdon());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}
		
	//Tenant CPE Stock Report
	public List<TenantStockReportBO> getTenantStockReport(String tenantCode,String status) {
		// TODO Auto-generated method stub
		String tenantType = tenantCode.substring(0, 3);
		List<TenantStockReportBO> list = new ArrayList<TenantStockReportBO>();
		if(tenantType.equals("LMO")) {
			list = demandNoteDaoImpl.getLMOStockReport(tenantCode,status);
		}else {
			list = demandNoteDaoImpl.getMSOStockReport(tenantCode,status);
		}
		return list;
	}
		
	//Tenant CPE Stock Download
	public HSSFWorkbook getTenantStockExcel(String tenantCode, String status) {
		List<TenantStockReportBO> list = new ArrayList<>();
		list = this.getTenantStockReport(tenantCode, status);

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Tenant CPE Stock ");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
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
			cell.setCellValue("Tenant WISE CPE STOCK ");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("CPE Serial No");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("CPE MAC Address");
			header.getCell(2).setCellStyle(style);
			
			header.createCell(3).setCellValue("CPE Profile");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Batch Date");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("CAF No");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("Tenant Code");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("District");
			header.getCell(7).setCellStyle(style);
			
			header.createCell(8).setCellValue("Mandal");
			header.getCell(8).setCellStyle(style);

			header.createCell(9).setCellValue("Village");
			header.getCell(9).setCellStyle(style);
			
			// create data rows
			int rowCount = 4;
			int i = 0;

			for (TenantStockReportBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getCpeSerialNo());
				aRow.createCell(2).setCellValue(obj.getCpeMacId());
				aRow.createCell(3).setCellValue(obj.getCpeProfile());
				aRow.createCell(4).setCellValue(obj.getBatchDate());
				aRow.createCell(5).setCellValue(obj.getCafNo());
				aRow.createCell(6).setCellValue(obj.getTenantCode());
				aRow.createCell(7).setCellValue(obj.getDistrict());
				aRow.createCell(8).setCellValue(obj.getMandal());
				aRow.createCell(9).setCellValue(obj.getVillage());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}
				
	//MsoWiseCpeStockDetails
	public List<MsoCafNotCpeStockBo> getMsoWiseCpeStockDetails() {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getMsoWiseCpeStock();
	}
	
	//download of mso cpe caf not stock
	public HSSFWorkbook getMsoCafNotCpeStockListExcel() {
		List<MsoCafNotCpeStockBo> list = new ArrayList<>();
		list = this.getMsoWiseCpeStockDetails();

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Mso Wise Cpe Stock Details");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
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
			cell.setCellValue("MSO WISE CPE STOCK COUNT DETAILS");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("MSO CODE");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("MSO Net Work");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("MSO Name");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("MSO Mobile No");
			header.getCell(4).setCellStyle(style);
			
			header.createCell(5).setCellValue("MSO District");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("MSO Mandal");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("MSO Village");
			header.getCell(7).setCellStyle(style);

			header.createCell(8).setCellValue("CAF NOT Stock Count");
			header.getCell(8).setCellStyle(style);

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (MsoCafNotCpeStockBo obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getMsoCode());
				aRow.createCell(2).setCellValue(obj.getMsoNetwName());
				aRow.createCell(3).setCellValue(obj.getMsoName());
				aRow.createCell(4).setCellValue(obj.getMsoMob());
				aRow.createCell(5).setCellValue(obj.getMsoDistrict());
				aRow.createCell(6).setCellValue(obj.getMsoMandal());
				aRow.createCell(7).setCellValue(obj.getMsoVillage());
				aRow.createCell(8).setCellValue(obj.getCafNotDoneCount());

			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}
	
	
	//LMOlist of login MSO
	public List<MsoDetailsWithLmosBO> getLmoDetailsOfLoggedinMso(String msoCode) {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getLmoDetailsOfLoggedinMso(msoCode);
	}
	
	//LMO's Caf details of login MSO
	public List<ComsHelperDTO> getLmoCafDetailsOfLoginMso(String lmoCode) {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getLmoCafDetailsOfLoginMso(lmoCode);
	}
	
	//MSO revenueShare Details
	public List<MsoRevenueShareBO> getRevenueShareDetailsOfLoginMso(String year, String month,String tenantCode) {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getRevenueShareDetailsOfLoginMso(year, month, tenantCode);
	}
	
	public String getTotalPreviousBalance(String year, String month,String tenantCode) {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getTotalPreviousBalance(year, month, tenantCode);
	}
	
	//LMO revenueShare caf wise Details
	public List<CafWiseRevenueOfLoginLmo> getRevenueDetailsCafwiseOfLoginLmo(String year, String month,String tenantCode) {
		
		List<CafWiseRevenueOfLoginLmoBo> lmoCafwiserevnue = new ArrayList<>();
		List<CafWiseRevenueOfLoginLmo> lmoCafwiserevnueWithSrvcTaxs = new ArrayList<>();
		double percentage=18.00/100.00;
		BigDecimal percen=new BigDecimal(percentage);
		BigDecimal apsflShare=null;
		BigDecimal msoShare=null;
		BigDecimal lmoShare=null;
		lmoCafwiserevnue=demandNoteDaoImpl.getRevenueDetailsCafwiseOfLoginLmo(year, month, tenantCode);
		//lmoCafwiserevnueWithSrvcTaxs.addAll(lmoCafwiserevnue);

		for ( CafWiseRevenueOfLoginLmoBo cafwisebo: lmoCafwiserevnue) {
			
			CafWiseRevenueOfLoginLmo cafwiseLMObo= new CafWiseRevenueOfLoginLmo();
			cafwiseLMObo.setCafNo(cafwisebo.getCafNo());
			cafwiseLMObo.setCafDate(cafwisebo.getCafDate());
			cafwiseLMObo.setContactmob(cafwisebo.getContactmob());
			cafwiseLMObo.setCustname(cafwisebo.getCustname());
			cafwiseLMObo.setLmocode(cafwisebo.getLmocode());
			cafwiseLMObo.setTotal(cafwisebo.getTotal());
			cafwiseLMObo.setPreviousBalance(cafwisebo.getPreviousBalance());
			cafwiseLMObo.setPaymentstatus(cafwisebo.getPaymentstatus());
			
			if(cafwisebo.getAPSFLShare() !=null)
			{
				apsflShare= new BigDecimal(cafwisebo.getAPSFLShare());
				apsflShare=	apsflShare.add(apsflShare.multiply(percen)).setScale(2, BigDecimal.ROUND_HALF_UP);
				cafwiseLMObo.setAPSFLShare(String.valueOf(apsflShare));
				
			}
			if(cafwisebo.getMSOShare() !=null)
			{
				msoShare= new BigDecimal(cafwisebo.getMSOShare());
				msoShare=	msoShare.add(msoShare.multiply(percen)).setScale(2, BigDecimal.ROUND_HALF_UP);
				cafwiseLMObo.setMSOShare(String.valueOf(msoShare));

			}
			if(cafwisebo.getLMOShare() !=null)
				
			{
				lmoShare= new BigDecimal(cafwisebo.getLMOShare());
				lmoShare=	lmoShare.add(lmoShare.multiply(percen)).setScale(2, BigDecimal.ROUND_HALF_UP);
				cafwiseLMObo.setLMOShare(String.valueOf(lmoShare));
			}
			lmoCafwiserevnueWithSrvcTaxs.add(cafwiseLMObo);
		}
	
		

		return lmoCafwiserevnueWithSrvcTaxs ;
		
		
	}
	
	//Revenue Share Download
	public HSSFWorkbook getTenantRevenueShareDetailsExcel(String year, String month,String tenantCode) {
		List<MsoRevenueShareBO> list = new ArrayList<>();
		list = this.getRevenueShareDetailsOfLoginMso(year, month, tenantCode);

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Tenant Revenue Share DETAILS");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
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
			cell.setCellValue("revenue Share DETAILS");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("LMO Code");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("MSO Share");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("LMO Share");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Total Bill");
			header.getCell(4).setCellStyle(style);
/*			
			header.createCell(5).setCellValue("MSO District");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("MSO Mandal");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("MSO Village");
			header.getCell(7).setCellStyle(style);*/
			// create data rows
			int rowCount = 4;
			int i = 0;

			for (MsoRevenueShareBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getLmoCode());
				aRow.createCell(2).setCellValue(obj.getMsoShare());
				aRow.createCell(3).setCellValue(obj.getLmoShare());
				aRow.createCell(4).setCellValue(obj.getTotalBill());
/*				aRow.createCell(5).setCellValue(obj.getMsoDistrict());
				aRow.createCell(6).setCellValue(obj.getMsoMandal());
				aRow.createCell(7).setCellValue(obj.getMsoVillage());*/
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}
	
	
		//Revenue Share Download
	public HSSFWorkbook getTenantCafWiseRevenueShareDetailsExcel(String year, String month,String tenantCode) {
		List<CafWiseRevenueOfLoginLmoBo> list = new ArrayList<>();
	//	list = this.getRevenueDetailsCafwiseOfLoginLmo(year, month, tenantCode);

		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Tenant Revenue Share DETAILS");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
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
			cell.setCellValue("CAF Wise loginLMO revenue Share DETAILS");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = row2.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = row2.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("AadharNo");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("CafNo");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("CustomerName");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("District");
			header.getCell(4).setCellStyle(style);			
			header.createCell(5).setCellValue("Mandal");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("Village");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("LMO Share");
			header.getCell(7).setCellStyle(style);
			

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (CafWiseRevenueOfLoginLmoBo obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				
				aRow.createCell(2).setCellValue(obj.getCafNo());
				
				//aRow.createCell(6).setCellValue(obj.getVillage());
				
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}

	public List<CustomerDTO> getCustomerDetails(String tenantcode, String from, String to) {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getCustomerDetails(tenantcode, from, to);
	}

	public HSSFWorkbook downloadgetmgrCustomerdetails(String tenantcode, String from, String to) {
		// TODO Auto-generated method stub
		List<CustomerDTO> list = demandNoteDaoImpl.getCustomerDetails(tenantcode, from, to);
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(" Customer Caf Details Form");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow title = sheet.createRow(0);// Title
		HSSFRow report_name = sheet.createRow(1);// Report name
		HSSFRow duration = sheet.createRow(2);// Dates
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
			Cell cell = title.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			title.setHeightInPoints(20);
			cell.setCellStyle(style);

			/* For creating second row */
			cell = report_name.createCell(2);
			cell.setCellValue("Customer Caf Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = duration.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = duration.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("Caf Number");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("Customer Name");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Aadhar Number");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Activation Date");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Mobile Number");
			header.getCell(5).setCellStyle(style);

			

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (CustomerDTO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getCustid());
				aRow.createCell(2).setCellValue(obj.getCustType());
				aRow.createCell(3).setCellValue(obj.getAadharno());
				aRow.createCell(4).setCellValue(obj.getDob());
				aRow.createCell(5).setCellValue(obj.getContactno());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;

	}
	
	//District Mandal Village Wise Caf Count Details
	public List<LmoStockCountBO> getDistrictMandalVillageWiseCafCount(String from, String to,String district,String mandal,String village) {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getDistrictMandalVillageWiseCafCount(from, to, district, mandal, village);
	}
	
	//District Mandal Village Wise Caf Count Till date Details
	public List<LmoStockCountBO> getDistrictMandalVillageWiseCafCountTilldate() {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.getDistrictMandalVillageWiseCafCountTilldate();
	}
	
	//District Mandal Village Wise Caf Count Details download.
	public HSSFWorkbook downloadgetDistrictMandalVillageWiseCafCount(String from, String to,String district,String mandal,String village) {
		// TODO Auto-generated method stub
		List<LmoStockCountBO> list = new ArrayList<>();
		list = this.getDistrictMandalVillageWiseCafCount(from, to, district, mandal,village);
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("District, mandal,village Wise Caf Details From :" + from + " To :" + to);
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow title = sheet.createRow(0);// Title
		HSSFRow report_name = sheet.createRow(1);// Report name
		HSSFRow duration = sheet.createRow(2);// Dates
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
			Cell cell = title.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			title.setHeightInPoints(20);
			cell.setCellStyle(style);

			/* For creating second row */
			cell = report_name.createCell(2);
			cell.setCellValue("District, mandal, village Wise Caf Count Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = duration.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = duration.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("District");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("Mandal");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Village");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Created Date");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Total Cafs");
			header.getCell(5).setCellStyle(style);

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (LmoStockCountBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getDistrict());
				aRow.createCell(2).setCellValue(obj.getMandal());
				aRow.createCell(3).setCellValue(obj.getVillage());
				aRow.createCell(4).setCellValue(obj.getCreateddate());
				aRow.createCell(5).setCellValue(obj.getCafCount());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;
	}
	
	//District Mandal Village Wise Caf Count till date Details download.
		public HSSFWorkbook downloadgetgetDistrictMandalVillageWiseCafCountTilldate() {
			// TODO Auto-generated method stub
			List<LmoStockCountBO> list = new ArrayList<>();
			list = this.getDistrictMandalVillageWiseCafCountTilldate();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("District, mandal,village Wise Caf Details till date");
			Row header = sheet.createRow(3);
			HSSFRow aRow = sheet.createRow(4);
			HSSFRow title = sheet.createRow(0);// Title
			HSSFRow report_name = sheet.createRow(1);// Report name
			HSSFRow duration = sheet.createRow(2);// Dates
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
				Cell cell = title.createCell(2);
				cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
				title.setHeightInPoints(20);
				cell.setCellStyle(style);

				/* For creating second row */
				cell = report_name.createCell(2);
				cell.setCellValue("District, mandal, village Wise Caf Count till date Report");
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
				cell.setCellStyle(style);

				/* For creating third row */
				cell = duration.createCell(0);
				cell.setCellValue("Generated On");
				cell.setCellStyle(style);

				cell = duration.createCell(1);
				cell.setCellValue(currDate);
				cell.setCellStyle(style);

				header.createCell(0).setCellValue("S.No");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Village");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("Mandal");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("District");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("Caf count");
				header.getCell(4).setCellStyle(style);


				// create data rows
				int rowCount = 4;
				int i = 0;

				for (LmoStockCountBO obj : list) {
					aRow = sheet.createRow(rowCount++);
					aRow.createCell(0).setCellValue(++i);
					aRow.createCell(1).setCellValue(obj.getVillage());
					aRow.createCell(2).setCellValue(obj.getMandal());
					aRow.createCell(3).setCellValue(obj.getDistrict());
					aRow.createCell(4).setCellValue(obj.getCafCount());
					
				}

				int rowValue = rowCount++;
				aRow = sheet.createRow(rowValue);

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sheet = null;
				header = null;
				aRow = null;
			}
			return workbook;
		}
	
	public List<CustomerDTO> districtMandalVillageCustomerCafReport(String cafDate, String district, String mandal, String village) {
		// TODO Auto-generated method stub
		return demandNoteDaoImpl.districtMandalVillageCustomerCafReport(cafDate, district, mandal, village);
	}

	
	//District Mandal Village Wise Customer Caf report Details download.
		public HSSFWorkbook downloadgetdistrictMandalVillageCustomerCafReport(String cafDate,String district,String mandal,String village) {
		
				List<CustomerDTO> list = demandNoteDaoImpl.districtMandalVillageCustomerCafReport( cafDate, district, mandal, village);
				DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet(" district, mandal, village wise Customer Caf Details ");
				Row header = sheet.createRow(3);
				HSSFRow aRow = sheet.createRow(4);
				HSSFRow title = sheet.createRow(0);// Title
				HSSFRow report_name = sheet.createRow(1);// Report name
				HSSFRow duration = sheet.createRow(2);// Dates
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
					Cell cell = title.createCell(2);
					cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
					sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
					title.setHeightInPoints(20);
					cell.setCellStyle(style);

					/* For creating second row */
					cell = report_name.createCell(2);
					cell.setCellValue("Customer Caf Report");
					sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
					cell.setCellStyle(style);

					/* For creating third row */
					cell = duration.createCell(0);
					cell.setCellValue("Generated On");
					cell.setCellStyle(style);

					cell = duration.createCell(1);
					cell.setCellValue(currDate);
					cell.setCellStyle(style);

					header.createCell(0).setCellValue("S.No");
					header.getCell(0).setCellStyle(style);

					header.createCell(1).setCellValue("Caf Number");
					header.getCell(1).setCellStyle(style);

					header.createCell(2).setCellValue("Customer Name");
					header.getCell(2).setCellStyle(style);

					header.createCell(3).setCellValue("Aadhar Number");
					header.getCell(3).setCellStyle(style);

					header.createCell(4).setCellValue("Activation Date");
					header.getCell(4).setCellStyle(style);

					header.createCell(5).setCellValue("Mobile Number");
					header.getCell(5).setCellStyle(style);

					

					// create data rows
					int rowCount = 4;
					int i = 0;

					for (CustomerDTO obj : list) {
						aRow = sheet.createRow(rowCount++);
						aRow.createCell(0).setCellValue(++i);
						aRow.createCell(1).setCellValue(obj.getCustid());
						aRow.createCell(2).setCellValue(obj.getCustType());
						aRow.createCell(3).setCellValue(obj.getAadharno());
						aRow.createCell(4).setCellValue(obj.getDob());
						aRow.createCell(5).setCellValue(obj.getContactno());
					}

					int rowValue = rowCount++;
					aRow = sheet.createRow(rowValue);

				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					sheet = null;
					header = null;
					aRow = null;
				}
				return workbook;

			}
public List<Offline_Payment> getOfflinePaymentChequeDetails() {
			return demandNoteDaoImpl.getOfflinePaymentChequeDetails();
		}
public int updateCheque_DDPayment(Offline_Payment lst) {
			return demandNoteDaoImpl.updateCheque_DDPayment(lst);
}


public Boolean insertBulkCheque_DDPayment(Offline_Payment1 lst) {
	return demandNoteDaoImpl.insertBulkCheque_DDPayment(lst);
}

// district wise pon Wise Caf Count
public List<DistrictPonWiseCafCountBO> getDistrictWisePonWiseCafCount(String tenantcode, String from, String to, String lmocode) {
	return demandNoteDaoImpl.getDistrictWisePonWiseCafCount(tenantcode, from, to, lmocode);
}


// district wise pon Wise Caf Count report Details download.
public HSSFWorkbook downloadgetDistrictWisePonWiseCafCountReport(String tenantcode, String from, String to, String lmocode) {

		List<DistrictPonWiseCafCountBO> list = demandNoteDaoImpl.getDistrictWisePonWiseCafCount(tenantcode, from, to, lmocode);
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(" District wise PON wise Caf count Report ");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow title = sheet.createRow(0);// Title
		HSSFRow report_name = sheet.createRow(1);// Report name
		HSSFRow duration = sheet.createRow(2);// Dates
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
			Cell cell = title.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			title.setHeightInPoints(20);
			cell.setCellStyle(style);

			/* For creating second row */
			cell = report_name.createCell(2);
			cell.setCellValue("District wise PON wise Caf count Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = duration.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = duration.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("District");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("Mandal");
			header.getCell(2).setCellStyle(style);
			
			header.createCell(3).setCellValue("Village");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("LMO Code");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("POP Name");
			header.getCell(5).setCellStyle(style);

			header.createCell(6).setCellValue("PortNo.");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("CAF Count");
			header.getCell(7).setCellStyle(style);

			

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (DistrictPonWiseCafCountBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getDistrict());
				aRow.createCell(2).setCellValue(obj.getMandal());
				aRow.createCell(3).setCellValue(obj.getVillagename());
				aRow.createCell(4).setCellValue(obj.getLmoCode());
				aRow.createCell(5).setCellValue(obj.getPopName());
				aRow.createCell(6).setCellValue(obj.getPortNo());
				aRow.createCell(7).setCellValue(obj.getCafCount());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;

	}

//cheque payment third screen
public List<LmoWalletUpdateByChequePaymentBO> getLmoWalletUpdateByChequePayment(String lmocode,String effectivefrom,String effectiveto,String cheque_ddno) {
	return demandNoteDaoImpl.getLmoWalletUpdateByChequePayment(lmocode,effectivefrom,effectiveto,cheque_ddno);
}

//LMO cheque payment third Screen  download.
public HSSFWorkbook downloadgetLmoWalletUpdateByChequePayment(String lmocode,String effectivefrom,String effectiveto,String cheque_ddno) {

		List<LmoWalletUpdateByChequePaymentBO> list = demandNoteDaoImpl.getLmoWalletUpdateByChequePayment(lmocode,effectivefrom,effectiveto,cheque_ddno);
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(" LMO Wallet Details after NEFT_Cheque_DD Payment  Report ");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow title = sheet.createRow(0);// Title
		HSSFRow report_name = sheet.createRow(1);// Report name
		HSSFRow duration = sheet.createRow(2);// Dates
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
			Cell cell = title.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			title.setHeightInPoints(20);
			cell.setCellStyle(style);

			/* For creating second row */
			cell = report_name.createCell(2);
			cell.setCellValue("LMO Wallet Details after NEFT_Cheque_DD Payment  Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = duration.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = duration.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("LMO Code");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("NEFT/Cheque/DD NO.");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("NEFT/Cheque/DD Amount");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("Bank Name");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("Payment Mode");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("LMO Wallet Amount");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("Status");
			header.getCell(7).setCellStyle(style);

			

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (LmoWalletUpdateByChequePaymentBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getLmoCode());
				aRow.createCell(2).setCellValue(obj.getCheque_DDno());
				aRow.createCell(3).setCellValue(obj.getCheque_Dd_Amount());
				aRow.createCell(4).setCellValue(obj.getBankName());
				aRow.createCell(5).setCellValue(obj.getPaymentMode());
				aRow.createCell(6).setCellValue(obj.getLmo_walletAmnt());
				aRow.createCell(7).setCellValue(obj.getStatus());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;

	}

public List<Offline_Payment> getChequeDetails(String cheque_ddno) {
	return demandNoteDaoImpl.getChequeDetails(cheque_ddno);
}

//OLT Master data Report
public List<OLTMasterDataBO> getOLTMasterData() {
	return demandNoteDaoImpl.getOLTMasterData();
}

//OLT Master Data details report download.
public HSSFWorkbook getOLTMasterDataDownload() {

		List<OLTMasterDataBO> list = demandNoteDaoImpl.getOLTMasterData();
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(" OLT Master Data  Report ");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow title = sheet.createRow(0);// Title
		HSSFRow report_name = sheet.createRow(1);// Report name
		HSSFRow duration = sheet.createRow(2);// Dates
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
			Cell cell = title.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			title.setHeightInPoints(20);
			cell.setCellStyle(style);

			/* For creating second row */
			cell = report_name.createCell(2);
			cell.setCellValue("OLT Master Data Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = duration.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = duration.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("POPSubstationUID");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("POP Name");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("POP OLT SrlNo");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("POP OLT IPAddress");
			header.getCell(4).setCellStyle(style);

			header.createCell(5).setCellValue("DistrictName");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("MandalName");
			header.getCell(6).setCellStyle(style);

			

			// create data rows
			int rowCount = 4;
			int i = 0;

			for (OLTMasterDataBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getPopSubstnUID());
				aRow.createCell(2).setCellValue(obj.getPopName());
				aRow.createCell(3).setCellValue(obj.getPopOltSerialNo());
				aRow.createCell(4).setCellValue(obj.getPopOltIPAddress());
				aRow.createCell(5).setCellValue(obj.getDistrictName());
				aRow.createCell(6).setCellValue(obj.getMandalName());
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;

	}


//Rigged Cafs Details
public List<RiggedCafBO> getRiggedCafsDetails(String district, String mandal, String village, String from, String to) {
	return demandNoteDaoImpl.getRiggedCafsDetails(district, mandal, village, from, to);
}

//Rigged caf details report download.
public HSSFWorkbook getRiggedCafsDetailsDownload(String district, String mandal, String village, String from, String to) {

		List<RiggedCafBO> list = demandNoteDaoImpl.getRiggedCafsDetails(district, mandal,village, from, to);
		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(" Rigged cafs  Report ");
		Row header = sheet.createRow(3);
		HSSFRow aRow = sheet.createRow(4);
		HSSFRow title = sheet.createRow(0);// Title
		HSSFRow report_name = sheet.createRow(1);// Report name
		HSSFRow duration = sheet.createRow(2);// Dates
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
			Cell cell = title.createCell(2);
			cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
			title.setHeightInPoints(20);
			cell.setCellStyle(style);

			/* For creating second row */
			cell = report_name.createCell(2);
			cell.setCellValue("Rigged CAFs Report");
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
			cell.setCellStyle(style);

			/* For creating third row */
			cell = duration.createCell(0);
			cell.setCellValue("Generated On");
			cell.setCellStyle(style);

			cell = duration.createCell(1);
			cell.setCellValue(currDate);
			cell.setCellStyle(style);

			header.createCell(0).setCellValue("S.No");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("LMO Code");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("LMO Network Name");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("LMO Name");
			header.getCell(3).setCellStyle(style);

			header.createCell(4).setCellValue("LMO Mobile Number");
			header.getCell(4).setCellStyle(style);
			
			header.createCell(5).setCellValue("District Name");
			header.getCell(5).setCellStyle(style);
			
			header.createCell(6).setCellValue("Mandal Name");
			header.getCell(6).setCellStyle(style);
			
			header.createCell(7).setCellValue("Village Name");
			header.getCell(7).setCellStyle(style);
			
			header.createCell(8).setCellValue("Total CAF count");
			header.getCell(8).setCellStyle(style);
			
			header.createCell(9).setCellValue("Not Connected CAF count");
			header.getCell(9).setCellStyle(style);
			
			// create data rows
			int rowCount = 4;
			int i = 0;

			for (RiggedCafBO obj : list) {
				aRow = sheet.createRow(rowCount++);
				aRow.createCell(0).setCellValue(++i);
				aRow.createCell(1).setCellValue(obj.getLmoCode());
				aRow.createCell(2).setCellValue(obj.getLmoNwName());
				aRow.createCell(3).setCellValue(obj.getLmoName());
				aRow.createCell(4).setCellValue(obj.getLmoMobile());
				aRow.createCell(5).setCellValue(obj.getDistrict());
				aRow.createCell(6).setCellValue(obj.getMandal());
				aRow.createCell(7).setCellValue(obj.getVillage());
				aRow.createCell(8).setCellValue(obj.getTotalCafCount());
				aRow.createCell(9).setCellValue(obj.getNotConnectCafCount());
				
			}

			int rowValue = rowCount++;
			aRow = sheet.createRow(rowValue);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sheet = null;
			header = null;
			aRow = null;
		}
		return workbook;

	}

//Created By SaiSumanth
public List<Object[]> tenantWalletTrans(String query) {
	

	
		List<Object[]> list = null;
		try {
			list = demandNoteDaoImpl.tenantWalletTrans(query);
		} catch (Exception e) {

		} finally {

		}
		return list;
	
}
/*public List<PONWiseBo> getPonReportForIncentives(String district, String mandal, String from, String to, String lmocode){
	List<PONWiseBo> ponWiseBO = new ArrayList<PONWiseBo>();
	
	try{
		ponWiseBO = demandNoteDaoImpl.getPonReportForIncentives(district, mandal, from, to, lmocode);

		
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	return ponWiseBO;
}


public HSSFWorkbook getPonReportForIncentivesExcelFile(String district, String mandal, String from, String to, String lmocode) {

	List<PONWiseBo> listPON = new ArrayList<>();
	PageObject pageObject = null;
	listPON = demandNoteDaoImpl.getPonReportForIncentives(district, mandal, from, to, lmocode);
	// listPON = reportsDTO.getPonWiseList();
	// if(listPON!=null ||listPON.size() !=0) {
	DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet sheet = workbook.createSheet("Allotted PON Wise CAF Count Report");

	// Create Row
	HSSFRow aRow = sheet.createRow(4);
	HSSFRow row = sheet.createRow(0);// Title
	HSSFRow row1 = sheet.createRow(1);// Report name
	HSSFRow row2 = sheet.createRow(2);// Dates
	Row header = sheet.createRow(3);

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

		// For creating first row
		Cell cell = row.createCell(2);
		cell.setCellValue("Andhra Pradesh State Fibernet Ltd");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
		row.setHeightInPoints(20);
		cell.setCellStyle(style);

		// For creating second row
		cell = row1.createCell(2);
		cell.setCellValue("Allotted PON Wise Report");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
		cell.setCellStyle(style);

		cell = row2.createCell(0);
		cell.setCellValue("Generated On");
		cell.setCellStyle(style);

		cell = row2.createCell(1);
		cell.setCellValue(currDate);
		cell.setCellStyle(style);

		header.createCell(0).setCellValue("S.No");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("POP_NAME");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("POP OLT IP Address");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("Port No");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("LMO Code");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("Contact Person Name");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("Contact Person Mobile");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("CAF Count");
		header.getCell(7).setCellStyle(style);

		header.createCell(8).setCellValue("District Name");
		header.getCell(8).setCellStyle(style);

		header.createCell(9).setCellValue("Mandal Name");
		header.getCell(9).setCellStyle(style);

		header.createCell(10).setCellValue("Created ON");
		header.getCell(10).setCellStyle(style);

		// create data rows
		int rowCount = 4;
		int i = 0;

		for (PONWiseBo obj : listPON) {
			aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(++i);
			aRow.createCell(1).setCellValue(obj.getPop_name());
			aRow.createCell(2).setCellValue(obj.getPop_olt_ipaddress());
			aRow.createCell(3).setCellValue(obj.getPortno());
			aRow.createCell(4).setCellValue(obj.getLmocode());
			aRow.createCell(5).setCellValue(obj.getRegoff_pocname());
			aRow.createCell(6).setCellValue(obj.getRegoff_pocmob1());
			aRow.createCell(7).setCellValue(obj.getCafno());
			aRow.createCell(8).setCellValue(obj.getDistrictname());
			aRow.createCell(9).setCellValue(obj.getMandalname());
			aRow.createCell(10).setCellValue(obj.getCreatedon());
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

public HSSFWorkbook downloadPONReportForIncentivesExcel(String district, String mandal, String from, String to, String lmocode,
		HttpServletRequest request, HttpServletResponse response) {
	HSSFWorkbook workbook = null;
	try (ServletOutputStream out = response.getOutputStream()) {
		workbook = this.getPonReportForIncentivesExcelFile(district, mandal, from, to, lmocode);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=PON Wise Report.xls");
		workbook.write(out);
	}catch (Exception e) {
		e.printStackTrace();
	}
	return workbook;
}
*/


	// new code vijaya
	
			/*public CafMaster getEamilOfCafWiseReport1(String val) {
				CafMaster listMso = new CafMaster();
				try {
					listMso = demandNoteDaoImpl.getEamilOfCafWiseReport1(val);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return listMso;
			}*/
	
}
