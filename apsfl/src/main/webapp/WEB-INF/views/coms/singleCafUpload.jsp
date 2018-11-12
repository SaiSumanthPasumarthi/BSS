<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Create<strong> Single Caf Upload</strong>
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
											<h3>Caf Upload Information</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Location</label> 
														<input type="text" name="location" id="location" <c:choose><c:when test = "${not empty enterpriseCustomerNode}"> value="${enterpriseCustomerNode.regnCode}" </c:when><c:otherwise>value="${orgCode}"</c:otherwise></c:choose> class="form-control form-white addressValidation" placeholder="Enter Value" maxlength="12" required >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">School Name<span style="color: red;">*</span></label> 
														<input type="text" name="SchoolName" id="SchoolName" value="${enterpriseCustomerNode.pocName}" class="form-control form-white charectersonly" maxlength="150" placeholder="Contact Person Name">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Mobile No<span style="color: red;">*</span></label> 
														<input type="text" name="pocmobile" id="pocmobile" value="${enterpriseCustomerNode.pocName}" class="form-control form-white charectersonly" maxlength="150" placeholder="Contact Person Name">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Name<span style="color: red;">*</span></label> 
														<input type="text" name="pocName" id="pocName" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No">
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class = "row"> 
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Designation<span style="color: red;">*</span></label> 
														<input type="text" name="pocDesignation" id="pocDesignation" value="${enterpriseCustomerNode.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No">
													</div>
											    </div>
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Email<span style="color: red;">*</span></label> 
														<input type="text" name="pocEmail" id="pocEmail" value="${enterpriseCustomerNode.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No">
													</div>
											    </div>
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">LMO Code<span style="color: red;">*</span></label>
														<c:if test="${not empty enterpriseCustomer.tenantCode}">
															<input type="hidden" name="tenantCode" value="${enterpriseCustomer.tenantCode}">
														</c:if>
														<div class="option-group">
															<select name="tenantCode" id="tenantCode" class="form-control form-white" <c:if test = "${not empty enterpriseCustomer.tenantCode}">disabled</c:if>>
																<option value="">--Select--</option>
																<c:forEach var="tenantType" items="${tenantTypeList}">
																	<c:choose>
																		<c:when test="${not empty enterpriseCustomer && tenantType == enterpriseCustomer.tenantCode}">
																			<option value="${tenantType}">${tenantType}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${tenantType}">${tenantType}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Longitude<span style="color: red;">*</span></label> 
														<input type="text" name="longitude" id="longitude" value="${enterpriseCustomerNode.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No">
													</div>
											    </div>
											</div>
											<div class = "row"> 
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Latitude<span style="color: red;">*</span></label> 
														<input type="text" name="latitude" id="latitude" value="${enterpriseCustomerNode.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Contact Person Mobile No">
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
