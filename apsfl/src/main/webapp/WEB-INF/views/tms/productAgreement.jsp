<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.model-div-size {
	max-height: 500px;
    overflow-y: scroll;
}

.ScrollStyle {
	max-height: 300px;
	overflow-y: auto;
	overflow-x: auto;
}
</style>

<body>

	<section>
	
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
		
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>
						Package<strong> Agreement</strong>
					</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li class="active">Package Selection</li>
						</ol>
					</div>
				</div>
				<div class="row main-row">				
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
							
								<input type="hidden" id="prodCodeHidden" />
								<input type="hidden" id="tenantCodeHidden" />
								<input type="hidden" id="effectiveFromHidden" />
								<input type="hidden" id="templCodeHidden" />

									<!-- END ROW INNER-->
									<div class="row divHideAndShow " id="productsListDivId">
										<div class="col-sm-12">
											<table class="table table-alt" id="productsListTable">
												<thead>
													<tr>
														<th>Package Name</th>
														<th>Package Type</th>
														<th>Customer Type</th>
														<th>Package Charge</th>
														<th>Tax Component</th>
														<th>Total Charge</th>
														<th>Lock in Period</th>
														<th>Effective From</th>
														<th hidden="hidden">Product Code</th>
														<th hidden="hidden">Tenant Code</th>
														<th hidden="hidden">Product Type</th>
														<th>Select</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${productsList}" var="product" varStatus="rowCount">
														<tr>
															<td class ="prodName">${product.productName}</td>
															<td>${product.prodType}</td>
															<td>${product.custType}</td>
															<td>${product.monthlyCharge}</td>
															<td>${product.monthlyTax}</td>
															<td>${product.productCharge}</td>
															<td>${product.lockInDays}</td>
															<td class ="effectiveFrom">${product.effectiveFrom}</td>
															<td hidden="hidden" class ="prodCode">${product.productCode}</td>
															<td hidden="hidden" class="tenantCode">${product.tenantcode}</td>
															<td hidden="hidden" class="prodType">${product.prodType}</td>
															<td><input type="checkbox" name="ch1" value = "${rowCount.index}" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<table  id="selectedProductTableHidden" hidden="hidden"></table>
										</div>
									</div>
									
								<div class="row divHideAndShow">
								<div class="col-lg-12">
									<div class="col-sm-6 disable-search " >
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Package Service Information</h3>
											</div>
											<div id="productinfo" style="max-height: 375px" class="ScrollStyle">
												<table class="table table-alt " id="prodServicelistTable" >
													<thead>
														<tr>
															<th>Package Name</th>
															<th>Service Name</th>
															<th>Service Code</th>
															<th>Charge Type</th>
															<th> Amount</th>
															<!-- <th>GL-Name</th>
															<th>GL-Code</th> -->
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
							

									<div class="col-sm-6" >
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Revenue Sharing</h3>
											</div>
											<div class="panel-content" style="max-height: 375px; min-height: 100px " >
												<div class="row">
													<div class="col-sm-12">
														<div class="form-group"   >
															<!--revenu sharing code start -->
															<div id="sub-div">
															<div class="col-sm-4">
															<label class="control-label">No of Partners <span style="color: red;">*</span></label>
															</div>
															<div class="col-sm-4">
															<input type="number" class="form-control form-white " maxlength="2" id="count" min="1" />
															</div>
															<div class="col-sm-4">
																<button type="button" id="modalbutton"
																	class="btn btn-embossed btn-primary"
																	data-toggle="modal" data-target="#myModal" >Get Details</button>
															</div>
															</div>
															<div>&nbsp</div>

															<!-- Modal -->
															<div id="myModal" class="modal fade  " role="dialog">
																<div class="modal-dialog ">

																	<!-- Modal content-->
																	<div class="modal-content model-div-size">
																		<div class="modal-header">
																			<h3 class="modal-title">Revenue sharing details</h3>
																		</div>

																		<div class="modal-body">
																			<!-- END ROW INNER-->
																			<div class="row m-b-12">
																				<div class="col-sm-12 disable-search">
																					<div  class="ScrollStyle ">
																						<table class="table  table-alt "   id="templateList">
																							<thead>
																								<tr>
																									<th>Select</th>
																									<th>Template Code</th>
																									<th>Template Name</th>
																									<th>No of Partners</th>

																								</tr>
																							</thead>
																							<tbody>
																								
																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																			<!-- END ROW INNER-->
																			<!-- sharing details-->
																			<div id="sharedetails" style="display: none" id="rsPopUpDivMaster">
																				<div class="panel-content" id="rsPopUpDiv">
																				
																				</div>
																			</div>


																		</div>
																		<div class="modal-footer">
																			<button type="button" class="btn btn-default" id ="popUpCloseButtonId"data-dismiss="modal">Close</button>
																			<button type="button" class="btn btn-primary"
																				data-dismiss="modal" id="selecttoshow" disabled>Select</button>
																		</div>
																	</div>
																	<!-- data after select button click in popup-->


																</div>
															</div>


															<!-- revenu sharing code end -->

															<!-- popup select button click display the data -->

															<div>
																<div id="selecedtemplatedata" style="max-height: 240px" >
																</div>
																<div>&nbsp</div>
																<div id="radioDivId1">
																<input type="radio" name="radio11" value = "singTempl" class="addOnClass11"> Same Revenue Sharing For All Packages
																</br><input type="radio" name="radio11" value = "multiTempl" class="addOnClass11"> Different Revenue Sharing For Each Packages
																</div>
																<div id="SinglRev"></br><input type="radio" name="radio11" value = "SinglRev"  class="addOnClass11"> Same Revenue Sharing For All tenants</div>
															</div>

														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									</div>	
								</div>
								<!-- Selected Products Information -->
									<div class="row" style=" position:static;">
										<div class="col-sm-12" id="multipleTempDevId">
											<div class="col-sm-12 backButtonDivId"></div>
											<br />
											<div id="selectedProdDivId" class="col-sm-6"></div>
											<div id="selectedTempViewDivId" class="col-sm-6 "></div>
										</div>
									</div>
								<div class="row">
								<div class="col-sm-12">
								<button class="btn btn-embossed btn-primary pull-right" type="button" id="saveProductAgreement" >Submit</button>
								</div>
								</div>
								</div>
							</div>
						</div>
					 </div>
					</div>
				</div>
  
        
        
  

	</section>