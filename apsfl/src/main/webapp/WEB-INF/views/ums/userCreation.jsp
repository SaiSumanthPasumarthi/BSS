<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<section>
	<!-- BEGIN MAIN CONTENT -->
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Create<strong> User</strong></h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./home.do">Home</a></li>
						<li class="active">Creating New User</li>
					</ol>
				</div>
				<div id="id_container" class="error" width="100%" align="center" style="color: red"><ol id="id_ol"></ol></div>
				<div id="id_container1" class="success" width="100%" align="center" style="color: green"><ol id="id_ol"></ol></div>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<form:form action="#" name="usersDTO" id="rolesFormID" method="post" commandName="usersDTO">
								<input type="hidden" value="<%=(String) request.getSession().getAttribute("domain")%>" id="domainHiddenID" />
								<input type="hidden" name="logginIDHidden" value="<%=(String) request.getSession().getAttribute("loginID")%>" id="logginIDHidden" />
								<input type="hidden" name="tenantCode" value="${tenantcode}" id="tenCodeID" />
								<input type="hidden" name="statusHiddenID" id="statusHiddenID" />
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Enter New User Details</h3>
											</div>
											<div class="panel-content">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Selected Tenant</label>
															<input type="text" name="tenantName" id="tenantID" class="form-control form-white" readonly="readonly" value="${tenantname}" required></input>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Select Role<span style="color: red;">*</span></label>
															<div class="option-group">
																<form:select path="roleName" id="roleNameSelectID" class="form-control form-white">
																	<form:option value="-1" label="--Select a Role--" />
																	<form:options items="${rolesListMap}" />
																</form:select>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Name <span style="color: red;">*</span></label>
															<input type="text" name="userName" id="userNameID" maxlength="50" class="form-control form-white charectersonly" placeholder="Enter User Name" required ></input>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Primary Phone Number<span style="color: red;">*</span></label>
															<input type="text" name="mobile1" id="mobile1ID" class="form-control form-white number" maxlength="10"  placeholder="Enter Primary Phone Number" required></input>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Secondary Phone Number</label>
															<input type="text" name="mobile2" id="mobile2ID" class="form-control form-white number" maxlength="10"  placeholder="Enter Secondary Phone Number"></input>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Primary Email Id<span style="color: red;">*</span></label>
															<input type="text" name="emailID1" id="emailID1ID" class="form-control form-white" maxlength="100" pattern="[^ @]+@[^ @]+.[a-z]+" placeholder="Enter Primary Email Id" required></input>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Secondary Email Id</label>
															<input type="text" name="emailID2" id="emailID2ID" class="form-control form-white" maxlength="100" pattern="[^ @]+@[^ @]+.[a-z]+" placeholder="Enter Secondary Email Id"></input>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Reporting Manager<span style="color: red;">*</span></label>
															<div class="option-group">
																<select name="rmUserID" id="reportingmanagerID" class="form-control form-white">
																	<c:forEach var="rwuserlist" items="${rwUsersList}">
																		<option value="${rwuserlist.loginID}">${rwuserlist.userName}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">Login Id<span style="color: red;">*</span></label>
															<input type="text" name="loginID" maxlength="50" id="loginElemID" class="form-control form-white" placeholder="Enter Login Id" required></input>
														</div>
													</div>

												</div>
												<div class="row">
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">Active ?<span style="color: red;">*</span></label>
															<input type="checkbox" style="width: 20px;height: 20px;" name="status" value="1" id="statusID" class="form-white" disabled="disabled" checked="checked"></input>
														</div>
													</div>
													<div class="col-sm-7">
														<div class="form-group"></div>
													</div>
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">&nbsp;</label>
															<button class="btn btn-embossed btn-success" id="saveID" value="Save"><i class="icon-plus"></i>Add New User</button>
															<button class="btn btn-embossed btn-success" id="updateID" value="Update"><i class="icon-plus"></i>Update User</button>
															<button class="btn btn-embossed btn-danger" id="cancelID" value="Back"><i class="icon-plus"></i>Cancel</button>
															<div class="option-group">&nbsp;</div>
														</div>
													</div>
												</div>
												<!-- END ROW INNER-->
												<!-- <div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">&nbsp;</label>
															<button class="btn btn-embossed btn-success" id="saveID" value="Save"><i class="icon-plus"></i>Add New User</button>
															<button class="btn btn-embossed btn-success" id="updateID" value="Update"><i class="icon-plus"></i>Update User</button>
															<button class="btn btn-embossed btn-danger" id="cancelID" value="Back"><i class="icon-plus"></i>Cancel</button>
															<div class="option-group">&nbsp;</div>
														</div>
													</div>
													<div id="id_container" class="error"><ol id="id_ol"></ol></div>
													<div id="id_container1" class="success"><ol id="id_ol"></ol></div>
												</div> -->
												<!-- END ROW INNER-->
												<div class="row">
													<div class="col-sm-12 enable-search">
														<table class="table table-alt" id="resultList">
															<thead>
																<tr>
																	<th>User Login Id</th>
																	<th>Role Name</th>
																	<th>User Name</th>
																	<th>Primary Phone No.</th>
																	<th>Primary Email Id</th>
																	<th>Active.?</th>
																	<th>Edit</th>
																	<!-- <th>Reporting Manager</th> -->
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${usersList}" var="usersVar" varStatus="status">
																	<tr>
																		<td style="word-break:break-all;width:200px;"><input type="hidden" name="loginIDHiddenID" value="${usersVar.loginID}" id="loginIDHiddenID" class="loginIDHiddenClass" /> <c:out value="${usersVar.loginID}" /></td>
																		<td style="word-break:break-all;width:200px;"><c:out value="${usersVar.roleName}" /></td>
																		<td style="word-break:break-all;width:200px;"><c:out value="${usersVar.userName}" /></td>
																		<td style="width:100px;"><c:out value="${usersVar.mobile1}" /></td>
																		<td style="word-break:break-all;width:300px;"><c:out value="${usersVar.emailID1}" /></td>
									                                		<c:if test="${usersVar.status==1}">
									                                	<td><input type="checkbox" style="width: 20px;height: 20px;" checked="checked" disabled /></td>
									                                	</c:if>
									                                	<c:if test="${usersVar.status==0}">
									                                	<td><input type="checkbox" style="width: 20px;height: 20px;" disabled /></td>
									                                	</c:if>
																		<td><a class="btn btn-sm btn-dark editAnchorClass" data-toggle="tooltip" data-rel="tooltip" data-original-title="Edit"><i class="icon-note"></i></a>
																			<!-- <a class="editAnchorClass"><img src="./resources/images/Edit.png" width="24" height="24"></a> -->
																			<%-- <input type="hidden" name="loginIDHiddenID" id ="loginIDHiddenID"  value="${usersVar.loginID}"/> --%>
																		</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
													<div>
														<div id="paginationDivID"></div>
													</div>
												</div>

											</div>
										</div>
									</div>
								</div>
							</form:form>
							<!-- END FORM -->
						</div>
						<!-- END MAIN PANEL CONTENT -->
					</div>
					<!-- END MAIN PANEL -->
				</div>
				<!-- HERE COMES YOUR CONTENT -->
			</div>
			<!-- END MAIN ROW -->
			<div class="clear"></div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>
	<!-- END MAIN CONTENT -->
	<!-- END FOOTER -->
