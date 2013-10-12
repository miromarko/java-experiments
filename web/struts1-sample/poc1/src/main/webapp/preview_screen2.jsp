<%-- 
    Document   : preview_screen2
    Created on : Jul 16, 2013, 12:45:26 PM
    Author     : Miroslav MARKO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="resources/css/style.css" rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,700,200' rel='stylesheet' type='text/css'>
        <title><bean:message key="lbl.headpreview"/></title>
    </head>
    <body>
		<div class='header'>
			<div class='app-title'>Proof of Concept 1</div>
			<img class='right-logo' src='http://www.capstonec.com/uploads/1/7/7/1/17716079/3076589.png' alt='Capstone Consulting Inc. Logo'>
		</div>

		<div class="content-wrapper">
            <h3><bean:message key="lbl.headpreview"/></h3>

            <html:form action="/screen2ok">
                <table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th><bean:message key="lbl.date"/></th>
                            <th><bean:message key="lbl.currency"/></th>
                            <th><bean:message key="lbl.amount"/></th>
                            <th><bean:message key="lbl.email"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td><html:text property="date0" readonly="true" /></td>
                            <td> <html:select property="currency0" disabled="true">
                                    <html:option value="USD" />  
                                    <html:option value="EUR" />
                                    <html:option value="GBP" />
                                    <html:option value="SGD" />
                                    <html:option value="AUD" />
                                </html:select> </td>
                            <td> <html:text property="amount0" readonly="true"/> </td>
                            <td> <html:text property="email0" readonly="true"/> </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td><html:text property="date1" readonly="true"/></td>
                            <td> <html:select property="currency1" disabled="true">
                                    <html:option value="USD" />  
                                    <html:option value="EUR" />
                                    <html:option value="GBP" />
                                    <html:option value="SGD" />
                                    <html:option value="AUD" />
                                </html:select> </td>
                            <td> <html:text property="amount1" readonly="true"/> </td>
                            <td> <html:text property="email1" readonly="true"/> </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td><html:text property="date2" readonly="true"/></td>
                            <td> <html:select property="currency2" disabled="true">
                                    <html:option value="USD" />  
                                    <html:option value="EUR" />
                                    <html:option value="GBP" />
                                    <html:option value="SGD" />
                                    <html:option value="AUD" />
                                </html:select> </td>
                            <td> <html:text property="amount2" readonly="true"/> </td>
                            <td> <html:text property="email2" readonly="true"/> </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td><html:text property="date3" readonly="true"/></td>
                            <td> <html:select property="currency3" disabled="true">
                                    <html:option value="USD" />  
                                    <html:option value="EUR" />
                                    <html:option value="GBP" />
                                    <html:option value="SGD" />
                                    <html:option value="AUD" />
                                </html:select> </td>
                            <td> <html:text property="amount3" readonly="true" /> </td>
                            <td> <html:text property="email3" readonly="true"/> </td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td><html:text property="date4" readonly="true" /></td>
                            <td> <html:select property="currency4" disabled="true">
                                    <html:option value="USD" />  
                                    <html:option value="EUR" />
                                    <html:option value="GBP" />
                                    <html:option value="SGD" />
                                    <html:option value="AUD" />
                                </html:select> </td>
                            <td> <html:text property="amount4" readonly="true"/> </td>
                            <td> <html:text property="email4" readonly="true"/> </td>
                        </tr>
                    </tbody>
                </table>

                <br>
                <html:button property="Back" onclick="document.location.href='screen2.do';"><bean:message key="lbl.back"/></html:button>
                <html:submit><bean:message key="lbl.continue" /></html:submit>
            </html:form>
		</div>

		<div class='footer'>
			copyright &copy; Capstone Consulting Inc., 2013
		</div>
    </body>
</html>
