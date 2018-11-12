package com.arbiva.apsfl.coms.dto;

public class PageObject {

	private int minSize;
	
	private int maxSize;
	
	private String sortColumn;
	
	private String sortOrder;
	
	private String searchParameter;
	
	private String totalDisplayCount;
	
	private String tenantType;

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public String getTotalDisplayCount() {
		return totalDisplayCount;
	}

	public void setTotalDisplayCount(String totalDisplayCount) {
		this.totalDisplayCount = totalDisplayCount;
	}

	public String getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(String searchParameter) {
		this.searchParameter = searchParameter;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	

}
