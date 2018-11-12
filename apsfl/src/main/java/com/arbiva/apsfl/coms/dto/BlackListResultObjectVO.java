/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author kiran
 *
 */
public class BlackListResultObjectVO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String msgResult;
	
	private List<Object[]> blackList;

	public String getMsgResult() {
		return msgResult;
	}

	public void setMsgResult(String msgResult) {
		this.msgResult = msgResult;
	}

	public List<Object[]> getBlackList() {
		return blackList;
	}

	public void setBlackList(List<Object[]> blackList) {
		this.blackList = blackList;
	}
}
