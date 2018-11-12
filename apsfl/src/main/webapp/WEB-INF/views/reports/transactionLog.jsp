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
	
	$("#radiodate").click (function() {
		$("#daterange").show();
   });
	
    $("#tillDate").click (function() {
		$("#daterange").hide();
		$("#txnFromDate,#txnToDate").val("");
    });
    
    
	$('#subsDiv').hide();
	$('#report').hide();
	
	$('#txnFromDate').datepicker({
		changeMonth : true,
		changeYear : true,
	});
	
	$('#txnToDate').datepicker({
		changeMonth : true,
		changeYear : true,
	});
	
	  $(document).on('click','#txnLogButton',function() {
		  
		if($("#radiodate").is(':checked')){
    	    if($("#txnFromDate").val()=='' || $("#txnToDate").val()== ''){
		       alert("Please Select The Date Range");
		       return false;
    	     } else {
    	    	 var fromDate=$("#txnFromDate").val();
   				 var toDate=$("#txnToDate").val();
   				 var radio="dateRange";
   				 $("#downloadFromDate").val(fromDate);
   				 $("#downloadToDate").val(toDate);
   				 $("#downloadRadio").val(radio);
    	     }
    	
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
		$('#subsDiv').show();
		$('#report').show();
		
		var url = "getTransactionLogPagination?date="+fromDate+"&date1="+toDate+"&radio="+radio;
		$("#subsDtls").dataTable({
			"bProcessing" : false,
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
												{"mData" : "onuSlno"},
												{"mData" : "stbSlno"},
												{"mData" : "iptvPack"},
												{"mData" : "transaction"},
												{"mData" : "txnDate"},
												{"mData" : "status"}
												
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
				<h2>Subscribers Details Report</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
			    </div>
			<!-- Service Wise Subscribers Report   -->
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Date Range<span style="color: red;">*</span></label>
								<div class="clear"></div>
								<div class="pull-left" style="padding-left: 0;">
									<input type="radio" name="radio" id="radiodate" class="form-white" value="dateRange">&nbsp;&nbsp;Date Range
								</div>
								<div class="col-sm-6">
									<input type="radio" name="radio" id="tillDate" class="form-white" value="tillDate">&nbsp;&nbsp;Till Date
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<div style="display: none" id="daterange">
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">From Date</label> 
									<input type="text" name="date" id="txnFromDate" class="date-picker form-control form-white" placeholder="Select From Date">
								</div>
							</div>
			
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">To Date</label> 
									<input type="text" name="date1" id="txnToDate"  class="date-picker form-control form-white" placeholder="Select To Date">
								</div>
							</div>
						</div>
			
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label hide-mobile">&nbsp;</label>
								<div class="option-group">
									<button class="btn btn-embossed btn-primary" type="submit" id="txnLogButton">
										<i class="fa fa-search"></i>Search
									</button>
								</div>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div id="messageDiv" style="float: right;">
						<font >${message}</font>
					</div>
					<div id="subsDiv">
					<table class="table table-alt"  id="subsDtls">
						<thead>
							<tr>
								<th>Caf No</th>
								<th>AAdhar No</th>
								<th>STB MAC Addr</th>
								<th>ONU Sl No</th>
								<th>STB Sl No</th>
								<th>IPTV Package</th>
								<th>Transaction</th>
								<th>Transaction Date</th>
								<th>Status</th>
							</tr>
						</thead>
					</table>
					</div>
						<form name="report" id="report" method="get" action="./downloadTransactionLog">
							<input type="hidden" name="download" value="true">
							<input type="hidden" name="date" id="downloadFromDate">
							<input type="hidden" name="date1" id="downloadToDate">
							<input type="hidden" name="radio" id="downloadRadio">
							<button class="btn btn-embossed btn-primary" type="submit">Download</button>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>    
	</div>
</section>
</body>	