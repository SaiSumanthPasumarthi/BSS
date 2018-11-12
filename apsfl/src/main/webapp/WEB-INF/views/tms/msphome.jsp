<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<script type="text/javascript">
function showRecord(id) {
	window.location.href = "<c:url value="/showtenantDetails"/>?tenantId=" + id;
}
</script>
<body>
<h1>
	Hello world!  
</h1>
<div class="page-content page-width">
<br><br>
<%-- <P> <a class="btn btn-outline btn-warning" href= "<c:url value="/lmo"/>">Create Tenant</a> </P>
<p> <a class="btn btn-outline btn-warning" href= "<c:url value="/lmoagreement"/>">Create Tenant Agreement with APF</a> </p>
<p> <a class="btn btn-outline btn-warning" href= "<c:url value="/lmomspagreement"/>">Create LMO Agreement with MSP</a> </p>
<p> <a class="btn btn-outline btn-warning" href= "<c:url value="/tenantapproval"/>">Agreement Approval by APF </a> </p>
<p> <a class="btn btn-outline btn-warning" href= "<c:url value="/lmoapproval"/>">LMO/MSP Agreement Approval </a> </p> --%>


  <!--  <div class="main-content" style=""> -->
    <!-- BEGIN PAGE CONTENT -->
     <div class="page-content page-width">
      <div class="page-title">
        <!-- <h2>Pending <strong>Agreement Details</strong></h2> -->
        <h2>Agreement Details</h2>
      </div>
      <%-- <div class="row">
		<div class="col-sm-4">
      		<P align="right"><a class="btn btn-outline btn-warning" href= "<c:url value="/lmo"/>">Create Tenant</a></P>
      		<p><a class="btn btn-outline btn-warning" href= "<c:url value="/lmoagreement"/>">Create Tenant Agreement with APF</a></p>
      	</div>
      </div> --%>
      <div class="row">
						<div class="col-sm-3">
						<div class="form-group">
                      </div>
                  </div>
                  <div class="col-sm-3">
                    <div class="form-group">
                     </div>
                  </div>
                  <div class="col-sm-11">
                          <div style="float : left;">
                          <div class="form-group">
                          	<a class="btn btn-outline btn-warning" href= "<c:url value="/lmo"/>">Create New Tenant</a>
                          	<%-- <a class="btn btn-outline btn-warning" href= "<c:url value="/tenantDetails"/>">Tenant Details</a> --%>
                            <%-- <a class="btn btn-outline btn-warning" href= "<c:url value="/lmoagreement"/>">Create Tenant Agreement with APF</a>
                            <a class="btn btn-outline btn-warning" href= "<c:url value="/lmomspagreement"/>">Create LMO MSP Agreement </a>
                            <a class="btn btn-outline btn-warning" href= "<c:url value="/tenantapproval"/>">Tenant Approval</a>
                            <a class="btn btn-outline btn-warning" href= "<c:url value="/lmoapproval"/>">LMO Approval</a> --%>
                        </div>
                        </div>					  
					  </div>
					  
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
            <div class="alert alert-success">
    			<center><!-- <font face="Times New Roman" size="4px" color="Green"><b> -->${success}<!-- </b></font> --></center>
     		</div>
			<%-- <form role="form" class="form-validation"> --%>
              <div class="row">
                <div class="col-sm-12">
                      <div class="row m-b-5">
                        <div >
                           <%-- <table class="table table-dynamic table-alt" id="tenantTable">
                            <thead>
                              <tr>
                                <th width="5%">S.No</th>
                                <th width="15%">Tenant Code</th>
                                <th width="20%">Agreement From Date</th>
                                <th width="20%">Agreement Date</th>
                                <th width="20%">Agreement To Date</th>                               
                                <th width="10%">Status</th>
                                <th width="10%">Action</th>
                                <!-- <th width="10%">Action</th> -->
                                <!-- <th style="visibility: hidden;">Actions</th> -->
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tenantAgreementList}" var="tenantAgreement" varStatus="rowNum">
                              <tr>
                                <td>${rowNum.count}</td> 
                                <td><a href="<c:url value="/tenantDetails"/>" >${tenantAgreement.tenantCode}</a></td>
                                <td>${tenantAgreement.agrFDate}</td>
                                <td>${tenantAgreement.agreementDate}</td>
                                <td>${tenantAgreement.agreementTo}</td>
                                <td>${tenantAgreement.status}</td>
                                <td><form action="<c:url value="/editTenantAgreementDetails"/>"  method="post">
					 		    		<input type="hidden" name="tenantCode" value="${tenantAgreement.tenantCode}"/>
							    		<input type="submit" class="btn btn-outline btn-primary" value="Edit" />
			  			        	</form>
			  			        </td>
                                <td><a class="btn btn-outline btn-danger" href="javascript:editRecord(${tenant.tenantId});">Edit</a></td>
                                <td><form action="<c:url value="/editTenantAgreementDetails"/>" method="post">
					 		    		<input type="hidden" name="tenantCode" value="${tenantAgreement.tenantCode}"/>
							    		<input type="submit" class="btn btn-outline btn-primary" value="Edit" />
			  			        	</form></td>
                              </tr>
							 </c:forEach>
                            </tbody>
                          </table> --%>
                          <table class="table table-dynamic table-alt" id="tenantTable" width="700px">
                            <thead>
                              <tr>
                                <th width="5%">S.No</th>
                                <th width="10%">LMO Code</th>
                                <th width="15%">LMO Name</th>
                                <th width="10%">City</th>
                                <th width="10%">Mobile Number</th>
                                <th width="10%">Approval Status</th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tenantAgreementList}" var="tenantAgreement" varStatus="rowNum">
                              <tr>
                                <td>${rowNum.count}</td> 
                                <td>${tenantAgreement.tenantCode}</td>
                              </tr>
							 </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div></div></div></div></div></div></div></div></div></div>

					 
					 
</body>
</html>
