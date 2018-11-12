/**
 * 
 */
package com.arbiva.apsfl.tms.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.arbiva.apsfl.tms.model.Lovs;

/**
 * @author Arbiva
 *
 */
@Component("lovsDao")
public class LovsDao {

	private static final Logger LOGGER = Logger.getLogger(LovsDao.class);
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	
	public List<Lovs> findByLovsByLovName(String lovName) {
		List<Lovs> lovs = new ArrayList<Lovs>();
		TypedQuery<Lovs> query = null;
		StringBuilder builder = new StringBuilder(" FROM ").append(Lovs.class.getSimpleName()).append(" WHERE lovname ='"+lovName+"'");
		try {
			LOGGER.info("START::findByLovName()");
			query = getEntityManager().createQuery(builder.toString(), Lovs.class);
			lovs = query.getResultList();
			LOGGER.info("END::findByLovName()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findByLovName() " + e);
		}finally{
			query = null;
			builder = null;
		}
		return lovs;
	}
	@Transactional
	public boolean addLovsByLovName(String lovName, String value) {
		String qry = null;
		Query query = null;
		List<Lovs> lovs = new ArrayList<Lovs>();
		lovs=findByLovsByLovName(lovName);
		boolean result;
		
		try {
			LOGGER.info("START::addLovsByLovName()");
		
		qry ="insert into lovs (lovname, lovseq, lovvalue, status) values (?,?,?,?)";
        	query = getEntityManager().createNativeQuery(qry);
			query.setParameter(1, lovName);
			if (!lovs.isEmpty())
			query.setParameter(2, (lovs.get(lovs.size()-1).getLovSeq())+1);
			else
				query.setParameter(2, "1");
			query.setParameter(3, value);
			query.setParameter(4, "1");
			
			
        	query.executeUpdate();
        	result=true;
        	
        	LOGGER.info("END::findByLovName()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::findByLovName() " + e);
			result=false;
		}finally{
			query = null;
			
		}
		
		return result;
	}
	
	
	@Transactional
	public void deleteByLovsByLovName(String lovName,String[] prefixes) {
		List<Lovs> lovs = new ArrayList<Lovs>();
		
		//StringBuilder builder = new StringBuilder(" FROM ").append(Lovs.class.getSimpleName()).append(" WHERE lovname ='"+lovName+"' and lovvalue='"+prefixes+"'" );
		try {
			LOGGER.info("START::deleteByLovsByLovName()");
			if (prefixes.length!=0)
			for (String prefix:prefixes){
			Query query = getEntityManager().createQuery(
				      "DELETE FROM Lovs lov WHERE lov.lovName='"+lovName+"'"+" and lov.lovValue='"+prefix+"'");
		
				   query.executeUpdate();
						                
				 
			LOGGER.info("Removed at " + new Date());
			}
			LOGGER.info("END::deleteByLovsByLovName()");
		} catch (Exception e) {
			LOGGER.error("EXCEPTION::deleteByLovsByLovName() " + e);
		}finally{
			
		}
		
	}
	
	

}
