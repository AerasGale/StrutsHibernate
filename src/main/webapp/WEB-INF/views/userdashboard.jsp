<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="h"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="b" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Dashboard</title>
</head>
<body>
	User Dashboard <br/>
	
	Welcome, <c:out value="${username}"></c:out> ! <br/>
	
	<form action="/StrutsHibernate/UserDashboard/ChangePassword.do" method="post">
		<button type="submit">Change Password</button>
	</form>
	
	<form action="/StrutsHibernate/UserDashboard/LogOut.do" method="post">
		<button type="submit">Log Out</button>
	</form>
	
	<form action="/StrutsHibernate/UserDashboard/DeleteAccount.do" method="post">
		<button type="submit">Delete Account</button>
	</form>
	
</body>
</html>

