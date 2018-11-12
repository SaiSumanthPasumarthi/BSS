<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body><%@ taglib prefix="c"
		uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<script src="./resources/js/mspLmoCpeRequest.js"></script>
<body>

	<section>
	 <!-- BEGIN MAIN CONTENT -->
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>MSO Details With Aligned LMOs Report</h2>

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
															id="msoDetailsWithAlignedLMOsTable">
															<thead>
																<tr>
																	<th>S.No</th>
																	<th>MSO code</th>
																	<th>MSO Network Name</th>
																	<th>Name of MSO ContactPerson</th>
																	<th>MSO MobileNo</th>
																	<th>MSO District</th>
																	<th>MSO Mandal</th>
																	<th>MSO Village</th>
																	<th>MSO On Board Date</th>
																	<th>LMO code</th>
																	<th>LMO Network Name</th>
																	<th>Name of LMO contactPerson</th>
																	<th>LMO MobileNo</th>
																	<th>LMO District</th>
																	<th>LMO Mandal</th>
																	<th>LMO Village</th>
																	<th>LMO On Board Date</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${lmoMsoList}"
																	var="lmoAndMso" varStatus="theCount">
																	<tr>
																		<td align="center">${theCount.count}</td>
																		<td>${lmoAndMso.msoCode}</td>
																		<td>${lmoAndMso.msoNetwName}</td>
																		<td>${lmoAndMso.msoName}</td>
																		<td>${lmoAndMso.msoMob}</td>
																		<td>${lmoAndMso.msoDistrict}</td>
																		<td>${lmoAndMso.msoMandal}</td>
																		<td>${lmoAndMso.msoVillage}</td>
																		<td>${lmoAndMso.mso_createdon}</td>
																		<td>${lmoAndMso.lmoCode}</td>
																		<td>${lmoAndMso.lmoNetwName}</td>
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
														<a href="./downloadMsoWiseLmoDetails?download=true">
															<INPUT TYPE="SUBMIT" VALUE="Download"
															class="btn btn-success">
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
