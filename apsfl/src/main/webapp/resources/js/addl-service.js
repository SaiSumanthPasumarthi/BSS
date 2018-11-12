$(document).ready(function() {
	
	$('.srvcF').hide();
	$('#dialog').hide();
	$('.ftaCheckBoxClassDiv').hide();
	
	
	$('#corposeCallButtonId').on('click',function(){
		$('#corposeCallForm').submit();
	});
	
	$('.createSrvcs').hide();
	$(".hideClass").hide();
	$(".numeric").numeric();
	 $("#srvcFeatDivId").hide();
	var srvcFeatFlage = false;
	
	if($("#id_editStatus").val() == "viewOnly")
	{
		$('.srvcF').show();
		$(".hideClass").show();
		$(".hiddenClass").hide();
		$("#coreServListID").prop("disabled", true);
	}
	else if($("#id_editStatus").val() == "edit")
	{
		$(".hideClass").show();
		$(".hiddenClass").show();
		$("#coreServListID").prop("disabled", true);
		ajaxindicatorstart('Please Wait...');
		var tab = "";
		var pCode = "";
		var pLabel = "";
		var pActLabel = "";
		var pName = "";
		var pValue = "";
		var pType = "";
		var val ="";
		$.ajax({ 
		     type: 'GET', 
		     url: 'paramLst?coreSrvcCode='+$("#coreServListID").val()+'&srvcCode='+$("#addServCodeID").val(), 
		     contentType: 'application/json',
		     success: function(result){
		    	 ajaxindicatorstop();
		    	 $("#parametersTable").find('tbody:last').empty();
		    	 $.each(result, function(){
		    		 val = this.paramValue;
		    		 pCode = '<td>'+this.paramCode+'</td>';
		    		 pLabel ='<td>'+this.paramDefaultLabel+'</td>';
		    		 pName = '<td>'+this.paramName+'</td>';
		    		 if(this.paramType == "PROVISIONING")
		    		 	pValue = '<td>'+this.paramValue+'</td>';
		    		 else if(this.paramType == "CHARGEABLE")
		    		 {
			    	 	if(this.paramValueType =='LISTBOX')
			    	 	{
			    	 		var selParamValue = '<option value="">-Select-</option>';
			    	 		$.each(this.paramLovValues, function(key, value){
			    	 			if(val == key)
			    	 				selParamValue = selParamValue+'<option selected value="'+key+'">'+value+'</option>';
			    	 			else
			    	 				selParamValue = selParamValue+'<option value="'+key+'">'+value+'</option>';
			    	 		});
			    	 		pValue = '<td><input type="hidden" value="select"/><select class="SrvcParamClass">'+selParamValue+'</select></td>';
			    	 	}
			    	 	else
			    	 		pValue = '<td><input type="hidden" value="text"/><input type="text" class="SrvcParamClass" value="'+this.paramValue+'" maxlength="255"/></td>'; 
		    		 }
		    		 else if(this.paramType == "BOTH")
		    		 {
			    	 	if(this.paramValueType =='LISTBOX')
			    	 	{
			    	 		var selParamValue = '<option value="">-Select-</option>';
			    	 		$.each(this.paramLovValues, function(key, value){
			    	 			if(val == key)
			    	 				selParamValue = selParamValue+'<option selected value="'+key+'">'+value+'</option>';
			    	 			else
			    	 				selParamValue = selParamValue+'<option value="'+key+'">'+value+'</option>';
			    	 		});
			    	 		pValue = '<td><input type="hidden" value="select"/><select class="SrvcParamClass">'+selParamValue+'</select></td>';
			    	 	}
			    	 	else
			    	 		pValue = '<td><input type="hidden" value="text"/><input type="text" class="SrvcParamClass" value="'+this.paramValue+'" maxlength="255"/></td>'; 
		    		 }
		    		 pType = '<td>'+this.paramType+'</td>';
		    		 tab = pCode+pLabel+pName+pValue+pType;		    	
		    		 $('#parametersTable').find('tbody:last').append('<tr>'+tab+'</tr>');
		    	 });
		     }
		  });
	}
	
	if($("#id_viewStatus").val() == "view")
	{
		$("#addlSrvcsTable").dataTable();
		$(".hideClass").hide();
		$('.viewSrvcs').show();
		$('.createSrvcs').hide();
	}
	else
	{
		$('.viewSrvcs').hide();
		$('.createSrvcs').show();
	}
	
	jQuery.validator.addClassRules('addlSrvcClass', { 
        required: true
    });
	$('.addlSrvcClass').validate();
	
	$("#updateID").hide();
	$("#effectiveDate").datepicker({ 
		dateFormat: 'dd-mm-yy', 
		minDate : 0,
		onSelect: function(){
			$("#effectiveTo").datepicker( "option", "minDate", $("#effectiveDate").datepicker("getDate") );
		}
	});
	
	$("#effectiveTo").datepicker({ 
		dateFormat: 'dd-mm-yy', 
		minDate : 0
	});
	
	var date = new Date();
	var currentMonth = date.getMonth();
	var currentDate = date.getDate();
	var currentYear = date.getFullYear();
	$("#ChannelsEffctive_text_Id").datepicker({
	minDate: new Date(currentYear, currentMonth, currentDate),
	changeMonth: true,
	changeYear: true,
	});
	
	$("#editChannel_button_id").on("click", function(){
		var srvcFeatSelectedList = [];
		
		if($(".srvcFeatCheckBoxClass:checked").length > 0){
			$(".srvcFeatCheckBoxClass:checked").each(function() {
				srvcFeatSelectedList.push(this.value);
			});
			var channelsEffctive = $("#ChannelsEffctive_text_Id").val();
				$('#srvcFeaturesCodes').val(srvcFeatSelectedList);
				$('#channelsEffFrom_Id').val(channelsEffctive);
				$('#updateChannel_form_id').submit();
		}else{
			alert("Please Select Atleast One Channel");
		}
		
	});
	
	$("#coreServListID").on("change", function(){
		 $('.ftaCheckBoxClassDiv').hide();
		 $("#srvcFeatDivId").hide();
		 $("#srvcFeatContentIptvDivId").html("");
		 $("#srvcFeatContentVoipDivId").html("");
		 srvcFeatFlage = false;
		ajaxindicatorstart('Please Wait...');
		if($(this).val()=="")
		{
			$(".hideClass").hide();
		}
		else
		{
			$(".hideClass").show();
			$("#addServCodeID").val('');
			$("#addServNameID").val('');
			$("#apfID").val('');
			$("#mspID").val('');
			$("#lmoID").val('');
			$("#effectiveDate").val('');		
			$('#effectiveTo').val('');
			var tab = "";
			var pCode = "";
			var pLabel = "";
			var pActLabel = "";
			var pName = "";
			var pValue = "";
			var pType = "";
			var srvcFeatType = "";
			var val ="";
			$.ajax({ 
			     type: 'GET', 
			     url: 'paramList?coreServCode='+$(this).val(), 
			     contentType: 'application/json',
			     async : false,
			     success: function(result){
			    	 ajaxindicatorstop();
			    	 $("#parametersTable").find('tbody:last').empty();
			    	 $.each(result.addSrvcDTOList, function(){
			    		 val = this.paramValue;
			    		 pCode = '<td>'+this.paramCode+'</td>';
			    		 pLabel ='<td>'+this.paramDefaultLabel+'</td>';
			    		 pName = '<td>'+this.paramName+'</td>';
			    		 if(this.paramType == "PROVISIONING")
			    		 	pValue = '<td>'+this.paramValue+'</td>';
			    		 else if(this.paramType == "CHARGEABLE")
			    		 {
				    	 	if(this.paramValueType =='LISTBOX')
				    	 	{
				    	 		var selParamValue = '<option value="">-Select-</option>';
				    	 		$.each(this.paramLovValues, function(key, value){
				    	 			selParamValue = selParamValue+'<option value="'+key+'">'+value+'</option>';
				    	 		});
				    	 		pValue = '<td><input type="hidden" value="select"/><select class="SrvcParamClass">'+selParamValue+'</select></td>';
				    	 	}
				    	 	else
				    	 		pValue = '<td><input type="hidden" value="text"/><input type="text" class="SrvcParamClass" value="'+this.paramValue+'" maxlength="255"/></td>'; 
			    		 }
			    		 else if(this.paramType == "BOTH")
			    		 {
				    	 	if(this.paramValueType =='LISTBOX')
				    	 	{
				    	 		var selParamValue = '<option value="">-Select-</option>';
				    	 		$.each(this.paramLovValues, function(key, value){
				    	 			if(val == key)
				    	 				selParamValue = selParamValue+'<option selected value="'+key+'">'+value+'</option>';
				    	 			else
				    	 				selParamValue = selParamValue+'<option value="'+key+'">'+value+'</option>';
				    	 		});
				    	 		pValue = '<td><input type="hidden" value="select"/><select class="SrvcParamClass">'+selParamValue+'</select></td>';
				    	 	}
				    	 	else
				    	 		pValue = '<td><input type="hidden" value="text"/><input type="text" class="SrvcParamClass" value="'+this.paramValue+'" maxlength="255"/></td>'; 
			    		 }
			    		 pType = '<td>'+this.paramType+'</td>';
			    		 tab = pCode+pLabel+pName+pValue+pType;		    	
			    		 $('#parametersTable').find('tbody:last').append('<tr>'+tab+'</tr>');
			    	 });
			    
			    	 var i =0;
			    	 var html = "";
			    	 $.each( result.srvcFeatList, function(){
			    		 $("#srvcFeatDivId").show();
			    		 srvcFeatFlage = true;
						 if(i == 0)
							 html = html + '<div class="row"><div class="col-sm-12">'
						 html = html + '<div class = "col-sm-2"><input type="checkbox" class="srvcFeatCheckBoxClass"  value='+this.featurecode+'> '+this.featurename+'</input></div>'
						 i++;
						 if(i == 6){
							 html = html + '</div></div>'
							 i = 0;
						 }
					 });
			    	 if(result.coreSrvc == 'IPTV'){
			    		 $("#srvcFeatContentIptvDivId").show();
			    		 $('#SrvcFeaturesHeader').html("Select Channels");
			    		 if(result.ftaFlage == '0'){
			    			 $('.ftaCheckBoxClassDiv').show();
				    	 }else {
				    		 $('.ftaCheckBoxClassDiv').show();
				    		 $('.ftaCheckBoxClass').prop("disabled", true);
				    	 }
			    		 $("#srvcFeatContentIptvDivId").append(html);
			    		 $("#srvcFeatContentVoipDivId").hide();
			    	 }
			    	 if(result.coreSrvc == 'VOIP'){
			    		 $("#srvcFeatContentVoipDivId").show();
			    		 $('#SrvcFeaturesHeader').html("Select Features");
			    		 $("#srvcFeatContentVoipDivId").append(html);
			    		 $("#srvcFeatContentIptvDivId").hide();
			    	 }
			    	 
			    	 
			     }
			  });
			var txCode = "";
			var effFrom = "";
			var tabtx = "";
			$.ajax({ 
			     type: 'GET', 
			     url: 'getSrvcTaxes?coreServCode='+$(this).val(), 
			     contentType: 'application/json',
			     success: function(result){ 
			    	 $("#taxesTable").find('tbody:last').empty();
			    	 $.each(result, function(){
			    		 txCode = '<td>'+this.taxCode+'</td>';
			    		 effFrom = '<td>'+this.taxPerc+'</td>';
			    		 tabtx=txCode+effFrom;
			    		 $('#taxesTable').find('tbody:last').append('<tr>'+tabtx+'</tr>');
			    	 });
			     }
			  });
		}
		 ajaxindicatorstop();
	});

	
	$(".saveClass").on("click", function(){
		
		if($('.ftaCheckBoxClass').is(":checked")){
			$("#dialog").dialog({
				modal: true,
				resizable: false,
				buttons: {
					"Yes": function() {
						$(this).dialog("close");
						addlServiceAdd();
					},
					"No": function() {
						$('.ftaCheckBoxClass').prop('checked', false);
						$(this).dialog("close");
					}
				}
			});
		}else{
			addlServiceAdd();
		}	
	
	});
	
	function addlServiceAdd(){
		
		var srvcFeatSelectedList = [];
		$(".srvcFeatCheckBoxClass:checked").each(function() {
			srvcFeatSelectedList.push(this.value);
		});
		
		if(srvcFeatFlage){
			if(srvcFeatSelectedList.length === 0){
				alert("Please Select Service Features ...");
				return false;
			}
		}
		
		var sval = "";
		var isvalid = true;
		$("table#parametersTable > tbody > tr").each(function(){
			if($(this).find('td:eq(4)').text() == "PROVISIONING")
				sval = $(this).find('td:eq(3)').text();
			else
			{
  			 if($(this).find('td:eq(3) input[type=hidden]').val()=='select')
  				sval = $(this).find('td:eq(3)').find('select option:selected').val();
  			 else
  				sval = $(this).find('td:eq(3) input[type=text]').val();
			}
			if(sval == '')
			{
				alert("Please enter value for "+$(this).find('td:eq(0)').text());
				isvalid = false;
				return false;
			}
		});
		if($(".addlSrvcClass").valid() && isvalid == true) 
		{
		  var serviceParamList = [];
		  var typeVal = "";
		  $("table#parametersTable > tbody > tr").each(function(){
			if($(this).find('td:eq(4)').text() == "PROVISIONING")
				typeVal = $(this).find('td:eq(3)').text();
    		 else
    		 {
    			 if($(this).find('td:eq(3) input[type=hidden]').val()=='select')
    				typeVal = $(this).find('td:eq(3)').find('select option:selected').val();
    			 else
    				typeVal = $(this).find('td:eq(3) input[type=text]').val();
    		 }
			var serviceParamObj = {
		   			"code": $(this).find('td:eq(0)').text(),
				    "prmactlbl": $(this).find('td:eq(1)').text(),
				    "prmvalue": typeVal,
				    "productminval": null,
				    "productmaxval": null
			};
			serviceParamList.push(serviceParamObj);
		});
		 if( $('.ftaCheckBoxClass').is(":checked")){
			 var ftaFlage = 1;
		 }else{
			 var ftaFlage = 0;
		 }
		var additionalServiceObj = {
				"code": $("#addServCodeID").val(),
				"name":  $("#addServNameID").val(),
				"coreServCode": $( "#coreServListID option:selected" ).val(),
				"effectiveFrom": $("#effectiveDate").val(),
				"effectiveTo": $("#effectiveTo").val(),
				"apsflPerc": $("#apfID").val(),
				"mspPerc": $("#mspID").val(),
				"lmoPerc": $("#lmoID").val(),
				"servParams": serviceParamList,
				"ftaFlage": ftaFlage,
				"srvcFeaturesList":srvcFeatSelectedList
		};
		var additionalServs = JSON.stringify(additionalServiceObj);
		console.log(additionalServs);
		ajaxindicatorstart('Please Wait...');
		$.ajax({ 
		     type: 'POST', 
		     url: 'additionalCoreServicesSave', 
		     data: additionalServs,
		     contentType: 'application/json',
		     success: function(data){
		    	 ajaxindicatorstop();
		    	 $.each(data, function(key, value){
		    			if(key == 'status')
		    			{
		    				$("#id_msg").html(value);
		    			}
		    			else
		    			{
		    				var i = 1;
		    				$.each(this, function(k,v){
		    					var tab ="";
		    					tab = "<td>"+i+"</td>" 
		    							+"<td>"+v.coreSrvcName+"</td>" 
		    							+"<td>"+v.srvcCode+"</td>" 
		    							+"<td>"+v.srvcName+"</td>" 
		    							+"<td><a href='./editAdditionalService?coreSrvcCode="+v.coresrvcCode+"&srvcCode="+v.srvcCode+"&effFrom="+v.effectivefrom+"'>View</a></td>" ;
		    							if(v.coresrvcCode == "IPTV")
		    								tab = tab	+"<td><a href='./editAdditionalService?coreSrvcCode="+v.coresrvcCode+"&srvcCode="+v.srvcCode+"&effFrom="+v.effectivefrom+"&editChannels=edit'>Edit Channels</a></td>";
		    							else
		    								tab = tab +"<td></td>";
		    					$('#addlSrvcsTable').find('tbody:last').append('<tr>'+tab+'</tr>');
		    					i++;
		    				});
		    			}
		    	});
		    	$("#coreServListID").val('');
		    	$("#addServCodeID").val('');
				$("#addServNameID").val('');
		    	$('.viewSrvcs').show();
		    	$('.createSrvcs').hide();
		    	$("#addlSrvcsTable").dataTable();
		     }
		 });
		}
	}
	
	$("#cancelID").click(function(){
		window.location.href = './viewAddlServices';
	});
});