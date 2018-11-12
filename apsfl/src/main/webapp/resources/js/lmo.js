$(document).ready(function(){
	
	var rowCount=0;
	var rowCount1=0;
	var list = 1;
	var arealist = 1;
	
	
	$("#CPEDetailsTable").dataTable({
		
        "aLengthMenu": [[100, 300, 700, 1000], [100, 300, 700, 1000]],
        "iDisplayLength": 100,
        "stateSave": true,
        
    });
	$("CPEReportDetailsTable").dataTable({
		"dom": 'rtip',
		"bProcessing" : true,
		"processing": "<span class='glyphicon glyphicon-refresh glyphicon-refresh-animate'></span>",
		
	
	 					}); 
	
	
	
	
	$("#log_history_table").dataTable();
	
	$(".lmoClass").hide();
	$(".mspClass").hide();
	$(".walletClass").hide();
	$(".filediv").hide();
	$("#deleteCPESelected").hide();
	$('#uncheckAll').hide();
	
	$('.charectersonly').bind('keyup', function() { 
	    $(this).val(function(i, val) {
	        return val.replace(/[^a-z\s]/gi,''); 
	    });
	});
	
	$(".mspChannel").click(function(event) {
		var i = $(".mspChannel").index(this);
		$(".mspChannelForm:eq("+i+")").attr('action',"mspChannelsErrorDownload");
		$(".mspChannelForm:eq("+i+")").submit();
	});
	
	$('#v_villageID').SumoSelect({
		 placeholder: "-Select-",
		 triggerChangeCombined : false,
		 search: true
	 });
	$('#v_substationID').SumoSelect({
		placeholder: "-Select-",
		search: true
	});
	
	if($("#id_status").val() == "Edit")
	{
		$(".filediv").show();
		$(".walletClass").show();
		$("#tenantCode").attr("readonly", "readonly");
		$("#portalEnrllmentno").attr("readonly", "readonly");
		
		list = $('.rIndex').length;
		arealist = $('.cIndex').length;
		
		if($("#tenantTypeID").val() == "LMO")
		{
			$(".lmoClass").show();
			$(".mspClass").hide();
		}
		else if($("#tenantTypeID").val() == "MSP")
		{
			$(".lmoClass").hide();
			$(".mspClass").show();
		}
		else
		{
			$(".lmoClass").hide();
			$(".mspClass").hide();
		}
		
		if($("#tenantTypelogin").val() == 'LMO') {
			$("#creditLimit").attr("readonly", "readonly");
			$("#walletAmt").attr("readonly", "readonly");
			$(".tenanttypeEnter").hide();
			$(".tenanttypeEdit").show();
		} else if($("#tenantTypelogin").val() == 'MSP') {
			$("#creditLimit").attr("readonly", "readonly");
			$("#walletAmt").attr("readonly", "readonly");
			$(".tenanttypeEnter").hide();
			$(".tenanttypeEdit").show();
			}
	}
	
    /*$('body').on('focus',".sentTranstDate", function(){
    	$(this).datepicker();
    });*/
    
	jQuery.validator.addClassRules('tenantRegClass', { 
        required: true
    });
	$('.tenantRegClass').validate();
	
	/*jQuery.validator.addClassRules('assetsClass', { 
        required: true
    });
	$('.assetsClass').validate();*/
	
	$(".addAreas").click(function(){
		var s="";
		var s1="";
		var html='';
		var numItems = $('.cIndex').length;
		if(numItems == 0)
			arealist = 0;
		$.ajax({ 
			     type: 'GET', 
			     url:  'getareaLovs',
			     async: false,
			     success: function(result){ 
			    	 $.each(result, function(key, value){
			    		 if(key=="cable")
			    		 {
			    			 s="<option value=''>-Select-</option>";
			    			 $.each(value, function(k,v) {
								s=s+"<option value='"+k+"'>"+v+"</option>";
							});
			    		 }
			    		 else if(key=="state")
			    		 {
			    			 s1="<option value=''>-Select-</option>";
			    			 $.each(value, function(a,b) {
								s1=s1+"<option value='"+a+"'>"+b+"</option>";
							});
			    		 }
			    	 });
			    	 html = 
			    		 '<div class="panel cIndex" style="border: 1px solid #d4d5d6;">'
			    		 +'  <div class="panel-content">'
			    		 +'    <div class="row areadivView">'
			    		 +'      <div class="col-sm-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="control-label">Area Name</label>'
			    		 +'          <input type="text" name="pareas['+arealist+'].areaname" class="form-control form-white areaClass" maxlength="75" placeholder="Enter Area Name"/>'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-sm-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="form-label">Cable Type Id</label>'
			    		 +'			 <select name="pareas['+arealist+'].areas_cabletypeid" class="form-control form-white areaClass">'+s+'</select>'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-md-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="control-label">Running Cable Length</label>'
			    		 +'          <input type="text" name="pareas['+arealist+'].runningcablelen" class="form-control form-white areaClass" maxlength="16" placeholder="Enter Running Cablelen"/>'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-md-3">'
			    		 +'        <div style="float:right; clear:right;">'
			    		 +'          <img style="cursor: pointer;" width="15" height="15" class="closeAreas" src="./resources/images/close1.png">'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'    </div>'
			    		 +'    <div class="row areadivView">'
			    		 +'      <div class="col-sm-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="control-label">State</label> '
			    		 +'			 <select name="pareas['+arealist+'].stateid" class="form-control form-white areaClass stateClass">'+s1+'</select>'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-md-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="form-label">District</label>'
			    		 +'			 <select name="pareas['+arealist+'].districtid" class="form-control form-white areaClass districtClass"><option value="">-Select-</option></select>'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-md-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="control-label">Mandal</label>'
			    		 +'			 <select name="pareas['+arealist+'].mandalid" class="form-control form-white areaClass mandalClass"><option value="">-Select-</option></select>'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-sm-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="control-label">Village</label>'
			    		 +'			 <select name="pareas['+arealist+'].villageid" class="form-control form-white areaClass villageClass"><option value="">-Select-</option></select>'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'    </div>'
			    		 +'    <div class="row areadivView">'
			    		 +'      <div class="col-md-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="form-label">Subscription Count</label> '
			    		 +'          <input type="text" name="pareas['+arealist+'].subscription_cnt" class="form-control form-white areaClass number" placeholder="Enter Subscription Cnt" />'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-md-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="control-label">Connection Count</label> '
			    		 +'          <input type="text" name="pareas['+arealist+'].conn_cnt" class="form-control form-white areaClass number" placeholder="Enter Connection Count" />'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-sm-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="control-label">Digital Connection Count</label> '
			    		 +'          <input type="text" name="pareas['+arealist+'].digconn_cnt" class="form-control form-white areaClass number" placeholder="Enter Digital Connection Count" />'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'      <div class="col-md-3">'
			    		 +'        <div class="form-group">'
			    		 +'          <label class="control-label">Analog Connection Count</label>'
			    		 +'          <input type="text" name="pareas['+arealist+'].anlconn_cnt" class="form-control form-white areaClass number" placeholder="Enter Analog Connection Count" />'
			    		 +'        </div>'
			    		 +'      </div>'
			    		 +'    </div>'
			    		 +'  </div>'
			    		 +'</div>';
			     }
		});
		 
		$(".areasINDiv").append(html);
		arealist++;
	});
	
	$(document).on('click', '.closeAreas', function(event){
		var index = $(this).index('.closeAreas');
		$(".cIndex:eq("+index+")").remove();
	});
	
	$(".addAssets").click(function(){
		var s="";
		var s1="";
		var html='';
		var numItems = $('.rIndex').length;
		if(numItems == 0)
			list = 0;
		$.ajax({ 
			     type: 'GET', 
			     url:  'getassetsLovs',
			     async: false,
			     success: function(result){ 
			    	 $.each(result, function(key, value){
			    		 if(key=="cable")
			    		 {
			    			 s="<option value=''>-Select-</option>";
			    			 $.each(value, function(k,v) {
								s=s+"<option value='"+k+"'>"+v+"</option>";
							});
			    		 }
			    		 else
			    		 {
			    			 s1="<option value=''>-Select-</option>";
			    			 $.each(value, function(a,b) {
								s1=s1+"<option value='"+a+"'>"+b+"</option>";
							});
			    		 }
			    	 });
			    	 html = 
						 '<div class="panel rIndex" style="border: 1px solid #d4d5d6;">'
						 +'  <div class="panel-content">'
						 +'    <div class="row assestsdivView">'
						 +'      <div class="col-sm-3">'
						 +'        <div class="form-group">'
						 +'          <label class="control-label">Cable Type</label>'
						 +'			 <select name="passets['+list+'].cabletypeid" class="form-control form-white tenantRegClass1">'+s+'</select>'
						 +'        </div>'
						 +'      </div>'
						 +'      <div class="col-sm-3">'
						 +'        <div class="form-group">'
						 +'          <label class="form-label">Asset Type</label>'
						 +'			 <select name="passets['+list+'].assettypeid" class="form-control form-white tenantRegClass1">'+s1+'</select>'
						 +'        </div>'
						 +'      </div>'
						 +'      <div class="col-md-3">'
						 +'        <div class="form-group">'
						 +'          <label class="control-label">Route Name</label>'
						 +'          <input type="text"  name="passets['+list+'].routename" class="form-control form-white tenantRegClass1" placeholder="Enter Route Name" />'
						 +'        </div>'
						 +'      </div>'
						 +'      <div class="col-md-2">'
						 +'        <div class="form-group">'
						 +'          <label class="control-label">Sent Transtime</label> '
						 +'          <input name="passets['+list+'].senttranstime" class="form-control form-white tenantRegClass1 sentTranstDate" placeholder="Enter Sent Transtime" />'
						 +'        </div>'
						 +'      </div>'
						 +'      <div class="col-md-1">'
						 +'        <div style="float:right; clear:right;">'
						 +'          <img style="cursor: pointer;" width="15" height="15" class="closeAssets" src="./resources/images/close1.png">'
						 +'        </div>'
						 +'      </div>'
						 +'    </div>'
						 +'    <div class="row assestsdivView">'
						 +'      <div class="col-sm-3">'
						 +'        <div class="form-group">'
						 +'          <label class="control-label">ImieNumber</label>'
						 +'          <input type="text" name="passets['+list+'].imieno" class="form-control form-white tenantRegClass1 number" placeholder="Enter ImieNumber" />'
						 +'        </div>'
						 +'      </div>'
						 +'      <div class="col-md-3">'
						 +'        <div class="form-group">'
						 +'          <label class="form-label">Version Number</label>'
						 +'          <input type="text" name="passets['+list+'].versionno" class="form-control form-white tenantRegClass1 number" placeholder="Enter Version Number" />'
						 +'        </div>'
						 +'      </div>'
						 +'      <div class="col-sm-3">'
						 +'        <div class="form-group">'
						 +'          <label class="control-label">Route Map</label> '
						 +'          <input type="file" name="passets['+list+'].routemap" class="form-control form-white" />'
						 +'        </div>'
						 +'      </div>'
						 +'      <div class="col-sm-3">'
						 +'        <div class="form-group">'
						 +'          <label class="form-label">Captured Assets</label>'
						 +'          <input type="file" name="passets['+list+'].capturedassets" class="form-control form-white" />'
						 +'        </div>'
						 +'      </div>'
						 +'    </div>'
						 +'  </div>'
						 +'</div>';
			    	 
			    	    $('body').on('focus',".sentTranstDate", function(){
			    	    	$(this).datepicker({
			    	    		numberOfMonths: 1,
			    	            changeMonth: true,
			    	            changeYear: true
			    	    	});
			    	    });
			     }
		});
		 
		$(".assetsINDiv").append(html);
		list++;
	});
	
	$(document).on('click', '.closeAssets', function(event){
		var index = $(this).index('.closeAssets');
		$(".rIndex:eq("+index+")").remove();
	});
	
	$('body').on('click', '#id_lmosubmit', function(){
		if($("#accountNo").val() != "" || $("#ifscCode").val() != "" 
			|| $("#acctTypelov").val() != "" || $("#branchName").val() != "" || $("#bankNamelov").val() != "")
			$(".bankClass").addClass("tenantRegClass");
		else
			$(".bankClass").removeClass("tenantRegClass");
		
		if($("#docUniqueId1").val() != "" || $("#doclov1").val() != "")
			$(".idProofClass").addClass("tenantRegClass");
		else
			$(".idProofClass").removeClass("tenantRegClass");
		
		if($("#docUniqueId2").val() != "" || $("#doclov2").val() != "")
			$(".addrProofClass").addClass("tenantRegClass");
		else
			$(".addrProofClass").removeClass("tenantRegClass");
			
			
		if($(".tenantRegClass").valid() && $("#id_status").val() == "Edit")
		{
			$("#form_id").attr('action',"createTenant");
			$("#form_id").submit();
		}
		else if($(".tenantRegClass").valid() && $("#id_status").val() != "Edit")
		{
			$("#form_id").attr('action',"createTenant");
			$("#form_id").submit();
		}
	});
	
	$("#tenantTypeID").change(function(){
		if($(this).val() == "LMO")
		{
			$(".lmoClass").show();
			$(".mspClass").hide();
		}
		else if($(this).val() == "MSP")
		{
			$(".lmoClass").hide();
			$(".mspClass").show();
		}
		else
		{
			$(".lmoClass").hide();
			$(".mspClass").hide();
		}
		$("#creditLimit").val("");
		$(".mspSpecific").val("");
	});
	
	var $content = $(".content").hide();
	  $(".toggle").on("click", function(e){
	    $(this).toggleClass("expanded");
	    var i = $(".toggle").index(this);
	    $(".content:eq("+i+")").slideToggle();
	  });
	  
	  
	$('body').on('change', '.stateClass', function(){
			var stateID = $(this).val();
			var indx = $(this).index('.stateClass');
			$(".districtClass:eq("+indx+")").val("");
			$(".mandalClass:eq("+indx+")").val("");
			$(".villageClass:eq("+indx+")").val("");
			$.ajax({ 
			     type: 'GET', 
			     url:  'getDistrictList?stateID='+stateID, 
			     success: function(result){ 
			    	 	$(".districtClass:eq("+indx+")").empty();
			    	 	$(".districtClass:eq("+indx+")").append('<option value="">-Select-</option>');
			    	 		var temp = [];
			    			$.each(result, function(key1, value1){
								temp.push({v:value1, k:key1});
				    		});
							temp.sort(function(a,b){
								   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
								    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
								      return 0;
							});
							$.each(temp, function(key, obj){
								$(".districtClass:eq("+indx+")").append('<option value='+obj.k+'>'+obj.v+'</option>');
							});
			     }
			  });
	});
	
	$('body').on('change', '.districtClass', function(){
		var indx = $(this).index('.districtClass');
		var stateID = $(".stateClass:eq("+indx+")").val();
		var districtID = $(this).val();
		$(".mandalClass:eq("+indx+")").val("");
		$(".villageClass:eq("+indx+")").val("");
		$.ajax({ 
		     type: 'GET', 
		     url:  'getMandalList?stateID='+stateID+'&districtID='+districtID, 
		     success: function(result){ 
		    	 	$(".mandalClass:eq("+indx+")").empty();
		    	 	$(".mandalClass:eq("+indx+")").append('<option value="">-Select-</option>');
		    	 		var temp = [];
		    			$.each(result, function(key1, value1){
							temp.push({v:value1, k:key1});
			    		});
						temp.sort(function(a,b){
							   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
							    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
							      return 0;
						});
						$.each(temp, function(key, obj){
							$(".mandalClass:eq("+indx+")").append('<option value='+obj.k+'>'+obj.v+'</option>');
						});
		     }
		  });
	});
	
	$('body').on('change', '.mandalClass', function(){
		var indx = $(this).index('.mandalClass');
		$(".villageClass:eq("+indx+")").val("");
		var stateID = $(".stateClass:eq("+indx+")").val();
		var districtID = $(".districtClass:eq("+indx+")").val();
		var mandalID = $(this).val();
		$.ajax({ 
		     type: 'GET', 
		     url:  'getVillageList?stateID='+stateID+'&districtID='+districtID+'&mandalID='+mandalID, 
		     success: function(result){ 
		    	 	$(".villageClass:eq("+indx+")").empty();
		    	 	$(".villageClass:eq("+indx+")").append('<option value="">-Select-</option>');
		    	 		var temp = [];
		    			$.each(result, function(key1, value1){
							temp.push({v:value1, k:key1});
			    		});
						temp.sort(function(a,b){
							   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
							    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
							      return 0;
						});
						$.each(temp, function(key, obj){
							$(".villageClass:eq("+indx+")").append('<option value='+obj.k+'>'+obj.v+'</option>');
						});
		     }
		  });
	});
	
	$('#v_districtID').change(function(){
		var districtID = $(this).val();
		$("#v_instDistID").val("");
		$("#v_mandalID").val("");
		$("#v_countID").val("");
		var num = $('#v_villageID option').length;
		for(var i=num; i>=1; i--)
		   $('#v_villageID')[0].sumo.remove(i-1);
		var num1 = $('#v_substationID option').length;
		for(var i=num1; i>=1; i--)
		   $('#v_substationID')[0].sumo.remove(i-1);
		$.ajax({ 
		     type: 'GET', 
		     url:  'getMandalAndSubsList?stateID=1&districtID='+districtID, 
		     success: function(result){ 
		    	 	$('#v_substationID')[0].sumo.unload();
		    	 	$("#v_substationID").append('<option value="">-Select-</option>');
		    	 		var temp2 = [];
		    			$.each(result, function(key1, value1){
		    				if(key1 == 'Substations'){
		    					$.each(value1, function(k,v){
							    temp2.push({v:v, k:k});
							    });
		    				}
			    		});
						temp2.sort(function(a,b){
							   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
							    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
							      return 0;
						});
						$.each(temp2, function(key, obj){
							$("#v_substationID").append('<option value='+obj.k+'>'+obj.v+'</option>');
						});
						$('#v_substationID').SumoSelect({
							placeholder: "-Select-",
							search: true
						});
		     }
		  });
	});
	
	$('#v_instDistID').change(function(){
		var districtID = $(this).val();
		$("#v_mandalID").val("");
		var num = $('#v_villageID option').length;
		for(var i=num; i>=1; i--)
		   $('#v_villageID')[0].sumo.remove(i-1);
		$.ajax({ 
		     type: 'GET', 
		     url:  'getMandalAndSubsList?stateID=1&districtID='+districtID, 
		     success: function(result){ 
		    	 	$("#v_mandalID").empty();
		    	 	$("#v_mandalID").append('<option value="">-Select-</option>');
		    	 		var temp = [];
		    			$.each(result, function(key1, value1){
		    				if(key1 == 'Mandals'){
		    					$.each(value1, function(k,v){
							    temp.push({v:v, k:k});
							    });
		    				}
			    		});
						temp.sort(function(a,b){
							   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
							    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
							      return 0;
						});
						$.each(temp, function(key, obj){
							$("#v_mandalID").append('<option value='+obj.k+'>'+obj.v+'</option>');
						});
		     }
		  });
	});
	
	$('#v_mandalID').change(function(){
		//$("#v_villageID").val("");
		var num = $('#v_villageID option').length;
		for(var i=num; i>=1; i--)
		   $('#v_villageID')[0].sumo.remove(i-1);

		var districtID = $("#v_instDistID").val();
		var mandalID = $(this).val();
		$.ajax({ 
		     type: 'GET', 
		     url:  'getVillageList?stateID=1&districtID='+districtID+'&mandalID='+mandalID, 
		     success: function(result){ 
		    	 	//$("#v_villageID").append('<option value="">-Select-</option>');
		    	 		$('#v_villageID')[0].sumo.unload();
		    	 		var temp = [];
		    			$.each(result, function(key1, value1){
							temp.push({v:value1, k:key1});
			    		});
						temp.sort(function(a,b){
							   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
							    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
							      return 0;
						});
						$.each(temp, function(key, obj){
							$("#v_villageID").append('<option value='+obj.k+'>'+obj.v+'</option>');
						});
						$('#v_villageID').SumoSelect({
							 placeholder: "-Select-",
							 triggerChangeCombined : false,
							 search: true
						});
		     }
		  });
	});
	
	$('#v_substationID').change(function(){
		var districtID = null;
		if($("#v_instDistID").val() == ''){
			districtID = $("#v_districtID").val();
			$("#v_instDistID").val(districtID);
		}
		else {
			districtID = $("#v_instDistID").val();
		}
		var subs = $(this).val();
		$("#v_mandalID").val("");
		$("#v_countID").val("");
		var num = $('#v_villageID option').length;
		for(var i=num; i>=1; i--)
		   $('#v_villageID')[0].sumo.remove(i-1);
		$.ajax({ 
		     type: 'GET', 
		     url:  'getMandalAndSubsList?stateID=1&districtID='+districtID,
		     async: false,
		     success: function(result){ 
		    	 	$("#v_mandalID").empty();
		    	 	$("#v_mandalID").append('<option value="">-Select-</option>');
		    	 		var temp = [];
		    			$.each(result, function(key1, value1){
		    				if(key1 == 'Mandals'){
		    					$.each(value1, function(k,v){
							    temp.push({v:v, k:k});
							    });
		    				}
			    		});
						temp.sort(function(a,b){
							   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
							    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
							      return 0;
						});
						$.each(temp, function(key, obj){
							$("#v_mandalID").append('<option value='+obj.k+'>'+obj.v+'</option>');
						});
						$.ajax({ 
							type: 'GET', 
							url:  'getMandalbySubstation?stateID=1&districtID='+districtID+'&substationID='+subs,
							async: false,
							success: function(result1){
								$("#v_mandalID").val(result1);	 
								$.ajax({ 
									type: 'GET', 
									url:  'getVillageList?stateID=1&districtID='+districtID+'&mandalID='+result1,
									async: false,
									success: function(result){ 
										//$("#v_villageID").append('<option value="">-Select-</option>');
										$('#v_villageID')[0].sumo.unload();
										var temp = [];
										$.each(result, function(key1, value1){
											temp.push({v:value1, k:key1});
										});
										temp.sort(function(a,b){
											if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
											if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
											return 0;
										});
										$.each(temp, function(key, obj){
											$("#v_villageID").append('<option value='+obj.k+'>'+obj.v+'</option>');
										});
										$('#v_villageID').SumoSelect({
											placeholder: "-Select-",
											triggerChangeCombined : false,
											search: true
										});
									}
								});
							}
						});
		     	}
		  	});
	});
		
	
	var rc = $("#id_substabCnt").val();
	$("#id_addbtn").click(function(){
		if( ($("#v_villageID option:selected" ).text()!= '-Select-') && ($("#v_districtID option:selected" ).text()!= '-Select-') && ($("#v_mandalID option:selected" ).text()!= '-Select-')
				&& ($("#v_substationID option:selected" ).text()!= '-Select-') && ($("#v_countID" ).val()!=""))
		{
			var option_all = $("#v_villageID option:selected").map(function(){
			        	return $(this).val()+':'+$(this).text();
					}).get().join(',');
			//alert(option_all);
			var villArr = option_all.split(',');
			for(var i = 0; i < villArr.length; i++)
			{
				var vills = villArr[i].split(':');
				var str ='<tr id="subs_'+rc+'">'+
				'<td><input type="hidden" name="tenantsbaList['+rc+'].districtuid" value="'+$("#v_districtID option:selected").val()+'"/>'+$("#v_districtID option:selected").text()+'</td>'+
				'<td><input type="hidden" name="tenantsbaList['+rc+'].mandalslno" value="'+$("#v_mandalID option:selected").val()+'"/>'+$("#v_mandalID option:selected").text()+'</td>'+
				'<td><input type="hidden" name="tenantsbaList['+rc+'].villageslno" value="'+vills[0]+'"/>'+vills[1]+'</td>'+
				'<td><input type="hidden" name="tenantsbaList['+rc+'].substnuid" value="'+$("#v_substationID option:selected").val()+'"/>'+$("#v_substationID option:selected").text()+'</td>'+
				'<td><input type="hidden" name="tenantsbaList['+rc+'].subscribercnt" value="'+$("#v_countID").val()+'"/>'+$("#v_countID").val()+'</td>'+
				'<td><a href="javascript:void(0);" class="btn btn-sm btn-danger villagesVListDel" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a>'+
				'</tr>';
				$("#subsMappingTable").append(str);
				rc++;
			}
		}
		
		$("#v_districtID").val("");
		$("#v_instDistID").val("");
		$("#v_mandalID").val("");
		//$("#v_villageID").val("");
		//$("#v_substationID").val("");
		$("#v_mandalID").empty();
		//$("#v_villageID").empty();
		//$("#v_substationID").empty();
		//$("#v_villageID").append('<option value="">-Select-</option>');
		$("#v_mandalID").append('<option value="">-Select-</option>');
		$("#v_substationID").append('<option value="">-Select-</option>');
		$("#v_countID").val("");
		var num = $('#v_villageID option').length;
		for(var i=num; i>=1; i--)
		   $('#v_villageID')[0].sumo.remove(i-1);
		var num1 = $('#v_substationID option').length;
		for(var i=num1; i>=1; i--)
		   $('#v_substationID')[0].sumo.remove(i-1);
		
	});
	
	var rc = $("#id_substabCnt").val();
	$(document).on('click', '.villagesVListDel', function(event) {
		var rowindex = $(this).closest('tr').index();
		//var rowindex = $(this).closest('tr').index().next();
		$(this).closest('tr').remove();
		      //$(".delaction").css('visibility', 'hidden')
	 });
	
	$(document).on('click', '#channelPackageName', function(event) {
		$("#channeldiv").show();
	 });
	
	$("#processRevenueSharingButton").click(function(event) {
		$("#processRevenueSharingform").attr('action',"executeRevSharing");
		$("#processRevenueSharingform").submit();
	});
	
	$("#revenueSharingDownloadButton").click(function(event) {
		$("#revenueSharingDownloadform").attr('action',"revSharingDownload");
		$("#revenueSharingDownloadform").submit();
	});
	
	$('#popdistrict').change(function() {
		var districtId = $(this).val();
		var $SIpopId = $('#SIpopId');
		$SIpopId.find('option').remove();
		$('<option>').val("").text("--Select--").appendTo($SIpopId);
		var $popmandal = $('#popmandal');
		$popmandal.find('option').remove();
		$('<option>').val("").text("--Select--").appendTo($popmandal);
		if(districtId!="") {
			$.ajax({
				type : "GET",
				url : "getMandalsByDistId",
				dataType : "json",
				data : "&districtId="+ districtId,
				success : function(response) {
					var $select = $('#popmandal');
					$select.find('option').remove();
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.mandalSlno).text(val.mandalName).appendTo($select);
					});
				}
			});
		}
	});
	
	$("#searchOltButton").click(function(event) {
		$("#oltform").attr('action',"searchOltsDetails");
		$("#oltform").submit();
   });
	
	$('#PortDetailsTable').on("click",'input[type=radio]',function() {
		$('.portInfo').show();
		var flag = true;
		$('.savePortDtlsButton').show();
		if ($(this).is(":checked")) {
			var checkId = $(this).val();
			$('#PortDetailsTable').find('tr').each(function() {
				if ($(this).find('input[type="radio"]').is(':checked')) {
					if(checkId === $(this).find('input[type="radio"]').val()){
						var suid = $(this).find('.suid').text();
						var oltno = $(this).find('.oltno').text();
						
						$.getJSON("getOltDetails", {suid:suid,oltno:oltno}, function(TenantVO) {
								$("#portlistTable > tbody").html("");
								var indexCount=0;
							   	$.each(TenantVO.portSubscriberCount,function(key,value) {
							   		
							   		
						   		var createdBy= TenantVO.portCreatedBy[key];
						   		
							   var modifiedOn= TenantVO.portModifiedOn[key];
								var createdOn= TenantVO.portCreatedOn[key];
								
								if (undefined==createdBy || null==createdBy)
									createdBy="";
								if (undefined==modifiedOn || null==modifiedOn)
									modifiedOn="";
								if (undefined==createdOn || null==createdOn)
									createdOn="";
								   	
							   		
							 			var options ='<select id="id_'+key+'" class="form-control form-white tenantClass" name="tenantClass"><option value="">-Select-</option>';
							 			options = options+'<option value="APSFL">APSFL</option>';
							 			$.each(TenantVO.tenantList, function(idx,obj1){
											options = options+'<option value="'+obj1.tenantCode+'">'+obj1.name+" | "+obj1.tenantCode+'</option>';
										})
										options = options+'</select>';
									   	$('#portlistTable> tbody').append('<tr>'
								        +'<td>'+key+'</td>'
								        +'<td>'+options+'</td>'
								        +'<td>'+value+'</td>'
								        +'<td>'+createdOn+'</td>'
								        +'<td>'+createdBy+'</td>'
								        +'<td>'+modifiedOn+'</td>'
								        +'<td hidden>'+oltno+'</td>'
								        +'<td hidden id="id1_'+key+'"></td></tr>');
								   	
								   	if( value != 0) {
								   		var index1;
								   		$.each(TenantVO.portLmoCode,function(key1,value1) {
								   			index1 = key1;
							 		 		if(value1 != "" )
							 		 		{
							 		 			if(key1 == key) {
							 		 			 $("#id_"+key1).val(value1);
							 		 			 $("#id_"+index1).attr("disabled", true).addClass('disable');
							 		 			}
							 		 		}
										});
								   	} else {
								   		$.each(TenantVO.portLmoCode,function(key1,value1) {
								   			if(value1 != "" )
							 		 		{
								   				if(key1 == key)
							 		 			$("#id_"+key1).val(value1);
							 		 		}
								   		});
								   	}
								});
							   	
					 		ajaxindicatorstop();
					 	});
					}
				}
			});
		}
});
	
	

	//Saving Port List
	$('#savePortDtlsButton').click(function(){
		var html='';
		var portDetailsList = [];
		$('#portlistTable > tbody > tr').each(function() {
			var row = $(this);
			var portNo = row.find('td:eq(0)').text();
			var slots = row.find('td:eq(2)').text();
			var createdOn=row.find('td:eq(3)').text();
			var createdBy=row.find('td:eq(4)').text();
			var modifiedOn=row.find('td:eq(5)').text();
			var popOltSerialno=row.find('td:eq(6)').text();

				 var lmocode = $(this).find('select[name="tenantClass"] option:selected').val();
			     var portObj = {
	       			"portNo":portNo,
	       			"lmocode": lmocode,
	       			"createdOn":createdOn,
	       			"createdBy":createdBy,
	       			"modifiedOn":modifiedOn,
	    		    "popOltSerialno": popOltSerialno,
	    		    "slots":slots,
			     };
			   portDetailsList.push(portObj);
			
		});
			portDetailsList = JSON.stringify(portDetailsList);
			
		 	$.ajax({ 
        	     type: 'POST', 
        	     url: 'savePortDetailsList', 
        	     data: portDetailsList,
        	     contentType: 'application/json',
        	     success: function(response) { 
        	    	 if(response !== "" && response !== null){
        	    		 alert(response);
        	    		 location.reload();
        	    	 }
        	     },
        	  });
		 	
		ajaxindicatorstop();
	});
	
	$(document).on('change','#portal_districtid', function() {
		var districtID = $(this).val();
		$.ajax({ 
		     type: 'GET', 
		     url:  'getMandalAndSubsList?stateID=1&districtID='+districtID, 
		     success: function(result){ 
		    	 	$("#portal_mandalid").empty();
		    	 	$("#portal_villageid").empty();
		    	 	$("#portal_mandalid").append('<option value="">-Select-</option>');
		    	 	$("#portal_villageid").append('<option value="">-Select-</option>');
		    	 		var temp = [];
		    			$.each(result, function(key1, value1){
		    				if(key1 == 'Mandals'){
		    					$.each(value1, function(k,v){
							    temp.push({v:v, k:k});
							    });
		    				}
			    		});
						temp.sort(function(a,b){
							   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
							    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
							      return 0;
						});
						$.each(temp, function(key, obj){
							$("#portal_mandalid").append('<option value='+obj.k+'>'+obj.v+'</option>');
						});
		     }
		  });
	});
	
	$('#portal_mandalid').change(function(){
		var districtID = $("#portal_districtid").val();
		var mandalID = $(this).val();
		$.ajax({ 
		     type: 'GET', 
		     url:  'getVillageList?stateID=1&districtID='+districtID+'&mandalID='+mandalID, 
		     success: function(result){ 
		    	 		$("#portal_villageid").empty();
		    	 		$("#portal_villageid").append('<option value="">-Select-</option>');
		    	 	//$("#v_villageID").append('<option value="">-Select-</option>');
		    	 		var temp = [];
		    			$.each(result, function(key1, value1){
							temp.push({v:value1, k:key1});
			    		});
						temp.sort(function(a,b){
							   if(a.v.toLowerCase() > b.v.toLowerCase()){ return 1;}
							    if(a.v.toLowerCase() < b.v.toLowerCase()){ return -1;}
							      return 0;
						});
						$.each(temp, function(key, obj){
							$("#portal_villageid").append('<option value='+obj.k+'>'+obj.v+'</option>');
						});
						/*$('#portal_villageid').SumoSelect({
							 placeholder: "-Select-",
							 triggerChangeCombined : false,
							 search: true
						});*/
		     }
		  });
	});
	 
	
	$(document).on('click','.viewPageTenantCode',function() {
		var i = $(".viewPageTenantCode").index(this);
		$(".viewpageForm:eq("+i+")").attr('action',"showtenantDetails");
		$(".viewpageForm:eq("+i+")").submit();
	});
	
	
	
	
	$("#searchCpeButton").click(function(event) {
		$('#id_container').find('ol').html("");
		var check_cnt = 0;
		
		
		
		if($("#fromDate").val()=="" && $("#toDate").val()== "" && $("#tenantType option:selected").val()== "" && $("#cpeInputStockFile").val()== ""
			&& $("#tenantCode").val()== "" && $("#cpeType option:selected").val()== "" && $("#cpe-Serial-Number").val()== "" ){				
		    $("#id_container").addClass('dberrorMsg');
			$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Atleast one field</li>');
			$("#id_container").show();	
			check_cnt=check_cnt+1;
			
		}
		
		
		  if($("#toDate").val()!=""){	
			  if($("#fromDate").val() == ""){
			    $("#id_container").addClass('dberrorMsg');
				$('#id_container').find('ol').append('<li style="list-style: none;">Please Select From Date</li>');
				$("#id_container").show();	
				check_cnt=check_cnt+1;
			}}
		  if($("#fromDate").val()!=""){	
			  if($("#toDate").val() == ""){
			    $("#id_container").addClass('dberrorMsg');
				$('#id_container').find('ol').append('<li style="list-style: none;">Please Select To Date</li>');
				$("#id_container").show();	
				check_cnt=check_cnt+1;
			}}
		  if(check_cnt == 0)
		  {
			  localStorage.clear();
			  
			$("#cpeform").attr('action',"searchCpeStockDetails");
			$("#cpeform").submit();
			
		  }else{return false;}
	});
	
	
	
	$(document).on('click','.editCpe',function() {
	       var i = $(".editCpe").index(this);
	       $(".editCpeForm:eq("+i+")").attr('action',"editCpe");
	       $(".editCpeForm:eq("+i+")").submit();
   });
	
	$('#CPEDetailsTable').on("click",'input[type=checkbox]',function() {
		var checkStatus = false;
		$('#id_container2').find('ol').html("");
		$('#CPEDetailsTable').find('tr').each(function() {
			if ($(this).find('input[type="checkbox"]').is(':checked')) {
				 checkStatus = true;
			}
		});
		if(checkStatus) {
			$('.cpeInfo').show();
			$('#deleteCPESelected').show();
		} else {
			$('.cpeInfo').hide();
			$('#deleteCPESelected').hide();
		}
	});
	
	$("#updateCPESubmit").click(function(event) {
		var check_cnt = 0;
		$('#id_container2').find('ol').html("");
		if($("#tenantCodee").val()==""){	
			    $("#id_container2").addClass('dberrorMsg');
				$('#id_container2').find('ol').append('<li style="list-style: none;">Please Enter Tenant Code</li>');
				$("#id_container2").show();	
				check_cnt=check_cnt+1;
			}  
		  
		var cpeDetailsList = [];
		var tenantCode = $('#tenantCodee').val();
			$('#CPEDetailsTable').find('tr').each(function() {
				if ($(this).find('input[type="checkbox"]').is(':checked')) {
					
						var cpeSrlno = $.trim($(this).find('#cpeSrlno').text().replace(/[\t\n]+/g,' '));
						var cpeObj = {
				       			"tenantCode":tenantCode,
				       			"cpeSrlno": cpeSrlno,
						     };
						cpeDetailsList.push(cpeObj);
				}
			});
			cpeDetailsList = JSON.stringify(cpeDetailsList);
			if(check_cnt == 0)
			  {
			 	$.ajax({ 
	        	     type: 'POST', 
	        	     url: 'updateCpeDetails', 
	        	     data: cpeDetailsList,
	        	     contentType: 'application/json',
	        	     beforeSend: function() {
						   ajaxindicatorstart("Loading...")
			            },
			            complete: function() {
			               ajaxindicatorstop()
			            },
	        	     success: function(response) { 
	        	    	 if(response !== "" && response !== null){
	        	    		 alert(response);
	        	    		 location.reload();
	        	    	 }
	        	     },
	        	  });
			  }else{
				  return false;
			  }
		});
	
	$('.checkAll').on("click", function () {
	    $("#CPEDetailsTable tr").each( function() {
             $(this).find("input").attr('checked', true); 
             $('.cpeInfo').show();
             $('.checkAll').hide();
             $('#uncheckAll').show()
             $('#deleteCPESelected').show();
	    });
	});
	
	$('#uncheckAll').on("click", function () {
	    $("#CPEDetailsTable tr").each( function() {
             $(this).find("input").attr('checked', false); 
             $('.cpeInfo').hide();
             $('#deleteCPESelected').hide();
             $('#uncheckAll').hide();
             $('.checkAll').show()
	    });
	});
	
		
	$("#deleteCPESelected").click(function(event) {
		
		var cpeDetailsList = [];
		var tenantCode = $('#tenantCodee').val();
			$('#CPEDetailsTable').find('tr').each(function() {
				if ($(this).find('input[type="checkbox"]').is(':checked')) {
					
				    
						var cpeSrlno = $(this).find('.cpeSrlno').text();
						var macAddress = $(this).find('#macAddress').text();
						var cpeObj = {
				   
				       			"cpeSrlno": cpeSrlno,
				       			"macAddress": macAddress
				       			
						     };
						cpeDetailsList.push(cpeObj);
				}
			});
			cpeDetailsList = JSON.stringify(cpeDetailsList);
			
			if ( cpeDetailsList!=null){
			
if (confirm("Are you sure you want to delete selected items ?")) {
  
			 	$.ajax({ 
	        	     type: 'POST', 
	        	     url: 'deleteCpeStocks', 
	        	     data: cpeDetailsList,
	        	     contentType: 'application/json',
	        	     beforeSend: function() {
						   ajaxindicatorstart("Loading...")
			            },
			            complete: function() {
			               ajaxindicatorstop()
			            },
	        	     success: function(response) { 
	        	    	 if(response !== "" && response !== null){
	        	    		 alert(response);
	        	    		 location.reload();
	        	    	 }
	        	     },
	        	  });
			 	
				} else {
				    //dont do anything
				}
			}
		});
	
	$('#tenantCode').keyup(function(){
		  $(this).val($(this).val().toUpperCase());
		});
	
	$('#tenantCodee').keyup(function(){
		  $(this).val($(this).val().toUpperCase());
		});
	
