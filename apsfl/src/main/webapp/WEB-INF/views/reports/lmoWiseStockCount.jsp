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
					<h2>LMO Wise Stock Count</h2>

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

															<table class="table table-alt" id="lmoStockCountTable">
																<thead>
																	<tr>
																		<th>SNo</th>
																		<th>LMO Code</th>
																		<th>Network Name</th>
																		<th>Name of Contact Person</th>
																		<th>Mobile No</th>
																		<th>District Name</th>
																		<th>Mandal Name</th>
																		<th>Village Name</th>
																		<th>CAF Done Count</th>
																		<th>Stock Available Count</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${list}" var="lmoStockCountList" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td> 
																			<td><c:out value="${lmoStockCountList.lmoCode}"></c:out></td>
																			<td><c:out value="${lmoStockCountList.networkName}"></c:out></td>
																			<td><c:out value="${lmoStockCountList.contactName}"></c:out></td>
																			<td><c:out value="${lmoStockCountList.mobileNo}"></c:out></td>
																			<td><c:out value="${lmoStockCountList.district}"></c:out></td>
																			<td><c:out value="${lmoStockCountList.mandal}"></c:out></td>
																			<td><c:out value="${lmoStockCountList.village}"></c:out></td>
																			<td>
																				<a href= "
																					<c:url value="tenantWiseCpeStock">
																						 <c:param name="tenantCode" value="${lmoStockCountList.lmoCode}"/>
																						 <c:param name="status" value="4"/>
																					</c:url>">
																					<c:out value="${lmoStockCountList.cafCount}"></c:out>
																				</a>
																			</td>
																			<td>
																				<a href= "
																					<c:url value="tenantWiseCpeStock">
																						 <c:param name="tenantCode" value="${lmoStockCountList.lmoCode}"/>
																						 <c:param name="status" value="3"/>
																					</c:url>">
																					<c:out value="${lmoStockCountList.stockCount}"></c:out>
																				</a>
																			</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														<a href="./downloadlmoWiseStockCount">	<INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-success"></a>
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