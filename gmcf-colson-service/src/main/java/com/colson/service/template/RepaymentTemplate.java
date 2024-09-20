package com.colson.service.template;

import com.colson.service.context.RepaymentContext;

public abstract class RepaymentTemplate {

    public void process(RepaymentContext context) {
        validated(context);
        calculateInterest(context);
        calculateFee(context);
        doProcess(context);// 具体业务交给策略处理
        logTransaction(context);
    }

    private void logTransaction(RepaymentContext context) {
        System.out.println(" logging transaction...");
    }

    private void calculateFee(RepaymentContext context) {
        System.out.println(" calculating fee...");
    }

    private void calculateInterest(RepaymentContext context) {
        System.out.println(" calculating interest...");
    }

    private void validated(RepaymentContext context) {
        System.out.println(" validating repayment param...");
    }

    protected abstract void doProcess(RepaymentContext context);
}
