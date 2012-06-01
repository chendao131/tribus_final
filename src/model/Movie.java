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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "movie")
public class Movie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1523016625960386020L;
	private int movieId;	
	private String movieNameOriginal;
	private String movieNameEn;
	private String movieNameChn;
	private String movieNameAlias;
	private int movieIMDB;
	private Set<Starring> stars = new HashSet<Starring>();
	private Set<MovieTag> tags = new HashSet<MovieTag>();
	private String movieWeb;
	private String movieRegion;
	private String movieLanguage;
	private Date movieDate;
	private int movieTime;
	private Byte[] movieView;
	private String movieDemo;
	private String movieBrief;
	private User user;
	private Date recordDate;
	private String moviePic;
	private String movieRating;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieNameOriginal() {
		return movieNameOriginal;
	}
	public void setMovieNameOriginal(String movieNameOriginal) {
		this.movieNameOriginal = movieNameOriginal;
	}
	public String getMovieNameEn() {
		return movieNameEn;
	}
	public void setMovieNameEn(String movieNameEn) {
		this.movieNameEn = movieNameEn;
	}
	public String getMovieNameChn() {
		return movieNameChn;
	}
	public void setMovieNameChn(String movieNameChn) {
		this.movieNameChn = movieNameChn;
	}
	public String getMovieNameAlias() {
		return movieNameAlias;
	}
	public void setMovieNameAlias(String movieNameAlias) {
		this.movieNameAlias = movieNameAlias;
	}
	public int getMovieIMDB() {
		return movieIMDB;
	}
	public void setMovieIMDB(int movieIMDB) {
		this.movieIMDB = movieIMDB;
	}
	@ManyToMany
	@JoinTable(name="movie_movie_starring", 
		joinColumns={@JoinColumn(name="movieId")},
		inverseJoinColumns={@JoinColumn(name="starId")}
	)
	public Set<Starring> getStars() {
		return stars;
	}
	public void setStars(Set<Starring> stars) {
		this.stars = stars;
	}
	@ManyToMany
	@JoinTable(name="movie_movie_tag", 
		joinColumns={@JoinColumn(name="movieId")},
		inverseJoinColumns={@JoinColumn(name="tagId")}
	)
	public Set<MovieTag> getTags() {
		return tags;
	}
	public void setTags(Set<MovieTag> tags) {
		this.tags = tags;
	}
	public String getMovieWeb() {
		return movieWeb;
	}
	public void setMovieWeb(String movieWeb) {
		this.movieWeb = movieWeb;
	}
	public String getMovieRegion() {
		return movieRegion;
	}
	public void setMovieRegion(String movieRegion) {
		this.movieRegion = movieRegion;
	}
	public String getMovieLanguage() {
		return movieLanguage;
	}
	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}
	public Date getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(Date movieDate) {
		this.movieDate = movieDate;
	}
	public int getMovieTime() {
		return movieTime;
	}
	public void setMovieTime(int movieTime) {
		this.movieTime = movieTime;
	}
	public Byte[] getMovieView() {
		return movieView;
	}
	public void setMovieView(Byte[] movieView) {
		this.movieView = movieView;
	}
	public String getMovieDemo() {
		return movieDemo;
	}
	public void setMovieDemo(String movieDemo) {
		this.movieDemo = movieDemo;
	}
	public String getMovieBrief() {
		return movieBrief;
	}
	public void setMovieBrief(String movieBrief) {
		this.movieBrief = movieBrief;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
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
	public String getMoviePic() {
		return moviePic;
	}
	public void setMoviePic(String moviePic) {
		this.moviePic = moviePic;
	}
	public String getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}
}