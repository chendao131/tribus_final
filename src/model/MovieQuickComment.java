package model;

import java.io.Serializable;
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
@Table(name="movie_quick_comment")
public class MovieQuickComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3638368088273655094L;
	private int quickCommentId;
	private User user;
	private String commentTitle;
	private String commentContent;
	private Date commentDate;
	private MovieComment comment;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getQuickCommentId() {
		return quickCommentId;
	}
	public void setQuickCommentId(int quickCommentId) {
		this.quickCommentId = quickCommentId;
	}
	@ManyToOne
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
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name = "commentId")
	public MovieComment getComment() {
		return comment;
	}
	public void setComment(MovieComment comment) {
		this.comment = comment;
	}
}
