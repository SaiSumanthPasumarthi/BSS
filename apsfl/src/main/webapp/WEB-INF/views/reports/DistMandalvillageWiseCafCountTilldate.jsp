<%@page import="com.arbiva.apsfl.coms.dto.ComsHelperDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" >
$(document).ready(function() {
$('#districtmandalandvillageWiseCafcounttilldateTable').dataTable();

  

});
</script>

<div id="monthlyCafDiv">
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>District Mandal and village wise till date caf count </strong>
				</h2>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<div class="row main-row">
								<div class="col-lg-12">
									<div class="panel main-panel">
										<div class="panel-content main-panel-content">
											<div class="row m-b-5">
												<div class="col-sm-12">
														<div class="row">

															<table class="table table-alt" id="districtmandalandvillageWiseCafcounttilldateTable">
																<thead>
																	<tr class="titleRow"
																		style="background-color: rgb(242, 242, 242);">
																		<th>S.no</th>
																		<th>Village</th>
																		<th>Mandal</th>
																		<th>district</th>
																		<th>caf count</th>
																	</tr>


																</thead>
																<tbody>
																	<c:forEach items="${districtWiseCafList}"
																		var="districtWiseCafList" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td>
																			
																			<td><c:out
																					value="${districtWiseCafList.village}"></c:out></td>
																			<td><c:out value="${districtWiseCafList.mandal}"></c:out></td>
																			<td><c:out
																					value="${districtWiseCafList.district}"></c:out></td>
																			<td><c:out
																					value="${districtWiseCafList.cafCount}"></c:out></td>
																		
																		</tr>
																	</c:forEach>
																</tbody>

															</table>
															<div class="form-group">
																<a
																	href="
																					<c:url value="./downloaddistMandalvillageWiseCafCountTilldate">
																					</c:url>">
																	<INPUT TYPE="SUBMIT" VALUE="Download"
																	class="btn btn-success">
																</a>

															</div>
														</div>

												</div>
												<input type="hidden"name="ofdate" id="ofdate" value="">
												<input type="hidden" name="hiddentodate" id="hiddentodate" value="">
												<input type="hidden"name="hiddenfromdate" id="hiddenfromdate" value="">
												
												<div class="form-group">
													<a
														href="
															<c:url value="distMandalvillageWiseCafCount">																
															</c:url>">
															<INPUT	TYPE="SUBMIT" VALUE="Back" class="btn btn-success">
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



				<!-- END FORM -->

			</div>

			<!-- END MAIN PANEL CONTENT -->
		</div>
		<!-- END MAIN PANEL -->
	</div>
	<!-- HERE COMES YOUR CONTENT -->
</div>
<!-- END MAIN ROW -->
<div class="clear">
</div>
<!-- END PAGE CONTENT -->

