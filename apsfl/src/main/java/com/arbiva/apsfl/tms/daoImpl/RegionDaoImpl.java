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
import org.springframework.stereotype.Component;

import com.arbiva.apsfl.tms.model.Region;

/**
 * @author Arbiva
 *
 */
@Component("regionDao")
public class RegionDaoImpl {

	private static final Logger LOGGER = Logger.getLogger(RegionDaoImpl.class);
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	public List<Region> findAllRegions() {
		TypedQuery<Region> query = null;
		List<Region> regions = new ArrayList<Region>();
		StringBuilder builder = new StringBuilder(" FROM ").append(Region.class.getSimpleName());
		try {
			LOGGER.info("START::findAllRegions()");
			query = getEntityManager().createQuery(builder.toString(), Region.class);
			regions = query.getResultList();
			LOGGER.info("END::findAllRegions()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findAllRegions() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return regions;
	}
	
	public Region getRegionTypeByRegionName(String regionName) {
		Region region = null;
		Query query = null;
		StringBuilder builder = new StringBuilder("select * from regions where regionname = '"+regionName+"' ");
			try {
				LOGGER.info("START::getRegionTypeByRegionName()");
				 query = getEntityManager().createNativeQuery(builder.toString(), Region.class);
				region = (Region) query.getSingleResult();
				LOGGER.info("END::getRegionTypeByRegionName()");
			} catch (Exception e) {
				LOGGER.error("EXCEPTION::getRegionTypeByRegionName() " + e);
			}
			finally{
				query = null;
				builder = null;
				regionName = null;
			}
		  return region;
		
	}
}
