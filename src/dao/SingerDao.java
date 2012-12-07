package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.Music;
import model.Singer;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SingerDao {
	@SuppressWarnings("unchecked")
	public List<Singer> searchSingerByName(String name){
		String hql ="select s from Singer s where lower(s.singerName) like :name";
		List<Singer> singers = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			singers = session.createQuery(hql).setString("name", "%"+name.toLowerCase()+"%").list();
		} catch (Exception e){
			e.printStackTrace();
		}
		return singers;		
		
	}
	
	public int save(Singer s) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(s);
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
	public Singer getSingerByName(String name){
		Singer s = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from Singer where singerName=:singerName";
			List<Singer> ss = session.createQuery(sql).setString("singerName", name)
					.list();
			s = ss.get(0);
			//System.out.println(b.getBookName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return s;		
	}
	
	public static void main(String argsp[]){
		SingerDao sd = new SingerDao();
		List<Singer> singers = sd.searchSingerByName("g");
	}
}
