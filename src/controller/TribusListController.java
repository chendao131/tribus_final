package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TribusClassify;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.TribusListClassifyDao;


@Controller
@RequestMapping("tribuslist")
public class TribusListController {

	
	@RequestMapping("add")
	public String addTribusList(){
		return null;
	}
	
	
	@RequestMapping("delete")
	public String deleteTribusList(){
		
		
		String[] ids = null;
		/**
		 *  
		 *  
		 *  dao.deleteByIds(ids);
		 *  
		 *  
		 */
		
		return null;
	}
	
	
	
	
	@RequestMapping("list")
	public String selectTribusList(){
							
		
		TribusListClassifyDao td = new TribusListClassifyDao();
		
		List l_book = null;// book
		
		List l_movie = null;// movie
		
		List l_music = null; // music
								
		return null;
	}
		
	@RequestMapping("classlist/{id}")
	public ModelAndView selectTribusListClass(
			HttpServletRequest req, HttpServletResponse res,			
			@PathVariable("id")		
			int id
	){
		ModelAndView mv = new ModelAndView();
		TribusListClassifyDao td = new TribusListClassifyDao();
		List<TribusClassify> l = td.getTribusListClassByUserId(id);
		mv.addObject("list", l);
		mv.setViewName("user/addTribusList");
		return mv;
	}
	
}

