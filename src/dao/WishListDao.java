package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import model.MyTribusList;
import model.WishList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class WishListDao {
	public List<WishList> getWishListByCondition(WishList m){
		if(m==null){
			return null;
		}
				
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(WishList.class);
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
	

	
	
	public int add( WishList wishList ) throws Exception {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			
			WishList id = (WishList)session.createSQLQuery("select * from wish_list where resourceId = ?" +
					" and userId = ? and type = ?").addEntity(WishList.class).
					setInteger(0, wishList.getResourceId()).
					setInteger(1, wishList.getUserProfile().getUser().getUserId()).
					setString(2, wishList.getType()).uniqueResult();
			
			if(id==null){			
				session.save( wishList );	
			}else{				
				session.delete(id);
				tx.commit( );			
				return -1;
			}			
			//session.flush();					
			tx.commit( );			
			return 1;			
		} catch ( Exception e ) {
			e.printStackTrace();
			tx.rollback( );			
		}
		return -1;

	}
	
	

	
	public int update( WishList wishList ) throws Exception {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.update( wishList );
			tx.commit( );
			return 1;
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();			
		}
		return -1;
	}
	
	

	public int delete( WishList wishList ) throws Exception {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.delete( wishList );
			tx.commit( );
			return 1;
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();
		}
		    return -1;
	}
	
	
	/**
	 * type music,book,movie,city
	 * @param type
	 * @param resourceId
	 * @return
	 */
	public boolean isAddResource(String type, int resourceId,int userId){
			String sql = "select * from wishList";				
			List<MyTribusList> bs = new ArrayList<MyTribusList>();		
			Session session = TribusHibernateSessionFactory.currentSession();		
	try {		
		session.createSQLQuery(sql).addEntity(MyTribusList.class).setInteger(0, resourceId).list();
	} catch ( Exception e ) {			
		System.out.println(e.getMessage());
	}		
		return false;		
	}
	
	
	public List<WishList> getWishListByUserId(int id){
		String sql = "select w.* from wish_list w,user_account u where w.userId = u.userId " +				
				" and u.userId = ? ";				
		List<WishList> bs = new ArrayList<WishList>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			bs = session.createSQLQuery(sql).addEntity(WishList.class).setInteger(0, id).list();									
		} catch ( Exception e ) {			
			System.out.println(e.getMessage());
		}
		return bs;		
	}
}
