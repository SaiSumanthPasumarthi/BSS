/**
 * 
 */
package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;

/**
 * @author Lakshman
 *
 */
public class VPNSrvcVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String substnUid;
	
	private String oltSerialNo;
	
	private String vpnSrvcName;

	public String getSubstnUid() {
		return substnUid;
	}

	public void setSubstnUid(String substnUid) {
		this.substnUid = substnUid;
	}

	public String getOltSerialNo() {
		return oltSerialNo;
	}

	public void setOltSerialNo(String oltSerialNo) {
		this.oltSerialNo = oltSerialNo;
	}

	public String getVpnSrvcName() {
		return vpnSrvcName;
	}

	public void setVpnSrvcName(String vpnSrvcName) {
		this.vpnSrvcName = vpnSrvcName;
	}
	
}
