<%-- 
    Document   : GetBook
    Created on : 11.12.2016, 17:52:12
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
         <form action="GetBook" id="Create" method="post">
            <p>Выберите читателя, который взял книгу:</p>
            <select form="Create" name="readerId">
                    <jstl:forEach var="reader" items="${requestScope.Readers}">
                        <option value="${reader.getId()}">${reader.getLastName()}</option>
                    </jstl:forEach>
            </select>
                    
            <p>Выберите книгу, которую он взял:</p>
                <select form="Create" name="bookId">
                    <jstl:forEach var="book" items="${requestScope.Books}">
                        <option value="${book.getId()}">${book.getName()}</option>
                    </jstl:forEach>
                </select>
                    
            <p>Выберите год, в который требуется вернуть книгу: </p><input name="year" form="Create" type="text"/>
            <p>Выберите месяц, в который требуется вернуть книгу: </p><input name="month" form="Create" type="text"/>
            <p>Выберите день, в который требуется вернуть книгу: </p><input name="day" form="Create" type="text"/>
            <input type="submit" form="Create"/>
        </form>
    </body>
</html>
