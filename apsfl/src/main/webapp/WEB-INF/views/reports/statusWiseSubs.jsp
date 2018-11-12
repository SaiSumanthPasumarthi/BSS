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
	var fromDate="${date1}";
	var toDate = "${date2}";
	var status = "${status}";
	var radio = "${radio}";
	var url = "getStatusWiseSubDetailsPagination?status="+status+"&date1="+ fromDate+ "&date2="+ toDate+ "&radio=" + radio;
	$("#statusWiseSubsTable").dataTable({
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
	                      { "sWidth": "10%", "aTargets": [ 5 ] , orderable: false},
	                     ], 

		"aoColumns" : [
							{"mData" : "stbMacId"},
							{"mData" : "subsCode"},
							{"mData" : "actDate"},
							{"mData" : "suspDate"},
							{"mData" : "deactDate"},
							{"mData" : "blackListDate"}
					]
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
				<h2>Status Wise Subscribers Report</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
			    </div>
			<!--Pack Wise Subscribers Report   -->
				<div class="row main-row">
						<div class="col-lg-12">
							<div class="panel main-panel">
								<div class="panel-content main-panel-content">
									<!-- END ROW INNER-->
									<div id="messageDiv" style="float: right;">
										<font >${message}</font>
									</div>
										<table class="table table-alt dataTab" id="statusWiseSubsTable">
											<thead>
												<tr>
													<th>Mac ID</th>
													<th>Subscriber Code</th>
													<th>Activation Date</th>
													<th>Suspend Date</th>
													<th>Deactivation Date</th>
													<th>Blacklisted Date</th>
												</tr>
											</thead>
										</table>
								   <button class="btn btn-embossed btn-primary" onclick="javascript: window.history.go(-2)">Back</button>
							</div>
						</div>
					</div>
				</div>
			</div>    
		</div>
	</section>
</body>	