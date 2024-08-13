package com.loy.t1aop.validation.impl;


import com.loy.t1aop.exception.SizeValidateException;
import com.loy.t1aop.validation.AnnotationParamValidator;
import com.loy.t1aop.validation.annotation.Size;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
class SizeParamValidator implements AnnotationParamValidator {

    @Override
    public Class<Size> getAnnotationClass() {
        return Size.class;
    }

    @Override
    public void validate(Object param, Annotation annotation) {
        Size annotationSize = (Size) annotation;
        int max = annotationSize.max();
        int min = annotationSize.min();
        if (param instanceof CharSequence string) {
            if (string.length() < min || string.length() > max)
                throw new SizeValidateException("Длина строки \"" + param + "\" не входит в диапазон: " + min + "-" + max);
        } else
            throw new SizeValidateException("Параметр " + param + " не соответствует типу String");
    }
}
