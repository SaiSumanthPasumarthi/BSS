<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="./resources/js/create-product.js"></script>

<style>
.modal-dialog {
    width: 1200px;
    margin: 30px auto;
}
</style>

<!-- BEGIN MAIN CONTENT -->
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Create<strong> Package </strong>
			</h2>
			<label id="productException" style="color: #ff0000"></label> 
							<label id="productException1" style="color: #ff0000"></label>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Create Package </li>
				</ol>
			</div>
		</div>

		<div id="mainDiv" class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form id="product_form" action="#" novalidate="novalidate">
							
							
							<!-- END ROW -->
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
								<div class="panel-header bg-light">
									<h3>Package  Information</h3>
								</div>
								<div>
									<input type="hidden"  value="${tenantcode}" id="tenantcodeId">
								</div>
								<div class="panel-content">
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Code <span
												style="color: red;">*</span></label> <input type="text"
												name="ProductCode" maxlength="20"
												class="form-control form-white productValidateClass specialCharactersNotAllow"
												placeholder="Enter Code" id="productCodeId">
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Name <span
												style="color: red;">*</span></label> <input type="text"
												name="ProductName" maxlength="90"
												class="form-control form-white productValidateClass specialCharactersNotAllow"
												placeholder="Enter Name" id="productNameId">
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="form-label">Effective From <span
												style="color: red;">*</span></label> <input type="text"
												id="Datepicker1"
												class="date-picker form-control form-white productValidateClass"
												placeholder="Select a date..." readonly="readonly">
										</div>
									</div>

									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Lock In Months</label> <input
												type="text" name="lockindays" maxlength="3"
												class="form-control form-white "
												placeholder="Enter Number (-- Optional --)"
												id="lockInDaysId">
										</div>
									</div>

								</div>
								<div class="row" id="packageRowDiv2Id">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Customer Type <span
												style="color: red;">*</span></label>
											<div>
												<select name="custType" id="custTypeId"
													class="form-control form-white productValidateClass">
													<option value="">--Select--</option>
													<c:forEach var="custType" items="${custTypeList}">
														<option value="${custType.lovValue}">${custType.lovValue}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Package  Type <span
												style="color: red;">*</span></label>
											<div>
												<select name="prodType" id="prodTypeId"
													class="form-control form-white productValidateClass"
													onchange="prodValidityDiv()">
													<option value="">--Select--</option>
													<c:forEach var="prodType" items="${prodTypeList}">
														<option value="${prodType.lovValue}">${prodType.lovValue}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-3" id="prodValidityDivId">
										<div class="form-group">
											<label class="control-label"> Package  Validity In
												Months<span style="color: red;">*</span>
											</label> <input type="text" name="prodValidity" maxlength="3"
												class="form-control form-white productValidateClass "
												placeholder="Enter Number" id="prodValidityId">
										</div>
									</div>
								</div>
							</div>
							</div>
							</div>
							</div>

							<!-- END ROW -->

							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Add Services</h3>
										</div>
										<div>
											<label id="duplicateServiceErrorId" style="color: #ff0000"></label>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Name <span
															style="color: red;">*</span></label>
														<div>
															<select id="serviceNameId" name="serviceName" onchange="setOtherFields()" class="form-control form-white">
																<option value="">--Select--</option>
																<%-- <c:forEach var="service" items="${servicesList}">
																	<option
																		value="${service.srvccode},${service.coresrvccode},${service.totalTax},${service.multSrvcsAllowed}">${service.srvcname}</option>
																</c:forEach> --%>
															</select>
														</div>
														<label id="serviceNameLable" style="color: #ff0000"></label>
													</div>
												</div>
												
													<div class="col-sm-3">
															<div class="form-group">
																<label class="control-label">Charge Types <span style="color: red;">*</span></label>
															<div>
															<input type="hidden" id="chargeTypeList" value="${chargeTypeList}"></input>
															<select name="ChargeType" id="chargeTypeId" class="form-control form-white chargeTypeClass" onChange="getChargeTypes()" multiple>
																<c:forEach var="chargeType" items="${chargeTypeList}">
																	<option value="${chargeType.chargeCode}">${chargeType.chargeName}</option>
																</c:forEach>
															</select>
															<input type="hidden" value = "false" id="isVoipId">
															</div>
														<label id="chargeTypeLable" style="color: #ff0000"></label>
															</div>
														</div>
											
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Code</label> <input
															type="text" name="Code" id="serviceCodeId"
															class="form-control form-white validateClass"
															readonly="readonly" placeholder="Code">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Core Service Name</label> <input
															type="text" name="CoreServiceName" id="CoreServiceNameId"
															class="form-control form-white validateClass"
															readonly="readonly" placeholder="Core Service Name">
															<input type="text"  id="CoreServiceCodeId" hidden="hidden">
															<input type="hidden" name="multSrvcsAllowed" id="multSrvcsAllowedId" />
													</div>
												</div>

											<!-- 	<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Tax Percentage</label>
														<input type="text" name="TaxAmount" id="taxAmountId"
															class="form-control form-white validateClass"
															readonly="readonly" placeholder="Tax Amount Percentage" />

														<input type="hidden" name="multSrvcsAllowed" id="multSrvcsAllowedId" />
													</div>
												</div> -->
												<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
											<div class="row">
											
										<%-- 	<div class="col-sm-3">
															<div class="form-group">
																<label class="control-label">Charge Types <span style="color: red;">*</span></label>
															<div>
															<input type="hidden" id="chargeTypeList" value="${chargeTypeList}"></input>
															<select name="ChargeType" id="chargeTypeId" class="form-control form-white" onChange="getChargeTypes()" multiple>
																<c:forEach var="chargeType" items="${chargeTypeList}">
																	<option value="${chargeType.chargeCode}">${chargeType.chargeName}</option>
																</c:forEach>
															</select>
															</div>
														<label id="chargeTypeLable" style="color: #ff0000"></label>
															</div>
														</div>--%>
											
											</div> 
											<div class="row col-sm-12" id ="telephonyFeatures">
												</div>
											<div class="row col-sm-12" id="chargeTypeTableDivId">
											</div>
											
											<div class="row">
											<div class="col-sm-3 "></div>
											<div class="col-sm-3 "></div>
											<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">&nbsp;</label>
														<div class="option-group">
															<button class="btn btn-embossed btn-primary "
																type="button" id="addServiceButtonId">
																<i class="icon-plus"></i>Add Service
															</button>
														</div>
													</div>
											</div>
											<div class="col-sm-3 ">
												<div class="form-group">
														<label class="control-label">&nbsp;</label>
														<div class="option-group">
															<button class="btn btn-embossed btn-primary " data-toggle="modal" data-target="#myModal"
																type="button" id="viewSrvcFeatButton">
																<i class="icon-plus"></i>
															</button>
															<button class="btn btn-embossed btn-primary " data-toggle="modal" data-target="#telePhoneyValues"
																type="button" id="viewtelePhoneyValues">
																<i class="icon-plus"></i>
															</button>
														</div>
													</div>
											</div>
											</div>
											<!-- END ROW INNER-->
											
												

											<div class="row">
												<div class="col-sm-12 disable-search">
													<table class="table  table-alt" id="gridTable">
														<thead>
															<tr>
																<th>Service Name</th>
																<th>Code</th>
																<th>Core Service</th>
																<th>Charge Type</th>
																<th>Delete</th>
															</tr>
														</thead>
														<tbody>
															<tr hidden="true">
																<td>Testing 1</td>
																<td>Code 1</td>
																<td>CS 1</td>
																<td>Monthly</td>
																<td>Delete</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
											<!-- END ROW INNER-->

										</div>
									</div>
								</div>
							</div>
							<!-- END ROW -->
							<button class="btn btn-embossed btn-primary" type="button"
								id="saveProductInfoId">Submit</button>
							<!--<button class="btn btn-embossed btn-danger" type="button"
								id="cancelButtonId">Cancel</button> -->
								<a href ="./home"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
						</form>

						<!-- END FORM -->
					</div>
					<!-- END MAIN PANEL CONTENT -->
				</div>
				<!-- END MAIN PANEL -->
			</div>
			<!-- HERE COMES YOUR CONTENT -->
		</div>
		<form action="" id="showProductsList">
			<input type="hidden" id="returnValueId" name="returnValue" />
		</form>

		<table 
			class="table table-striped table-bordered table-hover"
			id="hiddenTable">
			<tr>
				<th>Error Code</th>
				<th>Error Message</th>
				<th>Description</th>
			</tr>
		</table>

		<!-- END MAIN ROW -->
		<div class="clear"></div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END MAIN CONTENT -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Service Features</h4>
      </div>
      <div class="modal-body" id="srvcFutuPopUoDiv">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<div id="telePhoneyValues" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Telephony</h4>
      </div>
      <div class="modal-body" id="telePhoneyPopUpId">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>




