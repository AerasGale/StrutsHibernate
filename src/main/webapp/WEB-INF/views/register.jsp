<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="h"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="b" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register new user</title>
</head>
<body>
	<form action="/StrutsHibernate/home.do" method="post">
		<button type="submit">Home</button>
	</form>
<h3>Register new user</h3>
	
	<h:form action = "/internal/saveUser.do" method="post" >
		<b:message key="registerform.display.Username"/>
			<h:text name="saveUser" property="username"/> <br/>
		<b:message key="registerform.display.Password"/>
			<h:password name="saveUser" property="password" redisplay="false"/> <br/>
		<b:message key="registerform.display.RetypePassword"/>
			<h:password name="saveUser" property="repassword" redisplay="false"/> <br/>
		<h:submit/>
	</h:form>
	
	<hr/>
	<h:errors property="usernameTakenError"/> <br/>
	<h:errors property="usernameRequiredError"/> <br/>
	<h:errors property="passwordRequiredError"/> <br/>
	<h:errors property="passwordInvalidError"/> <br/>
	<h:errors property="passwordMismatchError"/> <br/>
	
</body>
</html>