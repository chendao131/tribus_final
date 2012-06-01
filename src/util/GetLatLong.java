package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;

public class GetLatLong {

	/**
	 *using googlemap api parse address through http
	 * 
	 * @param address
	 *
	 * @return http status code, precise (see the precise constant) , lati, longti 
	 *
	 */
	public String[] getLatlng(String address) {
		String ret = "";
		String[] names=new String[4];
		if (address != null && !address.equals("")) {
			try {
				address = URLEncoder.encode(address, "UTF-8");// avodi mess and error character
			} catch (UnsupportedEncodingException e1) {
			}			            
			// q is detination address, output is response type, key is the the keywords
			// 200 means success, 4 is precise constant, 41.87 is lati, -87 is longti
			String[] arr = new String[4];
			arr[0] = address;
			arr[1] = "csv";
			arr[2] = "true";
			arr[3] = "ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA";
			// http://maps.google.com/maps/geo?q=Chicago&output=cvs&key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA
			String url = MessageFormat
					.format(
							"http://maps.google.com/maps/geo?q={0}&output={1}&sensor={2}&key={3}",
							arr);
			URL urlmy = null;
			try {
				urlmy = new URL(url);
				HttpURLConnection con = (HttpURLConnection) urlmy
						.openConnection();
				con.setFollowRedirects(true);
				con.setInstanceFollowRedirects(false);
				con.connect();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "UTF-8"));
				String s = "";
				StringBuffer sb = new StringBuffer("");
				while ((s = br.readLine()) != null) {
					sb.append(s + "");
				}
				ret = "" + sb;
				names = ret.split("\\,");
			} catch (MalformedURLException e) {
			} catch (IOException e) {
			}
		}
		return names;
	}
}