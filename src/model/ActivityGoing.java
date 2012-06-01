package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "activity_going")
public class ActivityGoing {
	private Integer goingId;
	private Integer activityId;
	private Integer userId;
	private Integer goingStatus;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getGoingId() {
		return goingId;
	}

	public void setGoingId(Integer goingId) {
		this.goingId = goingId;
	}

	public Integer getGoingStatus() {
		return goingStatus;
	}

	public void setGoingStatus(Integer goingStatus) {
		this.goingStatus = goingStatus;
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
