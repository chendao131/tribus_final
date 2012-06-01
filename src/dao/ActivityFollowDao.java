package dao;

import java.util.List;

import model.Activity;
import model.ActivityFollow;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.TribusHibernateSessionFactory;

public class ActivityFollowDao {
	public Boolean addActivityFollow(ActivityFollow activityFollow) {// ������ע��

		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction();
			session.save(activityFollow);
			tx.commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateActivity(Activity activity) {// ���¹�ע�߶���
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction();
			session.update(activity);
			tx.commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ActivityFollow> getActivityFollowByCondition(
			Integer activityId, Integer userId) {// ��������ѯ������ĳ���id������й�ע��id�����ĳ����ע��id������ע�����лid
		// ������
		String hql = "from ActivityFollow where  1=1";
		if (activityId != 0) {
			hql = hql + " and activityId = '" + activityId + "'";
		}
		if (userId != 0) {
			hql = hql + " and userId = '" + userId + "'";
		}

		return TribusHibernateSessionFactory.currentSession().createQuery(hql)
				.list();

	}
	public Boolean delActivityFollow(ActivityFollow activityFollow) {// ɾ����ע��

		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction();
			session.delete(activityFollow);
			tx.commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}
}
