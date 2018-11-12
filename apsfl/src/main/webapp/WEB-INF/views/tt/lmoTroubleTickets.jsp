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
            <li class="active">SLA Report</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="slaFormID" method="post" commandName="troubleTicketDTO">
			  <!-- END ROW -->
			  <input type="hidden" name="flag" id="flagID" value="updateTTFLAG"/>
			   <input type="hidden" name="ticketNo" id="ticketNoID" value="${lmotts.ticketNo}"/>
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3><strong>SLA Violated TT's</strong></h3>
                    </div>
                   		<div class="row">
						<div class="col-sm-12">
								<table class="table table-dynamic table-alt" id="slaTableID">
									<thead>
										<tr>
											<th>S.No</th>
											<th>TT NO</th>
											<th>Issue Type</th>
											<th>Issue</th>
											<th>Created On</th>
											<th>Expected Closure Date</th>
											<th>Assigned To</th>
											<th>Priority</th>
											<th>Status</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty lmoTroubleTickets}">
											<c:forEach items="${lmoTroubleTickets}" var="lmotts" varStatus="rowNum">
												<tr>
													<td>${rowNum.count}</td>
													<td>${lmotts.ticketNo}</td>
													<td>${lmotts.issueType}</td>
													<td>${lmotts.issue}</td>
													<td>${lmotts.createdOnDate}</td>
													<td>${lmotts.expectedClosureDate}</td>
													<td>${lmotts.assignedTo}</td>
													<td>${lmotts.ticketPriority}</td>
													<td>${lmotts.currentStatus}</td>
													<td class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="${lmotts.ticketNo}"><a href="#" class="updateTTClass">Update TT</a></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
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
	
	//alert($('flagID').val());
	$(document).on('click', '.updateTTClass', function(){
		var ticketNo = $(this).closest('tr').find('.ticketNoClass').val();
		//alert(ticketNo);
		$("#ticketNoID").val(ticketNo);
		$("#slaFormID").attr('action',"createTroubleTicket.do");
		$("#slaFormID").submit();
		
		
	});
	
	 $('#slaTableID').dataTable({
		 "sPaginationType": "full_numbers",
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