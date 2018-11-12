package com.arbiva.apsfl.tms.dto;

public class CpeReportDTO {
	
	private String iptvActive;
	private String iptvNotActive;
	private String iptvDefective;
	private String onuActive;
	private String onuNotActive;
	private String onuDefective;
	private String total;
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getIptvActive() {
		return iptvActive;
	}
	public void setIptvActive(String iptvActive) {
		this.iptvActive = iptvActive;
	}
	public String getIptvNotActive() {
		return iptvNotActive;
	}
	public void setIptvNotActive(String iptvNotActive) {
		this.iptvNotActive = iptvNotActive;
	}
	public String getIptvDefective() {
		return iptvDefective;
	}
	public void setIptvDefective(String iptvDefective) {
		this.iptvDefective = iptvDefective;
	}
	public String getOnuActive() {
		return onuActive;
	}
	public void setOnuActive(String onuActive) {
		this.onuActive = onuActive;
	}
	public String getOnuNotActive() {
		return onuNotActive;
	}
	public void setOnuNotActive(String onuNotActive) {
		this.onuNotActive = onuNotActive;
	}
	public String getOnuDefective() {
		return onuDefective;
	}
	public void setOnuDefective(String onuDefctive) {
		this.onuDefective = onuDefctive;
	}
	

	
}
