package com.colson.service.template;

import com.colson.service.annotation.ApplyLogging;
import com.colson.service.annotation.CapitalCode;
import com.colson.service.context.RepaymentContext;
import org.springframework.stereotype.Component;

@Component
@CapitalCode("C5102")
public class LanhaiRepaymentTemplate extends AbstractRepaymentTemplate {

    @Override
//    @ApplyLogging // 这个方法会自动应用日志记录的装饰器
    protected void doProcess(RepaymentContext context) {
        // 蓝海银行具体还款逻辑
        System.out.println(" LanhaiRepaymentTemplate doProcess...");
    }
}
