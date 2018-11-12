<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
function editRecord(id) {
	window.location.href = "./editTenantAgreementDetails?tenantCode=" + id;
}
</script>  
  <!-- END HEADER --> 
  <!-- BEGIN MAIN CONTENT -->
  <div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong>Tenant Agreement with MSP</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./">Home</a> </li>
            <li class="active">Tenant Agreement with MSP</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<div class="row">
            <div class="col-lg-12">
              <!-- Custom Tabs -->
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#tab_1" data-toggle="tab">Tenant</a></li>
                  <li ><a href="#tab_2" id="tenantAgreementId" data-toggle="tab">Agreement</a></li>
                  
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="tab_1">
                   
            <div class="page-content page-width">
      <div class="page-title">
        <h2>Create<strong> Tenant</strong></h2>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form name="tenantfrm" class="form-validation" action="<c:url value="/cretaeTenant"/>" method="post" id="lom_form" enctype="multipart/form-data">

              <!-- END ROW -->
			    <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Tenant Information</h3>
                    </div>
                    <div class="panel-content">
					                      <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Name<span style="color: red;">*</span></label>
                      <input type="hidden" name="tenantId" id="tenantId" value="${tenantVO.tenantId}"/>
                      <input type="text" id="name" name="name" value="${tenantVO.name}" class="form-control form-white">
                    </div>
                  </div>
                 <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Tenant Type <span style="color: red;">*</span></label>
						<div class="option-group"><!-- <a title="Please select Tenant Type LOV" class="tooltip1"> -->
                        <select name="tenantTypeLov" id="tenantTypeLov" class="form-control form-white">
                                <option value="">Select Tenant Type </option>
								<option <c:choose><c:when test="${tenantVO.tenantTypeLov =='LMO'}"> value="LMO" selected </c:when>    
    									<c:otherwise>value="LMO"</c:otherwise></c:choose>>LMO</option>
								<option <c:choose><c:when test="${tenantVO.tenantTypeLov =='MSP'}"> value="MSP" selected </c:when>    
    									<c:otherwise>value="MSP"</c:otherwise></c:choose>>MSP</option>
                        </select>
                     </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                            <label class="form-label">Region<span style="color: red;">*</span></label>
                            <div class="option-group">
                            <!-- <input type="text" id="region" name="region" class="form-control form-white" placeholder="Enter Region" required> -->
                            <!-- <a title="Please Select Region" class="tooltip1"> -->
                            <select id="region" name="region" class="form-control form-white" disabled>
								<option value="">Select Region</option>
								<c:forEach var="region" items="${regionList}">
								<c:choose>
								<c:when
										test="${not empty tenantLicenseVO && not empty tenantLicenseVO.region && region.regionName==tenantLicenseVO.region.regionName}">
										<option value="${region.regionName}" selected >${region.regionName}</option>
								</c:when>
								<c:otherwise>
									<option value="${region.regionName}">${region.regionName}</option>
								</c:otherwise>
								</c:choose>		
								</c:forEach>
						</select>
                          </div>
                         </div>
                        </div>					  
					  </div>
				<div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Tenant Code<span style="color: red;">*</span></label>
                      <input type="text" name="tenantCode" id="tenantCode" class="form-control form-white" placeholder="Enter Tenant Code" maxlength="30" pattern="[a-zA-Z0-9]+" <c:if test="${not empty tenantVO}"> readonly </c:if> value="${tenantVO.tenantCode}"/>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">PAN</label>
                            <a title="Please Enter PAN Number" class="tooltip1"><input type="text" name="panNo" id="panNo" class="form-control form-white" placeholder="Enter PAN" value="${tenantVO.panNo}" maxlength="10" pattern="[A-Z]{5}\d{4}[A-Z]{1}"/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Aadhar</label>
                            <a title="Please Enter Aadhar Card Number" class="tooltip1"><input type="text" name="aadharCardNo" id="aadharCardNo" class="form-control form-white" placeholder="Enter Aadhar" value="${tenantVO.aadharCardNo}" maxlength="12" pattern="[0-9]{12}"/></a>
                          </div>
                        </div>					  
					  </div>
					 <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">TAN</label>
                      <a title="Please Enter TAN Number" class="tooltip1"><input type="text" name="tanNo" id="tanNo" class="form-control form-white" placeholder="Enter TAN" value="${tenantVO.tanNo}" maxlength="20" pattern="[a-zA-Z0-9]+"/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">TIN</label>
                            <a title="Please Enter TIN Number" class="tooltip1"><input type="text" name="tinNo" id="tinNo" class="form-control form-white" placeholder="Enter TIN" value="${tenantVO.tinNo}" maxlength="20" pattern="\d+"/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">GST</label>
                            <a title="Please Enter GST Number" class="tooltip1"><input type="text" name="gstNo" id="gstNo" class="form-control form-white" placeholder="Enter GST" value="${tenantVO.gstNo}" maxlength="20" pattern="[a-zA-Z0-9]+"/></a>
                          </div>
                        </div>					  
					  </div>
				<div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">VAT</label>
                      <a title="Please Enter VAT Number" class="tooltip1"><input type="text" name="vatNo" id="vatNo" class="form-control form-white" placeholder="Enter VAT" value="${tenantVO.vatNo}" maxlength="20" pattern="[a-zA-Z0-9]+"/></a>
                    </div>
                  </div>					  
					  </div>
                      <!-- END ROW INNER-->
                      <!-- END ROW INNER-->
                      <!-- END ROW INNER--> 
                      
                    </div>
                  </div>
                </div>
              </div>
			  
			  			  <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Address Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Address Line 1</label>
                      <a title="Please Enter Address 1" class="tooltip1"><textarea name="address1" id="address1" class="form-control form-white" placeholder="Enter Address Line 1" maxlength="225">${tenantVO.address1}</textarea></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Address Line 2</label>
						<a title="Please Enter Address 2" class="tooltip1"><textarea type="text" name="address2" id="address2" class="form-control form-white" maxlength="225" value="${tenantVO.address2}" placeholder="Enter Address Line 2"></textarea></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Locality</label>
                            <a title="Please Enter Locality" class="tooltip1"><input type="text" name="locality" id="locality" class="form-control form-white" placeholder="Enter Locality" value="${tenantVO.locality}" maxlength="100" pattern="[a-zA-Z]*"/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Area</label>
                      <a title="Please Enter Area" class="tooltip1"><input type="text" name="area" id="area" class="form-control form-white" placeholder="Enter Area" value="${tenantVO.area}" maxlength="100" pattern="[a-zA-Z]*"/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">City</label>
                            <a title="Please Enter City" class="tooltip1"><input type="text" name="city" id="city" class="form-control form-white" placeholder="Enter City" value="${tenantVO.city}" maxlength="20" pattern="[a-zA-Z]*"/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">State</label>
                            <a title="Please Enter State" class="tooltip1"><input type="text" name="stateName" id="stateName" class="form-control form-white" placeholder="Enter State" value="${tenantVO.stateName}" maxlength="20" pattern="[a-zA-Z][a-zA-Z\s]*"/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Pin Code</label>
                      <a title="Please Enter PIN Code" class="tooltip1"><input type="text" name="pincode" id="pincode" class="form-control form-white" placeholder="Enter PIN Code" value="${tenantVO.pincode}" maxlength="6" pattern="[0-9]{6}"/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">STD Code</label>
                            <a title="Please Enter STD Code" class="tooltip1"><input type="text" name="stdcode" id="stdcode" class="form-control form-white" placeholder="Enter STD Code" value="${tenantVO.stdcode}"  maxlength="5" pattern="\d+"/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Land Line</label>
                            <a title="Please Enter Landline" class="tooltip1"><input type="text" name="landline1" id="landline1" class="form-control form-white" placeholder="Enter Land Line" value="${tenantVO.landline1}"  maxlength="10" pattern="\d+"/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Email Id</label>
							<a title="Please Enter EmailId 1" class="tooltip1"><input type="text" name="emailId1" id="emailId1" class="form-control form-white" placeholder="Enter Email Id" value="${tenantVO.emailId1}" maxlength="100" /></a>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Fax</label>
							<a title="Please Enter Fax 1" class="tooltip1"><input type="text" name="fax1" id="fax1" class="form-control form-white" placeholder="Enter Fax" value="${tenantVO.fax1}" maxlength="10" pattern="\d+"/></a>
							</div>
						</div>					  
					  </div>
                      <!-- END ROW INNER-->
                      <!-- END ROW INNER--> 
                      
                    </div>
                  </div>
                </div>
              </div>
			  
			  			  <div class="row">
                <div class="col-sm-6">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Point of Contact Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Name</label>
                            <a title="Please Enter POC Name" class="tooltip1"><input type="text" name="pocName" id="pocName" class="form-control form-white" placeholder="Enter Point of Contact Name" value="${tenantVO.pocName}" maxlength="100" pattern="[a-zA-Z][a-zA-Z\s]*"/></a>
                          </div>
                        </div>
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Mobile Number</label>
                            <a title="Please Enter POC Mobile 1" class="tooltip1"><input type="text" name="pocMobileNo1" id="pocMobileNo1" class="form-control form-white" placeholder="Enter Point of Contact Mobile Number" value="${tenantVO.pocMobileNo1}" maxlength="10" pattern="[789][0-9]{9}"/></a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
				<div class="clear"></div>
              </div>
			  
			  			  <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Tenant Local Office Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Address Line 1</label>
                      <a title="Please Enter Local Office Address 1" class="tooltip1"><textarea name="localOfficeAddress1" id="localOfficeAddress1" class="form-control form-white" placeholder="Enter Address Line 1" maxlength="225">${tenantVO.localOfficeAddress1}</textarea></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Address Line 2</label>
                      <a title="Please Enter Local Office Address 2" class="tooltip1"><textarea name="localOfficeAddress2" id="localOfficeAddress2" class="form-control form-white" placeholder="Enter Address Line 2" maxlength="225">${tenantVO.localOfficeAddress2}</textarea></a>
						<!-- <input type="text" name="vat" class="form-control form-white" placeholder="Enter Address Line 2" required> -->
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Locality</label>
                            <a title="Please Enter Local Office Locality" class="tooltip1"><input type="text" name="localOfficeLocality" id="localOfficeLocality" class="form-control form-white" placeholder="Enter Locality" value="${tenantVO.localOfficeLocality}" maxlength="150" pattern="[a-zA-Z][a-zA-Z\s]*"/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Area</label>
                      <a title="Please Enter Local Office Area" class="tooltip1"><input type="text" name="localOfficeArea" id="localOfficeArea" class="form-control form-white" placeholder="Enter Area" value="${tenantVO.localOfficeArea}" maxlength="100" pattern="[a-zA-Z][a-zA-Z\s]*"/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">City</label>
                            <a title="Please Enter Local Office City" class="tooltip1"><input type="text" name="localOfficeCity" id="localOfficeCity" class="form-control form-white" placeholder="Enter City" value="${tenantVO.localOfficeCity}" maxlength="20" pattern="[a-zA-Z]*"/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">State</label>
                            <a title="Please Enter Local Office State" class="tooltip1"><input type="text" name="localOfficeStateName" id="localOfficeStateName" class="form-control form-white" placeholder="Enter State" value="${tenantVO.localOfficeStateName}" maxlength="25" pattern="[a-zA-Z][a-zA-Z\s]*"/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Pin Code</label>
                      <a title="Please Enter Local Office PIN Code" class="tooltip1"><input type="text" name="localOfficePincode" id="localOfficePincode" class="form-control form-white" placeholder="Enter PIN Code" value="${tenantVO.localOfficePincode}" maxlength="6" pattern="\d+"/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">STD Code</label>
                            <a title="Please Enter Local Office STD Code" class="tooltip1"><input type="text" name="localOfficestdcode" id="localOfficestdcode" class="form-control form-white" placeholder="Enter STD Code" value="${tenantVO.pocMobileNo2}" maxlength="5" pattern="\d+"/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Land Line</label>
                            <a title="Please Enter Local Office Landline 1" class="tooltip1"><input type="text" name="localOfficeLandline1" id="localOfficeLandline1" class="form-control form-white" placeholder="Enter Land Line" value="${tenantVO.localOfficeLandline1}" maxlength="10" pattern="\d+"/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Email Id</label>
							<a title="Please Enter Local Office EmailId 1" class="tooltip1"><input type="text" name="localOfficeEmailId1" id="localOfficeEmailId1" class="form-control form-white" placeholder="Enter Email Id" value="${tenantVO.localOfficeEmailId1}" maxlength="100" /></a>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Fax</label>
							<a title="Please Enter Local Office EmailId 2" class="tooltip1"><input type="text" name="localOfficeEmailId2" id="localOfficeEmailId2" class="form-control form-white" placeholder="Enter Email Fax" value="${tenantVO.localOfficeEmailId2}" maxlength="10" pattern="\d+" /></a>
							</div>
						</div>					  
					  </div>
                      <!-- END ROW INNER-->
                      <!-- END ROW INNER--> 
                      
                    </div>
                  </div>
                </div>
              </div>
			  
			  			  <div class="row">
                <div class="col-sm-6">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Local Office Point of Contact Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Name</label>
                            <a title="Please Enter Local Office POC Name" class="tooltip1"><input type="text" name="localOfficePocName" id="localOfficePocName" class="form-control form-white" placeholder="Enter Point of Contact Name" value="${tenantVO.localOfficePocName}" maxlength="100" pattern="[a-zA-Z][a-zA-Z\s]*"/></a>
                          </div>
                        </div>
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Mobile Number</label>
                            <a title="Please Enter Local Office POC Mobile 1" class="tooltip1"><input type="text" name="localOfficePocMobileNo1" id="localOfficePocMobileNo1" class="form-control form-white" placeholder="Enter Point of Contact Mobile Number" value="${tenantVO.localOfficePocMobileNo1}" maxlength="10" pattern="[789][0-9]{9}"/></a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
				<div class="clear"></div>
              </div>
			  
			  			  <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>License Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">License Number<span style="color: red;">*</span></label>
                      <a title="Please Enter License Number" class="tooltip1"><input type="text" name="licenserefno" id="licenserefno" class="form-control form-white" placeholder="Enter License Number" <c:if test="${not empty tenantLicenseVO}"> readonly </c:if> value="${tenantLicenseVO.licenserefno}" pattern="[a-zA-Z0-9]+"/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Document <span style="color: red;">*</span></label>
						<div class="option-group"><!-- <a title="Please Select Document Lov" class="tooltip1"> -->
						<c:if test="${not empty tenantDocumentsVO}"><input type="hidden" id="doclov" name="doclov" value="${tenantDocumentsVO.docmentLov}" /></c:if>
                        <select id="doclov" name="doclov" class="form-control form-white" <c:if test="${not empty tenantDocumentsVO}"> disabled </c:if>>
						<option value="">Select Document</option>
						<c:forEach var="lovs" items="${lovsList}">
							<c:choose>
							<c:when
								test="${not empty tenantDocumentsVO && lovs.lovId == tenantDocumentsVO.docmentLov}">
									<option value="${lovs.lovId}" selected >${lovs.lovValue}</option>
									</c:when>
									<c:otherwise>
									<option value="${lovs.lovId}">${lovs.lovValue}</option>
									</c:otherwise>
							</c:choose>	
						
						</c:forEach>
						</select>
                     </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Upload License<span style="color: red;">*</span></label>
                           	<input type="file" name="licenceId" id="licenceId" class="form-control form-white" placeholder="Attach License copy" /><c:if test="${not empty tenantDocumentsVO}">
                           	<span class="galleryImgs">
                            <a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO.documentLocationReference}" /></a></span></c:if>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Valid From<span style="color: red;">*</span></label>
                      <a title="Please Select Effective From Date" class="tooltip1"><input type="text" name="effectiveFrom" id="effectiveFrom" class="date-picker form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO}"> readonly </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO.effectiveFrom}" />' /></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Valid To<span style="color: red;">*</span></label>
                      <a title="Please Select Effective To Date" class="tooltip1"><input type="text" name="effectiveTO" id="effectiveTO" class="date-picker form-control form-white" placeholder="Select a date..." value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO.effectiveTo}" />'/></a>
                    </div>
                  </div>				  
					  </div>                      
                    </div>
                  </div>
                </div>
              </div>
			  
			  			  <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>ID Proof Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">ID Proof Number<span style="color: red;">*</span></label>
                      <a title="Please Enter ID Proof Number" class="tooltip1"><input type="text" name="docUniqueId1" id="docUniqueId1" class="form-control form-white" placeholder="Enter ID Proof Number" value="${tenantDocumentsVO1.docuniqueId}" /></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Document <span style="color: red;">*</span></label>
						    <div class="option-group">
						    <!-- <a title="Please Select Document Lov" class="tooltip1"> -->
						    <c:if test="${not empty tenantDocumentsVO1}"><input type="hidden" id="doclov1" name="doclov1" value="${tenantDocumentsVO1.docmentLov}" /></c:if>
                        <select id="doclov1" name="doclov1" class="form-control form-white" <c:if test="${not empty tenantDocumentsVO1}">disabled</c:if>>
						<option value="">Select Document </option>
						<c:forEach var="lovs" items="${lovsList}">
							<c:choose>
							<c:when
								test="${not empty tenantDocumentsVO1 && lovs.lovId == tenantDocumentsVO1.docmentLov}">
									<option value="${lovs.lovId}" selected >${lovs.lovValue}</option>
									</c:when>
									<c:otherwise>
									<option value="${lovs.lovId}">${lovs.lovValue}</option>
									</c:otherwise>
							</c:choose>	
						
						</c:forEach>
						</select>
                            </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Upload ID Proof<span style="color: red;">*</span></label>
                            <input type="file" name="idProof" id="idProof" class="form-control form-white" placeholder="Attach ID Proof Copy" ><c:if test="${not empty tenantDocumentsVO1}"> <span class="galleryImgs">
			   	    	<a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO1.documentLocationReference}" /></a></span> </c:if>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Valid From<span style="color: red;">*</span></label>
                      <a title="Please Select Effective From Date" class="tooltip1"><input type="text" name="effectiveFrom1" id="effectiveFrom1" class="date-picker form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO1}"> readonly </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' /></a>
                      <%-- <a title="Please Select Effective From Date" class="tooltip1"><input type="date" class="datepicker" class="datepicker" name="effectiveFrom1" id="effectiveFrom1" class="date-picker form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO1}">  </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' /></a> --%>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Valid To<span style="color: red;">*</span></label>
                      <a title="Please Select Effective To Date" class="tooltip1"><input type="text" name="effectiveTO1" id="effectiveTO1" class="date-picker form-control form-white" placeholder="Select a date..." value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveTo}" />'/></a>
                        <%-- <a title="Please Select Effective To Date" class="tooltip1"><input type="date" class="datepicker" name="effectiveTO1" id="effectiveTO1" class="date-picker form-control form-white" placeholder="Select a date..." value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveTo}" />' /></a> --%>
                    </div>
                  </div>				  
					  </div>                      
                    </div>
                  </div>
                </div>
              </div>
			  
			  			  <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Address Proof Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Address Proof Number<span style="color: red;">*</span></label>
                      <a title="Please Enter Address Proof Number" class="tooltip1"><input type="text" name="docUniqueId2" id="docUniqueId2" class="form-control form-white" placeholder="Enter Address Proof Number" value="${tenantDocumentsVO2.docuniqueId}" /></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Document <span style="color: red;">*</span></label>
						    <div class="option-group">
                              <!-- <a title="Please Select Document Lov" class="tooltip1"> -->
                              <c:if test="${not empty tenantDocumentsVO2}"><input type="hidden" id="doclov2" name="doclov2" value="${tenantDocumentsVO2.docmentLov}" /></c:if>
                        <select id="doclov2" name="doclov2" class="form-control form-white" <c:if test="${not empty tenantDocumentsVO2}">disabled</c:if>>
						<option value="">Select Document </option>
						<c:forEach var="lovs" items="${lovsList}">
							<c:choose>
							<c:when
								test="${not empty tenantDocumentsVO2 && lovs.lovId == tenantDocumentsVO2.docmentLov}">
									<option value="${lovs.lovId}" selected >${lovs.lovValue}</option>
									</c:when>
									<c:otherwise>
									<option value="${lovs.lovId}">${lovs.lovValue}</option>
									</c:otherwise>
							</c:choose>	
						
						</c:forEach>
						</select>
                            </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Upload Address Proof<span style="color: red;">*</span></label>
                            <input type="file" name="addressProof" id="addressProof" class="form-control form-white" placeholder="Attach Address Proof Copy" /><c:if test="${not empty tenantDocumentsVO2}"> <span class="galleryImgs">
			   	    	<a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO2.documentLocationReference}" /></a></span> </c:if>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Valid From<span style="color: red;">*</span></label>
                      <a title="Please Select Effective From Date" class="tooltip1"><input type="text" name="effectiveFrom2" id="effectiveFrom2" class="date-picker form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO2}"> readonly </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO2.effectiveFrom}" />' /></a>
                      <%-- <a title="Please Select Effective From Date" class="tooltip1"><input type="date" class="datepicker" name="effectiveFrom2" id="effectiveFrom2" class="date-picker form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO1}">  </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' /></a> --%>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Valid To<span style="color: red;">*</span></label>
                      <a title="Please Select Effective To Date" class="tooltip1"><input type="text" name="effectiveTO2" id="effectiveTO2" class="date-picker form-control form-white" placeholder="Select a date..." value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO2.effectiveTo}" />'/></a>
                        <%-- <a title="Please Select Effective To Date" class="tooltip1"><input type="date" class="datepicker" name="effectiveTO2" id="effectiveTO2" class="date-picker form-control form-white" placeholder="Select a date..." value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveTo}" />' /></a> --%>
                    </div>
                  </div>				  
					  </div>                      
                    </div>
                  </div>
                </div>
              </div>
			  
			  <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Bank Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">A/C Number<span style="color: red;">*</span></label>
                      <a title="Please Enter Bank Account Number" class="tooltip1"><input type="text" name="accountNo" id="accountNo" class="form-control form-white" placeholder="Enter Account Number" maxlength="15" pattern="\d+" <c:if test="${not empty tenantLicenseVO}"> readonly </c:if> value="${tenantBankVO.accountNo}" /></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">IFSC Code<span style="color: red;">*</span></label>
					  <a title="Please Enter IFSC Code" class="tooltip1"><input type="text" name="ifscCode" id="ifscCode" class="form-control form-white" placeholder="Enter IFSC Code" maxlength="11" pattern="[A-Z]{4}[0][\d]{6}$" value="${tenantBankVO.ifscCode}" /></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Account Type<span style="color: red;">*</span></label>
                        <a title="Please Enter Type of Account" class="tooltip1"><input type="text" name="acctTypelov" id="acctTypelov" class="form-control form-white" placeholder="Enter Account Type" value="${tenantBankVO.acctTypelov}" /></a>
                    </div>
                  </div>
                  					  
				</div>
					  <div class="row">
				
                  <div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Branch Name<span style="color: red;">*</span></label>
                      <a title="Please Enetr Branch Name" class="tooltip1"><input type="text" name="branchName" id="branchName" class="form-control form-white" placeholder="Enter Branch Name" value="${tenantBankVO.branchName}" maxlength="30"/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
						<label class="form-label">Bank Name<span style="color: red;">*</span></label>
                        <a title="Please Enter Bank Name" class="tooltip1"><input type="text" name="bankNamelov" id="bankNamelov" class="form-control form-white" placeholder="Enter Bank Name" value="${tenantBankVO.bankNamelov}" maxlength="50"/></a>
                    </div>
                  </div>				  
					  </div>                      
                    </div>
                  </div>
                </div>
              </div>
              </form>
			  </div></div></div></div>
			  
			  
			  </div>
          
                  </div><!-- /.tab-pane -->
                  <div class="tab-pane" id="tab_2">
			<div class="row">
                <div class="col-sm-12">
				<div class="panel">
				<div class="panel-header bg-light">
                      <h3>Tenant Agreement with APF</h3>
                    </div>
				<div class="panel-content">
              <form name="tenantAgmtForm" action="<c:url value="/approveTenantAgreement"/>" method="post" id="tenantApproval_form" enctype="multipart/form-data">
			<!-- <form role="form" class="form-validation">  -->
			<div class="row">
                <div class="col-sm-12">
				<div class="panel">
				<div class="panel-header bg-light">
                      <h3>APF Agreement with <strong>${tenantVO.name}</strong></h3>
                    </div>
				<div class="panel-content">
		
              <div class="row">                
                 <div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Tenant Name<span style="color: red;">*</span></label>
                      <input type="hidden" name="tenantId" id="tenantId" value="${tenantVO.tenantId}"/>
                      <input type="hidden" id="tenantCode" name="tenantCode" placeholder="Select Tenant Code" value="${tenantVO.tenantCode}" />
                      <input type="text" value="${tenantVO.name}" class="form-control form-white" readonly>
                    </div>
                  </div>
				 <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Select Core Service<span style="color: red;">*</span></label>
                       
                            <div class="option-group">
                            <select id="coreService" name="coreService" class="form-control form-white" >
                            	 <c:forEach var="tenenatService" items="${tenantServiceVO}">
										<option value="">${tenenatService.coresrvcCode}</option>
						 		</c:forEach>
							</select>
                            </div>
                            
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Agreement Date<span style="color: red;">*</span></label>
							<input type="text" name="agreementDate" id="agreementDate" class="date-picker form-control form-white" placeholder="Select a date..." readonly value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgreementVO1.agreementDate}" />'>
                          </div>
                        </div>
				 	
						</div>
						<div class="row">
						<div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Aggrement From<span style="color: red;">*</span></label>
							<input type="text"  name="agrFDate" id="agrFDate" class="date-picker form-control form-white" placeholder="Select a date..." readonly <c:if test="${not empty tenantAgreementVO.agrFDate}"> readonly </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgreementVO1.agrFDate}" />' />
                          </div>
                        </div>
						<div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Agreement To<span style="color: red;">*</span></label>
							<input type="text" name="agreementTo" id="agreementTo" class="date-picker form-control form-white" placeholder="Select a date..." readonly <c:if test="${empty tenantAgreementVO1}">value="12/31/9999" </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgreementVO1.agreementTo}" />'/>
                            <!-- <input type="text" name="datepicker2" class="date-picker form-control form-white" placeholder="Select a date..."> -->
                          </div>
                        </div>
						<div class="col-sm-4"> 
                    <div class="form-group">
                      <label class="control-label">Upload Agreement<span style="color: red;">*</span></label>
					 <c:if test="${not empty tenantAgreementVO1}"> 
					 <span class="galleryImgs">
			   	    	<a href="#"><img width="30" src="downloadFiles?path=${tenantAgreementVO1.agreementDocRef}" /></a>
			   	    	</span> </c:if>
                    </div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Deposit Amount<span style="color: red;">*</span></label>
                      <input type="text" name="depositAmount" id="depositAmount"  value="${tenantWalletVO.depositAmount}"  class="form-control form-white" placeholder="Enter Deposite Amount" readonly>
                    </div>
					</div>
					<div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Wallet Amount<span style="color: red;">*</span></label>
                      <input type="text" name="walletAmount" id="walletAmount" value="${tenantWalletVO.walletAmount}" class="form-control form-white" placeholder="Enter Wallet Amount" readonly>
                    </div>
					</div>
					</div>
					</div>
					</div>
						</div>		
              <!-- END ROW -->
              
              <!-- END ROW -->
             &nbsp;&nbsp;
             <div class="form-group">
            			 <label id = "sendBackLableId"> Please Enter Comment For SendBack</label>
            			 <label id = "rejectLableId"> Please Enter Comment For Reject</label>
						<textarea class="form-control" rows="5" id="sendBackTextAreaId" name="reason"  placeholder="Please Enter Comment For SendBack..">
						</textarea>
					</div>
					&nbsp;&nbsp;
             <div align="right">
    				<button class="btn btn-embossed btn-success" type="submit" name="action" value="Approve" value="Approve" >Approve</button>
              		<button class="btn btn-embossed btn-warning" type="button"  name="action" id="sendBack" value="Send Back"  >Send Back</button>
              		<button class="btn btn-embossed btn-warning" type="submit" name="action" id="sendBackButtonId" value="Send Back">Send Back</button>
			  		<button class="btn btn-embossed btn-danger" type="button" id="reject" name="action" value="Reject" >Reject</button>
			  		<button class="btn btn-embossed btn-danger" type="Submit" id="rejectButtonId" name="action" value="Reject" >Reject</button>
				</div>
              <%-- <button class="btn btn-embossed btn-success" type="submit" name="action" value="Save" onclick="clicked();" <c:if test="${not empty tenantAgreementVO1.tenantCode}">value="Approve" </c:if> <c:if test="${empty tenantAgreementVO1.tenantCode}"> </c:if>>Submit</button>
              <button class="btn btn-embossed btn-danger" type="button" name="action" id="sendBack" <c:if test="${not empty tenantAgreementVO1.tenantCode}">value="Send Back" type="Submit" </c:if> <c:if test="${empty tenantAgreementVO1.tenantCode}"> value="Reset" type="Reset" </c:if>>Cancel</button>
			  <c:if test="${not empty tenantAgreementVO1.tenantCode}"><button class="btn btn-embossed btn-danger" type="Submit" id="reject" name="action" value="Reject" >Reject</button></c:if> --%>
			  </div>	
			 </form>
					</div>
					</div>
					</div>
								
              <!-- END ROW -->
              
              
              
              <!-- END ROW -->
              
             <section class="v-center">
  				<%-- <div align="right">
   					<a class="js-open-modal btn_green" type="submit"  <c:if test="${not empty tenantAgreementVO1.tenantCode}"></c:if> <c:if test="${empty tenantAgreementVO1.tenantCode}"> </c:if>>Approve</a>
    				<a class="js-open-modal btn_sendbk" href="#" data-modal-id="popup1"> Send Back</a> 
    				<a class="js-open-modal btn_rej" href="#" data-modal-id="popup2"> Reject</a> 
    			</div> --%>
    			<%-- <div align="right">
    				<button class="btn btn-embossed btn-success" type="submit" name="action" value="Save" <c:if test="${not empty tenantAgreementVO1.tenantCode}">value="Approve" </c:if> <c:if test="${empty tenantAgreementVO1.tenantCode}"> </c:if>>Approve</button>
              		<button class="btn btn-embossed btn-danger" type="button" name="action" id="sendBack" <c:if test="${not empty tenantAgreementVO1.tenantCode}">value="Send Back" type="Submit" </c:if> <c:if test="${empty tenantAgreementVO1.tenantCode}"> value="Reset" type="Reset" </c:if>>Send Back</button>
			  		<c:if test="${not empty tenantAgreementVO1.tenantCode}">
			  		<button class="btn btn-embossed btn-warning" type="Submit" id="reject" name="action" value="Reject" >Reject</button></c:if>
				</div> --%>
			</section>
