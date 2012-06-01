package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import model.Music;
import model.MyTribusList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class TribusListDao {

	
	public List<MyTribusList> getTribusListByCondition(MyTribusList m){
		if(m==null){
			return null;
		}
				
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(MyTribusList.class);
			if(m.getCreateDate() !=null){c.add(Restrictions.eq("createDate", m.getCreateDate()));}
			if(m.getResourceId()!=0){c.add(Restrictions.eq("resourceId",m.getResourceId()));}
			if(m.getId()!=0){c.add(Restrictions.eq("id", m.getId()));}			
			if(m.getType() != null){c.add(Restrictions.eq("type", m.getType()));}				
									
			return c.list();
			
						
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	

	
	
	public long add( MyTribusList tribusList ) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( tribusList );
			//session.flush();					
			tx.commit( );			
			return 1;						
		} catch ( Exception e ) {
			tx.rollback( );			
		}
		return -1;
	}		
	
	public int update( MyTribusList tribusList ) throws Exception {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.update( tribusList );
			tx.commit( );
			return 1;
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();			
		}
		return -1;
	}
	
	

	public int delete( MyTribusList message ) throws Exception {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.delete( message );
			tx.commit( );
			return 1;
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();
		}
		    return -1;
	}
	



	public List<MyTribusList> getTribusListByUserId(int id){
		String sql = "select t.* from tribus_list t , user_account u where t.userId = u.userId and " +				
				" u.userId = ?";				
		List<MyTribusList> bs = new ArrayList<MyTribusList>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			bs = session.createSQLQuery(sql).addEntity(MyTribusList.class).setInteger(0, id).list();								
		} catch ( Exception e ) {			
			System.out.println(e.getMessage());
		}
		return bs;		
	}
	
	
	
	
	
//	public List<Music> getTribusListByUserId(int id){
//		String sql = "select m.* from music m, music_mark mm, user_account u where mm.musicLike = 1 and " +
//				"m.musicId = mm.musicId and" +
//				" u.userId = mm.userId and mm.userId = ?";				
//		List<Music> bs = new ArrayList<Music>();		
//		Session session = TribusHibernateSessionFactory.currentSession();		
//		try {
//			bs = session.createSQLQuery(sql).addEntity(Music.class).setInteger(0, id).list();									
//		} catch ( Exception e ) {			
//			System.out.println(e.getMessage());
//		}
//		return bs;		
//	}
}
