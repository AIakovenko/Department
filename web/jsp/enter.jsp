<%-- 
    Document   : enter
    Created on : Apr 28, 2014, 6:42:32 PM
    Author     : alex
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>
    </head>
    <body>
        <c:set var="welcome" value="MESSAGE_WELCOME_LOG_IN"/>
        <%@include file="/jspf/header.jspf" %>

        <form name="enterForm" method="POST" action="department">
            <input type="hidden" name="locale" value="${locale}"/>
            <fmt:message key="BUTTON_ENTER" var="enter"/>
            <button type="submit" name="command" value="login">${enter}</button>                     
        </form>        
    </body>
</html>