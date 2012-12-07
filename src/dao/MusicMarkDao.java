package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.MovieMark;
import model.MusicMark;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MusicMarkDao {
	public int deleteRate(Integer musicId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from MusicMark mm where mm.music.musicId = :musicId and mm.user.userId = :userId";
			List<MusicMark> mms = session.createQuery(hql).
								setInteger("musicId", musicId).setInteger("userId", userId).list();
			MusicDao md = new MusicDao();
			UserDao ud = new UserDao();
			if(mms.size()!=0){
				MusicMark mm = mms.get(0);
				mm.setMusicGrade(0);
				session.update(mm);
			}
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	@SuppressWarnings("unchecked")
	public Timestamp getMarkDateByMusicAndUserId(int musicId, int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "select mm.markDate from MusicMark mm where mm.music.musicId = :musicId and mm.user.userId = :userId";
			List<Timestamp> ts = session.createQuery(hql).
								setInteger("musicId", musicId).setInteger("userId", userId).list();
			return ts.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public MusicMark getMarkByMusicAndUserId(int musicId, int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			String hql = "from MusicMark mm where mm.music.musicId = :musicId and mm.user.userId = :userId";
			List<MusicMark> mms = session.createQuery(hql).
								setInteger("musicId", musicId).setInteger("userId", userId).list();
			return mms.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public int markWatchDoneByMusicIdAndUserId(Integer musicId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from MusicMark mm where mm.music.musicId = :musicId and mm.user.userId = :userId";
			List<MusicMark> mms = session.createQuery(hql).
								setInteger("musicId", musicId).setInteger("userId", userId).list();
			MusicDao md = new MusicDao();
			UserDao ud = new UserDao();
			if(mms.size()!=0){
				MusicMark mm = mms.get(0);
				mm.setMusicListen(2);
				session.update(mm);
			}else{
				MusicMark mm = new MusicMark();
				mm.setMusic(md.getMusicById(musicId));
				mm.setMusicListen(2);
				mm.setUser(ud.getUserById(userId));
				session.save(mm);
			}
			
			session.flush();
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int markWatchWantedByMusicIdAndUserId(Integer musicId, Integer userId){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from MusicMark mm where mm.music.musicId = :musicId and mm.user.userId = :userId";
			List<MusicMark> mms = session.createQuery(hql).
								setInteger("musicId", musicId).setInteger("userId", userId).list();
			MusicDao md = new MusicDao();
			UserDao ud = new UserDao();
			if(mms.size()!=0){
				MusicMark mm = mms.get(0);
				mm.setMusicListen(1);
				session.update(mm);
			}else{
				MusicMark mm = new MusicMark();
				mm.setMusic(md.getMusicById(musicId));
				mm.setMusicListen(1);
				mm.setUser(ud.getUserById(userId));
				session.save(mm);
			}
			session.flush();
			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int rateByMusicIdAndUserId(Integer musicId, Integer userId , Integer rate){
		int saveSuccess=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "from MusicMark mm where mm.music.musicId = :musicId and mm.user.userId = :userId";
			List<MusicMark> mms = session.createQuery(hql).
								setInteger("musicId", musicId).setInteger("userId", userId).list();
			MusicDao md = new MusicDao();
			UserDao ud = new UserDao();
			if(mms.size()!=0){
				MusicMark mm = mms.get(0);
				mm.setMusicGrade(rate);
				session.update(mm);
			}else{
				MusicMark mm = new MusicMark();
				mm.setMusicListen(0);
				mm.setMusicLike(0);
				mm.setMusic(md.getMusicById(musicId));
				mm.setMusicGrade(rate);
				mm.setUser(ud.getUserById(userId));
				session.save(mm);
			}

			tx.commit();
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();			
		}
		return saveSuccess;
	}
	
	public int save(MusicMark mm){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(mm);
			//session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;		
	}
	
	public int update(MusicMark mm){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(mm);
			tx.commit();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return -1;			
	}
	
	@SuppressWarnings("unchecked")
	public double getAverageGrade(int musicId){
		Session session = TribusHibernateSessionFactory.currentSession();
		int gradeSum = 0;
		try{
			String hql = "from MusicMark where musicId = :musicId";
			List<MusicMark> mms = session.createQuery(hql).
								setInteger("musicId", musicId).list();
			for(int i=0; i< mms.size(); i++){
				gradeSum+= mms.get(i).getMusicGrade();
			}
			return gradeSum/mms.size();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getLikeMusicByUseId(int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		//List<Integer> ms = new ArrayList<Integer>();
		List<Integer> ms = null;
		try{
			String hql = "select mm.music.musicId from MusicMark as mm where mm.user.userId = :userId and mm.musicLike= 1";
			ms = session.createQuery(hql).
								setInteger("userId", userId).list();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return ms;
	}
	
	@SuppressWarnings("unchecked")
	public int getGradeByMusicIdAndUseId(int musicId, int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		Integer grade=-1;
		try{
			String hql = "select mm.musicGrade from MusicMark as mm where mm.user.userId = :userId and mm.music.musicId= :musicId";
			List<Integer> g = session.createQuery(hql).setInteger("userId", userId).setInteger("musicId", musicId).list();
			grade = g.get(0);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return grade;
	}
	
	public void deleteMusicMark(MusicMark mm){		
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction( );
			try {
				session.delete( mm );
				tx.commit( );
			} catch ( Exception e ) {
				e.printStackTrace();
				tx.rollback( );			
			}		
	}	public static void main(String args[]){
		MusicMarkDao mmd = new MusicMarkDao();
		mmd.getLikeMusicByUseId(2);
		//MusicMark mm = new MusicMark();
		System.out.println(mmd.getGradeByMusicIdAndUseId(4, 1));
		//System.out.println(mmd.getAverageGrade(3));
/*		UserDao ud = new UserDao();
		User u = ud.getUserById(1);
		MusicDao md = new MusicDao();
		Music m = md.getMusicById(1);
		mm.setUser(u);
		mm.setMusicMarkId(1);
		mm.setMusic(m);
		mmd.save(mm);*/
	}
}
