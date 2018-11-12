<!-- END HEADER -->
<!-- BEGIN MAIN CONTENT -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="productServiceDiv">
<div class="main-content" >
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Product<strong> Selection</strong>
			</h2>
			<c:if test="${not empty message}"> <center id="comsErrorMsg"><font color='green' size="3">${message}</font></center></c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Product Selection</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form role="form" id="searchProducts" method="post">
							<div class="row">
								<div class="col-sm-2 m-t-5"><strong>Services<span style="color: red;">*</span></strong></div>
								<c:forEach items="${coreSvrcList}" var="coreService">
									<div class="col-sm-2 m-t-5">
										<label class="input-group">
											<input type="hidden" name="coreSrvcCode" id="coreSrvcCode">
											<input type="hidden" name="customerCode" id="customerCode" value="${entCustomer.custId}">
											<input type="checkbox" name="coreService" id="coreService"
											<c:choose><c:when test="${fn:contains(coreSrvcCode,coreService.code)}"> value="${coreService.code}" checked </c:when>    
    										<c:otherwise>value="${coreService.code}"</c:otherwise></c:choose>>${coreService.name}
    									</label>
									</div>
								</c:forEach>
							<div class="col-sm-2">
								<button class="btn btn-embossed btn-primary" type="button" id="getProducts"><i class="fa fa-search"></i>Search Products</button>
									<label id="coreError" style="text-align: left;color: red;"></label>
							</div>
							</div>
							<!-- END ROW INNER-->
							<c:if test="${not empty productForm}">
							<div class="row m-b-10">
								<div class="col-sm-12">
									<table class="table table-alt" id="productTable">
										<thead>
											<tr>
												<th>MSP Name</th>
												<th>Product Name</th>
												<th>Services</th>
												<th>Product Charge</th>
												<th>Tax</th>
												<th>Total Charge</th>
												<th>Lock in Period(Months)</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${productForm}" var="product" >
												<tr class="productTr">
													<td class="tenantCode">${product.tenantname}</td>
													<td class="prodName">${product.prodname}</td>
													<td class="prodCode" style="display: none;">${product.prodcode}</td>
													<td><table class = "coreSrvcTable">
															<tbody>
																<c:forEach items="${product.servicesList}" var="services">
																	<tr>
																		<td class="services">${services.serviceName} (${services.serviceCode})</td>
																		<td class="coreServiceCode" style="display: none;">${services.coreServiceCode}</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table></td>
													<td class="prodCharge"><fmt:formatNumber groupingUsed="true" minFractionDigits="2" value="${product.prodcharge}" /></td>
													<td class="taxAmount"><fmt:formatNumber groupingUsed="true" minFractionDigits="2" value="${product.taxamt}"/></td>
													<td class="totalCharge"><fmt:formatNumber groupingUsed="true" minFractionDigits="2" value="${product.totalCharge}"/></td>
													<td class="lockPeriod">${product.lockInPeriod}</td>
													<%-- <td><table>
															<tbody>
																<c:forEach items="${product.servicesList}" var="lockPeriod">
																	<tr>
																		<td class="lockPeriod">${lockPeriod.lockInPeriod}
																		</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table></td> --%>
													<td class="aLeft"><input type="checkbox" id="productCheckbox" class="productCheckbox" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END ROW INNER-->
							<div class="pull-right">
								<a href ="./createcustomer"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
								&nbsp;
								<button class="btn btn-embossed btn-primary" type="button" id="next" >Next</button>
									<label id="productError" style="text-align: left;color: red;"></label>
							</div>
							</c:if>
							<div class="clear"></div>
						</form>
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
<div id="cafDetailsDiv">
	<div class="main-content" style="display: none;">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2> CAF<strong> Entry</strong></h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">Home</a></li>
						<li class="active">CAF Entry</li>
					</ol>
				</div>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<form role="form" class="form-validation" name="customerform" id="customerform" action="./createCustomerCafDetails" method="post" enctype="multipart/form-data">
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Selected Products and Services</h3>
											</div>
											<div class="panel-content">
												<div class="row">
											<div class="col-sm-12">
													<div class="panel-content">
														<div class="row">
															<%-- <div class="col-sm-4">
																<div class="form-group">
																	<label class="control-label">LMO Code</label> 
																		<input type="text" name="lmoCode" id="lmoCode" value="${lmoCode}"  class="form-control form-white" placeholder="LMO Code" required readonly>
																</div>
															</div> --%>
															<div class="col-sm-4">
																<div class="form-group">
																	<label class="control-label">LMO Name</label> 
																		<input type="text" name="lmoName" value="${lmoName}" class="form-control form-white" required readonly>
																</div>
															</div>
															<div class="col-sm-4">
																<div class="form-group">
																	<label class="control-label">LMO Wallet</label> 
																	<input type="text" id="lmoWallet" name="lmoWallet" value="${lmoWalletBalence}" class="form-control form-white" required readonly>
																</div>
															</div>

															<div class="clear"></div>
														</div>
													</div>
											</div>
										</div>
												<div class="row">
													<div class="col-sm-12 disable-search">
														<table class="table table-dynamic table-alt" id="selectedProductListTable">
															<thead>
																<tr>
																	<th>S.No</th>
																	<th>Product</th>
																	<th>Services</th>
																	<th>Product Charge</th>
																	<th>Tax</th>
																	<th>Total Charge</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</div>
													<div class="clear"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->


								<div class="row">
									<div class="col-sm-2">
										<div class="form-group">
											<label class="control-label">CAF No<span style="color: red;">*</span></label>
												<input type="hidden" name="lmoCode" value="${lmoCode}" >
												<input type="hidden" name="prodCode" id="prodCode">
												<input type="hidden" name="totalAmount" id="totalAmount"> 
												<input type="hidden" name="totalTax" id="totalTax">  
												<input type="hidden" name="custId" id="custId" value="${customerCafVO.custId}">  
												<input type="text" name="cafNo" id="cafNo" value="${cafNo}" class="form-control form-white" placeholder="CAF No" readonly>
										</div>
									</div>
									<div class="col-sm-2">
										<div class="form-group">
											<label class="control-label">Type of Customer<span style="color: red;">*</span></label>
											<div class="option-group">
												<select name="custType" id="custType" class="form-control form-white">
													<option value="">--Select--</option>
													<option value="I">Individual</option>
													<option value="E">Enterprise</option>
												</select>
											</div>
										</div>
									</div>
									<%-- <div class="col-sm-2">
										<div class="form-group">
											<label class="control-label">LMO Code<span style="color: red;">*</span></label> 
												<input type="hidden" name="prodCode" id="prodCode">
												<input type="hidden" name="totalAmount" id="totalAmount"> 
												<input type="hidden" name="totalTax" id="totalTax">  
												<input type="hidden" name="custId" id="custId" value="${customerCafVO.custId}"> 
												<input type="text" name="lmoCode" id="lmoCode" class="form-control form-white" value="${lmoCode}" readonly required>
										</div>
									</div>
									<div class="col-sm-2">
										<div class="form-group">
											<label class="control-label">LMO Name<span style="color: red;">*</span></label> 
												<input type="text" name="lmoName" id="lmoName" class="form-control form-white" value="${lmoName}" readonly required>
										</div>
									</div> --%>
									<div class="col-md-2">
										<div class="form-group">
											<label class="form-label">Aadhar No<span style="color: red;">*</span></label> 
												<input type="text" name="aadharNumber" id="aadharNumber" class="form-control form-white numbersOnly" placeholder="Aadhar No" maxlength="12" required>
										</div>
									</div>
									<div class="col-sm-2">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="button" id="getAadharDetails" disabled>
													<i class="icon-plus"></i>Retrieve Customer Info
												</button><label id="aadharError" style="text-align: left;color: red;"></label>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Customer Information</h3>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Title<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="titleLovName" id="titleLovName" class="form-control form-white">
																	<option value="">--Select--</option>
																	<c:forEach var="lovs" items="${customerTypeList}">
																		<c:choose>
																			<c:when
																				test="${not empty customerCafVO && lovs.lovId == customerCafVO.title}">
																				<option value="${lovs.lovId}" selected>${lovs.lovValue}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${lovs.lovId}">${lovs.lovValue}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">First Name<span style="color: red;">*</span></label> 
																<input type="text" name="firstName" id="firstName" class="form-control form-white" placeholder="Enter First Name">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Middle Name</label> 
																<input type="text" name="middleName" id="middleName" class="form-control form-white" placeholder="Middle Name">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Last Name<span style="color: red;">*</span></label> 
																<input type="text" name="lastName" id="lastName" class="form-control form-white" placeholder="Last Name">
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Father's/HusbandName</label> 
																<input type="text" name="fatherName" id="fatherName" class="form-control form-white" placeholder="Father's/Husband Name">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Date of Birth<span style="color: red;">*</span></label> 
																<input type="text" name="dob" id="dob" class="date-picker form-control form-white" readonly placeholder="Select Date of Birth...">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Gender<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="gender" id="gender" class="form-control form-white">
																	<option value="">--Select--</option>
																	<option value="M">Male</option>
																	<option value="F">Female</option>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Email<span style="color: red;">*</span></label> 
																<input type="text" name="emailId" id="emailId" class="form-control form-white" placeholder="Enter Email">
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Other Information</h3>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Bill Cycle<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="billCycle" id="billCycle" class="form-control form-white">
																	<option value="">--Select--</option>
																	<c:forEach var="billCycle" items="${billCycleList}">
																		<c:choose>
																			<c:when
																				test="${not empty customerCafVO && billCycle.billCycle == customerCafVO.billCycle}">
																				<option value="${billCycle.billCycle}" selected>${billCycle.billCycle}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${billCycle.billCycle}">${billCycle.billCycle}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Segment<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="segment" id="segment" class="form-control form-white">
																	<option value="">Select Segment</option>
																	<c:forEach var="lovs" items="${segmentList}">
																		<c:choose>
																			<c:when
																				test="${not empty customerCafVO && lovs.lovId == customerCafVO.segment}">
																				<option value="${lovs.lovId}" selected>${lovs.lovValue}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${lovs.lovId}">${lovs.lovValue}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Channel<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="channel" id="channel" class="form-control form-white">
																	<option value="">--Select--</option>
																	<c:forEach var="lovs" items="${channelList}">
																		<c:choose>
																			<c:when
																				test="${not empty customerCafVO && lovs.lovId == customerCafVO.channel}">
																				<option value="${lovs.lovId}" selected>${lovs.lovValue}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${lovs.lovId}">${lovs.lovValue}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>

													<div class="clear"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->

								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>CPE Information</h3>
												<label id="macError" style="text-align: center;color: red;"></label>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">MAC Address<span style="color: red;">*</span></label> 
																<input type="text" name="macAddress" id="macAddress" class="form-control form-white" placeholder="Enter MAC Address">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">CPE ID<span style="color: red;">*</span></label> 
																<input type="text" name="cpeId" id="cpeId" class="form-control form-white" placeholder="Enter CPE ID">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">CPE Lease</label>
															<div class="clear"></div>
															<div class="pull-left" style="padding-left: 0;">
																<input type="radio" name="cpeLease" id="cpeLease" class="form-white" value="Y" checked>&nbsp;&nbsp;Yes
															</div>
															<div class="col-sm-6">
																<input type="radio" name="cpeLease" id="cpeLease1" class="form-white" value="N">&nbsp;&nbsp;No
															</div>
															<div class="clear"></div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Lock-in Months</label> 
																<input type="text" name="lockPeriod" id="lockPeriod" class="form-control form-white numbersOnly" maxlength="2" placeholder="Lock-in Months">
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Longitude</label> 
																<input type="text" name="longitude" id="longitude" class="form-control form-white numbersOnly" placeholder="Longitude">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Latitude</label> 
																<input type="text" name="latitude" id="latitude" class="form-control form-white numbersOnly" placeholder="Latitude">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Location</label> 
																<input type="text" name="location" id="location" class="form-control form-white" placeholder="Enter Location">
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->

								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Installation Address</h3>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Address Line1<span style="color: red;">*</span></label> 
																<input type="text" name="address1" id="address1" class="form-control form-white" placeholder="Enter Address">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Address Line2</label> 
																<input type="text" name="address2" id="address2" class="form-control form-white" placeholder="Enter Address">
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																<input type="text" name="locality" id="locality" class="form-control form-white" placeholder="Locality">
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">City/Village<span style="color: red;">*</span></label> 
																<input type="text" name="city" id="city" class="form-control form-white" placeholder="City/Village">
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">District</label> 
																<input type="text" name="area" id="area" class="form-control form-white" placeholder="District">
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
												<div class="row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">State<span style="color: red;">*</span></label> 
																<input type="text" name="state" id="state" value="${state}" readonly class="form-control form-white" placeholder="State">
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">Pin Code<span style="color: red;">*</span></label> 
																<input type="text" name="pinCode" id="pinCode" class="form-control form-white numbersOnly" maxlength="6" placeholder="Pin Code">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Mobile No<span style="color: red;">*</span></label> 
																<input type="text" name="mobileNo" id="mobileNo" class="form-control form-white numbersOnly" maxlength="10" placeholder="Enter Mobile">
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Landline No</label>
															<div class="clear"></div>
															<div class="col-sm-4 p-l-0">
																<div class="form-group">
																	<input type="text" name="STDCode" id="STDCode" class="form-control form-white numbersOnly" placeholder="STD Code">
																</div>
															</div>
															<div class="col-sm-8 p-l-0">
																<div class="form-group">
																	<input type="text" name="landLineNo" id="landLineNo" class="form-control form-white numbersOnly" placeholder="Landline No">
																</div>
															</div>
															<div class="clear"></div>
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->

								<div class="row">
									<div class="col-sm-6">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Proof Of Identity (POI)</h3>
												<label id="error" style="text-align: center;color: red;"></label>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">POI<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="poiLov" id="poiLov" class="form-control form-white">
																	<option value="">--Select--</option>
																	<c:forEach var="lovs" items="${poiDocumentList}">
																		<c:choose>
																			<c:when
																				test="${not empty customerCafVO && lovs.lovId == customerCafVO.poiLov}">
																				<option value="${lovs.lovId}" selected>${lovs.lovValue}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${lovs.lovId}">${lovs.lovValue}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Document No<span style="color: red;">*</span></label> 
																<input type="text" name="poiDocumentNo" id="poiDocumentNo" class="form-control form-white" placeholder="Document No">
														</div>
													</div>
													<div class="col-sm-5">
														<div class="form-group">
															<label class="control-label">Issuing Authority<span style="color: red;">*</span></label> 
																<input type="text" name="poiIssuingAuthority" id="poiIssuingAuthority" class="form-control form-white" placeholder="Issuing Authority">
														</div>
													</div>

													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Place of Issue<span style="color: red;">*</span></label> 
																<input type="text" name="poiPlaceOfIssue" id="poiPlaceOfIssue" class="form-control form-white" placeholder="Place of Issue">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Date of Issue<span style="color: red;">*</span></label> 
																<input type="text" name="poiDateOfIssue" id="poiDateOfIssue" class="date-picker form-control form-white" readonly placeholder="Select Date...">
														</div>
													</div>

													<div class="col-sm-5">
														<div class="form-group">
															<label class="control-label">Attachments<span style="color: red;">*</span></label>
															<div class="file">
																<div class="option-group">
																	<span class="file-button btn-primary">Browse</span> 
																		<input type="file" class="custom-file" name="poiDocument" id="poiDocument" onchange="document.getElementById('Attachments1_1').value = this.value;" required> 
																		<input type="text" class="form-control form-white" id="Attachments1_1" placeholder="Select Files" readonly>
																</div>
															</div>
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Proof Of Address (POA)</h3>
												<label id="error1" style="text-align: center;color: red;"></label>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">POA<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="poaLov" id="poaLov" class="form-control form-white">
																	<option vALUE="">--Select--</option>
																	<c:forEach var="lovs" items="${poaDocumentList}">
																		<c:choose>
																			<c:when
																				test="${not empty customerCafVO && lovs.lovId == customerCafVO.poaLov}">
																				<option value="${lovs.lovId}" selected>${lovs.lovValue}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${lovs.lovId}">${lovs.lovValue}</option>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Document No<span style="color: red;">*</span></label> 
																<input type="text" name="poaDocumentNo" id="poaDocumentNo" class="form-control form-white" placeholder="Document No">
														</div>
													</div>
													<div class="col-sm-5">
														<div class="form-group">
															<label class="control-label">Issuing Authority<span style="color: red;">*</span></label> 
																<input type="text" name="poaIssuingAuthority" id="poaIssuingAuthority" class="form-control form-white" placeholder="Issuing Authority">
														</div>
													</div>

													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Place of Issue<span style="color: red;">*</span></label> 
																<input type="text" name="poaPlaceOfIssue" id="poaPlaceOfIssue" class="form-control form-white" placeholder="Place of Issue">
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Date of Issue<span style="color: red;">*</span></label> 
																<input type="text" name="poaDateOfIssue" id="poaDateOfIssue" class="date-picker form-control form-white" readonly placeholder="Select Date...">
														</div>
													</div>

													<div class="col-sm-5">
														<div class="form-group">
															<label class="control-label">Attachments<span style="color: red;">*</span></label>
															<div class="file">
																<div class="option-group">
																	<span class="file-button btn-primary">Browse</span> 
																		<input type="file" class="custom-file" name="poaDocument" id="poaDocument" onchange="document.getElementById('Attachments1_2').value = this.value;" required> 
																		<input type="text" class="form-control form-white" id="Attachments1_2" placeholder="Select Files" readonly>
																</div>
															</div>
														</div>
													</div>
													<div class="clear"></div>
												</div>
												<!-- END ROW INNER-->
											</div>
										</div>
									</div>
									<div class="clear"></div>

								</div>
								<!-- END ROW -->

								<div class="row m-b-10">
									<div class="col-sm-3">
										<div class="form-group">
											<input type="checkbox" name="customerDec" id="customerDec" class="form-white pull-left"> 
												<label class="control-label p-l-10">Customer Declaration</label>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<input type="checkbox" name="lmoDec" id="lmoDec" class="form-white pull-left"> 
												<label class="control-label p-l-10">LMO Declaration</label>
										</div>
									</div>
									<div class="clear"></div>
								</div>
								<!-- END ROW -->
								<div class="pull-left">
									<button class="btn btn-embossed btn-primary" type="submit">Submit</button>
									<a href ="./createcustomer"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
								</div>
								<div class="clear"></div>
							</form>
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
<!-- END MAIN CONTENT -->

<!-- END FOOTER -->
