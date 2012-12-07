package dao;

import java.util.List;

import hibernate.TribusHibernateSessionFactory;

import model.Book;
import model.StarType;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StarTypeDao {
	public int save(StarType st){
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction( );
		try {
			session.save( st );
			session.flush();
			tx.commit( );			
			return 1;		
			
		} catch ( Exception e ) {
			tx.rollback( );			
		}
		return -1;		
	}
	@SuppressWarnings("unchecked")
	public StarType getTypeByName(String name){
		StarType st = new StarType();
		try{
			Session session = TribusHibernateSessionFactory.currentSession();
			String hql = "from StarType as st where st.typeName=:name";
			List<StarType> sts = session.createQuery(hql).setString("name", name)
					.list();
			st = sts.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return st;
	}
	
	@SuppressWarnings("unchecked")
	public StarType getStarTypeById(Integer typeId){
		StarType st = null;
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			String sql = "from StarType where typeId=:typeId";
			List<StarType> sts = session.createQuery(sql).setInteger("typeId", typeId)
					.list();
			st = sts.get(0);
			//System.out.println(b.getBookName());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return st;		
	}
	public static void main(String args[]){
		StarTypeDao std = new StarTypeDao();
		//StarType st = std.getTypeByName("actor");
		StarType st = std.getStarTypeById(2);
		System.out.println(st.getTypeName()+"*"+st.getTypeId());
	}
}
