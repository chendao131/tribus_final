package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Date;
import java.util.List;

import model.Activity;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ActivityDao {
	public boolean addActivity(Activity activity) {// 增加活动
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

	public boolean updateActivity(Activity activity) {// 更新活动
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

	public boolean delActivityById(int activityId) {// 通过活动ID删除活动
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

	public Activity getActivityById(int activityId) {// 通过活动ID找出活动
		return (Activity) TribusHibernateSessionFactory.currentSession().get(Activity.class, activityId);
	}

	public List<Activity> getAllActivity() {// 找出所有活动
		String hql = "from Activity";
		return TribusHibernateSessionFactory.currentSession().createQuery(hql).list();
	}


	public List<Activity> getActivityByCondition(String activityName,
			String activityCity, Date activityStartTime,
			Date activityFinishedTime) {
		// 组合语句
		String hql = "from Activity where  1=1 ";
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
	public List<Activity> getActivityByAbstractCondition(String condition){//广义模糊搜索，从所有字段进行搜索
		
		
		String sql="select * from activity where activityName like '%"+ condition+"%' or activityCity ='"+condition+"' or userId = '"+condition+"'";
		return TribusHibernateSessionFactory.currentSession().createSQLQuery(sql)
		.addEntity(Activity.class).list();
	}
	public List<Activity> getActivityByClassifiedId(Integer classifiedId){//通过标签id找出对应活动 			
		String sql="select * from activity where classifiedId ='"+ classifiedId+"'";
		return TribusHibernateSessionFactory.currentSession().createSQLQuery(sql)
		.addEntity(Activity.class).list();
	}
//	public static void main(String a[]) {
//		ActivityDao a1=new ActivityDao();
//		System.out.println(a1.getActivityByCondition(null, "大日本", null, null).get(0).getActivityName());
//	}
}
