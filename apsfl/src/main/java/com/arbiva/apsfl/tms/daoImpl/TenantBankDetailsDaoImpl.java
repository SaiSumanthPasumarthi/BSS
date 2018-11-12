/**
 * 
 */
package com.arbiva.apsfl.tms.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.arbiva.apsfl.tms.model.TenantBankDetails;

/**
 * @author Lakshman
 *
 */
@Repository
public class TenantBankDetailsDaoImpl {
	
	private static final Logger LOGGER = Logger.getLogger(TenantBankDetailsDaoImpl.class);
	
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}
	
	public TenantBankDetails findByTenantCode(String tenantCode) {
		TypedQuery<TenantBankDetails> query = null;
		TenantBankDetails tenantBankDetails = new TenantBankDetails();
		StringBuilder builder = new StringBuilder(" FROM ").append(TenantBankDetails.class.getSimpleName())
				.append(" WHERE tenantcode=:tenantCode");
		try {
			LOGGER.info("START::findByTenantCode()");
			query = getEntityManager().createQuery(builder.toString(),TenantBankDetails.class);
			query.setParameter("tenantCode", tenantCode);
			tenantBankDetails = query.getSingleResult();
			LOGGER.info("END::findByTenantCode()");
		}catch (NoResultException e1) {
			
		}catch (Exception e) {
			LOGGER.error("EXCEPTION::findByTenantCode() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenantBankDetails;
	}
	
	public TenantBankDetails find(String tenantCode) {
		TenantBankDetails tenantBankDetails = null;
		try {
			LOGGER.info("START::findByTenantCode()");
			tenantBankDetails = getEntityManager().find(TenantBankDetails.class, tenantCode);
			LOGGER.info("END::findByTenantCode()");
		}catch (Exception e) {
			LOGGER.error("EXCEPTION::find() " + e);
		}finally{
		}
		return tenantBankDetails;
	}
	
	public void saveTenantBankDetails(TenantBankDetails tenantBankDetails) {
		getEntityManager().merge(tenantBankDetails);
	}
	
	public List<TenantBankDetails> findAllTenantBankDetails() {
		List<TenantBankDetails> tenantBankDetails = new ArrayList<TenantBankDetails>();
		TypedQuery<TenantBankDetails> query = null;
		StringBuilder builder = new StringBuilder(" FROM ").append(TenantBankDetails.class.getSimpleName());
		try {
			LOGGER.info("START::findAllTenantBankDetails()");
			query = getEntityManager().createQuery(builder.toString(), TenantBankDetails.class);
			tenantBankDetails = query.getResultList();
			LOGGER.info("END::findAllTenantBankDetails()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findAllTenantBankDetails() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenantBankDetails;
	}
}
