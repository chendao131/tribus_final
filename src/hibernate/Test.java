package hibernate;

import java.util.Calendar;
import java.util.Date;

import model.User;

import org.hibernate.Session;

import dao.UserDao;

public class Test {
	public static void main(String[] args){
		
		
		System.out.println(123);
		Session s = TribusHibernateSessionFactory.currentSession();
		//System.out.println(s.createQuery("from Activity").list().get(0));
		
		User u = new User();
//		u.setAlias("m");
//		u.setEmail("wo");
//		u.setPassword("123");
//		u.setPic("pic");
//		u.setStatus(1);
//		u.setVerifycode(918);
		UserDao ud = new UserDao();
		//ud.add(u);
		//s.close();
		
		
		Calendar time=Calendar.getInstance();
		time.clear();
//		time.set(Calendar.YEAR,2011); //year 
//		time.set(Calendar.MONTH,3-1);//Calendar           
//		int day=time.getActualMaximum(Calendar.DAY_OF_MONTH);//
		//time.set(year, month, date, hourOfDay, minute, second)

		

		
		Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();
	}
}
