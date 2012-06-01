package vo;

public class MusicHomeReview {
	private Integer userId;
	private String userPic;
	private String userName;
	private Integer musicId;
	private String musicPic;
	private String commentTitle;
	private String commentContent;
	private double musicRating;
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
	public Integer getMusicId() {
		return musicId;
	}
	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}
	public String getMusicPic() {
		return musicPic;
	}
	public void setMusicPic(String musicPic) {
		this.musicPic = musicPic;
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
	public double getMusicRating() {
		return musicRating;
	}
	public void setMusicRating(double musicRating) {
		this.musicRating = musicRating;
	}
}
