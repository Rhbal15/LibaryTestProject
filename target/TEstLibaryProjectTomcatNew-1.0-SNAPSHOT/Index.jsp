<%-- 
    Document   : Index
    Created on : 08.12.2016, 22:47:03
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
        <form action="Index" id="readers" method="post">
            <input type="hidden" form="readers" name="type" value="Readers"/>
            <input type="submit" form="readers" value="Работать с читателями"/>
        </form>
        <form action="Index" id="authors" method="post">
            <input type="hidden" form="authors" name="type" value="Authors"/>
            <input type="submit" form="authors" value="Работать с авторами"/>
        </form>
        <form action="Index" id="books" method="post">
            <input type="hidden" form="books" name="type" value="Books"/>
            <input type="submit" form="books" value="Работать с книгами"/>
        </form>
        <form action="Index" id="bookStatuses" method="post">
            <input type="hidden" form="bookStatuses" name="type" value="BookStatuses"/>
            <input type="submit" form="bookStatuses" value="Работать с учетными записями"/>
        </form>
    </body>
</html>
