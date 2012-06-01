package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_quick_comment")
public class BookQuickComment {
	private int quickCommentId;
	private User user;
	private String commentTitle;
	private String commentContent;
	private Date commentDate;
	private MusicComment comment;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getQuickCommentId() {
		return quickCommentId;
	}
	public void setQuickCommentId(int quickCommentId) {
		this.quickCommentId = quickCommentId;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "commentId")
	public MusicComment getComment() {
		return comment;
	}
	public void setComment(MusicComment comment) {
		this.comment = comment;
	}
}
