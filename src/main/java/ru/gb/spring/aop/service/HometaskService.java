package ru.gb.spring.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gb.spring.aop.aspect.Timer;

@Component
@Slf4j
public class HometaskService {

    String f;
    @Timer
    public String returnSmth(int a){
        a=a*2;
        f =Integer.toString(a);
        return f;

    }
    @Timer
    public void doSmth(String f){
        f=f+3;
        System.out.println("in a previous method it was: "+f);
    }

}
