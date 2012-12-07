package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.Movie;
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
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;
	}
	
	public int saveImageByMovieIdAndPath(int movieId, String path) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(movieId);
		MovieImage mi = new MovieImage();
		mi.setImagePath(path);
		mi.setMovie(m);
		try {
			session.save(mi);
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
	public List<String> getImagesByMovieId(Integer movieId){
		List<String> imagePaths = new ArrayList<String>();
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "select mi.imagePath from MovieImage mi where mi.movie.movieId=:movieId";
			imagePaths = session.createQuery(hql).setInteger("movieId", movieId).list();
			System.out.println("success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return imagePaths;
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
		MovieImage mi = new MovieImage();
		MovieDao md = new MovieDao();
		Movie m = md.getMovieById(8380);
		mi.setImagePath("ddd");
		mi.setMovie(m);
		MovieImageDao mid = new MovieImageDao();
		mid.save(mi);
	}
}
