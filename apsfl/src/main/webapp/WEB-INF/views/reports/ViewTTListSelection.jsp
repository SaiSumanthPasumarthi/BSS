<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.arbiva.apsfl.coms.dto.ComsHelperDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/mspLmoCpeRequest.js"></script>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>Trouble Ticket</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./home.do">Home</a></li>
					<!-- <li class="active">SLA Re  port</li> -->
				</ol>
			</div>
		</div>
		<div class="row main-row" style="margin: 15px">
			<div class="col-lg-12">
				<div class="panel main-panel" style="width: 100%">
					<div class="panel-content main-panel-content">
						<%-- <form action="ViewTTListSelection" method="post">

							<input type="radio" name="radioselect" id="cafs" value="cafs" disabled>
							Total Cafs Till Date :<a href=# id="cafs1"><b id="cafscount" style="color: red">${x}</b> </a><br> 
							<input type="radio" name="radioselect" id="tickets" value="tickets" disabled>
							Total Pending Tickets Till Date :<a href=# id="tickets1"><b id="ticketscount" style="color: red">${y}</b> </a><br>
							<input type="radio" name="radioselect" id="tickets" value="tickets" disabled>
							Total Amount Due Till Date :<a href=#><b id="dueamount" style="color: red">${y}</b></a> <br>

						</form> --%>
						<input type=hidden id='selectoption' name='selectoption' value="${selectoption}"> 
						<div class="panel-content">
							<div class="row m-b-5">
								<div class="col-sm-12">
									<div id="cafslist">
										<table class="table table-alt" id="cafstilldate">
											<thead>
												<tr>
													<th>S.No</th>
													<th>Account No</th>
													<th>Customer Name</th>
													<th>Aadhar No</th>
													<th>APSFL Track Id</th>
													<th>ONU Serial No</th>
													<th>Activation Date</th>
													<th>Mobile No</th>
													<th>Status</th>
												</tr>
											</thead>

											</thead>
											<tbody>
												<c:forEach var="cellData" varStatus="loop1"
													items="${cafslist}">
													<tr class="bulkrec">
														<td><c:out value="${loop1.count}"></c:out></td>
														<td><c:out value="${cellData.cafNo}"></c:out></td>
														<td><c:out value="${cellData.custName}"></c:out></td>
														<td><c:out value="${cellData.aadharNo}"></c:out></td>
														<td><c:out value="${cellData.apsflUniqueId}"></c:out></td>
														<td><c:out value="${cellData.cpeSrlNo}"></c:out></td>
														<td><c:out value="${cellData.actdate}"></c:out></td>
														<td><c:out value="${cellData.mName}"></c:out></td>
														<td><c:out value="${cellData.status}"></c:out></td>
														
													</tr>
												</c:forEach>


											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-content">
							<div class="row m-b-5">
								<div class="col-sm-12">
									<div id="ticketlist">
										<table class="table table-alt" id="pendingttlist">
											<thead>
												<tr>
													<th width="5%">TT NO</th>
													<th width="10%">Caf NO</th>
													<th width="15%">Name</th>
													<th width="5%">Issue Type</th>
													<th width="25%">Issue</th>
													<th width="10%">Olt Serial No</th>
													<th width="10%">Port</th>
													<th width="10%">Olt ONUID</th>
													<th width="10%">Created On</th>
													<th width="20%">Expected Closure Date</th>
													<th width="10%">Status</th>
													<th width="15%">Complaint</th>
													<th width="5%">Update TT</th>

												</tr>
											</thead>
											<tbody>
												<c:forEach var="cellData" varStatus="loop2"
													items="${pendingttlist}">
													<tr class="bulkrec">
														<td><c:out value="${cellData.ticketNo}"></c:out></td>
														<td><c:out value="${cellData.cafNo}"></c:out></td>
														<td><c:out value="${cellData.custName}"></c:out></td>
														<td><c:out value="${cellData.issueType}"></c:out></td>
														<td><c:out value="${cellData.issue}"></c:out></td>
														<td><c:out value="${cellData.oltSerialNo}"></c:out></td>
														<td><c:out value="${cellData.port}"></c:out></td>
														<td><c:out value="${cellData.oltONUID}"></c:out></td>
														<td><c:out value="${cellData.createdOnDate}"></c:out></td>
														<td><c:out value="${cellData.expectedClosureDate}"></c:out></td>
														<td><c:out value="${cellData.currentStatus}"></c:out></td>
														<td><c:out value="${cellData.ticketDesc}"></c:out></td>
														<td>
															<Button class="btn btn-embossed btn-primary">
																<a href="UpdatettScreen" style="color: white">Update
																	TT</a>
															</Button>
														</td>

													</tr>
												</c:forEach>


											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End of ROW -->
	</div>
	<!-- End Of Page Content  -->
</div>
<!-- End Of Main Content -->

