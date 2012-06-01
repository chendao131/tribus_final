package vo;

import java.util.List;

import model.Activity;
import model.User;

public class MyActivity {
	private Activity activity;
	private User user;
	private List<UserComments> userComments;
	private String joined;
	private String followed;
	private String owner;
	public String getJoined() {
		return joined;
	}

	public void setJoined(String joined) {
		this.joined = joined;
	}

	public String getFollowed() {
		return followed;
	}

	public void setFollowed(String followed) {
		this.followed = followed;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}



	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserComments> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<UserComments> userComments) {
		this.userComments = userComments;
	}
}
