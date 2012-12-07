package util;

import java.lang.reflect.Field;

import model.User;
import model.UserProfile;

public class ObjectCopy {
	@SuppressWarnings({ "unchecked", "finally" })
	public static Object copy(Object source, Object destination, String[] id) {

		Class clazz = source.getClass();
		Class clazz_copy = destination.getClass();

		if (!clazz.getName().equals(clazz_copy.getName())) {
			System.out.println(source + " and " + destination
					+ " shoule be same class !");
			return destination;
		}

		try {
			Field[] fields = clazz_copy.getDeclaredFields();
			for (Field field : fields) {
				String name = field.getName();
				if (!isInArray(id, name)) {
					field.setAccessible(true);
					field.set(destination, field.get(source));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			return destination;
		}
			
						
	}

	private static boolean isInArray(String[] args, String in) {

		if ("serialVersionUID".equals(in)) {
			return true;
		}

		if (in == null || args == null || args.length == 0) {
			return false;
		}

		for (String string : args) {
			if (string.equals(in)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		User u =  new User();
		u.setUserAlias("zhangsan");
		u.setUserId(22);
		
		UserProfile upf = new UserProfile();
		upf.setProfId(123124);
		upf.setProfAge(13);
		upf.setUser(u);
		
		User u1 = new User();
		u1.setUserId(11);
		

		UserProfile upf1 = new UserProfile();
		upf1.setProfId(99);
		upf1.setProfAge(23);
		upf1.setUser(u1);
		
		copy(upf, upf1, new String[]{"user","profId","userId"});
		System.out.println(123);
		System.out.println(123);
	}

}
