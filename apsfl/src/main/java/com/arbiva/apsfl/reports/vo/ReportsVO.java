package com.arbiva.apsfl.reports.vo;

import java.io.Serializable;
import java.util.List;


public class ReportsVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<OsdFingPrntVO> osdFingPrnt;
    
	private List<CafDatesVO> cafDates;

	public List<OsdFingPrntVO> getOsdFingPrnt() {
		return osdFingPrnt;
	}

	public void setOsdFingPrnt(List<OsdFingPrntVO> osdFingPrnt) {
		this.osdFingPrnt = osdFingPrnt;
	}

	public List<CafDatesVO> getCafDates() {
		return cafDates;
	}

	public void setCafDates(List<CafDatesVO> cafDates) {
		this.cafDates = cafDates;
	}
}
