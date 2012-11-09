package edu.asupoly.cst425.lab4.handler;

import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import edu.asupoly.cst425.lab4.model.ReporterBean;
import edu.asupoly.cst425.lab4.model.ReporterBeanFactory;
import edu.asupoly.cst425.lab4.model.SubscriberBean;
import edu.asupoly.cst425.lab4.model.SubscriberBeanFactory;

public class LoginHandler implements ActionHandler
{
	private static Logger logger = Logger.getLogger(LoginHandler.class.getName());
	@Override
	public String handleAction(Map<String, String[]> params, HttpSession session) 
	{				
		String reporterId = null;
		String passwd = null;
		String errMsg = null;
		String role = null;
		ReporterBean reporterBean = null;
		SubscriberBean subscriberBean = null;

		reporterId = HandlerUtilities.getParameterValue("userid", params);
		passwd =  HandlerUtilities.getParameterValue("passwd", params);
		role = HandlerUtilities.getParameterValue("role", params);		
			
		errMsg = validateLoginPage(reporterId, passwd, role);
		
		if(errMsg != null) {
			session.setAttribute("msg", errMsg);
			return "index";
		}
		
		if(role.equals("r")) {			
			reporterBean = ReporterBeanFactory.getReporter(reporterId, passwd);					
			session.setAttribute("reporterBean", reporterBean);
		} else if(role.equals("s")) {			
			subscriberBean = SubscriberBeanFactory.getSubscriber(reporterId, passwd);		
		   	session.setAttribute("subscriberBean", subscriberBean);
		}			
		
		return "index";
	}
	
	private String validateLoginPage(String reporterId, String passwd, String role) {
		String errMsg = null;
		
		if(reporterId == null || reporterId.length() == 0 
				|| passwd == null || passwd.length() == 0) {
			errMsg = "The reporterID or password cannot be empty.";   
		} else if(HandlerUtilities.validateUsernamePassword(reporterId, passwd)) {
			errMsg = "The reporterID or password is not valid.";
		} else if(role == null) {
			errMsg = "The role solection was not valid.";
		}
		return errMsg;
	}
}
