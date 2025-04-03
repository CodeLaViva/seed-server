package com.nullpointer.seed.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    
    @Around("@annotation(com.nullpointer.seed.annotation.MonitorPerformance)")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        
        if (executionTime > 1000) {
            log.warn("方法 {} 执行时间过长: {}ms", joinPoint.getSignature(), executionTime);
        }
        
        return result;
    }
}