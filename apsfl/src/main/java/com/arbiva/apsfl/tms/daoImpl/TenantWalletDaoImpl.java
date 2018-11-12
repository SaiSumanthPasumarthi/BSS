/**
 * 
 */
package com.arbiva.apsfl.tms.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.arbiva.apsfl.tms.model.TenantWallet;

/**
 * @author Lakshman
 *
 */
@Repository
public class TenantWalletDaoImpl {

	private static final Logger LOGGER = Logger.getLogger(TenantWalletDaoImpl.class);
	
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("rawtypes")
	public TenantWallet findByTenantCode(String tenantCode) {
		TenantWallet tenantWallet = new TenantWallet();
		TypedQuery<TenantWallet> query =  null;
		StringBuilder builder = new StringBuilder(" FROM ").append(TenantWallet.class.getSimpleName()).append(" WHERE tenantcode=:tenantCode");
			try {
				LOGGER.info("START::findByTenantCode()");
				query = getEntityManager().createQuery(builder.toString(), TenantWallet.class);
				query.setParameter("tenantCode", tenantCode);
				List result = query.getResultList();
				if(!result.isEmpty())
					tenantWallet = (TenantWallet) result.get(0);
				
				LOGGER.info("END::findByTenantCode()");
			} catch (Exception e) {
				LOGGER.error("EXCEPTION::findByTenantCode() " + e);
			}finally{
				query = null;
				builder = null;
			}
		  return tenantWallet;
	}
	
	public void saveTenantWallet(TenantWallet tenantWallet) {
		try {
			getEntityManager().merge(tenantWallet);
		} catch(Exception e) {
			LOGGER.error("EXCEPTION::saveTenantWallet() " + e);
			e.printStackTrace();
		}finally{
		}
	}
	
	public List<TenantWallet> findAllTenantWallets() {
		TypedQuery<TenantWallet> query =  null;
		List<TenantWallet> tenantWallet = new ArrayList<TenantWallet>();
		StringBuilder builder = new StringBuilder(" FROM ").append(TenantWallet.class.getSimpleName());
		try {
			LOGGER.info("START::findAllRegions()");
			query = getEntityManager().createQuery(builder.toString(), TenantWallet.class);
			tenantWallet = query.getResultList();
			LOGGER.info("END::findAllRegions()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findAllRegions() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenantWallet;
	}
}
