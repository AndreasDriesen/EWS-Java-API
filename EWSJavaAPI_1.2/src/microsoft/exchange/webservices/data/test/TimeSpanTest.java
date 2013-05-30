/**************************************************************************
 * copyright file="TimeSpanTest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TimeSpanTest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Class TimeSpanTest.
 */
public class TimeSpanTest {
	// public sat;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar(2008, Calendar.OCTOBER, 10);
		timeSpanToXSDurationFixed(calendar);
	}

	/**
	 * Time span to xs duration.
	 * 
	 * @param timeSpan
	 *            the time span
	 * @return the string
	 */
	//TODO : what's the point of this function -- BUGGED ??? should be timespan.get(Calendar.SECOND)
	/*public static String timeSpanToXSDuration(Calendar timeSpan) {
		String offsetStr = (Calendar.SECOND < 0) ? "-" : "";
		String obj = String.format("%s %s %s %s %s ", offsetStr, Math
				.abs(Calendar.DAY_OF_MONTH), Math.abs(Calendar.HOUR_OF_DAY),
				Math.abs(Calendar.MINUTE), Math.abs(Calendar.SECOND) + "." +
						 Math.abs(Calendar.MILLISECOND));

		return obj;
	}*/
	
	
	public static String timeSpanToXSDurationFixed(Calendar timeSpan) {
		String offsetStr = (timeSpan.get(Calendar.SECOND) < 0) ? "-" : "";
		String obj = String.format("%s %s %s %s %s ", offsetStr, timeSpan.get(Calendar.DAY_OF_MONTH), 
				timeSpan.get(Calendar.HOUR_OF_DAY), timeSpan.get(Calendar.MINUTE), 
				timeSpan.get(Calendar.SECOND) + "." + timeSpan.get(Calendar.MILLISECOND));
		return obj;
	}
	
	
}
