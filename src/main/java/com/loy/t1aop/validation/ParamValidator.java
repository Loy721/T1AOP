package com.loy.t1aop.validation;

import java.lang.annotation.Annotation;

public interface ParamValidator {

    void validate(Object param, Annotation[] annotations);
}
