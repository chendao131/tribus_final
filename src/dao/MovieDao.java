package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hibernate.TribusHibernateSessionFactory;
import model.Book;
import model.Movie;
import model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vo.BookMarkVo;
import vo.MovieMarkVo;

@Repository
public class MovieDao {
	public int save(Movie m) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(m);
			session.flush();
			tx.commit();
			return 1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return -1;
	}
	
	public List<MovieMarkVo> getMovieAndGradeByUserId(int id){
		String sql = "select m.movieId,m.movieNameOriginal,mm.movieGrade from book b, book_mark bb, user_account u, user_profile up" +
		" where b.bookId = bb.bookId and u.userId = bb.userId and up.userId = u.userId" +
		" and u.userId = ? ";

		List<MovieMarkVo> l_movie_vo = new ArrayList<MovieMarkVo>();
		
		Session session = TribusHibernateSessionFactory.currentSession();
		List l = session.createSQLQuery(sql).setInteger(0, id).list();
		if( l!=null ){
			Iterator itr = l.iterator();
			while(itr.hasNext()){
				
				Object[] obj = (Object[])itr.next();
				MovieMarkVo bmv = new MovieMarkVo();

				bmv.setMovieId(Integer.parseInt(obj[0].toString()));
				
				bmv.setMovieNameAlias(obj[1].toString());
				
				bmv.setMovieGrade(Integer.parseInt(obj[2].toString()));
				
				l_movie_vo.add(bmv);
				
			}
		}
			return l_movie_vo;
	}
	/**
	 * movieLike
	 * movieWatch
	 * @param id
	 * @return
	 */
	public List<Movie> getMoviesWantedByUserId(int id){
		String sql = "select m.* from movie m, movie_mark mm, user_account u " +
				" where m.movieId = mm.movieId and u.userId = mm.userId " +
				" and mm.movieLike = 1 and u.userId = ?";
		Session session = TribusHibernateSessionFactory.currentSession();
		return session.createSQLQuery(sql).addEntity(Movie.class).setInteger(0, id).list();							
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Movie> getMoviesWatchedByUserId(int id){
		String sql = "select m.* from movie m, movie_mark mm, user_account u " +
		" where m.movieId = mm.movieId and u.userId = mm.userId " +
		" and mm.movieWatch = 1 and u.userId = ?";
		Session session = TribusHibernateSessionFactory.currentSession();
		return session.createSQLQuery(sql).addEntity(Movie.class).setInteger(0, id).list();
	}

	@SuppressWarnings("unchecked")
	public Movie getMovieByNameOriginal(String movieNameOriginal) {
		Movie m = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from Movie where movieNameOriginal=:movieNameOriginal";
			List<Movie> ms = session.createQuery(sql).setString("movieNameOriginal", movieNameOriginal)
					.list();
			m = ms.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return m;
	}
	
	@SuppressWarnings("unchecked")
	public Movie getMovieById(int movieId) {
		Movie m = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from Movie where movieId=:movieId";
			List<Movie> ms = session.createQuery(sql).setInteger("movieId", movieId)
					.list();
			m = ms.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return m;
	}
	
	
	public Movie getMovieByCondition(Movie m){
		if(m==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(Movie.class);
			if(m.getMovieId() !=0){c.add(Restrictions.eq("movieId", m.getMovieId()));}
			if(m.getMovieNameOriginal() !=null){c.add(Restrictions.eq("movieNameOriginal", m.getMovieNameOriginal()));}			
									
			Movie mo = (Movie)c.uniqueResult();  														
			return mo;
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}

	
	
	public List<Movie> getMoviesByCondition(Movie m){
		if(m==null){
			return null;
		}
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {			
								
			Criteria c = session.createCriteria(Movie.class);
			if(m.getMovieId() !=0){c.add(Restrictions.eq("movieId", m.getMovieId()));}
			if(m.getMovieNameOriginal() !=null){c.add(Restrictions.eq("movieNameOriginal", m.getMovieNameOriginal()));}			
									
			List<Movie>mo = c.list();  														
			return mo;
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());			
		}				
		return null;
	}
	
	
	
	
	public int updateMovieNameOriginal(Movie movie){
		System.out.println(movie.getMovieId()+"*"+movie.getMovieNameOriginal());
		int result=0;
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();		
		
		try {
			session.update(movie);
			//session.flush();
			tx.commit();
			result = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			tx.rollback();
		}
		return result;
	}
	
	public List<Movie> getMoviesByUserId(int id){
		String sql = "select m.* from movie m, movie_mark bm, user_account u where m.movieId = bm.movieId and" +
				" u.userId = bm.userId and bm.userId = ?";				
		List<Movie> bs = new ArrayList<Movie>();		
		Session session = TribusHibernateSessionFactory.currentSession();		
		try {
			bs = session.createSQLQuery(sql).addEntity(Movie.class).setInteger(0, id).list();									
		} catch ( Exception e ) {			
			System.out.println(e.getMessage());
		}
		return bs;		
	}
	
	public int update(Movie m) {
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
	public List<Movie> getRecentHotMovie(){
		String sql = "from Movie where movieDate>='2010-10-21'";
		List<Movie> recentHotMovies = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			 recentHotMovies = session.createQuery(sql).list();
			 System.out.println("success");
		} catch (Exception e){
			e.printStackTrace();
		}
		return recentHotMovies;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> getMovieByTag(String movieTag){
		//String sql = "from Movie where tags.tagName = :movieTag";
		//String[] tags = {"Drama"};
		/*String hql = "select m from Movie m "+
						"join m.tags t "+
						"where t.tagName in (:tags)";*/
		String hql = "select m from Movie m "+
				"join m.tags t "+
				"where t.tagName = :movieTag";
		List<Movie> movies = null;
		Session session = TribusHibernateSessionFactory.currentSession();
		try{
			 movies = session.createQuery(hql).setString("movieTag",movieTag).list();
			 System.out.println("success");
		} catch (Exception e){
			e.printStackTrace();
		}
		return movies;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> searchMovieByName(String name){
		//String sql = "from Movie where tags.tagName = :movieTag";
		//String[] tags = {"Drama"};
		/*String hql = "select m from Movie m "+
						"join m.tags t "+
						"where t.tagName in (:tags)";*/
		String hql = "from Movie";
		Session session = TribusHibernateSessionFactory.currentSession();
		List<Movie> allMovies = new ArrayList<Movie>();
		List<Movie> result = new ArrayList<Movie>();
		try{
			allMovies = session.createQuery(hql).list();
			 Iterator<Movie> iterator = allMovies.iterator();
			 while(iterator.hasNext()){
				 Movie m = iterator.next();
				 if(m.getMovieNameOriginal().indexOf(name)!=-1){
					 result.add(m);
				 }
			 }
			 System.out.println("success");
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String args[]){
		MovieDao md = new MovieDao();
		List<Movie> movies = md.searchMovieByName("Money");
		System.out.println(movies.size());
/*		List<Movie> recentHotMovies = md.getRecentHotMovie();
		Iterator<Movie> iterator = recentHotMovies.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next().getMoviePic());
		}*/

		//md.updateMovieNameOriginal(1, "m7");
		
/*		Movie s = md.getMovieByNameOriginal("m1");
		
		System.out.println(s);
		
		Movie m = new Movie();
		m.setMovieNameOriginal("m3");
		m.setMovieNameEn("m3");
		m.setMovieNameChn("m3");
		m.setMovieNameAlias("m3");
		m.setMovieIMDB(2);
		
		StarringDao sd = new StarringDao();
		Set<Starring> ss = new HashSet<Starring>();
		Starring s1 = sd.getStarringByName("s2");
		Starring s2 = sd.getStarringByName("s1");
		ss.add(s1); ss.add(s2);
		m.setStars(ss);
		
		MovieTagDao mtd = new MovieTagDao();
		Set<MovieTag> mts = new HashSet<MovieTag>();
		MovieTag mt = mtd.getMovieTagByName("tag2");
		mts.add(mt);
		m.setTags(mts);
		
		m.setMovieWeb("m3");
		m.setMovieRegion("m3");
		m.setMovieLanguage("m3");
		m.setMovieDate(Date.valueOf("1988-01-31"));
		m.setMovieTime(Integer.parseInt("100"));
		m.setMovieDemo("m3");
		m.setMovieBrief("m3");
		
		UserDao ud = new UserDao();
		User u = ud.getUserById(1);
		m.setUser(u);
		
		m.setMovieId(3);
		MovieDao md = new MovieDao();
		System.out.println(md.save(m));*/
	}

}
