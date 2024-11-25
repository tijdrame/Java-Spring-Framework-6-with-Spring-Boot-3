package com.emard.aopwithrest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class PerformanceMonitorAspect {
    @Around("execution (* com.emard.aopwithrest.service.JobService.*(..))")
    public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {

        long start=System.currentTimeMillis();

        Object obj= jp.proceed();
        long end=System.currentTimeMillis();

        log.info("Time taken by: "+jp.getSignature().getName()+" "+(end-start)+" ms");
        return obj;
    }
}
