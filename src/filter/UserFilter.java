package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {		
				
		HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession();
        String userLogin = (String)session.getAttribute("isLogin");
                          
        if(!"alreadylogin".equals(userLogin)){                
        	httpRequest.setAttribute("error", "you have to login !!");
            httpRequest.getRequestDispatcher("/user/login.action").forward(httpRequest, httpResponse);            
        }else{
        	chain.doFilter(httpRequest, httpResponse);
        }                
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
