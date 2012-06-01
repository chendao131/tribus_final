package test;

import sun.misc.BASE64Encoder;
import util.ByteUtils;

public class TestEmail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String email = "fuzantott@163.com";
		
		email.hashCode();
		BASE64Encoder encoder = new BASE64Encoder();
		  String code = encoder.encode(email.getBytes());
		  
		  System.out.println(ByteUtils.readIntBig(code.getBytes()));
	}

}
