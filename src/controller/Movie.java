package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("movie")
public class Movie {
	
	@RequestMapping("create/{id}")
	public ModelAndView create() {
		 ModelAndView view = new ModelAndView();		 		 		
		 view.setViewName("movie/create");		 
		 return view;
	}
}