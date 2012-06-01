package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.List;

import model.BookQuickComment;
import model.MovieQuickComment;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookQuickCommentDao {
	public int save(BookQuickComment bqc){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(bqc);
			session.flush();
			tx.commit();
			saveSuccess = 1;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookQuickComment> getQuickCommentByCommentId(Integer commentId){
		List<BookQuickComment> bqcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from BookQuickComment as bqc where bqc.comment.commentId=:commentId";
			bqcs = session.createQuery(hql).setInteger("commentId",commentId).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bqcs;		
	}
}
