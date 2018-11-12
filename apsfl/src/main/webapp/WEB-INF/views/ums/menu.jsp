<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>::APFADM::</title>
  <link href="./resources/um/css/ddmenu.css" rel="stylesheet" type="text/css" />
    <script src="./resources/um/js/ddmenu.js" type="text/javascript"></script>
<link href="./resources/um/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/um/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script src="./resources/um/js/jquery-1.12.0.js" type="text/javascript"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/um/js/bootstrap.min.js"></script>
    <script src="./resources/um/js/smartpaginator.js" type="text/javascript"></script>
<link href="./resources/um/css/smartpaginator.css" rel="stylesheet" type="text/css" />
   <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script> 
</head>

<script>
$(document).ready(function() {
	//alert("In ready:::");
	
	
});
</script>
<body>

         <ul>
    <c:forEach var="moduleNameVar" items="${moduleNameMap}">
     <c:if test="${fn:replace(moduleNameVar.menuPathID,'00','').length()==2}">
    <li><a href="${moduleNameVar.actionPath}">${moduleNameVar.menuItem}</a>
    	<ul>
    		 <c:forEach var="moduleNameVar1" items="${moduleNameMap}">
    		 	<c:if test="${fn:replace(moduleNameVar1.menuPathID,'00','').length()==4}">
    		 	<c:if test="${fn:substring(moduleNameVar.menuPathID,0,2) == fn:substring(moduleNameVar1.menuPathID,0,2)}">
    		 		 <li><a href="${moduleNameVar1.actionPath}">${moduleNameVar1.menuItem}</a>
    		 		 	<ul>
				    		 <c:forEach var="moduleNameVar2" items="${moduleNameMap}">
				    		 	<c:if test="${fn:replace(moduleNameVar2.menuPathID,'00','').length()==6}">
				    		 	<c:if test="${fn:substring(moduleNameVar1.menuPathID,0,4) == fn:substring(moduleNameVar2.menuPathID,0,4)}">
				    		 		 <li><a href="${moduleNameVar2.actionPath}">${moduleNameVar2.menuItem}</a>
				    		 		 </li>
				    		 	</c:if>
				    		 	</c:if>
				    		 </c:forEach>
				    	</ul>
    		 		 </li>
    		 	</c:if>
    		 	</c:if>
    		 </c:forEach>
    	</ul>
    </li>
    </c:if>
</c:forEach> 
</ul> 
 <nav id="ddmenu">
    <div class="menu-icon"></div>

    <ul>
        <li class="no-sub">
        	<a class="top-heading" href="#">Home</a>
        </li>   
         
        <li class="full-width">
            <span class="top-heading">User Management</span>
			<i class="caret"></i>           
            <div class="dropdown">
                <div class="dd-inner">
                    <div class="column">
                        <a href="roleCreation.do">Role Creation</a>
                    	<a href="roleServiceMapping.do">Role Service Mapping</a>
                    	<a href="userCreation.do">User Creation </a>
                    	<a href="userSearch.do">User Search </a>
                    	<a href="changePassword.do">Change Password </a>
                    </div>
				</div>
               
            </div>
        </li>
        
      
      
      <li class="full-width">
            <span class="top-heading">Service Catalog</span>
			<i class="caret"></i>           
            <div class="dropdown">
                <div class="dd-inner">
                    <div class="column">
                         <a href="serviceAction.do">Add Service</a>
                        <a href="#">Add Product</a>
                    </div>
                    
                </div>
            </div>
        </li>
        
        <li class="full-width">
            <span class="top-heading">Service Providers</span>
			<i class="caret"></i>           
            <div class="dropdown">
                <div class="dd-inner">
                    <div class="column">
                         <a href="#">Add Service Provider</a>
                    </div>
                    
                </div>
            </div>
        </li>
        
         <li class="no-sub">
        	<a class="top-heading" href="<c:url value="/Cat/pcs/" />">PCS</a>
        </li> 
        
        
         <li class="no-sub">
        	<a class="top-heading" href="logout.do"/>Logout</a>
        </li>   
        
    </ul>
</nav>

</body>
</html> --%>