package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Iterator;
import java.util.List;

import model.MovieMark;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieMarkDao {
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
			String hql = "from MovieMark where movieId = :movieId";
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
			String hql = "from MovieMark where movieId = :movieId and userId = :userId";
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
			String hql = "from MovieMark where movieId = :movieId";
			List<MovieMark> mms = session.createQuery(hql).
								setInteger("movieId", movieId).list();
			for(int i=0; i< mms.size(); i++){
				gradeSum+= mms.get(i).getMovieGrade();
			}
			return gradeSum/mms.size();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public List<MovieMark> getFollower(int movieId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "from MovieMark where movieId = :movieId and movieLike = 1";
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
			String hql = "select movieId from MovieMark where userId = :userId and movieLike=1";
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
			String hql = "from MovieMark where movieId=:movieId and movieGrade=:movieGrade";
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
			String hql = "select movieGrade from MovieMark where movieId=:movieId and userId=:userId";
			 List<Integer> grades = session.createQuery(hql).setInteger("movieId",movieId).setInteger("userId",userId).list();
			 grade = grades.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return grade;		
	}
	
	public static void main(String args[]){
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
	}
}
