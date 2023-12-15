package com.CNW.models.pagination;

public abstract class AbstractPaginationModel {
    Integer page = 1;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
