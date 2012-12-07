package dao;

import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.TribusClassify;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TribusListClassifyDao {

	public int save(TribusClassify z){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( z );			
			tx.commit( );			
			return 1;		
			
		} catch ( Exception e ) {
			tx.rollback( );			
		}
		return -1;		
	}
	
	@SuppressWarnings("unchecked")
	public TribusClassify getTribusListClassById(int id){
		TribusClassify z = null;
		try{
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from TribusClassify where id =:id";
			z = (TribusClassify) session.createQuery(hql).setInteger("id", id).uniqueResult();			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return z;
	}
	
	@SuppressWarnings("unchecked")
	public List<TribusClassify> getTribusListClassByUserId(int id){
		TribusClassify z = null;
		try{
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from TribusClassify where userId =:id";
			List<TribusClassify> l  = (List) session.createQuery(hql).setInteger("id", id).list();
			return l;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	public static void main(String[] args){
		TribusListClassifyDao tcd = new TribusListClassifyDao();
		TribusClassify t= new TribusClassify();
		t.setName("nihao");
		t.setUserId(5);
		
		tcd.save(t);
		
		tcd.getTribusListClassById(5);
		//tcd.getTribusListClassById(1);
	}
}
