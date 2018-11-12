package com.arbiva.apsfl.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;

public class MergeExcelUtil {
	
	private static final Logger logger = Logger.getLogger(FileUtil.class);
	
	public HSSFWorkbook mergeExcelFiles(List<HSSFWorkbook> list) throws IOException {
	    HSSFWorkbook book = new HSSFWorkbook();
	    HSSFSheet sheet = null;
	    int my_picture_id = 0;
	    
	    try (InputStream my_banner_image = this.getClass().getClassLoader().getResourceAsStream("/APSFL.png")) {
			byte[] bytes = IOUtils.toByteArray(my_banner_image);
			my_picture_id = book.addPicture(bytes, book.PICTURE_TYPE_PNG);
			my_banner_image.close();
	    }catch(Exception e) {
	    	logger.error("Exception in MergeExcelUtil :: " +e);
	    	e.printStackTrace();
	    }
			
	    
	    for (HSSFWorkbook fin : list) {
	    	HSSFWorkbook b = fin;
	      for (int i = 0; i < b.getNumberOfSheets(); i++) {
	    	sheet = book.createSheet(b.getSheetName(i));
	    	
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
			
	        copySheets(book, sheet, b.getSheetAt(i));
	      }
	    }
	    
	    return book;
	  }
	  
	  private static void copySheets(HSSFWorkbook newWorkbook, HSSFSheet newSheet, HSSFSheet sheet){     
	    copySheets(newWorkbook, newSheet, sheet, true);
	  }     

	  private static void copySheets(HSSFWorkbook newWorkbook, HSSFSheet newSheet, HSSFSheet sheet, boolean copyStyle){     
	    int newRownumber = newSheet.getLastRowNum() + 1;
	    int maxColumnNum = 0;     
	    Map<Integer, HSSFCellStyle> styleMap = (copyStyle) ? new HashMap<Integer, HSSFCellStyle>() : null;    
	    
	    for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {     
	      HSSFRow srcRow = sheet.getRow(i);     
	      HSSFRow destRow = newSheet.createRow(i + newRownumber);     
	      if (srcRow != null) {     
	        copyRow(newWorkbook, sheet, newSheet, srcRow, destRow, styleMap);     
	        if (srcRow.getLastCellNum() > maxColumnNum) {     
	            maxColumnNum = srcRow.getLastCellNum();     
	        }     
	      }     
	    }     
	    for (int i = 0; i <= maxColumnNum; i++) {     
	      newSheet.setColumnWidth(i, sheet.getColumnWidth(i));     
	    }     
	  }     
	  
	  public static void copyRow(HSSFWorkbook newWorkbook, HSSFSheet srcSheet, HSSFSheet destSheet, HSSFRow srcRow, HSSFRow destRow, Map<Integer, HSSFCellStyle> styleMap) {     
	    destRow.setHeight(srcRow.getHeight());
	    for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {     
	      HSSFCell oldCell = srcRow.getCell(j);
	      HSSFCell newCell = destRow.getCell(j);
	      if (oldCell != null) {     
	        if (newCell == null) {     
	          newCell = destRow.createCell(j);     
	        }     
	        copyCell(newWorkbook, oldCell, newCell, styleMap);
	      }     
	    }                
	  }
	  
	  public static void copyCell(HSSFWorkbook newWorkbook, HSSFCell oldCell, HSSFCell newCell, Map<Integer, HSSFCellStyle> styleMap) {      
	    if(styleMap != null) {     
	      int stHashCode = oldCell.getCellStyle().hashCode();     
	      HSSFCellStyle newCellStyle = styleMap.get(stHashCode);     
	      if(newCellStyle == null){     
	        newCellStyle = newWorkbook.createCellStyle();     
	        newCellStyle.cloneStyleFrom(oldCell.getCellStyle());     
	        styleMap.put(stHashCode, newCellStyle);     
	      }     
	      newCell.setCellStyle(newCellStyle);   
	    }     
	    switch(oldCell.getCellType()) {     
	      case HSSFCell.CELL_TYPE_STRING:     
	        newCell.setCellValue(oldCell.getRichStringCellValue());     
	        break;     
	      case HSSFCell.CELL_TYPE_NUMERIC:     
	        newCell.setCellValue(oldCell.getNumericCellValue());     
	        break;     
	      case HSSFCell.CELL_TYPE_BLANK:     
	        newCell.setCellType(HSSFCell.CELL_TYPE_BLANK);     
	        break;     
	      case HSSFCell.CELL_TYPE_BOOLEAN:     
	        newCell.setCellValue(oldCell.getBooleanCellValue());     
	        break;     
	      case HSSFCell.CELL_TYPE_ERROR:     
	        newCell.setCellErrorValue(oldCell.getErrorCellValue());     
	        break;     
	      case HSSFCell.CELL_TYPE_FORMULA:     
	        newCell.setCellFormula(oldCell.getCellFormula());     
	        break;     
	      default:     
	        break;     
	    }
	  }

}
