/**
 * 
 */
package com.arbiva.apsfl.tms.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.arbiva.apsfl.tms.model.Tenant;
import com.arbiva.apsfl.tms.model.TenantBussinessAreas;

/**
 * @author Arbiva
 *
 */
@Repository
public class TenantDaoImpl {

	private static final Logger LOGGER = Logger.getLogger(TenantDaoImpl.class);

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	public Tenant findByTenantCode(String tenantCode) {
		TypedQuery<Tenant> query = null;
		Tenant tenant = null;
		StringBuilder builder = new StringBuilder(" FROM ").append(Tenant.class.getSimpleName())
				.append(" WHERE tenantcode=:tenantCode");
		try {
			LOGGER.info("START::findByTenantCode()");
			 query = getEntityManager().createQuery(builder.toString(), Tenant.class);
			query.setParameter("tenantCode", tenantCode);
			tenant = query.getSingleResult();
			LOGGER.info("END::findByTenantCode()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findByTenantCode() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenant;
	}

	public List<Tenant> findMspCodeByTenantType() {
		List<Tenant> tenantList = new ArrayList<Tenant>();
		TypedQuery<Tenant> query = null;
		StringBuilder builder = new StringBuilder(" FROM ").append(Tenant.class.getSimpleName())
				.append(" WHERE tenanttypelov = 'MSP'");
		try {
			LOGGER.info("START::findMspCodeByTenantType()");
			query = getEntityManager().createQuery(builder.toString(), Tenant.class);
			tenantList = query.getResultList();
			LOGGER.info("END::findMspCodeByTenantType()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findMspCodeByTenantType() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenantList;
	}

	@SuppressWarnings("unchecked")
	public List<Tenant> findLmoCodeByTenantType(String tCode) {

		List<Tenant> tenantList = new ArrayList<Tenant>();
		//List<String> tenantAgreements = null;

		/*tenantAgreements = findTenantsIdsforAdmin(tCode);
		StringBuffer str2 = new StringBuffer();
		if (tenantAgreements.size() > 0) {
			for (String tenantCode : tenantAgreements) {
				str2.append(tenantCode);
				str2.append(",");
			}
			str2.replace(str2.toString().length() - 1, str2.toString().length(), "");
		} else
			str2.append("''");

		String str = " where tenantcode in(select tenantcode from tenantsagr where status = 2)"
					+ " and  tenantcode not in (" + str2 + ") ";
					

		StringBuilder builder = new StringBuilder(" FROM ").append(Tenant.class.getSimpleName()).append(str)
				.append(" and tenanttypelov = 'LMO' order by createdon desc");
		try {
			LOGGER.info("START::findLmoCodeByTenantType()");
			TypedQuery<Tenant> query = getEntityManager().createQuery(builder.toString(), Tenant.class);
			tenantList = query.getResultList();
			LOGGER.info("END::findLmoCodeByTenantType()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findLmoCodeByTenantType() " + e);
		}*/
		Query query =  null;
		StringBuilder builder = new StringBuilder("select * from tenants "
				+ " where tenantcode in(select tenantcode from tenantsagr where status = 2) "
				+ " and tenantcode not in "
				+ " (select lmocode from msplmoagr where mspcode='"+tCode+"' and status != 4 ) "
				+ " and tenanttypelov = 'LMO'  order by createdon desc ;");
		try{
			 query = em.createNativeQuery(builder.toString(), Tenant.class);
			tenantList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::findTenantsIdsforAdmin() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		
		return tenantList;
	}

	

	public List<Tenant> findAllTenants() {
		List<Tenant> Tenants = new ArrayList<Tenant>();
		StringBuilder builder = new StringBuilder(" FROM ").append(Tenant.class.getSimpleName());
		TypedQuery<Tenant> query = null;
		try {
			LOGGER.info("START::findAllTenants()");
			query = getEntityManager().createQuery(builder.toString(), Tenant.class);
			Tenants = query.getResultList();
			LOGGER.info("END::findAllTenants()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findAllTenants() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return Tenants;
	}

	public Tenant findByTenantId(Integer tenantid) {
		Tenant tenant = new Tenant();
		TypedQuery<Tenant> query = null;
		StringBuilder builder = new StringBuilder(" FROM ").append(Tenant.class.getSimpleName()).append(" WHERE tenantid=:tenantid");
		try {
			LOGGER.info("START::findByTenantId()");
			query = getEntityManager().createQuery(builder.toString(), Tenant.class);
			query.setParameter("tenantid", tenantid);
			tenant = query.getSingleResult();
			LOGGER.info("END::findByTenantId()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findByTenantId() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenant;
	}

	public Tenant saveTenant(Tenant tenant) {
		return getEntityManager().merge(tenant);
	}
	
	public TenantBussinessAreas saveTenantBussinessAreas(TenantBussinessAreas tba) {
		return getEntityManager().merge(tba);
	}

	@SuppressWarnings("unchecked")
	public List<Tenant> findAllTenantsBYCreatedBy(String loginID) {
		List<Tenant> tenantsList = new ArrayList<Tenant>();
		Query query = null;
		StringBuilder builder = new StringBuilder(" SELECT * FROM tenants "
				+ " where createdby ='"+loginID+"' or "
				+ " createdby in(select loginid from users where rmuserid = '"+loginID+"') ");
		try {
			 query = em.createNativeQuery(builder.toString(), Tenant.class);
			tenantsList  = query.getResultList();
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
			ex.printStackTrace();
		}finally{
			query = null;
			builder = null;
		}

		return tenantsList;
	}

	@SuppressWarnings("unchecked")
	public List<Tenant> findAllByTenantType(String tenantType) {
		List<Tenant> tenantsList = new ArrayList<Tenant>();
		Query query = null;
		StringBuilder builder = new StringBuilder(" select * from tenants where status = 2 and tenanttypelov = :tenantType order by tenantname");
		try {
			 query = em.createNativeQuery(builder.toString(), Tenant.class);
			query.setParameter("tenantType", tenantType);
			tenantsList  = query.getResultList();
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
		}finally{
			query = null;
			builder = null;
		}
		return tenantsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictList(String stateID) {
		Query query = null;
		List<Object[]> districtList = new ArrayList<Object[]>();
		StringBuilder builder = new StringBuilder("SELECT districtuid, districtname "+
								" FROM districts "+
								" WHERE stateid= :stateID "+
								" ORDER BY districtname asc ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter("stateID", stateID);
			districtList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::getDistrictList() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return districtList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMandalList(String stateID, String districtID) {
		Query query = null;
		List<Object[]> mandalList = new ArrayList<Object[]>();
		StringBuilder builder = new StringBuilder("SELECT mandalslno, mandalname FROM mandals "
				+" WHERE stateid = :stateID "
				+" AND districtuid = :districtID "
				+" ORDER BY mandalname asc ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter("stateID", stateID);
			query.setParameter("districtID", districtID);
			mandalList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::getMandalList() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return mandalList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVillageList(String stateID, String districtID, String mandalID ) {
		List<Object[]> villageList = new ArrayList<Object[]>();
		Query query = null;
		StringBuilder builder = new StringBuilder("SELECT villageslno, villagename FROM villages "
				+" WHERE stateid = :stateID "
				+" AND districtuid = :districtID "
				+" AND mandalslno = :mandalID "
				+" ORDER BY villagename asc ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter("stateID", stateID);
			query.setParameter("districtID", districtID);
			query.setParameter("mandalID", mandalID);
			villageList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::getVillageList() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return villageList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getcableList() {
		Query query = null;
		List<Object[]> cableList = new ArrayList<Object[]>();
		StringBuilder builder = new StringBuilder("SELECT cabletypeid, cabletypename FROM cabletypes");
		try{
			 query = em.createNativeQuery(builder.toString());	
			cableList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::getcableList() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return cableList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getassetsList() {
		Query query = null;
		List<Object[]> assetsList = new ArrayList<Object[]>();
		StringBuilder builder = new StringBuilder("SELECT assettypeid, assettypename FROM assettypes");
		try{
			 query = em.createNativeQuery(builder.toString());	
			assetsList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::getassetsList() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return assetsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getstateList() {
		Query query = null;
		List<Object[]> stateList = new ArrayList<Object[]>();
		StringBuilder builder = new StringBuilder("SELECT stateid, statename FROM states");
		try{
			 query = em.createNativeQuery(builder.toString());	
			stateList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::getstateList() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return stateList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubstationList() {
		Query query = null;
		List<Object[]> subsList = new ArrayList<Object[]>();
		StringBuilder builder = new StringBuilder("SELECT substnuid, substnname FROM substations");
		try{
			 query = em.createNativeQuery(builder.toString());	
			subsList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::getSubstationList() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return subsList;
	}
	
	public String checkTenentCode(String tenentCode) {
		Query query = null;
		String s ="Success";
		StringBuilder builder = new StringBuilder("SELECT count(1) FROM tenants where tenantcode = :tenentCode ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter("tenentCode", tenentCode);
			Object obj = query.getSingleResult();
			String a = obj == null ? "" : obj.toString();
			if(a != "0")
				s="Failure";
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::checkTenentCode() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return s;
	}
	
	public String checkTenentType() {
		Query query = null;
		String s ="Success";
		StringBuilder builder = new StringBuilder("SELECT count(1) FROM tenants where tenanttypelov = 'APSFL' ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			Object obj = query.getSingleResult();
			String a = obj == null ? "" : obj.toString();
			if(a != "0")
				s="Failure";
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::checkTenentCode() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return s;
	}
	
	@SuppressWarnings("unchecked")
	public String getDistrictName(String id) {
		List<Object[]> objList = new ArrayList<>();
		String res = "";
		Query query = null;
		StringBuilder builder = new StringBuilder("select districtuid, districtname from districts where districtuid =? ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter(1, id);
			objList = query.getResultList();
			if(!objList.isEmpty()){
				Object[] obj = objList.get(0);
				res = obj[1] == null ? "" :obj[1].toString();
			}
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::checkTenentCode() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public String getMandalName(String distId, String mandalID) {
		List<Object[]> objList = new ArrayList<>();
		String res = "";
		Query query = null;
		StringBuilder builder = new StringBuilder("select mandalslno, mandalname from mandals where districtuid=? and mandalslno=? ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter(1, distId);
			query.setParameter(2, mandalID);
			objList = query.getResultList();
			if(!objList.isEmpty()){
				Object[] obj = objList.get(0);
				res = obj[1] == null ? "" :obj[1].toString();
			}
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::checkTenentCode() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public String getVillageName(String distId, String mandalID, String villaageId) {
		List<Object[]> objList = new ArrayList<>();
		String res = "";
		Query query = null;
		StringBuilder builder = new StringBuilder("select villageslno, villagename from villages where districtuid=? and mandalslno=? and villageslno=? ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter(1, distId);
			query.setParameter(2, mandalID);
			query.setParameter(3, villaageId);
			objList = query.getResultList();
			if(!objList.isEmpty()){
				Object[] obj = objList.get(0);
				res = obj[1] == null ? "" :obj[1].toString();
			}
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::checkTenentCode() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<TenantBussinessAreas> getTbareasList(String tenantCode) {
		List<Object[]> objList = new ArrayList<>();
		List<TenantBussinessAreas> tbaList = new ArrayList<>();
		Query query = null;
		StringBuilder builder = new StringBuilder("select tba.districtuid, districtname, tba.mandalslno, mandalname, tba.villageslno, villagename,tba.subscribercnt, tba.substnuid, subs.substnname "
								+" from tenantbusareas tba, districts d, mandals m, villages v, substations subs "
								+" where tba.districtuid = d.districtuid "
								+" and d.districtuid = m.districtuid  "
								+" and tba.mandalslno = m.mandalslno "
								+" and d.districtuid = v.districtuid "
								+" and m.mandalslno = v.mandalslno "
								+" and tba.villageslno = v.villageslno "
								+" and tba.substnuid = subs.substnuid "
								+" and tba.tenantcode = ? ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter(1, tenantCode);
			objList = query.getResultList();
			for(Object[] object : objList){
				TenantBussinessAreas tba = new TenantBussinessAreas();
				tba.setDistrictuid(object[0] == null ? 0 : Integer.parseInt(object[0].toString()));
				tba.setDistrictName(object[1] == null ? "" : object[1].toString());
				tba.setMandalslno(object[2] == null ? 0 : Integer.parseInt(object[2].toString()));
				tba.setMandalName(object[3] == null ? "" : object[3].toString());
				tba.setVillageslno(object[4] == null ? 0 : Integer.parseInt(object[4].toString()));
				tba.setVillageName(object[5] == null ? "" : object[5].toString());
				tba.setSubscribercnt((Long) (object[6] == null ? "" :Long.parseLong( object[6].toString())));
				tba.setSubstnuid(object[7] == null ? "" : object[7].toString());
				tba.setSubStationName(object[8] == null ? "" : object[8].toString());
				tbaList.add(tba);
			}
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::getTbareasList() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return tbaList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getStdCodesByDist(String stateID, String districtId) {
			List<String> stdCodes = new ArrayList<String>();
			StringBuilder builder = new StringBuilder("SELECT DISTINCT stdcode FROM villages WHERE districtuid = "+districtId+" ORDER BY stdcode asc ;");
			Query query = null;
			try {
				query = em.createNativeQuery(builder.toString());
				stdCodes = query.getResultList();
					 
			} catch(Exception ex){
				LOGGER.error("EXCEPTION::getStdCodeByVillage() " + ex);
			}finally{
				query = null;
				builder = null;
			}
			return stdCodes;
		}

	@SuppressWarnings("unchecked")
	public List<String> getMandalsByDistStdCode(String stateID, String districtId, String stdCodeNum) {
		List<String> mandals = new ArrayList<String>();
		StringBuilder builder = new StringBuilder("SELECT mandalslno, mandalname FROM mandals WHERE districtuid='"+districtId+"' AND mandalslno IN (SELECT DISTINCT mandalslno FROM villages  WHERE stdcode='"+stdCodeNum+"') ORDER BY mandalname asc ;");
		Query query = null;
		try {
			query = em.createNativeQuery(builder.toString());
			mandals = query.getResultList();
				 
		} catch(Exception ex){
			LOGGER.error("EXCEPTION::getStdCodeByVillage() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return mandals;
	}

	
	public List<Object[]> getCpeDetails(String cpesrlno ,String fromDate , String toDate,String action) {
		List<Object[]> list = new ArrayList<>();
		Query query = null;
		StringBuilder builder =null;
		String whereClause = "";
		
		try {			
			if(fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()){
				whereClause = whereClause + " and CAST(cps.transaction_date AS date) BETWEEN '"+fromDate+"' and '"+toDate+"' ";
				
			}
			if(cpesrlno != null && !cpesrlno.isEmpty()){
				 whereClause = whereClause + " and (cps.cpesrlno = '"+cpesrlno+"') ";
			}
			if (action.equals("delete")){
				builder = new StringBuilder(" SELECT  cps.cpesrlno,cps.cpemacaddress, cps.msocode, cps.lmocode, IF(cps.transaction_status = 'D', 'Deleted', '') AS cpstransaction_status ,cps.transaction_date,cps.user");
				builder.append(" FROM cpestock_history cps where cps.transaction_status='D' " + whereClause );
			}else{
				 builder = new StringBuilder(" SELECT  cps.cpesrlno,cps.cpemacaddress, cps.msocode, cps.lmocode, IF(cps.transaction_status = 'C', 'Created', IF(cps.transaction_status = 'U', 'Updated', 'Deleted')) AS cpstransaction_status ,cps.transaction_date,cps.user");
					builder.append(" FROM cpestock_history cps where cps.cpesrlno = '"+cpesrlno+"'" );
			}
				
				
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
	
	
	}
 
