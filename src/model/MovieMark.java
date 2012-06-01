package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_mark")
public class MovieMark {
	private int movieMarkId;
	private int movieId;
	private byte[] movieWatch = new byte[4];
	private Integer movieLike;
	private int movieGrade;
	private int userId;
	private int movieTagId;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMovieMarkId() {
		return movieMarkId;
	}
	public void setMovieMarkId(int movieMarkId) {
		this.movieMarkId = movieMarkId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public byte[] getMovieWatch() {
		return movieWatch;
	}
	public void setMovieWatch(byte[] movieWatch) {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMovieTagId() {
		return movieTagId;
	}
	public void setMovieTagId(int movieTagId) {
		this.movieTagId = movieTagId;
	}
	
}
