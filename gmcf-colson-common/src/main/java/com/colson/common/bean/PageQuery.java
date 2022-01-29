package com.colson.common.bean;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageQuery implements Serializable {
    private int page = 1;
    private int size = 10;
    private List<String> sort;

    public PageQuery() {
    }

    public PageQuery(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public Pageable toPageable() {
        int page = this.page;
        --page;
        page = page < 0 ? 0 : page;
        if (this.sort != null && !this.sort.isEmpty()) {
            List<Sort.Order> orderList = new ArrayList(this.sort.size());
            Iterator var3 = this.sort.iterator();

            while(var3.hasNext()) {
                String str = (String)var3.next();
                String[] strs = str.split(" ");
                orderList.add(new Sort.Order(strs.length == 2 ? Sort.Direction.valueOf(strs[1].toUpperCase()) : Sort.Direction.ASC, strs[0]));
            }

            Sort sort = Sort.by(orderList);
            return PageRequest.of(page, this.size, sort);
        } else {
            return PageRequest.of(page, this.size);
        }
    }

    public int getPage() {
        return this.page;
    }

    public int getSize() {
        return this.size;
    }

    public List<String> getSort() {
        return this.sort;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }
}
