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
															<input type="hidden" name="agruniqueid" id="agruniqueid">
															<input type="hidden" name="prodCode" id="prodCode">
															<input type="hidden" name="tenantCode" id="tenantCode">
															<input type="hidden" name="totalAmount" id="totalAmount"> 
															<input type="hidden" name="totalTax" id="totalTax">  
															<%-- <input type="hidden" name="custId" id="custId" value="${customerCafVO.custId}"> --%>
															<input type="hidden" name="custType" id="custType" value="${custType}" />
															<input type="hidden" name="entCustomerCode" id="custType" value="${enterpriseCustomerVO.custId}" />
															<input type="hidden" name="entCustType" id="entCustType" value="${enterpriseCustomerVO.custType}" />
															<input type="hidden" name="status" id="status" value="0" />
															<input type="hidden" name="cafStatus" id="cafStatus" value="0" />
															<input type="text" name="cafNo" id="cafNo" value="${cafNo}" class="form-control form-white" placeholder="CAF No" readonly>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="form-label">Aadhar/Registration No<span style="color: red;">*</span></label> 
															<input type="text" name="aadharNumber" id="aadharNumber" class="form-control form-white number" <c:choose><c:when test="${custType == 'ENTERPRISE' || not empty enterpriseCustomerVO.uidNo}">readonly value="${enterpriseCustomerVO.uidNo}" </c:when> <c:when test="${not empty customer}">readonly value="${customer.custCode}" </c:when></c:choose> placeholder="Aadhar No/Register No" maxlength="12" required>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label id="aadharError" class = "aadharError" style="text-align: left;color: red;" ></label>
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
																	<c:when test="${custType == 'ENTERPRISE'}">
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
																<input type="text" name="firstName" id="firstName" class="form-control form-white charectersonly" <c:choose> <c:when test="${custType == 'ENTERPRISE' || not empty enterpriseCustomerVO.orgName}">readonly value="${enterpriseCustomerVO.orgName}" </c:when> <c:when test="${not empty customer}">readonly value="${customer.custName}" </c:when></c:choose> maxlength="50" placeholder="Enter First Name/Organization Name">
																<input type="hidden" name="custCode" id="custCode" value="${enterpriseCustomerVO.custCode}" >
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Middle Name</label> 
																<input type="text" name="middleName" id="middleName" class="form-control form-white charectersonly" value="${customer.mName}" maxlength="50" placeholder="Middle Name" <c:if test="${custType == 'ENTERPRISE' || customer!= null}">readonly</c:if>>
														</div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Surname<span style="color: red;">*</span></label> 
																<input type="text" name="lastName" id="lastName" class="form-control form-white charectersonly" value="${customer.lName}" maxlength="50" placeholder="Last Name" <c:if test="${custType == 'ENTERPRISE' || customer!= null}">readonly</c:if>>
														</div>
													</div>
													<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Father's/Husband Name</label> 
															<input type="text" name="fatherName" id="fatherName" class="form-control form-white charectersonly" value="${customer.fhName}" maxlength="50" placeholder="Father's/Husband Name" <c:if test="${custType == 'ENTERPRISE' || customer != null}">readonly</c:if>>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Date of Birth/Incorporation<c:if test="${custType == 'INDIVIDUAL' || customer.custType == 'INDIVIDUAL'}"><span style="color: red;">*</span></c:if></label>
														<label id="dobError" style="text-align: center;color: red;"></label>
															<input type="text" name="dob" id = "indDob" <c:choose><c:when test ="${custType == 'ENTERPRISE' || not empty entDate}">value='<fmt:formatDate pattern="MM/dd/yyyy" value="${entDate}"/>'</c:when><c:when test ="${customer.status eq 2}">value='<fmt:formatDate pattern="MM/dd/yyyy" value="${customer.dateofinc}"/>'</c:when><c:otherwise> id="dob" value='<fmt:formatDate pattern="MM/dd/yyyy" value="${customer.dateofinc}"/>'</c:otherwise></c:choose> class="date-picker form-control form-white" readonly placeholder="Select Date...">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Gender<span style="color: red;">*</span></label>
														<c:if test="${custType == 'INDIVIDUAL' && not empty customer}"><input type="hidden" name="gender" value="${customer.gender}"></c:if>
														<div class="option-group">
															<select name="gender" id="gender" class="form-control form-white" <c:if test="${custType == 'ENTERPRISE' || not empty customer}">disabled</c:if>>
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
															<input type="email" name="emailId" id="emailId" <c:choose> <c:when test="${custType == 'ENTERPRISE' || not empty enterpriseCustomerVO.emailId}">readonly value="${enterpriseCustomerVO.emailId}" </c:when> <c:when test="${not empty customer}">readonly value="${customer.email1}" </c:when></c:choose>  class="form-control form-white" maxlength="100" placeholder="Enter Email">
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Name of Contact Person<c:if test="${customer.custType == 'ENTERPRISE' || custType == 'ENTERPRISE'}"><span style="color: red;">*</span></c:if></label> 
														<label id="pocError" style="text-align: center;color: red;"></label>
														<input type="text" name="pocName" id="pocName" <c:choose> <c:when test="${custType == 'ENTERPRISE' || not empty enterpriseCustomerVO.pocName}">readonly value="${enterpriseCustomerVO.pocName}" </c:when> <c:when test="${not empty customer}">readonly value="${customer.pocName}" </c:when></c:choose> class="form-control form-white charectersonly" maxlength="100" placeholder="Enter Name">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Bill Frequency<span style="color: red;">*</span></label>
														<c:if test="${custType == 'ENTERPRISE'}"><input type="hidden" name="billCycle" value="${enterpriseCustomerVO.billCycle}"></c:if>
														<c:if test="${custType == 'INDIVIDUAL' && not empty customer}"><input type="hidden" name="billCycle" value="${customer.billfreqLov}"></c:if>
														<div class="option-group">
															<select name="billCycle" id="billCycle" class="form-control form-white" <c:if test="${custType == 'ENTERPRISE' || not empty customer}">disabled</c:if>>
																<c:forEach var="lovs" items="${billCycleList}">
																	<c:choose>
																		<c:when test="${not empty enterpriseCustomerVO.billCycle && lovs.lovValue == enterpriseCustomerVO.billCycle}">
																			<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																		</c:when>
																		<c:when test="${not empty customer.billfreqLov && lovs.lovValue == customer.billfreqLov}">
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
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">House/Flat<span style="color: red;">*</span></label> 
														<input type="text" name="address1" autocomplete="off" id="address1" <c:choose><c:when test = "${not empty enterpriseCustomer.address1}">value="${enterpriseCustomer.address1}"</c:when><c:when test = "${not empty customer.address1}"> value="${customer.address1}" </c:when></c:choose> class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Street</label> 
														<input type="text" name="address2" autocomplete="off" id="address2" <c:choose><c:when test = "${not empty enterpriseCustomer.address2}">value="${enterpriseCustomer.address2}"</c:when><c:when test = "${not empty customer.address2}"> value="${customer.address2}" </c:when></c:choose> class="form-control form-white addressValidation" maxlength="255" placeholder="Enter Address">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Locality/Area<span style="color: red;">*</span></label> 
														<input type="text" name="locality" autocomplete="off" id="locality" <c:choose><c:when test = "${not empty enterpriseCustomer.locality}">value="${enterpriseCustomer.locality}"</c:when><c:when test = "${not empty customer.locality}"> value="${customer.locality}" </c:when></c:choose> class="form-control form-white addressValidation" maxlength="255" placeholder="Locality">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">PoP Name<span style="color: red;">*</span></label>
														<div class="option-group">
															<select name="popId" id="popId" class="form-control1 form-white">
																<option value="">--Select--</option>
																<c:forEach var="pop" items="${popList}">
																	<c:choose>
																		<c:when test="${not empty cafObject}">
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
												<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">City/Village<span style="color: red;">*</span></label>
															<%-- <input type="text" name="area" id="area" value="${customer.area}" class="form-control form-white specialCharacters" maxlength="255" placeholder="Enter District"> --%> 
															<div class="option-group">
																<select name="city" id="cafVillage" class="form-control form-white" required>
																	<option value="">--Select--</option>
																	<c:choose>
																		<c:when test="${not empty villageList}">
																			<c:forEach var="village" items="${villageList}">
																				<c:choose>
																					<c:when test="${not empty customer.cityVillage && village.villageSlno == customer.cityVillage && village.mandalSlno == customer.mandal && customer.district == village.districtUid }">
																						<option value="${village.villageSlno},${village.mandalSlno},${village.districtUid}" selected>${village.villageName}</option>
																					</c:when>
																					<c:when test="${not empty enterpriseCustomer.cityVillage && village.villageSlno == enterpriseCustomer.cityVillage && village.mandalSlno == enterpriseCustomer.mandal && enterpriseCustomer.district == village.districtUid }">
																						<option value="${village.villageSlno},${village.mandalSlno},${village.districtUid}" selected>${village.villageName}</option>
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
																<select name="mandal" id="mandal" class="form-control form-white" required>
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
																<select name="district" id="district" class="form-control form-white" required>
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
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Pin Code<!-- <span style="color: red;">*</span> --></label> 
														<input type="text" name="pinCode" id="pinCode" <c:choose><c:when test = "${not empty enterpriseCustomer.pin}">value="${enterpriseCustomer.pin}"</c:when><c:when test = "${not empty customer.pin}"> value="${customer.pin}" </c:when></c:choose> class="form-control form-white number" maxlength="6" placeholder="Pin Code">
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Mobile No<span style="color: red;">*</span></label> 
														<input type="text" name="mobileNo" id="mobileNo" <c:choose><c:when test = "${not empty enterpriseCustomer.pocMob1}">value="${enterpriseCustomer.pocMob1}"</c:when><c:when test = "${not empty customer.pocMob1}"> value="${customer.pocMob1}" </c:when></c:choose> class="form-control form-white number" maxlength="10" placeholder="Enter Mobile">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Alternate Mobile No</label> 
														<input type="text" name="mobileNo1" id="mobileNo1" <c:choose><c:when test = "${not empty enterpriseCustomer.pocMob2}">value="${enterpriseCustomer.pocMob2}"</c:when><c:when test = "${not empty customer.pocMob2}"> value="${customer.pocMob2}" </c:when></c:choose> class="form-control form-white number" maxlength="10" placeholder="Enter Mobile">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Landline No</label>
														<div class="clear"></div>
														<div class="col-sm-4 p-l-0">
															<div class="form-group">
																<input type="text" name="stdCode" id="stdCode" <c:choose><c:when test = "${not empty enterpriseCustomer.stdCode}">value="${enterpriseCustomer.stdCode}"</c:when><c:when test = "${not empty customer.stdCode}"> value="${customer.stdCode}" </c:when></c:choose> class="form-control form-white number" maxlength="5" placeholder="STD Code">
															</div>
														</div>
														<div class="col-sm-8 p-l-0">
															<div class="form-group">
																<input type="text" name="landLineNo" id="landLineNo" <c:choose><c:when test = "${not empty enterpriseCustomer.landLine1}">value="${enterpriseCustomer.landLine1}"</c:when><c:when test = "${not empty customer.landLine1}"> value="${customer.landLine1}" </c:when></c:choose> class="form-control form-white number" maxlength="10" placeholder="Landline No">
															</div>
														</div>
														<div class="clear"></div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Longitude</label> 
														<input type="text" name="longitude" id="longitude" class="form-control form-white numbersOnly" maxlength="8" placeholder="Longitude">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Latitude</label> 
														<input type="text" name="latitude" id="latitude" class="form-control form-white numbersOnly" maxlength="8" placeholder="Latitude">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Location<span style="color: red;">*</span></label> 
														<input type="text" name="location" id="location" class="form-control form-white" maxlength="255" placeholder="Enter Location">
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
								<button class="btn btn-embossed btn-primary" type="submit" id="createCustomerButton" >Submit</button>
								<c:choose><c:when test="${custType == 'ENTERPRISE'}"><a href ="./enterpriseCustomer"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:when>
								<c:otherwise><a href ="./showcustomers"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a></c:otherwise></c:choose>
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