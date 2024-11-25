package com.emard.aopwithrest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    //ici * *.*(..) mais peu etre type (void..) fully class name, method name, args
    //@Before("execution(* *.*(..))")
    @Before("execution(* com.emard.aopwithrest.service.JobService.*(..))")
    //on after (finaly) afterReturning (succes) afterThrowing (exception)
    //Around pour mesure par ex la durée d'exec dune method
    public void logMethodCall(JoinPoint jp){
        //jointpoint n'est pas obligatoire
        log.info("Method called {}",jp.getSignature().getName());
    }
}
