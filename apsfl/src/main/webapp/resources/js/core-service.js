$(document).ready(function() {
	
	$('.createSrvcs').hide();
	$("#id_taxEditStatus").val('');
	
	if($("#id_editStatus").val() == "viewOnly")
		$('.disableView').hide();
		
	if($("#id_viewStatus").val() == "view")
	{
		$("#parametersTable").dataTable();
		$('.viewSrvcs').show();
		$('.createSrvcs').hide();
	}
	else
	{
		$('.viewSrvcs').hide();
		$('.createSrvcs').show();
	}
	
	jQuery.validator.addClassRules('coreSrvcClass', { 
        required: true
    });
	$('.coreSrvcClass').validate();
	
    jQuery.validator.addClassRules('srvcClass', {
    	required: true
	});
    $('.srvcClass').validate();
    
    jQuery.validator.addClassRules('taxClass', {
    	required: true
	});
    $('.taxClass').validate();
    
	/*$("#taxEffDtID").datepicker({ 
		dateFormat: 'dd-mm-yy' 
		});*/
	
	$(".lovClass").hide();
	$("#selParamValueID").hide();
	$("#paramValueID").addClass("srvcClass");
	
	$('.srvcClass').change(function(){
		$('label.error').hide();
	});
	
	$('#glCodeID').change(function(){
		$('#id_glName').val($(this).val());
	});
	
	$("#paramTypeID").change(function(){
		if($(this).val()=="CHARGEABLE"){
			$(".valueClass").hide();
			$("#paramValueID").removeClass("srvcClass");
			$("#selParamValueID").removeClass("srvcClass");
		}
		else if($(this).val()=="BOTH"){
			$(".valueClass").show();
			$("#paramValueID").removeClass("srvcClass");
			$("#selParamValueID").removeClass("srvcClass");
		}
		else{
			$(".valueClass").show();
			$("#paramValueID").addClass("srvcClass");
			$("#selParamValueID").addClass("srvcClass");
		}
		$("#paramValueID").val('');
		$("#lovID").val("");
		$("#selParamValueID").val("");
	});
	
	$("#valueTypeID").change(function(){
		if($(this).val() == 'LISTBOX')
		{
			$('.lovClass').show();
			$("#lovID").addClass("srvcClass");
			$("#selParamValueID").show();
			if($("#paramTypeID").val()=="CHARGEABLE" || $("#paramTypeID").val()=="BOTH")
				$("#selParamValueID").removeClass("srvcClass");
			else
				$("#selParamValueID").addClass("srvcClass");
			$("#paramValueID").hide();
			$("#paramValueID").removeClass("srvcClass");
			$("#lovID").val("");
			$("#selParamValueID").val("");
		}
		else
		{
			$('.lovClass').hide();
			$("#lovID").removeClass("srvcClass");
			$("#selParamValueID").hide();
			$("#selParamValueID").removeClass("srvcClass");
			$("#paramValueID").show();
			if($("#paramTypeID").val()=="CHARGEABLE" || $("#paramTypeID").val()=="BOTH")
				$("#paramValueID").removeClass("srvcClass");
			else
				$("#paramValueID").addClass("srvcClass");
			$("#paramValueID").val("");
		}
	});
	
	$("#lovID").change(function(){
		var lovVals = $(this).val();
		$.ajax({ 
		     type: 'GET', 
		     url:  'getLovVals?lovName='+lovVals, 
		     success: function(result){ 
		    	 	$("#selParamValueID").empty();
		    	 	$("#selParamValueID").append('<option value="">-Select-</option>');
		    		$.each(result, function( key, value ) {
		    			$("#selParamValueID").append('<option value='+key+'>'+value+'</option>');
		    		});
		     }
		  });
	});
	
	$("#selParamValueID").change(function(){
		$("#paramValueID").val($(this).find('option:selected').val());
	});
	
	var rowCount=0;
	var serviceParamList = [];
	$("#addsvrcID").click(function(){
		$("#id_srvcParamErr").html("");
		if($(".srvcClass").valid()) 
		{
			var i = 0;
			$.each(serviceParamList,function(key, value){
				if(value.code == $("#paramCodeID").val() && $("#id_srvcParamEditStatus").val() == '')
				{
					alert($("#paramCodeID").val()+" is already added.");
					i=1;
				}
			});
			if(i == 0)
			{
				if($("#id_srvcParamEditStatus").val() != '')
				{
					$('#svrcParamTableID > tbody:last tr:eq('+$("#id_srvcParamEditStatus").val()+')').find('td:nth-child(1)').text($("#paramCodeID").val());
					$('#svrcParamTableID > tbody:last tr:eq('+$("#id_srvcParamEditStatus").val()+')').find('td:nth-child(2)').text($("#paramNameID").val());
					$('#svrcParamTableID > tbody:last tr:eq('+$("#id_srvcParamEditStatus").val()+')').find('td:nth-child(3)').text($("#paramTypeID").val());
					$('#svrcParamTableID > tbody:last tr:eq('+$("#id_srvcParamEditStatus").val()+')').find('td:nth-child(4)').text($("#dataTypeID").val());
					$('#svrcParamTableID > tbody:last tr:eq('+$("#id_srvcParamEditStatus").val()+')').find('td:nth-child(5)').text($("#paramLabelID").val());
					$('#svrcParamTableID > tbody:last tr:eq('+$("#id_srvcParamEditStatus").val()+')').find('td:nth-child(6)').text($("#valueTypeID").val());
					$('#svrcParamTableID > tbody:last tr:eq('+$("#id_srvcParamEditStatus").val()+')').find('td:nth-child(7)').text($("#lovID").val());
					$('#svrcParamTableID > tbody:last tr:eq('+$("#id_srvcParamEditStatus").val()+')').find('td:nth-child(8)').text($("#paramValueID").val());
					var serviceParamObj1 = {
				   			"code": $("#paramCodeID").val(),
						    "name": $("#paramNameID").val(),
						    "paramType": $("#paramTypeID").val(),
						    "dataType": $("#dataTypeID").val(),
						    "paramValueType": $("#valueTypeID").val(),
						    "label":  $("#paramLabelID").val(),
						    "value": $("#paramValueID").val(),
						    "prmLOVName":$("#lovID").val()
						};
					serviceParamList.splice($("#id_srvcParamEditStatus").val(), 1, serviceParamObj1);
				}
				else
				{
					rowCount++;
					var str ='<tr id="svrc_'+rowCount+'"><td>'+$("#paramCodeID").val()+'</td><td>'+$("#paramNameID").val()+'</td><td>'+$("#paramTypeID").val()+'</td><td style="display: none;">'+$("#dataTypeID").val()+'</td><td>'+$("#paramLabelID").val()+'</td><td style="display: none;">'+$("#valueTypeID").val()+'</td><td style="display: none;">'+$("#lovID").val()+'</td><td>'+$("#paramValueID").val()+'</td><td> <a href="javascript:void(0);" class="btn btn-sm btn-dark srvcParamClassEdit" data-toggle="tooltip" data-rel="tooltip" data-original-title="Edit"><i class="icon-note"></i></a> '+
						'<a href="javascript:void(0);" class="btn btn-sm btn-danger srvcParamClass" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a>';
			
					$("#svrcParamTableID").append(str);
		
					var serviceParamObj = {
							"code": $("#paramCodeID").val(),
							"name": $("#paramNameID").val(),
							"paramType": $("#paramTypeID").val(),
							"dataType": $("#dataTypeID").val(),
							"paramValueType": $("#valueTypeID").val(),
							"label":  $("#paramLabelID").val(),
							"value": $("#paramValueID").val(),
							"prmLOVName":$("#lovID").val()
					};
		
					serviceParamList.push(serviceParamObj);
				}
				$(".srvcClass").val("");
				$("#paramValueID").val("");
				$("#selParamValueID").val("");
				$("#id_srvcParamEditStatus").val("");
				$("#paramCodeID").prop("disabled", false);
			}
		}
	});
	
	$(document).on('click', '.srvcParamClass', function(event) {
		var rowindex = $(this).closest('tr').index();
		serviceParamList.splice(rowindex, 1);
		$(this).closest('tr').remove();
		$(".srvcClass").val("");
		$("#paramValueID").val("");
		$("#id_srvcParamEditStatus").val("");
		$("#paramCodeID").prop("disabled", false);
	 });
	
	$(document).on('click', '.srvcParamClassEdit', function(event) {
		var rowindex = $(this).closest('tr').index();
		var vals = $(this).closest('tr').find('td:nth-child(8)').text();
		
		$("#paramCodeID").val($(this).closest('tr').find('td:nth-child(1)').text());
		$("#paramNameID").val($(this).closest('tr').find('td:nth-child(2)').text());
		$("#paramTypeID").val($(this).closest('tr').find('td:nth-child(3)').text());
		$("#dataTypeID").val($(this).closest('tr').find('td:nth-child(4)').text());
		$("#paramLabelID").val($(this).closest('tr').find('td:nth-child(5)').text());
		$("#valueTypeID").val($(this).closest('tr').find('td:nth-child(6)').text());
		$("#lovID").val($(this).closest('tr').find('td:nth-child(7)').text());
		$("#paramValueID").val($(this).closest('tr').find('td:nth-child(8)').text());
		$("#selParamValueID").val($(this).closest('tr').find('td:nth-child(8)').text());
		$("#id_srvcParamEditStatus").val(rowindex);
		$("#paramCodeID").prop("disabled", true);
		if($("#paramTypeID").val() == "CHARGEABLE")
			$(".valueClass").hide();	
		else
			$(".valueClass").show();
		if($("#valueTypeID").val() == 'LISTBOX')
		{
			$('.lovClass').show();
			$("#selParamValueID").show();
			$("#paramValueID").hide();
		}
		else
		{
			$('.lovClass').hide();
			$("#selParamValueID").hide();
			$("#paramValueID").show();
		}
		//$(this).closest('tr').remove();
		if($("#id_editStatus").val() == "edit")
		{
			if($("#valueTypeID").val() == 'LISTBOX')
			{
				$('.lovClass').show();
				$("#lovID").addClass("srvcClass");
				$("#selParamValueID").show();
				if($("#paramTypeID").val()=="CHARGEABLE" || $("#paramTypeID").val()=="BOTH")
					$("#selParamValueID").removeClass("srvcClass");
				else
					$("#selParamValueID").addClass("srvcClass");
				$("#paramValueID").hide();
				$("#paramValueID").removeClass("srvcClass");
				//$("#lovID").val("");
				//$("#selParamValueID").val("");
			}
			else
			{
				$('.lovClass').hide();
				$("#lovID").removeClass("srvcClass");
				$("#selParamValueID").hide();
				$("#selParamValueID").removeClass("srvcClass");
				$("#paramValueID").show();
				if($("#paramTypeID").val()=="CHARGEABLE" || $("#paramTypeID").val()=="BOTH")
					$("#paramValueID").removeClass("srvcClass");
				else
					$("#paramValueID").addClass("srvcClass");
				//$("#paramValueID").val("");
			}
			
			var $options = $(this).closest('tr').find('td:nth-child(9)').find('select option').clone();
			$("#selParamValueID").empty();
			$("#selParamValueID").append('<option value="">-Select-</option>');
			$('#selParamValueID').append($options);
			$("#selParamValueID").val(vals);
		}
	 });
	
	$("#taxCodeID").change(function(){
		//alert("hisdi");
		var taxCode = $("#taxCodeID").val();
		$.getJSON("taxPercentage?taxCode="+taxCode, function(result){
		    	 //alert(result);
		    	 $("#taxEffDtID").val(result);
		  });
	});
	
	var rowCnt=0;
	var taxList = [];
	$(document).on('click', '#addTaxID', function(event){
	if($(".taxClass").valid()) 
	{
		var i = 0;
		$("#id_taxErr").html("");
		$.each(taxList,function(key, value){
			if(value.taxCode == $("#taxCodeID").val() && $("#id_taxEditStatus").val() == '')
			{
				alert($("#taxCodeID").val()+" is already added.");
				i=1;
			}
		});
		if(i == 0)
		{
			if($("#id_taxEditStatus").val() != '')
			{
				$('#taxTableID > tbody:last tr:eq('+$("#id_taxEditStatus").val()+')').find('td:nth-child(1)').text($("#taxCodeID").val());
				$('#taxTableID > tbody:last tr:eq('+$("#id_taxEditStatus").val()+')').find('td:nth-child(2)').text($("#taxEffDtID").val());
				var taxObj1 = {
		   				"taxCode": $("#taxCodeID").val(),
				  	  	//"efftFrom": $("#taxEffDtID").val()		 
					};
				taxList.splice($("#id_taxEditStatus").val(), 1, taxObj1);
			}
			else
			{
				rowCnt++;
				/*var str ='<tr id="tax_'+rowCnt+'"><td>'+$("#taxCodeID").val()+'</td><td>'+$("#taxEffDtID").val()+'</td><td><a href="javascript:void(0);" class="btn btn-sm btn-dark taxCodeEditClass" data-toggle="tooltip" data-rel="tooltip" data-original-title="Edit"><i class="icon-note"></i></a> '+
					 '<a href="javascript:void(0);" class="btn btn-sm btn-danger taxCodeClass" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a></td></tr>';*/
				var str ='<tr id="tax_'+rowCnt+'"><td>'+$("#taxCodeID").val()+'</td><td>'+$("#taxEffDtID").val()+'</td><td><a href="javascript:void(0);" class="btn btn-sm btn-danger taxCodeClass" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a></td></tr>';
				$("#taxTableID").append(str);
		
				var taxObj = {
	   				"taxCode": $("#taxCodeID").val(),
			  	  	//"efftFrom": $("#taxEffDtID").val()		 
				};
		
				taxList.push(taxObj);
			}
			$(".taxClass").val("");
			$("#taxEffDtID").val("");
			$("#id_taxEditStatus").val("");
			$("#taxCodeID").prop("disabled", false);
		}
	 }
	});
	
	$(document).on('click', '.taxCodeClass', function(event) {
		var rowindex = $(this).closest('tr').index();
		taxList.splice(rowindex, 1);
		$(this).closest('tr').remove();
		$(".taxClass").val("");
		$("#id_taxEditStatus").val("");
		$("#taxCodeID").prop("disabled", false);
		
	 });
	
	$(document).on('click', '.taxCodeEditClass', function(event) {
		var rowindex = $(this).closest('tr').index();
		//taxList.splice(rowindex, 1);
		$("#taxCodeID").val($(this).closest('tr').find('td:nth-child(1)').text());
		$("#taxEffDtID").val($(this).closest('tr').find('td:nth-child(2)').text());
		$("#id_taxEditStatus").val(rowindex);
		$("#taxCodeID").prop("disabled", true);
		//$(this).closest('tr').remove();
	 });
	
	$("#submitID").click(function(){
		if(serviceParamList.length == 0)
		{
			$("#id_srvcParamErr").html("Please enter atleast one Service Parameter.");
		}
		if(taxList.length == 0)
		{
			$("#id_taxErr").html("Please enter atleast one Tax Code.");
		}
		if($(".coreSrvcClass").valid() && serviceParamList.length != 0 && taxList.length != 0) 
		{
			/*var ischecked = false;
			if($('#isMultipleID').prop("checked") == true)
				ischecked = true;
	        else if($('#isMultipleID').prop("checked") == false)
	        	ischecked = false;*/
			
		    var coreServiceObj = {
				"code": $("#coresrvcCodeID").val(),
				"name": $("#coresrvcNameID").val(),
				"provCode": $("#provCodeID").val(),
				"glCode": $("#glCodeID").val(),
				"multiple": $('#isMultipleID').prop("checked"),
				"oneTime": $('#isOneTimeID').prop("checked"),
				"provParams": {type: $("#provTypeID").val(),value: $("#provValID").val()},		
				"servParams": serviceParamList,
				"taxParams": taxList
		    };
		
		var coreServices = JSON.stringify(coreServiceObj); 
		console.log(coreServices);
		ajaxindicatorstart('Please Wait...');
		$.ajax({ 
		     type: 'POST', 
		     url: 'createCoreService', 
		     data: coreServices,
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
		    					//tab = "<td>"+i+"</td><td>"+v.servCode+"</td><td>"+v.servProvCode+"</td><td>"+v.servName+"</td><td><a href='./editCoreService?srvcCode="+v.servCode+"&effFrom="+v.effectivefrom+"'>View</a></td><td><a href='./modifyCoreService?srvcCode="+v.servCode+"'>Edit</a></td>"
		    					tab = "<td>"+i+"</td><td>"+v.servCode+"</td><td>"+v.servProvCode+"</td><td>"+v.servName+"</td><td><a href='./editCoreService?srvcCode="+v.servCode+"&effFrom="+v.effectivefrom+"'>View</a></td>"
		    					$('#parametersTable').find('tbody:last').append('<tr>'+tab+'</tr>');
		    					i++;
		    				});
		    			}
		    		});
		    		$('.viewSrvcs').show();
		    		$('.createSrvcs').hide();
		    		$("#parametersTable").dataTable();
		    		console.log("SUCCESS: ", data);
		     }
		  });
	   }
	});
	
	$("#cancelID").click(function(){
		window.location.href = './viewCoreServices';
	});
});