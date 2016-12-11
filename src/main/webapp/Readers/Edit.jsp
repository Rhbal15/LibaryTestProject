<%-- 
    Document   : Edit
    Created on : 08.12.2016, 21:07:47
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
            <p>Введите Фамилию:</p><input name="lastName" form="Edit" type="text" value="${reader.getLastName()}"/>
            <p>Введите Имя:</p><input name="firstName" form="Edit" type="text" value="${reader.getFirstName()}"/>
            <p>Введите Отчество:</p><input name="surname" form="Edit" type="text" value="${reader.getSurname()}"/>
            <p>Введите Возраст:</p><input name="age" form="Edit" type="text" value="${reader.getAge()}"/>
            <input type="hidden" form="Edit" name="id" value="${reader.getId()}"/>
            <input type="submit" form="Edit"/>
        </form>
    </body>
</html>
