<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="h"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="b" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	Hello world <br/>
	
	<form action="/StrutsHibernate/register.do">
		<button type="submit">Register</button>
	</form>
	
	<form action="/StrutsHibernate/login.do">
		<button type="submit">Log In</button>
	</form>
</body>
</html>