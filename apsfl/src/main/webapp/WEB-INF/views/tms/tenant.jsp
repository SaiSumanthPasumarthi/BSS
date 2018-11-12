<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 

<script type="text/javascript">
function showRecord(id) {
	window.location.href = "<c:url value="/showtenantDetails"/>?tenantId=" + id;
}
</script>

<!-- BEGIN MAIN CONTENT -->
  <div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Tenant<strong> Information</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./">Home</a> </li>
            <li class="active">Tenant Information</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form name="tenantfrm" commandName="tenantVO" class="form-validation" action="<c:url value="/cretaeTenant"/>" method="post" id="lom_form" enctype="multipart/form-data">

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
                      <input type="text" id="name" name="name" value="${tenantVO.name}" class="form-control form-white"  maxlength="100" pattern="[a-zA-Z][a-zA-Z\s]*" disabled></a>
                    </div>
                  </div>
                 <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Tenant Type <span style="color: red;">*</span></label>
						<div class="option-group">
						<input type="text" id="name" name="name" value="${tenantVO.tenantTypeLov}" class="form-control form-white" disabled>
                       <%--  <select name="tenantTypeLov" id="tenantTypeLov" class="form-control form-white" disabled>
                                <option value="">Select Tenant Type LOV</option>
								<option <c:choose><c:when test="${tenantVO.tenantTypeLov =='LMO'}"> value="LMO" selected </c:when>    
    									<c:otherwise>value="LMO"</c:otherwise></c:choose>>LMO</option>
								<option <c:choose><c:when test="${tenantVO.tenantTypeLov =='MSP'}"> value="MSP" selected </c:when>    
    									<c:otherwise>value="MSP"</c:otherwise></c:choose>>MSP</option>
    							<option <c:choose><c:when test="${tenantVO.tenantTypeLov =='APF'}"> value="APF" selected </c:when>    
    									<c:otherwise>value="APF"</c:otherwise></c:choose>>APF</option>
                        </select> --%>
                     </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                            <label class="form-label">Region<span style="color: red;">*</span></label>
                            <div class="option-group">
                            <!-- <input type="text" id="region" name="region" class="form-control form-white" placeholder="Enter Region" required> -->
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
                      <input type="text" name="tenantCode" id="tenantCode" class="form-control form-white"  maxlength="30" pattern="[a-zA-Z0-9]+"<c:if test="${not empty tenantVO}"> readonly </c:if> value="${tenantVO.tenantCode}" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">PAN</label>
                            <input type="text" name="panNo" id="panNo" class="form-control form-white"  value="${tenantVO.panNo}" maxlength="10" pattern="[A-Z]{5}\d{4}[A-Z]{1}" disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Aadhar</label>
                            <input type="text" name="aadharCardNo" id="aadharCardNo" class="form-control form-white"  value="${tenantVO.aadharCardNo}" maxlength="12" pattern="[0-9]{12}" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">TAN</label>
                      <input type="text" name="tanNo" id="tanNo" class="form-control form-white"  value="${tenantVO.tanNo}" maxlength="20" pattern="[a-zA-Z0-9]+" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">TIN</label>
                            <input type="text" name="tinNo" id="tinNo" class="form-control form-white"  value="${tenantVO.tinNo}" maxlength="20" pattern="\d+" disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">GST</label>
                            <input type="text" name="gstNo" id="gstNo" class="form-control form-white"  value="${tenantVO.gstNo}" maxlength="20" pattern="[a-zA-Z0-9]+" disabled/></a>
                          </div>
                        </div>					  
					  </div>
				<div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">VAT</label>
                     <input type="text" name="vatNo" id="vatNo" class="form-control form-white"  value="${tenantVO.vatNo}" maxlength="20" pattern="[a-zA-Z0-9]+" disabled/></a>
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
                      <textarea name="address1" id="address1" class="form-control form-white"  maxlength="225" disabled>${tenantVO.address1}</textarea></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Address Line 2</label>
						<textarea type="text" name="address2" id="address2" class="form-control form-white"  value="${tenantVO.address2}"  disabled></textarea></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Locality</label>
                           <input type="text" name="locality" id="locality" class="form-control form-white"  value="${tenantVO.locality}" maxlength="100" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Area</label>
                      <input type="text" name="area" id="area" class="form-control form-white"  value="${tenantVO.area}" maxlength="100" pattern="[a-zA-Z]*" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">City</label>
                            <a title="Please Enter City" class="tooltip1"><input type="text" name="city" id="city" class="form-control form-white" value="${tenantVO.city}" maxlength="20" pattern="[a-zA-Z]*" disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">State</label>
                            <input type="text" name="stateName" id="stateName" class="form-control form-white"  value="${tenantVO.stateName}" maxlength="20" pattern="[a-zA-Z][a-zA-Z\s]*" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Pin Code</label>
                      <input type="text" name="pincode" id="pincode" class="form-control form-white"  value="${tenantVO.pincode}" maxlength="6" pattern="[0-9]{6}" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">STD Code</label>
                            <input type="text" name="stdcode" id="stdcode" class="form-control form-white"  value="${tenantVO.stdcode}"  maxlength="5" pattern="\d+" disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Land Line</label>
                            <input type="text" name="landline1" id="landline1" class="form-control form-white"  value="${tenantVO.landline1}"  maxlength="10" pattern="\d+" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Email Id</label>
							<input type="text" name="emailId1" id="emailId1" class="form-control form-white"  value="${tenantVO.emailId1}" maxlength="100" disabled/></a>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Fax</label>
							<input type="text" name="fax1" id="fax1" class="form-control form-white" value="${tenantVO.fax1}" maxlength="10" pattern="\d+" disabled/></a>
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
                            <input type="text" name="pocName" id="pocName" class="form-control form-white"  value="${tenantVO.pocName}" maxlength="100" pattern="[a-zA-Z][a-zA-Z\s]*" disabled/></a>
                          </div>
                        </div>
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Mobile Number</label>
                            <input type="text" name="pocMobileNo1" id="pocMobileNo1" class="form-control form-white"  value="${tenantVO.pocMobileNo1}" maxlength="10" pattern="[789][0-9]{9}" disabled/></a>
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
                     <textarea name="localOfficeAddress1" id="localOfficeAddress1" class="form-control form-white"  maxlength="225" disabled>${tenantVO.localOfficeAddress1}</textarea></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Address Line 2</label>
                      <textarea name="localOfficeAddress2" id="localOfficeAddress2" class="form-control form-white" maxlength="225" disabled>${tenantVO.localOfficeAddress2}</textarea></a>
						<!-- <input type="text" name="vat" class="form-control form-white" placeholder="Enter Address Line 2" required> -->
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Locality</label>
                            <input type="text" name="localOfficeLocality" id="localOfficeLocality" class="form-control form-white"  value="${tenantVO.localOfficeLocality}" maxlength="150" pattern="[a-zA-Z][a-zA-Z\s]*" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Area</label>
                      <input type="text" name="localOfficeArea" id="localOfficeArea" class="form-control form-white"  value="${tenantVO.localOfficeArea}" maxlength="100" pattern="[a-zA-Z][a-zA-Z\s]*" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">City</label>
                            <input type="text" name="localOfficeCity" id="localOfficeCity" class="form-control form-white"  value="${tenantVO.localOfficeCity}" maxlength="20" pattern="[a-zA-Z]*" disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">State</label>
                            <input type="text" name="localOfficeStateName" id="localOfficeStateName" class="form-control form-white"  value="${tenantVO.localOfficeStateName}" maxlength="25" pattern="[a-zA-Z][a-zA-Z\s]*" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Pin Code</label>
                      <input type="text" name="localOfficePincode" id="localOfficePincode" class="form-control form-white"  value="${tenantVO.localOfficePincode}" maxlength="6" pattern="\d+" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">STD Code</label>
                            <input type="text" name="localOfficestdcode" id="localOfficestdcode" class="form-control form-white"  value="${tenantVO.pocMobileNo2}" maxlength="5" pattern="\d+" disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Land Line</label>
                            <input type="text" name="localOfficeLandline1" id="localOfficeLandline1" class="form-control form-white"  value="${tenantVO.localOfficeLandline1}" maxlength="10" pattern="\d+" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Email Id</label>
							<input type="text" name="localOfficeEmailId1" id="localOfficeEmailId1" class="form-control form-white"  value="${tenantVO.localOfficeEmailId1}" maxlength="100" disabled/>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Fax</label>
							<input type="text" name="localOfficeEmailId2" id="localOfficeEmailId2" class="form-control form-white"  value="${tenantVO.localOfficeEmailId2}" maxlength="10" pattern="\d+" disabled/>
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
                            <input type="text" name="localOfficePocName" id="localOfficePocName" class="form-control form-white"  value="${tenantVO.localOfficePocName}" maxlength="100" pattern="[a-zA-Z][a-zA-Z\s]*" disabled/></a>
                          </div>
                        </div>
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Mobile Number</label>
                            <input type="text" name="localOfficePocMobileNo1" id="localOfficePocMobileNo1" class="form-control form-white" value="${tenantVO.localOfficePocMobileNo1}" maxlength="10" pattern="[789][0-9]{9}" disabled/></a>
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
                      <input type="text" name="licenserefno" id="licenserefno" class="form-control form-white"  <c:if test="${not empty tenantLicenseVO}"> readonly </c:if> value="${tenantLicenseVO.licenserefno}" pattern="[a-zA-Z0-9]+"/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Document <span style="color: red;">*</span></label>
						<div class="option-group">
						<c:if test="${not empty tenantDocumentsVO}"><input type="hidden" id="doclov" name="doclov" value="${tenantDocumentsVO.docmentLov}" /></c:if>
                        <select id="doclov" name="doclov" class="form-control form-white" <c:if test="${not empty tenantDocumentsVO}">  </c:if> disabled>
						<option value="">Select Document </option>
						<c:forEach var="lovs" items="${generalDocList}">
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
                           <%--<input type="file" name="licenceId" id="licenceId" class="form-control form-white"  /><c:if test="${not empty tenantDocumentsVO}"><span class="galleryImgs">
                            <a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO.documentLocationReference}" /></a></span></c:if></a> --%>
                            <span class="galleryImgs"><a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO.documentLocationReference}" /></a></span>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Valid From<span style="color: red;">*</span></label>
                      <input type="text" name="effectiveFrom" id="effectiveFrom" class="date-custom form-control form-white"  <c:if test="${not empty tenantDocumentsVO}"> readonly </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO.effectiveFrom}" />' disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Valid To<span style="color: red;">*</span></label>
                      <input type="text" name="effectiveTO" id="effectiveTO" class="date-custom form-control form-white" value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO.effectiveTo}" />' disabled/></a>
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
                      <input type="text" name="docUniqueId1" id="docUniqueId1" disabled class="form-control form-white"  value="${tenantDocumentsVO1.docuniqueId}" /></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Document <span style="color: red;">*</span></label>
						    <div class="option-group">
						    
						    <c:if test="${not empty tenantDocumentsVO1}"><input type="hidden" id="doclov1" name="doclov1" class="form-control form-white" value="${tenantDocumentsVO1.docmentLov}" /></c:if>
                        <input type="hidden" id="doclov1" name="doclov1" value="${tenantDocumentsVO1.docmentLov}" />
                        <select id="doclov1" name="doclov1" class="form-control form-white" <c:if test="${not empty tenantDocumentsVO1}">disabled</c:if> disabled>
						<option value=""></option>
						<c:forEach var="lovs" items="${poiDocList}">
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
                            <%-- <input type="file" name="idProof" id="idProof" class="form-control form-white"  disabled><c:if test="${not empty tenantDocumentsVO1}"> <span class="galleryImgs">
			   	    	<a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO1.documentLocationReference}" /></a></span> </c:if> --%>
			   	    	<span class="galleryImgs"><a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO1.documentLocationReference}" /></a></span>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Valid From<span style="color: red;">*</span></label>
                      <input type="text" name="effectiveFrom1" id="effectiveFrom1" class="date-custom form-control form-white" <c:if test="${not empty tenantDocumentsVO1}"> readonly </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' disabled/></a>
                      <%-- <a title="Please Select Effective From Date" class="tooltip1"><input type="date" class="datepicker" class="datepicker" name="effectiveFrom1" id="effectiveFrom1" class="date-custom form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO1}">  </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' /></a> --%>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Valid To<span style="color: red;">*</span></label>
                      <input type="text" name="effectiveTO1" id="effectiveTO1" class="date-custom form-control form-white"  value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveTo}" />' disabled/></a>
                        <%-- <a title="Please Select Effective To Date" class="tooltip1"><input type="date" class="datepicker" name="effectiveTO1" id="effectiveTO1" class="date-custom form-control form-white" placeholder="Select a date..." value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveTo}" />' /></a> --%>
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
                      <input type="text" name="docUniqueId2" id="docUniqueId2" class="form-control form-white"  value="${tenantDocumentsVO2.docuniqueId}" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Document <span style="color: red;">*</span></label>
						    <div class="option-group">
                              <c:if test="${not empty tenantDocumentsVO2}"><input type="hidden" id="doclov2" name="doclov2" class="form-control form-white" value="${tenantDocumentsVO2.docmentLov}" /></c:if>
                        <select id="doclov2" name="doclov2" class="form-control form-white" <c:if test="${not empty tenantDocumentsVO2}">disabled</c:if> disabled>
						<option value="">Select Document </option>
						<c:forEach var="lovs" items="${poaDocList}">
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
                            <%-- <input type="file" name="addressProof" id="addressProof" class="form-control form-white"  /><c:if test="${not empty tenantDocumentsVO2}"> <span class="galleryImgs">
			   	    	<a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO2.documentLocationReference}" /></a></span> </c:if> --%>
			   	    	<span class="galleryImgs"><a href="#"><img width="30" src="downloadFiles?path=${tenantDocumentsVO2.documentLocationReference}" /></a></span>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Valid From<span style="color: red;">*</span></label>
                      <input type="text" name="effectiveFrom2" id="effectiveFrom2" class="date-custom form-control form-white" <c:if test="${not empty tenantDocumentsVO2}"> readonly </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO2.effectiveFrom}" />' disabled/></a>
                      <%-- <input type="date" class="datepicker" name="effectiveFrom2" id="effectiveFrom2" class="date-picker form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO1}">  </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' /></a> --%>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Valid To<span style="color: red;">*</span></label>
                     <input type="text" name="effectiveTO2" id="effectiveTO2" class="date-custom form-control form-white"  value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO2.effectiveTo}" />' disabled/></a>
                        <%-- <input type="date" class="datepicker" name="effectiveTO2" id="effectiveTO2" class="date-picker form-control form-white" value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveTo}" />' /></a> --%>
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
                      <h3>Serving Areas</h3>
                    </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Enrollment Number</label>
						<input type="text" name="enrollmentno" id="enrollmentno" class="form-control form-white" maxlength="100" value="${tenantVO.enrollmentno}"  disabled></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Area Name</label>
                           <input type="text" name="areaname" id="areaname" class="form-control form-white"  value="${tenantVO.areaname}" maxlength="75" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Cabletype Id</label>
                      <input type="text" name="cabletypeid" id="cabletypeid" class="form-control form-white"  value="${tenantVO.cabletypeid}" maxlength="30" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Running Cablelen</label>
                      <input type="text" name="runningcablelen" id="runningcablelen" class="form-control form-white" value="${tenantVO.runningcablelen}" maxlength="16" disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">State Id</label>
                            <input type="text" name="stateid" id="stateid" class="form-control form-white"  value="${tenantVO.stateid}" disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">District Id</label>
                      <input type="text" name="districtid" id="districtid" class="form-control form-white"  value="${tenantVO.districtid}" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Mandal Id</label>
                            <input type="text" name="mandalid" id="mandalid" class="form-control form-white"  value="${tenantVO.mandalid}"  disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                          <div class="form-group">
                            <label class="form-label">Village Id</label>
                            <input type="text" name="villageid" id="landline1" class="form-control form-white"  value="${tenantVO.villageid}"  disabled/></a>
                          </div>
                        </div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Subscription_cnt</label>
							<input type="text" name="subscription_cnt" id="subscription_cnt" class="form-control form-white"  value="${tenantVO.subscription_cnt}" disabled/></a>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Conn_cnt</label>
							<input type="text" name="conn_cnt" id="conn_cnt" class="form-control form-white" value="${tenantVO.conn_cnt}" disabled/></a>
							</div>
						</div>					  
					  </div>
					  <div class="row">
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Digconn_cnt</label>
							<input type="text" name="digconn_cnt" id="digconn_cnt" class="form-control form-white"  value="${tenantVO.digconn_cnt}" disabled/></a>
							</div>
						</div>
						<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
							<label class="control-label">Anlconn_cnt</label>
							<input type="text" name="anlconn_cnt" id="anlconn_cnt" class="form-control form-white"  value="${tenantVO.anlconn_cnt}" disabled/></a>
							</div>
						</div>
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
                      <h3>Bank Details</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
						<div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">A/C Number<span style="color: red;">*</span></label>
                      <input type="text" name="accountNo" id="accountNo" class="form-control form-white" disabled <c:if test="${not empty tenantLicenseVO}"> readonly </c:if> value="${tenantBankVO.accountNo}" /></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">IFSC Code<span style="color: red;">*</span></label>
					  <input type="text" name="ifscCode" id="ifscCode" class="form-control form-white" value="${tenantBankVO.ifscCode}" disabled/></a>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Account Type<span style="color: red;">*</span></label>
                       <input type="text" name="acctTypelov" id="acctTypelov" class="form-control form-white"  value="${tenantBankVO.acctTypelov}" disabled/></a>
                    </div>
                  </div>
                  					  
				</div>
					  <div class="row">
				
                  <div class="col-sm-4">
						<div class="form-group">
                      <label class="control-label">Branch Name<span style="color: red;">*</span></label>
                      <input type="text" name="branchName" id="branchName" class="form-control form-white"  value="${tenantBankVO.branchName}" maxlength="30" disabled/></a>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
						<label class="form-label">Bank Name<span style="color: red;">*</span></label>
                       <input type="text" name="bankNamelov" id="bankNamelov" class="form-control form-white"  value="${tenantBankVO.bankNamelov}" maxlength="50" disabled/></a>
                    </div>
                  </div>				  
					  </div>                      
                    </div>
                  </div>
                </div>
              </div>
              </form>
			 </div>
			</div>
		</div>
	</div>
  </div>
</div>

<!-- <script>
$(document).ready(function(){
   $('.galleryImgs').viewer();
});
</script> -->
