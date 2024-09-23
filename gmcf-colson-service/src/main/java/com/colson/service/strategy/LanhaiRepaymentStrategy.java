package com.colson.service.strategy;

import com.colson.service.annotation.CapitalCode;
import com.colson.service.context.RepaymentContext;
import org.springframework.stereotype.Component;

@Component
@CapitalCode("C5002")
public class LanhaiRepaymentStrategy implements RepaymentStrategy {
    @Override
    public void repayment(RepaymentContext repaymentContext) {
        System.out.println(" lanhai repayment");
        // 蓝海银行还款业务逻辑
    }
}
