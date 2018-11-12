package com.arbiva.apsfl.coms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BulkCustomerDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="cafno")
	private String cafno;

	@Column(name="custname")
	private String customername;
	
	@Column(name="pocmob1")
	private String mobile;
	
	@Column(name="regbal")
	private String dueamount;

    

    public String getCafno ()
    {
        return cafno;
    }

    public void setCafno (String cafno)
    {
        this.cafno = cafno;
    }

    public String getDueamount ()
    {
        return dueamount;
    }

    public void setDueamount (String dueamount)
    {
        this.dueamount = dueamount;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }

    public String getCustomername ()
    {
        return customername;
    }

    public void setCustomername (String customername)
    {
        this.customername = customername;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cafno = "+cafno+", dueamount = "+dueamount+", mobile = "+mobile+", customername = "+customername+"]";
    }
}
