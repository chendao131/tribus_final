package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

public class TribusObjectMapping {

	private static Map<String,Class> typeMap = new HashMap<String,Class>();
	
	static{	
			typeMap.put("int", Integer.TYPE);
			typeMap.put("string",java.lang.String.class);					
			typeMap.put("date", java.util.Date.class);							
	}
	
	public static void main(String[] args) {
		try {
			Class myclass = Class.forName("model.User");
			Object o = myclass.newInstance();
			
			Field[] fs = myclass.getDeclaredFields();
			for (Field field : fs) {
				System.out.println(field.getName());
				
				Class c = field.getType();
				System.out.println(c.getName());
				if(c.getName() == "int"){
					
					String ini = field.getName();
					String n_ini = ini.substring(0,1).toUpperCase()+ini.substring(1);
					ini = null;
					
					myclass.getDeclaredMethod("set"+n_ini, Integer.TYPE).invoke(o,new Integer(1) );
				}else if(c.getName() == "java.lang.String"){
					
					String ini = field.getName();
					String n_ini = ini.substring(0,1).toUpperCase()+ini.substring(1);
					ini = null;
					Class ccc = Class.forName("java.lang.String");
					myclass.getDeclaredMethod("set"+n_ini, ccc).invoke(o,"String123" );
				}
				
				
			}
			
			User u = (model.User)o;
			System.out.println(u.getUserId());
			System.out.println(u.getUserPw());
			
			
			System.out.println(Integer.TYPE);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static Object objectCopy(String className,
			HttpServletRequest request,
			HttpServletResponse response,
			Object old,			
			String[] ids){
		Object formObj = convert(className,request,response);
		return ObjectCopy.copy(old, formObj, ids);
	}
	
	public static Object objectCopy(Object oldfromDB, Object formObj,String[] ids){
		
		
		/**
		 * copy the properties from the formObj to the oldfromDB 
		 */
		return ObjectCopy.copy(formObj,oldfromDB,ids);
	}
	
	
	@SuppressWarnings("unchecked")
	public static Object convert(Map parames,String className){

		Class myclass = null;
		try {
			myclass = Class.forName(className);
			Object o = myclass.newInstance();
			
			Field[] fs = myclass.getDeclaredFields();
			for (Field field : fs) {				
				
				Class c = field.getType();
				
				String ini = field.getName();
				
				//ini = null;
								
				Object para_value = null;
				
				
				//<input name="username" value=""/>
				if(parames.get(ini)!=null && !"".equals(parames.get(ini))){
					
					para_value = parames.get(ini);					
					String n_ini = ini.substring(0,1).toUpperCase()+ini.substring(1);
					
					if( "int".equals(c.getName())){														
						myclass.getDeclaredMethod("set"+n_ini, Integer.TYPE).invoke(o, para_value);
					}else if("java.lang.String".equals(c.getName())){
						myclass.getDeclaredMethod("set"+n_ini, typeMap.get("string")).invoke(o,para_value.toString());
					}else if("java.util.Date".equals(c.getName())){					
						
						String format = (String)parames.get("dateFormatStyle");
						
						SimpleDateFormat sf = null;
						if(format == null){
							sf = new SimpleDateFormat("yyyy-M-d H:m:s");
						}else{
							sf = new SimpleDateFormat(format);
						}
						//SimpleDateFormat sf = new SimpleDateFormat("yyyy-M-d H:m:s");						
						
						Date d = sf.parse(para_value.toString());						
						myclass.getDeclaredMethod("set"+n_ini, typeMap.get("date")).invoke(o,d);
					}else if("java.util.Calendar".equals(c.getName())){												
						String format = (String)parames.get("dateFormatStyle");
						
						SimpleDateFormat sf = null;
						if(format == null){
							sf = new SimpleDateFormat("yyyy-M-d H:m:s");
						}else{
							sf = new SimpleDateFormat(format);
						}
						//SimpleDateFormat sf = new SimpleDateFormat("yyyy-M-d H:m:s");						
												
						Date d = sf.parse(para_value.toString());
						Calendar cale = new GregorianCalendar();
						cale.setTimeInMillis(d.getTime());
						myclass.getDeclaredMethod("set"+n_ini,typeMap.get("calendar")).invoke(o,cale);
					}				
				}				
			}
			
			
			return o; 


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;


	}
	
	
	public static Object convert(String className, HttpServletRequest request,
			HttpServletResponse response) {
		
		String format = request.getParameter("dateFormatStyle");
		SimpleDateFormat sf = null;
		
		Class myclass = null;
		try {
			myclass = Class.forName(className);
			Object o = myclass.newInstance();
			
			Field[] fs = myclass.getDeclaredFields();
			for (Field field : fs) {				
				
				// field Type : Integer,String,Date
				Class c = field.getType();
				
				// field name: userId, email, password
				String ini = field.getName();																
				String para_value = null;
				
				
				//<input name="username" value=""/>
				if(request.getParameter(ini)!=null && !"".equals(request.getParameter(ini))){
				
					String n_ini = ini.substring(0,1).toUpperCase()+ini.substring(1);
					
					para_value = request.getParameter(ini);
					ini = null;
					
					if( "int".equals(c.getName())){														
						myclass.getDeclaredMethod("set"+n_ini, Integer.TYPE).invoke(o, Integer.parseInt(para_value));
					}else if("java.lang.String".equals(c.getName())){
						myclass.getDeclaredMethod("set"+n_ini, typeMap.get("string")).invoke(o,para_value);
					}else if("java.util.Date".equals(c.getName())){
																		
						if(format == null){
							sf = new SimpleDateFormat("yyyy-M-d H:m:s");
						}else{
							sf = new SimpleDateFormat(format);
						}											
						
						Date d = sf.parse(para_value);						
						myclass.getDeclaredMethod("set"+n_ini, typeMap.get("date")).invoke(o,d);
					}else if("java.util.Calendar".equals(c.getName())){
						
						
						
						if(format == null){
							sf = new SimpleDateFormat("yyyy-M-d H:m:s");
						}else{
							sf = new SimpleDateFormat(format);
						}
																						
						Date d = sf.parse(para_value.toString());
						Calendar cale = new GregorianCalendar();
						cale.setTimeInMillis(d.getTime());
						myclass.getDeclaredMethod("set"+n_ini,typeMap.get("calendar")).invoke(o,cale);
						
					}				
				}				
			}
			
			
			return o; 


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
}
