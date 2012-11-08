package edu.asupoly.cst425.lab4.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import edu.asupoly.cst425.lab4.controller.ControllerServlet;
import edu.asupoly.cst425.lab4.model.NewsItemBeanFactory;
import edu.asupoly.cst425.lab4.model.ReporterBean;
import edu.asupoly.cst425.lab4.model.SubscriberBean;

public class SeeFavoritesHandler implements ActionHandler {

	@Override
	public String handleAction(Map<String, String[]> params, HttpSession session) 
	{
		 session.setAttribute("seeFavorites", "seeFavorites");		
		 return "index";
	}

}
