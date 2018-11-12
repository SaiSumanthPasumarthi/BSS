<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>
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
	$('#ageingDiv').hide();
	$('#report').hide();
	
	$('#ageingDate').datepicker({
		changeMonth : true,
		changeYear : true,
	});
	
	$('#ageingSearchButton').on("click",function(){
		
		if($('#ageingDate').val()==''){
			alert("Please select the Date");
			return false;
		}else {
			var date = $('#ageingDate').val();
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
		$('#ageingDiv').show();
		$('#report').show();
		var url = "getAgeingReportByActivationPagination?date="+date;
		$("#ageingTables").dataTable({
			"dom": 'rtip',
			"bProcessing" : true,
			"processing": "<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span>",
			"bServerSide" : true,
			"sort" : "position",
			"sAjaxSource" : url,
			"bDestroy": true,
			"aoColumnDefs": [
		                      { "sWidth": "10%", "aTargets": [ 0 ] },
		                      { "sWidth": "10%", "aTargets": [ 1 ] },
		                      { "sWidth": "10%", "aTargets": [ 2 ] },
		                      { "sWidth": "10%", "aTargets": [ 3 ] },
		                      { "sWidth": "10%", "aTargets": [ 4 ] },
		                      { "sWidth": "10%", "aTargets": [ 5 ] },
		                      { "sWidth": "10%", "aTargets": [ 6 ] },
		                      { "sWidth": "10%", "aTargets": [ 7 ] },
		                      { "sWidth": "10%", "aTargets": [ 8 ] , orderable: false},
		                     ], 
		                     
		                     "aoColumns" : [
		            						{"mData" : "cafNo"},
		            						{"mData" : "aadharNo"},
		            						{"mData" : "stbMacAddr"},
		            						{"mData" : "subsCode"},
		            						{"mData" : "custId"},
		            						{"mData" : "iptvPack"},
		            						{"mData" : "actDate"},
		            						{"mData" : "status"},
		            						{"mData" : "ageing"}
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
				<h2>Ageing Report</h2>
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
																					<input type="text" name="date" id="ageingDate" class="date-picker form-control form-white" placeholder="Select From Date">
																				</div>
																			</div>
																			<div class="col-sm-3">
																				<div class="form-group">
																					<label class="control-label hide-mobile">&nbsp;</label>
																					<div class="option-group">
																						<button class="btn btn-embossed btn-primary" type="submit" id="ageingSearchButton">
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
															<div id = "ageingDiv">
																<table class="table table-alt dataTab" id="ageingTables">
																	<thead>
																		<tr>
																			<th>CAF No</th>
																			<th>Aadhar No</th>
																			<th>STB MAC-Address</th>
																			<th>Subscriber Code</th>
																			<th>Customer ID</th>
																			<th>IPTV Package</th>
																			<th>Activation Date</th>
																			<th>Status</th>
																			<th>Ageing</th>
																		</tr>
																	</thead>
																</table>
																</div>
															<form name="report" id="report" method="get" action="./ageingReportDownload">
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