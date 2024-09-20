package com.colson.service.decorator;

import com.colson.service.context.RepaymentContext;
import com.colson.service.template.RepaymentTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoggingRepaymentDecorator extends RepaymentDecorator {

    public LoggingRepaymentDecorator(RepaymentTemplate decoratedTemplate) {
        super(decoratedTemplate);
    }

    @Override
    protected void additionalProcess(RepaymentContext context) {
        System.out.println("Additional process...");
    }
}
