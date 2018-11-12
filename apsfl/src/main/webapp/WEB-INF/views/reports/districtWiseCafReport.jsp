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
					<h2>District Wise CAF Report</h2>

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

															<table class="table table-alt" id="districtWiseCafTable">
																<thead>
																	<tr class="titleRow">
																		<th rowspan="3">S.no</th>
																		<th rowspan="3">District</th>
																		<th colspan="3">No. of CAF up to ${previousDate}</th>
																		<th colspan="3">No. of CAF on the day ${currentDate}</th>
																		<th colspan="3">Total</th>
																		<th rowspan="3">Cumulative CAF's</th>
																	</tr>
																	<tr class="titleRow">
																		<th rowspan="2">House hold CAF's</th>
																		<th colspan="2">No. Of Enterprise CAF's</th>
																		<th rowspan="2">House hold CAF's</th>
																		<th colspan="2">No. Of Enterprise CAF's</th>
																		<th rowspan="2">House hold CAF's</th>
																		<th colspan="2">No. Of Enterprise CAF's</th>
																	</tr>
																	<tr class="titleRow">
																		<th>Private</th>
																		<th>Govt.</th>
																		<th>Private</th>
																		<th>Govt.</th>
																		<th>Private</th>
																		<th>Govt.</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${districtWiseCafList}" var="districtWiseCaf" varStatus="theCount">
																		<tr>
																			<td>${theCount.count}</td>
																			<td>${districtWiseCaf.districtName}</td>
																			<td class="rowData">${districtWiseCaf.hhupToday}</td>
																			<td class="rowData">${districtWiseCaf.pvtupToday}</td>
																			<td class="rowData">${districtWiseCaf.govtupToday}</td>
																			<td class="rowData">${districtWiseCaf.hhOnday}</td>
																			<td class="rowData">${districtWiseCaf.pvtOnday}</td>
																			<td class="rowData">${districtWiseCaf.govtOnday}</td>
																			<td class="rowData">${districtWiseCaf.hhupToday + districtWiseCaf.hhOnday}</td>
																			<td class="rowData">${districtWiseCaf.pvtupToday + districtWiseCaf.pvtOnday}</td>
																			<td class="rowData">${districtWiseCaf.govtupToday + districtWiseCaf.govtOnday}</td>
																			<td class="rowData">${districtWiseCaf.hhupToday + districtWiseCaf.pvtupToday + districtWiseCaf.govtupToday + districtWiseCaf.hhOnday + districtWiseCaf.pvtOnday + districtWiseCaf.govtOnday}</td>
																		</tr>
																	</c:forEach>
																	<tr class="totalColumn">
																		<td colspan="2" style="font-weight: bold" class="aCenter">Total</td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																		<td class="totalCol" style="font-weight: bold"></td>
																	</tr>
																</tbody>
															</table>
														<a href="./downloadDistrictWiseCafReport?download=true">	<INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-success"></a>
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