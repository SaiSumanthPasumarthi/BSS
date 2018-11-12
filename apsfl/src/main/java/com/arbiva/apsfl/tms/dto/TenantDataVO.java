/**
 * 
 */
package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;

import com.arbiva.apsfl.coms.dto.PageObject;

/**
 * @author kiran
 *
 */
public class TenantDataVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private PageObject pageObject;
	
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

}
