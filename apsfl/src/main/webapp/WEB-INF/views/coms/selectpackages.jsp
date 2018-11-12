<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.modal-dialog {
    width: 1200px;
    margin: 30px auto;
}

</style>
<form role="form" class="form-validation" name="packagesform" id="packagesform" action="#" method="post" enctype="multipart/form-data">
<div id="selectPackagesDiv">
	<div class="main-content" >
		<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
		<c:choose>
			<c:when test="${tenantType == 'SI'}"> <h2>CPE<strong> Information</strong></h2> </c:when>
			<c:otherwise><h2>Package<strong> Selection</strong></h2></c:otherwise>
		</c:choose>
			<c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<c:choose>
						<c:when test="${tenantType == 'SI'}"> <li class="active">CPE Information page</li> </c:when>
						<c:otherwise> <li class="active">Package Selection</li> </c:otherwise>
					</c:choose>
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
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Wallet</label> 
										<input type="text" id="lmoWallet" name="lmoWallet" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${lmoWalletBalence}"/>" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Credit Limit</label> 
										<input type="text" id="creditLimit" name="creditLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${creditLimit}"/>" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Remaining Limit</label> 
										<c:choose>
											<c:when test="${flag == 'true'}">
												<input type="text" id="ruserLimit" name="ruserLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" disabled>
											</c:when>
											<c:otherwise>
												<input type="text" id="ruserLimit" name="ruserLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" required readonly>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<c:choose>
							<c:when test = "${customerCafVO.cafStatus != '89'}">
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
															<td>${product.tenantname}</td>
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
															<td>${product.tenantname}</td>
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
																 <c:if  test="${ oldlist.tenantCode == product.tenantcode && oldlist.prodCode == product.prodcode && oldlist.agruniqueid == product.agruniqueid}">checked='checked'</c:if> 
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
															<td>${product.tenantname}</td>
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
																	 <c:if  test="${ oldlist.tenantCode == product.tenantcode && oldlist.prodCode == product.prodcode && oldlist.agruniqueid == product.agruniqueid}"> checked='checked'</c:if> 
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
									</c:when>
									<c:otherwise>
										<center><font color='red' size="3">No Packages found for the selected CAF Type</font></center>
									</c:otherwise>
								</c:choose>
							</c:when>
							</c:choose>	
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>CPE Information <label id="slotError" style="text-align: center;color: red;"></label></h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">CAF NO<span style="color: red;">*</span></label>
														<input type="text" name="cafNo" id="cafNo" value="${cafNo}" class="form-control form-white" readonly  >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">PoP Name<span style="color: red;">*</span></label>
														<c:if test = "${not empty customerCafVO.popId}"><input type = "hidden" name = "popId" id = "subStnId" value = "${customerCafVO.popId}"></c:if>
															<div class="option-group">
																<select name="popId" id="popId" class="form-control1 form-white disable" disabled>
																<option value="">--Select--</option>
																	<c:forEach var="pop" items="${popList}">
																		<c:choose>
																			<c:when test="${not empty customerCafVO && customerCafVO.popId == pop.substnUid}">
																				<option value="${pop.substnUid}" selected>${pop.substnName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${pop.substnUid}">${pop.substnName}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div> 
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">OLT ID<span style="color: red;">*</span></label>
																<div class="option-group">
																	<select name="oltId" id="oltId" class="form-control form-white">
																	<option value="">--Select--</option>
																	<c:forEach var="olt" items="${oltList}">
																		<c:choose>
																			<c:when test="${not empty customerCafVO.oltId}">
																				<option value="${olt.popOltSerialno}" selected>${olt.pop_oltlabelno}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${olt.popOltSerialno}">${olt.pop_oltlabelno}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																	</select>
																</div> 
															</div>
														</div>
													<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">OLT Port-ID<span style="color: red;">*</span></label> 
															<div class="option-group">
																<select name="oltPortId" id="oltPortId" class="form-control form-white">
																	<option value="">--Select--</option>
																</select>
														</div>
													</div>
												</div>
												</div>
												<div class = "row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Level-1 Slot<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="l1Slot" id="l1Slot" <c:choose><c:when test="${domain == 'SI'}"> class="form-control form-white disable"  disabled</c:when><c:otherwise> class="form-control form-white" </c:otherwise></c:choose> >
																	<option value="">--Select--</option>
																</select>
															</div> 
														</div>
													</div>
													<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Level-2 Slot<span style="color: red;">*</span></label> 
															<div class="option-group">
																<select name="l2Slot" id="l2Slot" <c:choose><c:when test="${domain == 'SI'}"> class="form-control form-white disable"  disabled</c:when><c:otherwise> class="form-control form-white" </c:otherwise></c:choose> >
																	<option value="">--Select--</option>
																</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Level-3 Slot<span style="color: red;">*</span></label> 
															<div class="option-group">
																<select name="l3Slot" id="l3Slot" <c:choose><c:when test="${domain == 'SI'}"> class="form-control form-white disable"  disabled</c:when><c:otherwise> class="form-control form-white" </c:otherwise></c:choose> >
																	<option value="">--Select--</option>
																</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">ONU Serial Number<span style="color: red;">*</span></label>
														<label id="cpeError" style="text-align: center;color: red;"></label> 
														<input type="hidden" name="bulkUpload" id="bulkUpload" value = "No" > 
														<input type="text" name="cpeId" id="cpeId" class="form-control form-white" maxlength="20" placeholder="Enter ONU Serial Number">
													</div>
												</div>
											</div>
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">ONU Mac-Id<span style="color: red;">*</span></label>
															<label id="onuMacError" style="text-align: center;color: red;"></label> 
															<input type="text" name="onuMacAddress" id="onuMacAddress" class="form-control form-white" readonly maxlength="20" placeholder="Enter ONU Mac-Id" >
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">ONU Model<span style="color: red;">*</span></label>
															<input type="hidden" name="cpeModal" id="onuModel" class="form-control form-white">
															<div class="option-group">
																<select name="" id="cpeModal" class="form-control form-white disable" disabled>
																	<option value="">--Select--</option>
																	<c:forEach var = "cpemodal" items="${cpeModalList}">
																		<c:choose>
																			<c:when test="${empty customerCafVO}">
																				<option value="${cpemodal.profileId}" selected>${cpemodal.cpeModel}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${cpemodal.profileId}">${cpemodal.cpeModel}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">No Of ONU EMI<span style="color: red;">*</span></label>
															<input type="hidden" name="installmentCount" id="onuEmiCount" class="form-control form-white" >
															<div class="option-group">
																<select name="" id="cpeDeviceStatus" class="form-control form-white disable" disabled>
																	<option value="">--Select--</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">ONU EMI Price<span style="color: red;">*</span></label> 
															<input type="text" name="onuEmiPrice" id="onuEmiPrice" class="form-control form-white number" readonly placeholder="ONU Price/Installment">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">ONU Upfront Price<span style="color: red;">*</span></label> 
															<input type="text" name="cpePrice" id="cpePrice" class="form-control form-white number" readonly placeholder="ONU Price/Installment">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Installation Charge<span style="color: red;">*</span></label> 
															<input type="text" name="instCharge" id="instCharge" class="form-control form-white numbersOnly" readonly maxlength="11" placeholder="Installation Charge">
															<input type="hidden" name="cableCharge" id="cableCharge" class="form-control form-white numbersOnly" readonly value = "0">
														</div>
													</div>
													<div class="col-sm-3 noOfTPConnDiv" >
														<div class="form-group">
															<label class="control-label">No of Telephone Connections<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="noOfTPConn" id="noOfTPConn" class="form-control form-white" disabled>
																	<c:forEach var="lovs" items="${telephoneConnections}">
																		<c:choose>
																			<c:when	test="${not empty customerCafVO && lovs.lovId == customerCafVO.channel}">
																				<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${lovs.lovValue}">${lovs.lovValue}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div> 
														</div>
													</div>
													<div class="clear"></div>
												</div>
											<!-- END ROW INNER-->
										</div>
									</div>
								</div>
							</div>
							<div>
							<div class="row stbDiv" id="stbDiv" >
								<div class="col-sm-12">
									<div class="panel ">
										<div class="panel-header bg-light">
											<h3>IPTV Box Information<label id="slotError" style="text-align: center;color: red;"></label><img style="cursor: pointer;" class="addIPTVBoxs" src="./resources/images/Add_Assets.png" align="right"/></h3>
										</div>
										<div class="panel-content" >
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">IPTV Box Serial Number<span style="color: red;">*</span></label>
														<label id="stbError" class ="stbError" style="text-align: center;color: red;"></label> 
														<input type="text" name="iptvBoxList[0].stbSerialNo" id="stbSerialNo" required class="form-control form-white stbSerialNo" maxlength="20" placeholder="Enter IPTV Box Serial Number"  >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">IPTV Box Mac-Id<span style="color: red;">*</span></label>
														<label id="stbMacError" style="text-align: center;color: red;"></label> 
														<input type="text" name="iptvBoxList[0].macAddress" id="stbMacAddress" required class="form-control form-white macAddress" readonly maxlength="20" placeholder="Enter IPTV Box Mac-Id" readonly >
													</div>
												</div>
												<div class="col-sm-3" >
													<div class="form-group">
														<label class="control-label">IPTV Box Model<span style="color: red;">*</span></label>
														<input type="hidden" name="iptvBoxList[0].stbModel" id="IPTVModel" class="form-control form-white IPTVModel" placeholder="Enter CPE Modal">
														<div class="option-group">
															<select name="" id="stbModel" class="form-control form-white stbModel disable" disabled required>
																<option value="">--Select--</option>
																<c:forEach var = "stbModel" items="${stbModelList}">
																	<c:choose>
																		<c:when test="${empty customerCafVO}">
																			<option value="${stbModel.profileId}" selected>${stbModel.cpeModel}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${stbModel.profileId}">${stbModel.cpeModel}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">No Of IPTV Box EMI<span style="color: red;">*</span></label>
														<input type="hidden" name="iptvBoxList[0].stbInstallmentCount" id="stbEmiCount" class="form-control form-white stbEmiCount" >
														<div class="option-group">
															<select name="" id="stbDeviceStatus" required class="form-control form-white disable stbDeviceStatus" disabled>
																<option value="">--Select--</option>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">IPTV Box EMI Price<span style="color: red;">*</span></label> 
														<input type="text" name="iptvBoxList[0].stbEmiPrice" id="stbEmiPrice" required class="form-control form-white number stbEmiPrice" readonly placeholder="ONU Price/Installment">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">IPTV Box Upfront Price<span style="color: red;">*</span></label> 
														<input type="text" name="iptvBoxList[0].stbPrice" id="stbPrice" required class="form-control form-white number stbPrice" readonly placeholder="IPTV Box Price">
													</div>
												</div>
												<div class="col-sm-3"> 
													<div class="form-group">
 															<label class="control-label">IPTV Box Package<span style="color: red;">*</span></label>
													     <div class="option-group">
														    <select name="iptvBoxList[0].iptvSrvcCodes" id="iptvSrvcCodes"  class=" form-control form-white iptvSrvcCodes iptvselectbox" required disabled>
												                <option class="iptvoption" value="">--Select--</option>
												            </select> 
											            </div>
												        <div class="iptvhiddendiv" id="iptvhiddendiv" style ="display:none; overflow: scroll; border-style: groove; border-width: 1px;">
												            <ul style="list-style: none;"> </ul>
												        </div>
													 </div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row stbDiv" id="stbDiv" >
								<div class="col-sm-12">
									<div class="form-group IPTVDivs">
				             		</div>
			             		</div>
		             		</div>
		             		</div>
							<div class="row vpnSrvcDiv">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>VPN Service Information <label id="slotError" style="text-align: center;color: red;"></label></h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">VPN Service Name<span style="color: red;">*</span></label> 
														<div class="option-group">
															<select name="vpnService" id="vpnService" class="form-control form-white" disabled>
																<option value="">--Select--</option>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- END ROW -->
							<input type="hidden" name="agruniqueid" id="agruniqueid" value="${customerCafVO.agruniqueid}">
							<input type="hidden" name="prodCode" id="prodCode" value="${customerCafVO.prodCode}">
							<input type="hidden" name="tenantCode" id="tenantCode" value="${customerCafVO.tenantCode}">
							<input type="hidden" name="custId" id="custId" value="${customerCafVO.custId}">
							<input type="hidden" name="custType" id="custType" value="${customerCafVO.custType}" />
							<%-- <input type="hidden" name="cafNo" id="cafNo" value="${cafNo}"> --%>
							<input type="hidden" name="telProdCode" id="telProdCode" >
							<input type="hidden" name="telSrvcCode" id="telSrvcCode" >
							<input type="hidden" name="telTenantCode" id="telTenantCode" >
							<input type="hidden" name="aadharNumber" id="aadharNumber" value="${customerCafVO.aadharNumber}" >
							<input type="hidden" name="firstName" id="firstName" value="${customerCafVO.firstName}" >
							<input type="hidden" name="custCode" id="custCode" value="${customerCafVO.custCode}" >
							<input type="hidden" name="lastName" id="lastName" value="${customerCafVO.lastName}">
							<input type="hidden" name="billCycle" id="billCycle" value="${customerCafVO.billCycle}"/>
							<input type="hidden" name="district" id="district" value="${customerCafVO.district}"/>
							<input type="hidden" name="city" id="village" value="${customerCafVO.city}"/>
							<input type="hidden" name="pinCode" id="pinCode" value="${customerCafVO.pinCode}"/>
							<input type="hidden" name="mandal" id="mandal" value="${customerCafVO.mandal}"/>
							<input type="hidden" name="status" id="status" value="${customerCafVO.status}"/>
							<input type="hidden" name="cafStatus" id="cafStatus" value="${customerCafVO.cafStatus}"/>
							<input type="hidden" name="entCustType" id="entCustType" value="${customerCafVO.entCustType}"/>
							<input type="hidden" name="lmoCode" id="lmoCode" value="${customerCafVO.lmoCode}"/>
							<input type="hidden" name="" id="lmoAgrmntMspCodes" value="${lmoAgrmntMspCodes}"/>
							<input type="hidden" name="" id="tenantType" value="${tenantType}"/>
							<input type="hidden" name="" id="iptvPackages" value="${customerCafVO.iptvPackage}"/>
							<input type="hidden" name="popDistrict" id="" value="${customerCafVO.popDistrict}"/>
							<input type="hidden" name="popMandal" id="" value="${customerCafVO.popMandal}"/>
							<c:choose>
							<c:when test = "${customerCafVO.cafStatus != '89'}">
							<input type="hidden" name="coreSrvcCode" id="pkgCoreSrvcCode" />
								<div class="pull-right">
									<c:choose><c:when test="${custType == 'ENTERPRISE'}"><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
									<c:otherwise><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
									&nbsp;
									<button class="btn btn-embossed btn-primary" type="button" id="customerCafButton" >Next</button>
									<label id="productError" style="text-align: left;color: red;"></label>
								</div>
							</c:when>
							<c:otherwise>
							<input type="hidden" name="coreSrvcCode" id="prodCoreSrvcCode" value="${customerCafVO.coreSrvcCode}"/>
							<div class="pull-right">
								<c:choose><c:when test="${custType == 'ENTERPRISE'}"><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
								<c:otherwise><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
								&nbsp;
								<button class="btn btn-embossed btn-primary" type="button" id="enterpriseBulkUploadCafButton" >Next</button>
								<label id="productError" style="text-align: left;color: red;"></label>
							</div>
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
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Wallet</label> 
											<input type="text" id="lmoWallet" name="lmoWallet" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${lmoWalletBalence}"/>" class="form-control form-white" required readonly>
										</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Credit Limit</label> 
										<input type="text" id="creditLimit" name="creditLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${creditLimit}"/>" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Remaining Limit</label> 
										<c:choose>
											<c:when test="${flag == 'true'}">
												<input type="text" id="ruserLimit" name="ruserLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" disabled>
											</c:when>
											<c:otherwise>
												<input type="text" id="ruserLimit" name="ruserLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" required readonly>
											</c:otherwise>
										</c:choose>
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
										<c:choose><c:when test="${custType == 'ENTERPRISE'}"><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
										<c:otherwise><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
										&nbsp;
										<button class="btn btn-embossed btn-primary" type="button" id="cafFeaturesButton" >Next</button>
										<label id="productError" style="text-align: left;color: red;"></label>
									</div>
								<div class="clear">
								</div>
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
