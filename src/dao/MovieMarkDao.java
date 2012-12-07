package dao;

import hibernate.TribusHibernateSessionFactory;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import model.MovieMark;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieMarkDao {
	@SuppressWarnings("unchecked")
	public Timestamp getMarkDateByMovieAndUserId(int movieId, int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "select mm.markDate from MovieMark mm where mm.movie.movieId = :movieId and mm.user.userId = :userId";
			List<Timestamp> ts = session.createQuery(hql).
								setInteger("movieId", movieId).setInteger("userId", userId).list();
			return ts.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}	

	public int deleteRate(Integer movieId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from MovieMark mm where mm.movie.movieId = :movieId and mm.user.userId = :userId";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).setInteger("userId", userId).list();
			MovieDao md = new MovieDao();
			UserDao ud = new UserDao();
			if(mms.size()!=0){
				MovieMark mm = mms.get(0);
				mm.setMovieGrade(0);
				session.update(mm);
			}
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int rateByMovieIdAndUserId(Integer movieId, Integer userId , Integer rate){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from MovieMark mm where mm.movie.movieId = :movieId and mm.user.userId = :userId";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).setInteger("userId", userId).list();
			MovieDao md = new MovieDao();
			UserDao ud = new UserDao();
			if(mms.size()!=0){
				MovieMark mm = mms.get(0);
				mm.setMovieGrade(rate);
				session.update(mm);
			}else{
				MovieMark mm = new MovieMark();
				mm.setMovieWatch(0);
				mm.setMovieLike(0);
				mm.setMovie(md.getMovieById(movieId));
				mm.setMovieGrade(rate);
				mm.setUser(ud.getUserById(userId));
				session.save(mm);
			}

			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int markWatchDoneByMovieIdAndUserId(Integer movieId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from MovieMark mm where mm.movie.movieId = :movieId and mm.user.userId = :userId";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).setInteger("userId", userId).list();
			MovieDao md = new MovieDao();
			UserDao ud = new UserDao();
			if(mms.size()!=0){
				MovieMark mm = mms.get(0);
				mm.setMovieWatch(2);
				mm.setMarkDate(new Timestamp(System.currentTimeMillis()));
				session.update(mm);
			}else{
				MovieMark mm = new MovieMark();
				mm.setMovie(md.getMovieById(movieId));
				mm.setMovieWatch(2);
				mm.setUser(ud.getUserById(userId));
				session.save(mm);
			}
			
			session.flush();
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int markWatchWantedByMovieIdAndUserId(Integer movieId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from MovieMark mm where mm.movie.movieId = :movieId and mm.user.userId = :userId";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).setInteger("userId", userId).list();
			MovieDao md = new MovieDao();
			UserDao ud = new UserDao();
			if(mms.size()!=0){
				MovieMark mm = mms.get(0);
				mm.setMovieWatch(1);
				mm.setMarkDate(new Timestamp(System.currentTimeMillis()));
				session.update(mm);
			}else{
				MovieMark mm = new MovieMark();
				mm.setMovie(md.getMovieById(movieId));
				mm.setMovieWatch(1);
				mm.setUser(ud.getUserById(userId));
				session.save(mm);
			}
			session.flush();
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int save(MovieMark mm){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(mm);
			session.flush();
			tx.commit();
			saveSuccess = 1;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}

	@SuppressWarnings("unchecked")
	public List<MovieMark> getMarkByMovieId(int movieId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "from MovieMark mm where mm.movie.movieId = :movieId";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).list();
			return mms;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public MovieMark getMarkByMovieAndUserId(int movieId, int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "from MovieMark mm where mm.movie.movieId = :movieId and mm.user.userId = :userId";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).setInteger("userId", userId).list();
			return mms.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public double getAverageGrade(int movieId){
		Session session = TribusHibernateSessionFactory.currentSession();
		int gradeSum = 0;
		try{
			String hql = "from MovieMark mm where mm.movie.movieId = :movieId";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).list();
			for(int i=0; i< mms.size(); i++){
				gradeSum+= mms.get(i).getMovieGrade();
			}
			return gradeSum/mms.size();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<MovieMark> getFollower(int movieId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "from MovieMark mm where mm.movie.movieId = :movieId and movieLike = 1";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).list();
//			for(int i=0; i< mms.size(); i++){
//				System.out.println(mms.get(i).getUserId());
//			}
			return mms;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public int updateMark(MovieMark mm){
		int result=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();	
		
		try {
			session.update(mm);
			//session.flush();
			tx.commit();
			result = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getLikeMovieByUserId(Integer userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "select movie.movieId from MovieMark mm where mm.user.userId = :userId and movieLike=1";
			List<Integer> movieIDs = session.createQuery(hql).
								setInteger("userId", userId).list();
//			System.out.println("success");
			return movieIDs;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public int getNumberByRating(int movieId, Integer movieGrade){
		int number =0;
		List<MovieMark> mms = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieMark mm where mm.movie.movieId=:movieId and movieGrade=:movieGrade";
			 mms = session.createQuery(hql).setInteger("movieId",movieId).setInteger("movieGrade",movieGrade).list();
			 number = mms.size();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return number;		
	}
	
	@SuppressWarnings("unchecked")
	public Integer getMovieGradeByMovieIdAndUserId(int movieId, int userId){
		Integer grade =0;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "select movieGrade from MovieMark mm where mm.movie.movieId=:movieId and mm.user.userId=:userId";
			 List<Integer> grades = session.createQuery(hql).setInteger("movieId",movieId).setInteger("userId",userId).list();
			 grade = grades.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return grade;
	}
	
	@SuppressWarnings("unchecked")
	public Timestamp getTimestampByMarkId(int markId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "select mm.markDate from MovieMark mm where mm.movieMarkId=:markId";
			List<Timestamp> ts = session.createQuery(hql).
								setInteger("markId", markId).list();
			return ts.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}	
	
	public void deleteMovieMark(MovieMark mm){		
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction( );
			try {
				session.delete( mm );
				tx.commit( );
			} catch ( Exception e ) {
				e.printStackTrace();
				tx.rollback( );			
			}		
	}	public static void main(String args[]){
		MovieMarkDao mmd = new MovieMarkDao();
		//System.out.println(mmd.getNumberByRating(8380, 5));
/*		List<MovieMark> mm = mmd.getMarkByMovieId(1);
		Iterator<MovieMark> it = mm.iterator();
		while(it.hasNext()){
			System.out.println(it.next().getMovieTagId());
		}*/
		//mmd.getLikeMovieByUserId(1);
		//System.out.println(mmd.getMarkByMovieAndUserId(8380, 1).getMovieGrade());
		//System.out.println(mmd.getMarkByMovieAndUserId(8382, 1).getMovieGrade());
/*		MovieMark mm = new MovieMark();
		MovieDao md = new MovieDao();
		UserDao ud = new UserDao();
		mm.setUser(ud.getUserById(1));
		mm.setMovie(md.getMovieById(8380));
		mm.setMovieGrade(4);
		mmd.save(mm);*/
		mmd.rateByMovieIdAndUserId(8510, 1, 5);
	}
}
