<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Approve <strong>BlackList</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Approve BlackList</li>
				</ol>
			</div>
		</div>
		<%-- <label style="text-align: center;color: red; value="${message}"></label> --%>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<c:if test="${not empty Smsg}"><center> <font color='green' size="3">${Smsg}</font> </center> </c:if>
									<c:if test="${not empty Emsg}"> <center> <font color='red' size="3">${Emsg}</font> </center> </c:if>
									<div class="panel-header bg-light">
										<h3>Approve BlackLists</h3>
									</div>
									<div class="panel-content">
										<div class="row">
											<div class="col-sm-12">
												<table class="table table-alt" id="approveBlacklistTable">
													<thead>
														<tr>
															<th>S.No</th>
															<th>Aadhar-No</th>
															<th>Caf-No</th>
															<th>Mobile Number</th>
															<th>STB-SerialNo</th>
															<th>STB-MacAddress</th>
															<th>BlackListed-Date</th>
															<th>Reason</th>
															<th width="15%">Action</th>
														</tr>
													</thead>
													<tbody>
														<c:if test="${not empty blockedList}">
															<c:forEach items="${blockedList}" var="blocked" varStatus="rowNum">
																<tr>
																	<td>${rowNum.count}</td>
																	<td>${blocked.aadharno}</td>
																	<td>${blocked.cafno}</td>
																	<td>${blocked.phone}</td>
																	<td>${blocked.stbSlno}</td>
																	<td>${blocked.stbmac}</td>
																	<td><c:if test="${blocked.status=='0'}">${blocked.effectivefrom}</c:if>
																		<c:if test="${blocked.status!='0'}">${blocked.approvedon}</c:if>
																	</td>
																	<td>${blocked.reason}</td>
																	<td align="center"><c:choose>
																			<c:when test="${blocked.status=='1'}">APPROVED</c:when>
																			<c:when test="${blocked.status=='2'}">REJECTED</c:when>
																			<c:when test="${blocked.status=='4'}">Corpus Failed</c:when>
																			<c:when test="${blocked.status=='0'}">
																				<form action="<c:url value="/MakeApproveBlackList"/>" method="post">
																					<input type="hidden" id="custid" name="custid" value="${blocked.custid}" /> 
																					<input type="hidden" id="effectivefrom" name="effectivefrom" value="${blocked.effectivefrom}" />
																					<input type="hidden" id="cafno" name="cafno" value="${blocked.cafno}" />
																					<input type="hidden" id="nwsubscode" name="nwsubscode" value="${blocked.nwsubscode}" />
																					<input type="hidden" id="stbcafno" name="stbcafno" 	value="${blocked.stbcafno}" />
																					<!-- <button class="btn btn-embossed btn-primary ApproveBlackListButton" type="button" id="ApproveBlackListButton" >Approve</button> -->
																					<input type="submit" class="btn btn-embossed btn-success" value="Approve" style="float: left;" />
																				</form>
																				<form action="<c:url value="/RejectBlackList"/>" method="post">
																					<input type="hidden" id="custid" name="custid" value="${blocked.custid}" /> 
																					<input type="hidden" id="effectivefrom" name="effectivefrom" value="${blocked.effectivefrom}" /> 
																					<input type="hidden" id="cafno" name="cafno" value="${blocked.cafno}" />																					<input type="hidden" id="nwsubscode" name="nwsubscode" value="${blocked.nwsubscode}" /> 
																					<input type="hidden" id="stbcafno" name="stbcafno" value="${blocked.stbcafno}" />
																					<!-- <button class="btn btn-embossed btn-danger RejectBlackListButton" type="button" id="RejectBlackListButton" >Reject</button> -->
																					<input type="submit" class="btn btn-embossed btn-danger" value="Reject" style="float: left;" />
																				</form>
																			</c:when>
																		</c:choose></td>
																</tr>
															</c:forEach>
														</c:if>
													</tbody>
												</table>
											</div>
										</div>
										<!-- END ROW INNER-->
									</div>
								</div>
							</div>
						</div>
						<!-- END ROW -->

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
<!-- END MAIN CONTENT -->