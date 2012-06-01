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
@Table(name = "artist_singer")
public class Singer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5252778496917209024L;
	private Integer singerId;
	private String singerName;
	private Integer singerGender;
	private Band band;
	private String singerDOB;
	private String singerDOD;
	private String singerNation;
	private MusicType musicType;
	private String singerStyle;
	private String singerAlias;
	private String singerLanguage;
	private Company company;
	private String singerInstrument;
	private String singerWeb;
	private String singerBrief;
	private String singerPic;
	private User user;
	private String recordDate;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getSingerId() {
		return singerId;
	}
	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public Integer getSingerGender() {
		return singerGender;
	}
	public void setSingerGender(Integer singerGender) {
		this.singerGender = singerGender;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "bandId")
	public Band getBand() {
		return band;
	}
	public void setBand(Band band) {
		this.band = band;
	}
	public String getSingerDOB() {
		return singerDOB;
	}
	public void setSingerDOB(String singerDOB) {
		this.singerDOB = singerDOB;
	}
	public String getSingerDOD() {
		return singerDOD;
	}
	public void setSingerDOD(String singerDOD) {
		this.singerDOD = singerDOD;
	}
	public String getSingerNation() {
		return singerNation;
	}
	public void setSingerNation(String singerNation) {
		this.singerNation = singerNation;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "typeId")
	public MusicType getMusicType() {
		return musicType;
	}
	public void setMusicType(MusicType musicType) {
		this.musicType = musicType;
	}
	public String getSingerStyle() {
		return singerStyle;
	}
	public void setSingerStyle(String singerStyle) {
		this.singerStyle = singerStyle;
	}
	public String getSingerAlias() {
		return singerAlias;
	}
	public void setSingerAlias(String singerAlias) {
		this.singerAlias = singerAlias;
	}
	public String getSingerLanguage() {
		return singerLanguage;
	}
	public void setSingerLanguage(String singerLanguage) {
		this.singerLanguage = singerLanguage;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "companyId")
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getSingerInstrument() {
		return singerInstrument;
	}
	public void setSingerInstrument(String singerInstrument) {
		this.singerInstrument = singerInstrument;
	}
	public String getSingerWeb() {
		return singerWeb;
	}
	public void setSingerWeb(String singerWeb) {
		this.singerWeb = singerWeb;
	}
	public String getSingerBrief() {
		return singerBrief;
	}
	public void setSingerBrief(String singerBrief) {
		this.singerBrief = singerBrief;
	}
	public String getSingerPic() {
		return singerPic;
	}
	public void setSingerPic(String singerPic) {
		this.singerPic = singerPic;
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
