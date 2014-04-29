<%-- 
    Document   : confirmRegistration
    Created on : Apr 27, 2014, 1:28:53 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>

        <title><c:out value= "Confirm registration Information!"/> </title>
    </head>
    <body>
    <c:set var="thisPage" value="registrationForm"/>
    <c:set var="welcome" value="MESSAGE_WELCOME_REGISTRATION"/>
    <%@include file="/jspf/header.jspf" %>

    <div class="goMenu" align="right">
        <fmt:message key="BUTTON_GO_LOGIN" var="goLogin"/>
        <form method="POST" action="department">
            <input type="hidden" name="command" value=""/>
            <input type="submit" value="${goLogin}"/>
        </form>
    </div>
        <div class="topMessage">
            <fmt:message key="MESSAGE_YOU_HAVE_ENTERED_DATA" var="youHaveEnteredData"/>
            <c:out value="${youHaveEnteredData}:"/><br/>
            <fmt:message key="MESSAGE_NAME_LOGIN" var="uLogin"/>
            <c:out value="${uLogin}: ${login}"/><br/>
            <fmt:message key="MESSAGE_PASSPORT_ID" var="uID"/>
            <c:out value="${uID}: ${id}"/><br/>
            <c:out value="${familyName} ${givenName} ${additionalName}"/><br/>
            <fmt:message key="MESSAGE_ADDRESS" var="uAddress"/>
            <c:out value="${uAddress}:"/><br/>
            <c:out value="${address}, ${building} ${apartment}"/>
        </div>
        <div>
            <form method="POST" action="department">
                <input type="hidden" name="locale" value="${locale}"/>
                <fmt:message key="BUTTON_BACK" var="back"/>
                <button type="submit" name="command" value="registration">${back}</button>           
                <fmt:message key="BUTTON_REGISTRATION" var="registration"/>
                <button type="submit" name="command" value="registrationCommit">${registration}</button>  
            </form>
        </div>

</body>
</html>