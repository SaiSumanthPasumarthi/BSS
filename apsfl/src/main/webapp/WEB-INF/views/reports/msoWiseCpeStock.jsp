<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
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
				<h2>MSO Wise Stock Count</h2>

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

														<table class="table table-alt"
															id="msoWiseCpeStockCountTable">
															<thead>
																<tr>
																	<th>S.No</th>
																	<th>MSO code</th>
																	<th>MSO Network Name</th>
																	<th>MSO Name</th>
																	<th>MSO MobileNo</th>
																	<th>MSO District</th>
																	<th>MSO Mandal</th>
																	<th>MSO Village</th>
																	<th>Stock Available Count</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${msoCafNotCpeStockList}" var="msoCpeStock"
																	varStatus="theCount">
																	<tr>
																		<td align="center">${theCount.count}</td>
																			<td>${msoCpeStock.msoCode}</td>
																			<td>${msoCpeStock.msoNetwName}</td>
																			<td>${msoCpeStock.msoName}</td>
																			<td>${msoCpeStock.msoMob}</td>
																			<td>${msoCpeStock.msoDistrict}</td>
																			<td>${msoCpeStock.msoMandal}</td>
																			<td>${msoCpeStock.msoVillage}</td>
																			<td>
																				<a href= "
																					<c:url value="tenantWiseCpeStock">
																						 <c:param name="tenantCode" value="${msoCpeStock.msoCode}"/>
																						 <c:param name="status" value="2"/>
																					</c:url>">
																					<c:out value="${msoCpeStock.cafNotDoneCount}"></c:out>
																				</a>
																			</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
														<a href="./downloadMsoCafNOtCpeStock?download=true"> <INPUT
															TYPE="SUBMIT" VALUE="Download" class="btn btn-success">
														</a>
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
</html>