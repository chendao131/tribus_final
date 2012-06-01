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
@Table(name = "music_track")
public class MusicTrack implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1917263577026917609L;
	private Integer trackId;
	private Music music;
	private String trackName;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getTrackId() {
		return trackId;
	}
	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "musicId")
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
}
