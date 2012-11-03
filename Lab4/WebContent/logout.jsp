<html>
<head>
</head>
<%@ page session="true" import="edu.asupoly.cst425.lab4.ReporterBean, 
edu.asupoly.cst425.lab4.ReporterBeanFactory"
%>
<%
session.setAttribute("reporterBean", null);
response.sendRedirect("index.jsp");
%>
 
</html>