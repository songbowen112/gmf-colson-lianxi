package com.colson.dal.dto;

import com.colson.dal.common.biz.pkg.BaseDTO;

public class FeeDto extends BaseDTO {
    private String profitRate;
    private String comprehensiveRate;

    public String getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(String profitRate) {
        this.profitRate = profitRate;
    }

    public String getComprehensiveRate() {
        return comprehensiveRate;
    }

    public void setComprehensiveRate(String comprehensiveRate) {
        this.comprehensiveRate = comprehensiveRate;
    }

    @Override
    public String toString() {
        return "FeeDto{" +
                "profitRate='" + profitRate + '\'' +
                ", comprehensiveRate='" + comprehensiveRate + '\'' +
                '}';
    }
}
