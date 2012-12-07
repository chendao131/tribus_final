package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7704973177320458729L;
	private int bookId;
	private String bookName;
	private String bookSubtitle;
	private String bookISBN;
	private String bookAuthor;
	private String bookTranslator;
	private double bookPrice;
	private String bookPublisher;
	private Date bookPublishDate;
	private int bookPages;
	private String bookBrief;
	private String bookPic;
	private Set<BookTag> tags = new HashSet<BookTag>();
	private User user;
	private Date recordDate;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookSubtitle() {
		return bookSubtitle;
	}
	public void setBookSubtitle(String bookSubtitle) {
		this.bookSubtitle = bookSubtitle;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookTranslator() {
		return bookTranslator;
	}
	public void setBookTranslator(String bookTranslator) {
		this.bookTranslator = bookTranslator;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public Date getBookPublishDate() {
		return bookPublishDate;
	}
	public void setBookPublishDate(Date bookPublishDate) {
		this.bookPublishDate = bookPublishDate;
	}
	public int getBookPages() {
		return bookPages;
	}
	public void setBookPages(int bookPages) {
		this.bookPages = bookPages;
	}
	public String getBookBrief() {
		return bookBrief;
	}
	public void setBookBrief(String bookBrief) {
		this.bookBrief = bookBrief;
	}
	public String getBookPic() {
		return bookPic;
	}
	public void setBookPic(String bookPic) {
		this.bookPic = bookPic;
	}
	@ManyToMany
	@JoinTable(name="book_book_tag", 
		joinColumns={@JoinColumn(name="bookId")},
		inverseJoinColumns={@JoinColumn(name="tagId")}
	)
	public Set<BookTag> getTags() {
		return tags;
	}
	public void setTags(Set<BookTag> tags) {
		this.tags = tags;
	}
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}


}
