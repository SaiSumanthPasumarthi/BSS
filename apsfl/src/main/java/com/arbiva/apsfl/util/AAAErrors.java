package com.arbiva.apsfl.util;

public enum AAAErrors {
	
	ERROR1("User-Request","Disconnected on user request"),
	ERROR2("Lost Carrier","Connection disconnected due to signal drop"),
	ERROR3("Lost Service","Service is not available"),
	ERROR4("Idle-Timeout","	Session timed out due to inactivity after a long time."),
	ERROR5("Session Timeout","	Session Timeout"),
	ERROR6("Admin-Reset","Session disconnected due to changes made by service provider"),
	ERROR7("Admin-Reboot","Session disconnected due to sytem update"),
	ERROR8("Port Error","Unable to communicate with authentication server"),
	ERROR9("NAS-Error","Session disconnected due to an unknown error detection"),
	ERROR10("NAS-Request","Session disconnected due to a configuration error/settings error"),
	ERROR11("NAS-Reboot","Session disconnected due to server/system reboot"),
	ERROR12("Port Unneeded","Session disconnected due to data usage under minimum usage limit"),
	ERROR13("Port Preempted","ervice is only available to specific customers due to emergency"),
	ERROR14("Port Suspended","Service is termporarily suspended"),
	ERROR15("Service Unavailable","Requested service is not available now"),
	ERROR16("Callback","Service termporarily unavailable due to upgradation"),
	ERROR17("User Error","Service termporarily unavailable due to incorrect user input"),
	ERROR18("Host-Request","Service unavailable due to termination request from host/user");
	
	private String error;
	
	private String reason;
	
	AAAErrors(String error, String reason){
		this.error = error;
		this.reason = reason;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
