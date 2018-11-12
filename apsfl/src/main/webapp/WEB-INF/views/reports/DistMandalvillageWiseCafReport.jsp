<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>




<script type="text/javascript" >



$(document).ready(function() {
	
	$('#districtmandalandvillageWiseCafcountTable').dataTable();
	
	$("#effectivefrom").datepicker({
		dateFormat: 'yy-mm-dd',
		numberOfMonths: 1,
	    changeMonth: true,
	    changeYear: true,
	    onSelect: function (selected) {
	        var dt = new Date(selected);
	        dt.setDate(dt.getDate() + 1);
	    }
	});
	$("#effectiveto").datepicker({
		dateFormat: 'yy-mm-dd',
		numberOfMonths: 1,
	    changeMonth: true,
	    changeYear: true,
	    onSelect: function (selected) {
	        var dt = new Date(selected);
	        dt.setDate(dt.getDate() - 1);
	      
	    }
	});
	
	 $(document.body).on('change','#district_Id',function(){ 
		 
		 $("#mandalListid").hide();
		 
		 var districtUid = $("#district_Id option:selected").val();
		
		 $("#mandal-Div-Id").html("");
		var html = '<div class="form-group">' 
			+'<label class="control-label"> Select Mandal <span style="color: red;">*</span></label>';
			
					
									
		 $.ajax({ 
		     type: 'GET', 
		     async:false,
		     url: 'getMandalsByDistrictId', 
		     data: 	{districtId : districtUid},
		     success: function(response) { 
		    	 html = html	 +'<div><select class="form-control form-white" name="mandal" id="mandal-Select-Id">'; 
		    	 html = html +'<option value="">-Select-</option>';
		    	 $.each(response, function(key,mandal) { 
		    		 html = html +'<option value='+mandal.mandalSlno+'>'+mandal.mandalName+'</option>';
		    	 });
		    	 html = html + '</select> </div> </div>';
		    	 $("#mandal-Div-Id").show();
		    	 $("#mandal-Div-Id").append(html);
		     }
		     });
		 
	 
	 
	 });


	 $(document.body).on('change','#mandal-Div-Id',function(){ 
	
	 $("#villagelistid").hide();
	var mandalSlno = $("#mandal-Div-Id option:selected").val();
	var districtUid = $("#district_Id option:selected").val();

	
	 $("#village-Div-Id").html("");
	var html = '<div class="form-group">' 
		+'<label class="control-label"> Select Village <span style="color: red;">*</span></label>';
								
	 $.ajax({ 
	     type: 'GET', 
	     async:false,
	     url: 'getVillagesByDistrictIdAndMandalId', 
	     data: 	{districtId : districtUid, mandalId : mandalSlno},
	     success: function(response) { 
	    	 html = html	 +'<div><select class="form-control form-white" name="village" id="village-Select-Id">'; 
	    	 html = html +'<option value="">-Select-</option>';
	    	 $.each(response, function(key,village) { 
	    		 html = html +'<option value='+village.villageSlno+'>'+village.villageName+'</option>';
	    	 });
	    	 html = html + '</select> </div> </div>';
	    	 $("#village-Div-Id").show();
	    	 $("#village-Div-Id").append(html);
	     }
	     });
	 });
	 
	 
	 var radio = "${radio}";
		if (radio == "dateRange") {
			$("#radiodate").click();
		} else if (radio == "tillDate") {
			$("#tillDate").click();
		}
		
	    
		$("#radiodate").click (function() {
			$("#daterange").show();
	    });
		        
		
	    $(document).on('click','#searchButtons1',function() {
	    	
	    	var fromDate = $("#effectivefrom").val();
			var toDate = $("#effectiveto").val();
			var district1 = $("#district_Id").val();
			var mandal1 = $("#mandal-Select-Id").val();
			var village1 = $("#village-Select-Id").val();
	    	
	    	if(!($("#radiodate")).is(':checked') && !($("#tillDate")).is(':checked')){
				  alert("Please Select DateRange or TillDate");
				  return false;
			  }
	    	
	    	if($("#radiodate").is(':checked')){
	    	    if($("#effectivefrom").val()=='' || $("#effectiveto").val()== ''){
			       alert("Please Select The Date Range");
			      
			       return false;
	    	     }else{
	    	    	 if(district1 === undefined || district1 === ""){
	    	    		district1=""
	    	    	 }
	    	    	 if(mandal1 === undefined || mandal1 === ""){
	    	    		 mandal1="";
		    	    	 }
	    	    	 if(village1 === undefined || village1 === ""){
	    	    		
	    	    		 village1=""
		    	    	 }
	    	    	 window.location.href = "<c:url value="/distMandalvillageWiseCafCount"/>?effectivefrom="+ fromDate+ "&effectiveto="+ toDate+ "&district="+ district1+ "&mandal="+ mandal1+ "&village=" + village1;
	    	    	 
	    	     }
	    	    
	    	
		    }
	    	
	    	if($("#tillDate").is(':checked')){   
	    	    
	    	    	window.location.href = "<c:url value="/distMandalvillageWiseCafCountTilldate"/>";   	     
	    	
		    }
	    });


});
</script>


