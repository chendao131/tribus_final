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
	public void addActivityClassified(ActivityClassified activityClassified) {// ���ӻ����
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(activityClassified);
		tx.commit();

	}

	public void updateActivityClassified(ActivityClassified activityClassified) {// ���»�ַ���
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.update(activityClassified);
		tx.commit();
	}

	public void delActivityClassifiedById(int activityClassifiedId) {// ͨ�������IDɾ�������
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.delete(TribusHibernateSessionFactory.currentSession().get(
				ActivityClassified.class, activityClassifiedId));
		tx.commit();
	}

	public List<ActivityClassified> getAllActivityClassified() {// �ҳ����л����
		String hql = "from ActivityClassified";
		return TribusHibernateSessionFactory.currentSession().createQuery(
				hql).list();

	}
	public Integer getClassifiedIdByTag(String tagName) {// ͨ����ǩ���ҳ���Ӧ����Id
		String hql = "from ActivityClassified where classifiedName='"+tagName+"'";
		ActivityClassified ac=(ActivityClassified)TribusHibernateSessionFactory.currentSession().createQuery(
				hql).uniqueResult();
		return ac.getClassfiedId();

	}
	public String getClassifiedTagById(Integer classifiedId) {// ͨ����Ӧ����Id�ҳ���ǩ��
		String hql = "from ActivityClassified where classfiedId='"+classifiedId+"'";
		ActivityClassified ac=(ActivityClassified)TribusHibernateSessionFactory.currentSession().createQuery(
				hql).uniqueResult();
		return ac.getClassifiedName();

	}
}
