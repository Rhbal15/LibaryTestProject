<%-- 
    Document   : Create
    Created on : 08.12.2016, 11:14:09
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
        <form action="Create" id="Create" method="post">
            <p>Введите имя автора:</p><input name="name" form="Create" type="text"/>
            <p>Введите год рожения автора:</p><input name="age" form="Create" type="text"/>
            <input type="submit" form="Create"/>
        </form>
    </body>
</html>
