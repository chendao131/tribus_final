package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;

import model.BookTag;
import model.Movie;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookTagDao {
	public int save(BookTag bt) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(bt);
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
	
	@SuppressWarnings("unchecked")
	public boolean isExist(String tagName){
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from BookTag where tagName=:tagName";
			List<BookTag> bt = session.createQuery(sql).setString("tagName", tagName).list();
			if(bt.get(0) ==null){
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public BookTag getBookTagByName(String tagName){
		BookTag bt = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from BookTag as bt where bt.tagName=:tagName";
			List<BookTag> bts = session.createQuery(sql).setString("tagName", tagName)
					.list();
			bt = bts.get(0);
			System.out.println(bt.getTagId()+"**"+bt.getTagName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bt;	
	}
/*	public static void main(String args[]){
		BufferedReader reader = null;
		BookTagDao btd = new BookTagDao();
		Hashtable<String,Integer> alltags = new Hashtable<String, Integer>();
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Leon\\workspace\\thibusCrawler_GetBook_Final\\OutCome\\bookInfo\\FilmInfo2.txt"));
			String currentLine;
			while((currentLine = reader.readLine())!=null){
				if(currentLine.indexOf("subjects:")!=-1){
					
					String tags[] = currentLine.split(":")[1].split("&&");
					for(int i=0; i<tags.length-1; i++){
						if(!btd.isExist(tags[i])){
							//alltags.put(tags[i], 1);
							System.out.println(tags[i]);
							BookTag bt = new BookTag();
							bt.setTagName(tags[i]);
							btd.save(bt);
						}
					}
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}*/
}
