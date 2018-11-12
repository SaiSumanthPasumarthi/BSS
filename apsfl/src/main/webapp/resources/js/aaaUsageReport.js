 

$(document).ready(function() {
	var hiddenYear = $('#hiddenSelectYear').val();
	var min_year = new Date().getFullYear();
	min_year = 2016;
	max_year = min_year + 10;
	var div_data = "";
	for (var i = min_year; i<=max_year; i++){
		if(i == hiddenYear){
			 div_data = div_data + "<option selected value=" + i+ ">" + i + "</option>";
		}else{
			 div_data = div_data + "<option  value=" + i+ ">" + i + "</option>";
		}
	}
	$(div_data).appendTo('#yearListId');
	 
	var dataTable ;
	var url = "aaaUsageReportByDate?"
	var oTable = $('#aaaUsageReportTable').dataTable(dataTable);
	
	seachAaaUsageReportIdFunction();
	 
			$('#dateFromAaaUsage').datepicker({
				changeMonth : true,
				changeYear : true,
				inline : true,
				maxDate : '+0d',
				onSelect : function(mindate) {
					$("#dateToAaaUsage").datepicker("option", {
						minDate : new Date(mindate)
					})
				}
			});

			$('#dateToAaaUsage').datepicker({
				changeMonth : true,
				changeYear : true,
				inline : true,
				maxDate : '+0d',
				onSelect : function(maxDate) {
					$("#dateFromAaaUsage").datepicker("option", {
						maxDate : new Date(maxDate)
					})
				}
			});
			
			$(document).on('change','#districtListId',function() {
						var district = $("#districtListId").val();
						$("#mandalsListId").html("");
						$("#villagesListId").html("");
						var div_data="<option selected value=''>--SELECT--</option>";
						$(div_data).appendTo('#mandalsListId');
						var div_data1="<option selected value=''>--SELECT--</option>";
						$(div_data1).appendTo('#villagesListId');
						$.ajax({
							url         : 'getMandalsList',
							method      : 'get',
							contentType : 'application/json',
							data        : {
											districtName : district
										   },
							success     : function(data) {
								$.each(data, function(index, value) {
									var div_data = "<option value=" + value[0]+ ">" + value[1] + "</option>";
									$(div_data).appendTo('#mandalsListId');
								})
							},
							error      : function() {
								alert("error");
							}
						});

					});

			$(document).on('change','#mandalsListId',function() {
						var mandal = $("#mandalsListId").val();
						var district = $("#districtListId").val();
						$("#villagesListId").html("");
						var div_data="<option selected value=''>--SELECT--</option>";
						$(div_data).appendTo('#villagesListId');
						$.ajax({
							url 	    : 'getVillagesList',
							method	    : 'get',
							contentType : 'application/json',
							data        : {
											mandalName : mandal,
											districtName : district
										   },
							success		: function(data) {
								$.each(data, function(index, value) {
									  div_data = "<option value=" + value[0] + ">" + value[1] + "</option>";
									$(div_data).appendTo('#villagesListId');
								})
							},
							error      : function() {
								alert("error");
							}
						});

					});

			$("#seachAaaUsageReportId").click(function() {
				seachAaaUsageReportIdFunction();
			});
			
			function seachAaaUsageReportIdFunction(){
					var fromDate = $('#dateFromAaaUsage').val(); 
					var toDate = $('#dateToAaaUsage').val();
					
					var	yearListId = $('#yearListId').val(); 
					var monthsListId = $('#monthsListId').val();
					if((yearListId != '' && monthsListId != '') || (fromDate != '' && toDate != '')){
						var formData= jQuery('#aaaUsageReportForm').serialize();
						   url = url+formData; 
						        oTable.fnDestroy();
						        dataTable ={  
						        		"bProcessing": false,
						        		"sort" : "position",
						        		"bServerSide": true,
						        		"sAjaxSource" : url,
						        		"sScrollX": true,
						        	    "bScrollCollapse": true,
						        		"bautoWidth": false,
						        		"aoColumnDefs": 	[
						        		   { "sWidth": "18px", "aTargets": [2,3,4,5,6,9] },
						        		   { "sWidth": "21px", "aTargets": [0,7,8] },
						        		   { "sWidth": "25px", "aTargets": [1] }
						        		], 
						    	 
									     "aoColumns": [{ "mData": "sesStartTime" },
									                  { "mData": "sesEndTime" },
									                  { "mData": "sessiontime" },
									                  { "mData": "acctStatusType" },
									                  { "mData": "cafNo" },
								                      { "mData": "custname"  },
								                      { "mData": "substnname" },
								                      { "mData": "totalSize" },
								                      { "mData": "dwnldsize" },
								                      { "mData": "upldsize" },
											       ]
						  		  };
						        
						        oTable = $('#aaaUsageReportTable').dataTable(dataTable);
						        url ="aaaUsageReportByDate?";
					}else{
						alert("Please Select From/To OR Month/Year")
					}
					
			}
			
			$("#downLoadAAAReportId").click(function() {
			    var	fromDate = $('#dateFromAaaUsage').val(); 
				var toDate = $('#dateToAaaUsage').val();
				if (fromDate != '' && toDate != '') {
					$('#aaaUsageReportForm').submit();
				} else
					alert("Not Empty From Date TO Date");

			});
		});



 