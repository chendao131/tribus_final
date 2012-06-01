package model;

import java.io.Serializable;

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
@Table(name = "music_mark")
public class MusicMark implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1179895909084884485L;
	private Integer musicMarkId;
	private Music music;
	private Integer musicListen;
	private Integer musicLike;
	private Integer musicGrade;
	private User user;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getMusicMarkId() {
		return musicMarkId;
	}
	public void setMusicMarkId(Integer musicMarkId) {
		this.musicMarkId = musicMarkId;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "musicId")
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	public Integer getMusicListen() {
		return musicListen;
	}
	public void setMusicListen(Integer musicListen) {
		this.musicListen = musicListen;
	}
	public Integer getMusicLike() {
		return musicLike;
	}
	public void setMusicLike(Integer musicLike) {
		this.musicLike = musicLike;
	}
	public Integer getMusicGrade() {
		return musicGrade;
	}
	public void setMusicGrade(Integer musicGrade) {
		this.musicGrade = musicGrade;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
