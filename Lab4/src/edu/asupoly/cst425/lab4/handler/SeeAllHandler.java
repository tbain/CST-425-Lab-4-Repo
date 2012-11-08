package edu.asupoly.cst425.lab4.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

public class SeeAllHandler implements ActionHandler {

	@Override
	public String handleAction(Map<String, String[]> params, HttpSession session) {
		 session.setAttribute("seeFavorites", null);		
		 return "index";
	}

}
