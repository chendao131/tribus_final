package dao;

import hibernate.TribusHibernateSessionFactory;
import java.util.List;

import model.UserPrevilage;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserPrevilageDao {

	public boolean add(UserPrevilage userPrevilage) {
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(userPrevilage);
			tx.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return false;
	}

	public List<UserPrevilage> getAllUsersByActivityId(int activityId) {

		Session session = TribusHibernateSessionFactory.currentSession();

		return session.createSQLQuery("select * from user_previlage where activityId = ?").addEntity(
				UserPrevilage.class).setInteger(
						0, activityId).list();

	}

}