package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Book;
import model.BookComment;
import model.Movie;
import model.MovieComment;
import model.BookMark;
import model.MovieMark;
import model.Music;
import model.MusicComment;
import model.MusicMark;
import model.User;
import util.TimeCalculator;
import vo.HomePageFriendRecommend;
import vo.HomePageReview;

public class HomePageDao {
	public HomePageReview getBookHomePageReviewByCommentId(int commentId){
		HomePageReview homePageReview = new HomePageReview();
		BookCommentDao bcd = new BookCommentDao();
		BookMarkDao bmd = new BookMarkDao();
		BookComment bc = bcd.getBookCommentById(commentId);
		
		if(bc==null)
			return null;
		homePageReview.setCommentContent(bc.getCommentContent());
		homePageReview.setCommentId(bc.getCommentId());
		homePageReview.setCommentTitle(bc.getCommentTitle());
		homePageReview.setItemPic(bc.getBook().getBookPic());
		Integer bookId = bc.getBook().getBookId();
		homePageReview.setItemId(bookId);
		homePageReview.setItemName(bc.getBook().getBookName());
		Integer userId = bc.getUser().getUserId();
		homePageReview.setUserId(userId);
		homePageReview.setUserName(bc.getUser().getUserAlias());
		homePageReview.setUserPic(bc.getUser().getUserPic());
		homePageReview.setUserRate(bmd.getBookGradeByUserIdandBookId(userId, bookId));
		return homePageReview;
	}
	
	public List<HomePageFriendRecommend> getBookHomePageFriendRecommend(Integer userId){
		List<HomePageFriendRecommend> homePageRecommends = new ArrayList<HomePageFriendRecommend>();
		HomePageFriendRecommend homePageRecommend = null;
		FollowDao fd = new FollowDao();
		BookDao bd = new BookDao();
		BookMarkDao bmd = new BookMarkDao();
		List<User> allFriends = fd.getAllFriends(userId);  //get friends of this user
		if(allFriends.size()==0)
			return null;
		Iterator<User> iterator = allFriends.iterator();
		while(iterator.hasNext()){  //get marked movie by user
			User friend = iterator.next();
			List<Integer> likeBookIDs = bmd.getLikeBookByUserId(friend.getUserId());
			if(likeBookIDs==null || likeBookIDs.size()==0 || likeBookIDs.get(0) == null)
				continue;
			homePageRecommend = new HomePageFriendRecommend();
			Integer fisrtLikeBookId = likeBookIDs.get(0);
			Book b = bd.getBookById(fisrtLikeBookId);
			homePageRecommend.setFriendId(friend.getUserId());
			homePageRecommend.setFriendName(friend.getUserAlias());
			homePageRecommend.setItemId(b.getBookId());
			homePageRecommend.setItemName(b.getBookName());
			homePageRecommend.setItemPic(b.getBookPic());
			homePageRecommend.setRating(bmd.getBookGradeByUserIdandBookId(friend.getUserId(), b.getBookId()));
			homePageRecommend.setTimeSpan(TimeCalculator.getPastMins(bmd.getMarkDateByBookAndUserId(b.getBookId(), friend.getUserId())));
			homePageRecommends.add(homePageRecommend);
			if(homePageRecommends.size()>=4)
				break;
		}
		return homePageRecommends;
	}
	
	public Movie getMovieHomePageHotMovies(int movieId){
		MovieDao md = new MovieDao();
		Movie m = new Movie();
		md.getMovieById(movieId);
		return m;
	}
	
	public HomePageReview getMovieHomePageReviewByCommentId(int commentId){
		HomePageReview homePageReview = new HomePageReview();
		MovieCommentDao mcd = new MovieCommentDao();
		MovieMarkDao mmd = new MovieMarkDao();
		MovieComment mc = mcd.getMovieCommentByCommentId(commentId);
		if(mc==null)
			return null;
		homePageReview.setCommentContent(mc.getCommentContent());
		homePageReview.setCommentId(mc.getCommentId());
		homePageReview.setCommentTitle(mc.getCommentTitle());
		homePageReview.setItemPic(mc.getMovie().getMoviePic());
		Integer movieId = mc.getMovie().getMovieId();
		homePageReview.setItemId(movieId);
		homePageReview.setItemName(mc.getMovie().getMovieNameOriginal());
		Integer userId = mc.getUser().getUserId();
		homePageReview.setUserId(userId);
		homePageReview.setUserName(mc.getUser().getUserAlias());
		homePageReview.setUserPic(mc.getUser().getUserPic());
		System.out.println(mmd.getMovieGradeByMovieIdAndUserId(movieId, userId));
		homePageReview.setUserRate(mmd.getMovieGradeByMovieIdAndUserId(movieId, userId));
		return homePageReview;
	}
	
