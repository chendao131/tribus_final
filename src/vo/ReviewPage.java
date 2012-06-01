package vo;

import java.util.ArrayList;
import java.util.List;

public class ReviewPage {
	private int itemId;
	private String itemName;
	private String itemPic;
	private int commentId;
	private String commentTitle;
	private String commentContent;
	private String commentDate;
	private int userRate;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPic() {
		return itemPic;
	}
	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
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
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public int getUserRate() {
		return userRate;
	}
	public void setUserRate(int userRate) {
		this.userRate = userRate;
	}
}
