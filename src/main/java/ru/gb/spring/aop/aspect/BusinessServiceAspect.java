package ru.gb.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
@Aspect
@Slf4j
public class BusinessServiceAspect {

    @Pointcut("execution (* ru.gb.spring.aop.service.BusinessService.*(..))")
    public void BusinessServiceMethodPointcut(){

    }

    @Before("BusinessServiceMethodPointcut()")
    public void beforeBusinessServiceMethodPointcut(JoinPoint jp){
        String arg = (String) jp.getArgs()[0];

        log.info("This is the BEFORE ASPECT {}", arg);
    }


    @AfterThrowing("BusinessServiceMethodPointcut()")
    public void afterThrowingBusinessServiceMethod(){
        log.info("Method is finished with a mistake");

    }

    @AfterReturning("BusinessServiceMethodPointcut()")
    public void afterReturningBusinessServiceMethod(){
        log.info("Method is finished");
    }

    @After("BusinessServiceMethodPointcut()")
    public void afterBusinessServiceMethod(JoinPoint jp){
        String arg = (String) jp.getArgs()[0];
        System.out.println(arg);
    }

    @Around(("BusinessServiceMethodPointcut()"))
    public Object aroundBusinessServiceMethod(ProceedingJoinPoint pjp)  {
        try{
            Object proceed = pjp.proceed();
            return proceed;
        } catch (NullPointerException e){
                log.error("mistake happenned during the method foo calling: {}", e);
               return null;
        } catch (Throwable e) {

        } return pjp;


    }




}
