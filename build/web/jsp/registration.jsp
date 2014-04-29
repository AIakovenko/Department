<%-- 
    Document   : registration
    Created on : Apr 26, 2014, 2:26:07 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>
        <title><c:out value="Registration"/></title>

    </head>
    <body>
        <c:set var="thisPage" value="registration"/>
        <c:set var="state" value="register"/>
        <c:set var="welcome" value="MESSAGE_WELCOME_REGISTRATION"/>
        <%@include file="/jspf/header.jspf" %>

        
        <form name="loginForm" method="POST" action="department">
            <div id="registerLP">
                <fmt:message key="MESSAGE_NAME_LOGIN" var="nameLogin"/>
                <c:out value="${nameLogin}:"/><br/>
                <input type="text" name="login" value="${login}"><br/>

                <fmt:message key="MESSAGE_PASSWORD" var="passw"/>
                <c:out value="${passw}:"/><br/>
                <input type="password" name="password" value="${password}"><br/>

                <fmt:message key="MESSAGE_PASSWORD_AGAIN" var="passwAgain"/>
                <c:out value="${passwAgain}:"/><br/>
                <input type="password" name="passwordAgain" value="${passwordAgain}"><br/>
                <br/>
                <input type="hidden" name="locale" value="${locale}"/>
                <fmt:message key="BUTTON_ENTER" var="enter"/>
                <button type="submit" name="command" value="registrationForm">${enter}</button>   
            </div>
            <div id="registerName">
                <fmt:message key="MESSAGE_PASSPORT_ID" var="fID"/>
                <c:out value="${fID}:"/><br/>
                <input type="text" name="id" value="${id}"><br/>
                <fmt:message key="MESSAGE_FAMILY_NAME" var="fName"/>
                <c:out value="${fName}:"/><br/>
                <input type="text" name="familyName" value="${familyName}"><br/>
                <fmt:message key="MESSAGE_GIVEN_NAME" var="gName"/>
                <c:out value="${gName}:"/><br/>
                <input type="text" name="givenName" value="${givenName}"><br/>
                <fmt:message key="MESSAGE_ADDITIONAL_NAME" var="aName"/>
                <c:out value="${aName}:"/><br/>
                <input type="text" name="additionalName" value="${additionalName}"><br/>
            </div>
            <div id="registerAddress">
                <fmt:message key="MESSAGE_ADDRESS_STREET" var="addrStreet"/>
                <c:out value="${addrStreet}:"/><br/>
                <input type="text" name="address" value="${address}"><br/>
                <fmt:message key="MESSAGE_ADDRESS_BUILDING" var="addrBuilding"/>
                <c:out value="${addrBuilding}:"/><br/>
                <input type="text" name="building" value="${building}"><br/>
                <fmt:message key="MESSAGE_ADDRESS_FLAT" var="addrFlat"/>
                <c:out value="${addrFlat}:"/><br/>
                <input type="text" name="apartment" value="${apartment}"><br/>
            </div>
        </form>        

    </body>
</html>
