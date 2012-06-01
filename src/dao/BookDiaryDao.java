package dao;

import java.util.Date;

import hibernate.TribusHibernateSessionFactory;
import model.Book;
import model.BookDiary;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookDiaryDao {
	public int save(BookDiary bd){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(bd);
			session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;		
	}
/*	public static void main(String args[]){
		BookDiaryDao bcd = new BookDiaryDao();
		BookDiary bd = new BookDiary();
		UserDao ud = new UserDao();
		Book b = new Book();
		b.setBookId(1);
		User u = ud.getUserById(1);
		Date d = new Date("31/01/1988");
		bd.setDiaryDate(d);
		bd.setUser(u);
		bd.setDiaryId(1);
		bd.setBook(b);
		bcd.save(bd);
	}*/
}
