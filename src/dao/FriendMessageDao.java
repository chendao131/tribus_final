package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Follow;
import model.FriendMessage;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FriendMessageDao {
	
	public int add(FriendMessage fm){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( fm );
	//		session.flush();
			tx.commit( );			
			return 1;		
			
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback( );			
		}
		return -1;
	}	
	
	public List<FriendMessage> getAllFriendsMessage(int userId){		
		List<FriendMessage> messages = new ArrayList<FriendMessage>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			messages = session.createQuery("from FriendMessage where userId = :userId").setInteger(":userId", userId).list();														
		} catch ( Exception e ) {		
			e.printStackTrace();			
		}
		return messages;
	}
		
	/**
	 * if follow stauts eqauls 1 means follow, 0 or null means not follow
	 * @param follow
	 */
	public int update( FriendMessage fm ) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.update( fm );
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
