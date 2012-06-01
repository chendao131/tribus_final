package controller;

import java.util.LinkedList;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("userBook")
public class UserBookAction{
	
	@RequestMapping("userAddBook")
	public ModelAndView userAddBook() {
		 ModelAndView view = new ModelAndView();		
		 view.addObject("list", new LinkedList<String>());				 
		 view.setViewName("book/add");		 
		 return view;
	}
	

	@RequestMapping("list/{id}")
	public ModelAndView userBookList() {
		 ModelAndView view = new ModelAndView();		
		 				 		 		 		 
		 
		 view.setViewName("/user/index.jsp");		 
		 return view;
	}

	
	
	@RequestMapping("userWantBook")
	public ModelAndView userWantBook() {
		 ModelAndView view = new ModelAndView();		
		 view.addObject("list", new LinkedList<String>());		
		 
		 Book b = new Book();
		 
		 view.setViewName("/user/index.jsp");		 
		 return view;
	}
}