<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>Pon Changed CAF Details</h2>
			<label id="error" style="text-align: center; color: green;"
				value="${message}"></label> <label id="error1"
				style="text-align: center; color: red;"></label>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Pon Changed CAF Details</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row">
							<div class="col-sm-12">
								<div class="row">
									<div class="col-sm-2">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
											<a href="ponChange">
												<button class="btn btn-embossed btn-primary" type="button"
													id="getCafDetails">
													<i class="fa fa-refresh"></i>Pon Change
												</button>
											</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table class="table table-alt modifiedCafsTable">
									<thead>
										<tr>
											<th>Caf Number</th>
											<th>LMO Code</th>
											<th>Olt Sr No</th>
											<th>Ip Address</th>
											<th>Port No</th>
											<th>Onu Id</th>
											<th>Device</th>
											<th>Provision Status</th>
										</tr>
									</thead>
								</table>
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
		<div class="clear"></div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END MAIN CONTENT -->

<script>
$(function() {
    $(".modifiedCafsTable").DataTable( {
        ajax: {
            "url": "modifiedCafsData",
            "dataSrc": ""
       		 },
        columns: [
            { "data": 'cafno' },
            { "data": 'lmocode' },
            { "data": 'olt_id' },
            { "data": 'oltipaddr' },
            { "data": 'olt_portid' },
            { "data": 'olt_onuid' },
            { "data": 'requests' },
            { "data": 'provisioningStatus' }
        ]
    } );
    
    $.ajax({url: "modifiedCafsData", success: function(data){
			console.log(data);
	}});
    
});
</script>