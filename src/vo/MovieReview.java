package vo;

public class MovieReview {
	private Integer userId;
	private String userPic;
	private String userName;
	private Integer movieId;
	private String moviePic;
	private String commentTitle;
	private String commentContent;
	private Integer movieRating;
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
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getMoviePic() {
		return moviePic;
	}
	public void setMoviePic(String moviePic) {
		this.moviePic = moviePic;
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
	public Integer getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(Integer movieRating) {
		this.movieRating = movieRating;
	}
}
