package controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.BookComment;
import model.BookCommentDraft;
import model.MovieComment;
import model.Movie;
import model.MovieCommentDraft;
import model.MovieQuickComment;
import model.Music;
import model.Book;
import model.MusicComment;
import model.MusicCommentDraft;
import model.MusicQuickComment;
import model.User;
import model.UserProfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.BookCommentDao;
import dao.BookCommentDraftDao;
import dao.BookDao;
import dao.BookMarkDao;
import dao.MovieCommentDao;
import dao.MovieCommentDraftDao;
import dao.MovieDao;
import dao.MovieMarkDao;
import dao.MovieQuickCommentDao;
import dao.MusicCommentDao;
import dao.MusicCommentDraftDao;
import dao.MusicDao;
import dao.MusicMarkDao;
import dao.MusicQuickCommentDao;
import dao.UserDao;
import dao.UserProfileDao;

import vo.ReviewPage;
import vo.SingleReviewMain;
import vo.SingleReviewQuickComment;
import vo.SingleReviewRelatedArticle;
import util.DateToString;
import util.GetSessionUser;


@Controller
@RequestMapping("review")
public class ReviewController {
	@RequestMapping("movie")
	public ModelAndView getMovieReviewPage(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("movie/Review_page");
		MovieCommentDao mcd = new MovieCommentDao();
		MovieMarkDao mmd = new MovieMarkDao();
		List<MovieComment> mcs = mcd.getAllMovieComment();
		System.out.println(mcs.size());
		Iterator<MovieComment> iterator = mcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();

		while(iterator.hasNext()){
			MovieComment mc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(mc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			reviewPage.setCommentId(mc.getCommentId());
			reviewPage.setCommentTitle(mc.getCommentTitle());
			reviewPage.setItemId(mc.getMovie().getMovieId());
			reviewPage.setItemName(mc.getMovie().getMovieNameOriginal());
			reviewPage.setItemPic(mc.getMovie().getMoviePic());
			int userRate = mmd.getMovieGradeByMovieIdAndUserId(mc.getMovie().getMovieId(), mc.getUser().getUserId());

			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		return mv;
		
	}
	
	@RequestMapping("movie{movieId}")
	public ModelAndView getMovieReviewPageById(HttpServletRequest request, HttpServletResponse response, @PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("movie/Review_page_by_id");
		MovieCommentDao mcd = new MovieCommentDao();
		MovieMarkDao mmd = new MovieMarkDao();
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		Integer totalCommentNumber=0;
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		List<MovieComment> mcs = mcd.getMovieCommentById(movieId);
		Iterator<MovieComment> iterator = mcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		while(iterator.hasNext()){
			MovieComment mc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(mc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			reviewPage.setCommentId(mc.getCommentId());
			reviewPage.setCommentTitle(mc.getCommentTitle());
			reviewPage.setItemId(mc.getMovie().getMovieId());
			reviewPage.setItemName(mc.getMovie().getMovieNameOriginal());
			reviewPage.setItemPic(mc.getMovie().getMoviePic());
			int userRate = mmd.getMovieGradeByMovieIdAndUserId(mc.getMovie().getMovieId(), mc.getUser().getUserId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		
		for(int i=0; i<5 ; i++){
			totalCommentNumber+=r[i];
		}
		
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		mv.addObject("rateNumber", r);
		mv.addObject("itemId", m.getMovieId());
		mv.addObject("itemName", m.getMovieNameOriginal());
		mv.addObject("itemPic", m.getMoviePic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		return mv;
		
	}
	
	@RequestMapping("music")
	public ModelAndView getMusicReviewPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("review/Review_page");
		MusicCommentDao mcd = new MusicCommentDao();
		MusicMarkDao mmd = new MusicMarkDao();
		List<MusicComment> mcs = mcd.getAllMusicComment();
		Iterator<MusicComment> iterator = mcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		while(iterator.hasNext()){
			MusicComment mc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(mc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			reviewPage.setCommentId(mc.getCommentId());
			reviewPage.setCommentTitle(mc.getCommentTitle());
			reviewPage.setItemId(mc.getMusic().getMusicId());
			reviewPage.setItemName(mc.getMusic().getMusicName());
			reviewPage.setItemPic(mc.getMusic().getMusicPic());
			int userRate = mmd.getGradeByMusicIdAndUseId(mc.getMusic().getMusicId(), mc.getUser().getUserId());

			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		mv.addObject("reviewPages", reviewPages);
		return mv;
	}
	
	@RequestMapping("music{musicId}")
	public ModelAndView getMusicReviewPageById(HttpServletRequest request, HttpServletResponse response, @PathVariable("musicId")int musicId){
		ModelAndView mv = new ModelAndView("review/Review_page_by_id");
		MusicCommentDao mcd = new MusicCommentDao();
		MusicMarkDao mmd = new MusicMarkDao();
		MusicDao md = new MusicDao();
		Music m = md.getMusicById(musicId);
		List<MusicComment> mcs = mcd.getAllMusicComment();
		System.out.println(mcs.size());
		Iterator<MusicComment> iterator = mcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		while(iterator.hasNext()){
			MusicComment mc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(mc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			reviewPage.setCommentId(mc.getCommentId());
			reviewPage.setCommentTitle(mc.getCommentTitle());
			reviewPage.setItemId(mc.getMusic().getMusicId());
			reviewPage.setItemName(mc.getMusic().getMusicName());
			reviewPage.setItemPic(mc.getMusic().getMusicPic());
			int userRate = mmd.getGradeByMusicIdAndUseId(mc.getMusic().getMusicId(), mc.getUser().getUserId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		mv.addObject("rateNumber", r);
		mv.addObject("itemName", m.getMusicNum());
		mv.addObject("itemPic", m.getMusicPic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		return mv;
	}
	
	@RequestMapping("book")
	public ModelAndView getBookReviewPage(HttpServletRequest request, HttpServletResponse response){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("book/Review_page");
		BookCommentDao bcd = new BookCommentDao();
		BookMarkDao bmd = new BookMarkDao();
		List<BookComment> bcs = bcd.getAllBookComment();
		Iterator<BookComment> iterator = bcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		while(iterator.hasNext()){
			BookComment bc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(bc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(bc.getCommentDate()));
			reviewPage.setCommentId(bc.getCommentId());
			reviewPage.setCommentTitle(bc.getCommentTitle());
			reviewPage.setItemId(bc.getBook().getBookId());
			reviewPage.setItemName(bc.getBook().getBookName());
			reviewPage.setItemPic(bc.getBook().getBookPic());
			int userRate = bmd.getBookGradeByUserIdandBookId(bc.getUser().getUserId(), bc.getUser().getUserId());

			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		return mv;
	}
	
	@RequestMapping("book{bookId}")
	public ModelAndView getBookReviewPageById(HttpServletRequest request, HttpServletResponse response, @PathVariable("bookId")int bookId){
		ModelAndView mv = new ModelAndView("book/Review_page_by_id");
		BookCommentDao bcd = new BookCommentDao();
		BookMarkDao bmd = new BookMarkDao();
		BookDao bd = new BookDao();
		Book b = bd.getBookById(bookId);
		List<BookComment> bcs = bcd.getAllBookComment();
		Iterator<BookComment> iterator = bcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		while(iterator.hasNext()){
			BookComment bc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(bc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(bc.getCommentDate()));
			reviewPage.setCommentId(bc.getCommentId());
			reviewPage.setCommentTitle(bc.getCommentTitle());
			reviewPage.setItemId(bc.getBook().getBookId());
			reviewPage.setItemName(bc.getBook().getBookName());
			reviewPage.setItemPic(bc.getBook().getBookPic());
			int userRate = bmd.getBookGradeByUserIdandBookId(bc.getUser().getUserId(), bc.getBook().getBookId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		mv.addObject("rateNumber", r);
		mv.addObject("itemName", b.getBookName());
		mv.addObject("itemPic", b.getBookPic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		return mv;
	}
	
	@RequestMapping("movieReview/{commentId}")
	public ModelAndView getMovieSingleReview(HttpServletRequest request, HttpServletResponse response,  @PathVariable("commentId")int commentId){
		User me = GetSessionUser.getUser(request, response);
		int myId = me.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(myId);
		
		MovieCommentDao mcd = new MovieCommentDao();
		MovieComment mc = mcd.getMovieCommentByCommentId(commentId);
		System.out.println(mc.getUser().getUserId());
		int userId = mc.getUser().getUserId();
		int movieId = mc.getMovie().getMovieId();
		ModelAndView mv = new ModelAndView("movie/Single_review");
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
		System.out.println(m.getMovieId());
		singleReviewMain.setItemName(m.getMovieNameOriginal());
		singleReviewMain.setItemPic(m.getMoviePic());
		singleReviewMain.setItemRating(mmd.getAverageGrade(movieId));
		singleReviewMain.setUserId(userId);
		singleReviewMain.setUserName(u.getUserAlias());
		singleReviewMain.setUserPic(u.getUserPic());
		singleReviewMain.setUserRate(mmd.getMovieGradeByMovieIdAndUserId(movieId, userId));
		
		//get related article(all comments)
		List<SingleReviewRelatedArticle> relatedArticles = new ArrayList<SingleReviewRelatedArticle>();
		System.out.println(movieId);
		List<MovieComment> movieComments = mcd.getMovieCommentById(movieId);
		Iterator<MovieComment> iterator = movieComments.iterator();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		mc = null;
		while(iterator.hasNext()){
			mc = iterator.next();
			SingleReviewRelatedArticle article = new SingleReviewRelatedArticle();
			article.setItemName(m.getMovieNameOriginal());
			article.setCommentTitle(mc.getCommentTitle());
			article.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			relatedArticles.add(article);
			
			int userRate = mmd.getMovieGradeByMovieIdAndUserId(movieId, mc.getUser().getUserId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
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
			singleReviewQuickComment.setUserName(mqc.getUser().getUserAlias());
			System.out.println("000:"+singleReviewMain.getItemId());
			System.out.println("000:"+mqc.getCommentContent());
			System.out.println("000:"+mqc.getCommentTitle());
			System.out.println("000:"+DateToString.convertDateToString(mqc.getCommentDate()));
			System.out.println("000:"+mqc.getUser().getUserPic());	
			
			singleReviewQuickComments.add(singleReviewQuickComment);
		}
		
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		mv.addObject("rateNumber", r);
		mv.addObject("relatedArticles", relatedArticles);
		mv.addObject("singleReviewMain", singleReviewMain);
		mv.addObject("singleReviewQuickComments", singleReviewQuickComments);
		return mv;
	}
	
	@RequestMapping("musicReview/{commentId}")
	public ModelAndView getMusicSingleReview(HttpServletRequest request, HttpServletResponse response,  @PathVariable("commentId")int commentId){
		MusicCommentDao mcd = new MusicCommentDao();
		MusicComment mc = mcd.getMusicCommentByCommentId(commentId);
		System.out.println(mc.getUser().getUserId());
		int userId = mc.getUser().getUserId();
		int musicId = mc.getMusic().getMusicId();
		ModelAndView mv = new ModelAndView("review/Single_review");
		MusicMarkDao mmd = new MusicMarkDao();
		MusicDao md = new MusicDao();
		Music m = md.getMusicById(musicId);
		
		UserDao ud = new UserDao();
		User u = ud.getUserById(userId);
		
		
		SingleReviewMain singleReviewMain = new SingleReviewMain();
		singleReviewMain.setCommentContent(mc.getCommentContent());
		singleReviewMain.setCommentTitle(mc.getCommentTitle());
		singleReviewMain.setCommentNumber(mcd.getMusicCommentByMusicId(musicId).size());
		singleReviewMain.setItemBrief(m.getMusicBrief());
		singleReviewMain.setItemId(m.getMusicId());
		singleReviewMain.setItemName(m.getMusicName());
		singleReviewMain.setItemPic(m.getMusicPic());
		singleReviewMain.setItemRating(mmd.getAverageGrade(musicId));
		singleReviewMain.setUserId(userId);
		singleReviewMain.setUserName(u.getUserAlias());
		singleReviewMain.setUserPic(u.getUserPic());
		singleReviewMain.setUserRate(mmd.getGradeByMusicIdAndUseId(musicId, userId));
		
		//get related article(all comments)
		List<SingleReviewRelatedArticle> relatedArticles = new ArrayList<SingleReviewRelatedArticle>();
		List<MusicComment> musicComments = mcd.getMusicCommentByMusicId(musicId);
		Iterator<MusicComment> iterator = musicComments.iterator();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		mc = null;
		while(iterator.hasNext()){
			mc = iterator.next();
			SingleReviewRelatedArticle article = new SingleReviewRelatedArticle();
			article.setItemName(m.getMusicName());
			article.setCommentTitle(mc.getCommentTitle());
			article.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			relatedArticles.add(article);
			
			int userRate = mmd.getGradeByMusicIdAndUseId(musicId,mc.getUser().getUserId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
		}
		
		//get quick comment
		MusicQuickCommentDao mqcd = new MusicQuickCommentDao();
		List<MusicQuickComment> mqcs = mqcd.getQuickCommentByCommentId(commentId);
		System.out.println("000:"+mqcs.size());
		Iterator<MusicQuickComment> iterator1 = mqcs.iterator();
		MusicQuickComment mqc = new MusicQuickComment();
		List<SingleReviewQuickComment> singleReviewQuickComments = new ArrayList<SingleReviewQuickComment>();
		
		while(iterator1.hasNext()){
			mqc = iterator1.next();
			SingleReviewQuickComment singleReviewQuickComment = new SingleReviewQuickComment();
			
			singleReviewQuickComment.setCommentContent(mqc.getCommentContent());
			singleReviewQuickComment.setCommentDate(DateToString.convertDateToString(mqc.getCommentDate()));
			singleReviewQuickComment.setCommentTitle(mqc.getCommentTitle());
			singleReviewQuickComment.setUserPic(mqc.getUser().getUserPic());
			singleReviewQuickComment.setUserName(mqc.getUser().getUserAlias());
			
			singleReviewQuickComments.add(singleReviewQuickComment);
		}

		mv.addObject("rateNumber", r);
		mv.addObject("relatedArticles", relatedArticles);
		mv.addObject("singleReviewMain", singleReviewMain);
		mv.addObject("singleReviewQuickComments", singleReviewQuickComments);
		return mv;
	}
	
	@RequestMapping("bookReview/{commentId}")
	public ModelAndView getBookSingleReview(HttpServletRequest request, HttpServletResponse response,  @PathVariable("commentId")int commentId){
		BookCommentDao bcd = new BookCommentDao();
		BookComment bc = bcd.getBookCommentById(commentId);
		int userId = bc.getUser().getUserId();
		int bookId = bc.getBook().getBookId();
		ModelAndView mv = new ModelAndView("book/Single_review");
		BookMarkDao bmd = new BookMarkDao();
		BookDao bd = new BookDao();
		Book b = bd.getBookById(bookId);
		
		UserDao ud = new UserDao();
		User u = ud.getUserById(userId);
		
		
		SingleReviewMain singleReviewMain = new SingleReviewMain();
		singleReviewMain.setCommentContent(bc.getCommentContent());
		singleReviewMain.setCommentTitle(bc.getCommentTitle());
		singleReviewMain.setCommentNumber(bcd.getBookCommentByBookId(bookId).size());
		singleReviewMain.setItemBrief(b.getBookBrief());
		singleReviewMain.setItemId(b.getBookId());
		singleReviewMain.setItemName(b.getBookName());
		singleReviewMain.setItemPic(b.getBookPic());
		singleReviewMain.setItemRating(bmd.getAverageGrade(bookId));
		singleReviewMain.setUserId(userId);
		singleReviewMain.setUserName(u.getUserAlias());
		singleReviewMain.setUserPic(u.getUserPic());
		singleReviewMain.setUserRate(bmd.getBookGradeByUserIdandBookId(userId, bookId));
		
		//get related article(all comments)
		List<SingleReviewRelatedArticle> relatedArticles = new ArrayList<SingleReviewRelatedArticle>();
		List<BookComment> bookComments = bcd.getBookCommentByBookId(bookId);
		Iterator<BookComment> iterator = bookComments.iterator();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		bc = null;
		while(iterator.hasNext()){
			bc = iterator.next();
			SingleReviewRelatedArticle article = new SingleReviewRelatedArticle();
			article.setItemName(b.getBookName());
			article.setCommentTitle(bc.getCommentTitle());
			article.setCommentDate(DateToString.convertDateToString(bc.getCommentDate()));
			relatedArticles.add(article);
			
			int userRate = bmd.getBookGradeByUserIdandBookId(bc.getUser().getUserId(), bookId);
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
		}
		
		//get quick comment
		MusicQuickCommentDao mqcd = new MusicQuickCommentDao();
		List<MusicQuickComment> mqcs = mqcd.getQuickCommentByCommentId(commentId);
		System.out.println("000:"+mqcs.size());
		Iterator<MusicQuickComment> iterator1 = mqcs.iterator();
		MusicQuickComment mqc = new MusicQuickComment();
		List<SingleReviewQuickComment> singleReviewQuickComments = new ArrayList<SingleReviewQuickComment>();
		
		while(iterator1.hasNext()){
			mqc = iterator1.next();
			SingleReviewQuickComment singleReviewQuickComment = new SingleReviewQuickComment();
			
			singleReviewQuickComment.setCommentContent(mqc.getCommentContent());
			singleReviewQuickComment.setCommentDate(DateToString.convertDateToString(mqc.getCommentDate()));
			singleReviewQuickComment.setCommentTitle(mqc.getCommentTitle());
			singleReviewQuickComment.setUserPic(mqc.getUser().getUserPic());
			singleReviewQuickComment.setUserName(mqc.getUser().getUserAlias());
			
			singleReviewQuickComments.add(singleReviewQuickComment);
		}

		mv.addObject("rateNumber", r);
		mv.addObject("relatedArticles", relatedArticles);
		mv.addObject("singleReviewMain", singleReviewMain);
		mv.addObject("singleReviewQuickComments", singleReviewQuickComments);
		return mv;
	}
		
	@RequestMapping("movieReviewAction/{movieId}")
	public ModelAndView movieReviewAction(HttpServletRequest request, HttpServletResponse response,  @PathVariable("movieId")int movieId){
		ModelAndView mv = new ModelAndView("movie/create_a_comment");
		mv.addObject("movieId", movieId);
		return mv;
	}
		
	@RequestMapping("submitMovieReview/{movieId}")
	public String submitMovieComment(HttpServletRequest request, HttpServletResponse response,  @PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		
		System.out.println("successful");
		//save this comment
		MovieMarkDao mmd = new MovieMarkDao();
		MovieCommentDao mcd = new MovieCommentDao();
		MovieComment mc = new MovieComment();
		MovieDao md = new MovieDao();
		System.out.println(movieId);
		Movie m = md.getMovieById(movieId);
		mc.setMovie(m);
		mc.setCommentTitle(request.getParameter("comment_title"));
		mc.setCommentContent(request.getParameter("comment_content"));
		mc.setUser(u);
		Date now = new Date();
		mc.setCommentDate(now);
		mcd.save(mc);
		
		//return "movie/{"+movieId+"}";
		return "redirect:/movie/"+movieId+".action";
/*		Integer totalCommentNumber=0;
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		List<MovieComment> mcs = mcd.getMovieCommentById(movieId);
		Iterator<MovieComment> iterator = mcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		mc = null;
		while(iterator.hasNext()){
			mc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(mc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			reviewPage.setCommentId(mc.getCommentId());
			reviewPage.setCommentTitle(mc.getCommentTitle());
			reviewPage.setItemId(mc.getMovie().getMovieId());
			reviewPage.setItemName(mc.getMovie().getMovieNameOriginal());
			reviewPage.setItemPic(mc.getMovie().getMoviePic());
			int userRate = mmd.getMovieGradeByMovieIdAndUserId(mc.getMovie().getMovieId(), mc.getUser().getUserId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		
		for(int i=0; i<5 ; i++){
			totalCommentNumber+=r[i];
		}
		
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		mv.addObject("rateNumber", r);
		mv.addObject("itemId", m.getMovieId());
		mv.addObject("itemName", m.getMovieNameOriginal());
		mv.addObject("itemPic", m.getMoviePic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);		

		return mv;*/
	}
	
	@RequestMapping("draftMovieReview/{movieId}")
	public String draftMovieComment(HttpServletRequest request, HttpServletResponse response,  @PathVariable("movieId")int movieId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		//ModelAndView mv = new ModelAndView("movie/Review_page_by_id");
		
		//save this comment
		MovieComment mc = new MovieComment();
		MovieCommentDao mcd = new MovieCommentDao();
		MovieMarkDao mmd = new MovieMarkDao();
		MovieCommentDraftDao mcdd = new MovieCommentDraftDao();
		MovieCommentDraft commentDraft = new MovieCommentDraft();
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		commentDraft.setMovie(m);
		commentDraft.setDraftTitle(request.getParameter("comment_title"));
		commentDraft.setDraftContent(request.getParameter("comment_content"));
		commentDraft.setUser(u);
		Date now = new Date();
		commentDraft.setDraftDate(now);
		mcdd.save(commentDraft);
		
		//return "movie/"+movieId;
		return "redirect:/movie/"+movieId+".action";
/*
		Integer totalCommentNumber=0;
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		List<MovieComment> mcs = mcd.getMovieCommentById(movieId);
		Iterator<MovieComment> iterator = mcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		mc = null;
		while(iterator.hasNext()){
			mc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(mc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			reviewPage.setCommentId(mc.getCommentId());
			reviewPage.setCommentTitle(mc.getCommentTitle());
			reviewPage.setItemId(mc.getMovie().getMovieId());
			reviewPage.setItemName(mc.getMovie().getMovieNameOriginal());
			reviewPage.setItemPic(mc.getMovie().getMoviePic());
			int userRate = mmd.getMovieGradeByMovieIdAndUserId(mc.getMovie().getMovieId(), mc.getUser().getUserId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		
		for(int i=0; i<5 ; i++){
			totalCommentNumber+=r[i];
		}
		
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		mv.addObject("rateNumber", r);
		mv.addObject("itemId", m.getMovieId());
		mv.addObject("itemName", m.getMovieNameOriginal());
		mv.addObject("itemPic", m.getMoviePic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		
		//redirect
		return mv;*/
	}
	
	@RequestMapping("musicReviewAction/{musicId}")
	public ModelAndView musicReviewAction(HttpServletRequest request, HttpServletResponse response,  @PathVariable("musicId")int musicId){
		ModelAndView mv = new ModelAndView("music/create_a_comment");
		mv.addObject("musicId", musicId);
		return mv;
	}
		
	@RequestMapping("submitMusicReview/{musicId}")
	public ModelAndView submitMusicComment(HttpServletRequest request, HttpServletResponse response,  @PathVariable("musicId")int musicId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("review/Review_page_by_id");
		
		System.out.println("successful");
		//save this comment
		MusicMarkDao mmd = new MusicMarkDao();
		MusicCommentDao mcd = new MusicCommentDao();
		MusicComment mc = new MusicComment();
		MusicDao md = new MusicDao();
		System.out.println(musicId);
		Music m = md.getMusicById(musicId);
		mc.setMusic(m);
		mc.setCommentTitle(request.getParameter("comment_title"));
		mc.setCommentContent(request.getParameter("comment_content"));
		mc.setUser(u);
		Date now = new Date();
		mc.setCommentDate(now);
		mcd.save(mc);
		
		m = md.getMusicById(musicId);
		List<MusicComment> mcs = mcd.getAllMusicComment();
		System.out.println(mcs.size());
		Iterator<MusicComment> iterator = mcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		mc = null;
		while(iterator.hasNext()){
			mc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(mc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			reviewPage.setCommentId(mc.getCommentId());
			reviewPage.setCommentTitle(mc.getCommentTitle());
			reviewPage.setItemId(mc.getMusic().getMusicId());
			reviewPage.setItemName(mc.getMusic().getMusicName());
			reviewPage.setItemPic(mc.getMusic().getMusicPic());
			int userRate = mmd.getGradeByMusicIdAndUseId(mc.getMusic().getMusicId(), mc.getUser().getUserId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		mv.addObject("rateNumber", r);
		mv.addObject("itemName", m.getMusicNum());
		mv.addObject("itemPic", m.getMusicPic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		return mv;
	}
	
	@RequestMapping("draftMusicReview/{musicId}")
	public ModelAndView draftMusicComment(HttpServletRequest request, HttpServletResponse response,  @PathVariable("musicId")int musicId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		ModelAndView mv = new ModelAndView("review/Review_page_by_id");
		
		//save this comment
		MusicComment mc = new MusicComment();
		MusicCommentDao mcd = new MusicCommentDao();
		MusicMarkDao mmd = new MusicMarkDao();
		MusicCommentDraftDao mcdd = new MusicCommentDraftDao();
		MusicCommentDraft commentDraft = new MusicCommentDraft();
		MusicDao md = new MusicDao();
		Music m = md.getMusicById(musicId);
		commentDraft.setMusic(m);
		commentDraft.setDraftTitle(request.getParameter("comment_title"));
		commentDraft.setDraftContent(request.getParameter("comment_content"));
		commentDraft.setUser(u);
		Date now = new Date();
		commentDraft.setDraftDate(now);
		mcdd.save(commentDraft);
		
		

		Integer totalCommentNumber=0;
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		List<MusicComment> mcs = mcd.getMusicCommentByMusicId(musicId);
		Iterator<MusicComment> iterator = mcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		mc = null;
		while(iterator.hasNext()){
			mc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(mc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(mc.getCommentDate()));
			reviewPage.setCommentId(mc.getCommentId());
			reviewPage.setCommentTitle(mc.getCommentTitle());
			reviewPage.setItemId(mc.getMusic().getMusicId());
			reviewPage.setItemName(mc.getMusic().getMusicName());
			reviewPage.setItemPic(mc.getMusic().getMusicPic());
			int userRate = mmd.getGradeByMusicIdAndUseId(mc.getMusic().getMusicId(), mc.getUser().getUserId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		
		for(int i=0; i<5 ; i++){
			totalCommentNumber+=r[i];
		}
		mv.addObject("rateNumber", r);
		mv.addObject("itemName", m.getMusicName());
		mv.addObject("itemPic", m.getMusicPic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		
		//redirect
		return mv;
	}
	
	
	/////////////////////////////////////////////////////bbbbbbooooooookkkkkkk
	@RequestMapping("bookReviewAction/{bookId}")
	public ModelAndView bookReviewAction(HttpServletRequest request, HttpServletResponse response,  @PathVariable("bookId")int bookId){

		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);		
		
		ModelAndView mv = new ModelAndView("book/create_a_comment");
		mv.addObject("bookId", bookId);
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		
		return mv;
	}
		
	@RequestMapping("submitBookReview/{bookId}")
	public ModelAndView submitBookComment(HttpServletRequest request, HttpServletResponse response,  @PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("book/Review_page_by_id");
		
		System.out.println("successful");
		//save this comment
		BookMarkDao bmd = new BookMarkDao();
		BookCommentDao bcd = new BookCommentDao();
		BookComment bc = new BookComment();
		BookDao bd = new BookDao();
		System.out.println(bookId);
		Book b = bd.getBookById(bookId);
		bc.setBook(b);
		bc.setCommentTitle(request.getParameter("comment_title"));
		bc.setCommentContent(request.getParameter("comment_content"));
		bc.setUser(u);
		Date now = new Date();
		bc.setCommentDate(now);
		bcd.save(bc);
		
		List<BookComment> bcs = bcd.getAllBookComment();
		Iterator<BookComment> iterator = bcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		bc = null;
		while(iterator.hasNext()){
			bc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(bc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(bc.getCommentDate()));
			reviewPage.setCommentId(bc.getCommentId());
			reviewPage.setCommentTitle(bc.getCommentTitle());
			reviewPage.setItemId(bc.getBook().getBookId());
			reviewPage.setItemName(bc.getBook().getBookName());
			reviewPage.setItemPic(bc.getBook().getBookPic());
			int userRate = bmd.getBookGradeByUserIdandBookId(bc.getUser().getUserId(), bc.getBook().getBookId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		mv.addObject("rateNumber", r);
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		mv.addObject("itemId", b.getBookId());
		mv.addObject("itemName", b.getBookName());
		mv.addObject("itemPic", b.getBookPic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		return mv;
	}
	
	@RequestMapping("draftBookReview/{bookId}")
	public ModelAndView draftBookComment(HttpServletRequest request, HttpServletResponse response,  @PathVariable("bookId")int bookId){
		User u = GetSessionUser.getUser(request, response);
		int userId = u.getUserId();
		
		//get user
		UserProfileDao upd = new UserProfileDao();
		UserProfile up = upd.getUserProfileById(userId);
		
		ModelAndView mv = new ModelAndView("review/Review_page_by_id");
		
		//save this comment
		BookComment bc = new BookComment();
		BookCommentDao bcd = new BookCommentDao();
		BookMarkDao bmd = new BookMarkDao();
		BookCommentDraftDao mcdd = new BookCommentDraftDao();
		BookCommentDraft commentDraft = new BookCommentDraft();
		BookDao bd = new BookDao();
		Book b = bd.getBookById(bookId);
		commentDraft.setBook(b);
		commentDraft.setDraftTitle(request.getParameter("comment_title"));
		commentDraft.setDraftContent(request.getParameter("comment_content"));
		commentDraft.setUser(u);
		Date now = new Date();
		commentDraft.setDraftDate(now);
		mcdd.save(commentDraft);
		
		

		Integer totalCommentNumber=0;
		//List<MovieComment> mcs = mcd.getAllMovieComment();
		Integer[] r = new Integer[5];
		for(int i=0; i<5; i++){
			r[i] = 0;
		}
		List<BookComment> bcs = bcd.getBookCommentByBookId(bookId);
		Iterator<BookComment> iterator = bcs.iterator();
		List<ReviewPage> reviewPages = new ArrayList<ReviewPage>();
		bc = null;
		while(iterator.hasNext()){
			bc = iterator.next();
			ReviewPage reviewPage = new ReviewPage();
			reviewPage.setCommentContent(bc.getCommentContent());
			reviewPage.setCommentDate(DateToString.convertDateToString(bc.getCommentDate()));
			reviewPage.setCommentId(bc.getCommentId());
			reviewPage.setCommentTitle(bc.getCommentTitle());
			reviewPage.setItemId(bc.getBook().getBookId());
			reviewPage.setItemName(bc.getBook().getBookName());
			reviewPage.setItemPic(bc.getBook().getBookPic());
			int userRate = bmd.getBookGradeByUserIdandBookId(bc.getUser().getUserId(), bc.getBook().getBookId());
			switch(userRate){
			case 1: r[0]++;
			case 2: r[1]++;
			case 3: r[2]++;
			case 4: r[3]++;
			case 5: r[4]++;
			}
			reviewPage.setUserRate(userRate);
			reviewPages.add(reviewPage);
		}
		
		for(int i=0; i<5 ; i++){
			totalCommentNumber+=r[i];
		}
		mv.addObject("rateNumber", r);
		mv.addObject("userName", u.getUserAlias());
		//mv.addObject("userCity", up.getProfCity());
		mv.addObject("itemName", b.getBookName());
		mv.addObject("itemPic", b.getBookPic());
		mv.addObject("reviewNumber", reviewPages.size());
		mv.addObject("reviewPages", reviewPages);
		
		//redirect
		return mv;
	}
}
