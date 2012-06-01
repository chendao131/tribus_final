package dao;

import hibernate.TribusHibernateSessionFactory;
import model.MusicType;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MusicTypeDao {
	public int save(MusicType mt){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(mt);
			session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;		
	}
/*	public static void main(String args[]){
		
	}*/
}
