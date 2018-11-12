/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lakshman
 *
 */
public class BulkUploadCafVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pmntCustId;

	private String custType;

	private String regNumber;

	private String billCycle;

	private MultipartFile bulkUploadFile;

	private String bulkUpload;

	private String agruniqueid;
	
	private String prodCode;

	private String tenantCode;

	private String telProdCode;

	private String telSrvcCode;
	
	private String telTenantCode;
	
	private List<Caf> cafList;
	
	private List<ProductsVO> productList;

	public String getPmntCustId() {
		return pmntCustId;
	}

	public void setPmntCustId(String pmntCustId) {
		this.pmntCustId = pmntCustId;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}

	public MultipartFile getBulkUploadFile() {
		return bulkUploadFile;
	}

	public void setBulkUploadFile(MultipartFile bulkUploadFile) {
		this.bulkUploadFile = bulkUploadFile;
	}

	public String getBulkUpload() {
		return bulkUpload;
	}

	public void setBulkUpload(String bulkUpload) {
		this.bulkUpload = bulkUpload;
	}

	public String getAgruniqueid() {
		return agruniqueid;
	}

	public void setAgruniqueid(String agruniqueid) {
		this.agruniqueid = agruniqueid;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getTelProdCode() {
		return telProdCode;
	}

	public void setTelProdCode(String telProdCode) {
		this.telProdCode = telProdCode;
	}

	public String getTelSrvcCode() {
		return telSrvcCode;
	}

	public void setTelSrvcCode(String telSrvcCode) {
		this.telSrvcCode = telSrvcCode;
	}

	public String getTelTenantCode() {
		return telTenantCode;
	}

	public void setTelTenantCode(String telTenantCode) {
		this.telTenantCode = telTenantCode;
	}

	public List<Caf> getCafList() {
		return cafList;
	}

	public void setCafList(List<Caf> cafList) {
		this.cafList = cafList;
	}

	public List<ProductsVO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductsVO> productList) {
		this.productList = productList;
	}
	
}
