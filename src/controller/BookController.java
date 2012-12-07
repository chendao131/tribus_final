package controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Activity;
import model.Book;
import model.BookComment;
import model.BookTag;
import model.Movie;
import model.MovieComment;
import model.Music;
import model.MusicTag;
import model.MyTribusList;
import model.Singer;
import model.Starring;
import model.TribusClassify;
import model.User;
import model.UserProfile;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import config.GlobleConfig;

import util.DateToString;
import util.GetSessionUser;
import util.PageObject;
import util.Paging;
import util.TribusObjectMapping;

import vo.HomePageFriendRecommend;
import vo.HomePageReview;
import vo.MessageVO;
import vo.MovieReview;
import vo.MyTribusListVO;
import vo.SearchResult;
import vo.SinglePageFriendsRecord;
import vo.SinglePageMain;
import vo.SinglePageReview;
import dao.ActivityDao;
import dao.BookCommentDao;
import dao.BookDao;
import dao.BookMarkDao;
import dao.BookTagDao;
import dao.FollowDao;
import dao.HomePageDao;
import dao.MessageDao;
import dao.MovieCommentDao;
import dao.MovieDao;
import dao.MovieImageDao;
import dao.MovieMarkDao;
import dao.MusicDao;
import dao.MusicMarkDao;
import dao.MusicTagDao;
import dao.SingerDao;
import dao.SinglePageDao;
import dao.StarringDao;
import dao.TribusListClassifyDao;
import dao.TribusListDao;
import dao.UserDao;
import dao.UserProfileDao;

@Controller
@RequestMapping("book")
public class BookController {
	@RequestMapping("saveBookCreation")
	public String saveBookCreation(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		BookDao bd = new BookDao();
		Book b = new Book();
		
		Set<BookTag> tags = new HashSet<BookTag>();
		BookTag bt = new BookTag();
		BookTagDao btd = new BookTagDao();
		bt = btd.getBookTagByName(request.getParameter("bookTag"));
		tags.add(bt);
		b.setTags(tags);
		
		b.setBookName(request.getParameter("bookName"));
		b.setBookAuthor(request.getParameter("bookAuthor"));
		if(!GenericValidator.isBlankOrNull(request.getParameter("bookPage")))
			b.setBookPages(Integer.valueOf(request.getParameter("bookPage")));
		b.setBookPic(request.getParameter("successful_img_path"));
		if(!GenericValidator.isBlankOrNull(request.getParameter("bookDate")))
			b.setBookPublishDate(Date.valueOf(request.getParameter("bookDate")));
		if(!GenericValidator.isBlankOrNull(request.getParameter("bookBrief")))
			b.setBookBrief(request.getParameter("bookBrief"));
		b.setBookISBN(request.getParameter("bookISBN"));
		bd.save(b);
		
		return "redirect:/book/"+b.getBookId()+".action";
	}
	
