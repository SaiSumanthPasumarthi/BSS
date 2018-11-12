<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
table.dataTable tbody td {
	word-break: break-word;
	vertical-align: top;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#individualCafFPActivation').dataTable({
		"bProcessing" : false,
		"bServerSide" : true,
		"sort" : "position",
		"sAjaxSource" : "individualCafFPActivationPagination",
		"aoColumnDefs" : [
		                  {"sWidth": "10%" , "aTargets": [ 0 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "21%" , "aTargets": [ 1 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "10%" , "aTargets": [ 2 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "8%" ,"aTargets": [ 3 ] ,  "sClass" : "table-bordered"}, 
		                  {"sWidth": "15%" , "aTargets": [ 4 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "12%" , "aTargets": [ 5 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "11%" , "aTargets": [ 6 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "10%" , "aTargets": [ 7 ] , "sClass" : "table-bordered", sClass : "subcriberCode" },
		                  {"sWidth": "4%" , "aTargets": [ 8 ] , "sClass" : "table-bordered", sClass : "FPCheckbox", orderable: false},
		            	],
		"aoColumns" : [
					{"mData" : "cafNo"},
					{"mData": "",
		                 "render" : function(data, type, full, meta){
		                    return "<td>"+full.custName+" "+full.lName+"</td>";
		                 }
				    },
				    {"mData" : "aadharNo" },
				    {"mData" : "cafDate" },
					{"mData" : "cpePlace"},
					{"mData" : "stbSlno"},
					{"mData" : "stbMacaddr"},
			        {"mData" : "nwsubsCode"},
					{"mData" : "",
						"render" : function(data, type,full, meta) {
								var tdData = '';
								if(selectedFPSubsCodes .length === 0) {
									tdData = '<td><input type ="checkbox" class ="FPCheckbox"/></td>';
									} else {
										var count = 0;
										$.each(selectedFPSubsCodes, function(index, value) { 
								    		if(value === full.nwsubsCode) {
									    		count++;
								    		}
								    	});
								    	if(count > 0){
											tdData = '<td><input type ="checkbox" class ="FPCheckbox" checked="checked" /></td>';
							    		} else {
							    			tdData = '<td><input type ="checkbox" class ="FPCheckbox"/></td>';
								    	}
									}
							return tdData;
						}
					}
				]
			});
		});
</script>

<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>Individual<strong> Command Types</strong></h2>
			<label id="fingerPrintId" style="text-align: center; color: green;"></label>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Individual Command Types</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row m-b-10">
							<div class="col-sm-12" >
								<table class="table table-alt individualCafFPActivationTr" id="individualCafFPActivation" >
									<thead>
										<tr>
											<th width="10%">Caf No</th>
											<th width="21%">Customer Name</th>
											<th width="10%">Aadhar No</th>
											<th width="8%">CafDate</th>
											<th width="15%">Location</th>
											<th width="12%">IPTV Srl No</th>
											<th width="11%">IPTV Macaddress</th>
											<th width="10%">SubcriberCode</th>
											<th width="4%"></th>
										</tr>
									</thead>
								</table>
								<table class="individualCafFPActivationHidden" hidden="hidden"></table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<div class="panel-header bg-light">
										<h3>Command Types</h3>
									</div>
									<div class="panel-content">
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
													<label class="control-label">FingerPrint Types</label>
													<div class="clear"></div>
													<div class="pull-left" style="padding-left: 0;">
														<input type="radio" class="form-white" id="fingerPrint" name="fingerPrint" checked value="F">&nbsp;&nbsp;Finger Print
													</div>
													<div class="pull-left">&nbsp;&nbsp;&nbsp;
														<input type="radio" class="form-white" id="fingerPrint1" name="fingerPrint" value="O">&nbsp;&nbsp;OSD
													</div>
													<div class="col-sm-4" >
														<input type="radio" class="form-white" id="fingerPrint2" name="fingerPrint" value="S">&nbsp;&nbsp;Scroll Text
													</div>
													<div class="clear"></div>
												</div>
											</div>
										</div>
										<div id = "fingerPrintDiv">
										<div class = "row"> 
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Position<span style="color: red;">*</span></label> 
													<input type="text" name="" id="fpPosition" class="form-control form-white"  value= "400,800">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontType<span style="color: red;">*</span></label> 
													<input type="text" name="" id="fpFontType" class="form-control form-white" value = "Times Roman">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontSize<span style="color: red;">*</span></label> 
													<input type="text" name="" id="fpFontSize" class="form-control form-white" value = "36">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontColor<span style="color: red;">*</span></label>
													<input type="text" name="" id="fpFontColor" class="form-control form-white" value = "yellow">
												</div>
											</div>
											<div class="clear"></div>
										</div>
										<div class = "row"> 
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Duration<span style="color: red;">*</span></label> 
													<input type="text" name="" id="fpDuration" class="form-control form-white"  value= "20">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FingerPrintType<span style="color: red;">*</span></label>
													<div class="option-group">
														<select name="" id="fpfingerPrintType" class="form-control form-white" <c:if test="${custType == 'ENTERPRISE' || not empty customer}">disabled</c:if>>
												            <option value="Static" >Static </option>
												            <option value="Random" >Random</option> 
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Channel<span style="color: red;">*</span></label> 
													<input type="text" name="" id="fpChannel" class="form-control form-white" value = "0">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">BgColor<span style="color: red;">*</span></label>
													<input type="text" name="" id="fpBgColor" class="form-control form-white" value = "white">
												</div>
											</div>
											<div class="clear"></div>
										</div>
										</div>
										<div id = "osdDiv">
										<div class = "row"> 
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Position<span style="color: red;">*</span></label> 
													<input type="text" name="" id="osdPosition" class="form-control form-white"  value= "400,800">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontType<span style="color: red;">*</span></label> 
													<input type="text" name="" id="osdFontType" class="form-control form-white" value = "Times Roman">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontSize<span style="color: red;">*</span></label> 
													<input type="text" name="" id="osdFontSize" class="form-control form-white" value = "36">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontColor<span style="color: red;">*</span></label>
													<input type="text" name="" id="osdFontColor" class="form-control form-white" value = "yellow">
												</div>
											</div>
											<div class="clear"></div>
										</div>
										<div class = "row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">BgColor<span style="color: red;">*</span></label> 
													<input type="text" name="" id="osdBgColor" class="form-control form-white"  value= "red">
												</div>
											</div> 
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Duration<span style="color: red;">*</span></label> 
													<input type="text" name="" id="osdDuration" class="form-control form-white"  value= "20">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">message<span style="color: red;">*</span></label>
													<textarea name="" id="osdMessage" maxlength = "150" class="form-control form-white" >Test Message</textarea> 
													<!-- <input type="text" name="" id="osdMessage" maxlength = "150" class="form-control form-white" value = "Test Message"> -->
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">UserCanCloseMessage<span style="color: red;">*</span></label>
													<div class="option-group">
														<select name="" id="osdUserCanCloseMessage" class="form-control form-white" >
												            <option value="true" >true </option>
												            <option value="false" >false</option> 
														</select>
													</div>
												</div>
											</div>
											<div class="clear"></div>
										</div>
										
										<!-- Added for Multiple Subscriber codes -->
										<div class = "row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">SubscriberCode<!-- <span style="color: red;">*</span> --></label> 
													<!-- <input type="text" name="" id="osdSubCode" class="form-control form-white"  placeholder= "Subscriber Codes"> -->
													<textarea name="" id="osdSubCode" class="form-control form-white" placeholder= "Subscriber Codes"></textarea>
												</div>
											</div> 
											<div class="clear"></div>
										</div>
										
										
										</div>
										<div id = "stDiv">
										<div class = "row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">message<span style="color: red;">*</span></label> 
													<textarea name="" id="stMessage" maxlength = "900" class="form-control form-white" >Hello XYZ</textarea>
													<!-- <input type="text" name="" id="stMessage" maxlength = "900" class="form-control form-white" value = "Hello XYZ"> -->
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Duration<span style="color: red;">*</span></label> 
													<input type="text" name="" id="stDuration" class="form-control form-white"  value= "56">
												</div>
											</div>
											<div class="clear"></div>
										</div>
										</div>
								</div>
							</div>
						</div>
					</div>
						<div class="pull-right">
							<a href ="./home"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>&nbsp;
							<button class="btn btn-embossed btn-primary" type="button" id="individualCafFPActivationButton" >Submit</button>
							<label id="individualCafFPActivationError" style="text-align: left;color: red;"></label>
						</div>
						<!-- END ROW INNER-->

						<div class="clear"></div>
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
