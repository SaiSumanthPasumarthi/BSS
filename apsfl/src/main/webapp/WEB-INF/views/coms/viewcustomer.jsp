<%@page import="org.omg.CORBA.DATA_CONVERSION"%>
<%@page import="com.arbiva.apsfl.coms.dto.LmoDetailsBO"%>
<%@page import="com.arbiva.apsfl.coms.dto.ComsHelperDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
table.dataTable tbody td {
	word-break: break-word;
	vertical-align: top;
}
.hiddenField {
	display: none;
}
.modal-dialog {
	width: 1200px;
	margin: 30px auto;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
			var tenantType = $('#tenantType').val();
			if(tenantType == 'APSFL')
				{
				$('#searchdiv').show();						
				$(".cafsDownload1").hide();
				$("#btn").hide();
				$('#multidiv').hide();
				$('#search1div').hide();
				$('#nodatadiv').hide();	
				$('#cafdatadiv').hide();	
				
										
				var dataTable;
				var url = "viewcustomersPagination?"
				var oTable = $('#cafDataTables').dataTable(dataTable);
				$('#searchButtons').click(function() {
					$('#cafdatadiv').show();
					$('#nodatadiv').hide();	
					$("#btn").hide();					
				$('#error').empty();
				$(".cafsDownload1").show();
				$("#btn").hide();
					var lmoCode = $('#lmoCode').val();
					var msoCode = $('#msoCode').val();
					var district = $('#popDistrict').val() == undefined ? "" : $('#popDistrict').val();	
					url = url+$('form').serialize(); 
				    oTable.fnDestroy();    
					if (lmoCode != "") {
						ShowAjaxMultiData1();
					} else if (district != "") {
						ShowAjaxMultiData1();
					} else if (msoCode != "") {
						ShowAjaxMultiData1();
					} else if (msoCode == "" && district == "--Select--" && lmoCode == "") {
						$('#error').text("Please Select District OR any one of the Field .");
						
					} else {
						$('#error').text("Please Select District OR any one of the Field .");
					}

				    oTable = $('#cafDataTables').dataTable(dataTable);
				    url ="viewcustomersPagination?";
				}); //End of click 
// 				dataTable= $("#cafDataTables").dataTable();		
				}
			else if(tenantType == 'LMO')
				{
				$('#searchdiv').hide();
				$('#search1div').show();					
				$('#cafdatadiv').hide();	
				$('#nodatadiv').hide();	
							
// 				var url = "viewcustomersPagination?"
// 				ShowAjaxMultiData1();	
				$(".cafsDownload1").hide();
				$("#btn").hide();
				ShowAjaxMultiData();
					var hiddenYear = $('#hiddenSelectYear').val();
					var min_year = new Date().getFullYear();
					min_year = 2016;
					max_year = min_year + 10;
					var div_data = "";
					for (var i = min_year; i<=max_year; i++){
						if(i == hiddenYear){
							 div_data = div_data + "<option selected value=" + i+ ">" + i + "</option>";
						}else{
							 div_data = div_data + "<option  value=" + i+ ">" + i + "</option>";
						}
					}
					$(div_data).appendTo('#yearListId');
					var dataTable;
					var url = "viewcustomersPagination?"
					var oTable = $('#cafDataTables').dataTable(dataTable);
						
					$('#searchButtons1').click(function() {
						
							$('#cafdatadiv').show();
							$('#nodatadiv').hide();	
							$('#error').empty();
						$(".cafsDownload1").hide();
						$("#btn").hide();
						var month = $('#monthsListId').val() == undefined ? "" : $('#monthsListId').val();
						var year = $('#yearListId').val() == undefined ? "" : $('#yearListId').val();	
						url = url+$('form').serialize(); 
						
					    oTable.fnDestroy(); 
					       
						if (month != "") {
							ShowAjaxMultiData1();
						} else if (year != "") {
							ShowAjaxMultiData1();
						} else if (month ==  "--Select--" && year == "--Select--") {
							$('#error').text("Please Select District OR any one of the Field .");
							
						} else {
							$('#error').text("Please Select District OR any one of the Field .");
						}

					    oTable = $('#cafDataTables').dataTable(dataTable);
					    url ="viewcustomersPagination?";
					}); //End of click 
						
				}
			
			else if(tenantType == 'MSP'){
			$('#searchdiv').hide();
			$('#search1div').show();					
			$('#cafdatadiv').hide();	
			$('#nodatadiv').hide();	
						
//				var url = "viewcustomersPagination?"
//				ShowAjaxMultiData1();	
			$(".cafsDownload1").hide();
			$("#btn").hide();
			ShowAjaxMultiData();
				var hiddenYear = $('#hiddenSelectYear').val();
				var min_year = new Date().getFullYear();
				min_year = 2016;
				max_year = min_year + 10;
				var div_data = "";
				for (var i = min_year; i<=max_year; i++){
					if(i == hiddenYear){
						 div_data = div_data + "<option selected value=" + i+ ">" + i + "</option>";
					}else{
						 div_data = div_data + "<option  value=" + i+ ">" + i + "</option>";
					}
				}
				$(div_data).appendTo('#yearListId');
				var dataTable;
				var url = "viewcustomersPagination?"
				var oTable = $('#cafDataTables').dataTable(dataTable);
					
				$('#searchButtons1').click(function() {
					
						$('#cafdatadiv').show();
						$('#nodatadiv').hide();	
						$('#error').empty();
					$(".cafsDownload1").hide();
					$("#btn").hide();
					var month = $('#monthsListId').val() == undefined ? "" : $('#monthsListId').val();
					var year = $('#yearListId').val() == undefined ? "" : $('#yearListId').val();	
					url = url+$('form').serialize(); 
					
				    oTable.fnDestroy(); 
				       
					if (month != "") {
						ShowAjaxMultiData1();
					} else if (year != "") {
						ShowAjaxMultiData1();
					} else if (month ==  "--Select--" && year == "--Select--") {
						$('#error').text("Please Select District OR any one of the Field .");
						
					} else {
						$('#error').text("Please Select District OR any one of the Field .");
					}

				    oTable = $('#cafDataTables').dataTable(dataTable);
				    url ="viewcustomersPagination?";
				}); //End of click 
					
			}
			
			else
				{
				$('#searchdiv').hide();
				$('#search1div').show();					
				$('#cafdatadiv').show();	
				$('#nodatadiv').hide();	
				$('#multidiv').hide();	
							
				var url = "viewcustomersPagination?"
		ShowAjaxMultiData1();	
			}
			function ShowAjaxMultiData1() {
				$("#cafDataTables")
						.dataTable(
								{
									"bProcessing" : false,
									"bServerSide" : true,
									"sort" : "position",
									"sAjaxSource" : url,
									"aoColumnDefs" : [ {
										"sWidth" : "8%",
										"aTargets" : [ 0 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "15%",
										"aTargets" : [ 1 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "10%",
										"aTargets" : [ 2 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "8%",
										"aTargets" : [ 3 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "10%",
										"aTargets" : [ 4 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "8%",
										"aTargets" : [ 5 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "8%",
										"aTargets" : [ 6 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "8%",
										"aTargets" : [ 7 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "8%",
										"aTargets" : [ 8 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "8%",
										"aTargets" : [ 9 ],
										"sClass" : "table-bordered"
									}, {
										"sWidth" : "10%",
										"sClass" : "table-bordered",
										"aTargets" : [ 10 ],
										orderable : false
									}, ],
									"aoColumns" : [
											{
												"mData" : "cafNo"
											},
											{
												"mData" : "",
												"render" : function(
														data, type,
														full, meta) {
													return "<td class='customerName' style='word-break:break-all;'>"
															+ full.custName
															+ " "
															+ full.lName
															+ "</td>";
												}
											},
											{
												"mData" : "aadharNo"
											},
											{
												"mData" : "apsflUniqueId"
											},
											{
												"mData" : "cpeSrlNo"
											},
											{
												"mData" : "actdate"
											},
											{
												"mData" : "contactMob"
											},
											{
												"mData" : "depBal"
											},
											{
												"mData" : "regBal"
											},
											{
												"mData" : "status"
											},
											{
												"mData" : "",
												"render" : function(
														data, type,
														full, meta) {

													var tdData = ' <td align="center" style="word-break:break-all; float: left; padding-left: 10px;"> '
													tdData += '<form style="float: left; padding-left: 10px;">';
													tdData += '<input type = "hidden" class ="custmerCafNo" value="'+full.cafNo+'" />';
													tdData += '<input type="hidden" class = "custId" value="'+full.custId+'" />';
													tdData += '<span style="cursor: pointer;" class="viewcafDetails" data-toggle="modal" data-target="#myModal" title="View CAF information"><img src="./resources/images/apf_view.png"></span>';
													tdData += '</form>';

													if (tenantType != 'APSFL'
															&& tenantType != 'Call Center') {
														tdData += '<form style="float: left; padding-left: 5px;" name="editCustomerForm" action="#" class="editCustomerForm" method="post">';
														tdData += '<input type="hidden" name="custId" value="'+full.custId+'" />';
														tdData += '<span class="editCustomer" style="cursor: pointer;" title="Edit Customer Details"><img src="./resources/images/apf_edit.png"></span>';
														tdData += '</form>';
													}
													tdData += '</td>';
													return tdData;
												}											} 
											],
										       "fnDrawCallback" : function(oSettings) {
													 if ( oSettings.aiDisplay.length > 0 && $('#cafdatadiv').is(':visible')) {
														var tenantType = $('#tenantType').val();
										            	$(".cafsDownload1").show();
										            	if(tenantType=='LMO')
											            	{
															$("#btn").show();
											            	
											            	}
										            	else
											            	{
															$("#btn").hide();
											            	
											            	}
										            }
											            
													 else
														 {																 
															$('#cafdatadiv').hide();
															$('#nodatadiv').show();	
											            	$(".cafsDownload1").hide();
															$("#btn").hide();
																													
														 }
													 
										        }							
							});
			}
		});	
	

function ShowAjaxMultiData() {
var tenantType = $('#tenantType').val();
if (tenantType == 'LMO')
	{
	 $('#multidiv').show();
// 	 $(".cafsDownload1").show();
		 
	 $.ajax({
		    contentType: "application/json; charset=utf-8",
		    url: 'getLMOTotalAmountInfo',
		    success: function(data) {
		        console.log(data);
                $(data).each(function(i, data) {
                	$('<tr>').append(
                	        $('<td>').text(data.regBal),
                	        $('<td>').text(data.pmntAmt),
                	        $('<td>').text((data.regBal)-(data.pmntAmt))).appendTo('#multiActionTable'); 
                   
                });
               
		        }
		  });							 
	}
else
	{
	$('#multidiv').hide();
	$(".cafsDownload1").hide();
	$("#btn").hide();
	}
//do some stuff
}

	//new code
		
		
	 $(document).on('click', '.cafsDownload1', function(event){
		$("#multiActionForm1").attr('action',"multiActionCafDownload1");
		$("#multiActionForm1").submit();
	});

			  function sendDownload1()
    	    	{
    		  alert("Please press OK button"); 
				var month = $('#monthsListId').val() == undefined ? "" : $('#monthsListId').val();
				var year = $('#yearListId').val() == undefined ? "" : $('#yearListId').val();	
	  
    		  $.ajax({ type:'POST', 
    		  dataType: 'json', 
    		  data: { "month":month,"year":year},
    		  url :"cafdetailsemail", 
    		  success: function(data) 
    		  { 
        	  alert("Email sent sucessfully");
    		  console.log('success',data);
    		   }, 
    		  error:function(exception)
    		  { 
    		  alert('Exception:'+exception);
    		   } 
    		  });
    		   }
    		  		    		  		
</script>

<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Display<strong> Customers</strong>
			</h2>
			<c:if test="${not empty message}">
				<center id="comsErrorMsg">
					<font color='green' size="3">${message}</font>
				</center>
			</c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>

					<li class="active">Display Customers</li>

				</ol>
			</div>
		</div>
												<div class="row">
													<div class="panel panel-default ">
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form name="multiActionForm1"  id="multiActionForm1" method="post">
						<div class="col-sm-12" id="searchdiv">
						
							<div class="row">
								<div class="col-sm-12">
									<div class="row">
										<div class="col-sm-3">
										</div>
										<div class="clear"></div>
									</div>
									<!-- END ROW INNER-->
									<div class="row">
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">LMO Code</label> 
												<input type="text" name="lmoCode" id="lmoCode" <c:if test="${not empty multiActionVO.lmoCode}"> value="${multiActionVO.lmoCode}" </c:if> class="form-control form-white" placeholder="LMO Code">
											</div>
											</div>
											<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">MSO Code</label> 
												<input type="text" name="msoCode" id="msoCode" <c:if test="${not empty multiActionVO.msoCode}"> value="${multiActionVO.msoCode}" </c:if> class="form-control form-white" placeholder="MSO Code">
											</div>
										</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Distrct</label>
													<div class="option-group">
														<select name="district" id="popDistrict" class="form-control form-white" >
															<option value="">--Select--</option>
															<c:forEach var="district" items="${districtList}">
																<c:choose>
																	<c:when test="${not empty multiActionVO.district && district.districtUid == multiActionVO.district}">
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
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label hide-mobile">&nbsp;</label>
												<div class="option-group">
													<button class="btn btn-embossed btn-primary" type="button" id="searchButtons">
														<i class="fa fa-search"></i>Search
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							</div>
							<div class="col-sm-12" id="search1div">
																					<div class=" panel-body ">
															<div class="panel-group">
																<div class="panel panel-default">
																	<div class="panel-body">
													
																			<div class="row">
																				<div class="col-lg-12">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Month:<span
																								style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">

																							<select id='monthsListId' name="month" class="form-control form-white" required="required">
																								
																								<option selected value=''>--SELECT--</option>
																								<c:choose>
																									<c:when test="${month eq '01'}">
																										<option selected value="01">January</option>
																									</c:when>
																									<c:otherwise>
																										<option value="01">January</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '02'}">
																										<option selected value="02">February</option>
																									</c:when>
																									<c:otherwise>
																										<option value="02">February</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '03'}">
																										<option selected value="03">March</option>
																									</c:when>
																									<c:otherwise>
																											<option value="03">March</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '04'}">
																											<option selected value="04">April</option>
																									</c:when>
																									<c:otherwise>
																											<option value="04">April</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '05'}">
																										<option selected value="05">May</option>
																									</c:when>
																									<c:otherwise>
																										<option value="05">May</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '06'}">
																										<option selected value="06">June</option>
																									</c:when>
																									<c:otherwise>
																										<option value="06">June</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '07'}">
																										<option selected value="07">July</option>
																									</c:when>
																									<c:otherwise>
																										<option value="07">July</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '08'}">
																										<option selected value="08">August</option>
																									</c:when>
																									<c:otherwise>
																										<option value="08">August</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '09'}">
																										<option selected value="09">September</option>
																									</c:when>
																									<c:otherwise>
																										<option value="09">September</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '10'}">
																										<option selected value="10">October</option>
																									</c:when>
																									<c:otherwise>
																										<option value="10">October</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '11'}">
																										<option selected value="11">November</option>
																									</c:when>
																									<c:otherwise>
																										<option value="11">November</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '12'}">
																										<option selected value="12">December</option>
																									</c:when>
																									<c:otherwise>
																										<option value="12">December</option>
																									</c:otherwise>
																								</c:choose>
																							</select>
																						</div>
																					</div>
																					<input type="hidden" id = "hiddenSelectYear" value="${year}">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Year:<span
																								style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">
																							<select id='yearListId' name="year"
																								class="form-control form-white" required="required">
																								<option selected value=''>--SELECT--</option>
																							</select>
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label"></label>
																						</div>
																						<div class="form-group">
																							<button class="btn btn-embossed btn-primary" type="button" id="searchButtons1">
																								<i class="fa fa-search"></i>Search
																							</button>
																						</div>
																					</div>
																				</div>
																			</div>
																		
																	</div>
																</div>
															</div>
														</div>
							
							</div>
							
							</form>
								<div class="col-sm-12" id="multidiv">
								<input type="hidden" id="tenantType" value=${domain } />
									<table class="table table-alt" id="multiActionTable">
										<thead>
											<tr> 
											<td><b>Total Bill Amount</b></td>		
        									<td><b>Amount Collected</b></td>
       										 <td><b>Amount To Be Collected</b></td>											 
											 </tr>										</thead>
									</table>									
								</div>
								<div class="panel-content" id="nodatadiv">
								<font style="color: red;">
								Data is not found for the given inputs</font>
								</div>
								<div class="panel-content" id="cafdatadiv">
								
									<div class="panel-content">
										<input type="hidden" id="tenantType" value=${domain } />
										<table class="table table-alt" id="cafDataTables">
											<thead>
												<tr>
													<th>Account No</th>
													<th>Customer Name</th>
													<th>Aadhar No</th>
													<th>APSFL Track Id</th>
													<th>ONU Serial No</th>
													<th>Activation Date</th>
													<th>Mobile No</th>
													<th>Security Deposit</th>
													<th>Balance</th>
													<th>Status</th>
													<th>Action</th>
												</tr>
											</thead>
										</table>
										<button  type="button" class="btn btn-embossed btn-primary cafsDownload1">Download</button>
										<!-- <button  type="button" id="btn" class="btn btn-embossed btn-primary" onclick="sendDownload1();">SendInEmail</button> -->
										
									</div>
									</div>
									
								</div>
							</div>
						</div>
						</div>
						<!-- END ROW INNER-->
					<!-- 	<div class="" style="">						
						<button  type="button" class="btn btn-embossed btn-primary cafsDownload1">Download</button>
						</div> -->
						</form>
						<form name="cafForm"  id="cafForm" method="get" onload="calls()">
						</form>
						<div class="clear"></div>
						<div id="myModal" class="modal fade" role="dialog" tabindex='-1'>
							<div class="modal-dialog modal-header-info">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header modal-header-info">
										<button type="button" class="close" data-dismiss="modal">
											<font color='#cc1920'>&times;</font>
										</button>
										<h4 class="modal-title">CAF Information</h4>
									</div>
									<div class=" modal-body model-div-sizes"
										style="height: 75ex; overflow-y: scroll;">
										<div class="" id="cafDetailsPopUoDiv">
											<!-- 	<div class="row m-b-10">
											<div class="col-sm-12 disable-search"> -->
											<table class="table  table-alt">
												<tbody>
													<tr class="dataRow">
														<td colspan="7" style="background-color: #fff;">
															<!--Display data when click the row-->
															<div class="row">
																<div class="col-sm-12">
																	<div class="panel">
																		<div class="panel-header bg-light">
																			<h3>
																				Customer Information <img class="imgp"
																					onclick="showdata()"
																					onmouseover="this.style.cursor='pointer'"
																					id="imgp_custInfo" title="View Caf Details"
																					src="./resources/images/apf_add.png"
																					style="float: right;"> <img class="imgm"
																					id="imgm_custInfo" onclick="showdata()"
																					onmouseover="this.style.cursor='pointer'"
																					title="Hide Caf Details"
																					src="./resources/images/apf_sub.png"
																					style="float: right; display: none;">
																			</h3>
																		</div>
																		<div class="panel-content intrInfo" class="dataRow"
																			id="intr_custInfo"
																			style="display: none; border: 2px solid #d4d5d6;">
																			<div class="row">
																				<div class="col-sm-12 disable-search" id="#divId">
																					<table class="table table-dynamic table-alt"
																						id="getCustInfo">
																						<thead>
																							<tr>
																								<th>Name/Organization Name</th>
																								<th>Email</th>
																								<th>Date of Birth/Inc</th>
																								<th>Mobile No.</th>
																								<th>Bill Cycle</th>
																								<th>Customer Type</th>
																								<th>LMO Code</th>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																			</div>
																			<div class="row">
																				<div class="col-sm-12 disable-search" id="#divId">
																					<table class="table table-dynamic table-alt"
																						id="getinstadres">
																						<thead>
																							<tr>
																								<th>Address</th>
																								<th>District</th>
																								<th>Mandal</th>
																								<th>City/Village</th>
																								<th>Pincode</th>
																								<th>Longitude</th>
																								<th>Latitude</th>
																								<th>Location</th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																			</div>

																		</div>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-12">
																	<div class="panel">
																		<div class="panel-header bg-light">
																			<h3>
																				CPE Information <img class="imgp"
																					onclick="showdata_cpeInfo()"
																					onmouseover="this.style.cursor='pointer'"
																					id="imgp_cpeInfo" title="View Caf Details"
																					src="./resources/images/apf_add.png"
																					style="float: right;"> <img class="imgm"
																					id="imgm_cpeInfo" onclick="showdata_cpeInfo()"
																					onmouseover="this.style.cursor='pointer'"
																					title="Hide Caf Details"
																					src="./resources/images/apf_sub.png"
																					style="float: right; display: none;">
																			</h3>
																		</div>
																		<div class="panel-content intrInfo"
																			style="display: none;" id="intr_cpeInfo">
																			<div class="row">
																				<div class="col-sm-12 disable-search" id="#divId">
																					<div class="panel-header bg-light">
																						<h4>ONU Information</h4>
																					</div>
																					<table class="table table-dynamic table-alt"
																						id="getAllParticularCPE">
																						<thead>
																							<tr>
																								<th>POP Name</th>
																								<th>OLT ID</th>
																								<th>OLT Port ID/No</th>
																								<th>ONU Model</th>
																								<!-- <th>ONU Ownership</th> -->
																								<th>ONU Upfront Charge</th>
																								<th>ONU EMI Charge</th>
																								<th>ONU EMI Count</th>
																								<th>ONU SrlNo</th>
																								<th>ONU MacId</th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																			</div>

																			<div class="row">
																				<div class="col-sm-12 disable-search" id="#divId">
																					<div class="panel-header bg-light">
																						<h4>IPTV Information</h4>
																					</div>
																					<table class="table table-dynamic table-alt"
																						id="getStbInfo">
																						<thead>
																							<tr>
																								<th>S No.</th>
																								<th>IPTVBox Caf No</th>
																								<th>IPTVBox Model</th>
																								<!-- <th>IPTVBox Ownership</th> -->
																								<th>IPTVBox Upfront Charge</th>
																								<th>IPTVBox EMI Charge</th>
																								<th>IPTVBox EMI Count</th>
																								<th>IPTVBox SrlNo</th>
																								<th>IPTVBox MacId</th>
																								<th>Subscriber Code</th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																			</div>

																			<div class="row">
																				<div class="col-sm-12 disable-search" id="#divId">
																					<div class="panel-header bg-light">
																						<h4>Telephone Information</h4>
																					</div>
																					<table class="table table-dynamic table-alt"
																						id="telenoid">
																						<thead>
																							<tr>
																								<th>S No.</th>
																								<th>Telephone No</th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>

																			</div>
																		</div>
																	</div>
																</div>
															</div> <!-- END ROW -->
															<div class="row">
																<div class="col-sm-12">
																	<div class="panel">
																		<div class="panel-header bg-light">
																			<h3>
																				Packages and Services Information <img class="imgp"
																					onclick="showdata_pkgInfo()"
																					onmouseover="this.style.cursor='pointer'"
																					id="imgp_pkgInfo" title="View Caf Details"
																					src="./resources/images/apf_add.png"
																					style="float: right;"> <img class="imgm"
																					id="imgm_pkgInfo" onclick="showdata_pkgInfo()"
																					onmouseover="this.style.cursor='pointer'"
																					title="Hide Caf Details"
																					src="./resources/images/apf_sub.png"
																					style="float: right; display: none;">
																			</h3>
																		</div>
																		<div class="panel-content intrInfo"
																			style="display: none;" id="intr_pkgInfo">
																			<div class="row">
																				<div class="col-sm-6">
																					<table class="table table-dynamic table-alt"
																						id="cafdetails">
																						<thead>
																							<tr>
																								<th>S.No</th>
																								<!-- <th>IPTV CAF No</th> -->
																								<th>Package Name</th>
																								<th>Package Type</th>
																								<th>Services</th>
																								<th>Purchased On</th>
																								<th>Total Package Charge</th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																				<div class="col-sm-6 " id="#divId">
																					<table class="table table-dynamic table-alt"
																						id="telephonetable">
																						<thead>
																							<tr>
																								<th>Supplementary Service Name</th>
																								<th>Parameter Code</th>
																								<th>Parameter Value</th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div> <!-- END ROW -->
															<div class="row">
																<div class="col-sm-12">
																	<div class="panel">
																		<div class="panel-header bg-light">
																			<h3>
																				Payment And Bill Information <img class="imgp"
																					onclick="showdata_paymntInfo()"
																					onmouseover="this.style.cursor='pointer'"
																					id="imgp_paymntInfo" title="View Caf Details"
																					src="./resources/images/apf_add.png"
																					style="float: right;"> <img class="imgm"
																					id="imgm_paymntInfo"
																					onclick="showdata_paymntInfo()"
																					onmouseover="this.style.cursor='pointer'"
																					title="Hide Caf Details"
																					src="./resources/images/apf_sub.png"
																					style="float: right; display: none;">
																			</h3>
																		</div>
																		<div class="panel-content intrInfo"
																			style="display: none;" id="intr_paymntInfo">
																			<div class="row">
																				<div class="col-sm-6 " id="#divId">
																					<table class="table table-dynamic table-alt"
																						id="RecentPaymentsInformation">
																						<thead>
																							<tr>
																								<th>S.NO</th>
																								<th>Payment Ref No</th>
																								<th>Payment Type</th>
																								<th>Payment Date</th>
																								<th>Payment Amount</th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																				<div class="col-sm-6">
																					<table class="table table-dynamic table-alt"
																						id="BillInfotable">
																						<thead>
																							<tr>
																							<tr>
																								<th>S.NO</th>
																								<th>Bill Month</th>
																								<th>INV Amount</th>
																								<th>INV Date</th>
																								<th>INV Due Date</th>
																								<th></th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div> <!-- END ROW -->

															<div class="row">
																<div class="col-sm-12">
																	<div class="panel">
																		<div class="panel-header bg-light">
																			<h3>
																				Ticket Information <img class="imgp"
																					onclick="showdata_tcktInfo()"
																					onmouseover="this.style.cursor='pointer'"
																					id="imgp_tcktInfo" title="View Caf Details"
																					src="./resources/images/apf_add.png"
																					style="float: right;"> <img class="imgm"
																					id="imgm_tcktInfo" onclick="showdata_tcktInfo()"
																					onmouseover="this.style.cursor='pointer'"
																					title="Hide Caf Details"
																					src="./resources/images/apf_sub.png"
																					style="float: right; display: none;">
																			</h3>
																		</div>
																		<div class="panel-content intrInfo"
																			style="display: none;" id="intr_tcktInfo">
																			<div class="row">
																				<div class="col-sm-12 disable-search" id="#divId">
																					<table class="table table-dynamic table-alt"
																						id="TicketInfotable">
																						<thead>
																							<tr>
																							<tr>
																								<th>S.NO</th>
																								<th>Ticket No.</th>
																								<th>Submit Date</th>
																								<th>Brief Description</th>
																								<th>Status</th>
																								<th>Close Date</th>
																							</tr>
																						</thead>
																						<tbody>
																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="row"></div>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-embossed btn-danger"
												data-dismiss="modal">Close</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- END FORM -->
					</div>
					<!-- END MAIN PANEL CONTENT -->
				</div>
				<!-- END MAIN PANEL -->
			<!-- HERE COMES YOUR CONTENT -->
		<!-- END MAIN ROW -->
		<div class="clear"></div>
	
	<!-- END PAGE CONTENT -->
<!-- END MAIN CONTENT -->
