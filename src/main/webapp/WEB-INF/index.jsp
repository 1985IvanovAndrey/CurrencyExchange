<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
</head>
<body>

<div class="alert alert-success" role="alert">
    <div align="center"><h2>Добро пожаловать в кассу обмена валют!</h2></div>
    <div align="right">
        <form action="/admin">
            <input class=" btn btn-outline-primary btn-sm" type="submit" value="Admin page">
        </form>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4">
            <h3>Курсы валют ПриватБанка</h3>
            <div class="table-responsive">
                <thead class="thead-light">
                <table class="table table-bordered">
                    <thead class="thead-light">
                    <tr>
                        <th>Currency</th>
                        <th>Sale</th>
                        <th>Buy</th>
                    </tr>
                    </thead>
                    <c:forEach items="${ratesPrivatList}" var="privat">
                        <tr>
                            <td>${privat.ccy}</td>
                            <td>${privat.sale}</td>
                            <td>${privat.buy}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="col-sm-2">
        </div>
        <div class="col-sm-3">
            <h3>Курсы валют НБУ</h3>
            <div class="table-responsive">
                <thead class="thead-light">
                <table class="table table-bordered">
                    <thead class="thead-light">
                    <tr>
                        <th>Currency</th>
                        <th>Rate</th>
                    </tr>
                    </thead>
                    <c:forEach items="${ratesNbuList}" var="nbu">
                        <tr>
                            <td>${nbu.ccy}</td>
                            <td>${nbu.rate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4">
            <h3>Введите данные:</h3>
            <div class="table">
                <table class="table table-bordered">
                    <tr>
                        <th>
                            <form action="/operation" method="get">
                                <div class="form-group mb-2">
                                    <label for="name" class="col-form-label-sm">Name</label>
                                    <input type="text" class="form-control col-form-label-sm" id="name" name="name"
                                           placeholder="Enter name">
                                </div>
                                <select name="operation">
                                    <option>sale</option>
                                    <option>buy</option>
                                </select>
                                <select name="currency">
                                    <option>USD</option>
                                    <option>EUR</option>
                                    <option>RUR</option>
                                </select>
                                <div class="form-group mb-2">
                                    <label for="sum" class="col-form-label-sm">Сумма</label>
                                    <input type="text" class="form-control col-form-label-sm" id="sum" name="sum"
                                           placeholder="Enter sum">
                                </div>
                                <input class=" btn btn-outline-primary btn-sm mb-2" type="submit" value="Enter">
                            </form>
                        </th>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-sm-8">
            <c:if test="${!empty allOperation}">
                <h3>Просмотр журнала операций:</h3>
                <div class="table-responsive">
                    <thead class="thead-light">
                    <table class="table table-bordered">
                        <thead class="thead-light">
                        <tr>
                            <th>User</th>
                            <th>Currency</th>
                            <th>Operation</th>
                            <th>Sum</th>
                            <th>Rate</th>
                            <th>Sum_UAH</th>
                            <th>Date</th>
                        </tr>
                        </thead>
                        <c:forEach items="${allOperation}" var="operation">
                            <tr>
                                <td>${operation.nameUser}</td>
                                <td>${operation.currency}</td>
                                <td>${operation.operation}</td>
                                <td>${operation.sumOperation}</td>
                                <td>${operation.rate}</td>
                                <td>${operation.outputAmount}</td>
                                <td>${operation.date}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
