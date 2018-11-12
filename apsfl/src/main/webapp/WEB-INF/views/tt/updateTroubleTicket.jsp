<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>   
<style>
.hiddenField {
	display: none;
}

.tooltipclass{
    font-size: 12px;
    border:1px solid #e74c3c;
    background-color: white;
    font-family: sans-serif;
    opacity: 1;
}

.modal-dialog {
	width: 1200px;
	margin: 30px auto;
}

table.dataTable tbody td {
    word-break: break-word;
    vertical-align: top;
}

table{
    width:100% !important;
}
       
</style> 
<script type="text/javascript">
$(document).ready(function() {
	$('#ttHistorydiv').hide();
	var dataTable;
	var url;
		    var ttNumber = $("#ttNumberID").val();
		    var contactLandline = $("#landlineNoID").val();
		    var contactMobile = $("#MobileNoID").val();
		    var ticketPriority = $("#priorityID :selected").val();
		    var status = $("#statusID :selected").val();
		    var fromDate = $("#fromDatepickerID").val();
		    var toDate = $("#toDatepickerID").val();
		    var ticketFor = $("#ttForID").val();
		    var ttType = $("#ttTypeID").val();
		    var issueType = $("#issueTypeID").val();
		    var issue = $("#issueID").val();
		    var cafNo = $("#cafNoIID").val();
		    var district = $("#districtID").val();
		    var actualIssue = $("#actualIssueID").val();
		    
		    url = "updateTroubleTicketPagination?ttNumber="+ttNumber+"&contactLandline="+contactLandline+"&contactMobile="+contactMobile+"&ticketPriority="+ticketPriority+"&status="+status+"&fromDate="+fromDate+"&toDate="+toDate+"&ticketFor="+ticketFor+"&ttType="+ttType+"&issueType="+issueType+"&issue="+issue+"&cafNo="+cafNo+"&district="+district+"&actualIssue="+actualIssue;
			
			/* $("#ttHistoryTable").dataTable({
				"bProcessing" : false,
				"bServerSide" : true,
				"sort" : "position",
				"sAjaxSource" : url,
				//"scrollX" : true,
				"aoColumnDefs": [
			                       { "sWidth": "15%", "aTargets": [ 0 ] },
			                       { "sWidth": "15%", "aTargets": [ 1 ] },
			                       { "sWidth": "12%", "aTargets": [ 2 ] },
			                       { "sWidth": "12%", "aTargets": [ 3 ] },
			                       { "sWidth": "12%", "aTargets": [ 4 ] },
			                       { "sWidth": "15%", "aTargets": [ 5 ] },
			                       { "sWidth": "5%", "aTargets": [ 6 ] },
			                       { "sWidth": "5%", "aTargets": [ 7 ] },
			                       { "sWidth": "8%", "aTargets": [ 8 ]  , "orderable": false,},
			                       { "sWidth": "8%", "aTargets": [ 9 ]  , "orderable": false,},
			                       { "sWidth": "8%", "aTargets": [ 10 ]  , "orderable": false,},
			                       { "sWidth": "8%", "aTargets": [ 11 ]  , "orderable": false,},
			                       { "sWidth": "8%", "aTargets": [ 12 ]  , "orderable": false,},
			                       { "sWidth": "8%", "aTargets": [ 13 ] , "orderable": false,},
			                       { "sWidth": "8%", "aTargets": [ 14 ] , "orderable": false,},
			                       { "sWidth": "8%", "aTargets": [ 15 ] , "orderable": false,},
			                       { "sWidth": "8%", "aTargets": [ 16 ] , "orderable": false,},
			                       { className: "hiddenField" ,"sWidth": "10%", "aTargets": [ 17 ] },
			                       { className: "hiddenField" , "sWidth": "10%", "aTargets": [ 18 ] },
			                       { className: "hiddenField" , "aTargets": [ 19 ] },
			                       { className: "hiddenField" , "aTargets": [ 20 ] },
			                       { className: "hiddenField" , "aTargets": [ 21 ] },
			                       { className: "hiddenField" , "aTargets": [ 22 ] },
			                       { className: "hiddenField" , "aTargets": [ 23 ] },
			                       { className: "hiddenField" , "aTargets": [ 24 ] },
			                       { className: "hiddenField" , "aTargets": [ 25 ]}, 
			                       { className: "hiddenField" , "aTargets": [ 26 ] },
			                       { className: "hiddenField" , "aTargets": [ 27 ]},
			                       { className: "hiddenField" , "aTargets": [ 28 ] },
			                       { className: "hiddenField" , "aTargets": [ 29 ]},
			                       ], 
			                       "aoColumns": [
			   			                      {  "mData": "",
				   				                   "render" : function(data, type, full, meta){
				   				                	var tdData = "<td class='ticketNo' style='word-break:break-all;'>"+full.ticketNo+"";
				   				                		tdData +='<input type="hidden" class="lNameClass" value="'+full.aadharNo+'"/>'
				   				                		tdData +='<input type="hidden" class="oltSerialNoClass" value="'+full.contactMobile+'"/>'
				   				                		tdData +='<input type="hidden" class="portClass" value="'+full.contactLandline+'"/>'
				   				                		tdData +='<input type="hidden" class="oltONUIDClass" value="'+full.assignedTo+'"/>'
				   				                		tdData +='<input type="hidden" class="apsflTrackIDClass" value="'+full.ticketPriority+'"/>'
				   				                		tdData +='<input type="hidden" class="aadharNoClass" value="'+full.ticketDesc+'"/>'
				   				                		tdData +='<input type="hidden" class="pocMob1Class" value="'+full.ticketReason+'"/>'
				   				                		tdData +='<input type="hidden" class="email1Class" value="'+full.childTicketNos+'"/>'
				   				                		tdData +='<input type="hidden" class="address1Class" value="'+full.actualIssue+'"/>'
				   				                		tdData +='<input type="hidden" class="districtNameClass" value=  "'+full.districts+'" />'
				   				                		tdData +='<input type="hidden" class="mandalNameClass" value="'+full.subStationName+'"/>'
				   				                		tdData +='<input type="hidden" class="subStationNameClass" value="'+full.userName+'"/>'
				   				                		//tdData +='<input type="hidden" class="apsflLandlineNoClass" value="'+full.parentticketno+'"/>'
				   				                		if(full.parentticketno !=null){
				   				                			tdData +='<input type="hidden" class="apsflLandlineNoClass" value="'+full.parentticketno+'"/>'
														}else{
															tdData +='<input type="hidden" class="apsflLandlineNoClass" value=""/>'	
														}
				   				                		tdData += '</td>';
				   				                		return tdData;
				   			                 		 }
				   				                  },
			   				                  
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td class='cafNo' style='word-break:break-all;'>"+full.cafNo+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td class='issueType' style='word-break:break-all;'>"+full.issueType+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td class='issue' style='word-break:break-all;'>"+full.issue+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                	   return "<td class='custName' style='word-break:break-all;'>"+full.custName+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td class='oltSerialNo' style='word-break:break-all;'>"+full.oltSerialNo+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td class='port' style='word-break:break-all;'>"+full.port+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td class='oltONUID'> "+full.oltONUID+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='word-break:break-all;'> "+full.createdOnDate+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='word-break:break-all;'> "+full.expectedClosureDate+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                	var tdData =  "<td style='word-break:break-all;'>"
														if(full.modifiedOnString !=null){
															tdData += ""+full.modifiedOnString+"";
														}else{
															tdData += "";	
														}
														tdData += '</td>';
						                  			return tdData;
			   					                  //return "<td style='word-break:break-all;'> "+full.modifiedOnString+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='word-break:break-all;'> "+full.timeDiff+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='word-break:break-all;'> "+full.currentStatus+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                	  var tdData =  ' <td class="unknwnColumns"><a id="popAnchorID" href="#" class="popper" data-toggle="modal" data-target="#myModal1" >View TT Info</a></td> '
			   					                	  return tdData;
			   							                  
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                	  var tdData =  ' <td class="unknwnColumns"></td> '
			   					                	  return tdData;
			   							                  
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                	  var tdData =  ' <td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+full.ticketNo+'"><a href="#" class="updateTTClass">Update TT</a></td> '
			   					                	  return tdData;
			   							                  
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                	  var tdData =  ' <td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+full.ticketNo+'"><input type="hidden" class="ticketDesClass" value="'+full.ticketDesc+'"><input type="hidden" class="createdByClass" value="'+full.createdBy+'"><a href="#" class="ttHistoryClass" data-toggle="modal" data-target="#myModal">TT History</a></td> '
			   					                	  return tdData;
			   							                  
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.ticketDesc+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.aadharNo+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.contactMobile+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.contactLandline+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.assignedTo+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.ticketPriority+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.ticketReason+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.parentticketno+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.childTicketNos+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.actualIssue+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.districts+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.subStationName+"</td>";
			   					                  }
			   				                  },
			   				                  {  "mData": "",
			   					                   "render" : function(data, type, full, meta){
			   					                  return "<td style='display:none;'> "+full.userName+"</td>";
			   					                  }
			   				                  },
			   				                  
			   						       ],
			   						    "fnDrawCallback" : function(oSettings) {
											 if ( oSettings.aiDisplay.length > 0 ) {
								            	$(".exportClass").show();
								            }
								        }
									}); */
		    
		    $('#searchID').click(function() {
		    	  	 ttNumber = $("#ttNumberID").val();
				     contactLandline = $("#landlineNoID").val();
				     contactMobile = $("#MobileNoID").val();
				     ticketPriority = $("#priorityID :selected").val();
				     status = $("#statusID :selected").val();
				     fromDate = $("#fromDatepickerID").val();
				     toDate = $("#toDatepickerID").val();
				     ticketFor = $("#ttForID").val();
				     ttType = $("#ttTypeID").val();
				     issueType = $("#issueTypeID").val();
				     issue = $("#issueID").val();
				     cafNo = $("#cafNoIID").val();
				     district = $("#districtID").val();
				     actualIssue = $("#actualIssueID").val();
				     
				     $('#ttHistoryDivID').html("");
						$('#id_container').find('ol').html("");
							$('#ttHistoryDivID').html("");
							 var check_cnt = 0;
							 
							  if($("#ttNumberID").val()=="" && $("#landlineNoID").val()== "" && $("#MobileNoID").val()=="" 
									  && $("#priorityID option:selected").val()== "-1" && $("#ttForID").val()=="-1" 
									  && $("#ttTypeID").val()=="-1" && $("#issueTypeID").val()=="-1"  && $("#issueID").val()=="-1" 
									  && $("#statusID option:selected").val()=="-1" && $("#fromDatepickerID").val()== "" && $("#toDatepickerID").val()== "" && $("#cafNoIID").val()== "" && $("#districtID").val()== "-1" ){				
								    $("#id_container").addClass('dberrorMsg');
									$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Atleast one field</li>');
									$("#id_container").show();	
									$('#ttHistorydiv').hide();
									check_cnt=check_cnt+1;
								}   
							  
							  if($("#fromDatepickerID").val()!=""){	
								  if($("#toDatepickerID").val() == ""){
								    $("#id_container").addClass('dberrorMsg');
									$('#id_container').find('ol').append('<li style="list-style: none;">Please Select To Date</li>');
									$("#id_container").show();	
									$('#ttHistorydiv').hide();
									check_cnt=check_cnt+1;
								}  }
							  
							  if($("#toDatepickerID").val()!=""){	
								  if($("#fromDatepickerID").val() == ""){
								    $("#id_container").addClass('dberrorMsg');
									$('#id_container').find('ol').append('<li style="list-style: none;">Please Select From Date</li>');
									$("#id_container").show();	
									$('#ttHistorydiv').hide();
									check_cnt=check_cnt+1;
								}}
							  
							  if(check_cnt == 0)
							  {
								 $('#ttHistorydiv').show();
							     $("#ttHistoryTable").dataTable().fnDestroy();
								 url = "updateTroubleTicketPagination?ttNumber="+ttNumber+"&contactLandline="+contactLandline+"&contactMobile="+contactMobile+"&ticketPriority="+ticketPriority+"&status="+status+"&fromDate="+fromDate+"&toDate="+toDate+"&ticketFor="+ticketFor+"&ttType="+ttType+"&issueType="+issueType+"&issue="+issue+"&cafNo="+cafNo+"&district="+district+"&actualIssue="+actualIssue;
								 $("#ttHistoryTable").dataTable({
										"bProcessing" : false,
										"bServerSide" : true,
										"sort" : "position",
										"sAjaxSource" : url,
										//"scrollX" : true,
										"aoColumnDefs": [
									                       { "sWidth": "15%", "aTargets": [ 0 ] },
									                       { "sWidth": "15%", "aTargets": [ 1 ] },
									                       { "sWidth": "12%", "aTargets": [ 2 ] },
									                       { "sWidth": "12%", "aTargets": [ 3 ] },
									                       { "sWidth": "12%", "aTargets": [ 4 ] },
									                       { "sWidth": "15%", "aTargets": [ 5 ] },
									                       { "sWidth": "5%", "aTargets": [ 6 ] },
									                       { "sWidth": "5%", "aTargets": [ 7 ] },
									                       { "sWidth": "8%", "aTargets": [ 8 ]  , "orderable": false,},
									                       { "sWidth": "8%", "aTargets": [ 9 ]  , "orderable": false,},
									                       { "sWidth": "8%", "aTargets": [ 10 ]  , "orderable": false,},
									                       { "sWidth": "8%", "aTargets": [ 11 ]  , "orderable": false,},
									                       { "sWidth": "8%", "aTargets": [ 12 ]  , "orderable": false,},
									                       { "sWidth": "8%", "aTargets": [ 13 ] , "orderable": false,},
									                       { "sWidth": "8%", "aTargets": [ 14 ] , "orderable": false,},
									                       { "sWidth": "8%", "aTargets": [ 15 ] , "orderable": false,},
									                       { "sWidth": "8%", "aTargets": [ 16 ] , "orderable": false,},
									                       { className: "hiddenField" ,"sWidth": "10%", "aTargets": [ 17 ] },
									                       { className: "hiddenField" , "sWidth": "10%", "aTargets": [ 18 ] },
									                       { className: "hiddenField" , "aTargets": [ 19 ] },
									                       { className: "hiddenField" , "aTargets": [ 20 ] },
									                       { className: "hiddenField" , "aTargets": [ 21 ] },
									                       { className: "hiddenField" , "aTargets": [ 22 ] },
									                       { className: "hiddenField" , "aTargets": [ 23 ] },
									                       { className: "hiddenField" , "aTargets": [ 24 ] },
									                       { className: "hiddenField" , "aTargets": [ 25 ]}, 
									                       { className: "hiddenField" , "aTargets": [ 26 ] },
									                       { className: "hiddenField" , "aTargets": [ 27 ]},
									                       { className: "hiddenField" , "aTargets": [ 28 ] },
									                       { className: "hiddenField" , "aTargets": [ 29 ]},
									                       ], 
									                       "aoColumns": [
									   			                      {  "mData": "",
									   				                   "render" : function(data, type, full, meta){
									   				                	var tdData = "<td class='ticketNo' style='word-break:break-all;'>"+full.ticketNo+"</td>"
									   				                		tdData +='<input type="hidden" class="lNameClass" value="'+full.aadharNo+'"/>'
									   				                		tdData +='<input type="hidden" class="oltSerialNoClass" value="'+full.contactMobile+'"/>'
									   				                		tdData +='<input type="hidden" class="portClass" value="'+full.contactLandline+'"/>'
									   				                		tdData +='<input type="hidden" class="oltONUIDClass" value="'+full.assignedTo+'"/>'
									   				                		tdData +='<input type="hidden" class="apsflTrackIDClass" value="'+full.ticketPriority+'"/>'
									   				                		tdData +='<input type="hidden" class="aadharNoClass" value="'+full.ticketDesc+'"/>'
									   				                		tdData +='<input type="hidden" class="pocMob1Class" value="'+full.ticketReason+'"/>'
									   				                		tdData +='<input type="hidden" class="email1Class" value="'+full.childTicketNos+'"/>'
									   				                		tdData +='<input type="hidden" class="address1Class" value="'+full.actualIssue+'"/>'
									   				                		tdData +='<input type="hidden" class="districtNameClass" value=  "'+full.districts+'" />'
									   				                		tdData +='<input type="hidden" class="mandalNameClass" value="'+full.subStationName+'"/>'
									   				                		tdData +='<input type="hidden" class="subStationNameClass" value="'+full.userName+'"/>'
									   				                		//tdData +='<input type="hidden" class="apsflLandlineNoClass" value="'+full.parentticketno+'"/>'
									   				                		if(full.parentticketno !=null){
									   				                			tdData +='<input type="hidden" class="apsflLandlineNoClass" value="'+full.parentticketno+'"/>'
																			}else{
																				tdData +='<input type="hidden" class="apsflLandlineNoClass" value=""/>'	
																			}
									   				                		tdData += '</td>';
									   				                		return tdData;
									   			                 		 }
									   				                  },
									   				                  
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td class='cafNo' style='word-break:break-all;'>"+full.cafNo+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td class='issueType' style='word-break:break-all;'>"+full.issueType+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td class='issue' style='word-break:break-all;'>"+full.issue+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                	   return "<td class='custName' style='word-break:break-all;'>"+full.custName+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td class='oltSerialNo' style='word-break:break-all;'>"+full.oltSerialNo+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td class='port' style='word-break:break-all;'>"+full.port+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td class='oltONUID'> "+full.oltONUID+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='word-break:break-all;'> "+full.createdOnDate+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='word-break:break-all;'> "+full.expectedClosureDate+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                	   
									   					                	var tdData =  "<td style='word-break:break-all;'>"
																				if(full.modifiedOnString !=null){
																					tdData += ""+full.modifiedOnString+"";
																				}else{
																					tdData += "";	
																				}
																				tdData += '</td>';
												                  			return tdData;
									   					                	   
									   					                 // return "<td style='word-break:break-all;'> "+full.modifiedOnString+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='word-break:break-all;'> "+full.timeDiff+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='word-break:break-all;'> "+full.currentStatus+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                	  var tdData =  ' <td class="unknwnColumns"><a id="popAnchorID" href="#" class="popper" data-toggle="modal" data-target="#myModal1" >View TT Info</a></td> '
									   					                	  return tdData;
									   							                  
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                	  var tdData =  ' <td class="unknwnColumns"></td> '
									   					                	  return tdData;
									   							                  
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                	  var tdData =  ' <td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+full.ticketNo+'"><a href="#" class="updateTTClass">Update TT</a></td> '
									   					                	  return tdData;
									   							                  
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                	  var tdData =  ' <td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+full.ticketNo+'"><input type="hidden" class="ticketDesClass" value="'+full.ticketDesc+'"><input type="hidden" class="createdByClass" value="'+full.createdBy+'"><a href="#" class="ttHistoryClass" data-toggle="modal" data-target="#myModal">TT History</a></td> '
									   					                	  return tdData;
									   							                  
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.ticketDesc+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.aadharNo+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.contactMobile+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.contactLandline+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.assignedTo+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.ticketPriority+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.ticketReason+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.parentticketno+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.childTicketNos+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.actualIssue+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.districts+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.subStationName+"</td>";
									   					                  }
									   				                  },
									   				                  {  "mData": "",
									   					                   "render" : function(data, type, full, meta){
									   					                  return "<td style='display:none;'> "+full.userName+"</td>";
									   					                  }
									   				                  },
									   				                  
									   						       ],
									   						    "fnDrawCallback" : function(oSettings) {
																	 if ( oSettings.aiDisplay.length > 0 ) {
														            	$(".exportClass").show();
														            }
														        }
															});
							  }
		    });
		    
	 
	
	$("#ttForID").change(function(){
		//alert('in change');
		$("#ttTypeID").empty();
		$("#ttTypeID").append('<option value="-1">--Select TT Type--</option>');
		$("#ttTypeID").select2("val", "-1");
		$("#issueTypeID").empty();
		$("#issueTypeID").append('<option value="-1">--Select Issue Type--</option>');
		$("#issueTypeID").select2("val", "-1");
		$("#issueID").empty();
		$("#issueID").append('<option value="-1">--Select Issue--</option>');
		$("#issueID").select2("val", "-1");
		
		var issueTypeObj = {
				"ttFor":$(this).val(),
				  };
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     //contentType : 'application/json; charset=utf-8',
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getTTTypes', 
	      	     data:issueTypeObj,
	      	     success: function(data) { 
	      	    	// alert(data+"data");
	      	    	 $.each(data, function(key, value){ 
	      	    		$('#ttTypeID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
	      	     }
	      	     });
	 });
	
	
	$("#ttTypeID").change(function(){
		//alert('in change');
		$("#issueTypeID").empty();
		$("#issueTypeID").append('<option value="-1">--Select Issue Type--</option>');
		$("#issueTypeID").select2("val", "-1");
		$("#issueID").empty();
		$("#issueID").append('<option value="-1">--Select Issue--</option>');
		$("#issueID").select2("val", "-1");
		
		var issueTypeObj = {
				"ttType":$(this).val(),
				"ticketFor":$("#ttForID").val(),
			   };
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     //contentType : 'application/json; charset=utf-8',
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getIssueTypes', 
	      	     data:issueTypeObj,
	      	     success: function(data) { 
	      	    	// alert(data+"data");
	      	    	 $.each(data, function(key, value){ 
	      	    		$('#issueTypeID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
	      	     }
	      	     });
	 });
	
	$("#issueTypeID").change(function(){
		$("#issueID").empty();
		$('#issueID').append('<option value="-1">--Select Issue--</option>');
		$("#issueID").select2("val", "-1");

		var issueTypeObj = {
				"issueType":$(this).val(),
				"tickerFor":$("#ttForID").val(),
				"ticketType":$("#ttTypeID").val(),
			   };
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getIssues', 
	      	     data:issueTypeObj,
	      	     success: function(data) { 
	      	    	 $.each(data, function(key, value){ 
	      	    		$('#issueID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
	      	     }
	      	     });
	 });
	
$("#fromDatepickerID").datepicker({
		format : 'dd-mm-yy',
		autoclose : true,
		changeMonth: true,
        changeYear: true,
        maxDate:new Date(),
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate());
           
            $("#toDatepickerID").datepicker("option", "minDate", dt);
        }
	});
	 
		
	 $("#fromDatepickerID").datepicker({
			maxDate : new Date(),
		});
	 
	$("#toDatepickerID").datepicker({
		format : 'dd-mm-yy',
		autoclose : true,
		changeMonth: true,
        changeYear: true,
        maxDate:new Date(),
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate());
            $("#fromDatepickerID").datepicker("option", "maxDate", dt);
        }
	});
	
	$(document).on('click', '.popper', function(event){
		
		 $("#jsonErrListID1").empty();
		 $( ".selector" ).tooltip( "close" );
		 var ticketNo = $(this).closest('tr').find('.ticketNoClass').val();
			var createdBy = $(this).closest('tr').find('.createdByClass').val();	
			var ticketDesc = $(this).closest('tr').find('.ticketDesClass').val();
			var t='';
			var tt ='';

			//var ticketNo = $(this).closest('tr').find('.cafNOHiddenClass').val();
			var createdBy = $(this).closest('tr').find('.lNameClass').val();	
			var oltSerialNoClass = $(this).closest('tr').find('.oltSerialNoClass').val();
			var ticketDesc = $(this).closest('tr').find('.portClass').val();
			var oltONUID = $(this).closest('tr').find('.oltONUIDClass').val();
			var apsflTrack = $(this).closest('tr').find('.apsflTrackIDClass').val();
			var aadharNo = $(this).closest('tr').find('.aadharNoClass').val();
			var pocMob1 = $(this).closest('tr').find('.pocMob1Class').val();
			var apsflLandlineNo = $(this).closest('tr').find('.apsflLandlineNoClass').val();
			var email1 = $(this).closest('tr').find('.email1Class').val();
			var address1 = $(this).closest('tr').find('.address1Class').val();
			var districtName = $(this).closest('tr').find('.districtNameClass').val();
			var mandalName = $(this).closest('tr').find('.mandalNameClass').val();
			var subStationName = $(this).closest('tr').find('.subStationNameClass').val();
		//	var villageName = $(this).closest('tr').find('.villageNameClass').val();
					t=t+'<table class="table table-alt" >'
					t= t+'<thead><tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>TT Number: </strong>'+ticketNo+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Aadhar No: </strong>'+createdBy+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Mobile No: </strong>'+oltSerialNoClass+'</td></tr>'
					
					+'<tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>LandLine No: </strong>'+ticketDesc+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Assigned To: </strong>'+oltONUID+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Ticket Priority: </strong>'+apsflTrack+'</td></tr>'
					
					+'<tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>Complaint: </strong>'+aadharNo+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Ticket Reason: </strong>'+pocMob1+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Parent Ticket No: </strong>'+apsflLandlineNo+'</td></tr>'
					
					+'<tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>Child Ticket No: </strong>'+email1+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Issue Category : </strong>'+address1+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>District Name: </strong>'+districtName+'</td></tr>'
					
					+'<tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>SubStations : </strong>'+mandalName+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Created By: </strong>'+subStationName+'</td></tr>'
					
				//	+'<tr><td colspan="4" style="border:none;"><strong>Village: </strong>'+villageName+'</td></tr>'
					 t+'</thead><tbody>';
					 t = t+tt+"</tbody></table>";
					 
					 $("#jsonErrListID1").append(t);
			    	  
	 });
	 

	 
