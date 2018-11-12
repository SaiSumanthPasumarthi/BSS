<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main-content">
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
						<form role="form" class="form-validation" name="saveEditForm" id="saveEditForm" action="#" method="post" >
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
														<label class="form-label">Customer Id<span style="color: red;">*</span></label> 
															<input type="text" name="custId" id="custId" class="form-control form-white number" <c:if test="${not empty customer}">readonly</c:if> value="${customer.custId}" placeholder="Customer Id" maxlength="12" required readonly>
															<input type="hidden" name="lmoCode" id="lmoCode" value="${lmoCode}" >
															<input type="hidden" name="tenantCode" id="tenantCode">
															<%-- <input type="hidden" name="custId" id="custId" value="${customerCafVO.custId}"> --%>
															<input type="hidden" name="custType" id="custType" value="${customer.custType}" />
															<input type="hidden" name="status" id="status" value="0" />
															
															<input type="hidden" name="pocMob1Old" id="pocMob1" value="${customer.pocMob1}" />
															<input type="hidden" name="pocMob2Old" id="pocMob2" value="${customer.pocMob2}" />
															<input type="hidden" name="stdCodeOld" id="stdCode" value="${customer.stdCode}" />
															<input type="hidden" name="landLine1Old" id="landLine1" value="${customer.landLine1}" />
															<input type="hidden" name="email1Old" id="email1" value="${customer.email1}" />
															
															<input type="hidden" name="address1Old" id="address1" value="${customer.address1}" />
															<input type="hidden" name="address2Old" id="address2" value="${customer.address2}" />
															<input type="hidden" name="localityOld" id="locality" value="${customer.locality}" />
															<input type="hidden" name="districtOld" id="district1" value="${customer.district}" />
															<input type="hidden" name="mandalOld" id="mandal1" value="${customer.mandal}" />
															<input type="hidden" name="villageOld" id="village1" value="${customer.cityVillage}" />
															<input type="hidden" name="pinOld" id="pin1" value="${customer.pin}" />
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="form-label">Aadhar/Registration No<span style="color: red;">*</span></label> 
															<input type="text" name="aadharNumber" id="aadharNumber" class="form-control form-white number" <c:if test="${not empty customer}">readonly</c:if> value="${customer.custCode}" placeholder="Aadhar No/Register No" maxlength="12" required readonly>
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
															<select name="titleLovName" id="titleLovName" class="form-control form-white disable" disabled>
																<c:choose>
																	<c:when test="${customer.custType == 'ENTERPRISE'}">
																		<option value="Ms.">Ms.</option>
																	</c:when>
																	<c:otherwise>
																		<c:forEach var="lovs" items="${customerTypeList}">
																			<c:choose>
																				<c:when
																					test="${not empty customer && lovs.lovValue == customer.titleLov}">
																					<option value="${lovs.lovValue}">${lovs.lovValue}</option>
																				</c:when>
																				<c:otherwise>
																					<c:if test="${lovs.lovValue != 'Ms.'}">disabled
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
															<input type="text" name="firstName" id="firstName" class="form-control form-white charectersonly" <c:if test="${customer.status eq 2}">readonly</c:if> value="${customer.custName}" maxlength="50" placeholder="Enter First Name/Organization Name" readonly>
															<%-- <input type="hidden" name="custCode" id="custCode" <c:if test = "${not empty customer.parentcustcode}"> value="${customer.parentCustCode}" </c:if> > --%>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Middle Name</label> 
																<input type="text" name="middleName" id="middleName" class="form-control form-white charectersonly" <c:if test="${customer.custType == 'ENTERPRISE' || customer.status eq 2}">readonly</c:if> value="${customer.mName}" maxlength="50" placeholder="Middle Name" readonly>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Surname<span style="color: red;">*</span></label> 
																<input type="text" name="lastName" id="lastName" class="form-control form-white charectersonly" <c:if test="${customer.custType == 'ENTERPRISE' || customer.status eq 2}">readonly</c:if> value="${customer.lName}" maxlength="50" placeholder="Last Name" readonly>
														</div>
													</div>
													<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Father's/Husband Name</label> 
															<input type="text" name="fatherName" id="fatherName" <c:if test="${customer.custType == 'ENTERPRISE' || customer.status eq 2}">readonly</c:if> class="form-control form-white charectersonly" value="${customer.fhName}" maxlength="50" placeholder="Father's/Husband Name" readonly>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Date of Birth/Incorporation<span style="color: red;">*</span></label>
														<label id="dobError" style="text-align: center;color: red;"></label>
														<input type="text" name="dob" <c:choose><c:when test ="${customer.custType == 'ENTERPRISE' || customer.status eq 2}">value='<fmt:formatDate pattern="MM/dd/yyyy" value="${customer.dateofinc}"/>'</c:when><c:otherwise> id="dob" value='<fmt:formatDate pattern="MM/dd/yyyy" value="${customer.actualdob}" />'</c:otherwise></c:choose> readonly class="date-picker form-control form-white"  placeholder="Select Date...">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Gender<span style="color: red;">*</span></label>
														<c:if test="${not empty customer}"><input type="hidden" name="gender" value="${customer.gender}" readonly></c:if>
														<div class="option-group">
															<select name="gender" id="gender" class="form-control form-white disable" <c:if test="${customer.custType == 'ENTERPRISE' || customer.status eq 2}">disabled</c:if>>
										                    	<option value="">--Select--</option>
													            <option <c:choose><c:when test ="${customer.gender eq 'M'.charAt(0) }"> value="${customer.gender}" selected</c:when><c:otherwise>value="M"</c:otherwise></c:choose>>Male</option>
													            <option <c:choose><c:when test ="${customer.gender eq 'F'.charAt(0) }"> value="${customer.gender}" selected</c:when><c:otherwise>value="F"</c:otherwise></c:choose>>Female</option> 
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group editField">
														<label class="control-label">Email<span style="color: red;">*</span></label> 
														<input type="text" name="emailId" id="emailId" <c:choose> <c:when test="${customer.custType == 'ENTERPRISE' || not empty customer.email1}">readonly value="${customer.email1}" </c:when> <c:when test="${not empty customer}"> value="${customer.email1}" </c:when></c:choose> class="form-control form-white disable" maxlength="100" placeholder="Enter Email" readonly>
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Name of Contact Person</label> 
														<input type="text" name="pocName" id="pocName" <c:if test="${customer.status eq 2}">readonly</c:if> value="${customer.pocName}" class="form-control form-white charectersonly" maxlength="100" placeholder="Enter Name">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Bill Frequency<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="billCycle" id="billCycle" class="form-control form-white disable" disabled>
															<c:choose>
																	<c:when test="${not empty customer && lovs.lovValue == customer.billfreqLov && customer.custType == 'ENTERPRISE'}">
																	  <c:if test="${customer.custType == 'ENTERPRISE'}">disabled
																		<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																		</c:if>
																	</c:when>
																	<c:otherwise>
																		<c:forEach var="lovs" items="${billCycleList}">
																			<c:choose>
																				<c:when
																					test="${not empty customer && lovs.lovValue == customer.billfreqLov && customer.custType == 'INDIVIDUAL'}">
																					<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																				</c:when>
																				<c:otherwise>
																						<option value="${lovs.lovValue}">${lovs.lovValue}</option>
																				</c:otherwise>
																			</c:choose>
																		</c:forEach>
																	</c:otherwise>
																</c:choose>
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
											<h3>Address</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">House/Flat<span style="color: red;">*</span></label> 
														<input type="text" name="address1" id="address1" value="${customer.address1}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Street</label> 
														<input type="text" name="address2" id="address2" value="${customer.address2}" class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
														<input type="text" name="locality" id="locality" value="${customer.locality}" class="form-control form-white addressValidation" maxlength="255" placeholder="Locality" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">District<span style="color: red;">*</span></label>
															<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
															<div class="option-group">
																<select name="district" id="district" class="form-control form-white" required>
																	<option value="">--Select--</option>
																	<c:forEach var="district" items="${districtList}">
																		<c:choose>
																			<c:when test="${not empty customer.district && district.districtUid == customer.district}">
																				<option value="${district.districtUid}" selected >${district.districtName}</option>
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
												<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Mandal<span style="color: red;">*</span></label>
															<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
															<div class="option-group">
																<select name="mandal" id="mandal" class="form-control form-white">
																	<option value="">--Select--</option>
																	<c:forEach var="mandal" items="${mandalList}">
																		<c:choose>
																		<c:when test="${not empty customer.mandal && mandal.mandalSlno == customer.mandal && customer.district == mandal.districtUid}">
																				<option value="${mandal.mandalSlno}" selected>${mandal.mandalName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${mandal.mandalSlno}">${mandal.mandalName}</option>
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
															<div class="option-group">
																	<select name="cityVillage" id="village" class="form-control form-white">
																		<option value="">--Select--</option>
																		<c:choose>
																			<c:when test="${not empty villageList}">
																				<c:forEach var="village" items="${villageList}">
																					<c:choose>
																						<c:when test="${not empty customer.cityVillage && village.villageSlno == customer.cityVillage && village.mandalSlno == customer.mandal && customer.district == village.districtUid }">
																							<option value="${village.villageSlno}" selected>${village.villageName}</option>
																						</c:when>
																						<c:otherwise>
																							<option value="${village.villageSlno}">${village.villageName}</option>
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
														<label class="control-label">Pin Code<span style="color: red;">*</span></label> 
														<input type="text" name="pinCode" id="pinCode" value="${customer.pin}" class="form-control form-white number" maxlength="6" placeholder="Pin Code">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group editField">
														<label class="control-label">Mobile No<span style="color: red;">*</span></label> 
														<input type="text" name="mobileNo" id="mobileNo"  <c:if test="${customer.status eq 2}"></c:if> value="${customer.pocMob1}" class="form-control form-white number" maxlength="10" placeholder="Enter Mobile">
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group editField">
														<label class="control-label">Alternate Mobile No</label> 
														<input type="text"  name="mobileNo1" id="mobileNo1"  <c:if test="${customer.status eq 2}"></c:if> value="${customer.pocMob2}" class="form-control form-white number" maxlength="10" placeholder="Enter Mobile">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Landline No</label>
														<div class="clear"></div>
														<div class="col-sm-4 p-l-0">
															<div class="form-group editField">
																<input type="text" name="stdCode" id="stdCode"  <c:if test="${customer.status eq 2}"></c:if> value="${customer.stdCode}" class="form-control form-white number" maxlength="5" placeholder="STD Code">
															</div>
														</div>
														<div class="col-sm-8 p-l-0">
															<div class="form-group editField">
																<input type="text" name="landLineNo" id="landLineNo"  <c:if test="${customer.status eq 2}"></c:if> value="${customer.landLine1}" class="form-control form-white number" maxlength="10" placeholder="Landline No">
															</div>
														</div>
														<div class="clear"></div>
													</div>
												</div>
											</div>
											 <div class="row">
										<div class="clear"></div>
										</div> 
											<!-- END ROW INNER-->
										</div>
									</div>
								</div>
							</div>
							<!-- END ROW -->
							<div class="row m-b-10">
								<div class="col-sm-3">
									<div class="form-group">
										<input type="checkbox" name="customerDec" id="customerDec" <c:if test = "${cafObject.customerDeclaration eq 'Y'.charAt(0)}">checked </c:if> class="form-white pull-left"> 
											<label class="control-label p-l-10" >Customer Declaration</label>
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
                                 <button class="btn btn-embossed btn-primary" type="submit" id="saveEditCustomerButton" disabled>Submit</button>
								<a href ="./viewcustomers"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
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