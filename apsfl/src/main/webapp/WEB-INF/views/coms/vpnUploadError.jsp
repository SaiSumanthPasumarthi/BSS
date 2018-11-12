<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
table.dataTable tbody td {
    word-break: break-word;
    vertical-align: top;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var url = "vpnUploadErrorsPagination";
	$("#cafBulkErrorListTables").dataTable({
		"bProcessing" : false,
		"bServerSide" : true,
		"sort" : "position",
		"sAjaxSource" : url,
		"aoColumnDefs": [
							{ "sWidth": "15%", "aTargets": [ 0 ] },
							{ "sWidth": "20%", "aTargets": [ 1 ] },
							{ "sWidth": "20%", "aTargets": [ 2 ] },
							{ "sWidth": "15%", "aTargets": [ 3 ] },
							{ "sWidth": "15%", "aTargets": [ 4 ] },
							{ "sWidth": "15%", "aTargets": [ 5 ] , orderable: false},
							{ "sWidth": "4%", "aTargets": [ 6 ] , orderable: false},
						], 

		"aoColumns" : [
						{"mData" : "uploadid"},
						{"mData" : "fileName"},
						{"mData" : "uploaddate"},
						{"mData" : "totalRec"},
						{"mData" : "successRec"},
						{"mData" : "failureRec"},
						{"mData" : "",
							"render" : function(data, type,full, meta) {
								return '<td>'
								+ '<form class="vpnUploadErrorForm" name="vpnUploadErrorForm" action="#" method="post">'
								+ '<input type="hidden" name="uploadId" value="'+full.uploadid+'" />'
								+ '<span style="cursor:pointer" class="vpnUploadErrorButton"  title="Download Error File"><img src="./resources/images/downloadicon.png"></span>'
								+ '</form>'
								+ '</td>';
							}
						} 
					]
			});
		});
</script>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Display <strong>VPN Upload Error</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Display VPN Upload Error</li>
				</ol>
			</div>
		</div>
		<%-- <label id="" style="color: #006600">${returnValue}</label> --%>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<div class="panel-header bg-light">
										<h3>VPN Upload Error Information</h3>
									</div>
									<div class="panel-content">
										<div class="row">
											<div class="col-sm-12">
												<table class="table table-alt" id="cafBulkErrorListTables">
													<thead>
														<tr>
															<th>Upload Id</th>
															<th>File Name</th>
															<th>Uploaded Date</th>
															<th>Total Records</th>
															<th>Success Records</th>
															<th>Failure Records</th>
															<th>Download</th>
															<!-- <th>Edit/Delete</th> -->
														</tr>
													</thead>
													<%-- <tbody>
														<c:if test="${not empty vpnErrorList}">
															<c:forEach items="${vpnErrorList}" var="vpnError"
																varStatus="rowNum">
																<tr>
																	<td>${rowNum.count}</td>
																	<td>${vpnError.uploadId}</td>
																	<td>${vpnError.fileName}</td>
																	<td>${vpnError.fileSize}</td>
																	<td>${vpnError.noofRows}</td>
																	<td>${vpnError.successRecords}</td>
																	<td>${vpnError.noofRows-vpnError.successRecords}</td>
																	<td align="center">
																		<form class="vpnUploadErrorForm" name="vpnUploadErrorForm" action="#" method="post">
																			<input type="hidden" name="uploadId" value="${vpnError.uploadId}" /> 
																			<span class="vpnUploadErrorButton" onmouseover="this.style.cursor='pointer'" title="Download Error File"><img src="./resources/images/downloadicon.png"></span>
																		</form>
																	</td>
																</tr>
															</c:forEach>
														</c:if>
													</tbody> --%>
												</table>
											</div>
										</div>
										<!-- END ROW INNER-->
									</div>
								</div>
							</div>
						</div>
						<!-- END ROW -->

						<!-- END FORM -->
					</div>
					<!-- END MAIN PANEL CONTENT -->
				</div>
				<!-- END MAIN PANEL -->
			</div>
			<!-- HERE COMES YOUR CONTENT -->
		</div>
		<!-- END MAIN ROW -->
		<div class="clear"></div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END MAIN CONTENT -->