$(document).on('click', '.ttHistoryClass', function(){

//alert("in clcik11 krishh");

$("#jsonErrListID").empty();
var ticketNo = $(this).closest('tr').find('.ticketNoClass').val();
var createdBy = $(this).closest('tr').find('.createdByClass').val();	
var ticketDesc = $(this).closest('tr').find('.ticketDesClass').val();
var t='';
var tt ='';
		$("#ticketNoID").val(ticketNo);
		t= t+'<thead><tr><td colspan="4" style="border:none;"><strong>TT Number: </strong>'+ticketNo+'</td></tr> <tr><td colspan="4" style="border:none;"><strong>Created By: </strong>'+createdBy+'</td></tr><tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>Complaint: </strong>'+ticketDesc+'</td></tr>';
		//alert('ticketNo'+ticketNo);
		t= t+'<tr><th>Date</th><th>Assign To</th><th>Assign To Mobile No</th><th>Remarks</th><th>Status</th></tr></thead><tbody>';
		$('.trrClass').remove();
		$.ajax({ 
		     type: 'GET', 
		     url:  'getTTHistory?ttNumber='+ticketNo,
		     async: false,
		     success: function(data){ 
		    	//alert('success21'+data);
		    	 $.each(data, function(idx, obj){ 
		    	 
		    	//	 alert(obj);
		    		 tt = tt + ' <tr class="trrClass"> '+
						' <td><div id="reqjson" class="jjson">'+obj.effectiveFrm+'</div></td> '+
						' <td><div id="resjson" class="jjson">'+obj.assignedTo+'</div></td> '+
						' <td><div id="resjson" class="jjson">'+obj.mobileNo+'</div></td> '+
						' <td><span>'+obj.remarks+'</span></td> '+
						' <td>'+obj.currentStatus+'</td> '+
						' </tr> ';
		    		// i++;
		    	 });
		    	 
		    	 t = t+tt+"</tbody>";
		    	// alert(t+"t");
		    	 $("#jsonErrListID").append(t);
		    	
		    	 
		     }
		
		 
		});
		
});
	
