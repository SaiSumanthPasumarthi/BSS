<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<style>
.modal-dialog {
    width: 800px;
    margin: 30px auto;
}
table.dataTable tbody td {
   word-break: break-word;
   vertical-align: top;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	//alert('asbdabsdj');
	$('#multidiv').hide();
	var dataTable;
	var url = "searchCafDetails?"
	var oTable = $('#multiActionTable').dataTable(dataTable);
	$('#searchButtons').click(function() {
		$('#error').empty();
		$(".cafsDownload").hide();
		var fromDate = $('#effectiveFrom_coms').val();
		var toDate = $('#effectiveTo_coms').val();
		var status = $('#status').val();
		var aadharNo = $('#aadharNo').val();
		var cafNo = $('#cafNo').val();
		var apsflTrackId = $('#apsflTrackId').val();
		var orgName = $('#organizationName').val();
		var cafType = $('#cafType').val();
		var district = $('#popDistrict').val() == undefined ? "" : $('#popDistrict').val();
		var mandal = $('#popMandal').val() == undefined ? "" : $('#popMandal').val();
		var popId = $('#SIPopId').val() == undefined ? "" : $('#SIPopId').val();
	    
		url = url+$('form').serialize(); 
	    oTable.fnDestroy();
	    
	    if (fromDate != "" && toDate != "" ) {
	    	ShowAjaxMultiData();
		} else if (status != "") {
			ShowAjaxMultiData();
		} else if (aadharNo != "") {
			ShowAjaxMultiData();
		} else if (cafNo != "") {
			ShowAjaxMultiData();
		} else if (apsflTrackId != "") {
			ShowAjaxMultiData();
		} else if (orgName != "") {
			ShowAjaxMultiData();
		} else if (cafType != "") {
			ShowAjaxMultiData();
		} else if (district != "") {
			ShowAjaxMultiData();
		} else if (mandal != "") {
			ShowAjaxMultiData();
		} else if (popId != "") {
			ShowAjaxMultiData();
		} else {
			$('#error').text("Please Select From and To Date OR any one of the Field .");
			 $('#multidiv').hide();
		}
	   
	    oTable = $('#multiActionTable').dataTable(dataTable);
	    url ="searchCafDetails?";
	}); //End of click 
	dataTable= $("#multiActionTable").dataTable();
	
	function ShowAjaxMultiData() {
		 $('#multidiv').show();
		 dataTable ={
	  		      "bProcessing": false,
	  		      "sort" : "position",
	  		      "bServerSide": true,
	  		      "sAjaxSource" : url,
	  		      "aoColumnDefs": [
			                       { "sWidth": "5%", "aTargets": [ 0 ] },
			                       { "sWidth": "9%", "aTargets": [ 1 ] },
			                       { "sWidth": "8%", "aTargets": [ 2 ] },
			                       { "sWidth": "9%", "aTargets": [ 3 ] },
			                       { "sWidth": "5%", "aTargets": [ 4 ] },
			                       { "sWidth": "10%", "aTargets": [ 5 ] },
			                       { "sWidth": "8%", "aTargets": [ 6 ] },
			                       { "sWidth": "10%", "aTargets": [ 7 ] },
			                       { "sWidth": "7%", "aTargets": [ 8 ] },
			                       { "sWidth": "8%", "aTargets": [ 9 ] },
			                       { "sWidth": "10%", "aTargets": [ 10 ] },
			                       { "sWidth": "7%", "aTargets": [ 11 ] },
			                       { "sWidth": "13%", "aTargets": [ 12 ], "orderable": false, },
			                       ], 
			      "aoColumns": [
			                      {  "mData": "",
				                   "render" : function(data, type, full, meta){
				                  return "<td class='customerName' style='word-break:break-all;'>"+full.fName+" "+full.lName+"</td>";
			                 		 }
				                  },
				                  
				                  { "mData": "cafType" },
				                  
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                  return "<td class='apsflTrackId' style='word-break:break-all;'>"+full.apsflTrackId+"</td>";
					                  }
				                  },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                  return "<td class='cpeplace' style='word-break:break-all;'>"+full.cpeplace+"</td>";
					                  }
				                  },

			                      {  "mData": "",
				                      "render" : function(data, type, full, meta){
				                     return "<td class='oltPortNo' style='word-break:break-all;'>"+full.oltPortIdNo+"</td>";
				                     }
				                     },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                  return "<td class='tenantCode' style='word-break:break-all;'>"+full.tenantCode+"</td>";
					                  }
				                  },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
											var tdData =  '<td align="center" style="word-break:break-all; float: left; padding-left: 10px;"> '
												if(full.statusDesc == 'ACTIVE'){
													tdData += '<span style="cursor: pointer;" class="viewcafDtls cafNo" data-toggle="modal" data-target="#myCafModal" title="View CAF information"><a>'+full.cafNo+'</a></span>';	
												}else{
													tdData += '<span class="viewcafDtls cafNo">'+full.cafNo+'</span>';
												}
												tdData += '</td>';
				                  			return tdData;
					                  }
				                  },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                  return "<td class='custCode' style='word-break:break-all;'>"+full.custCode+"</td>";
					                  }
				                  },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                  return "<td class='popName' style='word-break:break-all;'>"+full.popName+"</td>";
					                  }
				                  },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                  return "<td class='cafdate'> "+full.cafdate+"</td>";
					                  }
				                  },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                  return "<td style='word-break:break-all;'> "+full.prodName+"</td>";
					                  }
				                  },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                  return "<td style='word-break:break-all;'> "+full.statusDesc+"</td>";
					                  }
				                  },
				                  {  "mData": "",
					                   "render" : function(data, type, full, meta){
					                	  var tdData =  ' <td align="center" style="word-break:break-all;"> '
					                  	   if((full.statusDesc == 'PENDING FOR SELECT PACKAGES' || full.statusDesc == 'FAILED CONNECTIONS') && full.tenantType != 'APSFL') {
					                  		   tdData += ' <form style="float: left; padding-left: 5px;" name="packagesButtonform" class="packagesButtonform" action="#" method="post">';
					                  		   tdData += ' <input type="hidden" name="cafNumber" class="cafNumber" value="'+full.cafNo+'" />';
						                  				if(full.tenantType == 'SI') {
						                  					tdData += ' <span id="packagesButton" class="packagesButton"  style="cursor: pointer;" title="Provide CPE Information"> <img src="./resources/images/apsfl_select.png"></span>';
							                  			} else {
							                  				tdData += ' <span id="packagesButton" class="packagesButton"  style="cursor: pointer;" title="Select Packages"> <img src="./resources/images/apsfl_select.png"></span>';
								                  		}  
				                  				tdData += ' </form>';
				                  				tdData += ' <form style="float: left; padding-left: 5px;" name="CafCustModifyButtonform" class="CafCustModifyButtonform" action="#" method="post">';
				                  				tdData += ' <input type="hidden" name="cafNumber" class="cafNumber" value="'+full.cafNo+'" />';
				                  				tdData += ' <span id="CafCustModifyButton" class="CafCustModifyButton" style="cursor: pointer;" title="Edit CustomerCaf Details"><img src="./resources/images/apsfl_edit.png"></span>';
				                  				tdData += ' </form>';
							                  		
						                  	   } else if (full.statusDesc == 'PENDING FOR CAF EDIT'){
						                  		 tdData += ' <form style="float: left; padding-left: 5px;" name="CafCustModifyButtonform" class="CafCustModifyButtonform" action="#" method="post">';
						                  		 tdData += ' <input type="hidden" name="cafNumber" class="cafNumber" value="'+full.cafNo+'" />';
					                  				if(full.tenantType == 'APSFL') {
					                  					tdData += ' <span id="APSFLCafModifyButton" class="APSFLCafModifyButton" style="cursor: pointer;" title="Edit CustomerCaf Details"><img src="./resources/images/apsfl_edit.png"></span>';
						                  			} else {
						                  				tdData += ' <span id="CafCustModifyButton" class="CafCustModifyButton" style="cursor: pointer;" title="Edit CustomerCaf Details"><img src="./resources/images/apsfl_edit.png"></span>';
							                  		}
					                  				tdData += ' </form>';
							                  		
							                  } else if((full.statusDesc == 'ACTIVE' && full.tenantType == 'APSFL' && full.custTypelov == 'GOVT') || (full.statusDesc == 'ACTIVE' && full.tenantType == 'LMO')) {
							                	  tdData += ' <form style="float: left; padding-left: 5px;" name="ActiveButtonform" class="ActiveButtonform" action="#" method="post">';
							                	  tdData += ' <input type="hidden" name="cafNumber" class="cafNumber" value="'+full.cafNo+'" />';
							                	  tdData += ' <input type="hidden" name="prodCafNo" class="" value="'+full.prodCafNo+'" />';
							                	  tdData += ' <input type="hidden" name="addOrChangePkgFlag" class="addOrChangePkgFlag" value="addPkgPageFlag" />';
							                	  tdData += ' <span class="ActiveButton" style="cursor: pointer;" title="Add New Package"><img src="./resources/images/apf_add.png"></span>';
							                	  tdData += ' </form>';
							                	  
							                	  tdData += ' <form style="float: left; padding-left: 5px;" name="ActiveButtonform" class="ActiveButtonform" action="#" method="post">';
							                	  tdData += ' <input type="hidden" name="cafNumber" class="cafNumber" value="'+full.cafNo+'" />';
							                	  tdData += ' <input type="hidden" name="prodCafNo" class="" value="'+full.prodCafNo+'" />';
							                	  tdData += ' <input type="hidden" name="addOrChangePkgFlag" class="addOrChangePkgFlag" value="chnagePkgPageFlag" />';
							                	  tdData += ' <span class="ActiveButton" style="cursor: pointer;" title="Change Base Package"><img src="./resources/images/apf_edit.png"></span>';
							                	  tdData += ' </form>';
							                	  
							                	  tdData += ' <form style="float: left; padding-left: 5px;" name="ActiveButtonforms" class="ActiveButtonforms" action="#" method="post">';
							                	  tdData += ' <input type="hidden" name="cafNo" class="cafNo" value="'+full.cafNo+'" />';
							                	  tdData += ' <span class="removePkg" style="cursor: pointer;" data-toggle="modal" data-target="#myModal" title="Remove Package"><img src="./resources/images/apf_sub.png"></span>';
							                	  tdData += ' </form>';
								              }
					                	  
					                	  tdData += ' </td>';
					                	  return tdData;
							                  
					                  }
				                  }
						       ],
					       "fnDrawCallback" : function(oSettings) {
								 if ( oSettings.aiDisplay.length > 0 && $('#multidiv').is(':visible')) {
					            	$(".cafsDownload").show();
					            }
					        }
	
	  		  };
		 /* dataTable.sAjaxSource = url; */
		}
});


