package com.loy.t1aop.validation.impl;

import com.loy.t1aop.exception.ValidateException;
import com.loy.t1aop.validation.AnnotationParamValidator;
import com.loy.t1aop.validation.ParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;

@Component
public class ParamValidatorImpl implements ParamValidator {

    @Autowired
    private List<AnnotationParamValidator> validators;


    @Override
    public void validate(Object param, Annotation[] annotationsParam) {
        if (param != null) {
            for (Annotation annotation : annotationsParam) {
                validators.stream().filter(v -> v.getAnnotationClass().equals(annotation.annotationType()))
                        .forEach(v -> v.validate(param, annotation));
            }
        } else
            throw new ValidateException("Аргумент null не может быть валидирован");
    }
}
