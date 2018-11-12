<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>JsonViewer</title>
<style>
.modal-dialog {
	width: 1200px;
	margin: 30px auto;
}

table td {
	word-wrap: break-word;
	max-width: 400px;
}
</style>
</head>
<body>
	<div class="main-content">
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Provision Error Recycling</h2>
				<label id="error" style="text-align: center; color: red;"></label>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">Home</a></li>
						<li class="active">Provision Error Recycling</li>
					</ol>
				</div>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<form id="jsonErrorFormID" method="GET">
								<form:form method="GET" modelAttribute="" id="ErrorFormID">
									<div class="row">
										<div class="col-sm-12">
											<div class="row">
												<div style="margin: auto; width: 30%" id="ErrorMsg">
													<font face="Times New Roman" size="4px" color="Red">${error}</font>
												</div>
												<div style="margin: auto; width: 30%" id="comsErrorMsg">
													<font face="Times New Roman" size="4px" color="Green">${message}</font>
												</div>
											</div>
											<select name="errorType" id="errorType" >
											  <option value="internal">Internal Equipment Errors</option>
											  <option value="other">Other Errors</option>
											 </select>
											 											 
											<div class="row firstTable">
												<input type="hidden" name="hiddenReqIds"
													id="hiddenReqIds_ID">
												<table id="pendprovid" class="table table-alt display">
													<thead>
														<tr>
															<th>Request ID</th>
															<th>Request</th>
															<th>Response</th>
															<th>Status</th>
															<th>Created Date</th>
															<th>Executed Date</th>
															<th></th>
														</tr>
													</thead>
													<tbody >
														<c:forEach items="${pedprovErrList}" var="provReq"
															varStatus="status">

															<c:set var="firstSteps" value="${provReq.resp}" />
															<c:set var="secondSteps" value="${provReq.resp}" />

															<c:if
																test="${fn:contains(firstSteps,'Equipment returned an unidentified error') || fn:contains(firstSteps,'OMCI communications timeout with remote device') || fn:contains(firstSteps,' Unknown parameter or value')}">

																<tr>
																	<td>${provReq.requestid}</td>
																	<td>${provReq.req}</td>
																	<td>${provReq.resp}</td>
																	<td>${provReq.status}</td>
																	<td>${provReq.createdDate}</td>
																	<td>${provReq.executedDate}</td>
																	<td><input type="checkbox" name="checkSel"
																		class="checkSelClass"></td>
																</tr>												
																
															</c:if>				
															
															
														</c:forEach>
													</tbody>
													</table>
													<div class="row" style="float: right; clear: right;">
												<button type="button" id="update_button"
													class="btn btn-embossed btn-primary">Update</button>
											</div>	
													</div>
													<div class="row secondTable">																								
													<table id="pendprovid2" class="table table-alt display" >
													<thead>
														<tr>
															<th>Request ID</th>
															<th>Request</th>
															<th>Response</th>
															<th>Status</th>
															<th>Created Date</th>
															<th>Executed Date</th>															
														</tr>
													</thead>													
													<tbody>
														<c:forEach items="${pedprovErrList}" var="provReq">
															<c:set var="firstSteps" value="${provReq.resp}" />
															<c:set var="secondSteps" value="${provReq.resp}" />												
															<tr>
																	<td>${provReq.requestid}</td>
																	<td>${provReq.req}</td>
																	<td>${provReq.resp}</td>
																	<td>${provReq.status}</td>
																	<td>${provReq.createdDate}</td>
																	<td>${provReq.executedDate}</td>																	
															</tr>																													
														</c:forEach>
													</tbody>				
												</table>
											</div>
											
										</div>
									</div>
								</form:form>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
<script type="text/javascript">

$(document).ready(function() {
	$('#pendprovid').DataTable();
	$('#pendprovid2').DataTable();
	
	 $( ".secondTable" ).hide();
	 //$( "#table2" ).hide();

	
$( "#errorType" ).change(function() {

var optionSelected= $( "#errorType option:selected" ).text();
 if (optionSelected=='Internal Equipment Errors'){

	 $( ".firstTable" ).show();
	 $( ".secondTable" ).hide();
	
	 
	
	
	 }else
		 {
		
		$( ".firstTable" ).hide();
		$( ".secondTable" ).show();
		
		
		 }
 
});
});
</script>
</html>
