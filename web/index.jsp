<%-- 
    Document   : index-v2
    Created on : Apr 17, 2023, 9:40:00 PM
    Author     : QuangNV
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>QuangNV</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                display: flex;
                width: 100vw;
                height: 100vh;
                margin: 0px;
            }
            .main_column {
                padding-left: 2%;
                display: inline-block;
                width: 50%;
                height: 100%;
                border: 2px red solid;
            }
            .todo_item {
                margin: 5px;
                padding: 5px;
                background-color: lightgray;
            }
        </style>
    </head>
    <body>
        <div class="main_column">
            <h1>Add new TODO</h1>
            <form action="servlet" method="post">
                <label for="name">Name</label>
                <input type ="text" id="name" name="name"/>
                <input type ="submit" value="Add"/>
            </form>
        </div>
        <div class="main_column">
            <h1>Todos</h1>
            <c:forEach var="todo" items="${todos}">
                <div class="todo_item">
                    <span>${todo.name}</span>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
