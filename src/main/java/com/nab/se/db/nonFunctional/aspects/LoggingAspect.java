
package com.nab.se.db.nonFunctional.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(@com.nab.se.db.nonFunctional.aspects.annotations.LogMe * *.*(..))")
    void annotatedClass() {
        //pointcut for annotated classes
    }

    @Pointcut("execution(* (@com.nab.se.db.nonFunctional.aspects.annotations.LogMe *).*(..))")
    void annotatedMethod() {
        //pointcut for annotated methods
    }


    @Around("annotatedClass() || annotatedMethod()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        StringBuffer loggedMessage = new StringBuffer();
        loggedMessage.append(getMethodSignatureLogMessage(point));
        loggedMessage.append(getMethodArgumentsLogMessage(point));
        Object result;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            loggedMessage.append(getExceptionLogMessage(throwable));
            LOGGER.severe(loggedMessage.toString());
            throw throwable;
        }
        loggedMessage.append(getReturnValueLogMessage(point, result));
        loggedMessage.append(getExecutionTimeLogMessage(start));
        LOGGER.info(loggedMessage.toString());
        return result;
    }

    private String getExceptionLogMessage(Throwable throwable) {
        return String.format("exception has happened: %s", throwable.getMessage());
    }

    private String getExecutionTimeLogMessage(long start) {
        return String.format("and execution time: %s MilliSeconds", System.currentTimeMillis() - start);
    }

    private String getMethodSignatureLogMessage(ProceedingJoinPoint point) {
        return String.format("calling %s \n", point.getSignature().toLongString());
    }

    private String getReturnValueLogMessage(ProceedingJoinPoint point, Object result) {
        return String.format("return type %s and value %s. \n", point.getSignature().toLongString(),
                result != null ? result.toString() : "null");
    }

    private String getMethodArgumentsLogMessage(ProceedingJoinPoint point) {
        Object[] arguments = point.getArgs();
        StringBuffer loggedMessage = new StringBuffer();
        Arrays.stream(arguments).filter(Objects::nonNull).forEach(argument -> {
            loggedMessage.append(String.format("With argument of type %s and value %s. \n", argument.getClass().toString(), argument.toString()));
        });
        return loggedMessage.toString();
    }
}