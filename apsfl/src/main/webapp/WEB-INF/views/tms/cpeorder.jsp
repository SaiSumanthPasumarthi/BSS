<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/create-product.js"></script>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Orders</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
					<!-- Create Cpe Order -->
				<c:if test="${status eq 'create'}">
				<div class="row main-row">
					<div class="col-sm-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div style="margin: auto; width: 20%" id="statusMessageId"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Create CPE Order</h3>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">CPE Type <span style="color: red;">*</span></label>
															<div>
																<select name="cpeType" id="cpe-Type-Id" class="form-control form-white" onchange="getAllCpeModelByCpeType()">
																	<option value="">--Select--</option>
																	<c:forEach var="cpeTypeVar" items="${cpeMasterList}">
																		<option value="">${cpeTypeVar.cpeTypeLov}</option>
																	</c:forEach>
																</select>
															</div>
														</div>

													</div>
													<div class="col-sm-4" id="cpe-Model-Div-Id"></div>
													<div class="col-sm-4 ">
														<div class="form-group">
															<label class="control-label">Enter Quantity<span style="color: red;">*</span></label>
															<input type="text" name="cpe-Quantity" class="form-control form-white" placeholder="Enter Number" maxlength="6" id="cpe-Quantity">
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-sm-8" id="cpe-cost-details-id"></div>
													<div class="col-sm-4">
														<input type="hidden" id="deviceCostId">
														<div class="form-group" style="float: right;">
															<label class="control-label">&nbsp;</label>
															<div class="option-group">
																<button class="btn btn-embossed btn-primary" type="button" id="add-Cpe-Button-Id"><i class="icon-plus"></i>Add</button>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-sm-12 disable-search">
														<table class="table  table-alt" id="cpe-gridTable">
															<thead>
																<tr>
																	<th hidden="hidden">Profile Id</th>
																	<th>CPE Type</th>
																	<th>CPE Model</th>
																	<th>CPE Cost</th>
																	<th>No of CPEs</th>
																	<th>Total Amount</th>
																	<th>Delete</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</div>
												</div>
												<div class="row" style="margin-left: 800px;">
													<button class="btn btn-embossed btn-primary" type="button" id="saveCpeOrders">Submit</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			
			
			<!-- View Cpe Order List -->
			<c:if test="${status eq 'view'}">
			<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div style="margin: auto; width: 20%" id="statusMessageId"></div>
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>View CPE Order</h3>
											</div>
											<div class="panel-content">
												<div class="row">
												
												<div class="row">
													<div class="col-sm-12 ">
														<table class="table  table-alt dataTab" >
															<thead>
																<tr>
																	<th>Tenant Name</th>
																	<th>CPE Type</th>
																	<th>CPE Model</th>
																	<th>Purchase Cost</th>
																	<th>No of CPEs</th>
																	<th>Requested On</th>
																	<th>Delivered</th>
																	<th>Blocked</th>
																	<th>Pending</th>
																	<th>Total Amount</th>
																	<th>Status</th>
																</tr>
															</thead>
															<tbody>
															<c:forEach items="${cpeMasterList}" var="cpe">
															<tr>
															<td>${cpe.tenantName}</td>
															<td>${cpe.cpeTypeLov}</td>
															<td>${cpe.cpeModel}</td>
															<td>${cpe.purchaseCost}</td>
															<td>${cpe.quantity}</td>
															<td>${cpe.orderedDate}</td>
															<td>${cpe.delvQuantity}</td>
															<td>${cpe.blockedQuantity}</td>
															<td>${cpe.quantity - cpe.delvQuantity}</td>
															<td>${cpe.totalCost}</td>
															<c:choose>
																<c:when test="${tenantType eq 'APFGC' || tenantType eq 'APSFL' }">
																<td><a href="#" onclick="cpeAllocation(${cpe.demandId})" class="btn btn-sm btn-primary">Allocate</a></td>
																</c:when>
																<c:otherwise>
																<td><a href="#" onclick="viewCpeAllocation(${cpe.demandId})"  class="btn btn-sm btn-primary">View</a></td>
																</c:otherwise>
															</c:choose>
															</tr>
															</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</c:if>
			</div>
		</div>
<form action="./allocateCpe" hidden="hidden" method="POST" id="allocateCpeFormId">
<input type="text" name="demandId" id="allocateCpeFormInputId">
</form>
<form action="./viewCpeByTenant" hidden="hidden" method="POST" id="viewCpeFormId">
<input type="text" name="demandId" id="viewCpeFormInputId">
</form>

	</section>