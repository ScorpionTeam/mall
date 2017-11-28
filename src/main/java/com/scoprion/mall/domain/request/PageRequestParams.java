package com.scoprion.mall.domain.request;

/**
 * @author ycj
 * @version V1.0 <分页请求参数基类>
 * @date 2017-11-28 14:54
 */
public class PageRequestParams {
    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 数量
     */
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageRequestParams{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
