package com.arbiva.apsfl.tms.daoImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.dto.AAAUsageDTO;
import com.arbiva.apsfl.dto.AaaUsageBYHoursDTO;
import com.arbiva.apsfl.tms.model.AAAUsageBO;
import com.arbiva.apsfl.tms.model.HSICummSummaryMonthlyCustViewDTO;
import com.arbiva.apsfl.tms.model.HSICummSummaryMonthlyViewDTO;
import com.arbiva.apsfl.util.CafEnumCodes;
import com.arbiva.apsfl.util.DateUtill;

@Repository
public class ReportsDaoImpl {

	private static final Logger LOGGER = Logger.getLogger(ReportsDaoImpl.class);
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getListByQuery(String queryString) {
		Query query = null;
		List<Object[]> result = new ArrayList<>();
		try {
			LOGGER.info("START::getListByQuery()");
			query = getEntityManager().createNativeQuery(queryString.toString());
			result = query.getResultList();
			LOGGER.info("END::getListByQuery()");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("EXCEPTION::getListByQuery() " + e);
		} finally {
			query = null;
		}
		return result;
	}

	public String getSingleRecord(String queryString) {
		Query query = null;
		Object result = "";
		try {
			LOGGER.info("START::getActDate()");
			query = getEntityManager().createNativeQuery(queryString);
			result = query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::getListByQuery() " + e);
			e.printStackTrace();
		}
		return result.toString();
	}

	public Object[] getObjectSingleRecord(String queryString) {
		Query query = null;
		Object[] object = null;
		try {
			LOGGER.info("START::getActDate()");
			query = getEntityManager().createNativeQuery(queryString);
			object = (Object[]) query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::getListByQuery() " + e);
			e.printStackTrace();
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public AAAUsageDTO getAAAUsageBySearch(PageObject pageObject, AAAUsageDTO aaaUsageDTO) {
		String finalQuery = null;
		String searchParameter = "";
		Query query = null;
		
		List<Object[]> objList = new ArrayList<>();
		List<AAAUsageBO> aaaUsageList = new ArrayList<>();

		StringBuilder selectQuery = new StringBuilder("");
		selectQuery.append("SELECT  cmst.custname, cmst.aadharno, c.apsfluniqueid, cmst.pocmob1,");
		selectQuery.append(" c.cpeslno, c.cpemacaddr, sub.substnname, c.agorahsisubscode, c.aaacode,");
		selectQuery.append(" DATE_FORMAT(FROM_UNIXTIME(hu.etimestampstring-hu.sessiontime),'%d-%m-%Y %H:%i:%s') starttime,");
		selectQuery.append(" DATE_FORMAT(FROM_UNIXTIME(hu.etimestampstring),'%d-%m-%Y %H:%i:%s') endtime,");
		selectQuery.append(" hu.sessiontime, ROUND(hu.dwnldsize/1024/1024 , 3) dwnldsize, ROUND(hu.upldsize/1024/1024 , 3) upldsize, ROUND((hu.dwnldsize+hu.upldsize)/1024/1024 , 3) totalsize, hu.acctcafno, hu.accntterminatecause");
		selectQuery.append(" FROM cafs c , customermst cmst, substations sub, hsiusage hu");
		
		StringBuilder whereClause = new StringBuilder("");
			whereClause.append(" WHERE c.custtypelov = cmst.custtypelov");
			whereClause.append(" AND c.custid = cmst.custid");
			whereClause.append(" AND hu.customerid = c.custid");
			whereClause.append(" AND sub.substnuid = c.pop_substnno");
			whereClause.append(" AND hu.accntStatusType IN ('Stop')");
			whereClause.append(" and hu.acctcafno = c.cafno ");
			whereClause.append(" AND c.status = 6");
			
		StringBuilder orderByClause = new StringBuilder("");
			
			if(aaaUsageDTO.getDistrictId() != null && !aaaUsageDTO.getDistrictId().isEmpty())
				whereClause.append(" AND c.inst_district = '"+aaaUsageDTO.getDistrictId()+"'");
			if(aaaUsageDTO.getMandalId() != null && !aaaUsageDTO.getMandalId().isEmpty())
				whereClause.append(" AND c.inst_mandal = '"+aaaUsageDTO.getMandalId()+"'");
			if(aaaUsageDTO.getVillageId() != null && !aaaUsageDTO.getVillageId().isEmpty())
				whereClause.append(" AND c.inst_city_village = '"+aaaUsageDTO.getVillageId()+"'");
			if(aaaUsageDTO.getAadharNo() != null && !aaaUsageDTO.getAadharNo().isEmpty())
				whereClause.append(" AND cmst.aadharno = '"+aaaUsageDTO.getAadharNo()+"'");
			if(aaaUsageDTO.getAccountCafNo() != null && !aaaUsageDTO.getAccountCafNo().isEmpty())
				whereClause.append(" AND c.cafno = '"+aaaUsageDTO.getAccountCafNo()+"'");
			if(aaaUsageDTO.getMobileNO() != null && !aaaUsageDTO.getMobileNO().isEmpty())
				whereClause.append(" AND cmst.pocmob1 = '"+aaaUsageDTO.getMobileNO()+"'");
			if(aaaUsageDTO.getFromDate() != null && aaaUsageDTO.getToDate() != null 
					&& !aaaUsageDTO.getFromDate().isEmpty() && !aaaUsageDTO.getToDate().isEmpty())
				whereClause.append(" AND DATE_FORMAT(FROM_UNIXTIME(hu.etimestampstring),'%m/%d/%Y') between '"+aaaUsageDTO.getFromDate()+"' and '"+aaaUsageDTO.getToDate()+"'");
			if(aaaUsageDTO.getYear() != null && !aaaUsageDTO.getYear().isEmpty())
				whereClause.append(" AND hu.usageyyyy = '"+aaaUsageDTO.getYear()+"' AND hu.usagemm = '"+aaaUsageDTO.getMonth()+"'");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					whereClause.append(" and (cmst.custname like '%" + searchParameter + "%' or cmst.aadharno like '%"+ searchParameter + "%' or c.apsfluniqueid like '%" + searchParameter + "%' ");
					whereClause.append(" or cmst.pocmob1 like '%" + searchParameter + "%'  or c.cpeslno like '%"+ searchParameter + "%' or c.cpemacaddr like '%" + searchParameter + "%' ");
					whereClause.append(" or c.aaacode like '%" + searchParameter + "%'  or hu.sessiontime like '%"+ searchParameter + "%' or ROUND(hu.dwnldsize/1024/1024 , 3) like '%" + searchParameter + "%' ");
					whereClause.append(" or ROUND(hu.upldsize/1024/1024 , 3) like '%" + searchParameter + "%'  or DATE_FORMAT(FROM_UNIXTIME(hu.etimestampstring),'%m-%d-%Y') LIKE '%" + searchParameter + "%' ");
					whereClause.append(" or ROUND((hu.dwnldsize+hu.upldsize)/1024/1024 , 3) like '%" + searchParameter + "%'");
					whereClause.append(" or sub.substnname like '%" + searchParameter + "%' or c.agorahsisubscode like '%"+ searchParameter + "%' ) ");
				}
				orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
			}
			
			finalQuery = selectQuery.append(whereClause).append(orderByClause).toString();
			LOGGER.info("AAA Usage Report Query ======>   "+ finalQuery);
			
			
			try {
				LOGGER.info("START::getAAAUsageBySearch()");
				query = getEntityManager().createNativeQuery(finalQuery);
				aaaUsageDTO.setCount(query.getResultList().size());
				if (pageObject != null) {
					query.setFirstResult(pageObject.getMinSize());
					query.setMaxResults(pageObject.getMaxSize());
				}
				objList =  query.getResultList();
				
				for(Object[] obj : objList){
					AAAUsageBO aaaUsageBO = new AAAUsageBO();
					aaaUsageBO.setCustname(obj[0] == null ? "NA" : obj[0].toString());
					aaaUsageBO.setAadharno(obj[1] == null ? "NA" : obj[1].toString());
					aaaUsageBO.setApsfluniqueid(obj[2] == null ? "NA" : obj[2].toString());
					aaaUsageBO.setPocmob1(obj[3] == null ? "NA" : obj[3].toString());
					aaaUsageBO.setCpeslno(obj[4] == null ? "NA" : obj[4].toString());
					aaaUsageBO.setCpemacaddr(obj[5] == null ? "NA" : obj[5].toString());
					aaaUsageBO.setSubstnname(obj[6] == null ? "NA" : obj[6].toString());
					aaaUsageBO.setAgorahsisubscode(obj[7] == null ? "NA" : obj[7].toString());
					aaaUsageBO.setAaacode(obj[8] == null ? "NA" : obj[8].toString());
					aaaUsageBO.setSesStartTime(obj[9] == null ? "NA" : obj[9].toString());
					aaaUsageBO.setSesEndTime(obj[10] == null ? "NA" : obj[10].toString());
					aaaUsageBO.setSessiontime(obj[11] == null ? "NA" : obj[11].toString());
					aaaUsageBO.setDwnldsize(obj[12] == null ? "NA" : obj[12].toString());
					aaaUsageBO.setUpldsize(obj[13] == null ? "NA" : obj[13].toString());
					aaaUsageBO.setTotalSize(obj[14] == null ? "NA" : obj[14].toString());
					aaaUsageBO.setCafNo(obj[15] == null ? "NA" : obj[15].toString());
					aaaUsageBO.setAcctStatusType(obj[16] == null ? "NA" : obj[16].toString());
					aaaUsageList.add(aaaUsageBO);
				}
				aaaUsageDTO.setList(aaaUsageList);
			} catch (Exception e) {
				LOGGER.error("EXCEPTION::getAAAUsageBySearch() " + e);
				e.printStackTrace();
			}
			
		
		return aaaUsageDTO;
	}

