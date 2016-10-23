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

<!DOCTYPE html>
<html>
<jsp:include page="fragments/header.jsp"/>

<div class="form-group centralize-text" style="width: 60%">
    <input type="text" class="form-control" name="customerName" id="customerName"
           placeholder="Для кого призначене замовлення">
</div>

<div class="form-container">
    <form:form action="countOrderPrice" method="post" modelAttribute="orderAmountModel"
               class="form-inline centralize-text">
        <div class="form-group">
            <label for="code" >Код товару</label>
            <form:input path="order.code" type="number" id="code" class="form-control" required="required"/>
        </div>
        <div class="form-group add-left-margin">
            <label for="amount" >Кількість товару</label>
            <form:input path="amount" type="number" id="amount" class="form-control" required="required"/>
        </div>
        <div class="form-group add-left-margin">
            <label >Прайс-лист</label>
            <form:select path="order.priceList.description" class="form-control" required="required" items="${allPriceListDescriptions}"/>
        </div>
        <div class="form-group add-left-margin">
            <button type="submit" class="btn btn-primary">Добавити</button>
        </div>
    </form:form>
    <c:if test="${not empty orderErrorMessage}">
        <div class="alert alert-danger">
            ${orderErrorMessage}
        </div>
    </c:if>
</div>

<div class="container">
    <h2>Список товарів</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Код</th>
            <th>Назва товару</th>
            <th>Кількість</th>
            <th>ДЦ</th>
            <th>ПЦ</th>
            <th>Прайс-лист</th>
            <th>Видалення</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderAmount" items="${allOrderAmounts}">
            <tr>
                <td>${orderAmount.order.code}</td>
                <td>${orderAmount.order.name}</td>
                <td>${orderAmount.amount}</td>
                <td>${orderAmount.order.distributionPrice * orderAmount.amount}</td>
                <td>${orderAmount.order.purchasingPrice * orderAmount.amount}</td>
                <td>${orderAmount.order.priceList.description}</td>
                <td class="centralize-text"><a href="<c:url value="/removeOrderAmount/${orderAmount.id}"/>"><span class="glyphicon glyphicon-remove"></span></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
