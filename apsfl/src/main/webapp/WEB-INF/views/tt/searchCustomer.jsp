<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>   
<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Trouble Ticket</h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./home.do">Home</a> </li>
            <li class="active">Create TT</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="createTTFormID" method="post" commandName="troubleTicketDTO">
			
			<input type="hidden" name="cafNo" id="cafNoID"/>
			<input type="hidden" name="ticketFor" id="ticketForHiddenID"/>
			<input type="hidden" name="flag" id="flagID" value="searchFLAG"/>
			<input type="hidden" name="tenantcode" id="tenantCodeID"/>
			
			  <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3><strong>Create TT for ${ticketFor}</strong></h3>
                    </div>
                    <div class="panel-content" align="center">
                      <div class="row">
                       <div class="col-sm-3" >
						<div class="form-group">													
						<label class="control-label">District</label>&nbsp;
							<form:select path="districts" id="districtID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select District--" />
								<form:options items="${districtsMap}" />
							</form:select> 
						</div>
						</div>
						
						 <div class="col-sm-3" >
						<div class="form-group">													
						<label class="control-label">Mandal</label>&nbsp;
							<form:select path="mandalID" id="mandalID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Mandal--" />
							</form:select> 
						</div>
						</div>
						
						 <div class="col-sm-3" >
						<div class="form-group">													
						<label class="control-label">Village</label>&nbsp;
							<form:select path="villageID" id="villageID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Village--" />
							</form:select> 
						</div>
						</div>
						
						 <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">APSFL ID</label> 
                      		<input type="text" name="apsflID" id="apsflID" class="form-control form-white" maxlength="10" placeholder="Enter APSFL ID">
                      		</div>
                        </div>
						
                       <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">Aadhar Number</label> 
                      		<input type="text" name="aadharNo" id="aadharNoID" class="form-control form-white number" maxlength="20" placeholder="Enter Aadhar Number">
                      		</div>
                        </div>
                        
                        <div class="col-sm-3">
	                  <div class="form-group">
	                     	<label class="control-label">Account No</label> 
	                     	<input type="text" name="tenantMobile" id="cafNoIID" class="form-control form-white" maxlength="20" placeholder="Enter Account No">
	                   	</div>
                       </div>
					                    
					      <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">Mobile Number</label> 
                      		<input type="text" name="contactMobile" id="MobileNoID" class="form-control form-white number" maxlength="10" placeholder="Enter Mobile Number">
                      		</div>
                        </div>
                               
                        <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">APSFL Landline Number</label> 
                      		<input type="text" name="contactLandline" id="landlineNoID" class="form-control form-white" maxlength="20" placeholder="Enter APSFL Landline Number">
                      	
                    		</div>
                        </div>
                        </div>
                        
                        <div class="row">
                         <div class="col-sm-3">
                   			<div class="form-group">
                      		<label class="control-label">ONU SrlNo</label> 
                      		<input type="text" name="oltId" id="oltId" class="form-control form-white" maxlength="20" placeholder="Enter ONU Srl Number">
                      	
                    		</div>
                        </div>
                        </div>
                        
                         <div class="row">
							 <div class="col-sm-8" style="float: left;">
	                   			<div class="form-group">
	                      		<label class="control-label"></label>
	                      		<input type="button" id="searchID" value="Search" class="btn btn-embossed btn-primary" />
	                      		<input type="submit" id="cancelID" value="Cancel" class="btn btn-outline btn-danger"/>
	                    		</div>
	                        </div>
						</div>
                      </div>   
                        <div class="row">
                       <div class="col-sm-12" id="ttHistoryDivID">
											</div> 
											</div> 
						<div id="myModal" class="modal fade" role="dialog" >
											<div class="modal-dialog" style="width: 1160px;">
												<!-- Modal content-->
												<div class="modal-content model-div-size" style="width: 1160px;">
													<div class="modal-header" style="width: 1160px;">
														<h3 class="modal-title"> View Customer Details
															<button type="button" class="btn btn-default" id="popUpCloseButtonId" style="float: right; clear: right; margin-top: -0.5em;" data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
															<div class="panel panel-default" style="width: 1129px; overflow-x: auto;">
																<div class="ScrollStyle" style="width: 1130px; overflow-x: auto;">
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
										
							<div id="myModal1" class="modal fade" role="dialog" >
											<div class="modal-dialog" >
												<!-- Modal content-->
												<div class="modal-content model-div-size" >
													<div class="modal-header">
														<h3 class="modal-title"> View Bill Details
															<button type="button" class="btn btn-default " style="float:right;" id="popUpCloseButtonId"  data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
															<div class="panel panel-default">
																<!-- <div class="ScrollStyle"  style="width: 1130px; overflow-x: auto;"> -->
																<div id="jsonErrListID1Id" class=" table-responsive">
																	<table class="table table-alt " id="jsonErrListID1">
																		
																	</table>
																	</div>
																</div>
																</div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
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
 .datatable td span{
    max-width: 400px;
    display: block;
    overflow: hidden;
} 

