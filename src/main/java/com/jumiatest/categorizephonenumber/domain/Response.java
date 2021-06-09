package com.jumiatest.categorizephonenumber.domain;

import java.util.List;

public class Response {

    private List<CustomerDetail> customerDetailList;

    private int totalPage;

    private int PageNumber;

    private int size;

    public Response() {
    }

    public Response(List<CustomerDetail> customerDetailList, int totalPage, int pageNumber, int size) {
        this.customerDetailList = customerDetailList;
        this.totalPage = totalPage;
        PageNumber = pageNumber;
        this.size = size;
    }

    public List<CustomerDetail> getCustomerDetailList() {
        return customerDetailList;
    }

    public void setCustomerDetailList(List<CustomerDetail> customerDetailList) {
        this.customerDetailList = customerDetailList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
