package util;

public class IntArrayToString {
	
	public static String arrayTostring(int[] a){
		StringBuffer buf = new StringBuffer();
		if(a.length == 0){
			return "";
		}
		for (int i = 0; i < a.length; i++) {
			if( i < a.length-1 ){buf.append(a[i]+",");}		
			else{
				buf.append(a[i]);
			}			
		}
		return buf.toString();
	}
	
	public static void main(String[] args){
		int[] a = new int[]{1,21,3};
		System.out.println(arrayTostring(a));
	}
}
