package com.arbiva.apsfl.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("IpAddressValues")
public class IpAddressValues {
	
	@Value("${TMS-URL}")
	private String tmsURL;

	@Value("${TMS-USERNAME}")
	private String tmsUserName;

	@Value("${TMS-PWD}")
	private String tmsPwd;
	
	@Value("${COM-URL}")
	private String comURL;

	@Value("${COM-USERNAME}")
	private String comUserName;

	@Value("${COM-PWD}")
	private String comPwd;
	
	@Value("${UMS-URL}")
	private String umsURL;

	@Value("${UMS-USERNAME}")
	private String umsUserName;

	@Value("${UMS-PWD}")
	private String umsPwd;
	
	@Value("${CAT-URL}")
	private String catURL;

	@Value("${CAT-USERNAME}")
	private String catUserName;

	@Value("${CAT-PWD}")
	private String catPwd;
	
	@Value("${TT-URL}")
	private String ttURL;
	
	@Value("${TT-USERNAME}")
	private String ttUserName;

	@Value("${TT-PWD}")
	private String ttPwd;
	
	@Value("${EM-URL}")
	private String emURL;
	
	@Value("${EM-USERNAME}")
	private String emUserName;

	@Value("${EM-PWD}")
	private String emPwd;
	
	@Value("${download.excel.path}")
	private String excelPath;
	
	@Value("${download.apk.path}")
	private String apkPath;
	
	@Value("${CC-URL}")
	private String ccURL;
	
	@Value("${CC-USERNAME}")
	private String ccUserName;

	@Value("${CC-PWD}")
	private String ccPwd;
	
	@Value("${BE-URL}")
	private String beURL;
	
	@Value("${BE-USERNAME}")
	private String beUserName;

	@Value("${BE-PWD}")
	private String bePwd;
	
	@Value("${download.excelBulkCaf.path}")
	private String bulkExcelPath;
	
	@Value("${download.excelVPNService.path}")
	private String vpnExcelPath;
	
	@Value("${download.excelchnlUploadService.path}")
	private String chnlUploadExcelPath;
	
	@Value("${download.searchexcel.path}")
	private String searchExcelPath;
	
	@Value("${download.offlinepaymentexceltemplate.path}")
	private String offlinepaymentexceltemplate;
	
	@Autowired
	HttpServletRequest request;
	
	

	public String getBeURL() {
		return beURL+"/BillingEngine/invoice/";
	}

	public String getBeUserName() {
		return beUserName;
	}

	public String getBePwd() {
		return bePwd;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getVpnExcelPath() {
		return vpnExcelPath;
	}

	public String getBulkExcelPath() {
		return bulkExcelPath;
	}

	public String getApkPath() {
		return apkPath;
	}

	public String getExcelPath() {
		return excelPath;
	}

	public String getTmsURL() {
		return tmsURL+"/tms/";
	}

	public String getTmsUserName() {
		return tmsUserName;
	}

	public String getTmsPwd() {
		return tmsPwd;
	}

	public String getComURL() {
		return comURL+"/coms/";
	}

	public String getComUserName() {
		return comUserName;
	}

	public String getComPwd() {
		return comPwd;
	}

	public String getUmsURL() {
		return umsURL+"/ums/";
	}

	public String getUmsUserName() {
		return umsUserName;
	}

	public String getUmsPwd() {
		return umsPwd;
	}

	public String getCatURL() {
		return catURL+"/cat/";
	}

	public String getCatUserName() {
		return catUserName;
	}

	public String getCatPwd() {
		return catPwd;
	}

	public String getTtURL() {
		return ttURL+"/tt/";
	}

	public String getTtUserName() {
		return ttUserName;
	}

	public String getTtPwd() {
		return ttPwd;
	}
	
	public String getEmURL() {
		return emURL+"/em/";
	}

	public String getEmUserName() {
		return emUserName;
	}

	public String getEmPwd() {
		return emPwd;
	}

	public String getCcURL() {
		return ccURL+"/cc/";
	}

	public String getCcUserName() {
		return ccUserName;
	}

	public String getCcPwd() {
		return ccPwd;
	}

	public String getChnlUploadExcelPath() {
		return chnlUploadExcelPath;
	}

	public void setChnlUploadExcelPath(String chnlUploadExcelPath) {
		this.chnlUploadExcelPath = chnlUploadExcelPath;
	}
	
	public String getSearchExcelPath() {
		return searchExcelPath;
	}

	public String getOfflinepaymentexceltemplate() {
		return offlinepaymentexceltemplate;
	}

	public void setOfflinepaymentexceltemplate(String offlinepaymentexceltemplate) {
		this.offlinepaymentexceltemplate = offlinepaymentexceltemplate;
	}
	

}
