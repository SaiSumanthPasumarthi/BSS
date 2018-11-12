<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>

<script src="./resources/js/mspLmoCpeRequest.js"></script>


<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title" style="margin-left: 50px">
					<h2>District Wise CAF Report</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				<div class="row main-row" style="margin-left: 45px">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row" align="center">
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="panel main-panel">
													<div class="panel-content main-panel-content">
														<div class="panel panel-default">
															<div class="panel-body">
																<form action="./managerdistWiseCafReport"
																	name="managerdistWiseCafReport"
																	id="managerdistWiseCafReport" method="POST">
																	<div class="row">
																		<div class="col-lg-12">
																			<div class="col-sm-3">
																				<%-- <div class="form-group"> 
 																							<label class="control-label">From Date<span style="color: red;"> </span></label> 
 																							<input type="date" name="effectivefrom" id="effectivefrom"  class="form-control form-white number" maxlength="10" placeholder="Enter Year:YYYY-MM-DD" min="2016-01-01" max="2030-12-31" required value="${hiddenfromdate}">  
 																					</div> --%>
																				<div class="form-group">
																					<label class="form-label">From<span
																						style="color: red;"></span></label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectivefrom" id="effectivefrom"  value="${hiddenfromdate}"/>
																				</div>
																				

																			</div>
																			<div class="col-sm-3">
																			
																				<div class="form-group">
																					<label class="form-label">To<span
																						style="color: red;"></span></label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectiveto" id="effectiveto"  value="${hiddentodate}"/>
																				</div>
																			</div>
																			
																			<div class="col-sm-1">
																			<label></label>
																			<label>
																			<p><span style="color:blue;">OR</span></p>
																			</label>
																			</div>
																			<div class="col-sm-3">
																			
																				<div class="form-group">
																					<label class="form-label">LMO Code </label><input type="text"
																						class="form-control form-white"
																						placeholder="Enter LMOCode"
																						name="lmocode" id="lmocode"/>
																				</div>
																			</div>

																			<input type="hidden" name="hiddentodate"
																				id="hiddentodate" value=""> <input
																				type="hidden" name="hiddenfromdate"
																				id="hiddenfromdate" value="">

																			<div class="col-sm-3">
																				<div class="form-group">
																					<label class="control-label"></label>
																				</div>
																				<div class="form-group">
																					<button class="btn btn-embossed btn-primary"
																						type="Submit" id="searchButtons1">
																						<i class="fa fa-search"></i>Search
																					</button>
																				</div>
																			</div>
																		</div>
																	</div>
																</form>
															</div>

														</div>
														<div class="row">
														<p>   &emsp; &emsp; <span style="font-weight:bold;color: #c13b34;">LMO Count: ${lmocount}</span> &emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;">Total CAF Count: ${totalcafcount}</span></p>
														</div>
														<div class="row">

															<table class="table table-alt" id="districtWiseCafTable">
																<thead>
																	<tr class="titleRow"
																		style="background-color: rgb(242, 242, 242);">
																		<th>S.no</th>
																		<th>Lmocode</th>
																		<th>District</th>
																		<th>Mandal</th>
																		<th>Village</th>
																		<th>Date</th>
																		<th>Total Cafs</th>
																	</tr>


																</thead>
																<tbody>
																	<c:forEach items="${districtWiseCafList}"
																		var="districtWiseCafList" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td>
																			<td><c:out
																					value="${districtWiseCafList.lmoCode}"></c:out></td>
																			<td><c:out
																					value="${districtWiseCafList.district}"></c:out></td>
																			<td><c:out value="${districtWiseCafList.mandal}"></c:out></td>
																			<td><c:out
																					value="${districtWiseCafList.village}"></c:out></td>
																			<td><c:out
																					value="${districtWiseCafList.createddate}"></c:out></td>
																			<td><a
																				href="
																					<c:url value="managerCustomerCafReport">
																						 <c:param name="tenantCode" value="${districtWiseCafList.lmoCode}"/>
																						 <c:param name="effectivefrom" value="${hiddenfromdate}"/>
																						 <c:param name="from" value="${districtWiseCafList.createddate}"/>
																						 <c:param name="to" value="${hiddentodate}"/> 
																					</c:url>">
																					<c:out value="${districtWiseCafList.cafCount}"></c:out>
																			</a></td>
																		</tr>
																	</c:forEach>
																</tbody>

															</table>
															<div class="form-group">
																<a
																	href="
																					<c:url value="./downloadManagerDistrictWiseDemand">
																						 <c:param name="effectivefrom" value="${hiddenfromdate}"/>
																						 <c:param name="effectiveto" value="${hiddentodate}"/>
																						 <c:param name="lmocode" value="${lmocode}"/>	 
																					</c:url>">
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
		</div>
	</section>