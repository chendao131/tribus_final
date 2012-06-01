package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Date;

import model.User;
import model.UserProfile;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserProfileDao {
	
	
	public int add( UserProfile user ) {
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
	
	public UserProfile getUserByCondition(UserProfile u){
		if(u==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(UserProfile.class);
			if(u.getProfFaceAccount() !=null){c.add(Restrictions.eq("profFaceAccount", u.getProfFaceAccount()));}
			if(u.getProfFacePw() !=null){c.add(Restrictions.eq("profFacePw", u.getProfFacePw()));}
			if(u.getProfHobby()!=null){c.add(Restrictions.eq("profHobby", u.getProfHobby()));}
			if(u.getProfDob() !=null){c.add(Restrictions.eq("profDob", u.getProfDob()));}
			if(u.getProfOccuId() != null){c.add(Restrictions.eq("profOccuId", u.getProfOccuId()));}
			if(u.getProfGender() != 0){c.add(Restrictions.eq("profGender", u.getProfGender()));}
									
			UserProfile user = (UserProfile)c.uniqueResult();  														
			return user;
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	
	public UserProfile getUserProfileById(int id){		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
			UserProfile user = (UserProfile)session.
			createSQLQuery("select * from user_profile where userId = ? ")
			.addEntity(UserProfile.class)						
			.setInteger(0, id).uniqueResult();													
			
			return user;
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	public UserProfile getUserProfileByUserId(int id){		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
			UserProfile user = (UserProfile)session.
			createSQLQuery("select * from user_profile where userId = ? ")
			.addEntity(UserProfile.class)						
			.setInteger(0, id).uniqueResult();													
			
			return user;
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	public int update( UserProfile user ) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.update( user );
			//session.flush();
			tx.commit( );
			return 1;
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();
			return -1;
		}		
	}
	
	public void delete( UserProfile user ) throws Exception {
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
						
	public static void main(String[] args){
		UserProfileDao u = new UserProfileDao();
		//UserProfile uf = u.getUserProfileById(1);
		//UserProfile uf = new UserProfile();
		
		UserDao ud = new UserDao();
		User u1 = ud.getUserById(3);
		
		UserProfile uf = u.getUserProfileById(1);
		
		System.out.println(uf.getProfAge());
		System.out.println(uf.getUser().getUserAlias());
		
		UserProfile upf1 = new UserProfile();
		upf1.setProfAge(22);		
		upf1.setUser(u1);
		
		
		upf1 = u.getUserProfileByUserId(3);
		upf1.setProfDob(new Date());
		u.update(upf1);
		System.out.println(123);
		//u.add(upf1);
		//upf1.setUser(user)
		
		
		
//		UserDao ud = new UserDao();
//		User uuu = ud.getUserById(1);
//		
//		
//		
//		uf.setProfAge(10);
//		uf.setProfDob(new Date());
//		uf.setUser(uuu);
		
		//u.add(uf);
//		UserProfile uf = new UserProfile();
//		User u
//		uf.setUser(user)
//		u.add(user);
		//User uu = uf.getUser();
		//System.out.println(uu);
		//System.out.println(ud.getUserById(1));
	}

}
