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
@Table(name = "artist_starring")
public class Starring implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1860183435884853783L;
	private int starId;
	private String starName;
	private String starAlias;
	private String starGender;
	private StarType starType;
	private Date starDOB;
	private Date starDOD;
	private String starBirthplace;
	private int starIMDB;
	private String starWeb;
	private String starFamily;
	private Zodiac zodiac;
	private String starBrief;
	private Company company;
	private String starPic;
	private User user;
	private Date recordDate;
	//private String starReward;
	
	
	
	
/*	public String getStarReward() {
		return starReward;
	}
	public void setStarReward(String starReward) {
		this.starReward = starReward;
	}*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getStarId() {
		return starId;
	}
	public void setStarId(int starId) {
		this.starId = starId;
	}
	public String getStarName() {
		return starName;
	}
	public void setStarName(String starName) {
		this.starName = starName;
	}
	public String getStarAlias() {
		return starAlias;
	}
	public void setStarAlias(String starAlias) {
		this.starAlias = starAlias;
	}
	public String getStarGender() {
		return starGender;
	}
	public void setStarGender(String starGender) {
		this.starGender = starGender;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "starTypeId")
	public StarType getStarType() {
		return starType;
	}
	public void setStarType(StarType starType) {
		this.starType = starType;
	}
	public Date getStarDOB() {
		return starDOB;
	}
	public void setStarDOB(Date starDOB) {
		this.starDOB = starDOB;
	}
	public Date getStarDOD() {
		return starDOD;
	}
	public void setStarDOD(Date starDOD) {
		this.starDOD = starDOD;
	}
	public String getStarBirthplace() {
		return starBirthplace;
	}
	public void setStarBirthplace(String starBirthplace) {
		this.starBirthplace = starBirthplace;
	}
	public int getStarIMDB() {
		return starIMDB;
	}
	public void setStarIMDB(int starIMDB) {
		this.starIMDB = starIMDB;
	}
	public String getStarWeb() {
		return starWeb;
	}
	public void setStarWeb(String starWeb) {
		this.starWeb = starWeb;
	}
	public String getStarFamily() {
		return starFamily;
	}
	public void setStarFamily(String starFamily) {
		this.starFamily = starFamily;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "zodiacId")
	public Zodiac getZodiac() {
		return zodiac;
	}
	public void setZodiac(Zodiac zodiac) {
		this.zodiac = zodiac;
	}
	public String getStarBrief() {
		return starBrief;
	}
	public void setStarBrief(String starBrief) {
		this.starBrief = starBrief;
	}
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "companyId")
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getStarPic() {
		return starPic;
	}
	public void setStarPic(String starPic) {
		this.starPic = starPic;
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
}
