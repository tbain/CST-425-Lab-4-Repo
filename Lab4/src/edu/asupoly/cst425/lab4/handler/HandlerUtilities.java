package edu.asupoly.cst425.lab4.handler;

import java.util.Map;

public class HandlerUtilities {
	
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

}
