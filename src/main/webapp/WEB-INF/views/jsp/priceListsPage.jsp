<%--
  Created by IntelliJ IDEA.
  User: Viktor
  Date: 14.10.2016
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<jsp:include page="fragments/header.jsp"/>

<div class="form-container">
    <form:form action="addPriceList" enctype="multipart/form-data" method="post" modelAttribute="priceListModel"
               class="form-inline centralize-text">
        <div class="form-group">
            <label for="description" >Опис прайс-листу</label>
            <form:input path="description" type="text" id="description" class="form-control" placeholder="Опис прайс листу"></form:input>
        </div>
        <div class="form-group">
            <label class="btn btn-default btn-file"><span class="glyphicon glyphicon-upload"></span> Вибрати прайс-лист<input name="excelFile" type="file" value="excelFile" id="excelFile" style="display: none;">
            </label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form:form>
</div>

<div class="container">
    <h2>Список прайс-листів</h2>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Назва прайс-листу</th>
            <th>Видалення</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="priceList" items="${allPriceLists}">
                <tr>
                    <td>${priceList.description}</td>
                    <td class="centralize-text"><a href="<c:url value="/removePriceList/${priceList.id}"/>"><span class="glyphicon glyphicon-remove"></span></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
