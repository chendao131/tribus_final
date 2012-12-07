package model;

import model.Activity;

public class SuperActivity {
private Activity activity;
private String followed;
private String joined;
private String owner;
private String wish;
private String tribus;
public Activity getActivity() {
	return activity;
}
public void setActivity(Activity activity) {
	this.activity = activity;
}
public String getWish() {
	return wish;
}
public void setWish(String wish) {
	this.wish = wish;
}
public String getTribus() {
	return tribus;
}
public void setTribus(String tribus) {
	this.tribus = tribus;
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

}
