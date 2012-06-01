package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movie;
import model.MovieComment;
import model.MovieMark;
import model.MovieTag;
import model.Starring;
import model.User;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import util.DateToString;
import util.GetSessionUser;
import vo.SearchResult;

import dao.MovieCommentDao;
import dao.MovieDao;
import dao.MovieMarkDao;
import dao.MovieTagDao;
import dao.SinglePageDao;
import dao.StarringDao;
import dao.UserDao;

public class StarringController {
	@RequestMapping("edit/{starId}")
	public ModelAndView edit(@PathVariable("starId")int starId) {
		ModelAndView mv = new ModelAndView("movie/edit/editAction");
		
		
		StarringDao sd = new StarringDao(); 
		
//		MovieDao md = new MovieDao();
//		Movie m = md.getMovieById(movieId);
		
		Starring s = sd.getStarringById(starId);
						
		mv.addObject("star",s );
//		mv.addObject("stars", m.getStars());
		mv.setViewName("star/edit");
		return mv;		
	}
	

	@RequestMapping(value="editAction" , method = RequestMethod.POST)
	public void editSubmit(HttpServletRequest request, HttpServletResponse response) {
		StarringDao sd = new StarringDao();
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		String movieNameOriginal = request.getParameter("movieNameOriginal");

		System.out.println(movieId+"*"+movieNameOriginal);
		//Movie m = md.getMovieById(Integer.parseInt(request.getParameter("movieId")));
		//m.setMovieNameOriginal("m12311");
		//md.updateMovieNameOriginal(m);
	}
	
	//add comment
	@RequestMapping("comment")
	public ModelAndView comment(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("movie/comment/commentAction");
		mv.addObject("movieId", 23); // transform moviedId from here
		return mv;
	}
	
	@RequestMapping(value = "commentSubmit", method=RequestMethod.POST)
	public void commentSubmit(HttpServletRequest request, HttpServletResponse response){
		String newComment = request.getParameter("newComment");
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		System.out.println(newComment+"*"+movieId+"*"+userId);
		MovieComment mc = new MovieComment();
		mc.setCommentContent(request.getParameter("newComment"));
		//get instance
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		UserDao ud = new UserDao();
		User u = ud.getUserById(userId);
		
		//System.out.println(m.getMovieNameOriginal()+"*");
		//System.out.println(m.getMovieId()+"*"+u.getUserId());
		mc.setCommentContent(newComment);
		mc.setMovie(m);
		mc.setUser(u);
		
		MovieCommentDao mcd = new MovieCommentDao();
		int result = mcd.save(mc);
		//System.out.println(result);
	}
	
	@RequestMapping("getComment/{movieId}")
	public ModelAndView getComment(@PathVariable("movieId")int movieId) {
		ModelAndView mv = new ModelAndView("movie/comment/getComment");
//		MovieDao md = new MovieDao();
//		Movie m = md.getMovieById(movieId);
		
		List<MovieComment> mcs = null;
		MovieCommentDao mcd = new MovieCommentDao();
		mcs = mcd.getMovieCommentById(movieId);

		Iterator<MovieComment> it = mcs.iterator();
		//Iterator<Starring> it = m.getStars().iterator();
		while(it.hasNext()){
			System.out.println(it.next().getCommentDate());
		}
		mv.addObject("movieComments", mcs);	
		return mv;		
	}
	
	@RequestMapping(value ="commentForm" , method = RequestMethod.POST)
	public ModelAndView commentForm(HttpServletRequest request, HttpServletResponse response){
		String movieId = request.getParameter("movieId");
		System.out.println(movieId);
		ModelAndView mv = new ModelAndView("movie/edit/editSuccess");
		return mv;
	}
	
	
	@RequestMapping("addMovie")
	public String addMovie(){
		return "movie/add/addAction";
	}
	
