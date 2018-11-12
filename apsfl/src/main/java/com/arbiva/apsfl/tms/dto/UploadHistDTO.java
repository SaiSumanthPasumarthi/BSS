package com.arbiva.apsfl.tms.dto;

public class UploadHistDTO {
	
	public enum UsersDataSearchParams{

		COLUMN_0("uploadid"),COLUMN_1("filename"),COLUMN_2("uploaddate"),COLUMN_3("noofrows"),
		COLUMN_4("successrecs");

		private String colName;

		private UsersDataSearchParams(String colName) {
			this.colName = colName;
		}

		public String getColName() {
			return colName;
		}

		public static String getColumns(String colName) {
			for (UsersDataSearchParams usersDataSearchParams : UsersDataSearchParams.values()) {
				if (usersDataSearchParams.toString().equals(colName)) {
					return usersDataSearchParams.getColName();
				}
			}
			return "";
		}
	}
	
	private String uploadid;
	
	private String uploadrecno;
	
	private String mspcode;
	
	private String chnlcode;
	
	private String chnlname;
	
	private String pkgname;
	
	private String srvccode;
	
	private String srvcname;
	
	private String featurecodes;
	
	private String totalRec;
	
	private String successRec;
	
	private String failureRec;
	
	private String fileName;
	
	private String uploaddate;
	
	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getTotalRec() {
		return totalRec;
	}

	public void setTotalRec(String totalRec) {
		this.totalRec = totalRec;
	}

	public String getSuccessRec() {
		return successRec;
	}

	public void setSuccessRec(String successRec) {
		this.successRec = successRec;
	}

	public String getFailureRec() {
		return failureRec;
	}

	public void setFailureRec(String failureRec) {
		this.failureRec = failureRec;
	}

	public String getUploadid() {
		return uploadid;
	}

	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}

	public String getMspcode() {
		return mspcode;
	}

	public void setMspcode(String mspcode) {
		this.mspcode = mspcode;
	}

	public String getChnlcode() {
		return chnlcode;
	}

	public void setChnlcode(String chnlcode) {
		this.chnlcode = chnlcode;
	}

	public String getChnlname() {
		return chnlname;
	}

	public void setChnlname(String chnlname) {
		this.chnlname = chnlname;
	}

	public String getPkgname() {
		return pkgname;
	}

	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}

	public String getSrvccode() {
		return srvccode;
	}

	public void setSrvccode(String srvccode) {
		this.srvccode = srvccode;
	}

	public String getSrvcname() {
		return srvcname;
	}

	public void setSrvcname(String srvcname) {
		this.srvcname = srvcname;
	}

	public String getFeaturecodes() {
		return featurecodes;
	}

	public void setFeaturecodes(String featurecodes) {
		this.featurecodes = featurecodes;
	}

	public String getUploadrecno() {
		return uploadrecno;
	}

	public void setUploadrecno(String uploadrecno) {
		this.uploadrecno = uploadrecno;
	}
	
}
