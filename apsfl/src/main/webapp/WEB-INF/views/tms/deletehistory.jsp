<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page session="false"%>

<script src="./resources/js/deletehistory.js"></script>
<html>
<head>
<title>Delete History</title>
<script src="./resources/js/jquery.autocomplete.min.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">
</head>
<body>
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>Delete History Log</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">HOME</a></li>
						<li class="active">Delete History</li>
					</ol>
				</div>
				
			   </div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<form role="form" class="form-validation" name="cpeform2"
								id="cpeform2" action="#" method="get" enctype="multipart/form-data">
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">From Date:</label>
											<input type="text" name="fromDate" id="fromDate"  class="form-control form-white " placeholder="Select From Date" value="<c:if test="${not empty fromDate}">${fromDate}</c:if>">
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">To Date:</label>
											<!--  input type="date" name="toDate" id="toDate"  class="date-picker form-control form-white toDate" placeholder="Select To Date" value="<c:if test="${not empty toDate}">${toDate}</c:if>">-->
												<input type="text" name="toDate" id="toDate"  class="form-control form-white toDate" placeholder="Select To Date" value="<c:if test="${not empty toDate}">${toDate}</c:if>">
										</div>
									</div>
							
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Cpe Serial Number</label> 
											<input type="text" name="cpeSerialNumber" id="cpe-Serial-Number"  class="form-control form-white" value="<c:if test="${not empty cpeSerialNumber}">${cpeSerialNumber}</c:if>"  >
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div >
												<button class="btn btn-embossed btn-primary" type="button"
													id="deleteHistoryButton">
													<i class="fa fa-search"></i>Search
												</button>
												
												<a  style="color:white;text-decoration: none;" href="./loghistory"><button class="btn btn-embossed btn-primary" type="button"
													id="deleteLogHistoryBackButton">
													Back
												</button></a>
												
												
											</div>
											
										</div>
									</div>
								</div>
								
							</form>
						
							<!--  div style= "margin-top:-43px;margin-left:10%;">
							<button  class="btn btn-embossed btn-primary" onclick="javascript: window.history.go(-1)">Back</button>
							</div-->
							
							<c:if test="${not empty cpeDeleteLogList}">
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt" id="cpe_delete_log">
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
												<c:forEach items="${cpeDeleteLogList}" var="cpe"
													varStatus="rowCount">
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
											
											
					 						
					 						<form name="deletedownload" id="delete-download" method="get" action="./cpeDeleteHistoryDownload">
																<input type="hidden" name="download" value="true">
																<input type="hidden"  name="toDate" value="${toDate}">
																<input type="hidden"  name="fromDate" value="${fromDate}">
																<input type="hidden"  name="cpeSerialNumber" value="${cpeSerialNumber}">
																
																<button class="btn btn-embossed btn-primary" type="submit">Download</button>
																
															</form>
					 						
					 						
									</div>
								</div>
							</div>
							</c:if>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script src="./resources/js/olt-port-allocation.js"></script>
</body>
</html>
