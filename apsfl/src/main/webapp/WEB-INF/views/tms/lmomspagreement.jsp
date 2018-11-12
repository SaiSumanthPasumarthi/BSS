<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="main-content" style="display: none;">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				LMO<strong> Agreement with MSP</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">MSP Agreement with LMO</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div>
							<center>
								<font face="Times New Roman" size="4px" color="Red">${error}</font>
							</center>
							<c:if test="${not empty tenantAgg.reasons}">
								<label>Reason For Send Back</label>
								<font face="Times New Roman" size="4px" color="Red">${tenantAgg.reasons}</font>
							</c:if>
						</div>
						<form name="tenantAgmtForm"
							action="<c:url value="/createTenantLmoMspAgreement"/>"
							method="post" id="lmoMsp_form" enctype="multipart/form-data">
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>
												<strong>${tenantName}</strong> Agreement with LMO
											</h3>
										</div>
										<div class="panel-content">

											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">LMO Name<span
															style="color: red;">*</span></label>
														<div class="option-group">
															<select id="lmoCode" name="lmoCode"
																class="form-control form-white">
																<c:forEach var="tenant" items="${tenantList}">
																	<c:choose>
																		<c:when
																			test="${not empty lmoCode  && lmoCode == tenant.tenantCode}">
																			<option value="${tenant.tenantCode}" selected>${tenant.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${tenant.tenantCode}">${tenant.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Region</label>
														<div class="option-group">
															<select id="region" name="region"
																class="form-control form-white"
																placeholder="Select Region">
																<option value="">Select Region</option>
																<c:forEach var="region" items="${regionList}">
																	<c:choose>
																		<c:when
																			test="${not empty tenantRegion && tenantRegion == region.regionName}">
																			<option value="${region.regionName}" selected>${region.regionName}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${region.regionName}">${region.regionName}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Core Service<span
															style="color: red;">*</span></label>
														<div class="option-group">
															<c:if test="${not empty tenantLMOServicesVO}">
																<input type="hidden" id="coreserviceCode"
																	name="coreserviceCode"
																	value="${tenantLMOServicesVO.coresrvcCode}">
															</c:if>
															<select id="coreServiceMulti" name="coreServiceMulti"
																class="form-control form-white" multiple="multiple">
																<c:forEach var="coreServices"
																	items="${coreServicesList}">
																	<c:choose>
																		<c:when
																			test="${not empty tenantLMOServicesVO && not empty tenantLMOServicesVO.coresrvcCode && coreServices.srvcCode==tenantLMOServicesVO.coresrvcCode}">
																			<option value="${coreServices.srvcCode}" selected>${coreServices.srvcName}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${coreServices.srvcCode}">${coreServices.srvcName}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Agreement Date<span
															style="color: red;">*</span></label> <input type="text"
															class="date-custom form-control form-white"
															placeholder="Select a date..." name="agreementDate"
															id="agreementDate"
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgg.agreementDate}" />' />
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Agreement From<span
															style="color: red;">*</span></label> <input type="text"
															class="date-custom form-control form-white"
															placeholder="Select a date..." name="agreementFrom"
															id="agreementFrom"
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgg.agrFDate}" />' />
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Agreement To<span
															style="color: red;">*</span></label> <input type="text"
															class="date-custom form-control form-white"
															placeholder="Select a date..." name="agreementTo"
															id="agreementTo"
															<c:if test="${empty tenantAgg}"> value="12/31/9999" </c:if>
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgg.agreementTo}" />' />
													</div>
												</div>
											</div>
											<div class="row">
												<%-- <div class="col-sm-4"> 
                    <div class="form-group">
                      <label class="control-label">Upload Agreement<span style="color: red;">*</span></label>
					  <input type="file" class="form-control form-white" placeholder="Upload Agreement File" name="agreementCopy" id="agreementCopy" /><c:if test="${not empty tenantLMOMSPAgreementVO}">
			   	    	<a href="#"><img width="30" src="downloadFiles?path=${tenantLMOMSPAgreementVO.agreementDocRef}" /></a></c:if>
                    </div>
					</div> --%>

												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label numbersOnly">Deposit Amount</label> <input
															type="text" class="form-control form-white"
															placeholder="Enter Deposit Amount" name="depositAmount"
															id="depositAmount"
															value="${tenantAgg.depositAmount}"
															pattern="\d+" />
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label numbersOnly">LMO Wallet Amount<span
															style="color: red;">*</span></label> <input type="text"
															class="form-control form-white"
															placeholder="Enter LMO WalletAmount"
															name="lmoWalletAmount" id="lmoWalletAmount"
															value="${tenantAgg.lmoWalletAmount}"
															pattern="\d+" />
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Upload Agreement</label> <input
															type="file" class="form-control form-white"
															placeholder="Upload Agreement File" name="agreementCopy"
															id="agreementCopy" />
														<c:if test="${not empty tenantAgg}">
															<span class="galleryImgs"> <a href="#"><img
																	width="30"
																	src="downloadFiles?path=${tenantAgg.agreementDocRef}" /></a>
															</span>
														</c:if>
													</div>
												</div>
											</div>
											<%-- <c:if test="${not empty tenantLMOMSPAgreementVO}">
					<div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Action<span style="color: red;">*</span></label>
                      <input type="radio" id="action" name="action"/>Approve &nbsp;
						<input type="radio" id="action1" name="action"/>Send Back &nbsp;
						<input type="radio" id="action2" name="action"/>Reject
                    </div>
					</div>
					
					<div class="row">
					<div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Comment<span style="color: red;">*</span></label>
                      <textarea name="reasons" id="reasons" class="form-control form-white" placeholder="Please Write a Comment..."></textarea>
                    </div></div></div></c:if> --%>
										</div>
									</div>

								</div>
							</div>

							<!-- END ROW -->
							<%-- <input type="submit" name="action" id="approve" class="btn btn-embossed btn-success" <c:if test="${not empty tenantLMOMSPAgreementVO.mspCode}">value="Approve" </c:if> <c:if test="${empty tenantLMOMSPAgreementVO.mspCode}">value="Save" </c:if>>
			  <input name="action" id="sendBack" class="btn btn-embossed btn-danger" <c:if test="${not empty tenantLMOMSPAgreementVO.mspCode}">value="Send Back" type="Submit" </c:if> <c:if test="${empty tenantLMOMSPAgreementVO.mspCode}"> value="Reset" type="Reset" </c:if> />
							<c:if test="${not empty tenantLMOMSPAgreementVO.mspCode}">&nbsp;&nbsp; <input type="Submit" name="action" id="reject" class="btn btn-embossed btn-danger" value="Reject" /></c:if> --%>
							<button class="btn btn-embossed btn-success" type="submit"
								name="action" value="Save">Submit</button>
							<button class="btn btn-embossed btn-warning" type="button"
								name="action" id="sendBack">Cancel</button>

							<!-- END ROW -->


						</form>
					</div>
				</div>

				<!-- END FORM -->

				<!-- END MAIN PANEL CONTENT -->
			</div>
			<!-- END MAIN PANEL -->
		</div>
		<!-- HERE COMES YOUR CONTENT -->
	</div>
</div>
<!-- END MAIN ROW -->
