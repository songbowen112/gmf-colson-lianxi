package com.colson.service.strategy;

import com.colson.service.context.RepaymentContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DefaultRepaymentStrategy implements RepaymentStrategy {
    @Override
    public void repayment(RepaymentContext repaymentContext) {
        System.out.println(" default repayment");
        // 默认还款策略
    }

}