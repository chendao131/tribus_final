package model;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "book_mark")
public class BookMark implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 435706465571427346L;
	private Integer bookMarkId;
	private Book book;
	private Integer bookRead;
	private Integer bookLike;
	private Integer bookGrade;
	private User user;
	private Timestamp markDate;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getBookMarkId() {
		return bookMarkId;
	}
	public void setBookMarkId(Integer bookMarkId) {
		this.bookMarkId = bookMarkId;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name = "bookId")
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getBookRead() {
		return bookRead;
	}
	public void setBookRead(Integer bookRead) {
		this.bookRead = bookRead;
	}
	public Integer getBookLike() {
		return bookLike;
	}
	public void setBookLike(Integer bookLike) {
		this.bookLike = bookLike;
	}
	public Integer getBookGrade() {
		return bookGrade;
	}
	public void setBookGrade(Integer bookGrade) {
		this.bookGrade = bookGrade;
	}
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getMarkDate() {
		return markDate;
	}
	public void setMarkDate(Timestamp markDate) {
		this.markDate = markDate;
	}
}