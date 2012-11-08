package edu.asupoly.cst425.lab4.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

public class LogoutHandler implements ActionHandler
{
	@Override
	public String handleAction(Map<String, String[]> params, HttpSession session) 
	{				
		if (session != null)
		{
			session.removeAttribute("reporterBean");
			session.removeAttribute("subscriberBean");
			return "index";
		}
		else
		{
			return null;
		}		
	}
}
