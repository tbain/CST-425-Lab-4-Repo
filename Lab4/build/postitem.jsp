<%@ page session = "true" 
import="edu.asupoly.cst425.lab4.*" %>

<%
  String title = request.getParameter("title");
  String story = request.getParameter("story");
  String itemId = request.getParameter("item");
  ReporterBean rBean = (ReporterBean)session.getAttribute("reporterBean");
  String msg = "";
  int id = 0;
  
  if (title != null && title.length() > 0 && story != null && story.length() > 0 && rBean != null) 
  { 
	  if (itemId != null && itemId.length() > 0) {
		  try {
			  id = Integer.parseInt(itemId);
		  } catch (Exception exc) {
				msg = "Invalid format for news item ID";		
		  }		
		  
		  if (msg.equals("") && NewsItemBeanFactory.editNewsItem(id, title, story, rBean.getReporterId())) {
			  msg = "News item " + id + " successfully edited!";
		  } 
		  else {
			  msg = "News item " + id + " could not be edited!";
		  }
	  } else {
		  NewsItemBeanFactory.addNewsItem(title, story, rBean.getReporterId());
		  msg = "News item successfully added!";
	  }
  }
  else
  {
	  msg = "Your session has expired, please login again!";
  }

  session.setAttribute("msg", msg);
  response.sendRedirect(response.encodeURL("index.jsp"));
%>