$("#tenantCodeID").val("${tenantCode}");
	$(document).on('click', '.updateTTClass', function(){
		var ticketNo = $(this).closest('tr').find('.ticketNoClass').val();
		$("#ticketNoID").val(ticketNo);
		$("#createTTFormID").attr('action',"createTroubleTicket.do");
		$("#createTTFormID").submit();
	});
	
	
	  $('.exportClass').click(function() {
	  	 ttNumber = $("#ttNumberID").val();
	     contactLandline = $("#landlineNoID").val();
	     contactMobile = $("#MobileNoID").val();
	     ticketPriority = $("#priorityID :selected").val();
	     status = $("#statusID :selected").val();
	     fromDate = $("#fromDatepickerID").val();
	     toDate = $("#toDatepickerID").val();
	     ticketFor = $("#ttForID").val();
	     ttType = $("#ttTypeID").val();
	     issueType = $("#issueTypeID").val();
	     issue = $("#issueID").val();
	     cafNo = $("#cafNoIID").val();
	     district = $("#districtID").val();
	     actualIssue = $("#actualIssueID").val();
		 url = "getTroubleTicketInfoDownload?ttNumber="+ttNumber+"&contactLandline="+contactLandline+"&contactMobile="+contactMobile+"&ticketPriority="+ticketPriority+"&status="+status+"&fromDate="+fromDate+"&toDate="+toDate+"&ticketFor="+ticketFor+"&ttType="+ttType+"&issueType="+issueType+"&issue="+issue+"&cafNo="+cafNo+"&district="+district+"&actualIssue="+actualIssue;
		 downloadFile();
		 function downloadFile() {
			  window.location.href = url;
			}
	 }); 
	 
	  
});


