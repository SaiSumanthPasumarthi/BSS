<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
$(document).ready(function() {
	
	$("#riggedCafsReportTable").dataTable();
	
	$('#popmandal').change(function() {
		var districtId = $('#popdistrict').val();
		var mandalId = $('#popmandal').val();
		if(districtId!="" && mandalId !="") {
			$.ajax({
				type : "GET",
				url : "getVillagesByDistrictIdAndMandalId",
				dataType : "json",
				data : "&districtId="+ districtId+"&mandalId="+mandalId,
				success : function(response) {
					var $select = $('#popvillage');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.villageSlno).text(val.villageName).appendTo($select);
					});
				}
			});
		}
	});
	
	$("#effectivefrom").datepicker({
		dateFormat: 'yy-mm-dd',
		numberOfMonths: 1,
	    changeMonth: true,
	    changeYear: true,
	    onSelect: function (selected) {
	        var dt = new Date(selected);
	        dt.setDate(dt.getDate() + 1);
	    }
	});
	$("#effectiveto").datepicker({
		dateFormat: 'yy-mm-dd',
		numberOfMonths: 1,
	    changeMonth: true,
	    changeYear: true,
	    onSelect: function (selected) {
	        var dt = new Date(selected);
	        dt.setDate(dt.getDate() - 1);
	      
	    }
	});
	
 	$("#tillriggedcafButton").click(function(){
		
		$("#effectivefrom").val('2016-01-01');
		var todate = new Date().toISOString().slice(0,10);
		$("#effectiveto").val(todate);
		
		
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
					<h2>Rigged CAFs details Report</h2>
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
													
													<form role="form" class="form-validation" name="riggedcafform"
								id="riggedcafform" action="./riggedcafreport" method="post">
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
									
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">  Village</label>
											<div class="option-group">

												<select name="villageslno" id="popvillage"
													class="form-control form-white">

													<option value="">--Select--</option>
													<c:forEach var="village" items="${villageList}">
														<c:choose>
															<c:when
																test="${not empty villageSlno && village.mandalSlno == villageSlno && village.villageSlno == mandalslno}">
																<option value="${village.villageSlno}" selected>${village.villageName}</option>
															</c:when>
															<c:otherwise>
																<option value="${village.villageSlno}">${village.villageName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									</div>
									<div class="col-sm-3">
																				<div class="form-group">
																					<label class="form-label">From<span
																						style="color: red;"></span></label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectivefrom" id="effectivefrom"  value="${effectivefrom}"/>
																				</div>
																				

																			</div>
																			<div class="col-sm-3">
																			
																				<div class="form-group">
																					<label class="form-label">To<span
																						style="color: red;"></span></label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectiveto" id="effectiveto"  value="${effectiveto}"/>
																				</div>
																			</div>

								
								

								
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="submit"
													id="searchriggedcafButton">
													<i class="fa fa-search"></i>Search
												</button>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="submit"
													id="tillriggedcafButton">
													<i class="fa fa-search"></i>Till Date
												</button>
											</div>
										</div>
									</div>
								</div>
								
							</form>
										
												<div class="row">
														<p> &emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;">LMO Count: ${lmocount}</span>  &emsp; &emsp;<span style="font-weight:bold;color: #c13b34;">Caf Count: ${totalcafcount}
														 </span>&emsp; &emsp;  <span style="font-weight:bold;color: #c13b34;">Rigged CAF Count: ${totalRiggedCafCount}</span></p>
												</div>
													
														<div class="row">

															<table class="table table-alt" id="riggedCafsReportTable">
																<thead>
																	<tr>
																		<th>LMO Code</th>	
																		<th>LMO Network Name</th>	
																		<th>LMO Name</th>	
																		<th>LMO MobileNo</th>	
																		<th>District</th>	
																		<th>Mandal</th>	
																		<th>Village</th>	
																		<th>Total CAF Count</th>
																		<th>Not Connect CAF Count</th>
																	</tr>
																</thead>
																
																<tbody>
																	<c:forEach items="${riggedCafList}" var="riggedCAFList" varStatus="theCount">
																		<tr>
																			
																			<td><c:out value="${riggedCAFList.lmoCode}"></c:out></td>
																			<td><c:out value="${riggedCAFList.lmoNwName}"></c:out></td>
																			<td><c:out value="${riggedCAFList.lmoName}"></c:out></td>
																			<td><c:out value="${riggedCAFList.lmoMobile}"></c:out></td>
																			<td><c:out value="${riggedCAFList.district}"></c:out></td>
																			<td><c:out value="${riggedCAFList.mandal}"></c:out></td>
																			<td><c:out value="${riggedCAFList.village}"></c:out></td>
																			<td><c:out value="${riggedCAFList.totalCafCount}"></c:out></td>
																			<td><c:out value="${riggedCAFList.notConnectCafCount}"></c:out></td>
																		</tr>
																	</c:forEach>
																</tbody>
																
															</table>
															
															<a
																	href="
																					<c:url value="./downloadriggedcafreport">
																						 <c:param name="district" value="${districtuid}"/>
																						 <c:param name="mandal" value="${mandalslno}"/>
																						 <c:param name="village" value="${villageslno}"/>
																						 <c:param name="from" value="${effectivefrom}"/>
																						 <c:param name="to" value="${effectiveto}"/>
																						 	 
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