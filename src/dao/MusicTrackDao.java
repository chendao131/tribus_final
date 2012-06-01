package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.Movie;
import model.MusicTrack;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MusicTrackDao {
	public int save(MusicTrack mt) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(mt);
			session.flush();
			tx.commit();
			session.clear();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public List<MusicTrack> getMusicTrackByMusicId(int musicId){
		String sql = "from MusicTrack as mt where mt.music.musicId= :musicId";
		List<MusicTrack> mts = new ArrayList<MusicTrack>();
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			mts = session.createQuery(sql).setInteger("musicId", musicId).list();
			 System.out.println("success");
		} catch (Exception e){
			e.printStackTrace();
		}
		return mts;
	}
	public static void main(String args[]){
		MusicTrackDao mtd = new MusicTrackDao();
		List<MusicTrack> mts = mtd.getMusicTrackByMusicId(4);
		Iterator<MusicTrack> iterator =  mts.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next().getTrackName());
		}
	}
}
