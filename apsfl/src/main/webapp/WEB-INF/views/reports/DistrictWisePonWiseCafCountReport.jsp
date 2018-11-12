<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>

<script src="./resources/js/mspLmoCpeRequest.js"></script>

<script type="text/javascript" >

$(document).ready(function() {
	
$('#districtponwisecafcountTable').dataTable();

});

</script>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>District Wise PON Wise Caf Count Report</h2>

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
													
													<div class="panel panel-default">
															<div class="panel-body">
																<form action="./districtWisePonWiseCafCount"
																	name="districtWisePonWiseCafCount"
																	id="districtWisePonWiseCafCount" method="POST">
																	<div class="row">
																		<div class="col-lg-12">
																			<div class="col-sm-3">
																				<div class="form-group">
																					<label class="form-label">From<span
																						style="color: red;"></span></label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectivefrom" id="effectivefrom" value="${effectivefrom}"/>
																				</div>
																				

																			</div>
																			<div class="col-sm-3">
																			
																				<div class="form-group">
																					<label class="form-label">To<span
																						style="color: red;"></span></label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectiveto" id="effectiveto"  value="${effectiveto}"/>
																				</div>
																			</div>
																			
																			<div class="col-sm-1">
																			<label></label>
																			<label>
																			<p><span style="color:blue, font-size: 150%;">OR</span></p>
																			</label>
																			</div>
																			<div class="col-sm-3">
																			
																				<div class="form-group">
																					<label class="form-label">LMO Code </label><input type="text"
																						class="form-control form-white"
																						placeholder="Enter LMOCode"
																						name="lmocode" id="lmocode" value="${lmocode}"/>
																				</div>
																			</div>


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
														<p> &emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;">LMO Count: ${lmocount}</span> &emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;"> Total CAF Count:${totalcafcount}</span>&emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;">Port Count: ${portcount}</span></p>
													</div>
														
														<div class="row">

															<table class="table table-alt" id="districtponwisecafcountTable">
																<thead>
																	<tr>
																		<th>SNo</th>
																		<th>District</th>
																		<th>Mandal</th>
																		<th>Village</th>
																		<th>LMOCode</th>
																		<th>POPName</th>
																		<th>PortNo.</th>
																		<th>CAF Count</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${districtPonWiseCafCountList}" var="distponcafcountList" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td> 
																			<td><c:out value="${distponcafcountList.district}"></c:out></td>
																			<td><c:out value="${distponcafcountList.mandal}"></c:out></td>
																			<td><c:out value="${distponcafcountList.villagename}"></c:out></td>
																			<td><c:out value="${distponcafcountList.lmoCode}"></c:out></td>
																			<td><c:out value="${distponcafcountList.popName}"></c:out></td>
																			<td><c:out value="${distponcafcountList.portNo}"></c:out></td>
																			<td><c:out value="${distponcafcountList.cafCount}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														<a
																	href="
																					<c:url value="./downloaddistrictWisePonWiseCafCount">
																						 <c:param name="effectivefrom" value="${effectivefrom}"/>
																						 <c:param name="effectiveto" value="${effectiveto}"/>
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
	</section>