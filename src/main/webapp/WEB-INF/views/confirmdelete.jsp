<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="h"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="b" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm Delete Account?</title>
</head>
<body>
<h3>Are you sure you want to delete your account?</h3>

<form action="/StrutsHibernate/internal/confirmDelete.do" method="get">
	<button type="submit">Confirm</button>
</form>
<form action="/StrutsHibernate/internal/cancelDelete.do" method="get">
	<button type="submit">Cancel</button>
</form>
<hr/>
<h:errors/>

</body>
</html>