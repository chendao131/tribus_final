package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.Book;
import model.Movie;
import model.MovieComment;
import model.Starring;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class StarringDao {
	@SuppressWarnings("unchecked")
	public List<Starring> searchStarByName(String name){
		//String sql = "from Movie where tags.tagName = :movieTag";
		//String[] tags = {"Drama"};
		/*String hql = "select m from Movie m "+
						"join m.tags t "+
						"where t.tagName in (:tags)";*/
/*		String hql = "from Starring";
		Session session = TribusHibernateSessionFactory.currentSession();
		List<Starring> allStars = new ArrayList<Starring>();
		List<Starring> result = new ArrayList<Starring>();
		try{
			allStars = session.createQuery(hql).list();
			 Iterator<Starring> iterator = allStars.iterator();
			 while(iterator.hasNext()){
				 Starring s = iterator.next();
				 if(s.getStarName().indexOf(name)!=-1){
					 result.add(s);
				 }
			 }
			 System.out.println("success");
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;*/
		
		String hql ="select s from Starring s where lower(s.starName) like :name";
		List<Starring> stars = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			 stars = session.createQuery(hql).setString("name", "%"+name.toLowerCase()+"%").list();
		} catch (Exception e){
			e.printStackTrace();
		}
		return stars;
		
		
	}
	
	public int save(Starring s){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			System.out.println("1");
			session.save(s);
			session.flush();
			tx.commit();
			System.out.println("2");
			return 1;

		} catch (Exception e) {
			tx.rollback();
		}
		return -1;	
	}
	
	@SuppressWarnings("unchecked")
	public Starring getStarringByName(String name){
		Starring s = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from Starring as s where s.starName=:name";
			s = (Starring)session.createQuery(hql).setString("name", name).uniqueResult();					
			return s;
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return s;
	}
/*	@SuppressWarnings("unchecked")
	public Starring getStarringByName(String name){
		List<Starring> ss = new ArrayList<Starring>();
		Starring s = new Starring();
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from Starring as s where s.starName=:name";
			ss = session.createQuery(hql).setString("name", name).list();	
			if(ss==null)
				return null;
			s = ss.get(0);
			return s;
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return s;
	}*/
	
	public Starring getStarringById(int id){
		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
			
			Criteria c = session.createCriteria(Starring.class);
			c.add(Restrictions.eq("starId",id)); 									
			Starring ms = (Starring)c.uniqueResult();  														
			return ms;
			
		} catch ( Exception e ) {
			e.printStackTrace();			
		}				
		return null;			
	}
	
	
	
	public List<Starring> getMovieCommentsByCondition(Starring s){
		if(s==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(Starring.class);
			if(s.getStarId() != 0 ){c.add(Restrictions.eq("starId",s.getStarId() ));}
			if(s.getStarIMDB() != 0){c.add(Restrictions.eq("starIMDB", s.getStarIMDB()));}
			if(s.getStarName() != null){c.add(Restrictions.eq("starName", s.getStarName()));}
			
										
			List<Starring> ms = c.list();  														
			return ms;
			
		} catch ( Exception e ) {
			e.printStackTrace();			
		}				
		return null;			
	}
	
	public int update(Starring s) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(s);
			//session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return -1;
	}
	
	public static void main(String args[]){
		StarringDao sd = new StarringDao();
		StarTypeDao std = new StarTypeDao();
		Starring newStar = new Starring();
		newStar.setStarName("d");
		newStar.setStarType(std.getStarTypeById(1));
		sd.save(newStar);
	}
}
