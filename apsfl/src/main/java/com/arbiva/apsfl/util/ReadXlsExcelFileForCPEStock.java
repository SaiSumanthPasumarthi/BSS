package com.arbiva.apsfl.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.arbiva.apsfl.tms.dto.CpeStockVO;
import com.arbiva.apsfl.tms.model.CpeStock;


public class ReadXlsExcelFileForCPEStock {
	
	public static List<CpeStock> extractExcelFile(MultipartFile excelFile, Model model,HttpServletRequest request) {
		List<CpeStock> cpeStockList = new ArrayList<CpeStock>();
		int numHeadRows = 1;
		int totalRows = 0;
		InputStream is = null;
		XSSFWorkbook workBook = null;
		XSSFSheet worksheet1 = null;
		XSSFRow headerRow = null;
		String[] selectedPrefixes=request.getParameterValues("cpePrefixes");
		
		boolean isPrefixMatching=false;
		try {
			is = excelFile.getInputStream();
			 workBook = new XSSFWorkbook(is);
			 worksheet1 = workBook.getSheetAt(0);
			 headerRow =  worksheet1.getRow(0); 
			int cellNum = headerRow.getPhysicalNumberOfCells();
			for(int i=0 ; i<cellNum; i++){
				switch(i){
					case 0 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Cpe Serial No")))
							model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						else 
							continue;
						break;
					case 1 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Cpe Mac Address")))
							model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						else 
							continue;
						break;
					case 2 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Batch Id")))
							model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						else 
							continue;
						break;
					case 3 : 
						if( headerRow.getCell(i) == null || headerRow.getCell(i).getStringCellValue() == "" || (!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Batch Date")))
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
						
						CpeStock cpeStock = new CpeStock();
						
						Boolean isEmpty = false;
						for (int index = 0; index < cellNum; index++) {
							if( (String)model.asMap().get("errorMsg") != null ){
							break;
							
							}
							switch (index) {
							case 0:
								if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR){
									model.addAttribute("errorMsg", "This document has one or more fields blank or zero. Please check the upload document and try again.");
								break;
								}
									else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
									cpeStock.setCpeslno(row.getCell(index).getStringCellValue().trim());
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
									 int name = (int) row.getCell(index).getNumericCellValue();
									 cpeStock.setCpeslno(String.valueOf(name).trim());
								}
								else continue;
								break;
							case 1:
								if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR){
									//.addAttribute("errorMsg", "The  document has one or more fields blank or zero. Please check the upload document and try again.");
									model.addAttribute("errorMsg", "This document has one or more mac address fields empty");
									break;
								}
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
									cpeStock.setCpeMacAddr(row.getCell(index).getStringCellValue().trim());
								else if(row.getCell(index) != null && row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
									 double name = row.getCell(index).getNumericCellValue();
									 cpeStock.setCpeMacAddr(String.valueOf(name).trim());
								}
								else continue;
								break;
							case 2:
								if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR)
									 cpeStock.setBatchId(null);
								else if(row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
									cpeStock.setBatchId(row.getCell(index).getStringCellValue().trim());
								else if(row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
									 int name = (int)row.getCell(index).getNumericCellValue();
									 cpeStock.setBatchId(String.valueOf(name).trim());
								}
								else continue;
								break;
							case 3:
								
								if(row.getCell(index) == null || row.getCell(index).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(index).getCellType() == Cell.CELL_TYPE_ERROR)
									cpeStock.setBatchDate(null);
								else if(row.getCell(index).getCellType() == Cell.CELL_TYPE_STRING)
									cpeStock.setBatchDate(DateUtill.stringtoDate(row.getCell(index).getStringCellValue().trim()));
								else if(row.getCell(index).getCellType() == Cell.CELL_TYPE_NUMERIC) {
									 Date name = row.getCell(index).getDateCellValue();
									 cpeStock.setBatchDate(name);
								}
								else continue;
								break;
							
							
							default:
								break;
							}
						}
						
						if( (String)model.asMap().get("errorMsg") != null )
						{cpeStockList.clear();
						break;
						}
						
							if (!isStringValid(cpeStock.getCpeslno(),false)) {
								model.addAttribute("errorMsg", "Wrong cpe Serial number format. Special characters identified : " +cpeStock.getCpeslno());
								break;
							}
							else if (!isStringValid(cpeStock.getCpeMacAddr(),true)) {
								model.addAttribute("errorMsg", "Mac address in wrong format for serial num: " + cpeStock.getCpeslno());
								break;
							}else if (selectedPrefixes!=null)
									{
								String cpeSerialNumber=cpeStock.getCpeslno();
								for (String prefix :request.getParameterValues("cpePrefixes") )
									
							    {
							    		if (cpeSerialNumber.startsWith(prefix)){
							    		 isPrefixMatching=true;
							    		 break;
							    		}
							    		else isPrefixMatching=false;
							    }	
								
								if (isPrefixMatching){
									if( (String)model.asMap().get("errorMsg") == null )
									cpeStockList.add(cpeStock);
									else 
									{cpeStockList.clear();
									break;
									}
								}
									else {
										model.addAttribute("errorMsg", "This document has one or more serial numbers not matching with given prefixes. Please check the uploaded document and try again.");
								break;
									}
								
							}else
							{
								if( (String)model.asMap().get("errorMsg") == null )
									cpeStockList.add(cpeStock);
								else 
									{cpeStockList.clear();
									break;
									}
									
								
							}
							
							
							

					} catch (Exception exception) {
						model.addAttribute("errorMsg", exception.getMessage());
					}
				}
				
				for (int i = 0; i < cpeStockList.size(); i++) {
					  for (int j = i+1; j < cpeStockList.size(); j++) {
						  
						  if (!cpeStockList.get(i).getCpeMacAddr().equals(cpeStockList.get(j).getCpeMacAddr()))
					{
							  //dont do anything
						  }
						   else {
							  model.addAttribute("errorMsg", "This document has one or more Mac Address.Please correct and reupload: " + cpeStockList.get(i).getCpeMacAddr());  
							  cpeStockList.clear();
							  break;
						  }
						  
						  if (!cpeStockList.get(i).getCpeslno().equals(cpeStockList.get(j).getCpeslno())){
							  
							  //dont do anything
						  }
						   else {
							  model.addAttribute("errorMsg", "This document has one or more Serial numbers.Please correct and reupload: "+ cpeStockList.get(i).getCpeslno());  
							  cpeStockList.clear();
							  break;
						  }
						  
						  
						  
					  }
					}
			} 
		} catch (Exception exception) {
			return cpeStockList;
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

		return cpeStockList;

	}
	
	 public static List<CpeStockVO> extractSearchExcelFile(MultipartFile excelFile, Model model) {
			List<CpeStockVO> cpeStockList = new ArrayList<CpeStockVO>();
			int numHeadRows = 1;
			int totalRows = 0;
			InputStream is = null;
			XSSFWorkbook workBook = null;
			XSSFSheet worksheet1 = null;
			XSSFRow headerRow = null;
			try {
				is = excelFile.getInputStream();
				 workBook = new XSSFWorkbook(is);
				 worksheet1 = workBook.getSheetAt(0);
				 headerRow =  worksheet1.getRow(0); 
				int cellNum = headerRow.getPhysicalNumberOfCells();
				
					
							if( headerRow.getCell(0) == null || headerRow.getCell(0).getStringCellValue() == "" || (!headerRow.getCell(0).getStringCellValue().equalsIgnoreCase("Cpe Serial No")))
								model.addAttribute("errorMsg", "Please upload a excel document in predefined format.");
						
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
							
							CpeStockVO cpeStock = new CpeStockVO();
							
							Boolean isEmpty = false;
							
									if(row.getCell(0) == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(0).getCellType() == Cell.CELL_TYPE_ERROR){
										model.addAttribute("errorMsg", "This document has one or more fields blank or zero. Please check the upload document and try again.");
									break;
									}
									
									else{
									 if(row.getCell(0) != null  && (!row.getCell(0).equals(""))&& row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING)
										cpeStock.setCpeSrlno(row.getCell(0).getStringCellValue().trim());
									else if(row.getCell(0) != null   && row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
										 int name = (int) row.getCell(0).getNumericCellValue();
										 cpeStock.setCpeSrlno(String.valueOf(name).trim());
									}
									 if (!isStringValid(cpeStock.getCpeSrlno(),false)) {
											model.addAttribute("errorMsg", "Wrong cpe Serial number format. Special characters identified : " +cpeStock.getCpeSrlno());
											break;
										}
									 else cpeStockList.add(cpeStock);		
									}
								
							
						} catch (Exception exception) {
							model.addAttribute("errorMsg", exception.getMessage());
						}
					}
				} 
			} catch (Exception exception) {
				return cpeStockList;
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

			return cpeStockList;

		}

	 
	 public static Boolean isStringValid(String string, boolean isMacAddress){
		 
		 Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
	 
		 boolean b = false;
	
	 
		if (isMacAddress ){
			String [] splitString= string.split(":");
			if (string.contains(":") && splitString.length==6  ){
						
				 for (String s : string.split(":")) {
					 if (s.length()!=2){
						 return false;
					 }else{
						 Matcher m = p.matcher(s);
				         b = m.find(); 
					 }
			     }
			}else return false;
		}else{
			 Matcher m = p.matcher(string);
		     b = m.find();  
		}
	
		if (b)
			return false;
		else
			return true;
		 }

}
