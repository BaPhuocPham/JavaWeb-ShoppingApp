<%-- Document : checkout Created on : Mar 14, 2023, 10:58:16 PM Author : AliensVN --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="phuocpb.cart.CartObj"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <h1>Checkout</h1>
        <c:if test="${not empty sessionScope.CART}">
            <form name="btAction" action="checkoutAction">
                <c:set var="errors" value = "${requestScope.CREATE_ERRORS}"/>
                Name* <input type="text" name="txtName" required /><br />
                <c:if test="${not empty errors.nameLengthError}">
                <font color = "red">
                ${errors.nameLengthError}<br/>
                </font>
            </c:if>
                Address* <input type="text" name="txtAddress" required /><br/>
                <c:if test="${not empty errors.addressLengthError}">
                <font color = "red">
                ${errors.addressLengthError}<br/>
                </font>
            </c:if>
                <input type="hidden" value="${requestScope.USER.txtTotal}" name="txtTotal" />
                <input type="submit" value="Print Order" name="btAction" />
            </form>
        </c:if>
        <c:if test="${empty sessionScope.CART}">
            <h1 style="color: red">Your cart is not exist!</h1>
        </c:if>
    </body>
</html>

