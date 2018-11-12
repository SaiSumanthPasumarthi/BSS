<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>Pon Change</h2>
			<label id="error" style="text-align: center; color: green;"
				value="${message}"></label> <label id="error1"
				style="text-align: center; color: red;"></label>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Pon Change</li>
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
											<label class="control-label">Caf Number</label> <input
												type="text" name="cafNo" id="cafNo"
												class="form-control form-white " maxlength="10"
												placeholder="Please Enter" required>
										</div>
									</div>
									<div class="col-sm-2">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="button"
													id="getCafDetails">
													<i class="fa fa-search"></i>Search
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
							
						<div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<div class="panel-header bg-light">
										<h3>Caf Details</h3>
									</div>
									<div class="panel-content">
										<div class="row">
											<div class="col-sm-6">
											<table class="table table-striped" id="cafInformation"></table>
											<button class="btn btn-embossed btn-primary" type="button" style="display: none;"
													id="getIpDetails">
													Change OLT Details
												</button>
											</div>
											<div class="col-sm-6">
											<table class="table table-striped" id="ipInformation" style="display: none">
												<tr><td>Change Address</td><td><input type="checkbox"  id="changeAddress" class="form-control" /></tr>
												<tr class="addressLines"><td>District*</td><td>
														<select name="district" id="blDistrict" class="form-control form-white" >
															<option value="">--Select--</option>
															<c:forEach var="district" items="${districtList}">
																<c:choose>
																	<c:when test="${not empty multiActionVO.district && district.districtUid == multiActionVO.district}">
																		<option value="${district.districtUid}" selected>${district.districtName}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${district.districtUid}">${district.districtName}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
												</td></tr>
												<tr class="addressLines"><td>Mandal*</td><td>
														<select name="mandal" id="blMandal" class="form-control form-white">
																</select>
												</td></tr>	
												<tr class="addressLines"><td>Village*</td><td>
														<select name="village" id="blVillage" class="form-control form-white">
																</select>
												</td></tr>	
												<tr class="addressLines"><td>Address Line1*</td><td><input type="text" class="form-control" id="addrLine1"/></td></tr>
												<tr class="addressLines"><td>Address Line2</td><td><input type="text" class="form-control" id="addrLine2"/></td></tr>
												<tr class="addressLines"><td>Locality*</td><td><input type="text" class="form-control" id="locality"/></td></tr>
												<tr class="addressLines"><td>Area*</td><td><input type="text" class="form-control" id="area"/></td></tr>
												
																							
												
												<tr><td>Olt Serial No*</td><td><select class="form-control" id="serialNoSelect"></select></td></tr>
												<tr><td>Olt Port No*</td><td><select class="form-control" id="portNoSelect"></select></td></tr>
												<tr><td></td><td><div class="option-group">
												
												<div class = "row">
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Level-1 Slot<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="l1Slot" id="l1_Slot">
																	<option value="">--Select--</option>
																</select>
															</div> 
														</div>
													</div>
													<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Level-2 Slot<span style="color: red;">*</span></label> 
															<div class="option-group">
																<select name="l2Slot" id="l2_Slot">
																	<option value="">--Select--</option>
																</select>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Level-3 Slot<span style="color: red;">*</span></label> 
															<div class="option-group">
																<select name="l3Slot" id="l3_Slot">
																	<option value="">--Select--</option>
																</select>
														</div>
													</div>
												</div>
											</div>

												
												<button class="btn btn-embossed btn-primary" type="button"
													id="update">Update
												</button>
												</div></td></tr>
											</table>
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
		<div class="clear"></div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END MAIN CONTENT -->

