package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Paging {
	
	
	//every page show how many items
	private static final int EVERY_PAGE_ITEM_NUM = 15;
	
	//how many page index like : previous page ,1,2,3,4 next page
	private static final int PAGING_SIZE = 10;

	@SuppressWarnings("unchecked")
	public PageObject getResult(List l, String url, int page) {

		List new_l = new ArrayList();
		StringBuffer pageCode = new StringBuffer("");

		BigDecimal b = new BigDecimal(l.size());
		BigDecimal c = new BigDecimal(EVERY_PAGE_ITEM_NUM);

		double count = (b.divide(c,RoundingMode.HALF_DOWN)).doubleValue();
		
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
			pageCode.append("<a href=" + url + "?page=" + (i+1) + "></a>");
		}

		if (page_bar_size <= 1 && count >PAGING_SIZE*EVERY_PAGE_ITEM_NUM && page+PAGING_SIZE <= count ) {
			pageCode.append("<a href=>next " + PAGING_SIZE + " page</a>");
		}

		pageCode.append("<input type=\"text\" name=\"page\" value=\"\" /> " + "<button> Go </button>");

		
		for (int i = (page-1)*EVERY_PAGE_ITEM_NUM; i <= page* EVERY_PAGE_ITEM_NUM-1; i++) {
			
			if(i>count*EVERY_PAGE_ITEM_NUM){
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
		
		for (int i = 0; i < 1105; i++) {
			l.add(i);
		}
		
		Paging p = new Paging();
		
		System.out.println(p.getResult(l, "/user/index.action",13).getPageCode());
		System.out.println(p.getResult(l, "/user/index.action",13).getL());		
	}

	class PageObject {
		private List l;
		private String pageCode;

		public List getL() {
			return l;
		}

		public void setL(List l) {
			this.l = l;
		}

		public String getPageCode() {
			return pageCode;
		}

		public void setPageCode(String pageCode) {
			this.pageCode = pageCode;
		}
	}
}
