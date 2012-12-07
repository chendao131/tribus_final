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
@Table(name="movie_comment_draft")
public class MovieCommentDraft implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 466275079059331471L;
	private int draftId;
	private User user;
	private String draftTitle;
	private String draftContent;
	private Date draftDate;
	private Movie movie;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getDraftId() {
		return draftId;
	}
	public void setDraftId(int draftId) {
		this.draftId = draftId;
	}
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDraftTitle() {
		return draftTitle;
	}
	public void setDraftTitle(String draftTitle) {
		this.draftTitle = draftTitle;
	}
	public String getDraftContent() {
		return draftContent;
	}
	public void setDraftContent(String draftContent) {
		this.draftContent = draftContent;
	}
	public Date getDraftDate() {
		return draftDate;
	}
	public void setDraftDate(Date draftDate) {
		this.draftDate = draftDate;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name = "movieId")
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
