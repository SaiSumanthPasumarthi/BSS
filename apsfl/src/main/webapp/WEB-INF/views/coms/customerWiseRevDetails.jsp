<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page session="false"%>
<html>
<head>
<title>Customer Wise revenue Details</title>
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

$("#customerwiserevdetails").dataTable(); 
		 					
		 			}); 
</script>
<body>
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>Customer Wise Revenue Details</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">HOME</a></li>
						<li class="active">Customer Wise Revenue Details</li>
					</ol>
				</div>
				
			   </div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
						
						
						<div class="row">
						
						<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Lmocode:</label><span style="font-weight:bold;color: #c13b34;">${lmocode}</span>
												
											</div>
											</div>
						
						<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Mso code:</label><span style="fo0nt-weight:bold;color: #c13b34;">${msocode}</span>
												
											</div>
											</div>
						
										<div class="col-sm-3">
											<div class="form-group">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">APSFL Share :</label><span style="font-weight:bold;color: #c13b34;">${apsflshare}</span>
												</div>
											</div> 
																					<div class="col-sm-3">
											<div class="form-group">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">MSO Share:</label><span style="font-weight:bold;color: #c13b34;">${msoshare}</span>
												
											</div>
											</div>
																					<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">LMO Share:</label><span style="font-weight:bold;color: #c13b34;">${lmoshare}</span>
												
											</div>
											</div>
											
											
											
											<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Total Paid:</label><span style="font-weight:bold;color: #c13b34;">${paidcount}</span>
												
											</div>
											</div>
											<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Total Not Paid:</label><span style="font-weight:bold;color: #c13b34;">${notpaidcount}</span>
												
											</div>
											
											</div>
											
											<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Total Collected:</label><span style="font-weight:bold;color: #c13b34;">${totalcollected}</span>
												
											</div>
											
											</div>
											
											<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Total Arrears:</label><span style="font-weight:bold;color: #c13b34;">${totalprevbal}</span>
												
											</div>
											
											</div>
											
											<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Remaining Limit:</label><span style="font-weight:bold;color: #c13b34;">${remaininglimit}</span>
												
											</div>
											
											</div>
											


									</div>			 
						
						
						

							<c:if test="${not empty revnList}">
							
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt" id="customerwiserevdetails" >
											<thead>
												<tr>
													<th>SLNO</th>
													<th>Caf No</th>
													<th>Caf Date</th>
													<!-- <th>LMO Code</th> -->
													<th>Customer Name</th>
													
													<th>Contact No</th>
													<!-- <th>Deposit Bal</th> -->
													<th>APSFL Share</th>
													<th>MSO Share</th>
													<th>LMO Share</th>
													<th>Total</th>
													<th>Arrears</th> 
													 <th>Payment Status</th> 
													
													
													
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${revnList}" var="cpe"
													varStatus="rowCount">
													<tr>
														<td>${rowCount.count}</td>
														<td>${cpe.cafNo}</td>
														<td>${cpe.cafDate}</td>
														<%-- <td>${cpe.lmocode}</td> --%>
														<td>${cpe.custname}</td>
														
														<td>${cpe.contactmob}</td>
														<%-- <td>${cpe.depbal}</td> --%>
														<td>${cpe.APSFLShare}</td>
														<td>${cpe.MSOShare}</td>
														<td>${cpe.LMOShare}</td>
														<td>${cpe.total}</td>
														 <td>${cpe.previousBalance}</td>
														 <td>${cpe.paymentstatus}</td>
														
														
														
														
													</tr>
												</c:forEach>
											</tbody>
										</table>
											
											<div class="" style="">
											
					 							<button class="btn btn-embossed btn-primary" onclick="javascript: window.history.go(-1)">Back</button>
					 							
<!-- 					 							 <button class="btn btn-embossed btn-primary" type="button" id="updateCPESubmit"><a href="./multiActionCafDownload1" style="color:white;" >Download</button>
 -->				 						
 <form  id="myform" method="post" action="./multiActionCafDownload1">
  <input type="hidden" name="month" value="${month}" /> 
  <input type="hidden" name="year" value="${year}" /> 
  <input type="hidden" name="lmocode" value="${lmocode}" /> 
  <button  style="margin-left:60px;margin-top:-35px;" class="btn btn-embossed btn-primary" type="input" id="updateCPESubmit"><a onclick="document.getElementById('myform').submit();"  style="color:white;" >Download</button>
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