	public List<HomePageFriendRecommend> getMovieHomePageFriendRecommend(Integer userId){
		List<HomePageFriendRecommend> homePageRecommends = new ArrayList<HomePageFriendRecommend>();
		HomePageFriendRecommend homePageRecommend = null;
		FollowDao fd = new FollowDao();
		MovieDao md = new MovieDao();
		MovieMarkDao mmd = new MovieMarkDao();
		List<User> allFriends = fd.getFollowEachOther(userId);  //get friends of this user
		if(allFriends.size()==0)
			return null;
		Iterator<User> iterator = allFriends.iterator();
		while(iterator.hasNext()){  //get marked movie by user
			User friend = iterator.next();
			List<Integer> likeMovieIDs = mmd.getLikeMovieByUserId(friend.getUserId());
			if(likeMovieIDs==null || likeMovieIDs.size()==0 || likeMovieIDs.get(0) == null)
				continue;
			homePageRecommend =  new HomePageFriendRecommend();
			Integer fisrtLikeMovieId = likeMovieIDs.get(0);
			Movie m = md.getMovieById(fisrtLikeMovieId);
			homePageRecommend.setFriendId(friend.getUserId());
			homePageRecommend.setFriendName(friend.getUserAlias());
			homePageRecommend.setItemId(m.getMovieId());
			homePageRecommend.setItemName(m.getMovieNameOriginal());
			homePageRecommend.setItemPic(m.getMoviePic());
			homePageRecommend.setRating(mmd.getMovieGradeByMovieIdAndUserId(m.getMovieId(), friend.getUserId()));
			homePageRecommend.setTimeSpan(TimeCalculator.getPastMins(mmd.getMarkDateByMovieAndUserId(m.getMovieId(), friend.getUserId())));
			homePageRecommends.add(homePageRecommend);
			if(homePageRecommends.size()>=4)
				break;
		}
		return homePageRecommends;
	}
	
	public List<HomePageFriendRecommend> getMusicHomePageFriendRecommend(Integer userId){
 		List<HomePageFriendRecommend> homePageRecommends = new ArrayList<HomePageFriendRecommend>();
		HomePageFriendRecommend homePageRecommend = null;
		FollowDao fd = new FollowDao();
		MusicDao md = new MusicDao();
		MusicMarkDao mmd = new MusicMarkDao();
		List<User> allFriends = fd.getAllFriends(userId);  //get friends of this user
		if(allFriends.size()==0)
			return null;
		Iterator<User> iterator = allFriends.iterator();
		while(iterator.hasNext()){  //get marked movie by user
			User friend = iterator.next();
			List<Integer> likeMusicIDs = mmd.getLikeMusicByUseId(friend.getUserId());
		//	if(likeMusicIDs==null || likeMusicIDs.size()==0 || likeMusicIDs.get(0) == null)
			if(likeMusicIDs== null || likeMusicIDs.size()==0 || likeMusicIDs.get(0) == null)
				continue;
			homePageRecommend  = new HomePageFriendRecommend();
			Integer fisrtLikeMusicId = likeMusicIDs.get(0);
			Music m = md.getMusicById(fisrtLikeMusicId);
			homePageRecommend.setFriendId(friend.getUserId());
			homePageRecommend.setFriendName(friend.getUserAlias());
			homePageRecommend.setItemId(m.getMusicId());
			homePageRecommend.setItemName(m.getMusicName());
			homePageRecommend.setItemPic(m.getMusicPic());
			homePageRecommend.setRating(mmd.getGradeByMusicIdAndUseId(m.getMusicId(), friend.getUserId()));
			homePageRecommend.setTimeSpan(TimeCalculator.getPastMins(mmd.getMarkDateByMusicAndUserId(m.getMusicId(), friend.getUserId())));
			homePageRecommends.add(homePageRecommend);
			if(homePageRecommends.size()>=4)
				break;
		}
		return homePageRecommends;
	}
	
	public HomePageReview getMusicHomePageReviewByCommentId(int commentId){
		HomePageReview homePageReview = new HomePageReview();
		MusicCommentDao mcd = new MusicCommentDao();
		MusicMarkDao mmd = new MusicMarkDao();
		MusicComment mc = mcd.getMusicCommentByCommentId(commentId);
		if(mc==null)
			return null;
		homePageReview.setCommentContent(mc.getCommentContent());
		homePageReview.setCommentId(mc.getCommentId());
		homePageReview.setCommentTitle(mc.getCommentTitle());
		homePageReview.setItemPic(mc.getMusic().getMusicPic());
		Integer musicId = mc.getMusic().getMusicId();
		homePageReview.setItemId(musicId);
		homePageReview.setItemName(mc.getMusic().getMusicName());
		Integer userId = mc.getUser().getUserId();
		homePageReview.setUserId(userId);
		homePageReview.setUserName(mc.getUser().getUserAlias());
		homePageReview.setUserPic(mc.getUser().getUserPic());
		homePageReview.setUserRate(mmd.getGradeByMusicIdAndUseId(musicId, userId));
		return homePageReview;
	}
	public static void main(String args[]){
		HomePageDao hpd = new HomePageDao();
		//hpd.getBookHomePageReviewByCommentId(4);
	}
}
