<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>
<style>
table.dataTable tbody td {
    word-break: break-word;
    vertical-align: top;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#chnlDiv').hide();
	$('#report').hide();
	
	$('#packDate').datepicker({
		changeMonth : true,
		changeYear : true,
	});
	
	$('#chnlSearchButton').on("click",function(){
		
		if($('#packDate').val()==''){
			alert("Please select the Date");
			return false;
		}else {
			var date = $('#packDate').val();
			$('#formDate').val(date);
		}
		
		$("#messageDiv").empty();
		var myDiv = document.getElementById("messageDiv");
		myDiv.style.fontSize = "3";
		myDiv.style.color = "green";
		var d = new Date();
		var month=d.getMonth()+1;
		var currdate = d.getDate() + "-" + month + "-" + d.getFullYear();
		var time = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		var message = "Report was Generated on  "+currdate+" "+time;
		$("#messageDiv").append(message);
		$('#chnlDiv').show();
		$('#report').show();
		var url = "getPackageWiseChannelsPagination?date="+date;
		$("#chnlsTable").dataTable({
			"bProcessing" : false,
			"bServerSide" : true,
			"sort" : "position",
			"sAjaxSource" : url,
			"bDestroy": true,
			"aoColumnDefs": [
		                      { "sWidth": "10%", "aTargets": [ 0 ] },
		                      { "sWidth": "10%", "aTargets": [ 1 ] },
		                      { "sWidth": "10%", "aTargets": [ 2 ] },
		                      { "sWidth": "10%", "aTargets": [ 3 ] , orderable: false},
		                     ], 
		                     
		                     "aoColumns" : [
		            						{"mData" : "packCode"},
		            						{"mData" : "packName"},
		            						{"mData" : "chnlCode"},
		            						{"mData" : "chnlName"},
		            					]
		
		 					});                 

		});
	
});
</script>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
				<h2>Package Wise Channels Report</h2>
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
									<!-- Ageing Activation Report   -->
										<div class="row main-row">
												<div class="col-lg-12">
													<div class="panel main-panel">
														<div class="panel-content main-panel-content">
																<div class="row">
																	<div class="col-sm-12">
																		<div class="row">
																			<div class="col-sm-3">
																				<div class="form-group">
																					<label class="control-label">Date</label> 
																					<input type="text" name="date" id="packDate" class="date-picker form-control form-white" placeholder="Select From Date">
																				</div>
																			</div>
																			<div class="col-sm-3">
																				<div class="form-group">
																					<label class="control-label hide-mobile">&nbsp;</label>
																					<div class="option-group">
																						<button class="btn btn-embossed btn-primary" type="submit" id="chnlSearchButton">
																							<i class="fa fa-search"></i>Search
																						</button>
																					</div>
																				</div>
																			</div>
																			<div class="clear"></div>
																		</div>
																	</div>
																</div>
															<!-- END ROW INNER-->
															<div id="messageDiv" style="float: right;">
																<font >${message}</font>
															</div>
															<div id = "chnlDiv">
																<table class="table table-alt dataTab" id="chnlsTable">
																	<thead>
																		<tr>
																			<th>Channel/IPTV Package Code</th>
																			<th>Channel/IPTV Package Name</th>
																			<th>Channel Code</th>
																			<th>Channel Name</th>
																		</tr>
																	</thead>
																</table>
																</div>
																<form name="report" id="report" method="get" action="./downloadPackageWiseChannels">
																	<input type="hidden" name="download" value="true">
																	<input type="hidden" id="formDate" name="date" value="${date}">
																	<button class="btn btn-embossed btn-primary" type="submit">Download</button>
																</form>
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
</body>	