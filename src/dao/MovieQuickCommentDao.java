package dao;

import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.MovieComment;
import model.MovieQuickComment;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieQuickCommentDao {
/*	public int addQuickCommentByReviewIdAndUserId(int reviewId, int userId){
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
	}*/
	
	public int save(MovieQuickComment mqc){
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
	public List<MovieQuickComment> getQuickCommentByCommentId(Integer commentId){
		List<MovieQuickComment> mqcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieQuickComment as mqc where mqc.comment.commentId=:commentId order by mqc.quickCommentId desc";
			 mqcs = session.createQuery(hql).setInteger("commentId",commentId).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mqcs;		
	}
	
	public static void main(String args[]){
		MovieQuickCommentDao mqcd = new MovieQuickCommentDao();
		MovieQuickComment mqc = new MovieQuickComment();
		List<MovieQuickComment> mqcs = mqcd.getQuickCommentByCommentId(25);
		Iterator<MovieQuickComment> iterator = mqcs.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next().getCommentContent());
		}
/*		mqc.setCommentContent("qqqqqqccccccc1");
		mqc.setCommentTitle("qc1");
		mqcd.save(mqc);*/
	}
}
