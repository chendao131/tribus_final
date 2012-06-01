package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

import model.User;

public class GetSessionUser {
    public static User getUser(
    		HttpServletRequest req,HttpServletResponse res
    ){
    	UserDao ud = new UserDao();
    	HttpSession session = req.getSession();
    	User u = session.getAttribute("user") == null ? ud.getUserById(1) : (User)session.getAttribute("user");	    		    	
    	return u;
    }
}
