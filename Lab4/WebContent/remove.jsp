<%@ page session = "true" 
import="edu.asupoly.cst425.lab4.*" %>

<%
String itemId = request.getParameter("item");
String title = request.getParameter("title");
ReporterBean rBean = (ReporterBean)session.getAttribute("reporterBean");
String msg = "";
int id = 0;

if (title != null && title.length() > 0 && rBean != null) { 
	  if (itemId != null && itemId.length() > 0) {
		  try 
		  {
			  id = Integer.parseInt(itemId);
		  } catch (Exception exc) 
		  {
				msg = "Invalid format for news item ID";		
		  }
		  if (msg.equals("") && NewsItemBeanFactory.removeNewsItem(id)) 
		  {
			  msg = "News item \"" + title + "\" successfully removed!";
		  } 
		  else 
		  {
			  msg = "News item \"" + title + "\" could not be removed!";
		  }
	  }
}
else
{
	 msg = "Your session has expired, please login again!";
}

session.setAttribute("msg", msg);
response.sendRedirect(response.encodeURL("index.jsp"));
%>