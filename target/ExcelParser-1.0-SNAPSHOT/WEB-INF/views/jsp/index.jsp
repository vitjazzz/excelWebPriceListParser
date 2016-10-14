<%--
  Created by IntelliJ IDEA.
  User: Viktor
  Date: 12.10.2016
  Time: 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Title</title>
</head>
<body>
    <form:form action="" enctype="multipart/form-data" method="post" modelAttribute="priceListModel">
        <label for="description" >Опис прайс-листу</label>
        <form:input path="description" type="text" id="description"></form:input>
        <input name="excelFile" type="file" value="excelFile" id="excelFile"/>
        <button type="submit">Submit</button>
    </form:form>
</body>
</html>