	@SuppressWarnings("unchecked")
	public List<AAAUsageBO> aaaUsageReportEmail() {
		Query query = null;
		
		List<Object[]> objList = new ArrayList<>();
		List<AAAUsageBO> aaaUsageList = new ArrayList<>();

		StringBuilder selectQuery = new StringBuilder("");
		selectQuery.append("SELECT cmst.custname, cmst.aadharno, c.apsfluniqueid, cmst.pocmob1, c.cpeslno, c.cpemacaddr, sub.substnname, c.agorahsisubscode, c.aaacode, ");
		selectQuery.append(" DATE_FORMAT(FROM_UNIXTIME(hu.etimestampstring-hu.sessiontime),'%d-%m-%Y %H:%i:%s') starttime,");
		selectQuery.append(" DATE_FORMAT(FROM_UNIXTIME(hu.etimestampstring),'%d-%m-%Y %H:%i:%s') endtime, ");
		selectQuery.append(" hu.sessiontime, ");
		selectQuery.append(" ROUND(hu.dwnldsize/1024 , 2) dwnldsize, ");
		selectQuery.append(" ROUND(hu.upldsize/1024 , 2) upldsize,");
		selectQuery.append(" ROUND((hu.dwnldsize+hu.upldsize)/1024 , 2) totalsize ");
		selectQuery.append(" FROM cafs c , customermst cmst, substations sub, hsiusage hu ");
		selectQuery.append(" WHERE c.custtypelov = cmst.custtypelov ");
		selectQuery.append(" AND c.custid = cmst.custid");
		selectQuery.append(" AND hu.customerid = c.custid ");
		selectQuery.append(" AND sub.substnuid = c.pop_substnno ");
		selectQuery.append(" AND hu.accntStatusType IN ('Stop') AND c.status = 6 ");
		selectQuery.append(" AND DATE_FORMAT(FROM_UNIXTIME(hu.etimestampstring),'%m/%d/%Y') = DATE_FORMAT(SUBDATE(CURRENT_DATE, 1),'%m/%d/%Y') ");
		selectQuery.append(" AND hu.acctcafno  = c.cafno ");
		selectQuery.append(" ORDER BY custname ASC");
		
			
			
			
			LOGGER.info("AAA Usage Email Query ======>   "+ selectQuery);
			
			
			try {
				LOGGER.info("START::getAAAUsageBySearch()");
				query = getEntityManager().createNativeQuery(selectQuery.toString());
				objList =  query.getResultList();
				
				for(Object[] obj : objList){
					AAAUsageBO aaaUsageBO = new AAAUsageBO();
					aaaUsageBO.setCustname(obj[0] == null ? "NA" : obj[0].toString());
					aaaUsageBO.setAadharno(obj[1] == null ? "NA" : obj[1].toString());
					aaaUsageBO.setApsfluniqueid(obj[2] == null ? "NA" : obj[2].toString());
					aaaUsageBO.setPocmob1(obj[3] == null ? "NA" : obj[3].toString());
					aaaUsageBO.setCpeslno(obj[4] == null ? "NA" : obj[4].toString());
					aaaUsageBO.setCpemacaddr(obj[5] == null ? "NA" : obj[5].toString());
					aaaUsageBO.setSubstnname(obj[6] == null ? "NA" : obj[6].toString());
					aaaUsageBO.setAgorahsisubscode(obj[7] == null ? "NA" : obj[7].toString());
					aaaUsageBO.setAaacode(obj[8] == null ? "NA" : obj[8].toString());
					aaaUsageBO.setSesStartTime(obj[9] == null ? "NA" : obj[9].toString());
					aaaUsageBO.setSesEndTime(obj[10] == null ? "NA" : obj[10].toString());
					aaaUsageBO.setSessiontime(obj[11] == null ? "NA" : obj[11].toString());
					aaaUsageBO.setDwnldsize(obj[12] == null ? "NA" : obj[12].toString());
					aaaUsageBO.setUpldsize(obj[13] == null ? "NA" : obj[13].toString());
					aaaUsageBO.setTotalSize(obj[14] == null ? "NA" : obj[14].toString());
					aaaUsageBO.setCafNo(obj[15] == null ? "NA" : obj[15].toString());
					aaaUsageList.add(aaaUsageBO);
				}
			} catch (Exception e) {
				LOGGER.error("EXCEPTION::getAAAUsageBySearch() " + e);
				e.printStackTrace();
			}
			
		
		return aaaUsageList;
	}

