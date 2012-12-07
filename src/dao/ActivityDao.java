package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Date;
import java.util.List;

import model.Activity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ActivityDao {
	public boolean addActivity(Activity activity) {// ��ӻ
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction();
			session.save(activity);
			tx.commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateActivity(Activity activity) {// ���»
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

	public boolean delActivityById(int activityId) {// ͨ��IDɾ��
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction();
			session.delete(TribusHibernateSessionFactory.currentSession().get(
					Activity.class, activityId));
			tx.commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Activity getActivityById(int activityId) {
		return (Activity) TribusHibernateSessionFactory.currentSession().get(
				Activity.class, activityId);
	}
	
	public String getActivityNameById(int activityId) {
		return  TribusHibernateSessionFactory.currentSession().createSQLQuery("select activityName from activity where activityId = :id").
		setInteger("id", activityId).uniqueResult().toString();				
	}

	public List<Activity> getAllActivity() {
		String hql = "from Activity";
		return TribusHibernateSessionFactory.currentSession().createQuery(hql)
				.list();
	}


	public List<Activity> getActivityByCondition(String activityName,
			String activityCity, Date activityStartTime,
			Date activityFinishedTime) {
		// ������
		String hql = "from Activity where  1=1";
		if (activityName != null) {
			hql = hql + " and activityName like '%" + activityName + "%'";
		}
		if (activityCity != null) {
			hql = hql + " and activityCity = '" + activityCity + "'";
		}
		if (activityStartTime != null) {
			hql = hql + " and activityStartTime > '" + activityStartTime + "'";
		}
		if (activityFinishedTime != null) {
			hql = hql + " and activityFinishedTime > '" + activityFinishedTime
					+ "'";
		}

		return TribusHibernateSessionFactory.currentSession().createQuery(hql)
				.list();
	}

	public List<Activity> getHottestActivity() {

		String sql = "select * from activity order by activityPriority desc limit 5 ";
		return TribusHibernateSessionFactory.currentSession().createSQLQuery(sql).addEntity(Activity.class).list();
	}

	public List<String> getTopTribusCity() {
		String sql = "select activityCity from activity a group by a.activityCity order by count(*) desc limit 4";
		return TribusHibernateSessionFactory.currentSession().createSQLQuery(sql).list();
	}
	public List<Activity> getActivityByAbstractCondition(String condition){//����ģ������������ֶν�������
		
		
		String sql="select * from activity where activityName like '%"+ condition+"%' or activityCity ='"+condition+"' or userId = '"+condition+"'";
		return TribusHibernateSessionFactory.currentSession().createSQLQuery(sql)
		.addEntity(Activity.class).list();
	}
	public List<Activity> getActivityByClassifiedId(Integer classifiedId){//ͨ���ǩid�ҳ��Ӧ� 
		
		
		String sql="select * from activity where classifiedId ='"+ classifiedId+"'";
		return TribusHibernateSessionFactory.currentSession().createSQLQuery(sql)
		.addEntity(Activity.class).list();
	}
public String getMaxId(){
	String sql="select max(activityId) from activity";
	return TribusHibernateSessionFactory.currentSession().createSQLQuery(sql).uniqueResult().toString();

	
}
}
