package test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import model.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.TribusObjectMapping;

public class TribusObjectMappingDateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testConvertMapString() {
		
		Map m = new HashMap();
	    User u = new User();
	    m.put("userId", 1);
	    m.put("email", "123@123.com");
	    m.put("alias", "Jack");		
		
		User u1 = (User)TribusObjectMapping.convert(m, "model.User");
		Assert.assertEquals(1, u1.getUserId());
		Assert.assertEquals("123@123.com", u1.getEmail());
		Assert.assertEquals("Jack", u1.getAlias());
		//fail("Not yet implemented");
	}

}
