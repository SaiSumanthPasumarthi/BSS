<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>         

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>::APFGC::</title>
<style>	
a {
    cursor: pointer;
}
</style>
 
</head>
<script>
$(document).ready(function() {
	 
  $("#searchID").click(function(){
	  alert('in search');
	  $("#rolesFormID").attr('action',"searchUser.do");
	  $("#rolesFormID").submit();
		/* var check_cnt = 0;
	  $("#updateID").hide();
	  $("#saveID").show();
	
		
		 
			
	
	  if($("#roleNameHidID").val()==""){
			$("#id_container").addClass('dberrorMsg');
			$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Role</li>');
			$("#id_container").show();	
			check_cnt=check_cnt+1;
		} 
		
		 
	  
	  if(check_cnt == 0)
		  {
	  $("#rolesFormID").attr('action',"addRole.do");
	  $("#rolesFormID").submit();
		  }
	  else{
		  return false;
	  } */
  });
  
  $('#paginationDivID').smartpaginator({totalrecords: "${totalRecords}",
      recordsperpage: 5, 
	  controlsalways:true,
      datacontainer: 'usersList', 
      dataelement: 'tr',
      theme: 'green'
      });
  
  
});

</script>
<body>
<form:form action="#" id="rolesFormID" method="post" commandName="usersVO">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr bgcolor="#efefef">
        <td height="20" style="padding-right:5px" align="right" class="top-heading"><a href="homeAction.do">Home</a> &gt; Administration &gt; User Search</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td align="center" style="font-family:Times New Roman; font-size:21px; color:#095870;"><b>User Search</b></td>
</tr>
</table>
<!-- <div id="id_container" class="error"><ol id="id_ol"></ol></div>
<div id="id_container1" class="success"><ol id="id_ol"></ol></div>
<br>
 --><table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">

  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
         <td>&nbsp;</td>
      </tr>
      <tr>
        <td>
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
         	 <tr>
           	 	<td>
            		<P class="form" align="center"><label>User Name</label>
            			<form:input path="userName" id="userNameHidId"/>
            		</P>
            </td>
            <td>
            		<P class="form" align="center"><label>Email</label>
            			<form:input path="emailID1" id="emailID1HidID"/>
            		</P>
            </td>
           </tr>
       		 </table>	
       	</td>
      </tr>
       <tr>
        <td><table width="62.5%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right">
           			 	 <p class="form">
            				<input  type="submit" id="searchID" value="Search" style="color:#fff; background-color:#39b54a; border-radius:4px;" /> 
            			</p> 
            </td>
            <td width="5px">&nbsp;</td>
            <td align="left"></td>
          </tr>
        </table></td>
      </tr>
     
      <tr>
        <td><table width="950px" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>&nbsp;</td>
            <td width="600px"><div class="pmsTableGenerator" >
                <table align="center" id="usersList">
                    <tr>
                           <!-- <th width="10%">S.No</th> -->
                           <th width="20%">User Name</th>
                           <th width="70%">Role Name</th>
                            <th width="10%">Email ID</th>
                           <th width="10%">Mobile</th>
                          <th width="10%">Status</th>
                   </tr>
                                <c:if test="${empty usersList}">
                              <tr><td colspan="5"><center><font color="red">No Records Found</font></center></td></tr>
                                    </c:if>
                                <c:forEach items="${usersList}" var="usersListVar" varStatus="status"> 
      <tr>
       	<%-- <td>${status.index+1}</td> --%>
       	<td><c:out value="${usersListVar.userName}"/></td>
        <td><c:out value="${usersListVar.roleName}"/></td>
        <td><c:out value="${usersListVar.emailID1}"/></td>
        <td><c:out value="${usersListVar.mobile1}"/></td>
        <td><c:out value="${usersListVar.status}"/></td>
      </tr>
    </c:forEach>
</table>
            </div></td>
            <td width="110px">&nbsp;</td>
          </tr>
        </table></td>
        </tr>
        
        	<tr>
       			 <td align="right" style="padding-right: 280px;"><div id="paginationDivID"></div> </td>
       	    </tr>
               
    </table></td>
  </tr>
</table>

</form:form>

  

</body>
</html>