.tooltipclass{
    font-size: 12px;
    border:1px solid #e74c3c;
    background-color: white;
    font-family: sans-serif;
    opacity: 1;
} 
/* .popper{
    font-size: 12px;
    background-color: white;
    font-family: sans-serif;
    opacity: 1;
}  */

</style>
<style>
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
.popbox h2 {
    background-color: #4D4F53;
    color: #E3E5DD;
    font-size: 14px;
    display: block;
    width: 100%;
    margin: -10px 0px 8px -10px;
    padding: 5px 10px;
}
@media (max-width: @screen-xs-min) {
  .modal-xs { width: @modal-sm; }
}
</style>

<!-- <script type="text/javascript">
var doc = new jsPDF();
var specialElementHandlers = {
    '#editor': function (element, renderer) {
        return true;
    }
};

$('#viewId').click(function () {
	alert("Hiii");
    doc.fromHTML($('#content').html(), 15, 15, {
        'width': 170,
            'elementHandlers': specialElementHandlers
    });
    doc.save('sample-file.pdf');
});
</script> -->

<script>

$(document).ready(function() {
	
	var doc = new jsPDF();
	var specialElementHandlers = {
	    '#editor': function (element, renderer) {
	        return true;
	    }
	};

	 
		
	$("input:text").keypress(function(event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
	
	$('#districtID').change(function(){
		var districtID = $(this).val();
		$("#mandalID").empty();
		$("#mandalID").append('<option value="-1">--Select Mandal--</option>');
		$("#villageID").empty();
		$("#villageID").append('<option value="-1">--Select Village--</option>');
		if(districtID!="-1") {
			$.ajax({ 
			     type: 'GET', 
			     url:  'getMandals?stateID=1&districtID='+districtID, 
			     success: function(result){
			    	 var temp = [];
			    	   	$.each(result, function(key1, value1){
			    	temp.push({v:value1, k:key1});
			    	   	});
			    	temp.sort(function(a,b){
			    	  if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
			    	   if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
			    	     return 0;
			    	});
			    	$.each(temp, function(key, obj){
			    	$("#mandalID").append('<option value='+obj.k+'>'+obj.v+'</option>');
			    	}); 
			     }
			  });
			}
	});
	
	$('#mandalID').change(function(){
		var districtID = $("#districtID").val();
		var mandalID = $(this).val();
		$("#villageID").empty();
		$("#villageID").append('<option value="-1">--Select Village--</option>');
		if(mandalID!="-1") {
			$.ajax({ 
			     type: 'GET', 
			     url:  'getVillages?stateID=1&districtID='+districtID+"&mandalID="+mandalID, 
			     success: function(result){
			    	 var temp = [];
			    	   	$.each(result, function(key1, value1){
			    	temp.push({v:value1, k:key1});
			    	   	});
			    	temp.sort(function(a,b){
			    	  if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
			    	   if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
			    	     return 0;
			    	});
			    	$.each(temp, function(key, obj){
			    	$("#villageID").append('<option value='+obj.k+'>'+obj.v+'</option>');
			    	}); 
			     }
			  });
			}
	});
	
	$("#cancelID").click(function(){
		$("#createTTFormID").attr('action',"createTT.do");
		$("#createTTFormID").submit();
	});
	
	$('#ttHistoryDivID').html("");
	
	//$("#cafNoID").val(cafNo);
	$("#ticketForHiddenID").val("${ticketFor}");
	
	$("#tenantCodeID").val("${tenantCode}");
	
$(document).on('click', '.createTTClass', function(){
		
		var cafNo = $(this).closest('tr').find('.cafNOHiddenClass').val();
		
		$("#cafNoID").val(cafNo);
		$("#createTTFormID").attr('action',"createTroubleTicket.do");
		$("#createTTFormID").submit();
		
	});
	
/* $(document).on('click', '.viewBillClass', function(){
	alert(cafNo+",,,lkm");
	var cafNo = $(this).closest('tr').find('.cafNOHiddenClass').val();
	alert(cafNo);
	$("#cafNoID").val(cafNo);
	$("#createTTFormID").attr('action',"getViewBillInfo.do");
	$("#createTTFormID").submit();
	
}); */

$(document).on('click', '.viewBillClass', function(){
	
	//alert("Hi.....");
	$("#jsonErrListID1").empty();
	var cafNo = $(this).closest('tr').find('.cafNOHiddenClass1').val();
	//var cafNo = $(this).attr("val");
	//alert("Hi....."+cafNo+"   "+"${custID}");
	
	var t='';
	var tt ='';
			 $.ajax({ 
			     type: 'GET', 
			     url:  'getViewBillInfo?cafNo='+cafNo,
			     async: false,
			     success: function(data){ 
			    	 
			    	 
			    	// t= t+'<div id="content">'
			    	 t= t+'<thead><tr><th>Bill Number</th><th >Bill Date </th><th> Bill from Date</th><th >Bill to Date</th><th>Bill Amount </th><th>Due Date</th><th>View Bill</th></tr></thead><tbody>';
			    ////	 t= t+ '<tr style="height:30px;"><td colspan="29"  style="float:left;"><a href="#" class="exportClass"><img width="30px" height="30px" src="./resources/images/excel.jpg"></a></td></tr>';
			    	
			   
			    $.each(data, function(idx, obj)
		    			 {
			    	 tt = tt + ' <tr> ';
					 tt = tt +		' <td>'+obj.billNo+'</td> ';
					 tt = tt +		' <td>'+obj.billDate+'</td> ';
					 tt = tt +		' <td>'+obj.billFromDate+'</td> ';
					 tt = tt +		' <td>'+obj.billToDate+'</td> ';
					 tt = tt +		' <td>'+obj.billAmount+'</td> ';
					 tt = tt +		' <td>'+obj.dueDate+'</td> ';
					 tt = tt +		' <td><form style="float:left;" name="downloadBill" action="#" target="new_tab" class="downloadBillForm" method="post" > <input type="hidden" name = "filePath" value="'+obj.filePath+'" /> <span class="downloadBill" style="cursor: pointer;" title="View Bill"><img src="./resources/images/apf_add.png"></span></form></td>';
					// tt = tt +		'<td><a href="#" id="viewId" class="viewTTBill" >View Bill</a></td>'
					// tt = tt +		'<td><a href="/resources/newsletter.pdf" target="_blank" download>Newsletter</a></td>'
					 
				    		
			    	// alert(t);
			   
			
			     });
			    tt = tt +	' </tr> ';
		    	 t = t+tt+"</tbody>";
			 	 $("#jsonErrListID1").append(t);
			     }
			    	 
			}); 
});

$(document).on('click','.downloadBill',function() {
	var i = $(".downloadBill").index(this);
	$(".downloadBillForm:eq("+i+")").attr('action',"downLoadBill1");
	$(".downloadBillForm:eq("+i+")").submit();
});

$(document).on('click', '.viewTTBill', function(){
	
//	alert("FCUK");
	 
    doc.fromHTML($('#jsonErrListID1Id').html(),  {
        
            'elementHandlers': specialElementHandlers
    });
    doc.save('sample-file.pdf');
	
			 $.ajax({ 
			     type: 'GET', 
			     url:  'downloadViewBill',
			     async: false,
			     success: function(data){ 
			 
			     }
			}); 
});

$(document).on('click', '.popper', function(){
	
	//alert("vamse shiva");
		/* var cafNo = $(this).closest('tr').find('.cafNOHiddenClass').val();
	
		$("#cafNoID").val(cafNo);
		$("#createTTFormID").attr('action',"createTroubleTicket.do");
		$("#createTTFormID").submit(); */
		$("#jsonErrListID").empty();
		//$( ".selector" ).tooltip( "close" );
		var ticketNo = $(this).closest('tr').find('.cafNOHiddenClass').val();
		var createdBy = $(this).closest('tr').find('.lNameClass').val();	
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
		var villageName = $(this).closest('tr').find('.villageNameClass').val();
		var cpePlaceClass = $(this).closest('tr').find('.cpePlaceClass').val();
		var t='';
		var tt ='';
				//$("#ticketNoID").val(ticketNo);
				t= t+'<thead><tr><td colspan="4" style="border:none;"><strong>Account No: </strong>'+ticketNo+'</td><td colspan="4" style="border:none;"><strong>Name: </strong>'+createdBy+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Olt Serial No : </strong>'+ticketDesc+'</td></tr>'
				t= t+'<tr><td colspan="4" style="border:none;"><strong>Mobile No: </strong>'+pocMob1+'</td><td colspan="4" style="border:none;"><strong>ApsflLandlineNo: </strong>'+apsflLandlineNo+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Email : </strong>'+email1+'</td></tr>'
				t= t+'<tr><td colspan="4" style="border:none;"><strong>Address : </strong>'+address1+'</td><td colspan="4" style="border:none;"><strong>District Name: </strong>'+districtName+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Mandal Name : </strong>'+mandalName+'</td></tr>'
				t= t+'<tr><td colspan="4" style="border:none;"><strong>SubStation Name: </strong>'+subStationName+'</td><td colspan="4" style="border:none;"><strong>Village name: </strong>'+villageName+'</td><td colspan="4" style="border:none;white-space: nowrap;"><strong>Cpe Location : </strong>'+cpePlaceClass+'</td></tr></thead><tbody>'
				    	 t = t+tt+"</tbody>";
				    	 $("#jsonErrListID").append(t);
				    	 //$(this).tooltip('open'); 
				    	// $( '.popper' ).tooltip({ content: $(t).html(),tooltipClass: 'tooltipclass' ,show: open});
				    	 
	});
/* $(document).on('mouseleave', '.popper', function(){
	 $('.popper').tooltip('close'); 
}); */
 
	
	$("#searchID").click(function(){
		$("#ttHistoryDivID").css({"min-width":"1100px"});
			$('#ttHistoryDivID').html("");
			$('#id_container').find('ol').html("");
			 var check_cnt = 0;
			 
			  if( $("#landlineNoID").val()== "" && $("#MobileNoID").val()=="" && $("#aadharNoID").val()== "" && $("#districtID").val()== "-1" && $("#mandalID").val()=="-1" && $("#villageID").val()== "-1"  && $("#apsflID").val()== "" && $("#cafNoIID").val()== "" && $("#oltId").val()== ""){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Atleast one field</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}  
			  if( $("#MobileNoID").val()!="" && $("#MobileNoID").val().length<10){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Valid Mobile Number</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}   
			  
			  if(check_cnt == 0)
			  {
		  
			var search = {
					"ticketFor":"${ticketFor}",
				    "contactLandline": $("#landlineNoID").val(),
				    "contactMobile": $("#MobileNoID").val(),
				    "aadharNo": $("#aadharNoID").val(),
				    "district": $("#districtID").val(),
				    "mandal": $("#mandalID").val(),
				    "village": $("#villageID").val(),
				    "apsflID": $("#apsflID").val(),
				    "cafNo": $("#cafNoIID").val(),
				    "oltId": $("#oltId").val(),
				   };
		   	
		  // 	var troubleTicketDTO = JSON.stringify(troubleTicket); 
		  // alert('in click111');
		   $.ajax({ 
      	     type: 'GET', 
      	     //contentType : 'application/json; charset=utf-8',
      	     dataType : 'json',
      	     async: false,
      	     url: 'getCustomerCafsInfo', 
      	     data:search,
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
				 html ='<table class="table table-alt data_table  datatable full-width" id="ttHistoryID" >'
					 	+' <thead>'
					 	+'<tr>'
					 	+'<th>Account No.</th>'
					 	+'<th>Name</th>'
					 	+'<th>Cpe Places</th>'
					 	+'<th>Olt Serial No</th>'
					 	+'<th>Port</th>'
					 	+'<th>Olt ONUID</th>'
					 	/*+'<th>APSFL Track ID</th>'
					 	+'<th>Aadhar No</th>'
					 	+'<th>Mobile No</th>'
					 	 +'<th>APSFL LandLine No</th>'
					 	+'<th>Email</th>'
					 	+'<th>Address</th>'
					 	+'<th>District</th>'
					 	+'<th>Mandal</th>'
					 	+'<th>Substation</th>'
					 	+'<th>Village</th>'
					 	 *//* +'<th>ONU Reg No</th>' */
					 	+'<th>View Customer Info</th>'
					 	+'<th>Action</th>'
					 	+'<th>View Bill</th>'
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
					 			   null
					 			   ]
					 			} );
					 	}
					 	else{
				 $.each(data, function(idx, obj){ 
					 html = html +'<tr>'
					                +'<td>'+obj.cafNo+'</td>'
					               // +'<td class="lNameClass" value="'+obj.fName+obj.lName+'">'+obj.fName+obj.lName+'</td>'
					 			    +'<td><input type="hidden" class="lNameClass" value="'+obj.fName+obj.lName+'"/>'+obj.fName+obj.lName+'</td>'
					 				+'<input type="hidden" class="oltSerialNoClass" value="'+obj.oltSerialNo+'"/>'
					 				+'<input type="hidden" class="portClass" value="'+obj.port+'"/>'
					 				+'<input type="hidden" class="oltONUIDClass" value="'+obj.oltONUID+'"/>'
					 				+'<input type="hidden" class="apsflTrackIDClass" value="'+obj.apsflTrackID+'"/>'
					 				+'<input type="hidden" class="aadharNoClass" value="'+obj.aadharNo+'"/>'
					 				+'<input type="hidden" class="pocMob1Class" value="'+obj.pocMob1+'"/>'
					 				+'<input type="hidden" class="apsflLandlineNoClass" value="'+obj.apsflLandlineNo+'"/>'
					 				+'<input type="hidden" class="email1Class" value="'+obj.email1+'"/>'
					 				+'<input type="hidden" class="address1Class" value="'+obj.address1+'"/>'
					 				+'<input type="hidden" class="districtNameClass" value="'+obj.districtName+'"/>'
					 				+'<input type="hidden" class="mandalNameClass" value="'+obj.mandalName+'"/>'
					 				+'<input type="hidden" class="subStationNameClass" value="'+obj.subStationName+'"/>'
					 				+'<input type="hidden" class="villageNameClass" value="'+obj.villageName+'"/>' 
					 				+'<input type="hidden" class="cpePlaceClass" value="'+obj.cpeplace+'"/>' 
					 				+'<td>'+obj.cpeplace+'</td>'
					 				+'<td>'+obj.oltSerialNo+'</td>'
					 				+'<td>'+obj.port+'</td>'
					 				+'<td>'+obj.oltONUID+'</td>'
					 				/* +'<td>'+obj.apsflTrackID+'</td>'
					 				+'<td>'+obj.aadharNo+'</td>'
					 				+'<td>'+obj.pocMob1+'</td>'
					 				+'<td>'+obj.apsflLandlineNo+'</td>'
					 				+'<td>'+obj.email1+'</td>'
					 				+'<td>'+obj.address1+'</td>'
					 				+'<td>'+obj.districtName+'</td>'
					 				+'<td>'+obj.mandalName+'</td>'
					 				+'<td>'+obj.subStationName+'</td>'
					 				+'<td>'+obj.villageName+'</td>' */
					 			
					 				/* +'<td>'+obj.onuRegNo+'</td>' */
					 				+'<td><a href="#" id="popAnchorId" class="popper" data-toggle="modal" data-target="#myModal">View Customer Info</a></td>'
					 				+'<td><input type="hidden" class="cafNOHiddenClass" value="'+obj.cafNo+'"/><a href="#" class="createTTClass">Create TT</a></td>'
					 				//+'<td><input type="hidden" class="cafNOHiddenClass" value="'+obj.cafNo+'"/><a href="#" class="viewBillClass">View Bill</a></td>'
					 				+'<td><input type="hidden" class="cafNOHiddenClass1" value="'+obj.custId+'"/><a href="#" val='+obj.custId+' id="popAnchorIdd" class="viewBillClass" data-toggle="modal" data-target="#myModal1">View Bill</a></td>'
					 				+'</tr>';
				 });
					 	}
				 html = html + '</tbody>'
				 				+ '</table>';
				 $('#ttHistoryDivID').append(html);
				 $("#ttHistoryID").dataTable({
				    	"scrollY": 500,
				    	 "scrollX":true
				        /* "scrollX": true */
				    });
				 $("#ttHistoryID").dataTable();
	}
      	     });}
			  else{
				  return false;
			  }
	});
});
$("a[rel='tooltip']").tooltip();
</script>  
<!-- END FOOTER --> 
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/tooltipster/3.0.5/js/jquery.tooltipster.min.js"></script>
<script src="./resources/js/jquery.mCustomScrollbar.concat.min.js"></script> <!-- Custom Scrollbar sidebar --> 
<script src="./resources/js/select2.min.js"></script> <!-- Select Inputs -->
<script src="./resources/js/bootstrap-tagsinput.min.js"></script> <!-- Select Inputs --> 
<script src="./resources/js/jquery.dataTables.min.js"></script> <!-- Tables Filtering, Sorting & Editing --> 
<script src="./resources/js/table_editable.js"></script> <!-- Tables Editing --> 
<script src="./resources/js/table_dynamic.js"></script> <!-- Tables Dynamic --> 