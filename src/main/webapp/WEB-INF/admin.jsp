<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dron
  Date: 05.12.2018
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <title>Main page</title>
    <link href="nestednav.css" rel="stylesheet">
    <style>
        <%@include file="/resources/css/main.css"%>
    </style>
    <title>AdminKa</title>
</head>
<body>
<div class="alert alert-success" role="alert">
    <div align="center"><h2>Добро пожаловать в интерфейс Администрирования</h2></div>
    <br>
    <div align="left">
        <form action="/">
            <input class=" btn btn-outline-primary btn-sm" type="submit" value="Back">
        </form>
    </div>
</div>
<br>
<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-10">
            <c:if test="${!empty allOperation}">
                <h3>Просмотр журнала операций:</h3>
                <div class="table-responsive">
                    <thead class="thead-light">
                    <table class="table table-bordered">
                        <thead class="thead-light">
                        <tr>
                            <th>Id</th>
                            <th>User</th>
                            <th>Currency</th>
                            <th>Operation</th>
                            <th>Sum</th>
                            <th>Rate</th>
                            <th>Sum_UAH</th>
                            <th>Status</th>
                            <th>Date</th>
                            <th>DELETE</th>
                            <th>EDIT</th>
                        </tr>
                        </thead>
                        <c:forEach items="${allOperation}" var="operation">
                            <tr>
                                <td>${operation.id}</td>
                                <td>${operation.nameUser}</td>
                                <td>${operation.currency}</td>
                                <td>${operation.operation}</td>
                                <td>${operation.sumOperation}</td>
                                <td>${operation.rate}</td>
                                <td>${operation.outputAmount}</td>
                                <td>${operation.status}</td>
                                <td>${operation.date}</td>
                                <td><a href="<c:url value='/admin/remove/${operation.id}'/>">Del</a></td>
                                <td><a href="<c:url value='/admin/edit/${operation.id}'/>">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>
<a href="/rest/get/json/privat">Получить все курсы ПриватБанка в формате JSON</a>
<h6>Что бы получить курс одной валюты Приватбанка в формате JSON->"/rest/get/json/privat/название валюты(usd,eur,rur)"</h6>
<br>
<a href="/rest/get/json/nbu">Получить все курсы НБУ в формате JSON</a>
<h6>Что бы получить курс одной валюты НБУ в формате JSON->"/rest/get/json/nbu/название валюты(usd,eur,rub)"</h6>

</body>
</html>
