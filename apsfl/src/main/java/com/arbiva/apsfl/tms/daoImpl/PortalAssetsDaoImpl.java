package com.arbiva.apsfl.tms.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.arbiva.apsfl.tms.model.PortalAssets;

@Repository
public class PortalAssetsDaoImpl {
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
		List<Object[]> assetsList = new ArrayList<Object[]>();
		Query query = null;
		StringBuilder builder = new StringBuilder("select (select cabletypename from cabletypes where cabletypeid = pa.cabletypeid) cabletype, "+
								" (select assettypename from assettypes where assettypeid = pa.assettypeid) assettype, "+
								" routename, senttranstime, imieno, versionno, routemapid "+
								" from portal_routes pa "+
								" where enrollmentno = :Enrollmentno ");
		try{
			 query = em.createNativeQuery(builder.toString());	
			query.setParameter("Enrollmentno", Enrollmentno);
			assetsList = query.getResultList();
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::findByEnrollmentno() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return assetsList;
	}
	
	public void savePortalAssets(PortalAssets portalassets) {
		getEntityManager().merge(portalassets);
	}
	
	public List<PortalAssets> findAllPortalAssets() {
		TypedQuery<PortalAssets> query = null;
		List<PortalAssets> portalassets = new ArrayList<PortalAssets>();
		StringBuilder builder = new StringBuilder(" FROM ").append(PortalAssets.class.getSimpleName());
		try {
			LOGGER.info("START::findAllPortalAssets()");
			query = getEntityManager().createQuery(builder.toString(), PortalAssets.class);
			portalassets = query.getResultList();
			LOGGER.info("END::findAllPortalAssets()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findAllPortalAssets() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return portalassets;
	}
	
	public PortalAssets findPortalAssets(long routemapid) {
		List<PortalAssets> PortalAssetsList = new ArrayList<>();
		TypedQuery<PortalAssets> query = null;
		PortalAssets portalAssets = new PortalAssets(); 
		StringBuilder builder = new StringBuilder(" FROM ").append(PortalAssets.class.getSimpleName());
		builder.append(" WHERE routemapid = :routemapid ");
		try{
			 query = getEntityManager().createQuery(builder.toString(), PortalAssets.class);
			query.setParameter("routemapid", routemapid);
			PortalAssetsList = query.getResultList();
			if(!PortalAssetsList.isEmpty()){
				portalAssets = PortalAssetsList.get(0);
			}
		}catch(Exception ex){
			LOGGER.error("EXCEPTION::findRouteMapImage() " + ex);
		}finally{
			query = null;
			builder = null;
		}
		return portalAssets;
	}
	
}
