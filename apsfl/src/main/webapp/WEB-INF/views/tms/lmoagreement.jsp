<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- BEGIN MAIN CONTENT -->
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Tenant <strong>Agreement with APF</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">LMO Agreement with APF</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div>
						<c:if test="${ not empty tenantAgree.reasons }">
						<label>Reason For Send Back</label>
						<font face="Times New Roman" size="4px" color="Red">${tenantAgree.reasons}</font>
						</c:if>	
							
						</div>
						<form name="tenantAgmtForm" 
							action="<c:url value="/createTenantAgreement"/>" method="post"
							id="lomAgreement_form" enctype="multipart/form-data">
							<!-- <form role="form" class="form-validation">  -->
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>
												LMO Agreement with <strong>${tenantName}</strong>
											</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Tenant Name<span
															style="color: red;">*</span></label>
														<div class="option-group">
															<%-- 															<input type="hidden" id="tenantCode" name="tenantCode" placeholder="Select Tenant Code" value="${tenantCode}" /> --%>
															<select id="tenantCode" name="tenantCode"
																class="form-control form-white">
																<%-- 																<option value="${tenantCode}">${tenantName}</option> --%>
																<c:forEach var="tenant" items="${tenantList}">
																	<c:choose>
																		<c:when
																			test="${not empty tenantCode  && tenant.tenantCode == tenantCode}">
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
														<label class="control-label">Select Core Service<span
															style="color: red;">*</span></label>
														<div class="option-group">
															<select id="coreServiceMulti" name="coreServiceMulti"
																multiple="multiple" placeholder="Select Core Service"
																required>
																<c:forEach var="coreServices"
																	items="${coreServicesList}">
																	<option value="${coreServices.srvcCode}">${coreServices.srvcName}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Agreement Date<span
															style="color: red;">*</span></label> <input type="text"
															name="agreementDate" id="agreementDate"
															class="date-custom form-control form-white"
															placeholder="Select a date..."
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgree.agreementDate}" />'>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Agreement From<span
															style="color: red;">*</span></label> <input type="text"
															name="agrFDate" id="agrFDate"
															class="date-custom form-control form-white"
															placeholder="Select a date..."
															<c:if test="${not empty tenantAgree.agrFDate}"> readonly </c:if>
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgree.agrFDate}" />' />
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Agreement To<span
															style="color: red;">*</span></label> <input type="text"
															name="agreementTo" id="agreementTo"
															class="date-custom form-control form-white"
															placeholder="Select a date..."
															<c:if test="${empty tenantAgree}"> value="12/31/9999" </c:if>
															value='<f:formatDate pattern="MM/dd/yyyy" value="${tenantAgree.agreementTo}" />' />
													</div>
												</div>

												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Deposit Amount</label> <input
															type="text" name="depositAmount" id="depositAmount"
															value="${tenantWallet.depositAmount}"
															class="form-control form-white numbersOnly"
															placeholder="Enter Deposite Amount" pattern="\d+">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Wallet Amount<span
															style="color: red;">*</span></label> <input type="text"
															name="walletAmount" id="walletAmount"
															value="${tenantWallet.walletAmount}"
															class="form-control form-white numbersOnly"
															placeholder="Enter Wallet Amount" required>
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Upload Agreement</label> <input
															type="file" name="agreementCopy" id="agreementCopy"
															class="form-control form-white"
															placeholder="Upload the Agreement" />
														<c:if test="${not empty tenantAgree}">
															<span class="galleryImgs"> <a href="#"><img width="30"
																	src="downloadFiles?path=${tenantAgree.agreementDocRef}" /></a></span>
														</c:if>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- END ROW -->

								<!-- END ROW -->
								&nbsp;&nbsp;
								<button class="btn btn-embossed btn-success" type="submit"
									name="action" value="Save">Submit</button>
								<button class="btn btn-embossed btn-warning" type="button"
									name="action" id="sendBack">Cancel</button>
							</div>
						</form>
					</div>
					<!-- END FORM -->

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
<!--  <script>
        $('select').multipleSelect();
    </script> -->