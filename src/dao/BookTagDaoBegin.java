package dao;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class BookTagDaoBegin implements MethodBeforeAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.print("Log something...");
		se
		tx
	}

}
