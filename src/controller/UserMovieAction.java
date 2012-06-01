package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application welcome page.
 */
@Controller
@RequestMapping("/userMovieAction")
public class UserMovieAction {
	

	/**
	 * Simply selects the welcome view to render by returning void and relying
	 * on the default request-to-view-translator.
	 */	
	
	
	  @RequestMapping("/userAddMovie")
	    public void userAddmovie() {
	        
	    }
	 
	    @RequestMapping("/userWantMovie/{movie_id}")
	    public String register() {	    	       
	         return "/user/want/movie/";
	    }
	 
	    @RequestMapping("/follow")
	    public ModelAndView follow() {	    	
	        ModelAndView view = new ModelAndView(); 
	        view.setViewName("/hello");
	        return view;
	    }
	    
	    
	    @RequestMapping("/unfollow")
	    public ModelAndView unfollow(){
	    	ModelAndView view = new ModelAndView();
	    	view.addObject("friend", null);
	    	return view;
	    }
}
