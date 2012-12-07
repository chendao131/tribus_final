package dao;

import model.BookTag;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] a){
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "ApplicationContext.xml" });
 
		BookTagDao cust = (BookTagDao) appContext.getBean("Proxy");
 
		
//		cust.printName();
		
	//	cust.printURL();AppFac.getApp
		ApplicationContext aa = AppFac.();
		
		try {
		//	cust.printThrowException();
			cust.test();
		} catch (Exception e) {
 
		}
	}
}
