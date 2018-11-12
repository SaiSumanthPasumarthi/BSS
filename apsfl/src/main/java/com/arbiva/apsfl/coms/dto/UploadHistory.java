/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Lakshman
 *
 */
public class UploadHistory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long uploadId;
	
	private String uploadType;
	
	private String fileName;
	
	private String fileSize;
	
	private Integer noofCols;
	
	private Long noofRows;
	
	private Calendar uploadDate;
	
	private String uploadedBy;
	
	private Long successRecords;
	
	private Long correctedRecords;
	
	private Integer status;

	public Long getUploadId() {
		return uploadId;
	}

	public void setUploadId(Long uploadId) {
		this.uploadId = uploadId;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getNoofCols() {
		return noofCols;
	}

	public void setNoofCols(Integer noofCols) {
		this.noofCols = noofCols;
	}

	public Long getNoofRows() {
		return noofRows;
	}

	public void setNoofRows(Long noofRows) {
		this.noofRows = noofRows;
	}

	public Calendar getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Calendar uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Long getSuccessRecords() {
		return successRecords;
	}

	public void setSuccessRecords(Long successRecords) {
		this.successRecords = successRecords;
	}

	public Long getCorrectedRecords() {
		return correctedRecords;
	}

	public void setCorrectedRecords(Long correctedRecords) {
		this.correctedRecords = correctedRecords;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
