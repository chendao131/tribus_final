package dao;

import hibernate.TribusHibernateSessionFactory;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Activity;
import model.ActivityAlbum;

import model.ActivityPic;

public class ActivityPicDao {
	public void addActivityPic(ActivityPic activityPic) {// 增加活动照片
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(activityPic);
		tx.commit();
	}

	public void updateActivityPic(ActivityPic activityPic) {// 更新活动照片
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.update(activityPic);
		tx.commit();
	}

	public void delActivityPicById(int activityPicId) {// 通过活动照片ID删除活动照片
		Session session = TribusHibernateSessionFactory.currentSession();
		Transaction tx = session.beginTransaction();
		session.delete(TribusHibernateSessionFactory.currentSession().get(
				ActivityPic.class, activityPicId));
		tx.commit();
	}

	public List<Activity> getAllActivityPic() {// 找出所有活动照片
		String hql = "from ActivityPic";
		return TribusHibernateSessionFactory.currentSession().createQuery(hql)
				.list();

	}
	public List<ActivityPic> getActivityPicByCondition(Integer picId,
			Integer activityAlbumId) {
		// 组合语句
		String hql = "from ActivityPic where  1=1";
		if (picId != 0) {
			hql = hql + " and picId = '" + picId + "'";
		}
		if (activityAlbumId != 0) {
			hql = hql + " and albumId = '" + activityAlbumId + "'";
		}

		return TribusHibernateSessionFactory.currentSession().createQuery(hql)
				.list();
	}


}
