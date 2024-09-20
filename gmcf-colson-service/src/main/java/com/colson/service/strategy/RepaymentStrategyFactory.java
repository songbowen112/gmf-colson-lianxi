package com.colson.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RepaymentStrategyFactory {

    @Autowired
    private Map<String, RepaymentStrategy> repaymentStrategyMap;

    public RepaymentStrategy getRepaymentStrategy(String repaymentType) {
        return repaymentStrategyMap.get(repaymentType);
    }
}
