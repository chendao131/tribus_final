package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Activity;
import model.Movie;
import model.MovieComment;
import model.MovieMark;
import model.MovieQuickComment;
import model.MovieTag;
import model.Starring;
import model.User;
import dao.ActivityDao;
import dao.FollowDao;
import dao.HomePageDao;
import dao.MovieCommentDao;
import dao.MovieDao;
import dao.MovieImageDao;
import dao.MovieMarkDao;
import dao.MovieQuickCommentDao;
import dao.MovieTagDao;
import dao.SinglePageDao;
import dao.StarringDao;
import dao.UserDao;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import util.DateToString;
import util.GetSessionUser;
import vo.FriendsRecommendMovie;
import vo.HomePageFriendRecommend;
import vo.HomePageReview;
import vo.MovieReview;
import vo.SearchResult;
import vo.SinglePageMain;
import vo.SinglePageReview;
import vo.SingleReviewMain;
import vo.SingleReviewQuickComment;
import vo.SingleReviewRelatedArticle;

/**
 * @author Leon
 *
 */
/**
 * @author Leon
 *
 */
@Controller
@RequestMapping("movie")
public class MovieController {
	
	@RequestMapping("movieHomePage")
	public ModelAndView displayMovieHomePage(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		System.out.println(userId);
		ModelAndView mv = new ModelAndView("movie/Movie_Final");
		HomePageDao homePageDao = new HomePageDao();
		MovieDao md = new MovieDao();
		//home page recent hot movie
		List<Movie> recentHotMovies_1 = new ArrayList<Movie>();
		for(int i = 8380; i< 8395; i++){
			Movie hotMovie = md.getMovieById(i);
			recentHotMovies_1.add(hotMovie);
		}
		List<Movie> recentHotMovies_2 = new ArrayList<Movie>();
		for(int i = 8500; i< 8515; i++){
			Movie hotMovie = md.getMovieById(i);
			recentHotMovies_2.add(hotMovie);
		}
		List<Movie> recentHotMovies_3 = new ArrayList<Movie>();
		for(int i = 8515; i< 8530; i++){
			Movie hotMovie = md.getMovieById(i);
			recentHotMovies_3.add(hotMovie);
		}
		//home page review
		List<HomePageReview> homePageReviews = new ArrayList<HomePageReview>();
		for(int i=1; i<3; i++){
			HomePageReview homePageReview = homePageDao.getMovieHomePageReviewByCommentId(i);
			homePageReviews.add(homePageReview);
		}
		
		
		//home page friend recommend
		List<HomePageFriendRecommend> homePageFriendRecommends = new ArrayList<HomePageFriendRecommend>();
		HomePageFriendRecommend homePageFriendRecommend1 = homePageDao.getMovieHomePageFriendRecommend(2);
		homePageFriendRecommends.add(homePageFriendRecommend1);
		HomePageFriendRecommend homePageFriendRecommend2 = homePageDao.getMovieHomePageFriendRecommend(3);
		homePageFriendRecommends.add(homePageFriendRecommend2);
		
		mv.addObject("userId", userId);
		mv.addObject("recentHotMovies_1", recentHotMovies_1);
		mv.addObject("recentHotMovies_2", recentHotMovies_2);
		mv.addObject("recentHotMovies_3", recentHotMovies_3);
		mv.addObject("homePageReviews",homePageReviews);
		mv.addObject("homePageFriendRecommends", homePageFriendRecommends);
		//guess you like
/*		ActivityDao ad = new ActivityDao();
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(ad.getActivityById(1));
		activities.add(ad.getActivityById(2));
		System.out.println(ad.getActivityById(1).getActivityDuration());
		mv.addObject("guessYouLike", activities);*/
		return mv;
		
	}
	
	@RequestMapping("{movieId}")
	public ModelAndView displaySingleMovie(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("movie/singleMoviePage");
		SinglePageDao singlePageDao = new SinglePageDao();
		MovieDao md = new MovieDao();
		
		SinglePageMain singlePageMain = singlePageDao.getMovieSinglePage(movieId);
		List<SinglePageReview> singlePageReviews = singlePageDao.getMovieSinglePageReviewById(movieId);
		System.out.println(singlePageReviews.size());
		mv.addObject("userId", userId);
		mv.addObject("singlePageReviews", singlePageReviews);
		mv.addObject("singlePageMain", singlePageMain);
		return mv;
	}
	
