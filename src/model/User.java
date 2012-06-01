package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_account")
public class User implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1523016625960386020L;
	
	
	private int userId;	
	private String userAlias;
	private String userEmail;	
	private String userPic;	
	private String userPw;		
	private int userState;
	private int userVerifycode;
	private Date createDate;
	
	
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUserId() {
		return userId;
	}	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	public String getUserAlias() {
		return userAlias;
	}
	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public int getUserVerifycode() {
		return userVerifycode;
	}
	public void setUserVerifycode(int userVerifycode) {
		this.userVerifycode = userVerifycode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	@Override
	public String toString(){
		return "userId: "+ this.userId +"\n"+
		"alias: "+ this.userAlias +"\n"+
		"email: "+ this.userEmail +"\n"+
		"password: "+ this.userPw +"\n"+
		"pic: "+ this.userPic +"\n"+
		"status: "+ this.userState +"\n"+
		"verifycode: "+ this.userVerifycode +"\n";
	}
}
