<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page session="false"%>
<html>
<head>
<title>Assign Village</title>
<script src="./resources/js/jquery.autocomplete.min.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">
</head>



<script type="text/javascript">
	$(document).ready(function() {
getMandal();
		function getMandal () {
		
		var districtId = $('#popdistrict').val();

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
		}
		
		$('#popdistrict').change(function() {
			
		});



		$("#save_village").click(function(event) {
			
			var check_cnt=0;
			
			
			/* if( $("#popdistrict option:selected").val()== "--Select--" &&  $("#popmandal option:selected").val()== "--Select--"  && $("#assign_village").val()== "" )
				{				
			    alert("Please enter atleast one field");
			    check_cnt++;
				
			}
			
			
			  if($("#popdistrict option:selected").val()!= "--Select--" &&  $("#popmandal option:selected").val()== "--Select--"){	
				  alert("Please Select a mandal");
				  check_cnt++;
				}

				  if($("#popdistrict option:selected").val()!= "--Select--" &&  $("#popmandal option:selected").val()!= "--Select--"  && $("#assign_village").val()== "" ){	
				  alert("Please Enter Village Name");
				  check_cnt++;
				}

				  if(check_cnt == 0)
			  {
				 
				$("#assign_village_form").attr('action',"assignvillage");
				$("#assign_village_form").submit();
				
			  }else{return false;} 
 */

			if($("#popdistrict option:selected").text()!= "--Select--" &&  $("#popmandal option:selected").text()!= "--Select--" && $("#assign_village").val()!= ""){	
				if (confirm("Are you sure you want add this new village?")) {
					
					$("#assign_village_form").attr('action',"assignvillage");
					$("#assign_village_form").submit();
				}else{return false;}
			} 
		});
		
		



		
	
		
});


	
	
</script>


<body>
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>Assign Village</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">HOME</a></li>
						<li class="active">Assign Village</li>
					</ol>
				</div>
				<label id="error" style="text-align: center; color: red;"></label>
				<div class="successmsg"></div>
			   </div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">

							
							<form role="form" class="form-validation" name="assignVillage"
								id="assign_village_form" action="#" method="post">
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">District</label>
											<div class="option-group">
												<select name="districtuid" id="popdistrict" required="required"
													class="form-control form-white">
													<option value="">--Select--</option>

													<c:forEach var="district" items="${districtList}">
														<c:choose>
															<c:when
																test="${not empty districtuid && district.districtUid == districtuid}">
																<option value="${district.districtUid}" selected>${district.districtName}</option>
															</c:when>
															<c:otherwise>
																<option value="${district.districtUid}">${district.districtName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</select>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Mandal</label>
											<div class="option-group">

												<select name="mandalslno" id="popmandal" required="required"
													class="form-control form-white">

													<option value="">--Select--</option>
													<c:forEach var="mandal" items="${mandalList}">
														<c:choose>
															<c:when
																test="${not empty mandalslno && mandal.mandalSlno == mandalslno && mandal.districtUid == districtuid}">
																<option value="${mandal.mandalSlno}" selected>${mandal.mandalName}</option>
															</c:when>
															<c:otherwise>
																<option value="${mandal.mandalSlno}">${mandal.mandalName}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								
								
								
								
								<div class=" col-sm-3 ">
													<label class="control-label">Enter Village Name<span style="color: red;">*</span></label>
													
													 <input type="text" name="villagename" id="assign_village" value="" class="form-control form-white" required="required" />
													
												</div>
												
												<div class="col-sm-12 "></div>
													<div class="col-sm-2 ">
										<div class="form-group">
											<label class="control-label hide-mobile">&nbsp;</label>
											<div class="option-group">
												<button class="btn btn-embossed btn-primary" type="submit"
													id="save_village">
													SAVE
												</button>
											</div>
										</div>
									</div>
								 <input type="hidden" name="districtList" id="district_List" value="${districtList}" />
							</form>
							<c:if test="${not empty message}">
								<center>
									<font color='green' size="3">${message}</font>
								</center>
							</c:if>
							
							<c:if test="${not empty message1}">
								<center>
									<font color='red' size="3">${message1}</font>
								</center>
							</c:if>

							
								
								
								
								</div>
						
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	
</body>
</html>
