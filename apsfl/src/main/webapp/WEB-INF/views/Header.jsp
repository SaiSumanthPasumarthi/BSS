 <!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="admin-themes-lab">
<meta name="author" content="themes-lab">
<title>:: Andhra Pradesh State FiberNet Ltd ::</title>

<link href="./resources/css/style.css" rel="stylesheet">
<link href="./resources/css/ui.css" rel="stylesheet">
<link href="./resources/css/layout.css" rel="stylesheet">
<link href="./resources/css/custom.css" rel="stylesheet">
<link href="./resources/css/viewer.css" rel="stylesheet">
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/sumoselect.css" rel="stylesheet">
<link href="./resources/css/jquery.ui.core.min.css"  rel="stylesheet" >
<link href="./resources/css/jquery.ui.theme.min.css"  rel="stylesheet" >
<link href="./resources/css/jquery.ui.datepicker.min.css"  rel="stylesheet" >
<link href="./resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">	
<link href="./resources/css/menu.css" rel="stylesheet" type="text/css">
<link href="./resources/css/jquery.treegrid.css" rel="stylesheet">
<link href="./resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="./resources/css/jquery-ui.structure.min.css" rel="stylesheet">
<link href="./resources/css/select2.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/jjsonviewer.css">


</head>
<body onload="disableBackButtonPress()" >
<section>
	<header class="fixed-header">
		<!-- BEGIN TOPBAR -->
		<div class="topbar">
			<div class="page-width">

				<div class="header-left">
					 <div class="theme-colors">
					 	<button type="button" class="btn-color-theme btn-light-green-theme " id="theme-light-green"></button>
						<button type="button" class="btn-color-theme btn-blue-theme" id="theme-blue"></button>
						<button type="button" class="btn-color-theme btn-yellow-theme" id="theme-yellow"></button>
						<button type="button" class="btn-color-theme btn-tealgreen-theme" id="theme-tealgreen"></button> 
					</div> 
					<div class="menu">
						<nav class="topbar">
							<ul>
								<c:forEach var="moduleNameVar" items="${moduleNameMap}">								  
									<c:if
										test="${fn:replace(moduleNameVar.menuPathID,'00','').length()==2}">
										<c:if test="${moduleNameVar.menuItem ne 'Logout'}">
										 			   <li><a href="${moduleNameVar.actionPath}">${moduleNameVar.menuItem}<i class="fa fa-angle-down"></i></a>
											  <ul class="submenu">
												<c:forEach var="moduleNameVar1" items="${moduleNameMap}">
													<c:if
														test="${fn:replace(moduleNameVar1.menuPathID,'00','').length()==4}">
														<c:if
															test="${fn:substring(moduleNameVar.menuPathID,0,2) == fn:substring(moduleNameVar1.menuPathID,0,2)}">
															<li><a href="${moduleNameVar1.actionPath}">${moduleNameVar1.menuItem}</a>
																<ul>
																	<c:forEach var="moduleNameVar2"
																		items="${moduleNameMap}">
																		<c:if
																			test="${fn:replace(moduleNameVar2.menuPathID,'00','').length()==6}">
																			<c:if
																				test="${fn:substring(moduleNameVar1.menuPathID,0,4) == fn:substring(moduleNameVar2.menuPathID,0,4)}">
																				<li><a href="${moduleNameVar2.actionPath}">
																				${moduleNameVar2.menuItem}</a>
																				</li>
																			</c:if>
																		</c:if>
																	</c:forEach>
																</ul></li>
														</c:if>
													</c:if>
												</c:forEach>
											</ul></li>
										</c:if>	
									</c:if>								  
								</c:forEach>
							</ul>
							<div class="clear"></div>
						</nav>
						<!-- menu div end -->
					</div>
				</div>
				<!-- HEADER LEFT END -->
				<div class="header-right">
					<ul class="header-menu nav navbar-nav">						
						<!-- BEGIN USER DROPDOWN -->
						<li class="dropdown" id="user-header"><a href="#"
							data-toggle="dropdown" data-hover="dropdown"
							data-close-others="true"> <img
								src="./resources/images/user1.png" alt="user1"> <span
								class="username"> ${userName}</span>
						</a>
							<ul class="dropdown-menu">
								<%-- <c:if test="${domain != 'SI'}"> --%>
									<li> <a href="./changePassword.do"><i class="icon-logout"></i><span>Change Password</span></a> </li>
							<%-- 	</c:if> --%>
								<li> <a href="./logout.do"><i class="icon-logout"></i><span>Logout</span></a> </li>
							</ul></li>
						<!-- END USER DROPDOWN -->
					</ul>
				</div>
				<!-- HEADER RIGHT END -->
				<div class="clear"></div>
			</div>
		</div>
		<!-- END TOPBAR -->

		<!-- BEGIN BOTBAR -->
		<div class="botbar">
			<div class="page-width">
				<!-- <div class="logo-right col-sm-2">
					<a href="#"><img src="./resources/images/APSFL.png"
						alt="logo" title="Andhra Pradesh State FiberNet Ltd" /></a>
				</div> -->
				<div class="col-sm-10"></div>
				<div class="col-sm-2">
					<a href="#"><img src="./resources/images/APSFL.png"
						alt="logo" title="Andhra Pradesh State FiberNet Ltd" /></a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!-- END BOTBAR -->

	</header>
	</section>
