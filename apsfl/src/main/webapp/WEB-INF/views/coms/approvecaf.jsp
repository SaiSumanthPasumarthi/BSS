<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Approve Caf<strong> Information</strong>
			</h2>
			<c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
			<c:if test="${not empty msg}"> <center id="comsErrorMsg" ><font color='red' size="3">${msg}</font></center></c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Customer Information</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row m-b-5">
							<div class="col-sm-12">
								<table class="table table-dynamic table-alt" id="approveCafTable">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Caf No</th>
											<th>Products</th>
											<th>Services</th>
											<th>Product Charge</th>
											<th>Tax</th>
											<th>Total Charge</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty cafList}">
											<c:forEach items="${cafList}" var="caf" varStatus="rowNum">
												<tr>
													<td>${rowNum.count}</td>
													<td>${caf[0]}</td>
													<td class="products">${caf[1]}</td>
													<td class="services">${caf[2]}</td>
													<td>${caf[3]}</td>
													<td>${caf[4]}</td>
													<td>${caf[3] + caf[4]}</td>
													<!-- <td><input type ="checkbox" name = "approveCafStatus" class ="approveCafStatus" /></td> -->
													<td class="aCenter">
														<form action="<c:url value="./approveCafDetails"/>" method="post">
															<input type="hidden" name="cafNo" value="${caf[0]}" /> 
															<input type="hidden" name="prodCode" value="${caf[5]}" />
															<input type="hidden" name="srvcCode" value="${caf[6]}" /> 
															<input type="hidden" name="mspCode" value="${caf[7]}" /> 
															<input  type="submit" class="btn btn-primary" name="action" value="Approve" />
															<input type="submit" class="btn btn-danger" name="action" value="Reject" />
														</form>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
						<!-- END ROW INNER-->


					</div>
				</div>
				<!-- END ROW -->
				<!-- END FORM -->
			</div>
			<!-- END MAIN PANEL CONTENT -->
		</div>
		<!-- END MAIN PANEL -->
	</div>
	<!-- HERE COMES YOUR CONTENT -->
</div>
<!-- END MAIN ROW -->
<div class="clear"></div>
