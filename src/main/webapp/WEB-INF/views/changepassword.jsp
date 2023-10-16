<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="h"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="b" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
	<h3>Change Password</h3>
	
	<form action="/StrutsHibernate/UserDashboard.do" method="post">
		<button type="submit">Return to Dashboard</button>
	</form>
	
	<h:form action="/internal/updatePassword.do" method="post">
		<b:message key="changepasswordform.display.OldPassword"/>
		<h:password property="oldPassword" name="changePassword" redisplay="false"/> <br/>
		
		<b:message key="changepasswordform.display.NewPassword"/>
		<h:password property="newPassword" name="changePassword" redisplay="false"/> <br/>
		
		<b:message key="changepasswordform.display.RetypePassword"/>
		<h:password property="rePassword" name="changePassword" redisplay="false"/> <br/>
		
		<h:submit />
	</h:form>
	<br/>
	<h:errors/>
</body>
</html>