package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Activity;
import model.ActivityClassified;
import model.ActivityPic;

public class ActivityClassifiedDao {
	public void addActivityClassified(ActivityClassified activityClassified) {// 增加活动分类
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(activityClassified);
		tx.commit();

	}

	public void updateActivityClassified(ActivityClassified activityClassified) {// 更新活动分分类
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.update(activityClassified);
		tx.commit();
	}

	public void delActivityClassifiedById(int activityClassifiedId) {// 通过活动分类ID删除活动分类
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.delete(TribusHibernateSessionFactory.currentSession().get(
				ActivityClassified.class, activityClassifiedId));
		tx.commit();
	}

	public List<ActivityClassified> getAllActivityClassified() {// 找出所有活动分类
		String hql = "from ActivityClassified";
		return TribusHibernateSessionFactory.currentSession().createQuery(
				hql).list();

	}
	public Integer getClassifiedIdByTag(String tagName) {// 通过标签名找出对应分类Id
		String hql = "from ActivityClassified where classifiedName='"+tagName+"'";
		ActivityClassified ac=(ActivityClassified)TribusHibernateSessionFactory.currentSession().createQuery(
				hql).uniqueResult();
		return ac.getClassfiedId();

	}
	public String getClassifiedTagById(Integer classifiedId) {// 通过对应分类Id找出标签名
		String hql = "from ActivityClassified where classfiedId='"+classifiedId+"'";
		ActivityClassified ac=(ActivityClassified)TribusHibernateSessionFactory.currentSession().createQuery(
				hql).uniqueResult();
		return ac.getClassifiedName();

	}
}
