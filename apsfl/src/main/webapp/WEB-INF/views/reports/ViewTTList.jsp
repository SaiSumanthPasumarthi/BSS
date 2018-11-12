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
					<!-- <li class="active">SLA Report</li> -->
				</ol>
			</div>
		</div>
		<div class="row main-row" style="margin: 15px">
			<div class="col-lg-12">
				<div class="panel main-panel" style="width: 100%">
					<div class="panel-content main-panel-content">
						<form action="ViewTTListSelection" method="post">
							<div>
										<table class="table table-alt">
											<thead>
												<tr>
													<th><input type="radio" name="radioselect" id="cafs" value="cafs" disabled>Total Cafs Till Date :</th>
													<th><input type="radio" name="radioselect" id="tickets" value="tickets" disabled>Total Pending Tickets Till Date :</th>
													<th><input type="radio" name="radioselect" id="tickets" value="Balance" disabled>Due Amount:</th>
													<th><input type="radio" name="radioselect" id="ponCount" value="ponCount" disabled>PON Count:</th>
												</tr>
											</thead>

											<tbody>
													<tr class="bulkrec">
														<td><a href="./ViewTTListSelection?selectoption=cafslist" id="cafs1"><b id="cafscount" style="color: red">${x}</b> </a></td>
														<td><a href="./ViewTTListSelection?selectoption=ticketlist" id="tickets1"><b id="ticketscount" style="color: red">${y}</b> </a></td>
														<td><a href=#><b id="dueamount" style="color: red">${z}</b></a> <br></td>
														<%-- <td><a href="./ponWiseReportPagination?lmocode=${tenantCode}" id="ponCount1"> --%>
														
																<td><a
																				href="
																					<c:url value="ponWiseReportPagination">
																						 <c:param name="districtuid" value=""/>
																						 <c:param name="mandalslno" value=""/>
																						 <c:param name="lmocode" value="${tenantCode}"/> 
																					</c:url>">
																					<%-- <c:out value="${poncount}"></c:out> --%>
																			
														
														<b id="ponsCount" style="color: red">${poncount}</b> </a></td>	
													</tr>
												


											</tbody>
										</table>
									</div>
							
							
							
							

						</form>
							</div>
				</div>
			</div>
		</div>
		<!-- End of ROW -->
	</div>
	<!-- End Of Page Content  -->
</div>
<!-- End Of Main Content -->

