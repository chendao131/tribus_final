package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.Book;
import model.Movie;
import model.Music;
import model.MyTribusList;
import model.TribusClassify;
import model.UserProfile;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import vo.MyTribusListVO;


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
	

	
	
	/**
	 * 
	 * @param tribusList
	 * @return
	 */
	
	public long add( MyTribusList tribusList ) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			
			MyTribusList id = (MyTribusList)session.createSQLQuery("select * from tribus_list where resourceId = ?" +
					" and userId = ? and type = ?").addEntity(MyTribusList.class).
					setInteger(0, tribusList.getResourceId()).
					setInteger(1, tribusList.getUserProfile().getUser().getUserId()).
					setString(2, tribusList.getType()).uniqueResult();
								
			System.out.println( tribusList.getResourceId());
			System.out.print(tribusList.getUserProfile().getUser().getUserId());
			System.out.println(tribusList.getType());
			
			if(id==null){
				session.save( tribusList );	
				tx.commit( );			
				return 1;
			}else{
				session.delete(id);
				tx.commit( );			
				return -1;
			}																
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();
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
	
	/**
	 * 
	 * @param type music,book,movie,city
	 * @param resourceId
	 * @return
	 */
	public boolean isAddResource(String type, int resourceId,int userId){
		String sql = "";				
		List<MyTribusList> bs = new ArrayList<MyTribusList>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			bs = session.createSQLQuery(sql).addEntity(MyTribusList.class).setInteger(0, resourceId).list();								
		} catch ( Exception e ) {			
			System.out.println(e.getMessage());
		}			
		return false;
		
	}
		

	public List<MyTribusList> getHotBookTribusList(){
		
		String sql = "select * from tribus_list where type = 'book' limit 0,5 ";
		Session session = TribusHibernateSessionFactory.currentSession();
		List<MyTribusList> tagNames = session.createSQLQuery(sql).addEntity(MyTribusList.class).list();		
		return tagNames;		
	}
	
	public List<MyTribusList> getHotMusicTribusList(){
		String sql = "select * from tribus_list where type = 'music' limit 0,5 ";
		Session session = TribusHibernateSessionFactory.currentSession();
		List<MyTribusList> tagNames = session.createSQLQuery(sql).addEntity(MyTribusList.class).list();		
		return tagNames;
	}
	
	public List<MyTribusList> getHotMovieTribusList(){
		String sql = "select * from tribus_list where type = 'movie' limit 0,5 ";
		Session session = TribusHibernateSessionFactory.currentSession();
		List<MyTribusList> tagNames = session.createSQLQuery(sql).addEntity(MyTribusList.class).list();		
		return tagNames;
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
	
	
	public List<MyTribusList> getTribusListByClassId(int classId){
		String sql = "select t.* from tribus_list t , tribus_list_classified tu where t.classid = tu.id and "+ 
                     "tu.id = ? ";			
		List<MyTribusList> bs = new ArrayList<MyTribusList>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			bs = session.createSQLQuery(sql).addEntity(MyTribusList.class).setInteger(0, classId).list();								
		} catch ( Exception e ) {			
			System.out.println(e.getMessage());
		}
		return bs;		
	}
	
	
	private List<MyTribusListVO> getTribusListByResourceName(String sql,String name){
		
		Session session = TribusHibernateSessionFactory.currentSession();
		List<MyTribusListVO> bs = new ArrayList<MyTribusListVO>();		
		try {
			List l = null;
			l = session.createSQLQuery(sql).setString(0, name).list();
			if(l!=null){
				Iterator itr = l.iterator();
				while(itr.hasNext()){
					Object[] obj = (Object[])itr.next();
					MyTribusListVO mvo = new MyTribusListVO();
					mvo.setUserName(obj[0].toString());
					mvo.setResourceName(obj[1].toString());
					mvo.setResourceId(Integer.parseInt(obj[2].toString()));
					
					bs.add(mvo);
				}
			}
		} catch ( Exception e ) {			
			e.printStackTrace();
		}
		
		return bs;
	}	
	
	public List<MyTribusListVO> getTribusListMovieByResourceName(String name){				
		String sql = "select u.userAlias,t.resourceName, t.resourceId from tribus_list t,user_account u" +
				" where t.userId = u.userId and t.type = 'movie' and t.resourceName  REGEXP '/[?]/' ";
		return getTribusListByResourceName(sql,name);
	}
	
	public List<MyTribusListVO> getTribusListMusicByResourceName(String name){
		String sql = "select u.userAlias,t.resourceName, t.resourceId from tribus_list t,user_account u" +
		" where t.userId = u.userId and t.type = 'music' and t.resourceName  REGEXP '/[?]/' ";
		return getTribusListByResourceName(sql,name);
	}
	
	public List<MyTribusListVO> getTribusListBookByResourceName(String name){
		String sql = "select u.userAlias,t.resourceName, t.resourceId from tribus_list t,user_account u" +
		" where t.userId = u.userId and t.type = 'book' and t.resourceName  REGEXP '/[?]/' ";
		return getTribusListByResourceName(sql,name);
	}
	
	
	public List<MyTribusListVO> getTribusListById(int id){
		String sql = "select u.userAlias, t.* from tribus_list t ,user_account u where t.userId = u.userId "+ 
                     " and t.resourceId = ? ";			
		List<MyTribusListVO> l = new ArrayList<MyTribusListVO>();
		List bs = new ArrayList();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			bs = session.createSQLQuery(sql).setInteger(0, id).list();	
			if(bs!=null){												
				Iterator itr = bs.iterator();
				while(itr.hasNext()){
					MyTribusListVO ml = new MyTribusListVO();
					Object[] obj = (Object[])itr.next();
					ml.setUserName(obj[0].toString());						
				}
				
			}
			
			
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
	
	public static void main(String[] args){
		TribusListDao td = new TribusListDao();
		
		
		
		MyTribusList mt = new MyTribusList();
		mt.setType("music");
		mt.setCreateDate(new Date());
		mt.setResourceId(10);
		mt.setResourceName("good music");
		mt.setTitle("what's that");
		//mt.set
		
		
		//TribusListClassifyDao tcd = new TribusListClassifyDao();														
		//mt.setClassified(tcd.getTribusListClassById(6));
				
		td.add(mt);				
		
		List<MyTribusList> tl = td.getTribusListByUserId(1);
		for (MyTribusList myTribusList : tl) {
			System.out.println(myTribusList.getClassified());			
		}
		
	}
}
