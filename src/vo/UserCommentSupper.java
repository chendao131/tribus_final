package vo;

import java.util.List;

import model.Activity;

public class UserCommentSupper {
private Activity activity;
private List<UserComment> userComment;
public Activity getActivity() {
	return activity;
}
public void setActivity(Activity activity) {
	this.activity = activity;
}
public List<UserComment> getUserComment() {
	return userComment;
}
public void setUserComment(List<UserComment> userComment) {
	this.userComment = userComment;
}

}
