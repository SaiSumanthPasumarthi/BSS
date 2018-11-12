package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class ListOfPortalAssets implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private Long routemapid;
	
	private String cabletypeid;
	
	private String assettypeid;
	
	private String routename;
	
	private MultipartFile routemap;
	
	private MultipartFile capturedassets;
	
	private String senttranstime;
	
	private String imieno;
	
	private String versionno;
	
	
	public Long getRoutemapid() {
		return routemapid;
	}

	public void setRoutemapid(Long routemapid) {
		this.routemapid = routemapid;
	}
	

	public String getCabletypeid() {
		return cabletypeid;
	}

	public void setCabletypeid(String cabletypeid) {
		this.cabletypeid = cabletypeid;
	}

	public String getAssettypeid() {
		return assettypeid;
	}

	public void setAssettypeid(String assettypeid) {
		this.assettypeid = assettypeid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public MultipartFile getRoutemap() {
		return routemap;
	}

	public void setRoutemap(MultipartFile routemap) {
		this.routemap = routemap;
	}

	public MultipartFile getCapturedassets() {
		return capturedassets;
	}

	public void setCapturedassets(MultipartFile capturedassets) {
		this.capturedassets = capturedassets;
	}

	public String getSenttranstime() {
		return senttranstime;
	}

	public void setSenttranstime(String senttranstime) {
		this.senttranstime = senttranstime;
	}

	public String getImieno() {
		return imieno;
	}

	public void setImieno(String imieno) {
		this.imieno = imieno;
	}

	public String getVersionno() {
		return versionno;
	}

	public void setVersionno(String versionno) {
		this.versionno = versionno;
	}

	

}
