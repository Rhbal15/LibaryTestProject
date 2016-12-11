<%-- 
    Document   : Create
    Created on : 09.12.2016, 11:36:46
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
        <form action="Create" id="Create" method="post">
            <p>Введите название книги:</p><input name="name" form="Create" type="text"/>
            <p>Введите жанр книги:</p><input name="genre" form="Create" type="text"/>
            <p>Введите год выпуска книги: </p><input name="release" form="Create" type="text"/>
            <p>Выберите автора книги: 
                <select form="Create" name="authorId">
                    <jstl:forEach var="author" items="${requestScope.Authors}">
                        <option value="${author.getId()}">${author.getName()}</option>
                    </jstl:forEach>
                </select>
            </p>
            <input type="submit" form="Create"/>
        </form>
    </body>
</html>
