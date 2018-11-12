package com.arbiva.apsfl.tt.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.TreeMap;

/**
 * 
 * @author gowthami
 *
 */

public class SLADTO implements Serializable{
	private BigInteger slaViolatedCnt;
	TreeMap<String,BigInteger> statusWiseCntMap=new TreeMap<String,BigInteger>();
	public BigInteger getSlaViolatedCnt() {
		return slaViolatedCnt;
	}
	public void setSlaViolatedCnt(BigInteger slaViolatedCnt) {
		this.slaViolatedCnt = slaViolatedCnt;
	}
	public TreeMap<String, BigInteger> getStatusWiseCntMap() {
		return statusWiseCntMap;
	}
	public void setStatusWiseCntMap(TreeMap<String, BigInteger> statusWiseCntMap) {
		this.statusWiseCntMap = statusWiseCntMap;
	}
}
