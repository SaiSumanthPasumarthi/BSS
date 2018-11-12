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
		
		var radio = "${radio}";
		if (radio == "dateRange") {
			$("#radiodate").click();
		} else if (radio == "tillDate") {
			$("#tillDate").click();
		}
		
	    
		$("#radiodate").click (function() {
			$("#daterange").show();
	    });
		        
	    $("#tillDate").click (function() {
			$("#daterange").hide();
			$("#effectiveFrom_coms,#effectiveTo_coms").val("");
	    });
	    
	    $(document).on('click','#reportSearchButton2',function() {
	    	
	    	if(!($("#radiodate")).is(':checked') && !($("#tillDate")).is(':checked')){
				  alert("Please Select DateRange or TillDate");
				  return false;
			  }
	    	
	    	if($("#radiodate").is(':checked')){
	    	    if($("#effectiveFrom_coms").val()=='' || $("#effectiveTO_coms").val()== ''){
			       alert("Please Select The Date Range");
			       return falsle;
	    	     }
	    	
		    }
	    });
	
		
});
	
	function showRecord(status) {

		var radio = "${radio}";
		if (radio == "dateRange") {
			var fromDate = $("#effectiveFrom_coms").val();
			var toDate = $("#effectiveTo_coms").val();
			window.location.href = "<c:url value="/getStatusWiseSubDetails"/>?status="+ status+ "&date1="+ fromDate+ "&date2="+ toDate+ "&radio=" + radio;
		} else if (radio == "tillDate") {
			var fromDate = $("#fromDate1").val();
			var toDate = $("#toDate1").val();
			window.location.href = "<c:url value="/getStatusWiseSubDetails"/>?status="+ status+ "&date1="+ fromDate+ "&date2="+ toDate+ "&radio=" + radio;
		}
	}
</script>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
				<h2>Total Number of Subscribers Report</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
			    </div>
			<!-- Total number of subscribers Report -->
								<div class="row main-row">
												<div class="col-lg-12">
													<div class="panel main-panel">
														<div class="panel-content main-panel-content">
															<form name="report" id="report" method="get" action="./getTotalNoOfSubscriber">
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
																				<input type="text" name="date1" id="effectiveFrom_coms" value="<c:if test="${not empty date1}">${date1}</c:if>" class="date-picker form-control form-white" placeholder="Select From Date">
																			</div>
																		</div>

																		<div class="col-sm-3">
																			<div class="form-group">
																				<label class="control-label">To Date</label> 
																				<input type="text" name="date2" id="effectiveTo_coms" value="<c:if test="${not empty date2}">${date2}</c:if>" class="date-picker form-control form-white" placeholder="Select To Date">
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label class="control-label hide-mobile">&nbsp;</label>
																			<div class="option-group">
																				<button class="btn btn-embossed btn-primary" type="submit" id="reportSearchButton2">
																					<i class="fa fa-search"></i>Search
																				</button>
																			</div>
																		</div>
																	</div>
																	<div class="clear"></div>
																</div>

															</form>
															<!-- END ROW INNER-->

															<c:if test="${not empty message}">
																<center>
																	<font color='red' size="3">${message}</font>
																</center>
															</c:if>
															<c:if test="${not empty message1}">
																<font style="float: right;" color='green' size="3">${message1}</font>
															</c:if>
															<c:choose>
																<c:when test="${not empty list}">
																	<table class="table table-alt dataTab" id="subscibersCountList">
																		<thead>
																			<tr class="titleRow">
																				<th>Status</th>
																				<th>Total Subscribers Count</th>
																				<th style="display: none;">abc</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:forEach items="${list}" var="obj">
																				<tr>
																					<td>${obj[1]}</td>
																					<td class="rowData" style="display: none;">${obj[2]}</td>
																					<td class=""><a href="javascript:showRecord(${obj[0]});">${obj[2]}</a></td>
																				</tr>
																			</c:forEach>
																			<tr class="totalColumn">
																				<td style="font-weight: bold" class="aCenter">Total</td>
																				<td class="totalCol" style="font-weight: bold"></td>
																				<td style="display: none;">abc</td>
																			</tr>
																		</tbody>
																	</table>
																	<form name="report" id="report" method="get" action="./downloadTotalNoOfSubscriber">
																		<input type="hidden" name="download" value="true">
																		<input type="hidden" name="date1" value="${date1}">
																		<input type="hidden" name="date2" value="${date2}">
																		<input type="hidden" name="radio" value="${radio}">
																		<input type="hidden" name="status" value="${status}">
																		<input type="hidden" id="fromDate1" name="fromDate1" value="${fromDate1}"> 
																		<input type="hidden" id="toDate1" name="toDate1" value="${toDate1}">
																		<button type="submit" class="btn btn-embossed btn-primary">Download</button>
																	</form>
																</c:when>
															</c:choose>
														</div>
													</div>
												</div>
											</div>
		</div>    
	</div>
</section>
</body>	