package dao;

import hibernate.TribusHibernateSessionFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Message;
import model.MovieComment;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.IntArrayToString;
import vo.ActivityGoingTempResult;

public class MessageDao {
	
	
	
	/**
	 * 
	 * @return
	 */
	public int[] getInboxNumberReadAndUnRead(int id){
		
		int[] readAndUnread = new int[2];
		
		
		String sql = "select count(*) as num  from message where messageToUserId = ? and isRead = 0 union "
		+"select count(*) as num  from message where messageToUserId = ?"; 
		
		List l = TribusHibernateSessionFactory.currentSession().createSQLQuery(sql).setInteger(1,id).setInteger(2, id).list();
		if( l!=null ){
			Iterator i = l.iterator();
			if(i.hasNext()){
				Object[] obj = (Object[])i.next();
				readAndUnread[0] = (Integer)obj[0];
				readAndUnread[1] = (Integer)obj[1];
			}			
		}
		
		return readAndUnread;
	}
	
	public Map<Integer,String> getUserNameById(){
		
		Map<Integer,String> m = new HashMap<Integer,String>();	
		String sql = "select u.userAlias , m.messageToUserId from user_account u, message m where" +
				" u.userId = m.messageToUserId ";
		
		List res_from_DB = new ArrayList();
		Iterator i = null;		
		res_from_DB = TribusHibernateSessionFactory.currentSession().createSQLQuery(sql).list();
		
		if( res_from_DB != null ){
			i = res_from_DB.iterator();
		}
		
		while (i.hasNext()) {	
			Object[] objs = (Object[]) i.next();
			m.put((Integer)objs[0], objs[1].toString());			
		}				
		
		return m;
	}
	
	public List<Message> getMessageListByCondition(Message m){
		if(m==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(Message.class);
			if(m.getMessageDate() !=null){c.add(Restrictions.eq("messageDate", m.getMessageDate()));}
			if(m.getMessageFromUserId() > 0){c.add(Restrictions.eq("messageFromUserId",m.getMessageFromUserId()));}
			if(m.getMessageContent()!=null){c.add(Restrictions.eq("messageContent", m.getMessageContent()));}
			if(m.getMessageToUserId() > 0){c.add(Restrictions.eq("messageToUserId", m.getMessageToUserId()));}
			if(m.getType() != null){c.add(Restrictions.eq("type", m.getType()));}	
			if(m.isRead()){c.add(Restrictions.eq("isRead", m.isRead()));}
									
			return c.list();  														
						
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	public Message getMessageByCondition(Message m){
		if(m==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(Message.class);
			if(m.getMessageDate() !=null){c.add(Restrictions.eq("messageDate", m.getMessageDate()));}
			if(m.getMessageFromUserId() > 0){c.add(Restrictions.eq("messageFromUserId",m.getMessageFromUserId()));}
			if(m.getMessageContent()!=null){c.add(Restrictions.eq("messageContent", m.getMessageContent()));}
			if(m.getMessageToUserId() > 0){c.add(Restrictions.eq("messageToUserId", m.getMessageToUserId()));}
			if(m.getType() != null){c.add(Restrictions.eq("type", m.getType()));}	
			if(m.isRead()){c.add(Restrictions.eq("isRead", m.isRead()));}
									
			Message m1 = (Message)c.uniqueResult();  														
			return m1;
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	
	public long add( Message message ){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( message );
			//session.flush();					
			tx.commit( );			
			return 1;						
		} catch ( Exception e ) {
			tx.rollback( );		
			e.printStackTrace();
		}
		return -1;
	}
	
	

	
	public int update( Message message ) throws Exception {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.update( message );
			tx.commit( );
			return 1;
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();			
		}
		return -1;
	}
	
//	
//	public int deleteList(){
//		String hql = "delete Message where messageId = :id";
//		
//		return 0;
//	}
	
	
	public List<Message> getMessagesByIds(int[] ids){
		
		String sql = "from Message where messageId in ("+IntArrayToString.arrayTostring(ids)+")";
		List<Message> l = TribusHibernateSessionFactory.currentSession().createQuery(sql).list();
		return l;
	}
	
	public int deleteMessageByIds(int[] ids){
		List<Message> l = getMessagesByIds(ids);
		return deleteMessageList(l);		
	}
	
	public int deleteMessageList(List<Message> list){
		if(list == null || list.size() == 0){
			return 0;
		}
		
		int i = 0;		
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {						
			for (Message message : list) {				
				session.delete( message );	
				i++;
			}			
			session.flush();			
			tx.commit( );					
			return i;
		} catch ( Exception e ) {
			tx.rollback( );
			e.printStackTrace();
		}
		    return i;			
	}
	
	public int delete( Message message ) throws Exception {
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
	
	
	public List<Message> getUserInboxMessageAll(int id){
		Session session = TribusHibernateSessionFactory.currentSession();
		String hql = "from Message where messageToUserId = :id";
		List<Message> l = session.createQuery(hql).setInteger("id", id).list();
		return l;
	}
	
	
	public List<Message> getUserInboxMessageAllUnread(int id){
		Session session = TribusHibernateSessionFactory.currentSession();
		String hql = "from Message where messageToUserId = :id and read = 0";
		List<Message> l = session.createQuery(hql).setInteger("id", id).list();
		return l;
	}
	
	
	public List<Message> getUserInboxMessageRead(){
		return null;
	}
	
	public List<Message> getUserInboxMessageUnRead(){
		return null;
	}

	
	public List<Message> getUserOutboxMessageAll(int id){
		Session session = TribusHibernateSessionFactory.currentSession();
		String hql = "from Message where messageFromUserId = :id";
		List<Message> l = session.createQuery(hql).setInteger("id", id).list();
		return l;
	}
	
	public List<Message> getUserOutboxMessageRead(){
		return null;
	}
	
	public List<Message> getUserOutboxMessageUnRead(){
		return null;
	}
}