	@RequestMapping("search/Movies={movieName}")
	public ModelAndView searchMoviesByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieName")String movieName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("movie/Search_Movie_Result_movie");
		SinglePageDao singlePageDao = new SinglePageDao();
		MovieDao md = new MovieDao();
		MovieMarkDao mmd = new MovieMarkDao();
		List<Movie> movies = md.searchMovieByName(movieName);
		Iterator<Movie> iterator = movies.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Movie m = iterator.next();
			System.out.println(m.getMovieId());
			System.out.println(m.getMoviePic());
			System.out.println(m.getMovieNameOriginal());
			SearchResult sr = new SearchResult();
			sr.setItemId(m.getMovieId());
			sr.setItemPic(m.getMoviePic());
			sr.setItemName(m.getMovieNameOriginal());
			if(m.getMovieDate()!=null){
				sr.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
			}else{
			}
			sr.setItemRate(mmd.getAverageGrade(m.getMovieId()));
			
			searchResults.add(sr);
		}
		System.out.println(searchResults.size());
		mv.addObject("searchResults", searchResults);
		
		return mv;
	}
	
	@RequestMapping("search/Celebrity={starName}")
	public ModelAndView searchCelebrityByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("starName")String starName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("movie/Search_Movie_Result_celebrities");
		StarringDao sd = new StarringDao();
		List<Starring> stars = sd.searchStarByName(starName);
		Iterator<Starring> iterator = stars.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Starring s = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setStarId(s.getStarId());
			sr.setStarPic(s.getStarPic());
			sr.setStarName(s.getStarName());
			sr.setSearchString(starName);
			searchResults.add(sr);
		}
		System.out.println(searchResults.size());
		mv.addObject("searchString", starName);
		mv.addObject("searchResults", searchResults);
		
		return mv;
	}
	
	@RequestMapping("reviews/{movieId}/{userId}")
	public ModelAndView getReviews(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId, @PathVariable("userId")int userId){
		ModelAndView mv = new ModelAndView("review/Review_page");
		MovieCommentDao mcd = new MovieCommentDao();
		MovieMarkDao mmd = new MovieMarkDao();
		MovieDao md = new MovieDao();
		List<MovieComment> mcs = mcd.getMovieCommentByMovieId(movieId);
		mv.addObject("movie", md.getMovieById(movieId));
		mv.addObject("movieComments", mcs);
		return mv;
	}
	
	@RequestMapping("review/{commentId}")
	public ModelAndView getSingleReview(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("commentId")int commentId){
		MovieCommentDao mcd = new MovieCommentDao();
		MovieComment mc = mcd.getMovieCommentByCommentId(commentId);
		System.out.println(mc.getUser().getUserId());
		int userId = mc.getUser().getUserId();
		int movieId = mc.getMovie().getMovieId();
		ModelAndView mv = new ModelAndView("review/Single_review");
		MovieMarkDao mmd = new MovieMarkDao();
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		
		UserDao ud = new UserDao();
		User u = ud.getUserById(userId);
		
		
		SingleReviewMain singleReviewMain = new SingleReviewMain();
		singleReviewMain.setCommentContent(mc.getCommentContent());
		singleReviewMain.setCommentTitle(mc.getCommentTitle());
		singleReviewMain.setCommentNumber(mcd.getCommentNumberByMovieId(movieId));
		singleReviewMain.setItemBrief(m.getMovieBrief());
		singleReviewMain.setItemId(m.getMovieId());
		singleReviewMain.setItemName(m.getMovieNameOriginal());
		singleReviewMain.setItemPic(m.getMoviePic());
		singleReviewMain.setItemRating(mmd.getAverageGrade(movieId));
		singleReviewMain.setUserId(userId);
		singleReviewMain.setUserName(u.getUserAlias());
		singleReviewMain.setUserPic(u.getUserPic());
		singleReviewMain.setUserRate(mmd.getMovieGradeByMovieIdAndUserId(movieId, userId));
		
		//get related article
		List<SingleReviewRelatedArticle> relatedArticles = new ArrayList<SingleReviewRelatedArticle>();
		System.out.println(movieId);
		List<MovieComment> movieComments = mcd.getMovieCommentById(movieId);
		Iterator<MovieComment> iterator = movieComments.iterator();
		mc = null;
		while(iterator.hasNext()){
			mc = iterator.next();
			SingleReviewRelatedArticle article = new SingleReviewRelatedArticle();
			article.setItemName(m.getMovieNameOriginal());
			article.setCommentTitle(mc.getCommentTitle());
			article.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			relatedArticles.add(article);
		}
		
		
		//get quick comment
		MovieQuickCommentDao mqcd = new MovieQuickCommentDao();
		List<MovieQuickComment> mqcs = mqcd.getQuickCommentByCommentId(commentId);
		System.out.println("000:"+mqcs.size());
		Iterator<MovieQuickComment> iterator1 = mqcs.iterator();
		MovieQuickComment mqc = new MovieQuickComment();
		List<SingleReviewQuickComment> singleReviewQuickComments = new ArrayList<SingleReviewQuickComment>();
		
		while(iterator1.hasNext()){
			mqc = iterator1.next();
			SingleReviewQuickComment singleReviewQuickComment = new SingleReviewQuickComment();
			
			singleReviewQuickComment.setCommentContent(mqc.getCommentContent());
			singleReviewQuickComment.setCommentDate(DateToString.convertDateToString(mqc.getCommentDate()));
			singleReviewQuickComment.setCommentTitle(mqc.getCommentTitle());
			singleReviewQuickComment.setUserPic(mqc.getUser().getUserPic());
			System.out.println("000:"+mqc.getCommentContent());
			System.out.println("000:"+mqc.getCommentTitle());
			System.out.println("000:"+DateToString.convertDateToString(mqc.getCommentDate()));
			System.out.println("000:"+mqc.getUser().getUserPic());	
			
			singleReviewQuickComments.add(singleReviewQuickComment);
		}
		System.out.println(singleReviewQuickComments.size());
/*		List<MovieComment> mcs = mcd.getMovieCommentByMovieId(movieId);
		mv.addObject("movie", md.getMovieById(movieId));
		mv.addObject("movieComments", mcs);*/
		mv.addObject("relatedArticles", relatedArticles);
		mv.addObject("singleReviewMain", singleReviewMain);
		mv.addObject("singleReviewQuickComments", singleReviewQuickComments);
		return mv;
	}
	
	@RequestMapping("get/{movieId}")
	public ModelAndView getMovie(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieId")int movieId){
		
		//ModelAndView mv = new ModelAndView("movie");
		ModelAndView mv = new ModelAndView("movie/Movie_Final");
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		mv.addObject("movie", m);
		//mv.setViewName("");
		return mv;
	}
	
	
	@RequestMapping("edit/{movieId}")
	public ModelAndView edit(@PathVariable("movieId")int movieId) {
		ModelAndView mv = new ModelAndView("movie/edit/editAction");
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		
		Set<Starring> ss = new HashSet<Starring>();
		Starring s1 = new Starring();
		Starring s2 = new Starring();
		
		s1.setStarName("niaho");
		s2.setStarName("bird");
		
		ss.add(s1);
		ss.add(s2);
		
		m.setStars(ss);

		Iterator<Starring> it = m.getStars().iterator();
		while(it.hasNext()){
			System.out.println(it.next().getStarName());
		}
		mv.addObject("movie", m);
		mv.addObject("stars", m.getStars());
		return mv;		
	}

	// edit movie
	@RequestMapping(value="editForm" , method = RequestMethod.POST)
	public ModelAndView editForm(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mv = new ModelAndView("movie/edit/editForm");
		MovieDao md = new MovieDao();
		Movie movie = md.getMovieById(1);  // movieId
		StarringDao sd = new StarringDao();
		Set<Starring> ss = new HashSet<Starring>();
		Starring s1 = sd.getStarringByName("star1");
		Starring s2 = sd.getStarringByName("star2");
		ss.add(s1); ss.add(s2);
		
		movie.setStars(ss);
		mv.addObject("movie", movie);
		return mv;
	}

	@RequestMapping(value="editSubmit" , method = RequestMethod.POST)
	public void editSubmit(HttpServletRequest request, HttpServletResponse response) {
		MovieDao md = new MovieDao();
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		String movieNameOriginal = request.getParameter("movieNameOriginal");

		System.out.println(movieId+"*"+movieNameOriginal);
		Movie m = md.getMovieById(Integer.parseInt(request.getParameter("movieId")));
		m.setMovieNameOriginal("m12311");
		md.updateMovieNameOriginal(m);
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
	
	@RequestMapping("search/{movieTag}")
	public ModelAndView searchMovieByTag(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieTag")String movieTag){
		
		ModelAndView mv = new ModelAndView("list");
		MovieDao md = new MovieDao();
//		Movie m = md.getMovieById(movieId);
		List<Movie> moviesByTag = new ArrayList<Movie>();
		moviesByTag = md.getMovieByTag(movieTag);
		
		mv.addObject("moviesByTag", moviesByTag);
	
		return mv;
	}
}