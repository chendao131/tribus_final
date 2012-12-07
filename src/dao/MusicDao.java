package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Book;
import model.Movie;
import model.Music;
import model.Starring;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import vo.MovieMarkVo;
import vo.MusicMarkVo;

public class MusicDao {
	@SuppressWarnings("unchecked")
	public List<Music> searchMusicByName(String name){
		String hql ="select m from Music m where lower(m.musicName) like :name order by m.musicPublishDate desc";
		List<Music> musics = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			musics = session.createQuery(hql).setString("name", "%"+name.toLowerCase()+"%").list();
		} catch (Exception e){
			e.printStackTrace();
		}
		return musics;
	}
	
	public int save(Music m) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(m);
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
	
	public List<MusicMarkVo> getMusicAndGradeByUserId(int id){
		String sql = "select m.musicId,m.musicName,mm.musicGrade from " +
				" music m, music_mark mm, user_account u, user_profile up" +
		" where m.musicId = mm.musicId and u.userId = mm.userId and up.userId = u.userId" +
		" and u.userId = ? ";

		List<MusicMarkVo> l_music_vo = new ArrayList<MusicMarkVo>();
		
		Session session = TribusHibernateSessionFactory.currentSession();
		List l = session.createSQLQuery(sql).setInteger(0, id).list();
		if( l!=null ){
			Iterator itr = l.iterator();
			while(itr.hasNext()){
				
				Object[] obj = (Object[])itr.next();
				MusicMarkVo bmv = new MusicMarkVo();

				//bmv.setMovieId(Integer.parseInt(obj[0].toString()));
				bmv.setMusicId(Integer.parseInt(obj[0].toString()));
				
				bmv.setMusicName(obj[1].toString());
				//bmv.setMovieNameAlias(obj[1].toString());
				
				bmv.setMusicGrade(Integer.parseInt(obj[2].toString()));
				//bmv.setMovieGrade(Integer.parseInt(obj[2].toString()));
				
				l_music_vo.add(bmv);
				
			}
		}
			return l_music_vo;
	}
	
	
	public List<Music> getMusicsWantedByUserId(int id){
		String sql = "select m.* from music m, music_mark mm, user_account u" +
		" where m.musicId = mm.musicId and u.userId = mm.userId" +
		" and mm.musicListen = 1 and u.userId = ?";
		Session session = TribusHibernateSessionFactory.currentSession();
		return session.createSQLQuery(sql).addEntity(Music.class).setInteger(0, id).list();		
	}
	
	
	public List<Music> getMusicsListenedByUserId(int id){
		String sql = "select m.* from music m, music_mark mm, user_account u" +
		" where m.musicId = mm.musicId and u.userId = mm.userId" +
		" and mm.musicListen = 2 and u.userId = ?";
		Session session = TribusHibernateSessionFactory.currentSession();
		return session.createSQLQuery(sql).addEntity(Music.class).setInteger(0, id).list();		
	}
	
	
	@SuppressWarnings("unchecked")
	public Music getMusicById(Integer musicId){
		Music m = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from Music where musicId=:musicId";
			List<Music> ms = session.createQuery(sql).setInteger("musicId", musicId)
					.list();
			m = ms.get(0);
			//System.out.println(b.getBookName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return m;		
	}
	
	public int update(Music m) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(m);
			//session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public List<Music> getRecentHotMusic(){
		List<Music> hotMusics = new ArrayList<Music>();
		int id=31;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			for(int i = 0; i< 6; i++){
				String sql = "from Music where musicId=" + id++;
				List<Music> ms = session.createQuery(sql).list();
				hotMusics.add(ms.get(0));
			}
			//System.out.println(b.getBookName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return hotMusics;
	}
	
	@SuppressWarnings("unchecked")
	public Music getMusicByName(String musicName){
		Music m = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from Music where musicName=:musicName";
			List<Music> ms = session.createQuery(sql).setString("musicName", musicName)
					.list();
			m = ms.get(0);
			//System.out.println(b.getBookName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return m;		
	}
	
	public List<Music> getMusicByCondition(Music m){
		if(m==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(Music.class);
			if(m.getMusicId() != 0 ){c.add(Restrictions.eq("musicId",m.getMusicId() ));}
			if(m.getMusicAlias() != null){c.add(Restrictions.eq("musicAlias", m.getMusicAlias()));}
			if(m.getMusicClassified() != null){c.add(Restrictions.eq("musicClassified", m.getMusicClassified()));}
			if(m.getMusicCode() != null){c.add(Restrictions.eq("musicCode", m.getMusicCode()));}
									
			List<Music> ms = c.list();  														
			return ms;
			
		} catch ( Exception e ) {
			e.printStackTrace();			
		}				
		return null;			
	}
	
	@SuppressWarnings("unchecked")
	public List<Music> getMusicsByUserId(int id){
		String sql = "select m.* from music m, music_mark mm, user_account u where m.musicId = mm.musicId and" +
				" u.userId = mm.userId and mm.userId = ?";				
		List<Music> bs = new ArrayList<Music>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			bs = session.createSQLQuery(sql).addEntity(Music.class).setInteger(0, id).list();									
		} catch ( Exception e ) {			
			System.out.println(e.getMessage());
		}
		return bs;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Music> searchMusicByTag(String musicTag){
		//String sql = "from Movie where tags.tagName = :movieTag";
		//String[] tags = {"Drama"};
		/*String hql = "select m from Movie m "+
						"join m.tags t "+
						"where t.tagName in (:tags)";*/
		String hql = "select m from Music m "+
				"join m.tags t "+
				"where t.tagName like :musicTag";
		List<Music> musics = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			 musics = session.createQuery(hql).setString("musicTag", "%"+musicTag+"%").list();
		} catch (Exception e){
			e.printStackTrace();
		}
		return musics;
	}
	
	public static void main(String args[]){
		MusicDao md = new MusicDao();
		Music m = md.getMusicByName("Blue Christmas");
		System.out.println(m.getMusicName());
	}
}
