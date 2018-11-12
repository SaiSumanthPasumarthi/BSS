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
					<h2>MSO Wise CPE Request Report</h2>

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
																		<th>MSO Code</th>
																		<th>MSO Company  Name</th>
																		<th>MSO Contact Name </th>
																		<th>MSO Contact No</th>
																		<th>District Name </th>
																		<th>Mandal Name</th>
																		<th>UpFront CPE's</th>
																		<th>CPE-36 Months (Rs.1700)</th>
																		<th>CPE-48 Months (Rs.500)</th>
																		<th>View</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${msoList}" var="listmso">
																		<tr>
																			<td><c:out value="${ listmso.tenantCode}"></c:out></td>
																			<td><c:out value="${listmso.tenantName}"></c:out></td>
																			<td><c:out value="${listmso.regOfficePocName }"></c:out></td>
																			<td><c:out value="${listmso.pocMobileNumber}"></c:out></td>
																			<td><c:out value="${listmso.districtName }"></c:out></td>
																			<td><c:out value="${listmso.mandalName }"></c:out></td>
																			<td><c:out value="${listmso.emiDemandQuantity}"></c:out></td>
																			<td><c:out value="${listmso.emi36DemandQuantity}"></c:out></td>
																			<td><c:out value="${listmso.emi48DemandQuantity}"></c:out></td>
																			<td><a href="./lmoDetails?tenantcode=${listmso.tenantCode}">View</a></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
															<a href="./downloadMsoDetails?download=true">	<INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-embossed btn-primary"></a>
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