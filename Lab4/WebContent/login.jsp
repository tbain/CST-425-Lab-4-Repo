<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<title>Reporter Login to the Web News Application</title>
<body>
<form method="post" action="<%= request.getContextPath() %>/controller?action=login">
Reporter ID: <input type="text" name="reporterid" size="25"/>
Password: <input type="password" name="passwd" size="25"/>
<p></p>
<input type="submit" value="Login"/>
</form>
</body>
</html>