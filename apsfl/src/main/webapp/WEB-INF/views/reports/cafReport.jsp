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
					<h2>CAF Report</h2>

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
																		<th rowspan="2" style="width: 8%;">Date</th>
																		<th rowspan="2" style="width: 8%;">District</th>
																		<th rowspan="2">Village / City</th>
																		<th rowspan="2">Name of the LCO</th>
																		<th rowspan="2">No. of CAF's on the day(HH)</th>
																		<th rowspan="2">Cumulative CAF's(HH)</th>
																		<th colspan="2">No. of Enterprise CAF's on the day</th>
																		<th colspan="2">Cumulative Enterprise CAF's</th>
																		<th rowspan="2">Cumulative CAF's</th>
																	</tr>
																	<tr>
																		<th>Private</th>
																		<th>Govt.</th>
																		<th>Private</th>
																		<th>Govt.</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${list}" var="listlmo" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td>
																			<td><c:out value="${listlmo.maxdat}"></c:out></td>
																			<td><c:out value="${listlmo.districtname}"></c:out></td>
																			<td><c:out value="${listlmo.village}"></c:out></td>
																			<td><c:out value="${listlmo.lmoName}"></c:out></td>
																			<td><c:out value="${listlmo.cafsCountOnTheDay}"></c:out></td>
																			<td><c:out value="${listlmo.cumulativeCafs}"></c:out></td>
																			<td><c:out value="${listlmo.epCafsCountPrivateDay}"></c:out></td>
																			<td><c:out value="${listlmo.epCafsCountGovtDay}"></c:out></td>
																			<td><c:out value="${listlmo.epCafsCountPrivateCu}"></c:out></td>
																			<td><c:out value="${listlmo.epCafsCountGovtCu}"></c:out></td>
																			<td><c:out value="${listlmo.cumulativeCafsEntered}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														<a href="./downloadCafWiseReport">	<INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-success"></a>
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