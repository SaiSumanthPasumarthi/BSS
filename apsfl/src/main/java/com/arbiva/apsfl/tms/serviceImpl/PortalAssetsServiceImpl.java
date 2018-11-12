package com.arbiva.apsfl.tms.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arbiva.apsfl.tms.daoImpl.PortalAssetsDaoImpl;
import com.arbiva.apsfl.tms.dto.ListOfPortalAssets;
import com.arbiva.apsfl.tms.dto.TenantVO;
import com.arbiva.apsfl.tms.model.PortalAssets;
import com.arbiva.apsfl.util.DateUtill;

@Service
public class PortalAssetsServiceImpl {
	
	private static final Logger LOGGER = Logger.getLogger(PortalAssetsServiceImpl.class);

	@Autowired
	PortalAssetsDaoImpl portalassetsdao;

	@Autowired
	HttpServletRequest httpServletRequest;

	public List<PortalAssets> findAllPortalAssets() {
		List<PortalAssets> portalassets = new ArrayList<PortalAssets>();
		portalassets = portalassetsdao.findAllPortalAssets();
		return portalassets;
	}

	/*public List<PortalAssets> findByEnrollmentno(String Enrollmentno) {
		List<PortalAssets> portalassets = new ArrayList<PortalAssets>();
		portalassets = portalassetsdao.findByEnrollmentno(Enrollmentno);
		return portalassets;
	}*/
	
	public List<ListOfPortalAssets> findByEnrollmentno(String Enrollmentno) {
		List<ListOfPortalAssets> portalAssets = new ArrayList<>();
		List<Object[]> portalOdjects = portalassetsdao.findByEnrollmentno(Enrollmentno);
		
		for (Object[] object : portalOdjects) {
			ListOfPortalAssets pa = null;
			try {
				pa = new ListOfPortalAssets();
				pa.setCabletypeid(object[0] == null ? "" : object[0].toString());
				pa.setAssettypeid(object[1] == null ? "" : object[1].toString());
				pa.setRoutename(object[2] == null ? "" : object[2].toString());
				pa.setSenttranstime(object[3] == null ? "" : DateUtill.dateToString((Date) object[3],"dd-MM-yyyy HH:mm:ss"));
				pa.setImieno(object[4] == null ? "" : object[4].toString());
				pa.setVersionno(object[5] == null ? "" : object[5].toString());
				pa.setRoutemapid(object[6] == null ? 0 : Long.parseLong(object[6].toString()));
				portalAssets.add(pa);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally{
				pa = null;
			}
		}
		return portalAssets;
	}
	
	public byte[] findRouteMapImage(long routemapid)
	{
		byte[] routemap = null;
		PortalAssets portalassets = new PortalAssets();
		try{
			portalassets = portalassetsdao.findPortalAssets(routemapid);
			routemap = portalassets.getRoutemap();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			portalassets = null;
		}
		return routemap;
	}
	
	public byte[] findCapturedAssetsImage(long routemapid)
	{
		byte[] routemap = null;
		PortalAssets portalassets = new PortalAssets();
		try{
			portalassets = portalassetsdao.findPortalAssets(routemapid);
			routemap = portalassets.getCapturedassets();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			portalassets = null;
		}
		return routemap;
	}
	
	public void savePortalAssets(TenantVO tenantVO) {
		
		PortalAssets portalassets = null;
		List<ListOfPortalAssets> portalAssetsList = tenantVO.getPassets();
		try {
				for(ListOfPortalAssets portalAssets : portalAssetsList){
						if ((portalAssets.getCabletypeid() == null || portalAssets.getCabletypeid().isEmpty()) &&
								(portalAssets.getAssettypeid() == null || portalAssets.getAssettypeid().isEmpty()) &&
								(portalAssets.getRoutename() == null || portalAssets.getRoutename().isEmpty()) &&
								(portalAssets.getRoutemap() == null || portalAssets.getRoutemap().isEmpty()) &&
								(portalAssets.getCapturedassets() == null || portalAssets.getCapturedassets().isEmpty()) &&
								(portalAssets.getSenttranstime() == null || portalAssets.getSenttranstime().isEmpty()) &&
								(portalAssets.getImieno() == null || portalAssets.getImieno().isEmpty()) &&
								(portalAssets.getVersionno() == null || portalAssets.getVersionno().isEmpty()))
						{
							continue;
						}
						else
						{
							portalassets = new PortalAssets();
							portalassets.setEnrollmentno(tenantVO.getTenantCode());
							portalassets.setRoutemapid(portalAssets.getRoutemapid());
							portalassets.setCabletypeid(portalAssets.getCabletypeid() == null || portalAssets.getCabletypeid().isEmpty() ? 0 : Integer.valueOf(portalAssets.getCabletypeid()));
							portalassets.setAssettypeid(portalAssets.getAssettypeid() == null || portalAssets.getAssettypeid().isEmpty() ? 0 : Integer.valueOf(portalAssets.getAssettypeid()));
							portalassets.setRoutename(portalAssets.getRoutename() == null || portalAssets.getRoutename().isEmpty() ? "" : portalAssets.getRoutename());
							portalassets.setRoutemap(portalAssets.getRoutemap().getBytes());
							portalassets.setCapturedassets(portalAssets.getCapturedassets().getBytes());
							portalassets.setSenttranstime(portalAssets.getSenttranstime() == null || portalAssets.getSenttranstime().isEmpty() ? new Date() : DateUtill.stringtoDate(portalAssets.getSenttranstime(), "dd-MM-yyyy HH:mm:ss"));
							portalassets.setImieno(portalAssets.getImieno() == null || portalAssets.getImieno().isEmpty() ? "" : portalAssets.getImieno());
							portalassets.setVersionno(portalAssets.getVersionno() == null || portalAssets.getVersionno().isEmpty() ? "" : portalAssets.getVersionno());
							portalassetsdao.savePortalAssets(portalassets);
						}
				}
			}catch (Exception e) {
				LOGGER.error("PortalAssetsServiceImpl::savePortalAssets() " + e);
				e.printStackTrace();
			}
			finally{
				tenantVO =  null;
				portalAssetsList = null;
				portalassets = null;
			}
		
			

	}

}
