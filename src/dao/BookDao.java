package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vo.BookMarkVo;


import model.Book;
import model.BookMark;
import model.Music;
import model.Singer;
import model.User;

@Repository
public class BookDao {
	@SuppressWarnings("unchecked")
	public List<Book> searchBookByName(String name){
		String hql ="select b from Book b where lower(b.bookName) like :name order by b.bookPublishDate desc";
		List<Book> books = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			books = session.createQuery(hql).setString("name", "%"+name.toLowerCase()+"%").list();
		} catch (Exception e){
			e.printStackTrace();
		}
		return books;	
		
	}
	
	public int save(Book b) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(b);
			session.flush();
			tx.commit();
			session.clear();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;
	}
	
	public List<BookMarkVo> getBookAndGradeByUserId(int userId){
		
		String sql = "select b.bookId,b.bookName,bb.bookGrade from book b, book_mark bb, user_account u, user_profile up" +
				" where b.bookId = bb.bookId and u.userId = bb.userId and up.userId = u.userId" +
				" and u.userId = ? ";
		
		List<BookMarkVo> l_book_vo = new ArrayList<BookMarkVo>();
		
		Session session = TribusHibernateSessionFactory.currentSession();
		List l = session.createSQLQuery(sql).setInteger(0, userId).list();
		if( l!=null ){
			Iterator itr = l.iterator();
			while(itr.hasNext()){
				
				Object[] obj = (Object[])itr.next();
				BookMarkVo bmv = new BookMarkVo();
				bmv.setBookId(Integer.parseInt(obj[0].toString()));
				bmv.setBookName(obj[1].toString());
				bmv.setBookGrade(Integer.parseInt(obj[2].toString()));
				
				l_book_vo.add(bmv);
			}
		}
		
		return l_book_vo;
	}
	
	public List<Book> getBooksWantedByUserId(int id){
		String sql = "select b.* from book b, book_mark bm, user_account u" +
				" where b.bookId = bm.bookId and u.userId = bm.userId" +
				" and bm.bookRead = 1 and u.userId = ?";
		Session session = TribusHibernateSessionFactory.currentSession();
		return session.createSQLQuery(sql).addEntity(Book.class).setInteger(0, id).list();
	}
	
	public List<Book> getBooksReadByUserId(int id){
		String sql = "select b.* from book b, book_mark bm, user_account u" +
		" where b.bookId = bm.bookId and u.userId = bm.userId" +
		" and bm.bookRead = 2 and u.userId = ?";
		Session session = TribusHibernateSessionFactory.currentSession();
		return session.createSQLQuery(sql).addEntity(Book.class).setInteger(0, id).list(); 		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Book> getBooksWantedByUserId(){
		return null;
	}
	
	
	/**
	 * 
	 * @param b
	 * @return  1 means success , -1 means fail
	 */
	public int update(Book b) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(b);
			//session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return -1;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Book getBookById(Integer bookId){
		Book b = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from Book where bookId=:bookId";
			List<Book> bs = session.createQuery(sql).setInteger("bookId", bookId)
					.list();
			b = bs.get(0);
			//System.out.println(b.getBookName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return b;		
	}
	
	
	public List<Book> getBookByCondition(Book b){
		if(b==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(Book.class);
			if(b.getBookId() != 0 ){c.add(Restrictions.eq("bookId",b.getBookId() ));}
			if(b.getBookName() != null){c.add(Restrictions.eq("bookName", b.getBookName()));}
			if(b.getBookISBN() != null){c.add(Restrictions.eq("bookISBN", b.getBookISBN()));}
			if(b.getBookAuthor() != null){c.add(Restrictions.eq("bookAuthor", b.getBookAuthor()));}
									
			List<Book> books = c.list();  														
			return books;
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBookByTag(String bookTag){
		List<Book> bs = new ArrayList<Book>();
		Book b = null;
		String hql = "select b from Book b join b.tags t where lower(t.tagName) like :bookTag";
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			bs = session.createQuery(hql).setString("bookTag", "%"+bookTag.toLowerCase()+"%")
					.list();
			b = bs.get(0);
			System.out.println("success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bs.subList(0, 6);
	}
	
	public List<Book> getBooksByUserId(int id){
		String sql = "select b.* from book b, book_mark bm, user_account u where b.bookId = bm.bookId and" +
				" u.userId = bm.userId and bm.userId = ?";				
		List<Book> bs = new ArrayList<Book>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			bs = session.createSQLQuery(sql).addEntity(User.class).setInteger(0, id).list();									
		} catch ( Exception e ) {			
			System.out.println(e.getMessage());
		}
		return bs;		
	}
	
	@SuppressWarnings("unchecked")
	public double getAverageGrade(int bookId){
		Session session = TribusHibernateSessionFactory.currentSession();
		int gradeSum = 0;
		try{
			String hql = "from BookMark where bookId = :bookId";
			List<BookMark> bms = session.createQuery(hql).
								setInteger("bookId", bookId).list();
			for(int i=0; i< bms.size(); i++){
				gradeSum+= bms.get(i).getBookGrade();
			}
			return gradeSum/bms.size();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> searchBookByTag(String bookTag){
		//String sql = "from Movie where tags.tagName = :movieTag";
		//String[] tags = {"Drama"};
		/*String hql = "select m from Movie m "+
						"join m.tags t "+
						"where t.tagName in (:tags)";*/
		String hql = "select b from Book b "+
				"join b.tags t "+
				"where lower(t.tagName) like :bookTag";
		List<Book> books = new ArrayList<Book>();
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			 books = session.createQuery(hql).setString("bookTag", "%"+bookTag+"%").list();
		} catch (Exception e){
			e.printStackTrace();
		}
		return books;
	}
	public static void main(String args[]){
		BookDao bd = new BookDao();
		bd.getBookByTag("United States Army");
	}
}
