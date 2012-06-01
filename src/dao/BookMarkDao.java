package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.Book;
import model.BookMark;
import model.MusicMark;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookMarkDao {
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
			System.out.println("success");
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
			System.out.println("success");
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
		return -1;
	}
	
	public static void main(String args[]){
		BookMarkDao bmd = new BookMarkDao();
		System.out.println(bmd.getBookGradeByUserIdandBookId(1, 2522));
		//System.out.println(bmd.getLikeBookByUserId(2).size());
		//List<Integer> bookIDs = bmd.getLikeBookByUserId(2);
		//System.out.println(bookIDs.size());
		//System.out.println(bookIDs.get(0));
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
