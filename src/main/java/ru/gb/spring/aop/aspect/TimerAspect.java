package ru.gb.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("@annotation(Timer)")
    public void methodTimer(){

    }

    @Around("methodTimer()")
    public  Object aroundTimerPointcud(ProceedingJoinPoint pjp) throws Throwable {
       float m = System.currentTimeMillis();
         pjp.proceed();
        log.info("Time took place: "+ (System.currentTimeMillis()-m));
       //System.out.println("Time took place: "+ (System.currentTimeMillis()-m));

        return pjp.proceed();
    }


}
