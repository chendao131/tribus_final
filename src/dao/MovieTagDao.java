package dao;


import hibernate.TribusHibernateSessionFactory;

import java.util.List;

import model.MovieTag;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieTagDao {
	public int save(MovieTag mt){
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
	public MovieTag getMovieTagByName(String name){
		MovieTag mt = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MovieTag as mt where mt.tagName=:name";
			List<MovieTag> mts = session.createQuery(hql).setString("name", name)
					.list();
			mt = mts.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mt;
	}
/*	public static void main(String args[]){
		MovieTag mt = new MovieTag();
		mt.setTagName("tag4");
		std.save(mt);
		MovieTagDao std = new MovieTagDao();
		MovieTag mt = std.getMovieTagByName("tag4");
		System.out.println(mt.getTagId());
	}*/
}
