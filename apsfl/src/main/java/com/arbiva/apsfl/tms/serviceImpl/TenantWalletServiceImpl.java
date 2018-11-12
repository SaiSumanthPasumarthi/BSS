/**
 * 
 */
package com.arbiva.apsfl.tms.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arbiva.apsfl.tms.daoImpl.TenantWalletDaoImpl;
import com.arbiva.apsfl.tms.dto.TenantVO;
import com.arbiva.apsfl.tms.model.TenantWallet;

/**
 * @author Lakshman
 *
 */
@Service
public class TenantWalletServiceImpl {

	private static final Logger LOGGER = Logger.getLogger(TenantWalletServiceImpl.class);

	@Autowired
	TenantWalletDaoImpl tenantWalletDao;

	@Autowired
	HttpServletRequest httpServletRequest;

	

	public List<TenantWallet> findAllTenantWallets() {
		List<TenantWallet> tenantWalletList = new ArrayList<TenantWallet>();
		try{
			tenantWalletList = tenantWalletDao.findAllTenantWallets();
		}catch(Exception e){
			
		}finally{
			
		}
		
		return tenantWalletList;
	}

	public TenantWallet findByTenantCode(String tenantCode) {
		TenantWallet obj = null;
		try{
			obj = tenantWalletDao.findByTenantCode(tenantCode);
		}catch(Exception e){
			
		}finally{
			
		}
		return obj;
	}

	public void saveTenantWallet(TenantVO tenantVO, Integer tenantId) {
		String loginID = (String) httpServletRequest.getSession(false).getAttribute("loginID");
		TenantWallet tenantWallet = null;
		try{
			
			if (loginID != null || loginID != "") {
				
				tenantWallet = tenantWalletDao.findByTenantCode(tenantVO.getTenantCode());
				if(tenantWallet == null){
					tenantWallet = new TenantWallet();
				}
				tenantWallet.setTenantCode(tenantVO.getTenantCode());
				tenantWallet.setDepositAmount(new BigDecimal(0));
				tenantWallet.setDepostLastcramt(new BigDecimal(0));
				tenantWallet.setDepostLastdbamt(new BigDecimal(0));
				tenantWallet.setWalletLastcramt(new BigDecimal(0));
				tenantWallet.setWalletLastdbamt(new BigDecimal(0));
				tenantWallet.setWalletAmount(new BigDecimal(0));
				tenantWallet.setCratedIPAddress(httpServletRequest.getRemoteAddr());
				tenantWallet.setCreatedBy(loginID);
				tenantWallet.setCreatedDate(Calendar.getInstance());
				tenantWallet.setModifiedDate(Calendar.getInstance());
				tenantWallet.setStatus(1);
				tenantWallet.setCrLimitAmt(tenantVO.getCreditLimit().isEmpty() ? new BigDecimal(0) : new BigDecimal(tenantVO.getCreditLimit()));
				tenantWallet.setUsedAmt(new BigDecimal(0));
				tenantWallet.setCrlimitLastdbamt(new BigDecimal(0));
				tenantWalletDao.saveTenantWallet(tenantWallet);
				LOGGER.info("saveTenantWallet :: Tenant Wallet Saved Successfully");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			LOGGER.error("saveTenantWallet ::"+ex.getMessage());
		}
		finally{
			tenantVO = null;
			tenantId = null;
			tenantWallet = null;
		}
		

	}
}
