package dao;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.Follow;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FollowDao {
	
	
	public int add(Follow follow){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( follow );
	//		session.flush();
			tx.commit( );			
			return 1;		
			
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback( );			
		}
		return -1;
	}
	
	
	public List<User> getAllFriends(int userId){		
		List<User> followees = new ArrayList<User>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			followees = session.createSQLQuery(
					"select user_account.* " +
					"from " +
					"user_account, follow " +					
					"where " +
					"user_account.userId = follow.followeeId " +
					"and " +
					"follow.followerId = ?")
			.addEntity(User.class).setInteger(0, userId).list();														
		} catch ( Exception e ) {		
			e.printStackTrace();			
		}
		return followees;
	}
		
	/**
	 * if follow stauts eqauls 1 means follow, 0 or null means not follow
	 * @param follow
	 */
	public int update( Follow follow ) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.update( follow );
			tx.commit( );
			return 1;
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();
			return -1;
		}
	}
	
	public void unfollow(Follow follow){
			
	}
	
	public static void main(String[] args){
		FollowDao fd = new FollowDao();
		Follow f = new Follow();
		f.setFolloweeId(1);
		f.setFollowerId(2);
		f.setFollowStatus(1);
		//System.out.println(f);
		//System.out.println(fd.add(f));
		
		
				
				List<User> allFollowees = fd.getAllFriends(1);  //get friends of this user
				Iterator<User> iterator = allFollowees.iterator();
				User u = new User();
				
				System.out.println(allFollowees.size());
				
				while(iterator.hasNext()){
					//System.out.println("3");
				}

		
		
	}

}
