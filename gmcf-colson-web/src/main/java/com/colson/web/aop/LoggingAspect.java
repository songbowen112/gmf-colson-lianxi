package com.colson.web.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(com.colson.service.annotation.ApplyLogging)") // 在有 @ApplyLogging 注解的方法上应用
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Before executing method: " + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        logger.info("After executing method: " + joinPoint.getSignature());
        return result;
    }
}