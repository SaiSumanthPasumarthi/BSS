$(document).ready(function() {

			$("#blockListedDetailsTable,#approveBlacklistTable").dataTable({
				"sPaginationType" : "full_numbers",
			});
			
			var date = new Date();
			var currentMonth = date.getMonth();
			var currentDate = date.getDate();
			var currentYear = date.getFullYear();
			 $("#effectiveFrom").datepicker({
			       numberOfMonths: 1,
			       changeMonth: true,
			       changeYear: true,
			       minDate : new Date(currentYear, currentMonth, currentDate),
			       onSelect: function (selected) {
			           var dt = new Date(selected);
			           dt.setDate(dt.getDate());
			           $("#effectiveTo").datepicker("option", "minDate", dt);
			       }
			   });
			   $("#effectiveTo").datepicker({
			    minDate : new Date(currentYear, currentMonth, currentDate),
			       numberOfMonths: 1,
			       changeMonth: true,
			       changeYear: true,
			       onSelect: function (selected) {
			           var dt = new Date(selected);
			           dt.setDate(dt.getDate() - 1);
			           $("#effectiveFrom").datepicker("option", "maxDate", dt);
			       }
			   });
			
			$("#BlockListSearchButton").click(function(event) {
				var mobileNo = $('#mobileNo').val();
				var stbNo = $('#stbNo').val();
				var stbMac = $('#stbMac').val();
				if(mobileNo == "" && stbNo == "" && stbMac == "") {
					$('#error1').text("Please Enter any one of the Field .");
				} else {
					$("#createBlockListForm").attr('action',"searchCafDetailsForBlockList");
					$("#createBlockListForm").submit();
				}
				});
			
			$("#blacklistcancel").click(function(event) {
				$("#cafToBlockListTable").html("");
				$("#resonTable").html("");
				
				});
			
			$(".radiostb").click(function(){
				$('#cafToBlockListTable').find('tr').each(function() {
					var row = $(this);
					if (row.find('input[type="radio"]').is(':checked')) {
						$("#MarkasBlock").removeAttr("disabled");
					}
				});
			});
			
			$("#MarkasBlock").click(function(event) {
				if( $("#reasonForBlocking").val().length > 0){
				var cafNo;
				var custid;
				var stbcafno;
				$('#cafToBlockListTable').find('tr').each(function() {
					var row = $(this);
					if (row.find('input[type="radio"]').is(':checked')) {
						  cafNo = row.find('.cafno').text();
						  custid = row.find('.custid').text();
						  stbcafno = row.find('.stbcafno').text();
						//alert(cafNo+ " "+ custids);
					}
				});
				$("#stbcafno").val(stbcafno);
				$("#cafToBlockListTable > tbody").html("");
				$("#BlockCustomerForm").attr('action', "BlackCustomer");
				$("#BlockCustomerForm").submit();
				}else {
					$("#message").text("Please Enter Reason for BlackListing");
				}
			});
			
		});