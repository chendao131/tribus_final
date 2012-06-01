package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "activity_follow")
public class ActivityFollow {
	private Integer followId;
	private Integer activityId;
	private Integer userId;
	private Integer followStatus;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getFollowId() {
		return followId;
	}

	public void setFollowId(Integer followId) {
		this.followId = followId;
	}

	public Integer getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
