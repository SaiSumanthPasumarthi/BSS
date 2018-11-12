package com.arbiva.apsfl.dto;

import java.util.List;

import com.arbiva.apfgc.tenant.bo.MsoRevenueShareBO;

public class UmsHelperDTO {
	
	private List<RolesDTO> rolesList;
	
	private List<UsersDTO> usersList;
	
	private String message;
	
	public List<MsoRevenueShareBO> getLmoRevenueShareList() {
		return lmoRevenueShareList;
	}

	public void setLmoRevenueShareList(List<MsoRevenueShareBO> lmoRevenueShareList) {
		this.lmoRevenueShareList = lmoRevenueShareList;
	}

	private List<FunctionsDTO> listObj;
	
	private List<MsoRevenueShareBO> lmoRevenueShareList;
	
	

	public List<FunctionsDTO> getListObj() {
		return listObj;
	}

	public void setListObj(List<FunctionsDTO> listObj) {
		this.listObj = listObj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<RolesDTO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<RolesDTO> rolesList) {
		this.rolesList = rolesList;
	}

	public List<UsersDTO> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UsersDTO> usersList) {
		this.usersList = usersList;
	}
	
	
	

}
