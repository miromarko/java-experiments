<%-- 
    Document   : screen1
    Created on : Jul 9, 2013
    Author     : Miroslav MARKO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="lbl.headscreen1"/></title>
		<link href='resources/css/style.css' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,700,200' rel='stylesheet' type='text/css'>
    </head>
    <body>
		<div class='header'>
			<div class='app-title'>// Currency Exchange System</div>
			<img class='right-logo' src='resources/img/aci_logo_white_medium.png' alt='ACI Payments Inc. Logo'>
		</div>

		<div class='content-wrapper'>

            <html:form action="/preview_screen1">
				
                <h3><bean:message key="lbl.headscreen1"/></h3>
			
				<div class='current-date'>
					Current Date: <b>dd/mm/yyyy</b>
				</div>

                <div class='errors'>
                    <html:errors/>
                </div>
				
				<div class='field'>
		            <span class='field-title'><bean:message key="lbl.date"/></span>
		            <html:text property="date" />
				</div>

				<div class='field'>
	                <span class='field-title'><bean:message key="lbl.currency"/></span>
	                <html:select property="currency">
	                    <html:option value="USD" />  
	                    <html:option value="EUR" />
	                    <html:option value="GBP" />
	                    <html:option value="SGD" />
	                    <html:option value="AUD" />
	                </html:select>
				</div>

				<div class='field'>
	                <span class='field-title'><bean:message key="lbl.amount"/></span>
		            <html:text property="amount" readonly="${sessionScope.accountDataForm.pastDate < 0}" />
				</div>

				<div class='field'>
	                <span class='field-title'><bean:message key="lbl.email" /></span>
	                <html:text property="email" />
				</div>

            	<div class='field'>
	                <span class='field-title'></span>
		          	<input type="submit" class='button'>
						
					<a href='screen1persisted.jsp'><input type="button" class='button' value="View All Entries" /></a>
				</div>

            </html:form>
			
		</div>

		<div class='footer'>
			copyright &copy; Capstone Consulting Inc., 2013 <img class='footer-logo' src="resources/img/capstone_logo_small.png" alt="Capstone Inc. Logo">
			
			<div class='right-side-footer'>
				<a href="#">Help</a> | <a href="#">Privacy Policy</a>
			</div>
		</div>
    </body>
</html>
