package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portal_areas")
public class PortalAreas implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    public PortalAreas() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "areaid")
	private Integer areaid;
	
	@Column(name = "enrollmentno")
	private String Enrollmentno;
	
	@Column(name = "areaname")
	private String areaname;
	
	@Column(name = "cabletypeid")
	private String cabletypeid;
	
	@Column(name = "runningcablelen")
	private Float runningcablelen;
	
	@Column(name = "stateid")
	private Integer stateid;
	
	@Column(name = "districtid")
	private Integer districtid;
	
	@Column(name = "mandalid")
	private Integer mandalid;
	
	@Column(name = "villageid")
	private Integer villageid;
	
	@Column(name = "subscription_cnt")
	private Long subscription_cnt;
	
	@Column(name = "conn_cnt")
	private Long conn_cnt;
	
	@Column(name = "digconn_cnt")
	private Long digconn_cnt;
	
	@Column(name = "anlconn_cnt")
	private Long anlconn_cnt;
	
	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	

	public String getEnrollmentno() {
		return Enrollmentno;
	}

	public void setEnrollmentno(String enrollmentno) {
		Enrollmentno = enrollmentno;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getCabletypeid() {
		return cabletypeid;
	}

	public void setCabletypeid(String cabletypeid) {
		this.cabletypeid = cabletypeid;
	}

	public Float getRunningcablelen() {
		return runningcablelen;
	}

	public void setRunningcablelen(Float runningcablelen) {
		this.runningcablelen = runningcablelen;
	}

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

	public Integer getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Integer districtid) {
		this.districtid = districtid;
	}

	public Integer getMandalid() {
		return mandalid;
	}

	public void setMandalid(Integer mandalid) {
		this.mandalid = mandalid;
	}

	public Integer getVillageid() {
		return villageid;
	}

	public void setVillageid(Integer villageid) {
		this.villageid = villageid;
	}

	public Long getSubscription_cnt() {
		return subscription_cnt;
	}

	public void setSubscription_cnt(Long subscription_cnt) {
		this.subscription_cnt = subscription_cnt;
	}

	public Long getConn_cnt() {
		return conn_cnt;
	}

	public void setConn_cnt(Long conn_cnt) {
		this.conn_cnt = conn_cnt;
	}

	public Long getDigconn_cnt() {
		return digconn_cnt;
	}

	public void setDigconn_cnt(Long digconn_cnt) {
		this.digconn_cnt = digconn_cnt;
	}

	public Long getAnlconn_cnt() {
		return anlconn_cnt;
	}

	public void setAnlconn_cnt(Long anlconn_cnt) {
		this.anlconn_cnt = anlconn_cnt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