	@SuppressWarnings("unchecked")
	public List<AaaUsageBYHoursDTO> getaaaUsageReportByHours(AaaUsageBYHoursDTO aaaUsageBYHoursDTO) {
	       LOGGER.info("START DAO IMPL::getaaaUsageReportByHours()");
           Query query = null;
           List<AaaUsageBYHoursDTO> aaaUsageBYHoursDTOs=new ArrayList<>();
           List<Object[]> daysUsageList=new ArrayList<>();
           StringBuilder prepareQuery = new StringBuilder("");
           prepareQuery.append(" SELECT day01usage, day02usage, day03usage, day04usage, day05usage, day06usage, day07usage, day08usage, day09usage, ");
           prepareQuery.append( "day10usage, day11usage, day12usage, day13usage, day14usage, day15usage, day16usage, day17usage, day18usage, day19usage,");
           prepareQuery.append( " day20usage, day21usage, day22usage, day23usage, day24usage, day25usage, day26usage, day27usage, day28usage,");
           prepareQuery.append(" day29usage, day30usage, day31usage, hc.acctcafno, hc.usagemm, hc.usageyyyy,   ");
           prepareQuery.append(" cmst.custname, c.apsfluniqueid, cmst.pocmob1, c.cpeslno, c.cpemacaddr, sub.substnname, c.agorahsisubscode, c.aaacode ");
           prepareQuery.append(" FROM hsicumusage hc, cafs c ,  substations sub, customermst cmst ");
           prepareQuery.append(" WHERE c.custtypelov = cmst.custtypelov ");
           prepareQuery.append(" AND c.custid = cmst.custid");
           prepareQuery.append(" AND hc.customerid = c.custid ");
           prepareQuery.append(" AND sub.substnuid = c.pop_substnno ");
           prepareQuery.append(" AND hc.acctcafno  = c.cafno ");
           if (aaaUsageBYHoursDTO.getMonthSelected()!= null && !aaaUsageBYHoursDTO.getMonthSelected().isEmpty()) 
        	   prepareQuery.append(" AND usagemm='"+aaaUsageBYHoursDTO.getMonthSelected()+"' ");
           if (aaaUsageBYHoursDTO.getYearSelected() != null && !aaaUsageBYHoursDTO.getYearSelected().isEmpty())
        	   prepareQuery.append(" AND usageyyyy = '"+aaaUsageBYHoursDTO.getYearSelected()+"' ");
           if (aaaUsageBYHoursDTO.getCafSelected() != null && !aaaUsageBYHoursDTO.getCafSelected().isEmpty())  
        	   prepareQuery.append(" AND acctcafno = '"+aaaUsageBYHoursDTO.getCafSelected()+"' ");
           
           query = getEntityManager().createNativeQuery(prepareQuery.toString());
           try{
           daysUsageList= query.getResultList();
           for(Object[] obj : daysUsageList){
        	   AaaUsageBYHoursDTO aaaUsageBYHoursDTO1= new  AaaUsageBYHoursDTO();
        	   aaaUsageBYHoursDTO1.setDay01usage(obj[0] == null ? "NA" : obj[0].toString());
        	   aaaUsageBYHoursDTO1.setDay02usage(obj[1] == null ? "NA" : obj[1].toString());
        	   aaaUsageBYHoursDTO1.setDay03usage(obj[2] == null ? "NA" : obj[2].toString());
        	   aaaUsageBYHoursDTO1.setDay04usage(obj[3] == null ? "NA" : obj[3].toString());
        	   aaaUsageBYHoursDTO1.setDay05usage(obj[4] == null ? "NA" : obj[4].toString());
        	   aaaUsageBYHoursDTO1.setDay06usage(obj[5] == null ? "NA" : obj[5].toString());
        	   aaaUsageBYHoursDTO1.setDay07usage(obj[6] == null ? "NA" : obj[6].toString());
        	   aaaUsageBYHoursDTO1.setDay08usage(obj[7] == null ? "NA" : obj[7].toString());
        	   aaaUsageBYHoursDTO1.setDay09usage(obj[8] == null ? "NA" : obj[8].toString());
        	   aaaUsageBYHoursDTO1.setDay10usage(obj[9] == null ? "NA" : obj[9].toString());
        	   aaaUsageBYHoursDTO1.setDay11usage(obj[10] == null ? "NA" : obj[10].toString());
        	   aaaUsageBYHoursDTO1.setDay12usage(obj[11] == null ? "NA" : obj[11].toString());
        	   aaaUsageBYHoursDTO1.setDay13usage(obj[12] == null ? "NA" : obj[12].toString());
        	   aaaUsageBYHoursDTO1.setDay14usage(obj[13] == null ? "NA" : obj[13].toString());
        	   aaaUsageBYHoursDTO1.setDay15usage(obj[14] == null ? "NA" : obj[14].toString());
        	   aaaUsageBYHoursDTO1.setDay16usage(obj[15] == null ? "NA" : obj[15].toString());
        	   aaaUsageBYHoursDTO1.setDay17usage(obj[16] == null ? "NA" : obj[16].toString());
        	   aaaUsageBYHoursDTO1.setDay18usage(obj[17] == null ? "NA" : obj[17].toString());
        	   aaaUsageBYHoursDTO1.setDay19usage(obj[18] == null ? "NA" : obj[18].toString());
        	   aaaUsageBYHoursDTO1.setDay20usage(obj[19] == null ? "NA" : obj[19].toString());
        	   aaaUsageBYHoursDTO1.setDay21usage(obj[20] == null ? "NA" : obj[20].toString());
        	   aaaUsageBYHoursDTO1.setDay22usage(obj[21] == null ? "NA" : obj[21].toString());
        	   aaaUsageBYHoursDTO1.setDay23usage(obj[22] == null ? "NA" : obj[22].toString());
        	   aaaUsageBYHoursDTO1.setDay24usage(obj[23] == null ? "NA" : obj[23].toString());
        	   aaaUsageBYHoursDTO1.setDay25usage(obj[24] == null ? "NA" : obj[24].toString());
        	   aaaUsageBYHoursDTO1.setDay26usage(obj[25] == null ? "NA" : obj[25].toString());
        	   aaaUsageBYHoursDTO1.setDay27usage(obj[26] == null ? "NA" : obj[26].toString());
        	   aaaUsageBYHoursDTO1.setDay28usage(obj[27] == null ? "NA" : obj[27].toString());
        	   aaaUsageBYHoursDTO1.setDay29usage(obj[28] == null ? "NA" : obj[28].toString()); 
        	   aaaUsageBYHoursDTO1.setDay30usage(obj[29] == null ? "NA" : obj[29].toString());
        	   aaaUsageBYHoursDTO1.setDay31usage(obj[30] == null ? "NA" : obj[30].toString());
        	   aaaUsageBYHoursDTO1.setCafSelected(obj[31] == null ? "NA" : obj[31].toString());
        	   aaaUsageBYHoursDTO1.setMonthSelected(obj[32] == null ? "NA" : obj[32].toString());
        	   aaaUsageBYHoursDTO1.setYearSelected(obj[33] == null ? "NA" : obj[33].toString());
        	   aaaUsageBYHoursDTO1.setCustomerName(obj[34] == null ? "NA" : obj[34].toString());
        	   aaaUsageBYHoursDTO1.setApsfltrackId(obj[35] == null ? "NA" : obj[35].toString());
        	   aaaUsageBYHoursDTO1.setMobileNo(obj[36] == null ? "NA" : obj[36].toString());
        	   aaaUsageBYHoursDTO1.setOnuSerialNo(obj[37] == null ? "NA" : obj[37].toString());
        	   aaaUsageBYHoursDTO1.setOnuMacAddress(obj[38] == null ? "NA" : obj[38].toString());
        	   aaaUsageBYHoursDTO1.setSubStationName(obj[39] == null ? "NA" : obj[39].toString());
        	   aaaUsageBYHoursDTO1.setAgoraCode(obj[40] == null ? "NA" : obj[40].toString());
        	   aaaUsageBYHoursDTO1.setAaaCode(obj[41] == null ? "NA" : obj[41].toString());
        	   aaaUsageBYHoursDTOs.add(aaaUsageBYHoursDTO1);
           }
           LOGGER.info("END DAO IMPL::getaaaUsageReportByHours()");
           }catch(Exception e)
           {
        	   LOGGER.error("EXCEPTION::getaaaUsageReportByHours() " + e);
        	   e.printStackTrace();
           }
          
		return aaaUsageBYHoursDTOs;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getActiveLandLines(String stateID, String districtId, String mandalId, String stdCodeNum,	String landLineNum) {
		 LOGGER.info("START DAO IMPL:: getActiveLandLines() ");
         Query query = null;
         List<Object[]> activeLandlneDetails=new ArrayList<>();
         StringBuilder prepareQuery = new StringBuilder("");
         prepareQuery.append(" SELECT DISTINCT  csp.cafno AS 'Caf Number', cu.custname AS 'Customer Name', ca.aadharno AS 'Aadhar Number', ca.custtypelov AS 'Customer Type' ,  csp.phoneno AS 'Landline Number', DATE_FORMAT(ca.actdate, '%d-%m-%Y')  AS 'Activation Date', ");
         prepareQuery.append( "c.prodcode AS 'Active Plan', CONCAT(ca.inst_addr1, ' , ',ca.inst_addr2,' , ',ca.inst_locality) AS 'Address'");
         prepareQuery.append( " from cafsrvcphonenos csp,  cafsrvcs c , cafs ca , customermst cu, villages v, products p WHERE ca.cafno = c.parentcafno AND ca.cafno = csp.parentcafno  AND ca.custid = cu.custid AND ca.status='6'");
         prepareQuery.append( " AND  ca.inst_district = v.districtuid AND ca.inst_city_village = v.villageslno AND ca.inst_mandal = v.mandalslno AND p.prodcode = c.prodcode AND p.prodtype = 'B'");
         if (districtId != "" && districtId != null )
        	 prepareQuery.append(" AND  ca.inst_district = '"+districtId+"' ");
         
         if (stdCodeNum != "" && stdCodeNum != null )
        	 prepareQuery.append(" AND v.stdcode = '"+stdCodeNum+"' ");
         
         if ( mandalId != "" && mandalId != null )  
        	 prepareQuery.append(" AND  ca.inst_mandal = '"+mandalId+"' ");
        
         if ( landLineNum != "" && landLineNum != null )  
        	 prepareQuery.append("  AND csp.phoneno = '"+landLineNum+"' ");
      
         prepareQuery.append(";");

         query = getEntityManager().createNativeQuery(prepareQuery.toString());
         try{
        	 activeLandlneDetails= query.getResultList();
				
				 LOGGER.info("END DAO IMPL::getActiveLandLines()");
         }catch(Exception e)
         {
      	   LOGGER.error("EXCEPTION::getActiveLandLines() " + e);
      	   e.printStackTrace();
         }
         
         return activeLandlneDetails;
		
	}

	@SuppressWarnings("unchecked")
	public List<HSICummSummaryMonthlyViewDTO> getTotalAAAUsagePerMonthDaywise(String month, String year) {
		List<HSICummSummaryMonthlyViewDTO> returnList = new ArrayList<>();
		StringBuilder builder = new StringBuilder("SELECT * FROM v_hsicumsmrydtl WHERE usageyyyymm ="+year+""+month+" AND CURRENT_DATE >= STR_TO_DATE((usageyyyymm * 100) + dayn, '%Y%m%d') ORDER BY dayn desc");
		 LOGGER.info("Hsi View Query =====>  "+builder.toString());
		try{
			
			returnList = getEntityManager().createNativeQuery(builder.toString(), HSICummSummaryMonthlyViewDTO.class).getResultList();
			
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return returnList;
	}

	@SuppressWarnings("unchecked")
	public HSICummSummaryMonthlyViewDTO getTotalAAAUsagePerMonth(String month, String year) {
		String prehr = DateUtill.getPreviousHour();
		String currDay = DateUtill.getCurentDay();
		
		if (prehr.equalsIgnoreCase("00")){
			prehr = "24";
			currDay = DateUtill.getPreviousDay();
		}
		 LOGGER.info("Hour  "+prehr);
		 LOGGER.info("Day  =====>  "+currDay);
		Integer hr = Integer.parseInt(prehr) + 48 ;
		
		if ((hr >= 48) && (hr < 60)){
			currDay = DateUtill.getPreviousDay(); 
		}
		//StringBuilder bulder = new StringBuilder("SELECT usageyyyymm, dnldsize , upldsize , actsubscount,SPLIT_STRING(day"+currDay+"usage,',',"+hr+") subscount FROM hsicumsmry WHERE usageyyyymm  ="+year+""+month);
		StringBuilder bulder = new StringBuilder("SELECT usageyyyymm, dnldsize , upldsize , actsubscount, day"+currDay+"usage subscount FROM hsicumsmry WHERE usageyyyymm  ="+year+""+month);
		 LOGGER.info("Query =====>  "+bulder.toString());
		HSICummSummaryMonthlyViewDTO hsiCummSummaryMonthlyViewDTO = null;
		Object[] obj = null;
		try{
			List<Object[]> list = getEntityManager().createNativeQuery(bulder.toString()).getResultList();
			if(list.size() > 0){
				obj = list.get(0);
				BigDecimal totDwn = new BigDecimal(obj[1] == null ? "0" : obj[1].toString()).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
				BigDecimal totUp = new BigDecimal(obj[2] == null ? "0" : obj[2].toString()).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
				
				hsiCummSummaryMonthlyViewDTO = new HSICummSummaryMonthlyViewDTO();
				hsiCummSummaryMonthlyViewDTO.setUsageYYYYMM(obj[0] == null ? "NA" : obj[0].toString());
				hsiCummSummaryMonthlyViewDTO.setTotalDwlSize(totDwn.toString());
				hsiCummSummaryMonthlyViewDTO.setTotalUplSize(totUp.toString());
				hsiCummSummaryMonthlyViewDTO.setTotalUsedSize(totDwn.add(totUp).toString());
				hsiCummSummaryMonthlyViewDTO.setSubsCount(obj[3] == null ? "NA" : obj[3].toString());
				
				//previous six Hr SubCount
				String str = obj[4].toString();
				String strArr[] = str.split(",");
				Integer preCust = 0;
				if((DateUtill.getCurrentYear() == year) && (DateUtill.getCurrentMonth() == month)){
				
				if ((hr >= 48) && (hr < 54)){
					for (int i = 60; i < 66; i++){
						preCust = preCust + Integer.parseInt(strArr[i]);
					}
				}
				
				if ((hr >= 54) && (hr < 60)){
					for (int i = 66; i < 72; i++){
						preCust = preCust + Integer.parseInt(strArr[i]);
					}					
				}
				
				if ((hr >= 60) && (hr < 66)){
					for (int i = 48; i < 54; i++){
						preCust = preCust + Integer.parseInt(strArr[i]);
					}					
				}
				
				if ((hr >= 66) && (hr < 72)){
					for (int i = 54; i < 60; i++){
						preCust = preCust + Integer.parseInt(strArr[i]);
					}					
				}

				}else{
					preCust=null;
					
				}
				
				hsiCummSummaryMonthlyViewDTO.setPreHrCount(preCust == null ? "NA" : Integer.toString(preCust));
				//hsiCummSummaryMonthlyViewDTO.setPreHrCount(obj[4] == null ? "NA" : obj[4].toString());

			}
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return hsiCummSummaryMonthlyViewDTO;
	}

	@SuppressWarnings("unchecked")
	public List<HSICummSummaryMonthlyCustViewDTO> getTotalAAAUsagePerMonthCustomerDayWise(String month, String year,
			String cafNo) {
		List<HSICummSummaryMonthlyCustViewDTO> returnList = new ArrayList<>();
		StringBuilder builder = new StringBuilder("SELECT custdistuid,customerid,acctcafno,usageyyyy,usagemm, dayn, ");
						builder.append("daydnldusage,dayupldusage,dlh01,dlh02,dlh03,dlh04,dlh05,dlh06,dlh07,dlh08,dlh09,dlh10");
						builder.append(",dlh11,dlh12,dlh13,dlh14,dlh15,dlh16,dlh17,dlh18,dlh19,dlh20,dlh21,dlh22,dlh23,dlh24");
						builder.append(",ulh01,ulh02,ulh03,ulh04,ulh05,ulh06,ulh07,ulh08,ulh09,ulh10,ulh11,ulh12,ulh13,ulh14");
						builder.append(",ulh15,ulh16,ulh17,ulh18,ulh19,ulh20,ulh21,ulh22,ulh23,ulh24 FROM (");
		String changeMonth = month;
		if(Integer.parseInt(month) < 10)
			changeMonth = month.substring(1);
		
		LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(changeMonth), 1);
		int days = date.lengthOfMonth();
		for (int i=1; i <= days ; i++ ){
			String day = i < 10 ? String.valueOf("0"+i) : String.valueOf(i);
			builder.append(this.getQuery(year,changeMonth,cafNo,day));
			if(i != days)
				builder.append(" UNION");
		}
		builder.append(" )a  ORDER BY dayn DESC");
		 LOGGER.info("Hsi View Query =====>  "+builder.toString());
		try{
			
			returnList = getEntityManager().createNativeQuery(builder.toString(), HSICummSummaryMonthlyCustViewDTO.class).getResultList();
			
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return returnList;
	}

	private String getQuery(String year, String changeMonth, String cafNo, String day) {
		StringBuilder builder = new StringBuilder();
		String dayOfMonth = Integer.parseInt(day) < 10 ? day.substring(1) : day ;
		builder.append(" SELECT custdistuid,customerid,acctcafno,usageyyyy,usagemm, "+dayOfMonth+" dayn");
		builder.append("  ,SPLIT_STRING(day"+day+"usage,',', 1)+SPLIT_STRING(day"+day+"usage,',', 2)+SPLIT_STRING(day"+day+"usage,',', 3)+SPLIT_STRING(day"+day+"usage,',' ,4)+SPLIT_STRING(day"+day+"usage,',' ,5)+SPLIT_STRING(day"+day+"usage,',' ,6)+SPLIT_STRING(day"+day+"usage,',' ,7)+SPLIT_STRING(day"+day+"usage,',' ,8)+SPLIT_STRING(day"+day+"usage,',' ,9)+SPLIT_STRING(day"+day+"usage,',',10)+SPLIT_STRING(day"+day+"usage,',',11)+SPLIT_STRING(day"+day+"usage,',',12)");
		builder.append(" +SPLIT_STRING(day"+day+"usage,',',13)+SPLIT_STRING(day"+day+"usage,',',14)+SPLIT_STRING(day"+day+"usage,',',15)+SPLIT_STRING(day"+day+"usage,',',16)+SPLIT_STRING(day"+day+"usage,',',17)+SPLIT_STRING(day"+day+"usage,',',18)+SPLIT_STRING(day"+day+"usage,',',19)+SPLIT_STRING(day"+day+"usage,',',20)+SPLIT_STRING(day"+day+"usage,',',21)+SPLIT_STRING(day"+day+"usage,',',22)+SPLIT_STRING(day"+day+"usage,',',23)+SPLIT_STRING(day"+day+"usage,',',24) daydnldusage");
		builder.append("  ,SPLIT_STRING(day"+day+"usage,',',25)+SPLIT_STRING(day"+day+"usage,',',26)+SPLIT_STRING(day"+day+"usage,',',27)+SPLIT_STRING(day"+day+"usage,',',28)+SPLIT_STRING(day"+day+"usage,',',29)+SPLIT_STRING(day"+day+"usage,',',30)+SPLIT_STRING(day"+day+"usage,',',31)+SPLIT_STRING(day"+day+"usage,',',32)+SPLIT_STRING(day"+day+"usage,',',33)+SPLIT_STRING(day"+day+"usage,',',34)+SPLIT_STRING(day"+day+"usage,',',35)+SPLIT_STRING(day"+day+"usage,',',36)");
		builder.append(" +SPLIT_STRING(day"+day+"usage,',',37)+SPLIT_STRING(day"+day+"usage,',',38)+SPLIT_STRING(day"+day+"usage,',',39)+SPLIT_STRING(day"+day+"usage,',',40)+SPLIT_STRING(day"+day+"usage,',',41)+SPLIT_STRING(day"+day+"usage,',',42)+SPLIT_STRING(day"+day+"usage,',',43)+SPLIT_STRING(day"+day+"usage,',',44)+SPLIT_STRING(day"+day+"usage,',',45)+SPLIT_STRING(day"+day+"usage,',',46)+SPLIT_STRING(day"+day+"usage,',',47)+SPLIT_STRING(day"+day+"usage,',',48) dayupldusage");
		builder.append("  ,SPLIT_STRING(day"+day+"usage,',', 1) dlh"+day+",SPLIT_STRING(day"+day+"usage,',', 2) dlh02,SPLIT_STRING(day"+day+"usage,',', 3) dlh03,SPLIT_STRING(day"+day+"usage,',' ,4) dlh04,SPLIT_STRING(day"+day+"usage,',' ,5) dlh05,SPLIT_STRING(day"+day+"usage,',' ,6) dlh06,SPLIT_STRING(day"+day+"usage,',' ,7) dlh07,SPLIT_STRING(day"+day+"usage,',' ,8) dlh08,SPLIT_STRING(day"+day+"usage,',' ,9) dlh09,SPLIT_STRING(day"+day+"usage,',',10) dlh10,SPLIT_STRING(day"+day+"usage,',',11) dlh11,SPLIT_STRING(day"+day+"usage,',',12) dlh12");
		builder.append("  ,SPLIT_STRING(day"+day+"usage,',',13) dlh13,SPLIT_STRING(day"+day+"usage,',',14) dlh14,SPLIT_STRING(day"+day+"usage,',',15) dlh15,SPLIT_STRING(day"+day+"usage,',',16) dlh16,SPLIT_STRING(day"+day+"usage,',',17) dlh17,SPLIT_STRING(day"+day+"usage,',',18) dlh18,SPLIT_STRING(day"+day+"usage,',',19) dlh19,SPLIT_STRING(day"+day+"usage,',',20) dlh20,SPLIT_STRING(day"+day+"usage,',',21) dlh21,SPLIT_STRING(day"+day+"usage,',',22) dlh22,SPLIT_STRING(day"+day+"usage,',',23) dlh23,SPLIT_STRING(day"+day+"usage,',',24) dlh24");
		builder.append(" ,SPLIT_STRING(day"+day+"usage,',',25) ulh"+day+",SPLIT_STRING(day"+day+"usage,',',26) ulh02,SPLIT_STRING(day"+day+"usage,',',27) ulh03,SPLIT_STRING(day"+day+"usage,',',28) ulh04,SPLIT_STRING(day"+day+"usage,',',29) ulh05,SPLIT_STRING(day"+day+"usage,',',30) ulh06,SPLIT_STRING(day"+day+"usage,',',31) ulh07,SPLIT_STRING(day"+day+"usage,',',32) ulh08,SPLIT_STRING(day"+day+"usage,',',33) ulh09,SPLIT_STRING(day"+day+"usage,',',34) ulh10,SPLIT_STRING(day"+day+"usage,',',35) ulh11,SPLIT_STRING(day"+day+"usage,',',36) ulh12");
		builder.append(" ,SPLIT_STRING(day"+day+"usage,',',37) ulh13,SPLIT_STRING(day"+day+"usage,',',38) ulh14,SPLIT_STRING(day"+day+"usage,',',39) ulh15,SPLIT_STRING(day"+day+"usage,',',40) ulh16,SPLIT_STRING(day"+day+"usage,',',41) ulh17,SPLIT_STRING(day"+day+"usage,',',42) ulh18,SPLIT_STRING(day"+day+"usage,',',43) ulh19,SPLIT_STRING(day"+day+"usage,',',44) ulh20,SPLIT_STRING(day"+day+"usage,',',45) ulh21,SPLIT_STRING(day"+day+"usage,',',46) ulh22,SPLIT_STRING(day"+day+"usage,',',47) ulh23,SPLIT_STRING(day"+day+"usage,',',48) ulh24");
		builder.append(" FROM hsicumusage WHERE usagemm = '"+changeMonth+"' AND usageyyyy = '"+year+"' AND acctcafno = '"+cafNo+"' ");
		builder.append(" AND CURRENT_DATE >= STR_TO_DATE((usageyyyy * 10000)+(usagemm * 100) + "+dayOfMonth+", '%Y%m%d')  ");
		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	public HSICummSummaryMonthlyCustViewDTO getTotalAAAUsagePerMonthCustomer(String month, String year, String cafNo) {
		String changeMonth = month;
		if(Integer.parseInt(month) < 10)
			changeMonth = month.substring(1);
		
		StringBuilder bulder = new StringBuilder("SELECT hc.usageyyyy,hc.usagemm,hc.dnldsize,hc.upldsize, hc.basednldsize+hc.baseupldsize planSize, cus.custname ");
		bulder.append(" FROM hsicumusage hc, cafs c, customermst cus ");
		bulder.append(" WHERE usagemm = '"+changeMonth+"' AND usageyyyy = '"+year+"' AND acctcafno = '"+cafNo+"' AND c.cafno = hc.acctcafno AND c.custid = cus.custid ");
		HSICummSummaryMonthlyCustViewDTO hsiCummSummaryMonthlyViewDTO = null;
		Object[] obj = null;
		try{
			List<Object[]> list = getEntityManager().createNativeQuery(bulder.toString()).getResultList();
			if(list.size() > 0){
				obj = list.get(0);
				
				BigDecimal totDwn = new BigDecimal(obj[2] == null ? "0" : obj[2].toString()).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
				BigDecimal totUp = new BigDecimal(obj[3] == null ? "0" : obj[3].toString()).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
				BigDecimal planUsage = new BigDecimal(obj[4] == null ? "0" : obj[4].toString()).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).divide(new BigDecimal("1024")).setScale(3, RoundingMode.CEILING);
				
				hsiCummSummaryMonthlyViewDTO = new HSICummSummaryMonthlyCustViewDTO();
				hsiCummSummaryMonthlyViewDTO.setUsageYYYY(obj[0] == null ? "NA" : obj[0].toString());
				hsiCummSummaryMonthlyViewDTO.setUsageMM(obj[1] == null ? "NA" : obj[1].toString());
				hsiCummSummaryMonthlyViewDTO.setTotalDwlSize(totDwn.toEngineeringString());
				hsiCummSummaryMonthlyViewDTO.setTotalUplSize(totUp.toEngineeringString());
				hsiCummSummaryMonthlyViewDTO.setTotalUsedSize(totDwn.add(totUp).toString());
				hsiCummSummaryMonthlyViewDTO.setCustName(obj[5] == null ? "NA" : obj[5].toString());
				hsiCummSummaryMonthlyViewDTO.setPlanUsage(planUsage);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return hsiCummSummaryMonthlyViewDTO;
	}
 
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgeingReport(String date,PageObject pageObject) {
		List<Object[]> ageingList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String finalQuery = null;
		String searchParameter = "";
		Query query = null;
		
		
		long count = 0l;
		Query countQuery = null;
		try{
			/*builder.append("SELECT DISTINCT(cfs.parentcafno),cf.custcode AadharNo,cfs.stbmacaddr,cfs.nwsubscode SubsCode,cf.custid, cs.srvccode package, cs.actdate, ");
			builder.append(" 'Active' AS STATUS, DATEDIFF('"+date+"', cs.actdate)+1 Ageing ");
			builder.append(" FROM cafstbs cfs,cafsrvcs cs,srvcs s,cafs cf WHERE cfs.parentcafno = cs.parentcafno AND cfs.stbcafno = cs.stbcafno ");
			builder.append(" AND s.srvccode = cs.srvccode AND s.coresrvccode = 'IPTV' AND cf.cafno = cfs.parentcafno and cf.cafno = cs.parentcafno ");
			builder.append(" AND cs.status = "+CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus()+" AND CURRENT_DATE()  BETWEEN s.effectivefrom AND s.effectiveto AND cs.actdate <= '"+date+"' ");
			*/
			builder.append("(select DISTINCT(cf.cafno) caf,cf.aadharno,stbmacaddr,stbs.nwsubscode,cf.custid,cs.srvccode,cf.actdate,'Active',IFNULL(Datediff('"+date+"', cf.actdate) + 1 ,0)Ageing"); 
			builder.append("	from cafs cf left join cafstbs stbs on cf.cafno=stbs.parentcafno  left join cafsrvcs cs  on cf.cafno=cs.parentcafno and cs.parentcafno=stbs.parentcafno and cs.stbcafno=stbs.stbcafno");
			builder.append("	inner join srvcs s      on  s.srvccode=cs.srvccode where cf.status=6 and (cf.actdate<='"+date+"') and CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto  and cs.status=6)");
			builder.append(" union (select Distinct(cf.cafno) caf,cf.aadharno,null,null,cf.custid,null,cf.actdate,'Active',null from cafs cf  where cf.cafno not in");
			builder.append(" (select DISTINCT(cf.cafno) caf from cafs cf right join cafstbs stbs on cf.cafno=stbs.parentcafno  inner join cafsrvcs cs on cf.cafno=cs.parentcafno and cs.parentcafno=stbs.parentcafno and cs.stbcafno=stbs.stbcafno");
			builder.append("	inner join srvcs s      on  s.srvccode=cs.srvccode where cf.status=6 and (cf.actdate<='"+date+"') and CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto  ) and cf.status=6)");
			
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					whereClause.append("and ( cfs.parentcafno like '%" + searchParameter + "%' or cf.custcode like '%"+ searchParameter + "%' ");
					whereClause.append("or cfs.stbmacaddr like '%" + searchParameter + "%' or cfs.nwsubscode like '%"+ searchParameter + "%' ");
					whereClause.append("or cs.srvccode like '%" + searchParameter + "%' or cs.actdate like '%"+ searchParameter + "%') ");
					
					//orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
				}
			}	
			finalQuery = builder.append(whereClause).append(orderByClause).toString();
			
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(finalQuery.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			ageingList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return ageingList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSrvcWiseSubsReport(String date,PageObject pageObject) {
		List<Object[]> subsList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					whereClause.append("and ( a.srvccode like '%" + searchParameter + "%' ) ");
					orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
				}
			}	
			
			builder = new StringBuilder("SELECT DISTINCT (a.srvccode), COUNT(1) Total_subs FROM cafsrvcs a, srvcs b ");
			builder.append(" WHERE a.srvccode=b.srvccode AND b.coresrvccode='IPTV' and a.actdate <= '" + date+ "' AND current_date() BETWEEN b.effectivefrom AND b.effectiveto ");
			builder.append(" and a.status = "+CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus()+" "+whereClause+" GROUP BY (a.srvccode) "+orderByClause+" ");
			
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			subsList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return subsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPackWiseSubsReport(String srvc,String activationDate,PageObject pageObject) {
		List<Object[]> subsList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					whereClause.append("and ( cf.aadharno like '%" + searchParameter + "%'  or cfs.stbmacaddr like '%"+ searchParameter + "%' ");
					whereClause.append("or cfs.stbslno like '%" + searchParameter + "%'  or cs.srvccode like '%"+ searchParameter + "%' ) ");
					orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
				}
			}	
			
			builder = new StringBuilder("SELECT cf.aadharno, cfs.stbmacaddr,cfs.stbslno, cs.srvccode ");
			builder.append(" FROM cafsrvcs cs, srvcs s, cafs cf,cafstbs cfs WHERE cs.srvccode = s.srvccode ");
			builder.append(" AND s.coresrvccode='IPTV' AND cs.actdate <= '"+activationDate+"' AND cs.srvccode = '"+srvc+"' ");
			builder.append(" AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto AND cs.status = "+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() +" ");
			builder.append(" AND cfs.parentcafno = cs.cafno AND cfs.parentcafno = cf.cafno and cs.parentcafno = cf.cafno AND cfs.stbcafno = cs.stbcafno "+whereClause+" "+orderByClause+" ");
			
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			subsList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return subsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubsDtlsReport(String fromDate,String toDate,PageObject pageObject) {
		List<Object[]> subsList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					
					whereClause.append("and ( cfs.stbmacaddr like '%" + searchParameter + "%' or cfs.parentcafno like '%"+ searchParameter + "%' ");
					whereClause.append("or  cs.prodcode like '%" + searchParameter + "%' or cs.deactdate like '%"+ searchParameter + "%' ");
					whereClause.append("or  cs.actdate like '%" + searchParameter + "%' or cfs.parentcafno like '%"+ searchParameter + "%' ");
					whereClause.append("or  cs.suspdate like '%" + searchParameter + "%' or cs.resumedate like '%"+ searchParameter + "%' ");
					whereClause.append("or  s.srvcname like '%" + searchParameter + "%' or s.featurecodes like '%"+ searchParameter + "%' ");
					whereClause.append("or  cs.parentcafno like '%" + searchParameter + "%' or cf.custid like '%"+ searchParameter + "%' ");
					whereClause.append("or  cf.aadharno like '%" + searchParameter + "%' or cf.cpemacaddr like '%"+ searchParameter + "%' ");
					whereClause.append("or  cf.inst_addr1 like '%" + searchParameter + "%' or cf.inst_addr2 like '%"+ searchParameter + "%' ");
					whereClause.append("or  cf.inst_area like '%" + searchParameter + "%' or v.villagename like '%"+ searchParameter + "%' ");
					whereClause.append("or  cf.inst_locality like '%" + searchParameter + "%' or m.mandalname like '%"+ searchParameter + "%' ");
					whereClause.append("or  d.districtname like '%" + searchParameter + "%' or cf.inst_pin like '%"+ searchParameter + "%' ");
					whereClause.append("or  cf.lattitude like '%" + searchParameter + "%' or cf.longitude like '%"+ searchParameter + "%') ");
					
					orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
				}
			}	
			
			builder = new StringBuilder("SELECT cfs.stbmacaddr ,cfs.parentcafno cafno,cs.prodcode package,cfs.nwsubscode Corpusnetworkid, ");
			builder.append(" DATE_FORMAT(IFNULL(cs.actdate,''),'%d-%m-%Y') ActivationDate,  DATE_FORMAT(IFNULL(cs.deactdate,''),'%d-%m-%Y') DeactivationDate, DATE_FORMAT(IFNULL(cs.suspdate,''),'%d-%m-%Y') Suspendeddate,  ");
			builder.append(" DATE_FORMAT(IFNULL(cs.resumedate,''),'%d-%m-%y') ResumeDate,s.srvcname IPTVAddlService,s.featurecodes Channellist,cs.parentcafno,cf.custid,cf.aadharno, ");
			builder.append(" cf.cpemacaddr,cf.inst_addr1,cf.inst_addr2,cf.inst_area, v.villagename,cf.inst_locality, ");
			builder.append(" m.mandalname,d.districtname,cf.inst_pin,cf.lattitude,cf.longitude, CASE WHEN cs.status = "+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + " ");
			builder.append(" then 'Active' when cs.status = " + CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus()+ " then 'Suspend' ");
			builder.append(" when cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus()+ " then 'Deactive'  when cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+ " then 'Black Listed' end status, ");
			builder.append(" CASE WHEN cs.status=" + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+ " THEN (SELECT DATE_FORMAT(IFNULL(bl.approvedon,''),'%d-%m-%Y') FROM blackliststb bl WHERE cfs.stbcafno = bl.stbcafno and bl.status = 1) ELSE '' END BlackListDate ");
			builder.append(" FROM cafstbs cfs,cafsrvcs cs,cafs cf,srvcs s,districts d, mandals m,villages v ");
			builder.append(" WHERE cfs.stbcafno = cs.stbcafno AND cfs.parentcafno = cs.parentcafno AND cf.cafno = cfs.parentcafno AND s.srvccode = cs.srvccode ");
			builder.append(" AND s.coresrvccode = 'IPTV' AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' AND cf.inst_mandal = m.mandalslno ");
			builder.append(" AND cf.inst_district = m.districtuid AND cf.inst_district = d.districtuid and cf.cafno = cs.parentcafno ");
			builder.append(" AND cf.inst_district = v.districtuid AND cf.inst_mandal = v.mandalslno AND cf.inst_city_village = v.villageslno ");
			builder.append(" AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto "+whereClause+"  "+orderByClause+" ");	
			
			
			
			

			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			subsList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return subsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPackWiseChnls(String date,PageObject pageObject) {
		List<Object[]> chnlList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					
					whereClause.append("and ( b.srvccode like '%" + searchParameter + "%' or b. srvcname like '%"+ searchParameter + "%' ");
					whereClause.append("or  c.featurecode like '%" + searchParameter + "%' or c.featurename like '%"+ searchParameter + "%' ) ");
				}
			}	
			
			builder = new StringBuilder("SELECT b.srvccode,b. srvcname, c.featurecode, c.featurename FROM srvcs b, srvcfeatures c ");
			builder.append(" WHERE b.coresrvccode='IPTV' AND c.coresrvccode='IPTV' AND '"+date+"' BETWEEN b.effectivefrom  AND b.effectiveto ");
			builder.append(" AND INSTR(CONCAT(',',b.featurecodes,','), CONCAT(',',c.featurecode,',')) > 0 "+whereClause+" ORDER BY 1,3  ");
			
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			chnlList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return chnlList;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getTransactionLog(String fromDate, String toDate, PageObject pageObject) {

		List<Object[]> subsList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					
					whereClause.append("and ( cf.cafno like '%" + searchParameter + "%' or cf.aadharno like '%"+ searchParameter + "%' ");
					whereClause.append("or  cfs.stbmacaddr like '%" + searchParameter + "%' or cpeslno like '%"+ searchParameter + "%' ");
					whereClause.append("or  cfs.stbslno like '%" + searchParameter + "%' or srvccodeaddl like '%"+ searchParameter + "%' ");
					whereClause.append("or  executed_date like '%" + searchParameter + "%' ) ");
					
					orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
				}
			}	
			
			builder = new StringBuilder("SELECT cf.cafno, cf.aadharno, cfs.stbmacaddr, cpeslno onuslno,cfs.stbslno stbslno, srvccodeaddl IPTVpackage, ");
			builder.append(" (CASE WHEN pv.url LIKE '%register%' THEN 'Corpus Subscriber Activation' WHEN pv.url LIKE '%addservicepack%' THEN 'Add Package' ");
			builder.append(" WHEN pv.url LIKE '%disconnect%' THEN 'Suspend Package' WHEN pv.url LIKE '%renew%' THEN 'Renew Package' ELSE 'Agora Subscriber Activation' ");
			builder.append(" END) 'Transaction', executed_date 'Transaction Date', CASE WHEN pv.status=1 THEN 'Transaction Successfully Completed' ");
			builder.append(" WHEN pv.status IN (0,3,4,5) THEN 'Transaction In Progress' WHEN pv.status IN (7,8,9) THEN 'Transaction Failed' END STATUS ");
			builder.append(" FROM provjsons pv, provrequests pr, cafs cf,cafstbs cfs WHERE pv.servicetype = 'IPTV' AND pv.requestid = pr.requestid ");
			builder.append(" AND executed_date BETWEEN '"+fromDate+"' AND DATE_ADD('"+toDate+"',INTERVAL 1 DAY) ");
			builder.append(" AND pr.acctcafno = cf.cafno AND cfs.stbmacaddr IS NOT NULL AND cfs.parentcafno = cf.cafno  "+whereClause+"  "+orderByClause+"");
			
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			subsList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return subsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCpeOrderReport(String fromDate, String toDate, PageObject pageObject) {

		List<Object[]> subsList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					
					whereClause.append("and ( t1.tenantcode like '%" + searchParameter + "%' or t.tenantname like '%"+ searchParameter + "%' ");
					whereClause.append("or  d.districtname like '%" + searchParameter + "%' or m.mandalname like '%"+ searchParameter + "%' ");
					whereClause.append("or  cpe.cpetypelov like '%" + searchParameter + "%' or cpe.cpe_model like '%"+ searchParameter + "%' ");
					whereClause.append("or  t1.dmnddate like '%" + searchParameter + "%' or  cp.purchasecost like '%" + searchParameter + "%' ");
					whereClause.append("or  t1.dmndqty like '%" + searchParameter + "%' or  t1.dmndprice like '%" + searchParameter + "%' ) ");
				}
			}	
			builder = new StringBuilder("SELECT  t1.tenantcode,t.tenantname,d.districtname,m.mandalname, cpe.cpetypelov,cpe.cpe_model,DATE_FORMAT(t1.dmnddate,'%d-%m-%Y') orderDate,cp.purchasecost, t1.dmndqty,t1.dmndprice ");
			builder.append(" FROM tenantcpedmnd t1, tenants t, cpecharges cp, cpe_profilemaster cpe,districts d, mandals m WHERE t.tenantcode=t1.tenantcode  AND cp.profile_id = t1.profile_id  ");
			builder.append(" AND t1.profile_id=cpe.profile_id AND portal_districtid = d.districtuid AND portal_mandalid = m.mandalslno AND m.districtuid = d.districtuid and t1.dmnddate BETWEEN '"+ fromDate + "' AND '" + toDate + "' "+whereClause+" order by t1.dmnddate ");
			
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			subsList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return subsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalNoOfSubs(String fromDate, String toDate, PageObject pageObject) {

		List<Object[]> subsList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					
					whereClause.append("and ( cs.status like '%" + searchParameter + "%' )");
					
					orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
				}
			}	
			builder = new StringBuilder("SELECT cs.status AS statusvalue, CASE WHEN cs.status = "+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' WHEN cs.status = "+ CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus() + "  THEN 'Suspended' ");
			builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus()+ " THEN 'Deactivated' WHEN cs.status = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+ " THEN 'Blacklisted' END STATUS, ");
			builder.append(" COUNT(DISTINCT cf.cafno) Total_Subscribers FROM cafsrvcs cs, srvcs s, cafs cf WHERE cs.srvccode = s.srvccode AND s.coresrvccode='IPTV' ");
			builder.append(" AND cs.parentcafno = cf.cafno AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto  AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
			builder.append(" GROUP BY cs.status, CASE WHEN cs.status = "+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + "  THEN 'Active' WHEN cs.status = "+ CafEnumCodes.CAF_SUSPENDED_STATUS.getStatus() + "  THEN 'Suspended' ");
			builder.append(" WHEN cs.status = " + CafEnumCodes.CAF_DEACTIVATION_STATUS.getStatus()+ " THEN 'Deactivated' WHEN cs.status = "+CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+" THEN 'Blacklisted' END "+whereClause+"  "+orderByClause+" ");
			
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			subsList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return subsList;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getStatusWiseSubs(String fromDate, String toDate,String status, PageObject pageObject,boolean tilldate){

		List<Object[]> subsList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					
					whereClause.append("and ( cfs.nwsubscode like '%" + searchParameter + "%' or cs.actdate like '%" + searchParameter + "%' ");
					whereClause.append("or  cs.suspdate like '%" + searchParameter + "%' or cs.deactdate like '%" + searchParameter + "%' ");
					whereClause.append(" or cfs.stbmacaddr like '%" + searchParameter + "%')");
					
					
				}
				if (tilldate!=true){
		        	whereClause.append("AND cs.actdate BETWEEN '" + fromDate + "' AND '" + toDate + "'");
		        }
				//orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
			}	
			/*builder = new StringBuilder("SELECT DISTINCT (cfs.parentcafno) ,cfs.nwsubscode,DATE_FORMAT(IFNULL(cs.actdate,''),'%d-%m-%Y') ActivationDate,DATE_FORMAT(IFNULL(cs.resumedate,''),'%d-%m-%Y') ResumeDate, cfs.stbmacaddr, ");
			builder.append(" DATE_FORMAT(IFNULL(cs.suspdate,''),'%d-%m-%Y') SuspendedDate, DATE_FORMAT(IFNULL(cs.deactdate,''),'%d-%m-%Y') DeactivationDate, ");
			builder.append(" CASE WHEN "+status+" = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+ " THEN (SELECT DATE_FORMAT(IFNULL(bl.approvedon,''),'%d-%m-%Y') FROM blackliststb bl WHERE cfs.stbcafno = bl.stbcafno and bl.status = 1) ELSE '' END BlackListDate ");
			builder.append(" FROM cafsrvcs cs , srvcs s , cafs cf,cafstbs cfs WHERE cs.srvccode=s.srvccode AND s.coresrvccode='IPTV' ");
			builder.append(" AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto  ");
			builder.append(" AND cfs.parentcafno = cs.parentcafno  AND cfs.parentcafno = cf.cafno AND cs.status = "+status+" AND cfs.stbcafno = cs.stbcafno ");
            builder.append(" AND cs.parentcafno = cf.cafno AND cfs.stbcafno = cs.stbcafno "+whereClause+"  "+orderByClause+" ");
			*/
			
			builder = new StringBuilder("SELECT DISTINCT (cf.cafno), cfs.nwsubscode,DATE_FORMAT(IFNULL(cf.actdate, ''), '%d-%m-%Y') ActivationDate, DATE_FORMAT(IFNULL(cs.resumedate, ''), '%d-%m-%Y') ResumeDate,");
			builder.append("cfs.stbmacaddr,DATE_FORMAT(IFNULL(cs.suspdate, ''), '%d-%m-%Y') SuspendedDate,DATE_FORMAT(IFNULL(cf.deactdate, ''), '%d-%m-%Y') DeactivationDate, ");
			builder.append(" CASE WHEN "+status+" = " + CafEnumCodes.CAF_BLACKLISTED_STATUS.getStatus()+ " THEN (SELECT DATE_FORMAT(IFNULL(bl.approvedon,''),'%d-%m-%Y') FROM blackliststb bl WHERE cfs.stbcafno = bl.stbcafno and bl.status = 1) ELSE '' END BlackListDate ");		
			builder.append(" FROM cafs cf LEFT JOIN cafsrvcs cs ON cf.cafno = cs.PARENTCAFNO LEFT JOIN cafstbs cfs ON cs.cafno = cfs.parentcafno LEFT JOIN srvcs s ON cs.srvccode = s.srvccode");
			builder.append(" WHERE cf.status = 6 AND cf.status = cs.status AND CURRENT_DATE() BETWEEN s.effectivefrom AND s.effectiveto" +whereClause+"  order by cfs.stbmacaddr DESC " );
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			subsList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return subsList;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getChnlsOnAlaCarteBasis(String fromDate, String toDate, PageObject pageObject) {
		List<Object[]> subsList = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		Query countQuery = null;
		try{
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
				if(searchParameter != null && !searchParameter.isEmpty()){
					
					whereClause.append("and ( cfs.parentcafno like '%" + searchParameter + "%' or cs.featurecodes like '%" + searchParameter + "%' ");
					whereClause.append("or  sf.featurename like '%" + searchParameter + "%' or cf.custcode like '%" + searchParameter + "%' ");
					whereClause.append(" or cfs.nwsubscode like '%" + searchParameter + "%' or cfs.stbslno like '%" + searchParameter + "%' ");
					whereClause.append(" or cfs.stbmacaddr like '%" + searchParameter + "%' )");
				}
				orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
			}	
			
			builder = new StringBuilder("SELECT cfs.parentcafno, GROUP_CONCAT(cs.featurecodes) AS FeatureCodes,sf.featurename,cf.custcode,cfs.nwsubscode,cfs.stbslno,cfs.stbmacaddr ");
			builder.append(" FROM cafsrvcs cs,srvcs s,srvcfeatures sf,cafs cf,cafstbs cfs  WHERE cs.status="+ CafEnumCodes.CAF_ACTIVATION_STATUS.getStatus() + " AND s.coresrvccode='IPTV' ");
			builder.append(" AND cs.srvccode=s.srvccode AND cs.actdate BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
			builder.append(" AND SPLIT_STRING(cs.featurecodes,',',1)=REPLACE(cs.featurecodes,',','') AND s.featurecodes=sf.featurecode AND cf.cafno=cfs.parentcafno ");
			builder.append(" AND cfs.parentcafno = cs.parentcafno AND cfs.stbcafno = cs.stbcafno "+whereClause+"  ");
			builder.append(" GROUP BY cfs.parentcafno,sf.featurename,cf.custcode,cfs.nwsubscode,cfs.stbslno,cfs.stbmacaddr,cs.featurecodes "+orderByClause+" ");
			
			countQuery = getEntityManager().createNativeQuery(builder.toString());
			count = countQuery.getResultList().size();
			pageObject.setTotalDisplayCount(String.valueOf(count));
			
			query = getEntityManager().createNativeQuery(builder.toString());
			query.setFirstResult(pageObject.getMinSize());
			query.setMaxResults(pageObject.getMaxSize());
			subsList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			builder = null;
			countQuery = null;
		}
		
		return subsList;
	}	
	
	@SuppressWarnings("unchecked")
    public List<HSICummSummaryMonthlyCustViewDTO> getTotalAAAUsagePerMonthDaywisePerCust(String month, String year,String cafNo,String day) {
        List<HSICummSummaryMonthlyCustViewDTO> returnList = new ArrayList<>();
        StringBuilder builder = new StringBuilder("SELECT * FROM v_hsicustsmrydtl WHERE usageyyyy ="+year+" AND usagemm ="+month+" AND acctcafno = "+cafNo+" ");
        if(!day.isEmpty() && !day.equalsIgnoreCase("null"))
        {
        	builder.append(" AND dayn = "+day);
        }
        builder.append(" ORDER BY dayn desc");

        LOGGER.info("Hsi View Query =====> "+builder.toString());
        try{

            returnList = getEntityManager().createNativeQuery(builder.toString(), HSICummSummaryMonthlyCustViewDTO.class).getResultList();

        }catch(Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return returnList;
    }
	
	public List<Object[]> getCpeDetails(String fromDate, String toDate, String cpeType,String status,String distUid) {
		List<Object[]> list = new ArrayList<>();
		Query query = null;
		String whereClause = "";
		StringBuilder builder =null;
		
		
		try {
			if(fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()){
				whereClause = whereClause + " and cps.batchdate BETWEEN '"+fromDate+"' and '"+toDate+"' ";
				
			}
			 	
				
			if(cpeType != null && !cpeType.isEmpty()){
				whereClause = whereClause + "  AND cpm.cpetypelov like'%"+cpeType+"%' ";
			}
			
			
			if(status != null){
				if (status.equals("notactive"))
					whereClause = whereClause + " AND (cps.status=2 OR cps.status = 3) ";
				else if (status.equals("active"))
					whereClause = whereClause + " AND (cps.status=4) ";
				else if (status.equals("defective"))
					whereClause = whereClause + " AND (cps.status=99) ";
			}
             if (distUid=="" ||distUid==null){
				
				//whereClause = whereClause + " AND d.districtuid IN('1','2','3','4','5','6','7','8','9','10','11','12','13')";
				
			}else
				whereClause = whereClause + " AND d.districtuid = " + distUid ;
					


			builder = new StringBuilder(" SELECT  Distinct cps.cpeslno,cps.cpemacaddr, cps.profile_id, cps.batchid, cps.batchdate, cps.mspcode, cps.lmocode, cps.status ,cpm.cpetypelov, cpm.cpe_profilename, d.districtname, m.mandalname, v.villagename, ");
			builder.append(" cpm.cpe_model,cps.cafno,t.tenantcode,c.actdate, c.deactdate ");
			//builder.append(" FROM cpestock cps , cpe_profilemaster cpm , tenants t, districts d, mandals m, villages v WHERE");
			builder.append(" FROM cpestock cps LEFT Join cafs c on cps.cafno=c.cafno LEFT JOIN cpe_profilemaster cpm ON  cpm.profile_id = cps.profile_id LEFT JOIN   tenants t ON (t.tenantcode = cps.lmocode OR t.tenantcode = cps.mspcode)");
					builder.append("  LEFT JOIN districts d ON d.districtuid = t.portal_districtid LEFT JOIN mandals m ON m.mandalslno = t.portal_mandalid LEFT Join villages v ON v.villageslno = t.portal_villageid");
			
			builder.append(" Where D.DISTRICTUID = m.DISTRICTUID AND m.mandalslno = v.mandalslno AND D.DISTRICTUID = v.DISTRICTUID " + whereClause);

			LOGGER.info("builder QUery " + builder);
			query = getEntityManager().createNativeQuery(builder.toString());
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} finally {
			query = null;
		}
		return list;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public String getCpeCount(String fromDate, String toDate, String cpeType,String status,String distUid) {
		BigInteger count =null;
		Query query = null;
		String whereClause = "";
		StringBuilder builder =null;
		List<Object> results=new ArrayList<>();
		
		
		try {
			if(fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()){
				whereClause = whereClause + " and cps.batchdate BETWEEN '"+fromDate+"' and '"+toDate+"' ";
				
			}
			 	
				
			if(cpeType != null && !cpeType.isEmpty()){
				whereClause = whereClause + "  AND cpm.cpetypelov like '%"+cpeType+"%' ";
			}
			
			
			if(status != null){
				if (status == "notactive")
					whereClause = whereClause + " AND (cps.status=2 OR cps.status = 3) ";
				else if (status == "active")
					whereClause = whereClause + " AND (cps.status=4) ";
				else if (status == "defective")
					whereClause = whereClause + " AND (cps.status=99) ";
			}
			
			if (distUid=="" ||distUid==null){
				
				//whereClause = whereClause + " AND d.districtuid IN('1','2','3','4','5','6','7','8','9','10','11','12','13')";
				
			}else
				whereClause = whereClause + " AND d.districtuid = " + distUid ;
			

		/*	builder = new StringBuilder(" SELECT COUNT(DISTINCT cps.cpeslno) totalcpe ");
			builder.append(" FROM cpestock cps , cpe_profilemaster cpm  WHERE");
			builder.append(" cpm.profile_id=cps.profile_id ");
			builder.append( whereClause + " order by cps.cpeslno");*/
			
			
			builder = new StringBuilder(" SELECT COUNT(DISTINCT cps.cpeslno) totalcpe FROM  cpestock cps LEFT JOIN   cpe_profilemaster cpm ON cpm.profile_id = cps.profile_id");
			builder.append(" LEFT JOIN   cafs c ON cps.cafno = c.cafno LEFT JOIN   tenants t ON (t.tenantcode = cps.lmocode OR t.tenantcode = cps.mspcode)  LEFT JOIN   districts d ON d.districtuid = t.portal_districtid");
			builder.append(" LEFT JOIN   mandals m ON m.mandalslno = t.portal_mandalid  LEFT JOIN   villages v ON v.villageslno = t.portal_villageid WHERE   cpm.profile_id = cps.profile_id");
			builder.append(" AND D.DISTRICTUID = m.DISTRICTUID  AND m.mandalslno = v.mandalslno  AND D.DISTRICTUID = v.DISTRICTUID ");
			builder.append( whereClause);
			
			
			

				
			LOGGER.info("builder QUery " + builder);
			query = getEntityManager().createNativeQuery(builder.toString());
			results =  query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} finally {
			query = null;
		}
		return results.isEmpty() ? "0" : results.get(0).toString();
	}
	
	
}
 
