package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("community")
public class Community {
	
	
	@RequestMapping("create/{id}")
	public ModelAndView create(
			@PathVariable("id")
			int id
	) {
		 ModelAndView view = new ModelAndView();				 	
		 
		 
		 view.setViewName("community/create");		 
		 return view;
	}
}
