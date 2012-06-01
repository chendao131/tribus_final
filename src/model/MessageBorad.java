package model;

import java.util.Date;

public class MessageBorad {
	private String content;
	private int toId;
	private int fromId;
	private Date date;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
