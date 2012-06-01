package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String para_value = "2012-12-13 14:25:33";
		//para_value = "2012/12/13 14:25:33";						
		new java.util.GregorianCalendar().set(2012, 11, 01, 14, 34, 45);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-M-d H:m:s");
		try {
			
			long time = sf.parse(para_value).getTime();
			System.out.println(time);
			
			Calendar c = new GregorianCalendar();
			c.setTimeInMillis(time);
			System.out.println(c.get(Calendar.YEAR));
			System.out.println(c.get(Calendar.MONTH)+1);
			System.out.println(c.get(Calendar.DAY_OF_MONTH));
			System.out.println(c.get(Calendar.HOUR_OF_DAY));
			System.out.println(c.get(Calendar.MINUTE));
			System.out.println(c.get(Calendar.SECOND));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
