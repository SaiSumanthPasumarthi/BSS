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
	var date="${date}";
	var srvcCode = "${srvccode}";
	var url = "showPackagewiseSubscribersPagination?srvcCode="+srvcCode+"&actDate="+date;
	$("#packWiseSubsTable").dataTable({
		"bProcessing" : false,
		"bServerSide" : true,
		"sort" : "position",
		"sAjaxSource" : url,
		"bDestroy": true,
		"aoColumnDefs": [
	                      { "sWidth": "22%", "aTargets": [ 0 ] },
	                      { "sWidth": "12%", "aTargets": [ 1 ] },
	                      { "sWidth": "32%", "aTargets": [ 2 ] },
	                      { "sWidth": "10%", "aTargets": [ 3 ] , orderable: false},
	                     ], 

		"aoColumns" : [
							{"mData" : "aadharNo"},
							{"mData" : "stbMacId"},
							{"mData" : "stbSlno"},
							{"mData" : "packName"}
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
				<h2>Package Wise Subscribers Report</h2>
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
										<table class="table table-alt dataTab" id="packWiseSubsTable">
											<thead>
												<tr>
													<th>Aadhar No</th>
													<th>STB MacId</th>
													<th>STB SrlNo</th>
													<th>Package Name</th>
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