package com.colson.service.decorator;

import com.colson.service.context.RepaymentContext;
import com.colson.service.strategy.RepaymentStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 日志装饰器
 */
@Component
public class LoggingRepaymentDecorator extends RepaymentDecorator {

    public LoggingRepaymentDecorator(@Qualifier("lanhaiRepaymentTemplate") RepaymentStrategy decoratedRepaymentStrategy) {
        super(decoratedRepaymentStrategy);
    }

    @Override
    public void repayment(RepaymentContext context) {
        System.out.println(" Logging before repayment...");
        super.repayment(context);
        System.out.println(" Logging after repayment...");

    }
}
