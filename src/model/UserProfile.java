package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="user_profile")
public class UserProfile {
		
	private int profId;	
	private int profAge;
	private Date profDob;
	private int profGender;// 0 : male; 1 : female
	private int profMa; // 0:yes ; 1:no
	private String profOccuId; // occupay desc;
	private String profHobby;
	private String profReligion;
	private String profFaceAccount;
	private String profFacePw;
	private String proTel;
	private String profSecQues;
	private String profSecAnsw;	
	private String profCity;			
	
	public String getProfCity() {
		return profCity;
	}
	public void setProfCity(String profCity) {
		this.profCity = profCity;
	}
	
	//	@MapsId
//	@OneToOne
//	@JoinColumn(name = "userId")
	private User user;
	
	//@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	//@JoinColumn(name = "userId")
	//private User user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)		
	public int getProfId() {
		return profId;
	}
	public void setProfId(int profId) {
		this.profId = profId;
	}
			    
	@OneToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getProfAge() {
		return profAge;
	}
	public void setProfAge(int profAge) {
		this.profAge = profAge;
	}
	public Date getProfDob() {
		return profDob;
	}
	public void setProfDob(Date profDob) {
		this.profDob = profDob;
	}
	public int getProfGender() {
		return profGender;
	}
	public void setProfGender(int profGender) {
		this.profGender = profGender;
	}
	public int getProfMa() {
		return profMa;
	}
	public void setProfMa(int profMa) {
		this.profMa = profMa;
	}
	public String getProfOccuId() {
		return profOccuId;
	}
	public void setProfOccuId(String profOccuId) {
		this.profOccuId = profOccuId;
	}
	public String getProfHobby() {
		return profHobby;
	}
	public void setProfHobby(String profHobby) {
		this.profHobby = profHobby;
	}
	public String getProfReligion() {
		return profReligion;
	}
	public void setProfReligion(String profReligion) {
		this.profReligion = profReligion;
	}
	public String getProfFaceAccount() {
		return profFaceAccount;
	}
	public void setProfFaceAccount(String profFaceAccount) {
		this.profFaceAccount = profFaceAccount;
	}
	public String getProfFacePw() {
		return profFacePw;
	}
	public void setProfFacePw(String profFacePw) {
		this.profFacePw = profFacePw;
	}
	public String getProTel() {
		return proTel;
	}
	public void setProTel(String proTel) {
		this.proTel = proTel;
	}
	public String getProfSecQues() {
		return profSecQues;
	}
	public void setProfSecQues(String profSecQues) {
		this.profSecQues = profSecQues;
	}
	public String getProfSecAnsw() {
		return profSecAnsw;
	}
	public void setProfSecAnsw(String profSecAnsw) {
		this.profSecAnsw = profSecAnsw;
	}
	
	
	
	
	
	
}
