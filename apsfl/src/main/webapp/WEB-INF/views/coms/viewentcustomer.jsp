<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong> Enterprise Customer</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Enterprise Customer</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form role="form" class="form-validation" name="entcustomerform" id="entcustomerform" action="./saveEntCustomer" method="post">
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<label class="control-label">Name</label> 
										<input type="text" name="name" value="${name}" class="form-control form-white" required readonly>
									</div>
								</div>
							</div>
							<div class="clear"></div>
							<!-- END ROW -->
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Organization Information</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Type of Organization<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="custType" id="custType" class="form-control form-white" disabled>
																<option value="${enterpriseCustomer.custType}">${enterpriseCustomer.custType}</option>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Sub-Type of Organization<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="officeTypeLov" id="officeTypeLov" class="form-control form-white" disabled>
																<option value="">--select--</option>
																<option></option>
																<c:if test="${not empty enterpriseCustomer}">
																	<option value="${enterpriseCustomer.officeTypeLov}" selected>${enterpriseCustomer.officeTypeLov}</option>
																</c:if>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="form-label" id="entLable">Organization Number<span style="color: red;">*</span></label> 
														<input type="text" name="uidNo" id="uidNo" value="${enterpriseCustomer.custCode}" class="form-control form-white number" placeholder="Enter Value" maxlength="12" required disabled>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Title<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="titleLovName" id="titleLovName" class="form-control form-white" disabled>
																<option value="572">Ms.</option>
															</select>
														</div>
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Organization Name<span style="color: red;">*</span></label> 
														<input type="text" name="orgName" id="orgName" value="${enterpriseCustomer.custName}" class="form-control form-white specialCharacters" maxlength="100" placeholder="Enter Organization Name" disabled>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Date of Incorporation</label> 
														<label id="dobError" style="text-align: center; color: red;"></label> 
														<input type="text" name="dateOfIncorporation" id="dateOfIncorporation" <c:if test = "${not empty enterpriseCustomer.dateofinc}">value='<fmt:formatDate pattern="MM/dd/yyyy" value="${enterpriseCustomer.dateofinc}"/>' </c:if> class="date-picker form-control form-white" readonly placeholder="Select date">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Email<span style="color: red;">*</span></label> 
														<input type="text" name="emailId" id="emailId" value="${enterpriseCustomer.email1}" class="form-control form-white" maxlength="100" placeholder="Enter Email" disabled>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Name<span style="color: red;">*</span></label> 
														<input type="text" name="pocName" id="pocName" value="${enterpriseCustomer.pocName}" class="form-control form-white specialCharacters" maxlength="150" placeholder="Contact Person Name" disabled>
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Mobile No<span style="color: red;">*</span></label> 
														<input type="text" name="pocmobile" id="pocmobile" value="${enterpriseCustomer.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No" disabled>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Bill Cycle<span
															style="color: red;">*</span></label>
														<div class="option-group">
															<select name="billfreqLov" id="billfreqLov" class="form-control form-white" disabled>
																<option value="${enterpriseCustomer.billfreqLov}">${enterpriseCustomer.billfreqLov}</option>
															</select>
														</div>
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="col-sm-6">
											<div class="panel">
												<div class="panel-header bg-light">
													<h3>Organization Location Address</h3>
													<label id="addError" style="text-align: center; color: red;"></label>
												</div>
												<div class="panel-content">
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Address Line1<span style="color: red;">*</span></label> 
																<input type="text" name="address1" id="address1" value="${enterpriseCustomer.address1}" class="form-control form-white" maxlength="255" placeholder="Enter Address" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Address Line2</label> 
																<input type="text" name="address2" id="address2" value="${enterpriseCustomer.address2}" class="form-control form-white" maxlength="255" placeholder="Enter Address" disabled>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																<input type="text" name="locality" id="locality" value="${enterpriseCustomer.locality}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Locality" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">District<span style="color: red;">*</span></label>
																<!-- <input type="text" name="area" id="area" class="form-control form-white" placeholder="District"> -->
																<div class="option-group">
																	<select name="area" id="district" class="form-control form-white" disabled>
																	<option value="">--Select--</option>
																		<c:forEach var="district" items="${districtList}">
																			<c:choose>
																				<c:when test="${district.districtUid == enterpriseCustomer.district}">
																					<option value="${district.districtUid}" selected>${district.districtName}</option>
																				</c:when>
																				<c:otherwise>
																					<option value="${district.districtUid}">${district.districtName}</option>
																				</c:otherwise>
																			</c:choose>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
															<label class="control-label">Mandal<span style="color: red;">*</span></label> 
															<!-- <input type="text" name="area" id="area" class="form-control form-white" placeholder="District"> -->
															<div class="option-group">
																<select name="mandal" id="mandal" class="form-control form-white" disabled>
																<option value="">--Select--</option>
																	<c:forEach var="mandal" items="${mandalList}">
																		<c:choose>
																			<c:when test="${not empty enterpriseCustomer.mandal && mandal.mandalSlno == enterpriseCustomer.mandal && mandal.districtUid == enterpriseCustomer.district}">
																				<option value="${mandal.mandalSlno}" selected>${mandal.mandalName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${mandal.mandalSlno}">${mandal.mandalName}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														 </div>
													 	</div>
													 	<div class="col-sm-6">
														 	<div class="form-group">
																<label class="control-label">City/Village<span style="color: red;">*</span></label>
																<input type="hidden" id="blVillage1" />
																<div class="option-group">
																	<select name="blCity" id="blVillage" class="form-control form-white">
																		<option value="">--Select--</option>
																		<option value="${villages.villageSlno}" selected>${villages.villageName}</option>
																	</select>
																</div>
																<%-- <input type="text" name="blCity" id="blCity" value="${enterpriseCustomer.blCityVillage}" class="form-control form-white specialCharacters" maxlength="255" placeholder="City/Village"> --%>
															</div>
														</div>	
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Pin Code<span style="color: red;">*</span></label> 
																<input type="text" name="pinCode" id="pinCode" value="${enterpriseCustomer.pin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Fax</label> 
																<input type="text" name="fax" id="fax" value="${enterpriseCustomer.fax1}" class="form-control form-white number" maxlength="10" placeholder="Enter Fax" disabled>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Landline No<span
																	style="color: red;">*</span></label>
																<div class="clear"></div>
																<div class="col-sm-5 p-l-0">
																	<div class="form-group">
																		<input type="text" name="stdCode" id="stdCode" value="${enterpriseCustomer.stdCode}" maxlength="10" class="form-control form-white number" placeholder="STD Code" disabled>
																	</div>
																</div>
																<div class="col-sm-7 p-l-0">
																	<div class="form-group">
																		<input type="text" name="landlineNo" id="landlineNo" maxlength="10" value="${enterpriseCustomer.landLine1}" class="form-control form-white number" placeholder="Landline No" disabled>
																	</div>
																</div>
															</div>
															<div class="clear"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="panel">
												<div class="panel-header bg-light">
													<h3>Organization Billing Address</h3>
												</div>
												<div class="panel-content">
													<%-- <div class="row">
														<div class="col-sm-12">
															<div class="form-group">
																<c:choose>
																	<c:when test="${not empty enterpriseCustomer}"><input type="checkbox" name="" disabled id="entCheckbox" class="form-white pull-left"></c:when>
																	<c:otherwise><input type="checkbox" name="" id="entCheckbox" class="form-white pull-left"></c:otherwise>
																</c:choose>
																<label class="control-label p-l-10">Same As Organization Location Address</label>
															</div>
														</div>
														<div class="clear"></div>
													</div> --%>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Address Line1<span style="color: red;">*</span></label> 
																<input type="text" name="address1" id="address1" value="${enterpriseCustomer.blAddress1}" class="form-control form-white" maxlength="255" placeholder="Enter Address" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Address Line2</label> 
																<input type="text" name="address2" id="address2" value="${enterpriseCustomer.blAddress2}" class="form-control form-white" maxlength="255" placeholder="Enter Address" disabled>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																<input type="text" name="locality" id="locality" value="${enterpriseCustomer.blLocality}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Locality" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">District<span style="color: red;">*</span></label>
																<!-- <input type="text" name="area" id="area" class="form-control form-white" placeholder="District"> -->
																<div class="option-group">
																	<select name="area" id="area" class="form-control form-white" disabled>
																	<option value="">--Select--</option>
																		<c:forEach var="district" items="${districtList}">
																			<c:choose>
																				<c:when test="${district.districtUid == enterpriseCustomer.blDistrict}">
																					<option value="${district.districtUid}" selected>${district.districtName}</option>
																				</c:when>
																				<c:otherwise>
																					<option value="${district.districtUid}">${district.districtName}</option>
																				</c:otherwise>
																			</c:choose>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
															<label class="control-label">Mandal<span style="color: red;">*</span></label> 
															<!-- <input type="text" name="area" id="area" class="form-control form-white" placeholder="District"> -->
															<div class="option-group">
																<select name="mandal" id="blMandal" class="form-control form-white" disabled>
																<option value="">--Select--</option>
																	<c:forEach var="mandal" items="${blMandalList}">
																		<c:choose>
																			<c:when test="${not empty enterpriseCustomer.blMandal && mandal.mandalSlno == enterpriseCustomer.blMandal && mandal.districtUid == enterpriseCustomer.blDistrict}">
																				<option value="${mandal.mandalSlno}" selected>${mandal.mandalName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${mandal.mandalSlno}">${mandal.mandalName}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														 </div>
													 	</div>
													 	<div class="col-sm-6">
													 	<div class="form-group">
															<label class="control-label">City/Village<span style="color: red;">*</span></label>
															<input type="hidden" id="blVillage1" />
															<div class="option-group">
																<select name="blCity" id="blVillage" class="form-control form-white">
																	<option value="">--Select--</option>
																	<option value="${blVillages.villageUid}" selected>${blVillages.villageName}</option>
																</select>
															</div>
															<%-- <input type="text" name="blCity" id="blCity" value="${enterpriseCustomer.blCityVillage}" class="form-control form-white specialCharacters" maxlength="255" placeholder="City/Village"> --%>
														</div>
														</div>	
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Pin Code<span style="color: red;">*</span></label> 
																<input type="text" name="pinCode" id="pinCode" value="${enterpriseCustomer.blPin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Fax</label> 
																<input type="text" name="fax" id="fax" value="${enterpriseCustomer.blFax1}" class="form-control form-white number" maxlength="10" placeholder="Enter Fax" disabled>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Landline No<span
																	style="color: red;">*</span></label>
																<div class="clear"></div>
																<div class="col-sm-5 p-l-0">
																	<div class="form-group">
																		<input type="text" name="stdCode" id="stdCode" value="${enterpriseCustomer.blStdCode}" maxlength="10" class="form-control form-white number" placeholder="STD Code" disabled>
																	</div>
																</div>
																<div class="col-sm-7 p-l-0">
																	<div class="form-group">
																		<input type="text" name="landlineNo" id="landlineNo" maxlength="10" value="${enterpriseCustomer.blLandLine1}" class="form-control form-white number" placeholder="Landline No" disabled>
																	</div>
																</div>
															</div>
															<div class="clear"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="pull-right" style="margin-right: 30px;">
										<a href="./enterpriseCustomer"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
									</div>
								</div>
							</div>
							<!-- END ROW -->
							<div class="clear"></div>
						</form>
						<!-- END FORM -->
					</div>
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
