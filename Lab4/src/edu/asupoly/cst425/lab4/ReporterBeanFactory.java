/**
 * ReporterBeanFactory.java
 */
package edu.asupoly.cst425.lab4;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class provides static accessor for getting reporters.
 * 
 * @author kgary
 *
 */
public final class ReporterBeanFactory {
	private static Map<String, ReporterBean> reporters = new HashMap<String, ReporterBean>();
	
	public static ReporterBean getReporter(String rid, String passwd) {
		ReporterBean rval = reporters.get(rid);
		
		if (rval != null && rval.getPasswd().equals(passwd)) {
			return rval;
		} else if (rid.equals(passwd)) {
			// Obviously we wouldn't do this, but we are stubbing out real auth behavior for now
			rval = new ReporterBean(rid, passwd);
			reporters.put(rid, rval);
		}
		return rval;
	}
	
	private ReporterBeanFactory() {}
}
