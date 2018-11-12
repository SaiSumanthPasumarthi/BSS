<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.model-div-size {
	max-height: 500px;
    overflow-y: scroll;
}

.ScrollStyle {
	max-height: 300px;
	overflow-y: auto;
}
</style>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>
						Package<strong> Agreement View</strong>
					</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li class="active">Package Agreement View</li>
						</ol>
					</div>
				</div>
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
							
								<input type="hidden" id="prodCodeHidden" />
								<input type="hidden" id="tenantCodeHidden" />
								<input type="hidden" id="effectiveFromHidden" />
								<input type="hidden" id="templCodeHidden" />

									<!-- END ROW INNER-->
									<div class="row divHideAndShow " id="viewproductsListDivId">
										<div class="col-sm-12">
											<table class="table table-alt" id="ViewProdAgremntTable">
												<thead>
													<tr>
														<th>Agreement ID</th>
														<th hidden="hidden">Template Code</th>
														<th hidden="hidden">Tenant Code</th>
														<th hidden="hidden">Product Code</th>
														<th>Package Name</th>
														<th>Package Type</th>
														<th>LMO Name</th>
														<th hidden="hidden">Tenant Name</th>
														<th>Effective From</th>
														<th hidden="hidden">Created By</th>
														<th>Lock In Period</th>
														
														<th>Package Charge</th>
														<th>Tax Component</th>
														<th>Total Charge</th>
														
														<th hidden="hidden">Duration Days</th>
														<th>Customer Type</th>
														<th>Select</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${allpkgagreeList}" var="agremntpkg" varStatus="rowCount">
														<tr>
															<td>${agremntpkg.agreementId}</td>
															<td hidden="hidden" class="tmplCode">${agremntpkg.rstmlCode}</td>
															<td hidden="hidden" class="tenantCode">${agremntpkg.tenantCode}</td>
															<td hidden="hidden" class ="prodCode">${agremntpkg.prodCode}</td>
															<td class ="prodName">${agremntpkg.prodName}</td>
															<td>${agremntpkg.prodtype}</td>
															<td>${agremntpkg.lmoName}</td>
															<td hidden="hidden">${agremntpkg.tenantName}</td>
															<td class ="effectiveFrom">${agremntpkg.effetiveFrom}</td>
															<td hidden="hidden">${agremntpkg.createdBy}</td>
															<td>${agremntpkg.lockindays}</td>
															
																<td>${agremntpkg.prodCharge}</td>
																<td>${agremntpkg.taxCompenent}</td>
																<td>${agremntpkg.totalCharge}</td><!-- Total charge -->
																<td hidden="hidden">${agremntpkg.durationDays}</td>
															    <td>${agremntpkg.custtypelov}</td>
															<td><input type="radio"  class="radio" name=radio1></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<!-- <table  id="selectedProductTableHidden" hidden="hidden"></table> -->
										</div>
									</div>
									
								<div class="row divHideAndShow">
								<div class="col-lg-12">
									<div class="col-sm-6 disable-search " >
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Package Service Information</h3>
											</div>
											<div id="productinfo" style="max-height: 375px" class="ScrollStyle">
												<table class="table table-alt " id="viewprodServicelistTable" >
													<thead>
														<tr>
															<th>Package Name</th>
															<th>Service Name</th>
															<th>Service Code</th>
															<th>Charge Type</th>
															<th> Amount</th>
															<!-- <th>GL-Name</th>
															<th>GL-Code</th> -->
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
							

									<div class="col-sm-6 disable-search " >
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Revenue sharing details</h3>
											</div>
											<div class="panel-content col-sm-12" id="rsPopUpDiv1">
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