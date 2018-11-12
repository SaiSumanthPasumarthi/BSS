package com.arbiva.apsfl.tms.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Transient;

@Entity
@IdClass(value = HSICummSummaryMonthlyViewPK.class)
public class HSICummSummaryMonthlyViewDTO {

	@Id
	@Column(name = "usageyyyymm")
	private String usageYYYYMM;
	
	@Id
	@Column(name = "dayn")
	private String day;
	
	@Column(name = "daydnldusage")
	private String daydnldusage;
	
	@Column(name = "dayupldusage")
	private String dayupldusage;
	
	@Transient
	private String dayTotalUsage;
	
	@Column(name = "daysubscount")
	private String daysubscount;
	
	//@Column(name = "prehrcnt")
	@Transient
	private String preHrCount;
	
	@Column(name = "ulh01")
	private String ulh01;
	@Column(name = "ulh02")
	private String ulh02;
	@Column(name = "ulh03")
	private String ulh03;
	@Column(name = "ulh04")
	private String ulh04;
	@Column(name = "ulh05")
	private String ulh05;
	@Column(name = "ulh06")
	private String ulh06;
	@Column(name = "ulh07")
	private String ulh07;
	@Column(name = "ulh08")
	private String ulh08;
	@Column(name = "ulh09")
	private String ulh09;
	@Column(name = "ulh10")
	private String ulh10;
	@Column(name = "ulh11")
	private String ulh11;
	@Column(name = "ulh12")
	private String ulh12;
	@Column(name = "ulh13")
	private String ulh13;
	@Column(name = "ulh14")
	private String ulh14;
	@Column(name = "ulh15")
	private String ulh15;
	@Column(name = "ulh16")
	private String ulh16;
	@Column(name = "ulh17")
	private String ulh17;
	@Column(name = "ulh18")
	private String ulh18;
	@Column(name = "ulh19")
	private String ulh19;
	@Column(name = "ulh20")
	private String ulh20;
	@Column(name = "ulh21")
	private String ulh21;
	@Column(name = "ulh22")
	private String ulh22;
	@Column(name = "ulh23")
	private String ulh23;
	@Column(name = "ulh24")
	private String ulh24;

	@Column(name = "dlh01")
	private String dlh01;
	@Column(name = "dlh02")
	private String dlh02;
	@Column(name = "dlh03")
	private String dlh03;
	@Column(name = "dlh04")
	private String dlh04;
	@Column(name = "dlh05")
	private String dlh05;
	@Column(name = "dlh06")
	private String dlh06;
	@Column(name = "dlh07")
	private String dlh07;
	@Column(name = "dlh08")
	private String dlh08;
	@Column(name = "dlh09")
	private String dlh09;
	@Column(name = "dlh10")
	private String dlh10;
	@Column(name = "dlh11")
	private String dlh11;
	@Column(name = "dlh12")
	private String dlh12;
	@Column(name = "dlh13")
	private String dlh13;
	@Column(name = "dlh14")
	private String dlh14;
	@Column(name = "dlh15")
	private String dlh15;
	@Column(name = "dlh16")
	private String dlh16;
	@Column(name = "dlh17")
	private String dlh17;
	@Column(name = "dlh18")
	private String dlh18;
	@Column(name = "dlh19")
	private String dlh19;
	@Column(name = "dlh20")
	private String dlh20;
	@Column(name = "dlh21")
	private String dlh21;
	@Column(name = "dlh22")
	private String dlh22;
	@Column(name = "dlh23")
	private String dlh23;
	@Column(name = "dlh24")
	private String dlh24;

	@Column(name = "sch01")
	private String sch01;
	@Column(name = "sch02")
	private String sch02;
	@Column(name = "sch03")
	private String sch03;
	@Column(name = "sch04")
	private String sch04;
	@Column(name = "sch05")
	private String sch05;
	@Column(name = "sch06")
	private String sch06;
	@Column(name = "sch07")
	private String sch07;
	@Column(name = "sch08")
	private String sch08;
	@Column(name = "sch09")
	private String sch09;
	@Column(name = "sch10")
	private String sch10;
	@Column(name = "sch11")
	private String sch11;
	@Column(name = "sch12")
	private String sch12;
	@Column(name = "sch13")
	private String sch13;
	@Column(name = "sch14")
	private String sch14;
	@Column(name = "sch15")
	private String sch15;
	@Column(name = "sch16")
	private String sch16;
	@Column(name = "sch17")
	private String sch17;
	@Column(name = "sch18")
	private String sch18;
	@Column(name = "sch19")
	private String sch19;
	@Column(name = "sch20")
	private String sch20;
	@Column(name = "sch21")
	private String sch21;
	@Column(name = "sch22")
	private String sch22;
	@Column(name = "sch23")
	private String sch23;
	@Column(name = "sch24")
	private String sch24;
	
