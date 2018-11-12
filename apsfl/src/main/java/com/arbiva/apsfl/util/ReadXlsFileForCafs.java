/**
 * 
 */
package com.arbiva.apsfl.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.arbiva.apsfl.coms.dto.Caf;
import com.arbiva.apsfl.coms.dto.CustomerCafVO;
import com.arbiva.apsfl.coms.dto.VPNSrvcVO;

/**
 * @author Lakshman
 *
 */
public class ReadXlsFileForCafs {

	public static List<Caf> BulkUploadExcelFile(MultipartFile file, String extension) throws IOException {
		
		List<Caf> cafLsit = new ArrayList<>();
		InputStream inputs = null;
		XSSFRow xlsxHeaderRow = null;
		XSSFWorkbook xlsxWorkbook;
		XSSFSheet xlsxSheet;
		
		HSSFRow xlsHeaderRow = null;
		HSSFWorkbook xlsWorkbook;
		HSSFSheet xlsSheet;
		
		try {
			inputs = file.getInputStream();
			if(extension.equalsIgnoreCase("xlsx")) {
				xlsxWorkbook = new XSSFWorkbook(inputs);
				xlsxSheet = xlsxWorkbook.getSheetAt(0);
				xlsxHeaderRow =  xlsxSheet.getRow(0);

				// Iterate through each rows one by one
				Iterator<Row> rowIterator = xlsxSheet.iterator();
				//int i = 0;
				while (rowIterator.hasNext()) {
					Caf caf = new Caf();
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:

								if (cell.getRowIndex() != 0) {
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("APSFL Code*")) {
										caf.setApsflUniqueId(NumberToTextConverter.toText(cell.getNumericCellValue()));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("District*")) {
										caf.setInstAddress1(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Mandal*")) {
										caf.setInstAddress1(caf.getInstAddress1()+","+String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("ONT Location*")) {
										caf.setCpePlace(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Organization Name and Address")) {
										caf.setCpePlace(String.valueOf((int) (cell.getNumericCellValue()))+"^"+caf.getCpePlace());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Mobile No*")) {
										caf.setContactmobileNo(NumberToTextConverter.toText(cell.getNumericCellValue()));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Name*")) {
										caf.setContactPerson(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Designation")) {
										caf.setContactPerson(caf.getContactPerson()+"("+String.valueOf((int) (cell.getNumericCellValue()))+")");
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Email")) {
										caf.setContactEmail(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Payment Responsible*")) {
										caf.setPaymentResponsible(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("SI LMO Code*")) {
										caf.setLmoCode(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Longitude")) {
										caf.setLongitude((float)cell.getNumericCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Latitude")) {
										caf.setLattitude((float)cell.getNumericCellValue());
										break;
									}
								}
								break;
							case Cell.CELL_TYPE_STRING:
								if (cell.getRowIndex() != 0) {
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("APSFL Code*")) {
										caf.setApsflUniqueId(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("District*")) {
										caf.setInstAddress1(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Mandal*")) {
										caf.setInstAddress1(caf.getInstAddress1()+","+cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("ONT Location*")) {
										caf.setCpePlace(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Organization Name and Address")) {
										caf.setCpePlace(cell.getStringCellValue()+"^"+caf.getCpePlace());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Mobile No*")) {
										caf.setContactmobileNo(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Name*")) {
										caf.setContactPerson(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Designation")) {
										caf.setContactPerson(caf.getContactPerson()+"("+cell.getStringCellValue()+")");
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Email")) {
										caf.setContactEmail(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Payment Responsible*")) {
										caf.setPaymentResponsible(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("SI LMO Code*")) {
										caf.setLmoCode(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Longitude")) {
										caf.setLongitude(Float.parseFloat(cell.getStringCellValue()));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Latitude")) {
										caf.setLattitude(Float.parseFloat(cell.getStringCellValue()));
										break;
									}
								}
								break;
							}
					}
					// Adding columns to CafList
					if (row.getRowNum() != 0)
						cafLsit.add(caf);
				}
			} else if(extension.equalsIgnoreCase("xls")) {
				xlsWorkbook = new HSSFWorkbook(inputs);
				xlsSheet = xlsWorkbook.getSheetAt(0);
				xlsHeaderRow =  xlsSheet.getRow(0);

				// Iterate through each rows one by one
				Iterator<Row> rowIterator = xlsSheet.iterator();
				//int i = 0;
				while (rowIterator.hasNext()) {
					Caf caf = new Caf();
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:

								if (cell.getRowIndex() != 0) {
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("APSFL Code*")) {
										caf.setApsflUniqueId(NumberToTextConverter.toText(cell.getNumericCellValue()));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("District*")) {
										caf.setInstAddress1(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Mandal*")) {
										caf.setInstAddress1(caf.getInstAddress1()+","+String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("ONT Location*")) {
										caf.setCpePlace(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Organization Name and Address")) {
										caf.setCpePlace(String.valueOf((int) (cell.getNumericCellValue()))+","+caf.getCpePlace());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Mobile No*")) {
										caf.setContactmobileNo(NumberToTextConverter.toText(cell.getNumericCellValue()));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Name*")) {
										caf.setContactPerson(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Designation")) {
										caf.setContactPerson(caf.getContactPerson()+"("+String.valueOf((int) (cell.getNumericCellValue()))+")");
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Email")) {
										caf.setContactEmail(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Payment Responsible*")) {
										caf.setPaymentResponsible(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("SI LMO Code*")) {
										caf.setLmoCode(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Longitude")) {
										caf.setLongitude((float)cell.getNumericCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Latitude")) {
										caf.setLattitude((float)cell.getNumericCellValue());
										break;
									}
								}
								break;
							case Cell.CELL_TYPE_STRING:
								if (cell.getRowIndex() != 0) {
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("APSFL Code*")) {
										caf.setApsflUniqueId(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("District*")) {
										caf.setInstAddress1(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Mandal*")) {
										caf.setInstAddress1(caf.getInstAddress1()+","+cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("ONT Location*")) {
										caf.setCpePlace(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Organization Name and Address")) {
										caf.setCpePlace(cell.getStringCellValue()+","+caf.getCpePlace());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Mobile No*")) {
										caf.setContactmobileNo(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Name*")) {
										caf.setContactPerson(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Contact Person Designation")) {
										caf.setContactPerson(caf.getContactPerson()+"("+cell.getStringCellValue()+")");
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Email")) {
										caf.setContactEmail(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Payment Responsible*")) {
										caf.setPaymentResponsible(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("SI LMO Code*")) {
										caf.setLmoCode(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Longitude")) {
										caf.setLongitude(Float.parseFloat(cell.getStringCellValue()));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Latitude")) {
										caf.setLattitude(Float.parseFloat(cell.getStringCellValue()));
										break;
									}
								}
								break;
							}
					}
					// Adding columns to CafList
					if (row.getRowNum() != 0)
						cafLsit.add(caf);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			xlsxWorkbook = null;
			xlsxSheet = null;
			xlsWorkbook = null;
			xlsSheet = null;
			xlsHeaderRow = null;
			xlsxHeaderRow = null;
			inputs.close();
		}
		return cafLsit;
	}
	
	public static List<Caf> BulkUploadCafList(CustomerCafVO customerCafVO) throws IOException {
		List<Caf> cafLsit = new ArrayList<>();
		Caf caf = new Caf();
		try {
			caf.setInstAddress1(customerCafVO.getDistrict().split(",")[1]+","+customerCafVO.getMandal().split(",")[1]);
			caf.setInstDistrict(customerCafVO.getDistrict().split(",")[0]);
			caf.setInstMandal(customerCafVO.getMandal().split(",")[0]);
			caf.setCpePlace(customerCafVO.getFirstName()+","+customerCafVO.getLocation());
			caf.setContactmobileNo(customerCafVO.getMobileNo());
			caf.setContactPerson(customerCafVO.getPocName()+"("+customerCafVO.getPocDesignation()+")");
			caf.setContactEmail(customerCafVO.getEmailId());
			caf.setPaymentResponsible("No");
			caf.setApsflUniqueId(customerCafVO.getApsflUniqueId());
			caf.setLmoCode(customerCafVO.getLmoCode());
			if(customerCafVO.getLongitude() != null) {
				if(!customerCafVO.getLongitude().isEmpty()) {
					caf.setLongitude(Float.parseFloat(customerCafVO.getLongitude()));
				}
			}
			if(customerCafVO.getLatitude() != null) {
				if(!customerCafVO.getLatitude().isEmpty()) {
					caf.setLattitude(Float.parseFloat(customerCafVO.getLatitude()));
				}
			}
			cafLsit.add(caf);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			caf = null;
		}
		return cafLsit;
	}

	@SuppressWarnings("rawtypes")
	public static Map getNumberOfColsAndRows(MultipartFile file, String extension) {
		InputStream inputs = null;
		XSSFWorkbook xlsxWorkbook;
		XSSFSheet xlsxSheet;
		
		HSSFWorkbook xlsWorkbook;
		HSSFSheet xlsSheet;
		
		String noOfCols = "";
		int noOfRows = 0;
		Map<String, String> recordDetails = new HashMap<>();
		try {
			inputs = file.getInputStream();
			if(extension.equalsIgnoreCase("xlsx")) {
				xlsxWorkbook = new XSSFWorkbook(inputs);
				xlsxSheet = xlsxWorkbook.getSheetAt(0);
				noOfCols = String.valueOf(xlsxSheet.getRow(0).getPhysicalNumberOfCells());
				Iterator<Row> rowIterator = xlsxSheet.iterator();
				while (rowIterator.hasNext()) {
					rowIterator.next();
					noOfRows++;
				}
			} else if(extension.equalsIgnoreCase("xls")) {
				xlsWorkbook = new HSSFWorkbook(inputs);
				xlsSheet = xlsWorkbook.getSheetAt(0);
				noOfCols = String.valueOf(xlsSheet.getRow(0).getPhysicalNumberOfCells());
				Iterator<Row> rowIterator = xlsSheet.iterator();
				while (rowIterator.hasNext()) {
					rowIterator.next();
					noOfRows++;
				}
			}
			recordDetails.put("noOfCols", noOfCols);
			recordDetails.put("noOfRows", String.valueOf(noOfRows - 1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputs = null;
			xlsxWorkbook = null;
			xlsxSheet = null;
			xlsWorkbook = null;
			xlsSheet = null;
		}
		return recordDetails;
	}
	
public static List<VPNSrvcVO> getVPNServiceList(MultipartFile file, String extension) throws IOException {
		
		List<VPNSrvcVO> vpnSrvcLsit = new ArrayList<>();
		InputStream inputs = null;
		XSSFRow xlsxHeaderRow = null;
		XSSFWorkbook xlsxWorkbook;
		XSSFSheet xlsxSheet;
		
		HSSFRow xlsHeaderRow = null;
		HSSFWorkbook xlsWorkbook;
		HSSFSheet xlsSheet;
		
		try {
			inputs = file.getInputStream();
			if(extension.equalsIgnoreCase("xlsx")) {
				xlsxWorkbook = new XSSFWorkbook(inputs);
				xlsxSheet = xlsxWorkbook.getSheetAt(0);
				xlsxHeaderRow =  xlsxSheet.getRow(0);
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = xlsxSheet.iterator();
				while (rowIterator.hasNext()) {
					VPNSrvcVO vpnSrvcVO = new VPNSrvcVO();
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (cell.getRowIndex() != 0) {
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("subStationUid*")) {
										vpnSrvcVO.setSubstnUid(NumberToTextConverter.toText(cell.getNumericCellValue()));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("oltSerialNo*")) {
										vpnSrvcVO.setOltSerialNo(NumberToTextConverter.toText(cell.getNumericCellValue()));
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("VPNServiceName*")) {
										vpnSrvcVO.setVpnSrvcName(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
								}
								break;
							case Cell.CELL_TYPE_STRING:
								if (cell.getRowIndex() != 0) {
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("subStationUid*")) {
										vpnSrvcVO.setSubstnUid(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("oltSerialNo*")) {
										vpnSrvcVO.setOltSerialNo(cell.getStringCellValue());
										break;
									}
									if (xlsxHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("VPNServiceName*")) {
										vpnSrvcVO.setVpnSrvcName(cell.getStringCellValue());
										break;
									}
								}
								break;
							}
					}
					// Adding columns to CafList
					if (row.getRowNum() != 0)
						vpnSrvcLsit.add(vpnSrvcVO);
				}
			} else if(extension.equalsIgnoreCase("xls")) {
				xlsWorkbook = new HSSFWorkbook(inputs);
				xlsSheet = xlsWorkbook.getSheetAt(0);
				xlsHeaderRow =  xlsSheet.getRow(0);
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = xlsSheet.iterator();
				while (rowIterator.hasNext()) {
					VPNSrvcVO vpnSrvcVO = new VPNSrvcVO();
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (cell.getRowIndex() != 0) {
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("subStationUid*")) {
										vpnSrvcVO.setSubstnUid(NumberToTextConverter.toText(cell.getNumericCellValue()));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("oltSerialNo*")) {
										vpnSrvcVO.setOltSerialNo(NumberToTextConverter.toText(cell.getNumericCellValue()));
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("VPNServiceName*")) {
										vpnSrvcVO.setVpnSrvcName(String.valueOf((int) (cell.getNumericCellValue())));
										break;
									}
								}
								break;
							case Cell.CELL_TYPE_STRING:
								if (cell.getRowIndex() != 0) {
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("subStationUid*")) {
										vpnSrvcVO.setSubstnUid(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("oltSerialNo*")) {
										vpnSrvcVO.setOltSerialNo(cell.getStringCellValue());
										break;
									}
									if (xlsHeaderRow.getCell(cell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("VPNServiceName*")) {
										vpnSrvcVO.setVpnSrvcName(cell.getStringCellValue());
										break;
									}
								}
								break;
							}
					}
					// Adding columns to CafList
					if (row.getRowNum() != 0)
						vpnSrvcLsit.add(vpnSrvcVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			xlsxWorkbook = null;
			xlsxSheet = null;
			xlsWorkbook = null;
			xlsSheet = null;
			xlsHeaderRow = null;
			xlsxHeaderRow = null;
			inputs.close();
		}
		return vpnSrvcLsit;
	}
}
