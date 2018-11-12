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
            <li class="active">TT Status</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="createTTFormID" method="get">
			  <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3><strong>TT Status</strong></h3>
                    </div>
                    <div class="panel-content ttHistoryDIVClass" style="display:none;" align="center">
                      <div class="row">
					    <div class="col-sm-4">
                   			<div class="form-group">
                      		<label class="control-label">TT Number<span style="color: red;">*</span></label> 
                      		<input type="text" name="ttNumber" id="ttNumber" class="form-control form-white" maxlength="50" placeholder="Enter TT Number">
                      		<input type="button" id="searchID" value="Search" class="btn btn-embossed btn-primary" />
                    		</div>
                        </div>
                        </div>
                      </div>   
                        <div class="row">
                       <div class="col-sm-10" id="ttHistoryDivID">
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
</style>

<script>
$(document).ready(function() {
	//alert("in ready");
	$('#ttNumber').keypress(function(e) { 
		if (e.keyCode == '13') { 
			e.preventDefault(); 
			}
		});
	var ticketDesc = "";
	$('#ttHistoryDivID').html("");
	
	$(".ttHistoryDIVClass").show();
	
	if("${ttHistoryFlag}" == "viewTTHistory")
		{
		var ttNumber = "${ticNo}";
		$(".ttHistoryDIVClass").hide();
		   $.ajax({ 
	      	     type: 'POST', 
	      	     async: false,
	      	     url: 'getTTHistory', 
	      	     data: {ttNumber:"${ticNo}"},
	      	     contentType: 'application/json',
	      	     success: function(data) { 
					 var html = "";
					 var tabelBody ="";
					 $.each(data, function(idx, obj){ 
					
						 ticketDesc	= obj.ticketDesc;
					
						 //alert(ticketDesc+"ticketDesc");
					 });
					 html ='<table class="table table-alt" id="ttHistoryID" >'
						 	+'<thead><tr><th colspan="4"><strong>TT Number: </strong>'+ttNumber+'</th></tr>'
						 	+'<tr><th colspan="4" style="white-space: nowrap;"><strong>Complaint: </strong>'+ticketDesc+'</th></tr>'
						 	+'<tr>'
						 	+'<th>Date</th>'
						 	+'<th>Assign To</th>'
						 	+'<th>Remarks</th>'
						 	+'<th>Status</th>'
						 	+'</tr>'
						 	+'</thead>'
						 	+'<tbody>';
						 	if(data.length == 0){
						 		html = html +'<tr>'
				 				+'<td align="center" colspan="4"><strong>No Data Found</strong></td>'
				 				+'</tr>';
						 	}
						 	else{
					 $.each(data, function(idx, obj){ 
						 html = html +'<tr>'
						 				+'<td>'+obj.effectiveFrm+'</td>'
						 				+'<td>'+obj.assignedTo+'</td>'
						 				+'<td>'+obj.remarks+'</td>'
						 				+'<td>'+obj.currentStatus+'</td>'
						 				+'</tr>';
						 
					 });
						 	}
					 html = html + '<tr><td colspan="4"><input type="submit" style="float:center;" id="okID" value="OK" class="btn btn-embossed btn-primary" /></td></tr></tbody>'
					 				+ '</table>';
					 $('#ttHistoryDivID').append(html);
			   
		}
	      	     });
		}
	
	$(document).on('click', '#okID', function(){
		//window.history.back();
		
		//history.go(-2);
		$("#createTTFormID").attr('action',"updateTTT.do");
		$("#createTTFormID").submit();
	});
	
	$("#searchID").click(function(){
			//alert('in click');
			$('#ttHistoryDivID').html("");
			var check_cnt = 0;
			 
			var ticketDesc = "";
			var createdBy = "";
			var modifiedOn = "";
			var createdOn = "";
			$(".closedTHClass").hide();
			var actualClosedDate = "";
			
		  if($("#ttNumber").val()==""){				
			    $("#id_container").addClass('dberrorMsg');
				$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter TT Number</li>');
				$("#id_container").show();	
				check_cnt=check_cnt+1;
			}   
			  
			  if(check_cnt == 0)
			  {
			var ttNumber = $("#ttNumber").val();
	   
		   $.ajax({ 
      	     type: 'GET', 
      	     async: false,
      	     url: 'getTTHistory', 
      	     data: {ttNumber:ttNumber},
      	     contentType: 'application/json',
      	     success: function(data) { 
      	    	 //alert("in success");
      	    	// alert(data.length);
				 var html = "";
				 var tabelBody ="";
				 
				 //alert(data.ticketDesc+"ticketdesc");
				 //alert(data.ticketNo+"ticketno");
				 
				 $.each(data, function(idx, obj){ 
						
					 ticketDesc	= obj.ticketDesc;
					 createdBy = obj.createdBy;
				 });
				
				 html ='<table class="table table-alt" id="ttHistoryID" >'
					 	+' <thead><tr><td colspan="4"><strong>TT Number: </strong>'+ttNumber+'</td></tr>'
					 	 +'<tr><td colspan="4"><strong>Created By: </strong>'+createdBy+'</td></tr>'
					 	+'<tr><td colspan="4"><strong>TT Description: </strong>'+ticketDesc+'</td></tr>'
					 	+'<tr>'
					 	+'<th>Date</th>'
					 	+'<th>Assign To</th>'
					 	+'<th>Assign To Mobile No</th>'
					 	+'<th>Remarks</th>'
					 	+'<th>Status</th>'
					 	+'</tr>'
					 	+'</thead>'
					 	+'<tbody>';
					 	if(data.length == 0){
					 		html = html +'<tr>'
			 				+'<td align="center" colspan="4"><strong>No Data Found</strong></td>'
			 				+'</tr>';
					 	}
					 	else{
				 $.each(data, function(idx, obj){ 
					 html = html +'<tr>'
					 				+'<td>'+obj.effectiveFrm+'</td>'
					 				+'<td>'+obj.assignedTo+'</td>'
					 				+'<td>'+obj.mobileNo+'</td>'
					 				+'<td>'+obj.remarks+'</td>'
					 				+'<td>'+obj.currentStatus+'</td>'
					 				+'</tr>';
				 });
					 	}
				 html = html + '</tbody>'
				 				+ '</table>';
				 $('#ttHistoryDivID').append(html);
		   
	}
      	     });}
			  else{
				  return false;
			  }
	});
});

</script>  
<!-- END FOOTER --> 
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a> 
<script src="./resources/js/jquery.mCustomScrollbar.concat.min.js"></script> <!-- Custom Scrollbar sidebar --> 
<script src="./resources/js/select2.min.js"></script> <!-- Select Inputs -->
<script src="./resources/js/bootstrap-tagsinput.min.js"></script> <!-- Select Inputs --> 
<script src="./resources/js/jquery.dataTables.min.js"></script> <!-- Tables Filtering, Sorting & Editing --> 
<script src="./resources/js/table_editable.js"></script> <!-- Tables Editing --> 
<script src="./resources/js/table_dynamic.js"></script> <!-- Tables Dynamic --> 