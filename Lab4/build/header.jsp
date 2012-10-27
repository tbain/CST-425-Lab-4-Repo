<%@ page session = "true"
import="edu.asupoly.cst425.lab4.ReporterBean"
%>

<%! ReporterBean rBean = null; %>

<% rBean = (ReporterBean)session.getAttribute("reporterBean"); %>
<html>
 <head>
   <title>NEW News - NEW News about stuff that matters.</title>
 </head>
 <body>
 <h1>NEW News</h1>
<%
if (rBean != null) {
%>
	Welcome <%= rBean.getReporterId() %>!
<%
}
%>
 News about news that matters.
 <center>
 <a href='<%= response.encodeURL("about.jsp") %>'>About</a> 
 <a href='<%= response.encodeURL("index.jsp") %>'>View News</a>
<%
if (rBean != null) {
%>
 <a href='<%= response.encodeURL("addnews.jsp") %>'>Add News</a> 
 <a href='<%= response.encodeURL("logout.jsp") %>'>Logout</a>
<%
} else {
%>
	<a href='<%= response.encodeURL("login.html") %>'>Login</a>
<%
}
%>

 </center>
