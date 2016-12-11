<%-- 
    Document   : Create
    Created on : 08.12.2016, 20:30:40
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
            <p>Введите Фамилию:</p><input name="lastName" form="Create" type="text"/>
            <p>Введите Имя:</p><input name="firstName" form="Create" type="text"/>
            <p>Введите Отчество:</p><input name="surname" form="Create" type="text"/>
            <p>Введите Возраст:</p><input name="age" form="Create" type="text"/>
            <input type="submit" form="Create"/>
        </form>
    </body>
</html>
