/**
 * 
 */
package com.arbiva.apsfl.tms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.arbiva.apsfl.tms.daoImpl.LovsDao;
import com.arbiva.apsfl.tms.model.Lovs;
import com.sun.media.jfxmedia.logging.Logger;

/**
 * @author Arbiva
 *
 */
@Component("lovsServive")
@Transactional
public class LovsServiceImpl {
	
	@Autowired
	LovsDao lovsDao;

	public List<Lovs> findByLovsByLovName(String lovName) {
		List<Lovs> list = null;
		try{
			list = lovsDao.findByLovsByLovName(lovName);
		}catch(Exception e){
			
		}finally{
			
		}
		return list;
	}
	
	@Transactional
	public boolean addLovsByLovName(String lovName,String value) {
		boolean result = false;
		try{
			result = lovsDao.addLovsByLovName(lovName,value);
			
		}catch(Exception e){
			
		}
		return result;
	}
	
	
	@Transactional
	public void deleteByLovsByLovName(String lovName,String[] prefixes) {
	
		try{
		lovsDao.deleteByLovsByLovName(lovName,prefixes);
			
		}catch(Exception e){
			
		}
	}
	
	
	
	
	
}
