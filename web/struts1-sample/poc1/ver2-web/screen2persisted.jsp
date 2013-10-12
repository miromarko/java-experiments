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
			<div class='app-title'>// Currency Exchange System</div>
			<img class='right-logo' src='resources/img/aci_logo_white_medium.png' alt='ACI Payments Inc. Logo'>
		</div>

		<div class="content-wrapper">
			
	        <h3>All Entries</h3>
			
			<div class='current-date'>
				Current Date: <b>dd/mm/yyyy</b>
			</div>

		    <c:forEach items="${sessionScope.accountDataForm2.accountDataList}" var="item">
		        <c:out value="${item}"/><br/>
		    </c:forEach>
			
			<br/>
			<br/>

	        <html:button property="screen2" onclick="document.location.href='screen2.jsp';">
	            <bean:message key="lbl.display.backtoscreen2" />
	        </html:button>

	        <html:button property="screen1" onclick="document.location.href='screen1.jsp';">
	            <bean:message key="lbl.display.screen1" />
	        </html:button>
			
			<input type='button' class='button' value="Print Records" />
		</div>

		<div class="content-wrapper">

	        <h3>Newly Persisted Entities</h3>
			
	        <c:forEach items=" ${sessionScope.accountDataForm2.persistable}" var="item">
	            <c:out value="${item}"/><br/>
	        </c:forEach>
		</div>

		<div class='footer'>
			copyright &copy; Capstone Consulting Inc., 2013 <img class='footer-logo' src="resources/img/capstone_logo_small.png" alt="Capstone Inc. Logo">
			
			<div class='right-side-footer'>
				<a href="#">Help</a> | <a href="#">Privacy Policy</a>
			</div>
		</div>
	</body>
</html>
