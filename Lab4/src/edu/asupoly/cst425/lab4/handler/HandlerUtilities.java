package edu.asupoly.cst425.lab4.handler;

import java.util.Map;
import java.util.logging.Logger;

public class HandlerUtilities {
	
	private static Logger logger = Logger.getLogger(HandlerUtilities.class.getName());
	
	/** Convenience method to make it easier to get a single parameter
	 *  based on the params object passed into an implementation of a
	 *  "handleAction" method. 
	 * 
	 * @param key The key of the parameter to get
	 * @param params the params map that contains the parameter
	 * @return the value of the parameter from the key
	 */
	public static String getParameterValue(String key, Map<String, String[]> params) {
		String[] value = (String[]) params.get(key);
		if(value == null) {
			return null;
		}
		return value[0];
	}
	
	/** Authenticates that the username and password are valid
	 * in this context that means they are the same value.
	 * 
	 * @param username 
	 * @param password
	 * @return whether they are valid (the same value)
	 */
	public static boolean validateUsernamePassword(String username, String password) {
		return !username.equalsIgnoreCase(password);
	}

}
