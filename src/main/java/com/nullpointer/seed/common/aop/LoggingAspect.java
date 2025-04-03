package com.nullpointer.seed.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.nullpointer.seed.controllers.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("Method {} executed in {}ms", joinPoint.getSignature(), elapsedTime);
            return result;
        } catch (Exception e) {
            log.error("Exception in {}", joinPoint.getSignature(), e);
            throw e;
        }
    }
}
