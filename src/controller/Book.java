package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("book")
public class Book {
	
	@RequestMapping("add/{id}")	
	public ModelAndView create() {
		 ModelAndView view = new ModelAndView();				 		 
		 view.setViewName("book/add");
		 return view;
	}
}