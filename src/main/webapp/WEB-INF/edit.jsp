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
    <title>Edit Operation</title>
</head>
<body>
<div class="=container">
    <div class="col-sm-5">
        <form action="/admin/edit/operation" method="get">
            <div class="form-group mb-2">
                <label for="name" class="col-form-label-sm">Name</label>
                <input type="text" class="form-control col-form-label-sm" id="name" name="name"
                       placeholder="Enter name">
            </div>
            <select name="currency">
                <option>USD</option>
                <option>EUR</option>
                <option>RUR</option>
            </select>
            <select name="operation">
                <option>sale</option>
                <option>buy</option>
            </select>
            <div class="form-group mb-2">
                <label for="sumOperation" class="col-form-label-sm">Sum operation</label>
                <input type="text" class="form-control col-form-label-sm" id="sumOperation" name="sumOperation"
                       placeholder="Enter sum_operation">
            </div>
            <div class="form-group mb-2">
                <label for="rate" class="col-form-label-sm">Rate</label>
                <input type="text" class="form-control col-form-label-sm" id="rate" name="rate"
                       placeholder="Enter rate">
            </div>
            <div class="form-group mb-2">
                <label for="sumUah" class="col-form-label-sm">Sum UAH</label>
                <input type="text" class="form-control col-form-label-sm" id="sumUah" name="sumUah"
                       placeholder="Enter sumUah">
            </div>
            <input class=" btn btn-outline-primary btn-sm mb-2" type="submit" value="Edit">
        </form>
    </div>
</div>
</body>
</html>
