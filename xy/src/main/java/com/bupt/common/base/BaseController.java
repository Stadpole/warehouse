package com.bupt.common.base;

/**
 * Created by bupt626 on 17-4-14.
 */
public class BaseController extends BaseCommonController {
    protected int start = 0;
    protected int pageSize = 10;

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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    protected int currentPage = 1;

}
