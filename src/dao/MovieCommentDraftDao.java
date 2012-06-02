package dao;

import hibernate.TribusHibernateSessionFactory;
import model.MovieComment;
import model.MovieCommentDraft;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovieCommentDraftDao {
	public int save(MovieCommentDraft mcd){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(mcd);
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
		MovieCommentDraftDao mcdd = new MovieCommentDraftDao();
		MovieCommentDraft mcd = new MovieCommentDraft();
		mcd.setDraftTitle("d1");
		mcdd.save(mcd);
	}
}
