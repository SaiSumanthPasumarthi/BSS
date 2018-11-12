<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.modal-dialog {
    width: 1200px;
    margin: 30px auto;
}

</style>
<form role="form" class="form-validation" name="Addpackagesform" id="Addpackagesform" action="#" method="post" >
<div id="selectPackagesDiv">
	<div class="main-content" >
		<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>Add<strong> Package</strong></h2>
			<c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Add Package </li>
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
							
							<!-- END ROW -->
							<input type="hidden" name="agruniqueid" id="agruniqueid">
							<input type="hidden" name="prodCode" id="prodCode">
							<input type="hidden" name="tenantCode" id="tenantCode">
							<input type="hidden" name="coreSrvcCode" id="pkgCoreSrvcCode" />
							<input type="hidden" name="custId" id="custId" value = "${customerCafVO.custId}">
							<input type="hidden" name="custCode" id="custCode" value = "${customerCode}">
							<input type="hidden" name="aadharNumber" id="aadharNumber" value = "${customerCafVO.custCode}">
							<input type="hidden" name="district" id="district" value = "${customerCafVO.district}" >
							<input type="hidden" name="custDistrictId" id="custDistrictId" value = "${customerCafVO.custDistrictId}" >
							<input type="hidden" name="mandal" id="mandal" value = "${customerCafVO.mandal}" >
							<input type="hidden" name="city" id="city" value = "${customerCafVO.city}" >
							<input type="hidden" name="cafNo" id="parentCafno" value = "${customerCafVO.cafNo}" >
							<input type="hidden" name="firstName" id="fName" value = "${customerCafVO.firstName}" >
							<input type="hidden" name="lastName" id="lName" value = "${customerCafVO.lastName}" >
							<input type="hidden" name="billCycle" id="billCycle" value = "${customerCafVO.billCycle}" >
							<input type="hidden" name="custType" id="custType" value = "${customerCafVO.custType}" >
							<input type="hidden" name="flag" id="flag" >
							<input type="hidden" name="changePkgFlag" id="changePkgFlag" value="${changePkgFlag}"/>
							<input type="hidden" name="prodCafNo" id="prodCafNo" value="${prodCafNo}"/>
							<c:choose>
								<c:when test="${not empty addOnProductList || not empty oneTimeProductList}">
								<div class="row m-b-10">
									<div class="col-sm-12">
										<c:if test="${not empty addOnProductList}">
										<div id="productTableDivAddOn"><b>Add On Packages</b>
										<table class="table table-alt prodclass" id="productTable">
											<thead>
												<tr>
													<th width="15%">MSP Name</th>
													<th width="20%">Package Name</th>
													<th width="30%">Services</th>
													<th width="7%">Channels</th>
													<th width="17%">Recurring Charge(Rs)<br/> <span style="font-size:13px; color:#ff0000;">(* Taxes Extra)</span> </th>
													<th width="8%">Lock-in<br/> Period <br/>Months</th>
													<th width="3%" style="padding-left:2px;"></th>
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
														<td class="coreServiceCode" style="display: none;">${product.coreSrvcCodesWithComma}</td>
														<td class="prodType" style="display: none;">${product.prodType}</td>
														<td class="mspCode" style="display: none;">${product.mspCode}</td>
														<td><table class = "coreSrvcTable">
																<tbody>
																	<c:forEach items="${product.servicesList}" var="services">
																		<tr>
																			<td class="services">${services.serviceName} (${services.coreServiceCode})</td>
																			<td class="srvcCode" style="display: none;">${services.serviceCode}</td>
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
														<td align="center"><input type="radio" name="radios" id="productCheckbox" class="productCheckbox1 addPackageRadio"/></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
										</c:if>
										<c:if test="${not empty oneTimeProductList}">
										<div id="productTableDivOneTime"><b>One-Time Packages</b>
										<table class="table table-alt prodclass" id="productTable">
											<thead>
												<tr>
													<th width="15%">MSP Name</th>
													<th width="20%">Package Name</th>
													<th width="30%">Services</th>
													<th width="7%">Channels</th>
													<th width="17%">Recurring Charge(Rs)<br/> <span style="font-size:13px; color:#ff0000;">(* Taxes Extra)</span> </th>
													<th width="8%">Lock-in<br/> Period <br/>Months</th>
													<th width="3%" style="padding-left:2px;"></th>
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
														<td class="coreServiceCode" style="display: none;">${product.coreSrvcCodesWithComma}</td>
														<td class="mspCode" style="display: none;">${product.mspCode}</td>
														<td><table class = "coreSrvcTable">
																<tbody>
																	<c:forEach items="${product.servicesList}" var="services">
																		<tr>
																			<td class="services">${services.serviceName} (${services.coreServiceCode})</td>
																			<td class="srvcCode" style="display: none;">${services.serviceCode}</td>
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
														<td align="center"><input type="radio" name="radios" id="productCheckbox" class="productCheckbox2 addPackageRadio" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
										</c:if>
									</div>
								</div>
								<!-- END ROW INNER-->
								<div id="STBBoxesCheck" style="display:none;">
									<div class="row">
										<div class="panel-header bg-light">
											<h3>Select the STB Box(s) to which the package has to be added</h3>
										</div>
										<input type = "hidden" id = "iptvCount" value = "${iptvCount}">
										<c:if test = "${not empty noOfSTBs }">
											<c:forEach items="${noOfSTBs}" var="stbBox" varStatus="loop">
												<div class="col-sm-2">
													<div class="form-group">
														<input type="checkbox" checked="checked" name="iptvBoxList[${loop.index}].stbSerialNo" id="stbSerialNoChecked${loop.index}" value="${stbBox.stbSlNo}" class="stbCafNosChecked form-white pull-left"> 
														<input type="hidden" checked="checked" name="iptvBoxList[${loop.index}].macAddress" id="macAddressChecked${loop.index}" value="${stbBox.stbMacAddr}" class="stbCafNosChecked form-white pull-left">
														<input type="hidden" checked="checked" name="iptvBoxList[${loop.index}].stbCafNo" id="stbCafNoChecked${loop.index}" value="${stbBox.stbCafNo}" class="stbCafNosChecked form-white pull-left">
														<div id="srvCodeDiv${loop.index}" class = "srvCodeDiv"></div>
														<label class="control-label p-l-10">${stbBox.stbSlNo}</label>
													</div>
												</div>
											</c:forEach>
										</c:if>
									</div>
								</div>
								<div class="pull-right">
								<label id="productError" style="text-align: left;color: red;"></label>
									<c:choose><c:when test="${custType == 'ENTERPRISE'}"><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
									<c:otherwise><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
									&nbsp;
									<button class="btn btn-embossed btn-primary" type="button" id="AddPackagesSubmitButton" >Submit</button>
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
<%-- <div id="featureParamDiv">
	<div class="main-content" >
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Telephone<strong> FeatureParameters</strong></h2>
				<c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
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
										<c:choose><c:when test="${custType == 'ENTERPRISE'}"><a href ="./enterpriseCustomer"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
										<c:otherwise><a href ="./showcustomers"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
										&nbsp;
										<button class="btn btn-embossed btn-primary" type="button" id="AddPackagesSubmitButton" >Submit</button>
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
</div> --%>
</form>
