<%-- 
    Document   : BookStatuses
    Created on : 09.12.2016, 18:15:48
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
                <th>Выберите читателя, с которым собираетесь работать:
            </tr> 
            <jstl:forEach var="reader" items="${requestScope.Readers}">
                <tr>
                    <td>
                        <a href="http://localhost:8084/TEstLibaryProjectTomcatNew/BookStatuses?id=${reader.getId()}">${reader.getLastName()}</a>
                    </td>
                </tr>
            </jstl:forEach>  
        </table>
        <table>
            <jstl:forEach var="bookStatus" items="${requestScope.BookStatuses}">
                <tr>
                    <td>
                        ${bookStatus.getBook().getName()}
                    </td>
                    <td>
                        ${bookStatus.getReader().getLastName()}
                    </td>
                    <td>
                        ${bookStatus.getTimeReciept()}
                    </td>
                    <td>
                        ${bookStatus.getTimeReturn()}
                    </td>
                    <td>
                        <jstl:choose>
                            <jstl:when test="${bookStatus.getTimeReturn()==null }">
                                <form action="BookStatuses/PutBook" id="${bookStatus.getId()}putBook" method="post">
                                    <input type="hidden" form="${bookStatus.getId()}putBook" name="id" value="${bookStatus.getId()}"/>
                                    <input type="submit" form="${bookStatus.getId()}putBook" value="Вернуть книгу"/>
                                </form>
                            </jstl:when>
                        </jstl:choose>
                    </td>
                    <td>
                        <form action="BookStatuses/Delete" id="${bookStatus.getId()}deleteBookStatus" method="post">
                            <input type="hidden" form="${bookStatus.getId()}deleteBookStatus" name="id" value="${bookStatus.getId()}"/>
                            <input type="submit" form="${bookStatus.getId()}deleteBookStatus" value="Удалить запись"/>
                        </form>
                    </td>
                </tr>
            </jstl:forEach> 
                
                <form action="BookStatuses/GetBook" id="${bookStatus.getId()}getBook" method="get">
                    <input type="submit" form="${bookStatus.getId()}getBook" value="Записать что читатель взял книгу"/>
                </form>
        </table>
    </body>
</html>
