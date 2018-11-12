<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
function editRecord(id) {
	window.location.href = "./editTenantAgreementDetails?tenantCode=" + id;
}
</script>
<div class="main-content" style="">
    <!-- BEGIN PAGE CONTENT -->
     <div class="page-content page-width">
      <div class="page-title">
        <h2>Tenant <strong>Agreement Details</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./">Home</a> </li>
            <li class="active">Tenant Agreement Details</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
              <div class="row">
                <div class="col-sm-12">
                      <div class="row m-b-5">
                        <div >
                           <table class="table table-dynamic table-alt" id="tenantTable">
                            <thead>
                              <tr>
                                <th width="5%">S.No</th>
                                <th width="15%">Tenant Code</th>
                                <th width="20%">Agreement From Date</th>
                                <th width="20%">Agreement Date</th>
                                <th width="20%">Agreement To Date</th>                               
                                <th width="10%">Status</th>
                                <th style="visibility: hidden;">Actions</th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tenantAgreementList}" var="tenantAgreement" varStatus="rowNum">
                              <tr>
                                <td>${rowNum.count}</td> 
                                <td>${tenantAgreement.tenantCode}</td>
                                <td>${tenantAgreement.agrFDate}</td>
                                <td>${tenantAgreement.agreementDate}</td>
                                <td>${tenantAgreement.agreementTo}</td>
                                <td>${tenantAgreement.status}</td>
                                <td><form action="<c:url value="/editTenantAgreementDetails"/>" method="post">
					 		    <input type="hidden" name="tenantCode" value="${tenantAgreement.tenantCode}"/>
							    <input type="submit" class="btn btn-outline btn-warning" value="Edit" />
			  			        </form></td>
                              </tr>
							 </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div></div></div></div></div></div></div></div></div>
  
<%-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function editRecord(id) {
	window.location.href = "./editTenantAgreementDetails?tenantCode=" + id;
}
</script>
</head>
<body>
<table class="data_tables_won" id="tenantTable" align="center" border="1">
			<thead>
				<tr>
					<th class="aCenter">Srl</th>
					<th class="aCenter">Tenant Code</th>
					<th class="aCenter">Agreement From Date</th>
					<th class="aCenter">Agreement Date</th>
					<th class="aCenter">Agreement To Date</th>
					<th class="aCenter">Status</th>
					<th class="aCenter no_sorting_m"><div
							style="visibility: hidden;">Actions</div></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tenantAgreementList}" var="tenantAgreement"
					varStatus="rowNum">
					<tr>
						<td class="aCenter">${rowNum.count}</td>
						<td class="aLeft">${tenantAgreement.tenantCode}</td>
						<td class="aLeft">${tenantAgreement.agrFDate}</td>
						<td class="aLeft">${tenantAgreement.agreementDate}</td>
						<td class="aLeft">${tenantAgreement.agreementTo}</td>
						<td class="aLeft">${tenantAgreement.status}</td>
						<td class="aCenter">
						<form action="<c:url value="/editTenantAgreementDetails"/>" method="post">
					 		    <input type="hidden" name="tenantCode" value="${tenantAgreement.tenantCode}"/>
							    <input type="submit" class="btn btn-default" value="Edit" />
			  			        </form>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
</body>
</html> --%>