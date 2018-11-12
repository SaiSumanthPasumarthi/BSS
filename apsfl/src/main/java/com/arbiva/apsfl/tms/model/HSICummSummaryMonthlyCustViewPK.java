package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

public class HSICummSummaryMonthlyCustViewPK implements Serializable{
	
	private static final long serialVersionUID = 3264299621156131804L;

	private String usageYYYY;
	private String usageMM;
	private String acctCafNo;
	private String day;
	
	public String getUsageYYYY() {
		return usageYYYY;
	}
	public void setUsageYYYY(String usageYYYY) {
		this.usageYYYY = usageYYYY;
	}
	public String getUsageMM() {
		return usageMM;
	}
	public void setUsageMM(String usageMM) {
		this.usageMM = usageMM;
	}
	public String getAcctCafNo() {
		return acctCafNo;
	}
	public void setAcctCafNo(String acctCafNo) {
		this.acctCafNo = acctCafNo;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acctCafNo == null) ? 0 : acctCafNo.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((usageMM == null) ? 0 : usageMM.hashCode());
		result = prime * result + ((usageYYYY == null) ? 0 : usageYYYY.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HSICummSummaryMonthlyCustViewPK other = (HSICummSummaryMonthlyCustViewPK) obj;
		if (acctCafNo == null) {
			if (other.acctCafNo != null)
				return false;
		} else if (!acctCafNo.equals(other.acctCafNo))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (usageMM == null) {
			if (other.usageMM != null)
				return false;
		} else if (!usageMM.equals(other.usageMM))
			return false;
		if (usageYYYY == null) {
			if (other.usageYYYY != null)
				return false;
		} else if (!usageYYYY.equals(other.usageYYYY))
			return false;
		return true;
	}

	
}
