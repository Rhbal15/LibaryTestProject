<%-- 
    Document   : Edit
    Created on : 09.12.2016, 11:56:13
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
        <form action="Edit" id="Edit" method="post">
            <p>Введите название книги:</p><input name="name" form="Edit" type="text" value="${book.getName()}"/>
            <p>Введите жанр книги:</p><input name="genre" form="Edit" type="text" value="${book.getGenre()}"/>
            <p>Введите год выпуска книги: </p><input name="release" form="Edit" type="text" value="${book.getRelease()}"/>
            <p>Выберите автора книги: 
                <select form="Edit" name="authorId">
                    <jstl:forEach var="authorForEach" items="${requestScope.Authors}">
                        <jstl:choose>
                            <jstl:when test="${authorForEach.getId()==book.getAuthor().getId()}">
                                <option selected value="${authorForEach.getId()}">${authorForEach.getName()}</option>
                            </jstl:when>    
                            <jstl:otherwise>
                                <option value="${authorForEach.getId()}">${authorForEach.getName()}</option>
                            </jstl:otherwise>    
                        </jstl:choose>
                    </jstl:forEach>
                </select>
            </p>
            <input type="hidden" form="Edit" name="id" value="${book.getId()}"/>
            <input type="submit" form="Edit"/>
        </form>
    </body>
</html>
