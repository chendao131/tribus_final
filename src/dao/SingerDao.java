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
		//String sql = "from Movie where tags.tagName = :movieTag";
		//String[] tags = {"Drama"};
		/*String hql = "select m from Movie m "+
						"join m.tags t "+
						"where t.tagName in (:tags)";*/
		String hql = "from Singer";
		Session session = TribusHibernateSessionFactory.currentSession();
		List<Singer> singers = new ArrayList<Singer>();
		List<Singer> result = new ArrayList<Singer>();
		try{
			singers = session.createQuery(hql).list();
			 Iterator<Singer> iterator = singers.iterator();
			 while(iterator.hasNext()){
				 Singer s = iterator.next();
				 if(s.getSingerName().indexOf(name)!=-1){
					 result.add(s);
				 }
			 }
			 System.out.println("success");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(result.size());
		return result;
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
