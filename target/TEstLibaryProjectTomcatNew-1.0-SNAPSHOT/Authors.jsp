<%-- 
    Document   : Authors
    Created on : 05.12.2016, 19:41:28
    Author     : User
--%>
<%@page import="com.sun.xml.internal.fastinfoset.util.PrefixArray"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <jstl:forEach var="author" items="${requestScope.Authors}">
                <tr>
                        <td>${author.getId()}</td>
                        <td>${author.getName()}</td>
                        <td>${author.getAge()}</td>
                        <td>
                            <form action="Authors/Delete" id="${author.getId()}delete" method="post">
                                <input type="hidden" form="${author.getId()}delete" name="id" value="${author.getId()}">
                                <input type="submit" form="${author.getId()}delete" value="Удалить"/>
                            </form>

                        </td>
                        <td>
                            <form action="Authors/Edit" id="${author.getId()}edit" method="get">
                                <input type="hidden" form="${author.getId()}edit" name="id" value="${author.getId()}">
                                <input type="submit" form="${author.getId()}edit" value="Изменить"/>
                            </form>

                        </td>
                </tr>
            </jstl:forEach>  
        </table>
        <div>
            <form action="Authors/Create" id="${author.getId()}create" method="get">
                    <input type="submit" form="${author.getId()}create" value="Создать"/>
            </form>
        </div>
    </body>
</html>
