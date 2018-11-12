<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong> Enterprise Customer Node</strong>
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
														<input type="text" name="orgName" id="orgName" value="${enterpriseCustomerNode.custName}" class="form-control form-white specialCharacters" maxlength="100" placeholder="Enter Organization Name" disabled>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Name<span style="color: red;">*</span></label>
														<input type="text" name="pocName" id="pocName" value="${enterpriseCustomerNode.pocName}" class="form-control form-white specialCharacters" maxlength="150" placeholder="Contact Person Name" disabled>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Mobile No<span style="color: red;">*</span></label> 
														<input type="text" name="pocmobile" id="pocmobile" value="${enterpriseCustomerNode.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No" disabled>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Payment Responsible</label>
														<div class="clear"></div>
														<div class="pull-left" style="padding-left: 0;">
															<input type="radio" class="form-white" id="paymentResponsible" name="paymentResponsible" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag eq 1}">checked </c:if> value="1" disabled>&nbsp;&nbsp;Yes
														</div>
														<div class="col-sm-6">
															<input type="radio" class="form-white" id="paymentResponsible1" name="paymentResponsible" <c:if test = "${enterpriseCustomerNode.pmntliabilityflag eq 0}">checked </c:if> value="0" disabled>&nbsp;&nbsp;No
														</div>
														<div class="clear"></div>
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
										</div>
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
																<input type="text" name="address1" id="address1" value="${enterpriseCustomerNode.address1}" class="form-control form-white" maxlength="255" placeholder="Enter Address" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Address Line2</label> 
																<input type="text" name="address2" id="address2" value="${enterpriseCustomerNode.address2}" class="form-control form-white" maxlength="255" placeholder="Enter Address" disabled>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																<input type="text" name="locality" id="locality" value="${enterpriseCustomerNode.locality}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Locality" disabled>
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
																<select name="mandal" id="mandal" class="form-control form-white" disabled>
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
																<input type="hidden" id="blVillage1" />
																<div class="option-group">
																	<select name="blCity" id="blVillage" class="form-control form-white">
																		<option value="">--Select--</option>
																		<option value="${villages.villageUid}" selected>${villages.villageName}</option>
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
																<input type="text" name="pinCode" id="pinCode" value="${enterpriseCustomerNode.pin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Fax</label> 
																<input type="text" name="fax" id="fax" value="${enterpriseCustomerNode.fax1}" class="form-control form-white number" maxlength="10" placeholder="Enter Fax" disabled>
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
																		<input type="text" name="stdCode" id="stdCode" value="${enterpriseCustomerNode.stdCode}" maxlength="10" class="form-control form-white number" placeholder="STD Code" disabled>
																	</div>
																</div>
																<div class="col-sm-7 p-l-0">
																	<div class="form-group">
																		<input type="text" name="landlineNo" id="landlineNo" maxlength="10" value="${enterpriseCustomerNode.landLine1}" class="form-control form-white number" placeholder="Landline No" disabled>
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
																<input type="text" name="address1" id="address1" value="${enterpriseCustomerNode.blAddress1}" class="form-control form-white" maxlength="255" placeholder="Enter Address" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Address Line2</label> 
																<input type="text" name="address2" id="address2" value="${enterpriseCustomerNode.blAddress2}" class="form-control form-white" maxlength="255" placeholder="Enter Address" disabled>
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																<input type="text" name="locality" id="locality" value="${enterpriseCustomerNode.blLocality}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Locality" disabled>
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
															<!-- <input type="text" name="area" id="area" class="form-control form-white" placeholder="District"> -->
															<div class="option-group">
																<select name="mandal" id="blMandal" class="form-control form-white" disabled>
																<option value="">--Select--</option>
																	<c:forEach var="mandal" items="${blMandalList}">
																		<c:choose>
																			<c:when test="${not empty enterpriseCustomerNode.blMandal && mandal.mandalSlno == enterpriseCustomerNode.blMandal && mandal.districtUid == enterpriseCustomerNode.blDistrict}">
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
																<input type="text" name="pinCode" id="pinCode" value="${enterpriseCustomerNode.blPin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code" disabled>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label class="control-label">Fax</label> 
																<input type="text" name="fax" id="fax" value="${enterpriseCustomerNode.blFax1}" class="form-control form-white number" maxlength="10" placeholder="Enter Fax" disabled>
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
																		<input type="text" name="stdCode" id="stdCode" value="${enterpriseCustomerNode.blStdCode}" maxlength="10" class="form-control form-white number" placeholder="STD Code" disabled>
																	</div>
																</div>
																<div class="col-sm-7 p-l-0">
																	<div class="form-group">
																		<input type="text" name="landlineNo" id="landlineNo" maxlength="10" value="${enterpriseCustomerNode.blLandLine1}" class="form-control form-white number" placeholder="Landline No" disabled>
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
