package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Activity;
import model.ActivityFollow;
import model.ActivityGoing;

import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.ActivityGoingTempResult;

import hibernate.TribusHibernateSessionFactory;

public class ActivityGoingDao {
	public Boolean addActivityGoing(ActivityGoing activityGoing) {// ����������

		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction();
			session.save(activityGoing);
			tx.commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ActivityGoing> getActivityGoingByCondition(Integer activityId,
			Integer userId) {// ��������ѯ������ĳ���id������в�����id�����ĳ��������id������������лid
		// ������
		String hql = "from ActivityGoing where  1=1";
		if (activityId != 0) {
			hql = hql + " and activityId = '" + activityId + "'";
		}
		if (userId != 0) {
			hql = hql + " and userId = '" + userId + "'";
		}

		return TribusHibernateSessionFactory.currentSession().createQuery(hql)
				.list();

	}

	public Boolean delActivityGoing(ActivityGoing activityGoing) {// ɾ��������

		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction();
			session.delete(activityGoing);
			tx.commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ActivityGoingTempResult> getActivityGoingAsNumbers() {
		String sql = "select activityId,count(activityId) as gp from activity_going group by activityId order by gp desc limit 3";
		List activityGoingTempResultList = new ArrayList();
		Iterator i = TribusHibernateSessionFactory.currentSession()
				.createSQLQuery(sql).list().iterator();
		while (i.hasNext()) {
			ActivityGoingTempResult a = new ActivityGoingTempResult();


			Object[] objs = (Object[]) i.next();
			a.setActivityId((Integer)objs[0]);
			a.setNumber(((BigInteger) objs[1]).intValue());

			activityGoingTempResultList.add(a);

		}
		return activityGoingTempResultList;
	}

	public int getActivityGoingNumbers(int activityId) {

		String sql = "select count(*) from activity_going where activity_going.activityId='"
				+ activityId + "'";
		Integer numbers=((BigInteger)TribusHibernateSessionFactory.currentSession()
				.createSQLQuery(sql).uniqueResult()).intValue();
return numbers;
	}


}
