package vo;

public class SinglePageReview {
	private Integer userId;
	private String userPic;
	private String userName;
	private Integer commentId;
	private String commentTitle;
	private String commentContent;
	private Integer userRate;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getUserRate() {
		return userRate;
	}
	public void setUserRate(Integer userRate) {
		this.userRate = userRate;
	}

}
