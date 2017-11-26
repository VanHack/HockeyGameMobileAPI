package com.rivanmota.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public final class DataUtils {

	public static Calendar javaSQLDateToCalendar(Date data) {
		if (data == null) {
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(data.getTime());
		return calendar;
	}
	
	public static Calendar javaSQLTimestampToCalendar(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		return calendar;
	}
	
	public static Timestamp getTimestampFromCalendar(Calendar calendar) {
		if (ObjectUtils.isNullOrEmpty(calendar)) {
			return null;
		}
	
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	public static Calendar zerarHora(Calendar calendar) {
		Calendar c = (Calendar) calendar.clone();
		
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c;
	}
	
}
