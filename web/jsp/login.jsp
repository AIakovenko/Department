<%-- 
    Document   : login
    Created on : Apr 11, 2014, 11:32:15 AM
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

        <form name="loginForm" method="POST" action="department">
          

            <fmt:message key="MESSAGE_NAME_LOGIN" var="nameLogin"/>
            <c:out value="${nameLogin}:"/><br/>
            <input type="text" name="login" value=""><br/>

            <fmt:message key="MESSAGE_PASSWORD" var="password"/>
            <c:out value="${password}:"/><br/>
            <input type="password" name="password" value=""><br/>
            
            <input type="hidden" name="locale" value="${locale}"/>
            <fmt:message key="BUTTON_ENTER" var="enter"/>
            <button type="submit" name="command" value="login">${enter}</button>           
            <fmt:message key="BUTTON_REGISTRATION" var="registration"/>
            <button type="submit" name="command" value="registration">${registration}</button>           
        </form>        
    </body>
</html>