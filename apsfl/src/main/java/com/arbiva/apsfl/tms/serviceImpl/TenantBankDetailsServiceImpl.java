package com.arbiva.apsfl.tms.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arbiva.apsfl.tms.daoImpl.TenantBankDetailsDaoImpl;
import com.arbiva.apsfl.tms.dto.TenantVO;
import com.arbiva.apsfl.tms.model.TenantBankDetails;

@Service
public class TenantBankDetailsServiceImpl {

	private static final Logger LOGGER = Logger.getLogger(TenantBankDetailsDaoImpl.class);

	@Autowired
	TenantBankDetailsDaoImpl tenantBankDetailsDao;

	@Autowired
	HttpServletRequest httpServletRequest;

	public List<TenantBankDetails> findAllTenantBankDetailss() {
		List<TenantBankDetails> tenantBankDetails = new ArrayList<TenantBankDetails>();
		try{
			tenantBankDetails = tenantBankDetailsDao.findAllTenantBankDetails();
		}catch(Exception e){
			
		}finally{
			
		}
		
		return tenantBankDetails;
	}

	public TenantBankDetails findByTenantCode(String tenantCode) {
		TenantBankDetails tenantBankDetails = new TenantBankDetails();
		
		try{
			tenantBankDetails = tenantBankDetailsDao.findByTenantCode(tenantCode);
		}catch(Exception e){
			
		}finally{
			
		}
		
		return tenantBankDetails;
	}

	public void saveTenantBankDetails(TenantVO tenantVO, Integer tenantId) {
		String userName = (String) httpServletRequest.getSession(false).getAttribute("loginID");
		TenantBankDetails tenantBankDetails = tenantBankDetailsDao.find(tenantVO.getTenantCode());
		TenantBankDetails tenantBankDetails1  = null;
		if (userName != null && userName != "") {
			try {
				if((tenantVO.getAccountNo() != null && !tenantVO.getAccountNo().isEmpty())
				   && (tenantVO.getIfscCode()!= null && !tenantVO.getIfscCode().isEmpty())
				   && (tenantVO.getAcctTypelov() != null && !tenantVO.getAcctTypelov().isEmpty())
				   && (tenantVO.getBankNamelov() != null && !tenantVO.getBankNamelov().isEmpty())
				   && (tenantVO.getBranchName() !=null && !tenantVO.getBranchName().isEmpty()))
				{
					
					if (tenantBankDetails != null) {
						tenantBankDetails.setModifiedDate(Calendar.getInstance());
						tenantBankDetails.setModifiedBy(userName);
						tenantBankDetails.setModifiedIPAddress(httpServletRequest.getRemoteAddr());
						tenantBankDetails.setStatus(1);
						tenantBankDetails.setAccountNo(tenantVO.getAccountNo() == null || tenantVO.getAccountNo().isEmpty() ? "" : tenantVO.getAccountNo());
						tenantBankDetails.setIfscCode(tenantVO.getIfscCode() == null || tenantVO.getIfscCode().isEmpty() ? "" : tenantVO.getIfscCode());
						tenantBankDetails.setAcctTypelov(tenantVO.getAcctTypelov() == null || tenantVO.getAcctTypelov().isEmpty() ? "" : tenantVO.getAcctTypelov());
						tenantBankDetails.setBankNamelov(tenantVO.getBankNamelov() == null || tenantVO.getBankNamelov().isEmpty() ? "" : tenantVO.getBankNamelov());
						tenantBankDetails.setBranchName(tenantVO.getBranchName() == null || tenantVO.getBranchName().isEmpty() ? "" : tenantVO.getBranchName());
						tenantBankDetailsDao.saveTenantBankDetails(tenantBankDetails);
					} else {
						tenantBankDetails1 = new TenantBankDetails();
						tenantBankDetails1.setCratedIPAddress(httpServletRequest.getRemoteAddr());
						tenantBankDetails1.setCreatedBy(userName);
						tenantBankDetails1.setCreatedDate(Calendar.getInstance());
						tenantBankDetails1.setModifiedDate(Calendar.getInstance());;
						tenantBankDetails1.setStatus(1);
						tenantBankDetails1.setTenantCode(tenantVO.getTenantCode() == null || tenantVO.getTenantCode().isEmpty() ? "" : tenantVO.getTenantCode());
						tenantBankDetails1.setAccountNo(tenantVO.getAccountNo() == null || tenantVO.getAccountNo().isEmpty() ? "" : tenantVO.getAccountNo());
						tenantBankDetails1.setIfscCode(tenantVO.getIfscCode() == null || tenantVO.getIfscCode().isEmpty() ? "" : tenantVO.getIfscCode());
						tenantBankDetails1.setAcctTypelov(tenantVO.getAcctTypelov() == null || tenantVO.getAcctTypelov().isEmpty() ? "" : tenantVO.getAcctTypelov());
						tenantBankDetails1.setBankNamelov(tenantVO.getBankNamelov() == null || tenantVO.getBankNamelov().isEmpty() ? "" : tenantVO.getBankNamelov());
						tenantBankDetails1.setBranchName(tenantVO.getBranchName() == null || tenantVO.getBranchName().isEmpty() ? "" : tenantVO.getBranchName());
						tenantBankDetailsDao.saveTenantBankDetails(tenantBankDetails1);
					}
				}
			} catch (Exception e) {
				LOGGER.error("TenantBankDetailsServiceImpl::saveTenantBankDetails() " + e);
				e.printStackTrace();
			}
			finally{
				tenantVO = null;
				tenantId = null;
				userName = null;
				tenantBankDetails = null;
				tenantBankDetails1 = null;
			}
		}
	}
}
