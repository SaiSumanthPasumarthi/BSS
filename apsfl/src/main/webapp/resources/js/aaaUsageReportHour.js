$(document).ready(function(){
	// AAA USAGE REPORT BY HOURS
	var hiddenYear = $('#hiddenSelectYear').val();
	var  dat = $('#aaaUsageByHoursTable').dataTable({"sScrollX": "true"});
	// For Listing Year
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

	//aaaUsgaeByHour  Search Button Click
	
	/*$("#aaaUsgaeByIDSearch").click(function() {
		
	    var	monthSelected= '';
	    monthSelected= $('#monthsListId').val(); 
		var yearSelected = '';
		yearSelected= $('#yearListId').val();
		var cafNumber= ''; 
		cafNumber= $('#cafNumSearchId').val();
		if (monthSelected != '' && yearSelected != '') {
			$.ajax({
				url 	    : 'getaaaUsageByHours',
				method	    : 'GET',
				async		:  false,
				contentType : 'application/json',
				data        : {
								monthSelected : monthSelected,
								yearSelected : yearSelected,
								cafSelected : cafNumber
							   },
				success	: function(data) {
					$('#aaaUsageByHourTableBody').html("");
					var tddata ='';
					$.each(data, function(index, value) {
							 tddata = tddata + '<tr><td>'+value.cafSelected+'</td>' + 
							 '<td>'+value.monthSelected+'</td>' +
							 " <td>"+value.zeroTo1+"</td><td>"+value.up0to1+"</td> " +
							 "<td>"+value.oneTo2+"</td><td>"+value.up1to2+"</td>" +
							 " <td>"+value.twoTo3+"</td> <td>"+value.up2to3+"</td> " +
							 "<td>"+value.threeTo4+"</td><td>"+value.up3to4+"</td>" +
							 " <td>"+value.fourTo5+"</td><td>"+value.up4to5+"</td>"+
							 "<td>"+value.fiveTo6+"</td><td>"+value.up5to6+"</td>" +
							 " <td>"+value.sixTo7+"</td><td>"+value.up6to7+"</td> " +
							 "<td>"+value.sevenTo8+"</td><td>"+value.up7to8+"</td>" +
							 " <td>"+value.eightTo9+"</td><td>"+value.up8to9+"</td>" +
							 " <td>"+value.nineTo10+"</td><td>"+value.up9to10+"</td> " +
							 "<td>"+value.tenTo11+"</td><td>"+value.up10to11+"</td>" +
							 " <td>"+value.elevenTo12+"</td><td>"+value.up11to12+"</td> " +
							 "<td>"+value.twelveTo13+"</td> <td>"+value.up12to13+"</td>" +
							 " <td>"+value.thirteenTo14+"</td><td>"+value.up13to14+"</td>" +
							 " <td>"+value.fourteenTo15+"</td><td>"+value.up14to15+"</td>" +
							 " <td>"+value.fifteenTo16+"</td><td>"+value.up15to16+"</td>" +
							 "<td>"+value.sixteenTo17+"</td><td>"+value.up16to17+"</td>" +
							 " <td>"+value.seventeenTo18+"</td><td>"+value.up17to18+"</td> " +
							 "<td>"+value.eighteenTo19+"</td><td>"+value.up18to19+"</td> " +
							 "<td>"+value.nineteenTo20+"</td><td>"+value.up19to20+"</td> " +
							 "<td>"+value.twentyTo21+"</td><td>"+value.up20to21+"</td> " +
							 "<td>"+value.twenty1To22+"</td><td>"+value.up21to22+"</td> " +
							 "<td>"+value.twenty2To23+"</td><td>"+value.up22to23+"</td> " +
							 "<td>"+value.twenty3To24+"</td><td>"+value.up23to24+"</td> " +
							 "<td>"+value.totalUsageOnDay+"</td>" +
							 " <td>"+value.uploadTotal+"</td> " +
							 "<td>"+value.totalUsage+"</td>" +
							 " </tr>";
					})
					
						$(tddata).appendTo('#aaaUsageByHourTableBody');
				},
				error      : function() {
					alert("error");
				}
			});
		} else
			alert("Please Select Month and Year...!!!");
	});*/
	
	
$("#downLoadAAAByHours").click(function() {
		
	    var	monthSelected= '';
	    monthSelected= $('#monthsListId').val(); 
		var yearSelected = '';
		yearSelected= $('#yearListId').val();
		var cafNumber= ''; 
		cafNumber= $('#cafNumSearchId').val();
		if (monthSelected != '' && yearSelected != '') {
			$('#aaaUsageByHourReport_Form').attr("action", "getaaaUsageByHoursDownload" );
			$('#aaaUsageByHourReport_Form').submit();
		} else
			alert("Please Select Month and Year...!!!");
	});

$("#aaaUsgaeByIDSearch").click(function() {
    var	monthSelected= '';
    monthSelected= $('#monthsListId').val(); 
	var yearSelected = '';
	yearSelected= $('#yearListId').val();
	var cafNumber= ''; 
	cafNumber= $('#cafNumSearchId').val();
	if (monthSelected != '' && yearSelected != '') {
		$('#aaaUsageByHourReport_Form').attr("action", "getaaaUsageByHours" );
		$('#aaaUsageByHourReport_Form').submit();
	} else
		alert("Please Select Month and Year...!!!");
});

$('.viewHr').on('click', function(){
	$('#hsiHourUsagePopUoDiv').html("");
	var donLoad = [];
	for(var i =0; i<24; i++){
		var j = i;
		donLoad[i]= $(this).closest('tr').find('.'+ ++j +'dow').text();
	}
	
	var upLoad = [];
	for(var i =0; i<24; i++){
		var j = i;
		upLoad[i]= $(this).closest('tr').find('.'+ ++j +'up').text();
	}
	
	var subCuont = [];
	for(var i =0; i<24; i++){
		var j = i;
		subCuont[i]= $(this).closest('tr').find('.'+ ++j +'sub').text();
	}

	
var html="";	
html = '<table class = "table table-alt ">'
	+'<thead>'
	+'<tr>'
	+'<th>Hour</th>'
	+'<th>Download [GB]</th>'
	+'<th>Upload [GB]</th>'
	+'<th>Total Usage [GB]</th>'
	+'<th>Subcribers Count</th>'
	+'</tr>'
	+'</thead>'
	+'<tbody>';
	for(var i =0; i<24; i++){
		var j = i;
		html = html + '	<tr>'
		+'	<td>'+i+'-'+ ++j +' Hour </td>'
		+'	<td>'+donLoad[i]+'</td>'
		+'<td>'+upLoad[i]+'</td>'
		+'<td>'+ (parseFloat(upLoad[i])+parseFloat(donLoad[i])).toFixed(3) +'</td>'
		+'<td>'+subCuont[i]+'</td>'
		+'</tr>'
	}
	
	html = html + '</tbody>'
	+'</table>';
	$('#hsiHourUsagePopUoDiv').append(html);
});

//six hours wise month usage.

$('.view6Hr').on('click', function(){
	$('#hsiHourUsagePopUoDiv2').html("");
	var donLoad = [];
	for(var i =0; i<24; i++){
		var j = i;
		donLoad[i]= $(this).closest('tr').find('.'+ ++j +'dow').text();
	}
	
	var upLoad = [];
	for(var i =0; i<24; i++){
		var j = i;
		upLoad[i]= $(this).closest('tr').find('.'+ ++j +'up').text();
	}
	
	var subCuont = [];
	for(var i =0; i<24; i++){
		var j = i;
		subCuont[i]= $(this).closest('tr').find('.'+ ++j +'sub').text();
	}
	
var html="";	
html = '<table class = "table table-alt ">'
	+'<thead>'
	+'<tr>'
	+'<th>Hour</th>'
	+'<th>Download [GB]</th>'
	+'<th>Upload [GB]</th>'
	+'<th>Total Usage [GB]</th>'
	+'<th>Subcribers Count</th>'
	+'</tr>'
	+'</thead>'
	+'<tbody>';
var ssubCount = 0;
var supLoad = 0.00;
var sdoload = 0.00;
for(var i = 0; i<24; i++){
	sdoload = (sdoload + parseFloat(donLoad[i]));
	supLoad = (supLoad + parseFloat(upLoad[i]));
	ssubCount = (ssubCount +parseInt(subCuont[i]));
	
	if(i == 5 || i == 11 || i == 17 || i == 23){
	html = html + '	<tr>'
	+'	<td>'+(i - 5)+'-'+ (i + 1) +' Hour </td>'
	+' <td>'+parseFloat(sdoload).toFixed(3)+'</td>'
	+'<td>'+parseFloat(supLoad).toFixed(3)+'</td>'
	+'<td>'+ (parseFloat(supLoad)+parseFloat(sdoload)).toFixed(3) +'</td>'
	+'<td>'+ssubCount+'</td>'
	+'</tr>'
	ssubCount = 0;
	supLoad = 0.00;
	sdoload = 0.00;
	
	}
}	

	html = html + '</tbody>'
	+'</table>';
	$('#hsiHourUsagePopUoDiv2').append(html);
});

$('.viewHrCust').on('click', function(){
	$('#hsiHourUsagePopUoDiv').html("");
	var donLoad = [];
	for(var i =0; i<24; i++){
		var j = i;
		donLoad[i]= $(this).closest('tr').find('.'+ ++j +'dow').text();
	}
	
	var upLoad = [];
	for(var i =0; i<24; i++){
		var j = i;
		upLoad[i]= $(this).closest('tr').find('.'+ ++j +'up').text();
	}
	
var html="";	
html = '<table class = "table table-alt ">'
	+'<thead>'
	+'<tr>'
	+'<th>Hour</th>'
	+'<th>Download [MB]</th>'
	+'<th>Upload [MB]</th>'
	+'<th>Total Usage [MB]</th>'
	+'</tr>'
	+'</thead>'
	+'<tbody>';
	for(var i =0; i<24; i++){
		var j = i;
		html = html + '	<tr>'
		+'	<td>'+i+'-'+ ++j +' Hour </td>'
		+'	<td>'+donLoad[i]+'</td>'
		+'<td>'+upLoad[i]+'</td>'
		+'<td>'+ (parseFloat(upLoad[i])+parseFloat(donLoad[i])).toFixed(3) +'</td>'
		+'</tr>'
	}
	
	html = html + '</tbody>'
	+'</table>';
	 $('#hsiHourUsagePopUoDiv').append(html);
});

//six hours wise customer usage.

$('.viewSixHrCust').on('click', function(){
	$('#hsiHourUsagePopUoDiv3').html("");
	var donLoad = [];
	for(var i =0; i<24; i++){
		var j = i;
		donLoad[i]= $(this).closest('tr').find('.'+ ++j +'dow').text();
	}
	
	var upLoad = [];
	for(var i =0; i<24; i++){
		var j = i;
		upLoad[i]= $(this).closest('tr').find('.'+ ++j +'up').text();
	}
	
var html="";	
html = '<table class = "table table-alt ">'
	+'<thead>'
	+'<tr>'
	+'<th>Hour</th>'
	+'<th>Download [MB]</th>'
	+'<th>Upload [MB]</th>'
	+'<th>Total Usage [MB]</th>'
	+'</tr>'
	+'</thead>'
	+'<tbody>';

var supLoad = 0.00;
var sdoload = 0.00;
for(var i = 0; i<24; i++){
	sdoload = (sdoload + parseFloat(donLoad[i]));
	supLoad = (supLoad + parseFloat(upLoad[i]));
	
	
	if(i == 5 || i == 11 || i == 17 || i == 23){
	html = html + '	<tr>'
	+'	<td>'+(i - 5)+'-'+ (i + 1) +' Hour </td>'
	+' <td>'+parseFloat(sdoload).toFixed(3)+'</td>'
	+'<td>'+parseFloat(supLoad).toFixed(3)+'</td>'
	+'<td>'+ (parseFloat(supLoad)+parseFloat(sdoload)).toFixed(3) +'</td>'
	+'</tr>'
	
	supLoad = 0.00;
	sdoload = 0.00;
	
	}
}	
	
	html = html + '</tbody>'
	+'</table>';
	 $('#hsiHourUsagePopUoDiv3').append(html);
});
	 
});



 
