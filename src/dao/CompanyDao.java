package dao;

import java.util.List;

import hibernate.TribusHibernateSessionFactory;

import model.Company;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao {
	public int addCompany(Company c){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( c );
			session.flush();
			tx.commit( );			
			return 1;		
			
		} catch ( Exception e ) {
			tx.rollback( );			
		}
		return -1;		
	}
	
	@SuppressWarnings("unchecked")
	public Company getCompanyByName(String name){
		Company c = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
/*			String hql = "from Company as c where c.companyName=:name";
			List<Company> cs = session.createSQLQuery(hql).setString("name", name).list();*/
			String hql = "from Company as c where c.companyName=:name";
			List<Company> cs = session.createQuery(hql).setString("name", name).list();
			c = cs.get(0);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return c;	
	}
	
/*	public static void main(String args[]){
		CompanyDao cd = new CompanyDao();
		Company c = cd.getCompanyByName("google");
		System.out.println(c.getCompanyId());
	}*/
}
