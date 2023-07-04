<%-- 
    Document   : createNewAccount.jsp
    Created on : Mar 3, 2023, 2:38:05 PM
    Author     : AliensVN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create new Account</title>
        <meta charset="UTF-8">
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Create new Account</h1>
        <form action="createNewAccountAction" method="POST">
            <c:set var="errors" value = "${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" 
                             />(6 - 20 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color = "red">
                ${errors.usernameLengthError}<br/>
                </font>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}<br/>
                </font>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6 - 30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color = "red">
                ${errors.passwordLengthError}<br/>
                </font>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color = "red">
                ${errors.confirmNotMatched}<br/>
                </font>
            </c:if>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color = "red">
                ${errors.fullnameLengthError}<br/>
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
    <script
</html>