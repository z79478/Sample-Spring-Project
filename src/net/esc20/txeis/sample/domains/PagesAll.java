package net.esc20.txeis.sample.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagesAll<E> implements Serializable{

	//This object is used to control the pagination for when all rows are
	//retrieved and the data spans multiple pages on the screen.
	//each PagesAll item is representative of a page on the screen
	//The pagesAll variable below, contains the rows that are associated with that page
	//in the PagesAll object.
	
	//i.e.  The PagesAll object retrieves 210 rows 
	//		if there are 30 rows per page this object will contain 7 items or 7 pages
	//		on each of the PagesAll object - there is a Page object that contains
	//		the 30 rows that exist on that page
	//		Therefore, PagesAll[1] contains a page object with rows 1-30
	//		PagesAll[2] contains a page object with rows 31-60, etc.

	private Integer currentPage = 0;
	private Integer totalPages = 0;
	//private Integer selectedPage = 0;
	private int totalItems;
    private int pageNumber;
    private int pagesAvailable;
	private Integer addedRow = 0;
	
	private static final long serialVersionUID = 4234990530517849914L;

	@SuppressWarnings("rawtypes")
	private List<Page> pages = new ArrayList<Page>();

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPagesAvailable(int pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

	@SuppressWarnings("rawtypes")
	public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPagesAvailable() {
        return pagesAvailable;
    }

	@SuppressWarnings("rawtypes")
	public List<Page> getPages() {
        return pages;
    }

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getAddedRow() {
		return addedRow;
	}

	public void setAddedRow(Integer addedRow) {
		this.addedRow = addedRow;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
