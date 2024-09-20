package com.colson.service.decorator;

import com.colson.service.context.RepaymentContext;
import com.colson.service.template.RepaymentTemplate;

public abstract class RepaymentDecorator extends RepaymentTemplate {
    protected RepaymentTemplate decoratedTemplate;

    public RepaymentDecorator(RepaymentTemplate decoratedTemplate) {
        this.decoratedTemplate = decoratedTemplate;
    }

    @Override
    public void doProcess(RepaymentContext context) {
        decoratedTemplate.process(context);// 处理基础逻辑
        additionalProcess(context);// 扩展的功能
    }

    protected abstract void additionalProcess(RepaymentContext context);
}
