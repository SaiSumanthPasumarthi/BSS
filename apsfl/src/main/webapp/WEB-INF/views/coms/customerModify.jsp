<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2> CAF<strong> Entry</strong></h2>
			<c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
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
						<form role="form" class="form-validation" name="customerform" id="customerform" action="#" method="post" >
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
														<label class="control-label">CAF No<span style="color: red;">*</span></label>
															<input type="hidden" name="lmoCode" id="lmoCode" value="${lmoCode}" >
															<input type="hidden" name="custId" id="custId" value="${cafObject.custId}">
															<input type="hidden" name="custType" id="custType" value="${cafObject.custType}" />
														<c:choose>
															<c:when test="${cafObject.custType == 'INDIVIDUAL'}">
															<input type="hidden" name="status" id="status" value="${customer.status}" />
															<input type="hidden" name="cafStatus" id="cafStatus" value="${cafObject.status}" />
															</c:when>
															<c:otherwise>
															<input type="hidden" name="status" id="status" value="${entCustomer.status}" />
															<input type="hidden" name="cafStatus" id="cafStatus" value="${cafObject.status}" />
															</c:otherwise>
														</c:choose>
														<input type="text" name="cafNo" id="cafNo" value="${cafObject.cafNo}" class="form-control form-white" placeholder="CAF No" readonly>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="form-label">Aadhar/Registration No<span style="color: red;">*</span></label> 
															<input type="text" name="aadharNumber" id="aadharNumber" class="form-control form-white number" <c:choose>
															<c:when test="${cafObject.custType == 'INDIVIDUAL' && not empty customer}"> readonly value="${customer.custCode}"</c:when>
															<c:when test="${cafObject.custType == 'ENTERPRISE' && not empty entCustomer}"> readonly value="${entCustomer.regnCode}"</c:when></c:choose> placeholder="Aadhar No/Register No" maxlength="12" required>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label hide-mobile" id="aadharError" style="text-align: left;color: red;" >&nbsp;</label>
														<div class="option-group">
															<button class="btn btn-embossed btn-primary" type="button" id="getAadharDetails" disabled>
																<i class="icon-plus"></i>Retrieve Customer Info
															</button>
														</div>
													</div>
												</div>
												</div>
												<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Title<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="titleLovName" id="titleLovName" class="form-control form-white">
																<c:choose>
																	<c:when test="${cafObject.custType == 'ENTERPRISE'}">
																		<option value="Ms.">Ms.</option>
																	</c:when>
																	<c:otherwise>
																		<c:forEach var="lovs" items="${customerTypeList}">
																			<c:choose>
																				<c:when
																					test="${not empty customer && lovs.lovValue == customer.titleLov}">
																					<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																				</c:when>
																				<c:otherwise>
																					<c:if test="${lovs.lovValue != 'Ms.'}">
																						<option value="${lovs.lovValue}">${lovs.lovValue}</option>
																					</c:if>
																				</c:otherwise>
																			</c:choose>
																		</c:forEach>
																	</c:otherwise>
																</c:choose>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">First Name/Organization Name<span style="color: red;">*</span></label> 
															<input type="text" name="firstName" id="firstName" class="form-control form-white charectersonly" <c:if test="${customer.status eq 2}">readonly</c:if> <c:choose>
															<c:when test="${cafObject.custType == 'INDIVIDUAL'}"> value="${customer.custName}"</c:when>
															<c:when test="${cafObject.custType == 'ENTERPRISE' && not empty entCustomer}"> readonly value="${entCustomer.custName}"</c:when></c:choose> maxlength="50" placeholder="Enter First Name/Organization Name">
															<input type="hidden" name="entCustType" id="entCustType" value="${entCustomer.custType}" />
															<input type="hidden" name="custCode" id="custCode" value="${cafObject.pmntCustId}" >
															<c:if test = "${not empty cafObject.apsflUniqueId}"><input type="hidden" name="apsflUniqueId" id="apsflUniqueId" value="${cafObject.apsflUniqueId}" ></c:if>
															<input type="hidden" name="entCustomerCode" id="entCustomerCode" value="${cafObject.custId}" >
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Middle Name</label> 
																<input type="text" name="middleName" id="middleName" class="form-control form-white charectersonly" <c:if test="${cafObject.custType == 'ENTERPRISE' || customer.status eq 2}">readonly</c:if> value="${customer.mName}" maxlength="50" placeholder="Middle Name" >
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Surname<span style="color: red;">*</span></label> 
																<input type="text" name="lastName" id="lastName" class="form-control form-white charectersonly" <c:if test="${cafObject.custType == 'ENTERPRISE' || customer.status eq 2}">readonly</c:if> value="${customer.lName}" maxlength="50" placeholder="Last Name" >
														</div>
													</div>
													<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Father's/Husband Name</label> 
															<input type="text" name="fatherName" id="fatherName" <c:if test="${cafObject.custType == 'ENTERPRISE' || customer.status eq 2}">readonly</c:if> class="form-control form-white charectersonly" value="${customer.fhName}" maxlength="50" placeholder="Father's/Husband Name" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Date of Birth/Incorporation<span style="color: red;">*</span></label>
														<label id="dobError" style="text-align: center;color: red;"></label> 
														<input type="text" name="dob" <c:if test="${customer.status eq 0}"> id="dob" </c:if> <c:choose>
														<c:when test ="${cafObject.custType == 'INDIVIDUAL'}">value='<fmt:formatDate pattern="MM/dd/yyyy" value="${customer.dateofinc}"/>'</c:when>
														<c:when test ="${cafObject.custType == 'ENTERPRISE'}">value='<fmt:formatDate pattern="MM/dd/yyyy" value="${entCustomer.dateofinc}"/>'</c:when>
														</c:choose> readonly class="date-picker form-control form-white" placeholder="Select Date...">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Gender<span style="color: red;">*</span></label>
														<c:if test="${cafObject.custType == 'ENTERPRISE' || customer.status eq 2}"><input type="hidden" name="gender" value="${customer.gender}"></c:if>
														<div class="option-group">
															<select name="gender" id="gender" class="form-control form-white disable" <c:if test="${cafObject.custType == 'ENTERPRISE' || customer.status eq 2}">disabled</c:if>>
										                    	<option value="">--Select--</option>
													            <option <c:choose><c:when test ="${customer.gender eq 'M'.charAt(0) }"> value="${customer.gender}" selected</c:when><c:otherwise>value="M"</c:otherwise></c:choose>>Male</option>
													            <option <c:choose><c:when test ="${customer.gender eq 'F'.charAt(0) }"> value="${customer.gender}" selected</c:when><c:otherwise>value="F"</c:otherwise></c:choose>>Female</option> 
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Email</label> 
															<input type="email" name="emailId" id="emailId"  <c:if test="${customer.status eq 2}">readonly</c:if> <c:choose>
															<c:when test="${cafObject.custType == 'INDIVIDUAL'}"> value="${customer.email1}"</c:when>
															<c:when test="${cafObject.custType == 'ENTERPRISE'}"> readonly value="${entCustomer.email1}"</c:when></c:choose> class="form-control form-white" maxlength="100" placeholder="Enter Email">
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Name of Contact Person<c:if test="${cafObject.custType == 'ENTERPRISE' || custType == 'ENTERPRISE'}"><span style="color: red;">*</span></c:if></label> 
														<input type="text" name="pocName" id="pocName" <c:if test="${customer.status eq 2}">readonly</c:if> <c:choose>
															<c:when test="${cafObject.custType == 'INDIVIDUAL'}"> value="${customer.pocName}"</c:when>
															<c:when test="${cafObject.custType == 'ENTERPRISE'}"> readonly value="${entCustomer.pocName}"</c:when></c:choose> class="form-control form-white charectersonly" maxlength="100" placeholder="Enter Name">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Bill Frequency<span style="color: red;">*</span></label>
														<c:choose>
															<c:when test="${cafObject.custType == 'INDIVIDUAL' && customer.status eq 2}">
																<input type="hidden" name="billCycle" value="${customer.billfreqLov}">
															</c:when>
															<c:when test="${cafObject.custType == 'ENTERPRISE'}">
																<input type="hidden" name="billCycle" value="${entCustomer.billfreqLov}">
															</c:when>
														</c:choose>
														<div class="option-group">
															<select name="billCycle" id="billCycle" class="form-control form-white disable"<c:choose>
															<c:when test="${cafObject.custType == 'INDIVIDUAL' && customer.status eq 2}">
																disabled
															</c:when>
															<c:when test="${cafObject.custType == 'ENTERPRISE'}">
																disabled
															</c:when>
														</c:choose>>
																<option value="">--Select--</option>
																<c:forEach var="lovs" items="${billCycleList}">
																	<c:choose>
																		<c:when test="${not empty customer.billfreqLov && lovs.lovValue == customer.billfreqLov}">
																			<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																		</c:when>
																		<c:when test="${not empty entCustomer.billfreqLov && lovs.lovValue == entCustomer.billfreqLov}">
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
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Installation Address</h3>
										</div>
											<c:choose>
												<c:when test = "${tenantType != 'SI'}">
													<div class="panel-content">
														<div class="row">
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">House/Flat<span style="color: red;">*</span></label> 
																	<input type="text" name="address1" id="address1" value="${cafObject.instAddress1}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Street</label> 
																	<input type="text" name="address2" id="address2" value="${cafObject.instAddress2}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																	<input type="text" name="locality" id="locality" value="${cafObject.instLocality}" class="form-control form-white" maxlength="255" placeholder="Locality">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Location</label> 
																	<input type="text" name="location" id="location" value="${cafObject.cpePlace}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Location">
																</div>
															</div>
															<div class="clear"></div>
														</div>
														<!-- END ROW INNER-->
														<div class="row">
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">PoP Name<span style="color: red;">*</span></label>
																	<div class="option-group">
																		<select name="popId" id="popId" class="form-control1 form-white" <c:if test="${tenantType == 'SI'}">disabled</c:if>>
																			<option value="">--Select--</option>
																			<c:forEach var="pop" items="${popList}">
																				<c:choose>
																					<c:when test="${not empty cafObject && cafObject.popSubstnno == pop.substnUid}">
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
																	<label class="control-label">City/Village<span style="color: red;">*</span></label>
																		<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
																		<div class="option-group">
																			<select name="city" id="cafVillage" class="form-control form-white" required <c:if test="${tenantType == 'SI'}">disabled</c:if>>
																				<option value="">--Select--</option>
																				<c:choose>
																					<c:when test="${not empty villageList}">
																						<c:forEach var="village" items="${villageList}">
																							<c:choose>
																								<c:when test="${not empty cafObject.instCityVillage && village.villageSlno == cafObject.instCityVillage && village.mandalSlno == cafObject.instMandal && cafObject.instDistrict == village.districtUid }">
																									<option value="${village.villageSlno}, ${village.mandalSlno}, ${village.districtUid}" selected>${village.villageName}</option>
																								</c:when>
																								<c:otherwise>
																									<option value="${village.villageSlno},${village.mandalSlno},${village.districtUid}">${village.villageName}</option>
																								</c:otherwise>
																							</c:choose>
																						</c:forEach>
																					</c:when>
																					<c:otherwise>
																						<option></option>
																					</c:otherwise>
																				</c:choose>
																			</select>
																		</div>
																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Mandal<span style="color: red;">*</span></label>
																			<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
																			<div class="option-group">
																				<select name="mandal" id="mandal" class="form-control form-white" required <c:if test="${tenantType == 'SI'}">disabled</c:if>>
																					<option value="">--Select--</option>
																					<c:choose>
																						<c:when test="${not empty mandals}">
																							<option value="${mandals.mandalSlno}" selected>${mandals.mandalName}</option>
																						</c:when>
																						<c:otherwise>
																							<option></option>
																						</c:otherwise>
																					</c:choose>
																				</select>
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label class="control-label">District<span style="color: red;">*</span></label>
																				<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
																				<div class="option-group">
																					<select name="district" id="district" class="form-control form-white" required <c:if test="${tenantType == 'SI'}">disabled</c:if>>
																						<option value="">--Select--</option>
																						<c:choose>
																							<c:when test="${not empty districts}">
																								<option value="${districts.districtUid}" selected>${districts.districtName}</option>
																							</c:when>
																							<c:otherwise>
																								<option></option>
																							</c:otherwise>
																						</c:choose>
																					</select>
																				</div>
																			</div>
																		</div>
																</div><!-- end of NormalLmo DIV -->
																<div class="clear"></div>
															<div class = "row">
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Pin Code<!-- <span style="color: red;">*</span> --></label> 
																		<input type="text" name="pinCode" id="pinCode" value = "${cafObject.instPin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code">
																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Mobile No<span style="color: red;">*</span></label> 
																		<input type="text" name="mobileNo" id="mobileNo" value = "${cafObject.contactmobileNo}" class="form-control form-white number" maxlength="10" placeholder="Enter Mobile">
																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Alternate Mobile No</label> 
																		<input type="text" name="mobileNo1" id="mobileNo1"  <c:if test="${customer.status eq 2 || entCustomer.status eq 2}">readonly</c:if> 
																		<c:choose>
																			<c:when test="${cafObject.custType == 'INDIVIDUAL'}">value="${customer.pocMob2}"</c:when>
																			<c:otherwise>value="${entCustomer.pocMob2}"</c:otherwise></c:choose> class="form-control form-white number" maxlength="10" placeholder="Enter Mobile">
																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Landline No</label>
																		<div class="clear"></div>
																		<div class="col-sm-4 p-l-0">
																			<div class="form-group">
																				<input type="text" name="stdCode" id="stdCode"  <c:if test="${customer.status eq 2 || entCustomer.status eq 2 }">readonly</c:if> 
																				<c:choose>
																			<c:when test="${cafObject.custType == 'INDIVIDUAL'}">value="${customer.stdCode}"</c:when>
																			<c:otherwise>value="${entCustomer.stdCode}"</c:otherwise></c:choose> class="form-control form-white number" maxlength="5" placeholder="STD Code">
																			</div>
																		</div>
																		<div class="col-sm-8 p-l-0">
																			<div class="form-group">
																				<input type="text" name="landLineNo" id="landLineNo"  <c:if test="${customer.status eq 2 || entCustomer.status eq 2 }">readonly</c:if> 
																				<c:choose>
																			<c:when test="${cafObject.custType == 'INDIVIDUAL'}">value="${customer.landLine1}"</c:when>
																			<c:otherwise>value="${entCustomer.landLine1}"</c:otherwise></c:choose> class="form-control form-white number" maxlength="10" placeholder="Landline No">
																			</div>
																		</div>
																		<div class="clear"></div>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Longitude</label> 
																		<input type="text" name="longitude" id="longitude" value="${cafObject.longitude}" class="form-control form-white numbersOnly" maxlength="8" placeholder="Longitude">
																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Latitude</label> 
																		<input type="text" name="latitude" id="latitude" value="${cafObject.lattitude}" class="form-control form-white numbersOnly" maxlength="8" placeholder="Latitude">
																	</div>
																</div>
														<div class="clear"></div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</c:when>
												<c:when test="${tenantType == 'SI' }">
													<div class="panel-content">
														<div class="row">
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">House/Flat<span style="color: red;">*</span></label> 
																	<input type="text" name="address1" id="address1" value="${cafObject.instAddress1}" class="form-control form-white" maxlength="255" placeholder="Enter Address">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Street</label> 
																	<input type="text" name="address2" id="address2" value="${cafObject.instAddress2}" class="form-control form-white" maxlength="255" placeholder="Enter Address">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
																	<input type="text" name="locality" id="locality" value="${cafObject.instLocality}" class="form-control form-white" maxlength="255" placeholder="Locality">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Location</label> 
																	<input type="text" name="location" id="location" value="${cafObject.cpePlace}" class="form-control form-white" maxlength="255" placeholder="Enter Location">
																</div>
															</div>
															<div class="clear"></div>
														</div>
													<!-- END ROW INNER-->
													<div class="row">
														<div class="col-sm-3">
															<div class="form-group">
																<label class="control-label">PoP District<span style="color: red;">*</span></label>
																	<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
																	<div class="option-group">
																		<select name="popDistrict" id="popDistrict" class="form-control form-white" required <c:if test="${tenantType != 'SI'}">disabled</c:if>>
																			<option value="">--Select--</option>
																			<c:forEach var="district" items="${districtList}">
																				<c:choose>
																					<c:when test="${(not empty cafObject && cafObject.popDistrict == district.districtUid) || (not empty cafObject.instDistrict && cafObject.instDistrict == district.districtUid)}">
																						<option value="${district.districtUid}" selected>${district.districtName}</option>
																					</c:when>
																					<c:otherwise>
																						<option value="${district.districtUid}">${district.districtName}</option>
																					</c:otherwise>
																				</c:choose>
																			</c:forEach>
																		</select>
																	</div>
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">PoP Mandal<span style="color: red;">*</span></label>
																		<div class="option-group">
																			<select name="popMandal" id="popMandal" class="form-control form-white" required <c:if test="${tenantType != 'SI'}">disabled</c:if>>
																				<option value="">--Select--</option>
																				<c:choose>
																					<c:when test="${not empty mandalList}">
																						<c:forEach var="mandal" items="${mandalList}">
																							<c:choose>
																								<c:when test="${(not empty cafObject.popMandal && mandal.mandalSlno == cafObject.popMandal && cafObject.popDistrict == mandal.districtUid) || (not empty cafObject.instMandal && mandal.mandalSlno == cafObject.instMandal && cafObject.instDistrict == mandal.districtUid)}">
																									<option value="${mandal.mandalSlno}" selected>${mandal.mandalName}</option>
																								</c:when>
																								<c:otherwise>
																									<option value="${mandal.mandalSlno}">${mandal.mandalName}</option>
																								</c:otherwise>
																							</c:choose>
																						</c:forEach>
																					</c:when>
																				</c:choose>
																			</select>
																		</div>
																	</div>
																</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">PoP Name<span style="color: red;">*</span></label>
																		<div class="option-group">
																			<select name="popId" id="SIPopId" class="form-control1 form-white" <c:if test="${tenantType != 'SI'}">disabled</c:if>>
																				<option value="">--Select--</option>
																				<c:forEach var="pop" items="${popList}">
																					<c:choose>
																						<c:when test="${not empty cafObject && cafObject.popSubstnno == pop.substnUid}">
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
																		<label class="control-label">Installation District<span style="color: red;">*</span></label>
																			<div class="option-group">
																				<c:if test = "${not empty cafObject.instDistrict}"><input type = "hidden" name="district" id="district" value="${cafObject.instDistrict}"></c:if>
																				<select name="district" id="district" class="form-control form-white disable" required disabled >
																					<option value="">--Select--</option>
																					<c:forEach var="district" items="${districtList}">
																						<c:choose>
																							<c:when test="${not empty cafObject && cafObject.instDistrict == district.districtUid}">
																								<option value="${district.districtUid}" selected>${district.districtName}</option>
																							</c:when>
																							<c:otherwise>
																								<option value="${district.districtUid}">${district.districtName}</option>
																							</c:otherwise>
																						</c:choose>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</div>
																</div>
															<div class="clear"></div>
															<div class = "row">
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Installation Mandal<span style="color: red;">*</span></label>
																			<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
																			<div class="option-group">
																				<c:if test = "${not empty cafObject.instMandal}"><input type = "hidden" name="mandal" id="mandal" value="${cafObject.instMandal}"></c:if>
																				<select name="mandal" id="mandal" class="form-control form-white disable" required disabled >
																					<option value="">--Select--</option>
																					<c:choose>
																						<c:when test="${not empty mandalList}">
																							<c:forEach var="mandal" items="${mandalList}">
																								<c:choose>
																									<c:when test="${not empty cafObject.instMandal && mandal.mandalSlno == cafObject.instMandal && cafObject.instDistrict == mandal.districtUid}">
																										<option value="${mandal.mandalSlno}" selected>${mandal.mandalName}</option>
																									</c:when>
																									<c:otherwise>
																										<option value="${mandal.mandalSlno}">${mandal.mandalName}</option>
																									</c:otherwise>
																								</c:choose>
																							</c:forEach>
																						</c:when>
																					</c:choose>
																				</select>
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label class="control-label">Installation City/Village<span style="color: red;">*</span></label>
																				<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
																				<div class="option-group">
																					<select name="city" id="village" class="form-control form-white" required <c:if test="${tenantType != 'SI'}">disabled</c:if>>
																						<option value="">--Select--</option>
																						<c:choose>
																							<c:when test="${not empty instVillageList}">
																								<c:forEach var="village" items="${instVillageList}">
																									<c:choose>
																										<c:when test="${not empty cafObject.instCityVillage && village.villageSlno == cafObject.instCityVillage && village.mandalSlno == cafObject.instMandal && cafObject.instDistrict == village.districtUid }">
																											<option value="${village.villageSlno}, ${village.mandalSlno}, ${village.districtUid}" selected>${village.villageName}</option>
																										</c:when>
																										<c:otherwise>
																											<option value="${village.villageSlno},${village.mandalSlno},${village.districtUid}">${village.villageName}</option>
																										</c:otherwise>
																									</c:choose>
																								</c:forEach>
																							</c:when>
																							<c:otherwise>
																								<option></option>
																							</c:otherwise>
																						</c:choose>
																					</select>
																				</div>
																			</div>
																	</div>
																	<div class="col-sm-3">
																		<div class="form-group">
																			<label class="control-label">Pin Code<!-- <span style="color: red;">*</span> --></label> 
																			<input type="text" name="pinCode" id="pinCode" value = "${cafObject.instPin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code">
																		</div>
																	</div>
																<div class="col-sm-3">
																	<div class="form-group">
																		<label class="control-label">Mobile No<span style="color: red;">*</span></label> 
																		<input type="text" name="mobileNo" id="mobileNo" value = "${cafObject.contactmobileNo}" class="form-control form-white number " maxlength="10" placeholder="Enter Mobile" readonly>
																	</div>
																</div>
															</div>
														<div class="row">
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Alternate Mobile No</label> 
																	<input type="text" name="mobileNo1" id="mobileNo1"  <c:if test="${customer.status eq 2 || entCustomer.status eq 2}">readonly</c:if> 
																	<c:choose>
																		<c:when test="${cafObject.custType == 'INDIVIDUAL'}">value="${customer.pocMob2}"</c:when>
																		<c:otherwise>value="${entCustomer.pocMob2}"</c:otherwise></c:choose> class="form-control form-white number" maxlength="10" placeholder="Enter Mobile">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Landline No</label>
																	<div class="clear"></div>
																	<div class="col-sm-4 p-l-0">
																		<div class="form-group">
																			<input type="text" name="stdCode" id="stdCode"  <c:if test="${customer.status eq 2 || entCustomer.status eq 2 }">readonly</c:if> 
																			<c:choose>
																		<c:when test="${cafObject.custType == 'INDIVIDUAL'}">value="${customer.stdCode}"</c:when>
																		<c:otherwise>value="${entCustomer.stdCode}"</c:otherwise></c:choose> class="form-control form-white number" maxlength="5" placeholder="STD Code">
																		</div>
																	</div>
																	<div class="col-sm-8 p-l-0">
																		<div class="form-group">
																			<input type="text" name="landLineNo" id="landLineNo"  <c:if test="${customer.status eq 2 || entCustomer.status eq 2 }">readonly</c:if> 
																			<c:choose>
																		<c:when test="${cafObject.custType == 'INDIVIDUAL'}">value="${customer.landLine1}"</c:when>
																		<c:otherwise>value="${entCustomer.landLine1}"</c:otherwise></c:choose> class="form-control form-white number" maxlength="10" placeholder="Landline No">
																		</div>
																	</div>
																	<div class="clear"></div>
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Longitude</label> 
																	<input type="text" name="longitude" id="longitude" value="${cafObject.longitude}" class="form-control form-white numbersOnly" maxlength="8" placeholder="Longitude">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Latitude</label> 
																	<input type="text" name="latitude" id="latitude" value="${cafObject.lattitude}" class="form-control form-white numbersOnly" maxlength="8" placeholder="Latitude">
																</div>
															</div>
													</div><!-- END ROW INNER-->
												<div class="clear"></div>
											</div>
										</c:when>
									</c:choose>
								</div>
							</div>
							</div>
							<!-- END ROW -->
							<div class="row m-b-10">
								<div class="col-sm-3">
									<div class="form-group">
										<input type="checkbox" name="customerDec" id="customerDec" <c:if test = "${cafObject.customerDeclaration eq 'Y'.charAt(0)}">checked </c:if> class="form-white pull-left"> 
											<label class="control-label p-l-10">Customer Declaration</label>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<input type="checkbox" name="lmoDec" id="lmoDec" <c:if test = "${cafObject.lmoDeclaration eq 'Y'.charAt(0)}">checked </c:if> class="form-white pull-left"> 
											<label class="control-label p-l-10">LMO Declaration</label>
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<!-- END ROW -->
							<div class="pull-left">
								<button class="btn btn-embossed btn-primary" type="submit" id="createCustomerButton" >Submit</button>
								<c:choose><c:when test="${custType == 'ENTERPRISE'}"><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
								<c:otherwise><a href ="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
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