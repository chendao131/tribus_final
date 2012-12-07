package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.servlet.http.HttpUtils;

import org.omg.IOP.Encoding;

//import com.sun.xml.internal.txw2.annotation.XmlElement;

public class getLang {

	private static final String KEY = "ABQIAAAAVjCvQMEky2Xe635UMlEabxTO38AnY8DSJFAp3kJGTR39HzmMnBRGDjb8R13u9dIrEKxIdjEXD20McA";
	private static final String OUTPUT = null;
	private static final String SENSOR = "true";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getLang g = new getLang();
		g.getLatlng("400 e 33rd st chicago");
	}

//	public static String GetlatlngByAddress(String address){   
//		String latlng = null;    try    {   
//			address = HttpUtils.UrlEncode(address, Encoding.UTF8);    
//			result = WebContent.GetDataFromUrl("http://maps.google.com/maps/geo?q=" + address + "&output=xml&sensor=true&key=dc27b1d958ee7725aa1b6899af7b50816258da9bf3ffa0f736db1bf3ca24877b7a25104e7f587e1c");     
//			XmlElement xml = new XmlElement();      
//			
//			((Object) xml).LoadXml(latlng);    
//			latlng = GetXMLValue(xml, "Response/Placemark/Point/coordinates");   
//			}    catch    {    
//				
//			}    return latlng;
//			}

	private String getLatlng(String address) {
		String ret = "";
		if (address != null && !address.equals("")) {
			try {
				address = URLEncoder.encode(address, "UTF-8");// 进行这一步是为了避免乱码
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			String[] arr = new String[4];
			arr[0] = address;
			arr[1] = OUTPUT;
			arr[2] = SENSOR;
			arr[3] = KEY;
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
					sb.append(s + "\r\n");
				}
				ret = "" + sb;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				;
			}
		}
		return ret;
	}

}
