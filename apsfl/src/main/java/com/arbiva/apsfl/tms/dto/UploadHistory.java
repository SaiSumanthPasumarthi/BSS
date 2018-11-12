/**
 * 
 */
package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class UploadHistory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String uploadId;
	
	private String uploadType;
	
	private String fileName;
	
	private String fileSize;
	
	private String noofCols;
	
	private String noofRows;
	
	private String uploadDate;
	
	private String uploadedBy;
	
	private String successRecords;
	
	private String correctedRecords;
	
	private String status;

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
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

	public String getNoofCols() {
		return noofCols;
	}

	public void setNoofCols(String noofCols) {
		this.noofCols = noofCols;
	}

	public String getNoofRows() {
		return noofRows;
	}

	public void setNoofRows(String noofRows) {
		this.noofRows = noofRows;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getSuccessRecords() {
		return successRecords;
	}

	public void setSuccessRecords(String successRecords) {
		this.successRecords = successRecords;
	}

	public String getCorrectedRecords() {
		return correctedRecords;
	}

	public void setCorrectedRecords(String correctedRecords) {
		this.correctedRecords = correctedRecords;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
