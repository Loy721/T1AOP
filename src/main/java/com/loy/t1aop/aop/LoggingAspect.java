package com.loy.t1aop.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
@Slf4j
public class LoggingAspect { //TODO afterThrowing

    @Around("com.loy.t1aop.aop.CommonPointcuts.serviceLayer()")
    public Object loggingServicesMethodsAround(ProceedingJoinPoint pjp) throws Throwable {
        String methodName =  pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        log.info("Выполнение сервисного метода {} с аргументами {}", methodName, Arrays.toString(args));
        Object result = pjp.proceed();
        log.info("Сервисный метод {} выполнился с результатом {}", methodName, result);

        return result;
    }
}
