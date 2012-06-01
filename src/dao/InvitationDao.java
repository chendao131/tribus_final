package dao;

import hibernate.TribusHibernateSessionFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Invitation;

import java.util.*;

public class InvitationDao {
	public void save(Invitation i){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( i );
			//session.flush();
			tx.commit( );			
			//return 1;		
			
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback( );			
		}
		//return -1;	
	}
	
	public List<Invitation> getAllEmails(){		
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from Invitation";
			List<Invitation> s = session.createQuery(hql).list();					
			return s;
		} catch (Exception e) {		
			e.printStackTrace();
		}		
		return null;
	}
	
	public static void main(String[] args){
		InvitationDao id = new InvitationDao();
		Invitation i = new Invitation();
		i.setEmail("fu@163.com");
		id.save(i);
	}
}
