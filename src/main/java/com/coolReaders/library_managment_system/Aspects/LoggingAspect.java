package com.coolReaders.library_managment_system.Aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

        @Before("execution(* com.coolReaders.library_managment_system.services.*.*(..)) && args(..)")
        public void logBeforeMethodExecution(JoinPoint joinPoint) {
            logger.info("Executing method: " + joinPoint.getSignature().getName() + 
                        " with arguments: " + Arrays.toString(joinPoint.getArgs()));
        }

        @AfterThrowing(pointcut = "execution(* com.coolReaders.library_managment_system.services.*.*(..))", throwing = "exception")
        public void logExceptions(JoinPoint joinPoint, Throwable exception) {
           logger.error("Exception in method: " + joinPoint.getSignature().getName() + 
                 " with message: " + exception.getMessage(), exception);
}

}
