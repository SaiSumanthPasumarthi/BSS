
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/changedevice.js"></script>
<!-- custom file -->
<%-- @Srinivas V @since Feb 8 2017  --%>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Change IPTV</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
								<li>CHANGE IPTV</li>
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
												<div class="row">
													<div class="panel panel-default ">
														<div class=" panel-body ">
															<div id="hideerrorStatusId" class="text-center"
																style="color: green;">

																<div>
																	<h4 align="center">${statusMessage}</h4>
																</div>

															</div>
															<form method="post" action="getStbDetails">
																<div class="row">
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label class="control-label">Aadhar Number</label> <input
																				type="text" id="aadharCardNo" name="aadharCardNo"
																				class="form-control form-white number"
																				placeholder="Enter Aadhar Card Number" value=""
																				maxlength="12">
																		</div>
																	</div>
																	<div class="col-sm-1">
																		<br /> <label class="control-label">OR</label>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label class="control-label">Mobile Number</label> <input
																				type="text" name="mobileNumber"
																				class="form-control form-white number"
																				maxlength="10" placeholder="Enter Mobile Number">
																		</div>
																	</div>
																	<div class="col-sm-1">
																		<br /> <label class="control-label">OR</label>
																	</div>
																	<div class="col-sm-2">
																		<div class="form-group">
																			<label class="control-label">CAF Number</label> <input
																				type="text" name="cafNumber"
																				class="form-control form-white number"
																				placeholder="Enter CAF Number">
																		</div>
																	</div>
																	<br />
																	<div class="col-sm-1">
																		<div class="form-group">
																			<button type="submit" id="seachStbButtonId"
																				class="btn btn-embossed btn-primary">
																				<i class="fa fa-search"></i>Search
																			</button>
																		</div>
																	</div>
																</div>
															</form>
														</div>
														<div class=" panel-body ">
															<%-- To Display Customers List  --%>
															<div class="col-lg-12">
																<div class="row">
																	<table class="table table-alt" id="cafListTableId">
																		<thead>
																			<tr>
																				<th>LMO Code</th>
																				<th>Aadhar Number</th>
																				<th>CAF Number</th>
																				<th>First Name</th>
																				<th>Last Name</th>
																				<th>Address</th>
																				<th>Locality</th>
																				<th>Village</th>
																				<th>Mandal</th>
																				<th>District</th>
																				<th></th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:forEach items="${customerList}" var="custList">
																				<tr>
																					<td><c:out value="${custList[0]}"></c:out></td>
																					<td><c:out value="${custList[1]}"></c:out></td>
																					<td><c:out value="${custList[2]}"></c:out></td>
																					<td><c:out value="${custList[3]}"></c:out></td>
																					<td><c:out value="${custList[4]}"></c:out></td>
																					<td><c:out value="${custList[5]}"></c:out></td>
																					<td><c:out value="${custList[6]}"></c:out></td>
																					<td><c:out value="${custList[7]}"></c:out></td>
																					<td><c:out value="${custList[8]}"></c:out></td>
																					<td><c:out value="${custList[9]}"></c:out></td>
																					<td><input type="radio" name="custListSelect"></td>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div id="errorMsgId" class="text-center" style="color: red;">
									<h4>
										<label class="control-label"> </label>
									</h4>
								</div>
								<div class=" panel-body ">

									<div class="panel-header bg-light" id="updateGridStbId">
										<h3>Assigned IPTV's</h3>
									</div>
									<div class="panel-content main-panel-content">
										<div class="row">
											<div id="stbDetailsId"></div>
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