</script>     
<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong>Trouble Ticket</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./home.do">Home</a> </li>
            <li class="active">Update TT</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="createTTFormID" method="post" commandName="troubleTicketDTO">
			
			<input type="hidden" name="ticketFor" id="ticketForHiddenID"/>
			<input type="hidden" name="ticketNo" id="ticketNoID"/>
			<input type="hidden" name="flag" id="flagID" value="updateTTFLAG"/>
			<input type="hidden" name="tenantcode" id="tenantCodeID"/>
			<input type="hidden" name="image" id="image"/>
			  <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3>Update TT</h3>
                    </div>
                    <div class="panel-content" align="center">
                      <div class="row">
                      <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">Ticket For</label>
					    
                      		<form:select path="ticketFor" id="ttForID" name="ticketFor" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Ticket For--" />
								<form:options items="${ticketFor}"/>
							</form:select>
                    		</div>
                        </div>
                       <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">TT Type</label> 
                      		<form:select path="ticketType" name="ttType" id="ttTypeID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select TT Type--" />
							</form:select>
                    		</div>
                        </div>
                         <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">Issue Type</label> 
                      			<form:select path="issueType" name="issueType" id="issueTypeID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Issue Type--" />
							</form:select>
                    		</div>
                        </div>
                        <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">Issue</label> 
                      		<form:select path="issueCode" id="issueID" name="issue" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Issue--" />
							</form:select>
                    		</div>
                        </div>
                        </div>
                    <div class="row">
                      <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">TT Number</label> 
                      		<input type="text" name="ttNumber" id="ttNumberID" name="ttNumber" class="form-control form-white ttNumber" maxlength="50" style="width: 200px; " placeholder="Enter TT Number">
                      		
                    		</div>
                        </div>
                        
                        <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">Landline Number</label> 
                      		<input type="text" name="contactLandline" id="landlineNoID" name="contactLandline" class="form-control form-white contactLandline" style="width: 200px; " maxlength="50" placeholder="Enter Landline Number">
                      	
                    		</div>
                        </div>
                         <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">Mobile Number</label> 
                      		<input type="text" name="contactMobile" id="MobileNoID" name="contactMobile" class="form-control form-white contactMobile" style="width: 200px; " maxlength="15" placeholder="Enter Mobile Number">
                      		
                    		</div>
                        </div>
                        <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Priority</label> 														   
							<form:select path="ticketPriority" id="priorityID" style="width: 200px; " name="ticketPriority" class="form-control form-white ticketPriority">
								<form:option value="-1" label="--Select Priority--" />
								<form:options items="${issuePriorityMap}"/>
							</form:select>
						</div>
						</div>
                       </div>
                         <div class="row">
                        <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">From</label> 														   
							<input type="text" name="fromDate" id="fromDatepickerID" name="fromDate" class="date-picker form-control form-white fromDatepickerID fromDate"  style="width: 200px; " maxlength="50" placeholder="From">
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">To</label> 														   
							<input type="text" name="toDate" id="toDatepickerID" name="toDate" class="date-picker form-control form-white fromDatepickerID toDate" style="width: 200px; " maxlength="50" placeholder="To">
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Status</label> 														   
							<form:select path="status" id="statusID" style="width: 200px; " name="status" class="form-control form-white status">
								<form:option value="-1" label="--Select Status--" />
								<form:options items="${statusMap}"/>
							</form:select>
						</div>
						</div>
						 <div class="col-sm-3">
	                  <div class="form-group">
	                     	<label class="control-label">Account No</label> 
	                     	<input type="text" name="cafNo" id="cafNoIID" style="width: 200px; " name="cafNO" class="form-control form-white cafNo" maxlength="20" placeholder="Enter Account No">
	                   	</div>
                       </div>
					  </div>
					  
					<div class="row">
                   			<div class="col-sm-3" >
						<div class="form-group">													
						<label class="control-label">District</label>&nbsp;
							<form:select path="districts" id="districtID" style="width: 200px; " name="district" class="form-control form-white select districts">
								<form:option value="-1" label="--Select District--" />
								<form:options items="${districtsMap}" />
							</form:select> 
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Closure Reason</label> 														   
							<form:select path="actualIssue" id="actualIssueID" style="width: 200px; " name="actualIssue" class="form-control form-white actualIssue">
								<form:option value="-1" label="--Select Closure Reason--" />
								<form:options items="${actualTicketMap}"/>
							</form:select>
						</div>
						</div>
						 <div class="col-sm-3">
                   			<div class="form-group" style="padding:15px 15px 15px 15px">
                      		
                      		<input type="button" id="searchID" value="Search" class="btn btn-embossed btn-primary" />
                      		
                    		</div>
                        </div>
					</div>
                         
                        <div class="row">
                       <div class="col-sm-12" id="ttHistoryDivID">
											</div> 
											</div>   
											
			          <div class="row m-b-5" id="ttHistorydiv">
						<div class="col-sm-12">
							<table class="table table-alt" id="ttHistoryTable">
								<thead>
								<tr class="exportClass" style="height:30px;display: none;"><td colspan="29"  style="float:left;"><img width="30px" height="30px" src="./resources/images/excel.jpg"></td></tr>
									<tr class="headerClass">
										<th>TT NO</th>
									 	<th>Account NO</th>
									 	<th>Issue Type</th>
									 	<th>Issue</th>
									 	<th>Name</th>
									 	<th>Olt Serial No</th>
									 	<th>Port</th>
									 	<th>Olt ONUID</th>
									 	<th>Created On</th>
									 	<th>Expected Closure Date</th>
									 	<th>Actual Closed Time</th>
									 	<th>Resolution Time</th>
									 	<th>Status</th>
										<th class="unknwnColumns">View TT Info</th>
									 	<th class="unknwnColumns">Attached Files</th>
									 	<th class="unknwnColumns">Update TT</th>
									    <th class="unknwnColumns">TT History</th>
									    <th style="display:none;">Complaint</th>
									    <th style="display:none;">Aadhar No</th>
									    <th style="display:none;">Mobile No</th>
									 	<th style="display:none;">LandLine No</th>
									 	<th style="display:none;">Assigned To</th>
									 	<th style="display:none;">Priority</th>
									 	<th style="display:none;">Reason</th>
									 	<th style="display:none;">Parent Ticket No.</th>
									 	<th style="display:none;">Child Ticket Nos</th>
									 	<th style="display:none;">Issue Category</th>
									 	<th style="display:none;">District</th>
									 	<th style="display:none;">Substation</th>
									 	<th style="display:none;">Created By</th>
									</tr>
								</thead>
							</table>
							<form name="cafForm" id="cafForm">
								<input type="hidden" id="cafText" name="cafNo"/>
							</form>
						</div>
					</div>
											
											<!-- Modal -->
										<div id="myModal" class="modal fade" role="dialog">
											<div class="modal-dialog ">
												<!-- Modal content-->
												<div class="modal-content model-div-size">
													<div class="modal-header">
														<h3 class="modal-title"> TT Status
															<button type="button" class="btn btn-default" id="popUpCloseButtonId" style="float: right; clear: right; margin-top: -0.5em;" data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
															<div class="panel panel-default" style="width: 1160px; overflow-x: auto;">
																<div class="ScrollStyle" style="width: 1162px; overflow-x: auto;">
																	<table class="table table-alt" id="jsonErrListID">
																		
																	</table>
																</div>
																</div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
										</div>
										<div id="myModal1" class="modal fade" role="dialog">
											<div class="modal-dialog ">
												<!-- Modal content-->
												<div class="modal-content model-div-size">
													<div class="modal-header">
														<h3 class="modal-title"> View TT Details
															<button type="button" class="btn btn-default" id="popUpCloseButtonId" style="float: right; clear: right; margin-top: -0.5em;" data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
															<div class="panel panel-default" style="width: 1160px; overflow-x: auto;">
																<div class="ScrollStyle" style="width: 1161px; overflow-x: auto;">
																	<table class="table table-alt" id="jsonErrListID1">
																		
																	</table>
																</div>
																</div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
										</div>
										<!--model-->       		  
                      </div>
                      </div>
                   </div>
                   </div>
                  </form:form>
                </div>
              </div>
              <!-- END ROW -->   
              <!-- END FORM -->
            </div>
            <!-- END MAIN PANEL CONTENT --> 
          </div>
          <!-- END MAIN PANEL --> 
        </div>
        <!-- HERE COMES YOUR CONTENT --> 
      </div>
      <!-- END MAIN ROW -->
      <!-- END PAGE CONTENT -->   
 
  <!-- END MAIN CONTENT --> 
<style>	
a {
    cursor: pointer;
}
.popbox {
    display: none;
    position: absolute;
    z-index: 99999;
    width: 400px;
    padding: 10px;
    background: #EEEFEB;
    color: #000000;
    border: 1px solid #4D4F53;
    margin: 0px;
    -webkit-box-shadow: 0px 0px 5px 0px rgba(164, 164, 164, 1);
    box-shadow: 0px 0px 5px 0px rgba(164, 164, 164, 1);
}
</style>

