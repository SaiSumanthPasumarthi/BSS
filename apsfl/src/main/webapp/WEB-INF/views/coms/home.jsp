<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Home<strong> Page</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Home Page</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row m-b-5">
							<div class="col-sm-12">
									<c:if test="${not empty message && !fn:contains(message, 'Error-No phonenos available in the given STD area')}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
									<c:if test="${not empty message && fn:contains(message, 'Error-No phonenos available in the given STD area')}"> <center id="comsErrorMsg" ><font color='red' size="3">${message}</font></center></c:if>
								<%-- <table class="table table-alt" id="customerTable">
									<thead>
										<tr>
											<th width="6%">S.No</th>
											<th width="20%">Customer Name</th>
											<th width="12%">Aadhar No</th>
											<th width="10%">Caf No</th>
											<th width="28%">Address</th>
											<th width="10%">Mobile No</th>
											<th width="14%">Customer Type</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty customerList}">
											<c:forEach items="${customerList}" var="customer" varStatus="rowNum">
												<tr>
													<td>${rowNum.count}</td>
													<td>${customer[3]}${customer[6]}</td>
													<td>${customer[2]}</td>
													<td>${customer[0]}</td>
													<td style="word-break: break-all;">${customer[5]},${customer[7]}, ${customer[8]}, ${customer[9]}</td>
													<td>${customer[4]}</td>
													<td>${customer[1]}</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table> --%>
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