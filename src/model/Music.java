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
@Table(name = "music")
public class Music implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7316982153231046300L;
	private Integer musicId;
	private String musicName;
	private String musicAlias;
	private Singer singer;
	private Integer musicClassified;
	private MusicType musicType;
	private MusicVersion musicVersion;
	private MusicAudio musicAudio;
	private String musicPublishDate;
	private String musicPublisher;
	private Integer musicNum;
	private Integer musicCode;
	private String musicBrief;
	private String musicPic;
	private User user;
	private String recordDate;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getMusicId() {
		return musicId;
	}
	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getMusicAlias() {
		return musicAlias;
	}
	public void setMusicAlias(String musicAlias) {
		this.musicAlias = musicAlias;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "singerId")
	public Singer getSinger() {
		return singer;
	}
	public void setSinger(Singer singer) {
		this.singer = singer;
	}
	public Integer getMusicClassified() {
		return musicClassified;
	}
	public void setMusicClassified(Integer musicClassified) {
		this.musicClassified = musicClassified;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "typeId")
	public MusicType getMusicType() {
		return musicType;
	}
	public void setMusicType(MusicType musicType) {
		this.musicType = musicType;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "versionId")
	public MusicVersion getMusicVersion() {
		return musicVersion;
	}
	public void setMusicVersion(MusicVersion musicVersion) {
		this.musicVersion = musicVersion;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "audioId")
	public MusicAudio getMusicAudio() {
		return musicAudio;
	}
	public void setMusicAudio(MusicAudio musicAudio) {
		this.musicAudio = musicAudio;
	}
	public String getMusicPublishDate() {
		return musicPublishDate;
	}
	public void setMusicPublishDate(String musicPublishDate) {
		this.musicPublishDate = musicPublishDate;
	}
	public String getMusicPublisher() {
		return musicPublisher;
	}
	public void setMusicPublisher(String musicPublisher) {
		this.musicPublisher = musicPublisher;
	}
	public Integer getMusicNum() {
		return musicNum;
	}
	public void setMusicNum(Integer musicNum) {
		this.musicNum = musicNum;
	}
	public Integer getMusicCode() {
		return musicCode;
	}
	public void setMusicCode(Integer musicCode) {
		this.musicCode = musicCode;
	}
	public String getMusicBrief() {
		return musicBrief;
	}
	public void setMusicBrief(String musicBrief) {
		this.musicBrief = musicBrief;
	}
	public String getMusicPic() {
		return musicPic;
	}
	public void setMusicPic(String musicPic) {
		this.musicPic = musicPic;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
}
