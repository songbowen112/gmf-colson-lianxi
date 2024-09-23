package com.colson.service.template;

import com.colson.service.context.RepaymentContext;
import com.colson.service.strategy.RepaymentStrategy;

public abstract class AbstractRepaymentTemplate implements RepaymentStrategy {

    /**
     * 模板方法：定义还款流程的固定部分
      */
    public final void repayment(RepaymentContext context) {
        validated(context);             // 验证还款信息
        calculateInterest(context);     // 计算还款利息
        calculateFee(context);          // 计算还款费用
        doProcess(context);             // 不同资方具体业务逻辑处理
        postRepayment(context);         // 还款后的处理
    }

    /**
     * 钩子方法：允许子类实现的步骤
     * @param context
     */
    protected abstract void doProcess(RepaymentContext context);

    protected void validated(RepaymentContext context) {
        // 默认的还款验证逻辑，可以在子类中覆盖
        System.out.println(" validating repayment param...");
    }

    protected void calculateInterest(RepaymentContext context) {
        // 默认的计算还款利息逻辑，可以在子类中覆盖
        System.out.println(" calculating interest...");
    }

    protected void calculateFee(RepaymentContext context) {
        // 默认的计算还款费用逻辑，可以在子类中覆盖
        System.out.println(" calculating fee...");
    }

    protected void postRepayment(RepaymentContext context) {
        // 默认的还款后处理逻辑，可以在子类中覆盖
        System.out.println(" processing post repayment...");
    }
}
