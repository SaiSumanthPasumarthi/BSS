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
			<form:form action="#" id="slaFormID" method="post" commandName="lmoslaDTO">
			<input type="hidden" name="status" id="statusID"/>
			<input type="hidden" name="assignedTo" id="assignedToID"/>
			<input type="hidden" name="loginID" id="loginIDID"/>
			<input type="hidden" name="tenantType" id="tenantTypeID"/>
			<input type="hidden" name="tenantCode" id="tenantCodeID"/>
			  <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3><strong>Tenant wise SLA Report</strong></h3>
                    </div>
                   		<div class="row">
						<div class="col-sm-12">
								<table class="table table-dynamic table-alt" id="slaTableID">
									<thead>
										<tr>
											<th>S.No</th>
											<th>User Name</th>
											<th>Tenant Type</th>
											<th>Mobile</th>
											<th>Email</th>
											<th>SLA Violated TT's Count</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty lmoSLAViolatedList}">
											<c:forEach items="${lmoSLAViolatedList}" var="lmotts" varStatus="rowNum">
												<tr>
													<td>${rowNum.count}</td>
													<td>${lmotts.userName}</td>
													<td>${lmotts.tenantType}</td>
													<td>${lmotts.mobile}</td>
													<td>${lmotts.email}</td>
													<td><input type="hidden" class="assignToClass" value="${lmotts.assignedTo}"/><input type="hidden" class="tenantTypeClass" value="${lmotts.tenantType}"/><input type="hidden" class="loginIDClass" value="${lmotts.loginID}"/><input type="hidden" class="tenantCodeClass" value="${lmotts.tenantCode}"/><a href="#" class="lmoSLAClass"><b>${lmotts.slaViolatedCnt}</b></a></td>
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

	$("#statusID").val("${status}");
	$(document).on('click', '.lmoSLAClass', function(){
				var assTo=$(this).closest("td").find('.assignToClass').val();
				var loginid=$(this).closest("td").find('.loginIDClass').val();
				var tentType=$(this).closest("td").find('.tenantTypeClass').val();
				var tentCode=$(this).closest("td").find('.tenantCodeClass').val();
				
				$("#assignedToID").val(assTo);
				$("#loginIDID").val(loginid);
				$("#tenantTypeID").val(tentType);
				$("#tenantCodeID").val(tentCode);
				$("#slaFormID").attr('action',"getLMOSLAViolatedTTsInfo.do");
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