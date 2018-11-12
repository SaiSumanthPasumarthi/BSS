package com.arbiva.apsfl.tms.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arbiva.apsfl.tms.daoImpl.TenantDocumentsDaoImpl;
import com.arbiva.apsfl.tms.dto.TenantVO;
import com.arbiva.apsfl.tms.model.TenantDocuments;
import com.arbiva.apsfl.util.FileUtil;

@Service
public class TenantDocumentsServiceImpl {

	private static final Logger LOGGER = Logger.getLogger(TenantDocumentsServiceImpl.class);

	@Autowired
	TenantDocumentsDaoImpl tenantDocumentsDao;

	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	FileUtil fileUtill;

	@Value("${IMAGE_PATH}")
	private String basePath;

	
	public void saveTenantDocument(TenantVO tenantVO, Integer tenantId) {
		
		try
		{
			if ((tenantVO.getDoclov1() != null && !tenantVO.getDoclov1().isEmpty()) && (tenantVO.getDocUniqueId1() != null && !tenantVO.getDocUniqueId1().isEmpty()))
				doSaveTenantDocs(tenantVO, "POI DOCUMENTS", tenantVO.getDocUniqueId1(), tenantVO.getDoclov1(), tenantVO.getIdProof());
			if ((tenantVO.getDoclov2() != null && !tenantVO.getDoclov2().isEmpty()) && (tenantVO.getDocUniqueId2() != null && !tenantVO.getDocUniqueId2().isEmpty()))
				doSaveTenantDocs(tenantVO, "POA DOCUMENTS", tenantVO.getDocUniqueId2(), tenantVO.getDoclov2(),  tenantVO.getAddressProof()) ;
			if (tenantVO.getLicenserefno() != null && !tenantVO.getLicenserefno().isEmpty())
				doSaveTenantDocs(tenantVO, "GENERAL DOCUMENTS", tenantVO.getLicenserefno(), "", tenantVO.getLicenceId());
		} catch (Exception e) {
			LOGGER.error("TenantDocumentsServiceImpl::saveTenantDocument() " + e);
			e.printStackTrace();
		}
		finally{
			tenantVO = null;
			tenantId = null;
		}
	}
	
	public void doSaveTenantDocs(TenantVO tenantVO, String documentLovName, String docUniqueId, String doclov, MultipartFile file)
	{
		try
		{
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date date = fmt.parse("2999-01-01");
			Date date1 = fmt.parse(fmt.format(new Date()));
			String loginID = (String) httpServletRequest.getSession(false).getAttribute("loginID");
			TenantDocuments TenantDocument = tenantDocumentsDao.find(tenantVO.getTenantCode(), documentLovName, date1);
			if(TenantDocument != null)
			{
				TenantDocument.setModifiedDate(Calendar.getInstance());
				TenantDocument.setModifiedBy(loginID);
				TenantDocument.setModifiedIPAddress(httpServletRequest.getRemoteAddr());
				String dbPath = fileUtill.saveImage(file.getOriginalFilename(), basePath,
						file.getBytes(), loginID, documentLovName);
				TenantDocument.setDocmentLov(doclov == null || doclov.isEmpty() ? "" : doclov);
				TenantDocument.setDocuniqueId(docUniqueId == null || docUniqueId.isEmpty() ? "" : docUniqueId);
				TenantDocument.setDocumentLocationReference(dbPath);
				tenantDocumentsDao.saveTenantDocument(TenantDocument);
			}
			else
			{
				TenantDocuments TenantDocument1 = tenantDocumentsDao.findByTenantCode(tenantVO.getTenantCode(), documentLovName);
				if(TenantDocument1 != null)
				{
					TenantDocument1.setEffectiveTo(date1);
					tenantDocumentsDao.saveTenantDocument(TenantDocument1);
				}
				
				TenantDocuments newTenantDocument = new TenantDocuments();
				newTenantDocument.setCratedIPAddress(httpServletRequest.getRemoteAddr());
				newTenantDocument.setCreatedBy(loginID);
				newTenantDocument.setCreatedDate(Calendar.getInstance());
				newTenantDocument.setStatus(1);
				newTenantDocument.setModifiedDate(Calendar.getInstance());
				String dbPath = fileUtill.saveImage(file.getOriginalFilename(), basePath,
						file.getBytes(), loginID, documentLovName);
				newTenantDocument.setTenantCode(tenantVO.getTenantCode());
				newTenantDocument.setDocumentLovName(documentLovName);
				newTenantDocument.setDocmentLov(doclov == null || doclov.isEmpty() ? "" : doclov);
				newTenantDocument.setDocuniqueId(docUniqueId == null || docUniqueId.isEmpty() ? "" : docUniqueId);
				newTenantDocument.setEffectiveFrom(date1);
				newTenantDocument.setEffectiveTo(date);
				newTenantDocument.setDocumentLocationReference(dbPath);
				tenantDocumentsDao.saveTenantDocument(newTenantDocument);
			}
		}catch (Exception e) {
			LOGGER.error("TenantDocumentsServiceImpl::doSaveTenantDocs() " + e);
			e.printStackTrace();
		}
		finally{
			tenantVO = null;
			documentLovName = null;
			docUniqueId = null;
			doclov = null;
			file = null;
		}
	}

	public List<TenantDocuments> findByTenantCode(String tenantCode) {
		List<TenantDocuments> tenantDocument = new ArrayList<TenantDocuments>();
		tenantDocument = tenantDocumentsDao.findByTenantCode(tenantCode);
		return tenantDocument;
	}
}
