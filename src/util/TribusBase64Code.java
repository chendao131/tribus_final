package util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TribusBase64Code {
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new BASE64Encoder()).encode(s.getBytes());
	}

	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
}
