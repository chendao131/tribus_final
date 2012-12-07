package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Activity;
import model.Book;
import model.Movie;
import model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import vo.MusicMarkVo;

public class UserDao { 

	public void cuttingBookPic(){
		String sql = "select bookId,bookPic from book";
		String u_sql = "update book set bookPicBig = ?, bookPicMiddle = ?, " +
				"bookPicSmall = ? where bookId = ?";
		
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			
			List l = session.createSQLQuery(sql).list();	
			if(l!=null){
				Iterator a = l.iterator();
				while(a.hasNext()){
					Object[] o = (Object[])a.next();
					System.out.println(o[0].toString());
					System.out.println(o[1].toString());
					
					String small = o[1].toString().replaceAll(".jpg", "_64_64.jpg");
					String mid = o[1].toString().replaceAll(".jpg", "_103_103.jpg");
					//session.createSQLQuery(u_sql).setString(0, o[1]).;
					session.createSQLQuery(u_sql).setString(0, o[1].toString()).setString(1, mid)
					.setString(2, small).setInteger(3, Integer.parseInt(o[0].toString())).executeUpdate();
					
				}
			}
			tx.commit();
			
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback();
		}				
		
	}
	
	public void cuttingMusicPic(){
		String sql = "select musicId,musicPic from music ";
		String u_sql = "update music set musicPicBig = ?, musicPicMiddle = ?, " +
				"musicPicSmall = ? where musicId = ?";
		
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			
			List l = session.createSQLQuery(sql).list();	
			if(l!=null){
				Iterator a = l.iterator();
				while(a.hasNext()){
					Object[] o = (Object[])a.next();
					System.out.println(o[0].toString());
					System.out.println(o[1].toString());
					
					String small = o[1].toString().replaceAll(".jpg", "_64_64.jpg");
					String mid = o[1].toString().replaceAll(".jpg", "_103_103.jpg");
					//session.createSQLQuery(u_sql).setString(0, o[1]).;
					session.createSQLQuery(u_sql).setString(0, o[1].toString()).setString(1, mid)
					.setString(2, small).setInteger(3, Integer.parseInt(o[0].toString())).executeUpdate();
					
				}
			}
			tx.commit();
			
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback();
		}				
		
	}
	
	
	public void cuttingMoviePic(){
		String sql = "select movieId,moviePic from movie ";
		String u_sql = "update movie set moviePicBig = ?, moviePicMiddle = ?, moviePicSmall = ? where movieId = ?";
		
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			
			List l = session.createSQLQuery(sql).list();	
			if(l!=null){
				Iterator a = l.iterator();
				while(a.hasNext()){
					Object[] o = (Object[])a.next();
					System.out.println(o[0].toString());
					System.out.println(o[1].toString());
					
					String small = o[1].toString().replaceAll(".jpg", "_64_64.jpg");
					String mid = o[1].toString().replaceAll(".jpg", "_103_103.jpg");
					//session.createSQLQuery(u_sql).setString(0, o[1]).;
					session.createSQLQuery(u_sql).setString(0, o[1].toString()).setString(1, mid)
					.setString(2, small).setInteger(3, Integer.parseInt(o[0].toString())).executeUpdate();
					
				}
			}
			tx.commit();
			
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback();
		}				
		
	}
	
	public User haveRegistered(String email, String pwd){
		
		User u = null;
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			
			u = (User)session.createQuery("from User where userEmail = :email and userPw = :pwd").setString("email", email).setString("pwd", pwd).uniqueResult();																	
			return u;
			
		} catch ( Exception e ) {
			e.printStackTrace();			
		}				
		return u;
	}
	
	public int add( User user ) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( user );
	//		session.flush();
			tx.commit( );			
			return 1;		
			
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback( );			
		}
		return -1;
	}
	
	public User getUserByCondition(User u){
		if(u==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(User.class);
			if(u.getUserAlias() !=null){c.add(Restrictions.eq("userAlias", u.getUserAlias()));}
			if(u.getUserEmail() !=null){c.add(Restrictions.eq("userEmail", u.getUserEmail()));}
			if(u.getUserPw()!=null){c.add(Restrictions.eq("userPw", u.getUserPw()));}
			if(u.getUserPic() !=null){c.add(Restrictions.eq("userPic", u.getUserPic()));}
			if(u.getUserVerifycode() != 0){c.add(Restrictions.eq("verifycode", u.getUserVerifycode()));}
			if(u.getUserId() != 0){c.add(Restrictions.eq("userId", u.getUserId()));}
									
			User user = (User)c.uniqueResult();  														
			return user;
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	
//	public List<User> getUserListByName(){
//		String sql = "select * from user_account where userAlias like %?% ";
//		
//		return null;
//	}
	
	public List<User> getUserListByCondition(User u){
		if(u==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(User.class);
			if(u.getUserAlias() !=null){c.add(Restrictions.like("alias", u.getUserAlias()));}
			if(u.getUserEmail() !=null){c.add(Restrictions.eq("email", u.getUserEmail()));}
			if(u.getUserPw()!=null){c.add(Restrictions.eq("password", u.getUserPw()));}
			if(u.getUserPic() !=null){c.add(Restrictions.eq("pic", u.getUserPic()));}
			if(u.getUserVerifycode() != 0){c.add(Restrictions.eq("verifycode", u.getUserVerifycode()));}
			if(u.getUserId() != 0){c.add(Restrictions.eq("userId", u.getUserId()));}
												  													
			return c.list();
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserById(int id){
		User u = null;
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
			u = (User)session.
			createQuery("from User where userId = ? ").setInteger(0, id).uniqueResult();																		
			
			session.clear();
			
			return u;
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return u;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getActivityById(int id){
		List<Activity> u = null;
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
			u = session.
			createSQLQuery("select a.* from user_account u, activity a where u.userId = a.userId and a.userId = ? ")
			.addEntity(Activity.class)						
			.setInteger(0, id).list();																						
			return u;
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return u;
	}
	
	public void update( User user ) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.update( user );
			tx.commit( );
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();
		}
	}
	
	public void delete( User user )  {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.delete( user );
			tx.commit( );
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback( );			
		}
	}
	
			
	@SuppressWarnings("unchecked")
	public User isEmailUsed(String email){		
		User u = null;			
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			u = (User)session.createSQLQuery(
					"select user_account.userId " +
					"from " +
					"user_account " +					
					"where " +
					"user_account.userEmail = ? ")
			.addEntity(User.class).setString(0, email).uniqueResult();
								
		} catch ( Exception e ) {			
			e.printStackTrace();
		}
		return u;
	}
	
	
	public List<Movie> getMoviesByUserFinished(int id){
		String sql = "select m.* from moive m, user_account u,movie_mark mm where mm.userId = " +
				" u.userId and mm.movieId = m.movieId where u.userId = ?";				
		Session session = TribusHibernateSessionFactory.currentSession();
		
		try {
			List<Movie> l = session.createSQLQuery(sql).addEntity(Movie.class).setInteger(0, id).list();
			return l;
		} catch ( Exception e ) {			
			e.printStackTrace();
		}				
		return null;
	}
	
	public List<Book> getBooksByUserFinished(int userId){
		return null;
	}
	
	public List<Book> getBooksByUserWanted(int userId){
		return null;
	}
	
	
	public Map<Long,String> getUserAndId(){
		Map<Long,String> m = new HashMap<Long,String>();		
		List<User> l = getUserListByCondition(new User());
		for (User user : l) {
			m.put(new Long(user.getUserId()), user.getUserAlias());
		}
	    return m;		
	}
	
	
	
	public static void main(String[] args){ 
		UserDao ud = new UserDao();	
//		ud.cuttingMoviePic();
//		ud.cuttingMusicPic();
//		ud.cuttingBookPic();
		User u = new User();
		//1348227449@facebook.com
		u.setUserEmail("1348227449@facebook.com");
		User u1 = ud.getUserByCondition(u);
		System.out.println(u1);
	}
	
}