package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UrlFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest hsq = (HttpServletRequest)arg0;
		String uri = hsq.getRequestURI();
		String url = hsq.getRequestURL().toString();
		System.out.println(uri);
		System.out.println(url);
		
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
