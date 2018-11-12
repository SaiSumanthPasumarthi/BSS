<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Cpe Allocation</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<label>${responce}</label>
											<div class="panel-header bg-light">
												<h3>Cpe Info</h3>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-12 ">
														<div class="col-sm-2">
															<label>Cpe Type</label> <input type="text"
																value="${cpeHelperDTO.cpeTypeLov}" disabled="disabled" />
														</div>
														<div class="col-sm-2">
															<label>Cpe Model</label> <input type="text"
																value="${cpeHelperDTO.cpeModel}" disabled="disabled" />
														</div>
														<div class="col-sm-2">
															<label>Purchase Cost</label> <input type="text"
																value="${cpeHelperDTO.purchaseCost}" disabled="disabled" />
														</div>
														<div class="col-sm-2">
															<label>In Stock</label> <input type="text"
																value="${cpeHelperDTO.cpeAvailability}"
																disabled="disabled" />
														</div>
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-12">
														<div class="col-sm-2">
															<label>MSO/LMO Name</label> <input type="text"
																value="${cpeHelperDTO.tenantName}" disabled="disabled" />
														</div>
														
														<div class="col-sm-2">
															<label>Order Id</label> <input type="text"
																value="${cpeHelperDTO.demandId}" disabled="disabled" />
														</div>

														<div class="col-sm-2">
															<label>Quantity</label> <input type="text"
																value="${cpeHelperDTO.quantity}" disabled="disabled" />
														</div>

														<div class="col-sm-2">
															<label>Requested On</label> <input type="text"
																value="${cpeHelperDTO.orderedDate}" disabled="disabled" />
														</div>

														<div class="col-sm-2">
															<label> Delivered </label> <input type="text"
																value="${cpeHelperDTO.delvQuantity}" disabled="disabled" />
														</div>

														<div class="col-sm-2">
															<label>Pending</label> <input type="text"
																value="${cpeHelperDTO.quantity - cpeHelperDTO.delvQuantity}"
																id = "pendingId" disabled="disabled" />
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
							<div class="panel-content main-panel-content">
								<div class="row">
									<div class="col-sm-12">
										<div class="col-sm-6">
											<div class="panel">
												<div class="panel-header bg-light">
													<h3>Delivered Summary</h3>
												</div>
												<div class="panel-content">
													<div class="row">
														<div class="col-sm-12">
															<table class="table table-alt" id="delvTableId">
																<thead>
																	<tr>
																		<th>Delivery Id</th>
																		<th>Delivered Date</th>
																		<th>Quantity</th>
																		<th>Total Charge</th>
																		<th>Delivery Status</th>
																		<th>Payment/Status</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${cpeHelperDTO.cpeDeliveryList}" var="cpeDelivery">
																		<tr>
																			<td class="dlvId">${cpeDelivery.dlvid}</td>
																			<td>${cpeDelivery.dlvDate}</td>
																			<td>${cpeDelivery.dlvQty}</td>
																			<td class="totalCost">${cpeDelivery.dlvQty * cpeHelperDTO.purchaseCost}</td>
																			<c:choose>
																				<c:when test="${cpeDelivery.isPaid eq 'NO'}">
																				<td>Blocked For MSO/LMO</td>
																				</c:when>
																				<c:otherwise>
																				<td>Allocated TO MSO/LMO</td>
																				</c:otherwise>
																			</c:choose>
																			<c:choose>
																				<c:when test="${cpeDelivery.isPaid eq 'NO'}">
																				<td><input type="radio" name = "payment" class="paymentModeCash" value="Cash">Cash
																				<input type="radio" name = "payment" class="paymentModeCard" value="Card">Credit Card
																				<button   class="btn btn-sm btn-primary paymentBtn">Pay Now</button></td>
																				</c:when>
																				<c:otherwise>
																				<td>Payment Done</td>
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
										<div class="row">
											<div class="col-sm-6">
												<div class="panel">
													<div class="panel-header bg-light">
														<h3>Allocate CPE</h3>
													</div>
													<div class="panel-content">
														<!-- Multi Select  -->

														<div class="row">
															<div class="col-xs-5">
																<select name="from[]" id="multiselect"
																	class="form-control" size="8" multiple="multiple">
																	<c:forEach items="${cpeHelperDTO.cpeSlnoList}"  var="cpeSlno">
																		<option value="${cpeSlno}">${cpeSlno}</option>
																	</c:forEach>
																</select>
															</div>

															<div class="col-xs-2">
																<!-- <button type="button" id="multiselect_rightAll"
																	class="btn btn-block">
																	<i class="glyphicon glyphicon-forward"></i>
																</button> -->
																<button type="button" id="multiselect_rightSelected"
																	class="btn btn-block">
																	<i class="glyphicon glyphicon-chevron-right"></i>
																</button>
																<button type="button" id="multiselect_leftSelected"
																	class="btn btn-block">
																	<i class="glyphicon glyphicon-chevron-left"></i>
																</button>
																<button type="button" id="multiselect_leftAll"
																	class="btn btn-block">
																	<i class="glyphicon glyphicon-backward"></i>
																</button>
															</div>

															<div class="col-xs-5">
																<select name="to[]" id="multiselect_to"
																	class="form-control" size="8" multiple="multiple"></select>
															</div>
														</div>
														<button id="test" class="btn btn-primary">Submit</button>
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
		</div>
		<form action="./saveCpeAllocation" id="cpeFormId" method="POST">
			<input type="hidden" value="${cpeHelperDTO.demandId}" name="demandId" />
			<input type="hidden" name="cpeIdsList" id="cpeIdsList" />
			<input type="hidden" name="tenantCode" value="${cpeHelperDTO.tenantCode}" />
			<input type="hidden" name="tenantType" value="${cpeHelperDTO.tenantType}" />
		</form>
		
		<form action="./cpePayment" id="cashPaymentFormId" method="POST">
			<input type="hidden" value="${cpeHelperDTO.demandId}" name="demandId" />
			<input type="hidden" name="paymentMode" id="paymentModeId" />
			<input type="hidden" name="dlvId" id="dlvIdId" />
			<input type="hidden" name="totalCost" id="totalCostId" />
		</form>
		
		<form action="./cpeCreditCardPayment" id="creditCardPaymentId" method="POST">
			<input type="hidden" name="tenantCode" value="${cpeHelperDTO.tenantCode}" />
			<input type="hidden" name="tenantName" value="${cpeHelperDTO.tenantName}" />
			<input type="hidden" name="demandId"  value="${cpeHelperDTO.demandId}" />
			<input type="hidden" name="paymentMode" id="paymentModeId1" />
			<input type="hidden" name="dlvId" id="dlvIdId1" />
			<input type="hidden" name="totalCost" id="totalCostId1" />
		</form>

	</section>

	<script type="text/javascript">
		jQuery(document).ready(function() {
			
			$('#delvTableId').dataTable({
				 "paging":   false,
				 "ordering": false,
			      "info":     false,
			      "searching": false
			 });
				
			
							$('#multiselect').multiselect({
												search : {
													left : '<input type="text" name="q" class="form-control" placeholder="Search..." />',
													right : '<input type="text" name="q" class="form-control" placeholder="Search..." />',
												}
											});
						});
		$("#test").on('click', function() {
			var cpeIdsList = [];
			$('#multiselect_to option').each(function(i, cpe) {
				cpeIdsList.push($(cpe).val());
			});
			if (cpeIdsList.length > 0) {
				var pending = $('#pendingId').val();
				if(cpeIdsList.length <= pending){
					$('#cpeIdsList').val(cpeIdsList);
					$('#cpeFormId').submit();
				}else{
					alert("Cannot allocate CPE's more than Pending Quantity ");
				}
				
			} else
				alert("Please Allocate At Least One CPE ");
		});
		
		 
		$('.paymentBtn').on( 'click', function () {
			 var paymentMode;
			 var dlvId = $(this).closest("tr").find(".dlvId").text();
			 var totalCost = $(this).closest("tr").find(".totalCost").text();
			 
				if($(this).closest("tr").find(".paymentModeCash").is(':checked'))
				{
					   paymentMode = $(this).closest("tr").find(".paymentModeCash").val(); 
				}
				if($(this).closest("tr").find(".paymentModeCard").is(':checked'))
				{
					   paymentMode = $(this).closest("tr").find(".paymentModeCard").val(); 
				}
			 
				if(paymentMode == undefined){
					alert("Please Select Payment Option Cash Or Credit Card For Delivery Id :: "+dlvId);
				}else{
					if(paymentMode == "Cash"){
						$('#paymentModeId').val(paymentMode);
						$('#dlvIdId').val(dlvId);
						$('#totalCostId').val(totalCost);
						$('#cashPaymentFormId').submit();
					}else if(paymentMode == "Card"){
						$('#paymentModeId1').val(paymentMode);
						$('#dlvIdId1').val(dlvId);
						$('#totalCostId1').val(totalCost);
						$('#creditCardPaymentId').submit();
					}
				}
		} );
		
		
	</script>