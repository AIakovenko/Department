<%-- 
    Document   : error
    Created on : Apr 18, 2014, 1:06:33 PM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/departmentTags.tld" prefix="tag" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="com.bionic.iakovenko.department.locales.message"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>
        <title>Error</title>
    </head>
    <body>
        <fmt:message key="MESSAGE_ERROR" var="titleError" />
        <div class="titleMessage">
            <c:out value="${titleError}"/>
        </div>
        <div id="errMessage">
            <hr/>
            <fmt:message key="${errorMessage}" var="error" />
            <tag:error message="${error}"/>
            <hr/>
        </div>
    </body>
</html>
