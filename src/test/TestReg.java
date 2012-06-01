package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {
	public static void main(String[] args){
		
		
		String a =  "<dl>" +
		"<dt><a class=\"img\" href=\"/subject/view/178964/asdfsadfasd\"><img alt=\"Going Back\" src=\"/public/cover_img/94360380e20843dea93f2bddd454660e.jpg\"></a></dt>"+
	    "<dd><a href=\"/subject/view/178964\">Going Back</a></dd>"+
	    "</dl>";
	  
		
		
		
		
		String myDomain = "href=\"/subject/view/178964";
		String regUrl = "//?(href=[\"][\'])([a-zA-Z]*)([\"\'>]*//";
		
		
		//System.out.println(myDomain.indexOf("href=[a-zA-Z]*/[a-zA-Z]*/[0-9]*"));
		System.out.println("^href=[\\S]*/[\\S]*/[\\d]*".matches(a));
		
		System.out.println(a.matches("^href=[\\S]*/[\\S]*/[\\d]*"));
		//System.out.println(regUrl.matches(myDomain));
		
		
		
		
		
		Pattern pattern = Pattern.compile("href=[\\S]*/[\\S]*/[\\d]*");		
		Matcher matcher = pattern.matcher(a);
		// Check all occurance
		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
		}
		
		Pattern replace = Pattern.compile("\\s+");
		Matcher matcher2 = replace.matcher(a);
		
	}
}
