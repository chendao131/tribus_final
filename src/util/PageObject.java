package util;

import java.util.List;

public class PageObject<E> {
	private List<E> l;
	private String pageCode;

	public List<E> getL() {
		return l;
	}

	public void setL(List<E> l) {
		this.l = l;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
}
