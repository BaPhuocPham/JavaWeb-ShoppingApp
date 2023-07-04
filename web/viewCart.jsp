<%-- 
    Document   : viewCart
    Created on : Mar 14, 2023, 10:56:05 PM
    Author     : AliensVN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="phuocpb.cart.CartObj"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:choose>
            <c:when test="${not empty sessionScope.CART}">
            <c:set var="cart" value="${sessionScope.CART}" />
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.getItems()}" />
                <form action="cartDeleteItemAction">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Action</th>
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
                                            <td>
                                                <input type="checkbox" name="chkItem" 
                                                       value="${key}" checked="checked" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="3">
                                            <a href="shoppingController">Add More Books to Your Cart</a>
                                        </td>
                                        <td>Total: ${sessionScope.CART_TOTAL}</td>
                                        <td>
                                            <input type="submit" value="Remove Item" name="btAction" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                        <form action="checkOutStaticPage">
                            <input type="submit" value="Checkout" name="btAction" />
                        </form>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>
                    No cart is existed!!
                </h2>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>