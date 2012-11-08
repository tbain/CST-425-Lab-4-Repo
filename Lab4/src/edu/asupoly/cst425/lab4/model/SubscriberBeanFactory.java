package edu.asupoly.cst425.lab4.model;

import java.util.HashMap;
import java.util.Map;

public class SubscriberBeanFactory {
	
private static Map<String, SubscriberBean> subscribers = new HashMap<String, SubscriberBean>();
	
	public static SubscriberBean getSubscriber(String rid, String passwd) {
		SubscriberBean sval = subscribers.get(rid);
		
		if (sval != null && sval.getPasswd().equals(passwd)) {
			return sval;
		} else if (rid.equals(passwd)) {
			// Obviously we wouldn't do this, but we are stubbing out real auth behavior for now
			sval = new SubscriberBean(rid, passwd);
			subscribers.put(rid, sval);
		}
		return sval;
	}
	

}
