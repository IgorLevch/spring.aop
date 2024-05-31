package ru.gb.spring.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.spring.aop.aspect.DoNotThrowNullPointerException;

@Service
@Slf4j
public class BusinessService {


    @DoNotThrowNullPointerException("ERROR MESSAGE")
    public String foo(String arg){
        if (arg ==null){
            throw new NullPointerException("Argument shouldn t be null");
        }

        log.info("Method foo called out with an argument {} lalalal ", arg);

        return "Business service:  " + arg;

    }
}
