<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.modal-dialog {
    width: 1200px;
    margin: 30px auto;
}
</style>
<form role="form" class="form-validation" name="bulkCafUploadForm" id="bulkCafUploadForm" action="#" method="post" enctype="multipart/form-data">
<div id="selectPackagesDiv">
	<div class="main-content" >
		<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>Bulk Cafs<strong> Upload</strong></h2>
			<c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Bulk Cafs Upload</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
							<div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Name</label> 
											<input type="text" name="lmoName" value="${lmoName}" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
										<c:choose>
										<c:when test = "${not empty enterpriseCustomerVO.bulkUploadFlag && enterpriseCustomerVO.bulkUploadFlag == 'SingalCaf'}">
											<h3>CAF Information <label id="" style="text-align: center;color: red;"></label></h3>
										</c:when>
										<c:otherwise>
											<h3>CAFS Upload Information <label id="" style="text-align: center;color: red;"></label></h3>
										</c:otherwise>
										</c:choose>
										</div>
										<div class="panel-content">
										<c:choose>
										<c:when test = "${not empty enterpriseCustomerVO.bulkUploadFlag && enterpriseCustomerVO.bulkUploadFlag == 'SingalCaf'}">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">APSFL Code<span style="color: red;">*</span></label>
														<label id="apsflUniqueIdError" style="text-align: center;color: red;"></label> 
														<input type="hidden" name="bulkUpload" id="bulkUpload" value = "SingalCaf" >
														<input type="text" name="apsflUniqueId" id="apsflUniqueId" class="form-control form-white" placeholder="Enter Value" maxlength="10" required  >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
													<label class="control-label">District<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="district" id="apsflDistrict" class="form-control form-white" required>
																<option value="">--Select--</option>
																<c:forEach var="district" items="${districtList}">
																	<c:choose>
																		<c:when test="${not empty cafObject && cafObject.instDistrict == district.districtUid}">
																			<option value="${district.districtUid},${district.districtName}" selected>${district.districtName}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${district.districtUid},${district.districtName}">${district.districtName}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Mandal<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="mandal" id="apsflMandal" class="form-control form-white" required>
																	<option value="">--Select--</option>
																	<c:choose>
																		<c:when test="${not empty mandalList}">
																			<c:forEach var="mandal" items="${mandalList}">
																				<c:choose>
																					<c:when test="${not empty cafObject.instMandal && mandal.mandalSlno == cafObject.instMandal && cafObject.instDistrict == mandal.districtUid}">
																						<option value="${mandal.mandalSlno},${mandal.mandalName}" selected>${mandal.mandalName}</option>
																					</c:when>
																					<c:otherwise>
																						<option value="${mandal.mandalSlno},${mandal.mandalName}">${mandal.mandalName}</option>
																					</c:otherwise>
																				</c:choose>
																			</c:forEach>
																		</c:when>
																	</c:choose>
																</select>
															</div>
														</div>
													</div>
												<!-- <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">District<span style="color: red;">*</span></label> 
														<input type="text" name="district" id="district" class="form-control form-white" placeholder="Enter Value" maxlength="100" required  >
													</div>
												</div> -->
												<!-- <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Mandal<span style="color: red;">*</span></label> 
														<input type="text" name="mandal" id="mandal" class="form-control form-white" placeholder="Enter Value" maxlength="100" required >
													</div>
												</div> -->
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Location<span style="color: red;">*</span></label> 
														<input type="text" name="location" id="location" class="form-control form-white" placeholder="Enter Value" maxlength="100" required >
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class = "row"> 
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Organization Name and Address</label> 
														<input type="text" name="firstName" id="firstName" class="form-control form-white" maxlength="100" placeholder="Enter Value" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Name<span style="color: red;">*</span></label> 
														<input type="text" name="pocName" id="pocName" class="form-control form-white" maxlength="100" placeholder="Enter Value" > 
													</div>
												</div>
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Designation</label> 
														<input type="text" name="pocDesignation" id="pocDesignation"  class="form-control form-white" maxlength="100" placeholder="Enter Value" >
													</div>
											    </div>
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Mobile No<span style="color: red;">*</span></label> 
														<input type="text" name="mobileNo" id="mobileNo" class="form-control form-white number" maxlength="10" placeholder="Enter Value" >
													</div>
												</div>
											</div>
											<div class = "row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Contact Person Email</label> 
														<input type="email" name="emailId" id="emailId" class="form-control form-white" maxlength="100" placeholder="Enter Value" >
													</div>
											    </div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">LMO Name<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="lmoCode" id="lmoCode" class="form-control form-white" >
																<option value="">--Select--</option>
																<c:forEach var="tenantType" items="${tenantTypeList}">
																	<c:choose>
																		<c:when test="${not empty enterpriseCustomer && tenantType[0] == enterpriseCustomer.tenantCode}">
																			<option value="${tenantType[0]}">${tenantType[1]}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${tenantType[0]}">${tenantType[1]}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Longitude</label> 
														<input type="text" name="longitude" id="longitude" class="form-control form-white numbersOnly" maxlength="16" placeholder="Enter Value">
													</div>
											    </div> 
											    <div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Latitude</label> 
														<input type="text" name="latitude" id="latitude" class="form-control form-white numbersOnly" maxlength="16" placeholder="Enter Value">
													</div>
											    </div>
											 </div>
										</c:when>
										<c:otherwise>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Select File<span style="color: red;">*</span></label>
														<input type="file" name="bulkUploadFile" id="bulkUploadFile" class="" >
														<input type="hidden" name="bulkUpload" id="bulkUpload" value = "MultipleCafs" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<a href ="./downloadExcelSheet?from=BulkCafs"><button class="btn btn-embossed btn-primary" type="button">Download Template</button></a>
													</div>
												</div>
											</div>
										</c:otherwise>
										</c:choose>
										<input type="hidden" name="agruniqueid" id="agruniqueid">
										<input type="hidden" name="prodCode" id="prodCode">
										<input type="hidden" name="tenantCode" id="tenantCode">
										<input type="hidden" name="telProdCode" id="telProdCode" >
										<input type="hidden" name="telSrvcCode" id="telSrvcCode" >
										<input type="hidden" name="telTenantCode" id="telTenantCode" >
										<input type="hidden" name="custCode" id="pmntCustId" value = "${enterpriseCustomerVO.custId}" >
										<input type="hidden" name="aadharNumber" id="regNumber" value = "${enterpriseCustomerVO.uidNo}" >
										<input type="hidden" name="billCycle" id="billCycle" value = "${enterpriseCustomerVO.billCycle}" >
										<input type="hidden" name="custType" value = "${enterpriseCustomerVO.custType}" />
										<input type="hidden" name="entCustType" value = "${enterpriseCustomerVO.officeTypeLov}" />
										<input type="hidden" name="stbModel" value = "NA" />
										<!-- END ROW INNER-->
										</div>
									</div>
								</div>
							</div>
							<c:choose>
								<c:when test="${not empty baseProductList}">
								<div class="row m-b-10">
									<div class="col-sm-12">
									<div id="productTableDiv"><b>Base Packages</b>
										<table class="table table-alt prodclass" id="productTable">
											<thead>
												<tr>
													<th width="15%">MSP Name</th>
													<th width="20%">Package Name</th>
													<th width="30%">Services</th>
													<th width="7%">Channels</th>
													<th width="16%">Recurring Charge(Rs)<br/> <span style="font-size:13px; color:#ff0000;">(* Taxes Extra)</span> </th>
													<th width="7%">Lock-in<br/> Period <br/>Months</th>
													<th width="4%" style="padding-left:2px;"></th>
												</tr>
											</thead>
											<tbody>
											 
												<c:forEach items="${baseProductList}" var="product" >
													<tr class="productTr">
														<td>${product.mspCode}</td>
														<td class="pkgType" style="display: none;"><c:choose><c:when test="${product.prodType == 'B'}">Base</c:when>
														<c:when test="${product.prodType == 'A'}">Add On</c:when>
														<c:otherwise>One Time</c:otherwise></c:choose></td>
														<td class="prodName">${product.prodname}</td>
														<td class="agruniqueid" style="display: none;">${product.agruniqueid}</td>
														<td class="tenantCode" style="display: none;">${product.tenantcode}</td>
														<td class="prodCode" style="display: none;">${product.prodcode}</td>
														<td class="prodType" style="display: none;">${product.prodType}</td>
														<td class="mspCode" style="display: none;">${product.mspCode}</td>
														<td><table class = "coreSrvcTable">
																<tbody>
																	<c:forEach items="${product.servicesList}" var="services" varStatus="rowNum">
																		<tr>
																			<td class="services">${services.serviceName} (${services.coreServiceCode})</td>
																			<c:choose><c:when test="${rowNum.count == 1}">
																				<td class="srvcCode" style="display: none;">${services.serviceCode}</td>
																				<td class="coreServiceCode" style="display: none;">${services.coreServiceCode}</td>
																			</c:when>
																			<c:otherwise>
																				<td class="srvcCode" style="display: none;">,${services.serviceCode}</td>
																				<td class="coreServiceCode" style="display: none;">,${services.coreServiceCode}</td>
																			</c:otherwise>
																			</c:choose>
																			<c:if test="${services.coreServiceCode == 'VOIP'}">
																				<td class="telSrvcCode" style="display: none;">${services.serviceCode}</td>
																				<td class="featureParamCode" style="display: none;">${services.featureCodes}</td>
																			</c:if>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</td>
														<td align = "center"><table class = "">
																<tbody>
																	<c:forEach items="${product.servicesList}" var="channel" varStatus="rowNum">
																		<tr>
																			<td class="">
																			<c:if test="${channel.coreServiceCode == 'IPTV'}">
																				<input type = "hidden" class = "iptvServiceCode" value= "${channel.serviceCode}">
																				<span class="viewChanel" data-toggle="modal" data-target="#myModal" onmouseover="this.style.cursor='pointer'" title="View Chanel Details"><img src="./resources/images/apf_view.png"></span>
																			</c:if>
																			</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</td>
														<td class="prodCharge">
															<c:forEach items="${product.chargeTypeList}" var="charge">
															<c:if test="${charge.chargeType == 'Recurring'}"><fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${charge.chargeAmt}" /></c:if>
															</c:forEach>
														</td>
														<td class="lockPeriod">${product.lockInPeriod}</td>
														<c:choose>
														<c:when test="${not empty OldSrvcList}">
														<td align="center"><input type="checkbox" id="productCheckbox" class="productCheckbox" 
														<c:set var="count"  value="${0}" scope="page"/> 
														 <c:forEach items="${OldSrvcList}" var="oldlist" >
																 <c:if  test="${ oldlist.tenantCode == product.tenantcode && oldlist.prodCode == product.prodcode && oldlist.agruniqueid == product.agruniqueid }">
																 	<c:set var="count" value="${count+1}" />
																 </c:if>  
														</c:forEach> 
														<c:choose>
															<c:when test="${count == 1}">checked </c:when>
															<c:otherwise>disabled</c:otherwise>
														</c:choose> />
														</td>
														</c:when>
														<c:otherwise>
														<td align="center"><input type="checkbox" id="productCheckbox" class="productCheckbox" ></td>
														</c:otherwise>
														</c:choose>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
										<c:if test="${not empty addOnProductList}">
										<div id="productTableDiv1"><b>Add On Packages</b>
										<table class="table table-alt prodclass" id="productTable">
											<thead>
												<tr>
													<th width="15%">MSP Name</th>
													<th width="20%">Package Name</th>
													<th width="30%">Services</th>
													<th width="7%">Channels</th>
													<th width="16%">Recurring Charge(Rs)<br/> <span style="font-size:13px; color:#ff0000;">(* Taxes Extra)</span> </th>
													<th width="7%">Lock-in<br/> Period <br/>Months</th>
													<th width="4%" style="padding-left:2px;"></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${addOnProductList}" var="product" >
													<tr class="productTr">
														<td>${product.mspCode}</td>
														<td class="pkgType" style="display: none;"><c:choose><c:when test="${product.prodType == 'B'}">Base</c:when>
														<c:when test="${product.prodType == 'A'}">Add On</c:when>
														<c:otherwise>One Time</c:otherwise></c:choose></td>
														<td class="prodName">${product.prodname}</td>
														<td class="agruniqueid" style="display: none;">${product.agruniqueid}</td>
														<td class="tenantCode" style="display: none;">${product.tenantcode}</td>
														<td class="prodCode" style="display: none;">${product.prodcode}</td>
														<td class="prodType" style="display: none;">${product.prodType}</td>
														<td class="mspCode" style="display: none;">${product.mspCode}</td>
														<td><table class = "coreSrvcTable">
																<tbody>
																	<c:forEach items="${product.servicesList}" var="services">
																		<tr>
																			<td class="services">${services.serviceName} (${services.coreServiceCode})</td>
																			<td class="srvcCode" style="display: none;">${services.serviceCode}</td>
																			<td class="coreServiceCode" style="display: none;">${services.coreServiceCode}</td>
																			<c:if test="${services.coreServiceCode == 'VOIP'}">
																				<td class="telSrvcCode" style="display: none;">${services.serviceCode}</td>
																				<td class="featureParamCode" style="display: none;">${services.featureCodes}</td>
																			</c:if>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</td>
														<td align = "center"><table class = "">
																<tbody>
																	<c:forEach items="${product.servicesList}" var="channel" varStatus="rowNum">
																		<tr>
																			<td class="">
																			<c:if test="${channel.coreServiceCode == 'IPTV'}">
																				<input type = "hidden" class = "iptvServiceCode" value= "${channel.serviceCode}">
																				<span class="viewChanel" data-toggle="modal" data-target="#myModal" onmouseover="this.style.cursor='pointer'" title="View Chanel Details"><img src="./resources/images/apf_view.png"></span>
																			</c:if>
																			</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</td>
														<td class="prodCharge">
															<c:forEach items="${product.chargeTypeList}" var="charge">
															<c:if test="${charge.chargeType == 'Recurring'}"><fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${charge.chargeAmt}" /></c:if>
															</c:forEach>
														</td>
														<td class="lockPeriod">${product.lockInPeriod}</td>
														<td align="center"><input type="checkbox" id="productCheckbox" class="productCheckbox1 iptvAddonClass"
														<c:forEach items="${OldSrvcList}" var="oldlist" >
															 <c:if  test="${ oldlist.tenantCode == product.tenantcode && oldlist.prodCode == product.prodcode && oldlist.agruniqueid == product.agruniqueid}">checked='checked' </c:if> 
														</c:forEach>
														/>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
										</c:if>
										<c:if test="${not empty oneTimeProductList}">
										<div id="productTableDiv2"><b>One-Time Packages</b>
										<table class="table table-alt prodclass" id="productTable">
											<thead>
												<tr>
													<th width="15%">MSP Name</th>
													<th width="20%">Package Name</th>
													<th width="30%">Services</th>
													<th width="7%">Channels</th>
													<th width="16%">Recurring Charge(Rs)<br/> <span style="font-size:13px; color:#ff0000;">(* Taxes Extra)</span> </th>
													<th width="7%">Lock-in<br/> Period <br/>Months</th>
													<th width="4%" style="padding-left:2px;"></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${oneTimeProductList}" var="product" >
													<tr class="productTr">
														<td>${product.mspCode}</td>
														<td class="pkgType" style="display: none;"><c:choose><c:when test="${product.prodType == 'B'}">Base</c:when>
														<c:when test="${product.prodType == 'A'}">Add On</c:when>
														<c:otherwise>One Time</c:otherwise></c:choose></td>
														<td class="prodName">${product.prodname}</td>
														<td class="agruniqueid" style="display: none;">${product.agruniqueid}</td>
														<td class="tenantCode" style="display: none;">${product.tenantcode}</td>
														<td class="prodCode" style="display: none;">${product.prodcode}</td>
														<td class="prodType" style="display: none;">${product.prodType}</td>
														<td class="mspCode" style="display: none;">${product.mspCode}</td>
														<td><table class = "coreSrvcTable">
																<tbody>
																	<c:forEach items="${product.servicesList}" var="services">
																		<tr>
																			<td class="services">${services.serviceName} (${services.coreServiceCode})</td>
																			<td class="srvcCode" style="display: none;">${services.serviceCode}</td>
																			<td class="coreServiceCode" style="display: none;">${services.coreServiceCode}</td>
																			<c:if test="${services.coreServiceCode == 'VOIP'}">
																				<td class="telSrvcCode" style="display: none;">${services.serviceCode}</td>
																				<td class="featureParamCode" style="display: none;">${services.featureCodes}</td>
																			</c:if>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</td>
														<td align = "center"><table class = "">
																<tbody>
																	<c:forEach items="${product.servicesList}" var="channel" varStatus="rowNum">
																		<tr>
																			<td class="">
																			<c:if test="${channel.coreServiceCode == 'IPTV'}">
																				<input type = "hidden" class = "iptvServiceCode" value= "${channel.serviceCode}">
																				<span class="viewChanel" data-toggle="modal" data-target="#myModal" onmouseover="this.style.cursor='pointer'" title="View Chanel Details"><img src="./resources/images/apf_view.png"></span>
																			</c:if>
																			</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</td>
														<td class="prodCharge">
															<c:forEach items="${product.chargeTypeList}" var="charge">
															<c:if test="${charge.chargeType == 'Recurring'}"><fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${charge.chargeAmt}" /></c:if>
															</c:forEach>
														</td>
														<td class="lockPeriod">${product.lockInPeriod}</td>
														<td align="center"><input type="checkbox" id="productCheckbox" class="productCheckbox2 iptvOneTimeClass" 
															 <c:forEach items="${OldSrvcList}" var="oldlist" >
																 <c:if  test="${ oldlist.tenantCode == product.tenantcode && oldlist.prodCode == product.prodcode && oldlist.agruniqueid == product.agruniqueid}">checked='checked'</c:if> 
															</c:forEach> />
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
										</c:if>
									</div>
								</div>
								<!-- END ROW INNER-->
								<div class="pull-right">
									<c:choose><c:when test="${enterpriseCustomerVO.custType != 'INDIVIDUAL'}"><a href ="./enterpriseCustomer"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
									<c:otherwise><a href ="./showcustomers"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
									&nbsp;
									<button class="btn btn-embossed btn-primary" type="button" id="customerCafButton" >Next</button>
									<label id="productError" style="text-align: left;color: red;"></label>
								</div>
								</c:when>
								<c:otherwise>
									<center><font color='red' size="3">No Packages found for the selected CAF Type</font></center>
								</c:otherwise>
							</c:choose>
							<div class="clear"></div>
						<!-- END FORM -->
					</div>
					<!-- END MAIN PANEL CONTENT -->
				</div>
				<!-- END MAIN PANEL -->
			</div>
			<!-- HERE COMES YOUR CONTENT -->
		</div>
		<!-- END MAIN ROW -->
		<div class="clear"></div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
	<div id="myModal" class="modal fade" role="dialog">
		 <div class="modal-dialog">
		   <!-- Modal content-->
		   <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Channel Names</h4>
		      </div>
		      <div class="modal-body" id="srvcFutuPopUoDiv">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
	  	</div>
	</div>
