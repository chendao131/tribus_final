package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.Book;
import model.BookMark;
import model.MovieMark;
import model.MusicMark;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookMarkDao {
	@SuppressWarnings("unchecked")
	public Timestamp getMarkDateByBookAndUserId(int bookId, int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "select bm.markDate from BookMark bm where bm.book.bookId = :bookId and bm.user.userId = :userId";
			List<Timestamp> ts = session.createQuery(hql).
								setInteger("bookId", bookId).setInteger("userId", userId).list();
			return ts.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public BookMark getMarkByBookAndUserId(int bookId, int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "from BookMark bm where bm.book.bookId = :bookId and bm.user.userId = :userId";
			List<BookMark> bms = session.createQuery(hql).
								setInteger("bookId", bookId).setInteger("userId", userId).list();
			return bms.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int deleteRate(Integer bookId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from BookMark bm where bm.book.bookId = :bookId and bm.user.userId = :userId";
			List<BookMark> bms = session.createQuery(hql).
								setInteger("bookId", bookId).setInteger("userId", userId).list();
			BookDao bd = new BookDao();
			UserDao ud = new UserDao();
			if(bms.size()!=0){
				BookMark bm = bms.get(0);
				bm.setBookGrade(0);
				session.update(bm);
			}
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int rateByBookIdAndUserId(Integer bookId, Integer userId , Integer rate){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from BookMark bm where bm.book.bookId = :bookId and bm.user.userId = :userId";
			List<BookMark> bms = session.createQuery(hql).
								setInteger("bookId", bookId).setInteger("userId", userId).list();
			BookDao bd = new BookDao();
			UserDao ud = new UserDao();
			if(bms.size()!=0){
				BookMark bm = bms.get(0);
				bm.setBookGrade(rate);
				session.update(bm);
			}else{
				BookMark bm = new BookMark();
				bm.setBookRead(0);
				bm.setBookLike(0);
				bm.setBook(bd.getBookById(bookId));
				bm.setBookGrade(rate);
				bm.setUser(ud.getUserById(userId));
				session.save(bm);
			}

			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int markWatchDoneByBookIdAndUserId(Integer bookId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from BookMark bm where bm.book.bookId = :bookId and bm.user.userId = :userId";
			List<BookMark> bms = session.createQuery(hql).
								setInteger("bookId", bookId).setInteger("userId", userId).list();
			BookDao bd = new BookDao();
			UserDao ud = new UserDao();
			if(bms.size()!=0){
				BookMark bm = bms.get(0);
				bm.setBookRead(2);
				session.update(bm);
			}else{
				BookMark bm = new BookMark();
				bm.setBook(bd.getBookById(bookId));
				bm.setBookRead(2);
				bm.setUser(ud.getUserById(userId));
				session.save(bm);
			}
			
			session.flush();
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int markWatchWantedByBookIdAndUserId(Integer bookId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from BookMark bm where bm.book.bookId = :bookId and bm.user.userId = :userId";
			List<BookMark> bms = session.createQuery(hql).
								setInteger("bookId", bookId).setInteger("userId", userId).list();
			BookDao bd = new BookDao();
			UserDao ud = new UserDao();
			if(bms.size()!=0){
				BookMark bm = bms.get(0);
				bm.setBookRead(1);
				session.update(bm);
			}else{
				BookMark bm = new BookMark();
				bm.setBook(bd.getBookById(bookId));
				bm.setBookRead(1);
				bm.setUser(ud.getUserById(userId));
				session.save(bm);
			}
			session.flush();
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int save(BookMark bm){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(bm);
//			session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getLikeBookByUserId(Integer userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "from BookMark where user.userId = :userId and bookLike=1";
			List<BookMark> bookMarks = session.createQuery(hql).
								setInteger("userId", userId).list();
			List<Integer> bookIDs = new ArrayList<Integer>();
			Iterator<BookMark> iterator = bookMarks.iterator();
			while(iterator.hasNext()){
				bookIDs.add(iterator.next().getBook().getBookId());
			}
			return bookIDs;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public Integer getBookGradeByUserIdandBookId(Integer userId, Integer bookId){
		Session session = TribusHibernateSessionFactory.currentSession();
		BookMark bm = new BookMark();
		try{
			String hql = "from BookMark where user.userId = :userId and book.bookId= :bookId";
			List<BookMark> bookMarks = session.createQuery(hql).
								setInteger("userId", userId).setInteger("bookId",bookId).list();
			bm = bookMarks.get(0);
			return bm.getBookGrade();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return -1;		
	}
	
	@SuppressWarnings("unchecked")
	public double getAverageGrade(int bookId){
		Session session = TribusHibernateSessionFactory.currentSession();
		int gradeSum = 0;
		try{
			String hql = "from BookMark where bookId = :bookId";
			List<BookMark> mms = session.createQuery(hql).
								setInteger("bookId", bookId).list();
			for(int i=0; i< mms.size(); i++){
				gradeSum+= mms.get(i).getBookGrade();
			}
			return gradeSum/mms.size();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public void deleteBookMark(BookMark bm){		
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction( );
			try {
				session.delete( bm );
				tx.commit( );
			} catch ( Exception e ) {
				e.printStackTrace();
				tx.rollback( );			
			}		
	}	public static void main(String args[]){
		BookMarkDao bmd = new BookMarkDao();
/*		BookMark bm = new BookMark();
		UserDao ud = new UserDao();
		User u = ud.getUserById(1);
		BookDao bd = new BookDao();
		Book b = bd.getBookById(1);
		bm.setBook(b);
		bm.setUser(u);
		bm.setBookMarkId(1);
		bmd.save(bm);*/
	}
}
