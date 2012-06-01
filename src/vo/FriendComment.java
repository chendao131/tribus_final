package vo;

import java.util.List;

import model.Activity;
import model.ActivityComment;

public class FriendComment {
	private Activity activity;
	private List<UserComments> userComments;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public List<UserComments> getUserComments() {
		return userComments;
	}

	public void setUserComment(List<UserComments> userComments) {
		this.userComments = userComments;
	}

}
