package dao;
import hibernate.TribusHibernateSessionFactory;

import java.util.List;

import model.MovieTag;
import model.MusicTag;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
public class MusicTagDao {
	public int save(MusicTag mt){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(mt);
			session.flush();
			tx.commit();
			session.clear();
			return 1;

		} catch (Exception e) {
			tx.rollback();
		}
		return -1;	
	}
	
	@SuppressWarnings("unchecked")
	public MusicTag getMusicTagByName(String name){
		MusicTag mt = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MusicTag as mt where mt.tagName=:name";
			List<MusicTag> mts = session.createQuery(hql).setString("name", name)
					.list();
			mt = mts.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mt;
	}
}
