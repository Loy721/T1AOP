package com.loy.t1aop.validation;

import java.lang.annotation.Annotation;

public interface AnnotationParamValidator {

    Class<? extends Annotation> getAnnotationClass();

    void validate(Object param, Annotation annotation);
}
