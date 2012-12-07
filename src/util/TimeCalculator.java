package util;

import java.sql.Timestamp;

import dao.MovieMarkDao;

public class TimeCalculator {
	public static String getPastMins(Timestamp t){
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String output;
		long diff = Math.abs((t.getTime()- now.getTime())/60000);
		if(diff < 60){
			output = String.valueOf(diff)+ "mins";
		}else if(60<=diff & diff<1440){
			output = String.valueOf(diff/60) + "hours";
		}else if(1440<=diff & diff<4320){
			output = String.valueOf(diff/1440) + "days";
		}else{
			output = "3 days";
		}
		return output;
	}
	
	public static void main(String args[]){
		MovieMarkDao mmd = new MovieMarkDao();
		System.out.println(TimeCalculator.getPastMins(mmd.getTimestampByMarkId(1)));
	}
}
