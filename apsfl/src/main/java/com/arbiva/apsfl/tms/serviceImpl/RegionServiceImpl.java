package com.arbiva.apsfl.tms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.arbiva.apsfl.tms.daoImpl.RegionDaoImpl;
import com.arbiva.apsfl.tms.model.Region;

@Component("regionService")
@Transactional
public class RegionServiceImpl {
	
	@Autowired
	RegionDaoImpl regionDao;
	
	public List<Region> findAllRegions() {
		List<Region> regions = new ArrayList<Region>();
		try{
			regions = regionDao.findAllRegions();
		}catch(Exception e){
			
		}finally{
			
		}
		return regions;
	}
	
	public Region getRegionTypeByRegionName(String regionName) {
		Region region = new Region();
		try{
			region = regionDao.getRegionTypeByRegionName(regionName);
		}catch(Exception e){
			
		}finally{
			
		}
		
		return region;
	}
}
