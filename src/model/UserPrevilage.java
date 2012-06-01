package model;

public class UserPrevilage {
	private Integer id;
	private Integer userId;
	private Integer activityId;
	private Integer previlage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getPrevilage() {
		return previlage;
	}

	public void setPrevilage(Integer previlage) {
		this.previlage = previlage;
	}
}
