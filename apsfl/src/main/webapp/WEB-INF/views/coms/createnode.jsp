<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Create<strong> Enterprise Customer Node</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Enterprise Customer Node</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form role="form" class="form-validation" name="entcustomernodeform" id="entcustomernodeform" action="./saveEntCustomer" method="post">
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
														<label class="control-label">Organization Name<span style="color: red;">*</span></label> 
														<input type="text" name="orgName" id="orgName" value="${enterpriseCustomerNode.custName}" class="form-control form-white" maxlength="100" placeholder="Enter Organization Name">
														<c:choose>
														<c:when test = "${not empty modify }">
														<input type="hidden" name="custType" id="custType" value="${enterpriseCustomerNode.custType}"> 
														<input type="hidden" name="custCode" id="custCode" value="${enterpriseCustomerNode.parentcustcode}">
														<input type="hidden" name="billCycle" id="billCycle" value="${enterpriseCustomerNode.billfreqLov}">
														<%-- <input type="hidden" name="emailId" id="emailId" value="${enterpriseCustomerNode.email1}"> --%>
														<input type="hidden" name="custId" id="custId" value="${enterpriseCustomerNode.custId}">
														<input type="hidden" name="officeTypeLov" id="officeTypeLov" value="${enterpriseCustomerNode.officeTypeLov}">
														<input type="hidden" name="dateOfIncorporation" id="dateOfIncorporation" <c:if test = "${not empty enterpriseCustomerNode.dateofinc}"> value='<fmt:formatDate pattern="MM/dd/yyyy" value="${enterpriseCustomerNode.dateofinc}"/>' </c:if> >
														</c:when>
														<c:otherwise>
														<input type="hidden" name="custType" id="custType" value="${custType}"> 
														<input type="hidden" name="custCode" id="custCode" value="${customerCode}">
														<input type="hidden" name="billCycle" id="billCycle" value="${billCycle}">
														<%-- <input type="hidden" name="emailId" id="emailId" value="${emailId}"> --%>
														<input type="hidden" name="custId" id="custId" value="${enterpriseCustomerNode.custId}">
														<input type="hidden" name="officeTypeLov" id="officeTypeLov" value="${officeTypeLov}">
														<input type="hidden" name="dateOfIncorporation" id="dateOfIncorporation" <c:if test ="${not empty dateOfIncorporation}"> value='<fmt:formatDate pattern="dd/MM/yyyy" value="${dateOfIncorporation}"/>'</c:if> >
														</c:otherwise></c:choose>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Registration No/VAT No/TAN No</label> 
														<input type="text" name="uidNo" id="nodeUidNo" <c:choose><c:when test = "${not empty enterpriseCustomerNode}"> value="${enterpriseCustomerNode.regnCode}" </c:when><c:otherwise>value="${orgCode}"</c:otherwise></c:choose> class="form-control form-white" placeholder="Enter Value" maxlength="12"  >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Name<span style="color: red;">*</span></label> 
														<input type="text" name="pocName" id="pocName" value="${enterpriseCustomerNode.pocName}" class="form-control form-white charectersonly" maxlength="150" placeholder="Contact Person Name">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Designation<span style="color: red;">*</span></label> 
														<input type="text" name="pocDesignation" id="pocDesignation" value="${enterpriseCustomerNode.pocDesignation}" class="form-control form-white" maxlength="50" placeholder="Contact Person Designation">
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class = "row"> 
												<!-- <div class="col-sm-12"> -->
												    <div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Contact Person Mobile No<span style="color: red;">*</span></label> 
															<input type="text" name="pocmobile" id="pocmobile" value="${enterpriseCustomerNode.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No">
														</div>
												    </div>
												    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Payment Responsible</label>
														<div class="clear"></div>
														<div class="pull-left" style="padding-left: 0;">
															<c:if test = "${not empty enterpriseCustomerNode}"> <input type = "hidden" name = "paymentResponsible" value = "${enterpriseCustomerNode.pmntliabilityflag}"></c:if>
															<input type="radio" class="form-white" id="paymentResponsible" name="paymentResponsible" <c:if test = "${not empty enterpriseCustomerNode}">disabled </c:if> <c:if test = "${enterpriseCustomerNode.pmntliabilityflag eq 1}">checked </c:if> value="1" checked >&nbsp;&nbsp;Yes&nbsp;&nbsp;
														</div>
															<input type="radio" class="form-white paymentResponsibleNode" id="paymentResponsible1" name="paymentResponsible" <c:if test = "${not empty enterpriseCustomerNode}">disabled </c:if> <c:if test = "${enterpriseCustomerNode.pmntliabilityflag eq 0}">checked </c:if> value="0" >&nbsp;&nbsp;No
														<div class="clear"></div>
													</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Contact Person EmailID</label><label id ='star'></label> 
															<label id="emailError" style="text-align: center; color: red;"></label> 
															<input type="email" name="emailId" id="emailId" value="${enterpriseCustomerNode.email1}" class="form-control form-white"  maxlength="100" placeholder="Contact Person EmailID">
														</div>
												    </div>
												<!-- </div> -->
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
																<input type="text" name="address1" autocomplete="off" id="address1" value="${enterpriseCustomerNode.address1}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Street</label>
																<input type="text" name="address2" autocomplete="off" id="address2" value="${enterpriseCustomerNode.address2}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Locality/Area<span style="color: red;">*</span></label>
																<input type="text" name="locality" autocomplete="off" id="locality" value="${enterpriseCustomerNode.locality}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Locality">
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
																				<c:when test="${district.districtUid == enterpriseCustomerNode.district}">
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
																				<c:when test="${not empty enterpriseCustomerNode.mandal && mandal.mandalSlno == enterpriseCustomerNode.mandal && mandal.districtUid == enterpriseCustomerNode.district}">
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
																					<c:when test="${not empty enterpriseCustomerNode.cityVillage && village.villageSlno == enterpriseCustomerNode.cityVillage && village.mandalSlno == enterpriseCustomerNode.mandal && enterpriseCustomerNode.district == village.districtUid }">
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
																<%-- <input type="text" name="city" id="city" value="${enterpriseCustomerNode.cityVillage}" class="form-control form-white specialCharacters" maxlength="255" placeholder="City/Village"> --%>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Pin Code<span style="color: red;">*</span></label>
																<input type="text" name="pinCode" id="pinCode" value="${enterpriseCustomerNode.pin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code">
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Fax</label>
																<input type="text" name="fax" id="fax" value="${enterpriseCustomerNode.fax1}" class="form-control form-white number" maxlength="10" placeholder="Enter Fax">
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
																		<input type="text" name="stdCode" id="stdCode" value="${enterpriseCustomerNode.stdCode}" maxlength="5" class="form-control form-white number" placeholder="STD Code">
																	</div>
																</div>
																<div class="col-sm-7 p-l-0">
																	<div class="form-group">
																		<input type="text" name="landlineNo" id="landlineNo" maxlength="10" value="${enterpriseCustomerNode.landLine1}" class="form-control form-white number" placeholder="Landline No">
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
																<input type="checkbox" name="" id="entCheckbox" class="form-white pull-left" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
																<label class="control-label p-l-10">Same As Organization Location Address</label>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Building Name<span style="color: red;">*</span></label>
																<input type="text" name="blAddress1" autocomplete="off" id="blAddress1" value="${enterpriseCustomerNode.blAddress1}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Street</label>
																<input type="text" name="blAddress2" autocomplete="off" id="blAddress2" value="${enterpriseCustomerNode.blAddress2}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Locality/Area<span style="color: red;">*</span></label>
																<input type="text" name="blLocality" id="blLocality" value="${enterpriseCustomerNode.blLocality}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Locality" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">District<span style="color: red;">*</span></label>
																<input type="hidden" id="blDistrict1" />
																<div class="option-group">
																	<select name="blArea" id="blDistrict" class="form-control form-white" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
																		<option value="">--Select--</option>
																		<c:forEach var="district" items="${districtList}">
																			<c:choose>
																				<c:when test="${district.districtUid == enterpriseCustomerNode.blDistrict}">
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
																	<select name="blMandal" id="blMandal" class="form-control form-white" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
																		<option value="">--Select--</option>
																		<c:forEach var="mandal" items="${mandalList}">
																			<c:choose>
																				<c:when test="${mandal.mandalSlno == enterpriseCustomerNode.blMandal && mandal.districtUid == enterpriseCustomerNode.blDistrict}">
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
																	<select name="blCity" id="blVillage" class="form-control form-white" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
																		<option value="">--Select--</option>
																		<c:choose>
																		<c:when test="${not empty villageList}">
																			<c:forEach var="village" items="${villageList}">
																				<c:choose>
																					<c:when test="${not empty enterpriseCustomerNode.blCityVillage && village.villageSlno == enterpriseCustomerNode.blCityVillage && village.mandalSlno == enterpriseCustomerNode.mandal && enterpriseCustomerNode.district == village.districtUid }">
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
																<%-- <input type="text" name="blCity" id="blCity" value="${enterpriseCustomerNode.blCityVillage}" class="form-control form-white specialCharacters" maxlength="255" placeholder="City/Village"> --%>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Pin Code<span style="color: red;">*</span></label>
																<input type="text" name="blPinCode" id="blPinCode" value="${enterpriseCustomerNode.blPin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Fax</label>
																<input type="text" name="blFax" id="blFax" value="${enterpriseCustomerNode.blFax1}" class="form-control form-white number" maxlength="10" placeholder="Enter Fax" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
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
																		<input type="text" name="blStdCode" id="blStdCode" value="${enterpriseCustomerNode.blStdCode}" maxlength="5" class="form-control form-white number" placeholder="STD Code" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
																	</div>
																</div>
																<div class="col-sm-7 p-l-0">
																	<div class="form-group">
																		<input type="text" name="blLandlineNo" id="blLandlineNo" value="${enterpriseCustomerNode.blLandLine1}" maxlength="10" class="form-control form-white number" placeholder="Landline No" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag == 0}">disabled</c:if>>
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
