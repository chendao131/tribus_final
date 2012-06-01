package model;

import java.util.Date;

public class UserEvent {

	
	private int id;	
	private int resourceId;	
	private String eventName;	
	private Date createDate;	
	private boolean read;
	private int userId;	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
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
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
