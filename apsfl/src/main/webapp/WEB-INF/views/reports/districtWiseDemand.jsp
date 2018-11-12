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
					<h2>District Wise CPE Request Report</h2>

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
																		<th rowspan="2">District</th>
																		<th colspan="4">No. Of Boxes Up to ${previousDate}</th>
																		<th colspan="4">No. Of Boxes On The Day (${currentDate})</th>
																		<th colspan="4">Total</th>
																	</tr>
																	<tr>
																		<th>4000</th>
																		<th>1700</th>
																		<th>500</th>
																		<th>Total</th>
																		<th>4000</th>
																		<th>1700</th>
																		<th>500</th>
																		<th>Total</th>
																		<th>4000</th>
																		<th>1700</th>
																		<th>500</th>
																		<th>Total</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${list}" var="listlmo" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td>
																			<td><c:out value="${listlmo.districtname}"></c:out></td>
																			<td><c:out value="${listlmo.d2qty0}"></c:out></td>
																			<td><c:out value="${listlmo.d2qty36}"></c:out></td>
																			<td><c:out value="${listlmo.d2qty48}"></c:out></td>
																			<td><c:out value="${listlmo.d2DisTotal}"></c:out></td>
																			<td><c:out value="${listlmo.d1qty0}"></c:out></td>
																			<td><c:out value="${listlmo.d1qty36}"></c:out></td>
																			<td><c:out value="${listlmo.d1qty48}"></c:out></td>
																			<td><c:out value="${listlmo.d1DisTotal}"></c:out></td>
																			<td><c:out value="${listlmo.dis4000Total}"></c:out></td>
																			<td><c:out value="${listlmo.dis1700Total}"></c:out></td>
																			<td><c:out value="${listlmo.dis500Total}"></c:out></td>
																			<td><c:out value="${listlmo.disTotal}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														<a href="./downloadDistrictWiseDemand?download=true">	<INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-success"></a>
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