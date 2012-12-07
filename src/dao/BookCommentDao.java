package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;

import model.BookComment;
import model.MovieComment;
import model.MusicComment;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import vo.UserComment;

public class BookCommentDao {
	@SuppressWarnings("unchecked")
	public List<BookComment> getCommentByBookIdAndRating(int bookId, int rating){
		List<BookComment> mcs = new ArrayList<BookComment>();
		List<BookComment> result = new ArrayList<BookComment>();
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from BookComment bc where bc.book.bookId=:bookId";
			 mcs = session.createQuery(hql).setInteger("bookId", bookId).list();
			 
			 BookMarkDao bmd = new BookMarkDao();
			 Iterator<BookComment> iterator = mcs.iterator();
			 while(iterator.hasNext()){
				 BookComment bc = iterator.next();
				 if(bmd.getBookGradeByUserIdandBookId(bc.getUser().getUserId(),bc.getBook().getBookId()) ==rating){
					 result.add(bc);
				 }
			 }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return result;		
	}
	
	public int save(BookComment bc){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(bc);
			session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;		
	}
	
	
/*	public List<BookComment> getMusicByCondition(BookComment bc){
		if(bc==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(BookComment.class);
			if(bc.getCommentId() != 0 ){c.add(Restrictions.eq("commentId",bc.getCommentId() ));}
			if(bc.getCommentDate() != null){c.add(Restrictions.eq("commentDate", bc.getCommentDate()));}
			
									
			List<BookComment> ms = c.list();  														
			return ms;
			
		} catch ( Exception e ) {
			e.printStackTrace();			
		}				
		return null;			
	}*/
	
	@SuppressWarnings("unchecked")
	public BookComment getBookCommentById(Integer commentId){
		BookComment bc = null;
		List<BookComment> bcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from BookComment where commentId=:commentId";
			 bcs = session.createQuery(hql).setInteger("commentId",commentId).list();
			bc = bcs.get(0);
			System.out.println("success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bc;			
	}
	
	
	/**
	 * private String bookName;
	private String bookUrl;	
	private String userComments;
	private Date date;
	 * 
	 * 
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BookComment> getBookCommentByUserId(int UserId ,int num){
		Session session = TribusHibernateSessionFactory.currentSession();
		List<BookComment> bcs = new ArrayList<BookComment>();
		try{
			String hql = "from BookComment as bc where bc.user.userId=:UserId";
			bcs = session.createQuery(hql).
								setInteger("UserId", UserId).list();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(bcs!=null && bcs.size() > num){
			return bcs.subList(0, num);
		}
		return bcs;		
	}
	
	@SuppressWarnings("unchecked")
	public List<BookComment> getAllBookComment(){
		List<BookComment> bcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from BookComment";
			bcs = session.createQuery(hql).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bcs;		
	}
	
	@SuppressWarnings("unchecked")
	public BookComment getBookCommentByUserIdAndBookId(Integer userId, Integer bookId){
		BookComment bc = null;
		List<BookComment> bcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from BookComment where user.userId=:userId and book.bookId=:bookId";
			 bcs = session.createQuery(hql).setInteger("userId", userId).setInteger("bookId",bookId).list();
			bc = bcs.get(0);
			System.out.println("success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bc;		
	}
	
	@SuppressWarnings("unchecked")
	public List<BookComment> getBookCommentByBookId(Integer bookId){
		BookComment bc = null;
		List<BookComment> bcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from BookComment where book.bookId=:bookId";
			 bcs = session.createQuery(hql).setInteger("bookId",bookId).list();
			bc = bcs.get(0);
			System.out.println("success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bcs;		
	}
	public static void main(String args[]){
		BookCommentDao bcd = new BookCommentDao();
		System.out.println(bcd.getBookCommentByUserIdAndBookId(1, 2522).getBook().getBookPic());
//		bcd.getBookCommentsByUserId();
		/*BookComment bc = new BookComment();
		UserDao ud = new UserDao();
		User u = ud.getUserById(1);
		bc.setUser(u);
		bc.setCommentId(1);
		bcd.save(bc);
		*/
	}
}
