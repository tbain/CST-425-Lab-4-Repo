<html>
<head>
</head>
<%@ page session="true" import="edu.asupoly.cst425.lab4.ReporterBean, 
edu.asupoly.cst425.lab4.ReporterBeanFactory"
%>
<%
String reporterId = null;
String passwd = null;
String errMsg = null;
ReporterBean reporterBean = null;

reporterId = request.getParameter("reporterid");
passwd = request.getParameter("passwd");

if (reporterId == null || reporterId.length() == 0 || passwd == null || passwd.length() == 0)	
{	
	errMsg = "The reporterID or password cannot be empty";  
}
else if ((reporterBean = ReporterBeanFactory.getReporter(reporterId, passwd)) == null)	
{	
	errMsg = "The reporterID or password is not valid";   
}

// OK check and see if there is an error message
if (errMsg != null) 
{
	session.setAttribute("msg", errMsg);
}
else 
{
   	session.setAttribute("reporterBean", reporterBean);
}

response.sendRedirect(response.encodeURL("index.jsp"));
%>
 
</html>