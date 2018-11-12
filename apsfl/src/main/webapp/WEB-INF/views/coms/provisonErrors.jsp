<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>jJsonViewer</title>
<style>
.modal-dialog {
	width: 1200px;
	margin: 30px auto;
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
							<%-- <form  id="jsonErrorFormID" method="GET" > --%>
							<form:form method="GET" modelAttribute="" id="jsonErrorFormID">
								<div class="row">
									<div class="col-sm-12">
										<div class="row">
											<div style="margin: auto; width: 30%" id="comsErrorMsg">
												<font face="Times New Roman" size="4px" color="Red">${error}</font>
											</div>
											<div style="margin: auto; width: 30%" id="comsErrorMsg">
												<font face="Times New Roman" size="4px" color="Green">${message}</font>
											</div>
										</div>
										<div class="row">
											<input type="hidden" name="hiddenReqIds" id="hiddenReqIds_ID">
											<table id="provReqListID" class="table dataTalProvising table-alt display">
												<thead>
													<tr>
														<th width="15%">Caf No</th>
														<th width="15%">Request ID</th>
														<th width="15%">Package Code</th>
														<th width="15%">Service Code</th>
														<th width="15%">Created Date</th>
														<th width="7%"></th>
														<th width="5%"></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${provErrList}" var="provReq" varStatus="status">
														<tr>
															<td>${provReq.cafNo}</td>
															<td>${provReq.requestId}</td>
															<td>${provReq.prodCode}</td>
															<td>${provReq.srvcCode}</td>
															<td>${provReq.createdOn}</td>
															<td><a href="#" class="viewBtnClass" data-toggle="modal" data-target="#myModal">View</a></td>
															<td><input type="checkbox" name="checkSel" class="checkSelClass"></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="row" style="float: right; clear: right;">
											<button type="button" id="resend_btn" class="btn btn-embossed btn-primary">Submit</button>
										</div>
										<!-- Modal -->
										<div id="myModal" class="modal fade" role="dialog">
											<div class="modal-dialog ">
												<!-- Modal content-->
												<div class="modal-content model-div-size">
													<div class="modal-header">
														<h3 class="modal-title"> Error Jsons
															<button type="button" class="btn btn-default" id="popUpCloseButtonId" style="float: right; clear: right; margin-top: -0.5em;" data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
																<div class="ScrollStyle" style="width: 1160px; overflow-x: auto;">
																	<table class="table table-alt" id="jsonErrListID">
																		<thead>
																			<tr>
																				<th>SNo</th>
																				<th>Request</th>
																				<th>Response</th>
																				<th>Created Date</th>
																				<th>Executed Date</th>
																				<th>Status</th>
																			</tr>
																		</thead>
																		<tbody>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>