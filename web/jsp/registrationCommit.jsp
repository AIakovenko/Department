<%-- 
    Document   : registrationCommit
    Created on : Apr 27, 2014, 12:40:37 PM
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

        <title><c:out value= "Well Done!"/> </title>
    </head>
    <body>
        <c:set var="thisPage" value="regisrationCommit"/>
        <c:set var="welcome" value="MESSAGE_WELCOME_REGISTRATION"/>
        <%@include file="/jspf/header.jspf" %>
        
        
        <div class="topMessage">
            <fmt:message key="MESSAGE_WELL_DONE" var="done"/>
            <c:out value="${done}!" />
        </div>

        <div class="goMenu" align="right">
            <fmt:message key="BUTTON_GO_LOGIN" var="goLogin"/>
            <form method="POST" action="department">
                <input type="hidden" name="command" value=""/>
                <input type="submit" value="${goLogin}"/>
            </form>
        </div>

    </body>
</html>
