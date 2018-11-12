package com.arbiva.apsfl.tt.dto;

import java.util.List;

public class TTTIptvDTO {

    private String stbslno;
	private String stbmacaddr;
    private String cpe_model;
    
    /*List<TTTIptvDTO> iptvList;
    
	public List<TTTIptvDTO> getIptvList() {
		return iptvList;
	}
	public void setIptvList(List<TTTIptvDTO> iptvList) {
		this.iptvList = iptvList;
	}*/
	public String getStbslno() {
		return stbslno;
	}
	public void setStbslno(String stbslno) {
		this.stbslno = stbslno;
	}
	public String getStbmacaddr() {
		return stbmacaddr;
	}
	public void setStbmacaddr(String stbmacaddr) {
		this.stbmacaddr = stbmacaddr;
	}
	public String getCpe_model() {
		return cpe_model;
	}
	public void setCpe_model(String cpe_model) {
		this.cpe_model = cpe_model;
	}
	public String getNwsubscode() {
		return nwsubscode;
	}
	public void setNwsubscode(String nwsubscode) {
		this.nwsubscode = nwsubscode;
	}
	private String nwsubscode;
	
	
}
