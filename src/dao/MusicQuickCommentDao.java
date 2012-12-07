package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import model.MusicQuickComment;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MusicQuickCommentDao {
	public int save(MusicQuickComment mqc){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(mqc);
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
	public List<MusicQuickComment> getQuickCommentByCommentId(Integer commentId){
		List<MusicQuickComment> mqcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MusicQuickComment as mqc where mqc.comment.commentId=:commentId order by mqc.quickCommentId desc";
			 mqcs = session.createQuery(hql).setInteger("commentId",commentId).list();
			 if(mqcs == null){
				 mqcs = new ArrayList<MusicQuickComment>();
			 }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mqcs;		
	}
}
