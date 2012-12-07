package controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Activity;
import model.Message;
import model.Movie;
import model.MovieComment;
import model.MovieImage;
import model.MovieMark;
import model.MovieQuickComment;
import model.MovieTag;
import model.MyTribusList;
import model.StarType;
import model.Starring;
import model.TribusClassify;
import model.User;
import model.UserProfile;
import dao.ActivityDao;
import dao.HomePageDao;
import dao.MessageDao;
import dao.MovieCommentDao;
import dao.MovieDao;
import dao.MovieImageDao;
import dao.MovieMarkDao;
import dao.MovieQuickCommentDao;
import dao.MovieTagDao;
import dao.SinglePageDao;
import dao.StarTypeDao;
import dao.StarringDao;
import dao.TribusListClassifyDao;
import dao.TribusListDao;
import dao.UserDao;
import dao.UserProfileDao;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import config.GlobleConfig;

import util.DateToString;
import util.GetSessionUser;
import util.PageObject;
import util.Paging;
import vo.HomePageFriendRecommend;
import vo.HomePageReview;
import vo.MessageVO;
import vo.MyTribusListVO;
import vo.SearchResult;
import vo.SinglePageFriendsRecord;
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
	@RequestMapping("picsSubmit/{movieId}")
	public String picsSubmit(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("movieId")int movieId){
		String redirect = "redirect:/movie/"+movieId+".action";
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		MovieDao md = new MovieDao();
		MovieImageDao mid = new MovieImageDao();
		for(int i=1 ; i<=6; i++){
			if(!request.getParameter("successful_img_path_"+i).equals("none")){
				MovieImage mi = new MovieImage();
				mi.setMovie(md.getMovieById(movieId));
				mi.setImagePath(request.getParameter("successful_img_path_"+i));
				mi.setImageDescription(request.getParameter("successful_img_decs_"+i));
				mid.save(mi);
				//mid.saveImageByMovieIdAndPath(movieId, request.getParameter("successful_img_path_"+i));
			}
		}
		return redirect;
	}
	
	@RequestMapping("uploadPicsAction/{movieId}")
	public ModelAndView uploadPics(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		ModelAndView mv = new ModelAndView("movie/Upload_pics");
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("itemId", movieId);
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("saveMovieCreation")
	public String saveMovieCreation(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		MovieDao md = new MovieDao();
		Movie m = new Movie();
		Set<Starring> stars = new HashSet<Starring>();
		Set<MovieTag> tags = new HashSet<MovieTag>();
		StarringDao sd= new StarringDao();
		StarTypeDao std = new StarTypeDao();
		String directors[] = request.getParameter("movieDirector").split(",");
		for(int i=0; i<directors.length; i++){
			Starring s = sd.getStarringByName(directors[i]);
			if(s!=null){
				stars.add(s);
			}
			else{
				Starring newStar = new Starring();
				newStar.setStarName(directors[i]);
				newStar.setStarType(std.getStarTypeById(1));
				sd.save(newStar);
				stars.add(newStar);
			}
		}
		String actors[] = request.getParameter("movieActor").split(",");
		for(int i=0; i<actors.length; i++){
			Starring s = sd.getStarringByName(actors[i]);
			if(s!=null){
				stars.add(s);
			}
			else{
				Starring newStar = new Starring();
				newStar.setStarName(actors[i]);
				newStar.setStarType(std.getStarTypeById(2));
				sd.save(newStar);
				stars.add(newStar);
			}
		}
		
		MovieTagDao mtd = new MovieTagDao();
		MovieTag mt = mtd.getMovieTagByName(request.getParameter("movieTag"));
		tags.add(mt);
		m.setTags(tags);
		
		m.setMoviePic(request.getParameter("successful_img_path"));
		m.setMoviePicBig(request.getParameter("successful_img_path_big"));
		m.setMoviePicMiddle(request.getParameter("successful_img_path_middle"));
		m.setMoviePicSmall(request.getParameter("successful_img_path_small"));
		m.setMovieNameOriginal(request.getParameter("movieNameOriginal"));
		m.setMovieRegion(request.getParameter("movieRegion"));
		if(!GenericValidator.isBlankOrNull(request.getParameter("movieDate")))
			m.setMovieDate(Date.valueOf(request.getParameter("movieDate")));
		m.setMovieBrief(request.getParameter("movieBrief"));
		m.setMovieRating(request.getParameter("movieRating"));
		m.setStars(stars);
		md.save(m);
		

		return "redirect:/movie/"+m.getMovieId()+".action";
	}
	
	@RequestMapping("createMovie")
	public ModelAndView createMovieAction(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		ModelAndView mv = new ModelAndView("movie/create_movie");
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("markMovie/{movieId}")
	public void markMovie(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		String mark = request.getParameter("mark");
		if(mark.equals("wanted")){
			MovieMarkDao mmd = new MovieMarkDao();
			mmd.markWatchWantedByMovieIdAndUserId(movieId, userId);
		}else if(mark.equals("done")){
			MovieMarkDao mmd = new MovieMarkDao();
			mmd.markWatchDoneByMovieIdAndUserId(movieId, userId);
		}
	}
	
	@RequestMapping("rateMovie/{movieId}")
	public void rateMovie(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		MovieMarkDao mmd = new MovieMarkDao();
		mmd.rateByMovieIdAndUserId(movieId, userId, Integer.parseInt(request.getParameter("rate")));
		mmd.markWatchDoneByMovieIdAndUserId(movieId, userId);
	}
	
	@RequestMapping("deleteRate/{movieId}")
	public void deleteRate(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		MovieMarkDao mmd = new MovieMarkDao();
		mmd.deleteRate(movieId, userId);
	}
	
	@RequestMapping("editMovie/{movieId}")
	public ModelAndView editMovie(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		ModelAndView mv = new ModelAndView("movie/edit_movie");
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);

		Set<Starring> stars = m.getStars();
		Iterator<Starring> iterator = stars.iterator();
		String directors="", actors="";
		int directorFlag=0, actorFlag=0;
		while(iterator.hasNext()){
			Starring s = iterator.next();
			if(s.getStarType().getTypeId()==1){
				if(directorFlag==0){
					directors = s.getStarName();
					directorFlag = 1;
				}else{
					directors=directors+", "+s.getStarName();
				}
			}else{
				if(actorFlag==0){
					actors = s.getStarName();
					actorFlag = 1;
				}else{
					actors = actors+", "+s.getStarName();
				}
			}
		}

		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("movie", m);
		mv.addObject("movieDirectors", directors);
		mv.addObject("movieActors", actors);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("saveMovieEdition/{movieId}")
	public String saveMovieEdition(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		StarringDao sd = new StarringDao();
		StarTypeDao std = new StarTypeDao();
		Set<Starring> stars = new HashSet<Starring>();
		Set<MovieTag> tags = new HashSet<MovieTag>();
		String directors[] = request.getParameter("movieDirector").split(",");
		for(int i=0; i<directors.length; i++){
			Starring s = sd.getStarringByName(directors[i]);
			if(s!=null){
				stars.add(s);
			}
			else{
				Starring newStar = new Starring();
				newStar.setStarName(directors[i]);
				newStar.setStarType(std.getStarTypeById(1));
				sd.save(newStar);
				stars.add(newStar);
			}
				
		}
		String actors[] = request.getParameter("movieActor").split(",");
		for(int i=0; i<actors.length; i++){
			Starring s = sd.getStarringByName(actors[i]);
			if(s!=null){
				stars.add(s);
			}
			else{
				Starring newStar = new Starring();
				newStar.setStarName(actors[i]);
				newStar.setStarType(std.getStarTypeById(2));
				sd.save(newStar);
				stars.add(newStar);
			}
		}
		
		MovieTagDao mtd = new MovieTagDao();
		MovieTag mt = mtd.getMovieTagByName(request.getParameter("movieTag"));
		tags.add(mt);
		m.setTags(tags);
		
		m.setMoviePic(request.getParameter("successful_img_path"));
		m.setMovieNameOriginal(request.getParameter("movieNameOriginal"));
		m.setMovieRegion(request.getParameter("movieRegion"));
		if(!GenericValidator.isBlankOrNull(request.getParameter("movieDate")))
			m.setMovieDate(Date.valueOf(request.getParameter("movieDate")));
		m.setMovieBrief(request.getParameter("movieBrief"));
		m.setStars(stars);
		m.setMovieRating(request.getParameter("movieRating"));
		md.update(m);
		return "redirect:/movie/"+movieId+".action";
	}
	
	@RequestMapping("movieHomePage")
	public ModelAndView displayMovieHomePage(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("movie/Movie_Final");
		HomePageDao homePageDao = new HomePageDao();
		MovieDao md = new MovieDao();
		//home page recent hot movie
		List<Movie> recentHotMovies_1 = new ArrayList<Movie>();
		//recentHotMovies_1.add(md.getMovieById(8772));
		recentHotMovies_1.add(md.getMovieById(20684));recentHotMovies_1.add(md.getMovieById(20637));
		recentHotMovies_1.add(md.getMovieById(19222));recentHotMovies_1.add(md.getMovieById(18897));
		recentHotMovies_1.add(md.getMovieById(13780));recentHotMovies_1.add(md.getMovieById(18648));
		recentHotMovies_1.add(md.getMovieById(20447));recentHotMovies_1.add(md.getMovieById(60));
		recentHotMovies_1.add(md.getMovieById(61));recentHotMovies_1.add(md.getMovieById(5619));
		List<Movie> recentHotMovies_2 = new ArrayList<Movie>();
		recentHotMovies_2.add(md.getMovieById(19948));recentHotMovies_2.add(md.getMovieById(116));
		recentHotMovies_2.add(md.getMovieById(20690));recentHotMovies_2.add(md.getMovieById(6161));
		recentHotMovies_2.add(md.getMovieById(19316));recentHotMovies_2.add(md.getMovieById(20691));
		recentHotMovies_2.add(md.getMovieById(16622));recentHotMovies_2.add(md.getMovieById(79));
		recentHotMovies_2.add(md.getMovieById(20689));recentHotMovies_2.add(md.getMovieById(14266));
		
		//home page review
		List<HomePageReview> homePageReviews = new ArrayList<HomePageReview>();
		for(int i=1; i<3; i++){
			HomePageReview homePageReview = homePageDao.getMovieHomePageReviewByCommentId(i);
			if(homePageReview!=null)
				homePageReviews.add(homePageReview);
		}
		
		//home page friend recommend
		List<HomePageFriendRecommend> homePageFriendRecommends = homePageDao.getMovieHomePageFriendRecommend(u.getUserId());

		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		
		//tribus list
		dao.TribusListDao td = new dao.TribusListDao();
		List<MyTribusList> tribusList = td.getHotMovieTribusList();
		
		mv.addObject("recentHotMovies_1", recentHotMovies_1);
		mv.addObject("recentHotMovies_2", recentHotMovies_2);
		//mv.addObject("recentHotMovies_3", recentHotMovies_3);
		if(!homePageReviews.isEmpty())
			mv.addObject("homePageReviews",homePageReviews);
		if(homePageFriendRecommends!=null)
			mv.addObject("homePageFriendRecommends", homePageFriendRecommends);
		mv.addObject("tribusList", tribusList);
		
		//guess you like
		ActivityDao ad = new ActivityDao();
		List<Activity> activities = ad.getHottestActivity();
		mv.addObject("guessYouLike", activities);
		return mv;
		
	}
	
	@RequestMapping("{movieId}")
	public ModelAndView displaySingleMovie(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		TribusListClassifyDao tcDao = new TribusListClassifyDao();
		MovieMarkDao mmd = new MovieMarkDao();
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("movie/singleMoviePage");
		SinglePageDao singlePageDao = new SinglePageDao();
		MovieDao md = new MovieDao();
		
		SinglePageMain singlePageMain = singlePageDao.getMovieSinglePage(movieId);
		List<SinglePageReview> singlePageReviews = singlePageDao.getMovieSinglePageReviewById(movieId);
		List<SinglePageFriendsRecord> friendRecords = singlePageDao.getMovieSinglePageFriendsRecord(userId, movieId);
		Integer markWatch = 0;
		if(mmd.getMarkByMovieAndUserId(movieId, userId)!=null)
			markWatch = mmd.getMarkByMovieAndUserId(movieId, userId).getMovieWatch();
		mv.addObject("markWatch", markWatch);
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		List<TribusClassify> l = tcDao.getTribusListClassByUserId(u.getUserId());
		mv.addObject("list", l);
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		mv.addObject("singlePageReviews", singlePageReviews);
		mv.addObject("singlePageMain", singlePageMain);
		mv.addObject("SinglePageFriendsRecord", friendRecords);
		mv.addObject("singlePageImages", singlePageDao.getMovieSinglePageGallery(movieId));
		
		mv.addObject("myRate", mmd.getMovieGradeByMovieIdAndUserId(movieId, userId));
		return mv;
	}
	
	@RequestMapping("search/{type}")
	public String searchMovieAction(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("type")int type){
		
		String redirect = null;
		String name = request.getParameter("single_search_name");
		switch(type){
		case 1: redirect = "redirect:/movie/search/Movies="+name+".action";break;
		case 2: redirect = "redirect:/movie/search/Celebrities="+name+".action";break;
		case 3: redirect = "redirect:/movie/search/List="+name+".action";break;
		}
		return redirect;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("search/Movies={movieName}")
	public ModelAndView searchMoviesByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieName")String movieName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		ModelAndView mv = new ModelAndView("movie/Search_Movie_Result_movie");
		SinglePageDao singlePageDao = new SinglePageDao();
		MovieDao md = new MovieDao();
		MovieMarkDao mmd = new MovieMarkDao();
		List<Movie> movies = md.searchMovieByName(movieName);
		Iterator<Movie> iterator = movies.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Movie m = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setItemId(m.getMovieId());
			sr.setItemPic(m.getMoviePic());
			sr.setItemName(m.getMovieNameOriginal().length()>15? (m.getMovieNameOriginal().subSequence(0, 15)+"..."): m.getMovieNameOriginal());
			if(m.getMovieDate()!=null){
				sr.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
			}else{
			}
			sr.setItemRate(mmd.getAverageGrade(m.getMovieId()));
			
			searchResults.add(sr);
		}

		int page=0;
		 if(GenericValidator.isInt(request.getParameter("page"))){
			 page = Integer.parseInt(request.getParameter("page"));
			  
		}
	 	Paging p = new Paging();
		p.setObj(searchResults);			
		p.setHaveOtherParameters(false);
		p.setEvery_page_item_num(20);
		if(page <= 0){
			page = 1;
		}
		PageObject po = p.getResult(GlobleConfig.my_domain+"/movie/search/Movies="+movieName+".action",page);
		mv.addObject("searchResults", po.getL());
		mv.addObject("pageStr", po.getPageCode());
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
//		mv.addObject("searchResults", searchResults);
		mv.addObject("searchName", movieName);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("search/Celebrities={starName}")
	public ModelAndView searchCelebrityByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("starName")String starName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
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

		int page=0;
		 if(GenericValidator.isInt(request.getParameter("page"))){
			 page = Integer.parseInt(request.getParameter("page"));
			  
		}
	 	Paging p = new Paging();
		p.setObj(searchResults);			
		p.setHaveOtherParameters(false);
		p.setEvery_page_item_num(20);
		if(page <= 0){
			page = 1;
		}
		PageObject po = p.getResult(GlobleConfig.my_domain+"/movie/search/Celebrities="+starName+".action",page);
		mv.addObject("searchResults", po.getL());
		mv.addObject("pageStr", po.getPageCode());
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("searchName", starName);
//		mv.addObject("searchResults", searchResults);
		
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("search/List={listName}")
	public ModelAndView searchTribusListByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("listName")String listName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		TribusListDao tld = new TribusListDao();
		ModelAndView mv = new ModelAndView("movie/Search_Movie_Result_list");
		List<MyTribusListVO> searchResults = tld.getTribusListMovieByResourceName(listName);
		
		int page=0;
		 if(GenericValidator.isInt(request.getParameter("page"))){
			 page = Integer.parseInt(request.getParameter("page"));
			  
		}
	 	Paging p = new Paging();
		p.setObj(searchResults);			
		p.setHaveOtherParameters(false);
		p.setEvery_page_item_num(20);
		if(page <= 0){
			page = 1;
		}
		PageObject po = p.getResult(GlobleConfig.my_domain+"/movie/search/List="+listName+".action",page);
		mv.addObject("searchResults", po.getL());
		mv.addObject("pageStr", po.getPageCode());
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("searchName", listName);
//		mv.addObject("searchResults", searchResults);	
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("reviews/{movieId}/{userId}")
	public ModelAndView getReviews(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId, @PathVariable("userId")int userId){
		User u = GetSessionUser.getUser(request, response);
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("review/Review_page");
		MovieCommentDao mcd = new MovieCommentDao();
		MovieMarkDao mmd = new MovieMarkDao();
		MovieDao md = new MovieDao();
		List<MovieComment> mcs = mcd.getMovieCommentByMovieId(movieId);
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("movie", md.getMovieById(movieId));
		mv.addObject("movieComments", mcs);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("review/{commentId}")
	public ModelAndView getSingleReview(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("commentId")int commentId){
		User me = GetSessionUser.getUser(request, response);
		int meId = me.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(meId);
		MovieCommentDao mcd = new MovieCommentDao();
		MovieComment mc = mcd.getMovieCommentByCommentId(commentId);
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
			singleReviewQuickComments.add(singleReviewQuickComment);
		}
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("relatedArticles", relatedArticles);
		mv.addObject("singleReviewMain", singleReviewMain);
		mv.addObject("singleReviewQuickComments", singleReviewQuickComments);
		
		mv.addObject("userName", me.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("get/{movieId}")
	public ModelAndView getMovie(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		//ModelAndView mv = new ModelAndView("movie");
		ModelAndView mv = new ModelAndView("movie/Movie_Final");
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("movie", m);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		//mv.setViewName("");
		return mv;
	}
	
	
	@RequestMapping("edit/{movieId}")
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieId")int movieId) {
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
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
		mv.addObject("movie", m);
		mv.addObject("stars", m.getStars());
		
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;		
	}

	// edit movie
	@RequestMapping(value="editForm" , method = RequestMethod.POST)
	public ModelAndView editForm(HttpServletRequest request,
			HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
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
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}

	@RequestMapping(value="editSubmit" , method = RequestMethod.POST)
	public void editSubmit(HttpServletRequest request, HttpServletResponse response) {
		MovieDao md = new MovieDao();
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		String movieNameOriginal = request.getParameter("movieNameOriginal");

		Movie m = md.getMovieById(Integer.parseInt(request.getParameter("movieId")));
		m.setMovieNameOriginal("m12311");
		md.updateMovieNameOriginal(m);
	}
	
	//add comment
	@RequestMapping("comment")
	public ModelAndView comment(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("movie/comment/commentAction");
		mv.addObject("movieId", 23); // transform moviedId from here
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping(value = "commentSubmit", method=RequestMethod.POST)
	public void commentSubmit(HttpServletRequest request, HttpServletResponse response){
		String newComment = request.getParameter("newComment");
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		MovieComment mc = new MovieComment();
		mc.setCommentContent(request.getParameter("newComment"));
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		UserDao ud = new UserDao();
		User u = ud.getUserById(userId);
		mc.setCommentContent(newComment);
		mc.setMovie(m);
		mc.setUser(u);
		
		MovieCommentDao mcd = new MovieCommentDao();
		int result = mcd.save(mc);
	}
	
	@RequestMapping("getComment/{movieId}")
	public ModelAndView getComment(HttpServletRequest request, HttpServletResponse response, @PathVariable("movieId")int movieId) {
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);

		
		ModelAndView mv = new ModelAndView("movie/comment/getComment");
//		MovieDao md = new MovieDao();
//		Movie m = md.getMovieById(movieId);
		
		List<MovieComment> mcs = null;
		MovieCommentDao mcd = new MovieCommentDao();
		mcs = mcd.getMovieCommentById(movieId);

		Iterator<MovieComment> it = mcs.iterator();
		mv.addObject("movieComments", mcs);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;		
	}
	
	@RequestMapping(value ="commentForm" , method = RequestMethod.POST)
	public ModelAndView commentForm(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		String movieId = request.getParameter("movieId");
		ModelAndView mv = new ModelAndView("movie/edit/editSuccess");
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	
	@RequestMapping("addMovie")
	public String addMovie(){
		return "movie/add/addAction";
	}
	
	@RequestMapping("addSubmit")
	public ModelAndView addSubmit(HttpServletRequest request,
						HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
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
		
		
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());

		m.setUser(u);
		
//		m.setMovieId(3);
		
		MovieDao md = new MovieDao();
		md.save(m);
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
		MovieMarkDao mmd = new MovieMarkDao();
		MovieMark mm = mmd.getMarkByMovieAndUserId(movieId, userId);
		if(mm.getMovieTagId()==1){
			mm.setMovieTagId(2);
		}else if(mm.getMovieTagId()==2){
			mm.setMovieTagId(3);
		}else{
			mm.setMovieTagId(1);
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
	}
	
	@RequestMapping("getAverageGrade/{movieId}")
	public ModelAndView getAverageGrade(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("movie/mark/getAve");
		MovieMarkDao mmd = new MovieMarkDao();
		double aveGrade = mmd.getAverageGrade(movieId);
		mv.addObject("averageGrade", aveGrade);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping("searchByTag/{movieTag}")
	public ModelAndView searchMovieByTag(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("movieTag")String movieTag){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("movie/Search_Movie_Result_movie");
		MovieDao md = new MovieDao();
		MovieMarkDao mmd = new MovieMarkDao();
//		Movie m = md.getMovieById(movieId);
		List<Movie> moviesByTag = md.searchMovieByTag(movieTag);
		Iterator<Movie> iterator = moviesByTag.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Movie m = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setItemId(m.getMovieId());
			if(m.getMoviePic()!=null)
				sr.setItemPic(m.getMoviePic());
			else
				sr.setItemPic("none");
			sr.setItemName(m.getMovieNameOriginal().length()>15? (m.getMovieNameOriginal().subSequence(0, 15)+"..."): m.getMovieNameOriginal());
			if(m.getMovieDate()!=null){
				sr.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
			}else{
			}
			sr.setItemRate(mmd.getAverageGrade(m.getMovieId()));
			
			searchResults.add(sr);
		}
		
		int page=0;
		 if(GenericValidator.isInt(request.getParameter("page"))){
			 page = Integer.parseInt(request.getParameter("page"));
			  
		}
	 	Paging p = new Paging();
		p.setObj(searchResults);			
		p.setHaveOtherParameters(false);
		p.setEvery_page_item_num(20);
		if(page <= 0){
			page = 1;
		}
		PageObject po = p.getResult(GlobleConfig.my_domain+"/movie/searchByTag/"+movieTag+".action",page);
		mv.addObject("searchResults", po.getL());
		mv.addObject("pageStr", po.getPageCode());
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("searchName", movieTag);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
}