package util;

import junit.framework.TestCase;
import model.User;

public class ObjectCopyTest extends TestCase {

	public void testCopy() {
		User u = new User();
//		u.setAlias("Jack");
//		u.setEmail("jack@jmail.com");
//		u.setStatus(1);
//		u.setPic("ilikepic");
//		u.setVerifycode(1098765442);
		
		u.setUserAlias("Jack");
		u.setUserEmail("userEmail");
		u.setUserState(1);
		u.setUserPic("user/view/pic");
		u.setUserVerifycode(123145);
		
		u.setUserId(1);
		
		User u1 = new User();
		
		u1 = (User)ObjectCopy.copy(u, u1, null);
		System.out.println(u1.toString());
		
		u1 = new User();
		ObjectCopy.copy(u,u1, new String[]{"id"});
		System.out.println(u1.toString());
		
		u1 = new User();
		ObjectCopy.copy(u,u1, new String[]{"id","alias"});
		System.out.println(u1.toString());
		
	}

}
