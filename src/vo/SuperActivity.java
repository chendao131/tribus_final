package vo;

import model.Activity;

public class SuperActivity {
private Activity activity;
private String followed;
public Activity getActivity() {
	return activity;
}
public void setActivity(Activity activity) {
	this.activity = activity;
}
public String getFollowed() {
	return followed;
}
public void setFollowed(String followed) {
	this.followed = followed;
}
public String getJoined() {
	return joined;
}
public void setJoined(String joined) {
	this.joined = joined;
}
public String getOwner() {
	return owner;
}
public void setOwner(String owner) {
	this.owner = owner;
}
private String joined;
private String owner;
}
