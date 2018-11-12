<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="./resources/js/mspLmoCpeRequest.js"></script>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>LMO Wise CPE Request Details</h2>

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
								<div class="row" align="center">
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="panel main-panel">
													<div class="panel-content main-panel-content">
														<div class="row">

															<table class="table table-alt" id="lmoMspTabId">
																<thead>
																	<tr>
																		<th>LMO Name</th>
																		<th>District Name</th>
																		<th>Mandal Name</th>
																		<th>UpFront CPE's</th>
																		<th>CPE-36 Months (Rs.1700)</th>
																		<th>CPE-48 Months (Rs.500)</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${lmoList}" var="listlmo">
																		<tr>
																			<td><c:out value="${ listlmo.tenantName}"></c:out></td>
																			<td><c:out value="${listlmo.districtName}"></c:out></td>
																			<td><c:out value="${listlmo.mandalName}"></c:out></td>
																			<td><c:out value="${listlmo.emiDemandQuantity}"></c:out></td>
																			<td><c:out value="${listlmo.emi36DemandQuantity}"></c:out></td>
																			<td><c:out value="${listlmo.emi48DemandQuantity}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														<a href="./msoDetails">	<INPUT TYPE="SUBMIT" VALUE="Back to MSO List" class="btn btn-success"></a>
														<a href="./downLoadLmoDetails?tenantcode=${tenantCode}&download=true">	<INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-success"></a>
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
	</section>