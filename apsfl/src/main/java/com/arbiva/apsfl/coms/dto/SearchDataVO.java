/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author kiran
 *
 */
public class SearchDataVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private PageObject pageObject;
	
	private MultiAction multiAction;
	
	private String loginId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public MultiAction getMultiAction() {
		return multiAction;
	}

	public void setMultiAction(MultiAction multiAction) {
		this.multiAction = multiAction;
	}
	
	
}
