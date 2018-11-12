<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript" >

$(document).ready(function() {
	
$('#oltmasterdataTable').dataTable();

});

</script>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>OLT Master Data Report</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row" align="center">
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="panel main-panel">
													<div class="panel-content main-panel-content">
														<div class="row">

															<table class="table table-alt" id="oltmasterdataTable">
																<thead>
																	<tr>
																		<th>SNo</th>
																		<th>POP SubstationUID</th>
																		<th>POP Name</th>
																		<th>POP OLT SerialNo.</th>
																		<th>POP OLT IPAddress</th>
																		<th>District Name</th>
																		<th>Mandal Name</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${oltMasterDataList}" var="oltmasterdataList" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td> 
																			<td><c:out value="${oltmasterdataList.popSubstnUID}"></c:out></td>
																			<td><c:out value="${oltmasterdataList.popName}"></c:out></td>
																			<td><c:out value="${oltmasterdataList.popOltSerialNo}"></c:out></td>
																			<td><c:out value="${oltmasterdataList.popOltIPAddress}"></c:out></td>
																			<td><c:out value="${oltmasterdataList.districtName}"></c:out></td>
																			<td><c:out value="${oltmasterdataList.mandalName}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														<a href="./downloadoltmasterdata">	<INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-success"></a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>