package com.colson.service.decorator;

import com.colson.service.context.RepaymentContext;
import com.colson.service.strategy.RepaymentStrategy;

/**
 * 还款装饰器基类
 */
public abstract class RepaymentDecorator implements RepaymentStrategy {

    protected final RepaymentStrategy decoratedRepaymentStrategy;

    public RepaymentDecorator(RepaymentStrategy decoratedRepaymentStrategy) {
        this.decoratedRepaymentStrategy = decoratedRepaymentStrategy;
    }

    @Override
    public void repayment(RepaymentContext context) {
        decoratedRepaymentStrategy.repayment(context);// 处理基础逻辑
    }

}
