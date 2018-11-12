<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<script type="text/javascript">
$(document).ready(function() {
	
	$('#ponWithNoCafTable').dataTable();
	
	$(document.body).on('click','#searchponnocafButton',function(){
		
		
		if(!$("#popdistrict").val() =='' && !$("#lmocode").val() == ''){
	
	alert("Please search 'Disrtict and Mandal' or   'LMOCode' only.");
	
}

});
	
	
})
</script>


<body>
	<section>
		BEGIN MAIN CONTENT
		<div class="main-content">
			BEGIN PAGE CONTENT
			<div class="page-content page-width">
				<div class="page-title">
					<h2>PON With Zero Caf Report</h2>
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
													
								<form role="form" class="form-validation" name="ponnocafform"
								id="ponnocafform" action="./ponWithNoCafPagination" method="post">
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
									
											<div class="col-sm-1">
												<label></label>
													<label>
													<p><span style="color:blue;">OR</span></p>
													</label>
											</div>
									
								<div class="col-sm-2">
																			
									<div class="form-group">
										<label class="form-label">LMO Code </label>
										<input type="text"
											class="form-control form-white"
											placeholder="Enter LMOCode"
											name="lmocode" id="lmocode"/>
									</div>
							</div>
									
								</div>
								

								
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="submit"
													id="searchponnocafButton">
													<i class="fa fa-search"></i>Search
												</button>
											</div>
										</div>
									</div>
								</div>
							</form>
							
												<div class="row">
														<p> &emsp; &emsp;   <span style="font-weight:bold;color: #c13b34;">LMO Count: ${lmocount}</span> &emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;"> POP Count: ${popcount}
														 </span> &emsp; &emsp; <span style="font-weight:bold;color: #c13b34;"> Port Count: ${portCount}</span></p>
												</div>
							
														<div class="row">

															<table class="table table-alt" id="ponWithNoCafTable">
																<thead>
																	<tr>
																		<th>SlNO.</th>
																		<th>POP NAME</th>	
																		<th>LMO CODE</th>	
																		<th>Port No</th>	
																		<th>Tenant Name</th>	
																		<th>District Name</th>
																		<th>Mandal Name</th>	
																		<th>Village Name</th>	
																		<th>Contact Person Mobile No</th>	
																	</tr>
																</thead>
																
																<tbody>
																	<c:forEach items="${ponWithZeroCAFList}" var="ponwthzerocaflist" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td> 
																			<td><c:out value="${ponwthzerocaflist.pop_name}"></c:out></td>
																			<td><c:out value="${ponwthzerocaflist.lmocode}"></c:out></td>
																			<td><c:out value="${ponwthzerocaflist.portno}"></c:out></td>
																			<td><c:out value="${ponwthzerocaflist.tenantname}"></c:out></td>
																			<td><c:out value="${ponwthzerocaflist.districtname}"></c:out></td>
																			<td><c:out value="${ponwthzerocaflist.mandalname}"></c:out></td>
																			<td><c:out value="${ponwthzerocaflist.villagename}"></c:out></td>
																			<td><c:out value="${ponwthzerocaflist.regoff_pocmob1}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
																
															</table>
													
															<a
																	href="
																					<c:url value="./downloadPonWithNoCafReport">
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