</section>
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a>

<script>
	$(document) .ready( function() {
		$("#id_container").show().delay(5000).fadeOut('slow');
		$("#id_container1").show().delay(5000).fadeOut('slow');
		
		$('#loginElemID').keyup(function(){
		    str = $(this).val()
		    str = str.replace(/\s/g,'')
		    $(this).val(str)
		});
		 // $(document).on( "keyup", "#roleNameHidID", function() {
				/* 	$('#userNameID').keyup(function(){
			   if (this.value.match(/[^a-zA-Z/s]/g)) {
			       this.value = this.value.replace(/[^a-zA-Z/s]/g, '');
			   }
			}); */
		/* $('#userNameID').keyup(function(){
		    str = $(this).val()
		    str = str.replace(/\s/g,'')
		    $(this).val(str)
		    if (this.value.match(/[^a-zA-Z/s]/g)) {
			       this.value = this.value.replace(/[^a-zA-Z/s]/g, '');
			   }
		}); */

		$('.charectersonly').bind('keyup', function() { 
		    $(this).val(function(i, val) {
		        return val.replace(/[^a-z\s]/gi,''); 
		    });
		});

		$('#resultList').dataTable({"sPaginationType": "full_numbers",});
		
						$('#id_container').hide();
						$('#id_container1').hide();
						$('#resultList').addClass("table-dynamic");
						//alert("in alert");
						var dbMsg = "${errorMsg}";
						if (dbMsg != "") {
							$("#id_container").addClass('error');
							$('#id_container').find('ol').append('<li style="list-style: none;">${errorMsg}</li>');
							$("#id_container").show();
							$("#roleNameSelectID").val('-1');
						}

						var dbsuccessMsg = "${successMsg}";

						if (dbsuccessMsg != "") {
							$("#id_container1").addClass('success');
							$('#id_container1').find('ol').append('<li style="list-style: none;">${successMsg}</li>');
							$("#id_container1").show();
							$("#roleNameSelectID").val('-1');

						}

						$("#updateID").hide();
						$("#saveID").show();

						$.ajaxSetup({
							async : false
						});
						$.getJSON("getRMUsers.do",function(res1) {
											var rwUsersList = '<option value="-1">--- Select a Mananger ---</option>';
											if (res1 != null) {
												for (var i = 0; i < res1.length; i++) {
													var loginID = res1[i].loginID;
													var userName = res1[i].userName;
													rwUsersList += '<option value='+loginID+'>'
															+ userName
															+ '</option>';
												}
											}
											$("#reportingmanagerID").html(rwUsersList);
										});

						$("#saveID").click( function() {
											$("#updateID").hide();
											$("#saveID").show();

											var check_cnt = 0;

											$('#id_container').hide();
											$('#id_container1').hide();

											var check_cnt = 0;
											var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
											$('#id_container').find('ol').html(
													'');
											$('#id_container1').find('ol')
													.html('');
											if ($("#roleNameSelectID").val() == '-1') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass('error');
												$('#id_container').find('ol').append('<li style="list-style: none;">Please Select Role Name</li>');
												$("#id_container").show();
											} 
											else if ($("#userNameID").val() == '') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Enter User Name</li>');
												$("#id_container").show();
											} else if ($("#userNameID").val().length < 4 && $("#userNameID").val().length > 50) {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass('error');
												$('#id_container').find('ol').append('<li style="list-style: none;">User Name must be atleast 4 characters and almost 50 characters</li>');
												$("#id_container").show();
											} 
											/* else if ($("#mobile1ID").val() == '' && $("#mobile1ID").val().length <= 9) {
												alert("mobile primay"+$("#mobile1ID").val().length);
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Enter Valid Primary Mobile Number</li>');
												$("#id_container").show();
											} */
											//
											  else if ($("#mobile1ID").val().length <= 9) {
												// alert("mobile primay"+$("#mobile1ID").val().length);
												check_cnt = check_cnt + 1;
												$("#id_container").addClass('error');
												$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Valid Primary Mobile Number</li>');
												$("#id_container").show();
											} 
											 /*  else if ( $("#mobile2ID").val() == '') {
													//alert("mobile secondary"+$("#mobile2ID").val().length);
											  } */
											  else if ($("#mobile2ID").val()!="" && $("#mobile2ID").val().length <= 9){
														 // alert("mobile secondary"+$("#mobile2ID").val().length);
														check_cnt = check_cnt + 1;
														$("#id_container").addClass('error');
														$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Valid Secondary Mobile Number</li>');
														$("#id_container").show();
													  }
												
											 else if ($("#emailID1ID").val() == '') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass('error');
												$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Primary Email ID</li>');
												$("#id_container").show();
											} else if (!re.test($("#emailID1ID").val())) {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass('error');
												$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Valid Primary Email ID</li>');
												$("#id_container").show();
											} 
											//
											/* else if ($("#emailID2ID").val() == '') {
												//alert("email22 empttt");
											}  */
											else if ($("#emailID2ID").val() != "" && !re.test($("#emailID2ID").val())) {
													//alert("email22 not empttt");
												check_cnt = check_cnt + 1;
												$("#id_container").addClass('error');
												$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Valid Secondary Email ID</li>');
												$("#id_container").show();
											}
											else if ($("#reportingmanagerID").val() == '-1') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass('error');
												$('#id_container').find('ol').append('<li style="list-style: none;">Please Select Reporting Manager</li>');
												$("#id_container").show();
											} else if ($("#loginElemID").val() == '') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Enter Login ID</li>');
												$("#id_container").show();
											} else if ($("#loginElemID").val().length < 4) {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass('error');
												$('#id_container').find('ol').append('<li style="list-style: none;">Login ID must be atleast 4 characters</li>');
												$("#id_container").show();
											}


											if (check_cnt == 0) {

												if ($("#statusID").is(':checked')) {
													$("#statusHiddenID").val("1");
												} else {
													$("#statusHiddenID").val("0");
												}

												$("#rolesFormID").attr('action',"saveUser.do");
												$("#rolesFormID").submit();
											} else {
												return false;
											}

										});

						$("#cancelID").click(function() {
							$("#rolesFormID").attr('action', "home.do");
							$("#rolesFormID").submit();
						});

						$("#statusID").click(function() {

							if ($(this).is(':checked')) {
								$(this).val('1');
							} else {
								$(this).val('0');
							}
						});
						$(".editAnchorClass").live('click',function() {

									$("#updateID").show();
									$("#saveID").hide();
									$("#statusID").removeAttr("disabled");
									/*  $("#userNameID").val('');
									 $("#mobile1ID").val('');
									 $("#mobile2ID").val('');
									 $("#emailID1ID").val('');
									 $("#emailID2ID").val('');
									 $("#roleNameSelectID").val('-1');
									 $("#reportingmanagerID").val('-1');
									 $("#loginElemID").val('');  */
									var loginID = $(this).closest("tr").find(
											$(".loginIDHiddenClass")).val();
									$.ajaxSetup({
										async : false
									});
									$.getJSON("editUserDetails.do", {
										loginID : loginID
									}, function(res) {
										//  alert(res.roleName+"res.roleName");
										$("#userNameID").val(res.userName)
												.attr("readonly", true);
										$("#mobile1ID").val(res.mobile1);
										$("#mobile2ID").val(res.mobile2);
										$("#emailID1ID").val(res.emailID1);
										$("#emailID2ID").val(res.emailID2);
										$("#loginElemID").val(res.loginID)
												.attr("readonly", true);
										$("#roleNameSelectID")
												.val(res.roleName);
										$("#reportingmanagerID").val(
												res.rmUserID);

										//  alert(res.status+"status");
										if (res.status == "1") {
											$("#statusID").addAttr("checked",
													"checked");
											$("#statusID").val("1");
										} else {
											$("#statusID")
													.removeAttr("checked");
											$("#statusID").val("0");
										}
									});
								});

						$("#updateID")
								.click(
										function() {

											var check_cnt = 0;
											var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

											//$("#updateID").show();
											//$("#saveID").hide(); 

											$('#id_container').hide();
											$('#id_container1').hide();

											$('#id_container').find('ol').html(
													'');
											$('#id_container1').find('ol')
													.html('');
											if ($("#roleNameSelectID").val() == '-1') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Select Role Name</li>');
												$("#id_container").show();
											} else if ($("#userNameID").val() == '') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Enter User Name</li>');
												$("#id_container").show();
											} else if ($("#mobile1ID").val() == '') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Enter Primary Mobile Number</li>');
												$("#id_container").show();
											} else if ($("#emailID1ID").val() == '') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Enter Primary Email ID</li>');
												$("#id_container").show();
											} else if (!re
													.test($("#emailID1ID")
															.val())) {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Enter Valid Primary Email ID</li>');
												$("#id_container").show();
											} else if ($("#reportingmanagerID")
													.val() == '-1') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Select Reporting Manager</li>');
												$("#id_container").show();
											} else if ($("#loginElemID").val() == '') {
												check_cnt = check_cnt + 1;
												$("#id_container").addClass(
														'error');
												$('#id_container')
														.find('ol')
														.append(
																'<li style="list-style: none;">Please Enter Login ID</li>');
												$("#id_container").show();
											}
											if (check_cnt == 0) {
												$("#rolesFormID").attr(
														'action',
														"updateUser.do");
												$("#rolesFormID").submit();

											} else {
												return false;
											}
										});
					});
</script>