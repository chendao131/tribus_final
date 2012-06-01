package dao;

import java.util.ArrayList;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.MovieMark;
import model.MusicMark;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MusicMarkDao {
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
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getLikeMusicByUseId(int userId){
		Session session = TribusHibernateSessionFactory.currentSession();
		List<Integer> ms = new ArrayList<Integer>();
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
	
	public static void main(String args[]){
		MusicMarkDao mmd = new MusicMarkDao();
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
