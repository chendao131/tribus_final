package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Date;
import java.util.List;
import model.Activity;
import model.ActivityComment;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ActivityCommentDao {
	public boolean addActivityComment(ActivityComment activityComment) {// 增加留言
		try {
			Session session = TribusHibernateSessionFactory.currentSession();
			Transaction tx = session.beginTransaction();
			session.save(activityComment);
			tx.commit();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<ActivityComment> getActivityCommentByCondition(int activityId,
			int userId) {// 提取留言
		// 组合语句
		String hql = "from ActivityComment where  1=1 ";
		if (activityId != 0) {
			hql = hql + " and activityId = '" + activityId + "'";
		}
		if (userId != 0) {
			hql += "and userId='" + userId + "'";
		}
		String hql1=" order by commentDate desc";
		return TribusHibernateSessionFactory.currentSession().createQuery(hql+ hql1)
				.list();
	}

	public List<ActivityComment> getActivityCommentRandom() {// 随机提取留言4条
		String sql = "select * from activity_comment order by RAND() limit 4";
		return TribusHibernateSessionFactory.currentSession().createSQLQuery(
				sql).addEntity(ActivityComment.class).list();
	}

	public List<String> getActivityDistinct(int userId)// 提取distinct活动
	{
		String sql = "select distinct activityId from activity_comment where userId='"
				+ userId + "'";
		return TribusHibernateSessionFactory.currentSession().createSQLQuery(
				sql).list();
	}
}
