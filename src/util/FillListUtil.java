package util;

import java.util.ArrayList;
import java.util.List;

public class FillListUtil<E> {
	public void fillList(int number,List<E> l){
		if(l.size() == 0){
			l = new ArrayList<E>();
			for (int i = 0; i < l.size(); i++) {
				l.add(null);				
			}
		}		
		if(l.size() < number){
			for (int i = 0; i < number; i++) {				
				l.add(null);			
			}
		}			
	}
	
	public static void main(String[] args){
		FillListUtil<String> f = new FillListUtil<String>();
		List l = new ArrayList();
		l.add("nijhao");
		f.fillList(20,l );
		
		System.out.println(123);
		System.out.println(123);
	}
}
