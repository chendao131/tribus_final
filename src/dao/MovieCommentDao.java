package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.BookComment;
import model.MovieComment;
import model.Music;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import util.DateToString;
import vo.SingleReviewRelatedArticle;
import model.Movie;
@Repository
public class MovieCommentDao {
	@SuppressWarnings("unchecked")
	public List<MovieComment> getCommentByMovieIdAndRating(int movieId, int rating){
		List<MovieComment> mcs = new ArrayList<MovieComment>();
		List<MovieComment> result = new ArrayList<MovieComment>();
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment mc where mc.movie.movieId=:movieId";
			 mcs = session.createQuery(hql).setInteger("movieId", movieId).list();
			 
			 MovieMarkDao mmd = new MovieMarkDao();
			 Iterator<MovieComment> iterator = mcs.iterator();
			 while(iterator.hasNext()){
				 MovieComment mc = iterator.next();
				 if(mmd.getMovieGradeByMovieIdAndUserId(mc.getMovie().getMovieId(), mc.getUser().getUserId())==rating){
					 result.add(mc);
				 }
			 }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return result;		
	}
	
	public int save(MovieComment mc){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(mc);
			session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;		
	}
	@SuppressWarnings("unchecked")
	public MovieComment getMovieCommentByContent(String content){
		MovieComment mc = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment as mc where mc.commentContent=:content";
			List<MovieComment> mcs = session.createQuery(hql).setString("content", content).list();
			mc = mcs.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mc;		
	}
		
	@SuppressWarnings("unchecked")
	public List<MovieComment> getMovieCommentById(Integer movieId){
		MovieComment mc = null;
		List<MovieComment> mcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment as mc where mc.movie.movieId=:movieId";
			 mcs = session.createQuery(hql).setInteger("movieId", movieId).list();
			//mc = mcs.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mcs;		
	}
	
	@SuppressWarnings("unchecked")
	public MovieComment getMovieCommentByCommentId(Integer commentId){
		MovieComment mc = null;
		List<MovieComment> mcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment as mc where mc.commentId=:commentId";
			 mcs = session.createQuery(hql).setInteger("commentId", commentId).list();
			mc = mcs.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mc;		
	}
	
	@SuppressWarnings("unchecked")
	public List<MovieComment> getAllMovieComment(){
		List<MovieComment> mcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment";
			 mcs = session.createQuery(hql).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mcs;		
	}
	
	@SuppressWarnings("unchecked")
	public List<MovieComment> getMovieCommentByUserId(Integer userId,int num){
		MovieComment mc = null;
		List<MovieComment> mcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment where userId=:userId";
			 mcs = session.createQuery(hql).setInteger("userId", userId).list();
			//mc = mcs.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		if(mcs!=null && mcs.size() > num){
			return mcs.subList(0, num);
		}
		return mcs;		
	}
	
	@SuppressWarnings("unchecked")
	public MovieComment getMovieCommentByUserIdAndMovieId(Integer userId, Integer movieId){
		MovieComment mc = null;
		List<MovieComment> mcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment where userId=:userId and movieId=:movieId";
			 mcs = session.createQuery(hql).setInteger("userId", userId).setInteger("movieId",movieId).list();
			mc = mcs.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mc;		
	}
	
	public List<MovieComment> getMovieCommentsByCondition(MovieComment m){
		if(m==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(MovieComment.class);
			if(m.getCommentId() != 0 ){c.add(Restrictions.eq("commentId",m.getCommentId() ));}
			if(m.getCommentDate() != null){c.add(Restrictions.eq("commentDate", m.getCommentDate()));}
										
			List<MovieComment> ms = c.list();  														
			return ms;
			
		} catch ( Exception e ) {
			e.printStackTrace();			
		}				
		return null;			
	}
	
	@SuppressWarnings("unchecked")
	public List<MovieComment> getMovieCommentByMovieId(Integer movieId){
		List<MovieComment> mcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment as mc where mc.movie.movieId=:movieId";
			 mcs = session.createQuery(hql).setInteger("movieId",movieId).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mcs;		
	}
	
	@SuppressWarnings("unchecked")
	public int getCommentNumberByMovieId(Integer movieId){
		int number = 0;
		List<MovieComment> mcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieComment where movieId=:movieId";
			 mcs = session.createQuery(hql).setInteger("movieId",movieId).list();
			 number = mcs.size();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return number;		
	}
	
	public static void main(String args[]){
	MovieCommentDao mcd = new MovieCommentDao();
		System.out.println(mcd.getCommentByMovieIdAndRating(8380, 5).size());
	}
	
}
