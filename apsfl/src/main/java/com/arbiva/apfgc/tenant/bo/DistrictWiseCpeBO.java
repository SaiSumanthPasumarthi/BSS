package com.arbiva.apfgc.tenant.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DistrictWiseCpeBO {
	
	
	@Id
	@Column(name="districtname")
	private String districtname;
	
	@Column(name="d2qty0")
	private Long  d2qty0;
	
	@Column(name="d2qty36")
	private Long d2qty36;
	
	@Column(name="d2qty48")
	private Long d2qty48;
	
	@Column(name="d1qty0")
	private Long d1qty0;
	
	@Column(name="d1qty36")
	private Long d1qty36;
	
	@Column(name="d1qty48")
	private Long d1qty48;
	
	@Transient
	private Long d2DisTotal;
	
	@Transient
	private Long d1DisTotal;
	
	@Transient
	private Long dis4000Total;
	
	@Transient
	private Long dis1700Total;
	
	@Transient
	private Long dis500Total;
	
	@Transient
	private Long disTotal;
	

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public Long getD2qty0() {
		return d2qty0;
	}

	public void setD2qty0(Long d2qty0) {
		this.d2qty0 = d2qty0;
	}

	public Long getD2qty36() {
		return d2qty36;
	}

	public void setD2qty36(Long d2qty36) {
		this.d2qty36 = d2qty36;
	}

	public Long getD2qty48() {
		return d2qty48;
	}

	public void setD2qty48(Long d2qty48) {
		this.d2qty48 = d2qty48;
	}

	public Long getD1qty0() {
		return d1qty0;
	}

	public void setD1qty0(Long d1qty0) {
		this.d1qty0 = d1qty0;
	}

	public Long getD1qty36() {
		return d1qty36;
	}

	public void setD1qty36(Long d1qty36) {
		this.d1qty36 = d1qty36;
	}

	public Long getD1qty48() {
		return d1qty48;
	}

	public void setD1qty48(Long d1qty48) {
		this.d1qty48 = d1qty48;
	}

	public Long getD2DisTotal() {
		return d2qty0 + d2qty36 + d2qty48;
	}

	public void setD2DisTotal(Long d2DisTotal) {
		this.d2DisTotal = d2DisTotal;
	}

	public Long getD1DisTotal() {
		return d1qty0 + d1qty36 + d1qty48;
	}

	public void setD1DisTotal(Long d1DisTotal) {
		this.d1DisTotal = d1DisTotal;
	}

	public Long getDis4000Total() {
		return d1qty0+ d2qty0 ;
	}

	public void setDis4000Total(Long dis4000Total) {
		this.dis4000Total = dis4000Total;
	}

	public Long getDis1700Total() {
		return d1qty36+ d2qty36 ;
	}

	public void setDis1700Total(Long dis1700Total) {
		this.dis1700Total = dis1700Total;
	}

	public Long getDis500Total() {
		return d1qty48+ d2qty48 ;
	}

	public void setDis500Total(Long dis500Total) {
		this.dis500Total = dis500Total;
	}

	public Long getDisTotal() {
		return d1qty0 + d1qty36 + d1qty48 + d2qty0 + d2qty36 + d2qty48;
	}

	public void setDisTotal(Long disTotal) {
		this.disTotal = disTotal;
	}

}
