package com.loy.t1aop.aop;

import com.loy.t1aop.exception.ValidateException;
import com.loy.t1aop.validation.ParamValidator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

@Component
@Aspect
@RequiredArgsConstructor
public class ParamValidationAspect {

    private final ParamValidator validator;


    @Before("com.loy.t1aop.aop.CommonPointcuts.serviceLayer()")
    public void validate(JoinPoint joinPoint) throws NoSuchMethodException {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            Object target = joinPoint.getTarget(); // берем таргет объект, чтобы получить аннотации параметров метода
            if (Arrays.stream(args).anyMatch(Objects::isNull))
                throw new ValidateException("Невозможно определить метод, пока параметры равны null");
            Class<?>[] params = Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
            Method method = target.getClass().getMethod(joinPoint.getSignature().getName(), params);
            Annotation[][] annotations = method.getParameterAnnotations();
            IntStream.range(0, args.length).forEach(i -> validator.validate(args[i], annotations[i]));
        }
    }
}
