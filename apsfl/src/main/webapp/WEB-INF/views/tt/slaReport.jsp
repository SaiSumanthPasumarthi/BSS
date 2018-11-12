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
			<input type="hidden" name="assignedTo" id="domainID" value="${userDomain}"/>
			<input type="hidden" name="status" id="statusID"/>
			
			  <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3><strong>SLA Report</strong></h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
					    <div class="col-sm-4">
                   			<div class="form-group">
                      		<label class="control-label">SLA Violated TT's Count<span style="color: red;">*</span></label> 
                      		<label style="width: 200px; "><b><c:if test="${slaViolatedCnt == 0}">0</c:if><c:if test="${slaViolatedCnt gt 0}"><a href="#" class="slaClass" val="All">${slaViolatedCnt}</a></c:if></b></label>
                      		</div>
                        </div>
                        </div>
                      </div>   
                        
						<div class="row">
						  <c:if test="${not empty statusWiseCntMap}">
								<c:forEach items="${statusWiseCntMap}" var="type" varStatus="rowNum">
								<div class="col-sm-2">
								<div class="form-group">
									<label class="control-label">${type.key}</label>
									<label style="width: 20px;">:<b><c:if test="${type.value == 0}">0</c:if><c:if test="${type.value gt 0}"><a href="#" class="slaClass" val="${type.key}">${type.value}</a></c:if></b></label>  	
								</div>
								</div>
								</c:forEach>
							</c:if>
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
	
	$(document).on('click', '.slaClass', function(){
		
		$("#statusID").val($(this).attr("val"));
				if($("#domainID").val().trim()=="APSFL" || $("#domainID").val().trim()=="MSP" || $("#domainID").val().trim()=="Call Center"){
					$("#slaFormID").attr('action',"getSLAViolatedLMOTTs.do");}
				else{
					$("#statusID").val($(this).attr("val"));
					$("#slaFormID").attr('action',"getSLAViolatedLMOTTs.do");}
				$("#slaFormID").submit();
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