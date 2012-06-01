package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;

import model.Movie;
import model.Music;
import model.MusicComment;
import model.Singer;
import model.Starring;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.DateToString;
import util.GetSessionUser;
import util.TribusObjectMapping;
import vo.FriendsRecommend;
import vo.FriendsRecommendMovie;
import vo.HomePageFriendRecommend;
import vo.HomePageReview;
import vo.MovieReview;
import vo.MusicHomeReview;
import vo.RecentHotMusic;
import vo.SearchResult;
import vo.SinglePageMain;
import vo.SinglePageReview;
import dao.BookDao;
import dao.HomePageDao;
import dao.MovieDao;
import dao.MovieMarkDao;
import dao.MusicCommentDao;
import dao.MusicDao;
import dao.MusicMarkDao;
import dao.SingerDao;
import dao.SinglePageDao;
import dao.StarringDao;

@Controller
@RequestMapping("music")
public class MusicController {
	@RequestMapping("search/Celebrity={starName}")
	public ModelAndView searchCelebrityByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("starName")String starName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("music/Search_Movie_Result_celebrities");
		SingerDao sd = new SingerDao();
		List<Singer> singers = sd.searchSingerByName(starName);
		Iterator<Singer> iterator = singers.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Singer s = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setStarId(s.getSingerId());
			sr.setStarPic(s.getSingerPic());
			sr.setStarName(s.getSingerName());
			sr.setSearchString(starName);
			searchResults.add(sr);
		}
		System.out.println(searchResults.size());
		mv.addObject("searchString", starName);
		mv.addObject("searchResults", searchResults);
		
		return mv;
	}
	
	@RequestMapping("musicHomePage")
	public ModelAndView displayMusicHomePage(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("music/Music_Final");
		HomePageDao homePageDao = new HomePageDao();
		MusicDao md = new MusicDao();
		//home page recent hot movie
		List<Music> recentHotMusics_1 = new ArrayList<Music>();
		for(int i = 3; i< 9; i++){
			Music hotMusic = md.getMusicById(i);
			recentHotMusics_1.add(hotMusic);
		}
		List<Music> recentHotMusics_2 = new ArrayList<Music>();
		for(int i = 9; i< 15; i++){
			Music hotMusic = md.getMusicById(i);
			recentHotMusics_2.add(hotMusic);
		}
		
		//home page review
		List<HomePageReview> homePageReviews = new ArrayList<HomePageReview>();
		for(int i=1; i<3; i++){
			HomePageReview homePageReview = homePageDao.getMusicHomePageReviewByCommentId(i);
			homePageReviews.add(homePageReview);
		}
		
		//home page friend recommend
		List<HomePageFriendRecommend> homePageFriendRecommends = new ArrayList<HomePageFriendRecommend>();
		HomePageFriendRecommend homePageFriendRecommend1 = homePageDao.getMusicHomePageFriendRecommend(2);
		homePageFriendRecommends.add(homePageFriendRecommend1);
		HomePageFriendRecommend homePageFriendRecommend2 = homePageDao.getMusicHomePageFriendRecommend(3);
		homePageFriendRecommends.add(homePageFriendRecommend2);
		
		mv.addObject("userId", userId);
		System.out.println(recentHotMusics_1.size());
		System.out.println(recentHotMusics_2.size());
		mv.addObject("recentHotMusics_1", recentHotMusics_1);
		mv.addObject("recentHotMusics_2", recentHotMusics_2);
		mv.addObject("homePageReviews",homePageReviews);
		mv.addObject("homePageFriendRecommends", homePageFriendRecommends);
		return mv;
	}
	
	@RequestMapping("{musicId}")
	public ModelAndView displaySingleMusic(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicId")int musicId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("music/singleMusicPage");
		SinglePageDao singlePageDao = new SinglePageDao();
		MusicDao md = new MusicDao();
		
		SinglePageMain singlePageMain = singlePageDao.getMusicSinglePage(musicId);
		List<SinglePageReview> singlePageReviews = singlePageDao.getMusicSinglePageReviewById(musicId);
		System.out.println(singlePageReviews.size());
		mv.addObject("userId", userId);
		mv.addObject("singlePageReviews", singlePageReviews);
		mv.addObject("singlePageMain", singlePageMain);
		return mv;
	}
	
	@RequestMapping("search/Musics={musicName}")
	public ModelAndView searchMusicByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("musicName")String musicName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("music/Search_Movie_Result_movie");
		SinglePageDao singlePageDao = new SinglePageDao();
		MusicDao md = new MusicDao();
		MusicMarkDao mmd = new MusicMarkDao();
		List<Music> musics = md.searchMusicByName(musicName);
		Iterator<Music> iterator = musics.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Music m = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setItemId(m.getMusicId());
			sr.setItemPic(m.getMusicPic());
			sr.setItemName(m.getMusicName());
			if(m.getRecordDate()!=null){
				sr.setItemDate(m.getRecordDate());
			}else{
			}
			sr.setItemRate(mmd.getAverageGrade(m.getMusicId()));
			
			searchResults.add(sr);
		}
		System.out.println(searchResults.size());
		mv.addObject("searchString", musicName);
		mv.addObject("searchResults", searchResults);
		
		return mv;
	}
	
	@RequestMapping("edit/{musicId}")
	public ModelAndView edit(@PathVariable("musicId")int bookId) {
		ModelAndView mv = new ModelAndView("book/edit");
		MusicDao md = new MusicDao();
		
		//Book b =bd.getBookById(bookId); 						
		//mv.addObject("book", b);
		//mv.addObject("stars", m.getStars());
		return mv;		
	}
	
	
	@RequestMapping("editAction")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("book/edit");
		BookDao bd = new BookDao();								 					
		Book b = (Book)TribusObjectMapping.convert("model.Book", req, res);
		Book old = bd.getBookById(b.getBookId());
		
		TribusObjectMapping.objectCopy(old, b, new String[]{"bookId"});
		
		int code = bd.update(old);
		if(code == 1){
			mv.addObject("message", "success");
		}else{
			mv.addObject("message", "fail");
		}		
//		mv.setViewName("redirect:book/"+b.getBookId()+"/"+code);
		//return new AnotherController().handleRequest(request, response);
		mv.setViewName("forward:/book/"+old.getBookId());
		return mv;		
	}

	
	@RequestMapping("get/{id}")
	public ModelAndView book(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("id")int id					
	) {
		ModelAndView mv = new ModelAndView();
		BookDao bd = new BookDao();							
		Book old = bd.getBookById(id);						
		mv.addObject("book",old);
		mv.setViewName("book");		
		//mv.addObject("stars", m.getStars());
		return mv;		
	}
	
	
	@RequestMapping("home")
	public ModelAndView music(HttpServletRequest req, HttpServletResponse res					
	) {
		String id = req.getParameter("id");
		ModelAndView mv = new ModelAndView();
		MusicDao bd = new MusicDao();							
		List<Music> l = bd.getMusicByCondition(new Music());						
		mv.addObject("musics",l);
		
		MusicCommentDao md = new MusicCommentDao();
		List<MusicComment> l_c = md.getMusicCommentsByCondition(new MusicComment());
		
		StarringDao sd = new StarringDao();
		Starring s = sd.getStarringById(Integer.parseInt(id));
		
		mv.setViewName("home");
		mv.addObject("musics", l);
		mv.addObject("musicComments", l_c);
		mv.addObject("star", s);
		
		return mv;		
	}
}
