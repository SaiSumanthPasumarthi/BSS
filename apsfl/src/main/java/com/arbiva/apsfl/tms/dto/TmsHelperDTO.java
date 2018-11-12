package com.arbiva.apsfl.tms.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


import com.arbiva.apsfl.coms.dto.OLT;
import com.arbiva.apsfl.tms.model.AdditionalService;
import com.arbiva.apsfl.tms.model.MspChnlsStg;
import com.arbiva.apsfl.tms.model.Tenant;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TmsHelperDTO implements Serializable {
	
	private static final long serialVersionUID = -297951986955190295L;
	
	private Map<String,String> stateList;
	private Map<String,String> districtList;
	private Map<String,String> mandalList ;
	private Map<String,String> villageList ;
	private Map<String,String> cableList;
	private Map<String,String> assetsList;
	private List<Region> regionsList ;
	private List<Lovs> generalDoc ;
	private List<Lovs> poiDoc;
	private List<Lovs> poaDoc ;
	private List<Lovs> tenantTpesList ;
	private String status;
	private List<TenantDTO> tenantsLis;
	private List<ListOfPortalAssets> portalAssetsList;
	private List<ListOfPortalAreas> portalAreasList;
	private List<TenantDocuments> tenantDocuments;
	private TenantVO tenantVo;
	private String mandalID;
	private String loginID;
	private String message;
	
	private Map<String,String> tenantTypeLovs ;
	private Map<String,String> regionLovs;
	private Map<String,String> templatesLov;
	private Map<String,String> tenantTypeVals ;
	private List<OLT> oltLovs;
	private List<OLT> oltLmoList;
	private List<OLT> oltList;
	private Map<String,String> oltPortIdList;
	private Map<String,String> templateTypesLov;
	private Map<String,String> chargeCodeslist;
	private Map<String,String> apDistrictList;
    private Map<String, Map<String, String>> mandalAndSubstationsList;
    private Map<String,Object> map;
    private List<MspChnlsStg> mspChnlsStgList;
    private List<UploadHistory> uploadHistory;
    private List<AdditionalService> additionalService;
    private List<UploadHistDTO> uploadHistDTO;
    private Map<String,String> chnlCodeName;
    private List<RevenueSharingDTO> revenueSharingDTOList;
    private List<Districts> districtsList;
	private List<Mandals> mandalsList;
	private List<Substations> subStationsList;
	private List<Tenant> mspList;
	private List<Tenant> lmoList;
    
	private String fileName;
	private String fileSize;
	private String noOfCols;
	private String noOfRows;
	
	private List<CpeStockVO> cpeStockList;
	
private List<CustomerInvDtlsDTO> customerInvDtlsDTO;
	
	public List<CustomerInvDtlsDTO> getCustomerInvDtlsDTO() {
		return customerInvDtlsDTO;
	}

	public void setCustomerInvDtlsDTO(List<CustomerInvDtlsDTO> customerInvDtlsDTO) {
		this.customerInvDtlsDTO = customerInvDtlsDTO;
	}
	
	public List<CpeStockVO> getCpeStockList() {
		return cpeStockList;
	}

	public void setCpeStockList(List<CpeStockVO> cpeStockList) {
		this.cpeStockList = cpeStockList;
	}
	
	@JsonIgnore
	private MultipartFile UploadFile;
	
	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getMandalID() {
		return mandalID;
	}

	public void setMandalID(String mandalID) {
		this.mandalID = mandalID;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, Map<String, String>> getMandalAndSubstationsList() {
		return mandalAndSubstationsList;
	}

	public void setMandalAndSubstationsList(Map<String, Map<String, String>> mandalAndSubstationsList) {
		this.mandalAndSubstationsList = mandalAndSubstationsList;
	}
	
	
	public Map<String, String> getApDistrictList() {
		return apDistrictList;
	}
	public void setApDistrictList(Map<String, String> apDistrictList) {
		this.apDistrictList = apDistrictList;
	}
	public Map<String, String> getChargeCodeslist() {
		return chargeCodeslist;
	}
	public void setChargeCodeslist(Map<String, String> chargeCodeslist) {
		this.chargeCodeslist = chargeCodeslist;
	}
	public Map<String, String> getTemplateTypesLov() {
		return templateTypesLov;
	}
	public void setTemplateTypesLov(Map<String, String> templateTypesLov) {
		this.templateTypesLov = templateTypesLov;
	}
	public Map<String, String> getOltPortIdList() {
		return oltPortIdList;
	}
	public void setOltPortIdList(Map<String, String> oltPortIdList) {
		this.oltPortIdList = oltPortIdList;
	}
	public Map<String, String> getTenantTypeLovs() {
		return tenantTypeLovs;
	}
	public void setTenantTypeLovs(Map<String, String> tenantTypeLovs) {
		this.tenantTypeLovs = tenantTypeLovs;
	}
	public Map<String, String> getRegionLovs() {
		return regionLovs;
	}
	public void setRegionLovs(Map<String, String> regionLovs) {
		this.regionLovs = regionLovs;
	}
	public Map<String, String> getTenantTypeVals() {
		return tenantTypeVals;
	}
	public void setTenantTypeVals(Map<String, String> tenantTypeVals) {
		this.tenantTypeVals = tenantTypeVals;
	}
	public Map<String, String> getTemplatesLov() {
		return templatesLov;
	}
	public void setTemplatesLov(Map<String, String> templatesLov) {
		this.templatesLov = templatesLov;
	}
	public List<TenantDocuments> getTenantDocuments() {
		return tenantDocuments;
	}
	public void setTenantDocuments(List<TenantDocuments> tenantDocuments) {
		this.tenantDocuments = tenantDocuments;
	}
	public List<ListOfPortalAssets> getPortalAssetsList() {
		return portalAssetsList;
	}
	public void setPortalAssetsList(List<ListOfPortalAssets> portalAssetsList) {
		this.portalAssetsList = portalAssetsList;
	}
	public List<ListOfPortalAreas> getPortalAreasList() {
		return portalAreasList;
	}
	public void setPortalAreasList(List<ListOfPortalAreas> portalAreasList) {
		this.portalAreasList = portalAreasList;
	}
	public TenantVO getTenantVo() {
		return tenantVo;
	}
	public void setTenantVo(TenantVO tenantVo) {
		this.tenantVo = tenantVo;
	}
	public List<TenantDTO> getTenantsLis() {
		return tenantsLis;
	}
	public void setTenantsLis(List<TenantDTO> tenantsLis) {
		this.tenantsLis = tenantsLis;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, String> getStateList() {
		return stateList;
	}
	public void setStateList(Map<String, String> stateList) {
		this.stateList = stateList;
	}
	public Map<String, String> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(Map<String, String> districtList) {
		this.districtList = districtList;
	}
	public Map<String, String> getMandalList() {
		return mandalList;
	}
	public void setMandalList(Map<String, String> mandalList) {
		this.mandalList = mandalList;
	}
	public Map<String, String> getVillageList() {
		return villageList;
	}
	public void setVillageList(Map<String, String> villageList) {
		this.villageList = villageList;
	}
	public Map<String, String> getCableList() {
		return cableList;
	}
	public void setCableList(Map<String, String> cableList) {
		this.cableList = cableList;
	}
	public Map<String, String> getAssetsList() {
		return assetsList;
	}
	public void setAssetsList(Map<String, String> assetsList) {
		this.assetsList = assetsList;
	}
	public List<Region> getRegionsList() {
		return regionsList;
	}
	public void setRegionsList(List<Region> regionsList) {
		this.regionsList = regionsList;
	}
	public List<Lovs> getGeneralDoc() {
		return generalDoc;
	}
	public void setGeneralDoc(List<Lovs> generalDoc) {
		this.generalDoc = generalDoc;
	}
	public List<Lovs> getPoiDoc() {
		return poiDoc;
	}
	public void setPoiDoc(List<Lovs> poiDoc) {
		this.poiDoc = poiDoc;
	}
	
	public List<Lovs> getPoaDoc() {
		return poaDoc;
	}
	public void setPoaDoc(List<Lovs> poaDoc) {
		this.poaDoc = poaDoc;
	}
	public List<Lovs> getTenantTpesList() {
		return tenantTpesList;
	}
	public void setTenantTpesList(List<Lovs> tenantTpesList) {
		this.tenantTpesList = tenantTpesList;
	}
	public List<OLT> getOltLovs() {
		return oltLovs;
	}
	public void setOltLovs(List<OLT> oltLovs) {
		this.oltLovs = oltLovs;
	}
	public List<OLT> getOltLmoList() {
		return oltLmoList;
	}
	public void setOltLmoList(List<OLT> oltLmoList) {
		this.oltLmoList = oltLmoList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getNoOfCols() {
		return noOfCols;
	}

	public void setNoOfCols(String noOfCols) {
		this.noOfCols = noOfCols;
	}

	public String getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(String noOfRows) {
		this.noOfRows = noOfRows;
	}

	public List<MspChnlsStg> getMspChnlsStgList() {
		return mspChnlsStgList;
	}

	public void setMspChnlsStgList(List<MspChnlsStg> mspChnlsStgList) {
		this.mspChnlsStgList = mspChnlsStgList;
	}

	public List<UploadHistory> getUploadHistory() {
		return uploadHistory;
	}

	public void setUploadHistory(List<UploadHistory> uploadHistory) {
		this.uploadHistory = uploadHistory;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<AdditionalService> getAdditionalService() {
		return additionalService;
	}

	public void setAdditionalService(List<AdditionalService> additionalService) {
		this.additionalService = additionalService;
	}

	public List<UploadHistDTO> getUploadHistDTO() {
		return uploadHistDTO;
	}

	public void setUploadHistDTO(List<UploadHistDTO> uploadHistDTO) {
		this.uploadHistDTO = uploadHistDTO;
	}

	public Map<String,String> getChnlCodeName() {
		return chnlCodeName;
	}

	public void setChnlCodeName(Map<String,String> chnlCodeName) {
		this.chnlCodeName = chnlCodeName;
	}

	public List<RevenueSharingDTO> getRevenueSharingDTOList() {
		return revenueSharingDTOList;
	}

	public void setRevenueSharingDTOList(List<RevenueSharingDTO> revenueSharingDTOList) {
		this.revenueSharingDTOList = revenueSharingDTOList;
	}

	public List<Districts> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<Districts> districtsList) {
		this.districtsList = districtsList;
	}

	public List<Mandals> getMandalsList() {
		return mandalsList;
	}

	public void setMandalsList(List<Mandals> mandalsList) {
		this.mandalsList = mandalsList;
	}

	public List<Substations> getSubStationsList() {
		return subStationsList;
	}

	public void setSubStationsList(List<Substations> subStationsList) {
		this.subStationsList = subStationsList;
	}

	public List<OLT> getOltList() {
		return oltList;
	}

	public void setOltList(List<OLT> oltList) {
		this.oltList = oltList;
	}

	public List<Tenant> getMspList() {
		return mspList;
	}

	public void setMspList(List<Tenant> mspList) {
		this.mspList = mspList;
	}

	public List<Tenant> getLmoList() {
		return lmoList;
	}

	public void setLmoList(List<Tenant> lmoList) {
		this.lmoList = lmoList;
	}
	
}
