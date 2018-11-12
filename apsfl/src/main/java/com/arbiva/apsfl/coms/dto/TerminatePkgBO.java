/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * @author Lakshman
 *
 */
@Entity
public class TerminatePkgBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cafNo;

	private String prodCafNo;

	private String prodDate;

	private String prodCode;

	private String prodName;

	private String agrmntCode;

	private String stbCafNo;

	private String stbSrlNo;

	private String nwSubscriberCode;

	private String tenantCode;

	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	public String getProdCafNo() {
		return prodCafNo;
	}

	public void setProdCafNo(String prodCafNo) {
		this.prodCafNo = prodCafNo;
	}

	public String getProdDate() {
		return prodDate;
	}

	public void setProdDate(String prodDate) {
		this.prodDate = prodDate;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getAgrmntCode() {
		return agrmntCode;
	}

	public void setAgrmntCode(String agrmntCode) {
		this.agrmntCode = agrmntCode;
	}

	public String getStbCafNo() {
		return stbCafNo;
	}

	public void setStbCafNo(String stbCafNo) {
		this.stbCafNo = stbCafNo;
	}

	public String getStbSrlNo() {
		return stbSrlNo;
	}

	public void setStbSrlNo(String stbSrlNo) {
		this.stbSrlNo = stbSrlNo;
	}

	public String getNwSubscriberCode() {
		return nwSubscriberCode;
	}

	public void setNwSubscriberCode(String nwSubscriberCode) {
		this.nwSubscriberCode = nwSubscriberCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

}
