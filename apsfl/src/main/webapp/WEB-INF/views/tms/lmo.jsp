<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- BEGIN MAIN CONTENT -->
<div class="main-content"
	style="display: none; margin-left: 150px; width: 1024px">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong> Tenant</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Tenant Service</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div>
							<center>
								<font face="Calibri" size="4px" color="Red">${error}</font>
							</center>
							<center>
								<font face="Calibri" size="4px" color="Green">${message}</font>
							</center>
						</div>
						<%-- <form role="form" class="form-validation"> --%>
						<form name="tenantfrm" action="<c:url value="/createTenant"/>"
							method="post" id="lom_form" enctype="multipart/form-data">

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
														<label class="control-label">Tenant Type <span style="color: red;">*</span></label>
														<div class="option-group">
															<!-- <a title="Please select Tenant Type LOV" class="tooltip1"> -->
															<select name="tenantTypeLov" id="tenantTypeLov"
																class="form-control form-white">
																<option value="">Select Tenant Type</option>
																<c:forEach var="tenatType" items="${tenantTypes}">
																	<c:choose>
																		<c:when
																			test="${tenantObj.tenantTypeLov eq tenatType.lovValue}">
																			<option value="${tenatType.lovValue}" selected>${tenatType.lovValue}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${tenatType.lovValue}">${tenatType.lovValue}</option>
																		</c:otherwise>
																	</c:choose>

																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Region<span
															style="color: red;">*</span></label>
														<div class="option-group">
															<!-- <input type="text" id="region" name="region" class="form-control form-white" placeholder="Enter Region" required> -->
															<!-- <a title="Please Select Region" class="tooltip1"> -->
															<c:if test="${tenantObj.tenantId != null}"><input type="hidden" name="region" value="${tenantLicenseVO.region.regionName}"></c:if>
															<select id="region" name="region" <c:if test="${tenantObj.tenantId != null}">disabled</c:if>
																class="form-control form-white" >
																<option value="">Select Region</option>
																<c:forEach var="region" items="${regionList}">
																	<c:choose>
																		<c:when
																			test="${not empty tenantLicenseVO && not empty tenantLicenseVO.region && region.regionName==tenantLicenseVO.region.regionName}">
																			<option value="${region.regionName}" selected>${region.regionName}</option>
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
														<label class="control-label">Tenant Code<span
															style="color: red;">*</span></label> <a
															title="Please Enter Tenant Code" class="tooltip1"><input
															type="text" name="tenantCode" id="tenantCode"
															class="form-control form-white specialCharacters" <c:if test="${tenantObj.tenantId != null}">readonly</c:if>
															placeholder="Enter Tenant Code " maxlength="20"
															pattern="[a-zA-Z0-9]+" value="${tenantObj.tenantCode}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Enrollment Number<span
															style="color: red;">*</span></label> <a
															title="Please Enter Enrollment Number" class="tooltip1"><input
															type="text" name="portalEnrllmentno"
															id="portalEnrllmentno" class="form-control form-white specialCharacters"
															placeholder="Enter Enrollment Number" maxlength="100"
															value="${tenantObj.portalEnrllmentno}" pattern="[0-9]{12}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Name<span style="color: red;">*</span></label> <input type="hidden"
															name="tenantId" id="tenantId" value="${tenantObj.tenantId}" />
															<a title="Please Enter Tenant Name" class="tooltip1" data-placement="top"><input type="text" id="name"
															name="name" value="${tenantObj.name}" class="form-control form-white nonumbers" placeholder="Enter Name"
															maxlength="100" pattern="[a-zA-Z][a-zA-Z\s]*"></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Aadhar</label> <a
															title="Please Enter Aadhar Card Number" class="tooltip1"><input
															type="text" name="aadharCardNo" 
															id="aadharCardNo" class="form-control form-white specialCharacters"
															placeholder="Enter Aadhar"
															value="${tenantObj.aadharCardNo}" maxlength="12"
															pattern="[0-9]{12}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">PAN</label> <a
															title="Please Enter PAN Number" class="tooltip1"><input
															type="text" name="panNo" id="panNo"
															class="form-control form-white specialCharacters" placeholder="Enter PAN"
															maxlength="10" value="${tenantObj.panNo}"
															pattern="[A-Z]{5}\d{4}[A-Z]{1}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">TAN</label> <a
															title="Please Enter TAN Number" class="tooltip1"><input
															type="text" name="tanNo" id="tanNo"
															class="form-control form-white" placeholder="Enter TAN"
															value="${tenantObj.tanNo}" maxlength="50"
															pattern="[a-zA-Z0-9]+" /></a>
													</div>
												</div>
											</div>
											<div class="row">
											<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">TIN</label> <a
															title="Please Enter TIN Number" class="tooltip1"><input
															type="text" name="tinNo" id="tinNo"
															class="form-control form-white" placeholder="Enter TIN"
															value="${tenantObj.tinNo}" maxlength="50" pattern="\d+" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">GST</label> <a
															title="Please Enter GST Number" class="tooltip1"><input
															type="text" name="gstNo" id="gstNo"
															class="form-control form-white" placeholder="Enter GST"
															value="${tenantObj.gstNo}" maxlength="50"
															pattern="[a-zA-Z0-9]+" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">VAT</label> <a
															title="Please Enter VAT Number" class="tooltip1"><input
															type="text" name="vatNo" id="vatNo"
															class="form-control form-white" placeholder="Enter VAT"
															value="${tenantObj.vatNo}" maxlength="50"
															pattern="[a-zA-Z0-9]+" /></a>
													</div>
												</div>
											</div>
											<div class="row">
											<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Postal Registration
															No</label> <a title="Please Enter Postal Registration No"
															class="tooltip1"><input type="text"
															name="portalPostalRegno" id="portalPostalRegno"
															class="form-control form-white number"
															placeholder="Enter Postal Registration No" maxlength="50"
															value="${tenantObj.portalPostalRegno}" pattern="[0-9]{12}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Postal Expired Date</label> <a
															title="Please Enter Postal Expired Date" class="tooltip1"><input
															type="text" name="portalPostExpDate"
															id="portalPostExpDate" class="form-control form-white"
															placeholder="Enter Postal Expired Date"
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantObj.portalPostExpDate}"/>' /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Credit Limit</label> <a
															title="Please Enter Credit Limit" class="tooltip1"> <input
															type="text" name="creditLimit" id="creditLimitId"
															class="form-control form-white"
															placeholder="Enter Credit Limit" maxlength="7"
															pattern="[0-9]+" /></a>
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
											<h3>Registered Office Details</h3>
										</div>
										<div class="panel-content">
										<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Point of Contact Name</label> <a
															title="Please Enter POC Name" class="tooltip1"><input
															type="text" name="pocName" id="pocName"
															class="form-control form-white"
															placeholder="Enter Point of Contact Name"
															value="${tenantObj.pocName}" maxlength="40"
															pattern="[a-zA-Z][a-zA-Z\s]*" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Mobile Number</label> <a
															title="Please Enter POC Mobile 1" class="tooltip1"><input
															type="text" name="pocMobileNo1" id="pocMobileNo1"
															class="form-control form-white number"
															placeholder="Enter Point of Contact Mobile Number"
															value="${tenantObj.pocMobileNo1}" maxlength="10"
															pattern="[789][0-9]{9}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Email Id <span
															style="color: red;">*</span></label> <a
															title="Please Enter EmailId" class="tooltip1"><input
															type="text" name="emailId1" id="emailId1"
															class="form-control form-white"
															placeholder="Enter Email Id" value="${tenantObj.emailId1}"
															maxlength="100" required /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Address Line 1</label> <a
															title="Please Enter Address 1" class="tooltip1"><textarea
																name="address1" id="address1"
																class="form-control form-white"
																placeholder="Enter Address Line 1" maxlength="225">${tenantObj.address1}</textarea></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Address Line 2</label> <a
															title="Please Enter Address 2" class="tooltip1"><textarea
																name="text" name="address2" id="address2"
																class="form-control form-white" maxlength="225"
																placeholder="Enter Address Line 2">${tenantObj.address2}</textarea></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Locality</label> <a
															title="Please Enter Locality" class="tooltip1"><input
															type="text" name="locality" id="locality"
															class="form-control form-white"
															placeholder="Enter Locality" value="${tenantObj.locality}"
															maxlength="100" pattern="[a-zA-Z]*" /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Area</label> <a
															title="Please Enter Area" class="tooltip1"><input
															type="text" name="area" id="area"
															class="form-control form-white" placeholder="Enter Area"
															value="${tenantObj.area}" maxlength="100"
															pattern="[a-zA-Z]*" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">City</label> <a
															title="Please Enter City" class="tooltip1"><input
															type="text" name="city" id="city"
															class="form-control form-white" placeholder="Enter City"
															value="${tenantObj.city}" maxlength="50"
															pattern="[a-zA-Z]*" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">State</label> <a
															title="Please Enter State" class="tooltip1"><input
															type="text" name="stateName" id="stateName"
															class="form-control form-white" placeholder="Enter State"
															value="${tenantObj.stateName}" maxlength="50"
															pattern="[a-zA-Z][a-zA-Z\s]*" /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Pin Code</label> <a
															title="Please Enter PIN Code" class="tooltip1"><input
															type="text" name="pincode" id="pincode"
															class="form-control form-white number"
															placeholder="Enter PIN Code" value="${tenantObj.pincode}"
															maxlength="6" pattern="[0-9]{6}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Landline No</label>
															<div class="clear"></div>
															<div class="col-sm-4 p-l-0">
																<div class="form-group">
																	<input type="text" name="stdcode" id="stdcode" class="form-control form-white number" maxlength="5" placeholder="Enter STD Code"/>
																</div>
															</div>
															<div class="col-sm-8 p-l-0">
																<div class="form-group">
																	<input type="text" name="landline1" id="landline1" class="form-control form-white number" maxlength="10" placeholder="Enter Land Line No"/>
																</div>
															</div>
															<div class="clear"></div>
														</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Fax</label> <a
															title="Please Enter Fax 1" class="tooltip1"><input
															type="text" name="fax1" id="fax1"
															class="form-control form-white" placeholder="Enter Fax"
															value="${tenantObj.fax1}" maxlength="10" pattern="\d+" /></a>
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
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Local Office Details</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Point of Contact Name</label> <a
															title="Please Enter Local Office POC Name"
															class="tooltip1"><input type="text"
															name="localOfficePocName" id="localOfficePocName"
															class="form-control form-white"
															placeholder="Enter Point of Contact Name"
															value="${tenantObj.localOfficePocName}" maxlength="50"
															pattern="[a-zA-Z][a-zA-Z\s]*" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Mobile Number</label> <a
															title="Please Enter Local Office POC Mobile 1"
															class="tooltip1"><input type="text"
															name="localOfficePocMobileNo1"
															id="localOfficePocMobileNo1"
															class="form-control form-white number"
															placeholder="Enter Point of Contact Mobile Number"
															value="${tenantObj.localOfficePocMobileNo1}"
															maxlength="10" pattern="[789][0-9]{9}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Email Id</label> <a
															title="Please Enter Local Office EmailId 1"
															class="tooltip1"><input type="text"
															name="localOfficeEmailId1" id="localOfficeEmailId1"
															class="form-control form-white"
															placeholder="Enter Email Id"
															value="${tenantObj.localOfficeEmailId1}" maxlength="100" /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Address Line 1</label> <a
															title="Please Enter Local Office Address 1"
															class="tooltip1"><textarea name="localOfficeAddress1"
																id="localOfficeAddress1" class="form-control form-white"
																placeholder="Enter Address Line 1" maxlength="225">${tenantObj.localOfficeAddress1}</textarea></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Address Line 2</label> <a
															title="Please Enter Local Office Address 2"
															class="tooltip1"><textarea name="localOfficeAddress2"
																id="localOfficeAddress2" class="form-control form-white"
																placeholder="Enter Address Line 2" maxlength="225">${tenantObj.localOfficeAddress2}</textarea></a>
														<!-- <input type="text" name="vat" class="form-control form-white" placeholder="Enter Address Line 2" required> -->
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Locality</label> <a
															title="Please Enter Local Office Locality"
															class="tooltip1"><input type="text"
															name="localOfficeLocality" id="localOfficeLocality"
															class="form-control form-white"
															placeholder="Enter Locality"
															value="${tenantObj.localOfficeLocality}" maxlength="150"
															pattern="[a-zA-Z][a-zA-Z\s]*" /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Area</label> <a
															title="Please Enter Local Office Area" class="tooltip1"><input
															type="text" name="localOfficeArea" id="localOfficeArea"
															class="form-control form-white" placeholder="Enter Area"
															value="${tenantObj.localOfficeArea}" maxlength="100"
															pattern="[a-zA-Z][a-zA-Z\s]*" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">City</label> <a
															title="Please Enter Local Office City" class="tooltip1"><input
															type="text" name="localOfficeCity" id="localOfficeCity"
															class="form-control form-white" placeholder="Enter City"
															value="${tenantObj.localOfficeCity}" maxlength="20"
															pattern="[a-zA-Z]*" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">State</label> <a
															title="Please Enter Local Office State" class="tooltip1"><input
															type="text" name="localOfficeStateName"
															id="localOfficeStateName" class="form-control form-white"
															placeholder="Enter State"
															value="${tenantObj.localOfficeStateName}" maxlength="25"
															pattern="[a-zA-Z][a-zA-Z\s]*" /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Pin Code</label> <a
															title="Please Enter Local Office PIN Code"
															class="tooltip1"><input type="text"
															name="localOfficePincode" id="localOfficePincode"
															class="form-control form-white"
															placeholder="Enter PIN Code"
															value="${tenantObj.localOfficePincode}" maxlength="6"
															pattern="\d+" /></a>
													</div>
												</div>
												<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Landline No</label>
															<div class="clear"></div>
															<div class="col-sm-4 p-l-0">
																<div class="form-group">
																	<input type="text" name="localOfficestdcode" id="localOfficestdcode" class="form-control form-white number" maxlength="5" placeholder="Enter STD Code"/>
																</div>
															</div>
															<div class="col-sm-8 p-l-0">
																<div class="form-group">
																	<input type="text" name="localOfficeLandline1" id="localOfficeLandline1" class="form-control form-white number" maxlength="10" placeholder="Enter Land Line No"/>
																</div>
															</div>
															<div class="clear"></div>
														</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Fax</label> <a
															title="Please Enter Local Office EmailId 2"
															class="tooltip1"><input type="text"
															name="localOfficeEmailId2" id="localOfficeEmailId2"
															class="form-control form-white"
															placeholder="Enter Email Fax"
															value="${tenantObj.localOfficeEmailId2}" maxlength="10"
															pattern="\d+" /></a>
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
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Serving Areas <img style="width:150; height:150" class="imageplus" src="./resources/images/Add_Areas.png" align="right"/> </h3>
										</div>
										<div class="panel-content">
											<div class="row">
											
											<c:choose>
												<c:when test="${portalareasVO.size()<=0}">
													<label> No Records Found </label>
												</c:when>
												<c:otherwise>
												
												<table class="table  table-alt">
													<thead>
														<tr>
															<th>S.No</th>
															<th>Area Name</th>
															<th>District</th>
															<th>Mandal</th>
															<th>Village</th>
														</tr>
													</thead>
													<tbody>
													<c:set var="portalareasVOListSize" value="${fn:length(portalareasVO)}" />
													
														<!-- row1-->
														<c:forEach var="areasVO" varStatus="rowNum"
															items="${portalareasVO}">
															<tr onClick="enableOrDisSelect(${rowNum.count})">
																<td>${rowNum.count}</td>
																<td>${areasVO.areaname}</td>
																<td>${areasVO.districtid}</td>
																<td>${areasVO.mandalid}</td>
																<td>${areasVO.villageid}</td>
															</tr>
															<tr id="intr${rowNum.count}"  class="rowdata" style="display: none">
																<td colspan="5">
																	
																	
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Areas</h3>
											</div>
											<div class="panel-content">
												<div class="row">
												<%-- <div class="col-sm-4">
						                       <div class="form-group">
                                               <!-- <label class="control-label">Area Id</label> -->
                                               <input type="hidden" name="pareas[${rowNum.index}].areaid" id="areaid" readonly value="${areasVO.areaid}"/>
                      							</div>
												</div> --%>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Area Name</label>
															<input type="hidden" name="pareas[${rowNum.index}].areaid" id="areaid" readonly value="${areasVO.areaid}"/>
															 <a title="Please Enter Area Name" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].areaname" id="areaname"
																class="form-control form-white" maxlength="75"
																value="${areasVO.areaname}"
																placeholder="Enter Area Name" /></a>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="form-label">Cable Type Id</label> <a
																title="Please Enter Cabletype Id" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].areas_cabletypeid"
																id="areas_cabletypeid" class="form-control form-white"
																placeholder="Enter Cabletype Id"
																value="${areasVO.cabletypeid}" maxlength="30" /></a>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Running Cable Length</label> <a
																title="Please Enter Running Cablelen" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].runningcablelen" id="runningcablelen"
																class="form-control form-white"
																placeholder="Enter Running Cablelen"
																value="${areasVO.runningcablelen}" maxlength="16" /></a>
														</div>
													</div>
												   </div>
												  <div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">State Id</label> <a
																title="Please Enter State Id" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].stateid" id="stateid"
																class="form-control form-white number"
																placeholder="Enter State Id" value="${areasVO.stateid}" /></a>
														</div>
													</div>

													<div class="col-md-4">
														<div class="form-group">
															<label class="form-label">District Id</label> <a
																title="Please Enter District Id" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].districtid" id="districtid"
																class="form-control form-white number"
																placeholder="Enter District Id"
																value="${areasVO.districtid}" /></a>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Mandal Id</label> <a
																title="Please Enter Mandal Id" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].mandalid" id="mandalid"
																class="form-control form-white number"
																placeholder="Enter Mandal Id"
																value="${areasVO.mandalid}" /></a>
														</div>
													</div>
												    </div>
												<div class="row">
												    <div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Village Id</label> <a
																title="Please Enter Village Id" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].villageid" id="villageid"
																class="form-control form-white number"
																placeholder="Enter Village Id"
																value="${areasVO.villageid}" /></a>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="form-label">Subscription Count</label> <a
																title="Please Enter Subscription Cnt" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].subscription_cnt"
																id="subscription_cnt" class="form-control form-white number"
																placeholder="Enter Subscription Cnt"
																value="${areasVO.subscription_cnt}" /></a>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Connection Count</label> <a
																title="Please Enter Connection Cnt" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].conn_cnt" id="conn_cnt"
																class="form-control form-white number"
																placeholder="Enter Connection Cnt"
																value="${areasVO.conn_cnt}" /></a>
														</div>
													</div>
												   </div>
												  <div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Digital Connection Count</label> <a
																title="Please Enter Digconn Cnt" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].digconn_cnt" id="digconn_cnt"
																class="form-control form-white number"
																placeholder="Enter Digconn Cnt"
																value="${areasVO.digconn_cnt}" /></a>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Analog Connection Count</label> <a
																title="Please Enter Anlconn Cnt" class="tooltip1"><input
																type="text" name="pareas[${rowNum.index}].anlconn_cnt" id="anlconn_cnt"
																class="form-control form-white number"
																placeholder="Enter Anlconn Cnt"
																value="${areasVO.anlconn_cnt}" /></a>
														</div>
													</div>
												</div>
												<!-- END ROW INNER-->
												<!-- END ROW INNER-->
											</div>
										</div>
									</div>
								</div>
							
																</td>
																
															</tr>
														</c:forEach>
													</tbody>
												</table>
												
												</c:otherwise>
												</c:choose>
												<div id="displayareas">
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
											<h3>Serving Assets<img style="width:150; height:150" class="imageassets" src="./resources/images/Add_Assets.png" align="right"/> </h3>
										</div>
										<div class="panel-content">
											<div class="row">
											<c:choose>
												<c:when test="${portalassetsVO.size()<=0}">
													<label> No Records Found </label>
												</c:when>
												<c:otherwise>
												 <table class="table  table-alt">
													<thead>
														<tr>
															<th>S.No</th>
															<th>Route Name</th>
															<th>Route Map</th>
															<th>IMIE Number</th>
															<th>Version Number</th>
														</tr>
													</thead>
													<tbody>
													<c:set var="portalassetsVOListSize" value="${fn:length(portalassetsVO)}" />
														<!-- row1-->
														<c:forEach var="assetsVO" varStatus="rowNum" items="${portalassetsVO}">
													      <tr onClick="enableOrDisSelect1(${rowNum.count})">
											         			<td>${rowNum.count}</td>
																<td>${assetsVO.routename}</td>
																<td>${assetsVO.routemap}</td>
																<td>${assetsVO.imieno}</td>
																<td>${assetsVO.versionno}</td>
															</tr>
															<tr id="intrr${rowNum.count}"  class="rowdatar" style="display: none">
																<td colspan="5">
																								
																	<!--  START OF DIVDATA -->
																

								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Assets</h3>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Cable Type Id<span
																style="color: red;">*</span></label>
																<input type="hidden" name="passets[${rowNum.index}].routemapid" id="routemapid" value="${assetsVO.routemapid}"/> 
																<a title="Please Enter Cable Type Id" class="tooltip1"><input
																type="text" name="passets[${rowNum.index}].cabletypeid"
																id="cabletypeid" class="form-control form-white number"
																value="${assetsVO.cabletypeid}"
																placeholder="Enter Cable Type Id" /></a>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="form-label">Asset Type Id<span
																style="color: red;">*</span></label> <a
																title="Please Enter Asset Type Id" class="tooltip1"><input
																type="text" name="passets[${rowNum.index}].assettypeid" id="assettypeid"
																class="form-control form-white number"
																placeholder="Enter Asset Type Id"
																value="${assetsVO.assettypeid}" /></a>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Route Name<span
																style="color: red;">*</span></label> <a
																title="Please Enter Route Name" class="tooltip1"><input
																type="text" name="passets[${rowNum.index}].routename" id="routename"
																class="form-control form-white"
																placeholder="Enter Route Name"
																value="${assetsVO.routename}" maxlength="255" /></a>
														</div>
													</div>
													</div>
													<div class="row">													
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Route Map<span
																style="color: red;">*</span></label> <a
																title="Please Enter Route Map" class="tooltip1"><input
																type="text" name="passets[${rowNum.index}].routemap" id="routemap"
																class="form-control form-white"
																placeholder="Enter Route Map"
																value="${assetsVO.routemap}" /></a>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="form-label">Captured Assets<span
																style="color: red;">*</span></label> <a
																title="Please Enter Captured Assets" class="tooltip1"><input
																type="text" name="passets[${rowNum.index}].capturedassets" id="capturedassets"
																class="form-control form-white"
																placeholder="Enter Captured Assets"
																value="${assetsVO.capturedassets}" /></a>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Sent Transtime<span
																style="color: red;">*</span></label> <a
																title="Please Enter Sent Transtime" class="tooltip1"><input
																type="text" name="passets[${rowNum.index}].senttranstime" id="senttranstime"
																class="form-control form-white"
																placeholder="Enter Sent Transtime"
																value="${assetsVO.senttranstime}" /></a>
														</div>
													</div>
												    </div>
												   <div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">ImieNumber<span
																style="color: red;">*</span></label> <a
																title="Please Enter ImieNumber" class="tooltip1"><input
																type="text" name="passets[${rowNum.index}].imieno" id="imieno"
																class="form-control form-white number"
																placeholder="Enter ImieNumber"
																value="${assetsVO.imieno}" maxlength="255" pattern="[0-9]{255}" /></a>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="form-label">Version Number<span
																style="color: red;">*</span></label> <a
																title="Please Enter Version Number" class="tooltip1"><input
																type="text" name="passets[${rowNum.index}].versionno" id="versionno"
																class="form-control form-white number"
																placeholder="Enter Version Number"
																value="${assetsVO.versionno}" pattern="[0-9]" /></a>
														</div>
													</div>
													</div>
												<!-- END ROW INNER-->
												<!-- END ROW INNER-->

											</div>
										</div>
									</div>
								</div>

							
																	<!--  END OF DIVDATA -->
															</td>	
																
															</tr>
															
														</c:forEach>
													</tbody>
												</table>
												</c:otherwise>
												</c:choose>
								               <div id="displayassets" >
									
												</div> 
											</div>

											<!-- END ROW INNER-->
											<!-- END ROW INNER-->

										</div>
									</div>
								</div>
							</div>
							<div class="row">
									<!-- <div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Associated RMSPs, MSPs</h3>
											</div>
										</div>
									</div>
								</div> -->
								<div class="col-sm-6">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>RMSPs with associated years</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
													<table id="taxTableID" class="table  table-alt">
							                            <thead>
							                              <tr>
							                              	<th>SNo</th>
							                                <th>Regional MSP</th>
							                                <th>Associated Years</th>
															<!-- <th>Action</th> -->
							                              </tr>
							                            </thead>
							                            <tbody>
							                            <tr>
							                            	<td>1</td>
							                            	<td><input type="text" name="portalRgnmsp1" class="localTextCss" value="${tenantObj.portalRgnmsp1}"/></td>
							                                <td><input type="text" name="portalRegMsp1AssocYrs" class="localTextCss number" value="${tenantObj.portalRegMsp1AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>2</td>
							                            	<td><input type="text" name="portalRgnMsp2" class="localTextCss" value="${tenantObj.portalRgnMsp2}"/></td>
							                                <td><input type="text" name="portalRegMsp2AssocYrs" class="localTextCss number" value="${tenantObj.portalRegMsp2AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>3</td>
							                            	<td><input type="text" name="portalRgnMsp3" class="localTextCss" value="${tenantObj.portalRgnMsp3}"/></td>
							                                <td><input type="text" name="portalRegMsp3AssocYrs" class="localTextCss number" value="${tenantObj.portalRegMsp3AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>4</td>
							                            	<td><input type="text" name="portalRgnMsp4" class="localTextCss" value="${tenantObj.portalRgnMsp4}"/></td>
							                                <td><input type="text" name="portalRegMsp4AssocYrs" class="localTextCss number" value="${tenantObj.portalRegMsp4AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>5</td>
							                            	<td><input type="text" name="portalRgnMsp5" class="localTextCss" value="${tenantObj.portalRgnMsp5}"/></td>
							                                <td><input type="text" name="portalRegMsp5Assocyrs" class="localTextCss number" value="${tenantObj.portalRegMsp5Assocyrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>6</td>
							                            	<td><input type="text" name="portalRgnMsp6" class="localTextCss" value="${tenantObj.portalRgnMsp6}"/></td>
							                                <td><input type="text" name="portalRegMsp6Assocyrs" class="localTextCss number" value="${tenantObj.portalRegMsp6Assocyrs}"/></td>
							                            </tr>
							                            </tbody>
							                         </table>
							                         </div>
							                     </div>
											</div>
											<!-- END ROW INNER-->
										</div>
								</div>
								</div>
								<div class="col-sm-6">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>MSPs with associated years</h3>
										</div>
										<div class="panel-content">
										<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
													<table id="taxTableID" class="table  table-alt">
							                            <thead>
							                              <tr>
							                              	<th>SNo</th>
							                                <th>Local MSP</th>
							                                <th>Associated Years</th>
															<!-- <th>Action</th> -->
							                              </tr>
							                            </thead>
							                            <tbody>
							                            <tr>
							                            	<td>1</td>
							                            	<td><input type="text" name="portalLocmsp1" class="localTextCss" value="${tenantObj.portalLocmsp1}"/></td>
							                                <td><input type="text" name="portalLocMsp1AssocYrs" class="localTextCss number" value="${tenantObj.portalLocMsp1AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>2</td>
							                            	<td><input type="text" name="portalLocMsp2" class="localTextCss" value="${tenantObj.portalLocMsp2}"/></td>
							                                <td><input type="text" name="portalLocMsp2AssocYrs" class="localTextCss number" value="${tenantObj.portalLocMsp2AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>3</td>
							                            	<td><input type="text" name="portalLocMsp3" class="localTextCss" value="${tenantObj.portalLocMsp3}"/></td>
							                                <td><input type="text" name="portalLocMsp3AssocYrs" class="localTextCss number" value="${tenantObj.portalLocMsp3AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>4</td>
							                            	<td><input type="text" name="portalLocMsp4" class="localTextCss" value="${tenantObj.portalLocMsp4}"/></td>
							                                <td><input type="text" name="portalLocMsp4AssocYrs" class="localTextCss number" value="${tenantObj.portalLocMsp4AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>5</td>
							                            	<td><input type="text" name="portalLocMsp5" class="localTextCss" value="${tenantObj.portalLocMsp5}"/></td>
							                                <td><input type="text" name="portalLocMsp5AssocYrs" class="localTextCss number" value="${tenantObj.portalLocMsp5AssocYrs}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>6</td>
							                            	<td><input type="text" name="portalLocMsp6" class="localTextCss" value="${tenantObj.portalLocMsp6}"/></td>
							                                <td><input type="text" name="portalLocMsp6AssocYrs" class="localTextCss number" value="${tenantObj.portalLocMsp6AssocYrs}"/></td>
							                            </tr>
							                            </tbody>
							                         </table>
							                         </div>
							                     </div>
											</div>
											<!-- END ROW INNER-->
										</div>
									</div>
								</div>
								<div class="clear"></div>

							</div>
							<!-- END ROW -->

							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Associated Sub-Stations</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
													<table id="taxTableID" class="table  table-alt">
							                            <thead>
							                              <tr>
							                              	<th>SNo</th>
							                                <th>Sub Station Id</th>
							                                <th>Sub Station Distance</th>
															<!-- <th>Action</th> -->
							                              </tr>
							                            </thead>
							                            <tbody>
							                            <tr>
							                            	<td>1</td>
							                            	<td><input type="text" name="portalSubstn1Id" class="localTextCss" value="${tenantObj.portalSubstn1Id}"/></td>
							                                <td><input type="text" name="portalSubstn1Distance" class="localTextCss number" value="${tenantObj.portalSubstn1Distance}"/></td>
							                            </tr>
							                            <tr>
							                            	<td>2</td>
							                            	<td><input type="text" name="portalSubstn2Id" class="localTextCss" value="${tenantObj.portalSubstn2Id}"/></td>
							                                <td><input type="text" name="portalSubstn2Distance" class="localTextCss number" value="${tenantObj.portalSubstn2Distance}"/></td>
							                            </tr>
							                            </tbody>
							                         </table>
													</div>
												</div>
												<!-- END ROW INNER-->
												<!-- END ROW INNER-->

											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>MSP specific fields</h3>
										</div>
										<div class="panel-content">
										<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Mso Name</label> <a
															title="Please Enter Portal Mso Name" class="tooltip1"><input
															type="text" name="portalMsoName" id="portalMsoName"
															class="form-control form-white"
															placeholder="Enter Portal Mso Name" maxlength="255"
															value="${tenantObj.portalMsoName}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">DasLicence Provider</label> <a
															title="Please Enter Portal DasLicence Provider"
															class="tooltip1"><input type="text"
															name="portalDasLicenceProvider"
															id="portalDasLicenceProvider"
															class="form-control form-white"
															placeholder="Enter Portal DasLicence Provider"
															maxlength="255"
															value="${tenantObj.portalDasLicenceProvider}" /></a>
													</div>
												</div>
												<!-- END ROW INNER-->
												<!-- END ROW INNER-->

											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Digital Connections Count</label> <a
															title="Please Enter Portal DigitalConn Cnt"
															class="tooltip1"><input type="text"
															name="portalDgtConnCnt" id="portalDgtConnCnt"
															class="form-control form-white number"
															placeholder="Enter Portal DigitalConn Cnt"
															value="${tenantObj.portalDgtConnCnt}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Analog Connections Count</label> <a
															title="Please Enter Portal AnalogConn Cnt"
															class="tooltip1"><input type="text"
															name="portalAnlConnCnt" id="portalAnlConnCnt"
															class="form-control form-white number"
															value="${tenantObj.portalAnlConnCnt}"
															placeholder="Enter Portal AnalogConn Cnt" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Das License</label> <a
															title="Please Enter Portal Das License" class="tooltip1"><input
															type="text" name="portalDasLicense" id="portalDasLicense"
															class="form-control form-white"
															placeholder="Enter Portal Das License"
															value="${tenantObj.portalDasLicense}" maxlength="255" /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Das Licence Type</label> <a
															title="Please Enter Portal Das Licence Type"
															class="tooltip1"><input type="text"
															name="portalDasLicenceType" id="portalDasLicenceType"
															class="form-control form-white"
															placeholder="Enter Portal Das Licence Type"
															value="${tenantObj.portalDasLicenceType}" maxlength="255" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Das License Holder</label> <a
															title="Please Portal Das License Holder" class="tooltip1"><input
															type="text" name="portalDasLicenseHolder"
															id="portalDasLicenseHolder"
															class="form-control form-white"
															placeholder="Enter Portal Das License Holder"
															value="${tenantObj.portalDasLicenseHolder}"
															maxlength="255" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="control-label">Das License ExpDate</label> <a
															title="Please Enter Portal Das License ExpDate"
															class="tooltip1"><input type="text"
															name="portalDasLicenseExpDate"
															id="portalDasLicenseExpDate"
															class="form-control form-white"
															placeholder="select Portal Das License ExpDate"
															value='<f:formatDate pattern="MM/dd/yyyy"  value="${tenantObj.portalDasLicenseExpDate}"/>' /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">No Of Pay Channels</label> <a
															title="Please Enter Portal Paychnl Cnt" class="tooltip1"><input
															type="text" name="portalPaychnlCnt" id="portalPaychnlCnt"
															class="form-control form-white number"
															placeholder="Enter Portal Paychnl Cnt"
															value="${tenantObj.portalPaychnlCnt}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Type Of Company</label> <a
															title="Please Enter Portal Company Type" class="tooltip1"><input
															type="text" name="portalCompanyType"
															id="portalCompanyType" class="form-control form-white"
															placeholder="Enter Portal Company Type"
															value="${tenantObj.portalCompanyType}" maxlength="255" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Partner Name</label> <a
															title="Please Enter Portal Partner Name" class="tooltip1"><input
															type="text" name="portalPartnerName"
															id="portalPartnerName" class="form-control form-white"
															placeholder="Enter Portal Partner Name"
															value="${tenantObj.portalPartnerName}" /></a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">No Of House Holds</label> <a
															title="Please Enter Portal House Hold Cnt"
															class="tooltip1"><input type="text"
															name="portalHouseHoldCnt" id="portalHouseHoldCnt"
															class="form-control form-white number"
															placeholder="Enter Portal House Hold Cnt"
															value="${tenantObj.portalHouseHoldCnt}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">MIB License Number</label> <a
															title="Please Enter Portal Mib License No"
															class="tooltip1"><input type="text"
															name="portalMibLicenseNo" id="portalMibLicenseNo"
															class="form-control form-white"
															placeholder="Enter Portal Mib License No"
															value="${tenantObj.fax1}" maxlength="255" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="control-label">MIB License Expiry Date</label> <a
															title="Please Enter Portal Mib License ExpDate"
															class="tooltip1"><input type="text"
															name="portalMibLicenseExpDate"
															id="portalMibLicenseExpDate"
															class="form-control form-white"
															placeholder="select Portal Mib License ExpDate"
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantObj.portalMibLicenseExpDate}"/>' /></a>
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
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>License Details</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">License Number<span
															style="color: red;">*</span></label> <a
															title="Please Enter License Number" class="tooltip1"><input
															type="text" name="licenserefno" id="licenserefno" <c:if test="${tenantObj.tenantId != null}">readonly</c:if>
															maxlength="20" class="form-control form-white specialCharacters"
															placeholder="Enter License Number"
															value="${tenantDocumentsVO.docuniqueId}"
															pattern="[a-zA-Z0-9]+" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Document<span
															style="color: red;">*</span></label>
														<div class="option-group">
															<!-- <a title="Please Select Document Lov" class="tooltip1"> -->
															<c:if test="${tenantObj.tenantId != null}"><input type="hidden" name="doclov" value="${tenantDocumentsVO.docmentLov}"></c:if>
															<select id="doclov" name="doclov" <c:if test="${tenantObj.tenantId != null}">disabled</c:if>
																class="form-control form-white">
																<option value="">Select Document</option>
																<c:forEach var="lovs" items="${generalDocList}">
																	<c:choose>
																		<c:when
																			test="${not empty tenantDocumentsVO && lovs.lovId == tenantDocumentsVO.docmentLov}">
																			<option value="${lovs.lovId},${lovs.lovName}"
																				selected>${lovs.lovValue}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${lovs.lovId},${lovs.lovName}">${lovs.lovValue}</option>
																		</c:otherwise>
																	</c:choose>

																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<c:if test="${empty tenantDocumentsVO}">
															<label class="form-label">Upload License<span
																style="color: red;">*</span></label>
															<input type="file" name="licenceId" id="licenceId"
																class="form-control form-white" required />
														</c:if>
														<c:if test="${not empty tenantDocumentsVO}">
															<label class="form-label">Upload License</label>
															<input type="file" name="licenceId" id="licenceId"
																class="form-control form-white" />
															<span class="galleryImgs"> <a href="#"><img
																	width="30"
																	src="downloadFiles?path=${tenantDocumentsVO.documentLocationReference}" /></a>
															</span>
														</c:if>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Valid From<span
															style="color: red;">*</span></label> <a
															title="Please Select Effective From Date"
															class="tooltip1"><input type="text"
															name="effectiveFrom" id="effectiveFrom" <c:if test="${tenantObj.tenantId != null}">readonly</c:if>
															class=" form-control form-white" 
															placeholder="Select a date..."
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO.effectiveFrom}" />' /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Valid To<span
															style="color: red;">*</span></label> <a
															title="Please Select Effective To Date" class="tooltip1"><input
															type="text" name="effectiveTO" id="effectiveTO"
															class=" form-control form-white"
															placeholder="Select a date..."
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO.effectiveTo}" />' /></a>
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
														<label class="control-label">ID Proof Number<span
															style="color: red;">*</span></label> <a
															title="Please Enter ID Proof Number" class="tooltip1"><input
															type="text" name="docUniqueId1" id="docUniqueId1"
															maxlength="20" class="form-control form-white specialCharacters"
															placeholder="Enter ID Proof Number"
															value="${tenantDocumentsVO1.docuniqueId}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Document <span
															style="color: red;">*</span></label>
														<div class="option-group">
															<!-- <a title="Please Select Document Lov" class="tooltip1"> -->
															<c:if test="${tenantObj.tenantId != null}"><input type="hidden" name="doclov1" value="${tenantDocumentsVO1.docmentLov}"></c:if>
															<select id="doclov1" name="doclov1" <c:if test="${tenantObj.tenantId != null}">disabled</c:if>
																class="form-control form-white">
																<option value="">Select Document</option>
																<c:forEach var="lovs" items="${poiDocList}">
																	<c:choose>
																		<c:when
																			test="${not empty tenantDocumentsVO1 && lovs.lovId == tenantDocumentsVO1.docmentLov}">
																			<option value="${lovs.lovId},${lovs.lovName}"
																				selected>${lovs.lovValue}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${lovs.lovId},${lovs.lovName}">${lovs.lovValue}</option>
																		</c:otherwise>
																	</c:choose>

																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<c:if test="${empty tenantDocumentsVO1}">
															<label class="form-label">Upload ID Proof<span
																style="color: red;">*</span></label>
															<input type="file" name="idProof" id="idProof"
																class="form-control form-white" required />
														</c:if>
														<c:if test="${not empty tenantDocumentsVO1}">
															<span class="galleryImgs"> <label
																class="form-label">Upload ID Proof</label> <input
																type="file" name="idProof" id="idProof"
																class="form-control form-white" /> <a href="#"><img
																	width="30"
																	src="downloadFiles?path=${tenantDocumentsVO1.documentLocationReference}" /></a></span>
														</c:if>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Valid From<span
															style="color: red;">*</span></label> <a
															title="Please Select Effective From Date"
															class="tooltip1"><input type="text"
															name="effectiveFrom1" id="effectiveFrom1" <c:if test="${tenantObj.tenantId != null}">readonly</c:if>
															class="form-control form-white"
															placeholder="Select a date..."
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' /></a>
														<%-- <a title="Please Select Effective From Date" class="tooltip1"><input type="date" class="datepicker" class="datepicker" name="effectiveFrom1" id="effectiveFrom1" class="date-picker form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO1}">  </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' /></a> --%>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Valid To<span
															style="color: red;">*</span></label> <a
															title="Please Select Effective To Date" class="tooltip1"><input
															type="text" name="effectiveTO1" id="effectiveTO1"
															value="01/01/9999" class=" form-control form-white"
															placeholder="Select a date..." readonly /></a>
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
														<label class="control-label">Address Proof Number<span
															style="color: red;">*</span></label> <a
															title="Please Enter Address Proof Number"
															class="tooltip1"><input type="text"
															name="docUniqueId2" maxlength="20" id="docUniqueId2"
															class="form-control form-white specialCharacters"
															placeholder="Enter Address Proof Number"
															value="${tenantDocumentsVO2.docuniqueId}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Document <span
															style="color: red;">*</span></label>
														<div class="option-group">
															<!-- <a title="Please Select Document Lov" class="tooltip1"> -->
															<c:if test="${tenantObj.tenantId != null}"><input type="hidden" name="doclov2" value="${tenantDocumentsVO2.docmentLov}"></c:if>
															<select id="doclov2" name="doclov2" <c:if test="${tenantObj.tenantId != null}">disabled</c:if>
																class="form-control form-white">
																<option value="">Select Document</option>
																<c:forEach var="lovs" items="${poaDocList}">
																	<c:choose>
																		<c:when
																			test="${not empty tenantDocumentsVO2 && lovs.lovId == tenantDocumentsVO2.docmentLov}">
																			<option value="${lovs.lovId},${lovs.lovName}"
																				selected>${lovs.lovValue}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${lovs.lovId},${lovs.lovName}">${lovs.lovValue}</option>
																		</c:otherwise>
																	</c:choose>

																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<c:if test="${empty tenantDocumentsVO2}">
															<label class="form-label">Upload Address Proof<span
																style="color: red;">*</span></label>
															<input type="file" name="addressProof" id="addressProof"
																class="form-control form-white" required />
														</c:if>
														<c:if test="${not empty tenantDocumentsVO2}">
															<span class="galleryImgs"> <label
																class="form-label">Upload Address Proof</label> <input
																type="file" name="addressProof" id="addressProof"
																class="form-control form-white" /> <a href="#"><img
																	width="30"
																	src="downloadFiles?path=${tenantDocumentsVO2.documentLocationReference}" /></a></span>
														</c:if>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Valid From<span
															style="color: red;">*</span></label> <a
															title="Please Select Effective From Date"
															class="tooltip1"><input type="text"
															name="effectiveFrom2" id="effectiveFrom2" <c:if test="${tenantObj.tenantId != null}">readonly</c:if>
															class="form-control form-white"
															placeholder="Select a date..."
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO2.effectiveFrom}" />' /></a>
														<%-- <a title="Please Select Effective From Date" class="tooltip1"><input type="date" class="datepicker" name="effectiveFrom2" id="effectiveFrom2" class="date-picker form-control form-white" placeholder="Select a date..." <c:if test="${not empty tenantDocumentsVO1}">  </c:if> value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantDocumentsVO1.effectiveFrom}" />' /></a> --%>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Valid To<span
															style="color: red;">*</span></label> <a
															title="Please Select Effective To Date" class="tooltip1"><input
															type="text" name="effectiveTO2" id=effectiveTO2
															class="form-control form-white"
															placeholder="Select a date..." value="01/01/9999"
															readonly /></a>
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
														<label class="control-label">A/C Number<span
															style="color: red;">*</span></label> <a
															title="Please Enter Bank Account Number" class="tooltip1"><input
															type="text" name="accountNo" id="accountNo" <c:if test="${tenantObj.tenantId != null}">readonly</c:if>
															class="form-control form-white number"
															placeholder="Enter Account Number" maxlength="15"
															pattern="\d+" value="${tenantBankVO.accountNo}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">IFSC Code<span
															style="color: red;">*</span></label> <a
															title="Please Enter IFSC Code" class="tooltip1"><input
															type="text" name="ifscCode" id="ifscCode"
															class="form-control form-white specialCharacters"
															placeholder="Enter IFSC Code" maxlength="11"
															pattern="[A-Z]{4}[0][\d]{6}$"
															value="${tenantBankVO.ifscCode}" /></a>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Account Type<span
															style="color: red;">*</span></label> <a
															title="Please Enter Type of Account" class="tooltip1"><input
															type="text" name="acctTypelov" id="acctTypelov"
															class="form-control form-white lettersOnly" maxlength="30"
															placeholder="Enter Account Type"
															value="${tenantBankVO.acctTypelov}" /></a>
													</div>
												</div>

											</div>
											<div class="row">

												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Branch Name<span
															style="color: red;">*</span></label> <a
															title="Please Enetr Branch Name" class="tooltip1"><input
															type="text" name="branchName" id="branchName"
															class="form-control form-white lettersOnly"
															placeholder="Enter Branch Name"
															value="${tenantBankVO.branchName}" maxlength="30" /></a>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Bank Name<span
															style="color: red;">*</span></label> <a
															title="Please Enter Bank Name" class="tooltip1"><input
															type="text" name="bankNamelov" id="bankNamelov"
															class="form-control form-white lettersOnly"
															placeholder="Enter Bank Name"
															value="${tenantBankVO.bankNamelov}" maxlength="50" /></a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<button class="btn btn-embossed btn-success" type="submit"
								value="Save">Submit</button>
							<button class="btn btn-embossed btn-danger" type="button"
								value="Back">Cancel</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>

    
	function enableOrDisSelect(v){	
	$(".rowdata").not("#intr"+v).hide();
	$("#intr"+v).toggle();	

	}
	function enableOrDisSelect1(k){	
		$(".rowdatar").not("#intrr"+k).hide();
		$("#intrr"+k).toggle();	

		}

    $(document).ready(function() {

    	               var	list = "${portalareasVOListSize}";
    	   	          $(".imageplus").click(function(){
                       var html = "";
                                              
                       html = html + '<div class="row">' 
                       + '<div class="col-sm-12">'
                       + '<div class="panel">'
                       + '<div class="panel-header bg-light">'
                       + '<h3>Areas</h3>'
                       + '</div>'
                       + '<div class="panel-content">'
                       + '<div class="row">'
                      /*  + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<input type="hidden" name="pareas['+list+'].areaid" id="areaid" /> '
                       + '</div>'
                       + '</div>' */
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Area Name</label>'  
                       + '<input type="hidden" name="pareas['+list+'].areaid" id="areaid" /> '
                       + '<a title="Please Enter Area Name" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].areaname" '
                       + 'id="areaname" class="form-control form-white" '
                       + 'placeholder="Enter Area Name"'
                       + 'maxlength="75" /></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="form-label">Cable Type Id</label>'  
                       + '<a title="Please Enter Cabletype Id" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].areas_cabletypeid" '
                       + 'id="areas_cabletypeid" class="form-control form-white" '
                       + 'placeholder="Enter Cabletype Id"'
                       + 'maxlength="30" /></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-md-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Running Cablelen</label>'  
                       + '<a title="Please Enter Running Cablelen" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].runningcablelen" '
                       + 'id="runningcablelen" class="form-control form-white numbersOnly" '
                       + 'placeholder="Enter Running Cablelen" '
                       + 'maxlength="16" /></a>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '<div class="row">'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">State Id</label>'  
                       + '<a title="Please Enter State Id" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].stateid" '
                       + 'id="stateid" class="form-control form-white number" '
                       + 'placeholder="Enter State Id"/></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="form-label">District Id</label>'  
                       + '<a title="Please Enter District Id" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].districtid" '
                       + 'id="districtid" class="form-control form-white number" '
                       + 'placeholder="Enter District Id"/></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-md-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Mandal Id</label>'  
                       + '<a title="Please Enter Mandal Id" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].mandalid" '
                       + 'id="mandalid" class="form-control form-white number" '
                       + 'placeholder="Enter Mandal Id"/></a>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '<div class="row">'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Village Id</label>'  
                       + '<a title="Please Enter Village Id" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].villageid" '
                       + 'id="villageid" class="form-control form-white number" '
                       + 'placeholder="Enter Village Id"/></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="form-label">Subscription Count</label>'  
                       + '<a title="Please Enter Subscription Cnt" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].subscription_cnt"'
                       + 'id="subscription_cnt" class="form-control form-white number"'
                       + 'placeholder="Enter Subscription Cnt"/></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-md-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Connection Count</label>'  
                       + '<a title="Please Enter Connection Cnt" class="tooltip1">'
                       + '<input type="text"  name="pareas['+list+'].conn_cnt" '
                       + 'id="conn_cnt" class="form-control form-white number" '
                       + 'placeholder="Enter Connection Cnt"/></a>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '<div class="row">'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Digital Connection Cnt</label>'  
                       + '<a title="Please Enter Digconn Cnt" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].digconn_cnt" '
                       + 'id="digconn_cnt" class="form-control form-white number" '
                       + 'placeholder="Enter Digconn Cnt"/></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-md-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Analog Connection Count</label>'  
                       + '<a title="Please Enter Anlconn Cnt" class="tooltip1">'
                       + '<input type="text" name="pareas['+list+'].anlconn_cnt"'
                       + 'id="anlconn_cnt" class="form-control form-white number"'
                       + 'placeholder="Enter Anlconn Cnt"/></a>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '</div>';
                       $("#displayareas").append(html);
                       list++
                     });

    	   	       var  list1 = "${portalassetsVOListSize}";
                   $(".imageassets").click(function(){
                       var html = "";
                                              
                       html = html + '<div class="row">' 
                       + '<div class="col-sm-12">'
                       + '<div class="panel">'
                       + '<div class="panel-header bg-light">'
                       + '<h3>Assets</h3>'
                       + '</div>'
                       + '<div class="panel-content">'
                       + '<div class="row">'
                       /* + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<input type="hidden" name="passets['+list1+'].routemapid" id="routemapid"/> '
                       + '</div>'
                       + '</div>' */
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Cable Type Id<span style="color: red;">*</span></label>'
                       + '<input type="hidden" name="passets['+list1+'].routemapid" id="routemapid"/> '  
                       + '<a title="Please Enter Cable Type Id" class="tooltip1">'
                       + '<input type="text" name="passets['+list1+'].cabletypeid" '
                       + 'id="cabletypeid" class="form-control form-white number"'
                       + 'placeholder="Enter Cable Type Id" required /></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="form-label">Asset Type Id<span style="color: red;">*</span></label>'  
                       + '<a title="Please Enter Asset Type Id" class="tooltip1">'
                       + '<input type="text" name="passets['+list1+'].assettypeid"'
                       + 'id="assettypeid" class="form-control form-white number" '
                       + 'placeholder="Enter Asset Type Id" required /></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-md-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Route Name<span style="color: red;">*</span></label>'  
                       + '<a title="Please Enter Route Name" class="tooltip1">'
                       + '<input type="text" name="passets['+list1+'].routename" '
                       + 'id="routename" class="form-control form-white"'
                       + 'placeholder="Enter Route Name" maxlength="255" required /></a>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '<div class="row">'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Route Map<span style="color: red;">*</span></label>'  
                       + '<a title="Please Enter Route Map" class="tooltip1">'
                       + '<input type="text" name="passets['+list1+'].routemap"'
                       + 'id="routemap" class="form-control form-white" '
                       + 'placeholder="Enter Route Map" required /></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="form-label">Captured Assets<span style="color: red;">*</span></label>'
                       + '<a title="Please Enter Captured Assets" class="tooltip1">'
                       + '<input type="text" name="passets['+list1+'].capturedassets"'
                       + 'id="capturedassets" class="form-control form-white" '
                       + 'placeholder="Enter Captured Assets" required /></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-md-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">Sent Transtime<span style="color: red;">*</span></label>'  
                       + '<a title="Please Enter Sent Transtime" class="tooltip1">'
                       + '<input type="text" name="passets['+list1+'].senttranstime"'
                       + 'id="senttranstime" class="form-control form-white" '
                       + 'placeholder="Enter Sent Transtime" required /></a>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '<div class="row">'
                       + '<div class="col-sm-4"> '
                       + '<div class="form-group">'
                       + '<label class="control-label">ImieNumber<span style="color: red;">*</span></label>'  
                       + '<a title="Please Enter ImieNumber" class="tooltip1">'
                       + '<input type="text" name="passets['+list1+'].imieno"'
                       + 'id="imieno" class="form-control form-white number" '
                       + 'placeholder="Enter ImieNumber" maxlength="255" pattern="[0-9]{255}" required /></a>'
                       + '</div>'
                       + '</div>'
                       + '<div class="col-md-4"> '
                       + '<div class="form-group">'
                       + '<label class="form-label">Version Number<span style="color: red;">*</span></label>'  
                       + '<a title="Please Enter Version Number" class="tooltip1">'
                       + '<input type="text" name="passets['+list1+'].versionno"'
                       + 'id="versionno" class="form-control form-white number" '
                       + 'placeholder="Enter Version Number" pattern="[0-9]"required /></a>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '</div>'
                       + '</div>';
                       

                       $("#displayassets").append(html);
                                          
                       list1++;

                          });

        $("#licenceId, #addressProof, #doclov2").change(function(){
            var max_file_size = 10;
            var min_file_size = 0;
            var f_bytes=this.files[0].size;
            var name=this.files[0].name;


            var minname=2;
                    var firstindex=name.substr(0, name.lastIndexOf('.'));


                    /* if(name.split(".").length>2 || name.split("%").length>1 || name.split("0").length>1 || firstindex.length<minname)
                    
            {
                alert("Numbers & Spaces are not Allowed in File Name");
                $("#jpgtpdx").val("");
                return false;
            } */
            
            {
                var ext=name.substring(name.lastIndexOf(".")+1,name.length);
                if(ext != 'jpg' && ext != 'JPG' && ext != 'JPEG' && ext != 'JPEG' && ext != 'png' && ext != 'PNG' && ext != 'pdf' && ext != 'PDF' )

                {
                    alert('You selected a " .'+name.substring(name.lastIndexOf(".")+1,name.length)+' "  file; Please Select a " .jpg/.jpeg/.png/.pdf/ " file');
                    $("#licenceId, #addressProof, #doclov2").val("");
                    return false;
                }
                else
                {
                          var type=this.files[0].type;
                          var f_kBytes = f_bytes/1024;
                          var f_kBytesMin = f_bytes/1024;
                          var f_mbytes = f_kBytes/1024;

                          if(f_mbytes > max_file_size)
                          {
                                alert("The size of the file your trying to upload exceeds the limit");
                                $("#licenceId, #addressProof, #doclov2").val("");
                          }
                          if(f_kBytesMin < min_file_size)
                          {

                                alert("The size of the file your trying to upload did not meet the minimum limit");
                                $("#licenceId, #addressProof, #doclov2").val("");
                          }

                    return true;
                }
            }
            
        });




    });

</script>
<style>
.localTextCss {
  border:none;
  width:100%;
  height:100%;
  padding: 0 4px 0 4px;
}
</style>