<div id="popup1" class="modal-box">
  <header> <a href="#" class="js-modal-close close"><img src="images/gray-square_white-x.png"></a>
    Send Back Comments
  </header>
  <div class="modal-body">
    <p><textarea name="Send Back" cols="70" rows="3">Feedback...</textarea></p>
  </div>
  <footer><a class="js-open-modal btn_green" href="#">Submit</a></footer>
</div>
<div id="popup2" class="modal-box">
  <header> <a href="#" class="js-modal-close close"><img src="images/gray-square_white-x.png"></a>
   Reject Comments
  </header>
  <div class="modal-body">
    <p><textarea name="Reject" cols="70" rows="3" >Feedback...</textarea></p>
  </div>
  <footer><a class="js-open-modal btn_green" href="#">Submit</a></footer>
</div>
			  </div>	
			  </div>
			  </div> </div><!-- /.tab-content -->
              </div>
            </div>
			  <!-- END FORM -->
            
            <!-- END MAIN PANEL CONTENT --> 
          </div>
          <!-- END MAIN PANEL --> 
        </div>
        <!-- HERE COMES YOUR CONTENT --> 
      </div>
      <!-- END MAIN ROW -->
      <div class="clear"></div>
    </div>
    <!-- END PAGE CONTENT -->   
  </div>
  </div>
  <!-- END MAIN CONTENT --> 
   
	 <!-- END FOOTER --> 

