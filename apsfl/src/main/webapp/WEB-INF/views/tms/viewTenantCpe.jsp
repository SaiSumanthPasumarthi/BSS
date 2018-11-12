<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2></h2>
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
												<h3>CPE/Order Info</h3>
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
														<%-- <div class="col-sm-2">
															<label>IN STOCK</label> <input type="text"
																value="${cpeHelperDTO.cpeAvailability}"
																disabled="disabled" />
														</div> --%>
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-12">
														<div class="col-sm-2">
															<label>MSO/MSP Name</label> <input type="text"
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
																id="pendingId" disabled="disabled" />
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
										<div class="col-sm-12">
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
																		<th>Payment Status</th>
																		<th>View CPE No's</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${cpeHelperDTO.cpeDeliveryList}"
																		var="cpeDelivery">
																		<tr>
																			<td class="dlvId">${cpeDelivery.dlvid}</td>
																			<td>${cpeDelivery.dlvDate}</td>
																			<td>${cpeDelivery.dlvQty}</td>
																			<td class="totalCost">${cpeDelivery.dlvQty * cpeHelperDTO.purchaseCost}</td>
																			<c:choose>
																				<c:when test="${cpeDelivery.isPaid eq 'NO'}">
																					<td>Blocked For MSO</td>
																				</c:when>
																				<c:otherwise>
																					<td>Allocated TO MSO</td>
																				</c:otherwise>
																			</c:choose>
																			<c:choose>
																				<c:when test="${cpeDelivery.isPaid eq 'NO'}">
																					<td>Need To Pay</td>
																				</c:when>
																				<c:otherwise>
																					<td>Payment Done</td>
																				</c:otherwise>
																			</c:choose>
																			<td><button class="btn btn-sm btn-primary paymentBtn cpeNosView"  data-toggle="modal" data-target="#myModal"
																					value="${cpeDelivery.dlvid}">View</button></td>
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
			</div>
		</div>
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">CPE SNO's</h4>
					</div>
					<div class="modal-body" id="srvcFutuPopUoDiv"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
	</section>

	<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#delvTableId').dataTable({
			 "paging":   false,
			 "ordering": false,
		      "info":     false,
		      "searching": false
		 });
	});
	
		$('.cpeNosView').on('click',function() {
					var dlvId = $(this).val();
					$.ajax({
						async : false,
						type : 'GET',
						url : 'getCpeNosByDlvId',
						data : {
							dlvId : dlvId
						},
						contentType : 'application/json',
						success : function(data) {
							$('#srvcFutuPopUoDiv').html("");
							var i = 0;
							var html = "";

							$.each(data, function(idx, cpeSNo) {
								if (i == 0)
									html = html + '<div class = "row" >'
								html = html + '<div class = "col-sm-2">'
										+ cpeSNo + '</div>'
								i++;
								if (i == 6) {
									html = html + '</div>'
									i = 0;
								}
							});
							$('#srvcFutuPopUoDiv').append(html);
						}
					});
				});
	</script>