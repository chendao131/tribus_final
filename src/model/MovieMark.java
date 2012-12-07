package model;

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
@Table(name = "movie_mark")
public class MovieMark {
	private int movieMarkId;
	private Movie movie;
	private Integer movieWatch;
	private Integer movieLike;
	private int movieGrade;
	private User user;
	private int movieTagId;
	private Timestamp markDate;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMovieMarkId() {
		return movieMarkId;
	}
	public void setMovieMarkId(int movieMarkId) {
		this.movieMarkId = movieMarkId;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name = "movieId")
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Integer getMovieWatch() {
		return movieWatch;
	}
	public void setMovieWatch(Integer movieWatch) {
		this.movieWatch = movieWatch;
	}
	public Integer getMovieLike() {
		return movieLike;
	}
	public void setMovieLike(Integer movieLike) {
		this.movieLike = movieLike;
	}
	public int getMovieGrade() {
		return movieGrade;
	}
	public void setMovieGrade(int movieGrade) {
		this.movieGrade = movieGrade;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getMovieTagId() {
		return movieTagId;
	}
	public void setMovieTagId(int movieTagId) {
		this.movieTagId = movieTagId;
	}
	public Timestamp getMarkDate() {
		return markDate;
	}
	public void setMarkDate(Timestamp markDate) {
		this.markDate = markDate;
	}
	
}
