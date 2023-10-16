<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="h"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="b" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log In</title>
</head>
<body>
	<form action="/StrutsHibernate/home.do" method="post">
		<button type="submit">Home</button>
	</form>
<h3>Login</h3>
	<h:form action="internal/generateLoginToken.do" method="post">
		<b:message key="loginform.display.Username"/>
		<h:text name="logIn" property="username"/><br/>
		
		<b:message key="loginform.display.Password"/>
		<h:password name="logIn" property="password" redisplay="false"/><br/>
		
		<h:submit/>
	</h:form>
	<hr/>
	<h:errors />
</body>
</html>