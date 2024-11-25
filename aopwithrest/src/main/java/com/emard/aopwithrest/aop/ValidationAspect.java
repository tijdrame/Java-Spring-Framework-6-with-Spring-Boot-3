package com.emard.aopwithrest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ValidationAspect {
    @Around("execution (* com.emard.aopwithrest.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint jp, int postId) throws Throwable {
        if (postId<0) {
            log.info("PostId is negative, updating it");
            postId=-postId;
            log.info("new Value "+postId);
        }
        Object obj=jp.proceed(new Object[] {postId});
        return obj;
    }
}
