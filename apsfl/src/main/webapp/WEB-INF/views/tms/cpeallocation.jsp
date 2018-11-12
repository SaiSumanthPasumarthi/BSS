<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%@ page session="false"%>




<html>
<style>

#load{
    width:100%;
    height:100%;
    position:fixed;
    z-index:9999;
    background:url('resources/images/ajax-loader.gif') no-repeat center center rgba(0,0,0,0.80)
}


</style>

<script type="text/javascript">

document.onreadystatechange = function () {
	  var state = document.readyState
	  if (state == 'interactive') {
	       document.getElementById('contents').style.visibility="hidden";
	  } else if (state == 'complete') {
	      setTimeout(function(){
	         document.getElementById('interactive');
	         document.getElementById('load').style.visibility="hidden";
	         document.getElementById('contents').style.visibility="visible";
	      },4500);
	  }
	}

</script>
<head>
<title>CPE Allocation</title>
<script src="./resources/js/jquery.autocomplete.min.js"></script>
<script src="./resources/js/form.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">
</head>








<body>
<div id="load"></div>
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>CPE Allocation</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">HOME</a></li>
						<li class="active">CPE Allocation</li>
					</ol>
				</div>
				<div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
				 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
				<label id="error" style="text-align: center; color: red;"></label>
				<div class="successmsg"></div>
			   </div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">

							<div class="row">
								<div style="margin: auto; width: 25%">
									<label id="id_oltMsg"></label>
								</div>
							</div>

							<form role="form" class="form-validation" name="cpeform"
								id="cpeform" action="#" method="post" enctype="multipart/form-data">
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">From Date:</label>
											<input type="text" name="fromDate" id="fromDate"  class="form-control form-white " placeholder="Select From Date" value="<c:if test="${not empty fromDate}">${fromDate}</c:if>">
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">To Date:</label>
											<!--  input type="date" name="toDate" id="toDate"  class="date-picker form-control form-white toDate" placeholder="Select To Date" value="<c:if test="${not empty toDate}">${toDate}</c:if>">-->
												<input type="text" name="toDate" id="toDate"  class="form-control form-white toDate" placeholder="Select To Date" value="<c:if test="${not empty toDate}">${toDate}</c:if>">
										</div>
									</div>
									
									
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Tenant Type</label>
											<div class="option-group">
												<select name="tenantType" id="tenantType" 
													class="form-control form-white">
														<option value="">--Select--</option>
														<c:choose>
														<c:when test="${not empty tenantType}">
															<option value="${tenantType}" selected>${tenantType}</option>
															<c:if test="${tenantType == 'MSP'}">
																<option value="LMO">LMO</option>
															</c:if>
															<c:if test="${tenantType == 'LMO'}">
																<option value="MSP">MSP</option>
															</c:if>
														</c:when>
														<c:otherwise>
															<option value="MSP">MSP</option>
															<option value="LMO">LMO</option>
														</c:otherwise>
													</c:choose>
												</select>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Tenant Code</label> 
											<input type="text" name="tenantCode" id="tenantCode"  class="form-control form-white" value="<c:if test="${not empty tenantCode}">${tenantCode}</c:if>">
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Cpe Serial Number</label> 
											<input type="text" name="cpeSerialNumber" id="cpe-Serial-Number"  class="form-control form-white" value="<c:if test="${not empty cpeSerialNumber}">${cpeSerialNumber}</c:if>"  >
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Box Type</label>
											<div class="option-group">
												<select name="cpeType" id="cpeType" 
													class="form-control form-white">
													<option value="">--Select--</option>
													<c:choose>
														<c:when test="${not empty cpeType}">
															<option value="${cpeType}" selected>${cpeType}</option>
															<c:if test="${cpeType == 'IPTV/Android Box'}">
																<option value="ONU">ONU</option>
															</c:if>
															<c:if test="${cpeType == 'ONU'}">
																<option value="IPTV/Android Box">IPTV/Android Box</option>
															</c:if>
														</c:when>
														<c:otherwise>
															<option value="ONU">ONU</option>
															<option value="IPTV/Android Box">IPTV/Android Box</option>
														</c:otherwise>
													</c:choose>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-sm-3">
										<div class="form-group">
										<input id="cpeInputStockFile" type="file"  name="cpeSearchStockFile"style="position:relative;top:30px;" >	
										<div class="col-sm-1" ><a class="btn btn-embossed btn-primary" href="./downloadSearchExcelFile" style="position:relative;left:180px;"> Download Template</a></div>
										</div>
									</div>
								</div>
								
								

								
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="button"
													id="searchCpeButton">
													<i class="fa fa-search"></i>Search
												</button>
												<a style="color:white;text-decoration: none;" href="./deleteloghistory"><button class="btn btn-embossed btn-primary" type="button"
													id="deletedCpeStockButton">
													Deleted Stocks
												</button></a>
											</div>
										</div>
									</div>
								</div>
								
							</form>
							<c:if test="${not empty message}">
								<center>
									<font color='red' size="3">${message}</font>
								</center>
							</c:if>

							<c:if test="${not empty cpeList}">
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt" id="CPEDetailsTable">
											<thead>
												<tr>
													<th>S.NO</th>
													<th>CPE SrlNO</th>
													<th>CPE Mac Address</th>
													<th>CPE Type</th>
													<th>CPE Model</th>
													<th>Batch Date</th>
													<th>MSP Code</th>
													<th>LMO Code</th>
													<th>District Name</th>
													<th>Mandal Name</th>
													<th>Village Name</th>
													<th>Edit</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${cpeList}" var="cpe"
													varStatus="rowCount">
													<tr>
														<td>${rowCount.count}</td>
														<!-- td class="cpeSrlno" id="cpeSrlno" >
														
																				<a href= " 
																					<c:url value="cpeStockCreateUpdateLogHistory">
																		
																						 <c:param name="cpesrlno" value="${cpe.cpeSrlno}"/>
																					</c:url>" 
																					>																		
																					<c:out value="${cpe.cpeSrlno}"></c:out>
																				</a>
																				
																				
																				
														</td-->
														<!-- td class="cpeSrlno" id="cpeSrlno" >${cpe.cpeSrlno}</td-->
														
														<td><a  href='cpeStockCreateUpdateLogHistory?cpesrlno=${cpe.cpeSrlno}'
																						 class="peSrlno" id="cpeSrlno">${cpe.cpeSrlno}</a></td>
														
														<td id="macAddress">${cpe.macAddress}</td>
														<td>${cpe.cpeTypeLov}</td>
														<td>${cpe.cpeModel}</td>
														<td>${cpe.cpeBatchDate}</td>
														<td>${cpe.mspCode}</td>
														<td>${cpe.lmoCode}</td>
														<td>${cpe.districtName}</td>
														<td>${cpe.mandalName}</td>
														<td>${cpe.villageName}</td>
														<td><input type="checkbox" class="checkboxx" name="ch2" value = "${rowCount.index}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
											<div class="pull-right">
												<button class="btn btn-embossed btn-primary checkAll" type="button"  id="checkall">Select All</button>&nbsp;
												<button class="btn btn-embossed btn-primary " type="button"  id="uncheckAll">Unselect All</button>&nbsp;
												<button class="btn btn-embossed btn-primary" type="button" id="deleteCPESelected">Delete Selected</button> 
											</div>
											
											<div class="" style="">
												<button style="" type="button" class="btn btn-embossed btn-primary"><a style="color:white;" href="./cpeStockDownload" class="cpeStockDownload">Download</a></button>
					 						</div>
									</div>
								</div>

								<div class="row cpeInfo" style="display: none">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Tenant Information</h3>
										</div>
										<div class="panel-content">
												<div class="row">
												<div id="id_container2" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Tenant Code<span style="color: red;">*</span></label> 
															<input type="text" name="tenantCodee" id="tenantCodee" class="form-control form-white" required>
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<div class="row">
													<div class="pull-right">
														<a href="./home"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>&nbsp;
														<button class="btn btn-embossed btn-primary" type="button" id="updateCPESubmit">Update</button> 
													</div>
												</div>
										</div>
									</div>
								</div>
							</div>
							</c:if>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	
   	
	<script src="./resources/js/olt-port-allocation.js"></script>
</body>
</html>
