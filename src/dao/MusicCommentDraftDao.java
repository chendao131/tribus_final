package dao;

import hibernate.TribusHibernateSessionFactory;

import model.MusicCommentDraft;
import model.Music;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MusicCommentDraftDao {
	public int save(MusicCommentDraft mcd){
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
		MusicCommentDraftDao mcdd = new MusicCommentDraftDao();
		MusicCommentDraft mcd = new MusicCommentDraft();
		MusicDao md = new MusicDao();
		Music m = md.getMusicById(3);
		mcd.setMusic(m);
		mcd.setDraftTitle("d1");
		mcdd.save(mcd);
	}
}
