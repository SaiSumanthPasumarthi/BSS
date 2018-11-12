package com.arbiva.apsfl.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.arbiva.apsfl.tms.dto.RevenueSharingDTO;
import com.arbiva.apsfl.tms.model.MspChnlsStg;
import com.arbiva.apsfl.tms.model.RsMonthlyIn;

public class ReadXlsExcelFileForMspChannelUpload {

	public static List<MspChnlsStg> extractExcelFile(MultipartFile file, String extension) throws IOException {

		List<MspChnlsStg> mspChnlsStgList = new ArrayList<MspChnlsStg>();
		InputStream inputs = null;
		XSSFRow headerRow = null;
		XSSFWorkbook workbook;
		XSSFSheet sheet;

		HSSFRow xlsHeaderRow = null;
		HSSFWorkbook xlsWorkbook;
		HSSFSheet xlsSheet;
		try {
			inputs = file.getInputStream();
			if (extension.equalsIgnoreCase("xlsx")) {

				workbook = new XSSFWorkbook(inputs);
				sheet = workbook.getSheetAt(0);
				headerRow = sheet.getRow(0);
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					MspChnlsStg mspChnlsStg = new MspChnlsStg();
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							if (cell.getRowIndex() != 0) {
								if (headerRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("MspCode")) {
									mspChnlsStg.setMspcode(NumberToTextConverter.toText(cell.getNumericCellValue()));
									break;
								}
								if (headerRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("Channel Code")) {
									mspChnlsStg.setChnlcode(NumberToTextConverter.toText(cell.getNumericCellValue()));
									break;
								}
								if (headerRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("Channel Name")) {
									mspChnlsStg.setChnlname(String.valueOf((int) (cell.getNumericCellValue())));
									break;
								}
							}
							break;
						case Cell.CELL_TYPE_STRING:
							if (cell.getRowIndex() != 0) {
								if (headerRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("MspCode")) {
									mspChnlsStg.setMspcode(cell.getStringCellValue());
									break;
								}
								if (headerRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("Channel Code")) {
									mspChnlsStg.setChnlcode(cell.getStringCellValue());
									break;
								}
								if (headerRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("Channel Name")) {
									mspChnlsStg.setChnlname(cell.getStringCellValue());
									break;
								}
							}
							break;
						}
					}

					if (row.getRowNum() != 0)
						mspChnlsStgList.add(mspChnlsStg);
				}
			} else if (extension.equalsIgnoreCase("xls")) {
				xlsWorkbook = new HSSFWorkbook(inputs);
				xlsSheet = xlsWorkbook.getSheetAt(0);
				xlsHeaderRow = xlsSheet.getRow(0);

				// Iterate through each rows one by one
				Iterator<Row> rowIterator = xlsSheet.iterator();
				// int i = 0;
				while (rowIterator.hasNext()) {
					MspChnlsStg mspChnlsStg = new MspChnlsStg();
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							if (cell.getRowIndex() != 0) {
								if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("MspCode")) {
									mspChnlsStg.setMspcode(NumberToTextConverter.toText(cell.getNumericCellValue()));
									break;
								}
								if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("Channel Code")) {
									mspChnlsStg.setChnlcode(NumberToTextConverter.toText(cell.getNumericCellValue()));
									break;
								}
								if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("Channel Name")) {
									mspChnlsStg.setChnlname(String.valueOf((int) (cell.getNumericCellValue())));
									break;
								}
							}
							break;
						case Cell.CELL_TYPE_STRING:
							if (cell.getRowIndex() != 0) {
								if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("MspCode")) {
									mspChnlsStg.setMspcode(cell.getStringCellValue());
									break;
								}
								if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("Channel Code")) {
									mspChnlsStg.setChnlcode(cell.getStringCellValue());
									break;
								}
								if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue()
										.equalsIgnoreCase("Channel Name")) {
									mspChnlsStg.setChnlname(cell.getStringCellValue());
									break;
								}
							}
							break;
						}
					}

					if (row.getRowNum() != 0)
						mspChnlsStgList.add(mspChnlsStg);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook = null;
			sheet = null;
			inputs.close();
		}
		return mspChnlsStgList;
	}

	public static void channelUplaodErrorDownloadExcel(List<MspChnlsStg> errorList, String fileName, String extension,
			HttpServletResponse httpResponse) throws IOException {
		XSSFWorkbook workbook;
		XSSFSheet sheet;

		HSSFRow xlsHeaderRow = null;
		HSSFWorkbook xlsWorkbook;
		HSSFSheet xlsSheet;

		try (ServletOutputStream out = httpResponse.getOutputStream()) {
			if (extension.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("MSP CHANNEL REPORT DOWNLOAD ");
				// create header row
				Row header = sheet.createRow(0);

				CellStyle style = workbook.createCellStyle();
				sheet.setDefaultColumnWidth(30);
				Font font = workbook.createFont();
				font.setFontName("Arial");
				style.setFillForegroundColor(HSSFColor.WHITE.index);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setColor(HSSFColor.BLACK.index);
				style.setFont(font);

				header.createCell(0).setCellValue("MspCode");
				header.getCell(0).setCellStyle(style);

				header.createCell(1).setCellValue("Channel Code");
				header.getCell(1).setCellStyle(style);

				header.createCell(2).setCellValue("Channel Name");
				header.getCell(2).setCellStyle(style);

				header.createCell(3).setCellValue("Remarks");
				header.getCell(3).setCellStyle(style);

				header.createCell(4).setCellValue("Upload Date");
				header.getCell(4).setCellStyle(style);

				// create data rows
				int rowCount = 1;

				for (MspChnlsStg mspChnlsStg : errorList) {
					XSSFRow Row = sheet.createRow(rowCount++);
					Row.createCell(0).setCellValue(mspChnlsStg.getMspcode() == null ? "" : mspChnlsStg.getMspcode());
					Row.createCell(1).setCellValue(mspChnlsStg.getChnlcode() == null ? "" : mspChnlsStg.getChnlcode());
					Row.createCell(2).setCellValue(mspChnlsStg.getChnlname() == null ? "" : mspChnlsStg.getChnlname());
					Row.createCell(3).setCellValue(mspChnlsStg.getRemarks() == null ? "" : mspChnlsStg.getRemarks());
					Row.createCell(4).setCellValue(mspChnlsStg.getStatus() == null ? "" : mspChnlsStg.getStatus());
				}

				httpResponse.setContentType("application/vnd.ms-excel");
				httpResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
				workbook.write(out);
				System.out.println("Excel written successfully..");

			} else if (extension.equalsIgnoreCase("xls")) {

				xlsWorkbook = new HSSFWorkbook();
				xlsSheet = xlsWorkbook.createSheet("MSP CHANNEL REPORT DOWNLOAD ");
				xlsHeaderRow = xlsSheet.createRow(0);

				CellStyle style = xlsWorkbook.createCellStyle();
				xlsSheet.setDefaultColumnWidth(30);
				Font font = xlsWorkbook.createFont();
				font.setFontName("Arial");
				style.setFillForegroundColor(HSSFColor.WHITE.index);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setColor(HSSFColor.BLACK.index);
				style.setFont(font);

				xlsHeaderRow.createCell(0).setCellValue("MspCode");
				xlsHeaderRow.getCell(0).setCellStyle(style);

				xlsHeaderRow.createCell(1).setCellValue("Channel Code");
				xlsHeaderRow.getCell(1).setCellStyle(style);

				xlsHeaderRow.createCell(2).setCellValue("Channel Name");
				xlsHeaderRow.getCell(2).setCellStyle(style);

				xlsHeaderRow.createCell(3).setCellValue("Remarks");
				xlsHeaderRow.getCell(3).setCellStyle(style);

				xlsHeaderRow.createCell(4).setCellValue("Upload Date");
				xlsHeaderRow.getCell(4).setCellStyle(style);

				// create data rows
				int rowCount = 1;

				for (MspChnlsStg mspChnlsStg : errorList) {
					HSSFRow Row = xlsSheet.createRow(rowCount++);
					Row.createCell(0).setCellValue(mspChnlsStg.getMspcode() == null ? "" : mspChnlsStg.getMspcode());
					Row.createCell(1).setCellValue(mspChnlsStg.getChnlcode() == null ? "" : mspChnlsStg.getChnlcode());
					Row.createCell(2).setCellValue(mspChnlsStg.getChnlname() == null ? "" : mspChnlsStg.getChnlname());
					Row.createCell(3).setCellValue(mspChnlsStg.getRemarks() == null ? "" : mspChnlsStg.getRemarks());
					Row.createCell(4).setCellValue(mspChnlsStg.getStatus() == null ? "" : mspChnlsStg.getStatus());
				}

				httpResponse.setContentType("application/vnd.ms-excel");
				httpResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
				xlsWorkbook.write(out);
				System.out.println("Excel written successfully..");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void revSharingDownload(List<RevenueSharingDTO> revenueSharingDTOList,
			HttpServletResponse httpResponse) throws IOException {
		XSSFWorkbook workbook;
		XSSFSheet sheet;

		try (ServletOutputStream out = httpResponse.getOutputStream()) {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("REVENUE SHARING DOWNLOAD ");
			// create header row
			Row header = sheet.createRow(0);

			CellStyle style = workbook.createCellStyle();
			sheet.setDefaultColumnWidth(30);
			Font font = workbook.createFont();
			font.setFontName("Arial");
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.BLACK.index);
			style.setFont(font);

			header.createCell(0).setCellValue("TenantCode");
			header.getCell(0).setCellStyle(style);

			header.createCell(1).setCellValue("Share Amount");
			header.getCell(1).setCellStyle(style);

			header.createCell(2).setCellValue("FormOn");
			header.getCell(2).setCellStyle(style);

			header.createCell(3).setCellValue("Status");
			header.getCell(3).setCellStyle(style);

			// create data rows
			int rowCount = 1;

			for (RevenueSharingDTO revenueSharingDTO : revenueSharingDTOList) {
				XSSFRow Row = sheet.createRow(rowCount++);
				Row.createCell(0).setCellValue(
						revenueSharingDTO.getTenantcode() == null ? "" : revenueSharingDTO.getTenantcode());
				Row.createCell(1)
						.setCellValue(revenueSharingDTO.getShareamt() == null ? "" : revenueSharingDTO.getShareamt());
				Row.createCell(2)
						.setCellValue(revenueSharingDTO.getFormon() == null ? "" : revenueSharingDTO.getFormon());
				Row.createCell(4)
						.setCellValue(revenueSharingDTO.getStatus() == null ? "" : revenueSharingDTO.getStatus());
			}

			httpResponse.setContentType("application/vnd.ms-excel");
			httpResponse.setHeader("Content-Disposition", "attachment; filename = revSharingExcelDownload.xls ");
			workbook.write(out);
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
