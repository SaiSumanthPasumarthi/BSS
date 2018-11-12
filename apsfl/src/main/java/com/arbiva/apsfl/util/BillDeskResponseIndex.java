package com.arbiva.apsfl.util;

public enum BillDeskResponseIndex {
	
	MERCHANTID(0),
	CUSTOMERID(1),
	TXNREFERENCENO(2),
	BANKREFERENCENO(3),
	TXNAMOUNT(4),
	BANKID(5),
	BIN(6),
	TXNTYPE(7),
	CURRENCYNAME(8),
	ITEMCODE(9),
	SECURITYTYPE(10),
	SECURITYID(11),
	SECURITYPASSWORD(12),
	TXNDATE(13),
	AUTHSTATUS(14),
	SETTLEMENTTYPE(15),
	ADDITIONALINFO1(16),
	ADDITIONALINFO2(17),
	ADDITIONALINFO3(18),
	ADDITIONALINFO4(19),
	ADDITIONALINFO5(21),
	ADDITIONALINFO6(22),
	ADDITIONALINFO7(23),
	ERRORSTATUS(24),
	ERRORDESCRIPTION(25),
	CHECKSUM(26);
	
	private int index;
	
	private BillDeskResponseIndex(int i) {
		
		this.index = i;
	}
	
	
	public int getIndex(){
		return index;
	}

}
