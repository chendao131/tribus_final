package util;

import java.util.*;

import model.Book;

import dao.BookDao;
import dao.MovieDao;
import dao.MusicDao;

import vo.BookMarkVo;
import vo.MovieMarkVo;
import vo.MusicMarkVo;


public class CompareUserLike {
	public int getUserSimilar(List<Book> u1, int uId1 ,List<Book> u2,int uId2){
		
//		BookDao bd = new BookDao();
//		
//		
//		for (Book book : u1) {
//			int id = book.getBookId();
//			if(u2.contains(u1)){
//				
//			}
//		}
		return 0;
	}
	
	public List<BookMarkVo> getUserBookSimilar(int user1,int user2){
		BookDao bd = new BookDao();
		List<BookMarkVo> m1 = bd.getBookAndGradeByUserId(user1);
		List<BookMarkVo> m2 = bd.getBookAndGradeByUserId(user2);
		
		List<BookMarkVo> list = new ArrayList<BookMarkVo>(); 
		
		
		
		int i = 0;
		for (BookMarkVo bookMarkVo : m1) {
			if(m2.contains(bookMarkVo)){
				list.add(bookMarkVo);
				i++;
			}
		}		
		return list;
	}
	
	
	
	public List<MovieMarkVo> getUserMovieSimilar(int user1,int user2){
		MovieDao bd = new MovieDao();
		List<MovieMarkVo> m1 = bd.getMovieAndGradeByUserId(user1);
		List<MovieMarkVo> m2 = bd.getMovieAndGradeByUserId(user2);
		
		List<MovieMarkVo> l = new ArrayList<MovieMarkVo>();
		
		int i = 0;
		for (MovieMarkVo movieMarkVo : m1) {
			if(m2.contains(movieMarkVo)){
				l.add(movieMarkVo);
				i++;
			}
		}		
		return l;
	}
	
	
	public List<MusicMarkVo> getUserMusicSimilar(int user1,int user2){
		MusicDao bd = new MusicDao();
		List<MusicMarkVo> m1 = bd.getMusicAndGradeByUserId(user1);
		List<MusicMarkVo> m2 = bd.getMusicAndGradeByUserId(user2);
		
		List<MusicMarkVo> list = new ArrayList<MusicMarkVo>();
		
		int i = 0;
		for (MusicMarkVo musicMarkVo : m1) {
			if(m2.contains(musicMarkVo)){
				i++;
				list.add(musicMarkVo);
			}
		}		
		return list;
	}
}
