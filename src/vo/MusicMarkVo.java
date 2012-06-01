package vo;

import model.Music;

public class MusicMarkVo extends Music implements Comparable<MusicMarkVo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7001402463218026768L;
	
	private int musicGrade;
	
	
	
	public int getMusicGrade() {
		return musicGrade;
	}



	public void setMusicGrade(int musicGrade) {
		this.musicGrade = musicGrade;
	}



	/** 
	 *  1,2 same
	 *  1,3 diff
	 *  1,4 diff
	 *  1,5 diff
	 *  2,3 same
	 *  2,4 diff
	 *  2,5 diff
	 *  3,4 same
	 *  3,5 diff
	 *  4,5 same
	 */
	public int compareTo(MusicMarkVo o) {
		// TODO Auto-generated method stub
		
		
		if(
				this.getMusicGrade() == o.getMusicGrade() ||
				
				this.getMusicGrade() - o.getMusicGrade() < 1 ||
				
				this.getMusicGrade() - o.getMusicGrade() > -1						
		){
			return 0;			
		}else if(
				this.getMusicGrade() - o.getMusicGrade() > 1			
		){
			return -1;
		}else{
			return 1;
		}	
				
	}

}
