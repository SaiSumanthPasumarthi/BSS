<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<%@ page session="false"%>
<html>
<head>
<title>Cpe Log History</title>
<script src="./resources/js/jquery.autocomplete.min.js"></script>
<script src="./resources/js/lmo.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">
</head>
<body>
<%  
	
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.
%>

	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>Cpe Log History</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">HOME</a></li>
						
						
					</ol>
				</div>
				
			   </div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<!--  c:if test="${not empty cpeLogList}"-->
							
							
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt  id=log_history_table">
											<thead>
												<tr>
													<th>S.NO</th>
													<th>CPE SrlNO</th>
													<th>CPE Mac Address</th>
													<th>MSP Code</th>
													<th>LMO Code</th>
													<th>Transaction Status</th>
													<th>Transaction date</th>
													<th>User</th>
													
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${cpeLogList}" var="cpe"
													varStatus="rowCount">
													<c:set var = "cpeSerialNumber" scope = "request" value = "${cpe.cpeSrlno}"/>
													<tr>
														<td>${rowCount.count}</td>
														<td>${cpe.cpeSrlno}</td>
														<td>${cpe.macAddress}</td>
														<td>${cpe.mspCode}</td>
														<td>${cpe.lmoCode}</td>
														<td>${cpe.cpeLogTransactionStatus}</td>
														<td>${cpe.cpeLogTransactionDate}</td>
														<td>${cpe.user}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
											<form name="updatedownload" id="update-download" method="get" action="./cpeCreateUpdateLogDownload">
																<input type="hidden" name="download" value="true">
																<input type="hidden"  name="cpeSerialNumber" value="${cpeSerialNumber}">
																
																<button class="btn btn-embossed btn-primary" type="submit">Download</button>
																<a class="col-sm-0" style="color:white;text-decoration: none;" href="./loghistory"><button id="updateHistoryBackButton" class="btn btn-embossed btn-primary " type="button"
													id="logHistoryBackButton">
													Back
												</button></a>
												
																
															</form>
															
													<!-- button  style= "margin-top:-35px;margin-left:10%;" class="btn btn-embossed btn-primary" onclick="javascript: window.history.go(-1)">Back</button-->		
					 						
						 						
						 						 
						 						
									</div>
								</div>

							</div>
							<!--/c:if-->
						</div>
					</div>
				</div>

			</div>
		</div>
	
	<script src="./resources/js/olt-port-allocation.js"></script>
	<script src="./resources/js/lmo.js"></script>
		
</body>

</html>
