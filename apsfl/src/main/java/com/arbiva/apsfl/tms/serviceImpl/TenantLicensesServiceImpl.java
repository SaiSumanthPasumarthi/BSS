package com.arbiva.apsfl.tms.serviceImpl;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arbiva.apsfl.tms.daoImpl.RegionDaoImpl;
import com.arbiva.apsfl.tms.daoImpl.TenantDaoImpl;
import com.arbiva.apsfl.tms.daoImpl.TenantLicensesDaoImpl;
import com.arbiva.apsfl.tms.dto.TenantVO;
import com.arbiva.apsfl.tms.model.Region;
import com.arbiva.apsfl.tms.model.Tenant;
import com.arbiva.apsfl.tms.model.TenantLicenses;

@Service
public class TenantLicensesServiceImpl {

	private static final Logger LOGGER = Logger.getLogger(TenantDaoImpl.class);

	@Autowired
	TenantLicensesDaoImpl tenantLicensesDao;

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	RegionDaoImpl regionDao;

	public void saveTenantLicenses(TenantVO tenantVO, Tenant tenant, Integer tenantId) {
		String userName = (String) httpServletRequest.getSession(false).getAttribute("loginID");
		TenantLicenses tenantLicenses = tenantLicensesDao.find(tenantVO.getTenantCode());
		TenantLicenses tenantLicenses1 = null;
		Region region = regionDao.getRegionTypeByRegionName(tenantVO.getRegion());
		
		if (userName != null && userName != "") {
			try {
				if((tenantVO.getLicenserefno()!= null && !tenantVO.getLicenserefno().isEmpty())
						&& (tenantVO.getRegion() != null && !tenantVO.getRegion().isEmpty()))
				{
					
					if(tenantLicenses != null)
					{
						tenantLicenses.setModifiedDate(Calendar.getInstance());
						tenantLicenses.setModifiedBy(userName);
						tenantLicenses.setModifiedDate(Calendar.getInstance());
						tenantLicenses.setModifiedIPAddress(httpServletRequest.getRemoteAddr());
						tenantLicenses.setStatus(1);
						tenantLicenses.setLicenserefno(tenantVO.getLicenserefno() == null || tenantVO.getLicenserefno().isEmpty() ? "" : tenantVO.getLicenserefno());
						tenantLicenses.setRegion(region);
						tenantLicensesDao.saveTenantLicenses(tenantLicenses);
					}
					else
					{
						tenantLicenses1 = new TenantLicenses();
						tenantLicenses1.setCratedIPAddress(httpServletRequest.getRemoteAddr());
						tenantLicenses1.setCreatedBy(userName);
						tenantLicenses1.setCreatedDate(Calendar.getInstance());
						tenantLicenses1.setModifiedDate(Calendar.getInstance());
						tenantLicenses1.setStatus(1);
						tenantLicenses1.setLicenseexpDate("2999-01-01");
						tenantLicenses1.setLicenserefno(tenantVO.getLicenserefno() == null || tenantVO.getLicenserefno().isEmpty() ? "" : tenantVO.getLicenserefno());
						tenantLicenses1.setRegion(region);
						tenantLicenses1.setTenantcode(tenantVO.getTenantCode());
						tenantLicensesDao.saveTenantLicenses(tenantLicenses1);
					}
				}
			} catch (Exception e) {
				LOGGER.error("TenantLicensesServiceImpl::saveTenantLicenses() " + e);
				e.printStackTrace();
			}
			finally{
				tenantVO = null;
				tenant = null;
				tenantId = null;
				tenantLicenses = null;
				tenantLicenses1 = null;
				region = null;
				userName = null;
			}
		}
	}


	public TenantLicenses findByTenantCode(String tenantCode) {
		return tenantLicensesDao.findByTenantCode(tenantCode);
	}
}
