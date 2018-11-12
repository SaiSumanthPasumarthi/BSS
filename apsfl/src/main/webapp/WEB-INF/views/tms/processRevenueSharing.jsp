<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="./resources/css/monthAndYear.css" rel="stylesheet">
<script>
$(document).ready(function() {
	$('#revenueShareTenants').DataTable();
	   $('#revDate').datepicker({
	     changeMonth: true,
	     changeYear: true,
	     dateFormat: 'MM yy',
	     maxDate: 0,
	       
	     onClose: function() {
	        var iMonth = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	        var iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	        $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
	     },
	       
	     beforeShow: function() {
	       if ((selDate = $(this).val()).length > 0) 
	       {
	          iYear = selDate.substring(selDate.length - 4, selDate.length);
	          iMonth = jQuery.inArray(selDate.substring(0, selDate.length - 5), $(this).datepicker('option', 'monthNames'));
	          $(this).datepicker('option', 'defaultDate', new Date(iYear, iMonth, 1));
	           $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
	       }
	    }
	  });
	});
</script>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2> Revenue<strong> Sharing</strong></h2>
			<c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Revenue Sharing</li>
				</ol>
			</div>
			</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form action="./executeRevSharing" method="post" >
							<div class="row">
								<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Date<span style="color: red;">*</span></label> 
														<input type="text" name="revDate" id="revDate" class="form-control form-white"  placeholder="Select Date..." 
														 required="required" readonly="readonly">
													</div>
								</div>
								<div class="col-sm-3">
								<button class="btn btn-embossed btn-primary" type="submit" id="processRevenueSharingButton" >Submit</button>
								</div>
							</div>
						</form>
						<c:if test="${not empty list}">
						<div class="row">
							<table class="table table-alt" id ="revenueShareTenants">
								<thead>
									<tr>
										<th>Tenant Code</th>
										<th>Tenant Name</th>
										<th>For Month</th>
										<th>Share Amount</th>
										<th>Tax Amount</th>
										<th>Entertainment Tax Amount</th>
										<th>Gross Amount</th>
										<th>IFSC Code</th>
										<th>Account Number</th>
										<th>ACH File Status</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${list}" var = "teantDetails">
									<tr>
										<td>${teantDetails.tenantcode}</td>
										<td>${teantDetails.tenantName}</td>
										<td>${teantDetails.formon}</td>
										<td>${teantDetails.shareamt}</td>
										<td>${teantDetails.taxamt}</td>
										<td>${teantDetails.enttax}</td>
										<td>${teantDetails.grosspaidamt}</td>
										<td>${teantDetails.ifscCode}</td>
										<td>${teantDetails.accountNo}</td>
										<c:choose>
										<c:when test="${teantDetails.status eq '1'}">
										<td>File Is Not Created</td>
										</c:when>
										<c:otherwise>
										<td>File Is Created</td>
										</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="row">
							<form  action="./achFileGenerate" method="post">
								<input type="hidden" name="revDate" value="${revDate}">
								<button class="btn btn-embossed btn-primary" type="submit"> Generate File For ACH</button>
							</form>
						</div>
						</c:if>
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
	</div>
	<!-- END PAGE CONTENT -->
</div>