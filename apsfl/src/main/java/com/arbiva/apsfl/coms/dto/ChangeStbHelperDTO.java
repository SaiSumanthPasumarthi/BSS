package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

import com.arbiva.apsfl.tms.model.Lovs;


 

/**
 * @author Srinivas V
 * @since Feb 08 2017
 */
public class ChangeStbHelperDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ChangeStbDTO> changeStbList;
	
	private List<Lovs> lovChangeReasonlist;

	public List<ChangeStbDTO> getChangeStbList() {
		return changeStbList;
	}

	public void setChangeStbList(List<ChangeStbDTO> changeStbList) {
		this.changeStbList = changeStbList;
	}

	public List<Lovs> getLovChangeReasonlist() {
		return lovChangeReasonlist;
	}

	public void setLovChangeReasonlist(List<Lovs> lovChangeReasonlist) {
		this.lovChangeReasonlist = lovChangeReasonlist;
	}

}
