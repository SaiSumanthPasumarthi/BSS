package com.arbiva.apsfl.tms.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.arbiva.apsfl.tms.model.TenantDocsPK;
import com.arbiva.apsfl.tms.model.TenantDocuments;

@Repository
public class TenantDocumentsDaoImpl {
	
private static final Logger LOGGER = Logger.getLogger(TenantDocumentsDaoImpl.class);
		
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}
	
	@SuppressWarnings("unchecked")
	public List<TenantDocuments> findByTenantCode(String tenantCode) {
		Query query = null;
		List<TenantDocuments> tenantDocuments = new ArrayList<TenantDocuments>();
		//StringBuilder builder = new StringBuilder(" FROM ").append(TenantDocuments.class.getSimpleName()).append(" WHERE tenantcode=:tenantCode");
		StringBuilder builder = new StringBuilder("select * from tenantdocs "+
							" where tenantcode = :tenantCode "+
							" and effectivefrom = (select max(effectivefrom) from tenantdocs WHERE tenantcode = :tenantCode)");
		try {
			LOGGER.info("START::findByTenantDocumentsCode()");
			 query = getEntityManager().createNativeQuery(builder.toString(), TenantDocuments.class);
			query.setParameter("tenantCode", tenantCode);
			tenantDocuments = query.getResultList();
			LOGGER.info("END::findByTenantDocumentsCode()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findByTenantDocumentsCode() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenantDocuments;
	}
	
	@SuppressWarnings("unchecked")
	public TenantDocuments findByTenantCode(String tenantCode, String doclovname) {
		Query query = null;
		List<TenantDocuments> tenantDocumentsList = new ArrayList<TenantDocuments>();
		TenantDocuments tenantDocuments = null;
		StringBuilder builder = new StringBuilder("select * from tenantdocs "+
							" where tenantcode = :tenantCode and doclovname = :doclovname "+
							" and effectivefrom = (select max(effectivefrom) from tenantdocs WHERE tenantcode = :tenantCode and doclovname = :doclovname)");
		try {
			LOGGER.info("START::findByTenantDocumentsCode()");
			 query = getEntityManager().createNativeQuery(builder.toString(), TenantDocuments.class);
			query.setParameter("tenantCode", tenantCode);
			query.setParameter("doclovname", doclovname);
			tenantDocumentsList = query.getResultList();
			if(!tenantDocumentsList.isEmpty()){
				tenantDocuments = tenantDocumentsList.get(0);
			}
			LOGGER.info("END::findByTenantDocumentsCode()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findByTenantDocumentsCode() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenantDocuments;
	}
	
	public TenantDocuments find(String tenantCode, String documentLovName, Date effectiveFrom) {
		TenantDocuments tenantDocuments = null;
		try {
			tenantDocuments = getEntityManager().find(TenantDocuments.class, new TenantDocsPK(tenantCode, documentLovName, effectiveFrom));
			LOGGER.info("END::findByTenantCode(tenantCode,documentLovName,effectiveFrom)");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findByTenantCode(tenantCode,documentLovName,effectiveFrom) " + e);
		}
		return tenantDocuments;
	}
	
	public void saveTenantDocument(TenantDocuments tenantDocuments) {
		getEntityManager().merge(tenantDocuments);
	}
}
