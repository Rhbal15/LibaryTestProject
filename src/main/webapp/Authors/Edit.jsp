<%-- 
    Document   : AuthorsEdit
    Created on : 08.12.2016, 10:35:48
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Edit" id="Edit" method="post">
            <p>Введите имя автора:</p><input name="name" form="Edit" type="text" value="${author.getName()}"/>
            <p>Введите год рожения автора:</p><input name="age" form="Edit" type="text" value="${author.getAge()}"/>
            <input type="hidden" form="Edit" name="id" value="${author.getId()}"/>
            <input type="submit" form="Edit"/>
        </form>
    </body>
</html>
