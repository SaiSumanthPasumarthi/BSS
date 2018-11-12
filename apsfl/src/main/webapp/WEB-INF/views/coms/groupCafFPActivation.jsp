<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
table.dataTable tbody td {
   word-break: break-word;
   vertical-align: top;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var result = 0;
	$("#groupDataDiv").hide();
	var dataTable;
	var url = "groupCafFPActivationDetails?"
	var oTable = $('#groupCafFPActivation').dataTable(dataTable);
	$('#groupCafFPActivationButton').click(function() {
		$('#groupCafFPActivationFormError').empty();
		$('#groupCafFPActivation').empty();
		$("#groupCafFPActivationSubmit").attr("disabled", "disabled");
		var district = $("#district").val();
		var mandal = $("#mandal").val();
		var village = $("#village").val();
		var selectCaf = $('input[name=selectCaf]:checked').val();
		url = url+$('form').serialize(); 
	    oTable.fnDestroy();
		if(selectCaf == "Gl") {
			ShowAjaxFPData();
		} else if(district != "" && mandal != "" && village != "") {
			ShowAjaxFPData();
		} else if(district != "" && mandal != "") {
			ShowAjaxFPData();
		} else if(district != "") {
			ShowAjaxFPData();
		} else {
			$('#groupCafFPActivationFormError').text("Please Enter all mandatory fields.");
			$("#groupDataDiv").hide();
		}
	    oTable = $('#groupCafFPActivation').dataTable(dataTable);
	    url ="groupCafFPActivationDetails?";
	});  /*End of Click*/
	//dataTable= $("#groupCafFPActivation").dataTable( );
	function ShowAjaxFPData(){
		$("#groupDataDiv").show();
		 dataTable = {   
	  		      "bProcessing": false,
	  		      "sort" : "position",
	  		      "bServerSide": true,
	  		      "sAjaxSource" : url,
	  		      "aoColumnDefs": [
	  		                       {"sWidth": "11%" , "aTargets": [ 0 ] , "sClass" : "table-bordered"}, 
								   {"sWidth": "22%" , "aTargets": [ 1 ] , "sClass" : "table-bordered"}, 
								   {"sWidth": "10%" , "aTargets": [ 2 ] , "sClass" : "table-bordered"}, 
								   {"sWidth": "9%" ,"aTargets": [ 3 ] ,  "sClass" : "table-bordered"}, 
								   {"sWidth": "16%" , "aTargets": [ 4 ] , "sClass" : "table-bordered"}, 
								   {"sWidth": "12%" , "aTargets": [ 5 ] , "sClass" : "table-bordered"}, 
								   {"sWidth": "11%" , "aTargets": [ 6 ] , "sClass" : "table-bordered"}, 
								   {"sWidth": "10%" , "aTargets": [ 7 ] , "sClass" : "table-bordered", sClass : "subcriberCode" },
								 ],
				"aoColumns" : [
				               {"mData" : "cafNo"},
							   {"mData": "",
									"render" : function(data, type, full, meta) {
									  return "<td>"+full.custName+" "+full.lName+"</td>";
									}
								},
								{"mData" : "aadharNo" },
								{"mData" : "cafDate" },
								{"mData" : "cpePlace"},
								{"mData" : "stbSlno"},
								{"mData" : "stbMacaddr"},
								{"mData" : "nwsubsCode"},
							],
							
				"fnDrawCallback" : function(oSettings) {
								 if ( oSettings.aiDisplay.length > 0 ) {
					            	$("#groupCafFPActivationSubmit").removeAttr("disabled");
					            }
					        }
	  		  };
		} /*End of Function*/
	
	});
</script>

