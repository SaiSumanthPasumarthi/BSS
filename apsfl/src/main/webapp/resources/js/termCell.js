$(document).ready(function(){
	
	var termCellDT = $("#termCellTableId").DataTable({
 		"sScrollX": "true",
	        });
	
	// For ENable Default search based on District and mandal and StdCode
	$("input[name=detailSearch]").attr('checked', 'checked');
	  $('#districtID').removeAttr("disabled");
	   $('#mandalsID').removeAttr("disabled");
	   $('#stdCodesID').removeAttr("disabled");
	   $('#landLineNum').attr("disabled","disabled");
	//for Selecting only one Radio Button
	
	$('input[type=radio]').on('change', function(){
	    $('input[type=radio]').not(this).prop('checked', false);
	});
	
	//For Disable Fields
	  // Search Based on District Mandal and Std CODE
	 $('#directSearchlbl').click(function()
			 {
			   $('#landLineNum').removeAttr("disabled");
			   $('#districtID').attr("disabled","disabled");
				  $('#mandalsID').attr("disabled","disabled");
				  $('#stdCodesID').attr("disabled","disabled");
				  $('select option:contains("SELECT")').prop('selected',true);
			 });
        // Search Based on LandLine Number
			 $('#detailSearchlbl').click(function()
			 {
			   $('#landLineNum').attr("disabled","disabled");
			   $('#districtID').removeAttr("disabled");
			   $('#mandalsID').removeAttr("disabled");
			   $('#stdCodesID').removeAttr("disabled");
			   $("#landLineNum").val('');
			 });
			 
			// to get STD Codes
				$(document).on('change','#districtID',function() {
					var district = $("#districtID").val();
					$("#mandalsID").html("");
					$("#stdCodesID").html("");
					var div_data="<option selected value=''>--SELECT--</option>";
					$(div_data).appendTo('#mandalsID');
					var div_data1="<option selected value=''>--SELECT--</option>";
					$(div_data).appendTo('#stdCodesID');
					 
					$.ajax({
						url         : 'getStdCodesByDist',
						method      : 'get',
						contentType : 'application/json',
						data        : {
										districtName : district
									   },
					    beforeSend: function() {
										   ajaxindicatorstart("Loading...")
							            },
				        complete: function() {
							            ajaxindicatorstop()
							            },
						success     : function(data) {
							$.each(data, function(index, value) {
								if(value!=''){
								var div_data = "<option value=" + value+ ">" + value + "</option>";
								$(div_data).appendTo('#stdCodesID');
								}
							})
						},
						error      : function() {
							alert("error");
						}
					});

				});
				
				// to Get Mandal Names
				$(document).on('change','#stdCodesID',function() {
					var stdCode = $("#stdCodesID").val();
					var district = $("#districtID").val();
					$("#mandalsID").html("");
					var div_data="<option selected value=''>--SELECT--</option>";
					$(div_data).appendTo('#mandalsID');
					$.ajax({
						url 	    : 'getMandalsByDistStdCode',
						method	    : 'get',
						contentType : 'application/json',
						data        : {
										stdCodeNum : stdCode,
										districtName : district
									   },
						beforeSend: function() {
										   ajaxindicatorstart("Loading...")
							            },
				        complete: function() {
							            ajaxindicatorstop()
							            },
						success		: function(data) {
							 
							$.each(data, function(index, value) {
								  div_data = "<option value=" +value[0]+ ">" +value[1] + "</option>";
								$(div_data).appendTo('#mandalsID');
							})
						},
						error      : function() {
							alert("error");
						}
					});
				});
				
				$("#seachtermCellId").click(function() {
					var	district = $('#districtID').val(); 
					var mandal = $('#mandalsID').val();
					var	stdCode = $('#stdCodesID').val(); 
					var landLineNum = $('#landLineNum').val();
					 
					if($('#detailSearch').is(':checked') || $('#directSearch').is(':checked')){
						
						var testflag = 'false';
						if($('#detailSearch').is(':checked'))
							{
						if($('#detailSearch').is(':checked')  && district != '' )
							testflag='true';
						else 
							alert("Please Select District...!!!");
							}
						else
							{
						if($('#directSearch').is(':checked') && landLineNum != '')
							testflag='true';
						else
							alert("Please Enter LandLine Number... !!!");
							}
						if(testflag == 'true')
							{
								$.ajax({
									url 	    : 'getActiveLandLines',
									method	    : 'get',
									contentType : 'application/json',
									data        : {
													mandalName : mandal,
													districtName : district,
													stdCodeNum : stdCode,
													landLineNum : landLineNum,
												   },
												   beforeSend: function() {
													   ajaxindicatorstart("Loading...")
										            },
							        complete: function() {
										            ajaxindicatorstop()
										            },
												   success		: function(data) {
														var body ='';
														termCellDT.clear().draw();
														$.each(data, function(index, value) {
															termCellDT.row.add( [value[0],value[1],value[2],value[3],value[4],value[5],value[6],value[7]]);
														});
														termCellDT.draw();
													},
												  error      : function() {
										             alert("error");
								             	   }
								});
							}
					}
				});
				
				//Download termcell Report
				$("#downLoadTermCellReportId").click(function() {
				    
					var	district = $('#districtID').val(); 
					var mandal = $('#mandalsID').val();
					var	stdCode = $('#stdCodesID').val(); 
					var landLineNum = $('#landLineNum').val();
					 
					if($('#detailSearch').is(':checked') || $('#directSearch').is(':checked')){
						
						var testflag = 'false';
						if($('#detailSearch').is(':checked'))
							{
						if($('#detailSearch').is(':checked')  && district != '' )
							testflag='true';
						else 
							alert("Please Select District...!!!");
							}
						else
							{
						if($('#directSearch').is(':checked') && landLineNum != '')
							testflag='true';
						else
							alert("Please Enter LandLine Number... !!!");
							}
						if(testflag == 'true')
							{
						$('#termCellReportForm').submit();
							}
					}
					else
						{
						alert("No input Found... !!!");
						}

				});
				
				 //Remove '0' from land Line NUmber
				$("#landLineNum").numeric().keyup(function (e) {
				    var num = $(this).val();
				    while (num.substring(0, 1) === '0') {   
				        num = num.substring(1);            
				    }
				    $(this).val(num);    				});
});