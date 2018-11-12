package com.arbiva.apsfl.tms.daoImpl;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.arbiva.apfgc.tenant.bo.CafWiseReportBO;
import com.arbiva.apfgc.tenant.bo.CafWiseRevenueOfLoginLmoBo;
import com.arbiva.apfgc.tenant.bo.CpeModelBO;
import com.arbiva.apfgc.tenant.bo.DistrictPonWiseCafCountBO;
import com.arbiva.apfgc.tenant.bo.DistrictWiseCafBO;
import com.arbiva.apfgc.tenant.bo.DistrictWiseCpeBO;
import com.arbiva.apfgc.tenant.bo.EnterpriseSubscriberBO;
import com.arbiva.apfgc.tenant.bo.TenantStockReportBO;
import com.arbiva.apfgc.tenant.bo.LmoStockCountBO;
import com.arbiva.apfgc.tenant.bo.LmoWalletUpdateByChequePaymentBO;
import com.arbiva.apfgc.tenant.bo.MsoCafNotCpeStockBo;
import com.arbiva.apfgc.tenant.bo.MsoDetailsWithLmosBO;
import com.arbiva.apfgc.tenant.bo.MsoRevenueShareBO;
import com.arbiva.apfgc.tenant.bo.MsoWiseCpeBo;
import com.arbiva.apfgc.tenant.bo.OLTMasterDataBO;
import com.arbiva.apfgc.tenant.bo.Offline_Payment1;
import com.arbiva.apfgc.tenant.bo.PONWiseBo;
import com.arbiva.apfgc.tenant.bo.PONWithZeroCAFBO;
import com.arbiva.apfgc.tenant.bo.RiggedCafBO;
import com.arbiva.apfgc.tenant.bo.SubstationWiseCafBo;
import com.arbiva.apsfl.coms.dto.CafAndCpeChargesVO;
import com.arbiva.apsfl.coms.dto.ComsHelperDTO;
import com.arbiva.apsfl.coms.dto.CustomerDTO;
import com.arbiva.apsfl.coms.dto.PageObject;
import com.arbiva.apsfl.dto.Offline_Payment;

import com.arbiva.apsfl.tms.model.EmailMaster;
import com.arbiva.apsfl.tms.model.Tenant;
import com.arbiva.apsfl.tms.model.TenantWallet;
import com.arbiva.apsfl.util.CafEnumCodes;

@Repository
public class DemandNoteDaoImpl {
	
	private static final Logger logger = Logger.getLogger(DemandNoteDaoImpl.class);

	
	private EntityManager em;
	
