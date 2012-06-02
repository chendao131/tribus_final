package dao;

import hibernate.TribusHibernateSessionFactory;
import model.BookCommentDraft;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookCommentDraftDao {
	public int save(BookCommentDraft bcd){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(bcd);
			session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;		
	}
	public static void main(String args[]){
		BookCommentDraftDao bcdd = new BookCommentDraftDao();
		BookCommentDraft bcd = new BookCommentDraft();
	
		bcd.setDraftTitle("d1");
		bcdd.save(bcd);
	}
}
