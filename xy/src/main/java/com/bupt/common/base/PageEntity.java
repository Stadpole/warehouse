package com.bupt.common.base;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by bupt626 on 17-4-13.
 */
public class PageEntity<T> {
    @Expose
    private List<T> results;          // 鏁版嵁
    @Expose
    private long    totalResults;     // 鎬昏褰曟潯鏁�
    @Expose
    private int     totalPages;       // 鎬婚〉鏁�
    @Expose
    private int     start;            // 寮�濮嬬殑绱㈠紩鏁�
    @Expose
    private int     pageSize;         // 每页大小
    @Expose
    private int     currentPageLength; // 褰撳墠椤电殑璁板綍鏁�
    @Expose
    private int     currentPage;      // 当前页

    public PageEntity() {
    }

    public PageEntity(int start, int pageSize) {
        this.start = start;
        this.pageSize = pageSize;
    }
    public PageEntity(int start, int pageSize,int currentPage) {
        this.start = start;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages() {
        if (this.getPageSize() == 0) {
            this.totalPages = 1;
        } else {
            this.totalPages = (int) (this.totalResults / this.getPageSize() + ((this.totalResults % this.getPageSize()) == 0 ? 0
                    : 1));
            if (totalPages == 0)
                totalPages = 1;
        }

        this.totalPages = totalPages;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPageLength() {
        return currentPageLength;
    }

    public void setCurrentPageLength(int currentPageLength) {
        this.currentPageLength = currentPageLength;
    }

}