	@Autowired
	TenantWalletDaoImpl tenantWalletDao;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}
	
	/* DAO Implementation: To get List of Mso by using tenant code */
	@SuppressWarnings("unchecked")
	public List<Object[]> getMsoList() {
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select pml.mso_enrollment_number,t.tenantname,t.locoff_pocmob1, sum(pml.noemidemandqty), sum(pml.emi36demandqty), sum(pml.emi48demandqty),t.regoff_pocname , d.districtname, m.mandalname from portal_msp_lmos pml, tenants t, districts d , mandals m where d.districtuid = t.portal_districtid  and m.districtuid = t.portal_districtid  and m.mandalslno = t.portal_mandalid and pml.mso_enrollment_number = t.tenantcode group by mso_enrollment_number");
		List<Object[]> listMso = new ArrayList<>();
		try {
			listMso = getEntityManager().createNativeQuery(queryString.toString()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMso;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getLmoList(String tenantCode) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		queryString.append("select  lmo_name, noemidemandqty, emi36demandqty, emi48demandqty, lmo_mandal_name, lmo_district_name from portal_msp_lmos where mso_enrollment_number = :tenantCode ");
		List<Object[]> listMso = new ArrayList<>();
		try {
			query = getEntityManager().createNativeQuery(queryString.toString());
			query.setParameter("tenantCode", tenantCode);
			listMso = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMso;
	}

	@SuppressWarnings("unchecked")
	public List<MsoWiseCpeBo> getMsoWiseDemand() {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<MsoWiseCpeBo> listMso = new ArrayList<>();
		
		queryString.append(" select a.mspcode,date_format(b.maxdate,'%d-%b-%Y') maxdate, a.mspname, a.districtname");
		queryString.append(" ,SUM(CASE WHEN a.dmddate=b.maxdate THEN qty0  ELSE 0 END) dqty0 ");
		queryString.append(" ,SUM(CASE WHEN a.dmddate=b.maxdate THEN qty36 ELSE 0 END) dqty36 ");
		queryString.append(" ,SUM(CASE WHEN a.dmddate=b.maxdate THEN qty48 ELSE 0 END) dqty48");
		queryString.append(" ,SUM(qty0) cqty0");
		queryString.append(" ,SUM(qty36) cqty36");
		queryString.append(" ,SUM(qty48) cqty48");
		queryString.append(" FROM (select mspcode,concat(tenantname,' (', ifnull(locoff_pocname,''),')' ) mspname,districtname,date(dmdtime) dmddate,noemidemandqty qty0,emi36demandqty qty36,emi48demandqty qty48  ");
		queryString.append("        from mspcpedmd m1, mspcpedmddtl m2, tenants t, districts d where m1.dmdid = m2.dmdid and m1.mspcode = t.tenantcode and t.portal_districtid = d.districtuid and t.tenantcode <> 'MSO99158' ");
		queryString.append("      ) a");
		queryString.append(" 	,(select m3.mspcode,MAX(DATE(m3.dmdtime)) maxdate from mspcpedmd m3, mspcpedmddtl m4, tenants t1 where m3.dmdid = m4.dmdid and m3.mspcode = t1.tenantcode  group by mspcode) b");
		queryString.append(" where a.mspcode=b.mspcode ");
		queryString.append(" group by a.mspcode,b.maxdate, a.mspname, a.districtname order by b.maxdate desc");
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(),MsoWiseCpeBo.class);
			listMso = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMso;
	}
	
	@SuppressWarnings("unchecked")
	public List<DistrictWiseCpeBO> getDistrictWiseDemand() {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<DistrictWiseCpeBO> listMso = new ArrayList<>();
		
		/*
		 * 
	SELECT  d.districtname
	     ,SUM(CASE WHEN m1.dmdtime       < ADDDATE(CURRENT_DATE(),-1) THEN m2.noemidemandqty  ELSE 0 END) d2qty0 
		 ,SUM(CASE WHEN m1.dmdtime       < ADDDATE(CURRENT_DATE(),-1) THEN m2.emi36demandqty  ELSE 0 END) d2qty36 
		 ,SUM(CASE WHEN m1.dmdtime       < ADDDATE(CURRENT_DATE(),-1) THEN m2.emi48demandqty  ELSE 0 END) d2qty48
	     ,SUM(CASE WHEN DATE(m1.dmdtime) = ADDDATE(CURRENT_DATE(),-1) THEN m2.noemidemandqty  ELSE 0 END) d1qty0 
		 ,SUM(CASE WHEN DATE(m1.dmdtime) = ADDDATE(CURRENT_DATE(),-1) THEN m2.emi36demandqty  ELSE 0 END) d1qty36 
		 ,SUM(CASE WHEN DATE(m1.dmdtime) = ADDDATE(CURRENT_DATE(),-1) THEN m2.emi48demandqty  ELSE 0 END) d1qty48
  		FROM mspcpedmd m1, mspcpedmddtl m2, tenants t, districts d 
 	 WHERE m1.dmdid = m2.dmdid and m1.mspcode = t.tenantcode and t.portal_districtid = d.districtuid AND t.tenantcode <> 'MSO99158'
 	GROUP BY d.districtname;
		 */
		
		queryString.append(" SELECT  d.districtname ");
		queryString.append(" ,SUM(CASE WHEN m1.dmdtime       < CURRENT_DATE() THEN m2.noemidemandqty  ELSE 0 END) d2qty0  ");
		queryString.append(" ,SUM(CASE WHEN m1.dmdtime       < CURRENT_DATE() THEN m2.emi36demandqty  ELSE 0 END) d2qty36 ");
		queryString.append(" ,SUM(CASE WHEN m1.dmdtime       < CURRENT_DATE() THEN m2.emi48demandqty  ELSE 0 END) d2qty48 ");
		queryString.append(" ,SUM(CASE WHEN DATE(m1.dmdtime) = CURRENT_DATE() THEN m2.noemidemandqty  ELSE 0 END) d1qty0  ");
		queryString.append(" ,SUM(CASE WHEN DATE(m1.dmdtime) = CURRENT_DATE() THEN m2.emi36demandqty  ELSE 0 END) d1qty36  ");
		queryString.append(" ,SUM(CASE WHEN DATE(m1.dmdtime) = CURRENT_DATE() THEN m2.emi48demandqty  ELSE 0 END) d1qty48 ");
		queryString.append(" FROM mspcpedmd m1, mspcpedmddtl m2, tenants t, districts d  ");
		queryString.append(" WHERE m1.dmdid = m2.dmdid and m1.mspcode = t.tenantcode and t.portal_districtid = d.districtuid AND t.tenantcode <> 'MSO99158'");
		queryString.append(" GROUP BY d.districtname");
		
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(),DistrictWiseCpeBO.class);
			listMso = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMso;
	}

	@SuppressWarnings("unchecked")
	public List<CafWiseReportBO> getCafWiseDemand() {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<CafWiseReportBO> list = new ArrayList<>();
		
		/*SELECT date_format(m.maxdate,'%d-%b-%Y') maxdate,a.districtname,a.villagename,a.lmoname
	     ,SUM(CASE WHEN a.custtypelov='INDIVIDUAL' AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) hhday
	     ,SUM(CASE WHEN a.custtypelov='INDIVIDUAL'                         THEN 1 ELSE 0 END) hhcum
	     ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='PRIVATE' AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) pvtday
	     ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='GOVT'    AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) govtday
	     ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='PRIVATE'                         THEN 1 ELSE 0 END) pvtcum
	     ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='GOVT'                            THEN 1 ELSE 0 END) govtcum
	 FROM (SELECT cf.cafdate,cf.inst_district,d.districtname,cf.inst_mandal,cf.inst_city_village, v.villagename,cf.lmocode,cf.custtypelov
	             ,CASE WHEN cf.custtypelov='ENTERPRISE' THEN (SELECT ec.enttypelov from entcustomers ec where ec.custid=cf.custid) ELSE '' END enttype
	             ,CONCAT(IFNULL(t.tenantname,''),'(',IFNULL(t.locoff_pocname,IFNULL(t.regoff_pocname,'')),')') lmoname
	         FROM cafs cf,districts d,villages v,tenants t
	        WHERE cf.inst_district = d.districtuid AND cf.inst_district=v.districtuid AND cf.inst_mandal=v.mandalslno AND cf.inst_city_village=v.villageslno
	          AND cf.lmocode = t.tenantcode
	         ) a
	     ,(SELECT inst_district,inst_mandal,inst_city_village,lmocode,MAX(cafdate) maxdate FROM cafs 
	        GROUP BY inst_district,inst_mandal,inst_city_village,lmocode) m
	WHERE a.lmocode = m.lmocode AND a.inst_district = m.inst_district AND a.inst_city_village=m.inst_city_village
	GROUP BY    m.maxdate,a.districtname,a.villagename,a.lmoname
	order by m.maxdate desc*/
		
/*		queryString.append("  SELECT date_format(m.maxdate,'%d-%b-%Y') maxdate,d.districtname,v.villagename, concat(t.tenantname, ' (', ifnull(t.regoff_pocname,''),')') tname");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='INDIVIDUAL' AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) hhday");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='INDIVIDUAL'                         THEN 1 ELSE 0 END) hhcum");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='PRIVATE' AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) pvtday");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='GOVT'    AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) govtday");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='PRIVATE'                         THEN 1 ELSE 0 END) pvtcum");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='GOVT'                            THEN 1 ELSE 0 END) govtcum");
		queryString.append(" FROM (select cafdate,inst_district,inst_mandal,inst_city_village,lmocode,custtypelov,CASE WHEN custtypelov='ENTERPRISE' THEN (SELECT enttypelov from entcustomers ec where ec.custid=cf.custid) ELSE '' END enttype");
		queryString.append(" from cafs cf) a");		
		queryString.append(" ,(select lmocode,MAX(cafdate) maxdate FROM cafs GROUP bY lmocode) m");
		queryString.append(" ,districts d,villages v,tenants t");
		queryString.append(" WHERE a.inst_district = d.districtuid AND a.inst_district=v.districtuid AND a.inst_mandal=v.mandalslno AND a.inst_city_village=v.villageslno");
		queryString.append("  AND a.lmocode = m.lmocode AND a.lmocode = t.tenantcode");
		queryString.append(" GROUP BY    m.maxdate,d.districtname,v.villagename,tname  order by m.maxdate desc");*/
		
		queryString.append(" SELECT date_format(m.maxdate,'%d-%b-%Y') maxdate,a.districtname,a.villagename,a.lmoname tname");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='INDIVIDUAL' AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) hhday");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='INDIVIDUAL'                         THEN 1 ELSE 0 END) hhcum");
		queryString.append("  ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='PRIVATE' AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) pvtday");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='GOVT'    AND a.cafdate=m.maxdate THEN 1 ELSE 0 END) govtday");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='PRIVATE'                         THEN 1 ELSE 0 END) pvtcum");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='ENTERPRISE' AND a.enttype='GOVT'                            THEN 1 ELSE 0 END) govtcum");
		queryString.append("  FROM (SELECT cf.cafdate,cf.inst_district,d.districtname,cf.inst_mandal,cf.inst_city_village, v.villagename,cf.lmocode,cf.custtypelov");
		queryString.append("    ,CASE WHEN cf.custtypelov='ENTERPRISE' THEN (SELECT ec.enttypelov from entcustomers ec where ec.custid=cf.custid) ELSE '' END enttype");
		queryString.append("   ,CONCAT(IFNULL(t.tenantname,''),'(',IFNULL(t.locoff_pocname,IFNULL(t.regoff_pocname,'')),')') lmoname");
		queryString.append("  FROM cafs cf,districts d,villages v,tenants t, mandals m");
		queryString.append("   WHERE cf.inst_district = d.districtuid AND cf.inst_district=v.districtuid AND cf.inst_mandal=v.mandalslno AND cf.inst_city_village=v.villageslno");
		queryString.append("     AND cf.lmocode = t.tenantcode AND cf.status = 6 AND m.districtuid = d.districtuid AND m.mandalslno = cf.inst_mandal AND v.mandalslno = m.mandalslno ");
		queryString.append("    ) a");
		queryString.append(" ,(SELECT inst_district,inst_mandal,inst_city_village,lmocode,MAX(cafdate) maxdate FROM cafs WHERE status = 6  ");
		queryString.append("  GROUP BY inst_district,inst_mandal,inst_city_village,lmocode) m");
		queryString.append(" WHERE a.lmocode = m.lmocode AND a.inst_district = m.inst_district and a.inst_mandal = m.inst_mandal AND a.inst_city_village=m.inst_city_village");
		queryString.append(" GROUP BY    m.maxdate,a.districtname,a.villagename,a.lmoname");
		queryString.append(" order by m.maxdate desc");
		
		logger.info("Caf Query ===== "+ queryString.toString());
		
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(),CafWiseReportBO.class);
			list = query.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<EmailMaster> getEamilOfCafWiseReport(String val) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		queryString.append("select * from emailrptmst where rptname = :val ");
		List<EmailMaster> listMso = new ArrayList<>();
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(), EmailMaster.class);
			query.setParameter("val", val);
			listMso = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMso;
	}

	public <T> T save(T t) {
		return getEntityManager().merge(t);
	}

	@SuppressWarnings("unchecked")
	public List<DistrictWiseCafBO> getDistrictWiseCafList() {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<DistrictWiseCafBO> districtWiseCafList = new ArrayList<>();
		
		queryString.append(" SELECT a.districtname ");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='"+CafEnumCodes.CUST_TYPE_CODE.getCode()+"' AND a.actdate < CURRENT_DATE() THEN 1 ELSE 0 END) hhuptoday  ");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='"+CafEnumCodes.CUST_TYPE_CODE.getCode()+"' AND a.actdate = CURRENT_DATE() THEN 1 ELSE 0 END) hhonday ");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='"+CafEnumCodes.ENTCUST_TYPE_CODE.getCode()+"' AND a.enttype='"+CafEnumCodes.PRIVATE_TYPE_CODE.getCode()+"' AND a.actdate < CURRENT_DATE() THEN 1 ELSE 0 END) pvtuptoday ");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='"+CafEnumCodes.ENTCUST_TYPE_CODE.getCode()+"' AND a.enttype='"+CafEnumCodes.GOVT_TYPE_CODE.getCode()+"'    AND a.actdate < CURRENT_DATE() THEN 1 ELSE 0 END) govtuptoday  ");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='"+CafEnumCodes.ENTCUST_TYPE_CODE.getCode()+"' AND a.enttype='"+CafEnumCodes.PRIVATE_TYPE_CODE.getCode()+"' AND a.actdate = CURRENT_DATE() THEN 1 ELSE 0 END) pvtonday  ");
		queryString.append(" ,SUM(CASE WHEN a.custtypelov='"+CafEnumCodes.ENTCUST_TYPE_CODE.getCode()+"' AND a.enttype='"+CafEnumCodes.GOVT_TYPE_CODE.getCode()+"'    AND a.actdate = CURRENT_DATE() THEN 1 ELSE 0 END) govtonday ");
		queryString.append(" FROM (SELECT cf.actdate,d.districtname,cf.custtypelov  ");
		queryString.append(" ,CASE WHEN cf.custtypelov='"+CafEnumCodes.ENTCUST_TYPE_CODE.getCode()+"' THEN (SELECT ec.enttypelov from entcustomers ec where ec.custid=cf.custid) ELSE '' END enttype ");
		queryString.append(" FROM cafs cf,districts d ");
		queryString.append(" WHERE cf.inst_district = d.districtuid and cf.status in (6)) a");
				//+ "not in ("+CafEnumCodes.CAF_BULK_UPLOAD_STATUS.getStatus()+", "+CafEnumCodes.CAF_BULK_UPLOAD_PENDING_STATUS.getStatus()+", "+CafEnumCodes.CAF_PENDING_STATUS.getStatus()+") ) a ");
		queryString.append(" GROUP BY a.districtname ORDER BY 1 ");
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(),DistrictWiseCafBO.class);
			districtWiseCafList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return districtWiseCafList;
	}
	
	@SuppressWarnings("unchecked")
	public List<EnterpriseSubscriberBO> getEnterpriseSubscriberList(PageObject pageObject) {
		List<EnterpriseSubscriberBO> enterpriseSubscriberList = new ArrayList<>();
		StringBuilder queryString = new StringBuilder();
		String finalQuery = null;
		String searchParameter = "";
		Query query = null;
		long count = 0l;
		try{
			queryString.append(" SELECT cf.custid, c.custname, ");
			queryString.append(" IFNULL((SELECT prnt.custname FROM customermst prnt WHERE prnt.custid=c.parentcustcode),c.custname) parentcustname, ");
			queryString.append(" COUNT(1) totalcafs, cf.lmocode, SUM(CASE WHEN cf.status = 6 THEN 1 ELSE 0 END) activecount, ");
			queryString.append(" SUM(CASE WHEN cf.status = 7 THEN 1 ELSE 0 END) suspendcount, ");
			queryString.append(" SUM(CASE WHEN cf.status = 8 THEN 1 ELSE 0 END) inactivecount, ");
			queryString.append(" SUM(CASE WHEN cf.status IN(88) THEN 1 ELSE 0 END) pendingforcafeditcount, ");
			queryString.append(" SUM(CASE WHEN cf.status IN(2) THEN 1 ELSE 0 END) pendingforprovisioncount, ");
			queryString.append(" SUM(CASE WHEN cf.status IN(89) THEN 1 ELSE 0 END) pendingforpaymentcount , t.tenantname ");
			queryString.append(" FROM cafs cf,customermst c , tenants t ");
			queryString.append(" WHERE cf.custid = c.custid AND cf.custtypelov='ENTERPRISE' AND c.enttypelov = 'GOVT' AND cf.lmocode=t.tenantcode ");
			
			StringBuilder whereClause = new StringBuilder("");
			StringBuilder orderByClause = new StringBuilder("");
			StringBuilder groupByClause = new StringBuilder("");
			if (pageObject != null) {
				searchParameter = pageObject.getSearchParameter();
					if(searchParameter != null && !searchParameter.isEmpty()){
						whereClause.append("and (custname like '%" + searchParameter + "%' or cf.lmocode like '%"+ searchParameter + "%' ");
						whereClause.append("or t.tenantname like '%" + searchParameter + "%' ) ");
					}
				orderByClause.append(" ORDER BY "+pageObject.getSortColumn()+" "+pageObject.getSortOrder());
				groupByClause.append(" GROUP BY cf.custid, c.custname, cf.lmocode ");
			    finalQuery = queryString.append(whereClause).append(groupByClause).append(orderByClause).toString();
				
				query = getEntityManager().createNativeQuery(finalQuery.toString(),EnterpriseSubscriberBO.class);
				count = query.getResultList().size();
				pageObject.setTotalDisplayCount(String.valueOf(count));
				
				query = getEntityManager().createNativeQuery(finalQuery.toString(),EnterpriseSubscriberBO.class);
				query.setFirstResult(pageObject.getMinSize());
				query.setMaxResults(pageObject.getMaxSize());
			}
			else{
				groupByClause.append(" GROUP BY cf.custid, c.custname, cf.lmocode ");
			    finalQuery = queryString.append(groupByClause).toString();
				query = getEntityManager().createNativeQuery(finalQuery.toString(),EnterpriseSubscriberBO.class);
			}
			enterpriseSubscriberList = query.getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			query = null;
			queryString = null;
			query = null;
		}
		
		return enterpriseSubscriberList;
	}

	@SuppressWarnings("rawtypes")
	public String getBalanceStatus(int size, String profileId, String tenantCode) {
		StringBuilder builder = new StringBuilder();
		Query query = null;
		String returnVal = null;
		try {
			logger.info("CpeDaoImpl :: updateCpeBlockedAmount() :: SATRT");
			 
			builder.append(" SELECT CASE WHEN   ((a.chramt <=0) OR (b.wallamt >= a.chramt)) THEN 'true' ELSE 'false' END FROM "
					+ " (SELECT (upfrontcharges * "+size+") chramt FROM cpecharges WHERE profile_id = "+profileId+" AND CURRENT_DATE BETWEEN effectivefrom AND effectiveto)a,"
					+ " (SELECT ( crlimitamt + walletamt + cperelamt - usedamt - cpeblkamt) AS wallamt FROM tenantswallet WHERE tenantcode = '"+tenantCode+"') b");
		 
			query = getEntityManager().createNativeQuery(builder.toString());
			
			List l = query.getResultList();
			
			returnVal = l.get(0).toString();
			logger.info("CpeDaoImpl :: updateCpeBlockedAmount() :: END");
		} catch (Exception e) {
			logger.error("CpeDaoImpl::updateCpeBlockedAmount() " + e);
		} finally {
			query = null;
			builder = null;
		}
		
		return returnVal;
	}
	
	public Tenant findByTenantId(Integer tenantid) {
		Tenant tenant = new Tenant();
		TypedQuery<Tenant> query = null;
		StringBuilder builder = new StringBuilder(" FROM ").append(Tenant.class.getSimpleName())
				.append(" WHERE tenantid=:tenantid");
		try {
			logger.info("START::findByTenantId()");
			query = getEntityManager().createQuery(builder.toString(), Tenant.class);
			query.setParameter("tenantid", tenantid);
			tenant = query.getSingleResult();
			logger.info("END::findByTenantId()");
		} catch (Exception e) {
			logger.error("EXCEPTION::findByTenantId() " + e);
		} finally {
			query = null;
			builder = null;
		}
		return tenant;
	}

	public CpeModelBO getCpeModelInfoBySrlNo(String cpeSrlNo) {
		CpeModelBO cpeModelBO = new CpeModelBO();
		Query query = null;
		try{
		StringBuilder builder = new StringBuilder("SELECT cpe_model,cp.profile_id,cp.cpetypelov FROM cpe_profilemaster cp, cpestock cs WHERE cs.profile_id = cp.profile_id AND cs.cpeslno = '"+cpeSrlNo+"'");
			query = getEntityManager().createNativeQuery(builder.toString(), CpeModelBO.class);
			cpeModelBO =  (CpeModelBO) query.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			query = null;
			query = null;
		}
		
		return cpeModelBO;
	}
	
	@SuppressWarnings("unchecked")
	public List<PONWiseBo> getAllotedPONWithCaf(String district, String mandal, String lmocode) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<PONWiseBo> listPON = new ArrayList<>();
		
			try {
			/*queryString.append("SELECT olm.pop_name,olm.pop_olt_ipaddress,olt.portno,olt.lmocode,ts.regoff_pocname,ts.regoff_pocmob1,SUM(CASE WHEN cafno IS NOT NULL THEN 1 ELSE 0 END) cafno,ds.districtname,ms.mandalname,ts.createdon ");
			queryString.append("FROM oltportdtls olt, oltmaster olm ,");
			queryString.append("substations sub,districts ds,mandals ms ,cafs cf,tenants ts ");
			queryString.append("WHERE olt.pop_olt_serialno=olm.pop_olt_serialno ");
			queryString.append("AND olm.pop_substnuid=sub.substnuid ");
			queryString.append("AND sub.districtuid=ds.districtuid ");
			queryString.append("AND sub.mandalslno=ms.mandalslno ");
			queryString.append("AND cf.lmocode=ts.tenantcode ");
			queryString.append("AND ms.districtuid=ds.districtuid ");
			queryString.append("AND cf.lmocode=olt.lmocode ");
			queryString.append("AND olm.pop_olt_ipaddress=cf.oltipaddr ");
			queryString.append("AND olt.portno=cf.olt_portid ");*/
				
			queryString.append("SELECT olm.pop_name,olm.pop_olt_ipaddress,olt.portno,c.lmocode,t.regoff_pocname,t.regoff_pocmob1, ");
			queryString.append("SUM(CASE WHEN c.cafno IS NOT NULL THEN 1 ELSE 0 END) cafno,d.districtname,m.mandalname,t.createdon ");
			queryString.append("FROM cafs c ");
			queryString.append("LEFT JOIN oltportdtls olt ON olt.lmocode=c.lmocode AND olt.portno=c.olt_portid AND olt.pop_olt_serialno=c.olt_id AND c.olt_cardno=olt.cardid ");
			queryString.append("LEFT JOIN oltmaster olm ON olm.pop_olt_serialno=olt.pop_olt_serialno AND olm.pop_olt_ipaddress=c.oltipaddr ");
			queryString.append("LEFT JOIN districts d ON d.districtuid=inst_district ");
			queryString.append("LEFT JOIN mandals m ON m.mandalslno=c.inst_mandal AND d.districtuid=m.districtuid ");
			queryString.append("LEFT JOIN villages v ON v.villageslno=c.inst_city_village AND v.mandalslno=m.mandalslno AND v.districtuid=d.districtuid ");
			queryString.append("LEFT JOIN tenants t ON t.tenantcode=c.lmocode ");
			queryString.append("LEFT JOIN substations sub ON sub.substnuid=olm.pop_substnuid ");
			/*queryString.append("WHERE c.status=6 AND c.lmocode LIKE '%LMO%' ");*/ 
			queryString.append("WHERE c.status=6 ");
			
			String whereClause1="";
			if((district != null && !district.isEmpty()) && (mandal != null && !mandal.isEmpty())){
				
				whereClause1 = " AND d.districtuid='"+district+"' and m.mandalslno='"+mandal+"' ";

			}

				else if(lmocode != null && !lmocode.isEmpty()){
					
					whereClause1 = "  AND olt.lmocode='"+lmocode+"' ";
		
			}
			
				else{
					
					whereClause1= "";
					
				}
			queryString.append(whereClause1);
			
			queryString.append(" GROUP BY c.lmocode,olt.portno,olm.pop_name,olm.pop_olt_serialno, ");
			queryString.append(" d.districtname,m.mandalname,v.villagename,t.tenantname,olm.pop_olt_ipaddress,olm.pop_name ");
				/*queryString.append(" GROUP BY olm.pop_name,olm.pop_olt_ipaddress,olt.pop_olt_serialno,olt.portno,olt.lmocode,ds.districtname,ms.mandalname,ts.createdon,ts.regoff_pocname,ts.regoff_pocmob1 ");*/
			  
				query = getEntityManager().createNativeQuery(queryString.toString(),PONWiseBo.class);
		
			
			listPON = query.getResultList();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			query = null;
			queryString = null;
			query = null;
		}
		return listPON;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SubstationWiseCafBo> getSubstationWisePONWithCaf(String district, String mandal) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<SubstationWiseCafBo> listSubstationWisePONWithCaf = new ArrayList<>();
		
		/*queryString.append("SELECT olm.pop_name,olm.pop_olt_ipaddress,ds.districtname,ms.mandalname,olt.portno,SUM(CASE WHEN cafno IS NOT NULL THEN 1 ELSE 0 END) cafno ");
		queryString.append("FROM oltportdtls olt, oltmaster olm , ");
		queryString.append("substations sub,districts ds,mandals ms ,cafs cf,tenants ts ");
		queryString.append("WHERE olt.pop_olt_serialno=olm.pop_olt_serialno ");
		queryString.append("AND olm.pop_substnuid=sub.substnuid ");
		queryString.append("AND sub.districtuid=ds.districtuid ");
		queryString.append("AND sub.mandalslno=ms.mandalslno ");
		queryString.append("AND cf.lmocode=ts.tenantcode ");
		queryString.append("AND ms.districtuid=ds.districtuid ");
		queryString.append("AND cf.lmocode=olt.lmocode ");
		queryString.append("AND olm.pop_olt_ipaddress=cf.oltipaddr ");
		queryString.append("AND olt.portno=cf.olt_portid ");
		queryString.append("GROUP BY olm.pop_name,olm.pop_olt_ipaddress,ds.districtname,ms.mandalname,olt.portno ");*/
		
		String whereClause="";
		
	
		if((district != null && !district.isEmpty()) && (mandal != null && !mandal.isEmpty())){
			
			whereClause = " AND d.districtuid='"+district+"' and m.mandalslno='"+mandal+"' ";

		}
		
		queryString.append("SELECT sub.substnname,olt.pop_olt_ipaddress,olt.pop_olt_serialno,olp.cardid,olp.portno,d.districtname,m.mandalname,COUNT(cfs.cafno) AS cafcount ");
		queryString.append("FROM substations sub ");
		queryString.append("LEFT JOIN oltmaster olt ON olt.pop_substnuid=sub.substnuid ");
		queryString.append("LEFT JOIN oltportdtls olp ON olp.pop_olt_serialno=olt.pop_olt_serialno ");
		queryString.append("LEFT JOIN cafs cfs ON  cfs.olt_id=olt.pop_olt_serialno AND cfs.oltipaddr=olt.pop_olt_ipaddress  AND cfs.olt_cardno=olp.cardid AND cfs.olt_portid=olp.portno AND cfs.status IN(6,2) ");
		queryString.append("LEFT JOIN districts d ON d.districtuid=sub.districtuid ");
		queryString.append("LEFT JOIN mandals m ON m.mandalslno=sub.mandalslno AND d.districtuid=m.districtuid ");
		queryString.append("WHERE cfs.olt_cardno IN ('1','2','3','4') ");
		queryString.append(whereClause);
		queryString.append("GROUP BY   sub.substnname,olt.pop_olt_ipaddress,olt.pop_olt_serialno,olp.cardid,olp.portno,d.districtname,m.mandalname ");
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(),SubstationWiseCafBo.class);
			listSubstationWisePONWithCaf = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSubstationWisePONWithCaf;
	}
	
	@SuppressWarnings("unchecked")
	public List<PONWithZeroCAFBO> getPONWithZeroCAFBO(String district, String mandal, String lmocode) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<PONWithZeroCAFBO> listPONWithZeroCAF = new ArrayList<>();

		try {
			queryString.append("SELECT olm.pop_name,olt.lmocode,olt.portno,ts.tenantname,m.mandalname,d.districtname,v.villagename,ts.regoff_pocmob1 FROM ");
			queryString.append("oltportdtls olt,oltmaster olm,substations sub,tenants ts,mandals m,districts d,villages v,users u ");
			queryString.append("WHERE   olm.pop_substnuid=sub.substnuid ");
			queryString.append("AND olt.pop_olt_serialno=olm.pop_olt_serialno ");
			queryString.append("AND olt.l3slotsused IS NULL ");
			queryString.append("AND olt.lmocode LIKE 'LMO%' "); 
			queryString.append("AND olt.lmocode=ts.tenantcode ");
			queryString.append("AND u.tenantcode=olt.lmocode ");
			queryString.append("AND ts.portal_districtid=d.districtuid ");
			queryString.append("AND ts.portal_mandalid=m.mandalslno ");
			queryString.append("AND ts.portal_districtid=m.districtuid ");
			queryString.append("AND ts.portal_villageid =v.villageslno "); 
			queryString.append("AND ts.portal_mandalid=v.mandalslno ");
			queryString.append("AND ts.portal_districtid= v.districtuid ");
			
			
			String whereClause1="";
			if((district != null && !district.isEmpty()) && (mandal != null && !mandal.isEmpty())){
				
				whereClause1 = " AND d.districtuid='"+district+"' and m.mandalslno='"+mandal+"' ";

			}

				else if(lmocode != null && !lmocode.isEmpty()){
					
					whereClause1 = "  AND olt.lmocode='"+lmocode+"' ";
		
			}
			
				else{
					
					whereClause1= "";
					
				}
			queryString.append(whereClause1);

					queryString.append(" GROUP BY olm.pop_name,olt.lmocode,olt.portno,ts.tenantname,m.mandalname,d.districtname,v.villagename ");
					
				    
				    
				    query = getEntityManager().createNativeQuery(queryString.toString(),PONWithZeroCAFBO.class);

		
				listPONWithZeroCAF = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			query = null;
			queryString = null;
			query = null;
		}
		return listPONWithZeroCAF;
	}
	
	//LMO Wise Stock Count
	@SuppressWarnings("unchecked")
	public List<LmoStockCountBO> getlmoWiseStockCount() {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<LmoStockCountBO> lmoWiseStockCount = new ArrayList<>();
		
		queryString.append("SELECT ts.tenantcode,ts.tenantname,ts.regoff_pocname AS LMO_Name,ts.regoff_pocmob1 AS LMO_MobileNO,ds.districtname as districtname,ms.mandalname as Lmo_Mandalname,vs.villagename as Lmo_Villagename, ");
		queryString.append("SUM(CASE WHEN cs.STATUS = 4 THEN 1 ELSE 0 END) CAF_Done_Stock_Count , ");
		queryString.append("SUM(CASE WHEN cs.STATUS = 3  THEN 1 ELSE 0 END) AS CAF_Not_Stock_Count ,ts.regoff_pocname as CAF_Created_DATE ");  
		queryString.append("FROM CPESTOCK cs ");
		queryString.append("LEFT JOIN tenants ts ON   cs.LMOCODE=ts.tenantcode AND ts.tenanttypelov='LMO' ");
		queryString.append("LEFT JOIN districts ds ON ds.districtuid=ts.portal_districtid ");
		queryString.append("LEFT JOIN mandals ms ON ms.mandalslno=ts.portal_mandalid AND ds.districtuid=ms.districtuid ");
		queryString.append("LEFT JOIN villages vs ON vs.districtuid=ds.districtuid AND vs.mandalslno=ms.mandalslno AND "); 
		queryString.append("ts.portal_villageid=vs.villageslno AND vs.districtuid=ts.portal_districtid AND vs.mandalslno=ts.portal_mandalid ");
		queryString.append("where ts.tenantcode is not null AND cs.lmocode like '%LMO%' ");
		queryString.append("GROUP BY ts.tenantcode,ts.tenantname,ts.regoff_pocname,ts.regoff_pocmob1 ");
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(),LmoStockCountBO.class);
			lmoWiseStockCount = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lmoWiseStockCount;
	}

	// District Manager Wise Stock Count
	@SuppressWarnings("unchecked")
	public List<CustomerDTO> getCustomerDetails(String tenantcode, String from, String to) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<Object[]> temp = new ArrayList<>();
		List<CustomerDTO> lmoWiseStockCount = new ArrayList<>();

		queryString.append(
				" select cafno,custname,cf.aadharno,cafdate,pocmob1 from cafs cf,customermst mst where  cf.aadharno=mst.aadharno and cf.lmocode='"
						+ tenantcode + "' ");
		queryString.append(" AND cf.status in('6','2') AND DATE(cafdate) = '" + from + "'");
		try {
			query = getEntityManager().createNativeQuery(queryString.toString());
			temp = query.getResultList();
			for (Object[] i : temp) {
				String[] rec = new String[i.length];
				rec[0] = (i[0] == null ? "NA" : i[0].toString());
				rec[1] = (i[1] == null ? "NA" : i[1].toString());
				rec[2] = (i[2] == null ? "NA" : i[2].toString());
				rec[3] = (i[3] == null ? "NA" : i[3].toString());
				rec[4] = (i[4] == null ? "NA" : i[4].toString());
				CustomerDTO rec1 = new CustomerDTO();
				rec1.setCustid(rec[0]);
				rec1.setCustType(rec[1]);
				rec1.setAadharno(rec[2]);
				rec1.setDob(rec[3]);
				rec1.setContactno(rec[4]);
				lmoWiseStockCount.add(rec1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lmoWiseStockCount;
	}

	// View Customer Details
	@SuppressWarnings("unchecked")
	public List<LmoStockCountBO> getdistrictManagerStockCount(String tenantcode, String from, String to, String lmocode) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		
		String whereClause = "";
		
		List<Object[]> temp = new ArrayList<>();
		List<LmoStockCountBO> lmoWiseStockCount = new ArrayList<>();
		
		if((from != null && !from.isEmpty()) && (to != null && !to.isEmpty()) && (lmocode != null && !lmocode.isEmpty())){
			whereClause = "AND c.lmocode='"+lmocode+"' GROUP BY c.lmocode,c.cafdate, d.districtname,m.mandalname,v.villagename  HAVING c.cafdate BETWEEN '"+from+"' AND '"+to+"' ";
		}
		
		else if((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())){
			whereClause = " GROUP BY c.lmocode,c.cafdate, d.districtname,m.mandalname,v.villagename  HAVING c.cafdate BETWEEN '"+from+"' AND '"+to+"' ";
		}
		else if(lmocode != null && !lmocode.isEmpty()){
			whereClause = " AND c.lmocode='"+lmocode+"' GROUP BY c.lmocode,c.cafdate, d.districtname,m.mandalname,v.villagename ";
		}else{
			
			whereClause = " GROUP BY c.lmocode,c.cafdate, d.districtname,m.mandalname,v.villagename";
			
		}
		
		queryString.append(" SELECT c.lmocode,d.districtname,m.mandalname,v.villagename,DATE_FORMAT(c.cafdate,'%Y-%m-%d'),COUNT(c.cafno) from cafs c ");
		queryString.append(" LEFT JOIN oltportdtls olt ON olt.lmocode=c.lmocode AND olt.portno=c.olt_portid AND olt.pop_olt_serialno=c.olt_id AND c.olt_cardno=olt.cardid ");
		queryString.append(" LEFT JOIN oltmaster olm ON olm.pop_olt_serialno=olt.pop_olt_serialno AND olm.pop_olt_ipaddress=c.oltipaddr ");
		queryString.append(" LEFT JOIN districts d ON d.districtuid=inst_district ");
		queryString.append(" LEFT JOIN mandals m ON m.mandalslno=c.inst_mandal AND d.districtuid=m.districtuid LEFT JOIN villages v ON v.villageslno=c.inst_city_village AND v.mandalslno=m.mandalslno AND v.districtuid=d.districtuid ");
		queryString.append(" LEFT JOIN tenants t ON t.portal_districtid=c.inst_district LEFT JOIN substations sub ON sub.substnuid=olm.pop_substnuid ");
		queryString.append(" WHERE c.status in('6') AND t.tenantcode='"+tenantcode+"' ");
		queryString.append(whereClause);

		/*
		 * SELECT cf.lmocode,ds.districtname AS districtname,ms.mandalname AS
		 * Lmo_Mandalname, vs.villagename AS Lmo_Villagename,
		 * DATE_FORMAT(cf.cafdate,'%Y-%m-%d')CAF_Created_DATE, -- SUM(CASE WHEN
		 * cf.STATUS = 6 THEN 1 ELSE 0 END) COUNT(CF.CAFNO) CAF_Done_Stock_Count FROM
		 * cafs cf,tenants ts,mandals ms,districts ds,villages vs ,oltportdtls olt WHERE
		 * ts.portal_districtid=cf.inst_district AND cf.inst_district=ds.districtuid AND
		 * cf.inst_mandal=ms.mandalslno AND cf.inst_city_village=vs.villageslno AND
		 * vs.mandalslno=ms.mandalslno AND vs.districtuid=ds.districtuid AND
		 * ts.portal_districtid=ds.districtuid AND ts.portal_districtid=ms.districtuid
		 * AND ts.portal_districtid=vs.districtuid AND olt.cardid=cf.olt_cardno AND
		 * olt.pop_olt_serialno=cf.olt_id AND ts.tenantcode='CTRDM' GROUP BY
		 * cf.lmocode,ds.districtname,ms.mandalname,vs.villagename,cafdate HAVING
		 * DATE(CAF_Created_DATE) BETWEEN '2016-06-05' AND '2017-06-05';
		 * 
		 */

		try {
			query = getEntityManager().createNativeQuery(queryString.toString());
			temp = query.getResultList();
			for (Object[] i : temp) {
				LmoStockCountBO rec = new LmoStockCountBO();
				rec.setLmoCode(i[0].toString());
				rec.setDistrict(i[1].toString());
				rec.setMandal(i[2].toString());
				rec.setVillage(i[3].toString());
				rec.setCreateddate(i[4].toString());
				rec.setCafCount(Long.valueOf(i[5].toString()));
				lmoWiseStockCount.add(rec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lmoWiseStockCount;
	}

	// MSO Wise LMO Details
	@SuppressWarnings("unchecked")
	public List<MsoDetailsWithLmosBO> getMsoWiseLmoDetails() {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<MsoDetailsWithLmosBO> listMsoWiseLmo = new ArrayList<>();

		queryString.append("SELECT DISTINCT TE.tenantname AS MSONAME,REPLACE(REPLACE(SUBSTRING_INDEX(r.partnerlist, ',', -1),']',''),' ','') MSO,  ");
		queryString.append("TE.regoff_pocname AS MSO_ContactName,TE.regoff_pocmob1 AS MSO_MobileNo,ds.districtname AS mso_district,ms.mandalname AS mso_mandal,vs.villagename AS mso_villagename,TE.createdon AS mso_createdon,T.tenantname AS LMONAME, ");
		queryString.append("REPLACE(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(r.partnerlist, ',', 2), ',', -1), ']', '' ),' ','') LMO, T.regoff_pocname AS LMO_ContactName, ");
		queryString.append("T.regoff_pocmob1 AS LMO_MobileNo,dt.districtname AS lmo_district,mt.mandalname AS lmo_mandal,vt.villagename AS lmo_villagename, T.createdon AS lmo_createdon ");
		queryString.append("FROM RSAGRMNTS r "); 
		queryString.append("LEFT JOIN tenants T ON T.tenantcode = REPLACE(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(r.partnerlist, ',', 2), ',', -1), ']', '' ),' ','') ");
		queryString.append("LEFT JOIN tenants TE ON TE.tenantcode = REPLACE(REPLACE(SUBSTRING_INDEX(r.partnerlist, ',', -1),']',''),' ','') ");
		queryString.append("LEFT JOIN districts ds ON ds.districtuid=te.portal_districtid ");
		queryString.append("LEFT JOIN mandals ms ON ms.mandalslno=te.portal_mandalid AND ds.districtuid=ms.districtuid ");
		queryString.append("LEFT JOIN villages vs ON vs.districtuid=ds.districtuid AND vs.mandalslno=ms.mandalslno AND te.portal_villageid=vs.villageslno AND vs.districtuid=te.portal_districtid AND vs.mandalslno=te.portal_mandalid ");
		queryString.append("LEFT JOIN districts dt ON dt.districtuid=t.portal_districtid ");
		queryString.append("LEFT JOIN mandals mt ON mt.mandalslno=t.portal_mandalid AND dt.districtuid=mt.districtuid ");
		queryString.append("LEFT JOIN villages vt ON vt.districtuid=dt.districtuid AND vt.mandalslno=mt.mandalslno AND t.portal_villageid=vt.villageslno AND vt.districtuid=t.portal_districtid AND vt.mandalslno=t.portal_mandalid ");
		queryString.append("WHERE partnerlist LIKE '%MSO%' ");
		queryString.append("GROUP BY TE.tenantname,MSO,TE.regoff_pocname,TE.regoff_pocmob1,T.tenantname,LMO,T.regoff_pocname,T.regoff_pocmob1, ");
		queryString.append("ds.districtname,ms.mandalname,dt.districtname,mt.mandalname,vs.villagename,vt.villagename,TE.createdon,T.createdon ");
		queryString.append("ORDER BY TE.tenantname ");

		try {
			query = getEntityManager().createNativeQuery(queryString.toString(), MsoDetailsWithLmosBO.class);
			listMsoWiseLmo = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getMsoWiseLmoDetails" + e);
		}
		return listMsoWiseLmo;
	}
	
	//LMO CPE Stock
	@SuppressWarnings("unchecked")
	public List<TenantStockReportBO> getLMOStockReport(String tenantCode,String status) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<TenantStockReportBO> lmoStockReport = new ArrayList<>();
		
		queryString.append("select cs.cpeslno,cs.cpemacaddr,cpm.cpetypelov AS cpe_profile,cs.batchdate,cs.cafno,ts.tenantcode,ds.districtname,ms.mandalname,vs.villagename from cpestock cs ");
		queryString.append("LEFT JOIN cpe_profilemaster cpm ON cs.profile_id=cpm.profile_id ");
		queryString.append("LEFT JOIN tenants ts ON   cs.LMOCODE=ts.tenantcode AND ts.tenanttypelov='LMO' ");
		queryString.append("LEFT JOIN districts ds ON ds.districtuid=ts.portal_districtid ");
		queryString.append("LEFT JOIN mandals ms ON ms.mandalslno=ts.portal_mandalid AND ds.districtuid=ms.districtuid ");
		queryString.append("LEFT JOIN villages vs ON vs.districtuid=ds.districtuid AND vs.mandalslno=ms.mandalslno AND ");
		queryString.append("ts.portal_villageid=vs.villageslno AND vs.districtuid=ts.portal_districtid AND vs.mandalslno=ts.portal_mandalid ");
		queryString.append("where ts.tenantcode is not null AND cs.lmocode like '%LMO%' AND cs.lmocode='"+tenantCode+"' and cs.status='"+status+"' ");
		
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(),TenantStockReportBO.class);
			lmoStockReport = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lmoStockReport;
	}
	
	//MSO CPE Stock
	@SuppressWarnings("unchecked")
	public List<TenantStockReportBO> getMSOStockReport(String tenantCode,String status) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<TenantStockReportBO> msoStockReport = new ArrayList<>();
		
		queryString.append("SELECT cs.cpeslno,cs.cpemacaddr,cpm.cpetypelov AS cpe_profile,cs.batchdate,cs.cafno,ts.tenantcode,ds.districtname,ms.mandalname,vs.villagename ");
		queryString.append("FROM CPESTOCK cs ");
		queryString.append("LEFT JOIN cpe_profilemaster cpm ON cs.profile_id=cpm.profile_id ");
		queryString.append("LEFT JOIN tenants ts ON   cs.MSPCODE=ts.tenantcode AND ts.tenanttypelov='MSP' ");
		queryString.append("LEFT JOIN districts ds ON ds.districtuid=ts.portal_districtid ");
		queryString.append("LEFT JOIN mandals ms ON ms.mandalslno=ts.portal_mandalid AND ds.districtuid=ms.districtuid ");
		queryString.append("LEFT JOIN villages vs ON vs.districtuid=ds.districtuid AND vs.mandalslno=ms.mandalslno AND  ");
		queryString.append("ts.portal_villageid=vs.villageslno AND vs.districtuid=ts.portal_districtid AND vs.mandalslno=ts.portal_mandalid ");
		queryString.append("WHERE ts.tenantcode IS NOT NULL AND cs.MSPCODE LIKE '%MSO%' AND cs.lmocode IS NULL AND cs.MSPCODE='"+tenantCode+"' AND cs.status = '"+status+"' ");
		
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(),TenantStockReportBO.class);
			msoStockReport = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msoStockReport;
	}
	
	//Mso Wise Stock Count
	@SuppressWarnings("unchecked")
	public List<MsoCafNotCpeStockBo> getMsoWiseCpeStock() {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<MsoCafNotCpeStockBo> MsoCafNotCpeStockList = new ArrayList<>();

		queryString.append("SELECT ts.tenantcode,ts.tenantname,ts.regoff_pocname AS MSO_Name,ts.regoff_pocmob1 AS MSO_MobileNO,ds.districtname AS districtname,ms.mandalname AS Mso_Mandalname,vs.villagename AS Mso_Villagename, ");
		//queryString.append("-- SUM(CASE WHEN cs.STATUS = 4 THEN 1 ELSE 0 END) CAF_Done_Stock_Count , ");
		queryString.append("SUM(CASE WHEN cs.STATUS = 2  THEN 1 ELSE 0 END) AS CAF_Not_Stock_Count  ");
		queryString.append(" FROM CPESTOCK cs ");
		queryString.append("LEFT JOIN tenants ts ON   cs.MSPCODE=ts.tenantcode AND ts.tenanttypelov='MSP' ");
		queryString.append(" LEFT JOIN districts ds ON ds.districtuid=ts.portal_districtid ");
		queryString.append("LEFT JOIN mandals ms ON ms.mandalslno=ts.portal_mandalid AND ds.districtuid=ms.districtuid ");
		queryString.append("LEFT JOIN villages vs ON vs.districtuid=ds.districtuid AND vs.mandalslno=ms.mandalslno AND  ");
		queryString.append("ts.portal_villageid=vs.villageslno AND vs.districtuid=ts.portal_districtid AND vs.mandalslno=ts.portal_mandalid ");
		queryString.append("WHERE ts.tenantcode IS NOT NULL AND cs.MSPCODE LIKE '%MSO%' AND cs.lmocode IS NULL ");
		queryString.append("GROUP BY ts.tenantcode,ts.tenantname,ts.regoff_pocname,ts.regoff_pocmob1 ");

		try {
			query = getEntityManager().createNativeQuery(queryString.toString(), MsoCafNotCpeStockBo.class);
			MsoCafNotCpeStockList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getMsoWiseCpeStockDetails" + e);
		}
		return MsoCafNotCpeStockList;
	}
	
	//LMO Details of Loggedin MSO
	@SuppressWarnings("unchecked")
	public List<MsoDetailsWithLmosBO> getLmoDetailsOfLoggedinMso(String msoCode) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<MsoDetailsWithLmosBO> listMsoWiseLmo = new ArrayList<>();

		queryString.append("SELECT DISTINCT TE.tenantname AS MSONAME,REPLACE(REPLACE(SUBSTRING_INDEX(r.partnerlist, ',', -1),']',''),' ','') MSO,  ");
		queryString.append("TE.regoff_pocname AS MSO_ContactName,TE.regoff_pocmob1 AS MSO_MobileNo,ds.districtname AS mso_district,ms.mandalname AS mso_mandal,vs.villagename AS mso_villagename,TE.createdon AS mso_createdon,T.tenantname AS LMONAME, ");
		queryString.append("REPLACE(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(r.partnerlist, ',', 2), ',', -1), ']', '' ),' ','') LMO, T.regoff_pocname AS LMO_ContactName, ");
		queryString.append("T.regoff_pocmob1 AS LMO_MobileNo,dt.districtname AS lmo_district,mt.mandalname AS lmo_mandal,vt.villagename AS lmo_villagename, T.createdon AS lmo_createdon ");
		queryString.append("FROM RSAGRMNTS r "); 
		queryString.append("LEFT JOIN tenants T ON T.tenantcode = REPLACE(REPLACE(SUBSTRING_INDEX(SUBSTRING_INDEX(r.partnerlist, ',', 2), ',', -1), ']', '' ),' ','') ");
		queryString.append("LEFT JOIN tenants TE ON TE.tenantcode = REPLACE(REPLACE(SUBSTRING_INDEX(r.partnerlist, ',', -1),']',''),' ','') ");
		queryString.append("LEFT JOIN districts ds ON ds.districtuid=te.portal_districtid ");
		queryString.append("LEFT JOIN mandals ms ON ms.mandalslno=te.portal_mandalid AND ds.districtuid=ms.districtuid ");
		queryString.append("LEFT JOIN villages vs ON vs.districtuid=ds.districtuid AND vs.mandalslno=ms.mandalslno AND te.portal_villageid=vs.villageslno AND vs.districtuid=te.portal_districtid AND vs.mandalslno=te.portal_mandalid ");
		queryString.append("LEFT JOIN districts dt ON dt.districtuid=t.portal_districtid ");
		queryString.append("LEFT JOIN mandals mt ON mt.mandalslno=t.portal_mandalid AND dt.districtuid=mt.districtuid ");
		queryString.append("LEFT JOIN villages vt ON vt.districtuid=dt.districtuid AND vt.mandalslno=mt.mandalslno AND t.portal_villageid=vt.villageslno AND vt.districtuid=t.portal_districtid AND vt.mandalslno=t.portal_mandalid ");
		queryString.append("WHERE partnerlist LIKE '%MSO%' AND TE.tenantcode = :msoCode ");
		queryString.append("GROUP BY TE.tenantname,MSO,TE.regoff_pocname,TE.regoff_pocmob1,T.tenantname,LMO,T.regoff_pocname,T.regoff_pocmob1, ");
		queryString.append("ds.districtname,ms.mandalname,dt.districtname,mt.mandalname,vs.villagename,vt.villagename,TE.createdon,T.createdon ");
		queryString.append("ORDER BY TE.tenantname ");

		try {
			query = getEntityManager().createNativeQuery(queryString.toString(), MsoDetailsWithLmosBO.class);
			query.setParameter("msoCode", msoCode);
			listMsoWiseLmo = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLmoDetailsOfLoggedinMso()" + e);
		}
		return listMsoWiseLmo;
	}
	
	//LMO Caf Details of Login MSO
	@SuppressWarnings("unchecked")
	public List<ComsHelperDTO> getLmoCafDetailsOfLoginMso(String lmoCode) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<ComsHelperDTO> lmoCafsMsolog = new ArrayList<>();

		queryString.append("SELECT cf.cafno, cust.depbal, cust.regbal, cf.lmocode, cf.contactmob, cust.custname, cust.mname, IFNULL(cust.lname, '') lname, cust.custid, ");
		queryString.append("cf.cpeslno, CASE WHEN cf.status = 6 THEN 'Active' WHEN cf.status = 2 THEN 'Pending For Provisioning'  WHEN cf.status = 7 THEN 'Suspended' ");
		queryString.append("WHEN cf.status = 8 THEN 'De-activated' END STATUS, IFNULL(DATE_FORMAT(cf.actdate, '%d/%m/%Y'), 'NA') actdate, cust.aadharno, ");
		queryString.append("cf.apsfluniqueid FROM cafs cf, customermst cust WHERE cf.custid = cust.custid AND cf.lmocode = :lmoCode ");

		try {
			query = getEntityManager().createNativeQuery(queryString.toString());
			query.setParameter("lmoCode", lmoCode);
			lmoCafsMsolog = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLmoDetailsOfLoggedinMso()" + e);
		}
		return lmoCafsMsolog;
	}
	
	//MSO Revenue share Details
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MsoRevenueShareBO> getRevenueShareDetailsOfLoginMso(String year,String month, String tenantCode) {
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		List<MsoRevenueShareBO> revenueList = new ArrayList<>();
		
		if(tenantCode.startsWith("MSO")){
			queryString.append("select lmocode,apsflinv_share,msoinv_share,lmoinv_share,(apsflinv_share + msoinv_share + lmoinv_share) as totalbill,lmo_total_collected,(apsflinv_share + msoinv_share + lmoinv_share-lmo_total_collected) as yettocollect,caf_count,credit_limit ");
			queryString.append("from rev_summarytable where msocode =:tenantCode and invyr =:year and invmn =:month ");
		}else{
			queryString.append("select lmocode,apsflinv_share,msoinv_share,lmoinv_share,(apsflinv_share + msoinv_share + lmoinv_share) as totalbill,lmo_total_collected,(apsflinv_share + msoinv_share + lmoinv_share-lmo_total_collected) as yettocollect,caf_count,credit_limit ");
			queryString.append("from rev_summarytable where lmocode =:tenantCode and invyr =:year and invmn =:month group by lmocode,apsflinv_share,msoinv_share,lmoinv_share,totalbill,lmo_total_collected,yettocollect,caf_count,credit_limit ");
		}
		
		
		
/*if(tenantCode.startsWith("MSO")){
			queryString.append("select lmocode,apsflinv_share,msoinv_share,lmoinv_share,(apsflinv_share + msoinv_share + lmoinv_share) as totalbill,(SELECT ifnull(sum(cu.invamt +cu.srvctax),0.00) FROM   custinv cu,  cafs cf ");      
					queryString.append("	WHERE   cu.prevpaidamt = 1    AND cu.invyr = :year   AND cu.invmn = :month     AND cu.pmntcustid = cf.custid and   ");  
							queryString.append("	cf.lmocode= :tenantcode  ) as lmo_total_collected,(apsflinv_share + msoinv_share + lmoinv_share) as yettocollect,caf_count,(select (tw.crlimitamt + tw.walletamt - tw.usedamt) from tenantswallet tw where tw.tenantcode=:tenantCode) as credit_limit ");
			queryString.append(" from rev_summarytable where msocode =:tenantCode and invyr =:year and invmn =:month ");
		}else{
			queryString.append("select lmocode,apsflinv_share,msoinv_share,lmoinv_share,(apsflinv_share + msoinv_share + lmoinv_share) as totalbill,(SELECT ifnull(sum(cu.invamt +cu.srvctax),0.00) FROM   custinv cu,  cafs cf ");      
			queryString.append("	WHERE   cu.prevpaidamt = 1    AND cu.invyr = :year   AND cu.invmn = :month     AND cu.pmntcustid = cf.custid and  ");  
					queryString.append("	cf.lmocode=:tenantCode  ) as lmo_total_collected,(apsflinv_share + msoinv_share + lmoinv_share) as yettocollect,caf_count,(select (tw.crlimitamt + tw.walletamt - tw.usedamt) from tenantswallet tw where tw.tenantcode=:tenantCode) as credit_limit ");			queryString.append("from rev_summarytable where lmocode =:tenantCode and invyr =:year and invmn =:month group by lmocode,apsflinv_share,msoinv_share,lmoinv_share,totalbill,lmo_total_collected,yettocollect,caf_count,credit_limit ");
		}*/
		
		
		
		
		try {
			query = getEntityManager().createNativeQuery(queryString.toString(), MsoRevenueShareBO.class);
			query.setParameter("year", year);
			query.setParameter("month", month);
			query.setParameter("tenantCode", tenantCode);
			revenueList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLmoDetailsOfLoggedinMso()" + e);
		}
		return revenueList;
	}
	
	
	
		@SuppressWarnings("unchecked")
		@Transactional
		public String getTotalPreviousBalance(String year,String month, String tenantCode) {
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			String totalPrevBal="";
			
				queryString.append("SELECT sum(cu.prevbal) as totalsum FROM custinv cu,cafs cf WHERE cu.prevpaidamt = '0'  and (cu.invamt + cu.srvctax+cu.prevbal-cu.prevpymtreceived) > '0' ");
				queryString.append(" AND cu.pmntcustid = cf.custid AND  lmocode =:tenantCode and invyr =:year and invmn =:month and cf.custid is not null GROUP BY cf.lmocode");
       
			
			
			try {
				query = getEntityManager().createNativeQuery(queryString.toString());
				query.setParameter("year", year);
				query.setParameter("month", month);
				query.setParameter("tenantCode", tenantCode);
				totalPrevBal = query.getResultList().get(0).toString();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getTotalPreviousBalance()" + e);
			}
			return totalPrevBal;
		}

	
	// login LMO Revenue Caf wise in Details 
		@Transactional
		@SuppressWarnings("unchecked")
		public List<CafWiseRevenueOfLoginLmoBo> getRevenueDetailsCafwiseOfLoginLmo(String year,String month, String lmoCode) {
			logger.info("getRevenueCafwiseDetailsOfLoginLmo():start");
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			List<CafWiseRevenueOfLoginLmoBo> lmoCafwiserevnue = new ArrayList<>();
			

			/*queryString.append("SELECT cf.cafno, cust.depbal, cust.regbal, cf.lmocode, cf.contactmob, cust.custname, cust.mname, IFNULL(cust.lname, '') lname, cust.custid, ");
			queryString.append("cf.cpeslno, CASE WHEN cf.status = 6 THEN 'Active' WHEN cf.status = 2 THEN 'Pending For Provisioning'  WHEN cf.status = 7 THEN 'Suspended' ");
			queryString.append("WHEN cf.status = 8 THEN 'De-activated' END STATUS, IFNULL(DATE_FORMAT(cf.actdate, '%d/%m/%Y'), 'NA') actdate, cust.aadharno, ");
			queryString.append("cf.apsfluniqueid FROM cafs cf, customermst cust WHERE cf.custid = cust.custid AND cf.lmocode = :lmoCode ");*/
			
			
			/*queryString.append(" SELECT DISTINCT cf.cafno as cafno,cf.lmocode as lmocode,c.custname as custname,");
			queryString.append(" DATE_FORMAT(cf.cafdate,'%d/%m/%Y') cafDate,cf.contactmob as contactmob,c.depbal as depbal, ");
			queryString.append(" IFNULL((SELECT SUM(apsflshareamt) FROM invamtsharedtls invs WHERE cf.cafno=invs.cafno AND invyr='"+year+"'  AND invmn='"+month+"'  GROUP BY invs.cafno),0.00) AS APSFLShare,");
			queryString.append(" IFNULL((SELECT SUM(msoshareamt) FROM invamtsharedtls invs WHERE cf.cafno=invs.cafno AND invyr='"+year+"'   AND invmn='"+month+"'  GROUP BY invs.cafno),0.00) AS MSOShare,");
			queryString.append(" IFNULL((SELECT SUM(lmoshareamt) FROM invamtsharedtls invs WHERE cf.cafno=invs.cafno AND invyr= '"+year+"' AND invmn='"+month+"' GROUP BY invs.cafno),0.00) AS LMOShare,");
			queryString.append(" IFNULL((SELECT SUM(invamt+srvctax) FROM cafinv WHERE pmntcustid=cf.pmntcustid AND acctcafno=cf.cafno AND invyr='"+year+"' AND invmn='"+month+"' ),0.00) AS total,");
			queryString.append(" IFNULL((SELECT prevbal FROM custinv cus WHERE cus.prevpaidamt='0' AND (cus.invamt + cus.srvctax+cus.prevbal-cus.prevpymtreceived) > '0' AND cus.pmntcustid=cf.pmntcustid AND invyr='"+year+"' AND invmn='"+month+"' GROUP BY cus.pmntcustid, prevbal),'0.00') AS PreviousBalance ");
			//queryString.append("  IFNULL((SELECT IF( cus.prevpaidamt = '0', 'Not Paid', 'Paid') FROM custinv cus WHERE cus.pmntcustid = cf.pmntcustid ");
			//queryString.append(" AND invyr = '"+year+"' AND invmn = '"+month+"' GROUP BY cus.pmntcustid), 0.00) AS paymentstatus ");
			queryString.append(" FROM cafs cf, cafinv cfv,customermst c ,wipstages w WHERE cf.custid = c.custid AND cf.cafno=cfv.acctcafno " );
			queryString.append(" AND cfv.invyr='"+year+"' AND invmn='"+month+"' AND w.appcode = 'COMS' AND w.statuscode = cf.status  AND cf.lmocode='"+lmoCode+"'");*/
			
			
			queryString.append("	SELECT DISTINCT     cf.cafno AS cafno,     cf.lmocode AS lmocode,     c.custname AS custname, ");  
			queryString.append(" DATE_FORMAT(cf.cafdate, '%d/%m/%Y') cafDate,     cf.contactmob AS contactmob,    SUM(invs.apsflshareamt) AS APSFLShare, "); 
			queryString.append(" SUM(msoshareamt) AS MSOShare,    SUM(lmoshareamt) AS LMOShare,     IFNULL((SELECT SUM(invamt + srvctax)   FROM  cafinv ");  
			queryString.append("  WHERE   pmntcustid = cf.pmntcustid    AND acctcafno = cf.cafno   AND invyr = '"+year+"'  AND invmn = '"+month+"'),   0.00) AS total, ");
			queryString.append(" IFNULL((SELECT prevbal   FROM   custinv cus  WHERE  cus.prevpaidamt = '0'  AND (cus.invamt + cus.srvctax + cus.prevbal ) > '0'  ");
			queryString.append("  AND cus.pmntcustid = cf.pmntcustid   AND invyr = '"+year+"'   AND invmn = '"+month+"'   GROUP BY cus.pmntcustid,prevbal),  '0.00') AS PreviousBalance , ");
			queryString.append("  IFNULL((SELECT IF( cus.prevpaidamt = '0', 'Not Paid', 'Paid') FROM custinv cus WHERE cus.pmntcustid = cf.pmntcustid ");
			queryString.append(" AND invyr = '"+year+"' AND invmn = '"+month+"' GROUP BY cus.pmntcustid,cus.prevpaidamt), 0.00) AS paymentstatus ");
			queryString.append(" FROM     cafs cf,     cafinv cfv,     customermst c,   invamtsharedtls invs WHERE     cf.custid = c.custid ");    
			queryString.append("  AND cf.cafno = cfv.acctcafno         AND cfv.invyr = '"+year+"'        AND cfv.invmn = '"+month+"'         AND invs.invyr = '"+year+"'  ");       
			queryString.append("  AND invs.invmn = '"+month+"'          AND cf.lmocode ='"+lmoCode+"' ");    
			queryString.append(" AND cf.cafno = invs.cafno GROUP BY  cf.cafno ");  


			try {
				query = getEntityManager().createNativeQuery(queryString.toString(),CafWiseRevenueOfLoginLmoBo.class);
				/*query.setParameter("lmocode", lmoCode);
				query.setParameter("invyr", year);
				query.setParameter("invmn", month);*/
				lmoCafwiserevnue = query.getResultList();
	
				
			
				logger.info("getRevenueDetailsCafwiseOfLoginLmo():END");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getRevenueDetailsCafwiseOfLoginLmo()" + e);
			}
			return lmoCafwiserevnue;
		}
		
		//District, Mandal, Village, Wise, Caf, Count till date Details
		@SuppressWarnings("unchecked")
		public List<LmoStockCountBO> getDistrictMandalVillageWiseCafCountTilldate(){
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			List<Object[]>temp=new ArrayList<>();
			List<LmoStockCountBO> lmoWiseStockCount = new ArrayList<>();
			
			queryString.append(" SELECT DISTINCT(vs.villagename),ms.mandalname,ds.districtname,COUNT(cf.cafno) FROM cafs cf,districts ds,mandals ms,villages vs  ");
			queryString.append(" WHERE cf.inst_district=ds.districtuid "); 
			queryString.append(" AND cf.inst_district=ms.districtuid ");
			queryString.append(" AND cf.inst_mandal=ms.mandalslno ");
			queryString.append(" AND cf.inst_district=vs.districtuid ");
			queryString.append(" AND cf.inst_mandal=vs.mandalslno ");
			queryString.append(" AND cf.inst_city_village=vs.villageslno ");
			queryString.append(" AND CF.status IN('6','2') ");
			queryString.append(" GROUP BY ds.districtname,ms.mandalname,vs.villagename ");
			
			try {
				logger.info("getDistrictMandalVillageWiseCafCountTilldate():start");
                query = getEntityManager().createNativeQuery(queryString.toString());
				temp = query.getResultList();
				
				for(Object[] i:temp) {
					LmoStockCountBO rec=new LmoStockCountBO();
					rec.setVillage(i[0] == null ? null : i[0].toString());
					rec.setMandal(i[1] == null ? null : i[1].toString());
					rec.setDistrict(i[2] == null ? null : i[2].toString());
					rec.setCafCount(Long.valueOf(i[3] == null ? null : i[3].toString()));
					
					lmoWiseStockCount.add(rec);	
					logger.info("getDistrictMandalVillageWiseCafCountTilldate():End");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getDistrictMandalVillageWiseCafCountTilldate():End"+e);
			}finally {
				query = null;
				queryString = null;
			
			}
			return lmoWiseStockCount;
		}
		
		//District, Mandal, Village, Wise, Caf, Count Details
				@SuppressWarnings("unchecked")
				public List<LmoStockCountBO> getDistrictMandalVillageWiseCafCount(String from,String to,String district,String mandal,String village){
					StringBuilder queryString = new StringBuilder();
					Query query = null;
					String whereClause = ""; 
					List<Object[]>temp=new ArrayList<>();
					List<LmoStockCountBO> lmoWiseStockCount = new ArrayList<>();
					
				
					

					
					if((district != null && !district.isEmpty()) && (mandal != null && !mandal.isEmpty()) && (village != null && !village.isEmpty())){
						
						whereClause = " AND d.districtuid='"+district+"' and m.mandalslno='"+mandal+"' and v.villageslno='"+village+"' ";

					}

						else if((district != null && !district.isEmpty()) && (mandal != null && !mandal.isEmpty())){
							
							whereClause = " AND d.districtuid='"+district+"' and m.mandalslno='"+mandal+"' ";
				
					}

					else if(district != null && !district.isEmpty()){
						
						whereClause = " AND d.districtuid='"+district+"' ";
						
					}
					
					else{
						
						whereClause = "";
						
					}
					
					
					queryString.append(" SELECT d.districtname,m.mandalname,v.villagename,COUNT(c.cafno),DATE_FORMAT(c.cafdate,'%Y-%m-%d')CAF_Created_DATE  FROM cafs c ");  
					queryString.append(" LEFT JOIN oltportdtls olt ON olt.lmocode=c.lmocode AND olt.portno=c.olt_portid AND olt.pop_olt_serialno=c.olt_id AND c.olt_cardno=olt.cardid ");  
					queryString.append(" LEFT JOIN oltmaster olm ON olm.pop_olt_serialno=olt.pop_olt_serialno AND olm.pop_olt_ipaddress=c.oltipaddr ");  
					queryString.append(" LEFT JOIN districts d ON d.districtuid=inst_district ");  
					queryString.append(" LEFT JOIN mandals m ON m.mandalslno=c.inst_mandal AND d.districtuid=m.districtuid  ");
					queryString.append(" LEFT JOIN villages v ON v.villageslno=c.inst_city_village AND v.mandalslno=m.mandalslno AND v.districtuid=d.districtuid  ");
					queryString.append(" LEFT JOIN tenants t ON t.tenantcode=c.lmocode  "); 
					queryString.append(" LEFT JOIN substations sub ON sub.substnuid=olm.pop_substnuid  WHERE c.status IN ('6','2')  "); 
					queryString.append(whereClause);
					queryString.append(" GROUP BY  c.lmocode,d.districtname,m.mandalname,v.villagename,   ");
					queryString.append(" c.cafdate HAVING DATE(CAF_Created_DATE) BETWEEN '"+from+"' AND '"+to+"' ");
					
					try {
						logger.info("getDistrictMandalVillageWiseCafCount():start");
		                query = getEntityManager().createNativeQuery(queryString.toString());
						temp = query.getResultList();
						
						for(Object[] i:temp) {
							LmoStockCountBO rec=new LmoStockCountBO();
							rec.setDistrict(i[0] == null ? null : i[0].toString());
							rec.setMandal(i[1] == null ? null : i[1].toString());
							rec.setVillage(i[2] == null ? null : i[2].toString());
							rec.setCafCount(Long.valueOf(i[3] == null ? null : i[3].toString()));
							rec.setCreateddate(i[4] == null ? null : i[4].toString());
							
							lmoWiseStockCount.add(rec);	
							logger.info("getDistrictMandalVillageWiseCafCount():End");
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("getDistrictMandalVillageWiseCafCount():End"+e);
					}finally {
						query = null;
						queryString = null;
						whereClause = null;
					
					}
					return lmoWiseStockCount;
				}
		
		// districtMandalVillageCustomerCafReport
		@SuppressWarnings("unchecked")
		public List<CustomerDTO> districtMandalVillageCustomerCafReport(String cafDate, String district, String mandal, String village) {
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			List<Object[]>temp=new ArrayList<>();
			List<CustomerDTO> lmoWiseStockCount = new ArrayList<>();
			

			
			queryString.append(" SELECT cafno, custname, cf.aadharno, cafdate, pocmob1 ");
			queryString.append(" FROM cafs cf, customermst mst,districts d,mandals m,villages v ");
			queryString.append(" WHERE cf.aadharno = mst.aadharno AND cf.custid=mst.custid ");
			
			queryString.append(" AND cf.inst_district=d.districtuid AND cf.inst_mandal=m.mandalslno AND m.districtuid=d.districtuid ");
			queryString.append(" AND cf.inst_city_village=v.villageslno AND v.mandalslno=m.mandalslno AND v.districtuid=d.districtuid ");
			queryString.append("  AND cf.status in ('6','2') AND d.districtname='"+district+"' AND m.mandalname='"+mandal+"' AND v.villagename='"+village+"' ");
			queryString.append("  AND DATE(cafdate) = '"+cafDate+"' ");
			
	
			try {
				query = getEntityManager().createNativeQuery(queryString.toString());
				temp = query.getResultList();
				for(Object[] i:temp) {
					String[] rec=new String[i.length];
					rec[0]=(i[0]==null?"NA":i[0].toString());
					rec[1]=(i[1]==null?"NA":i[1].toString());
					rec[2]=(i[2]==null?"NA":i[2].toString());
					rec[3]=(i[3]==null?"NA":i[3].toString());
					rec[4]=(i[4]==null?"NA":i[4].toString());
					CustomerDTO rec1=new CustomerDTO();
					rec1.setCustid(rec[0]);
					rec1.setCustType(rec[1]);
					rec1.setAadharno(rec[2]);
					rec1.setDob(rec[3]);
					rec1.setContactno(rec[4]);
					lmoWiseStockCount.add(rec1);	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("districtMandalVillageCustomerCafReport():End"+e);
			}finally {
				query = null;
				queryString = null;
				
			}
			return lmoWiseStockCount;
		}
	
				@Transactional
		public int updateCheque_DDPayment(Offline_Payment pymt) {
					
					BigDecimal oldAmtWallet=null;
					BigDecimal oldAmtCrlimit=null;
					String newAmtWallet="";
					String newAmtCrlimit="";
					java.util.Date currDate= new java.util.Date();
					TenantWallet tenantWallet = new TenantWallet();
					
					
						
			try{
				tenantWallet = tenantWalletDao.findByTenantCode(pymt.getLmocode());
				oldAmtWallet=tenantWallet.getWalletAmount();
				oldAmtCrlimit=tenantWallet.getCrLimitAmt();
				
				newAmtWallet=String.valueOf(oldAmtWallet.add(new BigDecimal(pymt.getAmount())));
				newAmtCrlimit=	String.valueOf(oldAmtCrlimit.add(new BigDecimal(pymt.getAmount())));
				String transid="";
				String dep_date="";
				String com="";
				String setquery;
			

				int status=0;
				if(pymt.getTrans_id()==null)  transid+="(null)"; else transid=pymt.getTrans_id();
				if(pymt.getComments()==null)  com+="(null)"; else com=pymt.getComments();
				if(pymt.getDep_date()==null) setquery="UPDATE offline_payment SET STATUS = '"+pymt.getStatus()+"' ,comments = '"+com+"',dep_date="+(null)+",trans_id="+transid+"  WHERE id= '"+pymt.getId()+"'";
				else setquery="UPDATE offline_payment SET STATUS = '"+pymt.getStatus()+"' ,comments = '"+com+"',dep_date= '"+pymt.getDep_date()+"',trans_id='"+transid+"'  WHERE id= '"+pymt.getId()+"'";
				String query="";
				Query qry = null;
				
				if(setquery!="") {
					query=setquery;
					getEntityManager().createNativeQuery(query).executeUpdate();
					if(pymt.getStatus()!=2) {
				//	query="UPDATE tenantswallet SET deposit_amt = "+ pymt.getAmount()+"  WHERE tenantcode= '"+pymt.getLmocode()+"'";
					query="UPDATE tenantswallet SET wallet_lastcramt = "+ pymt.getAmount()+" ,wallet_lastcrdate=CURDATE(), walletamt= "+ newAmtWallet+" , crlimitamt= "+ newAmtCrlimit+" WHERE tenantcode= '"+pymt.getLmocode()+"' ";
					getEntityManager().createNativeQuery(query).executeUpdate();

					//SG: inserting into tenantswallettrans as well
					logger.info("Inserting into tenantwallettrans ");
					query ="insert into tenantswallettrans (trandate, tenantcode, crdb_flag, tranamt,tranmode,tranrefno,trantype) values (?,?,?,?,?,?,?)";
					qry = getEntityManager().createNativeQuery(query);
					qry.setParameter(1, currDate);
					qry.setParameter(2, pymt.getLmocode());
					qry.setParameter(3, "C");
					qry.setParameter(4, pymt.getAmount());
					qry.setParameter(5, "DD");
					qry.setParameter(6, pymt.getTrans_id());
					qry.setParameter(7, "wallet");
					
					
					qry.executeUpdate();
					
					
					
					}
					return 1;
				}
			}catch(Exception e){
					return 0;
			}
			return 1;
		}
				
				@Transactional
				public int updateBulkCheque_DDPayment(Offline_Payment pymt) {
					
					BigDecimal oldAmtWallet=null;
					BigDecimal oldAmtCrlimit=null;
					String newAmtWallet="";
					String newAmtCrlimit="";
					java.util.Date currDate= new java.util.Date();
					TenantWallet tenantWallet = new TenantWallet();
					
					Query qry = null;
					
					tenantWallet = tenantWalletDao.findByTenantCode(pymt.getLmocode());
					oldAmtWallet=tenantWallet.getWalletAmount();
					oldAmtCrlimit=tenantWallet.getCrLimitAmt();
					
					newAmtWallet=String.valueOf(oldAmtWallet.add(new BigDecimal(pymt.getAmount())));
					newAmtCrlimit=	String.valueOf(oldAmtCrlimit.add(new BigDecimal(pymt.getAmount())));
					try{
						String transid="";
						String dep_date="";
						String com="";
						String setquery;
						int status=0;
						if(pymt.getTrans_id()==null)  transid+="(null)"; else transid=pymt.getTrans_id();
						if(pymt.getComments()==null)  com+="(null)"; else com=pymt.getComments();
						if(pymt.getDep_date()==null) setquery="UPDATE offline_payment SET STATUS = '"+pymt.getStatus()+"' ,comments = '"+com+"',dep_date="+(null)+",trans_id="+transid+"  WHERE id= '"+pymt.getId()+"'";
						else setquery="UPDATE offline_payment SET STATUS = '"+pymt.getStatus()+"' ,comments = '"+com+"',dep_date= '"+pymt.getDep_date()+"',trans_id='"+transid+"'  WHERE id= '"+pymt.getId()+"'";
						String query="";
						
						if(setquery!="") {
							query=setquery;
							getEntityManager().createNativeQuery(query).executeUpdate();
							if(pymt.getStatus()!=2) {
								query="UPDATE tenantswallet SET wallet_lastcramt = "+ pymt.getAmount()+" ,wallet_lastcrdate=CURDATE(), walletamt= "+ newAmtWallet+" , crlimitamt= "+ newAmtCrlimit+" WHERE tenantcode= '"+pymt.getLmocode()+"' ";
								getEntityManager().createNativeQuery(query).executeUpdate();

								//SG: inserting into tenantswallettrans as well
								logger.info("Inserting into tenantwallettrans ");
								query ="insert into tenantswallettrans (trandate, tenantcode, crdb_flag, tranamt,tranmode,tranrefno,trantype) values (?,?,?,?,?,?,?)";
								qry = getEntityManager().createNativeQuery(query);
								qry.setParameter(1, currDate);
								qry.setParameter(2, pymt.getLmocode());
								qry.setParameter(3, "C");
								qry.setParameter(4, pymt.getAmount());
								qry.setParameter(5, "DD");
								qry.setParameter(6, pymt.getTrans_id());
								qry.setParameter(7, "wallet");
								
								
								qry.executeUpdate();
								
							}
							return 1;
						}
					}catch(Exception e){
							return 0;
					}
					return 1;
				}
		
		@SuppressWarnings("unchecked")
		public List<Offline_Payment> getOfflinePaymentChequeDetails() {
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			List<Offline_Payment> ChequeList = new ArrayList<>();
			List<Object[]>temp=new ArrayList<>();
				queryString.append("select * from offline_payment  where status in (1,2) order by id desc ");
			try {
				query = getEntityManager().createNativeQuery(queryString.toString());
				temp = query.getResultList();
				for(Object[] i:temp) {
					Offline_Payment temp1=new Offline_Payment();
					if(i[0]!=null )temp1.setId(Long.valueOf(i[0].toString()));
					if(i[1]!=null ) temp1.setCheque_ddno(i[1].toString());
					if(i[2]!=null ) temp1.setAmount(Double.valueOf(i[2].toString()));
					if(i[3]!=null ) temp1.setLmocode(i[3].toString());
					if(i[4]!=null ) temp1.setBankname(i[4].toString());
					if(i[5]!=null ) temp1.setReceived_date(i[5].toString());
					if(i[6]!=null ) temp1.setModified_on(Date.valueOf(i[6].toString()));
					if(i[7]!=null ) temp1.setDep_date(Date.valueOf(i[7].toString()));
					if(i[8]!=null ) temp1.setClearing_date(Date.valueOf(i[8].toString()));
					if(i[9]!=null ) temp1.setValid_date(Date.valueOf(i[9].toString()));
					if(i[10]!=null ) temp1.setTrans_id(i[10].toString());
					if(i[11]!=null ) temp1.setStatus(Integer.parseInt(i[11].toString()));
					if(i[12]!=null ) temp1.setApprover_1(i[12].toString());
					if(i[13]!=null ) temp1.setApprover_2(i[13].toString());
					if(i[14]!=null ) temp1.setComments(i[14].toString());
					if(i[15]!=null ) temp1.setPayment_mode(i[15].toString());
					ChequeList.add(temp1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getOfflinePaymentChequeDetails()" + e);
			}finally {
				query = null;
				queryString = null;
				
			}
			return ChequeList;
		}
		
		
		//District wise pon wise Caf Count Details
		
		@SuppressWarnings("unchecked")
		public List<DistrictPonWiseCafCountBO> getDistrictWisePonWiseCafCount(String tenantcode, String from, String to, String lmocode) {
			logger.info("DemandNoteDaoImpl::getDistrictWisePonWiseCafCount():start");
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			List<DistrictPonWiseCafCountBO> districtPonWiseCafCountBO = new ArrayList<>();
			String whereClause="";
			
			if((from != null && !from.isEmpty()) && (to != null && !to.isEmpty()) && (lmocode != null && !lmocode.isEmpty())){
				
				whereClause = " AND c.lmocode = '"+lmocode+"' AND c.cafdate BETWEEN '"+from+"' AND '"+to+"' "
						+ " GROUP BY c.lmocode ,olt.portno , olm.pop_name , olm.pop_olt_serialno , d.districtname , m.mandalname , v.villagename , t.tenantname , olm.pop_olt_ipaddress , olm.pop_name ";
                               

			}

				else if((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())){
					
					whereClause =  " AND c.cafdate BETWEEN '"+from+"' AND '"+to+"' "
							+" GROUP BY c.lmocode ,olt.portno , olm.pop_name , olm.pop_olt_serialno , d.districtname , m.mandalname , v.villagename , t.tenantname , olm.pop_olt_ipaddress , olm.pop_name ";
                            
		
			}

			else if(lmocode != null && !lmocode.isEmpty()){
				
				whereClause = " AND c.lmocode = '"+lmocode+"' "
						+ " GROUP BY c.lmocode ,olt.portno , olm.pop_name , olm.pop_olt_serialno , d.districtname , m.mandalname , v.villagename , t.tenantname , olm.pop_olt_ipaddress , olm.pop_name ";
				
			}else{
				whereClause = " GROUP BY c.lmocode ,olt.portno , olm.pop_name , olm.pop_olt_serialno , d.districtname , m.mandalname , v.villagename , t.tenantname , olm.pop_olt_ipaddress , olm.pop_name "; 
				
			}
			

			//District PON wise Data  respective district.
					
					queryString.append(" SELECT d.districtname,m.mandalname,v.villagename, c.lmocode,olm.pop_name,olt.portno,COUNT(c.cafno) FROM cafs c ");
					queryString.append(" LEFT JOIN oltportdtls olt ON olt.lmocode=c.lmocode AND olt.portno=c.olt_portid AND olt.pop_olt_serialno=c.olt_id AND c.olt_cardno=olt.cardid ");
					queryString.append(" LEFT JOIN oltmaster olm ON olm.pop_olt_serialno=olt.pop_olt_serialno AND olm.pop_olt_ipaddress=c.oltipaddr ");
					queryString.append(" LEFT JOIN districts d ON d.districtuid=inst_district ");
					queryString.append(" LEFT JOIN mandals m ON m.mandalslno=c.inst_mandal AND d.districtuid=m.districtuid ");
					queryString.append(" LEFT JOIN villages v ON v.villageslno=c.inst_city_village AND v.mandalslno=m.mandalslno AND v.districtuid=d.districtuid ");
					queryString.append(" LEFT JOIN tenants t ON  t.portal_districtid = c.inst_district ");
					queryString.append(" LEFT JOIN substations sub ON sub.substnuid=olm.pop_substnuid ");
					queryString.append(" WHERE c.status IN ('6')  ");
					queryString.append(" AND t.tenantcode =:tenantcode");
					/*queryString.append("  AND c.lmocode LIKE '%LMO%' ");*/
					queryString.append(whereClause);
					


			try {
				query = getEntityManager().createNativeQuery(queryString.toString(),DistrictPonWiseCafCountBO.class);
				
				query.setParameter("tenantcode", tenantcode);

				districtPonWiseCafCountBO = query.getResultList();
	
				
			
				logger.info("DemandNoteDaoImpl::getDistrictWisePonWiseCafCount():END");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("DemandNoteDaoImpl::getDistrictWisePonWiseCafCount()" + e);
			}finally {
				query = null;
				queryString = null;
				
			}
			return districtPonWiseCafCountBO;
		}
		
		//
		@SuppressWarnings("unchecked")
		public List<LmoWalletUpdateByChequePaymentBO> getLmoWalletUpdateByChequePayment(String lmocode,String effectivefrom,String effectiveto,String cheque_ddno) {
			logger.info("DemandNoteDaoImpl::getLmoWalletUpdateByChequePayment():start");
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			String whereClause="";
			List<LmoWalletUpdateByChequePaymentBO> lmoWalletUpdateByChequePaymentList = new ArrayList<>();

			//Cheque payment third screen

		    
			if((lmocode != null && !lmocode.isEmpty()) && (effectivefrom != null && !effectivefrom.isEmpty()) && (effectiveto != null && !effectiveto.isEmpty()) && (cheque_ddno != null && !cheque_ddno.isEmpty())){
				
				whereClause = "  AND of.lmocode= '"+lmocode+"'AND of.cheque_ddno= '"+cheque_ddno+"' and of.dep_date BETWEEN '"+effectivefrom+"' AND '"+effectiveto+"' ";
				
			}else if((effectivefrom != null && !effectivefrom.isEmpty()) && (effectiveto != null && !effectiveto.isEmpty())){
				

				whereClause = " AND of.dep_date BETWEEN '"+effectivefrom+"' AND '"+effectiveto+"' ";
				
			}
			else if((lmocode != null && !lmocode.isEmpty())){

				whereClause = " AND of.lmocode= '"+lmocode+"' ";
				
            }else if((cheque_ddno != null && !cheque_ddno.isEmpty())){

            	whereClause = " AND of.cheque_ddno= '"+cheque_ddno+"' ";
            	
          }else{
        	
 
        	  whereClause = "";
        }
			queryString.append(" SELECT  of.cheque_ddno,of.amount,of.lmocode,of.bankname,of.payment_mode,of.status,of.dep_date,sum(tw.deposit_amt) as deposit_amt ");
		    queryString.append(" FROM offline_payment of,tenantswallet tw ");
		    queryString.append(" WHERE of.lmocode = tw.tenantcode ");
		    queryString.append(whereClause);
		    queryString.append(" GROUP BY of.lmocode,of.cheque_ddno,of.amount, of.bankname, of.payment_mode, of.status, of.dep_date,of.id ORDER BY of.id DESC ");

			try {
				query = getEntityManager().createNativeQuery(queryString.toString(),LmoWalletUpdateByChequePaymentBO.class);
				

				
				lmoWalletUpdateByChequePaymentList = query.getResultList();
	
				
			
				logger.info("DemandNoteDaoImpl::getLmoWalletUpdateByChequePayment():END");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("DemandNoteDaoImpl::getLmoWalletUpdateByChequePayment()" + e);
			}finally {
				query = null;
				queryString = null;
				
			}
			return lmoWalletUpdateByChequePaymentList;
		}
		
		@SuppressWarnings("unchecked")
		public List<Offline_Payment> getChequeDetails(String cheque_ddno) {
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			List<Offline_Payment> ChequeList = new ArrayList<>();
			List<Object[]>temp=new ArrayList<>();
				queryString.append("select * from offline_payment  where cheque_ddno=:cheque_ddno and status in (1,2) order by id desc ");
			try {
				query = getEntityManager().createNativeQuery(queryString.toString());
				
				query.setParameter("cheque_ddno", cheque_ddno);
				
				temp = query.getResultList();
				for(Object[] i:temp) {
					Offline_Payment temp1=new Offline_Payment();
					if(i[0]!=null )temp1.setId(Long.valueOf(i[0].toString()));
					if(i[1]!=null ) temp1.setCheque_ddno(i[1].toString());
					if(i[2]!=null ) temp1.setAmount(Double.valueOf(i[2].toString()));
					if(i[3]!=null ) temp1.setLmocode(i[3].toString());
					if(i[4]!=null ) temp1.setBankname(i[4].toString());
					if(i[5]!=null ) temp1.setReceived_date(i[5].toString());
					if(i[6]!=null ) temp1.setModified_on(Date.valueOf(i[6].toString()));
					if(i[7]!=null ) temp1.setDep_date(Date.valueOf(i[7].toString()));
					if(i[8]!=null ) temp1.setClearing_date(Date.valueOf(i[8].toString()));
					if(i[9]!=null ) temp1.setValid_date(Date.valueOf(i[9].toString()));
					if(i[10]!=null ) temp1.setTrans_id(i[10].toString());
					if(i[11]!=null ) temp1.setStatus(Integer.parseInt(i[11].toString()));
					if(i[12]!=null ) temp1.setApprover_1(i[12].toString());
					if(i[13]!=null ) temp1.setApprover_2(i[13].toString());
					if(i[14]!=null ) temp1.setComments(i[14].toString());
					if(i[15]!=null ) temp1.setPayment_mode(i[15].toString());
					ChequeList.add(temp1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("DemandNoteDaoImpl::getChequeDetailsdao()" + e);
			}finally {
				query = null;
				queryString = null;
				
			}
			return ChequeList;
		}
		
		
		
		@Transactional
		@SuppressWarnings("finally")
		public Boolean insertBulkCheque_DDPayment(Offline_Payment1 pymt) {
			Boolean status=false;
			try{
				logger.info("START::insertBulkCheque_DDPayment()");
				getEntityManager().persist(pymt);
				status=true;
			}
			catch(ConstraintViolationException e){
				logger.info("End:: in::insertBulkCheque_DDPayment()"+e.getConstraintViolations());
				status=false;
			}
			catch(Exception e){
				logger.info("End:: in::insertBulkCheque_DDPayment()");
				status=false;
			}finally {
				return status;
			}
		}
		
		
		// OLT Master Data Details report
		@SuppressWarnings("unchecked")
		public List<OLTMasterDataBO> getOLTMasterData(){
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			List<OLTMasterDataBO> oltmasterdatalist = new ArrayList<>();

			
			queryString.append("SELECT pop_substnuid,pop_name,pop_olt_serialno,pop_olt_ipaddress,d.districtname,m.mandalname FROM oltmaster olt ");
			queryString.append("LEFT JOIN substations sub ON olt.pop_substnuid=sub.substnuid ");
			queryString.append("LEFT JOIN districts d ON d.districtuid=sub.districtuid ");
			queryString.append("LEFT JOIN mandals m ON m.mandalslno=sub.mandalslno AND d.districtuid=m.districtuid ");
			queryString.append("GROUP BY pop_name,pop_substnuid,pop_olt_serialno,pop_olt_ipaddress,d.districtname,m.mandalname ");

			try {
				query = getEntityManager().createNativeQuery(queryString.toString(), OLTMasterDataBO.class);
				oltmasterdatalist = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("DemandNoteDaoImpl::getOLTMasterData():" + e);
			}
			return oltmasterdatalist;
		}
		
		// Rigged CAF report
		@SuppressWarnings("unchecked")
		public List<RiggedCafBO> getRiggedCafsDetails(String district, String mandal, String village, String from, String to){
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			String whereClause="";
			List<RiggedCafBO> riggedCafList = new ArrayList<>();
			
			
			if((district != null && !district.isEmpty()) && (mandal != null && !mandal.isEmpty()) && (village != null && !village.isEmpty()) && (from != null && !from.isEmpty()) && (to != null && !to.isEmpty())){
				
				whereClause = " AND d.districtuid='"+district+"' and m.mandalslno='"+mandal+"' and v.villageslno='"+village+"' AND c.cafdate BETWEEN '"+from+"' AND '"+to+"' ";

			}

				else if((district != null && !district.isEmpty()) && (mandal != null && !mandal.isEmpty()) && (from != null && !from.isEmpty()) && (to != null && !to.isEmpty())){
					
					whereClause = " AND d.districtuid='"+district+"' and m.mandalslno='"+mandal+"' AND c.cafdate BETWEEN '"+from+"' AND '"+to+"' ";
		
			}

			else if(district != null && !district.isEmpty() && (from != null && !from.isEmpty()) && (to != null && !to.isEmpty())){
				
				whereClause = " AND d.districtuid='"+district+"' AND c.cafdate BETWEEN '"+from+"' AND '"+to+"' ";
				
			}
			
			else if((district != null && !district.isEmpty()) && (mandal != null && !mandal.isEmpty()) && (village != null && !village.isEmpty())){
				
				whereClause = " AND d.districtuid='"+district+"' and m.mandalslno='"+mandal+"' and v.villageslno='"+village+"' ";
				
			}
			
			else if((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())){
				
				whereClause = " AND c.cafdate BETWEEN '"+from+"' AND '"+to+"' ";
				
			}
			
			else{
				
				whereClause = "";
				
			}

			
			queryString.append("select c.lmocode,t.tenantname AS networkname,t.regoff_pocname AS lmoname,t.regoff_pocmob1, ");
			queryString.append("d.districtname,m.mandalname, v.villagename, ");
			queryString.append("COUNT(cafno) AS Total_caf_count, ");
			queryString.append("ifnull ((select count(cafno) from riggedcafs where lmocode =c.lmocode AND districtname = d.districtname AND mandalname=m.mandalname  ");
			queryString.append("AND villagename = v.villagename group by districtname,mandalname,villagename),0)riggedcafs  ");
			queryString.append("from cafs c,tenants t,districts d,mandals m,villages v ");
			queryString.append("WHERE t.tenantcode=c.lmocode ");
			queryString.append("AND d.districtuid=c.inst_district ");
			queryString.append("AND m.mandalslno=c.inst_mandal AND d.districtuid=m.districtuid ");
			queryString.append("AND v.villageslno=c.inst_city_village AND v.mandalslno=m.mandalslno  ");
			queryString.append("AND v.districtuid=d.districtuid  ");
			queryString.append("and c.status='6' ");
			queryString.append(whereClause);
			queryString.append("group by c.lmocode,networkname,lmoname,t.regoff_pocmob1,d.districtname,m.mandalname,v.villagename ");

			try {
				query = getEntityManager().createNativeQuery(queryString.toString(), RiggedCafBO.class);
				riggedCafList = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("DemandNoteDaoImpl::getRiggedCafsDetails():" + e);
			}
			return riggedCafList;
		}
		
		// Created By SaiSumanth
				@SuppressWarnings("unchecked")
				public List<Object[]> tenantWalletTrans(String queryString) {
					Query query = null;
					List<Object[]> result = new ArrayList<>();
					try {
						logger.info("START::tenantWalletTrans()");
						query = getEntityManager().createNativeQuery(queryString.toString());
						result = query.getResultList();
						logger.info("query.........."+query);
						logger.info("result.........."+result);
						logger.info("END::tenantWalletTrans()");
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("EXCEPTION::tenantWalletTrans() " + e);
					} finally {
						query = null;
					}
					return result;
				}
				
		
		/*//Pon report for incentive
		
		@SuppressWarnings("unchecked")
		public List<PONWiseBo> getPonReportForIncentives(String district, String mandal, String from, String to, String lmocode) {
			StringBuilder queryString = new StringBuilder();
			Query query = null;
			List<PONWiseBo> listPON = new ArrayList<>();
			
				try {
					
				queryString.append("SELECT olm.pop_name,olm.pop_olt_ipaddress,olt.portno,c.lmocode,t.regoff_pocname,t.regoff_pocmob1, ");
				queryString.append("SUM(CASE WHEN c.cafno IS NOT NULL THEN 1 ELSE 0 END) cafno,d.districtname,m.mandalname,t.createdon ");
				queryString.append("FROM cafs c ");
				queryString.append("LEFT JOIN oltportdtls olt ON olt.lmocode=c.lmocode AND olt.portno=c.olt_portid AND olt.pop_olt_serialno=c.olt_id AND c.olt_cardno=olt.cardid ");
				queryString.append("LEFT JOIN oltmaster olm ON olm.pop_olt_serialno=olt.pop_olt_serialno AND olm.pop_olt_ipaddress=c.oltipaddr ");
				queryString.append("LEFT JOIN districts d ON d.districtuid=inst_district ");
				queryString.append("LEFT JOIN mandals m ON m.mandalslno=c.inst_mandal AND d.districtuid=m.districtuid ");
				queryString.append("LEFT JOIN villages v ON v.villageslno=c.inst_city_village AND v.mandalslno=m.mandalslno AND v.districtuid=d.districtuid ");
				queryString.append("LEFT JOIN tenants t ON t.tenantcode=c.lmocode ");
				queryString.append("LEFT JOIN substations sub ON sub.substnuid=olm.pop_substnuid ");
				queryString.append("WHERE c.status=6 ");
				
				String whereClause1="";
				if((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())){
					
					whereClause1 = " AND d.districtuid='"+from+"' and m.mandalslno='"+to+"' ";

				}

					else if(lmocode != null && !lmocode.isEmpty()){
						
						whereClause1 = "  AND olt.lmocode='"+lmocode+"' ";
			
				}
				
					else{
						
						whereClause1= "";
						
					}
				queryString.append(whereClause1);
				
				queryString.append(" GROUP BY c.lmocode,olt.portno,olm.pop_name,olm.pop_olt_serialno, ");
				queryString.append(" d.districtname,m.mandalname,v.villagename,t.tenantname,olm.pop_olt_ipaddress,olm.pop_name ");
					queryString.append(" GROUP BY olm.pop_name,olm.pop_olt_ipaddress,olt.pop_olt_serialno,olt.portno,olt.lmocode,ds.districtname,ms.mandalname,ts.createdon,ts.regoff_pocname,ts.regoff_pocmob1 ");
				  
					query = getEntityManager().createNativeQuery(queryString.toString(),PONWiseBo.class);
			
				
				listPON = query.getResultList();
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				query = null;
				queryString = null;
				query = null;
			}
			return listPON;
		}*/
		

		
		
}
