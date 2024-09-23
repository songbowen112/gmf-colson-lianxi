package com.colson.service.strategy;

import com.colson.service.annotation.CapitalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RepaymentStrategyFactory {

    private final Map<String, RepaymentStrategy> strategyMap = new ConcurrentHashMap<>();

    @Autowired
    public RepaymentStrategyFactory(ApplicationContext context) {
        // 获取所有实现了 RepaymentStrategy 的 Bean
        Map<String, RepaymentStrategy> strategies = context.getBeansOfType(RepaymentStrategy.class);

        // 遍历所有策略类，检查是否有 CapitalCode 注解
        for (RepaymentStrategy strategy : strategies.values()) {
            CapitalCode capitalCode = strategy.getClass().getAnnotation(CapitalCode.class);
            if (capitalCode != null) {
                // 将资方编码与策略类映射
                strategyMap.put(capitalCode.value(), strategy);
            }
        }
    }

    public RepaymentStrategy getStrategy(String capitalCode) {
        return strategyMap.get(capitalCode);
    }
}
