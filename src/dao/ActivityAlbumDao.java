package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Activity;
import model.ActivityAlbum;

public class ActivityAlbumDao {
	public void addActivityAlbum(ActivityAlbum activityAlbum) {// 创建相册
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(activityAlbum);
		tx.commit();
	}

	public void updateActivityAlbum(ActivityAlbum activityAlbum) {// 更新相册
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.update(activityAlbum);
		tx.commit();
	}

	public void delAlubumById(int albumId) {// 通过相册ID删除相册
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.delete(TribusHibernateSessionFactory.currentSession().get(
				ActivityAlbum.class, albumId));
		tx.commit();
	}

	public List<ActivityAlbum> getAllActivityAlbum() {// 找出所有相册
		String hql = "from ActivityAlbum";
		return TribusHibernateSessionFactory.currentSession().createQuery(hql)
				.list();
	}

	public List<ActivityAlbum> getActivityAlbumByCondition(Integer activityId,
			Integer activityAlbumId) {
		// 组合语句
		String hql = "from ActivityAlbum where  1=1";
		if (activityId != 0) {
			hql = hql + " and activityId = '" + activityId + "'";
		}
		if (activityAlbumId != 0) {
			hql = hql + " and albumId = '" + activityAlbumId + "'";
		}

		return TribusHibernateSessionFactory.currentSession().createQuery(hql)
				.list();
	}

	public Integer getMaxAlbumId() {

		String sql = "select max(albumId) from activity_album";

		return Integer.parseInt(TribusHibernateSessionFactory.currentSession().createSQLQuery(sql).uniqueResult().toString());
	}
}
