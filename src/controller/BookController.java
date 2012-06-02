package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.BookComment;
import model.Movie;
import model.MovieComment;
import model.Music;
import model.Singer;
import model.User;
import model.UserProfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.GetSessionUser;
import util.TribusObjectMapping;

import vo.HomePageFriendRecommend;
import vo.HomePageReview;
import vo.MovieReview;
import vo.SearchResult;
import vo.SinglePageMain;
import vo.SinglePageReview;
import dao.BookCommentDao;
import dao.BookDao;
import dao.BookMarkDao;
import dao.FollowDao;
import dao.HomePageDao;
import dao.MovieCommentDao;
import dao.MovieDao;
import dao.MovieImageDao;
import dao.MovieMarkDao;
import dao.MusicDao;
import dao.MusicMarkDao;
import dao.SingerDao;
import dao.SinglePageDao;
import dao.UserDao;
import dao.UserProfileDao;

@Controller
@RequestMapping("book")
public class BookController {
	
	@RequestMapping("search/books={bookName}")
	public ModelAndView searchMusicByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookName")String bookName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("book/Search_Movie_Result_movie");
		SinglePageDao singlePageDao = new SinglePageDao();
		BookDao bd = new BookDao();
		BookMarkDao bmd = new BookMarkDao();
		List<Book> books = bd.searchBookByName(bookName);
		Iterator<Book> iterator = books.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Book b = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setItemId(b.getBookId());
			sr.setItemPic(b.getBookPic());
			sr.setItemName(b.getBookName());
			if(b.getBookPublishDate()!=null){
				sr.setItemDate(b.getBookPublishDate());
			}else{
			}
			sr.setItemRate(bmd.getAverageGrade(b.getBookId()));
			searchResults.add(sr);
		}
		System.out.println(searchResults.size());
		mv.addObject("searchString", bookName);
		mv.addObject("searchResults", searchResults);
		
		return mv;
	}
	
	@RequestMapping("bookHomePage")
	public ModelAndView displayBookHomePage(HttpServletRequest request, HttpServletResponse response) {
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("book/book_Final");
		HomePageDao homePageDao= new HomePageDao();
		BookDao bd = new BookDao();
		//hot books by tags
		List<Book> booksByTag1 = bd.getBookByTag("United States Army");
		List<Book> booksByTag2 = bd.getBookByTag("Family Memoirs & Histories");

		//home page review
		List<HomePageReview> homePageReviews = new ArrayList<HomePageReview>();
		for(int i=1; i<3; i++){
			HomePageReview homePageReview = homePageDao.getBookHomePageReviewByCommentId(i);
			homePageReviews.add(homePageReview);
		}
		
		//home page friend recommend
		List<HomePageFriendRecommend> homePageFriendRecommends = new ArrayList<HomePageFriendRecommend>();
		HomePageFriendRecommend homePageFriendRecommend1 = homePageDao.getBookHomePageFriendRecommend(1);
		homePageFriendRecommends.add(homePageFriendRecommend1);
		HomePageFriendRecommend homePageFriendRecommend2 = homePageDao.getBookHomePageFriendRecommend(2);
		homePageFriendRecommends.add(homePageFriendRecommend2);
		
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		mv.addObject("homePageReviews", homePageReviews);
		mv.addObject("homePageFriendRecommends", homePageFriendRecommends);
		mv.addObject("booksByTag1", booksByTag1);
		mv.addObject("booksByTag2", booksByTag2);
		
		//guess you like
/*		ActivityDao ad = new ActivityDao();
		List<Activity> activities = new ArrayList<Activity>();
		activities.add(ad.getActivityById(1));
		activities.add(ad.getActivityById(2));
		System.out.println(ad.getActivityById(1).getActivityDuration());
		mv.addObject("guessYouLike", activities);*/
		return mv;		
	}
	
	@RequestMapping("{bookId}")
	public ModelAndView displaySingleBookPage(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("book/singleBookPage");
		SinglePageDao singlePageDao = new SinglePageDao();
		BookDao md = new BookDao();
		
		SinglePageMain singlePageMain = singlePageDao.getBookSinglePage(bookId);
		List<SinglePageReview> singlePageReviews = singlePageDao.getBookSinglePageReviewById(bookId);
		System.out.println(singlePageReviews.size());
		
		mv.addObject("userId", userId);
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		mv.addObject("singlePageReviews", singlePageReviews);
		mv.addObject("singlePageMain", singlePageMain);
		return mv;
	}
	
	@RequestMapping("edit/{bookId}")
	public ModelAndView edit(@PathVariable("bookId")int bookId) {
		ModelAndView mv = new ModelAndView("book/edit");
		BookDao bd = new BookDao();		
		Book b =bd.getBookById(bookId); 						
		mv.addObject("book", b);
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
	public ModelAndView book(HttpServletRequest req, HttpServletResponse res					
	) {
		ModelAndView mv = new ModelAndView();
		BookDao bd = new BookDao();							
		List<Book> l = bd.getBookByCondition(new Book());						
		mv.addObject("books",l);
		mv.setViewName("home");				
		return mv;		
	}
	
}
