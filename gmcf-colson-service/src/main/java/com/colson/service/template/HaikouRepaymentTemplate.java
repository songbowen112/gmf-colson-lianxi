package com.colson.service.template;

import com.colson.service.annotation.CapitalCode;
import com.colson.service.context.RepaymentContext;
import com.colson.service.strategy.RepaymentStrategy;
import org.springframework.stereotype.Component;

@Component
@CapitalCode("C5103")
public class HaikouRepaymentTemplate extends AbstractRepaymentTemplate {

    @Override
    protected void doProcess(RepaymentContext context) {
        // 海口银行具体还款逻辑
        System.out.println(" HaikouRepaymentTemplate doProcess...");
    }
}
