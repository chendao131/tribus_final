package vo;

import java.util.Date;

public class MessageVO {
	/**
	 * inbox , outbox  
	 */
	private String type;
	
	private int  messageFromUserId;
	
	private int messageToUserId;
	
	private String messageContent;
	
	private boolean messageRead;
	
	private Date messageDate;
	
	private int messageId;
	
	private String messageTitle;
				 
	private String messageFromUserAlias;
	
	private String messageToUserAlias; 

	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public boolean isMessageRead() {
		return messageRead;
	}

	public void setMessageRead(boolean messageRead) {
		this.messageRead = messageRead;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageFromUserAlias() {
		return messageFromUserAlias;
	}

	public void setMessageFromUserAlias(String messageFromUserAlias) {
		this.messageFromUserAlias = messageFromUserAlias;
	}

	public String getMessageToUserAlias() {
		return messageToUserAlias;
	}

	public void setMessageToUserAlias(String messageToUserAlias) {
		this.messageToUserAlias = messageToUserAlias;
	}			
}
