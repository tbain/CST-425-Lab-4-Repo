package edu.asupoly.cst425.lab4.handler;

import java.util.Map;
import javax.servlet.http.HttpSession;

public interface ActionHandler {

		public String handleAction(Map<String, String[]> params, HttpSession session);

}
