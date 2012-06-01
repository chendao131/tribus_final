package util;

import java.security.MessageDigest;

public class TribusMD5 {
	public static void main(String[] args) {
		System.out.println(md5Encode("jige"));
	}

	public static String md5Encode(String str) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte bytes[] = md5.digest();
			for (int i = 0; i < bytes.length; i++) {
				String s = Integer.toHexString(bytes[i] & 0xff);
				if (s.length() == 1) {
					buf.append("0");
				}
				buf.append(s);
			}
		} catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			
		}
		return buf.toString();
	}
}