<script>
$(function(){

var appendthis =  ("<div class='modal-overlay js-modal-close'></div>");

	$('a[data-modal-id]').click(function(e) {
		e.preventDefault();
    $("body").append(appendthis);
    $(".modal-overlay").fadeTo(500, 0.7);
    //$(".js-modalbox").fadeIn(500);
		var modalBox = $(this).attr('data-modal-id');
		$('#'+modalBox).fadeIn($(this).data());
	});  
  
  
$(".js-modal-close, .modal-overlay").click(function() {
    $(".modal-box, .modal-overlay").fadeOut(500, function() {
        $(".modal-overlay").remove();
    });
 
});
 
$(window).resize(function() {
    $(".modal-box").css({
        top: ($(window).height() - $(".modal-box").outerHeight()) / 2,
        left: ($(window).width() - $(".modal-box").outerWidth()) / 2
    });
});
 
$(window).resize();
 
});
</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

<script type="text/javascript">
$('#sendBack').click(function(){
	$('#sendBack').hide();
	$('#sendBackTextAreaId').show();
	$('#sendBackButtonId').show();
	$('#sendBackLableId').show();
	
});

$('#reject').click(function(){
	$('#reject').hide();
	$('#sendBackTextAreaId').show();
	$('#rejectButtonId').show();
	$('#rejectLableId').show();
	
});
</script>
<script type="text/javascript">
$(function(){
   
    $('#action').on('change', function() {
 			 $('#comment').hide();
 			 $("#sendBack").attr("disabled","disabled");
 			 $("#reject").attr("disabled","disabled");
 			 $("#approve").removeAttr("disabled");
 	 });
    $('#action1').on('change', function() {
		 $('#comment').show();
		 $("#reject").attr("disabled","disabled");
		 $("#approve").attr("disabled","disabled");
		 $("#sendBack").removeAttr("disabled");
 });
    $('#action2').on('change', function() {
		 $('#comment').show();
		 $("#sendBack").attr("disabled","disabled");
		 $("#approve").attr("disabled","disabled");
		 $("#reject").removeAttr("disabled");
});
});
</script>
</body>
</html>