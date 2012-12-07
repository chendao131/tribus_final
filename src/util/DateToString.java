package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {
	public static String convertDateToString(Date date){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		if(date == null)
			date= new Date();
		String reportDate = df.format(date);
		return reportDate;
	}
}