	@RequestMapping("createBook")
	public ModelAndView createBookAction(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("book/create_movie");
		
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
	
	@RequestMapping("rateBook/{bookId}")
	public void rateBook(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		BookMarkDao bmd = new BookMarkDao();
		bmd.rateByBookIdAndUserId(bookId, userId, Integer.parseInt(request.getParameter("rate")));
		bmd.markWatchDoneByBookIdAndUserId(bookId, userId);
	}

	@RequestMapping("deleteRate/{bookId}")
	public void deleteRate(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		BookMarkDao bmd = new BookMarkDao();
		bmd.deleteRate(bookId, userId);
	}
	
	@RequestMapping("markBook/{bookId}")
	public void markMovie(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		String mark = request.getParameter("mark");
		if(mark.equals("wanted")){
			BookMarkDao bmd = new BookMarkDao();
			bmd.markWatchWantedByBookIdAndUserId(bookId, userId);
		}else if(mark.equals("done")){
			BookMarkDao bmd = new BookMarkDao();
			bmd.markWatchDoneByBookIdAndUserId(bookId, userId);
		}
	}
	
	@RequestMapping("rateMovie/{movieId}")
	public void rateMovie(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		MovieMarkDao mmd = new MovieMarkDao();
		mmd.rateByMovieIdAndUserId(movieId, userId, Integer.parseInt(request.getParameter("rate")));
	}
	
	@RequestMapping("search/{type}")
	public String searchBookAction(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("type")int type){
		
		String redirect = null;
		String name = request.getParameter("single_search_name");
		switch(type){
		case 1: redirect = "redirect:/book/search/Books="+name+".action";break;
		case 2: redirect = "redirect:/book/search/Celebrities="+name+".action";break;
		case 3: redirect = "redirect:/book/search/List="+name+".action";break;
		}
		return redirect;
	}
	
	@RequestMapping("search/Books={bookName}")
	public ModelAndView searchBooksByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookName")String bookName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		ModelAndView mv = new ModelAndView("book/Search_Movie_Result_movie");
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
			sr.setItemName(b.getBookName().length()>15? (b.getBookName().subSequence(0, 15)+"..."): b.getBookName());
			if(b.getBookPublishDate()!=null){
				//sr.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
				sr.setItemDate((DateToString.convertDateToString(b.getBookPublishDate())));
			}else{
			}
			sr.setItemRate(bmd.getAverageGrade(b.getBookId()));
			
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
		PageObject po = p.getResult(GlobleConfig.my_domain+"/book/search/Books="+bookName+".action",page);
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
		mv.addObject("searchName", bookName);
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
		ModelAndView mv = new ModelAndView("book/Search_Movie_Result_celebrities");
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
		PageObject po = p.getResult(GlobleConfig.my_domain+"/book/search/Celebrities="+starName+".action",page);
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
		ModelAndView mv = new ModelAndView("book/Search_Movie_Result_list");
		TribusListDao tld = new TribusListDao();
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
		PageObject po = p.getResult(GlobleConfig.my_domain+"/book/search/List="+listName+".action",page);
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
	
	@RequestMapping("editBook/{bookId}")
	public ModelAndView editMovie(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		ModelAndView mv = new ModelAndView("book/edit_book");
		BookDao bd = new BookDao();
		Book b = bd.getBookById(bookId);
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("book", b);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("saveBookEdition/{bookId}")
	public String saveBookEdition(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		BookDao bd = new BookDao();
		Book b = bd.getBookById(bookId);
		
		Set<BookTag> tags = new HashSet<BookTag>();
		BookTag bt = new BookTag();
		BookTagDao btd = new BookTagDao();
		bt = btd.getBookTagByName(request.getParameter("bookTag"));
		tags.add(bt);
		b.setTags(tags);
		
		b.setBookPic(request.getParameter("successful_img_path"));
		b.setBookName(request.getParameter("bookName"));
		b.setBookAuthor(request.getParameter("bookAuthor"));
		if(!GenericValidator.isBlankOrNull(request.getParameter("bookDate")))
			b.setBookPublishDate(Date.valueOf(request.getParameter("bookDate")));
		b.setBookPages(Integer.parseInt(request.getParameter("bookPage")));
		b.setBookBrief(request.getParameter("bookBrief"));
		b.setBookISBN(request.getParameter("bookISBN"));
		bd.update(b);
		return "redirect:/book/"+bookId+".action";
	}	
	@RequestMapping("search/books={bookName}")
	public ModelAndView searchMusicByName(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookName")String bookName){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
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
				sr.setItemDate(DateToString.convertDateToString(b.getBookPublishDate()));
			}else{
			}
			sr.setItemRate(bmd.getAverageGrade(b.getBookId()));
			searchResults.add(sr);
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
		mv.addObject("searchString", bookName);
		mv.addObject("searchResults", searchResults);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
	@RequestMapping("bookHomePage")
	public ModelAndView displayBookHomePage(HttpServletRequest request, HttpServletResponse response) {
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("book/book_Final");
		HomePageDao homePageDao= new HomePageDao();
		BookDao bd = new BookDao();
		//hot books by tags
		List<Book> booksByTag1 = bd.getBookByTag("american");
		List<Book> booksByTag2 = bd.getBookByTag("Family Memoirs & Histories");

		//home page review
		List<HomePageReview> homePageReviews = new ArrayList<HomePageReview>();
		for(int i=1; i<3; i++){
			HomePageReview homePageReview = homePageDao.getBookHomePageReviewByCommentId(i);
			if(homePageReview!=null)
				homePageReviews.add(homePageReview);
		}
		
		//home page friend recommend
		List<HomePageFriendRecommend> homePageFriendRecommends = homePageDao.getBookHomePageFriendRecommend(u.getUserId());
		//add tribus list
		dao.TribusListDao td = new dao.TribusListDao();
		List<MyTribusList> tribusList = td.getHotBookTribusList();
		
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
		if(!homePageReviews.isEmpty())
			mv.addObject("homePageReviews", homePageReviews);
		if(homePageFriendRecommends!=null)
			mv.addObject("homePageFriendRecommends", homePageFriendRecommends);
		mv.addObject("booksByTag1", booksByTag1);
		mv.addObject("booksByTag2", booksByTag2);
		mv.addObject("tribusList", tribusList);
		
		
		//guess you like
		ActivityDao ad = new ActivityDao();
		List<Activity> activities = ad.getHottestActivity();
		mv.addObject("guessYouLike", activities);
		return mv;		
	}
	
	@RequestMapping("{bookId}")
	public ModelAndView displaySingleBookPage(HttpServletRequest request, HttpServletResponse response,  
			@PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		TribusListClassifyDao tcDao = new TribusListClassifyDao();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("book/singleBookPage");
		SinglePageDao singlePageDao = new SinglePageDao();
		BookDao md = new BookDao();
		
		SinglePageMain singlePageMain = singlePageDao.getBookSinglePage(bookId);
		List<SinglePageReview> singlePageReviews = singlePageDao.getBookSinglePageReviewById(bookId);
		List<SinglePageFriendsRecord> friendRecords = singlePageDao.getBookSinglePageFriendsRecord(userId, bookId);
		
		BookMarkDao bmd = new BookMarkDao();
		Integer markWatch = 0;
		if(bmd.getMarkByBookAndUserId(bookId, userId)!=null)
			markWatch = bmd.getMarkByBookAndUserId(bookId, userId).getBookRead();
		mv.addObject("markWatch", markWatch);
		
		dao.TribusListDao td = new dao.TribusListDao();
		List<MyTribusList> tribusList = td.getHotBookTribusList();
		
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
		
		mv.addObject("myRate", bmd.getBookGradeByUserIdandBookId(userId, bookId));
		return mv;
	}
	
	@RequestMapping("edit/{bookId}")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,
					@PathVariable("bookId")int bookId) {
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);

		ModelAndView mv = new ModelAndView("book/edit");
		BookDao bd = new BookDao();		
		Book b =bd.getBookById(bookId); 						
		mv.addObject("book", b);

		dao.TribusListDao td = new dao.TribusListDao();
		List<MyTribusList> tribusList = td.getHotBookTribusList();
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		//mv.addObject("stars", m.getStars());
		return mv;		
	}
	
