$(document).ready(function(){
	
	$('#submit_Button_Id_Port_Split').attr('disabled',true);
	
	$('#olt_SrlNo_Select_Id').on('change', function() {
		$('#submit_Button_Id_Port_Split').attr('disabled',true);
		 $('#olt_Port_div_Id').html("");
		$('#olt_L1_size_div_Id').html("");
		$('#olt_L1_Ids_div_Id').html("");
		$('#olt_L2_size_div_Id').html("");
		$('#olt_L3_size_div_Id').html("");
		$('#olt_L1_Slot_details_div_Id').html("");
		$('#olt_L3_Slot_details_div_Id').html("");
		  if(this.value == "" ){
			  $('#olt_Port_div_Id').html("");
		  }else{
			  ajaxindicatorstart('Please Wait...');
				$.ajax({ 
				     type: 'GET', 
				     url:  'getOltPortsByTenantCodeAndOltSrNo?oltSrNo='+this.value, 
				     success: function(result){ 
				    	 $('#olt_Port_div_Id').html("");
				    	 var html = '<lable><b>OLT Port</b></lable>'
			    		 			+'<select class="form-control form-white" id = "olt_Port_Select_Id" required="required" name ="oltportNo">'
									+'<option value="">--Select--</option>';
									
				    	 	$.each(result, function( key, value ) {
				    	 		html = html  +'<option value='+value+'>'+value+'</option>';
				    	 	});
				    	 	html = html  +'</select>';
				    	 	$('#olt_Port_div_Id').append(html);
				    		ajaxindicatorstop();
				     }
				  });
		  }
	});
	
	$(document).on('change','#olt_Port_Select_Id', function() {
		$('#submit_Button_Id_Port_Split').attr('disabled',true);
		$('#olt_L1_Ids_div_Id').html("");
		$('#olt_L2_size_div_Id').html("");
		 $('#olt_L3_size_div_Id').html("");
		 $('#olt_L1_Slot_details_div_Id').html("");
		 $('#olt_L3_Slot_details_div_Id').html("");
		var oltSerNo = $('#olt_SrlNo_Select_Id').val();
		var oltPortNo = this.value;
		  if(this.value == "" ){
			  $('#olt_L1_size_div_Id').html("");
		  }else{
			  ajaxindicatorstart('Please Wait...');
				$.ajax({ 
				     type: 'GET', 
				     url:  'getL1SplitterTypeByOltport?oltPortNo='+oltPortNo+'&oltSerNo='+oltSerNo, 
				     success: function(result){ 
				    	 $('#olt_L1_size_div_Id').html("");
				    	 $.each(result, function( key, value ) {
								if(key == "L1PortSize")
								{
									if(value==0){
										
									}
									if(value.length > 0){
										 var html = '<lable><b>No Of L1 Slots</b></lable>'
							    		 		+'<select class="form-control form-white" id = "L1_Port_Size_Select_Id" required="required">'
												+'<option value="">--Select--</option>';
												
							    	 	$.each(value, function( key, value ) {
							    	 		html = html  +'<option value='+value+'>'+value+'</option>';
							    	 	});
							    	 	html = html  +'</select>';
							    	 	$('#olt_L1_size_div_Id').append(html);
									}
								}
								if(key == "L1PortIds")
								{
									if(value.length > 0){
										 var html = '<lable><b>L2 Split L1 Slot No</b></lable>'
							    		 		+'<select class="form-control form-white" id = "L1_Port_Select_Id" required="required" name ="l1PortId">'
												+'<option value="">--Select--</option>';
												
							    	 	$.each(value, function( key, value ) {
							    	 		html = html  +'<option value='+value+'>'+value+'</option>';
							    	 	});
							    	 	html = html  +'</select>';
							    	 	$('#olt_L1_Ids_div_Id').append(html);
									}
								}
								if(key == "l1SlotDetails")
								{
									if(value != ""){
										 var html = '<lable><b>l1 Slot Split Details</b></lable>'
							    		 		+'<input type="text" class="form-control form-white" id="l1SlotDtails_Id"   disabled="disabled" value='+value+'>'
							    		 		+'<input type="text" name="l1SlotDtails"   hidden value='+value+'>';
												
							    	 	$('#olt_L1_Slot_details_div_Id').append(html);
							    	 	
							    	 	var arr = value.split(',');
							    	 	var size = arr.length;
							    	 	var html1 = '<lable><b>No Of L1 Slots</b></lable>'
						    		 		+'<input type="text"  class="form-control form-white"   disabled="disabled" value='+size+'>';
							    	 	$('#olt_L1_size_div_Id').append(html1);
									}
								}
								if(key == "l3SlotsDetails")
								{
									
									 var html = '<lable><b>l3 Slot Split Details</b></lable>'
						    		 		+'<input type="text" class="form-control form-white" id="l3SlotDtails_Id"   disabled="disabled" value='+value+'>'
						    		 		+'<input type="text" name="l3SlotDtails"   hidden value='+value+'>';
											
						    	 	$('#olt_L3_Slot_details_div_Id').append(html);
									
								}
				    	 	});
				    		ajaxindicatorstop();
				     }
				  });
		  }
	});
	
	
	$(document).on('change','#L1_Port_Size_Select_Id', function() {
		$('#submit_Button_Id_Port_Split').attr('disabled',true);
		$('#olt_L2_size_div_Id').html("");
		 $('#olt_L3_size_div_Id').html("");
		 $('#olt_L1_Slot_details_div_Id').html("");
		var oltSerNo = $('#olt_SrlNo_Select_Id').val();
		var oltPortNo = $('#olt_Port_Select_Id').val(); 
		var l1PortSize = this.value;
		var value = "";
		  if(this.value == "" ){
			  $('#olt_L1_Ids_div_Id').html("");
		  }else{
			  ajaxindicatorstart('Please Wait...');
			  $('#olt_L1_Ids_div_Id').html("");
			    	 	
				    var html = '<lable><b>L2 Split L1 Slot No</b></lable>'
			    		 		+'<select class="form-control form-white" id = "L1_Port_Select_Id" required="required" name ="l1PortId">'
								+'<option value="">--Select--</option>';
			    	 	for(i=1; i<= l1PortSize; i++){
			    	 		if(value == "")
			    	 			value = i;
			    	 		else
			    	 			value = value + ","+ i;
			    	 		html = html  +'<option value='+i+'>'+i+'</option>';
			    	 	}
			    		 html = html  +'</select>';
			    	 	 var html1 = '<lable><b>l1 Slot Split Details</b></lable>'
			    		 		+'<input type="text"  class="form-control form-white" id="l1SlotDtails_Id"   disabled="disabled" value='+value+'>'
			    		 		+'<input type="text" name="l1SlotDtails"   hidden value='+value+'>';
								
			    	 	$('#olt_L1_Ids_div_Id').append(html);
			    	 	$('#olt_L1_Slot_details_div_Id').append(html1);
				    	ajaxindicatorstop();
		  }
	});
	
	var maxSize = 0;
	$(document).on('change','#L1_Port_Select_Id', function() {
		var oltSerNo = $('#olt_SrlNo_Select_Id').val();
		var oltPortNo = $('#olt_Port_Select_Id').val(); 
	
		 maxSize = 0;
	//	 $('#olt_L3_size_div_Id').html("");
	//	 $('#olt_L3_Slot_details_div_Id').html("");
		  if(this.value == "" ){
			  $('#olt_L2_size_div_Id').html("");
		  }else{
			  ajaxindicatorstart('Please Wait...');
			  $('#olt_L2_size_div_Id').html("");
			  $('#olt_L3_size_div_Id').html("");
				    	 var html = '<lable><b>No Of L2 Slots</b></lable>'
			    		 		+'<select class="form-control form-white" id = "L2_Port_Size_Select_Id" required="required" name ="l2PortSize">'
								+'<option value="">--Select--</option>';
				    	 	
				    	 	var data = $('#l1SlotDtails_Id').val();
				    	 	var arr = data.split(',');
				    	 	var size = arr.length;
				    	 		size = 128 / size;
				    	 	for (i = 1; i < size; i++) {
								i = i * 2;
								html = html  +'<option value='+i+'>'+i+'</option>';
				    	 		maxSize = i;
								i = i - 1;
							}
				    	 	html = html  +'</select>';
			    	 	$('#olt_L2_size_div_Id').append(html);
				    	ajaxindicatorstop();
				    	
		  }
	});
	
	
	$(document).on('change','#L2_Port_Size_Select_Id', function() {
		$('#olt_L3_size_div_Id').html("");
		var temp=$('#l3SlotDtails_Id').val();
		//alert(temp);
		var value = $('#L2_Port_Size_Select_Id').val(); 
		  if(this.value == "" ){
			  $('#olt_L3_size_div_Id').html("");
		  }else{
			  var i  = maxSize / value ;
			  var html = '<lable><b>No Of L3 Slots </b></lable><input type="text" class="form-control form-white"   disabled="disabled" value='+i+'>';
			  $('#olt_L3_size_div_Id').append(html);
			 /* var html = '<lable><b>l3 Slot Split Details</b></lable>'
  		 		+'<textarea rows="4" cols="50" class="form-control form-white" id="l3SlotDtails_Id"   disabled="disabled" value='+temp+'>'+temp+'</textarea>'
  		 		+'<input type="text" name="l3SlotDtails"   hidden value='+temp+'>';
				$('#olt_L3_Slot_details_div_Id').append(html);*/
			  $('#submit_Button_Id_Port_Split').attr('disabled',false);
		  }
	});
});