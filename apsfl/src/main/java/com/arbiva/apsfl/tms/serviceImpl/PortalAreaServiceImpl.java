package com.arbiva.apsfl.tms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arbiva.apsfl.tms.daoImpl.PortalAreaDaoImpl;
import com.arbiva.apsfl.tms.dto.ListOfPortalAreas;
import com.arbiva.apsfl.tms.dto.TenantVO;
import com.arbiva.apsfl.tms.model.PortalAreas;

@Service
public class PortalAreaServiceImpl {
	
	private static final Logger LOGGER = Logger.getLogger(PortalAreaServiceImpl.class);

	@Autowired
	PortalAreaDaoImpl portalAreaDaoImpl;

	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	TenantServiceImpl tenantService;

	public List<PortalAreas> findAllPortalAreas() {
		List<PortalAreas> portalAreas = new ArrayList<PortalAreas>();
		try{
			portalAreas = portalAreaDaoImpl.findAllPortalAreas();
		}catch(Exception e){
			
		}finally{
			
		}
		
	
		return portalAreas;
	}

	public List<ListOfPortalAreas> findByEnrollmentno(String Enrollmentno) {
		List<ListOfPortalAreas> portalAreas = new ArrayList<>();
		List<Object[]> portalOdjects = null;
		try{
			
			 portalOdjects = portalAreaDaoImpl.findByEnrollmentno(Enrollmentno);
			
			for (Object[] object : portalOdjects) {
					ListOfPortalAreas pa = new ListOfPortalAreas();
					pa.setAreaname(object[0] == null ? "" : object[0].toString());
					pa.setAreas_cabletypeid(object[1] == null ? "" : object[1].toString());
					pa.setRunningcablelen(object[2] == null ? "" : object[2].toString());
					pa.setStateid(object[3] == null ? "" : object[3].toString());
					pa.setDistrictid(object[4] == null ? "" : object[4].toString());
					pa.setMandalid(object[5] == null ? "" : object[5].toString());
					pa.setVillageid(object[6] == null ? "" : object[6].toString());
					pa.setSubscription_cnt(object[7] == null ? "" : object[7].toString());
					pa.setConn_cnt(object[8] == null ? "" : object[8].toString());
					pa.setDigconn_cnt(object[9] == null ? "" : object[9].toString());
					pa.setAnlconn_cnt(object[10] == null ? "" : object[10].toString());
					pa.setAreaid(object[11] == null ? "" : object[11].toString());
					pa.setDistrictList(tenantService.getDistrictList(pa.getStateid()));
					pa.setMandalList(tenantService.getMandalList(pa.getStateid(), pa.getDistrictid()));
					pa.setVillageList(tenantService.getVillageList(pa.getStateid(), pa.getDistrictid(), pa.getMandalid()));
					portalAreas.add(pa);
			}
			
		}catch(Exception e){
			
		}finally{
			portalOdjects = null;
		}
		
		return portalAreas;
	}

	public void savePortalAreas(TenantVO tenantVO) {
		List<ListOfPortalAreas>  portalAreasList = tenantVO.getPareas();
		PortalAreas portalareas= null;
		try {
				for(ListOfPortalAreas portalAreas : portalAreasList){
						if((portalAreas.getAreaname() == null || portalAreas.getAreaname().isEmpty()) &&
								(portalAreas.getAreas_cabletypeid() == null || portalAreas.getAreas_cabletypeid().isEmpty()) &&
								(portalAreas.getRunningcablelen() == null || portalAreas.getRunningcablelen().isEmpty()) &&
								(portalAreas.getStateid() == null || portalAreas.getStateid().isEmpty()) &&
								(portalAreas.getDistrictid() == null || portalAreas.getDistrictid().isEmpty()) &&
								(portalAreas.getMandalid() == null || portalAreas.getDistrictid().isEmpty()) &&
								(portalAreas.getVillageid() == null || portalAreas.getVillageid().isEmpty()) &&
								(portalAreas.getSubscription_cnt() == null || portalAreas.getSubscription_cnt().isEmpty()) &&
								(portalAreas.getConn_cnt() == null || portalAreas.getConn_cnt().isEmpty()) &&
								(portalAreas.getDigconn_cnt() == null || portalAreas.getDigconn_cnt().isEmpty()) &&
								(portalAreas.getAnlconn_cnt() == null || portalAreas.getAnlconn_cnt().isEmpty()))
						{
							continue;
						}
						else
						{
							portalareas = new PortalAreas();
							portalareas.setEnrollmentno(tenantVO.getTenantCode());
							portalareas.setAreaid(portalAreas.getAreaid() == null || portalAreas.getAreaid().isEmpty() ? 0 : Integer.valueOf(portalAreas.getAreaid()));
							portalareas.setAreaname(portalAreas.getAreaname());
							portalareas.setCabletypeid(portalAreas.getAreas_cabletypeid());
							portalareas.setRunningcablelen(portalAreas.getRunningcablelen() == null || portalAreas.getRunningcablelen().isEmpty() ? 0 : Float.valueOf(portalAreas.getRunningcablelen()));
							portalareas.setStateid(portalAreas.getStateid() == null || portalAreas.getStateid().isEmpty() ? 0 : Integer.valueOf(portalAreas.getStateid()));
							portalareas.setDistrictid(portalAreas.getDistrictid() == null || portalAreas.getDistrictid().isEmpty() ? 0 : Integer.valueOf(portalAreas.getDistrictid()));
							portalareas.setMandalid(portalAreas.getMandalid() == null || portalAreas.getMandalid().isEmpty() ? 0 : Integer.valueOf(portalAreas.getMandalid()));
							portalareas.setVillageid(portalAreas.getVillageid() == null || portalAreas.getVillageid().isEmpty() ? 0 : Integer.valueOf(portalAreas.getVillageid()));
							portalareas.setSubscription_cnt(portalAreas.getSubscription_cnt() == null || portalAreas.getSubscription_cnt().isEmpty() ? 0 : Long.valueOf(portalAreas.getSubscription_cnt()));
							portalareas.setConn_cnt(portalAreas.getConn_cnt() == null || portalAreas.getConn_cnt().isEmpty() ? 0 : Long.valueOf(portalAreas.getConn_cnt()));
							portalareas.setDigconn_cnt(portalAreas.getDigconn_cnt() == null || portalAreas.getDigconn_cnt().isEmpty()  ? 0 : Long.valueOf(portalAreas.getDigconn_cnt()));
							portalareas.setAnlconn_cnt(portalAreas.getAnlconn_cnt() == null || portalAreas.getAnlconn_cnt().isEmpty() ? 0 : Long.valueOf(portalAreas.getAnlconn_cnt()));
							portalAreaDaoImpl.savePortalAreas(portalareas);
						}
				}
			}catch (Exception e) {
				LOGGER.error("PortalAreaServiceImpl::savePortalAreas() " + e);
				e.printStackTrace();
			}finally{
				tenantVO = null;
				portalAreasList = null;
				portalareas = null;
			}
	}
}