</script>
<div class="main-content">
 <!-- BEGIN PAGE CONTENT -->form
	<div class="page-content page-width">
		<div class="page-title">
			<h2>Work<strong> Order</strong></h2>
			<c:if test="${not empty flag}"> <%-- <center><font color='green' size="3">${flag}</font></center> --%>
				<c:set var = "respmessage" value = "${flag}"/>
				<c:choose>
					<c:when test="${fn:containsIgnoreCase(respmessage, 'success')}">
						<center><font color='green' size="3">${flag}</font></center>
					</c:when>
					<c:otherwise>
						<center><font color='red' size="3">${flag}</font></center>
					</c:otherwise>
				</c:choose>
			</c:if>
			<label id="error" style="text-align: center; color: red;"></label>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Work Order</li>
				</ol>
			</div>
		</div>
		
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form name="multiActionForm"  id="multiActionForm" method="post">
							<div class="row">
								<div class="col-sm-12">
									<div class="row">
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">From Date</label> 
												<input type="text" name="effectiveFrom" id="effectiveFrom_coms" <c:if test="${not empty multiActionVO.effectiveFrom}"> value="${multiActionVO.effectiveFrom}" </c:if> class="date-picker form-control form-white" readonly placeholder="Select From Date">
											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">To Date</label> 
												<input type="text" name="effectiveTo" id="effectiveTo_coms" <c:if test="${not empty multiActionVO.effectiveTo}"> value="${multiActionVO.effectiveTo}" </c:if> class="date-picker form-control form-white" readonly placeholder="Select To Date">
											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">Status</label>
												<div class="option-group">
													<select name="status" id="status" class="form-control form-white">
														<option value="">--Select--</option>
														<c:forEach var="status" items="${statusCodes}">
															<c:if test = "${status.statusCode != '89'}">
																<c:if test="${(domain == 'SI' && status.statusCode != '3') || (domain != 'SI')}">
																<c:choose>
																	<c:when test="${not empty multiActionVO.status && status.statusCode == multiActionVO.status}">
																		<option value="${status.statusCode}" selected>
																			<c:choose>
																			<c:when test="${domain == 'SI' && status.statusCode == '0'}">FAILED CONNECTIONS</c:when>
																			<c:otherwise>${status.statusDesc}</c:otherwise>
																			</c:choose>
																		</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${status.statusCode}">
																			<c:choose>
																			<c:when test="${domain == 'SI' && status.statusCode == '0'}">FAILED CONNECTIONS</c:when>
																			<c:otherwise>${status.statusDesc}</c:otherwise>
																			</c:choose>
																		</option>
																	</c:otherwise>
																</c:choose>
																</c:if>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">APSFL TrackId</label>
												<input type="text" name="apsflTrackId" id="apsflTrackId" <c:if test="${not empty multiActionVO.apsflTrackId}"> value="${multiActionVO.apsflTrackId}" </c:if> class="form-control form-white" placeholder="APSFL TrackId">
											</div>
										</div>
										<div class="clear"></div>
									</div>
									<!-- END ROW INNER-->
									<div class="row">
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">Aadhar/Registration No</label>
												<input type="text" name="aadharNo" id="aadharNo" <c:if test="${not empty multiActionVO.aadharNo}"> value="${multiActionVO.aadharNo}" </c:if> class="form-control form-white" maxlength="12" placeholder="Aadhar Number">
											</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">Account Number</label> 
												<input type="text" name="cafNo" id="cafNo" <c:if test="${not empty multiActionVO.cafNo}"> value="${multiActionVO.cafNo}" </c:if> class="form-control form-white number" placeholder="CAF Number">
											</div>
										</div>
										<c:if test="${tenantType == 'SI' || tenantType == 'APSFL'}">
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
													<label class="control-label">Mandal</label>
													<div class="option-group">
														<select name="mandal" id="popMandal" class="form-control form-white">
															<option value="">--Select--</option>
															<c:forEach var="mandal" items="${mandalList}">
																<c:choose>
																	<c:when test="${not empty multiActionVO.mandal && mandal.mandalSlno == multiActionVO.mandal && mandal.districtUid == multiActionVO.district}">
																		<option value="${mandal.mandalSlno}" selected>${mandal.mandalName}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${mandal.mandalSlno}">${mandal.mandalName}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</c:if>
										<c:if test="${tenantType == 'LMO'}">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Customer/Organization Name</label> 
													<input type="text" name="organizationName" id="organizationName" <c:if test="${not empty multiActionVO.organizationName}"> value="${multiActionVO.organizationName}" </c:if> class="form-control form-white" placeholder="Organization Name">
												</div>
											</div>
											<div class="col-sm-3" >
												<div class="form-group">
													<label class="control-label">CAF Type</label>
													<div class="option-group">
														<select name="cafType" id="cafType" class="form-control form-white" >
														<option value="" selected>--Select--</option>
														<c:forEach var="customerType" items="${CustomerTypeList}">
															<c:choose>
																<c:when test="${not empty multiActionVO.cafType && customerType.lovValue == multiActionVO.cafType}">
																	<option value="${customerType.lovValue}" selected>${customerType.lovValue}</option>
																</c:when>
																<c:otherwise>
																	<option value="${customerType.lovValue}">${customerType.lovValue}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
														</select>
													</div> 
												</div>
											</div>
										</c:if>
									</div>
									<div class="row">
										<c:if test="${tenantType == 'SI' || tenantType == 'APSFL'}">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">PoP Name<!-- <span style="color: red;">*</span> --></label>
													<div class="option-group">
														<select name="popName" id="SIPopId" <c:choose><c:when test="${!fn:contains('26', multiActionVO.status)}"> class="form-control form-white disable"  disabled</c:when><c:otherwise> class="form-control form-white" </c:otherwise></c:choose> >
															<option value="">--Select--</option>
															<c:forEach var="pop" items="${popList}">
																<c:choose>
																	<c:when test="${not empty multiActionVO.popName && multiActionVO.popName == pop.substnUid}">
																		<option value="${pop.substnUid}" selected>${pop.substnName}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${pop.substnUid}">${pop.substnName}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">Customer/Organization Name</label> 
												<input type="text" name="organizationName" id="organizationName" <c:if test="${not empty multiActionVO.organizationName}"> value="${multiActionVO.organizationName}" </c:if> class="form-control form-white" placeholder="Organization Name">
											</div>
										</div>
										<div class="col-sm-3" >
											<div class="form-group">
												<label class="control-label">CAF Type</label>
												<div class="option-group">
													<select name="cafType" id="cafType" class="form-control form-white" >
													<option value="" selected>--Select--</option>
													<c:forEach var="customerType" items="${CustomerTypeList}">
														<c:choose>
															<c:when test="${not empty multiActionVO.cafType && customerType.lovValue == multiActionVO.cafType}">
																<option value="${customerType.lovValue}" selected>${customerType.lovValue}</option>
															</c:when>
															<c:otherwise>
																<option value="${customerType.lovValue}">${customerType.lovValue}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
													</select>
												</div> 
											</div>
										</div>
										<div class="col-sm-3" >
											<div class="form-group">
												<label class="control-label">CAF Sub Type</label>
												<div class="option-group">
													<select name="cafSubType" id="cafSubType" class="form-control form-white" >
													<option value="" selected>--Select--</option>
													<c:forEach var="customerType" items="${CustomerSubTypeList}">
														<c:choose>
															<c:when test="${not empty multiActionVO.cafType && customerType.lovValue == multiActionVO.cafType}">
																<option value="${customerType.lovValue}" selected>${customerType.lovValue}</option>
															</c:when>
															<c:otherwise>
																<option value="${customerType.lovValue}">${customerType.lovValue}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
													</select>
												</div> 
											</div>
										</div>
										</c:if>
									</div>
									<div class = "row">
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
						</form>
         					<div class="row m-b-5" id="multidiv">
								<div class="col-sm-12">
									<table class="table table-alt" id="multiActionTable">
										<thead>
											<tr>
												<th >Customer/Organization Name</th>
												<th >CAF Type</th>
												<th >APSFL TrackId</th>
												<th >Location</th>
												<th >OLTPort ID/NO </th>
												<th >LMO Code</th>
												<th >Account No</th>
												<th >Aadhar/Registration No</th>
												<th >POP Name</th>
												<th >CAF Date</th>
												<th >Package Name</th>
												<th >Status</th>
												<th >Actions</th> 
											</tr>
										</thead>
									</table>
									<form name="cafForm" id="cafForm">
										<input type="hidden" id="cafText" name="cafNo"/>
									</form>
								</div>
							</div>
							<div id="myModal" class="modal fade" role="dialog" tabindex='-1'>
								<div class="modal-dialog modal-header-info">
								<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header modal-header-info">
											<button type="button" class="close" data-dismiss="modal">
												<font color='#cc1920'>&times;</font>
											</button>
											<h4 class="modal-title">Terminate Package Information</h4>
										</div>
										<div class=" modal-body model-div-sizes">
											<div class="panel">
												<table class="table  table-alt">
													<tbody>
														<tr class="dataRow">
															<td>
																<!--Display data when click the row-->
																<label id="pkgerror" style="text-align: left;color: red;"></label>
																<div class="panel-header bg-light">
																	<h3>Terminate Package Information</h3>
																</div>
																<div class="panel-content">
																	<div class="row">
																		<div class="col-sm-12 disable-search" id="#divId">
																			<table class="table table-dynamic table-alt pkgclass" id="removePkgTable">
																				<thead>
																					<tr>
																						<th>Account No.</th>
																						<th>Product Name</th>
																						<th>IPTV Box Serial No.</th>
																						<th>Activation Date</th>
																						<th hidden="hidden">Caf No</th>
																						<th hidden="hidden">Product Code</th>
																						<th hidden="hidden">STB Caf No.</th>
																						<th>Action</th>
																				</thead>
																				<tbody>
																				</tbody>
																			</table>
																		</div>
																		<div style="padding-left: 14px;" class="">
																			<button style="display: none;" class="btn btn-embossed btn-primary" type="button" id="PkgSubmitButton" >Submit</button>
																		</div>
																	</div>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										    <div class="" style="padding-left: 670px;">
												<button type="button" class="btn btn-embossed btn-danger" data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
							</div>
						</div>
						<div id="myCafModal" class="modal fade" role="dialog" tabindex='-1'>
								<div class="modal-dialog modal-header-info">
								<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header modal-header-info">
											<button type="button" class="close" data-dismiss="modal">
												<font color='#cc1920'>&times;</font>
											</button>
											<h4 class="modal-title">Caf Details</h4>
										</div>
										<div class=" modal-body model-div-sizes">
											<div class="panel">
												<table class="table  table-alt">
													<tbody>
														<tr class="dataRow">
															<td>
																<!--Display data when click the row-->
																<label id="" style="text-align: left;color: red;"></label>
																<div class="panel-header bg-light">
																	<h3>Caf Details</h3>
																</div>
																<div class="panel-content">
																	<div class="row">
																		<div class="col-sm-12" id="#divId">
																			<table class="table table-dynamic table-alt" id="cafDetailsTable">
																				<thead>
																					<tr>
																						<th>ONU SerialNo</th>
																						<th>ONU Mac Address</th>
																						<th>ONU Place</th>
																						<th>IPTV SrlNo - IPTV MacAddr</th>
																						<th>Landline No</th>
																				</thead>
																				<tbody>
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										    <div class="" style="">
												<button type="button" class="btn btn-embossed btn-danger" data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
							</div>
						</div>
						<div class="" style="">
							<button style="display: none;" type="button" class="btn btn-embossed btn-primary cafsDownload">Download</button>
					 	</div>
					</div>
				</div>
			</div>			
	 	</div>
 	</div>
 </div>
