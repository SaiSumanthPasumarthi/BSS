<%@page import="com.arbiva.apsfl.coms.dto.LmoDetailsBO"%>
<%@page import="com.arbiva.apsfl.coms.dto.ComsHelperDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
					<h2>List of LMOs </h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li>Display of Lmos</li>
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

										<input type="hidden" id="tenantType" value=${lmoList } />
										<table class="table table-alt" id="msoLmosDataTable">
											<thead>
												<tr>
												    <th>SNo</th>
													<th>MSO code</th>
													<th>LMO Network Name</th>
													<th>LMO code</th>
													<th>Name of LMO</th>
													<th>LMO MobileNo</th>
													<th>LMO District</th>
													<th>LMO Mandal</th>
												 	<th>LMO Village</th>
												    <th>LMO CreatedOn</th>
												</tr>
											</thead>
											<tbody>
													<c:forEach items="${lmoList}"
														var="lmoAndMso" varStatus="theCount">
															<tr>
																<td align="center">${theCount.count}</td>
																<td>${lmoAndMso.msoCode}</td>
																<td>${lmoAndMso.lmoNetwName}</td>
																<td>
																	<a href= "
																		<c:url value="lmoCafsDetails">
																			 <c:param name="lmocode" value="${lmoAndMso.lmoCode}"/>
																		</c:url>">
																		<c:out value="${lmoAndMso.lmoCode}"></c:out>
																	</a>
																</td>
																<td>${lmoAndMso.lmoName}</td>
																<td>${lmoAndMso.lmoMob}</td>
																<td>${lmoAndMso.lmoDistrict}</td>
																<td>${lmoAndMso.lmoMandal}</td>
																<td>${lmoAndMso.lmoVillage}</td>
																<td>${lmoAndMso.lmo_createdon}</td>
															</tr>
																</c:forEach>
															</tbody>
										</table>
									<!-- 	<button  type="button" class="btn btn-embossed btn-primary cafsDownload1">Download</button> -->
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
	</body>