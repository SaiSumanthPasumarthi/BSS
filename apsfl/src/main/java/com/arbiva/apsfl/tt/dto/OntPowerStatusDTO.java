package com.arbiva.apsfl.tt.dto;

public class OntPowerStatusDTO {

	private String operationalState;
	private String temperature;
	private String rxPower1550nm;
	private String rxPower1490nm;
	private String txPower1310nm;//
	private String distance;
    private Integer statusCode;
    private String message;
    private Integer opstatusCode;
    private String lastDownCause;
	
	public String getLastDownCause() {
		return lastDownCause;
	}
	public void setLastDownCause(String lastDownCause) {
		this.lastDownCause = lastDownCause;
	}
	public Integer getOpstatusCode() {
		return opstatusCode;
	}
	public void setOpstatusCode(Integer opstatusCode) {
		this.opstatusCode = opstatusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getRxPower1550nm() {
		return rxPower1550nm;
	}
	public void setRxPower1550nm(String rxPower1550nm) {
		this.rxPower1550nm = rxPower1550nm;
	}
	public String getRxPower1490nm() {
		return rxPower1490nm;
	}
	public void setRxPower1490nm(String rxPower1490nm) {
		this.rxPower1490nm = rxPower1490nm;
	}
	public String getTxPower1310nm() {
		return txPower1310nm;
	}
	public void setTxPower1310nm(String txPower1310nm) {
		this.txPower1310nm = txPower1310nm;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getOperationalState() {
		return operationalState;
	}
	public void setOperationalState(String operationalState) {
		this.operationalState = operationalState;
	}
	
}
