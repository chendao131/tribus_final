package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "music_audio")
public class MusicAudio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4453272951646736260L;
	private Integer audioId;
	private String audioName;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getAudioId() {
		return audioId;
	}
	public void setAudioId(Integer audioId) {
		this.audioId = audioId;
	}
	public String getAudioName() {
		return audioName;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
}
