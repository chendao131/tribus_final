package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import model.Book;
import model.BookComment;
import model.Movie;
import model.MovieComment;
import model.Music;
import model.MusicComment;
import model.Starring;
import model.User;
import util.DateToString;
import util.TimeCalculator;
import vo.SinglePageFriendsRecord;
import vo.SinglePageMain;
import vo.SinglePageReview;

public class SinglePageDao {
	public List<String> getMovieSinglePageGallery(int movieId){
		MovieImageDao mid = new MovieImageDao();
		return mid.getImagesByMovieId(movieId);
	}
	public List<SinglePageFriendsRecord> getBookSinglePageFriendsRecord(Integer userId, Integer bookId){
		UserDao ud = new UserDao();
		BookMarkDao bmd = new BookMarkDao();
		FollowDao fd = new FollowDao();
		fd.getAllFriends(userId);
		Iterator<User> iterator = fd.getAllFriends(userId).iterator();
		List<SinglePageFriendsRecord> friendRecords = new ArrayList<SinglePageFriendsRecord>();
		while(iterator.hasNext()){
			User friend = iterator.next();
			int friendId = friend.getUserId();
			if(bmd.getMarkByBookAndUserId(bookId, friendId)!=null){
				SinglePageFriendsRecord thisRecord = new SinglePageFriendsRecord();
				thisRecord.setFriendPic(friend.getUserPic());
				thisRecord.setFriendName(friend.getUserAlias());
				thisRecord.setTimeSpan(TimeCalculator.getPastMins(bmd.getMarkDateByBookAndUserId(bookId, friendId)));
				friendRecords.add(thisRecord);
			}
			if(friendRecords.size()>=3)
				break;
		}
		return friendRecords;
	}
	
	public List<SinglePageFriendsRecord> getMusicSinglePageFriendsRecord(Integer userId, Integer musicId){
		UserDao ud = new UserDao();
		MusicMarkDao mmd = new MusicMarkDao();
		FollowDao fd = new FollowDao();
		fd.getAllFriends(userId);
		Iterator<User> iterator = fd.getAllFriends(userId).iterator();
		List<SinglePageFriendsRecord> friendRecords = new ArrayList<SinglePageFriendsRecord>();
		while(iterator.hasNext()){
			User friend = iterator.next();
			int friendId = friend.getUserId();
			if(mmd.getMarkByMusicAndUserId(musicId, friendId)!=null){
				SinglePageFriendsRecord thisRecord = new SinglePageFriendsRecord();
				thisRecord.setFriendPic(friend.getUserPic());
				thisRecord.setFriendName(friend.getUserAlias());
				thisRecord.setTimeSpan(TimeCalculator.getPastMins(mmd.getMarkDateByMusicAndUserId(musicId, friendId)));
				friendRecords.add(thisRecord);
			}
			if(friendRecords.size()>=3)
				break;
		}
		return friendRecords;
	}
	
	public List<SinglePageFriendsRecord> getMovieSinglePageFriendsRecord(Integer userId, Integer movieId){
		UserDao ud = new UserDao();
		MovieMarkDao mmd = new MovieMarkDao();
		FollowDao fd = new FollowDao();
		fd.getAllFriends(userId);
		Iterator<User> iterator = fd.getAllFriends(userId).iterator();
		List<SinglePageFriendsRecord> friendRecords = new ArrayList<SinglePageFriendsRecord>();
		while(iterator.hasNext()){
			User friend = iterator.next();
			int friendId = friend.getUserId();
			if(mmd.getMarkByMovieAndUserId(movieId, friendId)!=null){
				SinglePageFriendsRecord thisRecord = new SinglePageFriendsRecord();
				thisRecord.setFriendPic(friend.getUserPic());
				thisRecord.setFriendName(friend.getUserAlias());
				thisRecord.setTimeSpan(TimeCalculator.getPastMins(mmd.getMarkDateByMovieAndUserId(movieId, friendId)));
				friendRecords.add(thisRecord);
			}
			if(friendRecords.size()>=3)
				break;
		}
		return friendRecords;
	}
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
		if(m.getMovieBrief()!=null)
			singlePageMain.setItemBrief_short(m.getMovieBrief().length()<50 ? m.getMovieBrief():m.getMovieBrief().substring(0, 49)+"...");
		if(m.getMovieDate()!=null)
			singlePageMain.setItemDate(DateToString.convertDateToString(m.getMovieDate()));
		singlePageMain.setItemId(movieId);
		singlePageMain.setItemName(m.getMovieNameOriginal());
		singlePageMain.setItemPic(m.getMoviePic());
		singlePageMain.setItemRate(m.getMovieRating());
		singlePageMain.setItemAveGrade(mmd.getAverageGrade(movieId));
		singlePageMain.setItemRegion(m.getMovieRegion());
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
		singlePageMain.setItemDirectors(directors);
		singlePageMain.setItemActors(actors);
		
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
		if(m.getMusicBrief()!=null)
			singlePageMain.setItemBrief_short(m.getMusicBrief().length()<50 ? m.getMusicBrief():m.getMusicBrief().substring(0, 49)+"...");
		if(m.getMusicPublishDate()!=null)
			singlePageMain.setItemDate(DateToString.convertDateToString(m.getMusicPublishDate()));
		singlePageMain.setItemId(musicId);
		singlePageMain.setItemName(m.getMusicName());
		singlePageMain.setItemPic(m.getMusicPic());
		if(m.getSinger()!=null)
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
		if(b.getBookBrief()!=null)
			singlePageMain.setItemBrief(b.getBookBrief());
		if(b.getBookBrief()!=null)
			singlePageMain.setItemBrief_short(b.getBookBrief().length()<50 ? b.getBookBrief():b.getBookBrief().substring(0, 49)+"...");
		//singlePageMain.setItemBrief(b.getBookBrief()==null? "":b.getBookBrief());
		if(b.getBookPublishDate()!=null)
			singlePageMain.setItemDate(DateToString.convertDateToString(b.getBookPublishDate()));
		singlePageMain.setItemId(bookId);
		singlePageMain.setItemName(b.getBookName());
		singlePageMain.setItemPic(b.getBookPic());
		singlePageMain.setBookAuthor(b.getBookAuthor());
		singlePageMain.setBookISBN(b.getBookISBN());
		singlePageMain.setBookPages(b.getBookPages());
		singlePageMain.setItemAveGrade(bmd.getAverageGrade(bookId));
		return singlePageMain;
	}
/*	public static void main(String args[]){
		SinglePageDao spd = new SinglePageDao();
		spd.getMovieSinglePage(10833);
	}*/
}
