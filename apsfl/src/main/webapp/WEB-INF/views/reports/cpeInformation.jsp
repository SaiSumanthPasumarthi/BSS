<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
table.dataTable tbody td {
    word-break: break-word;
    vertical-align: top;
}
a:hover {
	        cursor: pointer;
	    }
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var status="";
		var cpetype="";
		var radio = "${radio}";
		if (radio == "dateRange") {
			//$("#radiodate").click();
			$("#radiodate").prop("checked", true);
			$("#daterange").show();
		} else if (radio == "tillDate") {
			//$("#tillDate").click();
			$("#tillDate").prop("checked", true);
		}
		
	    
		$("#radiodate").click (function() {
			$("#daterange").show();
	    });
		        
	    $("#tillDate").click (function() {
			$("#daterange").hide();
			$("#effectiveFrom_coms,#effectiveTo_coms").val("");
	    });


	    $("#IPTVactive").click (function() {
			status="active";
			cpetype="IPTV";
			showRecord(cpetype,status);
				
			
	    });
	    $("#IPTVnotactive").click (function() {
			status="notactive";
			cpetype="IPTV";
			showRecord(cpetype,status);
	    });

	    $("#IPTVdefective").click (function() {
			status="defective";
			cpetype="IPTV";
			showRecord(cpetype,status);	
	    });

	    $("#ONUactive").click (function() {
			status="active";
			cpetype="ONU";
			showRecord(cpetype,status);
			
	    });

	    $("#ONUnotactive").click (function() {
			status="notactive";
			cpetype="ONU";
			showRecord(cpetype,status);
			
	    });

	    $("#ONUdefective").click (function() {
			status="defective";
			cpetype="ONU";
			showRecord(cpetype,status);
			
	    });

	    $(document).on('click','#searchcpebutton',function() {
	    	
	    	if(!($("#radiodate")).is(':checked') && !($("#tillDate")).is(':checked')){
				  alert("Please Select DateRange or TillDate");
				  return false;
			  }
	    	
	    	if($("#radiodate").is(':checked')){
	    	    if($("#effectiveFrom_coms").val()=='' || $("#effectiveTO_coms").val()== ''){
			       alert("Please Select The Date Range");
			       
			       return false;
			      
	    	     }
	    	
		    }
	    });
	
		
});


	
	function showRecord(cpetype,status) {	
		var district=$("#cpedistrict").val();
		var radio = "${radio}";
		if (radio == "dateRange") {
			var fromDate = $("#effectiveFrom_coms").val();
			var toDate = $("#effectiveTo_coms").val();
			//window.location.href = "<c:url value="/getStatusWiseSubDetails"/>?status="+ status+ "&date1="+ fromDate+ "&date2="+ toDate+ "&cpetype=" + cpetype;
			window.location.href = "getStatusWiseCpeDetails?status="+ status+ "&date1="+ fromDate+ "&date2="+ toDate+ "&cpetype=" + cpetype + "&districtuid="+district;
		} else if (radio == "tillDate") {
			var fromDate = $("#fromDate1").val();
			var toDate = $("#toDate1").val();
			
			
			//window.location.href = "<c:url value="/getStatusWiseSubDetails"/>?status="+ status+ "&date1="+ fromDate+ "&date2="+ toDate+ "&cpetype=" + cpetype;
			window.location.href = "getStatusWiseCpeDetails?status="+ status+ "&date1="+ fromDate+ "&date2="+ toDate+ "&cpetype=" + cpetype + "&districtuid="+district;
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
				<h2>Cpe Report</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
			    </div>
			<!-- cpe Information-->
								<div class="row main-row">
												<div class="col-lg-12">
													<div class="panel main-panel">
														<div class="panel-content main-panel-content">
															<form name="cpereport" id="cpereport" method="get" action="./cpeInformation">
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
																				<input type="text" name="date1" id="effectiveFrom_coms" value="<c:if test="${not empty date1}">${date1}</c:if>" class="form-control form-white" placeholder="Select From Date">
																			</div>
																		</div>

																		<div class="col-sm-3">
																			<div class="form-group">
																				<label class="control-label">To Date</label> 
																				<input type="text" name="date2" id="effectiveTo_coms" value="<c:if test="${not empty date2}">${date2}</c:if>" class="form-control form-white" placeholder="Select To Date">
																			</div>
																		</div>
																	</div>
																	
																	
																							
										<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">District</label>
											<div class="option-group">
												<select name="districtuid" id="cpedistrict"
													class="form-control form-white">
													<option value="">All</option>

													<c:forEach var="district" items="${districtList}">
														<c:choose>
															<c:when
																test="${not empty districtuid && district.districtUid == districtuid}">
																<option value="${district.districtUid}" selected>${district.districtName}</option>
															</c:when>
															<c:otherwise>
																<option value="${district.districtUid}">${district.districtName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select>
											</div>
										</div>
										</div>
										
																	
																	
																	
																	
													<div class="clear"></div>				
																	
																	
																	<div  class="col-sm-3">
																		<div class="form-group">
																			<label class="control-label hide-mobile">&nbsp;</label>
																			<div  class="option-group">
																				<button  class="btn btn-embossed btn-primary" type="submit" id="searchcpebutton">
																					<i  class="fa fa-search"></i>Search
																				</button>
																			</div>
																		</div>
																	</div>
																	<div class="clear"></div>
																

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
														<c:if test="${not empty radio}">

																	<table class="table table-alt dataTab" id="cpeCountList">
																		<thead>
																			<tr class="titleRow">
																				<th>Status</th>
																				<th>Cpe Count</th>
																				
																			</tr>
																		</thead>
																		<tbody>
																			
																				<tr>
																					<td>IPTV CAFs done</td>
																					
																					<td class="" ><a id="IPTVactive" >${cpeDetails.iptvActive}</a></td>
																				</tr>
																				
																				<tr>
																					<td>IPTV CAFs not done</td>
																					<td class="" ><a id="IPTVnotactive" >${cpeDetails.iptvNotActive}</a></td>
																				</tr>
																				<tr>
																					<td>IPTV Defective Devices</td>
																					<td class="" ><a id="IPTVdefective" >${cpeDetails.iptvDefective}</a></td>
																				</tr>
																				<tr>
																					<td>ONU CAFs done</td>
																					<td class="" ><a id="ONUactive" >${cpeDetails.onuActive}</a></td>
																				</tr>
																				<tr>
																					<td>ONU CAFs not done</td>
																					<td class="" ><a id="ONUnotactive" >${cpeDetails.onuNotActive}</a></td>
																				</tr>
																				<tr>
																					<td>ONU Defective Devices</td>
																					<td class=""  ><a id="ONUdefective">${cpeDetails.onuDefective}</a></td>
																				</tr>
																			
																			<tr class="totalColumn">
																				<td style="font-weight: bold" class="aCenter">Total</td>
																				<td style="font-weight: bold">${cpeDetails.total}</td>
																				<td style="display: none;">abc</td>
																			</tr>
																		</tbody>
																	</table>
																	<form name="report" id="report" method="get" action="./downloadCpeInfo">
																		<input type="hidden" name="download" value="true">
																		<input type="hidden" name="date1" value="${date1}">
																		<input type="hidden" name="date2" value="${date2}">
																		<input type="hidden" name="radio" value="${radio}">
																		<input type="hidden" name="status" value="${status}">
																		<input type="hidden" id="fromDate1" name="fromDate1" value="${fromDate1}"> 
																		<input type="hidden" id="toDate1" name="toDate1" value="${toDate1}">
																		<button type="submit" class="btn btn-embossed btn-primary">Download</button>
																	</form>
																
															</c:if>
														</div>
													</div>
												</div>
											</div>
		</div>    
	</div>
</section>
</body>	