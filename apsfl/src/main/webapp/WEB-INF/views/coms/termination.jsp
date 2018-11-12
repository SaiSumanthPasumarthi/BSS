
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/changedevice.js"></script>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Customer Termination</h2>

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
														<div id="hideerrorStatusId" class="text-center"
															style="color: red;">
															<h4>
																<c:out value="${statusMessage}"></c:out>
															</h4>
														</div>
														<form method="POST" action="getAllCafsByCustId">
															<div class="row">
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Select Id Type</label> 
																		<select name="idType" class="form-control form-white"  required="required">
																		<option value=""> -- Select --</option>
																		<option value="aadhar"> Aadhar No / Reg No</option>
																		<option value="mobile"> Mobile No</option>
																		<option value="cafNo">CAF No</option>
																			<c:if test="${domain eq 'SI'}">
																				<option value="trackId">Track ID</option>
																			</c:if>
																		</select>
																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Value</label> 
																		<input type="text" name="idValue" class="form-control form-white"  placeholder="Enter Id Value" required="required">
																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<button type="submit" id="seachStbButtonId" class="btn btn-embossed btn-primary"><i class="fa fa-search"></i>Search	</button>
																	</div>
																</div>
															</div>
														</form>
													</div>
													<div class="col-lg-12">
														<div class="row">
															<table class="table table-alt" id="cafListTableId">
																<thead>
																	<tr>
																		<th>LMO Code</th>
																		<th>Aadhar Number / Reg No</th>
																		<th>CAF Number</th>
																		<th>First Name</th>
																		<th>Last Name</th>
																		<th>Address</th>
																		<th>Locality</th>
																		<th>Village</th>
																		<th>Mandal</th>
																		<th>District</th>
																		<th><input type="checkbox" name="ch1" id="check_All" ></th>
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
																			<td><input type="checkbox" name="ch1" class="check_indi" value ="${custList[2]}"></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
															<input type="hidden" id = "cafNos_termination" value="${cafNos}">
															
															<input type="hidden" id = "tenantCode" value="${tenantCode}">
															
														</div>
														<div id="errorMsgId" class="text-center"style="color: red;">
															<h4>
																<label class="control-label"> </label>
															</h4>
														</div>
													</div>
													
													<div class="row">
											           	<div class="col-sm-2">
																	<div class="form-group">
																		<label class="control-label" style="color:blue">ApprovedBy<span style="color: red;">*</span></label> 
																		<input type="text" id="approvedby" name="approvedby" class="form-control form-white"  placeholder="Enter approvedby" pattern="[A-Za-z0-9]+" title="Please enter alphanumeric only" required="required" style="width:118px">
																	</div>
																</div>
													</div>
													
													<div><button  id="submit_termination" class="btn btn-primary" disabled="disabled">Terminate CAF's</button></div>
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