package com.arbiva.apsfl.coms.dto;
import java.io.Serializable;
import java.util.List;

import com.arbiva.apsfl.tms.model.Lovs;


/**
 * @author Srinivas V
 * @since Feb 08 2017
 */
 

public class ChangeOnuHelperDTO implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private List<ChangeOnuDTO> changeOnuList;
	
	private List<Lovs> lovChangeReasonlist;

	public List<ChangeOnuDTO> getChangeOnuList() {
		return changeOnuList;
	}

	public void setChangeOnuList(List<ChangeOnuDTO> changeOnuList) {
		this.changeOnuList = changeOnuList;
	}

	public List<Lovs> getLovChangeReasonlist() {
		return lovChangeReasonlist;
	}

	public void setLovChangeReasonlist(List<Lovs> lovChangeReasonlist) {
		this.lovChangeReasonlist = lovChangeReasonlist;
	}

	 

}
 
 

 