<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#ponReportTable").dataTable();
	
	
	$(document.body).on('click','#searchponcafButton',function(){
		
		
				if(!$("#popdistrict").val() =='' && !$("#lmocode").val() == ''){
			
			alert("Please select 'Disrtict and Mandal' or  Enter 'LMOCode' only.");
			
		}
		
	});
	
	
	
		});
</script>
<body>
	<section>
		BEGIN MAIN CONTENT
		<div class="main-content">
			BEGIN PAGE CONTENT
			<div class="page-content page-width">
				<div class="page-title">
					<h2>PON Wise CAF Count Report</h2>
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
													
													<form role="form" class="form-validation" name="poncafform"
								id="poncafform" action="./ponWiseReportPagination" method="post">
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label"> Distrct</label>
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
											<label class="control-label">  Mandal</label>
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
									
											<div class="col-sm-1">
												<label></label>
													<label>
													<p><span style="color:blue;">OR</span></p>
													</label>
											</div>
									
								<div class="col-sm-2">
																			
									<div class="form-group">
										<label class="form-label"> LMO Code </label>
										<input type="text"
											class="form-control form-white"
											placeholder="Enter LMOCode"
											name="lmocode" id="lmocode" value="${lmocode}"/>
									</div>
							</div>
									
								</div>
								

								
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="submit"
													id="searchponcafButton">
													<i class="fa fa-search"></i>Search
												</button>
											</div>
										</div>
									</div>
								</div>
							</form>
										
												<div class="row">
														<p> &emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;">LMO Count: ${lmocount}</span> &emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;"> POP Count:${popcount}</span> &emsp; &emsp; 
														 <span style="font-weight:bold;color: #c13b34;">Port Count: ${portCount}</span> &emsp; &emsp;<span style="font-weight:bold;color: #c13b34;">Caf Count: ${cafCount}
														 </span></p>
												</div>
													
														<div class="row">

															<table class="table table-alt" id="ponReportTable">
																<thead>
																	<tr>
																		<th>POP_NAME</th>	
																		<th>POP OLT</th>	
																		<th>Port No</th>	
																		<th>LMO Code</th>	
																		<th>Contact Person Name</th>	
																		<th>Contact Person Mobile</th>	
																		<th>CAF Count</th>	
																		<th>District Name</th>	
																		<th>Mandal Name</th>	
																		<th>Created ON</th>
																	</tr>
																</thead>
																
																<tbody>
																	<c:forEach items="${ponWithCAFList}" var="ponCAFList" varStatus="theCount">
																		<tr>
																			
																			<td><c:out value="${ponCAFList.pop_name}"></c:out></td>
																			<td><c:out value="${ponCAFList.pop_olt_ipaddress}"></c:out></td>
																			<td><c:out value="${ponCAFList.portno}"></c:out></td>
																			<td><c:out value="${ponCAFList.lmocode}"></c:out></td>
																			<td><c:out value="${ponCAFList.regoff_pocname}"></c:out></td>
																			<td><c:out value="${ponCAFList.regoff_pocmob1}"></c:out></td>
																			<td><c:out value="${ponCAFList.cafno}"></c:out></td>
																			<td><c:out value="${ponCAFList.districtname}"></c:out></td>
																			<td><c:out value="${ponCAFList.mandalname}"></c:out></td>
																			<td><c:out value="${ponCAFList.createdon}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
																
															</table>
															
															<a
																	href="
																					<c:url value="./downloadPonWiseReport">
																						 <c:param name="district" value="${districtuid}"/>
																						 <c:param name="mandal" value="${mandalslno}"/>
																						 <c:param name="lmocode" value="${lmocode}"/>	 
																					</c:url>">
																	<INPUT TYPE="SUBMIT" VALUE="Download"
																	class="btn btn-success">
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
					</div>
				</div>
			</div>
		</div>
	</section>
</body>