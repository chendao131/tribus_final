package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vo.Ele;



public class testPage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List l = new ArrayList();
		Integer i = new Integer(1);
		int watershed = 5;// ÿҳ����
		int pageNumbers;// һ����ҳ

		l.add(i);
		l.add(i);
		l.add(i);
		l.add(i);
		l.add(i);
		l.add(i);
		l.add(i);
		l.add(i);

		if (l.size() % watershed == 0) {
			pageNumbers = (int) l.size() / watershed;// ���û������������£���ȡ��
		} else {
			pageNumbers = (int) l.size() / watershed + 1;// ���������������£���ȡ��+1
		}
		System.out.println(pageNumbers);
		List resultList = new ArrayList();// �����
		for (int p = 0; p < pageNumbers; p++) {

			if (p + 1 == pageNumbers) {// ���һҳ

				Iterator iterator1 = l.subList(p * 5, l.size()).iterator();// ��ȡ���һҳ
				while (iterator1.hasNext()) {
					Ele e = new Ele();
					e.setEle(iterator1.next());
					e.setPageNumber(p + 1);
					resultList.add(e);
				}
			} else {
				Iterator iterator = l.subList(p * 5, (p + 1) * 5).iterator();// from
																				// ���һҳ֮ǰ��
				while (iterator.hasNext()) {
					Ele e = new Ele();
					e.setEle(iterator.next());
					e.setPageNumber(p + 1);
					resultList.add(e);
				}
			}
		}

	}

}
