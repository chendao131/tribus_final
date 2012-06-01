package vo;
import model.Book;

public class BookMarkVo extends Book implements Comparable<BookMarkVo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8109335007934182013L;
	
	private int bookGrade;

	public int getBookGrade() {
		return bookGrade;
	}

	public void setBookGrade(int bookGrade) {		
		this.bookGrade = bookGrade;
	}			
	
	public int compareTo(BookMarkVo o) {
		
		if( o.getBookId() == o.getBookId() ){
			if(
					this.getBookGrade() == o.getBookGrade() ||
					
					this.getBookGrade() - o.getBookGrade() < 2 ||
					
					this.getBookGrade() - o.getBookGrade() > -2						
			){
				return 0;			
			}else if(
					this.getBookGrade() - o.getBookGrade() > 2			
			){
				return -1;
			}else{
				return 1;
			}	
		}
		
		return -1;			
	}
	
	public static void main(String[] args){
		BookMarkVo bkv = new BookMarkVo();
//		bkv.g
	}
	
}	
