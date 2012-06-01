package vo;

import java.util.Date;

public class ActivityGoingMax {
private int activityId;
private String picPath;
private int numbers;
private String activityName;
private Date activityStartTime;
private String userName;
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getActivityName() {
	return activityName;
}
public void setActivityName(String activityName) {
	this.activityName = activityName;
}
public Date getActivityStartTime() {
	return activityStartTime;
}
public void setActivityStartTime(Date activityStartTime) {
	this.activityStartTime = activityStartTime;
}
public int getActivityId() {
	return activityId;
}
public void setActivityId(int activityId) {
	this.activityId = activityId;
}
public String getPicPath() {
	return picPath;
}
public void setPicPath(String picPath) {
	this.picPath = picPath;
}
public int getNumbers() {
	return numbers;
}
public void setNumbers(int numbers) {
	this.numbers = numbers;
}
}
