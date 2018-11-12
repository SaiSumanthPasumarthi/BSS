<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>         
<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Error Management</h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./home.do">Home</a> </li>
            <li class="active">ME Error Process</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="meUsageErrorsFormID" method="post" commandName="tpUsageErrorsDTOObj">
			 <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
	              <div class="row">
	                <div class="col-sm-12">
	                  <div class="panel">
	                   <div class="panel-header bg-light">
	                      <h3><strong>Update ME TP Error Process</strong></h3>
	                    </div>
                    
                      </div>
                    </div>
                  </div>
                  <div class="row">
                       <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">File Sequence No.</label> 														   
							<input type="text" name="fileSeqNo" id="fileSeqNoID" style="width: 200px;background-color:#ECEDEE;" class="form-control form-white" readOnly></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Record ID</label> 														   
							<input type="text" name="recid" id="recidID" style="width: 200px;background-color:#ECEDEE;" class="form-control form-white" readOnly></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Sub Service</label> 														   
							<input type="text" name="srcSrvcSubtype" id="srcSrvcSubtypeID" style="width: 200px;background-color:#ECEDEE;" class="form-control form-white" readOnly></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Service Category</label> 														   
							<input type="text" name="serviceCategory" id="serviceCategoryID" style="width: 200px;" class="form-control form-white"></input>
						</div>
						</div>
				 </div> 
				 <div class="row">
				 		<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Role of Node</label> 														   
							<input type="text" name="roleOfNode" id="roleOfNodeID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
                       <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Sub ID</label> 														   
							<input type="text" name="subIDData" id="subIDDataID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Service ID</label> 														   
							<input type="text" name="serviceID" id="serviceIDID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Value Added Service</label> 														   
							<input type="text" name="valueAddedServiceID" id="valueAddedServiceIDID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
				 </div>  
				  <div class="row">
				 <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Originating IOI</label> 														   
							<input type="text" name="orgIOI" id="orgIOIID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Terminating IOI</label> 														   
							<input type="text" name="termIOI" id="termIOIID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Service Request Time</label> 														   
							<input type="text" name="serviceRequestTime" id="serviceRequestTimeID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Request Party Address</label> 														   
							<input type="text" name="reqPartyAddress" id="reqPartyAddressID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
				</div>
				
				 <div class="row">
				 <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Calling Party Address</label> 														   
							<input type="text" name="callingPartyAddress" id="callingPartyAddressID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Called Party Address</label> 														   
							<input type="text" name="calledPartyAddress" id="calledPartyAddressID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Start Time</label> 														   
							<input type="text" name="starttime" id="starttimeID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">End Time</label> 														   
							<input type="text" name="endtime" id="endtimeID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
				</div>
				 <div class="row">
                       <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Call Duration</label> 														   
							<input type="text" name="callDuration" id="callDurationID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Call Description</label> 														   
							<input type="text" name="callDesc" id="callDescID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Supplementary Services</label> 														   
							<input type="text" name="supplementryServiceList" id="supplementryServiceListID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Record Sequence No</label> 														   
							<input type="text" name="recordSequenceNo" id="recordSequenceNoID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
				 </div>    
				  <div class="row">
                       <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Record Close Reason</label> 														   
							<input type="text" name="recCloseReason" id="recCloseReasonID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Status</label>
										<form:select path="status" id="statusID"
											style="width: 200px; " class="form-control form-white">
											<%-- <form:option value="-1" label="--Select Status--" /> --%>
											<form:options items="${statusMap}" />
										</form:select>
										<!-- <input type="text" name="status" id="statusID" style="width: 200px;background-color:#ECEDEE;" class="form-control form-white" ></input> -->
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Error</label> 														   
							<input type="text" name="errorcode" id="errorcodeID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div>
						
						 <div class="col-sm-4" style="float:center;">
                   			<div class="form-group">
                      		<label class="control-label"></label>
                      		<input type="button" id="updateID" value="Update" class="btn btn-embossed btn-primary" />
                      		
                    		</div>
                        </div>
                       <!--  <div class="col-sm-3">
						<div class="form-group">																											   
							<input type="hidden" name="msg" id="msgID" style="width: 200px;" class="form-control form-white" ></input>
						</div>
						</div> -->
						<!-- <input type="hidden" name="msgID" class="recHiddenClass"/> -->
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
	
		 
	 $("#recidID").val("${recid}");
	 $("#srcSrvcSubtypeID").val("${srcSrvcSubtype}");
	 $("#fileSeqNoID").val("${fileSeqNo}");
	 $("#roleOfNodeID").val("${roleOfNode}");
 	 $("#callingPartyAddressID").val("${callingPartyAddress}");
	 $("#calledPartyAddressID").val("${calledPartyAddress}");
	 $("#starttimeID").val("${starttime}");
	 $("#endtimeID").val("${endtime}");
	 $("#serviceCategoryID").val("${serviceCategory}");
	 $("#callDurationID").val("${callDuration}");
	 $("#serviceRequestTimeID").val("${serviceRequestTime}");
	 $("#orgIOIID").val("${orgIOI}");
	 $("#termIOIID").val("${termIOI}");
	 $("#recordSequenceNoID").val("${recordSequenceNo}");
	 $("#recCloseReasonID").val("${recCloseReason}");
	 $("#subIDDataID").val("${subIDData}");
	 $("#reqPartyAddressID").val("${reqPartyAddress}");
	 $("#callDescID").val("${callDesc}");
	 $("#serviceIDID").val("${serviceID}");
	 $("#valueAddedServiceIDID").val("${valueAddedServiceID}");
	 $("#supplementryServiceListID").val("${supplementryServiceList}");
	 $("#otherFieldsID").val("${otherFields}");
	 $("#statusID").val("${status}");
	 $("#errorcodeID").val("${errorcode}");
	 
	 if("${msg}"!="")
		{
		alert("${msg}");
		window.location="./meTPErrorProcess";
		}
	 
	 $("#updateID").click(function(){
	 alert("in click");
	
	
	 $("#meUsageErrorsFormID").attr('action',"updateError.do");	
	 $("#meUsageErrorsFormID").submit(); 
	  });
	 
 });

</script>  
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