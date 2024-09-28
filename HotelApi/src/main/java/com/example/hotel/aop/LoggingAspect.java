package com.example.hotel.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Log method entry
    @Before("execution(* com.example.hotel..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().getName());
    }

    // Log method exit
    @AfterReturning(value = "execution(* com.example.hotel..*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        logger.info("Method " + joinPoint.getSignature().getName() + " executed successfully, return: " + result);
    }

    // Log exceptions
    @AfterThrowing(value = "execution(* com.example.hotel..*(..))", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method: " + joinPoint.getSignature().getName() + " Message: " + exception.getMessage());
    }

    // Around advice (if you need to log method execution time)
    @Around("execution(* com.example.hotel..*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("Method " + joinPoint.getSignature().getName() + " executed in: " + elapsedTime + "ms");
        return result;
    }
}
