package com.colson.dal.bean;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchQuery implements Serializable {
    private List<Condition> conditions;
    private List<String> includeFields;
    private List<String> excludeFields;
    private PageQuery pageQuery;

    public SearchQuery() {
    }

    public void addCondition(Condition condition) {
        if (this.conditions == null) {
            this.conditions = new ArrayList();
        }

        this.conditions.add(condition);
    }

    public void addCondition(Condition... conditions) {
        Condition[] var2 = conditions;
        int var3 = conditions.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Condition condition = var2[var4];
            this.addCondition(condition);
        }

    }

    public SearchQuery include(String field) {
        if (StringUtils.isNotEmpty(field)) {
            if (this.includeFields == null) {
                this.includeFields = new ArrayList();
            }

            this.includeFields.add(field);
        }

        return this;
    }

    public SearchQuery exclude(String field) {
        if (StringUtils.isNotEmpty(field)) {
            if (this.excludeFields == null) {
                this.excludeFields = new ArrayList();
            }

            this.excludeFields.add(field);
        }

        return this;
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }

    public List<String> getIncludeFields() {
        return this.includeFields;
    }

    public List<String> getExcludeFields() {
        return this.excludeFields;
    }

    public PageQuery getPageQuery() {
        return this.pageQuery;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public void setIncludeFields(List<String> includeFields) {
        this.includeFields = includeFields;
    }

    public void setExcludeFields(List<String> excludeFields) {
        this.excludeFields = excludeFields;
    }

    public void setPageQuery(PageQuery pageQuery) {
        this.pageQuery = pageQuery;
    }
}
