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
@Table(name = "book_comment")
public class BookComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4436406397836602963L;
	private int commentId;
	private User user;
	private String commentTitle;
	private String commentContent;
	private Date commentDate;
	private Book book;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	@JoinColumn(name = "bookId")
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
