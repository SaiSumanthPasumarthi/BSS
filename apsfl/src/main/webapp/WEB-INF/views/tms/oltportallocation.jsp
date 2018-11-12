<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page session="false"%>
<html>
<head>
<title>OLT Port Allocation</title>
<script src="./resources/js/jquery.autocomplete.min.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">
</head>
<body>
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>OLT Port Allocation</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">HOME</a></li>
						<li class="active">OLT Port Allocation</li>
					</ol>
				</div>
				<label id="error" style="text-align: center; color: red;"></label>
				<div class="successmsg"></div>
			   </div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">

							<div class="row">
								<div style="margin: auto; width: 25%">
									<label id="id_oltMsg"></label>
								</div>
							</div>

							<form role="form" class="form-validation" name="oltform"
								id="oltform" action="#" method="post">
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Distrct</label>
											<div class="option-group">
												<select name="districtuid" id="popdistrict"
													class="form-control form-white">
													<option value="">--Select--</option>

													<c:forEach var="district" items="${districtList}">
														<c:choose>
															<c:when
																test="${not empty districtuid && district.districtUid == districtuid}">
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
											<label class="control-label">Mandal</label>
											<div class="option-group">

												<select name="mandalslno" id="popmandal"
													class="form-control form-white">

													<option value="">--Select--</option>
													<c:forEach var="mandal" items="${mandalList}">
														<c:choose>
															<c:when
																test="${not empty mandalslno && mandal.mandalSlno == mandalslno && mandal.districtUid == districtuid}">
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
								</div>
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="button"
													id="searchOltButton">
													<i class="fa fa-search"></i>Search
												</button>
											</div>
										</div>
									</div>
								</div>
							</form>
							<c:if test="${not empty message}">
								<center>
									<font color='red' size="3">${message}</font>
								</center>
							</c:if>

							<c:if test="${not empty oltList}">
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt" id="PortDetailsTable">
											<thead>
												<tr>
													<th>S.No</th>
													<th hidden="hidden">Substaion Id</th>
													<th>Substaion Name</th>
													<th>Olt Serial No</th>
													<!--  th>CreatedOn</th>
													<th>CreatedBy</th>
													<th>ModifiedOn</th-->
													<th>Subscriber Count</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${oltList}" var="oltno"
													varStatus="rowCount">
													<tr>
														<td>${rowCount.count}</td>
														<td hidden="hidden" class="suid"
															id="suid${rowCount.count}">${oltno.substnuid}</td>
														<td>${oltno.substnname}</td>
														<td class="oltno" id="oltno${rowCount.count}">${oltno.popOltSerialno}</td>
														<td>${oltno.subscriberCount}</td>
														<!--td>${oltno.createdon}</td>
														<td>${oltno.createdby}</td>
														<td>${oltno.modifiedon}</td-->
														<td><input type="radio" name="ch1"
															value="${rowCount.index}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>

								<div class="row portInfo" style="display: none">
									<div class="col-lg-6">
										<div class="col-sm-12 disable-search ">
											<div class="panel">
												<div class="panel-header bg-light">
													<h3>Port Information</h3>
												</div>
												<div id="portinfo" style="max-height: 600px"
													class="ScrollStyle">
													<table class="table table-alt " id="portlistTable">
														<thead>
															<tr>
																<th>Port No.</th>
																<th>Tenant Details</th>
																<th>Subscriber Count</th>
																<th>CreatedOn</th>
																<th>CreatedBy</th>
																<th>ModifiedOn</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="row savePortDtlsButton" style="display: none">
									<div class="col-sm-2 pull-right">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="button"
													id="savePortDtlsButton">
													<i class="fa fa-search"></i>SAVE
												</button>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script src="./resources/js/olt-port-allocation.js"></script>
</body>
</html>
