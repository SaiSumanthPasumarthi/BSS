package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

public class HSICummSummaryMonthlyViewPK implements Serializable  {

	private static final long serialVersionUID = -8097309697442740052L;
	
	private String usageYYYYMM;
	
	private String day;

	public String getUsageYYYYMM() {
		return usageYYYYMM;
	}

	public void setUsageYYYYMM(String usageYYYYMM) {
		this.usageYYYYMM = usageYYYYMM;
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
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((usageYYYYMM == null) ? 0 : usageYYYYMM.hashCode());
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
		HSICummSummaryMonthlyViewPK other = (HSICummSummaryMonthlyViewPK) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (usageYYYYMM == null) {
			if (other.usageYYYYMM != null)
				return false;
		} else if (!usageYYYYMM.equals(other.usageYYYYMM))
			return false;
		return true;
	}

}
