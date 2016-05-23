package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
    	if (instance == null) {
        	synchronized(DateProvider.class) {
        		if ( instance == null)
        			instance = new DateProvider();
        	}
        }
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public Date addDays(int dd) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(new Date()); // Now use today date.
    	c.add(Calendar.DATE, dd); // 10 days back
    	return c.getTime() ;
    }
}
