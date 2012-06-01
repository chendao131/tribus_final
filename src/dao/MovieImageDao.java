package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.MovieImage;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovieImageDao {
	public int save(MovieImage mi) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(mi);
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
			//String sql = "select mi.imagePath from MovieImage as mi join MovieAlbum as ma where ma.movie.movieId=:movieId";
			//String sql = "from MovieImage as mi join MovieAlbum as ma where ma.movie.movieId=:movieId";
			//String sql = "select movie_image.imagePath from movie_image left join movie_album on movie_album.movieId= :movieId";
			String hql = "select albumId from MovieAlbum where movieId=:movieId";
			List<Integer> albums = session.createQuery(hql).setInteger("movieId", movieId).list();
			hql = "select imagePath from MovieImage where albumId=:albumId";
			images = session.createQuery(hql).setInteger("albumId", albums.get(0)).list();
			Iterator<Integer> iterator = albums.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next());
			}
			//String sql = "select movie_image.imagePath from movie_image left join movie_album on movie_album.albumId= 1";
			//List<MovieImage> mis = session.createQuery(sql).setInteger("movieId", movieId).list();
			//images = session.createSQLQuery(sql).setInteger("movieId", movieId).list();
			//images = session.createSQLQuery(sql).list();
			System.out.println("success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return images;		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getImageById(Integer imageId){
		List<String> images = new ArrayList<String>();
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			//String sql = "from MovieImage as mi join MovieAlbum as ma where ma.movieId=:movieId";
			String sql = "select mi.imagePath from MovieImage as mi where mi.imageId=:imageId";
			//List<MovieImage> mis = session.createQuery(sql).setInteger("movieId", movieId).list();
			images = session.createQuery(sql).setInteger("imageId", imageId).list();
			
			//System.out.println(b.getBookName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return images;		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getImageByAlbumId(Integer albumId){
		List<String> images = new ArrayList<String>();
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			//String sql = "from MovieImage as mi join MovieAlbum as ma where ma.movieId=:movieId";
			String sql = "select mi.imagePath from MovieImage as mi where mi.movieAlbum.albumId=:albumId";
			//List<MovieImage> mis = session.createQuery(sql).setInteger("movieId", movieId).list();
			images = session.createQuery(sql).setInteger("albumId", albumId).list();
			System.out.println("success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return images;		
	}
	public static void main(String args[]){
		MovieImageDao mid = new MovieImageDao();
		List<String> images = mid.getImageByMovieId(8380);
		Iterator<String> iterator = images.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		//System.out.println(mid.getImageById(1).get(0));
		//System.out.println(mid.getImageByAlbumId(1).get(0));
		//mid.getImageByMovieId(8380);
		//mad.save(mi);
	}
}
