package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.MovieComment;
import model.Music;
import model.MusicComment;
import model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class MusicCommentDao {
	@SuppressWarnings("unchecked")
	public List<MusicComment> getCommentByMusicIdAndRating(int musicId, int rating){
		List<MusicComment> mcs = new ArrayList<MusicComment>();
		List<MusicComment> result = new ArrayList<MusicComment>();
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MusicComment mc where mc.music.musicId=:musicId";
			 mcs = session.createQuery(hql).setInteger("musicId", musicId).list();
			 
			 MusicMarkDao mmd = new MusicMarkDao();
			 Iterator<MusicComment> iterator = mcs.iterator();
			 while(iterator.hasNext()){
				 MusicComment mc = iterator.next();
				 if(mmd.getGradeByMusicIdAndUseId(mc.getMusic().getMusicId(), mc.getUser().getUserId())==rating){
					 result.add(mc);
				 }
			 }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return result;		
	}
	
	public int save(MusicComment mc){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(mc);
			session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;		
	}
	
	@SuppressWarnings("unchecked")
	public MusicComment getMusicCommentByCommentId(int commentId){
		MusicComment mc = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		List<MusicComment> mcs = new ArrayList<MusicComment>();
		try{
			String hql = "from MusicComment where commentId=:commentId";
			mcs = session.createQuery(hql).
								setInteger("commentId", commentId).list();
			mc = mcs.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mc;		
	}
	
	@SuppressWarnings("unchecked")
	public List<MusicComment> getMusicCommentByUserId(int UserId,int num){
		Session session = TribusHibernateSessionFactory.currentSession();
		List<MusicComment> mcs = new ArrayList<MusicComment>();
		try{
			String hql = "from MusicComment as mc where mc.user.userId=:UserId";
			mcs = session.createQuery(hql).
								setInteger("UserId", UserId).list();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(mcs!=null && mcs.size() > num){
			return mcs.subList(0, num);
		}
		return mcs;		
	}
	
	@SuppressWarnings("unchecked")
	public List<MusicComment> getAllMusicComment(){
		List<MusicComment> mcs = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from MusicComment";
			 mcs = session.createQuery(hql).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return mcs;		
	}
	
	@SuppressWarnings("unchecked")
	public List<MusicComment> getMusicCommentByMusicId(int musicId){
		Session session = TribusHibernateSessionFactory.currentSession();
		List<MusicComment> mcs = new ArrayList<MusicComment>();
		try{
			String hql = "from MusicComment as mc where mc.music.musicId=:musicId";
			mcs = session.createQuery(hql).
								setInteger("musicId", musicId).list();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mcs;		
	}
	
	public List<MusicComment> getMusicCommentsByCondition(MusicComment m){
		if(m==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(MusicComment.class);
			if(m.getCommentId() != 0 ){c.add(Restrictions.eq("commentId",m.getCommentId() ));}
			if(m.getCommentDate() != null ){c.add(Restrictions.eq("commentDate",m.getCommentDate() ));}
			
			List<MusicComment> ms = c.list();  														
			return ms;
			
		} catch ( Exception e ) {
			e.printStackTrace();			
		}				
		return null;			
	}
	
	public static void main(String args[]){
	MusicCommentDao mcd = new MusicCommentDao();
	
/*	UserDao ud = new UserDao();
	User u = ud.getUserById(1);
	mc.setUser(u);
	mc.setCommentId(1);
	mcd.save(mc);*/
}
}
