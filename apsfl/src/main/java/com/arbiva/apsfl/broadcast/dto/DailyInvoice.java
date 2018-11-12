package com.arbiva.apsfl.broadcast.dto;

public class DailyInvoice {

	//private static final Logger logger = LoggerFactory.getLogger(DailyInvoice.class);
	
	private static final long serialVersionUID = 1L;
	
	public DailyInvoice() {
		
	}

	private Long broadcasterCode;
	
	private Long groupId;
	
	private int numOfSubcribers;
	
	private float totalChargeForGroup;
	
	private float swatchBharatCess;
	
	private float krushiKissanCess;
	
	private float entertainmentTax;
	
	private int invoiceMonthYear;
	
	private short status;

	public Long getBroadcasterCode() {
		return broadcasterCode;
	}

	public void setBroadcasterCode(Long broadcasterCode) {
		this.broadcasterCode = broadcasterCode;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public int getNumOfSubcribers() {
		return numOfSubcribers;
	}

	public void setNumOfSubcribers(int numOfSubcribers) {
		this.numOfSubcribers = numOfSubcribers;
	}

	public float getTotalChargeForGroup() {
		return totalChargeForGroup;
	}

	public void setTotalChargeForGroup(float totalChargeForGroup) {
		this.totalChargeForGroup = totalChargeForGroup;
	}

	public float getSwatchBharatCess() {
		return swatchBharatCess;
	}

	public void setSwatchBharatCess(float swatchBharatCess) {
		this.swatchBharatCess = swatchBharatCess;
	}

	public float getKrushiKissanCess() {
		return krushiKissanCess;
	}

	public void setKrushiKissanCess(float krushiKissanCess) {
		this.krushiKissanCess = krushiKissanCess;
	}

	public float getEntertainmentTax() {
		return entertainmentTax;
	}

	public void setEntertainmentTax(float entertainmentTax) {
		this.entertainmentTax = entertainmentTax;
	}

	public int getInvoiceMonthYear() {
		return invoiceMonthYear;
	}

	public void setInvoiceMonthYear(int invoiceMonthYear) {
		this.invoiceMonthYear = invoiceMonthYear;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