	@Transient
	private String year;
	
	@Transient
	private String month;
	
	@Transient
	private String monthDay;
	
	@Transient
	private String totalDwlSize;
	
	@Transient
	private String totalUplSize;
	
	@Transient
	private String totalUsedSize;
	
	@Transient
	private String subsCount;
	
	public String getPreHrCount() {
		return preHrCount;
	}
	public void setPreHrCount(String preHrCount) {
		this.preHrCount = preHrCount;
	}
	public String getYear(){
		return usageYYYYMM.substring(0, 4);
	}
	public String getMonth(){
		String months[] = {"JAN","FEB","MAR","APRIL","MAY","JUNE","JULY","AUG","SEP","OCT","NOV","DEC"};
		int i = Integer.parseInt(usageYYYYMM.substring(4));
		return months[i - 1];
	}
	
	public String getMonthDay(){
		return getDay()+"-"+getMonth();
	}
	public String getTotalDwlSize() {
		return totalDwlSize;
	}
	public void setTotalDwlSize(String totalDwlSize) {
		this.totalDwlSize = totalDwlSize;
	}
	public String getTotalUplSize() {
		return totalUplSize;
	}
	public void setTotalUplSize(String totalUplSize) {
		this.totalUplSize = totalUplSize;
	}
	public String getTotalUsedSize() {
		return totalUsedSize;
	}
	public void setTotalUsedSize(String totalUsedSize) {
		this.totalUsedSize = totalUsedSize;
	}
	public String getSubsCount() {
		return subsCount;
	}
	public void setSubsCount(String subsCount) {
		this.subsCount = subsCount;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}
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
	
	
	public String getDayTotalUsage() {
		BigDecimal dayDown = new BigDecimal(this.getDaydnldusage());
		BigDecimal dayUp = new BigDecimal(this.getDayupldusage());
		BigDecimal totalUsage = dayDown.add(dayUp);
		return totalUsage.toString();
	}
	public void setDayTotalUsage(String dayTotalUsage) {
		this.dayTotalUsage = dayTotalUsage;
	}
	public String getDaydnldusage() {
		BigDecimal dayDown = new BigDecimal(daydnldusage);
		BigDecimal totalUsage = ((dayDown.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDaydnldusage(String daydnldusage) {
		this.daydnldusage = daydnldusage;
	}
	public String getDayupldusage() {
		BigDecimal dayUp = new BigDecimal(dayupldusage);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDayupldusage(String dayupldusage) {
		this.dayupldusage = dayupldusage;
	}
	public String getDaysubscount() {
		return daysubscount;
	}
	public void setDaysubscount(String daysubscount) {
		this.daysubscount = daysubscount;
	}
	public String getUlh01() {
		BigDecimal dayUp = new BigDecimal(ulh01);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh01(String ulh01) {
		this.ulh01 = ulh01;
	}
	public String getUlh02() {
		BigDecimal dayUp = new BigDecimal(ulh02);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh02(String ulh02) {
		this.ulh02 = ulh02;
	}
	public String getUlh03() {
		BigDecimal dayUp = new BigDecimal(ulh03);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh03(String ulh03) {
		this.ulh03 = ulh03;
	}
	public String getUlh04() {
		BigDecimal dayUp = new BigDecimal(ulh04);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh04(String ulh04) {
		this.ulh04 = ulh04;
	}
	public String getUlh05() {
		BigDecimal dayUp = new BigDecimal(ulh05);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh05(String ulh05) {
		this.ulh05 = ulh05;
	}
	public String getUlh06() {
		BigDecimal dayUp = new BigDecimal(ulh06);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh06(String ulh06) {
		this.ulh06 = ulh06;
	}
	public String getUlh07() {
		BigDecimal dayUp = new BigDecimal(ulh07);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh07(String ulh07) {
		this.ulh07 = ulh07;
	}
	public String getUlh08() {
		BigDecimal dayUp = new BigDecimal(ulh08);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh08(String ulh08) {
		this.ulh08 = ulh08;
	}
	public String getUlh09() {
		BigDecimal dayUp = new BigDecimal(ulh09);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh09(String ulh09) {
		this.ulh09 = ulh09;
	}
	public String getUlh10() {
		BigDecimal dayUp = new BigDecimal(ulh10);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh10(String ulh10) {
		this.ulh10 = ulh10;
	}
	public String getUlh11() {
		BigDecimal dayUp = new BigDecimal(ulh11);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh11(String ulh11) {
		this.ulh11 = ulh11;
	}
	public String getUlh12() {
		BigDecimal dayUp = new BigDecimal(ulh12);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh12(String ulh12) {
		this.ulh12 = ulh12;
	}
	public String getUlh13() {
		BigDecimal dayUp = new BigDecimal(ulh13);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh13(String ulh13) {
		this.ulh13 = ulh13;
	}
	public String getUlh14() {
		BigDecimal dayUp = new BigDecimal(ulh14);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh14(String ulh14) {
		this.ulh14 = ulh14;
	}
	public String getUlh15() {
		BigDecimal dayUp = new BigDecimal(ulh15);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh15(String ulh15) {
		this.ulh15 = ulh15;
	}
	public String getUlh16() {
		BigDecimal dayUp = new BigDecimal(ulh16);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh16(String ulh16) {
		this.ulh16 = ulh16;
	}
	public String getUlh17() {
		BigDecimal dayUp = new BigDecimal(ulh17);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh17(String ulh17) {
		this.ulh17 = ulh17;
	}
	public String getUlh18() {
		BigDecimal dayUp = new BigDecimal(ulh18);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh18(String ulh18) {
		this.ulh18 = ulh18;
	}
	public String getUlh19() {
		BigDecimal dayUp = new BigDecimal(ulh19);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh19(String ulh19) {
		this.ulh19 = ulh19;
	}
	public String getUlh20() {
		BigDecimal dayUp = new BigDecimal(ulh20);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh20(String ulh20) {
		this.ulh20 = ulh20;
	}
	public String getUlh21() {
		BigDecimal dayUp = new BigDecimal(ulh21);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh21(String ulh21) {
		this.ulh21 = ulh21;
	}
	public String getUlh22() {
		BigDecimal dayUp = new BigDecimal(ulh22);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh22(String ulh22) {
		this.ulh22 = ulh22;
	}
	public String getUlh23() {
		BigDecimal dayUp = new BigDecimal(ulh23);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh23(String ulh23) {
		this.ulh23 = ulh23;
	}
	public String getUlh24() {
		BigDecimal dayUp = new BigDecimal(ulh24);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setUlh24(String ulh24) {
		this.ulh24 = ulh24;
	}
	public String getDlh01() {
		BigDecimal dayUp = new BigDecimal(dlh01);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh01(String dlh01) {
		this.dlh01 = dlh01;
	}
	public String getDlh02() {
		BigDecimal dayUp = new BigDecimal(dlh02);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh02(String dlh02) {
		this.dlh02 = dlh02;
	}
	public String getDlh03() {
		BigDecimal dayUp = new BigDecimal(dlh03);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh03(String dlh03) {
		this.dlh03 = dlh03;
	}
	public String getDlh04() {
		BigDecimal dayUp = new BigDecimal(dlh04);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh04(String dlh04) {
		this.dlh04 = dlh04;
	}
	public String getDlh05() {
		BigDecimal dayUp = new BigDecimal(dlh05);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh05(String dlh05) {
		this.dlh05 = dlh05;
	}
	public String getDlh06() {
		BigDecimal dayUp = new BigDecimal(dlh06);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh06(String dlh06) {
		this.dlh06 = dlh06;
	}
	public String getDlh07() {
		BigDecimal dayUp = new BigDecimal(dlh07);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh07(String dlh07) {
		this.dlh07 = dlh07;
	}
	public String getDlh08() {
		BigDecimal dayUp = new BigDecimal(dlh08);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh08(String dlh08) {
		this.dlh08 = dlh08;
	}
	public String getDlh09() {
		BigDecimal dayUp = new BigDecimal(dlh09);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh09(String dlh09) {
		this.dlh09 = dlh09;
	}
	public String getDlh10() {
		BigDecimal dayUp = new BigDecimal(dlh10);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh10(String dlh10) {
		this.dlh10 = dlh10;
	}
	public String getDlh11() {
		BigDecimal dayUp = new BigDecimal(dlh11);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh11(String dlh11) {
		this.dlh11 = dlh11;
	}
	public String getDlh12() {
		BigDecimal dayUp = new BigDecimal(dlh12);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh12(String dlh12) {
		this.dlh12 = dlh12;
	}
	public String getDlh13() {
		BigDecimal dayUp = new BigDecimal(dlh13);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh13(String dlh13) {
		this.dlh13 = dlh13;
	}
	public String getDlh14() {
		BigDecimal dayUp = new BigDecimal(dlh14);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh14(String dlh14) {
		this.dlh14 = dlh14;
	}
	public String getDlh15() {
		BigDecimal dayUp = new BigDecimal(dlh15);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh15(String dlh15) {
		this.dlh15 = dlh15;
	}
	public String getDlh16() {
		BigDecimal dayUp = new BigDecimal(dlh16);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh16(String dlh16) {
		this.dlh16 = dlh16;
	}
	public String getDlh17() {
		BigDecimal dayUp = new BigDecimal(dlh17);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh17(String dlh17) {
		this.dlh17 = dlh17;
	}
	public String getDlh18() {
		BigDecimal dayUp = new BigDecimal(dlh18);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh18(String dlh18) {
		this.dlh18 = dlh18;
	}
	public String getDlh19() {
		BigDecimal dayUp = new BigDecimal(dlh19);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh19(String dlh19) {
		this.dlh19 = dlh19;
	}
	public String getDlh20() {
		BigDecimal dayUp = new BigDecimal(dlh20);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh20(String dlh20) {
		this.dlh20 = dlh20;
	}
	public String getDlh21() {
		BigDecimal dayUp = new BigDecimal(dlh21);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh21(String dlh21) {
		this.dlh21 = dlh21;
	}
	public String getDlh22() {
		BigDecimal dayUp = new BigDecimal(dlh22);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh22(String dlh22) {
		this.dlh22 = dlh22;
	}
	public String getDlh23() {
		BigDecimal dayUp = new BigDecimal(dlh23);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh23(String dlh23) {
		this.dlh23 = dlh23;
	}
	public String getDlh24() {
		BigDecimal dayUp = new BigDecimal(dlh24);
		BigDecimal totalUsage = ((dayUp.divide(new BigDecimal("1024"))).divide(new BigDecimal("1024"))).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
		return totalUsage.toString();
	}
	public void setDlh24(String dlh24) {
		this.dlh24 = dlh24;
	}
	public String getSch01() {
		return sch01;
	}
	public void setSch01(String sch01) {
		this.sch01 = sch01;
	}
	public String getSch02() {
		return sch02;
	}
	public void setSch02(String sch02) {
		this.sch02 = sch02;
	}
	public String getSch03() {
		return sch03;
	}
	public void setSch03(String sch03) {
		this.sch03 = sch03;
	}
	public String getSch04() {
		return sch04;
	}
	public void setSch04(String sch04) {
		this.sch04 = sch04;
	}
	public String getSch05() {
		return sch05;
	}
	public void setSch05(String sch05) {
		this.sch05 = sch05;
	}
	public String getSch06() {
		return sch06;
	}
	public void setSch06(String sch06) {
		this.sch06 = sch06;
	}
	public String getSch07() {
		return sch07;
	}
	public void setSch07(String sch07) {
		this.sch07 = sch07;
	}
	public String getSch08() {
		return sch08;
	}
	public void setSch08(String sch08) {
		this.sch08 = sch08;
	}
	public String getSch09() {
		return sch09;
	}
	public void setSch09(String sch09) {
		this.sch09 = sch09;
	}
	public String getSch10() {
		return sch10;
	}
	public void setSch10(String sch10) {
		this.sch10 = sch10;
	}
	public String getSch11() {
		return sch11;
	}
	public void setSch11(String sch11) {
		this.sch11 = sch11;
	}
	public String getSch12() {
		return sch12;
	}
	public void setSch12(String sch12) {
		this.sch12 = sch12;
	}
	public String getSch13() {
		return sch13;
	}
	public void setSch13(String sch13) {
		this.sch13 = sch13;
	}
	public String getSch14() {
		return sch14;
	}
	public void setSch14(String sch14) {
		this.sch14 = sch14;
	}
	public String getSch15() {
		return sch15;
	}
	public void setSch15(String sch15) {
		this.sch15 = sch15;
	}
	public String getSch16() {
		return sch16;
	}
	public void setSch16(String sch16) {
		this.sch16 = sch16;
	}
	public String getSch17() {
		return sch17;
	}
	public void setSch17(String sch17) {
		this.sch17 = sch17;
	}
	public String getSch18() {
		return sch18;
	}
	public void setSch18(String sch18) {
		this.sch18 = sch18;
	}
	public String getSch19() {
		return sch19;
	}
	public void setSch19(String sch19) {
		this.sch19 = sch19;
	}
	public String getSch20() {
		return sch20;
	}
	public void setSch20(String sch20) {
		this.sch20 = sch20;
	}
	public String getSch21() {
		return sch21;
	}
	public void setSch21(String sch21) {
		this.sch21 = sch21;
	}
	public String getSch22() {
		return sch22;
	}
	public void setSch22(String sch22) {
		this.sch22 = sch22;
	}
	public String getSch23() {
		return sch23;
	}
	public void setSch23(String sch23) {
		this.sch23 = sch23;
	}
	public String getSch24() {
		return sch24;
	}
	public void setSch24(String sch24) {
		this.sch24 = sch24;
	}
}
