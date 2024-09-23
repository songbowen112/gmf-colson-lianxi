package com.colson.service.template;

import com.colson.service.annotation.CapitalCode;
import com.colson.service.context.RepaymentContext;
import com.colson.service.strategy.RepaymentStrategy;
import org.springframework.stereotype.Component;

@Component
@CapitalCode("C5102")
public class LanhaiRepaymentTemplate extends AbstractRepaymentTemplate {

    @Override
    protected void doProcess(RepaymentContext context) {
        // 蓝海银行具体还款逻辑
        System.out.println(" LanhaiRepaymentTemplate doProcess...");
    }
}
