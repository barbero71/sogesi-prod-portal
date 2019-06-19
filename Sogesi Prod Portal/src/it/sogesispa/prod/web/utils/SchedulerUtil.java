package it.sogesispa.prod.web.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SchedulerUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
	public static boolean isDayOK(Date d, String[] excludedDate)
	{
		boolean ret = false;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			ret=true;
			for(String s: excludedDate)
			{
				if(sdf.format(d).equalsIgnoreCase(s))
				{
					ret=false;
				}
			}
		}
		return ret;
	}
}
