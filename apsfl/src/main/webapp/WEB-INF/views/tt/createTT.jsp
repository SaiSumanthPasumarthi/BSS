<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>         
<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong>Trouble Ticket</strong></h2>
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
			
			<input type="hidden" name="ticketFor" id="ticketForHiddenID"/>
			<input type="hidden" name="domain" id="domainHiddenID"/>
			
			
			<input type="hidden" name="tenantcode" id="tenantCodeID"/>
			<input type="hidden" name="flag" id="flagID" value="createTTFLAG"/>
			 
              <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3>Create TT</h3>
                    </div>
                    <div class="panel-content" align="center">
                      <div class="row">
					  	<%-- <div class="col-sm-4">
                   			<div class="form-group">
                      		<label class="control-label">Role Name</label>
                      		<form:input type="text" path="roleName" id="roleNameHidID" maxlength="90" class="form-control form-white" placeholder="Enter Role Name" value="" style="width: 200px; "/>
                    		</div>
                        </div> --%>
                        
                         <div class="col-sm-10 apsfl">
                   			<div class="form-group">
                      		<!-- <label class="control-label">Active ?</label> -->
                      		<%-- <form:checkbox path="rowID" value="1" style="width: 20px;height: 20px;" class="activeClass" id="activeID" disabled="true" checked="checked"/> --%>
                      		<form:radiobutton path="ticketForHidden" class="ttType" value="APSFL NOC"/>Ticket for APSFL NOC
                    		</div>
                        </div>
                        <div class="col-sm-10 msp">
                   			<div class="form-group">
                      		<!-- <label class="control-label">Active ?</label> -->
                      		<%-- <form:checkbox path="rowID" value="1" style="width: 20px;height: 20px;" class="activeClass" id="activeID" disabled="true" checked="checked"/> --%>
							<form:radiobutton path="ticketForHidden" class="ttType" value="MSP"/>Ticket for MSP
                      		
                    		</div>
                        </div>
                        <div class="col-sm-10 lmo">
                   			<div class="form-group">
                      		<!-- <label class="control-label">Active ?</label> -->
                      		<%-- <form:checkbox path="rowID" value="1" style="width: 20px;height: 20px;" class="activeClass" id="activeID" disabled="true" checked="checked"/> --%>
							<form:radiobutton path="ticketForHidden" class="ttType" value="LMO"/>Ticket for LMO
                      		
                    		</div>
                        </div>
                         <div class="col-sm-10 silmo">
                   			<div class="form-group">
                      		<!-- <label class="control-label">Active ?</label> -->
                      		<%-- <form:checkbox path="rowID" value="1" style="width: 20px;height: 20px;" class="activeClass" id="activeID" disabled="true" checked="checked"/> --%>
							<form:radiobutton path="ticketForHidden" class="ttType" value="SI"/>Ticket for SI LMO
                      		
                    		</div>
                        </div>
                        <div class="col-sm-10 customer">
                   			<div class="form-group">
                      		<!-- <label class="control-label">Active ?</label> -->
                      		<%-- <form:checkbox path="rowID" value="1" style="width: 20px;height: 20px;" class="activeClass" id="activeID" disabled="true" checked="checked"/> --%>
							<form:radiobutton path="ticketForHidden" class="ttType" value="Customer"/>Ticket for Customer
                      		
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
	$("#tenantCodeID").val("${tenantCode}");
	var domain = "${domain}";
	$("#domainHiddenID").val(domain);
	
	 if(domain == "APSFL")
	{	
		$(".apsfl").hide();
		$(".silmo").hide();
		$(".msp").show();
		$(".lmo").show();
		$(".customer").show();
	}
	 else  if(domain == "APSFL NOC")
		{
			$(".apsfl").show();
			$(".silmo").hide();
			$(".msp").hide();
			$(".lmo").hide();
			$(".customer").hide();
		}
	
	else if(domain == "MSP")
	{
		$(".msp").show();
		$(".silmo").hide();
		$(".apsfl").hide();
		$(".lmo").show();
		$(".customer").show();
	}
	
	else if( domain == "SI")
	{
		$(".lmo").hide();
		$(".silmo").show();
		$(".customer").show();
		$(".apsfl").hide();
		$(".msp").hide();
	} 
	else if(domain == "LMO" )
		{
		$(".lmo").show();
		$(".silmo").hide();
		$(".customer").show();
		$(".apsfl").hide();
		$(".msp").hide();
		}
	else if(domain == "Call Center")
	{
		$(".silmo").hide();
		$(".lmo").hide();
		$(".customer").show();
		$(".apsfl").show();
		$(".msp").hide();
	} 
	else if(domain == "OCC")
	{
		$(".apsfl").show();
		$(".silmo").hide();
		$(".msp").hide();
		$(".lmo").show();
		$(".customer").hide();
	} 
	
	
	$(".ttType").change(function(){
		    $("#ticketForHiddenID").val($(this).val());
			$("#createTTFormID").attr('action',"createTroubleTicket.do");
			$("#createTTFormID").submit();
		
		
		/*if($(this).val() == "APFGC")
		{
			$("#createTTFormID").attr('action',"createTT.do");
			$("#createTTFormID").submit();
		}
		 else if($(this).val() == "MSP")
		{
			$("#createTTFormID").attr('action',"createTT.do");
			$("#createTTFormID").submit();
		}
		else if($(this).val() == "LMO")
		{
			$("#createTTFormID").attr('action',"createTT.do");
			$("#createTTFormID").submit();
		} */
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