	@RequestMapping("addSubmit")
	public ModelAndView addSubmit(HttpServletRequest request,
						HttpServletResponse response){
		
/*		System.out.println(request.getParameter("movieNameOriginal"));
		System.out.println(request.getParameter("movieNameEn"));
		System.out.println(request.getParameter("MovieNameChn"));
		System.out.println(request.getParameter("directorStarName"));
		System.out.println(request.getParameter("leadStarName"));
		System.out.println(request.getParameter("tagName"));
		System.out.println(request.getParameter("movieDate"));*/
		
		ModelAndView mv = new ModelAndView("movie/add/success");
		StarringDao sd = new StarringDao();
		Set<Starring> ss = new HashSet<Starring>();
		Starring s1 = sd.getStarringByName(request.getParameter("directorStarName"));
		Starring s2 = sd.getStarringByName(request.getParameter("leadStarName"));
		ss.add(s1); ss.add(s2);
		
		
		MovieTagDao mtd = new MovieTagDao();
		Set<MovieTag> mts = new HashSet<MovieTag>();
		MovieTag mt = mtd.getMovieTagByName(request.getParameter("tagName"));
		mts.add(mt);
		
		
		Movie m = new Movie();
		m.setStars(ss);
		m.setTags(mts);
		m.setMovieNameOriginal(request.getParameter("movieNameOriginal"));
		m.setMovieNameEn(request.getParameter("movieNameEn"));
		m.setMovieNameChn(request.getParameter("movieNameChn"));
		m.setMovieNameAlias(request.getParameter("movieNameAlias"));
		m.setMovieIMDB(Integer.parseInt(request.getParameter("movieIMDB")));
		
		m.setMovieWeb(request.getParameter("movieWeb"));
		m.setMovieRegion(request.getParameter("movieWeb"));
		m.setMovieLanguage(request.getParameter("movieLanguage"));
		m.setMovieDate(Date.valueOf(request.getParameter("movieDate")));
		m.setMovieTime(Integer.parseInt(request.getParameter("movieTime")));
		m.setMovieDemo(request.getParameter("movieDemo"));
		m.setMovieBrief(request.getParameter("movieBrief"));
		
		UserDao ud = new UserDao();
		User u = ud.getUserById(1);
		m.setUser(u);
		
//		m.setMovieId(3);
		
		MovieDao md = new MovieDao();
		md.save(m);
		System.out.println("222");
		return mv;
	}
/*	public static void main(String[] args){
		MovieController mc = new MovieController();
		
	}*/
	@RequestMapping("mark")
	public String markAction(){
		return "movie/mark/markAction";
	}
	
	@RequestMapping("markSubmit/{movieId}/{userId}")
	public void markSubmit(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieId")int movieId, 
			@PathVariable("movieId")int userId){
//		System.out.println(request.getParameter("watched"));
		MovieMarkDao mmd = new MovieMarkDao();
		MovieMark mm = mmd.getMarkByMovieAndUserId(movieId, userId);
		if(mm.getMovieTagId()==1){
			System.out.println("1");
			mm.setMovieTagId(2);
		}else if(mm.getMovieTagId()==2){
			System.out.println("2");
			mm.setMovieTagId(3);
		}else{
			mm.setMovieTagId(1);
		}
		System.out.println(mmd.updateMark(mm));
	}
	
	@RequestMapping("rating")
	public String ratingAction(){
		return "movie/mark/ratingAction";
	}
	
	@RequestMapping("ratingSubmit/{userId}/{movieId}/{movieTagId}")
	public void ratingSubmit(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieId")int userId, 
			@PathVariable("movieId")int movieId, @PathVariable("movieTagId")int movieTagId){
		String rating = request.getParameter("rating");
		MovieMarkDao mmd = new MovieMarkDao();
		MovieMark mm = mmd.getMarkByMovieAndUserId(movieId, userId);
		mm.setMovieGrade(Integer.parseInt(rating));
		int result = mmd.updateMark(mm);
		System.out.println(result);
	}
	
	@RequestMapping("getAverageGrade/{movieId}")
	public ModelAndView getAverageGrade(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieId")int movieId){
		System.out.println(movieId);
		ModelAndView mv = new ModelAndView("movie/mark/getAve");
		MovieMarkDao mmd = new MovieMarkDao();
		double aveGrade = mmd.getAverageGrade(movieId);
		mv.addObject("averageGrade", aveGrade);
		System.out.println(aveGrade);
		return mv;
	}
	
	
	@RequestMapping("get/{movieId}")
	public ModelAndView getMovie(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieId")int movieId){
		
		ModelAndView mv = new ModelAndView("movie");
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		mv.addObject("movie", m);
	
		return mv;
	}
	
	
	@RequestMapping("list")
	public ModelAndView getMovieList(HttpServletRequest request,
			HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView("list");
		MovieDao md = new MovieDao();
//		Movie m = md.getMovieById(movieId);
		List l = md.getMoviesByCondition(new Movie());
		mv.addObject("movies", l);
	
		return mv;
	}
	

}
