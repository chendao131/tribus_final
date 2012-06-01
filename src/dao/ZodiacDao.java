package dao;

import hibernate.TribusHibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Zodiac;

public class ZodiacDao {
	
	public int save(Zodiac z){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( z );
			//session.flush();
			tx.commit( );			
			return 1;		
			
		} catch ( Exception e ) {
			tx.rollback( );			
		}
		return -1;		
	}
	
	@SuppressWarnings("unchecked")
	public Zodiac getZodiacByName(String name){
		Zodiac z = null;
		try{
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from Zodiac as z where z.zodiacName=:name";
			z = (Zodiac) session.createQuery(hql).setString("name", name).uniqueResult();			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return z;
	}
/*	public static void main(String args[]){
		ZodiacDao zd = new ZodiacDao();
		Zodiac z = new Zodiac();
		z.setZodiacName("z2");
		zd.save(z);
		
		ZodiacDao zd = new ZodiacDao();
		Zodiac z = zd.getZodiacByName("z1");
		System.out.println(z.getZodiacId());
	}*/
}
