/**
 * 
 */
package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.util.List;

import com.arbiva.apsfl.coms.dto.CafsForBlockListVO;

/**
 * @author kiran
 *
 */
public class BlackListResultVO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String msgResult;
	
	private List<BlackListInfoVO> blackList;
	
	private List<CafsForBlockListVO> cafsList;
	
	public List<CafsForBlockListVO> getCafsList() {
		return cafsList;
	}

	public void setCafsList(List<CafsForBlockListVO> cafsList) {
		this.cafsList = cafsList;
	}

	public String getMsgResult() {
		return msgResult;
	}

	public void setMsgResult(String msgResult) {
		this.msgResult = msgResult;
	}

	public List<BlackListInfoVO> getBlackList() {
		return blackList;
	}

	public void setBlackList(List<BlackListInfoVO> blackList) {
		this.blackList = blackList;
	}
	
	
}
