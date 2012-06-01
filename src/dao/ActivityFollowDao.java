package dao;

import java.util.List;

import model.Activity;
import model.ActivityFollow;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.TribusHibernateSessionFactory;

public class ActivityFollowDao {
	public Boolean addActivityFollow(ActivityFollow activityFollow) {// 创建关注者

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

	public boolean updateActivity(Activity activity) {// 更新关注者对象
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
			Integer activityId, Integer userId) {// 依条件查询，根据某个活动id查出所有关注者id或根据某个关注者id查出其关注的所有活动id
		// 组合语句
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
	public Boolean delActivityFollow(ActivityFollow activityFollow) {// 删除关注者

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
