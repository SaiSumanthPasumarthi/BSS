<%@page import="com.arbiva.apsfl.coms.dto.ComsHelperDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div id="monthlyCafDiv">
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>CAF's List</strong>
				</h2>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<div class="row main-row">
								<div class="col-lg-12">
									<div class="panel main-panel">
										<div class="panel-content main-panel-content">
											<div class="row m-b-5">
												<div class="col-sm-12">
													<table class="table table-alt" id="monthlyPaymentTable1">
														<thead>
															<tr>
																<th width="15%">Caf Number</th>
																<th width="15%">Customer Name</th>
																<th width="15%">Aadhar Number</th>
																<th width="15%">ActivationDate</th>
																<th width="15%">MobileNumber</th>
															</tr>
														</thead>
														<tbody>


															<c:forEach var="cellData" varStatus="loop"
																items="${districtWiseCafList}">
																<tr class="bulkrec">
																	<td width="15%"><c:out value="${cellData.custid}"></c:out></td>
																	<td width="15%"><c:out
																			value="${cellData.custType}"></c:out></td>
																	<td width="15%"><c:out
																			value="${cellData.aadharno}"></c:out></td>
																	<td width="15%"><c:out value="${cellData.dob}"></c:out></td>
																	<td width="15%"><c:out
																			value="${cellData.contactno}"></c:out></td>
																</tr>
															</c:forEach>


														</tbody>
													</table>

												</div>
												<input type="hidden" name="tenantCode" id="tenantCode" value="">
												<input type="hidden"name="ofdate" id="ofdate" value="">
												<input type="hidden" name="hiddentodate" id="hiddentodate" value="">
												<input type="hidden"name="hiddenfromdate" id="hiddenfromdate" value="">
												<div class="form-group">
													<a href= "
																					<c:url value="downloadManagerCustomerDemand">
																						 <c:param name="tenantCode" value="${tenantCode}"/>
																						 <c:param name="from" value="${ofdate}"/>
																						 <c:param name="effectivefrom" value="${hiddenfromdate}"/>
																						 <c:param name="to" value="${hiddentodate}"/>	 
																					</c:url>">
																					<INPUT	TYPE="SUBMIT" VALUE="Download" class="btn btn-success">
																				</a>
												</div>
												<div class="form-group">
													<a
														href="
																					<c:url value="managerdistWiseCafReport">
																						 <c:param name="effectivefrom" value="${hiddenfromdate}"/>
																						 <c:param name="effectiveto" value="${hiddentodate}"/>
																						 <c:param name="lmocode" value="${tenantCode}"/>	 
																					</c:url>">
																					<INPUT	TYPE="SUBMIT" VALUE="Back" class="btn btn-success">
													</a>

												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
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
<!-- END MAIN CONTENT -->
