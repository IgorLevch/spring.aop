package ru.gb.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class HometaskServiceAspect {

    @Pointcut("execution (* ru.gb.spring.aop.service.HometaskService.*(..))")
    public void hometaskServicePointcut(){

    }

    @Before("hometaskServicePointcut()")
    public void beforeHometaskServicePointcut(JoinPoint jp){
       Object arg = (Object) jp.getArgs()[0];


        log.info("This is the BEFORE ASPECT {}", arg);


    }



    @After("hometaskServicePointcut()")
    public void afterHometaskServicePointcut(JoinPoint jp){
        Object arg = (Object) jp.getArgs()[0];


        log.info("This is the AFTER ASPECT {}", arg);



    }

}
