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
			
			<input type="hidden" name="ticketForHidden" id="ticketForHiddenID"/>
			 
              <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container2" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3><strong>TT Success</strong></h3>
                    </div>
                    <div class="panel-content" style="float: center;">
                      <div class="row">
                      <!-- <span id="generatedTTNoID"></span> -->
                      
                      <div id="id_container1" class="success" style="color:green;float:center;"><ol id="id_ol"></ol></div>					
                        	  </div>     
                        	   <div class="row">
					       <div class="col-sm-7" style="float: center;">
                   			<div class="form-group">
                      		<label class="control-label"></label>
                      		<input type="submit" id="okID" value="OK" class="btn btn-embossed btn-primary" /> 
            				
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
</style>

<script>
$(document).ready(function() {
	//alert("${ttNumber}"+"ttnumber");
	  // $("#generatedTTNoID").html();
	  $("#id_container").hide();
	  //alert('in ready'+"${ttNumber}");
	   if("${ttNumber}" == "")
		   {
		   if("${currentStatus}" == "failed")
		   {
			   $("#id_container1").addClass('dbsuccessMsg');
			$('#id_container1').find('ol').append('<li style="list-style: none;">TT Creation Failed</li>');
			$("#id_container1").show();
		   }
		   else
		   $("#id_container").hide();
		   }
	   else{
		   
		   $("#id_container1").addClass('dbsuccessMsg');
		 //  alert("${currentStatus}");
		   if("${currentStatus}" == "")
		   {
			$('#id_container1').find('ol').append('<li style="list-style: none;">TT Created SuccessFully with TT Number:'+"${ttNumber}"+'</li>');
		   }
		   else if("${currentStatus}" == "updatesuccess")
		   {
				$('#id_container1').find('ol').append('<li style="list-style: none;">TT Updated SuccessFully ( TT Number:'+"${ttNumber}"+' )</li>');
			   }
		   else if("${currentStatus}" == "updateupdatefailed")
		   {
				$('#id_container1').find('ol').append('<li style="list-style: none;">TT Updation Failed ( TT Number:'+"${ttNumber}"+' )</li>');
			   }
		   else if("${currentStatus}" == "assignedFailed")
		   {
				$('#id_container1').find('ol').append('<li style="list-style: none;">'+"${ttNumber}"+'TT is already assigned. )</li>');
			   }
		   else
			   {
			   $('#id_container1').find('ol').append('<li style="list-style: none;">TT existed for the same issue with TT Number:'+"${ttNumber}"+' and status '+"${currentStatus}"+'</li>');
			   }
			$("#id_container1").show();	
		   
	   }
		   
		
		
	   $("#okID").click(function(){
		  
		   
		   $("#createTTFormID").attr('action',"createTT.do");
			$("#createTTFormID").submit();
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