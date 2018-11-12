<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/mspLmoCpeRequest.js"></script>
<body>
	<section> <!-- BEGIN MAIN CONTENT -->
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Tenant wise CPE stock Report</h2>

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

														<table class="table table-alt" id="tenantWiseCpeStockTable">
															<thead>
																<tr>
																	<th>SNo</th>
																	<th>CPE Serial No</th>
																	<th>CPE MAC Address</th>
																	<th>CPE Profile</th>
																	<th>Batch Date</th>
																	<th>CAF No</th>
																	<th>Tenant code</th>
																	<th>District</th>
																	<th>Mandal</th>
																	<th>Village</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${tenantStock}"
																	var="tenantWiseCpe" varStatus="theCount">
																	<tr>
																		<td><c:out value="${theCount.count}"></c:out></td> 
																		<td><c:out value="${tenantWiseCpe.cpeSerialNo}"></c:out></td>
																		<td><c:out value="${tenantWiseCpe.cpeMacId}"></c:out></td>
																		<td><c:out value="${tenantWiseCpe.cpeProfile}"></c:out></td>
																		<td><c:out value="${tenantWiseCpe.batchDate}"></c:out></td>
																		<td><c:out value="${tenantWiseCpe.cafNo}"></c:out></td>
																		<td><c:out value="${tenantWiseCpe.tenantCode}"></c:out></td>
																		<td><c:out value="${tenantWiseCpe.district}"></c:out></td>
																		<td><c:out value="${tenantWiseCpe.mandal}"></c:out></td>
																		<td><c:out value="${tenantWiseCpe.village}"></c:out></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
														<a href= "
															<c:url value="downloadTenantWiseCpeStock">
																<c:param name="download" value="true"/>
																<c:param name="tenantCode" value="${tenantCode}"/>
																<c:param name="status" value="${status }"/>
															</c:url>">
															<INPUT TYPE="SUBMIT" VALUE="Download"
															class="btn btn-success">
														</a>
														<button class="btn btn-embossed btn-primary" onclick="javascript:window.history.go(-2);">Back</button>
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
