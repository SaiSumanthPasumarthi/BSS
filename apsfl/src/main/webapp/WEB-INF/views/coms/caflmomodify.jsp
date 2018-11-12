<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				CAF<strong> Information </strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">CAF Information</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form role="form" class="form-validation" name="bulkCafUploadForm" id="bulkCafUploadForm" action="./saveLMOCafDetails" method="post">
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
											<h3>CAF Information</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">APSFL Code<span style="color: red;">*</span></label> 
														<input type="hidden" name="cafNo" id="cafNo" value = "${cafObject.cafNo}" >
														<input type="text" name="apsflUniqueId" id="apsflUniqueId" class="form-control form-white" value = "${cafObject.apsflUniqueId}" placeholder="Enter Value" maxlength="10" required >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
													<label class="control-label">District<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="district" id="apsflDistrict" class="form-control form-white" required>
																<option value="">--Select--</option>
																<c:forEach var="district" items="${districtList}">
																	<c:choose>
																		<c:when test="${not empty cafObject && cafObject.instDistrict == district.districtUid}">
																			<option value="${district.districtUid},${district.districtName}" selected>${district.districtName}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${district.districtUid},${district.districtName}">${district.districtName}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Mandal<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="mandal" id="apsflMandal" class="form-control form-white" required>
																	<option value="">--Select--</option>
																	<c:choose>
																		<c:when test="${not empty mandalList}">
																			<c:forEach var="mandal" items="${mandalList}">
																				<c:choose>
																					<c:when test="${not empty cafObject.instMandal && mandal.mandalSlno == cafObject.instMandal && cafObject.instDistrict == mandal.districtUid}">
																						<option value="${mandal.mandalSlno},${mandal.mandalName}" selected>${mandal.mandalName}</option>
																					</c:when>
																					<c:otherwise>
																						<option value="${mandal.mandalSlno},${mandal.mandalName}">${mandal.mandalName}</option>
																					</c:otherwise>
																				</c:choose>
																			</c:forEach>
																		</c:when>
																	</c:choose>
																</select>
															</div>
														</div>
													</div>
												<%-- <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">District<span style="color: red;">*</span></label> 
														<input type="hidden" name="cafNo" id="cafNo" value = "${cafObject.cafNo}" >
														<input type="text" name="district" id="district" class="form-control form-white" value = "${cafObject.instDistrict}" placeholder="Enter Value" maxlength="100" required  >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Mandal<span style="color: red;">*</span></label> 
														<input type="text" name="mandal" id="mandal" class="form-control form-white" value = "${cafObject.instMandal}" placeholder="Enter Value" maxlength="100" required >
													</div>
												</div> --%>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Location<span style="color: red;">*</span></label> 
														<input type="text" name="location" id="location" class="form-control form-white" value = "${cafObject.instLocality}" placeholder="Enter Value" maxlength="100" required >
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class = "row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Organization Name<span style="color: red;">*</span></label> 
														<input type="text" name="firstName" id="firstName" value = "${cafObject.cpePlace}" class="form-control form-white" maxlength="100" placeholder="Enter Value" >
													</div>
												</div> 
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Mobile No<span style="color: red;">*</span></label> 
														<input type="text" name="mobileNo" id="mobileNo" value = "${cafObject.contactmobileNo}" class="form-control form-white number" maxlength="10" placeholder="Enter Value" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Name<span style="color: red;">*</span></label> 
														<input type="text" name="pocName" id="pocName" class="form-control form-white" maxlength="100" value = "${cafObject.contactPerson}" placeholder="Enter Value" > 
													</div>
												</div>
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Designation</label> 
														<input type="text" name="pocDesignation" id="pocDesignation" value = "${cafObject.pocDesignation}" class="form-control form-white" maxlength="100" placeholder="Enter Value" >
													</div>
											    </div>
											</div>
											<div class = "row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Email</label> 
														<input type="text" name="emailId" id="emailId" value = "${cafObject.contactEmail}" class="form-control form-white" maxlength="100" placeholder="Enter Value" >
													</div>
											    </div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">LMO Name<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="lmoCode" id="lmoCode" class="form-control form-white" >
																<option value="">--Select--</option>
																<c:forEach var="tenantType" items="${tenantTypeList}">
																	<c:choose>
																		<c:when test="${not empty cafObject && tenantType[0] == cafObject.lmoCode}">
																			<option value="${tenantType[0]}" selected >${tenantType[1]}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${tenantType[0]}">${tenantType[1]}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Longitude</label> 
														<input type="text" name="longitude" id="longitude" value = "${cafObject.longitude}" class="form-control form-white numbersOnly" maxlength="16" placeholder="Enter Value">
													</div>
											    </div> 
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Latitude</label> 
														<input type="text" name="latitude" id="latitude" value = "${cafObject.lattitude}" class="form-control form-white numbersOnly" maxlength="16" placeholder="Enter Value">
													</div>
											    </div>
											 </div>
											<!-- END ROW INNER-->
										</div>
									</div>
								</div>
							</div>
							<!-- END ROW -->
							<div class="pull-left">
								<button class="btn btn-embossed btn-primary" type="submit">Submit</button>
								<a href="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
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
