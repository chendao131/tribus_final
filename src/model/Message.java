package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "message")
public class Message {
	
	/**
	 * inbox , outbox  
	 */
	private String messageType;
	
	private int  messageFromUserId;
	
	private int messageToUserId;
	
	private String messageContent;
	
	private boolean messageRead;
	
	private Date messageDate;
	
	private int messageId;
	
	private String messageTitle;
				
		
	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}	

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public boolean isMessageRead() {
		return messageRead;
	}

	public void setMessageRead(boolean messageRead) {
		this.messageRead = messageRead;
	}

	public int getMessageFromUserId() {
		return messageFromUserId;
	}

	public void setMessageFromUserId(int messageFromUserId) {
		this.messageFromUserId = messageFromUserId;
	}

	public int getMessageToUserId() {
		return messageToUserId;
	}

	public void setMessageToUserId(int messageToUserId) {
		this.messageToUserId = messageToUserId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	
	
}

