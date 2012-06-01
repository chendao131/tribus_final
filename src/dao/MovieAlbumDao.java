package dao;

import java.util.ArrayList;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.MovieAlbum;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovieAlbumDao {
	public int save(MovieAlbum ma) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(ma);
			session.flush();
			tx.commit();
			System.out.println("yes");
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getImageByMovieId(Integer movieId){
		List<String> images = new ArrayList<String>();
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			//String sql = "from MovieImage as mi join MovieAlbum as ma where ma.movieId=:movieId";
			String sql = "select mi.imagePath from MovieAlbum as ma join MovieImage as mi where ma.movie.movieId=:movieId";
			//List<MovieImage> mis = session.createQuery(sql).setInteger("movieId", movieId).list();
			images = session.createQuery(sql).setInteger("movieId", movieId).list();
			System.out.println("success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return images;		
	}
	public static void main(String args[]){
		MovieAlbumDao mad = new MovieAlbumDao();
		mad.getImageByMovieId(8380);
/*		MovieAlbum ma = new MovieAlbum();
		ma.setAlbumName("3"); ma.setAlbumDescription("3");
		mad.save(ma);*/
	}
}
