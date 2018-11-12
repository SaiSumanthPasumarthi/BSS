package com.arbiva.apsfl.coms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.arbiva.apsfl.coms.dto.CustomerCafBO.UsersDataSearchParams;

/**
 * @author Lakshman
 *
 */
@Entity
public class LmoDetailsBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum UsersDataSearchParams{

		COLUMN_0("regBal"),COLUMN_1("lmoCode"),COLUMN_2("pmntamt");

		private String colName;

		private UsersDataSearchParams(String colName) {
			this.colName = colName;
		}

		public String getColName() {
			return colName;
		}

		public static String getColumns(String colName) {
			for (UsersDataSearchParams usersDataSearchParams : UsersDataSearchParams.values()) {
				if (usersDataSearchParams.toString().equals(colName)) {
					return usersDataSearchParams.getColName();
				}
			}
			return "";
		}
	}
	
	private BigDecimal regBal;
	
	public BigDecimal getRegBal() {
		return regBal;
	}

	public void setRegBal(BigDecimal regBal) {
		this.regBal = regBal;
	}
	
	private String lmoCode;

	public String getLmoCode() {
		return lmoCode;
	}

	public void setLmoCode(String lmoCode) {
		this.lmoCode = lmoCode;
	}

	private BigDecimal pmntAmt;
	
	public BigDecimal getPmntAmt() {
		return pmntAmt;
	}

	public void setPmntAmt(BigDecimal pmntAmt) {
		this.pmntAmt = pmntAmt;
	}

}
