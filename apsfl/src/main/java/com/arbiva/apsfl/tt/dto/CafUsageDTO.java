package com.arbiva.apsfl.tt.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author gowthami
 *
 */

public class CafUsageDTO implements Serializable{
	private BigDecimal baseDnldSize;
	private BigDecimal baseUpldSize;
	private BigDecimal bstrDnldSize;
	private BigDecimal bstrUpldSize;
	private BigDecimal totalDnldSize;
	private BigDecimal totalUpldSize;
	private BigDecimal dnldUsage;
	private BigDecimal upldUsage;
	
	private String normalSpeed;
	private String threasholdspeed;
	public String getNormalSpeed() {
		return normalSpeed;
	}
	public void setNormalSpeed(String normalSpeed) {
		this.normalSpeed = normalSpeed;
	}
	public String getThreasholdspeed() {
		return threasholdspeed;
	}
	public void setThreasholdspeed(String threasholdspeed) {
		this.threasholdspeed = threasholdspeed;
	}
	public BigDecimal getBaseDnldSize() {
		return baseDnldSize;
	}
	public void setBaseDnldSize(BigDecimal baseDnldSize) {
		this.baseDnldSize = baseDnldSize;
	}
	public BigDecimal getBaseUpldSize() {
		return baseUpldSize;
	}
	public void setBaseUpldSize(BigDecimal baseUpldSize) {
		this.baseUpldSize = baseUpldSize;
	}
	public BigDecimal getBstrDnldSize() {
		return bstrDnldSize;
	}
	public void setBstrDnldSize(BigDecimal bstrDnldSize) {
		this.bstrDnldSize = bstrDnldSize;
	}
	public BigDecimal getBstrUpldSize() {
		return bstrUpldSize;
	}
	public void setBstrUpldSize(BigDecimal bstrUpldSize) {
		this.bstrUpldSize = bstrUpldSize;
	}
	public BigDecimal getTotalDnldSize() {
		return totalDnldSize;
	}
	public void setTotalDnldSize(BigDecimal totalDnldSize) {
		this.totalDnldSize = totalDnldSize;
	}
	public BigDecimal getTotalUpldSize() {
		return totalUpldSize;
	}
	public void setTotalUpldSize(BigDecimal totalUpldSize) {
		this.totalUpldSize = totalUpldSize;
	}
	public BigDecimal getDnldUsage() {
		return dnldUsage;
	}
	public void setDnldUsage(BigDecimal dnldUsage) {
		this.dnldUsage = dnldUsage;
	}
	public BigDecimal getUpldUsage() {
		return upldUsage;
	}
	public void setUpldUsage(BigDecimal upldUsage) {
		this.upldUsage = upldUsage;
	}

}