window.onbeforeunload = function() {
	
	    var substring = "Log";
	    
	
		if (!$('h2').text().includes(substring)){
			
		var from_Date = $('#fromDate').val();
		var to_Date = $('#toDate').val();
		var tenant_Type = $('#tenantType option:selected').val();
		var tenant_Code = $('#tenantCode').val();
		var serial_Number = $('#cpe-Serial-Number').val();
		var box_Type = $('#cpeType option:selected').val();
	
		
		if(from_Date !== null )
			sessionStorage.setItem('fromDate', $('#fromDate').val());
		if(to_Date !== null)
			sessionStorage.setItem("toDate", $('#toDate').val());
		if(tenant_Type !== null)
			sessionStorage.setItem("tenantType", $('#tenantType option:selected').val());
		if(tenant_Code !== null)
			sessionStorage.setItem("tenantCode", $('#tenantCode').val());
		if(serial_Number !== null)
			sessionStorage.setItem("serialNumber", $('#cpe-Serial-Number').val());
		if(box_Type !== null)
			sessionStorage.setItem("cpeType", $('#cpeType option:selected').val());
		
		}
	}
	
	
	
	window.onload = function() {
		
		var substring = "Log";
		
		if (!$('h2').text().includes(substring)){
			
		var fromDate = sessionStorage.getItem("fromDate");
		 var toDate = sessionStorage.getItem("toDate");
		 var tenantType = sessionStorage.getItem("tenantType");
		 var tenantCode = sessionStorage.getItem("tenantCode");
		 var serialNumber = sessionStorage.getItem("serialNumber");
		 var cpeType = sessionStorage.getItem("cpeType");
		 
		if (null !== fromDate &&  typeof undefined!== fromDate){

			$('#fromDate').val(fromDate);
		
		}
	   
	    if (null !== toDate && typeof undefined !== toDate) {
	    
	    	$('#toDate').val(toDate);
		}
	   
	    if (null !== tenantType && typeof undefined !== tenantType)
	    	$('#tenantType option:selected').val(tenantType);
	    
	   
	    if (null !== tenantCode && typeof undefined !== tenantCode)
	    	$('#tenantCode').val(tenantCode);
	    
	    
	    if (null !== serialNumber && typeof undefined !== serialNumber)
	    	$('#cpe-Serial-Number').val(serialNumber);
	    
	    if (null !== cpeType && typeof undefined !== cpeType)
	    	$('#cpeType option:selected').val(cpeType);
	    
	    if (sessionStorage.getItem('count')==1){
	    	
	    	sessionStorage.setItem('count', 2);
	    	$("#cpeform").attr('action',"searchCpeStockDetails");
	    	
	    	 
		$("#cpeform").submit();
	    	
		
	    }
	    
		}else{ 
			
			if ($('h2').text().includes(substring)) sessionStorage.setItem('count', 1);	
		}
		
	}

	
});

