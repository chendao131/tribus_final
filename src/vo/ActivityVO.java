package vo;

import model.ActivityComment;

public class ActivityVO extends ActivityComment {
	
	public ActivityVO(ActivityComment ac){
		
		super.setActivityId(ac.getActivityId());
		super.setCommentContent(ac.getCommentContent());
		super.setCommentDate(ac.getCommentDate());
		super.setCommentId(ac.getCommentId());
		super.setUserId(ac.getUserId());
				
	}
	
	private String activityName;

	public String getActivityName() {						
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	
	
}