	@RequestMapping("editAction")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res) {
		User u = GetSessionUser.getUser(req, res);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
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
		dao.TribusListDao td = new dao.TribusListDao();
		List<MyTribusList> tribusList = td.getHotBookTribusList();
		
		MessageDao mgd = new MessageDao();
		List<MessageVO> unreadInboxmails = new ArrayList<MessageVO>();
		try {
			unreadInboxmails = mgd.getUserInboxMessageAllUnread(userId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("unreadMail", unreadInboxmails !=null ? unreadInboxmails.size(): 0);
		
		mv.setViewName("forward:/book/"+old.getBookId());
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
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
	
	@RequestMapping("searchByTag/{bookTag}")
	public ModelAndView searchBookByTag(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("bookTag")String bookTag){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("book/Search_Movie_Result_movie");
		BookDao bd = new BookDao();
		BookMarkDao bmd = new BookMarkDao();
		List<Book> books = bd.searchBookByTag(bookTag);
		Iterator<Book> iterator = books.iterator();
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		while(iterator.hasNext()){
			Book b = iterator.next();
			SearchResult sr = new SearchResult();
			sr.setItemId(b.getBookId());
			sr.setItemPic(b.getBookPic());
			sr.setItemName(b.getBookName().length()>15? (b.getBookName().subSequence(0, 15)+"..."): b.getBookName());
			if(b.getBookPublishDate()!=null){
				//sr.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
				sr.setItemDate(DateToString.convertDateToString(b.getBookPublishDate()));
			}else{
			}
			sr.setItemRate(bmd.getAverageGrade(b.getBookId()));
			
			searchResults.add(sr);
		}
		
		dao.TribusListDao td = new dao.TribusListDao();
		List<MyTribusList> tribusList = td.getHotBookTribusList();
		
		
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
		PageObject po = p.getResult(GlobleConfig.my_domain+"/book/searchByTag/"+bookTag+".action",page);
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
		mv.addObject("searchName", bookTag);
		mv.addObject("userName", u.getUserAlias());
		mv.addObject("userCity", up.getProfCity());
		return mv;
	}
	
}
