/*STB Change*/
/* 
 @author Srinivas V
 @since Feb 08 2017
 */
$(document).ready(function() {
					$("#cafListTableId").dataTable();
					$("#cafListOnuId").dataTable(); 
					$("#updateGridStbId").hide(); 
					$("#updateGridOnuId").hide();
					 

					/* Update Stb Mac AddressDetails */

					$(document).on("click","input:radio[name=custListSelect]",function() {
										$("#hideerrorStatusId").hide();
										 
										var cafNum = $(this).closest("tr").find('td:eq(2)').text();
										$('#stbDetailsId').empty();
										/* Update Stb Mac AddressDetails */
										$.ajax({
													url : 'getStbDetailsByCafNo',
													data : { cafNum : cafNum },
													contentType : 'application/json',
													async : false,
													success : function(ChangeStbHelperDTO) {
														var divHtml = ' ';
														var count=1;
														
														$.each(ChangeStbHelperDTO.changeStbList,function( index , value ) {
																			divHtml += '<div class="panel-group"><div class="panel panel-default">'
																					+ '<div class="panel-heading"><strong>IPTV # '
																					+ count
																					+ '</strong></div><div class="panel-body">'
																				    +'<div class="row"><form id="updateStbForm'
																					+ index
																					+ '" method="post" class="myform" ><div class="col-sm-3">'
																					+ '<div class="form-group">'
																					+ '<label class="control-label">CAF Number</label>'
																					+ '<input type="text" name="parentCafno" class="form-control form-white number" value='
																					+ value.parentCafno
																					+ ' readonly >'
																					+ '</div></div>'
																					+ '<div class="col-sm-3">'
																					+ '<div class="form-group">'
																					+ '<label class="control-label">Subscriber Code</label>'
																					+ '<input type="text" id="nwSubCode'
																					+ index
																					+ '" class="form-control form-white number" name="nwSubCode" readonly  value='
																					+ value.nwSubCode
																					+ ' >'
																					+ '</div></div>'
																					+ '<div class="col-sm-3">'
																					+ '<div class="form-group">'
																					+ '<label class="control-label">Old IPTV Serial Number</label>'
																					+ '<input type="text" id="oldStbSerialNum'
																					+ index
																					+ '" class="form-control form-white number" name="oldStbSerialNum" readonly  value='
																					+ value.oldStbSerialNum
																					+ ' >'
																					+ '</div></div>'
																					+ '<div class="col-sm-3">'
																					+ '<div class="form-group">'
																					+ '<label class="control-label">Old IPTV MAC Address</label>'
																					+ '<input type="text" id="oldStbMacAddr'
																					+ index
																					+ '" class="form-control form-white number" name="oldStbMacAddr" readonly  value='
																					+ value.oldStbMacAddr
																					+ ' >'
																					+ '</div></div>'
																					+ '<div class="col-sm-3">'
																					+ '<div class="form-group">'
																					+ '<label class="control-label">New IPTV Serial Number<span style="color: red;">*</span></label>'
																					+ '<input name="newStbSerialNum" id="newStbSerialNum'
																					+ index
																					+ '" type="text" class="form-control form-white newStbSerialNum " required="required"  placeholder="Enter New IPTV Serial Number">'
																					+ '</div></div>'
																					+ '<div class="col-sm-3"><div class="form-group">'
																					+ '<label class="control-label">New IPTV MAC Address</label>'
																					+ '<input name="newStbMacAddr" id="newStbMacAddr'
																					+ index
																					+ '" type="text" class="form-control form-white newStbMacAddr" required="required"  readonly  >'
																					+ '<input type="hidden" name="stbCafNo" value='
																					+ value.stbCafNo
																					+ '>'
																					+ '<input type="hidden" name="custId" value = '+value.custId+'>'
																					+ '<input type="hidden" name="oldStbProfileId" value = '+value.oldStbProfileId+'>'
																					+ '</div></div>'
																					+ '<div class="col-sm-3"><div class="form-group">'
																					+ '<label class="control-label">Reason For Change<span style="color: red;">*</span></label>'
																					+ '<select id="changePurpose'
																					+ index
																					+ '"  name = "changePurpose" class="form-control form-white" required = "required" >'
																					+ '<option selected="selected" value="">-Select-</option>';
																					$.each(ChangeStbHelperDTO.lovChangeReasonlist,function( index , value ) {
																						divHtml = divHtml + '<option value="'+value.lovSeq+'">'+value.lovValue+'</option>';
																					});
																					
																					divHtml = divHtml +  '</select>'
																					+ '</div></div>'
																					+ '<div class="col-sm-3"><div class="form-group">'
																					+ '<label class="control-label">Change Comment<span style="color: red;">*</span></label>'
																					+ '<textarea name="changeComment" id="changeCommentId"'
																					+ 'class="form-control form-white " required="required"    placeholder="Enter Change comments">'
																					+ '</textarea>'
																					+ '</div></div>'
																					+ '<br/><div class="col-sm-3">'
																					+ '<button type="button"  class="btn btn-embossed btn-primary updateStb" value="'
																					+ index
																					+ '">Submit</button>'
																					+ '</div></form> </div><br/>'
																					+'</div</div>'
																					count+=1;
																					
																		});
														divHtml = divHtml + '</div>';
														
														$('#stbDetailsId').append(divHtml);
														$("#updateGridStbId").show();
													}
												}); //End Of Ajax call for /getStbDetailsByCafNo method 
									});// End of On click Event For to get STB's by using Caf NUmber
					
					
					$(document).on(	'change' , '.newStbSerialNum' , function() {
						var index = $(this).index('.newStbSerialNum');
						var newStbserial = $(".newStbSerialNum:eq(" + index+ ")").val();
					 
							$.ajax({
										url : 'getNewStdStatus',
										data : { newStbserial : newStbserial },
										contentType : 'application/json',
										async : false,
										success : function(data) {
													 
											if (data.length < 26) {
												 $('#newStbMacAddr' + index).val(data);
												 if ($('#changePurpose' + index).val() == ""){
												 $('#errorMsgId').show().text( "Please Select Change Reason" );
													$("#errorMsgId").delay(3000).fadeOut(500);
												 }
											} else {
												$('#errorMsgId').show().text(data);
												$("#errorMsgId").delay(3000).fadeOut(500);
												$('#newStbMacAddr' + index).val('');
												$('.newStbSerialNum' + index).val('');
												return false;
											}
										}
									});
					});
					
					$(document).on('click','.updateStb',function() {
						var index = $(this).val();
						var newStbSeries = $('#newStbMacAddr' + index).val();
						var oldStbSeries = $('#oldStbSerialNum' + index).val();
						
						if (newStbSeries == '' || newStbSeries == oldStbSeries ){
							$('#errorMsgId').show().text(" Please enter New STB Series ");
							$("#errorMsgId").delay(3000).fadeOut(500);
							return false;
						  }
						else if ($('#changePurpose' + index).val() == ""){
							$('#errorMsgId').show().text(" Please select Reason for Change. ");
						$("#errorMsgId").delay(3000).fadeOut(500);
						return false;
						}
						else  
						{
							$("#updateStbForm" + index).attr('action',"updatestbDetails");
							$("#updateStbForm" + index).submit();
						}
					});
 
					/* ONU change Update Mac Address Change */
					
					$(document).on("click","input:radio[name=custListSelected]",function() {
					             	$("#hideerrorStatusONUId").hide();
									var cafNumber = $(this).closest("tr").find('td:eq(2)').text();
										$('#onuDetailsId').empty();
										var divOnuHtml = ' ';
										/*Getting Details of Onu by using CAF number */
										$.ajax({
											url : 'getOnuDetailsByCafNo',
											data : { cafNumber : cafNumber },
											contentType : 'application/json',
											async : false,
											success : function(ChangeOnuHelperDTO) {
												var divOnuHtml = ' ';
												 if(ChangeOnuHelperDTO.changeOnuList.length > 0) {
												$.each(ChangeOnuHelperDTO.changeOnuList,function( index , value ) {
												
													divOnuHtml += '<div class="row"><form id="updateOnuForm'
														+ '" method="post" class="onuUpdateForm"  action="updateOnuChangeDetails"><div class="col-sm-3">'
														+ '<div class="form-group">'
														+ '<label class="control-label">CAF Number</label>'
														+ '<input type="text" name="cafNumberOnuChange" class="form-control form-white number" value = "'+value.cafNumberOnuChange+'" readonly >'
														+ '</div></div>'
														+ '<div class="col-sm-3"><div class="form-group">'
														+ '<label class="control-label">Subscriber Code</label>'
														+ '<input type="text" name="nwSubCode" id="nwSubCode" class="form-control form-white" readonly value = "'+value.nwSubCode+'">'
														+ '</div></div>'
														+ '<div class="col-sm-3"><div class="form-group">'
														+ '<label class="control-label">Old ONU Serial Number</label>'
														+ '<input type="text" name="oldOnuSerialNum" id="oldOnuSerailID" class="form-control form-white number" readonly value = "'+value.oldOnuSerialNum+'">'
														+ '</div></div>'
														+ '<div class="col-sm-3">'
														+ '<div class="form-group">'
														+ '<label class="control-label">Old ONU Mac Address</label>'
														+ '<input type="text" id="oldOnuMacAddr'
														+ '" class="form-control form-white" name="oldCpeOnuMacAddress" readonly  value = "'+value.oldCpeOnuMacAddress+'" >'
														+ '</div></div>'
														+ '<div class="col-sm-3"><div class="form-group">'
														+ '<label class="control-label">ONU Profile model</label>'
														+ '<input type="text" name="onuModel" class="form-control form-white " readonly value = "'+value.onuModel+'">'
														+ '</div></div>'
														+ '<div class="col-sm-3"><div class="form-group">'
														+ '<label class="control-label">New ONU Serial Number <span style="color: red;">*</span></label>'
														+ '<input name="newOnuSerialNum" id="newOnuSerialId'
														+ '" type="text" class="form-control form-white " required="required"    placeholder="Enter New Onu Serial*">'
														+ '</div></div>'
														+ '<div class="col-sm-3"><div class="form-group">'
														+ '<label class="control-label">New Onu MAC Address</label>'
														+ '<input type="text" name="newCpeOnuMacAddress" id ="newCpeOnuMacAddress" class="form-control form-white" readonly ">'
														+ '</div></div>'
														+ '<div class="col-sm-3"><div class="form-group">'
														+ '<label class="control-label">Reason For Change<span style="color: red;">*</span></label>'
														+ '<select id="changePurposeOnu'
														+ '"  name="changePurpose" class="form-control form-white" required="required">'
														+ '<option selected="selected" value = "">-Select-</option>'
														$.each(ChangeOnuHelperDTO.lovChangeReasonlist,function( index , value ) {
															divOnuHtml = divOnuHtml + '<option value="'+value.lovSeq+'">'+value.lovValue+'</option>';
														});
														divOnuHtml = divOnuHtml +  '</select>'
														+ '</div></div>'
														+ '<div class="col-sm-3"><div class="form-group">'
														+ '<label class="control-label">Change Comments <span style="color: red;">*</span></label>'
														+ '<textarea name="changeComment" id="changecommentId"'
														+ 'class="form-control form-white " required="required" placeholder="Enter Change Comments">'
														+ '</textarea>'
														+ '</div></div>'
														+ '<input type="hidden" name="cafsCustId" value = "'+value.cafsCustId+'">'
														+ '<input type="hidden" name="profileIdOnu" value = "'+value.profileIdOnu+'">'
														+ ' <div class="col-sm-12"><div class="form-group">'
														+ '<label class="control-label">  </label>'
														+ '<br/><br/><button type="submit" class="btn btn-embossed btn-primary pull-right " value="'
														+ '">Submit</button>'
														+ '</div></div></form> </div>'
													 
												});
											}
												$('#onuDetailsId').append(divOnuHtml);
												$("#updateGridOnuId").show();
											}
											
										});// ajax call for /getOnuDetailsByCafNo method
									});// End of document on click for updation 
					
					
					
					/* Checking for  ONU Available for LMO and LMO partners of Mso*/
					$(document).on(	'change' , '#newOnuSerialId' , function() {
						 
						var oldOnuSerialNum = $('#oldOnuSerailID').val();
						var newOnuSerialNum=$('#newOnuSerialId').val();
						$.ajax({
							url : 'getOnuStatus',
							data : { newOnuSerialNum : newOnuSerialNum ,oldOnuSerialNum : oldOnuSerialNum},
							contentType : 'application/json',
							async : false,
							success : function(data) {

								if (data.length < 30) {
									 $('#newCpeOnuMacAddress').val(data);
									 if ($('#changePurposeOnu').val() == ""){
									 $('#errorMsgId').show().text( "Please Select Change Reason" );
										$("#errorMsgId").delay(3000).fadeOut(500);
									 }
								} else {
									$('#errorMsgId').show().text(data);
									$("#errorMsgId").delay(3000).fadeOut(500);
									 
									return false;
								}
							}
						});//Ajax call for /getOnuStatus method
					});//document On Change Function for ONU Serial Change text box (newOnuSerialId)
					
			
					/* Termination of CAFS  */
					var cafsList = new Array();
					var tenantCode = $('#tenantCode').val();
					
					$('#cafListTableId tbody tr').on("click",'input[type=checkbox]',function() {
						if ($(this).is(":checked")) {
							var checkId = $(this).val();
							cafsList.push(checkId);
						}else{
							var checkId = $(this).val();
							cafsList = jQuery.grep(cafsList, function(value) {
								  return value != checkId;
								});
						}
						if(cafsList.length > 0)
							$('#submit_termination').prop('disabled',false);
						else
							$('#submit_termination').prop('disabled',true);
					});/* End of Termination of CAFS    */
					
					/* Enabling and disabling All check boxes  */
					$('#check_All').on("click",function() {
						if ($(this).is(":checked")) {
							cafsList = new Array(); 
							cafsList = $('#cafNos_termination').val();
							$('.check_indi').prop('checked',true);
							$('#submit_termination').prop('disabled',false);
						}
						else{
							cafsList = new Array();
							$('.check_indi').prop('checked',false);
							$('#submit_termination').prop('disabled',true);
						}
							
					});/* End Of Enabling and disabling All check boxes */
					
					/* submitting CAF's  */
					$('#submit_termination').on("click",function() {
						
						if ($("#approvedby").val() == "" )
						{
							alert("Please enter Approvedby Name");
							return false;
						 }
						if ($("#approvedby").val() == " " )
						{
							alert("Please enter valid Approvedby Name");
							return false;
						 }

						$('#submit_termination').prop('disabled',true);
						var approvedby = $('#approvedby').val();
						
						ajaxindicatorstart("Loading ....");
						
						$.ajax({
							url : 'terminateCafs',
							data : {cafsList : cafsList.toString(), tenantCode : tenantCode, approvedby : approvedby},
							contentType : 'application/json',
							async : false,
							success : function(data) {
								alert(data);
								location.reload();
								$('.check_indi').prop('disabled',true);
								$('#check_All').prop('disabled',true);
							},
							error : function(data){
								$('.check_indi').prop('checked',false);
								$('#check_All').prop('checked',false);
								alert(data);
								location.reload();
							}
						});//Ajax call for sending CAFs
						ajaxindicatorstop();
					});/* End Of submitting CAF's  */
					
});//document Ready function





