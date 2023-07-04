<%-- 
    Document   : printOrder
    Created on : Mar 9, 2023, 12:42:30 AM
    Author     : TienPhong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
    </head>
    <body>
        <h1>Your Orders</h1>
        <c:choose>
            <c:when test="${not empty sessionScope.CART}">
            <c:set var="cart" value="${sessionScope.CART}" />
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.getItems()}" />
                <form>
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="count" value="0" />
                                    <c:forEach var="key" items="${items.keySet()}">
                                        <c:set var="dto" value="${items.get(key)}" />
                                        <tr>
                                            <td>
                                                <c:set var="count" value="${count + 1}" />
                                                ${count}
                                                <input type="hidden" value="${key}" name="txtSku" />
                                            </td>
                                            <td>
                                                ${dto.name}
                                            </td>
                                            <td>
                                                ${dto.quantity}
                                            </td>
                                            <td>
                                                ${dto.price}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="4">Total: ${sessionScope.CART_TOTAL}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                        <form name="btAction" action="shoppingController">
                            <input type="submit" value="Continue to buy Book" name="btAction" />
                        </form>
                        <form name="btAction" action="loginPage">
                            <input type="submit" value="Home" lable name="btAction" />
                        </form>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>
                    No cart is existed!!
                </h2>
            </c:otherwise>
        </c:choose>
        <c:remove var="CART" scope="session"></c:remove>
        <c:remove var="CART_TOTAL" scope="session"></c:remove>
    </body>
</html>