<!-- <script>
$(document).ready(function() {
	$("#pop1").hide();
	
	var troubleTicketDTO = {
		    "ttNumber": $("#ttNumberID").val(),
		    "contactLandline": $("#landlineNoID").val(),
		    "contactMobile": $("#MobileNoID").val(),
		    "ticketPriority": $("#priorityID :selected").val(),
		    "status": 9,
		    "fromDate": $("#fromDatepickerID").val(),
		    "toDate": $("#toDatepickerID").val(),
		    "ticketFor": $("#ttForID").val(),
		    "ttType": $("#ttTypeID").val(),
		    "issueType": $("#issueTypeID").val(),
		    "issue": $("#issueID").val(),
		    "cafNo": $("#cafNoIID").val(),
		    "district": $("#districtID").val(),
		    "actualIssue":$("#actualIssueID").val(),
		};
	
	   $.ajax({ 
    	     type: 'GET', 
    	     //contentType : 'application/json; charset=utf-8',
    	     dataType : 'json',
    	     async: false,
    	     url: 'getTTsInfo', 
    	     data:troubleTicketDTO,
    	     beforeSend: function() {
				   ajaxindicatorstart("Loading...")
	            },
            complete: function() {
	            ajaxindicatorstop()
	            },

    	     success: function(data) { 
    	    	 
    	    	// alert("in success");
    	    	 //alert(data.length);
				 var html = "";
				 var tabelBody ="";
				 html ='<table  class="table table-alt data_table  datatable full-width" id="ttHistoryID" style="width: 100%;">'
					 	+' <thead>'
					 	+'<tr style="height:30px;"><td colspan="29"  style="float:left;"><a href="./getTTsInfoDownload" class="exportClass"><img width="30px" height="30px" src="./resources/images/excel.jpg"></a></td></tr>' 
					 	+'<tr class="headerClass">'
					 	+'<th>TT NO</th>'
					 	+'<th>Account NO</th>'
					 	+'<th>Issue Type</th>'
					 	+'<th>Issue</th>'
					 	+'<th>Name</th>'
					 	+'<th>Olt Serial No</th>'
					 	+'<th>Port</th>'
					 	+'<th>Olt ONUID</th>'
					 	+'<th>Created On</th>'
					 	+'<th>Expected Closure Date</th>'
					 	+'<th>Actual Closed Time</th>'
					 	+'<th>Resolution Time</th>'
					 	+'<th>Status</th>'
					 //	+'<th style="width:2px;">Complaint</th>'
						+'<th class="unknwnColumns">View TT Info</th>'
					 	+'<th class="unknwnColumns">Attached Files</th>'
					 	+'<th class="unknwnColumns">Update TT</th>'
					    +'<th class="unknwnColumns">TT History</th>' 
					    +'<th style="display:none;  width: 1px;">Complaint</th>' 
					    +'<th style="display:none;  width: 1px;">Aadhar No</th>' 
					    +'<th style="display:none;  width: 1px;">Mobile No</th>'
					 	+'<th style="display:none;  width: 1px;">LandLine No</th>' 
					 	+'<th style="display:none;  width: 1px;">Assigned To</th>'
					 	+'<th style="display:none;  width: 1px;">Priority</th>'
					 	+'<th style="display:none;  width: 1px;">Reason</th>'
					 	+'<th style="display:none;  width: 1px;">Parent Ticket No.</th>'
					 	+'<th style="display:none;  width: 1px;">Child Ticket Nos</th>'
					 	+'<th style="display:none;  width: 1px;">Issue Category</th>'
					 	+'<th style="display:none;  width: 1px;">District</th>'
					 	+'<th style="display:none;  width: 1px;">Substation</th>'
					 	+'<th style="display:none;  width: 1px;">Created By</th>'
					 	+'</tr>'
					 	+'</thead>'
					 	+'<tbody>';
					 	if(data.length == 0){
					 		$('#ttHistoryID').dataTable( {
					 			  "columns": [
					 			    { "searchable": false },
					 			    null,
					 			    null,
					 			    null,
					 			    null,
					 			   null,
					 			    null,
					 			   null,
					 			    null,
					 			    null,
					 			   null,
					 			   null,
					 			    null,
					 			    null,
					 			    null,
					 			   null,
					 			  null,
					 			    null,
					 			    null,
					 			    null,
					 			   null,
					 			   null,
					 			   null,
					 			   null	,
					 			  null,
					 			 null,
						 			null,
					 			 null,
					 			null,
					 			   null	]
					 			} );
					 	}
					 	else{
				 $.each(data, function(idx, obj){ 
					 html = html +'<tr>'
					    +'<input type="hidden" class="lNameClass" value="'+obj.aadharNo+'"/>'
		 				+'<input type="hidden" class="oltSerialNoClass" value="'+obj.contactMobile+'"/>'
		 				+'<input type="hidden" class="portClass" value="'+obj.contactLandline+'"/>'
		 				+'<input type="hidden" class="oltONUIDClass" value="'+obj.assignedTo+'"/>'
		 				+'<input type="hidden" class="apsflTrackIDClass" value="'+obj.ticketPriority+'"/>'
		 				+'<input type="hidden" class="aadharNoClass" value="'+obj.ticketDesc+'"/>'
		 				+'<input type="hidden" class="pocMob1Class" value="'+obj.ticketReason+'"/>'
		 				+'<input type="hidden" class="email1Class" value="'+obj.childTicketNos+'"/>'
		 				+'<input type="hidden" class="address1Class" value="'+obj.actualIssue+'"/>'
		 				+'<input type="hidden" class="districtNameClass" value=  "'+obj.districts+'" />'
		 				+'<input type="hidden" class="mandalNameClass" value="'+obj.subStationName+'"/>'
		 				+'<input type="hidden" class="subStationNameClass" value="'+obj.userName+'"/>'
		 			 				+'<td>'+obj.ticketNo+'</td>';
					 				 if(obj.parentticketno !=null)
									 {
										 html = html +'<input type="hidden" class="apsflLandlineNoClass" value="'+obj.parentticketno+'"/>';
									 }
								     else{
								      	 html = html+'<input type="hidden" class="apsflLandlineNoClass" value=""/>';
								     }
					 				if(obj.cafNo !=null)
				 					{
					 					html = html+'<td>'+obj.cafNo+'</td>';
				 					}
					 				else{
					 					html = html+'<td></td>';
					 				}
					 				if(obj.issueType !=null)
				 					{
					 					html = html+'<td>'+obj.issueType+'</td>';
				 					}
					 				else{
					 					html = html+'<td></td>';
					 				}
					 				if(obj.issue !=null)
				 					{
					 					html = html+'<td>'+obj.issue+'</td>';
				 					}
					 				else{
					 					html = html+'<td></td>';
					 				}
					 				if(obj.custName !=null)
				 					{
					 					html = html+'<td>'+obj.custName+'</td>';
				 					}
					 				else{
					 					html = html+'<td></td>';
					 				}
					 				
					 				if(obj.oltSerialNo !=null)
					 				{
					 				html = html+'<td>'+obj.oltSerialNo+'</td>';
					 				}
					 				else{
					 				html = html+'<td></td>';
					 				}
					 				if(obj.port !=null)
					 				{
					 				html = html+'<td>'+obj.port+'</td>';
					 				}
					 				else{
					 				html = html+'<td></td>';
					 				}
					 				if(obj.oltONUID !=null)
					 				{
					 				html = html+'<td>'+obj.oltONUID+'</td>';
					 				}
					 				else{
					 				html = html+'<td></td>';
					 				}
					 				if(obj.createdOnDate !=null)
				 					{
					 					html = html+'<td>'+obj.createdOnDate+'</td>';
				 					}
					 				else{
					 					html = html+'<td></td>';
					 				}
					 				if(obj.expectedClosureDate !=null)
				 					{
					 					html = html+'<td>'+obj.expectedClosureDate+'</td>';
				 					}
					 				else{
					 					html = html+'<td></td>';
					 				}
					 				if(obj.modifiedOnString !=null)
				 					{
					 					html = html+'<td>'+obj.modifiedOnString+'</td>';
				 					}
					 				else{
					 					html = html+'<td></td>';
					 				}
					 				if(obj.timeDiff !=null)
					 					{
					 					html = html+'<td>'+obj.timeDiff+'</td>';
					 					}
					 				else{
					 					html = html+'<td></td>';
					 				}
					 				
					 				html = html+'<td>'+obj.currentStatus+'</td>';
					 				
					 				/* if(obj.ticketDesc !=null)
				 					{
					 					html = html+'<td width:2px;">'+obj.ticketDesc+'</td>';
				 					}
					 				else{
					 					html = html+'<td width:2px;"></td>';
					 				} */
									html = html+'<td class="unknwnColumns"><a id="popAnchorID" href="#" class="popper" data-toggle="modal" data-target="#myModal1" >View TT Info</a></td>';
					 				
					 				if(obj.imagePath !=null)
					 				{
					 					html = html+'<td class="unknwnColumns"><input type="hidden" class="imagePath" value="'+obj.imagePath+'"><button type="button" class="btn btn-embossed btn-primary imagepathh" style=" margin-left: 9px; margin-top: 10px; width:38px "><i class="fa fa-download imagepathh" aria-hidden="true"></i></button></td>';
					 				}
					 				else{
					 				html = html+'<td class="unknwnColumns"></td>';
					 				}
					 				  if(obj.actionFlag == "N"){
					 					 html = html +  '<td class="unknwnColumns"></td>';
					 				  }
					 				  else{
					 					 html = html +'<td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+obj.ticketNo+'"><a href="#" class="updateTTClass">Update TT</a></td>';
					 				  }
					 				 html = html +'<td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+obj.ticketNo+'"><input type="hidden" class="ticketDesClass" value="'+obj.ticketDesc+'"><input type="hidden" class="createdByClass" value="'+obj.createdBy+'"><a href="#" class="ttHistoryClass" data-toggle="modal" data-target="#myModal">TT History</a></td>';
					 				 
					 			    if(obj.ticketDesc !=null)
					 				{
					 				html = html+'<td style="display:none;  width: 1px;">'+obj.ticketDesc+'</td>';
					 				}
					 				else{
					 				html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 			    if(obj.aadharNo !=null)
					 				{
					 				html = html+'<td style="display:none;  width: 1px;">'+obj.aadharNo+'</td>';
					 				}
					 				else{
					 				html = html+'<td style="display:none;  width: 1px;"></td>';
					 				} 
					 				if(obj.contactMobile !=null)
				 					{
					 					html = html+'<td style="display:none;  width: 1px;">'+obj.contactMobile+'</td>';
				 					}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.contactLandline !=null)
				 					{
					 					html = html+'<td style="display:none;  width: 1px;">'+obj.contactLandline+'</td>';
				 					}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.assignedTo !=null)
				 					{
					 					html = html+'<td style="display:none;  width: 1px;">'+obj.assignedTo+'</td>';
				 					}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.ticketPriority !=null)
				 					{
					 					html = html+'<td style="display:none;  width: 1px;">'+obj.ticketPriority+'</td>';
				 					}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				
					 				if(obj.ticketReason !=null)
				 					{
					 					html = html+'<td style="display:none;  width: 1px;">'+obj.ticketReason+'</td>';
				 					}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.parentticketno !=null)
				 					{
					 					html = html+'<td style="display:none;  width: 1px;">'+obj.parentticketno+'</td>';
				 					}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.childTicketNos !=null)
				 					{
					 					html = html+'<td style="display:none;  width: 1px;">'+obj.childTicketNos+'</td>';
				 					}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.actualIssue !=null)
				 					{
					 					html = html+'<td style="display:none;  width: 1px;">'+obj.actualIssue+'</td>';
				 					}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.districts !=null)
					 				{
					 				html = html+'<td style="display:none;  width: 1px;">'+obj.districts+'</td>';
					 				}
					 				else{
					 				html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.subStationName !=null)
					 				{
					 				html = html+'<td style="display:none;  width: 1px;">'+obj.subStationName+'</td>';
					 				}
					 				else{
					 				html = html+'<td style="display:none;  width: 1px;"></td>';
					 				}
					 				if(obj.userName !=null)
					 				{
					 				html = html+'<td style="display:none;  width: 1px;">'+obj.userName+'</td>';
					 				}
					 				else{
					 					html = html+'<td style="display:none;  width: 1px;"></td>';} 
					 				 
					 				html = html+'</tr>';
				 });
				 	}
				 html = html + '</tbody>'
				 				+ '</table>';
				 $('#ttHistoryDivID').append(html);
				  $("#ttHistoryID").dataTable({
				    	"scrollY": 700
				    	// "scrollX":true
				    }); 
		   
	}
    	     });
	
	$("#ttForID").change(function(){
		//alert('in change');
		$("#ttTypeID").empty();
		$("#ttTypeID").append('<option value="-1">--Select TT Type--</option>');
		$("#ttTypeID").select2("val", "-1");
		$("#issueTypeID").empty();
		$("#issueTypeID").append('<option value="-1">--Select Issue Type--</option>');
		$("#issueTypeID").select2("val", "-1");
		$("#issueID").empty();
		$("#issueID").append('<option value="-1">--Select Issue--</option>');
		$("#issueID").select2("val", "-1");
		
		var issueTypeObj = {
				"ttFor":$(this).val(),
				  };
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     //contentType : 'application/json; charset=utf-8',
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getTTTypes', 
	      	     data:issueTypeObj,
	      	     success: function(data) { 
	      	    	// alert(data+"data");
	      	    	 $.each(data, function(key, value){ 
	      	    		$('#ttTypeID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
	      	     }
	      	     });
	 });
	
	
	$("#ttTypeID").change(function(){
		//alert('in change');
		$("#issueTypeID").empty();
		$("#issueTypeID").append('<option value="-1">--Select Issue Type--</option>');
		$("#issueTypeID").select2("val", "-1");
		$("#issueID").empty();
		$("#issueID").append('<option value="-1">--Select Issue--</option>');
		$("#issueID").select2("val", "-1");
		
		var issueTypeObj = {
				"ttType":$(this).val(),
				"ticketFor":$("#ttForID").val(),
			   };
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     //contentType : 'application/json; charset=utf-8',
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getIssueTypes', 
	      	     data:issueTypeObj,
	      	     success: function(data) { 
	      	    	// alert(data+"data");
	      	    	 $.each(data, function(key, value){ 
	      	    		$('#issueTypeID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
	      	     }
	      	     });
	 });
	
	$("#issueTypeID").change(function(){
		$("#issueID").empty();
		$('#issueID').append('<option value="-1">--Select Issue--</option>');
		$("#issueID").select2("val", "-1");

		var issueTypeObj = {
				"issueType":$(this).val(),
				"tickerFor":$("#ttForID").val(),
				"ticketType":$("#ttTypeID").val(),
			   };
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getIssues', 
	      	     data:issueTypeObj,
	      	     success: function(data) { 
	      	    	 $.each(data, function(key, value){ 
	      	    		$('#issueID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
	      	     }
	      	     });
	 });
	
	
	
	
	 /* $(document).on("click", ".exportClass", function(e) {
		$('#ttHistoryID').tableExport({type:'excel',escape:'false',ignoreColumn:[15,16]});
	});  */
	
	 /* $(document).on("click", ".exportClass", function(e) {
		 //alert("inside click");
		var a= $('<table>').append($("#ttHistoryID").clone().find('tr+tr+tr').remove().end());
		a.find("tr:first-child").remove();
		a.find("tr:eq(1)").remove();  
		a.find('.headerClass').attr('style','height:30px');
		a.find('th').attr('style','height:30px');
		a.find('th').attr('style','width:100px');
		a.find('td').attr('style','width:100px');
		//alert(a.html());
		a.find('.unknwnColumns').remove().end(); 
		var aa=$("#ttHistoryID").DataTable().$('tr').clone();
		aa.find('td').attr('style','width:100px');
		aa.find('.unknwnColumns').remove().end();
		a.append(aa).table2excel({
	           // exclude: ".unknwnColumns",
	          
	            name: "TT_Update",
	            filename:"{{sample}}"
	        }); 
	     });  */
	 /*    $("#ttHistoryID").dataTable({
	    	"scrollY": 200,
	        "scrollX": true
	    }); 
	
	$("#ticketForHiddenID").val("${ticketFor}");
	
	/* $("#fromDatepickerID").datepicker("option", "maxDate", new Date());
	 
	 $("#toDatepickerID").datepicker("option", "maxDate", new Date()); */
	 
	$("#fromDatepickerID").datepicker({
		
		format : 'dd-mm-yy',
		autoclose : true,
		changeMonth: true,
        changeYear: true,
        maxDate:new Date(),
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate());
           
            $("#toDatepickerID").datepicker("option", "minDate", dt);
        }
	});
	 
	 /* var date = new Date();
		var currentMonth = date.getMonth();
		var currentDate = date.getDate();
		var currentYear = date.getFullYear(); */
		
	 $("#fromDatepickerID").datepicker({
			maxDate : new Date(),
		});
	 
	$("#toDatepickerID").datepicker({
		
		format : 'dd-mm-yy',
		//startDate : '0d',
		autoclose : true,
		changeMonth: true,
        changeYear: true,
        maxDate:new Date(),
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate());
           
            $("#fromDatepickerID").datepicker("option", "maxDate", dt);
        }
	});
	
	
	 $("#toDatepickerID").datepicker({
			maxDate : new Date(),
		});
	

	/* $('.toDatepickerID').datepicker().on('changeDate',function(ev) {
		alert("change date to");
				$(".fromDatepickerID").datepicker("setEndDate", new Date(ev.date));
			});
	 */
	/* $("#fromDatepickerID").datepicker({
		onSelect: function(selected) {
	          $("#toDatepickerID").datepicker("option","minDate", selected)
	        }
	});
	
	$("#toDatepickerID").datepicker({
		onSelect: function(selected) {
	           $("#txtFromDate").datepicker("option","maxDate", selected)
	        }
		}); */
	
	$(document).on('click', '.updateTTClass', function(){
		var ticketNo = $(this).closest('tr').find('.ticketNoClass').val();
		
		$("#ticketNoID").val(ticketNo);
		$("#createTTFormID").attr('action',"createTroubleTicket.do");
		$("#createTTFormID").submit();
		
		
	});
		
		 $(document).on('click', '.imagepathh', function(){
			 
			// alert("On Click");
			 var imagepath = $(this).closest('tr').find('.imagePath').val();

			 $("#image").val(imagepath);
			 $("#createTTFormID").attr('action',"downloadImage.do");
			 $("#createTTFormID").submit();


			 });
		 
		 $(document).on('click', '.popper', function(event){
		
			 $("#jsonErrListID1").empty();
			 $( ".selector" ).tooltip( "close" );
			 var ticketNo = $(this).closest('tr').find('.ticketNoClass').val();
				var createdBy = $(this).closest('tr').find('.createdByClass').val();	
				var ticketDesc = $(this).closest('tr').find('.ticketDesClass').val();
				var t='';
				var tt ='';

				//var ticketNo = $(this).closest('tr').find('.cafNOHiddenClass').val();
				var createdBy = $(this).closest('tr').find('.lNameClass').val();	
				var oltSerialNoClass = $(this).closest('tr').find('.oltSerialNoClass').val();
				var ticketDesc = $(this).closest('tr').find('.portClass').val();
				var oltONUID = $(this).closest('tr').find('.oltONUIDClass').val();
				var apsflTrack = $(this).closest('tr').find('.apsflTrackIDClass').val();
				var aadharNo = $(this).closest('tr').find('.aadharNoClass').val();
				var pocMob1 = $(this).closest('tr').find('.pocMob1Class').val();
				var apsflLandlineNo = $(this).closest('tr').find('.apsflLandlineNoClass').val();
				var email1 = $(this).closest('tr').find('.email1Class').val();
				var address1 = $(this).closest('tr').find('.address1Class').val();
				var districtName = $(this).closest('tr').find('.districtNameClass').val();
				var mandalName = $(this).closest('tr').find('.mandalNameClass').val();
				var subStationName = $(this).closest('tr').find('.subStationNameClass').val();
			//	var villageName = $(this).closest('tr').find('.villageNameClass').val();
						t=t+'<table class="table table-alt" >'
						t= t+'<thead><tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>TT Number: </strong>'+ticketNo+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Aadhar No: </strong>'+createdBy+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Mobile No: </strong>'+oltSerialNoClass+'</td></tr>'
						
						+'<tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>LandLine No: </strong>'+ticketDesc+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Assigned To: </strong>'+oltONUID+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Ticket Priority: </strong>'+apsflTrack+'</td></tr>'
						
						+'<tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>Complaint: </strong>'+aadharNo+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Ticket Reason: </strong>'+pocMob1+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Parent Ticket No: </strong>'+apsflLandlineNo+'</td></tr>'
						
						+'<tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>Child Ticket No: </strong>'+email1+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Issue Category : </strong>'+address1+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>District Name: </strong>'+districtName+'</td></tr>'
						
						+'<tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>SubStations : </strong>'+mandalName+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Created By: </strong>'+subStationName+'</td></tr>'
						
					//	+'<tr><td colspan="4" style="border:none;"><strong>Village: </strong>'+villageName+'</td></tr>'
						 t+'</thead><tbody>';
						 t = t+tt+"</tbody></table>";
						 
						 $("#jsonErrListID1").append(t);
						 
				    	// $( '.popper' ).tooltip({ content: $(t).html(),tooltipClass: 'tooltipclass' });
				    	 
				    	 
				    	 
				    	  
		 });
		 
	/* 	 $(document).on('mouseleave', '.popper', function(){
			 $('.popper').tooltip('close'); 
		});
		 
		 $(document).on('mouseenter', '.popper', function(){
			 $('.popper').tooltip('open'); 
		}); */
		 
