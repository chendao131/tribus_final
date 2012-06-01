package vo;

public class BookReview {
	private Integer userId;
	private String userPic;
	private String userName;
	private Integer bookId;
	private String bookPic;
	private String commentTitle;
	private String commentContent;
	private Integer bookRating;
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

	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookPic() {
		return bookPic;
	}
	public void setBookPic(String bookPic) {
		this.bookPic = bookPic;
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
	public Integer getBookRating() {
		return bookRating;
	}
	public void setBookRating(Integer bookRating) {
		this.bookRating = bookRating;
	}
}