<script>
	var globalLmoOlts ;
	var existingserialNo;
	var existingPortNo;
	var existingOltIp;
	var existingOnuId;
	var cafNo;
	var lmoCode;
  $("#getCafDetails").click(function(){
	  if($("#cafNo").val().length==0){
		  alert('Caf No cannot be empty');
	  }else{
	  cafNo = $("#cafNo").val();
	  $.ajax({url: "ponChangeGetCafDetails?cafNo="+$("#cafNo").val(), success: function(data){
		  if(data.length==0){
			  alert('No data found. Caf No might be wrong or Caf not assigned to current LMO');
		  }else{
		  var obj = jQuery.parseJSON(data);
		  lmoCode=obj.lmocode;
		  var table = '<tr><td>CAF No</td><td>'+obj.cafno+'</td><tr>';
		  table = table + '<tr><td>Customer Name</td><td>'+obj.custname+'</td><tr>';
		  table = table + '<tr><td>LMO Code</td><td>'+obj.lmocode+'</td><tr>';
		  table = table + '<tr><td>Customer Id</td><td>'+obj.custid+'</td><tr>';
		  table = table + '<tr><td>Pop Name</td><td>'+obj.pop_name+'</td><tr>';
		  table = table + '<tr><td>District Name</td><td>'+obj.districtname+'</td><tr>';
		  table = table + '<tr><td>Mandal Name</td><td>'+obj.mandalname+'</td><tr>';
		  table = table + '<tr><td>Olt Id</td><td>'+obj.olt_id+'</td><tr>';
		  table = table + '<tr><td>IP Address</td><td>'+obj.oltipaddr+'</td><tr>';
		  table = table + '<tr><td>Port Id</td><td>'+obj.olt_portid+'</td><tr>';
		  table = table + '<tr><td>Onu Id</td><td>'+obj.olt_onuid+'</td><tr>';
		  existingOltIp = obj.oltipaddr;
		  existingOnuId = obj.olt_onuid;
		  existingserialNo = obj.olt_id;
		  existingPortNo = obj.olt_portid;
		  $("#cafInformation").html(table);
		  $("#getIpDetails").show();
		  $("#ipInformation").hide();
		  }//inner else
	    }});
	  }//else
	});
  
  $("#getIpDetails").click(function(){
	  var serialNoOptions ='<option value="">--Select--</option>';
	  $.ajax({url: "ponChangeGetLmoOlts", success: function(data){
		  globalLmoOlts =  $.parseJSON(data);
		  $.each($.parseJSON(data), function(key,value){
			    console.log(value.pop_olt_ipaddress);
			    serialNoOptions = serialNoOptions +'<option value="'+value.pop_olt_serialno+'">'+value.pop_olt_serialno+'('+value.pop_oltlabelno+')</option>';
			});
		  $("#ipInformation").show();
		  $("#serialNoSelect").html(serialNoOptions);
	    }});
	});
  
  $('#serialNoSelect').on('change', function() {
	  var portOptions ='';
	  var selectedSrNo = this.value;
	  $.each(globalLmoOlts, function(key,value){
		  if(value.pop_olt_serialno == selectedSrNo){
			  var array = value.portno.split(",");
			  array.sort();
			  portOptions = portOptions +'<option value="">--Select--</option>';
			  $.each(array,function(i){
				  if(!(value.pop_olt_serialno == existingserialNo && array[i] == existingPortNo)){
				  	portOptions = portOptions +'<option value="'+array[i]+'">'+array[i]+'</option>';
				  }
				});
		  }
		});
	  $("#portNoSelect").html(portOptions);
	});
  
   $("#update").click(function(){
	  var newSerialNo = $("#serialNoSelect option:selected").val();
	  var newPort = $("#portNoSelect option:selected").val();
	  var addressLine1 = $("#addrLine1").val();
	  var addressLine2 = $("#addrLine2").val();
	  var locality = $("#locality").val();
	  var area = $("#area").val();
	  var village = $("#blVillage option:selected").val();
	  var mandal = $("#blMandal option:selected").val();
	  var district = $("#blDistrict option:selected").val();
	  var l1_Slot = $('#l1_Slot').val();
	  var l2_Slot = $('#l2_Slot').val();
	  var l3_Slot = $('#l3_Slot').val();
	  var l1L2L3Join=l1_Slot.concat("-").concat(l2_Slot).concat("-").concat(l3_Slot);
	  if(newSerialNo.length == 0 || newPort.length == 0){
		  alert('Olt Serial No and Olt Port No Cannot be emprty.');
	  }else if($('#changeAddress').is(":checked")){
		  if(addressLine1.length == 0 || locality.length == 0 || area.length == 0 || village.length == 0 || mandal.length == 0 ||  district.length == 0){
			  alert('Please fill all mandatory fields.');
		  }
		  else{
			  if($('#changeAddress').is(":checked")){
				   $.ajax({url: "updateCafAddress?cafNo="+cafNo+"&addressLine1="+addressLine1+"&addressLine2="+addressLine2+"&locality="+locality+"&area="+area+"&village="+village+"&mandal="+mandal+"&district="+district, success: function(data){
			  			console.log("address change msg "+data);
			  		}}); 
			  	}//if
			  $.ajax({url: "updatePonChange?cafNo="+cafNo+"&oltId="+newSerialNo+"&OltPortId="+newPort+"&oltIp="+existingOltIp+"&onuId="+existingOnuId+"&splitter="+l1L2L3Join+"&oldOltPort="+existingPortNo+"&oldOltSrlNo="+existingserialNo, success: function(data){
				  			alert(data);
				  			window.location.href = "viewModifiedCafs";
				  }});
			 
			  }//else
	  }
	  else{
		  if($('#changeAddress').is(":checked")){
			   $.ajax({url: "updateCafAddress?cafNo="+cafNo+"&addressLine1="+addressLine1+"&addressLine2="+addressLine2+"&locality="+locality+"&area="+area+"&village="+village+"&mandal="+mandal+"&district="+district, success: function(data){
		  			console.log("address change msg "+data);
		  		}}); 
		  	}//if
		  $.ajax({url: "updatePonChange?cafNo="+cafNo+"&oltId="+newSerialNo+"&OltPortId="+newPort+"&oltIp="+existingOltIp+"&onuId="+existingOnuId + "&splitter="+l1L2L3Join+"&oldOltPort="+existingPortNo+"&oldOltSrlNo="+existingserialNo, success: function(data){
			  			alert(data);
			  			window.location.href = "viewModifiedCafs";
			  }});
		 
		  }//else
	}); 


   $('#portNoSelect').change(function() {
	  
		var oltSrlNo = $('#serialNoSelect').val();
		var oltPort = $('#portNoSelect').val();
		var lmocode = lmoCode;
		
		if(oltSrlNo != "" && oltPort != "" && lmoCode != "") {
			$.ajax({
				type : "GET",
				url : "getOLTPortSplitterData",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&oltPort="+oltPort,
				success : function(response) {
					var $select = $('#l1_Slot');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response.level1SlotList, function(key,val) {              
		        	   $('<option>').val(val).text(val).appendTo($select);
					});
				}
			});
		}
	});


	/* Get OLT Port Level2 and Level3 Data By Level1 Slot*/
	$('#l1_Slot').change(function() {
		var oltSrlNo = $('#serialNoSelect').val();
		var oltPort = $('#portNoSelect').val();
		
		var l1_Slot = $('#l1_Slot').val();
	
		if(oltSrlNo != "" && oltPort != "" && lmoCode != "" && l1_Slot != "") {
			$.ajax({
				type : "GET",
				url : "getOLTPortSplitterData",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&oltPort="+oltPort+"&l1slot="+l1_Slot,
				success : function(response) {
					var $select = $('#l2_Slot');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response.level2SlotList, function(key,val) {              
		        	   $('<option>').val(val).text(val).appendTo($select);
					});
					
					
				}
			});
		}
	});
	
	
	//SG: only unused slots shall be there in L3
	$('#l2_Slot').change(function() {
		var oltSrlNo = $('#serialNoSelect').val();
		var oltPort = $('#portNoSelect').val();
		var l1_Slot = $('#l1_Slot').val();
		var l2_Slot = $('#l2_Slot').val();
		var l1L2Join="";
		
		
		if(oltSrlNo != "" && oltPort != "" && lmoCode != "" && l1_Slot != "" && l2_Slot!="") {
			l1L2Join=l1_Slot.concat("-").concat(l2_Slot);
			$.ajax({
				type : "GET",
				url : "getOLTL3PortSplitterData",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&oltPort="+oltPort+"&l1L2slot="+l1L2Join,
				success : function(response) {
					
					var $select1 = $('#l3_Slot');
					$select1.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select1);
					$.each(response.level3SlotList, function(key,val) {              
		        	   $('<option>').val(val).text(val).appendTo($select1);
					});
				}
			});
		}
	});
	
	
	
	
	
	
	/* Get OLT Port Level1 Data By OLT Srl Number*/
	$('#l3_Slot').change(function() {
		var oltSrlNo = $('#serialNoSelect').val();
		var oltPort = $('#portNoSelect').val();
		var l1_Slot = $('#l1_Slot').val();
		var l2_Slot = $('#l2_Slot').val();
		var l3_Slot = $('#l3_Slot').val();
		var portSlotValue = l1_Slot+"-"+l2_Slot+"-"+l3_Slot+",";
		if(oltSrlNo != "" && oltPort != "" && portSlotValue != "") {
			$.ajax({
				type : "GET",
				url : "checkSplitterValue",
				data : "&oltSrlNo="+ oltSrlNo+"&oltPort="+oltPort+"&portSlotValue="+portSlotValue,
				success : function(response) {
					if(response == "true") {
						$('#slotError').text('Slot is already assigned.');
						$('#l1_Slot').val('');
						$('#l2_Slot').val('');
						$('#l3_Slot').val('');
					} else if("false") {
						$('#slotError').text('');
					}
				}
			});
		}
	});
	
	
   
   $(document).ready(function(){
	   $('.addressLines').toggle();
	    $('#changeAddress').click(function(){
	        $('.addressLines').toggle();
	    });
	});
  </script>