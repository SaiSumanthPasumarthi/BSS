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
																		<th rowspan="2">S.no</th>
																		<th rowspan="2">Date</th>
																		<th rowspan="2">District</th>
																		<th rowspan="2">MSO Code</th>
																		<th rowspan="2">Name of the MSO</th>
																		<th colspan="3">Total Demand raised on the day</th>
																		<th rowspan="2">Total on the day</th>
																		<th colspan="3">Cumulative</th>
																		<th rowspan="2">Cumulative Total</th>
																	</tr>
																	<tr>
																		<th>4000</th>
																		<th>1700</th>
																		<th>500</th>
																		<th>4000</th>
																		<th>1700</th>
																		<th>500</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${list}" var="listlmo" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td>
																			<td><c:out value="${listlmo.maxdat}"></c:out></td>
																			<td><c:out value="${listlmo.districtname}"></c:out></td>
																			<td><c:out value="${listlmo.mspcode}"></c:out></td>
																			<td><c:out value="${listlmo.mspname}"></c:out></td>
																			<td><c:out value="${listlmo.dqty0}"></c:out></td>
																			<td><c:out value="${listlmo.dqty36}"></c:out></td>
																			<td><c:out value="${listlmo.dqty48}"></c:out></td>
																			<td><c:out value="${listlmo.dqty0+listlmo.dqty36+listlmo.dqty48}"></c:out></td>
																			<td><c:out value="${listlmo.cqty0}"></c:out></td>
																			<td><c:out value="${listlmo.cqty36}"></c:out></td>
																			<td><c:out value="${listlmo.cqty48}"></c:out></td>
																			<td><c:out value="${listlmo.cqty0+listlmo.cqty36+listlmo.cqty48}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														<a href="./downloadMsoWiseDemand?download=true">	<INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-success"></a>
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