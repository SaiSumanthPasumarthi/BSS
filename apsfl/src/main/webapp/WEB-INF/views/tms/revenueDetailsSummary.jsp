<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
a:hover {
	cursor: pointer;
}
</style>
<script>
	$(document)
			.ready(
					function() {
						
						$("#revdetailstable").dataTable(); 
						var hiddenYear = $('#hiddenSelectYear').val();
						var min_year = new Date().getFullYear();
						min_year = 2016;
						max_year = min_year + 15;
						var div_data = "";
						for (var i = min_year; i <= max_year; i++) {
							if (i == hiddenYear) {
								div_data = div_data
										+ "<option selected value=" + i+ ">"
										+ i + "</option>";
							} else {
								div_data = div_data
										+ "<option  value=" + i+ ">" + i
										+ "</option>";
							}
						}
						$(div_data).appendTo('#yearListId');

						$('#updateid')
								.click(
										function() {

											var month = $('#monthsListId')
													.val() == undefined ? ""
													: $('#monthsListId').val();
											var year = $('#yearListId').val() == undefined ? ""
													: $('#yearListId').val();

											if (month == "--Select--"
													&& year == "--Select--") {
												alert("Please Select month and year.");

											}
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
					<h2>Revenue Share Details</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				<!-- cpe Information-->
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<form name="cpereport" id="cpereport" method="get"
									action="./getMonthWiseShares">

								

									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Month:<span
												style="color: red;">*</span></label>
										</div>
										<div class="form-group">

											<select id='monthsListId' name="invmn"
												class="form-control form-white" required="required">

												<option selected value=''>--SELECT--</option>
												<c:choose>
													<c:when test="${month eq '01'}">
														<option selected value="01">January</option>
													</c:when>
													<c:otherwise>
														<option value="01">January</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '02'}">
														<option selected value="02">February</option>
													</c:when>
													<c:otherwise>
														<option value="02">February</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '03'}">
														<option selected value="03">March</option>
													</c:when>
													<c:otherwise>
														<option value="03">March</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '04'}">
														<option selected value="04">April</option>
													</c:when>
													<c:otherwise>
														<option value="04">April</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '05'}">
														<option selected value="05">May</option>
													</c:when>
													<c:otherwise>
														<option value="05">May</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '06'}">
														<option selected value="06">June</option>
													</c:when>
													<c:otherwise>
														<option value="06">June</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '07'}">
														<option selected value="07">July</option>
													</c:when>
													<c:otherwise>
														<option value="07">July</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '08'}">
														<option selected value="08">August</option>
													</c:when>
													<c:otherwise>
														<option value="08">August</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '09'}">
														<option selected value="09">September</option>
													</c:when>
													<c:otherwise>
														<option value="09">September</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '10'}">
														<option selected value="10">October</option>
													</c:when>
													<c:otherwise>
														<option value="10">October</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '11'}">
														<option selected value="11">November</option>
													</c:when>
													<c:otherwise>
														<option value="11">November</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${month eq '12'}">
														<option selected value="12">December</option>
													</c:when>
													<c:otherwise>
														<option value="12">December</option>
													</c:otherwise>
												</c:choose>
											</select>
										</div>
									</div>




									<input type="hidden" id="hiddenSelectYear" value="${year}">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Year:<span
												style="color: red;">*</span></label>
										</div>
										<div class="form-group">
											<select id='yearListId' name="invyr"
												class="form-control form-white" required="required">
												<option selected value=''>--SELECT--</option>
											</select>
										</div>
									</div>

									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">District</label>
											</div>
											<div class="option-group">
												<select name="districtuid" id="cpedistrict"
													class="form-control form-white">
													<option value="">All</option>

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
										

									<div class="clear"></div>
									
									<c:if test="${not empty message}">
										<center>
											<font color='green' size="3">${message}</font>

										</center>
									</c:if>
			
									
									


									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="submit"
													id="updateid">
													<i class="fa fa-search"></i>Search
												</button>
											</div>
										</div>
									</div>
									<div class="clear"></div>


								</form>


							<div class="row">
										<div class="col-sm-3">
											<div class="form-group">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">APSFL Total Share :</label><span style="font-weight:bold;color: #c13b34;">${apsfl_total_share}</span>
												</div>
											</div> 
																					<div class="col-sm-3">
											<div class="form-group">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">MSO Total Share:</label><span style="font-weight:bold;color: #c13b34;">${mso_total_share}</span>
												
											</div>
											</div>
																					<div class="col-sm-3">
											<div  class="form-group pull-right">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">LMO Total Share:</label><span style="font-weight:bold;color: #c13b34;">${lmo_total_share}</span>
												
											</div>
											</div>
											<div class="clear"></div>
											<div class="col-sm-12">
											</div>
											
											
											<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Total Paid:</label><span style="font-weight:bold;color: #c13b34;">${lmo_total_paid}</span>
												
											</div>
											</div>
											<div class="col-sm-3">
											<div  class="form-group ">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Total Not Paid:</label><span style="font-weight:bold;color: #c13b34;">${lmo_total_notpaid}</span>
												
											</div>
											
											</div>

									</div>			 

							
									<div class="row m-b-5">
										<div class="col-sm-12">
											<table class="table table-alt" id="revdetailstable">
												<thead>


													<tr>
														<th>SLNO</th>
														<th>LMO Code</th>
														<th>MSO Code</th>
														<th>Invoice year</th>
														<th>Invoice Month</th>
														<th>LMO Share</th>
														<th>MSO Share</th>
														<th>APSFL Share</th>
														<th>Amount Collected</th>
														 <th>CustomerWise  Arrears</th>
														 <th>Caf Count</th>  
														 <th>Credit Remaining</th> 
														 <th>Paid Count</th> 
														 <th>Not Paid Count</th> 

													</tr>
												</thead>
												<tbody>
													<c:forEach items="${revsharelist}" var="revinv"
														varStatus="rowCount">
														<tr>
															<td>${rowCount.count}</td>
															
															
														 <td><a  href='cafwiseRevenueDetailsOfLoginLmo?lmocode=${revinv.lmocode}&invyr=${year}&invmn=${month}&msocode=${revinv.msocode}&apsflshare=${revinv.apsflmonthlyshare}&lmoshare=${revinv.lmomonthlyshare}&msoshare=${revinv.msomonthlyshare}&totalcollected=${revinv.totalcollected}&totalprevbal=${revinv.totalPrevBal}&cafcount=${revinv.cafcount}&remaininglimit=${revinv.creditlimit}&paidcount=${revinv.paid}&notpaidcount=${revinv.notpaid}'class="revid" id="revid">${revinv.lmocode}</a></td> 
															
															<td>${revinv.msocode}</td>
															<td>${revinv.invyr}</td>
															<td>${revinv.invmn}</td>
															<td>${revinv.lmomonthlyshare}</td>
															<td>${revinv.msomonthlyshare}</td>
															<td>${revinv.apsflmonthlyshare}</td>
															<td>${revinv.totalcollected}</td>
															 <td>${revinv.totalPrevBal}</td> 
															 <td>${revinv.cafcount}</td> 
															  <td>${revinv.creditlimit}</td> 
															   <td>${revinv.paid}</td> 
															    <td>${revinv.notpaid}</td> 

														</tr>
													</c:forEach>
												</tbody>
											</table>


											<div class="" style="">
												<button style="" type="button"
													class="btn btn-embossed btn-primary">
													<a style="color: white;" href="./revsharedownload ">Download</a>
												</button>
											</div>
										</div>
									</div>


								<%-- </c:if> --%>
								<!-- END ROW INNER-->




							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
