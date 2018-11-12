package com.arbiva.apsfl.tms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "portal_routes")
public class PortalAssets  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    public PortalAssets() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "routemapid")
	private Long routemapid;
	
	@Column(name = "enrollmentno")
	private String Enrollmentno;
	
	@Column(name = "cabletypeid")
	private Integer cabletypeid;
	
	@Column(name = "assettypeid")
	private Integer assettypeid;
	
	@Column(name = "routename")
	private String routename;
	
	@Lob
    @Column(name="routemap")
    private byte[] routemap;

	@Lob
	@Column(name = "capturedassets")
	private byte[] capturedassets;
	
	@Column(name = "senttranstime")
	private Date senttranstime;
	
	@Column(name = "imieno")
	private String imieno;
	
	@Column(name = "versionno")
	private String versionno;

	public Long getRoutemapid() {
		return routemapid;
	}

	public void setRoutemapid(Long routemapid) {
		this.routemapid = routemapid;
	}

	public String getEnrollmentno() {
		return Enrollmentno;
	}

	public void setEnrollmentno(String enrollmentno) {
		Enrollmentno = enrollmentno;
	}

	public Integer getCabletypeid() {
		return cabletypeid;
	}

	public void setCabletypeid(Integer cabletypeid) {
		this.cabletypeid = cabletypeid;
	}

	public Integer getAssettypeid() {
		return assettypeid;
	}

	public void setAssettypeid(Integer assettypeid) {
		this.assettypeid = assettypeid;
	}

	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public byte[] getRoutemap() {
		return routemap;
	}

	public void setRoutemap(byte[] routemap) {
		this.routemap = routemap;
	}

	public byte[] getCapturedassets() {
		return capturedassets;
	}

	public void setCapturedassets(byte[] capturedassets) {
		this.capturedassets = capturedassets;
	}

	public Date getSenttranstime() {
		return senttranstime;
	}

	public void setSenttranstime(Date senttranstime) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