$(document).on('click', '.ttHistoryClass', function(){
	
	//alert("in clcik11 krishh");
	
	$("#jsonErrListID").empty();
	var ticketNo = $(this).closest('tr').find('.ticketNoClass').val();
	var createdBy = $(this).closest('tr').find('.createdByClass').val();	
	var ticketDesc = $(this).closest('tr').find('.ticketDesClass').val();
	var t='';
	var tt ='';
			$("#ticketNoID").val(ticketNo);
			t= t+'<thead><tr><td colspan="4" style="border:none;"><strong>TT Number: </strong>'+ticketNo+'</td></tr> <tr><td colspan="4" style="border:none;"><strong>Created By: </strong>'+createdBy+'</td></tr><tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>Complaint: </strong>'+ticketDesc+'</td></tr>';
			//alert('ticketNo'+ticketNo);
			t= t+'<tr><th>Date</th><th>Assign To</th><th>Assign To Mobile No</th><th>Remarks</th><th>Status</th></tr></thead><tbody>';
			$('.trrClass').remove();
			$.ajax({ 
			     type: 'GET', 
			     url:  'getTTHistory?ttNumber='+ticketNo,
			     async: false,
			     success: function(data){ 
			    	//alert('success21'+data);
			    	 $.each(data, function(idx, obj){ 
			    	 
			    	//	 alert(obj);
			    		 tt = tt + ' <tr class="trrClass"> '+
							' <td><div id="reqjson" class="jjson">'+obj.effectiveFrm+'</div></td> '+
							' <td><div id="resjson" class="jjson">'+obj.assignedTo+'</div></td> '+
							' <td><div id="resjson" class="jjson">'+obj.mobileNo+'</div></td> '+
							' <td><span>'+obj.remarks+'</span></td> '+
							' <td>'+obj.currentStatus+'</td> '+
							' </tr> ';
			    		// i++;
			    	 });
			    	 
			    	 t = t+tt+"</tbody>";
			    	// alert(t+"t");
			    	 $("#jsonErrListID").append(t);
			    	
			    	 
			     }
			
			 
			});
			
});
		
	$("#tenantCodeID").val("${tenantCode}");
	
	
	$("#searchID").click(function(){
		$('#ttHistoryDivID').html("");
		$('#id_container').find('ol').html("");
			$('#ttHistoryDivID').html("");
			 var check_cnt = 0;
			 
			  if($("#ttNumberID").val()=="" && $("#landlineNoID").val()== "" && $("#MobileNoID").val()=="" 
					  && $("#priorityID option:selected").val()== "-1" && $("#ttForID").val()=="-1" 
					  && $("#ttTypeID").val()=="-1" && $("#issueTypeID").val()=="-1"  && $("#issueID").val()=="-1" 
					  && $("#statusID option:selected").val()=="-1" && $("#fromDatepickerID").val()== "" && $("#toDatepickerID").val()== "" && $("#cafNoIID").val()== "" && $("#districtID").val()== "-1" ){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Atleast one field</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}   
			  
			  if($("#fromDatepickerID").val()!=""){	
				  if($("#toDatepickerID").val() == ""){
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Select To Date</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}  }
			  
			  if($("#toDatepickerID").val()!=""){	
				  if($("#fromDatepickerID").val() == ""){
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Select From Date</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}}
			  
			  if(check_cnt == 0)
			  {
					var troubleTicketDTO = {
					    "ttNumber": $("#ttNumberID").val(),
					    "contactLandline": $("#landlineNoID").val(),
					    "contactMobile": $("#MobileNoID").val(),
					    "ticketPriority": $("#priorityID :selected").val(),
					    "status": $("#statusID :selected").val(),
					    "fromDate": $("#fromDatepickerID").val(),
					    "toDate": $("#toDatepickerID").val(),
					    "ticketFor": $("#ttForID").val(),
					    "ttType": $("#ttTypeID").val(),
					    "issueType": $("#issueTypeID").val(),
					    "issue": $("#issueID").val(),
					    "cafNo": $("#cafNoIID").val(),
					    "district": $("#districtID").val(),
					    "actualIssue":$("#actualIssueID").val(),
					};
		   	
				   $.ajax({ 
		      	     type: 'GET', 
		      	     //contentType : 'application/json; charset=utf-8',
		      	     dataType : 'json',
		      	     async: false,
		      	     url: 'getTTsInfo', 
		      	     data:troubleTicketDTO,
		      	     beforeSend: function() {
					   ajaxindicatorstart("Loading...")
		            },
		            complete: function() {
		               ajaxindicatorstop()
		            },
		      	     success: function(data) { 
		      	    	// alert("in success");
		      	    	 //alert(data.length);
						 var html = "";
						 var tabelBody ="";
						 html ='<table  class="table table-alt data_table  datatable full-width" id="ttHistoryID">'
							 	+' <thead>'
							 	+'<tr style="height:30px;"><td colspan="29"  style="float:left;"><a href="./getTTsInfoDownload" class="exportClass"><img width="30px" height="30px" src="./resources/images/excel.jpg"></a></td></tr>' 
							 	+'<tr class="headerClass">'
							 	+'<th>TT NO</th>'
							 	+'<th>Account NO</th>'
							 	+'<th>Issue Type</th>'
							 	+'<th>Issue</th>'
							 	+'<th>Name</th>'
							 	+'<th>Olt Serial No</th>'
							 	+'<th>Port</th>'
							 	+'<th>Olt ONUID</th>'
							 	+'<th>Created On</th>'
							 	+'<th>Expected Closure Date</th>'
							 	+'<th>Actual Closed Time</th>'
							 	+'<th>Resolution Time</th>'
							 	+'<th>Status</th>'
							 	+'<th class="unknwnColumns">View TT Info</th>'
							 	+'<th class="unknwnColumns">Attached Files</th>'
							 	+'<th class="unknwnColumns">Update TT</th>'
							    +'<th class="unknwnColumns">TT History</th>' 
							    +'<th style="display:none;  width: 1px;">Complaint</th>' 
							    +'<th style="display:none;  width: 1px;">Aadhar No</th>' 
							    +'<th style="display:none;  width: 1px;">Mobile No</th>'
							 	+'<th style="display:none;  width: 1px;">LandLine No</th>' 
							 	+'<th style="display:none;  width: 1px;">Assigned To</th>'
							 	+'<th style="display:none;  width: 1px;">Priority</th>'
							 	+'<th style="display:none;  width: 1px;">Reason</th>'
							 	+'<th style="display:none;  width: 1px;">Parent Ticket No.</th>'
							 	+'<th style="display:none;  width: 1px;">Child Ticket Nos</th>'
							 	+'<th style="display:none;  width: 1px;">Issue Category</th>'
							 	+'<th style="display:none;  width: 1px;">District</th>'
							 	+'<th style="display:none;  width: 1px;">Substation</th>'
							 	+'<th style="display:none;  width: 1px;">Created By</th>'
							 
							 	+'</tr>'
							 	+'</thead>'
							 	+'<tbody>';
							 	if(data.length == 0){
							 		$('#ttHistoryID').dataTable( {
							 			  "columns": [
							 			    { "searchable": false },
							 			    null,
							 			    null,
							 			    null,
							 			      null,
							 			  null,
							 			  null,
							 			   null,
							 			    null,
							 			    null,
							 			   null,
							 			   null,
							 			    null,
							 			    null,
							 			    null,
							 			   null,
							 			  null,
							 			    null,
							 			    null,
							 			    null,
							 			   null,
							 			   null,
							 			   null,
							 			   null	,
							 			  null,
							 			 null,
							 			null,
							 			 null,
								 			null,
							 			   null	]
							 			} );
							 	}
							 	else{
								 	$.each(data, function(idx, obj){ 
									 html = html +'<tr>'
									    +'<input type="hidden" class="lNameClass" value="'+obj.aadharNo+'"/>'
						 				+'<input type="hidden" class="oltSerialNoClass" value="'+obj.contactMobile+'"/>'
						 				+'<input type="hidden" class="portClass" value="'+obj.contactLandline+'"/>'
						 				+'<input type="hidden" class="oltONUIDClass" value="'+obj.assignedTo+'"/>'
						 				+'<input type="hidden" class="apsflTrackIDClass" value="'+obj.ticketPriority+'"/>'
						 				+'<input type="hidden" class="aadharNoClass" value="'+obj.ticketDesc+'"/>'
						 				+'<input type="hidden" class="pocMob1Class" value="'+obj.ticketReason+'"/>'
						 				+'<input type="hidden" class="email1Class" value="'+obj.childTicketNos+'"/>'
						 				+'<input type="hidden" class="address1Class" value="'+obj.actualIssue+'"/>'
						 				+'<input type="hidden" class="districtNameClass" value=  "'+obj.districts+'" />'
						 				+'<input type="hidden" class="mandalNameClass" value="'+obj.subStationName+'"/>'
						 				+'<input type="hidden" class="subStationNameClass" value="'+obj.userName+'"/>'
						 				+'<td>'+obj.ticketNo+'</td>';
									 				 if(obj.parentticketno !=null)
													 {
														 html = html +'<input type="hidden" class="apsflLandlineNoClass" value="'+obj.parentticketno+'"/>';
													 }
												     else{
												      	 html = html+'<input type="hidden" class="apsflLandlineNoClass" value=""/>';
												     }
									 				if(obj.cafNo !=null)
								 					{
									 					html = html+'<td>'+obj.cafNo+'</td>';
								 					}
									 				else{
									 					html = html+'<td></td>';
									 				}
									 				if(obj.issueType !=null)
								 					{
									 					html = html+'<td>'+obj.issueType+'</td>';
								 					}
									 				else{
									 					html = html+'<td></td>';
									 				}
									 				if(obj.issue !=null)
								 					{
									 					html = html+'<td>'+obj.issue+'</td>';
								 					}
									 				else{
									 					html = html+'<td></td>';
									 				}
									 				if(obj.custName !=null)
								 					{
									 					html = html+'<td>'+obj.custName+'</td>';
								 					}
									 				else{
									 					html = html+'<td></td>';
									 				}
									 				
									 				if(obj.oltSerialNo !=null)
									 				{
									 				html = html+'<td>'+obj.oltSerialNo+'</td>';
									 				}
									 				else{
									 				html = html+'<td></td>';
									 				}
									 				if(obj.port !=null)
									 				{
									 				html = html+'<td>'+obj.port+'</td>';
									 				}
									 				else{
									 				html = html+'<td></td>';
									 				}
									 				if(obj.oltONUID !=null)
									 				{
									 				html = html+'<td>'+obj.oltONUID+'</td>';
									 				}
									 				else{
									 				html = html+'<td></td>';
									 				}
									 				if(obj.createdOnDate !=null)
								 					{
									 					html = html+'<td>'+obj.createdOnDate+'</td>';
								 					}
									 				else{
									 					html = html+'<td></td>';
									 				}
									 				if(obj.expectedClosureDate !=null)
								 					{
									 					html = html+'<td>'+obj.expectedClosureDate+'</td>';
								 					}
									 				else{
									 					html = html+'<td></td>';
									 				}
									 				if(obj.modifiedOnString !=null)
								 					{
									 					html = html+'<td>'+obj.modifiedOnString+'</td>';
								 					}
									 				else{
									 					html = html+'<td></td>';
									 				}
									 				if(obj.timeDiff !=null)
									 					{
									 					html = html+'<td>'+obj.timeDiff+'</td>';
									 					}
									 				else{
									 					html = html+'<td></td>';
									 				}
									 				
									 				html = html+'<td>'+obj.currentStatus+'</td>';
									 				html = html+'<td class="unknwnColumns"><a id="popAnchorID" href="#" class="popper" data-toggle="modal" data-target="#myModal1">View TT Info</a></td>';
									 				
									 				if(obj.imagePath !=null)//
									 				{
									 					html = html+'<td class="unknwnColumns"><input type="hidden" class="imagePath" value="'+obj.imagePath+'"><button type="button" class="btn btn-embossed btn-primary imagepathh" style=" margin-left: 9px; margin-top: 10px; width:38px "><i class="fa fa-download imagepathh" aria-hidden="true"></i></button></td>';
									 				}
									 				else{
									 				html = html+'<td class="unknwnColumns"></td>';
									 				}
									 				  if(obj.actionFlag == "N"){
									 					 html = html +  '<td class="unknwnColumns"></td>';
									 				  }
									 				  else{
									 					 html = html +'<td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+obj.ticketNo+'"><a href="#" class="updateTTClass">Update TT</a></td>';
									 				  }
									 				 html = html +'<td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+obj.ticketNo+'"><input type="hidden" class="ticketDesClass" value="'+obj.ticketDesc+'"><input type="hidden" class="createdByClass" value="'+obj.createdBy+'"><a href="#" class="ttHistoryClass" data-toggle="modal" data-target="#myModal">TT History</a></td>';
									 				
									 				 if(obj.ticketDesc !=null)
									 				{
									 				html = html+'<td style="display:none;  width: 1px;">'+obj.ticketDesc+'</td>';
									 				}
									 				else{
									 				html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 			
									 				 if(obj.aadharNo !=null)
									 				{
									 				html = html+'<td style="display:none;  width: 1px;">'+obj.aadharNo+'</td>';
									 				}
									 				else{
									 				html = html+'<td style="display:none;  width: 1px;"></td>';
									 				} 
									 				if(obj.contactMobile !=null)
								 					{
									 					html = html+'<td style="display:none;  width: 1px;">'+obj.contactMobile+'</td>';
								 					}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.contactLandline !=null)
								 					{
									 					html = html+'<td style="display:none;  width: 1px;">'+obj.contactLandline+'</td>';
								 					}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.assignedTo !=null)
								 					{
									 					html = html+'<td style="display:none;  width: 1px;">'+obj.assignedTo+'</td>';
								 					}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.ticketPriority !=null)
								 					{
									 					html = html+'<td style="display:none;  width: 1px;">'+obj.ticketPriority+'</td>';
								 					}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.ticketReason !=null)
								 					{
									 					html = html+'<td style="display:none;  width: 1px;">'+obj.ticketReason+'</td>';
								 					}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.parentticketno !=null)
								 					{
									 					html = html+'<td style="display:none;  width: 1px;">'+obj.parentticketno+'</td>';
								 					}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.childTicketNos !=null)
								 					{
									 					html = html+'<td style="display:none;  width: 1px;">'+obj.childTicketNos+'</td>';
								 					}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.actualIssue !=null)
								 					{
									 					html = html+'<td style="display:none;  width: 1px;">'+obj.actualIssue+'</td>';
								 					}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.districts !=null)
									 				{
									 				html = html+'<td style="display:none;  width: 1px;">'+obj.districts+'</td>';
									 				}
									 				else{
									 				html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.subStationName !=null)
									 				{
									 				html = html+'<td style="display:none;  width: 1px;">'+obj.subStationName+'</td>';
									 				}
									 				else{
									 				html = html+'<td style="display:none;  width: 1px;"></td>';
									 				}
									 				if(obj.userName !=null)
									 				{
									 				html = html+'<td style="display:none;  width: 1px;">'+obj.userName+'</td>';
									 				}
									 				else{
									 					html = html+'<td style="display:none;  width: 1px;"></td>';
									 				} 
									 				 
									 				html = html+'</tr>';
						 			});
							 	}
						 html = html + '</tbody>'
						 				+ '</table>';
						 $('#ttHistoryDivID').append(html);
						 $("#ttHistoryID").dataTable({
						    	"scrollY": 700
						    	// "scrollX":false
						        /* "scrollX": true */
						    });
				   
			}
		      	     });}
			  else{
				  return false;
			  }
	});
});

</script>   -->
<!-- END FOOTER --> 
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a> 
<script src="./resources/js/jquery.mCustomScrollbar.concat.min.js"></script> <!-- Custom Scrollbar sidebar --> 
<script src="./resources/js/tableExport.js"></script> <!-- Select Inputs -->
<script src="./resources/js/jquery.base64.js"></script> <!-- Export table -->
<script src="./resources/js/select2.min.js"></script> <!-- Export table -->
<script src="./resources/js/bootstrap-tagsinput.min.js"></script> <!-- Select Inputs --> 
<script src="./resources/js/jquery.dataTables.min.js"></script> <!-- Tables Filtering, Sorting & Editing --> 
<script src="./resources/js/dataTables.bootstrap.js"></script>
<script src="./resources/js/jquery.table2excel.js"></script>
<script src="./resources/js/table_editable.js"></script> <!-- Tables Editing --> 
<script src="./resources/js/table_dynamic.js"></script> <!-- Tables Dynamic --> 