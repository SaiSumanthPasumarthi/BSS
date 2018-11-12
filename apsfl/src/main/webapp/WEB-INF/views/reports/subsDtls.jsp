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
		$("#subsFromDate,#subsToDate").val("");
    });
    
    
	$('#subsDiv').hide();
	$('#report').hide();
	
	$('#subsFromDate').datepicker({
		changeMonth : true,
		changeYear : true,
	});
	
	$('#subsToDate').datepicker({
		changeMonth : true,
		changeYear : true,
	});
	
	  $(document).on('click','#subsDtlsButton',function() {
		  
		  if(!($("#radiodate")).is(':checked') && !($("#tillDate")).is(':checked')){
			  alert("Please Select DateRange or TillDate");
			  return false;
		  }
		  
		if($("#radiodate").is(':checked')){
    	    if($("#subsFromDate").val()=='' || $("#subsToDate").val()== ''){
		       alert("Please Select The Date Range");
		       return false;
    	     } else {
    	    	 var fromDate=$("#subsFromDate").val();
   				 var toDate=$("#subsToDate").val();
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
		
		var url = "getSubscriberDetailsPagination?date="+fromDate+"&date1="+toDate+"&radio="+radio;
		$("#subsDtls").dataTable({
			"bProcessing" : false,
			"bServerSide" : true,
			"sort" : "position",
			"sAjaxSource" : url,
			"bDestroy": true,
			"scrollX" : true,
			"aoColumnDefs": [
		                      { "sWidth": "10%", "aTargets": [ 0 ] },
		                      { "sWidth": "10%", "aTargets": [ 1 ] },
		                      { "sWidth": "10%", "aTargets": [ 2 ] },
		                      { "sWidth": "10%", "aTargets": [ 3 ] },
		                      { "sWidth": "10%", "aTargets": [ 4 ] },
		                      { "sWidth": "10%", "aTargets": [ 5 ] },
		                      { "sWidth": "10%", "aTargets": [ 6 ] },
		                      { "sWidth": "10%", "aTargets": [ 7 ] },
		                      { "sWidth": "10%", "aTargets": [ 8 ] },
		                      { "sWidth": "10%", "aTargets": [ 9 ] },
		                      { "sWidth": "10%", "aTargets": [ 10 ] },
		                      { "sWidth": "10%", "aTargets": [ 11 ] },
		                      { "sWidth": "10%", "aTargets": [ 12 ] },
		                      { "sWidth": "10%", "aTargets": [ 13 ] },
		                      { "sWidth": "10%", "aTargets": [ 14 ] },
		                      { "sWidth": "10%", "aTargets": [ 15 ] },
		                      { "sWidth": "10%", "aTargets": [ 16 ] },
		                      { "sWidth": "10%", "aTargets": [ 17 ] },
		                      { "sWidth": "10%", "aTargets": [ 18 ] },
		                      { "sWidth": "10%", "aTargets": [ 19 ] },
		                      { "sWidth": "10%", "aTargets": [ 20 ] },
		                      { "sWidth": "10%", "aTargets": [ 21 ] },
		                      { "sWidth": "10%", "aTargets": [ 22 ] },
		                      { "sWidth": "10%", "aTargets": [ 23 ] , orderable: false},
		                      
		                     ], 
		                     
		                     "aoColumns" : [
		                                    
												{"mData" : "stbMacId"},
												{"mData" : "cafNo"},
												{"mData" : "packName"},
												{"mData" : "subsCode"},
												{"mData" : "actDate"},
												{"mData" : "deactDate"},
												{"mData" : "suspDate"},
												{"mData" : "resumeDate"},
												{"mData" : "blacklistDate"},
												{"mData" : "iptvPackName"},
												{"mData" : "custId"},
												{"mData" : "aadharNo"},
												{"mData" : "onuMacId"},
												{"mData" : "instAddr1"},
												{"mData" : "instAddr2"},
												{"mData" : "instArea"},
												{"mData" : "instCity"},
												{"mData" : "instLocality"},
												{"mData" : "instMandal"},
												{"mData" : "instDist"},
												{"mData" : "instPin"},
												{"mData" : "latitude"},
												{"mData" : "longitude"},
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
									<input type="text" name="date" id="subsFromDate" class="date-picker form-control form-white" placeholder="Select From Date">
								</div>
							</div>
			
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">To Date</label> 
									<input type="text" name="date1" id="subsToDate"  class="date-picker form-control form-white" placeholder="Select To Date">
								</div>
							</div>
						</div>
			
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label hide-mobile">&nbsp;</label>
								<div class="option-group">
									<button class="btn btn-embossed btn-primary" type="submit" id="subsDtlsButton">
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
								<th>STB MAC-ID</th>
								<th>CAF No</th>
								<th>Package</th>
								<th>Subscriber Code</th>
								<th>Activation Date</th>
								<th>Deactivation Date</th>
								<th>Suspended Date</th>
								<th>Resume Date</th>
								<th>Blacklist Date</th>
								<th>IPTV Package Name</th>
								<th>Customer ID</th>
								<th>Aadhar No</th>
								<th>ONU MAC-ID</th>
								<th>Installation Address 1</th>
								<th>Installation Address 2</th>
								<th>Installation Area</th>
								<th>Installation City/Village</th>
								<th>Installation Locality</th>
								<th>Installation Mandal</th>
								<th>Installation District</th>
								<th>Installation Pin</th>
								<th>Latitude</th>
								<th>Longitude</th>
								<th>Status</th>
							</tr>
						</thead>
					</table>
					</div>
						<form name="report" id="report" method="get" action="./downloadSubscriberDetails">
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