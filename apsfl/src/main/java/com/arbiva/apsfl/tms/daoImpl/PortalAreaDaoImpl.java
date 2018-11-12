package com.arbiva.apsfl.tms.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.arbiva.apsfl.tms.model.PortalAreas;

@Repository
public class PortalAreaDaoImpl {
	
private static final Logger LOGGER = Logger.getLogger(PortalAssetsDaoImpl.class);
	
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByEnrollmentno(String Enrollmentno) {
		List<Object[]> areaList = new ArrayList<Object[]>();
		Query query = null;
		StringBuilder builder = new StringBuilder("select areaname,(select cabletypename from cabletypes where cabletypeid = pa.cabletypeid) cabletype, "+
					" runningcablelen,stateid,districtid,mandalid,villageid,subscription_cnt,conn_cnt,digconn_cnt,anlconn_cnt,areaid "+
					" from portal_areas pa "+
					" where enrollmentno = :Enrollmentno ");
		try{
			query = em.createNativeQuery(builder.toString());	
			query.setParameter("Enrollmentno", Enrollmentno);
			areaList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::findByEnrollmentno() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return areaList;
	}
	
	public void savePortalAreas(PortalAreas portalAreas) {
		getEntityManager().merge(portalAreas);
	}
	
	public List<PortalAreas> findAllPortalAreas() {
		TypedQuery<PortalAreas> query = null;
		List<PortalAreas> portalAreas = new ArrayList<PortalAreas>();
		StringBuilder builder = new StringBuilder(" FROM ").append(PortalAreas.class.getSimpleName());
		try {
			LOGGER.info("START::findAllPortalAreas()");
			query = getEntityManager().createQuery(builder.toString(), PortalAreas.class);
			portalAreas = query.getResultList();
			LOGGER.info("END::findAllPortalAreas()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findAllPortalAreas() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return portalAreas;
	}
	
}


