package vo;

import model.Movie;

public class MovieMarkVo extends Movie implements Comparable<MovieMarkVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2526250805802984288L;
	
	private int movieGrade;
	
	
	

	public int getMovieGrade() {
		return movieGrade;
	}




	public void setMovieGrade(int movieGrade) {
		this.movieGrade = movieGrade;
	}




	public int compareTo(MovieMarkVo o) {
		// TODO Auto-generated method stub				
		if(
				this.getMovieGrade() == o.getMovieGrade() ||
				
				this.getMovieGrade() - o.getMovieGrade() < 2 ||
				
				this.getMovieGrade() - o.getMovieGrade() > -2						
		){
			return 0;			
		}else if(
				this.getMovieGrade() - o.getMovieGrade() > 2			
		){
			return -1;
		}else{
			return 1;
		}		
		
	}
	
	

}
