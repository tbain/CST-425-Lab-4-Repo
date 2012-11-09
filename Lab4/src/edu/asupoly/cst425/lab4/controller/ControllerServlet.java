package edu.asupoly.cst425.lab4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.asupoly.cst425.lab4.dao.Lab4DAO;
import edu.asupoly.cst425.lab4.handler.ActionHandler;
import edu.asupoly.cst425.lab4.handler.AddFavoriteHandler;
import edu.asupoly.cst425.lab4.handler.AddNewsHandler;
import edu.asupoly.cst425.lab4.handler.LoginHandler;
import edu.asupoly.cst425.lab4.handler.LogoutHandler;
import edu.asupoly.cst425.lab4.handler.RemoveFavoritesHandler;
import edu.asupoly.cst425.lab4.handler.RemoveNewsHandler;
import edu.asupoly.cst425.lab4.handler.SeeAllHandler;
import edu.asupoly.cst425.lab4.handler.SeeFavoritesHandler;
import edu.asupoly.cst425.lab4.model.NewsItemBeanFactory;
import edu.asupoly.cst425.lab4.model.SubscriberBeanFactory;

public class ControllerServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(
            "edu.asupoly.cst425.t6mvc2.controller.ControllerServlet");
    private static String errorPage = "error.jsp";   
    private static Map<String, ActionHandler> handlers = new HashMap<String, ActionHandler>();
    private static Map<String, String> pageViews = new HashMap<String, String>();
    public static final String INITPARAM_DB_URL = "databaseURL";
    private Lab4DAO dao;
    public static final String DAO = "dao";
    public static final String NEWS_ITEM_FACTORY = "news_item_factory";
    public static final String SUBSCRIBER_ITEM_FACTORY = "subscriber_item_factory";
    
    public void init(ServletConfig config) {
    	// normally I might read the action mapping from a config file
    	try {
			super.init(config);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	handlers.put("login", new LoginHandler());
    	handlers.put("logout", new LogoutHandler());
    	handlers.put("addNews", new AddNewsHandler());
    	handlers.put("removeNews", new RemoveNewsHandler());
    	handlers.put("addFavorite", new AddFavoriteHandler());
    	handlers.put("seeAll", new SeeAllHandler());
    	handlers.put("seeFavorites", new SeeFavoritesHandler());
    	handlers.put("removeFavorite", new RemoveFavoritesHandler());
    	pageViews.put("index", "/index.jsp");   
    	logger.info("in config");
    	final String dbURL = config.getInitParameter(INITPARAM_DB_URL);    	
    	Lab4DAO dao = new Lab4DAO(dbURL);
    	config.getServletContext().setAttribute(ControllerServlet.NEWS_ITEM_FACTORY, new NewsItemBeanFactory(dao));
    	config.getServletContext().setAttribute(ControllerServlet.SUBSCRIBER_ITEM_FACTORY, new SubscriberBeanFactory(dao));
    	config.getServletContext().setAttribute(ControllerServlet.INITPARAM_DB_URL, dbURL);
    }
    
    private void doAction(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {    	
    	
    	HttpSession session = request.getSession();    	
    	String forwardPage = errorPage;
    	Map<String, String[]> params = request.getParameterMap();
    	String action = request.getParameter("action");
    	String result = "";
    	
    	if (action == null)
    	{
    		action = "login";
    	}    	
    	if (action!= null && action.length() > 0) 
    	{	
    		// Forward to web application to page indicated by action
    		ActionHandler handler = handlers.get(action);
    		
    		if (handler != null) 
    		{    			
    			result = handler.handleAction(params, session);
    			
    			if (result != null && result.length() > 0)
    			{
    				forwardPage = pageViews.get(result);
    			}
    			if (forwardPage == null || forwardPage.length() == 0)
    			{
    				forwardPage = errorPage;
    			}
    		}
    	}
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }
    
    /**
     * Handle forms
     *
     * @param request HTTP Request object
     * @param response HTTP Response object
     *
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
    	// A more intelligent framework would do something more intelligent!
    	doAction(request, response);
    }

    /**
     *
     * @param request HTTP Request object
     * @param response HTTP Response object
     *
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	// A more intelligent framework would do something more intelligent!
    	doAction(request, response);
    }
}
