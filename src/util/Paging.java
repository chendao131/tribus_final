package util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Paging<E> {
	
	private List<E> obj;
	
	private boolean haveOtherParameters;
	
	//every page show how many items
	private int every_page_item_num = 3;
	
	//how many page index like : previous page ,1,2,3,4 next page
	private static final int PAGING_SIZE = 10;

	
	public int getEvery_page_item_num() {
		return every_page_item_num;
	}

	public void setEvery_page_item_num(int every_page_item_num) {
		this.every_page_item_num = every_page_item_num;
	}

	public boolean isHaveOtherParameters() {
		return haveOtherParameters;
	}

	public void setHaveOtherParameters(boolean haveOtherParameters) {
		this.haveOtherParameters = haveOtherParameters;
	}

	public List<E> getObj() {
		return obj;
	}

	public void setObj(List<E> obj) {
		this.obj = obj;
	}

	@SuppressWarnings("unchecked")
	public PageObject getResult(String url, int page) {
		
		List l = this.obj;
		
		List new_l = new ArrayList();
		StringBuffer pageCode = new StringBuffer("");

		BigDecimal b = new BigDecimal(l.size());
		BigDecimal c = new BigDecimal(every_page_item_num);
		 
		
		double count = Math.ceil(b.divide(c,2,BigDecimal.ROUND_HALF_UP).doubleValue());
				
		if(page <0 || page >count){
			page = 1;
		}		
		int page_bar_size = page/PAGING_SIZE;		
		
		if (page_bar_size > 1) {
			pageCode.append("<a href=" + url + "?page=" + (page - PAGING_SIZE) + ">previous page</a>");
		}

		for (int i = page_bar_size*PAGING_SIZE; i < (page_bar_size+1)*PAGING_SIZE; i++) {			
			if(i>=count){				
				break;
			}			
			
			if(haveOtherParameters){
				pageCode.append("<a href=" + url + "&page=" + (i+1) + ">"+(i+1)+"</a>\n");
			}else{
				pageCode.append("<a href=" + url + "?page=" + (i+1) + ">"+(i+1)+"</a>\n");	
			}
			
		}

		if (page_bar_size <= 1 && count >PAGING_SIZE*every_page_item_num && page+PAGING_SIZE <= count ) {
			pageCode.append("<a href=>next " + PAGING_SIZE + " page</a>");
		}

		//pageCode.append("<input type=\"text\" name=\"page\" value=\"\" /> " + "<button> Go </button>");

		
		for (int i = (page-1)*every_page_item_num; i < page* every_page_item_num; i++) {
			
			if(i>count*every_page_item_num ||  i >= l.size() ){
				break;
			}
			new_l.add(l.get(i));
		}

		PageObject po = new PageObject();
		po.setL(new_l);
		po.setPageCode(pageCode.toString());

		return po;
	}

	public int getNum() {
		return 1;
	}

	public static void main(String[] args) {
		
		List<Integer> l = new ArrayList<Integer>();
		
		for (int i = 0; i < 9; i++) {
			l.add(i);
		}
		
		Paging<Integer> p = new Paging<Integer>();
		p.setObj(l);
		System.out.println(p.getResult("/user/index.action",2).getPageCode());
		System.out.println(p.getResult("/user/index.action",2).getL());		
	}	
}
