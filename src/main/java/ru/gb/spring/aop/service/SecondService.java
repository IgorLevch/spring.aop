package ru.gb.spring.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.spring.aop.aspect.DoNotThrowNullPointerException;

@Service
@Slf4j
public class SecondService {

    @DoNotThrowNullPointerException
    public String bar(String arg){
        if (arg ==null){
            throw new NullPointerException("Argument shouldn t be null");
        }

        log.info("Method #bar called out with an argument {} buhahah ", arg);

        return "Second service:  " + arg;

    }
}
