<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="./resources/js/create-product.js"></script>

<script src="./resources/js/jquery.autocomplete.min.js"></script>
<script src="./resources/js/bootstrap-select.min.js"></script>
<script src="./resources/js/defaults-en_US.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">
<link href="./resources/css/bootstrap-select.min.css" rel="stylesheet">


<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Bulk Offline Payment</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
					
			<form action="uploadOfflineCheckPaymentFile" id="cpeStockUploadFormId" method="POST" enctype="multipart/form-data">
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div style="margin: auto; width: 60%" id="statusMessageId">
								<span>${response}</span>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Bulk Offline Payment</h3>
											</div>
											<div class="panel-content">
												<%-- <div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">CPE Type <span style="color: red;">*</span></label>
															<div>
																<select name="cpeType" id="cpe-Type-Id" class="form-control form-white" onchange="getAllCpeModelByCpeType()" required="required">
																	<option value="">--Select--</option>
																	<c:forEach var="cpeTypeVar" items="${cpeMasterList}">
																		<option value="">${cpeTypeVar.cpeTypeLov}</option>
																	</c:forEach>
																</select>
															</div>
														</div> 

													</div>
													<div class="col-sm-4" id="cpe-Model-Div-Id"></div>
												</div> --%>
												<div class="row"></div>
												<div class="row"></div>
												<div class="row"></div>
												<div class="row">
												<!-- <div class="col-sm-6">
												<div class="form-group">
													<label class="control-label">Tenant Name | Tenant Code<span style="color: red;">*</span></label>
													<div class="option-group">
													 <input type="text"  id="tms_olt_search" value="" class="form-control form-white" required="required" />
													</div>
												</div>
												</div>
												</div>
												<div  class="col-sm-4 row"  id="cpe-Prefixes-Id"></div>
												
									            
									            <div  class="row"  id="add-delete-buttons" style="margin-top:23px;display:none;">
													<div class="col-sm-1" style="margin-left:-55px" ><button class="btn btn-embossed btn-primary" type="Button" id="add-prefix" onclick="openPopUp()">New Prefix</button></div>
													<div class="col-sm-0"  ><button  class="btn btn-embossed btn-primary"type="Button" id="delete-prefix" style="margin-left:30px;" >Delete</button></div>
									            </div>
									            
									            <div id="divId" style="display:none;">
													Enter Prefix Value:<input type="text" name="prefixValue"/>
												</div>
																								
													 <label for="cpe-Prefixes">Cpe Prefixes<span style="color: red;">*</span></label>
													<select  name= "cpePrefixes" class="selectpicker" multiple id = "cpe-Prefixes"  required="required">
											 			<c:forEach var="prefix" items="${cpePrefixes}">
															<option >${prefix}</option>
														</c:forEach>
													</select>
										       	 -->
										       
												<div style="clear:both;"></div>
													<!-- <div class="row" >
													<div class="col-sm-8" id="cpe-cost-details-id"></div>
													<div class="col-sm-4" >
													<input type="hidden" name ="profileId" id="cpe_profile_Id" >
													<input type="hidden" value="" name ="tenantId" id="tenant_id" > -->
													<input  type="file" name="OfflineCheckPaymentFile" required="required"> 
													</div>
													</div>

												<div class="row"></div>
												<div class="row"></div>
												<br>
												<br>
												<div class="row" ">
												<div class="col-sm-2"><button class="btn btn-embossed btn-primary" type="submit" id="offlinebulkupload">Submit</button></div>
												 <div class="col-sm-1"><a class="btn btn-embossed btn-primary" href="./downloadOfflineExcelFileTemp"> Download Template</a></div>
									            </div>
											</div>
										</div>
									</div>
								</div>
								
								<div>
								${status}
								</div>
								<%-- <c:if test="${fn:length(cpeSlNoList) > 0}">
									<c:if test="${not empty cpeSlNoList[0]}">
									<div class="panel">
											<div class="panel-header bg-light">
												<h3>Error / Duplicate Entry</h3>
											</div>
											
											
											<c:if test="${not empty ErrorOrDuplicateList}">
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt" id="CPEUploadDetailsTable">
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
													<th>CAF Number</th>
													
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${ErrorOrDuplicateList}" var="cpe"
													varStatus="rowCount">
													<tr>
														<td>${rowCount.count}</td>
														<td class="cpeSrlno" id="cpeSrlno">${cpe.cpeSrlno}</td>
														<td id="macAddress">${cpe.macAddress}</td>
														<td>${cpe.cpeTypeLov}</td>
														<td>${cpe.cpeModel}</td>
														<td>${cpe.cpeBatchDate}</td>
														<td>${cpe.mspCode}</td>
														<td>${cpe.lmoCode}</td>
														<td>${cpe.districtName}</td>
														<td>${cpe.mandalName}</td>
														<td>${cpe.villageName}</td>
														<td>${cpe.cpeCafNo}</td>
														
													</tr>
												</c:forEach>
											</tbody>
										</table>
											
											
									</div>
								</div>

							 </c:if>
											
											
											
											
											
											<!--  div class="panel-content">
												<div class="row">
													 <c:set var="index" value="${1}" />
														<table class="table table-alt">
											  				<c:forEach items="${cpeSlNoList}" var="cpeSlNo">
											   					 <c:if test="${index eq 1}">
											   						 <tr>
											   					 </c:if>
															      <td> ${cpeSlNo} </td>
															      <c:if test="${endindex eq 1}">
											   						 </tr>
											   					 </c:if>
											   					 <c:if test="${index eq 7}">
											   						<c:set var="index" value="${0}" />
											   					  <c:set var="endindex" value="${0}" />
											   					 </c:if>
											   					  <c:set var="index" value="${index + 1}" />
											 				 </c:forEach>
														</table>
												</div>
										</div-->
								</div>
							 </c:if>
							 </c:if>
							 
							  <c:if test="${not empty cpeUploadedStockList}">
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt" id="CPEUploadDetailsTable">
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
													
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${cpeUploadedStockList}" var="cpe"
													varStatus="rowCount">
													
													<tr>
														<td>${rowCount.count}</td>
														<td class="cpeSrlno" id="cpeSrlno">${cpe.cpeSrlno}</td>
														<td id="macAddress">${cpe.macAddress}</td>
														<td>${cpe.cpeTypeLov}</td>
														<td>${cpe.cpeModel}</td>
														<td>${cpe.cpeBatchDate}</td>
														<td>${cpe.mspCode}</td>
														<td>${cpe.lmoCode}</td>
														<td>${cpe.districtName}</td>
														<td>${cpe.mandalName}</td>
														<td>${cpe.villageName}</td>
														
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>

							 </c:if> --%>
							</div>
						</div>
					</div>
			
			</form>	
		 </div>
		</div>

</section>

</body>
