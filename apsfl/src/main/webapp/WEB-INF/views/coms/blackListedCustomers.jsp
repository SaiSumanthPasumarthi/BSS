<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong>BlackListed Customers</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">BlackListed Customers</li>
				</ol>
			</div>
		</div>
		<%-- <label id="serviceNameLable" style="color: #006600">${returnValue}</label> --%>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<c:if test="${not empty Smsg}">
										<center>
											<font color='green' size="3">${Smsg}</font>
										</center>
									</c:if>
									<c:if test="${not empty Emsg}">
										<center>
											<font color='red' size="3">${Emsg}</font>
										</center>
									</c:if>
									<div class="panel-content">
										<form name="BlockListedForm" id="BlockListedForm" action="<c:url value="/searchblackListedCustomers"/>"
											method="post">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">STB-MacAddress</label> 
															<input type="text" name="stbMac" id="stbMac" class="form-control form-white " placeholder="please Enter" value="<c:if test="${not empty stbMac}">${stbMac}</c:if>">
														</div>
													</div>
													<div class="col-sm-1">
														<br> <label class="control-label">OR</label>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">From Date</label> 
															<input type="text" name="fromdate" id="effectiveFrom" class="date-picker form-control form-white" placeholder="Select From Date" value="<c:if test="${not empty fromdate}">${fromdate}</c:if>">
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">To Date</label> 
															<input type="text" name="todate" id="effectiveTO" class="date-picker form-control form-white" placeholder="Select To Date" value="<c:if test="${not empty todate}">${todate}</c:if>">
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label hide-mobile">&nbsp;</label>
															<div class="option-group">
																<button class="btn btn-embossed btn-primary" type="submit" id="BlockedSearchButton">
																	<i class="fa fa-search"></i>Search
																</button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</form>
									</div>
									<c:if test="${not empty BlackListed}">
										<div class="panel-header bg-light">
											<h3>BlackListed Customers</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-12">
													<table class="table table-alt" id="ViewBlackListedCustomersTable">
														<thead>
															<tr>
																<th>S.No</th>
																<th>Aadhar No</th>
																<th>Customer Name</th>
																<th>STB-SerialNo</th>
																<th>STB-MacAddress</th>
																<th>BlackListed-Date</th>
																<th>Caf No</th>
																<th>Installation Address</th>
																<th>Reason</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${BlackListed}" var="BlackList" varStatus="rowNum">
																<tr>
																	<td>${rowNum.count}</td>
																	<td>${BlackList.aadharno}</td>
																	<td>${BlackList.fname} ${BlackList.lname}</td>
																	<td>${BlackList.stbslno}</td>
																	<td>${BlackList.stbmac}</td>
																	<td>${BlackList.approvedon}</td>
																	<td>${BlackList.cafno}</td>
																	<td style="word-break: break-all;">${BlackList.addr1},${BlackList.addr2}, ${BlackList.locality},${BlackList.area}</td>
																	<td style="word-break: break-all;">${BlackList.reason}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<table>
														<tr>
															<td>
																<form action="<c:url value="./blacklistCustomerDownload"/>" method="post">
																	<input type="text" hidden="hidden" name="fromdate" value="<c:if test="${not empty fromdate}">${fromdate}</c:if>" />
																	<input type="text" hidden="hidden'" name="todate" value="<c:if test="${not empty todate}">${todate}</c:if>" />
																	<input type="text" hidden="hidden'" name="stbMac" value="<c:if test="${not empty stbMac}">${stbMac}</c:if>" />
																	<input type="text" hidden="hidden" name="download" value="true" />
																	<button class="btn btn-embossed btn-primary" type="submit">Download</button>
																</form>
															</td>
														</tr>
													</table>
												</div>
											</div>
											<!-- END ROW INNER-->
										</div>
									</c:if>
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