</div>
<div id="featureParamDiv">
	<div class="main-content" >
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Telephone<strong> FeatureParameters</strong></h2>
				<%-- <c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if> --%>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">Home</a></li>
						<li class="active">Telephone FeatureParam</li>
					</ol>
				</div>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Name</label> 
											<input type="text" name="lmoName" value="${lmoName}" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row m-b-10">
								<div  class="col-sm-12">
								 <div id="tel_features" class="panel">
								 </div>
								</div>
							</div>
							<!-- END ROW INNER-->
							<div class="pull-right">
								<c:choose><c:when test="${enterpriseCustomerVO.custType != 'INDIVIDUAL'}"><a href ="./enterpriseCustomer"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
								<c:otherwise><a href ="./showcustomers"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
								&nbsp;
								<button class="btn btn-embossed btn-primary" type="button" id="cafFeaturesButton" >Submit</button>
								<label id="productError" style="text-align: left;color: red;"></label>
							</div>
							<div class="clear"></div>
							<!-- END FORM -->
						</div>
						<!-- END MAIN PANEL CONTENT -->
					</div>
					<!-- END MAIN PANEL -->
				</div>
				<!-- HERE COMES YOUR CONTENT -->
			</div>
			<!-- END MAIN ROW -->
			<div class="clear"></div>
		</div>
	<!-- END PAGE CONTENT -->
	</div>
</div>
</form>
