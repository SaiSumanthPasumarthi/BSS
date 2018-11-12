/**
 * 
 */
package com.arbiva.apsfl.tms.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.arbiva.apsfl.tms.model.TenantLicenses;

/**
 * @author Lakshman
 *
 */
@Repository
public class TenantLicensesDaoImpl {

	private static final Logger LOGGER = Logger.getLogger(TenantDaoImpl.class);

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	/*public TenantLicenses findByTenantCode(String tenantCode, Region region) {
		TenantLicenses tenantLicenses = new TenantLicenses();
		StringBuilder builder = new StringBuilder(" FROM ").append(TenantLicenses.class.getSimpleName()).append(" WHERE tenantcode=:tenantCode");
		builder.append(" AND regiontype=:regiontype");
		builder.append(" AND regioncode=:regioncode");
		try {
			LOGGER.info("START::findByTenantCode()");
			TypedQuery<TenantLicenses> query = getEntityManager().createQuery(builder.toString(), TenantLicenses.class);
			query.setParameter("tenantCode", tenantCode);
			query.setParameter("regiontype", region.getId().getRegionType());
			query.setParameter("regioncode", region.getId().getRegionCode());
			tenantLicenses = query.getSingleResult();
			LOGGER.info("END::findByTenantCode()");
		}catch (NoResultException e1) {
			
		}
		catch (Exception e) {
			LOGGER.error("EXCEPTION::findByTenantCode() " + e);
		}
		return tenantLicenses;
	}*/

	public void saveTenantLicenses(TenantLicenses tenantLicenses) {
		getEntityManager().merge(tenantLicenses);
	}

	public TenantLicenses findByTenantCode(String tenantCode) {
		TypedQuery<TenantLicenses> query = null;
		TenantLicenses tenantLicenses = new TenantLicenses();
		StringBuilder builder = new StringBuilder(" FROM ").append(TenantLicenses.class.getSimpleName()).append(" WHERE tenantcode=:tenantCode");
		try {
			LOGGER.info("START::findByTenantCode()");
			query = getEntityManager().createQuery(builder.toString(), TenantLicenses.class);
			query.setParameter("tenantCode", tenantCode);
			tenantLicenses = query.getSingleResult();
			LOGGER.info("END::findByTenantCode()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findByTenantCode() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return tenantLicenses;
	}
	
	public TenantLicenses find(String tenantCode) {
		TenantLicenses tenantLicenses = null;
		try {
			tenantLicenses = getEntityManager().find(TenantLicenses.class, tenantCode);
			LOGGER.info("END::find()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::find() " + e);
		}finally{
		}
		return tenantLicenses;
	}
	
}
