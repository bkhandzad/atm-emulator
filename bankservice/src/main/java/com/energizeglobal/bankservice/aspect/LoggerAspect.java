package com.energizeglobal.bankservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggerAspect {
    private Logger logger = LoggerFactory.getLogger(Logger.class);
    @Pointcut("execution(* com.energizeglobal.bankservice.api.*.*(..))")
    public void apiCalls(){}

    @Before("apiCalls()")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Method:" + joinPoint.getSignature()+"Execution started");
    }

    @After("apiCalls()")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("Method:" + joinPoint.getSignature()+"Executed successfully");
    }
}
