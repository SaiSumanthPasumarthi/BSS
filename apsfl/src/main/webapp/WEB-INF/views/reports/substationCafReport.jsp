<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="./resources/js/mspLmoCpeRequest.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		//$('#olt8Div').hide();
		$('#olt16Div').hide();
		$("#radioolt").click();
		$("#radioolt").click(function() {
			$('#olt16Div').hide();
			$("#olt8Div").show();
		});

		$("#radioMultiOlt").click(function() {
			$('#olt8Div').hide();
			$("#olt16Div").show();
			
		});

	});
	
</script>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Substation and OLT Wise CAF Report</h2>

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
							
							<form role="form" class="form-validation" name="subcafform"
								id="subcafform" action="./substationCafReport" method="post">
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
		
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Olt Type<span style="color: red;">*</span></label>
											<div class="clear"></div>
											<div class="pull-left" style="padding-left: 0;">
												<input type="radio" name="radio" id="radioolt" class="form-white" value="8">&nbsp;&nbsp;8 Port OLT
											</div>
											<div class="col-sm-6">
												<input type="radio" name="radio" id="radioMultiOlt" class="form-white" value="16">&nbsp;&nbsp;16 Port OLT
											</div>
											<div class="clear"></div>
										</div>
									</div>
									<div class="clear"></div>
								</div>
								<div id="olt8Div">
								
									<div class="row">
											<p>  &emsp; &emsp; 
												 <span style="font-weight:bold;color: #c13b34;">Port Count: ${portcount8}</span> &emsp; &emsp;<span style="font-weight:bold;color: #c13b34;">Caf Count: ${cafcount8}
												 </span></p>
												</div>
	
									<table class="table table-alt" id="subWise8Table">
										<thead>
											<tr>
												<th rowspan="2" >SNo</th>
												<th rowspan="2" style="text-align:center;">Substation Name & OLT IP Address & OLT Serial</th>
												<th rowspan="2" >Card ID</th>
												<th rowspan="2" >District Name</th>
												<th rowspan="2" >Mandal Name</th>
												<th colspan="8" style="text-align:center;">PORT NO</th>
												<th rowspan="2">Total</th>
											</tr>
											<tr>
												<th>1</th>
												<th>2</th>
												<th>3</th>
												<th>4</th>
												<th>5</th>
												<th>6</th>
												<th>7</th>
												<th>8</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list8}" var="subCafList" varStatus="theCount">
												<tr>
													<td><c:out value="${theCount.count}"></c:out></td> 
													<td><c:out value="${subCafList.popNameWithIP}"></c:out></td>
													<td><c:out value="${subCafList.cardid}"></c:out></td>
													<td><c:out value="${subCafList.districtname}"></c:out></td>
													<td><c:out value="${subCafList.mandalname}"></c:out></td>
													<td><c:out value="${subCafList.cafCount1}"></c:out></td>
													<td><c:out value="${subCafList.cafCount2}"></c:out></td>
													<td><c:out value="${subCafList.cafCount3}"></c:out></td>
													<td><c:out value="${subCafList.cafCount4}"></c:out></td>
													<td><c:out value="${subCafList.cafCount5}"></c:out></td>
													<td><c:out value="${subCafList.cafCount6}"></c:out></td>
													<td><c:out value="${subCafList.cafCount7}"></c:out></td>
													<td><c:out value="${subCafList.cafCount8}"></c:out></td>
													<td><c:out value="${subCafList.total}"></c:out></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
																						
								<a href="
								   <c:url value="./downloadSubWiseReport?noofports=8">
									  <c:param name="district" value="${districtuid}"/>
									  <c:param name="mandal" value="${mandalslno}"/>	 
									</c:url>">
									<INPUT TYPE="SUBMIT" VALUE="Download"
										class="btn btn-success">
									</a>
								
								</div>
								
								<div id="olt16Div">
								
										<div class="row">
											<p>  &emsp; &emsp; 
												 <span style="font-weight:bold;color: #c13b34;">Port Count: ${portcount16}</span> &emsp; &emsp;<span style="font-weight:bold;color: #c13b34;">Caf Count: ${cafcount16}
												 </span></p>
												</div>
	
									<table class="table table-alt" id="subWise16Table">
										<thead>
											<tr>
												<th rowspan="2" >SNo</th>
												<th rowspan="2" style="text-align:center;">Substation Name & OLT IP Address & OLT Serial</th>
												<th rowspan="2" >Card ID</th>
												<th rowspan="2" >District Name</th>
												<th rowspan="2" >Mandal Name</th>
												<th colspan="16" style="text-align:center;">PORT NO</th>
												<th rowspan="2">Total</th>
											</tr>
											<tr>
												<th>1</th>
												<th>2</th>
												<th>3</th>
												<th>4</th>
												<th>5</th>
												<th>6</th>
												<th>7</th>
												<th>8</th>
												<th>9</th>
												<th>10</th>
												<th>11</th>
												<th>12</th>
												<th>13</th>
												<th>14</th>
												<th>15</th>
												<th>16</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list16}" var="subCafList" varStatus="theCount">
												<tr>
													<td><c:out value="${theCount.count}"></c:out></td> 
													<td><c:out value="${subCafList.popNameWithIP}"></c:out></td>
													<td><c:out value="${subCafList.cardid}"></c:out></td>
													<td><c:out value="${subCafList.districtname}"></c:out></td>
													<td><c:out value="${subCafList.mandalname}"></c:out></td>
													<td><c:out value="${subCafList.cafCount1}"></c:out></td>
													<td><c:out value="${subCafList.cafCount2}"></c:out></td>
													<td><c:out value="${subCafList.cafCount3}"></c:out></td>
													<td><c:out value="${subCafList.cafCount4}"></c:out></td>
													<td><c:out value="${subCafList.cafCount5}"></c:out></td>
													<td><c:out value="${subCafList.cafCount6}"></c:out></td>
													<td><c:out value="${subCafList.cafCount7}"></c:out></td>
													<td><c:out value="${subCafList.cafCount8}"></c:out></td>
													<td><c:out value="${subCafList.cafCount9}"></c:out></td>
													<td><c:out value="${subCafList.cafCount10}"></c:out></td>
													<td><c:out value="${subCafList.cafCount11}"></c:out></td>
													<td><c:out value="${subCafList.cafCount12}"></c:out></td>
													<td><c:out value="${subCafList.cafCount13}"></c:out></td>
													<td><c:out value="${subCafList.cafCount14}"></c:out></td>
													<td><c:out value="${subCafList.cafCount15}"></c:out></td>
													<td><c:out value="${subCafList.cafCount16}"></c:out></td>
													<td><c:out value="${subCafList.total}"></c:out></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
														
								<a href="
								   <c:url value="./downloadSubWiseReport?noofports=16">
									  <c:param name="district" value="${districtuid}"/>
									  <c:param name="mandal" value="${mandalslno}"/> 
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
	</section>