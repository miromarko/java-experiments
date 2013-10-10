<%-- 
    Document   : preview_screen1
    Created on : Jul 9, 2013, 10:27:45 AM
    Author     : Miroslav MARKO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="lbl.headpreview"/></title>
		<link href='resources/css/style.css' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,700,200' rel='stylesheet' type='text/css'>
    </head>
    <body>
		<div class='header'>
			<div class='app-title'>Proof of Concept 1</div>
			<img class='right-logo' src='http://www.capstonec.com/uploads/1/7/7/1/17716079/3076589.png' alt='Capstone Consulting Inc. Logo'>
		</div>

		<div class="content-wrapper">
            <html:form action="/screen1ok">

	            <h3><bean:message key="lbl.headpreview"/></h3>

				<div class='field'>
	                <span class='field-title'><bean:message key="lbl.date"/></span>
	                <html:text property="date" readonly="true"/>
				</div>

				<div class='field'>
	                <span class='field-title'><bean:message key="lbl.currency"/></span>
	                <html:select property="currency"  disabled="true">
	                    <html:option value="USD" />  
	                    <html:option value="EUR" />
	                    <html:option value="GBP" />
	                    <html:option value="SGD" />
	                    <html:option value="AUD" />
	                </html:select>
				</div>

				<div class='field'>
		            <span class='field-title'><bean:message key="lbl.amount"/></span>
		            <html:text property="amount" readonly="true"/>
				</div>

				<div class='field'>
	                <span class='field-title'><bean:message key="lbl.email" /></span>
	                <html:text property="email" readonly="true"/>
				</div>

				<div class='field'>
					<span class='field-title'></span>
	                <html:button property="Back" onclick="document.location.href='screen1.do';"><bean:message key="lbl.back"/></html:button>
	                <html:submit><bean:message key="lbl.continue" /></html:submit>
				</div>

            </html:form>
        </div>
		<div class='footer'>
			copyright &copy; Capstone Consulting Inc., 2013
		</div>
    </body>
</html>
