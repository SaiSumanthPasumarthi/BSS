package com.arbiva.apsfl.coms.dto;

public class PaymentDetailsVO {

	private String prodname;

	private String prodtype;

	private String prodcharge;

	private String prodtax;

	private String srvcname;

	private String createdon;
	
	private String stbcafno;

	public String getStbcafno() {
		return stbcafno;
	}

	public void setStbcafno(String stbcafno) {
		this.stbcafno = stbcafno;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getProdtype() {
		return prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public String getProdcharge() {
		return prodcharge;
	}

	public void setProdcharge(String prodcharge) {
		this.prodcharge = prodcharge;
	}

	public String getProdtax() {
		return prodtax;
	}

	public void setProdtax(String prodtax) {
		this.prodtax = prodtax;
	}

	public String getSrvcname() {
		return srvcname;
	}

	public void setSrvcname(String srvcname) {
		this.srvcname = srvcname;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

}
