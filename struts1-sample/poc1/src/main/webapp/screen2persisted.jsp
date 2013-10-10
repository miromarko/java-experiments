<%-- 
    Document   : screen2persisted
    Created on : Jul 16, 2013, 1:23:06 PM
    Author     : Miroslav MARKO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/style.css" rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,700,200' rel='stylesheet' type='text/css'>
        <title><bean:message key="lbl.screen.persisted2"/></title>
    </head>
    <body>
		<div class='header'>
			<div class='app-title'>Proof of Concept 1</div>
			<img class='right-logo' src='http://www.capstonec.com/uploads/1/7/7/1/17716079/3076589.png' alt='Capstone Consulting Inc. Logo'>
		</div>

		<div class="content-wrapper">
	        <h3>New Persisted Entities</h3>
			
	        <c:forEach items=" ${sessionScope.accountDataForm2.persistable}" var="item">
	            <c:out value="${item}"/><br>
	        </c:forEach>
		</div>

		<div class="content-wrapper">
	        <h3>All Persisted Entities</h3>

		    <c:forEach items="${sessionScope.accountDataForm2.accountDataList}" var="item">
		        <c:out value="${item}"/><br>
		    </c:forEach>
		</div>

		<div class='footer'>
			copyright &copy; Capstone Consulting Inc., 2013
		</div>
	</body>
</html>
