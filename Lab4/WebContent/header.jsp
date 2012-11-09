<%@ page session = "true"
import="edu.asupoly.cst425.lab4.model.*"
%>

<%! ReporterBean rBean = null;
	SubscriberBean sBean = null;
%>

<% rBean = (ReporterBean)session.getAttribute("reporterBean");
   sBean = (SubscriberBean)session.getAttribute("subscriberBean");%>
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
 <a href='<%=response.encodeURL(request.getContextPath() +"/controller") +"?action=seeAll" %>'>View All News</a> 

<%
if (rBean != null) {
%>
 <a href='<%=response.encodeURL(request.getContextPath() +"/controller") +"?action=seeFavorites" %>'>View My News</a> 
 <a href='<%= response.encodeURL("addnews.jsp") %>'>Add News</a> 
 <a href='<%=response.encodeURL(request.getContextPath() +"/controller") +"?action=logout" %>'>Logout</a> 
<%
} else if(sBean != null) {
%>	
  <a href='<%=response.encodeURL(request.getContextPath() +"/controller") +"?action=seeFavorites" %>'>See Favorites</a> 
  <a href='<%=response.encodeURL(request.getContextPath() +"/controller") +"?action=logout" %>'>Logout</a>
<% 
} else {
%>
  <a href='<%= response.encodeURL("login.jsp") %>'>Login</a>
<%
}
%>

 </center>
