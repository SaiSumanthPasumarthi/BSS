$(document).ready(function(){
	$(".hide1").hide();
	$(".hide2").hide();
	$(".cpeHide").hide();
	$(".numeric").numeric({ 
		negative : false,
		decimalPlaces : 2 
	});
	
	$('.splclass').keyup(function(){
		var yourInput = $(this).val();
		re = /[`~!@#$%^&*()|+=?;:'",.<>\{\}\[\]\\\/]/gi;
		var isSplChar = re.test(yourInput);
		if(isSplChar)
		{
			var no_spl_char = yourInput.replace(/[`~!@#$%^&*()|+=?;:'",.<>\{\}\[\]\\\/]/gi, '');
			$(this).val(no_spl_char);
		}
	});

	
	jQuery.validator.addClassRules('templateClass', { 
        required: true
    });
	$('.templateClass').validate();
	
	jQuery.validator.addClassRules('chargeTypeClass', { 
        required: true
    });
	//$('.chargeTypeClass').validate();
	
	jQuery.validator.addClassRules('tempClass', { 
        required: true
    });
	$('.tempClass').validate();
	
	jQuery.validator.addClassRules('tClass', { 
        required: true
    });
	$('.tClass').validate();
	
	$("#newbtn_id").click(function(){
		if($("#id_tempNameMast").val() != "")
			$("#id_tempNameMast").val("");
		$(".templateClass").attr("disabled", false);
		$(".templateClass").val("");
		$("#savebtn_id").show();
		$("#id_region").empty();
		$("#id_table > tbody").html("");
		$(".hide2").hide();
		$(".hide1").show();
		$("#id_msg").html("");
		$('#id_cpeCharges ')[0].sumo.unSelectAll();
		$(".cpeHide").hide();
	});
	

	$('#id_cpeCharges ').SumoSelect({
		 placeholder: "Please Select CPE Charges",
		 triggerChangeCombined : false,
	 });
	
	$('#id_templType').on('change', function() {
	   if($("#id_templType :selected").text() == 'CPE')
		   $(".cpeHide").show();	
	   else{
		   $(".cpeHide").hide();
		   $('#id_cpeCharges ')[0].sumo.unSelectAll();
	   }
		  
	  
	});
	
	$("#savebtn_id").click(function(){
		if($(".templateClass").valid())
		{
			var flag = true;
			
			if($("#id_templType :selected").text() == "CPE"){
				if($(".chargeTypeClass").valid())
					flag = true;
				else
					flag = false;
			}
			
			if(flag){
			var tenant ="";
			var templateMasterObj = {
					"rstmplCode":$("#id_tempCode").val(),
					"rstmplName":$("#id_tempName").val(),
					"partnerCnt":$("#id_noOfPartners").val(),
					"templType":$("#id_templType :selected").text(),
					"chargeCodes":$('#id_cpeCharges').val()
			};
			var templateMaster = JSON.stringify(templateMasterObj);
			ajaxindicatorstart('Please Wait...');
			$.ajax({ 
			     type: 'POST', 
			     url: 'saveTemplateMaster', 
			     data: templateMaster,
			     contentType: 'application/json', 
			     success: function(result){
			    	$(window).scrollTop(0);
			    	if(result == "success")
			    	{
			    	 $.ajax({ 
					     type: 'GET', 
					     url:  'getRevenueSharingLovs?tempCode='+$("#id_tempCode").val(), 
					     success: function(result){
					    	 $.each(result, function(key, value){
					    		 var temp = [];
					    		 	if(key == 'regionLovs')
					    			{
										$("#id_region").empty();
							    	 	$("#id_region").append('<option value="">-Select-</option>');
										$.each(value, function(key1, value1){
											temp.push({v:value1, k:key1});
							    		});
										temp.sort(function(a,b){
											   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
											    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
											      return 0;
										});
										$.each(temp, function(key, obj){
											$("#id_region").append('<option value='+obj.k+'>'+obj.v+'</option>');
										});
					    			}
					    			else if(key == 'tenantTypeLovs')
					    			{
					    				tenant ='<select class="form-control form-white tenClass"><option value="">-Select-</option>';
					    				$.each(value, function(key1, value1){
											temp.push({v:value1, k:key1});
							    		});
										temp.sort(function(a,b){
											   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
											    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
											      return 0;
										});
										$.each(temp, function(key, obj){
											tenant = tenant+'<option value='+obj.k+'>'+obj.v+'</option>';
										});
					    				tenant = tenant+'</select>';
					    			}
					    			else if(key == 'templatesLov')
					    			{
					    				$("#id_tempNameMast").empty();
							    	 	$("#id_tempNameMast").append('<option value="">-Select-</option>');
										$.each(value, function(key1, value1){
											temp.push({v:value1, k:key1});
							    		});
										temp.sort(function(a,b){
											   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
											    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
											      return 0;
										});
										$.each(temp, function(key, obj) {
											$("#id_tempNameMast").append('<option value='+obj.k+'>'+obj.v+'</option>');
										});
					    			}
							 });
					    	 var tab ="";
					    	 for(var i=1; i<=$("#id_noOfPartners").val();i++)
					    	 {
									tab = tab + '<tr>'+
												'<td><label>'+i+'</label></td>'+
												'<td>'+tenant+'</td>'+						
					            				'<td>'+
													'<input type="text" name="" class="form-control form-white numeric tenantClass" maxlength="5" placeholder="Enter Value">'+
												'</td>'+                                                          
					          				'</tr>';
					    	 }
								$('#id_table').find('tbody:last').append(tab);
								$(".hide2").show();
								$(".numeric").numeric({ 
									negative : false,
									decimalPlaces : 2 
								});
								$(".templateClass").attr("disabled", "disabled");
								$("#savebtn_id").hide();
								ajaxindicatorstop();
								//alert("Tempalate Saved Successfully.");
								$("#id_msg").html('<font color="green">Template Saved Successfully.</font>');
					     }
					  });
			    	}
			    	else if(result == "DuplicateEntry")
			    	{
			    		ajaxindicatorstop();
			    		$("#id_msg").html('<font color="red">Template Code/Name Already Exists.</font>');
			    	}
			    	else	
			    	{
			    		ajaxindicatorstop();
			    		//alert("Falied to Save Tempalate.");
			    		$("#id_msg").html('<font color="red">Falied to Save Tempalate.</font>');
			    	}
			     }
			});
		  }	
		}
	});
	
	$("#id_revsubmit").on("click", function(){
		if($(".tempClass").valid())
		{
			var tenantsPerc = [];
			var isvalid = true;
			var totalPerc = 0;
			$("table#id_table > tbody > tr").each(function(){
				if($(this).find('td:eq(1)').find('select option:selected').val()=="" || $(this).find('td:eq(2) input[type=text]').val()=="")
				{
					alert("Please enter value at row: "+$(this).find('td:eq(0)').text());
					isvalid = false;
					return false;
				}
				totalPerc = +totalPerc + +$(this).find('td:eq(2) input[type=text]').val();
				var tenantsPercObj ={
						"tenantslno":$(this).find('td:eq(0)').text(),
						"tenanttype":$(this).find('td:eq(1)').find('select option:selected').val(),
						"revshareperc":$(this).find('td:eq(2) input[type=text]').val()
				};
				tenantsPerc.push(tenantsPercObj);
			});
			if(isvalid == true && totalPerc != 100.00)
			{
				alert("Total Revenue Share must be equal to 100.00%");
				isvalid = false;
			}
			if(isvalid == true)
			{
				var rsTemplate ={
					"rstmplCode":$("#id_tempCode").val(),
					"regioncode":$("#id_region").val(),
					"rsTemplateList":tenantsPerc
				};
			
				var rsTemplateJSON = JSON.stringify(rsTemplate); 
				console.log(rsTemplateJSON);
				ajaxindicatorstart('Please Wait...');
				$.ajax({ 
					type: 'POST', 
					url: 'saveTemplateDetails', 
					data: rsTemplateJSON,
					contentType: 'application/json', 
					success: function(result){
						$(".tenantClass").val("");
						$(".tenClass").attr("disabled", true);
						ajaxindicatorstop();
						$(window).scrollTop(0);
						if(result == "success")
							//alert("Tempalate Details Saved Successfully.");
							$("#id_msg").html('<font color="green">Tempalate Details Saved Successfully.</font>');
						else
							//alert("Falied to Save Tempalate Details.");
							$("#id_msg").html('<font color="red">Falied to Save Tempalate Details.</font>');
					}
				});
			}
		}
	});
	
	$("#updatebtn_id").on("click", function(){
		$(".hide1").hide();
		if($(".tClass").valid())
		{
			$("#id_msg").html("");
			ajaxindicatorstart('Please Wait...');
			$.ajax({ 
			     type: 'GET', 
			     url:  'getTemplateValues?tempCode='+$("#id_tempNameMast").val(), 
			     success: function(rs){
			    	 $(".hide1").show();
			    	 $("#id_tempCode").val(rs.rstmplCode);
					 $("#id_tempName").val(rs.rstmplName); 
					 $("#id_noOfPartners").val(rs.partnerCnt);
					 $("#id_templType").val(rs.templType);
					 $.ajax({ 
					     type: 'GET', 
					     url:  'getRevenueSharingLovs?tempCode='+$("#id_tempNameMast").val(), 
					     success: function(result){
					    	 var val = "";
					    	 $.each(result, function(key, value){
					    		 	var temp = [];
					    		 	if(key == 'regionLovs')
					    			{
										$("#id_region").empty();
							    	 	$("#id_region").append('<option value="">-Select-</option>');
										$.each(value, function(key1, value1){
											temp.push({v:value1, k:key1});
							    		});
										temp.sort(function(a,b){
											   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
											    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
											      return 0;
										});
										$.each(temp, function(key, obj){
											$("#id_region").append('<option value='+obj.k+'>'+obj.v+'</option>');
										});
					    			}
					    			else if(key == 'tenantTypeLovs')
					    			{
					    				tenant ='<select class="form-control form-white tenClass"><option value="">-Select-</option>';
					    				$.each(value, function(key1, value1){
											temp.push({v:value1, k:key1});
							    		});
										temp.sort(function(a,b){
											   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
											    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
											      return 0;
										});
										$.each(temp, function(key, obj){
											tenant = tenant+'<option value='+obj.k+'>'+obj.v+'</option>';
										});
					    				tenant = tenant+'</select>';
					    			}
					    			else if(key == 'templatesLov')
					    			{
					    				$("#id_tempNameMast").empty();
							    	 	$("#id_tempNameMast").append('<option value="">-Select-</option>');
										$.each(value, function(key1, value1){
											temp.push({v:value1, k:key1});
							    		});
										temp.sort(function(a,b){
											   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
											    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
											      return 0;
										});
										$.each(temp, function(key, obj) {
											$("#id_tempNameMast").append('<option value='+obj.k+'>'+obj.v+'</option>');
										});
					    			}
					    			else if(key == 'tenantTypeVals')
					    			{
					    				val = value;
					    			}
									
							 });
					    	 var tab ="";
					    	 for(var i=1; i<=$("#id_noOfPartners").val();i++)
					    	 {
									tab = tab + '<tr>'+
												'<td><label>'+i+'</label></td>'+
												'<td>'+tenant+'</td>'+						
					            				'<td>'+
													'<input type="text" name="" class="form-control form-white numeric tenantClass" maxlength="5" placeholder="Enter Value">'+
												'</td>'+                                                          
					          				'</tr>';
					    	 }
					    	 	$("#id_table > tbody").html("");
								$('#id_table').find('tbody:last').append(tab);
								var i = 0;
								$.each(val, function(k, v){
									$('#id_table > tbody > tr:eq('+i+')').find('td:eq(1) select').val(v);
									$('#id_table > tbody > tr:eq('+i+')').find('td:eq(1) select').attr("disabled", true);
									i++;
								});
								$(".hide2").show();
								$(".numeric").numeric({ 
									negative : false,
									decimalPlaces : 2 
								});
								$(".templateClass").attr("disabled", "disabled");
								$("#savebtn_id").hide();
								ajaxindicatorstop();
								
								
					     }
					  });
			     }
			});
		}
	});
	
	$("#id_region").change(function(){
		if($(this).val()!="")
		{
			$("#id_msg").html("");
			ajaxindicatorstart('Please Wait...');
			$.ajax({ 
				type: 'GET', 
				url:  'getRegTempPercList?tempCode='+$("#id_tempCode").val()+'&region='+$(this).val(), 
				success: function(result){
					$.each(result, function(k, v){
						//$('#id_table > tbody > tr:eq('+k+')').find('td:eq(1) select').val(v.tenanttype);
						$('#id_table > tbody > tr:eq('+k+')').find('td:eq(2) input[type=text]').val(v.revshareperc);
					});
					if(result.length == 0)
					{
						$("table#id_table > tbody > tr").each(function(){
							//$(this).find('td:eq(1) select').attr("disabled", false);
							//$(this).find('td:eq(1) select').val("");
							$(this).find('td:eq(2) input[type=text]').val("");
						});
						$("#id_revsubmit").show();
					}
					else
						$("#id_revsubmit").hide();
					ajaxindicatorstop();
				}
			});
		}
	});
	
});