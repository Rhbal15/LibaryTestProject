<%-- 
    Document   : Readers
    Created on : 08.12.2016, 20:17:28
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Id</th><td>Фамилия</td><td>Имя</td><td>Отчество</td><td>Возраст</td>
            </tr> 
            <jstl:forEach var="reader" items="${requestScope.Readers}">
                <tr>
                    <td>${reader.getId()}</td>
                    <td>${reader.getLastName()}</td>
                    <td>${reader.getFirstName()}</td>
                    <td>${reader.getSurname()}</td>
                    <td>${reader.getAge()}</td>
                    <td>
                        <form action="Readers/Delete" id="${reader.getId()}delete" method="post">
                            <input type="hidden" form="${reader.getId()}delete" name="id" value="${reader.getId()}">
                            <input type="submit" form="${reader.getId()}delete" value="Удалить"/>
                        </form>
                    </td>
                    <td>
                        <form action="Readers/Edit" id="${reader.getId()}edit" method="get">
                            <input type="hidden" form="${reader.getId()}edit" name="id" value="${reader.getId()}">
                            <input type="submit" form="${reader.getId()}edit" value="Изменить"/>
                        </form>

                    </td>
                </tr>
            </jstl:forEach>  
        </table>
       
        <div>
            <form action="Readers/Create" id="${author.getId()}create" method="get">
                    <input type="submit" form="${author.getId()}create" value="Создать"/>
            </form>
        </div>
    </body>
</html>