<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title" style="margin-left: 50px">
					<h2>District, Mandal and Village Wise CAF Report</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				<div class="row main-row" style="margin-left: 45px">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row" align="center">
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="panel main-panel">
													<div class="panel-content main-panel-content">
														<div class="panel panel-default">
															<div class="panel-body">
																<form name="distMandalvillageWiseCafCount" id="distMandalvillageWiseCafCount" method="POST" novalidate>
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
																		<div class="col-lg-12">
																			<div class="col-sm-3">

																				<div class="form-group">
																					<label class="form-label">From<span
																						style="color: red;">*</span></label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectivefrom" id="effectivefrom" readonly value="${hiddenfromdate}"/>
																				</div>
																				

																			</div>
																			
																			
																			<div class="col-sm-3">
																			
																				<div class="form-group">
																					<label class="form-label">To<span
																						style="color: red;">*</span></label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectiveto" id="effectiveto" readonly value="${hiddentodate}"/>
																				</div>
																			</div>
																			
																			
															<div class="col-sm-12">
																			
														<div class="form-group col-sm-3" id="districtListid">
															<label class="control-label">Select District </label>
															
																<select name="district" id="district_Id" class="form-control form-white" required="required">
																	<option value="">--Select--</option>
 																	<c:forEach var="districtVar" items="${districtList}">
																		<option value="${districtVar.districtUid}">${districtVar.districtName}</option>
																	</c:forEach>
																</select>
															
														</div>
														<div class="form-group col-sm-3" id="mandalListid">
															<label class="control-label">Select Mandal </label>
															<div>
																<select  id="mandal-Id" class="form-control form-white" required="required">
																	<option value="">--Select--</option>
																	
																</select>
															</div>
														</div>
													<div style="display:none" class="col-sm-3" id="mandal-Div-Id"></div>
													
													<div class="form-group col-sm-3" id="villagelistid">
															<label class="control-label">Select Village </label>
															<div>
																<select  id="village-Id" class="form-control form-white" required="required">
																<option value="">--Select--</option>
																	
																</select>
															</div>
														</div>
                                                      <div class="col-sm-3" id="village-Div-Id"></div>  
                                                      </div>        
													
													</div>					

															</div>				

																			<input type="hidden" name="hiddentodate"
																				id="hiddentodate" value=""> 
																				<input
																				type="hidden" name="hiddenfromdate"
																				id="hiddenfromdate" value="">
                                                     

																			<div class="col-sm-3">
																				<div class="form-group">
																					<label class="control-label"></label>
																				</div>
																				<div class="form-group">
																					<button class="btn btn-embossed btn-primary"
																						type="button" id="searchButtons1">
																						<i class="fa fa-search"></i>Search
																					</button>
																				</div>
																			</div>
																		
																	</div>
															</form>
															</div>

														</div>
														<div class="row">

															<table class="table table-alt" id="districtmandalandvillageWiseCafcountTable">
																<thead>
																	<tr class="titleRow"
																		style="background-color: rgb(242, 242, 242);">
																		<th>S.no</th>
																		<th>District</th>
																		<th>Mandal</th>
																		<th>Village</th>
																		<th>Date</th>
																		<th>Total Cafs</th>
																	</tr>


																</thead>
																<tbody>
																	<c:forEach items="${districtWiseCafList}"
																		var="districtWiseCafList" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td>
																			
																			<td><c:out
																					value="${districtWiseCafList.district}"></c:out></td>
																			<td><c:out value="${districtWiseCafList.mandal}"></c:out></td>
																			<td><c:out
																					value="${districtWiseCafList.village}"></c:out></td>
																			<td><c:out
																					value="${districtWiseCafList.createddate}"></c:out></td>
																			<td><a
																				href="
																					<c:url value="districtMandalVillageCustomerCafReport">
																						 <c:param name="effectivefrom" value="${effectivefrom}"/>
																						 <c:param name="effectiveto" value="${effectiveto}"/>
																						 <c:param name="cafDate" value="${districtWiseCafList.createddate}"/>
																						 <c:param name="district" value="${districthidden}"/>
																						 <c:param name="mandal" value="${mandalhidden}"/>
																						 <c:param name="village" value="${villagehidden}"/> 
																						 <c:param name="districtName" value="${districtWiseCafList.district}"/>
																						 <c:param name="mandalName" value="${districtWiseCafList.mandal}"/>
																						 <c:param name="villageName" value="${districtWiseCafList.village}"/>	 
																					</c:url>">
																					<c:out value="${districtWiseCafList.cafCount}"></c:out>
																			</a></td>
																		</tr>
																	</c:forEach>
																</tbody>

															</table>
															<div class="form-group">
																<a
																	href="
																					<c:url value="./downloaddistMandalvillageWiseCafCount">
																						 <c:param name="effectivefrom" value="${effectivefrom}"/>
																						 <c:param name="effectiveto" value="${effectiveto}"/>	
																						 <c:param name="district" value="${districthidden}"/> 
																						 <c:param name="mandal" value="${mandalhidden}"/>
																						 <c:param name="village" value="${villagehidden}"/> 
																					</c:url>">
																	<INPUT TYPE="SUBMIT" VALUE="Download"
																	class="btn btn-success">
																</a>

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
			</div>
		</div>
	</section>
