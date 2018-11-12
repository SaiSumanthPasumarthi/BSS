package com.arbiva.apsfl.dto;

import java.io.Serializable;

/**
 * 
 * @author Sai Suresh
 *
 */
public class MenuItemsDTO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6095001362955849362L;

	private String menuPathID;
	
	private String menuItem;
	
	private String actionPath;

	public String getMenuPathID() {
		return menuPathID;
	}

	public void setMenuPathID(String menuPathID) {
		this.menuPathID = menuPathID;
	}

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}

	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
	
}
