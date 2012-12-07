package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity_pic")
public class ActivityPicComment {
	Integer commentId;
	Integer userId;
	Integer activityPicId;
	String commentContent;
	Date commentDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Integer getActivityPicId() {
		return activityPicId;
	}

	public void setActivityPicId(Integer activityPicId) {
		this.activityPicId = activityPicId;
	}

}