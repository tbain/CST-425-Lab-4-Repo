package edu.asupoly.cst425.lab4.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import edu.asupoly.cst425.lab4.model.ReporterBean;
import edu.asupoly.cst425.lab4.model.ReporterBeanFactory;

public class LoginHandler implements ActionHandler{

	@Override
	public String handleAction(Map<String, String[]> params, HttpSession session) 
	{				
		String reporterId = null;
		String passwd = null;
		String errMsg = null;
		ReporterBean reporterBean = null;

		reporterId = HandlerUtilities.getParameterValue("reporterid", params);
		passwd =  HandlerUtilities.getParameterValue("passwd", params);
	
		if (reporterId == null || reporterId.length() == 0 || passwd == null || passwd.length() == 0)
			{	errMsg = "The reporterID or password cannot be empty";   }
		else if ((reporterBean = ReporterBeanFactory.getReporter(reporterId, passwd)) == null)
			{	errMsg = "The reporterID or password is not valid";   }
	
		// OK check and see if there is an error message
		if (errMsg != null) 
		{
			session.setAttribute("msg", errMsg);
		}
		else 
		{
		   	session.setAttribute("reporterBean", reporterBean);
		}
		
		return "index";
	}


}
