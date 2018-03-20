package net.esc20.txeis.sample.models;

import net.esc20.txeis.sample.domains.Employee;
import net.esc20.txeis.sample.domains.Page;

public class PagingModel {

	Page<Employee> page = new Page<Employee>();
	int currentPage = 0;
	int newPage = 0;
	int maxPages = 0;
	int prevPage = 0;
	int nextPage = 0;
		
	public Page<Employee> getPage() {
		return page;
	}

	public void setPage(Page<Employee> page) {
		this.page = page;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNewPage() {
		return newPage;
	}

	public void setNewPage(int newPage) {
		this.newPage = newPage;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	
}
