package com.arbiva.apsfl.tms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegionPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "regiontype")
	private Character regionType;
	
	@Column(name = "regioncode")
	private String regionCode;
	
	public RegionPK() {
		super();
	}

	public RegionPK(Character regionType, String regionCode) {
		super();
		this.regionType = regionType;
		this.regionCode = regionCode;
	}

	public Character getRegionType() {
		return regionType;
	}

	public String getRegionCode() {
		return regionCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regionCode == null) ? 0 : regionCode.hashCode());
		result = prime * result + ((regionType == null) ? 0 : regionType.hashCode());
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
		RegionPK other = (RegionPK) obj;
		if (regionCode == null) {
			if (other.regionCode != null)
				return false;
		} else if (!regionCode.equals(other.regionCode))
			return false;
		if (regionType == null) {
			if (other.regionType != null)
				return false;
		} else if (!regionType.equals(other.regionType))
			return false;
		return true;
	}

}
