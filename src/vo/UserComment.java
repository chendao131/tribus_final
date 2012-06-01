package vo;

import java.util.Date;

import model.Activity;

public class UserComment {
	private Integer userId;
	private String commentContent;
	private Date commentDate;
	private String activityName;
	private int numbers;
	private Date startTime;
	private String city;
	private String street;
	private String userPic;
	private String userName;
	private Activity activity;
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}



	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
}
