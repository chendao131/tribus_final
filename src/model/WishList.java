package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wish_list")
public class WishList {
	private int id;
	private int resourceId;
	private Date createDate;
	private String type;
	private String wishListName;
	private UserProfile userProfile;
		
	
	@OneToOne
	@JoinColumn(name="userId")
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
			
	public String getWishListName() {
		return wishListName;
	}
	public void setWishListName(String wishListName) {
		this.wishListName = wishListName;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}		
			
	
}
