<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Create<strong> Enterprise Customer</strong>
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
											<h3> Organization Information <label id="errorUidNo" style="text-align: left; color: red;"></label></h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Type of Organization<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="custType" id="custType" class="form-control form-white">
																<option value="" >--Select--</option>
																<c:forEach var="lovs" items="${lovsList}">
																	<c:if test = "${(tenantType == 'LMO' && lovs.lovValue != 'GOVT') || (tenantType != 'LMO')}">
																	<c:choose>
																		<c:when test="${not empty enterpriseCustomer && lovs.lovValue == enterpriseCustomer.custType}">
																			<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${lovs.lovValue}">${lovs.lovValue}</option>
																		</c:otherwise>
																	</c:choose>
																	</c:if>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Sub-Type of Organization<span style="color: red;">*</span>
														</label>
														<div class="option-group">
															<select name="officeTypeLov" id="officeTypeLov" class="form-control form-white">
																<option value="">--Select--</option>
																<c:forEach var="lovs" items="${officeTypeLovsList}">
																	<c:choose>
																		<c:when test="${not empty enterpriseCustomer && lovs.lovValue == enterpriseCustomer.officeTypeLov}">
																			<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${lovs.lovValue}">${lovs.lovValue}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="form-label" id="entLable">Registration No/VAT No/TAN No</label> 
														<input type="hidden" name="paymentResponsible" value="1"> 
														<input type="hidden" name="custId" value="${enterpriseCustomer.custId}"> 
														<input type="text" name="uidNo" id="rootUidNo" value="${enterpriseCustomer.regnCode}" class="form-control form-white" placeholder="Enter Value" maxlength="12" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Organization Name<span style="color: red;">*</span></label> 
														<input type="text" name="orgName" id="orgName" value="${enterpriseCustomer.custName}" class="form-control form-white" maxlength="100" placeholder="Enter Organization Name">
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Date of Incorporation<!-- <span style="color: red;">*</span> --></label>
														<label id="dobError" style="text-align: center; color: red;"></label> 
														<input type="text" name="dateOfIncorporation" id= "dateOfIncorporation" <c:if test = "${empty enterpriseCustomer.dateofinc}">id="dateOfIncorporation" </c:if> value = "06/02/2014" class="date-picker form-control form-white" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Email<span style="color: red;">*</span></label> 
														<input type="text" name="emailId" id="emailId" value="${enterpriseCustomer.email1}" class="form-control form-white" maxlength="100" placeholder="Enter Email">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Name<span style="color: red;">*</span></label> 
														<input type="text" name="pocName" id="pocName" value="${enterpriseCustomer.pocName}" class="form-control form-white charectersonly" maxlength="150" placeholder="Contact Person Name">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Designation<span style="color: red;">*</span></label> 
														<input type="text" name="pocDesignation" id="pocDesignation" value="${enterpriseCustomer.pocDesignation}" class="form-control form-white charectersonly"  placeholder="Contact Person Designation">
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person MobileNo<span style="color: red;">*</span></label> 
														<input type="text" name="pocmobile" id="pocmobile" value="${enterpriseCustomer.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Bill Frequency<span style="color: red;">*</span></label>
														<c:if test="${not empty enterpriseCustomer.billfreqLov}">
															<input type="hidden" name="billCycle" value="${enterpriseCustomer.billfreqLov}">
														</c:if>
														<div class="option-group">
															<select name="billCycle" id="billCycle" class="form-control form-white"
																<c:if test = "${not empty enterpriseCustomer.billfreqLov}">disabled</c:if>>
																<!-- <option value="">--Select--</option> -->
																<c:forEach var="billCycle" items="${billCycleList}">
																	<c:choose>
																		<c:when test="${not empty enterpriseCustomer && billCycle.lovValue == enterpriseCustomer.billfreqLov}">
																			<option value="${billCycle.lovValue}" selected>${billCycle.lovValue}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${billCycle.lovValue}">${billCycle.lovValue}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
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
																<label class="control-label">Building Name<span style="color: red;">*</span></label> 
																<input type="text" name="address1" id="address1" value="${enterpriseCustomer.address1}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Street</label> 
																<input type="text" name="address2" id="address2" value="${enterpriseCustomer.address2}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																<input type="text" name="locality" id="locality" value="${enterpriseCustomer.locality}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Locality">
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">District<span style="color: red;">*</span></label>
																<!-- <input type="text" name="area" id="area" class="form-control form-white" placeholder="District"> -->
																<div class="option-group">
																	<select name="area" id="district" class="form-control form-white">
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
																	<select name="mandal" id="mandal" class="form-control form-white">
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
																<div class="option-group">
																	<select name="city" id="village" class="form-control form-white">
																		<option value="">--Select--</option>
																		<c:choose>
																			<c:when test="${not empty villageList}">
																				<c:forEach var="village" items="${villageList}">
																					<c:choose>
																						<c:when test="${not empty enterpriseCustomer.cityVillage && village.villageSlno == enterpriseCustomer.cityVillage && village.mandalSlno == enterpriseCustomer.mandal && enterpriseCustomer.district == village.districtUid }">
																							<option value="${village.villageSlno}" selected>${village.villageName}</option>
																						</c:when>
																						<c:otherwise>
																							<option value="${village.villageSlno}">${village.villageName}</option>
																						</c:otherwise>
																					</c:choose>
																				</c:forEach>
																			</c:when>
																			<c:otherwise>
																				<option></option>
																			</c:otherwise>
																		</c:choose>
																	</select>
																</div>
																<%-- <input type="text" name="city" id="city" value="${enterpriseCustomer.cityVillage}" class="form-control form-white specialCharacters" maxlength="255" placeholder="City/Village"> --%>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Pin Code<span style="color: red;">*</span></label> 
																<input type="text" name="pinCode" id="pinCode" value="${enterpriseCustomer.pin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code">
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Fax</label> 
																<input type="text" name="fax" id="fax" value="${enterpriseCustomer.fax1}" class="form-control form-white number" maxlength="10" placeholder="Enter Fax">
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Landline No<span style="color: red;">*</span></label>
																<div class="clear"></div>
																<div class="col-sm-5 p-l-0">
																	<div class="form-group">
																		<input type="text" name="stdCode" id="stdCode" value="${enterpriseCustomer.stdCode}" maxlength="5" class="form-control form-white number" placeholder="STD Code">
																	</div>
																</div>
																<div class="col-sm-7 p-l-0">
																	<div class="form-group">
																		<input type="text" name="landlineNo" id="landlineNo" maxlength="10" value="${enterpriseCustomer.landLine1}" class="form-control form-white number" placeholder="Landline No">
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
													<div class="row">
														<div class="col-sm-12">
															<div class="form-group">
																<input type="checkbox" name="" id="entCheckbox" class="form-white pull-left"> 
																<label class="control-label p-l-10">Same As Organization Location Address</label>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Building Name<span style="color: red;">*</span></label> 
																<input type="text" name="blAddress1" id="blAddress1" value="${enterpriseCustomer.blAddress1}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Street</label> 
																<input type="text" name="blAddress2" id="blAddress2" value="${enterpriseCustomer.blAddress2}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																<input type="text" name="blLocality" id="blLocality" value="${enterpriseCustomer.blLocality}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Locality">
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">District<span style="color: red;">*</span></label> 
																<input type="hidden" id="blDistrict1" />
																<div class="option-group">
																	<select name="blArea" id="blDistrict" class="form-control form-white">
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
																<input type="hidden" id="blMandal1" />
																<div class="option-group">
																	<select name="blMandal" id="blMandal" class="form-control form-white">
																		<option value="">--Select--</option>
																		<c:forEach var="mandal" items="${mandalList}">
																			<c:choose>
																				<c:when test="${mandal.mandalSlno == enterpriseCustomer.blMandal && mandal.districtUid == enterpriseCustomer.blDistrict}">
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
																		<c:choose>
																			<c:when test="${not empty villageList}">
																				<c:forEach var="village" items="${villageList}">
																					<c:choose>
																						<c:when test="${not empty enterpriseCustomer.blCityVillage && village.villageSlno == enterpriseCustomer.blCityVillage && village.mandalSlno == enterpriseCustomer.mandal && enterpriseCustomer.district == village.districtUid }">
																							<option value="${village.villageSlno}" selected>${village.villageName}</option>
																						</c:when>
																						<c:otherwise>
																							<option value="${village.villageSlno}">${village.villageName}</option>
																						</c:otherwise>
																					</c:choose>
																				</c:forEach>
																			</c:when>
																			<c:otherwise>
																				<option></option>
																			</c:otherwise>
																		</c:choose>
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
																<input type="text" name="blPinCode" id="blPinCode" value="${enterpriseCustomer.blPin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code">
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Fax</label> 
																<input type="text" name="blFax" id="blFax" value="${enterpriseCustomer.blFax1}" class="form-control form-white number" maxlength="10" placeholder="Enter Fax">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Landline No<span style="color: red;">*</span></label>
																<div class="clear"></div>
																<div class="col-sm-5 p-l-0">
																	<div class="form-group">
																		<input type="text" name="blStdCode" id="blStdCode" value="${enterpriseCustomer.blStdCode}" maxlength="5" class="form-control form-white number" placeholder="STD Code">
																	</div>
																</div>
																<div class="col-sm-7 p-l-0">
																	<div class="form-group">
																		<input type="text" name="blLandlineNo" id="blLandlineNo" value="${enterpriseCustomer.blLandLine1}" maxlength="10" class="form-control form-white number" placeholder="Landline No">
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
							</div>
							<!-- END ROW -->
							<div class="pull-left">
								<button class="btn btn-embossed btn-primary" type="submit">Submit</button>
								<a href="./enterpriseCustomer"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
							</div>
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
