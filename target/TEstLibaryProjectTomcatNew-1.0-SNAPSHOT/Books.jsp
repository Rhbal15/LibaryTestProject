<%-- 
    Document   : Books
    Created on : 08.12.2016, 23:03:10
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
                <th>Id</th><td>Название книги</td><td>Жанр</td><td>Год издания</td><td>Имя автора</td>
            </tr> 
            <jstl:forEach var="book" items="${requestScope.Books}">
                <tr>
                    <td>${book.getId()}</td>
                    <td>${book.getName()}</td>
                    <td>${book.getGenre()}</td>
                    <td>${book.getRelease()}</td>
                    <td>${book.getAuthor().getName()}</td>
                    <td>
                        <form action="Books/Delete" id="${book.getId()}delete" method="post">
                            <input type="hidden" form="${book.getId()}delete" name="id" value="${book.getId()}"/>
                            <input type="submit" form="${book.getId()}delete" value="Удалить"/>
                        </form>
                    </td>
                    <td>
                        <form action="Books/Edit" id="${book.getId()}edit" method="get">
                            <input type="hidden" form="${book.getId()}edit" name="id" value="${book.getId()}"/>
                            <input type="submit" form="${book.getId()}edit" value="Изменить"/>
                        </form>

                    </td>
                </tr>
                </jstl:forEach>
            </table>
            
            <div>
                <form action="Books/Create" id="${book.getId()}create" method="get">
                        <input type="submit" form="${book.getId()}create" value="Создать"/>
                </form>
            </div>
    </body>
</html>
