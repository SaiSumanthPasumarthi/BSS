$(document).ready(function(){
	
/*	$('#provReqListID').DataTable({
	    	//"scrollY":	"300px",
	        "scrollCollapse": true,
	        "paging":	false
	});*/
	
	$('#pendprovid').DataTable();
	
	$(".viewBtnClass" ).on( "click", function(){
		var index = $(this).index('.viewBtnClass');
		var reqId = $("table#provReqListID > tbody > tr:eq("+index+") > td:eq(1)").text();
		var t = "";
		var i = 1;
		$('.trrClass').remove();
		$.ajax({ 
		     type: 'GET', 
		     url:  'getErrJsons?reqid='+reqId,
		     async: false,
		     success: function(result){ 
		    	 $.each(result, function(key, value){
		    		 t = t + ' <tr class="trrClass"> '+
						' <td>'+i+'</td> '+
						' <td><div id="reqjson" class="jjson">'+value.req+'</div></td> '+
						' <td><div id="resjson" class="jjson">'+value.resp+'</div></td> '+
						' <td style="white-space: nowrap;">'+value.createdDate+'</td> '+
						' <td style="white-space: nowrap;">'+value.executedDate+'</td> '+
						' <td style="white-space: nowrap;">'+value.status+'</td> '+
						' </tr> ';
		    		 i++;
		    	 });
		    	 $("#jsonErrListID").append(t);
		     }
		});
		
		/*var jjson = '{"aid":{"ipAddress":"127.0.0.1","card":"1","tp":"10","onuId":"23"},"tps":[{"card":"1","tps":[1]}],"admin":1,"name":"HSI","networkServiceName":"HSI","upstreamTrafficProfileName":"","l2DhcpRelay":{"remoteId":"ID","useGlobalDhcp":true},"igmpOptions":{"useGlobal":false,"enable":true}}';
	
		var IS_JSON = true;
    	try
    	{
            var json = $.parseJSON(jjson);
            $(".jjson").jJsonViewer(jjson);
    	}
    	catch(err)
    	{
            IS_JSON = false;
            $(".jjson").html("False");
    	}*/
	});
	
	$("#resend_btn" ).on( "click", function(){
		var arr = [];
		var checkedAll = false;
alert("resend");
		$('input:checkbox').each(function(){
			if($(this).is(":checked"))
			{
				checkedAll = true;
				var index = $('input:checkbox').index(this)
				alert(index);
				arr.push($("table#provReqListID > tbody > tr:eq("+index+") > td:eq(1)").text());
			}
		});
		if(!checkedAll)
			alert("Please select atleast one record");
		else
		{
			$("#hiddenReqIds_ID").val(arr);
			
			$("#jsonErrorFormID").attr('action',"saveProvErrRecycle");
			$("#jsonErrorFormID").submit();
		}
		
	});
	
	
	
	
	$("#update_button" ).on( "click", function(){
		var arr = [];
		var checkedAll = false;
		alert();
		
		$('input:checkbox').each(function(){
			alert("hi");
			if($(this).is(":checked"))
			{
				alert();
				checkedAll = true;
				var  index= $('input:checkbox').index(this)
				alert(index);
				arr.push($("table#pendprovid > tbody > tr:eq("+index+") > td:eq(1)").text());
			}
		});
		if(!checkedAll)
			alert("Please select atleast one record");
		else
		{
			
			$("#hiddenReqIds_ID").val(arr);
			
			$("#jsonErrorFormID").attr('action',"updateProvisionErrors");
			$("#jsonErrorFormID").submit();
		}
		
	});
   
});