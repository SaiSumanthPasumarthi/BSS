/**
 * 
 */
package com.arbiva.apsfl.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Arbiva
 *
 */
public class UserMenuItemsRestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsersDTO usersDTOobj;
	
	private List<MenuItemsDTO> menuItemList;

	public UsersDTO getUsersDTOobj() {
		return usersDTOobj;
	}

	public void setUsersDTOobj(UsersDTO usersDTOobj) {
		this.usersDTOobj = usersDTOobj;
	}

	public List<MenuItemsDTO> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<MenuItemsDTO> menuItemList) {
		this.menuItemList = menuItemList;
	}


}