<script src="./resources/js/jquery-1.11.1.min.js"></script>
<script src="./resources/js/jquery-migrate-1.2.1.min.js"></script>
<script src="./resources/js/jquery-ui-1.11.2.min.js"></script>
<script src="./resources/js/bootstrap.min.js"></script>
<script src="./resources/js/jquery.validate.min.js"></script>
<script src="./resources/js/bootstrap-hover-dropdown.min.js"></script>
<script src="./resources/js/jquery.ui-1.10.4.datepicker.min.js" type="text/javascript"></script>
<!-- <script src="./resources/js/plugins.js"></script> Main Plugin Initialization Script  -->
<script src="./resources/js/application.js"></script> <!-- Main Application Script --> 
<script src="./resources/js/nprogress.js"></script> <!-- Progress bar on page load -->
<script src="./resources/js/menu.js"></script> <!-- Menu --> 
<script src="./resources/js/layout.js"></script> <!-- Main Application Script --> 
<script src="./resources/js/custom.js"></script> <!-- custom file -->
<script src="./resources/js/jquery.mCustomScrollbar.concat.min.js"></script> <!-- Custom Scrollbar sidebar --> 
<script src="./resources/js/bootstrap-tagsinput.min.js"></script> <!-- Select Inputs --> 
<script src="./resources/js/jquery.dataTables.min.js"></script> <!-- Tables Filtering, Sorting & Editing --> 
<script src="./resources/js/table_editable.js"></script> <!-- Tables Editing -->
<script src="./resources/js/table_dynamic.js"></script>
<script src="./resources/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="./resources/js/jquery.numeric.js"></script>
<script src="./resources/js/jquery.cookie.js"></script>
<script src="./resources/js/viewer.js"></script>  
<script src="./resources/js/spnning.js"></script>
<script src="./resources/js/tenantvalidations.js"></script>
<script src="./resources/js/lmo.js"></script>
<script src="./resources/js/revenue_sharing.js" type="text/javascript"></script>
<script src="./resources/js/jquery.searchabledropdown-1.0.8.min.js"></script>
<script src="./resources/js/jquery.treegrid.min.js"></script>
<script src="./resources/js/caf-script.js"></script> <!-- custom file -->
<script src="./resources/js/BlockList-script.js"></script> <!-- custom file -->
<script src="./resources/js/caf-validation.js"></script> <!-- custom file -->
<script src="./resources/js/cpe.js"></script> <!-- custom file -->
<script src="./resources/js/jquery.sumoselect.min.js"></script>
<script src="./resources/js/jquery-ui.min.js"></script>
<script src="./resources/js/multiselect.min.js"></script>
<script type="text/javascript" src="./resources/js/jjsonviewer.js"></script>
<script type="text/javascript" src="./resources/js/jsonError.js"></script>
<script type="text/javascript" src="./resources/js/additional-methods.min.js"></script>
</body>	
	
	<!-- END HEADER -->
	<!-- <a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a> 
</body> -->
