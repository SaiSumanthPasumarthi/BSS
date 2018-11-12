<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page session="false"%>
<html>
<head>
<title>CPE Status Wise Info</title>
<script src="./resources/js/jquery.autocomplete.min.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">
</head>

<style>
table.dataTable tbody td {
    word-break: break-word;
    vertical-align: top;
}
.dataTables_processing {
left: 50%;
position: absolute;
top: 50%;
z-index: 100;
font-size:200%;
}

</style>
<script type="text/javascript">
$(document).ready(function() {

$("#CPEReportDetailsTable").dataTable(); 
		 					
		 			}); 
</script>
<body>
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>CPE Status Wise Info</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">HOME</a></li>
						<li class="active">CPE Status Wise Info</li>
					</ol>
				</div>
				
			   </div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
						

							<c:if test="${not empty cpeReportlist}">
							
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt" id="CPEReportDetailsTable" >
											<thead>
												<tr>
													<th>S.NO</th>
													<th>CPE SrlNO</th>
													<th>CPE Mac Address</th>
													<th>CPE Type</th>
													<th>CPE Model</th>
													<th>Batch Date</th>
													<th>MSP Code</th>
													<th>LMO Code</th>
													<th>District Name</th>
													<th>Mandal Name</th>
													<th>Village Name</th>
													<th>CAF Number</th>
													<th>CAF Act Date</th>
													<th>CAF De-Act Date</th>
													
													
													
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${cpeReportlist}" var="cpe"
													varStatus="rowCount">
													<tr>
														<td>${rowCount.count}</td>
														<td>${cpe.cpeSrlno}</td>
														<td>${cpe.macAddress}</td>
														<td>${cpe.cpeTypeLov}</td>
														<td>${cpe.cpeModel}</td>
														<td>${cpe.cpeBatchDate}</td>
														<td>${cpe.mspCode}</td>
														<td>${cpe.lmoCode}</td>
														<td>${cpe.districtName}</td>
														<td>${cpe.mandalName}</td>
														<td>${cpe.villageName}</td>
														<td>${cpe.cpeCafNo}</td>
														<td>${cpe.cafActivationDate}</td>
														<td>${cpe.cafDeactivationDate}</td>
														
														
														
													</tr>
												</c:forEach>
											</tbody>
										</table>
											
											<div class="" style="">
												<button type="button" class="btn btn-embossed btn-primary"><a href="./cpeHistoryDownload" style="color:white;"  class="cpeStockDownload">Download</a></button>
					 							<button class="btn btn-embossed btn-primary" onclick="javascript: window.history.go(-1)">Back</button>
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
