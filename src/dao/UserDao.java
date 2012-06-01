package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Date;
import java.util.List;

import model.Activity;
import model.Book;
import model.Movie;
import model.User;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDao { 
		
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
			if(u.getUserAlias() !=null){c.add(Restrictions.eq("alias", u.getUserAlias()));}
			if(u.getUserEmail() !=null){c.add(Restrictions.eq("email", u.getUserEmail()));}
			if(u.getUserPw()!=null){c.add(Restrictions.eq("password", u.getUserPw()));}
			if(u.getUserPic() !=null){c.add(Restrictions.eq("pic", u.getUserPic()));}
			if(u.getUserVerifycode() != 0){c.add(Restrictions.eq("verifycode", u.getUserVerifycode()));}
			if(u.getUserId() != 0){c.add(Restrictions.eq("userId", u.getUserId()));}
									
			User user = (User)c.uniqueResult();  														
			return user;
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	
	public List<User> getUserListByCondition(User u){
		if(u==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(User.class);
			if(u.getUserAlias() !=null){c.add(Restrictions.eq("alias", u.getUserAlias()));}
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
			createSQLQuery("select * from user_account where userId = ? ")
			.addEntity(User.class)						
			.setInteger(0, id).uniqueResult();						
												
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
			System.out.println(e.getCause( ).getMessage( ) );
		}
	}
	
	public void delete( User user ) throws Exception {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.delete( user );
			tx.commit( );
		} catch ( Exception e ) {
			tx.rollback( );
			throw e;
		}
	}
	
	

	public int follow(int userId,int friendId){					
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
		    	
			
																						
		} catch ( Exception e ) {			
			System.out.println(e.getMessage());
		}
		return 1;
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
			System.out.println(e.getMessage());
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
	
	public static void main(String[] args){ 
		UserDao ud = new UserDao();
		//User u = ud.getUserById(1);
		User u = new User();//ud.getUserById(1);
		u.setCreateDate(new Date());
		u.setUserAlias("zhangshan");
		u.setUserEmail("zhangshan@163.com");
		//u.setUserId(userId)
		u.setUserPic("ui/oi/op.jpg");
		u.setUserPw("xiao shan");
		u.setUserState(0);
		u.setUserVerifycode(1234567);
		System.out.println(u);
		
		ud.add(u);
	}
	
}