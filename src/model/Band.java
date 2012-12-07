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
@Table(name = "artist_band")
public class Band implements Serializable{
	private static final long serialVersionUID = 1519932537131693333L;
	/**
	 * 
	 */
	private Integer bandId;
	private String bandName;
	private String bandAlias;
	private Date bandDOB;
	private String bandNation;
	private MusicType musicType;
	private String bandStyle;
	private String bandLanguage;
	private Company company;
	private String bandWeb;
	private String bandPic;
	private User user;
	private Date recordDate;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getBandId() {
		return bandId;
	}
	public void setBandId(Integer bandId) {
		this.bandId = bandId;
	}
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	public String getBandAlias() {
		return bandAlias;
	}
	public void setBandAlias(String bandAlias) {
		this.bandAlias = bandAlias;
	}
	public Date getBandDOB() {
		return bandDOB;
	}
	public void setBandDOB(Date bandDOB) {
		this.bandDOB = bandDOB;
	}
	public String getBandNation() {
		return bandNation;
	}
	public void setBandNation(String bandNation) {
		this.bandNation = bandNation;
	}
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "typeId")
	public MusicType getMusicType() {
		return musicType;
	}
	public void setMusicType(MusicType musicType) {
		this.musicType = musicType;
	}
	public String getBandStyle() {
		return bandStyle;
	}
	public void setBandStyle(String bandStyle) {
		this.bandStyle = bandStyle;
	}
	public String getBandLanguage() {
		return bandLanguage;
	}
	public void setBandLanguage(String bandLanguage) {
		this.bandLanguage = bandLanguage;
	}
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name = "companyId")
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getBandWeb() {
		return bandWeb;
	}
	public void setBandWeb(String bandWeb) {
		this.bandWeb = bandWeb;
	}
	public String getBandPic() {
		return bandPic;
	}
	public void setBandPic(String bandPic) {
		this.bandPic = bandPic;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
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
