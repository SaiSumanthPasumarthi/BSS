package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.util.Map;

public class ListOfPortalAreas implements Serializable {
	private static final long serialVersionUID = 1L;
	private String areaid;
    
	private String areaname;
	
	private String areas_cabletypeid;
	
	private String runningcablelen;
	
	private String stateid;
	
	private String districtid;
	
	private String mandalid;
	
	private String villageid;
	
	private String subscription_cnt;
	
	private String conn_cnt;
	
	private String digconn_cnt;
	
	private String anlconn_cnt;
	
	private Map<String, String> districtList;
	
	private Map<String, String> mandalList;
	
	private Map<String, String> villageList;
	

	public Map<String, String> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(Map<String, String> districtList) {
		this.districtList = districtList;
	}

	public Map<String, String> getMandalList() {
		return mandalList;
	}

	public void setMandalList(Map<String, String> mandalList) {
		this.mandalList = mandalList;
	}

	public Map<String, String> getVillageList() {
		return villageList;
	}

	public void setVillageList(Map<String, String> villageList) {
		this.villageList = villageList;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAreas_cabletypeid() {
		return areas_cabletypeid;
	}

	public void setAreas_cabletypeid(String areas_cabletypeid) {
		this.areas_cabletypeid = areas_cabletypeid;
	}

	public String getRunningcablelen() {
		return runningcablelen;
	}

	public void setRunningcablelen(String runningcablelen) {
		this.runningcablelen = runningcablelen;
	}

	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getDistrictid() {
		return districtid;
	}

	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}

	public String getMandalid() {
		return mandalid;
	}

	public void setMandalid(String mandalid) {
		this.mandalid = mandalid;
	}

	public String getVillageid() {
		return villageid;
	}

	public void setVillageid(String villageid) {
		this.villageid = villageid;
	}

	public String getSubscription_cnt() {
		return subscription_cnt;
	}

	public void setSubscription_cnt(String subscription_cnt) {
		this.subscription_cnt = subscription_cnt;
	}

	public String getConn_cnt() {
		return conn_cnt;
	}

	public void setConn_cnt(String conn_cnt) {
		this.conn_cnt = conn_cnt;
	}

	public String getDigconn_cnt() {
		return digconn_cnt;
	}

	public void setDigconn_cnt(String digconn_cnt) {
		this.digconn_cnt = digconn_cnt;
	}

	public String getAnlconn_cnt() {
		return anlconn_cnt;
	}

	public void setAnlconn_cnt(String anlconn_cnt) {
		this.anlconn_cnt = anlconn_cnt;
	}

	

}
