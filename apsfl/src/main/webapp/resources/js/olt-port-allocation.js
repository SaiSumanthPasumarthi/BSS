$(document).ready(function() {
	
		$(".oltShowclass").hide();
		$('#tms_oltPortId').SumoSelect();
		$("#tms_olt_btn_submit").hide();
		
		var id = "";
		var oltPortsList = [];
		var oltLmoListSize = 0;
		$('#tms_olt_search').autocomplete({
			serviceUrl: 'getTags',
			paramName: "tagName",
			delimiter: ",",
		    transformResult: function(response) {
		    	id = "";
		        return {
		            suggestions: $.map($.parseJSON(response), function(item) {
		                return { value: item.tenantName, data: item.tenantId };
		            })
		        };
		    },
		    onSelect: function (suggestion) {
		    	id=suggestion.data;
		    	$('#tenant_id').val(id);
		    }
		});
		
		$("#tms_oltId").change(function(){
			var a = $('#tms_oltId option:selected').val();
			
			var tname_tcode = $('#tms_olt_search').val();
			var t = tname_tcode.split(' | ');
			
			ajaxindicatorstart('Please Wait...');
			$.ajax({ 
			     type: 'GET', 
			     url:  'getOLTPortIds?oltId='+a+'&tenantCode='+t[1], 
			     success: function(result){ 
			    	 	$("#tms_oltPortId").empty();
			    	 	$('#tms_oltPortId')[0].sumo.unload();
			    	 	$.each(result, function( key, value ) {
							if(key == "oltPortIdList")
							{
								$.each(value, function(k, v) {
									$("#tms_oltPortId").append('<option value='+k+'>'+v+'</option>');
								});
							}
			    	 	});
						$('#tms_oltPortId').SumoSelect();
						ajaxindicatorstop();
			     }
			  });
		})
		
		$('#tms_olt_search').keydown(function(){
			$(".oltShowclass").hide();
			$('#tms_olt_table tr:gt(0)').remove();
			$(".oltVClass").val("");
			$("#tms_oltPortId").empty();
			$('#tms_oltPortId')[0].sumo.reload();
	    });
		
		$("#tms_olt_btn_search").click(function(){
			$('#tms_olt_table tr:gt(0)').remove();
			$(".oltVClass").val("");
			$("#tms_oltPortId").empty();
			$('#tms_oltPortId')[0].sumo.reload();
			$("#tms_olt_btn_submit").hide();
			if(id == "")
				alert("Please select value from suggestion dropdown");
			else
			{
			  ajaxindicatorstart('Please Wait...');
			  $.ajax({ 
			     type: 'GET', 
			     url:  'getOLTLovs?tenantId='+id, 
			     success: function(result){ 
			    	 	$("#tms_oltId").empty(); 
			    	 	$("#tms_oltId").append('<option value="">-Select-</option>');
						$.each(result, function( key, value ) {
							if(key == "oltLovs")
							{
								$.each(value, function(k, v) {
									$("#tms_oltId").append('<option value='+v.popOltSerialno+'>'+v.popOltSerialno+'</option>');
								});
							}
							else if(key == "oltLmoList")
							{
								$.each(value, function(k, v) {
									var str ='<tr>'
												+'<td>'+v.popOltSerialno+'</td>'
												+'<td>'+v.oltPortId+'</td>'
												+'<td></td>'
											+'</tr>';
									$("#tms_olt_table").append(str);
								});
								oltLmoListSize = value.length;
							}
			    		});
						$(".oltShowclass").show();
						ajaxindicatorstop();
			     }
			  });
			}
		});
		
		$("#tms_olt_btn_add").click(function(){
			var valid = true; 
			if($('#tms_oltId').val() == '')
			{
				alert("Please Select OLT Id");
				valid = false; 
			}
			else if($("#tms_oltPortId").val() == null)
			{
				alert("Please Select OLT Port Id");
				valid = false; 
			}
			if(valid) 
			{
				var i = 0;
				var tname_tcode = $('#tms_olt_search').val();
				var t = tname_tcode.split(' | ');
				var olt_id = $('#tms_oltId option:selected').val();
				var olt_port_id = $("#tms_oltPortId").val();
				var olt_port_ids = olt_port_id.toString().split(',');
				var oltInvalid = [];
				
				$("table#tms_olt_table > tbody > tr").each(function(){
					if($(this).find('td:eq(1)').text() == olt_id)
					{
						for(var kk = 0; kk < olt_port_ids.length; kk++)
						{
							if($(this).find('td:eq(2)').text().includes(olt_port_ids[kk].toString()))
								oltInvalid.push(olt_port_ids[kk].toString());
						}
					}
				});
				if(oltInvalid.toString()!= '')
				{
					alert("Port "+oltInvalid.toString()+" For OLT ID "+olt_id+" is already added.");
					i=1;
				}
				if(i == 0)
				{
					var str ='<tr>'
						+'<td>'+olt_id+'</td>'
						+'<td>'+$("#tms_oltPortId").val()+'</td>'
						+'<td>'
						+'<a href="javascript:void(0);" class="btn btn-sm btn-danger oltClassDel" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a>'
						+'</td>'
					+'</tr>';
					$("#tms_olt_table").append(str);
					var oltPortsObj = {
							"tenantCode": t[1],
							"oltId": olt_id,
							"oltPortId": $("#tms_oltPortId").val()
					};
					oltPortsList.push(oltPortsObj);
					$("#tms_olt_btn_submit").show();
				}
				$(".oltVClass").val("");
				$('#tms_oltPortId')[0].sumo.unSelectAll();
				
			}
		});
		
		$(document).on('click', '.oltClassDel', function(event) {
			var rowindex = $(this).closest('tr').index();
			oltPortsList.splice(rowindex-oltLmoListSize, 1);
			$(this).closest('tr').remove();
			$(".oltVClass").val("");
			$('#tms_oltPortId')[0].sumo.unSelectAll();
		});
		
		$("#tms_olt_btn_submit").click(function(){
			var rowCount = $('#tms_olt_table tr:gt(0)').length;
			if(rowCount-oltLmoListSize == '0')
			{
				alert("No records to update. Please select atleast one record.");
			}
			else
			{
				ajaxindicatorstart('Please Wait...');
				console.log(JSON.stringify(oltPortsList));
				$.ajax({ 
				     type: 'POST', 
				     url: 'saveOltPorts', 
				     data: JSON.stringify(oltPortsList),
				     contentType: 'application/json',
				     success: function(data){ 
				    		ajaxindicatorstop();
				    		if(data=="success")
				    			$("#id_oltMsg").html('<font face="Times New Roman" size="4px" color="Green">OLT Ports Allocated Successfully.</font>');
				    		else
				    			$("#id_oltMsg").html('<font face="Times New Roman" size="4px" color="Red">Failed to Allocate OLT Ports</font>');
				    		$(".oltShowclass").hide();
							$('#tms_olt_table tr:gt(0)').remove();
							$(".oltVClass").val("");
							$('#tms_oltPortId')[0].sumo.unSelectAll();
							$('#tms_olt_search').val("");
							oltPortsList = [];
				     }
				});
				
			}
			
		});
});