<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
    <head>
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <%--   <title><tiles:insertAttribute name="title" ignore="true" /></title>  --%>
    </head>
    <body>
        <table width="100%" align="center">
            <tr>
                <td>
                    <tiles:insertAttribute name="header" />
                </td>
            </tr>
            <tr>
             <td>
                    <tiles:insertAttribute name="menu"/>
                </td>
              </tr>
            <tr>
             </tr>
            <tr>   
                <td align="center">
                <div  style="min-height:900px; overflow-y:auto; "> 
                    <tiles:insertAttribute name="body" />
                  </div>
                </td>
            </tr>
            <tr>
                <td>
                    <tiles:insertAttribute name="footer" />
                </td>
            </tr>
        </table>
    </body>
</html>