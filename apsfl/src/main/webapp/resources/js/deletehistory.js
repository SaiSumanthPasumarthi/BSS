$(document).ready(function(){
	
	$("#deleteHistoryButton").click(function(event) {
		
		$('#id_container').find('ol').html("");
		var check_cnt = 0;
		
		
		
		if($("#fromDate").val()=="" && $("#toDate").val()== ""  && $("#cpe-Serial-Number").val()== "" ){				
			alert("Please Enter Atleast one field");
			check_cnt=check_cnt+1;
			
		}
		
		
		  if($("#toDate").val()!=""){	
			  if($("#fromDate").val() == ""){
				alert("Please Select From Date");
				check_cnt=check_cnt+1;
			}}
		  if($("#fromDate").val()!=""){	
			  if($("#toDate").val() == ""){
				  alert("Please Select To Date");
				check_cnt=check_cnt+1;
			}}
		  if(check_cnt == 0)
		  {
			$("#cpeform2").attr('action',"searchDeleteHistoryDetails");
			$("#cpeform2").submit();
		  }else{return false;}
	});
	
});

