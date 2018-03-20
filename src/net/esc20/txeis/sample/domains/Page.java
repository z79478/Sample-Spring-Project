package net.esc20.txeis.sample.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<E> implements Serializable{

	private static final long serialVersionUID = 4234990530517849914L;
	
	private int totalItems;
    private int pageNumber;
    private int pagesAvailable;
  	
    private List<E> pageItems = new ArrayList<E>();

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPagesAvailable(int pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

    public void setPageItems(List<E> pageItems) {
        this.pageItems = pageItems;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPagesAvailable() {
        return pagesAvailable;
    }

    public List<E> getPageItems() {
        return pageItems;
    }

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
