package ru.gb.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DoNotThrowNullPointerExceptionAspect {

    @Pointcut("@within(ru.gb.spring.aop.aspect.DoNotThrowNullPointerException)")
    public void classesAnnotatedWithPointcut(){

    }

    @Pointcut("@annotation(DoNotThrowNullPointerException)")
    public void methodAnnotatedWithPointcut(){

    }

    @Pointcut("classesAnnotatedWithPointcut() || methodAnnotatedWithPointcut()")
    public void classesOrMethodAnnotatedWithPointcut(){

    }

    @Around("classesOrMethodAnnotatedWithPointcut()")
    public  Object aroundAnnotatedMethodsPointcud(ProceedingJoinPoint pjp) throws Throwable {

        try{
            return pjp.proceed();

        } catch (NullPointerException e){
            String className = pjp.getTarget().getClass().getName();
            String methodName = pjp.getSignature().getName();

            String errorMessage = null;
            String defaultErrorMessage = extractAnnotationValue(pjp);
            if (defaultErrorMessage != null && !defaultErrorMessage.isBlank()) {
                errorMessage = defaultErrorMessage;

            }  else {
                errorMessage = e.getMessage();
            }


            log.error("mistake happened during the method {}#{} calling: {}", className, methodName, errorMessage);
            return null;

        }

    }


    private String extractAnnotationValue(ProceedingJoinPoint pjp){
        //достаем аннотацию из метода:

        MethodSignature signature = (MethodSignature) pjp.getSignature(); // signature -
        // это как бы описание нашего пойнтката
        java.lang.reflect.Method method = signature.getMethod(); // а из него достаем метод
        // далее из метода достаем аннотацию:
        DoNotThrowNullPointerException annotationOnMethod =
                method.getAnnotation(DoNotThrowNullPointerException.class);

        if (annotationOnMethod !=null) {
            return annotationOnMethod.value();

        }


        // если аннотации в методе нет, достаем ее из класса:

        Class<?> beanClass = pjp.getTarget().getClass();  // делаем только для класса
        DoNotThrowNullPointerException annotationOnClass =   //это объект аннотации
                beanClass.getAnnotation(DoNotThrowNullPointerException.class);   // это уже рефлексия

        if (annotationOnClass !=null) {
            return annotationOnClass.value();  // это value(), которая есть в самой аннотации                      }


        }

        return null;

    }

        }