<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Group<strong> Command Types</strong>
			</h2>
			<label id="fingerPrintId" style="text-align: center; color: green;"></label>
			<label id="groupCafFPActivationFormError" style="text-align: left; color: red;"></label>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Group Command Types</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form name="groupCafFPActivationForm" id="groupCafFPActivationForm" method="post">
							<div class="row">
								<div class="col-sm-12">
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label class="control-label">Message Type<span style="color: red;">*</span></label>
												<div class="clear"></div>
												<div class="pull-left" style="padding-left: 0;">
													<input type="radio" name="selectCaf" id="selectCaf" class="form-white" value="Gl" checked >&nbsp;&nbsp;Global Message
												</div>
												<div class="col-sm-6">
													<input type="radio" name="selectCaf" id="selectCaf1" class="form-white" value="Gr" >&nbsp;&nbsp;Group Message
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
									<div class="row">
										<div id="globalDiv">
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">District<span style="color: red;">*</span></label>
												<div class="option-group">
													<select name="district" id="district" class="form-control form-white">
														<option value="">--Select--</option>
														<c:forEach var="district" items="${districtList}">
															<c:choose>
																<c:when test="${not empty districtId && district.districtUid == districtId}">
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
												<label class="control-label">Mandal<span style="color: red;">*</span></label>
												<div class="option-group">
													<select name="mandal" id="mandal" class="form-control form-white">
														<option value="">--Select--</option>
														<c:forEach var="mandal" items="${mandalList}">
															<c:choose>
																<c:when test="${not empty mandalId && mandal.mandalSlno ==  mandalId && mandal.districtUid == districtId}">
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
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">City/Village<span style="color: red;">*</span></label>
												<div class="option-group">
													<select name="village" id="village" class="form-control form-white">
														<option value="">--Select--</option>
														<c:choose>
															<c:when test="${not empty villageList}">
																<c:forEach var="village" items="${villageList}">
																	<c:choose>
																		<c:when test="${not empty villageId && village.villageSlno == villageId && village.mandalSlno == mandalId && village.districtUid == districtId }">
																			<option value="${village.villageSlno}" selected>${village.villageName}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${village.villageSlno}">${village.villageName}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</c:when>
															<c:otherwise>
																<option></option>
															</c:otherwise>
														</c:choose>
													</select>
												</div>
											</div>
										</div>
										</div>
										<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label hide-mobile">&nbsp;</label>
												<div class="option-group">
													<button class="btn btn-embossed btn-primary" type="button" id="groupCafFPActivationButton">
														<i class="fa fa-search"></i>Search
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
						<div id="groupDataDiv">
							<div class="row m-b-5">
								<div class="col-sm-12">
									<table class="table table-alt individualCafFPActivationTr" id="groupCafFPActivation">
										<thead>
											<tr>
												<th width="10%">Caf No</th>
												<th width="21%">Customer Name</th>
												<th width="10%">Aadhar No</th>
												<th width="9%">CafDate</th>
												<th width="17%">Location</th>
												<th width="10%">IPTV Srl No</th>
												<th width="10%">IPTV Macaddress</th>
												<th width="10%">SubcriberCode</th>
											</tr>
										</thead>
										<%-- <tbody>
											<c:if test="${not empty customerList}">
												<c:forEach items="${customerList}" var="customer" varStatus="rowNum">
													<tr class="indFPTr">
														<td>${rowNum.count}</td>
														<td>${customer[0]}</td>
														<td style="word-break: break-all;">${customer[2]}${customer[3]}</td>
														<td>${customer[1]}</td>
														<td><fmt:parseDate value="${customer[5]}" pattern="yyyy-MM-dd" var="adate" />
															<fmt:formatDate pattern="dd-MM-yyyy" value="${adate}" />
														</td>
														<td>${customer[6]}</td>
														<td>${customer[7]}</td>
														<td>${customer[8]}</td>
														<td class="subcriberCode">${customer[9]}</td>
														<!-- <td align="center"><input type="checkbox" class="FPCheckbox" checked disabled /></td> -->
													</tr>
												</c:forEach>
											</c:if>
										</tbody> --%>
									</table>
								</div>
							</div> <!-- End of DisplayData -->
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Command Types</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-12">
													<div class="form-group">
														<label class="control-label">FingerPrint Types</label>
														<div class="clear"></div>
														<div class="pull-left" style="padding-left: 0;">
															<input type="radio" class="form-white" id="fingerPrint" name="fingerPrint" checked value="F">&nbsp;&nbsp;Finger Print
														</div>
														<div class="pull-left">&nbsp;&nbsp;&nbsp;
															<input type="radio" class="form-white" id="fingerPrint1" name="fingerPrint" value="O">&nbsp;&nbsp;OSD
														</div>
														<div class="col-sm-6">
															<input type="radio" class="form-white" id="fingerPrint2" name="fingerPrint" value="S">&nbsp;&nbsp;Scroll Text
														</div>
														<div class="clear"></div>
													</div>
												</div>
											</div>
											<div id="fingerPrintDiv">
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Position<span style="color: red;">*</span></label> 
															<input type="text" name="" id="fpPosition" class="form-control form-white" value="400,800">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">FontType<span style="color: red;">*</span></label> 
															<input type="text" name="" id="fpFontType" class="form-control form-white" value="Times Roman">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">FontSize<span style="color: red;">*</span></label> 
															<input type="text" name="" id="fpFontSize" class="form-control form-white" value="36">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">FontColor<span style="color: red;">*</span></label> 
															<input type="text" name="" id="fpFontColor" class="form-control form-white" value="yellow">
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Duration<span style="color: red;">*</span></label> 
															<input type="text" name="" id="fpDuration" class="form-control form-white" value="20">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">FingerPrintType<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="" id="fpfingerPrintType" class="form-control form-white"
																	<c:if test="${custType == 'ENTERPRISE' || not empty customer}">disabled</c:if>>
																	<option value="Static">Static</option>
																	<option value="Random">Random</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Channel<span style="color: red;">*</span></label> 
															<input type="text" name="" id="fpChannel" class="form-control form-white" value="0">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">BgColor<span style="color: red;">*</span></label>
															<input type="text" name="" id="fpBgColor" class="form-control form-white" value = "white">
														</div>
													</div>
													<div class="clear"></div>
												</div>
											</div>
											<div id="osdDiv">
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Position<span style="color: red;">*</span></label> 
															<input type="text" name="" id="osdPosition" class="form-control form-white" value="400,800">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">FontType<span style="color: red;">*</span></label> 
															<input type="text" name="" id="osdFontType" class="form-control form-white" value="Times Roman">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">FontSize<span style="color: red;">*</span></label> 
															<input type="text" name="" id="osdFontSize" class="form-control form-white" value="36">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">FontColor<span style="color: red;">*</span></label> 
															<input type="text" name="" id="osdFontColor" class="form-control form-white" value="yellow">
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">BgColor<span style="color: red;">*</span></label> 
															<input type="text" name="" id="osdBgColor" class="form-control form-white" value="red">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Duration<span style="color: red;">*</span></label> 
															<input type="text" name="" id="osdDuration" class="form-control form-white" value="20">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">message<span style="color: red;">*</span></label>
															<textarea name="" id="osdMessage" maxlength="150" class="form-control form-white">Test Message</textarea>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">UserCanCloseMessage<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="" id="osdUserCanCloseMessage" class="form-control form-white">
																	<option value="true">true</option>
																	<option value="false">false</option>
																</select>
															</div>
														</div>
													</div>
													<div class="clear"></div>
												</div>
											</div>
											<div id="stDiv">
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">message<span style="color: red;">*</span></label>
															<textarea name="" id="stMessage" maxlength="900" class="form-control form-white">Hello XYZ</textarea>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Duration<span style="color: red;">*</span></label> 
															<input type="text" name="" id="stDuration" class="form-control form-white" value="56">
														</div>
													</div>
													<div class="clear"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="pull-right">
									<a href="./home"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>&nbsp;
									<button class="btn btn-embossed btn-primary" type="button" id="groupCafFPActivationSubmit" disabled>Submit</button> 
									<label id="individualCafFPActivationError" style="text-align: left; color: red;"></label>
								</div>
							</div>
						</div>
						<div class="clear"></div>
						<!-- END FORM -->
					</div>
					<!-- END MAIN PANEL CONTENT -->
				</div>
				<!-- END MAIN PANEL -->
			</div>
			<!-- HERE COMES YOUR CONTENT -->
		</div>
		<!-- END MAIN ROW -->
		<div class="clear"></div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END MAIN CONTENT -->
