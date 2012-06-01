package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import model.Book;
import model.BookComment;
import model.Movie;
import model.MovieComment;
import model.Music;
import model.MusicComment;
import util.DateToString;
import vo.SinglePageMain;
import vo.SinglePageReview;

public class SinglePageDao {
	public List<SinglePageReview> getMovieSinglePageReviewById(Integer movieId){
		List<SinglePageReview> singlePageReviews = new ArrayList<SinglePageReview>();
		MovieCommentDao md = new MovieCommentDao();
		MovieMarkDao mmd = new MovieMarkDao();
		List<MovieComment> movieComments = md.getMovieCommentById(movieId);
		Iterator<MovieComment> iterator = movieComments.iterator();
		while(iterator.hasNext()){
			MovieComment mc = iterator.next();
			SinglePageReview singlePageReview = new SinglePageReview();
			singlePageReview.setCommentContent(mc.getCommentContent());
			singlePageReview.setCommentId(mc.getCommentId());
			singlePageReview.setCommentTitle(mc.getCommentTitle());
			singlePageReview.setUserId(mc.getUser().getUserId());
			singlePageReview.setUserName(mc.getUser().getUserAlias());
			singlePageReview.setUserPic(mc.getUser().getUserPic());
			singlePageReview.setUserRate(mmd.getMovieGradeByMovieIdAndUserId(movieId, mc.getUser().getUserId()));
			singlePageReviews.add(singlePageReview);
		}
		return singlePageReviews;
	}
	
	public SinglePageMain getMovieSinglePage(Integer movieId){
		SinglePageMain singlePageMain = new SinglePageMain();
		MovieDao md = new MovieDao();
		MovieMarkDao mmd = new MovieMarkDao();
		Movie m = md.getMovieById(movieId);
		singlePageMain.setItemBrief(m.getMovieBrief());
		singlePageMain.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
		singlePageMain.setItemId(movieId);
		singlePageMain.setItemName(m.getMovieNameOriginal());
		singlePageMain.setItemPic(m.getMoviePic());
		singlePageMain.setItemRate(m.getMovieRating());
		singlePageMain.setItemAveGrade(mmd.getAverageGrade(movieId));
		return singlePageMain;
	}
	
	public List<SinglePageReview> getMusicSinglePageReviewById(Integer musicId){
		List<SinglePageReview> singlePageReviews = new ArrayList<SinglePageReview>();
		MusicCommentDao mcd = new MusicCommentDao();
		MusicMarkDao mmd = new MusicMarkDao();
		List<MusicComment> musicComments = mcd.getMusicCommentByMusicId(musicId);
		Iterator<MusicComment> iterator = musicComments.iterator();
		while(iterator.hasNext()){
			MusicComment mc = iterator.next();
			SinglePageReview singlePageReview = new SinglePageReview();
			singlePageReview.setCommentContent(mc.getCommentContent());
			singlePageReview.setCommentId(mc.getCommentId());
			singlePageReview.setCommentTitle(mc.getCommentTitle());
			singlePageReview.setUserId(mc.getUser().getUserId());
			singlePageReview.setUserName(mc.getUser().getUserAlias());
			singlePageReview.setUserPic(mc.getUser().getUserPic());
			singlePageReview.setUserRate(mmd.getGradeByMusicIdAndUseId(musicId, mc.getUser().getUserId()));
			singlePageReviews.add(singlePageReview);
		}
		return singlePageReviews;
	}
	
	public SinglePageMain getMusicSinglePage(Integer musicId){
		SinglePageMain singlePageMain = new SinglePageMain();
		MusicDao md = new MusicDao();
		MusicMarkDao mmd = new MusicMarkDao();
		Music m = md.getMusicById(musicId);
		singlePageMain.setItemBrief(m.getMusicBrief());
		singlePageMain.setItemDate(m.getMusicPublishDate());
		singlePageMain.setItemId(musicId);
		singlePageMain.setItemName(m.getMusicName());
		singlePageMain.setItemPic(m.getMusicPic());
		singlePageMain.setSingerName(m.getSinger().getSingerName());
		singlePageMain.setItemAveGrade(mmd.getAverageGrade(musicId));
		return singlePageMain;
	}
	
	public List<SinglePageReview> getBookSinglePageReviewById(Integer bookId){
		List<SinglePageReview> singlePageReviews = new ArrayList<SinglePageReview>();
		BookCommentDao bcd = new BookCommentDao();
		BookMarkDao bmd = new BookMarkDao();
		List<BookComment> bookComments = bcd.getBookCommentByBookId(bookId);
		Iterator<BookComment> iterator = bookComments.iterator();
		while(iterator.hasNext()){
			BookComment mc = iterator.next();
			SinglePageReview singlePageReview = new SinglePageReview();
			singlePageReview.setCommentContent(mc.getCommentContent());
			singlePageReview.setCommentId(mc.getCommentId());
			singlePageReview.setCommentTitle(mc.getCommentTitle());
			singlePageReview.setUserId(mc.getUser().getUserId());
			singlePageReview.setUserName(mc.getUser().getUserAlias());
			singlePageReview.setUserPic(mc.getUser().getUserPic());
			singlePageReview.setUserRate(bmd.getBookGradeByUserIdandBookId(mc.getUser().getUserId(), bookId));
			singlePageReviews.add(singlePageReview);
		}
		return singlePageReviews;
	}
	
	public SinglePageMain getBookSinglePage(Integer bookId){
		SinglePageMain singlePageMain = new SinglePageMain();
		BookDao bd = new BookDao();
		BookMarkDao bmd = new BookMarkDao();
		Book b = bd.getBookById(bookId);
		singlePageMain.setItemBrief(b.getBookBrief());
		singlePageMain.setItemDate(b.getBookPublishDate());
		singlePageMain.setItemId(bookId);
		singlePageMain.setItemName(b.getBookName());
		singlePageMain.setItemPic(b.getBookPic());
		singlePageMain.setBookAuthor(b.getBookAuthor());
		singlePageMain.setBookISBN(b.getBookISBN());
		singlePageMain.setItemAveGrade(bmd.getAverageGrade(bookId));
		return singlePageMain;
	}
}
