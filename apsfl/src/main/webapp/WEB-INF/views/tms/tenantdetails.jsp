<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
function editRecord(id) {
	window.location.href = "<c:url value="/editTenantDetails"/>?tenantId=" + id;
}
</script>
<div class="main-content" style="">
    <!-- BEGIN PAGE CONTENT -->
     <div class="page-content page-width">
      <div class="page-title">
        <h2>Tenant <strong> Details</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./">Home</a> </li>
            <li class="active">Tenant Details</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
            <div>
    			<center><font face="Times New Roman" size="4px" color="Green"><b>${success}</b></font></center>
     		</div>
			<%-- <form role="form" class="form-validation"> --%>
              <div class="row">
                <div class="col-sm-12">
                      <div class="row m-b-5">
                        <div >
                           <table class="table table-dynamic table-alt" id="tenantTable" width="800px">
                            <thead>
                              <tr>
                                <th width="5%">S.No</th>
                                <th width="25%">Tenant Code</th>
                                <th width="35%">Tenant Name</th>
                                <th width="10%">Tenant Type </th>
                                <!-- <th width="10%">Region</th> -->                               
                                <th width="15%">Aadhar Number</th>                               
                                <th width="10%">Mobile Number</th>
                                <th width="10%">Action</th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tenantList}" var="tenant" varStatus="rowNum">
                              <tr>
                                <td>${rowNum.count}</td> 
                                <td>${tenant.tenantCode}</td>
                                <td>${tenant.name}</td>
                                <td>${tenant.tenantTypeLov}</td>
                                <%-- <td>${tenant.region}</td> --%>
                                <td>${tenant.aadharCardNo}</td>
                                <td>${tenant.pocMobileNo1}</td>
                                <td><a class="btn btn-outline btn-warning" href="javascript:editRecord(${tenant.tenantId});">Edit</a></td>
                              </tr>
							 </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                      </div></div></div></div></div></div></div></div>
  <!-- END MAIN CONTENT --> 